package com.example.book.dto.book;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookRequest {

    private String title;
    private String author;
    private boolean archived;
    private boolean shareable;
}
