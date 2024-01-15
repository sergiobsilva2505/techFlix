package br.com.fiap.techFlix.application.useCases;

import br.com.fiap.techFlix.application.gateways.VideoGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ListVideoUseCaseTest {

    @Mock
    private VideoGateway videoGateway;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void listVideos() {
    }

    @Test
    void listVideo() {
        fail("teste não implementado");
    }
}