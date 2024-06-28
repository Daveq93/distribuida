package com.distribuida.books.clients;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.distribuida.books.to.AuthorDto;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
// @RegisterRestClient(baseUri = "http://localhost:9090")
// se va al archivo de configuracion
@RegisterRestClient(baseUri = "stork://my-service")
public interface AuthorRestClient {
 


    @GET
    @Path("/{id}")
    public AuthorDto findById(@PathParam("id") Integer id);

    // @POST
    // public Response create(Author author){
    //     repo.persist(author);
    //     return Response.status(Response.Status.CREATED).build();
    // }

    // @PUT
    // @Path("/{id}")
    // public Response update(@PathParam("id") Integer id,Author author){

    //     // al manejar asi se van a actualizar sin un update
    //     var obj = repo.findById(id);

    //     obj.setId(author.getId());
    //     obj.setFirstName(author.getFirstName());
    //     obj.setLastName(author.getLastName());
        

    //     return Response.ok().build();
    // }

    // @DELETE
    // @Path("/{id}")
    // public Response delete(@PathParam("id") Integer id){

    //     repo.delete(repo.findById(id));
    //     return Response.ok().build();
    // }

}
