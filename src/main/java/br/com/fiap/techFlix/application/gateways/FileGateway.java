package br.com.fiap.techFlix.application.gateways;

import br.com.fiap.techFlix.domain.entities.File;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

public interface FileGateway {

    Mono<byte[]> findById(String id);
    File saveAttachment(MultipartFile file, String id) throws Exception;
}
