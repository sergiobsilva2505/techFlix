package br.com.fiap.techFlix.adapter.web.user;

import br.com.fiap.techFlix.application.ports.UserCreatePort;
import jakarta.validation.constraints.*;

public record UserCreateDTO(@NotBlank String name, @NotNull @Email String email, @NotBlank String password) implements UserCreatePort {
}
