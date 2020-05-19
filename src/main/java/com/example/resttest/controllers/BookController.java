package com.example.resttest.controllers;

import com.example.resttest.models.Book;
import com.example.resttest.models.Response;
import com.example.resttest.repositories.DatabaseSimulator;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    DatabaseSimulator databaseSimulator;
    List<Book> bookList;

    public BookController() {
        databaseSimulator = new DatabaseSimulator();
        bookList = databaseSimulator.getAllBooks();
    }

    @RequestMapping(value = "/booksJSON", produces = "application/json")
    public Book oneBookJSON() {
        return new Book("Tapeter och solsken", "Janne Andersson", 10);
    }

    @RequestMapping(value = "/booksXML", produces = "application/xml")
    public Book oneBookXML() {
        return new Book("Tapeter och solsken", "Janne Andersson", 10);
    }

    @RequestMapping(value = "/books", produces = "application/xml")
    public List<Book> allBooks() {
        return bookList;
    }

    @RequestMapping(value = "/book/{id}", produces = "application/json")
    public Book getBookByID(@PathVariable int id) {
        Book res = null;
        for (Book book : bookList) {
            if (book.getId() == id)
                res = book;
        }
        if(!res.hasLink("all_books")) {
            Link link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).allBooks()).withRel("all_books");
            res.add(link1);
        }

        if(!res.hasLink("delete_book")) {
            Link link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).deleteByID(id)).withRel("delete_book");
            res.add(link1);
        }

        if(!res.hasLink("add_book")) {
            Link link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).addBook(res)).withRel("add_book");
            res.add(link1);
        }

        return res;
    }

    @RequestMapping(value = "/booksbetween/{idFrom}/{idTo}", produces = "application/json")
    public List<Book> booksBetween(@PathVariable int idFrom, @PathVariable int idTo) {
        List<Book> res = new ArrayList<>();
        for (Book b : bookList
        ) {
            int id = b.getId();
            if (id >= idFrom && id <= idTo) {
                res.add(b);
            }

        }
        return res;
    }

    @RequestMapping(value = "/book/{id}/delete", produces = "application/json")
    public Response deleteByID(@PathVariable int id) {
        Response response = new Response("Book deleted", false);
        int indexToRemove = -1;
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getId() == id)
                indexToRemove = i;

        }
        if (indexToRemove != -1) {
            bookList.remove(indexToRemove);
            response.setStatus(true);
        } else
            response.setMessage("Not found");

        return response;
    }


    @PostMapping("/book/add")
    public Response addBook(@RequestBody Book book) {
        Response response = new Response("Book added", false);
        if ((book.getId() != 0) && (book.getAuthor() != null) && book.getTitle() != null) {
            bookList.add(book);
            response.setStatus(true);
        } else
            response.setMessage("failed to add");
        return response;

    }


}
