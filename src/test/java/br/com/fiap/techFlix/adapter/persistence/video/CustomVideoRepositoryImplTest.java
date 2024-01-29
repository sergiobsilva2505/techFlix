package br.com.fiap.techFlix.adapter.persistence.video;

import br.com.fiap.techFlix.application.ports.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomVideoRepositoryImplTest {

    private MongoTemplate mongoTemplate;
    private CustomVideoRepositoryImpl customVideoRepositoryImpl;

    @BeforeEach
    void setUp() {
        mongoTemplate = mock(MongoTemplate.class);
        customVideoRepositoryImpl = new CustomVideoRepositoryImpl(mongoTemplate);
    }

    @Test
    void shouldReturnPageOfVideosWhenSearchIsCalledWithValidData() {
        VideoSearchPort videoSearchPort = mock(VideoSearchPort.class);
        when(videoSearchPort.hasTitle()).thenReturn(true);
        when(videoSearchPort.title()).thenReturn("title");
        when(videoSearchPort.page()).thenReturn(0);
        when(videoSearchPort.size()).thenReturn(10);
        Page<VideoDocument> page = Page.empty();
        when(mongoTemplate.count(any(Query.class), any(Class.class))).thenReturn(0L);
        when(mongoTemplate.find(any(Query.class), any())).thenReturn(List.of());
        Page<VideoDocument> returnedPage = customVideoRepositoryImpl.search(videoSearchPort);
        assertEquals(page.getContent(), returnedPage.getContent());
    }

    @Test
    void shouldReturnPageOfVideosWhenSearchIsCalledWithCategoryName() {
        VideoSearchPort videoSearchPort = mock(VideoSearchPort.class);
        when(videoSearchPort.hasCategoryName()).thenReturn(true);
        when(videoSearchPort.categoryName()).thenReturn("action");
        when(videoSearchPort.page()).thenReturn(0);
        when(videoSearchPort.size()).thenReturn(10);
        Page<VideoDocument> page = Page.empty();
        when(mongoTemplate.count(any(Query.class), any(Class.class))).thenReturn(0L);
        when(mongoTemplate.find(any(Query.class), any())).thenReturn(List.of());
        Page<VideoDocument> returnedPage = customVideoRepositoryImpl.search(videoSearchPort);
        assertEquals(page.getContent(), returnedPage.getContent());
    }

    @Test
    void shouldReturnPageOfVideosWhenSearchIsCalledWithPublicationDate() {
        VideoSearchPort videoSearchPort = mock(VideoSearchPort.class);
        when(videoSearchPort.hasPublicationDate()).thenReturn(true);
        when(videoSearchPort.publicationDate()).thenReturn(LocalDate.now());
        when(videoSearchPort.publicationDateOperation()).thenReturn(Operation.GTE);
        when(videoSearchPort.page()).thenReturn(0);
        when(videoSearchPort.size()).thenReturn(10);
        Page<VideoDocument> page = Page.empty();
        when(mongoTemplate.count(any(Query.class), any(Class.class))).thenReturn(0L);
        when(mongoTemplate.find(any(Query.class), any())).thenReturn(List.of());
        Page<VideoDocument> returnedPage = customVideoRepositoryImpl.search(videoSearchPort);
        assertEquals(page.getContent(), returnedPage.getContent());
    }

    @Test
    void shouldReturnPageOfVideosWhenSearchIsCalledWithSort() {
        VideoSearchPort videoSearchPort = mock(VideoSearchPort.class);
        when(videoSearchPort.hasSort()).thenReturn(true);
        when(videoSearchPort.sort()).thenReturn(Direction.ASC);
        when(videoSearchPort.page()).thenReturn(0);
        when(videoSearchPort.size()).thenReturn(10);
        Page<VideoDocument> page = Page.empty();
        when(mongoTemplate.count(any(Query.class), any(Class.class))).thenReturn(0L);
        when(mongoTemplate.find(any(Query.class), any())).thenReturn(List.of());
        Page<VideoDocument> returnedPage = customVideoRepositoryImpl.search(videoSearchPort);
        assertEquals(page.getContent(), returnedPage.getContent());
    }

    @Test
    void shouldReturnPageOfVideosWhenSearchIsCalledWithPublicationDateAndOperationLTE() {
        VideoSearchPort videoSearchPort = mock(VideoSearchPort.class);
        when(videoSearchPort.hasPublicationDate()).thenReturn(true);
        when(videoSearchPort.publicationDate()).thenReturn(LocalDate.now());
        when(videoSearchPort.publicationDateOperation()).thenReturn(Operation.LTE);
        when(videoSearchPort.page()).thenReturn(0);
        when(videoSearchPort.size()).thenReturn(10);
        Page<VideoDocument> page = Page.empty();
        when(mongoTemplate.count(any(Query.class), any(Class.class))).thenReturn(0L);
        when(mongoTemplate.find(any(Query.class), any())).thenReturn(List.of());
        Page<VideoDocument> returnedPage = customVideoRepositoryImpl.search(videoSearchPort);
        assertEquals(page.getContent(), returnedPage.getContent());
    }
}