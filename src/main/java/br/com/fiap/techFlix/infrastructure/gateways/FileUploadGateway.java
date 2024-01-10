package br.com.fiap.techFlix.infrastructure.gateways;

import br.com.fiap.techFlix.application.gateways.FileGateway;
import br.com.fiap.techFlix.domain.entities.File;
import br.com.fiap.techFlix.infrastructure.persistence.FileDocument;
import br.com.fiap.techFlix.infrastructure.persistence.FileRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class FileUploadGateway implements FileGateway {

    private final FileRepository fileRepository;

    public FileUploadGateway(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public Mono<byte[]> findById(String id) {
        return fileRepository.findById(id).map(FileDocument::getContent);
    }

    public File saveAttachment(MultipartFile file, String id) throws Exception {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence " + fileName);
            }

            if (file.getBytes().length > (1024 * 1024 * 4)) {
                throw new Exception("File size exceeds maximum limit");
            }

            FileDocument attachment = new FileDocument(id, fileName, file.getContentType(), file.getSize(), file.getBytes());

            return FileMapper.toDomain(fileRepository.save(attachment).block());
        } catch (MaxUploadSizeExceededException e) {
            throw new MaxUploadSizeExceededException(file.getSize());
        }
    }
}
