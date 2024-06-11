package com.practice.spring_ai.chatClient;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.model.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController("api/v1/chat")
public class ChatController {

    private  final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping
    public String simpleChat(@RequestParam("prompt" ) String prompt){
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

    @GetMapping("/generateStream")
    public Flux<String> generateStream(@RequestParam("message") String message) {
        return chatClient.prompt()
                .user(message)
                .stream()
                .content();
    }


}

