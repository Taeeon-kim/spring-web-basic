package com.codeit.springwebbasic.book.controller;

import com.codeit.springwebbasic.book.dto.request.BookCreateRequestDto;
import com.codeit.springwebbasic.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor // 의존성 주입 매개값을 전달받는 생성자
public class BookController {

    private final BookService bookService;

    /*
       {
        "title": string,
        "author": string,
        "isbn": string,
        "publisher": string,
        "publishedDate": date
       }
     */
    @GetMapping("/books")
    public void createBook(@Valid @RequestBody BookCreateRequestDto requestDto) {
        System.out.println("requestDto = " + requestDto);
        bookService.createBook(requestDto);

    }

}
