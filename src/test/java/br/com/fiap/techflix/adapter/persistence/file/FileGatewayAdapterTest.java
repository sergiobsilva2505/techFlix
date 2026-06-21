package br.com.fiap.techflix.adapter.persistence.file;

import br.com.fiap.techflix.domain.entities.file.File;
import br.com.fiap.techflix.domain.entities.file.FileNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import br.com.fiap.techflix.adapter.persistence.file.exception.FileSizeExceededException;
import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import java.io.IOException;

import static org.mockito.Mockito.*;

class FileGatewayAdapterTest {

    private FileRepository fileRepository;
    private MultipartFile multipartFile;
    private FileGatewayAdapter fileGatewayAdapter;

    @BeforeEach
    void setup() {
        fileRepository = mock(FileRepository.class);
        multipartFile = mock(MultipartFile.class);
        fileGatewayAdapter = new FileGatewayAdapter(fileRepository);
    }

    @Test
    void findByIdReturnsFileContentWhenExists() {
        String fileId = "123";
        FileDocument document = new FileDocument("123", "test.txt", "video/mp4", 3, new byte[]{1, 2, 3});
        when(fileRepository.findById(fileId)).thenReturn(Mono.just(document));
        Mono<byte[]> result = fileGatewayAdapter.findById(fileId);
        StepVerifier.create(result)
                .expectNext(document.getContent())
                .verifyComplete();
    }

    @Test
    void findByIdReturnsEmptyWhenDoesNotExist() {
        String fileId = "123";
        when(fileRepository.findById(fileId)).thenReturn(Mono.empty());
        Mono<byte[]> result = fileGatewayAdapter.findById(fileId);
        StepVerifier.create(result)
                .expectError(FileNotFoundException.class)
                .verify();
    }

    @Test
    void saveAttachmentSuccessfully() throws Exception {
        String fileName = "test.txt";
        when(multipartFile.getOriginalFilename()).thenReturn(fileName);
        when(multipartFile.getBytes()).thenReturn(new byte[]{1, 2, 3});
        FileDocument document = new FileDocument("123", fileName, "video/mp4", 3, new byte[]{1, 2, 3});
        when(fileRepository.save(any())).thenReturn(Mono.just(document));
        Mono<File> result = fileGatewayAdapter.saveAttachment(multipartFile);
        StepVerifier.create(result)
                .expectNextMatches(file -> file.getName().equals(fileName))
                .verifyComplete();
    }

    @Test
    void saveAttachmentFailsWhenFileNameContainsInvalidPath() {
        String fileName = "../test.txt";
        when(multipartFile.getOriginalFilename()).thenReturn(fileName);

        Mono<File> result = Mono.defer(() -> {
            try {
                return fileGatewayAdapter.saveAttachment(multipartFile);
            } catch (Exception e) {
                return Mono.error(e);
            }
        });

        StepVerifier.create(result)
                .expectError(InvalidFileNameException.class)
                .verify();
    }

    @Test
    void saveAttachmentFailsWhenFileSizeExceedsLimit() throws IOException {
        String fileName = "test.txt";
        byte[] largeFileContent = new byte[1024 * 1024 * 16]; // 16MB
        when(multipartFile.getOriginalFilename()).thenReturn(fileName);
        when(multipartFile.getBytes()).thenReturn(largeFileContent);

        Mono<File> result = Mono.defer(() -> {
            try {
                return fileGatewayAdapter.saveAttachment(multipartFile);
            } catch (Exception e) {
                return Mono.error(e);
            }
        });

        StepVerifier.create(result)
                .expectError(FileSizeExceededException.class)
                .verify();
    }
}