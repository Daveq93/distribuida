package com.distribuida.books.to;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AuthorDto {
    private Integer id;

    // @Column(name="first_name")
    private String firstName;

    // @Column(name="last_name")
    private String lastName;

}
