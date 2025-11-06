package com.codeit.springwebbasic.event.listner;

import com.codeit.springwebbasic.event.BookRentedEvent;
import com.codeit.springwebbasic.notification.NotificationDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationEventListener {

    private final NotificationDispatcher dispatcher;

    // 이벤트가 발생되면 해당 메서드가 자동으로 호출됩니다.
    // 매개값으로 이벤트 발행 시 생성한 객체가 전달 됩니다.
    // 이벤트가 발생하면 @EventListener가 있는애들은 귀기울이다가 해당 이벤트객체를 매개변수를 받는 애가 반응을한다.
    // 분산 이벤트 핸들링, Observer패턴, 이벤트 기반 아키텍처
    @EventListener
    public void handleBookRentedEvent(BookRentedEvent event) {
        String message = String.format("%s님, %s를 대여하였습니다. 반납기한: %s",
                event.getMember().getName(),
                event.getBook().getTitle(),
                event.getRental().getDueDate().toLocalDate());

        dispatcher.broadcast(event.getMember().getEmail(), message);
    }

}
