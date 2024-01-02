package br.com.fiap.techFlix.video;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Video {

    @Id
    private String id;
    private String title;
    private String description;
    private String url;
    private LocalDateTime publicationDate;



}
