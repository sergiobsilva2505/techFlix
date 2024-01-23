package br.com.fiap.techFlix.application.gateways.video;

import br.com.fiap.techFlix.adapter.web.video.SearchVideoDTO;
import br.com.fiap.techFlix.application.gateways.PagePort;
import br.com.fiap.techFlix.application.ports.VideoPublishPort;
import br.com.fiap.techFlix.domain.entities.category.Category;
import br.com.fiap.techFlix.domain.entities.video.Video;

import java.time.LocalDateTime;
import java.util.Optional;

public interface VideoGateway {

    Optional<Video> findById(String id);

    PagePort<Video> findAll(int page, int size);

    PagePort<Video> searchVideos(SearchVideoDTO searchVideoDTO);

    Video save(VideoPublishPort videoPublishPort, Category category, LocalDateTime now);
}
