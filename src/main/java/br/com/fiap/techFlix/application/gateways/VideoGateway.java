package br.com.fiap.techFlix.application.gateways;

import br.com.fiap.techFlix.domain.entities.Video;
import br.com.fiap.techFlix.infrastructure.controllers.SearchVideoDTO;

import java.util.Optional;

public interface VideoGateway {

    Video save(Video video);

    Optional<Video> findById(String id);

    PagePort<Video> findAll(int page, int size);

    PagePort<Video> searchVideos(SearchVideoDTO searchVideoDTO);

}
