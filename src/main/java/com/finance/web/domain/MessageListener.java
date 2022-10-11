package com.finance.web.domain;

import com.finance.web.vo.Message;

public interface MessageListener {

    void pushMessage(Message message);

}
