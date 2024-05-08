package com.example.book.converter;

import com.example.book.dto.book.BookRequest;
import com.example.book.dto.book.BookResponse;
import com.example.book.entity.Book;

public class BookMapper {

    public BookRequest toDto(Book book) {
        return BookRequest
                .builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .archived(book.isArchived())
                .shareable(book.isShareable())
                .build();
    }


    public Book toBook(BookRequest bookRequest) {
        return Book
                .builder()
                .archived(bookRequest.isArchived())
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .shareable(bookRequest.isShareable())
                .build();
    }

    public BookResponse toResponse(Book book) {
        return BookResponse
                .builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorName(book.getAuthor())
                .isbn(book.getIsbn())
                .rate(book.getRate())
                .archived(book.isArchived())
                .shareable(book.isShareable())
                .ownerName(book.getOwner().getFullName())
                .build();
    }
}
