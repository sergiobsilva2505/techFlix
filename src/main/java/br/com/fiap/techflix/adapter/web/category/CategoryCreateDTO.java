package br.com.fiap.techflix.adapter.web.category;

import br.com.fiap.techflix.application.ports.CategoryCreatePort;
import jakarta.validation.constraints.NotBlank;

public record CategoryCreateDTO(@NotBlank String name) implements CategoryCreatePort {
}
