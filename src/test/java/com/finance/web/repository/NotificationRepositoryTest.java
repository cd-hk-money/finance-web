package com.finance.web.repository;

import com.finance.web.entity.Notification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class NotificationRepositoryTest {

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    void create() throws Exception {
        //given
        Notification noti = new Notification("035720",
                "카카오 확정실적 발표", "2021년 매출액 xxx조");

        //when
        Notification save = notificationRepository.save(noti);

        //then
        assertThat(save).isEqualTo(noti);

    }

    @Test
    void read() throws Exception {
        //given
        Notification noti1 = new Notification("035720",
                "카카오 확정실적 발표", "2021년 매출액 xxx조");

        Notification noti2 = new Notification("035720",
                "카카오 대표이사 변경", "카카오 대표이사가 변경되었어요");

        Notification save1 = notificationRepository.save(noti1);
        Notification save2 = notificationRepository.save(noti2);

        //when
        List<Notification> allByStockCode = notificationRepository.findAllByStockCode("035720");

        //then
        assertThat(allByStockCode.size()).isEqualTo(2);

        for (Notification notification : allByStockCode) {
            System.out.println("notification = " + notification);
            System.out.println("Title = " + notification.getTitle());
            System.out.println("Code = " + notification.getStockCode());
            System.out.println("Sentence = " + notification.getSentence());
            System.out.println("notification = " + notification.getCreatedDate());
            System.out.println("----------------------------------------");
        }
    }


}