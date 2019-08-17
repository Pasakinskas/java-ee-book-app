package com.pasakinskas.homework.controller;

import com.pasakinskas.homework.model.Book;
import com.pasakinskas.homework.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Path("/api")
public class BooksController {

    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return Response.ok(books).build();
    }

    @GET
    @Path("/create-mocks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fillMockData() {
        bookService.createMockData();
        return Response.ok().build();
    }

    @GET
    @Path("/books/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookByBarcode(@PathParam("barcode") String barcode) {
        Book book = bookService.getBookByBarcode(barcode);
        if (book == null) {
            Map<String, String> resMsg = new HashMap<>();
            resMsg.put("message", "No such book");
            return Response.status(404).entity(resMsg).build();
        }

        return Response.ok(book).build();
    }

    @GET
    @Path("/books/{barcode}/total-sum")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookTotalSum(@PathParam("barcode") String barcode) {
        return Response.ok().build();
    }

    @PUT
    @Path("/books/{barcode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("barcode") String barcode, @RequestBody @Validated Book book) {

        return Response.ok().build();
    }

    @POST
    @Path("/books/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewBook(@QueryParam("type") String type , @RequestBody @Validated Book book) {

        return Response.ok().build();
    }
}
