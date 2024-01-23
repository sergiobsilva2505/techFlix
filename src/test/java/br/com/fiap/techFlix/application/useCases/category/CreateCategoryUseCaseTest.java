package br.com.fiap.techFlix.application.useCases.category;

import br.com.fiap.techFlix.application.gateways.category.CategoryGateway;
import br.com.fiap.techFlix.domain.entities.category.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CreateCategoryUseCaseTest {

    @Mock
    private CategoryGateway categoryGateway;

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
    void createCategory__should_create_a_category() {
        Category categoryToSave = new Category("Categoria FIAP");

        when(categoryGateway.save(any(Category.class))).thenReturn(categoryToSave);

        Category saved = categoryGateway.save(categoryToSave);
        verify(categoryGateway, times(1)).save(categoryToSave);
        assertThat(saved).isEqualTo(categoryToSave);
    }
}