package com.koreait.exam.chat_24_09;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private List<ChatMessage> chatMessages = new ArrayList<>();

    public record writeChatMessageResponse(long id) {

    }

    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<writeChatMessageResponse> writeMessage() {
        ChatMessage message = new ChatMessage("홍길동", "안녕");

        chatMessages.add(message);

        return new RsData<>("S-1",
                "메세지가 작성됨",
                new writeChatMessageResponse(message.getId())
        );
    }
}
