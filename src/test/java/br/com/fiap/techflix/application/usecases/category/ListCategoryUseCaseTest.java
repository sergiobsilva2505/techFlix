package br.com.fiap.techflix.application.usecases.category;

import br.com.fiap.techflix.application.gateways.category.CategoryGateway;
import br.com.fiap.techflix.domain.entities.category.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class ListCategoryUseCaseTest {

    private CategoryGateway categoryGateway;
    private ListCategoryUseCase listCategoryUseCase;

    @BeforeEach
    void setUp() {
        categoryGateway = mock(CategoryGateway.class);
        listCategoryUseCase = new ListCategoryUseCase(categoryGateway);
    }

    @Test
    @DisplayName("Retorna categoria quando ID existe")
    void shouldReturnCategoryWhenIdExists() {
        Category category = mock(Category.class);
        String id = "existingId";
        when(categoryGateway.findById(id)).thenReturn(Optional.of(category));

        Category result = listCategoryUseCase.listCategory(id);

        assertThat(result).isEqualTo(category);
        verify(categoryGateway, times(1)).findById(id);
    }

    @Test
    @DisplayName("Lança exceção quando ID não existe")
    void shouldThrowExceptionWhenIdDoesNotExist() {
        String id = "nonExistingId";
        when(categoryGateway.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> listCategoryUseCase.listCategory(id))
                .isInstanceOf(IllegalArgumentException.class);
        verify(categoryGateway, times(1)).findById(id);
    }
}