package com.codeit.springwebbasic.book.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private LocalDate publishedDate;
    private BookStatus status;

    // 나중에 DB를 사용시 status 값도 DB에 저장되기 때문에 밑에 2개의 메서드는 사라지게 됨

    public void rentOut(){
        if(this.status != BookStatus.AVAILABLE){
            throw new IllegalStateException("Book is already rented out");
        }
        this.status = BookStatus.RENTED;
    }

    public void returnBook(){
        if(this.status != BookStatus.RENTED){
            throw new IllegalStateException("Book is not rented out");
        }
        this.status = BookStatus.AVAILABLE;
    }

}
