package com.codeit.springwebbasic.book.service;

import com.codeit.springwebbasic.book.dto.request.BookCreateRequestDto;
import com.codeit.springwebbasic.book.entity.Book;
import com.codeit.springwebbasic.book.entity.BookStatus;
import com.codeit.springwebbasic.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book createBook(BookCreateRequestDto requestDto) {

        // ISBN 중복체크
        Optional<Book> byIsbn = bookRepository.findByIsbn(requestDto.getIsbn());
        if (byIsbn.isPresent()) {
            throw new IllegalArgumentException("Book with ISBN " + requestDto.getIsbn() + " already exists");
        }

        Book build = Book.builder()
                .title(requestDto.getTitle())
                .author(requestDto.getAuthor())
                .author(requestDto.getAuthor())
                .isbn(requestDto.getIsbn())
                .publisher(requestDto.getPublisher())
                .publishedDate(requestDto.getPublishedDate())
                .status(BookStatus.AVAILABLE)
                .build();
        System.out.println("build = " + build);

       return bookRepository.save(build);

    }
}
