package com.pasakinskas.homework.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.pasakinskas.homework.model.AntiqueBook;
import com.pasakinskas.homework.model.Book;
import com.pasakinskas.homework.model.ScienceJournal;
import com.pasakinskas.homework.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@Path("/api")
public class BooksController {

    private final BookService bookService;
    private final Validator validator;

    @Autowired
    public BooksController(BookService bookService, Validator validator) {
        this.bookService = bookService;
        this.validator = validator;
    }

    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooksByType(@QueryParam("type") String type) {
        if (type == null) {
            List<Book> books = bookService.getAll();
            return Response.ok(books).build();
        }
        if (type.equals("AntiqueBook")) {
            List<AntiqueBook> books = bookService.getAllAntiqueBooks();
            return Response.ok(books).build();
        } else if (type.equals("ScienceJournal")) {
           List<ScienceJournal> books = bookService.getAllScienceJournals();
            return Response.ok(books).build();
        } else {
            return Response.status(404).build();
        }
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
            resMsg.put("errMsg", "No such book");
            return Response.status(404).entity(resMsg).build();
        }

        return Response.ok(book).build();
    }

    @GET
    @Path("/books/{barcode}/total-sum")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookTotalSum(@PathParam("barcode") String barcode) {
        Book book = bookService.getBookByBarcode(barcode);
        Map<String, String> resMsg = new HashMap<>();
        if (book == null) {

            resMsg.put("errMsg", "No book found by your provided barcode");
            return Response.status(404).entity(resMsg).build();
        } else {
            resMsg.put("totalSum", book.calculateTotalPrice().toString());
            return Response.status(200).entity(resMsg).build();
        }
    }

    @PUT
    @Path("/books")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@QueryParam("type") String type, JsonNode json) {
        Book book = bookService.buildFromJson(json,type);
        Set<ConstraintViolation<Object>> violations = validator.validate(book);
        if (violations.isEmpty()) {
            bookService.replaceByBarcode(book);
            return Response.ok(book).build();
        } else {
            return Response.status(400).build();
        }
    }

    @DELETE
    @Path("/books/{barcode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBook(@PathParam("barcode") String barcode) {
        try {
            bookService.deleteBookByBarcode(barcode);
            return Response.ok().build();
        } catch (NullPointerException e) {
            return Response.status(404).build();
        }
    }

    @POST
    @Path("/books")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewBook(@QueryParam("type") String type, @RequestBody JsonNode json) {

        try {
            Book book = bookService.buildFromJson(json, type);
            Set<ConstraintViolation<Object>> violations = validator.validate(book);
            if (violations.isEmpty() && bookService.barcodeNotTaken(book.getBarcode())) {
                bookService.addNew(book);
                return Response.status(201).build();
            } else {
                return Response.status(400).build();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Response.status(500).build();
        }
    }
}
