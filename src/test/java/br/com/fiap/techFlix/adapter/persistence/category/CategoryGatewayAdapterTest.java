package br.com.fiap.techFlix.adapter.persistence.category;

import br.com.fiap.techFlix.application.ports.CategoryCreatePort;
import br.com.fiap.techFlix.domain.entities.category.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryGatewayAdapterTest {

    private CategoryRepository categoryRepository;
    private CategoryGatewayAdapter categoryGatewayAdapter;

    @BeforeEach
    public void setup() {
        categoryRepository = mock(CategoryRepository.class);
        categoryGatewayAdapter = new CategoryGatewayAdapter(categoryRepository);
    }

    @Test
    public void saveCategorySuccessfully() {
        CategoryCreatePort categoryCreatePort = mock(CategoryCreatePort.class);
        CategoryDocument document = new CategoryDocument("123", "Action");
        when(categoryRepository.save(any())).thenReturn(document);
        Category result = categoryGatewayAdapter.save(categoryCreatePort);
        assertNotNull(result);
        verify(categoryRepository, times(1)).save(any());
    }

    @Test
    public void findByIdReturnsCategoryWhenExists() {
        String categoryId = "123";
        CategoryDocument document = new CategoryDocument("123", "Action");
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(document));
        Optional<Category> result = categoryGatewayAdapter.findById(categoryId);
        assertTrue(result.isPresent());
        verify(categoryRepository, times(1)).findById(categoryId);
    }

    @Test
    public void findByIdReturnsEmptyWhenDoesNotExist() {
        String categoryId = "123";
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
        Optional<Category> result = categoryGatewayAdapter.findById(categoryId);
        assertTrue(result.isEmpty());
        verify(categoryRepository, times(1)).findById(categoryId);
    }

    @Test
    public void findAllByNameInReturnsCategories() {
        List<String> names = Arrays.asList("Action", "Comedy");
        List<CategoryDocument> documents = Arrays.asList(new CategoryDocument("123", "Action"), new CategoryDocument("456", "Comedy"));
        when(categoryRepository.findByNameIn(names)).thenReturn(documents);
        List<Category> result = categoryGatewayAdapter.findAllByNameIn(names);
        assertEquals(documents.size(), result.size());
        verify(categoryRepository, times(1)).findByNameIn(names);
    }

    @Test
    public void existsByNameReturnsTrueWhenExists() {
        String name = "Action";
        when(categoryRepository.existsByName(name)).thenReturn(true);
        boolean result = categoryGatewayAdapter.existsByName(name);
        assertTrue(result);
        verify(categoryRepository, times(1)).existsByName(name);
    }

    @Test
    public void existsByNameReturnsFalseWhenDoesNotExist() {
        String name = "Action";
        when(categoryRepository.existsByName(name)).thenReturn(false);
        boolean result = categoryGatewayAdapter.existsByName(name);
        assertFalse(result);
        verify(categoryRepository, times(1)).existsByName(name);
    }
}