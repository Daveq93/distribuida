package com.distribuida.books.rest;

import java.util.List;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.distribuida.books.clients.AuthorRestClient;
import com.distribuida.books.db.Book;
import com.distribuida.books.repo.BooksRepository;
import com.distribuida.books.to.BookDto;

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

@Path("/books")

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class BookRest {

    // @Inject solo funcionara entre componentes cdi, se tiene que poner
    // @ApplicationScoped

    @Inject
    BooksRepository repo;

    @Inject
    @RestClient
    AuthorRestClient authorRest;

    @GET
    public List<BookDto> findAll() {
        var books = repo.findAll().list();
        
        // RestClientBuilder.newBuilder().baseUrl(new URL("http://localhost:9090")).build(AuthorRestClient.class)
        
        return books.stream()
                .map(book -> {
                    // buscar el autor obj.getAuthorId()
                    // conectarse al servicio 'authors-app'
                    // http://localhost:8080/authors/{id}

                    var author = authorRest.findById(book.getAuthorId());

                    BookDto dto = new BookDto();
                    dto.setId(book.getId());
                    dto.setTitle(book.getTitle());
                    dto.setIsbn(book.getIsbn());
                    dto.setPrice(book.getPrice());

                    dto.setAuthorName(author.getFirstName());
                    System.out.println("buscando libro");

                    return dto;

                    // dto.setAuthorName(...);
                }).toList();

    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        var op = repo.findByIdOptional(id);
        return op.isEmpty() ? Response.status(Response.Status.NOT_FOUND).build() : Response.ok(op.get()).build();
    }

    @POST
    public Response create(Book book) {
        repo.persist(book);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Book book) {

        // al manejar asi se van a actualizar sin un update
        var obj = repo.findById(id);

        obj.setIsbn(book.getIsbn());
        obj.setTitle(book.getTitle());
        obj.setPrice(book.getPrice());
        obj.setAuthorId(book.getAuthorId());

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {

        repo.delete(repo.findById(id));
        return Response.ok().build();
    }

}
