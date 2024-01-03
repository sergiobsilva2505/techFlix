package br.com.fiap.techFlix.useCases.video;

import br.com.fiap.techFlix.adapters.category.CategoryMapper;
import br.com.fiap.techFlix.adapters.category.CategoryRepository;
import br.com.fiap.techFlix.adapters.video.*;
import br.com.fiap.techFlix.entities.category.Category;
import br.com.fiap.techFlix.entities.video.Video;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
public class PublishVideoUseCase {

    private final CategoryRepository categoryRepository;
    private final VideoRepository videoRepository;

    public PublishVideoUseCase(CategoryRepository categoryRepository, VideoRepository videoRepository) {
        this.categoryRepository = categoryRepository;
        this.videoRepository = videoRepository;
    }

    public Mono<Video> publishVideo(VideoPublishDTO videoPublishDTO) {
        CategoryMapper category = categoryRepository.findByName(videoPublishDTO.categoryName()).orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Mono<VideoMapper> videoMapper = videoRepository.save(new VideoMapper(videoPublishDTO.title(), videoPublishDTO.description(), category, LocalDateTime.now()));
        return videoMapper.map(video -> new Video(video.getId(), video.getTitle(), video.getDescription(), new Category(category.getName()), video.getPublicationDate()));
    }
}
