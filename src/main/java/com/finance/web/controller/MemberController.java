package com.finance.web.controller;

import com.finance.web.dto.Response;
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
    private final Response response;

    @GetMapping("/members/{memberId}/notifications")
    public ResponseEntity<? extends Object> notificationItemList(@PathVariable String memberId) {

        return response.success(memberService.getItemListFromNotifications(memberId));
    }

    @PostMapping("/members/{memberId}/notifications")
    public ResponseEntity<? extends Object> notificationItemAdd(@PathVariable String memberId, @RequestBody StockItem stockItem) {
        return memberService.addItemToNotifications(memberId, stockItem) ? response.success(stockItem.getStockCode() + " is pushed", HttpStatus.CREATED) :
                response.fail(stockItem.getStockCode() + " is not pushed", HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/members/{memberId}/notifications")
    public ResponseEntity<? extends Object> notificationItemRemove(@PathVariable String memberId, @RequestBody StockItem stockItem) {
        return memberService.deleteItemInNotifications(memberId, stockItem) ? response.success(HttpStatus.NO_CONTENT) :
                response.fail("item isn't deleted", HttpStatus.NOT_MODIFIED);
    }

}
