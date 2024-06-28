package com.distribuida.authors.rest;

import java.util.List;

import com.distribuida.authors.db.Author;
import com.distribuida.authors.repo.AuthorRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;



@Path("/authors")


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class AuthorRest {
    
    // @Inject solo funcionara entre componentes cdi, se tiene que poner
    // @ApplicationScoped

    @Inject
    AuthorRepository repo;

    @GET
    public List<Author> findAll(){
        return repo.findAll().list();
    }


    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id){
        var op = repo.findByIdOptional(id);
        return op.isEmpty() ? Response.status(Response.Status.NOT_FOUND).build(): Response.ok(op.get()).build();
    }

    @POST
    public Response create(Author author){
        repo.persist(author);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id,Author author){

        // al manejar asi se van a actualizar sin un update
        var obj = repo.findById(id);

        obj.setId(author.getId());
        obj.setFirstName(author.getFirstName());
        obj.setLastName(author.getLastName());
        

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id){

        repo.delete(repo.findById(id));
        return Response.ok().build();
    }

}
