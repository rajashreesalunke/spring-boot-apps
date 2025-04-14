package com.handwriter.controller;

import com.handwriter.model.Note;
import com.handwriter.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin("*")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @PostMapping
    public Note saveNote(@RequestBody Note note) {
        note.setCreatedAt(LocalDateTime.now());
        return noteRepository.save(note);
    }

    @GetMapping("/{userId}")
    public List<Note> getNotesByUser(@PathVariable String userId) {
        return noteRepository.findByUserId(userId);
    }
}