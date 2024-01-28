package br.com.fiap.techFlix.adapter.persistence.video;

import br.com.fiap.techFlix.adapter.persistence.bookmarkvideo.BookmarkVideoRepository;
import br.com.fiap.techFlix.adapter.persistence.bookmarkvideo.UserBookmarkedCategories;
import br.com.fiap.techFlix.adapter.persistence.category.CategoryDocument;
import br.com.fiap.techFlix.application.ports.*;
import br.com.fiap.techFlix.domain.entities.category.Category;
import br.com.fiap.techFlix.domain.entities.video.Video;
import br.com.fiap.techFlix.domain.entities.video.VideoDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VideoGatewayAdapterTest {

    private BookmarkVideoRepository bookmarkVideoRepository;
    private VideoRepository videoRepository;
    private VideoGatewayAdapter videoGatewayAdapter;

    @BeforeEach
    void setUp() {
        bookmarkVideoRepository = mock(BookmarkVideoRepository.class);
        videoRepository = mock(VideoRepository.class);
        videoGatewayAdapter = new VideoGatewayAdapter(bookmarkVideoRepository, videoRepository);
    }

    @Test
    void shouldIncrementViewsWhenWatchVideoIsCalled() {
        String videoId = "videoId";
        videoGatewayAdapter.watchVideo(videoId);
        verify(videoRepository).addView(videoId);
    }

    @Test
    void shouldIncrementLikesWhenLikeVideoIsCalled() {
        String videoId = "videoId";
        videoGatewayAdapter.likeVideo(videoId);
        verify(videoRepository).addLike(videoId);
    }

    @Test
    void shouldDecrementLikesWhenUnlikeVideoIsCalled() {
        String videoId = "videoId";
        videoGatewayAdapter.unlikeVideo(videoId);
        verify(videoRepository).removeLike(videoId);
    }

    @Test
    void shouldReturnTrueWhenExistsByIdIsCalledWithExistingId() {
        String videoId = "existingId";
        when(videoRepository.existsById(videoId)).thenReturn(true);
        boolean exists = videoGatewayAdapter.existsById(videoId);
        assertTrue(exists);
    }

    @Test
    void shouldReturnFalseWhenExistsByIdIsCalledWithNonExistingId() {
        String videoId = "nonExistingId";
        when(videoRepository.existsById(videoId)).thenReturn(false);
        boolean exists = videoGatewayAdapter.existsById(videoId);
        assertFalse(exists);
    }

    @Test
    void shouldReturnVideoWhenFindByIdIsCalledWithExistingId() {
        String videoId = "existingId";
        VideoDocument video = getVideoDocument();
        when(videoRepository.findById(videoId)).thenReturn(Optional.of(video));
        Optional<Video> returnedVideo = videoGatewayAdapter.findById(videoId);
        assertEquals(video.getId(), returnedVideo.get().getId());
    }

    @Test
    void shouldReturnEmptyWhenFindByIdIsCalledWithNonExistingId() {
        String videoId = "nonExistingId";
        when(videoRepository.findById(videoId)).thenReturn(Optional.empty());
        Optional<Video> returnedVideo = videoGatewayAdapter.findById(videoId);
        assertEquals(Optional.empty(), returnedVideo);
    }

    @Test
    void shouldReturnPageOfVideosWhenFindAllIsCalled() {
        Page<Video> page = Page.empty();
        when(videoRepository.findAll(PageRequest.of(0, 10))).thenReturn(Page.empty());
        PagePort<Video> returnedPage = videoGatewayAdapter.findAll(0, 10);
        assertIterableEquals(page.getContent(), returnedPage.getContent());
    }

    @Test
    void shouldReturnPageOfVideosWhenSearchVideosIsCalledWithValidData() {
        VideoSearchPort videoSearchPort = mock(VideoSearchPort.class);
        Page<VideoDocument> page = Page.empty();
        when(videoRepository.search(videoSearchPort)).thenReturn(page);
        PagePort<Video> returnedPage = videoGatewayAdapter.searchVideos(videoSearchPort);
        assertIterableEquals(page.getContent(), returnedPage.getContent());
    }

    @Test
    void shouldReturnEmptyPageWhenSearchVideosIsCalledWithNoMatchingData() {
        VideoSearchPort videoSearchPort = mock(VideoSearchPort.class);
        when(videoRepository.search(videoSearchPort)).thenReturn(Page.empty());
        PagePort<Video> returnedPage = videoGatewayAdapter.searchVideos(videoSearchPort);
        assertTrue(returnedPage.getContent().isEmpty());
    }

    @Test
    void shouldSaveVideoWhenValidDataIsProvided() {
        VideoPublishPort videoPublishPort = mock(VideoPublishPort.class);
        List<Category> categories = List.of(new Category("123", "action"));
        LocalDateTime publicationDate = LocalDateTime.now();

        VideoDocument videoDocument = getVideoDocument();
        when(videoRepository.save(any(VideoDocument.class))).thenReturn(videoDocument);

        Video returnedVideo = videoGatewayAdapter.save(videoPublishPort, categories, publicationDate);

        assertEquals(videoDocument.getId(), returnedVideo.getId());
    }

    @Test
    void shouldUpdateVideoWhenValidDataIsProvided() {
        Video video = new Video("videoId", "title", "description", List.of(new Category("123", "action")), new VideoDetails(0, 0), LocalDateTime.now());
        VideoUpdatePort videoUpdatePort = mock(VideoUpdatePort.class);
        List<Category> categories = List.of(new Category("456", "romance"));

        when(videoUpdatePort.title()).thenReturn("newTitle");
        when(videoUpdatePort.description()).thenReturn("newDescription");
        when(videoUpdatePort.categoryNames()).thenReturn(categories.stream().map(Category::getName).toList());

        when(videoRepository.save(any(VideoDocument.class))).thenAnswer(a -> a.getArguments()[0]);

        Video returnedVideo = videoGatewayAdapter.update(video, videoUpdatePort, categories);

        assertEquals("newTitle", returnedVideo.getTitle());
        assertEquals("newDescription", returnedVideo.getDescription());
        assertIterableEquals(categories.stream().map(Category::getName).toList(), returnedVideo.getCategories().stream().map(Category::getName).toList());
    }

    @Test
    void shouldDeleteVideoWhenDeleteVideoIsCalled() {
        String videoId = "videoId";
        doNothing().when(videoRepository).deleteById(videoId);
        videoGatewayAdapter.deleteVideo(videoId);
        verify(videoRepository).deleteById(videoId);
    }

    @Test
    void shouldReturnLikedCategoriesWhenGetLikedCategoriesIsCalledWithValidUserId() {
        String userId = "userId";
        List<UserBookmarkedCategories> categories = List.of(new UserBookmarkedCategories("action", 5));
        when(bookmarkVideoRepository.getTop5LikedCategories(userId)).thenReturn(categories);
        List<UserBookmarkedCategoriesPort> returnedCategories = videoGatewayAdapter.getLikedCategories(userId);
        assertEquals(categories.size(), returnedCategories.size());
    }

    @Test
    void shouldReturnRecommendationsWhenGetRecommendationsIsCalled() {
        List<VideoDocument> videos = List.of(getVideoDocument());
        when(videoRepository.getRecommendations()).thenReturn(videos);
        List<Video> returnedVideos = videoGatewayAdapter.getRecommendations();
        assertIterableEquals(videos.stream().map(VideoDocument::getId).toList(), returnedVideos.stream().map(Video::getId).toList());
    }

    @Test
    void shouldReturnRecommendationsWhenGetRecommendationsWithCategoriesIsCalled() {
        List<VideoDocument> videos = List.of(getVideoDocument());
        UserBookmarkedCategories userCategory = new UserBookmarkedCategories("action", 5);
        when(bookmarkVideoRepository.getTop5LikedCategories(any())).thenReturn(List.of(userCategory));
        when(videoRepository.getRecommendations(any())).thenReturn(videos);
        List<Video> returnedVideos = videoGatewayAdapter.getRecommendations(List.of(userCategory));
        assertIterableEquals(videos.stream().map(VideoDocument::getId).toList(), returnedVideos.stream().map(Video::getId).toList());
    }

    private VideoDocument getVideoDocument() {
        return new VideoDocument("videoId", "title", "description", List.of(new CategoryDocument("123", "action")), new VideoDetailsDocument(0, 0), LocalDateTime.now());
    }
}