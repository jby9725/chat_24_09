package com.koreait.exam.chat_24_09;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chat")
public class ChatController {

    ChatMessage chatMessage = new ChatMessage("홍길동", "안녕");

    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<ChatMessage> writeMessage() {
        return new RsData<>("S-1", "메세지가 작성됨.", chatMessage);
    }
}
