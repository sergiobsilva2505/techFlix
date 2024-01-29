package br.com.fiap.techFlix.adapter.web.video;

import br.com.fiap.techFlix.adapter.web.file.FileMapper;
import br.com.fiap.techFlix.adapter.web.file.FileShowDTO;
import br.com.fiap.techFlix.application.gateways.file.FileGateway;
import br.com.fiap.techFlix.application.useCases.video.WatchVideoUseCase;
import br.com.fiap.techFlix.domain.entities.file.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class VideoFileControllerTest {

    @Mock
    FileGateway fileGateway;

    @Mock
    WatchVideoUseCase watchVideoUseCase;

    @Mock
    MultipartFile multipartFile;

    @InjectMocks
    VideoFileController videoFileController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUploadFile() throws Exception {
        File file = mock(File.class);
        FileShowDTO fileShowDTO = FileMapper.toView(file);
        when(fileGateway.saveAttachment(any(MultipartFile.class))).thenReturn(Mono.just(file));

        Mono<FileShowDTO> response = videoFileController.fileUpload(multipartFile);

        assertEquals(fileShowDTO, response.block());
        verify(fileGateway).saveAttachment(any(MultipartFile.class));
    }

    @Test
    void shouldPlayVideo() {
        byte[] byteArray = new byte[0];
        ByteArrayResource resource = new ByteArrayResource(byteArray);
        when(fileGateway.findById(anyString())).thenReturn(Mono.just(byteArray));

        Mono<Resource> response = videoFileController.playVideo("id", "bytes=0-");

        assertEquals(resource, response.block());
        verify(fileGateway).findById(anyString());
        verify(watchVideoUseCase).watchVideo(anyString());
    }
}