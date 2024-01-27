package br.com.fiap.techFlix.application.useCases.category;

import br.com.fiap.techFlix.application.gateways.category.CategoryGateway;
import br.com.fiap.techFlix.application.ports.CategoryCreatePort;
import br.com.fiap.techFlix.domain.entities.category.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCategoryUseCaseTest {

    private CategoryGateway categoryGateway;
    private CreateCategoryUseCase createCategoryUseCase;

    @BeforeEach
    void setUp() {
        categoryGateway = mock(CategoryGateway.class);
        createCategoryUseCase = new CreateCategoryUseCase(categoryGateway);
    }

    @Test
    void shouldCreateCategory() {
        CategoryCreatePort categoryCreatePort = mock(CategoryCreatePort.class);
        Category category = mock(Category.class);
        when(category.getName()).thenReturn("Category1");
        when(categoryGateway.save(any(CategoryCreatePort.class))).thenReturn(category);
        when(categoryGateway.existsByName("Category1")).thenReturn(false);

        Category result = createCategoryUseCase.createCategory(categoryCreatePort);

        assertNotNull(result);
        assertEquals("Category1", result.getName());
    }

    @Test
    void shouldThrowExceptionWhenCategoryAlreadyExists() {
        CategoryCreatePort categoryCreatePort = mock(CategoryCreatePort.class);
        when(categoryCreatePort.name()).thenReturn("Category1");
        when(categoryGateway.existsByName("Category1")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> createCategoryUseCase.createCategory(categoryCreatePort));
    }

}