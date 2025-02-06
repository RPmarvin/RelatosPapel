package com.relatos_papel.ms_books_catalogue.data;

import com.relatos_papel.ms_books_catalogue.controller.model.SearchBookRequest;
import com.relatos_papel.ms_books_catalogue.data.model.Book;
import com.relatos_papel.ms_books_catalogue.data.utils.Consts;
import com.relatos_papel.ms_books_catalogue.data.utils.SearchCriteria;
import com.relatos_papel.ms_books_catalogue.data.utils.SearchOperation;
import com.relatos_papel.ms_books_catalogue.data.utils.SearchStatement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.StringUtils;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {
    private final BookJpaRepository repository;

    public List<Book> getBooks() {
        return repository.findAll();
    }
    public Book getById(long id) {
        return repository.findById(id).orElse(null);
    }
    public Book save(Book book) {
        return repository.save(book);
    }
    public void delete(Book book) {
        repository.delete(book);
    }
    public List<Book> search(SearchBookRequest request) {
        SearchCriteria<Book> spec = new SearchCriteria<>();
        if(StringUtils.isNotBlank(request.getTitle())){
            spec.add(new SearchStatement(Consts.TITLE, request.getTitle(), SearchOperation.MATCH));
        }
        if(StringUtils.isNotBlank(request.getAuthor())){
            spec.add(new SearchStatement(Consts.AUTHOR, request.getAuthor(), SearchOperation.MATCH));
        }
        if(StringUtils.isNotBlank(request.getCategory())){
            spec.add(new SearchStatement(Consts.CATEGORY, request.getCategory(), SearchOperation.EQUAL));
        }
        if(request.getVisible() != null){
            spec.add(new SearchStatement(Consts.VISIBLE, request.getVisible(), SearchOperation.EQUAL));
        }
        if(request.getPriceFrom() != null){
            spec.add(new SearchStatement(Consts.PRICE, request.getPriceFrom(), SearchOperation.GREATER_THAN_EQUAL));
        }
        if(request.getPriceTo() != null){
            spec.add(new SearchStatement(Consts.PRICE, request.getPriceTo(), SearchOperation.LESS_THAN_EQUAL));
        }

        if(request.getPublicationDateFrom() != null){
            spec.add(new SearchStatement(Consts.PUBLICATION_DATE, request.getPublicationDateFrom(), SearchOperation.GREATER_THAN_EQUAL));
        }
        if(request.getPublicationDateTo() != null){
            spec.add(new SearchStatement(Consts.PUBLICATION_DATE, request.getPublicationDateTo(), SearchOperation.LESS_THAN_EQUAL));
        }

        if(request.getRatingFrom() != null){
            spec.add(new SearchStatement(Consts.RATING, request.getRatingFrom(), SearchOperation.GREATER_THAN_EQUAL));
        }
        if(request.getRatingTo() != null){
            spec.add(new SearchStatement(Consts.RATING, request.getRatingTo(), SearchOperation.LESS_THAN_EQUAL));
        }


      return repository.findAll(spec);
    }
}
