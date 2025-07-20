package com.example.stringValidator.service;

import com.example.stringValidator.dto.ValidationResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StringValidatorService {

    public ValidationResponse findLongestValidString(String validCharacters, String[] strings) {
        if (validCharacters == null || validCharacters.isEmpty() || strings == null || strings.length == 0) {
            return new ValidationResponse(
                    "",
                    "No valid string found: invalid input (null or empty valid characters or strings)",
                    List.of()
            );
        }

        // Convert valid characters to a Set for O(1) lookup
        Set<Character> validCharSet = validCharacters.chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.toSet());

        String longestValidString = "";
        List<ValidationResponse.StringValidationResult> results = new ArrayList<>();

        for (String str : strings) {
            String reason;
            boolean isValid = false;

            if (str == null || str.isEmpty()) {
                reason = "String is null or empty";
            } else {
                ValidationResult validationResult = isValidString(str, validCharSet);
                isValid = validationResult.isValid;
                reason = validationResult.reason;
            }

            if (isValid && str.length() > longestValidString.length()) {
                longestValidString = str;
            }
            results.add(new ValidationResponse.StringValidationResult(str, isValid, reason));
        }

        String message = longestValidString.isEmpty() ? "No valid string found" : "Longest valid string: " + longestValidString;
        return new ValidationResponse(longestValidString, message, results);
    }

    private record ValidationResult(boolean isValid, String reason) {
    }

    private ValidationResult isValidString(String input, Set<Character> validCharSet) {
        // Check for consecutive repetitive characters
        for (int i = 0; i < input.length() - 1; i++) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                return new ValidationResult(false, "Contains consecutive repetitive characters");
            }
        }

        // This is to check if all characters are in the valid set
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!validCharSet.contains(c)) {
                // Fixed to include the 1-based position
                return new ValidationResult(false, "Contains invalid character '" + c + "' at position " + (i + 1));
            }
        }

        return new ValidationResult(true, "Valid string with no consecutive repetitive characters");
    }
}