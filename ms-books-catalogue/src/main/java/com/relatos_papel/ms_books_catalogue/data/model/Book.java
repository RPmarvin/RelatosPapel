package com.relatos_papel.ms_books_catalogue.data.model;

import com.relatos_papel.ms_books_catalogue.controller.model.BookDto;
import com.relatos_papel.ms_books_catalogue.data.utils.Consts;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Consts.TITLE)
    private String title;

    @Column(name = Consts.AUTHOR)
    private String author;

    @Column(name = Consts.ISBN)
    private String isbn;

    @Column(name = Consts.PUBLICATION_DATE)
    private LocalDate publicationDate;

    @Column(name = Consts.CATEGORY)
    private String category;

    @Column(name = Consts.PRICE)
    private BigDecimal price;

    @Column(name = Consts.VISIBLE)
    private Boolean visible;

    @Column(name = Consts.STOCK)
    private Integer stock;

    @Column(name = Consts.RATING, precision = 2, scale = 1)
    private BigDecimal rating;


    public void update(BookDto bookDto) {
        this.title = bookDto.getTitle();
        this.author = bookDto.getAuthor();
        this.isbn = bookDto.getIsbn();
        this.publicationDate = bookDto.getPublicationDate();
        this.category = bookDto.getCategory();
        this.price = bookDto.getPrice();
        this.visible = bookDto.getVisible();
        this.stock = bookDto.getStock();
        this.rating = bookDto.getRating();
    }
}
