package br.com.fiap.techFlix.application.useCases.category;

import br.com.fiap.techFlix.application.gateways.category.CategoryGateway;
import br.com.fiap.techFlix.domain.entities.category.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class ListCategoryUseCaseTest {

    @Mock
    private CategoryGateway categoryGateway;

    AutoCloseable openMocks;

    private ListCategoryUseCase listCategoryUseCase;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        listCategoryUseCase = new ListCategoryUseCase(categoryGateway);
    }

    @Test
    void shouldReturnCategoryWhenIdExists() {
        Category category = mock(Category.class);
        String id = "existingId";
        when(categoryGateway.findById(id)).thenReturn(Optional.of(category));

        Category result = listCategoryUseCase.listCategory(id);

        assertThat(result).isEqualTo(category);
        verify(categoryGateway, times(1)).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenIdDoesNotExist() {
        String id = "nonExistingId";
        when(categoryGateway.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> listCategoryUseCase.listCategory(id))
                .isInstanceOf(IllegalArgumentException.class);
        verify(categoryGateway, times(1)).findById(id);
    }
}