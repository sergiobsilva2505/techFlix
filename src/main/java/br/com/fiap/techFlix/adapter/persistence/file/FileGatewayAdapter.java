package br.com.fiap.techFlix.adapter.persistence.file;

import br.com.fiap.techFlix.adapter.web.file.FileMapper;
import br.com.fiap.techFlix.application.gateways.file.FileGateway;
import br.com.fiap.techFlix.domain.entities.file.File;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
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
        return fileRepository.findById(id).map(FileDocument::getContent);
    }

    public Mono<File> saveAttachment(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            if (fileName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence " + fileName);
            }

            if (file.getBytes().length > (1024 * 1024 * 15)) {
                throw new Exception("File size exceeds maximum limit");
            }

            FileDocument attachment = new FileDocument(null, fileName, file.getContentType(), file.getSize(), file.getBytes());

            return fileRepository.save(attachment).map(FileMapper::toDomain);
        } catch (MaxUploadSizeExceededException e) {
            throw new MaxUploadSizeExceededException(file.getSize());
        }
    }
}
