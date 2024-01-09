package br.com.fiap.techFlix.infrastructure.gateways;

import br.com.fiap.techFlix.application.gateways.CategoryGateway;
import br.com.fiap.techFlix.application.gateways.VideoGateway;
import br.com.fiap.techFlix.domain.entities.Category;
import br.com.fiap.techFlix.domain.entities.Video;
import br.com.fiap.techFlix.infrastructure.persistence.CategoryRepository;
import br.com.fiap.techFlix.infrastructure.persistence.VideoRepository;

import java.util.Optional;

public class VideoRepositoryGateway implements VideoGateway {

    private final VideoRepository videoRepository;

    public VideoRepositoryGateway(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public Video save(Video video) {
//        VideoDocument saved = videoRepository.save(VideoMapper.toPersistence(video));
//        return VideoMapper.toDomain(saved);
        return null;
    }

    @Override
    public Optional<Video> findById(String id) {
        return Optional.empty();
    }
}
