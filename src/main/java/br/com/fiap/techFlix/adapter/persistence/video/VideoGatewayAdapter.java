package br.com.fiap.techFlix.adapter.persistence.video;

import br.com.fiap.techFlix.adapter.persistence.bookmarkvideo.BookmarkVideoRepository;
import br.com.fiap.techFlix.adapter.persistence.category.CategoryDocument;
import br.com.fiap.techFlix.adapter.web.PageDTO;
import br.com.fiap.techFlix.adapter.web.category.CategoryMapper;
import br.com.fiap.techFlix.adapter.web.video.VideoMapper;
import br.com.fiap.techFlix.application.gateways.video.VideoGateway;
import br.com.fiap.techFlix.application.ports.*;
import br.com.fiap.techFlix.domain.entities.category.Category;
import br.com.fiap.techFlix.domain.entities.video.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class VideoGatewayAdapter implements VideoGateway {

    private final BookmarkVideoRepository bookmarkVideoRepository;
    private final VideoRepository videoRepository;

    public VideoGatewayAdapter(BookmarkVideoRepository bookmarkVideoRepository, VideoRepository videoRepository) {
        this.bookmarkVideoRepository = bookmarkVideoRepository;
        this.videoRepository = videoRepository;
    }

    @Override
    public void watchVideo(String id) {
        videoRepository.addView(id);
    }

    @Override
    public void likeVideo(String id) {
        videoRepository.addLike(id);
    }

    @Override
    public void unlikeVideo(String id) {
        videoRepository.removeLike(id);
    }

    @Override
    public boolean existsById(String id) {
        return videoRepository.existsById(id);
    }

    @Override
    public Optional<Video> findById(String id) {
        return videoRepository.findById(id).map(VideoMapper::toDomain);
    }

    @Override
    public PagePort<Video> findAll(int page, int size) {
        Page<Video> videos = videoRepository.findAll(PageRequest.of(page, size)).map(VideoMapper::toDomain);
        return new PageDTO<>(videos);
    }

    @Override
    public PagePort<Video> searchVideos(VideoSearchPort videoSearchPort) {
        Page<VideoDocument> search = videoRepository.search(videoSearchPort);

        return new PageDTO<>(search).map(VideoMapper::toDomain);
    }

    @Override
    public Video save(VideoPublishPort videoPublishPort, List<Category> categories, LocalDateTime publicationDate) {
        List<CategoryDocument> categoryDocuments = categories.stream().map(CategoryMapper::toPersistence).toList();
        VideoDocument saved = videoRepository.save(VideoMapper.toPersistence(videoPublishPort, categoryDocuments, publicationDate));
        return VideoMapper.toDomain(saved);
    }

    @Override
    public Video update(Video video, VideoUpdatePort videoUpdatePort, List<Category> categories) {
        video.setTitle(videoUpdatePort.title());
        video.setDescription(videoUpdatePort.description());
        video.setCategories(categories);

        VideoDocument saved = videoRepository.save(VideoMapper.toPersistence(video));
        return VideoMapper.toDomain(saved);
    }

    @Override
    public void deleteVideo(String id) {
        videoRepository.deleteById(id);
    }

    @Override
    public List<UserBookmarkedCategoriesPort> getLikedCategories(String userId) {
        return new ArrayList<>(bookmarkVideoRepository.getTop5LikedCategories(userId));
    }

    @Override
    public List<Video> getRecommendations() {
        return videoRepository.getRecommendations().stream().map(VideoMapper::toDomain).toList();
    }

    @Override
    public List<Video> getRecommendations(List<UserBookmarkedCategoriesPort> categories) {
        List<String> categoriesNames = categories.stream().map(UserBookmarkedCategoriesPort::getName).toList();
        return videoRepository.getRecommendations(categoriesNames).stream().map(VideoMapper::toDomain).toList();
    }

    @Override
    public VideoStatisticsPort getOverallStatistics() {
        return videoRepository.getOverallStatistics();
    }
}
