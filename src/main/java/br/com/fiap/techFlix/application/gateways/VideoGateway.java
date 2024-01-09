package br.com.fiap.techFlix.application.gateways;

import br.com.fiap.techFlix.domain.entities.Video;

import java.util.List;
import java.util.Optional;

public interface VideoGateway {

    Video save(Video video);

    Optional<Video> findById(String id);

    List<Video> findAll();
}
