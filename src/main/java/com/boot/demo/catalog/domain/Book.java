package com.boot.demo.catalog.domain;

import jakarta.validation.constraints.NotBlank;

public record Book(

        @NotBlank(message = "isbn is required")
        String isbn,

        @NotBlank(message = "title is required")
        String title,
        @NotBlank(message = "author is required")
        String author,
        @NotBlank(message = "price is required")
        String price) {
}
