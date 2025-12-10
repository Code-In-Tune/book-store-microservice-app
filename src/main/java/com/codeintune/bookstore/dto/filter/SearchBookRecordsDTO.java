package com.codeintune.bookstore.dto.filter;

import lombok.Data;

@Data
public class SearchBookRecordsDTO {
    private String title;
    private String author;
    private String publisher;
}
