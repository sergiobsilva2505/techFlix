package br.com.fiap.techFlix.adapter.web.category;

import br.com.fiap.techFlix.application.ports.CategoryCreatePort;

public record CategoryCreateDTO(String name) implements CategoryCreatePort {
}
