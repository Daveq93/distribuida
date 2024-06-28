package com.distribuida.books.to;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class BookDto {

    private Integer id;
    private String isbn;
    private BigDecimal price;
    private String title;
    private String authorName;
}
