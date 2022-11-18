package com.finance.web.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class Response {

    @Getter
    @Builder
    private static class Body {

        private int state;
        private String result;
        private String massage;
        private Object data;
        private Object error;
    }


}
