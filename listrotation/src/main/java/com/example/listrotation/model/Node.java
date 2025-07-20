package com.example.listrotation.model;

import lombok.Data;

@Data
public class Node {
    private String value;
    private Node next;

    public Node(String value) {
        this.value = value;
        this.next = null;
    }
}