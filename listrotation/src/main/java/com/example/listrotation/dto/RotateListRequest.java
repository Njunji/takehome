package com.example.listrotation.dto;

import lombok.Data;

@Data
public class RotateListRequest {
    private String[] values;
    private int n;
}