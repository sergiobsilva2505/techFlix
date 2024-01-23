package br.com.fiap.techFlix.adapter.web.user;

import br.com.fiap.techFlix.domain.entities.user.User;
import br.com.fiap.techFlix.adapter.persistence.user.UserDocument;

public class UserMapper {

    public static User toDomain(UserDocument userDocument) {
        return new User(userDocument.getId(), userDocument.getName(), userDocument.getEmail(), userDocument.getPassword(), userDocument.getToken());
    }

    public static User toDomain(UserCreateDTO userCreateDTO) {
        return new User(userCreateDTO.name(), userCreateDTO.email(), userCreateDTO.password());
    }

    public static UserDocument toPersistence(User user) {
        return new UserDocument(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getToken());
    }

    public static UserViewDTO toView(User user) {
        return new UserViewDTO(user.getId(), user.getName(), user.getEmail());
    }
}
