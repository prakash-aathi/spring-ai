package com.practice.spring_ai.extractTransformLoad.controller;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/etl")
public class EtlChatController {

     List<Document> loadText(Resource resource, String fileName) {
        TextReader textReader = new TextReader(resource);
        textReader.getCustomMetadata().put("filename", fileName);
        return textReader.read();
    }

    @PostMapping("/upload")
    public ResponseEntity<List<Document>> uploadDocument(@RequestParam("file") MultipartFile file) {
        // extract
        try {
            ByteArrayResource resource = new ByteArrayResource(file.getBytes());
            List<Document> documents = loadText(resource,file.getOriginalFilename());
            return ResponseEntity.ok(documents);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
}

