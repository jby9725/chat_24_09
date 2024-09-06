package com.koreait.exam.chat_24_09;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private List<ChatMessage> chatMessages = new ArrayList<>();

    //    @AllArgsConstructor
//    @Getter
//    public static class writeMessageRequest {
//        private final String authorName;
//        private final String content;
//    }
//    (==)
    public record writeMessageRequest(String authorName, String content) {
    }

    public record writeMessageResponse(long id) {

    }

    public record messagesResponse(List<ChatMessage> chatMessages, long messageCount) {
    }

    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<writeMessageResponse> writeMessage(@RequestBody writeMessageRequest req) {
        ChatMessage message = new ChatMessage(req.authorName, req.content);

        chatMessages.add(message);

        return new RsData<>("S-1",
                "메세지가 작성됨",
                new writeMessageResponse(message.getId())
        );
    }

    @GetMapping("/messages")
    @ResponseBody
    public RsData<messagesResponse> messages() {
        return new RsData<>("S-1",
                "성공",
                new messagesResponse(chatMessages, chatMessages.size())
        );
    }
}
