package com.codeit.springwebbasic.rental.entity;

import com.codeit.springwebbasic.book.entity.Book;
import com.codeit.springwebbasic.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rental {

    private Long id;
    private Member member;
    private Book book;
    private LocalDateTime rentedAt;
    private LocalDateTime dueDate;
    private LocalDateTime returnedAt;

    // 반납 가능
    public void returnBook(){
        if(this.returnedAt != null){
            throw new IllegalStateException("Book is already returned");
        }
        book.returnBook(); // book의 status 변경
        this.returnedAt = LocalDateTime.now();
    }

    // 기한이 지났는지를 체크
    public boolean isOverdue(){
        LocalDateTime checkDate = returnedAt != null ? returnedAt : LocalDateTime.now();
        return checkDate.isAfter(dueDate);
    }

}
