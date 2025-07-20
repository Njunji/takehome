package com.example.mostcommonwords.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MostCommonWordsServiceTest {

    @Autowired
    private MostCommonWordsService mostCommonWordsService;

    @Test
    void testFindThreeMostCommonWords() {
        String sentence = "hi there care to discuss algorithm basis or how to solve algorithm or";
        assertEquals(List.of("algorithm", "or", "to"),
                mostCommonWordsService.findThreeMostCommonWords(sentence));
    }

    @Test
    void testLessThanThreeWords() {
        String sentence = "hi hi";
        assertEquals(List.of("hi"), mostCommonWordsService.findThreeMostCommonWords(sentence));
    }

    @Test
    void testEmptySentence() {
        String sentence = "";
        assertEquals(List.of(), mostCommonWordsService.findThreeMostCommonWords(sentence));
    }

    @Test
    void testNullSentence() {
        assertEquals(List.of(), mostCommonWordsService.findThreeMostCommonWords(null));
    }

    @Test
    void testWithPunctuation() {
        String sentence = "hi, there! hi: there.";
        assertEquals(List.of("hi", "there"), mostCommonWordsService.findThreeMostCommonWords(sentence));
    }

    @Test
    void testSingleWordMultipleTimes() {
        String sentence = "hello hello hello hello";
        assertEquals(List.of("hello"), mostCommonWordsService.findThreeMostCommonWords(sentence));
    }

    @Test
    void testExactlyThreeWordsDifferentFrequencies() {
        String sentence = "apple apple banana cherry cherry cherry";
        assertEquals(List.of("apple", "banana", "cherry"),
                mostCommonWordsService.findThreeMostCommonWords(sentence));
    }

    @Test
    void testMoreThanThreeWordsSameFrequency() {
        String sentence = "ant bee cat dog ant bee cat dog";
        assertEquals(List.of("ant", "bee", "cat"),
                mostCommonWordsService.findThreeMostCommonWords(sentence));
    }

    @Test
    void testMixedCaseAndPunctuation() {
        String sentence = "Hello HELLO hello, World world! WORLD.";
        assertEquals(List.of("hello", "world"), mostCommonWordsService.findThreeMostCommonWords(sentence));
    }
}