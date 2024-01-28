package br.com.fiap.techFlix.adapter.persistence.video;

import br.com.fiap.techFlix.adapter.persistence.category.CategoryDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@Testcontainers
@DataMongoTest
class VideoRepositoryIntegrationTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.4").withExposedPorts(27017);

    @Autowired
    VideoRepository videoRepository;

    private VideoDocument video1, video2, video3;

    @BeforeEach
    void setUp() {
        CategoryDocument category = new CategoryDocument("123", "action");
        CategoryDocument category2 = new CategoryDocument("456", "comedy");
        CategoryDocument category3 = new CategoryDocument("789", "drama");
        CategoryDocument category4 = new CategoryDocument("101", "romance");

        video1 = new VideoDocument("123", "title", "description", List.of(category, category2), new VideoDetailsDocument(0, 2), LocalDateTime.now());
        video2 = new VideoDocument("456", "title", "description", List.of(category, category3), new VideoDetailsDocument(2, 0), LocalDateTime.now());
        video3 = new VideoDocument("789", "title", "description", List.of(category4), new VideoDetailsDocument(0, 0), LocalDateTime.now());

        videoRepository.save(video1);
        videoRepository.save(video2);
        videoRepository.save(video3);
    }

    @Test
    void shouldReturnVideosOrderedByLikesAndViewsWhenGetRecommendationsIsCalledWithoutCategories() {
        List<VideoDocument> recommendations = videoRepository.getRecommendations();
        assertEquals(3, recommendations.size());
        assertIterableEquals(List.of(video2.getId(), video1.getId(), video3.getId()), recommendations.stream().map(VideoDocument::getId).toList());
    }

    @Test
    void shouldReturnVideosOrderedByScoreLikesAndViewsWhenGetRecommendationsIsCalledWithCategories() {
        List<VideoDocument> recommendations = videoRepository.getRecommendations(List.of("action"));
        assertEquals(2, recommendations.size());
        assertEquals(video2.getId(), recommendations.get(0).getId());
        assertEquals(video1.getId(), recommendations.get(1).getId());
    }
}