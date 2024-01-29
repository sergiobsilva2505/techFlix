package br.com.fiap.techFlix.adapter.web.video;

import br.com.fiap.techFlix.adapter.web.PageDTO;
import br.com.fiap.techFlix.adapter.web.file.FileMapper;
import br.com.fiap.techFlix.adapter.web.file.FileShowDTO;
import br.com.fiap.techFlix.application.gateways.file.FileGateway;
import br.com.fiap.techFlix.application.ports.PagePort;
import br.com.fiap.techFlix.application.useCases.video.*;
import br.com.fiap.techFlix.domain.entities.file.File;
import br.com.fiap.techFlix.domain.entities.video.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VideoControllerTest {

    @Mock
    DeleteVideoUseCase deleteVideoUseCase;

    @Mock
    FileGateway fileGateway;

    @Mock
    ListVideoUseCase listVideoUseCase;

    @Mock
    PublishVideoUseCase publishVideoUseCase;

    @Mock
    SearchVideoUseCase searchVideoUseCase;

    @Mock
    UpdateVideoUseCase updateVideoUseCase;

    @Mock
    VideoRecommendationsUseCase videoRecommendationsUseCase;

    @Mock
    WatchVideoUseCase watchVideoUseCase;

    @Mock
    MultipartFile multipartFile;

    @InjectMocks
    VideoController videoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldPublishVideo() {
        Video video = mock(Video.class);
        when(video.getId()).thenReturn("id");
        when(publishVideoUseCase.publishVideo(any())).thenReturn(video);

        ResponseEntity<String> response = videoController.publishVideo(mock(VideoPublishDTO.class));

        assertEquals(201, response.getStatusCodeValue());
        verify(publishVideoUseCase).publishVideo(any(VideoPublishDTO.class));
    }

    @Test
    void shouldGetVideoById() {
        Video video = mock(Video.class);
        when(listVideoUseCase.listVideo(anyString())).thenReturn(video);

        ResponseEntity<VideoShowDTO> response = videoController.getVideoById("id");

        assertEquals(200, response.getStatusCodeValue());
        verify(listVideoUseCase).listVideo(anyString());
    }

    @Test
    void shouldUpdateVideo() {
        Video video = mock(Video.class);
        when(updateVideoUseCase.updateVideo(anyString(), any(VideoUpdateDTO.class))).thenReturn(video);

        ResponseEntity<VideoShowDTO> response = videoController.updateVideo("id", mock(VideoUpdateDTO.class));

        assertEquals(200, response.getStatusCodeValue());
        verify(updateVideoUseCase).updateVideo(anyString(), any(VideoUpdateDTO.class));
    }

    @Test
    void shouldDeleteVideo() {
        doNothing().when(deleteVideoUseCase).deleteVideo(anyString());

        ResponseEntity<Void> response = videoController.deleteVideo("id");

        assertEquals(204, response.getStatusCodeValue());
        verify(deleteVideoUseCase).deleteVideo(anyString());
    }

    @Test
    void shouldUploadFile() throws Exception {
        File file = mock(File.class);
        FileShowDTO fileShowDTO = FileMapper.toView(file);
        when(fileGateway.saveAttachment(any(MultipartFile.class))).thenReturn(Mono.just(file));

        Mono<FileShowDTO> response = videoController.fileUpload(multipartFile);

        assertEquals(fileShowDTO, response.block());
        verify(fileGateway).saveAttachment(any(MultipartFile.class));
    }

    @Test
    void shouldPlayVideo() {
        byte[] byteArray = new byte[0];
        ByteArrayResource resource = new ByteArrayResource(byteArray);
        when(fileGateway.findById(anyString())).thenReturn(Mono.just(byteArray));

        Mono<Resource> response = videoController.playVideo("id", "bytes=0-");

        assertEquals(resource, response.block());
        verify(fileGateway).findById(anyString());
        verify(watchVideoUseCase).watchVideo(anyString());
    }

    @Test
    void shouldSearchVideos() {
        PagePort<Video> pagePort = new PageDTO<>(Page.empty());
        when(searchVideoUseCase.searchVideos(any(VideoSearchDTO.class))).thenReturn(pagePort);

        PagePort<VideoShowDTO> response = videoController.searchVideos(mock(VideoSearchDTO.class));

        assertEquals(pagePort.getContent(), response.getContent());
        verify(searchVideoUseCase).searchVideos(any(VideoSearchDTO.class));
    }

    @Test
    void shouldGetRecommendations() {
        List<Video> videos = List.of(mock(Video.class));
        List<VideoShowDTO> videoShowDTOList = videos.stream().map(VideoMapper::toView).toList();
        when(videoRecommendationsUseCase.getRecommendations(anyString())).thenReturn(videos);

        List<VideoShowDTO> response = videoController.getRecommendations("userId");

        assertEquals(videoShowDTOList, response);
        verify(videoRecommendationsUseCase).getRecommendations(anyString());
    }
}