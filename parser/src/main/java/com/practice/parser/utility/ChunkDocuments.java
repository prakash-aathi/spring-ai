package com.practice.parser.utility;

import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChunkDocuments extends TextSplitter {
    @Override
    protected List<String> splitText(String text) {
//        System.out.println("==== split text ==== called ");
//        System.out.println(text);
//        List<String> texts = List.of(text.split("(?<=\\.)"));
////        System.out.println(texts);
//        System.out.println(texts.size());
//        return texts;
        int chuckSize = text.length() / 2;


        List<String> chunks = new ArrayList<>();

        chunks.add(text.substring(0, chuckSize));
        chunks.add(text.substring(chuckSize));

        return chunks;
    }
}
