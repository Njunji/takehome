package com.example.mostcommonwords.controller;

import com.example.mostcommonwords.dto.MostCommonWordsResponse;
import com.example.mostcommonwords.service.MostCommonWordsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/words")
public class MostCommonWordsController {

    private final MostCommonWordsService mostCommonWordsService;

    public MostCommonWordsController(MostCommonWordsService mostCommonWordsService) {
        this.mostCommonWordsService = mostCommonWordsService;
    }

    @PostMapping("/most-common")
    public MostCommonWordsResponse findThreeMostCommonWords(@RequestBody String sentence) {
        return new MostCommonWordsResponse(mostCommonWordsService.findThreeMostCommonWords(sentence));
    }
}