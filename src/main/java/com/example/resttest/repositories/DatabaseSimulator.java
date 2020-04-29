package com.example.resttest.repositories;

import com.example.resttest.models.Book;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSimulator {

    public List<Book> getAllBooks()
    {
        List<Book> bookList=new ArrayList<>();
        Book book1=new Book("Tapeter och solsken","Janne ANdersson",1);
        Book book2=new Book("Java och annat","Svante",2);
        Book book3=new Book("REST","Ida Malmqvist",3);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        return bookList;
    }
}
