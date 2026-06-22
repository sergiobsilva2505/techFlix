package br.com.fiap.techflix.application.gateways.video;

import br.com.fiap.techflix.application.ports.*;
import br.com.fiap.techflix.domain.entities.category.Category;
import br.com.fiap.techflix.domain.entities.video.Video;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VideoGateway {

    void watchVideo(String id);

    void likeVideo(String id);

    void unlikeVideo(String id);

    boolean existsById(String id);

    Optional<Video> findById(String id);

    PagePort<Video> findAll(int page, int size);

    PagePort<Video> searchVideos(VideoSearchPort videoSearchPort);

    Video save(VideoPublishPort videoPublishPort, List<Category> categories, LocalDateTime publicationDate);

    Video update(Video video, VideoUpdatePort videoUpdatePort, List<Category> categories);

    void deleteVideo(String id);

    List<UserBookmarkedCategoriesPort> getLikedCategories(String userId);

    List<Video> getRecommendations();

    List<Video> getRecommendations(List<UserBookmarkedCategoriesPort> categories);

    VideoStatisticsPort getOverallStatistics();
}
