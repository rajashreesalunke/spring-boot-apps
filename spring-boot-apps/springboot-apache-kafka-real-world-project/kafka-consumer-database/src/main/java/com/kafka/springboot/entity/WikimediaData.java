package com.kafka.springboot.entity;

import jakarta.persistence.*;
import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
import org.springframework.stereotype.Service;

//@Data
@Entity
@Table(name="wikimedia_recentchange")
//@Getter
//@Setter
public class WikimediaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Lob
    @Column(name = "wiki_event_data", columnDefinition = "TEXT")

    private String  wikiEventData;

    public void setWikiEventData(String wikiEventData) {
        this.wikiEventData = wikiEventData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWikiEventData() {
        return wikiEventData;
    }
}
