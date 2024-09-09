package com.koreait.exam.chat_24_09;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/chat")
@Slf4j
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

    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<writeMessageResponse> writeMessage(@RequestBody writeMessageRequest req) {
        ChatMessage message = new ChatMessage(req.authorName, req.content);
        chatMessages.add(message);
        return new RsData<>("S-1",
                "메세지 작성됨",
                new writeMessageResponse(message.getId())
        );
    }

    public record messagesRequest(Long fromId) {
    }

    public record messagesResponse(List<ChatMessage> chatMessages, long count) {
    }

    @GetMapping("/messages")
    @ResponseBody
    public RsData<messagesResponse> messages(messagesRequest req) {

        List<ChatMessage> messages = chatMessages;

        log.debug("req : {}", req);

        // 번호가 같이 입력되었다면???
        if (req.fromId != null) {
            // 해당 번호의 채팅 메세지가 전체 리스트의 몇번째 인덱스인지? 없다면 -1
            int index = IntStream.range(0, messages.size())
                    .filter(i -> chatMessages.get(i).getId() == req.fromId)
                    .findFirst()
                    .orElse(-1);

            if (index != -1) {
                // 만약에 index가 -1이 아니라면? 0번부터 index번 까지 제거한 리스트를 만든다.
                messages = messages.subList(index + 1, messages.size());
            }
        }


        return new RsData<>("S-1",
                "성공",
                new messagesResponse(messages, messages.size())
        );
    }

    @GetMapping("/room")
    public String showRoom() {
        return "chat/room";
    }
}
