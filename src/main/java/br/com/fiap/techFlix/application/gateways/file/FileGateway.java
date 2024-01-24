package br.com.fiap.techFlix.application.gateways.file;

import br.com.fiap.techFlix.domain.entities.file.File;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

public interface FileGateway {

    Mono<byte[]> findById(String id);

    Mono<File> saveAttachment(MultipartFile file) throws Exception;
}
