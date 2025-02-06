package com.relatos_papel.ms_books_catalogue.service;

import com.relatos_papel.ms_books_catalogue.controller.model.BookDto;
import com.relatos_papel.ms_books_catalogue.controller.model.CreateBookRequest;
import com.relatos_papel.ms_books_catalogue.controller.model.SearchBookRequest;
import com.relatos_papel.ms_books_catalogue.data.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks(SearchBookRequest request);

    Book getBook(String id);

    Boolean removeBook(String id);

    Book createBook(CreateBookRequest request);

    Book updateBook(String id, String updateRequest);
    Book updateBook(String id, BookDto updateRequest);
}
