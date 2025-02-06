package com.relatos_papel.ms_books_catalogue.controller.model;

import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookDto {
    private String title;
    private String author;
    private String isbn;
    private LocalDate publicationDate;
    private String category;
    private BigDecimal price;
    private Boolean visible;
    private Integer stock;
    private BigDecimal rating;

}
