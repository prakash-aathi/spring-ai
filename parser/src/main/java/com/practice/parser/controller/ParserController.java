package com.practice.parser.controller;


import com.practice.parser.utility.ChunkDocuments;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/parsing")
public class ParserController {

    List<Document> loadText(Resource resource, String fileName) {
        TextReader textReader = new TextReader(resource);
        textReader.getCustomMetadata().put("filename", fileName);

        return textReader.read();
    }

    private final ChunkDocuments chunkDocuments;

    @Autowired
    public ParserController(ChunkDocuments chunkDocuments) {
        this.chunkDocuments = chunkDocuments;
    }

    @PostMapping("/upload")
    public ResponseEntity<List<Document>> uploadDocument(@RequestParam("file") MultipartFile file) {
        // extract

        try {
            ByteArrayResource resource = new ByteArrayResource(file.getBytes());
            List<Document> documents = loadText(resource,file.getOriginalFilename());
            System.out.println(documents);
            List<Document> documents1 = chunkDocuments.split(documents);
            return ResponseEntity.ok(documents1);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
}
