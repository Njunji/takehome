package com.example.stringValidator.controller;

import com.example.stringValidator.dto.ValidationResponse;
import com.example.stringValidator.service.StringValidatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StringValidatorControllerTest {

    @Mock
    private StringValidatorService validatorService;

    @InjectMocks
    private StringValidatorController controller;

    @Test
    void testFindLongestValidString() {
        String validCharacters = "ABCD";
        String[] strings = {"AABCDA", "ABCDZADC", "ABCDBCA", "ABCDABDCA"};
        ValidationResponse response = new ValidationResponse(
                "ABCDABDCA",
                "Longest valid string: ABCDABDCA",
                List.of(
                        new ValidationResponse.StringValidationResult("AABCDA", false, "Contains consecutive repetitive characters"),
                        new ValidationResponse.StringValidationResult("ABCDZADC", false, "Contains invalid character 'Z' at position 5"),
                        new ValidationResponse.StringValidationResult("ABCDBCA", true, "Valid string with no consecutive repetitive characters"),
                        new ValidationResponse.StringValidationResult("ABCDABDCA", true, "Valid string with no consecutive repetitive characters")
                )
        );

        when(validatorService.findLongestValidString(validCharacters, strings)).thenReturn(response);

        StringValidatorController.ValidationRequest request = new StringValidatorController.ValidationRequest(validCharacters, strings);
        ResponseEntity<ValidationResponse> result = controller.findLongestValidString(request);

        assertEquals("ABCDABDCA", result.getBody().longestValidString());
        assertEquals("Longest valid string: ABCDABDCA", result.getBody().message());
        List<ValidationResponse.StringValidationResult> results = result.getBody().results();
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
    void testNoValidString() {
        String validCharacters = "ABCD";
        String[] strings = {"AABCDA", "ABCDZADC"};
        ValidationResponse response = new ValidationResponse(
                "",
                "No valid string found",
                List.of(
                        new ValidationResponse.StringValidationResult("AABCDA", false, "Contains consecutive repetitive characters"),
                        new ValidationResponse.StringValidationResult("ABCDZADC", false, "Contains invalid character 'Z' at position 5")
                )
        );

        when(validatorService.findLongestValidString(validCharacters, strings)).thenReturn(response);

        StringValidatorController.ValidationRequest request = new StringValidatorController.ValidationRequest(validCharacters, strings);
        ResponseEntity<ValidationResponse> result = controller.findLongestValidString(request);

        assertEquals("", result.getBody().longestValidString());
        assertEquals("No valid string found", result.getBody().message());
        List<ValidationResponse.StringValidationResult> results = result.getBody().results();
        assertEquals(2, results.size());
        assertEquals("AABCDA", results.get(0).input());
        assertFalse(results.get(0).isValid());
        assertEquals("Contains consecutive repetitive characters", results.get(0).reason());
        assertEquals("ABCDZADC", results.get(1).input());
        assertFalse(results.get(1).isValid());
        assertEquals("Contains invalid character 'Z' at position 5", results.get(1).reason());
    }
}

