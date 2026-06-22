package br.com.fiap.techflix.adapter.web.user;

import br.com.fiap.techflix.application.ports.UserCreatePort;
import jakarta.validation.constraints.*;

public record UserCreateDTO(@NotBlank String name, @NotNull @Email String email, @NotBlank String password) implements UserCreatePort {
}
