package com.koreait.exam.chat_24_09;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @PostMapping("/writeMessage")
    @ResponseBody
    public String writeMessage() {
        return "메세지 작성됨";
    }
}
