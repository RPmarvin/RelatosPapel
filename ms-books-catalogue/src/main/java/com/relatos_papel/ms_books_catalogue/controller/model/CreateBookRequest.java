package com.relatos_papel.ms_books_catalogue.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateBookRequest {
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
