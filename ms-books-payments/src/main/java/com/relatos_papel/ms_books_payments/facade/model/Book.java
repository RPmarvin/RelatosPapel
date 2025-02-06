package com.relatos_papel.ms_books_payments.facade.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Book {
    private Long id;

    private String title;

    private String author;

    private String isbn;

    private LocalDate publicationDate;

    private String category;

    private BigDecimal price;

    private Boolean visible;

    private Integer stock;

    private BigDecimal rating   ;

}
