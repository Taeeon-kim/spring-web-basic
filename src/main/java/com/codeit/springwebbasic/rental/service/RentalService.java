package com.codeit.springwebbasic.rental.service;

import com.codeit.springwebbasic.book.entity.Book;
import com.codeit.springwebbasic.book.repository.BookRepository;
import com.codeit.springwebbasic.event.BookRentedEvent;
import com.codeit.springwebbasic.member.entity.Member;
import com.codeit.springwebbasic.member.repository.MemberRepository;
import com.codeit.springwebbasic.notification.NotificationDispatcher;
import com.codeit.springwebbasic.notification.NotificationService;
import com.codeit.springwebbasic.rental.entity.Rental;
import com.codeit.springwebbasic.rental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
//    private final NotificationService notificationService; // @Primary 의존성 주입
//    private final NotificationDispatcher notificationDispatcher;

    // 스프링에서 제공하는 이벤트 발행 객체 -> 생성자로 함께 주입 받겠다.
    private final ApplicationEventPublisher eventPublisher;

    public Rental rentBook(Long memberId, Long bookId) {


        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        // 대여처리
        book.rentOut();

        Rental rental = Rental.builder()
                .member(member)
                .book(book)
                .rentedAt(LocalDateTime.now())
                .dueDate(LocalDateTime.now().plusDays(7))
                .build();
        System.out.println("rental = " + rental);
        Rental saved = rentalRepository.save(rental); // 결과물

        // 대여가 완료되었다는 이벤트를 발행
        // 이벤트 리스너중, 해당 객체를 매개값으로 받을 수 있는 핸들러가 이벤트를 감지하고 로직을 수행
        eventPublisher.publishEvent(new BookRentedEvent(this, saved));

        // 알림 발송

//        notificationService.sendNotification(member.getEmail(), message);
//        notificationDispatcher.broadcast(member.getEmail(), message);
        return saved;

    }
}
