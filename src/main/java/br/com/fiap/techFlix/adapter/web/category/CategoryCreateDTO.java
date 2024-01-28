package br.com.fiap.techFlix.adapter.web.category;

import br.com.fiap.techFlix.application.ports.CategoryCreatePort;
import jakarta.validation.constraints.NotBlank;

public record CategoryCreateDTO(@NotBlank String name) implements CategoryCreatePort {
}
