package br.com.fiap.techFlix.adapter.web.user;

import br.com.fiap.techFlix.domain.entities.user.User;

public record UserViewDTO(String id, String name, String email) {

    public UserViewDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
