package com.practice.parser.controller;


import com.practice.parser.model.UserDetails;
import com.practice.parser.utility.ChunkDocuments;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/parsing")
@Slf4j
public class ParserController {

    private final ChunkDocuments chunkDocuments;
    private final ChatClient chatClient;

    @Autowired
    public ParserController(ChunkDocuments chunkDocuments,ChatClient.Builder chatCLientBuilder) {
        this.chunkDocuments = chunkDocuments;
        this.chatClient = chatCLientBuilder.build();
    }

    @PostMapping("/upload")
    public ResponseEntity<UserDetails> uploadDocument(@RequestParam("file") MultipartFile file) {
        try {
            // extract
            String fileContent = new String(file.getBytes(), StandardCharsets.UTF_8);
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("filename", file.getOriginalFilename());
            metadata.put("source", "uploaded file");
            List<Document> documents = List.of(new Document(fileContent,metadata));
            List<Document> chunkedDocuments = chunkDocuments.split(documents);
            log.info("Extracted and chunked documents: {}", chunkedDocuments);

            UserDetails userDetails = new UserDetails();
            // loop through LLM and form json
            for(Document document : chunkedDocuments){
                log.info("Processing document: {}", document);
                userDetails = chatClient.prompt()
                        .system("You are a resume parser. Your task is to fill in the missing details in the JSON template with information extracted from chunks of a resume. If the JSON field is already filled correctly, leave it as is. If it is null or empty and the content is present in the resume chunk, fill it in the appropriate format.\n" +
                                "\n" +
                                "Here is the current JSON template:\n " + userDetails.toString())
                        .user(document.getContent())
                        .call()
                        .entity(UserDetails.class);
                log.info("Successfully processed document, updated user details: {}", userDetails);
            }

            return ResponseEntity.ok(userDetails);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
}
