package com.example.listrotation.controller;

import com.example.listrotation.dto.RotateListRequest;
import com.example.listrotation.dto.RotateListResponse;
import com.example.listrotation.service.ListRotationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/list")
public class ListRotationController {

    private final ListRotationService listRotationService;

    public ListRotationController(ListRotationService listRotationService) {
        this.listRotationService = listRotationService;
    }

    @PostMapping("/rotate")
    public RotateListResponse rotateList(@RequestBody RotateListRequest request) {
        String[] rotatedValues = listRotationService.rotateList(request.getValues(), request.getN());
        return new RotateListResponse(rotatedValues);
    }
}