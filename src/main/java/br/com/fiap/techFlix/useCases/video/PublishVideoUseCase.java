package br.com.fiap.techFlix.useCases.video;

import br.com.fiap.techFlix.entities.category.Category;
import br.com.fiap.techFlix.entities.video.Video;

import java.time.LocalDateTime;

public class PublishVideoUseCase {

    public Video publishVideo(String title, String description, Category category) {
        LocalDateTime publicationDate = LocalDateTime.now();
        return new Video(title, description, category, publicationDate);
    }
}
