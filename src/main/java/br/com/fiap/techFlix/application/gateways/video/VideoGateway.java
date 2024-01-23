package br.com.fiap.techFlix.application.gateways.video;

import br.com.fiap.techFlix.application.ports.*;
import br.com.fiap.techFlix.domain.entities.category.Category;
import br.com.fiap.techFlix.domain.entities.video.Video;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VideoGateway {

    Optional<Video> findById(String id);

    PagePort<Video> findAll(int page, int size);

    PagePort<Video> searchVideos(VideoSearchPort videoSearchPort);

    Video save(VideoPublishPort videoPublishPort, Category category, LocalDateTime publicationDate);

    List<Video> getRecommendations(String userId);
}
