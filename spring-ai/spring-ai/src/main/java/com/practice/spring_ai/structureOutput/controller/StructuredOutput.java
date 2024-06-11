package com.practice.spring_ai.structureOutput.controller;

import com.practice.spring_ai.structureOutput.modal.QuizQuestions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController("api/v1/structure/chat")
public class StructuredOutput {

    private final ChatClient chatClient;

    public StructuredOutput(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    // - mapping Ai string response into modal - Structured output - BeanOutput converter
    // endpoint to enter topic it generates a list of questions from topic
    @GetMapping("/generate/questions")
    public QuizQuestions questionsGenerate(@RequestParam("topic") String topic){
        return chatClient.prompt()
                .system("You are question and answer generating bot, generate 2 questions from topic")
                .user(u -> u.text("Topic is: {topic}").param("topic",topic))
                .call()
                .entity(QuizQuestions.class);
    }

    // generic bean type List
    @GetMapping("/generate/list/questions")
    public List<String> generateListQuestions(@RequestParam("topic") String topic){
        return chatClient.prompt()
                .system("You are question and answer generating bot, generate 2 questions from topic")
                .user(u -> u.text("Topic is: {topic}").param("topic",topic))
                .call()
                .entity(new ParameterizedTypeReference<List<String>>() {
                });
    }

    // - map output converter
    @GetMapping("/generate/map/questions")
    public Map<String,Object> generateMapOfQuestions(@RequestParam("topic") String topic){
        return chatClient.prompt()
                .system("You are question and answer generating bot, generate 2 questions from topic")
                .user(u -> u.text("Topic is: {topic}").param("topic",topic))
                .call()
                .entity(new ParameterizedTypeReference<Map<String,Object>>() {
                });
    }

    // list output converter
    @GetMapping("/generate/default/list/questions")
    public List<String> generateDefaultListOfQuestions(@RequestParam("topic") String topic){
        return chatClient.prompt()
                .system("You are question and answer generating bot, generate 2 questions from topic")
                .user(u -> u.text("Topic is: {topic}").param("topic",topic))
                .call()
                .entity(new ListOutputConverter(new DefaultConversionService()));
    }



}
