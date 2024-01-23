package br.com.fiap.techFlix.application.useCases.category;

import br.com.fiap.techFlix.application.gateways.category.CategoryGateway;
import br.com.fiap.techFlix.domain.entities.category.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ListCategoryUseCaseTest {

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
    void listCategory__should_return_a_category_by_name() {
        String categoryName = "Categoria Alura";
        Category categoryAlura = new Category("Categoria Alura");

        when(categoryGateway.findByName(categoryName)).thenReturn(Optional.of(categoryAlura));
        Optional<Category> optionalCategory = categoryGateway.findByName(categoryName);

        verify(categoryGateway, times(1)).findByName(categoryName);
        assertThat(optionalCategory).isPresent().contains(optionalCategory.get());
        optionalCategory.ifPresent(category -> {
            assertThat(category).isEqualTo(categoryAlura);
        });

        categoryName = "";
        when(categoryGateway.findByName(categoryName)).thenReturn(Optional.empty());

        optionalCategory = categoryGateway.findByName(categoryName);

        verify(categoryGateway, times(1)).findByName(categoryName);
        assertThat(optionalCategory).isEmpty();
    }
}