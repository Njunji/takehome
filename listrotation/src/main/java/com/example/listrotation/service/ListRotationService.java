package com.example.listrotation.service;

import com.example.listrotation.model.Node;
import org.springframework.stereotype.Service;

@Service
public class ListRotationService {

    public String[] rotateList(String[] values, int n) {
        if (values == null || values.length == 0 || n < 0) {
            return new String[0];
        }

        // Convert array to linked list
        Node head = arrayToList(values);
        if (head == null || head.getNext() == null) {
            return values;
        }

        // Normalizing n to avoid unnecessary rotations
        int length = getListLength(head);
        n = n % length;
        if (n == 0) {
            return values;
        }

        // Find the new tail (length - n - 1)th node
        Node current = head;
        for (int i = 0; i < length - n - 1; i++) {
            current = current.getNext();
        }

        // New head is the node after current
        Node newHead = current.getNext();
        current.setNext(null); // Break the link to make current the new tail

        // Connect the old tail to the old head
        Node tail = newHead;
        while (tail.getNext() != null) {
            tail = tail.getNext();
        }
        tail.setNext(head);

        // Convert back to array
        return listToArray(newHead);
    }

    private Node arrayToList(String[] values) {
        if (values.length == 0) {
            return null;
        }
        Node head = new Node(values[0]);
        Node current = head;
        for (int i = 1; i < values.length; i++) {
            current.setNext(new Node(values[i]));
            current = current.getNext();
        }
        return head;
    }

    private String[] listToArray(Node head) {
        int length = getListLength(head);
        String[] result = new String[length];
        Node current = head;
        for (int i = 0; i < length; i++) {
            result[i] = current.getValue();
            current = current.getNext();
        }
        return result;
    }

    private int getListLength(Node head) {
        int length = 0;
        Node current = head;
        while (current != null) {
            length++;
            current = current.getNext();
        }
        return length;
    }
}