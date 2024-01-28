package br.com.fiap.techFlix.adapter.persistence.bookmarkvideo;

import br.com.fiap.techFlix.adapter.persistence.category.CategoryDocument;
import br.com.fiap.techFlix.adapter.persistence.user.UserDocument;
import br.com.fiap.techFlix.adapter.persistence.video.VideoDetailsDocument;
import br.com.fiap.techFlix.adapter.persistence.video.VideoDocument;
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
class BookmarkVideoRepositoryIntegrationTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.4").withExposedPorts(27017);

    @Autowired
    BookmarkVideoRepository bookmarkVideoRepository;

    private BookmarkVideoDocument bookmarkVideoDocument, bookmarkVideoDocument2;

    @BeforeEach
    void setUp() {
        UserDocument user = new UserDocument("123", "name", "email", "password", "token");

        CategoryDocument category = new CategoryDocument("123", "action");
        CategoryDocument category2 = new CategoryDocument("456", "comedy");
        CategoryDocument category3 = new CategoryDocument("789", "drama");

        VideoDocument video = new VideoDocument("123", "title", "description", List.of(category, category2), new VideoDetailsDocument(0, 0), LocalDateTime.now());
        VideoDocument video2 = new VideoDocument("456", "title", "description", List.of(category, category3), new VideoDetailsDocument(0, 0), LocalDateTime.now());

        bookmarkVideoDocument = new BookmarkVideoDocument("123", user, video);
        bookmarkVideoDocument2 = new BookmarkVideoDocument("456", user, video2);

        bookmarkVideoRepository.save(bookmarkVideoDocument);
        bookmarkVideoRepository.save(bookmarkVideoDocument2);
    }

    @Test
    void shouldReturnTop5LikedCategories() {
        List<UserBookmarkedCategories> top5LikedCategories = bookmarkVideoRepository.getTop5LikedCategories("123");
        assertEquals(3, top5LikedCategories.size());
        assertIterableEquals(List.of("action", "comedy", "drama"), top5LikedCategories.stream().map(UserBookmarkedCategories::getName).sorted().toList());
    }
}
