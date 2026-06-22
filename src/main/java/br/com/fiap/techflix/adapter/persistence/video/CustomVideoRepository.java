package br.com.fiap.techflix.adapter.persistence.video;

import br.com.fiap.techflix.application.ports.VideoSearchPort;
import org.springframework.data.domain.Page;

public interface CustomVideoRepository {

    Page<VideoDocument> search(VideoSearchPort videoSearchPort);
}
