package br.com.fiap.techFlix.adapter.web.category;

import br.com.fiap.techFlix.application.useCases.category.CreateCategoryUseCase;
import br.com.fiap.techFlix.application.useCases.category.ListCategoryUseCase;
import br.com.fiap.techFlix.domain.entities.category.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    @Mock
    CreateCategoryUseCase createCategoryUseCase;

    @Mock
    ListCategoryUseCase listCategoryUseCase;

    @InjectMocks
    CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateCategory() {
        Category category = mock(Category.class);
        when(category.getId()).thenReturn("id");
        when(createCategoryUseCase.createCategory(any(CategoryCreateDTO.class))).thenReturn(category);

        ResponseEntity<String> response = categoryController.createCategory(mock(CategoryCreateDTO.class));

        assertEquals(201, response.getStatusCodeValue());
        verify(createCategoryUseCase).createCategory(any(CategoryCreateDTO.class));
    }

    @Test
    void shouldGetCategoryById() {
        Category category = mock(Category.class);
        when(listCategoryUseCase.listCategory(anyString())).thenReturn(category);

        ResponseEntity<CategoryShowDTO> response = categoryController.getCategoryById("id");

        assertEquals(200, response.getStatusCodeValue());
        verify(listCategoryUseCase).listCategory(anyString());
    }
}