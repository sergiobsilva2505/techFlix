package br.com.fiap.techFlix.infrastructure.gateways;

import br.com.fiap.techFlix.domain.entities.Category;
import br.com.fiap.techFlix.domain.entities.User;
import br.com.fiap.techFlix.infrastructure.controllers.CategoryShowDTO;
import br.com.fiap.techFlix.infrastructure.controllers.UserCreateDTO;
import br.com.fiap.techFlix.infrastructure.persistence.CategoryDocument;
import br.com.fiap.techFlix.infrastructure.persistence.UserDocument;

public class UserMapper {

    public static User toDomain(UserDocument userDocument) {
        return new User(userDocument.getId(), userDocument.getName(), userDocument.getEmail(), userDocument.getPassword(), userDocument.getToken());
    }

    public static User toDomain(UserCreateDTO userCreateDTO) {
        return new User(userCreateDTO.name(), userCreateDTO.email(), userCreateDTO.password());
    }

    public static UserDocument toPersistence(User user) {
        return new UserDocument(user.getName(), user.getEmail(), user.getPassword(), user.getToken());
    }

//    public static CategoryShowDTO toView(Category category) {
//        return new CategoryShowDTO(category.getName());
//    }
}
