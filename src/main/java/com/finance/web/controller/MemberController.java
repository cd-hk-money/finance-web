package com.finance.web.controller;

import com.finance.web.service.MemberService;
import com.finance.web.vo.StockItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ResponseBody
    @GetMapping("/members/{memberId}/notifications")
    public ResponseEntity<Object> notificationItemList(@PathVariable String memberId) {
        return new ResponseEntity(memberService.getItemListFromNotifications(memberId), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/members/{memberId}/notifications")
    public ResponseEntity<Object> notificationItemAdd(@PathVariable String memberId, @RequestBody StockItem stockItem) {
        return new ResponseEntity(memberService.addItemToNotifications(memberId, stockItem), HttpStatus.CREATED);
    }

    @ResponseBody
    @DeleteMapping("/members/{memberId}/notifications")
    public ResponseEntity<Object> notificationItemRemove(@PathVariable String memberId, @RequestBody StockItem stockItem) {
        return new ResponseEntity(memberService.deleteItemInNotifications(memberId, stockItem), HttpStatus.NO_CONTENT);
    }

}
