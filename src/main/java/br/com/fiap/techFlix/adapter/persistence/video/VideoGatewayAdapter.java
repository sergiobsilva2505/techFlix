package br.com.fiap.techFlix.adapter.persistence.video;

import br.com.fiap.techFlix.adapter.persistence.category.CategoryDocument;
import br.com.fiap.techFlix.adapter.web.Operation;
import br.com.fiap.techFlix.adapter.web.PageDTO;
import br.com.fiap.techFlix.adapter.web.category.CategoryMapper;
import br.com.fiap.techFlix.adapter.web.video.VideoMapper;
import br.com.fiap.techFlix.application.gateways.video.VideoGateway;
import br.com.fiap.techFlix.application.ports.*;
import br.com.fiap.techFlix.domain.entities.category.Category;
import br.com.fiap.techFlix.domain.entities.video.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class VideoGatewayAdapter implements VideoGateway {

    private final MongoTemplate mongoTemplate;
    private final VideoRepository videoRepository;

    public VideoGatewayAdapter(MongoTemplate mongoTemplate, VideoRepository videoRepository) {
        this.mongoTemplate = mongoTemplate;
        this.videoRepository = videoRepository;
    }

    @Override
    public Video save(VideoPublishPort videoPublishPort, Category category, LocalDateTime publicationDate) {
        CategoryDocument categoryDocument = CategoryMapper.toPersistence(category);
        VideoDocument saved = videoRepository.save(VideoMapper.toPersistence(videoPublishPort, categoryDocument, publicationDate));
        return VideoMapper.toDomain(saved);
    }

    @Override
    public List<Video> getRecommendations(String userId) {
        return List.of();
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
        Query query = new Query();

        if (videoSearchPort.hasTitle()) {
            Pattern pattern = Pattern.compile(".*%s.*".formatted(videoSearchPort.title()), Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("title").regex(pattern));
        }

        if (videoSearchPort.hasCategoryName()) {
            Pattern pattern = Pattern.compile(".*%s.*".formatted(videoSearchPort.categoryName()), Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("category.name").regex(pattern));
        }

        if (videoSearchPort.hasPublicationDate()) {
            if (Operation.GTE.equals(videoSearchPort.publicationDateOperation())) {
                query.addCriteria(Criteria.where("publicationDate").gte(videoSearchPort.publicationDate()));
            } else if (Operation.LTE.equals(videoSearchPort.publicationDateOperation())) {
                query.addCriteria(Criteria.where("publicationDate").lte(videoSearchPort.publicationDate().atTime(LocalTime.MAX)));
            }
        }

        long count = mongoTemplate.count(query, VideoDocument.class);

        PageRequest pageable = PageRequest.of(videoSearchPort.page(), videoSearchPort.size());
        query.with(pageable);

        List<VideoDocument> videoDocuments = mongoTemplate.find(query, VideoDocument.class);
        Page<VideoDocument> page = PageableExecutionUtils.getPage(videoDocuments, pageable, () -> count);

        return new PageDTO<>(page).map(VideoMapper::toDomain);
    }
}