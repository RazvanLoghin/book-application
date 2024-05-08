package com.example.book.service;

import com.example.book.dto.PageResponse;
import com.example.book.dto.book.BookRequest;
import com.example.book.dto.book.BookResponse;
import org.springframework.security.core.Authentication;

public interface BookService {
    Integer save(BookRequest bookRequest, Authentication authentication);

    BookResponse getBookById(Integer bookId);

    PageResponse<BookResponse> findAllBooks(int page, int size, Authentication connectedUser);

    PageResponse<BookResponse> findAllBooksByOwner(int page, int size, Authentication connectedUser);
}
