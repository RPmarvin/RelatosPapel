package com.relatos_papel.ms_books_catalogue.controller.model;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchBookRequest {
    @Parameter(name = "title", description = "Título del libro. No tiene que ser exacto", example = "El principito", required = false)
    private String title;

    @Parameter(name = "author", description = "Autor del libro. No tiene que ser exacto", example = "Mario Vargas Llosa", required = false)
    private String author;

    @Parameter(name = "isbn", description = "Código ISBN. Debe ser exacto", example = "979-8-886-45174-0", required = false)
    private String isbn;

//    @Parameter(name = "publicationDate", description = "Fecha de Publicación del libro. No tiene que ser exacto", example = "28/02/2025", required = false)
//    private String publicationDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Parameter(description = "Fecha de publicación desde (yyyy-MM-dd)", example = "2023-01-01")
    private LocalDate publicationDateFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Parameter(description = "Fecha de publicación hasta (yyyy-MM-dd)", example = "2023-12-31")
    private LocalDate publicationDateTo;

    @Parameter(name = "priceFrom", description = "Precio mínimo del libro. Número entero o número decimal con 2 cifras", example = "100", required = false)
    private BigDecimal priceFrom;

    @Parameter(name = "priceTo", description = "Precio máximo del libro. Número entero o número decimal con 2 cifras", example = "200.00", required = false)
    private BigDecimal  priceTo;


    @Parameter(name = "category", description = "Categoría de libro. Debe ser exacto", example = "Terror", required = false)
    private String category;

    @Parameter(name = "visible", description = "Estado del libro. true o false", example = "true", required = false)
    private Boolean visible;

    @Parameter(name = "ratingFrom", description = "Rating mínimo. Número entero o número decimal con 2 cifras entre 0 y 5", example = "0.5", required = false)
    private BigDecimal ratingFrom;

    @Parameter(name = "ratingTo", description = "Rating máximo. Número entero o número decimal con 2 cifras", example = "5", required = false)
    private BigDecimal  ratingTo;
}
