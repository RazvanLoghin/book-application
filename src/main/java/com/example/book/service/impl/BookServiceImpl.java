package com.example.book.service.impl;

import com.example.book.converter.BookMapper;
import com.example.book.dto.PageResponse;
import com.example.book.dto.book.BookRequest;
import com.example.book.dto.book.BookResponse;
import com.example.book.entity.Book;
import com.example.book.entity.User;
import com.example.book.exception.BookNotFoundException;
import com.example.book.repository.BookRepository;
import com.example.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.book.dto.book.BookSpecification.withOwnerId;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public Integer save(BookRequest bookRequest, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Book book = bookMapper.toBook(bookRequest);
        book.setOwner(user);
        return bookRepository.save(book).getId();
    }

    @Override
    public BookResponse getBookById(Integer bookId) {
        return bookRepository
                .findById(bookId)
                .map(bookMapper::toResponse)
                .orElseThrow(() -> new BookNotFoundException("Book with id %s was not found"));
    }

    @Override
    public PageResponse<BookResponse> findAllBooks(int page, int size, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Book> books = bookRepository.findAllDisplayableBooks(pageable, user.getId());
        return getBookResponseAndReturnPageResponse(books);
    }

    @Override
    public PageResponse<BookResponse> findAllBooksByOwner(int page, int size, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Book> books = bookRepository.findAll(withOwnerId(user.getId()), pageable);
        return getBookResponseAndReturnPageResponse(books);
    }

    private PageResponse<BookResponse> getBookResponseAndReturnPageResponse(Page<Book> books) {
        List<BookResponse> bookResponses = books.stream()
                .map(bookMapper::toResponse)
                .toList();
        return new PageResponse<>(
                bookResponses,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages(),
                books.isFirst(),
                books.isLast()
        );
    }
}
