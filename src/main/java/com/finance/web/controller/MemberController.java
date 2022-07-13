package com.finance.web.controller;

import com.finance.web.service.MemberService;
import com.finance.web.vo.StockItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ResponseBody
    @RequestMapping(value = "/members/{memberId}/notification", method = GET)
    public ResponseEntity<Object> notificationItemList(@PathVariable String memberId) {
        return new ResponseEntity(memberService.getItemListFromNotifications(memberId), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/members/{memberId}/notification", method = POST)
    public ResponseEntity<Object> notificationItemAdd(@PathVariable String memberId, @RequestBody StockItem stockItem) {
        return new ResponseEntity(memberService.addItemToNotifications(memberId, stockItem), HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/members/{memberId}/notification", method = DELETE)
    public ResponseEntity<Object> notificationItemRemove(@PathVariable String memberId, @RequestBody StockItem stockItem) {
        return new ResponseEntity(memberService.deleteItemInNotifications(memberId, stockItem), HttpStatus.NO_CONTENT);
    }

}
