package br.com.fiap.techFlix.adapters.category;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class CategoryMapper {

    @Id
    private String id;
    private String name;
}
