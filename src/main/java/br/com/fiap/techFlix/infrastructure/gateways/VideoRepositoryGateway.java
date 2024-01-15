package br.com.fiap.techFlix.infrastructure.gateways;

import br.com.fiap.techFlix.application.gateways.PagePort;
import br.com.fiap.techFlix.application.gateways.VideoGateway;
import br.com.fiap.techFlix.domain.entities.Video;
import br.com.fiap.techFlix.infrastructure.persistence.VideoDocument;
import br.com.fiap.techFlix.infrastructure.persistence.VideoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public class VideoRepositoryGateway implements VideoGateway {

    private final VideoRepository videoRepository;

    public VideoRepositoryGateway(VideoRepository videoRepository) {
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
}
