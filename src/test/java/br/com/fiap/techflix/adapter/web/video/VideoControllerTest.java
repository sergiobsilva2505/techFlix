package br.com.fiap.techflix.adapter.web.video;

import br.com.fiap.techflix.adapter.web.PageDTO;
import br.com.fiap.techflix.application.ports.PagePort;
import br.com.fiap.techflix.application.usecases.video.*;
import br.com.fiap.techflix.domain.entities.video.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VideoControllerTest {

    @Mock
    DeleteVideoUseCase deleteVideoUseCase;

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

    @InjectMocks
    VideoController videoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Publica vídeo com sucesso")
    void shouldPublishVideo() {
        Video video = mock(Video.class);
        when(video.getId()).thenReturn("id");
        when(publishVideoUseCase.publishVideo(any())).thenReturn(video);

        ResponseEntity<String> response = videoController.publishVideo(mock(VideoPublishDTO.class));

        assertEquals(201, response.getStatusCode().value());
        verify(publishVideoUseCase).publishVideo(any(VideoPublishDTO.class));
    }

    @Test
    @DisplayName("Retorna vídeo pelo ID")
    void shouldGetVideoById() {
        Video video = mock(Video.class);
        when(listVideoUseCase.listVideo(anyString())).thenReturn(video);

        ResponseEntity<VideoShowDTO> response = videoController.getVideoById("id");

        assertEquals(200, response.getStatusCode().value());
        verify(listVideoUseCase).listVideo(anyString());
    }

    @Test
    @DisplayName("Atualiza vídeo com sucesso")
    void shouldUpdateVideo() {
        Video video = mock(Video.class);
        when(updateVideoUseCase.updateVideo(anyString(), any(VideoUpdateDTO.class))).thenReturn(video);

        ResponseEntity<VideoShowDTO> response = videoController.updateVideo("id", mock(VideoUpdateDTO.class));

        assertEquals(200, response.getStatusCode().value());
        verify(updateVideoUseCase).updateVideo(anyString(), any(VideoUpdateDTO.class));
    }

    @Test
    @DisplayName("Remove vídeo com sucesso")
    void shouldDeleteVideo() {
        doNothing().when(deleteVideoUseCase).deleteVideo(anyString());

        ResponseEntity<Void> response = videoController.deleteVideo("id");

        assertEquals(204, response.getStatusCode().value());
        verify(deleteVideoUseCase).deleteVideo(anyString());
    }

    @Test
    @DisplayName("Busca vídeos com filtros")
    void shouldSearchVideos() {
        PagePort<Video> pagePort = new PageDTO<>(Page.empty());
        when(searchVideoUseCase.searchVideos(any(VideoSearchDTO.class))).thenReturn(pagePort);

        PagePort<VideoShowDTO> response = videoController.searchVideos(mock(VideoSearchDTO.class));

        assertEquals(pagePort.getContent(), response.getContent());
        verify(searchVideoUseCase).searchVideos(any(VideoSearchDTO.class));
    }

    @Test
    @DisplayName("Retorna recomendações de vídeos para o usuário")
    void shouldGetRecommendations() {
        List<Video> videos = List.of(mock(Video.class));
        List<VideoShowDTO> videoShowDTOList = videos.stream().map(VideoMapper::toView).toList();
        when(videoRecommendationsUseCase.getRecommendations(anyString())).thenReturn(videos);

        List<VideoShowDTO> response = videoController.getRecommendations("userId");

        assertEquals(videoShowDTOList, response);
        verify(videoRecommendationsUseCase).getRecommendations(anyString());
    }
}