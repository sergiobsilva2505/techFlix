package br.com.fiap.techflix.adapter.web.user;

import br.com.fiap.techflix.adapter.persistence.user.UserDocument;
import br.com.fiap.techflix.application.ports.UserCreatePort;
import br.com.fiap.techflix.domain.entities.user.User;

public class UserMapper {

    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static User toDomain(UserDocument userDocument) {
        return new User(userDocument.getId(), userDocument.getName(), userDocument.getEmail(), userDocument.getPassword(), userDocument.getToken());
    }

    public static UserDocument toPersistence(User user) {
        return new UserDocument(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getToken());
    }

    public static UserDocument toPersistence(UserCreatePort userCreatePort, String token) {
        return new UserDocument(null, userCreatePort.name(), userCreatePort.email(), userCreatePort.password(), token);
    }

    public static UserViewDTO toView(User user) {
        return new UserViewDTO(user.getName(), user.getEmail());
    }
}
