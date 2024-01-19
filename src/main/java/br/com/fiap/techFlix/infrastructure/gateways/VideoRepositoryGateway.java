package br.com.fiap.techFlix.infrastructure.gateways;

import br.com.fiap.techFlix.application.gateways.PagePort;
import br.com.fiap.techFlix.application.gateways.VideoGateway;
import br.com.fiap.techFlix.domain.entities.Video;
import br.com.fiap.techFlix.infrastructure.controllers.SearchVideoDTO;
import br.com.fiap.techFlix.infrastructure.persistence.VideoDocument;
import br.com.fiap.techFlix.infrastructure.persistence.VideoRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class VideoRepositoryGateway implements VideoGateway {

    private final MongoTemplate mongoTemplate;
    private final VideoRepository videoRepository;

    public VideoRepositoryGateway(MongoTemplate mongoTemplate, VideoRepository videoRepository) {
        this.mongoTemplate = mongoTemplate;
        this.videoRepository = videoRepository;
    }

    @Override
    public Video save(Video video) {
        VideoDocument saved = videoRepository.save(VideoMapper.toPersistence(video));
        return VideoMapper.toDomain(saved);
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
    public PagePort<Video> searchVideos(SearchVideoDTO searchVideoDTO) {
        Query query = new Query();

        if (searchVideoDTO.hasTitle()) {
            Pattern pattern = Pattern.compile(".*%s.*".formatted(searchVideoDTO.title()), Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("title").regex(pattern));
        }

        PageRequest pageable = PageRequest.of(searchVideoDTO.page(), searchVideoDTO.size());
        query.with(pageable);

        List<VideoDocument> videoDocuments = mongoTemplate.find(query, VideoDocument.class);
        Page<VideoDocument> page = PageableExecutionUtils.getPage(videoDocuments, pageable, () -> mongoTemplate.count(query, VideoDocument.class));

        return new PageDTO<>(page).map(VideoMapper::toDomain);
    }
}
