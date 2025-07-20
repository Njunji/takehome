package com.example.stringValidator.service;

import com.example.stringValidator.dto.ValidationResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StringValidatorServiceTest {

    @InjectMocks
    private StringValidatorService validatorService;

    @Test
    void testFindLongestValidString() {
        String validCharacters = "ABCD";
        String[] strings = {"AABCDA", "ABCDZADC", "ABCDBCA", "ABCDABDCA"};
        ValidationResponse response = validatorService.findLongestValidString(validCharacters, strings);

        assertEquals("ABCDABDCA", response.longestValidString());
        assertEquals("Longest valid string: ABCDABDCA", response.message());
        List<ValidationResponse.StringValidationResult> results = response.results();
        assertEquals(4, results.size());

        assertEquals("AABCDA", results.get(0).input());
        assertFalse(results.get(0).isValid());
        assertEquals("Contains consecutive repetitive characters", results.get(0).reason());

        assertEquals("ABCDZADC", results.get(1).input());
        assertFalse(results.get(1).isValid());
        assertEquals("Contains invalid character 'Z' at position 5", results.get(1).reason());

        assertEquals("ABCDBCA", results.get(2).input());
        assertTrue(results.get(2).isValid());
        assertEquals("Valid string with no consecutive repetitive characters", results.get(2).reason());

        assertEquals("ABCDABDCA", results.get(3).input());
        assertTrue(results.get(3).isValid());
        assertEquals("Valid string with no consecutive repetitive characters", results.get(3).reason());
    }

    @Test
    void testNoValidStrings() {
        String validCharacters = "ABCD";
        String[] strings = {"AABCDA", "ABCDZADC"};
        ValidationResponse response = validatorService.findLongestValidString(validCharacters, strings);

        assertEquals("", response.longestValidString());
        assertEquals("No valid string found", response.message());
        List<ValidationResponse.StringValidationResult> results = response.results();
        assertEquals(2, results.size());

        assertEquals("AABCDA", results.get(0).input());
        assertFalse(results.get(0).isValid());
        assertEquals("Contains consecutive repetitive characters", results.get(0).reason());

        assertEquals("ABCDZADC", results.get(1).input());
        assertFalse(results.get(1).isValid());
        assertEquals("Contains invalid character 'Z' at position 5", results.get(1).reason());
    }

    @Test
    void testEmptyOrNullInput() {
        ValidationResponse response1 = validatorService.findLongestValidString("", new String[]{"ABCD"});
        assertEquals("", response1.longestValidString());
        assertEquals("No valid string found: invalid input (null or empty valid characters or strings)", response1.message());
        assertTrue(response1.results().isEmpty());

        ValidationResponse response2 = validatorService.findLongestValidString("ABCD", new String[]{});
        assertEquals("", response2.longestValidString());
        assertEquals("No valid string found: invalid input (null or empty valid characters or strings)", response2.message());
        assertTrue(response2.results().isEmpty());

        ValidationResponse response3 = validatorService.findLongestValidString(null, new String[]{"ABCD"});
        assertEquals("", response3.longestValidString());
        assertEquals("No valid string found: invalid input (null or empty valid characters or strings)", response3.message());
        assertTrue(response3.results().isEmpty());

        ValidationResponse response4 = validatorService.findLongestValidString("ABCD", null);
        assertEquals("", response4.longestValidString());
        assertEquals("No valid string found: invalid input (null or empty valid characters or strings)", response4.message());
        assertTrue(response4.results().isEmpty());
    }

    @Test
    void testSingleValidString() {
        String validCharacters = "ABCD";
        String[] strings = {"ABCDBCA"};
        ValidationResponse response = validatorService.findLongestValidString(validCharacters, strings);

        assertEquals("ABCDBCA", response.longestValidString());
        assertEquals("Longest valid string: ABCDBCA", response.message());
        List<ValidationResponse.StringValidationResult> results = response.results();
        assertEquals(1, results.size());
        assertEquals("ABCDBCA", results.get(0).input());
        assertTrue(results.get(0).isValid());
        assertEquals("Valid string with no consecutive repetitive characters", results.get(0).reason());
    }

    @Test
    void testMultipleValidStrings() {
        String validCharacters = "ABCD";
        String[] strings = {"ABC", "ABCDBCA", "AB"};
        ValidationResponse response = validatorService.findLongestValidString(validCharacters, strings);

        assertEquals("ABCDBCA", response.longestValidString());
        assertEquals("Longest valid string: ABCDBCA", response.message());
        List<ValidationResponse.StringValidationResult> results = response.results();
        assertEquals(3, results.size());
        assertEquals("ABC", results.get(0).input());
        assertTrue(results.get(0).isValid());
        assertEquals("Valid string with no consecutive repetitive characters", results.get(0).reason());
        assertEquals("ABCDBCA", results.get(1).input());
        assertTrue(results.get(1).isValid());
        assertEquals("Valid string with no consecutive repetitive characters", results.get(1).reason());
        assertEquals("AB", results.get(2).input());
        assertTrue(results.get(2).isValid());
        assertEquals("Valid string with no consecutive repetitive characters", results.get(2).reason());
    }
}