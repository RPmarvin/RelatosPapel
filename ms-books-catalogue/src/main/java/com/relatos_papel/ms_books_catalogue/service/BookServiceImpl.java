package com.relatos_papel.ms_books_catalogue.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.relatos_papel.ms_books_catalogue.controller.model.BookDto;
import com.relatos_papel.ms_books_catalogue.controller.model.CreateBookRequest;
import com.relatos_papel.ms_books_catalogue.controller.model.SearchBookRequest;
import com.relatos_papel.ms_books_catalogue.data.BookRepository;
import com.relatos_papel.ms_books_catalogue.data.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository repository;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public List<Book> getBooks(SearchBookRequest searchBookRequest) {
        if (StringUtils.hasLength(searchBookRequest.getTitle())
                || StringUtils.hasLength(searchBookRequest.getAuthor())
                || StringUtils.hasLength(searchBookRequest.getIsbn())
                || StringUtils.hasLength(searchBookRequest.getCategory())
                || searchBookRequest.getVisible() != null
                || searchBookRequest.getPriceFrom() != null
                || searchBookRequest.getPriceTo() != null
                || searchBookRequest.getPublicationDateFrom() != null
                || searchBookRequest.getPublicationDateTo() != null
                || searchBookRequest.getRatingFrom() != null
                || searchBookRequest.getRatingTo() != null
        ) {
            return repository.search(searchBookRequest);
        }
        List<Book> books = repository.getBooks();
        return books.isEmpty() ? null : books;
    }

    @Override
    public Book getBook(String id) {
        return repository.getById(Long.valueOf(id));
    }

    @Override
    public Boolean removeBook(String id) {
        Book book = repository.getById(Long.valueOf(id));
        if (book != null) {
            repository.delete(book);
            return Boolean.TRUE;
        } else return Boolean.FALSE;
    }

    @Override
    public Book createBook(CreateBookRequest request) {
        if (request != null && StringUtils.hasLength(request.getTitle().trim())
                && StringUtils.hasLength(request.getAuthor().trim())
                && StringUtils.hasLength(request.getIsbn().trim())
                && request.getPublicationDate() != null
                && StringUtils.hasLength(request.getCategory().trim())
                && request.getVisible() != null
                && request.getPrice() != null
                && request.getStock() != null
        ) {
            Book book = Book.builder()
                    .title(request.getTitle())
                    .author(request.getAuthor())
                    .isbn(request.getIsbn())
                    .publicationDate(request.getPublicationDate())
                    .category(request.getCategory())
                    .price(request.getPrice())
                    .visible(request.getVisible())
                    .stock(request.getStock())
                    .rating(request.getRating())
                    .build();
            return repository.save(book);

        } else return null;
    }

    @Override
    public Book updateBook(String id, String request) {
        Book book = repository.getById(Long.valueOf(id));
        if (book != null) {
            try {
                JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(request));
                JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(book)));
                Book patched = objectMapper.treeToValue(target, Book.class);
                repository.save(patched);
                return patched;
            } catch (JsonProcessingException | JsonPatchException e) {
                log.error("Error updating Book {}", id, e);
            }
        }
        return null;
    }

    @Override
    public Book updateBook(String id, BookDto updateRequest) {
        Book book = repository.getById(Long.valueOf(id));
        if (book != null) {
            book.update(updateRequest);
            repository.save(book);
            return book;
        }
        return null;
    }
}
