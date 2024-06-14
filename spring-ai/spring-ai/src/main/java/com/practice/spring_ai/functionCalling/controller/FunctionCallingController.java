package com.practice.spring_ai.functionCalling.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/function")
public class FunctionCallingController {

    private final ChatClient chatClient;

    public FunctionCallingController(ChatClient.Builder chatClientBuilder ) {
        this.chatClient = chatClientBuilder.build();
    }


    // example prompt Calculate the GST for a product with a price of 1000. The GST rate is 18%.
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
