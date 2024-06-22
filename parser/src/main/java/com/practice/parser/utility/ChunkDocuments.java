package com.practice.parser.utility;

import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChunkDocuments extends TextSplitter {

    // Define character limits based on your model's support
    // I am using model supports ~ 256000 character
    private static final int MIN_CHAR_COUNT = 1000;
    private static final int MAX_CHAR_COUNT = 4000;

    @Override
    protected List<String> splitText(String text) {
        if (text == null || text.isEmpty()) {
            return List.of();
        }

        List<String> chunks = new ArrayList<>();
        int textLength = text.length();
        int startIndex = 0;

        while (startIndex < textLength) {
            int endIndex = Math.min(startIndex + MAX_CHAR_COUNT, textLength);

            // Ensure we don't break in the middle of a word
            if (endIndex < textLength) {
                while (endIndex > startIndex && !Character.isWhitespace(text.charAt(endIndex))) {
                    endIndex--;
                }
            }

            // If we can't find a space, just split at MAX_CHAR_COUNT
            if (endIndex == startIndex) {
                endIndex = Math.min(startIndex + MAX_CHAR_COUNT, textLength);
            }

            String chunk = text.substring(startIndex, endIndex).trim();
            chunks.add(chunk);
            startIndex = endIndex;

            // Ensure minimum character count for the next chunk
            if (startIndex + MIN_CHAR_COUNT > textLength && startIndex < textLength) {
                chunks.add(text.substring(startIndex).trim());
                break;
            }
        }

        return chunks;
    }
}
