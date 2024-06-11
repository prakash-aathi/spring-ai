package com.practice.spring_ai.functionCalling.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/function")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder ) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/gstBot")
    public String botCalculateGst(@RequestParam("prompt") String prompt){
        return chatClient.prompt()
                .user(prompt)
                .system("You are bot calculate GST or tell total price of product. Do not do any calculations use function call." +
                        "if user not given product price then tell them to enter product price")
                .functions("calculateGstFunction")
                .call()
                .content();
    }

}
