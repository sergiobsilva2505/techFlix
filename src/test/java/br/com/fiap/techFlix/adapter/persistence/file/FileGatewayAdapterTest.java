package br.com.fiap.techFlix.adapter.persistence.file;

import br.com.fiap.techFlix.domain.entities.file.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FileGatewayAdapterTest {

    private FileRepository fileRepository;
    private MultipartFile multipartFile;
    private FileGatewayAdapter fileGatewayAdapter;

    @BeforeEach
    public void setup() {
        fileRepository = mock(FileRepository.class);
        multipartFile = mock(MultipartFile.class);
        fileGatewayAdapter = new FileGatewayAdapter(fileRepository);
    }

    @Test
    public void findByIdReturnsFileContentWhenExists() {
        String fileId = "123";
        FileDocument document = new FileDocument("123", "test.txt", "video/mp4", 3, new byte[]{1, 2, 3});
        when(fileRepository.findById(fileId)).thenReturn(Mono.just(document));
        Mono<byte[]> result = fileGatewayAdapter.findById(fileId);
        StepVerifier.create(result)
                .expectNext(document.getContent())
                .verifyComplete();
    }

    @Test
    public void findByIdReturnsEmptyWhenDoesNotExist() {
        String fileId = "123";
        when(fileRepository.findById(fileId)).thenReturn(Mono.empty());
        Mono<byte[]> result = fileGatewayAdapter.findById(fileId);
        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    public void saveAttachmentSuccessfully() throws Exception {
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
    public void saveAttachmentFailsWhenFileNameContainsInvalidPath() throws Exception {
        String fileName = "../test.txt";
        when(multipartFile.getOriginalFilename()).thenReturn(fileName);
        assertThrows(Exception.class, () -> fileGatewayAdapter.saveAttachment(multipartFile));
    }

    @Test
    public void saveAttachmentFailsWhenFileSizeExceedsLimit() throws Exception {
        String fileName = "test.txt";
        byte[] largeFileContent = new byte[1024 * 1024 * 16]; // 16MB
        when(multipartFile.getOriginalFilename()).thenReturn(fileName);
        when(multipartFile.getBytes()).thenReturn(largeFileContent);
        assertThrows(Exception.class, () -> fileGatewayAdapter.saveAttachment(multipartFile));
    }
}