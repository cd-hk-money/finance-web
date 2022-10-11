package com.finance.web.controller;

import com.finance.web.service.MessageService;
import com.finance.web.vo.Message;
import com.finance.web.vo.StockItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/message")
    public ResponseEntity<Object> messageAdd(@RequestBody StockItem stockItem, @RequestBody Message message) {
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
