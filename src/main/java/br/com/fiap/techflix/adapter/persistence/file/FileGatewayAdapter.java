package br.com.fiap.techflix.adapter.persistence.file;

import br.com.fiap.techflix.adapter.persistence.file.exception.FileSizeExceededException;
import br.com.fiap.techflix.adapter.web.file.FileMapper;
import br.com.fiap.techflix.application.gateways.file.FileGateway;
import br.com.fiap.techflix.domain.entities.file.File;
import br.com.fiap.techflix.domain.entities.file.FileNotFoundException;
import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class FileGatewayAdapter implements FileGateway {

    private final FileRepository fileRepository;

    public FileGatewayAdapter(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public Mono<byte[]> findById(String id) {
        return fileRepository.findById(id)
                .switchIfEmpty(Mono.error(new FileNotFoundException(id)))
                .map(FileDocument::getContent);
    }

    public Mono<File> saveAttachment(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        if (fileName.contains("..")) {
            throw new InvalidFileNameException(fileName, "Filename contains invalid path sequence ");
        }

        if (file.getBytes().length > (1024 * 1024 * 15)) {
            throw new FileSizeExceededException("File size exceeds maximum limit");
        }

        FileDocument attachment = new FileDocument(null, fileName, file.getContentType(), file.getSize(), file.getBytes());

        return fileRepository.save(attachment).map(FileMapper::toDomain);
    }
}
