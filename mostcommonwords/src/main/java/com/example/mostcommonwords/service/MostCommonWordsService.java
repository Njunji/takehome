package com.example.mostcommonwords.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MostCommonWordsService {

    public List<String> findThreeMostCommonWords(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) {
            return List.of();
        }

        // Split sentence into words, convert to lowercase, and remove punctuation
        String[] words = sentence.toLowerCase()
                .replaceAll("[^a-zA-Z\\s]", "")
                .split("\\s+");

        // Count word frequencies
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                wordCount.merge(word, 1, Integer::sum);
            }
        }

        // Sort words by frequency (descending) to get the top three most frequent
        List<String> topWords = wordCount.entrySet().stream()
                // Sort by frequency descending
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                // Take up to three entries
                .limit(3)
                .map(Map.Entry::getKey)
                // Sort alphabetically
                .sorted()
                .collect(Collectors.toList());

        return topWords;
    }
}