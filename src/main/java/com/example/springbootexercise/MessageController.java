package com.example.springbootexercise;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageController {
    private List<Message> messages = new ArrayList<>();

    @PostMapping("/api/messages")
    public ResponseEntity<String> addMessage(@RequestBody Message message) {
        messages.add(message);

        messages.forEach(System.out::println);
        return ResponseEntity.ok("Nachricht hinzugefügt");
    }

    @GetMapping("/api/messages")
    public List<Message> getMessages() {
        return messages;
    }

    @DeleteMapping("/api/messages/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable String id) {
        messages.stream()
                .filter(message -> message.getId().equals(id))
                .findFirst()
                .ifPresent(message -> messages.remove(message));
        if(messages.isEmpty()) {
            return ResponseEntity.ok("Nachricht gelöscht");
        } else {
            return ResponseEntity.ok("Nachricht nicht gefunden");
        }

    }


}
