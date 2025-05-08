package com.kafka.springboot.controller;

import com.kafka.springboot.entity.WikimediaData;
import com.kafka.springboot.repository.WikimediaDataRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/wiki")
@CrossOrigin(origins = "*") // Allow frontend access (adjust origin as needed)
public class WikiController {


    private WikimediaDataRepository wikimediaDataRepository;

    public WikiController(WikimediaDataRepository wikimediaDataRepository) {
        this.wikimediaDataRepository = wikimediaDataRepository;
    }

    @GetMapping("/recent-changes")
    public List<WikimediaData> getRecentChanges() {
        // You can add pagination or sorting here later if needed
        return wikimediaDataRepository.findAll();
    }
}
