package br.com.fiap.techFlix.adapter.persistence.video;

import br.com.fiap.techFlix.application.ports.VideoSearchPort;
import org.springframework.data.domain.Page;

public interface CustomVideoRepository {

    Page<VideoDocument> search(VideoSearchPort videoSearchPort);
}
