package br.com.fiap.techFlix.config;

import br.com.fiap.techFlix.application.gateways.CategoryGateway;
import br.com.fiap.techFlix.application.gateways.VideoGateway;
import br.com.fiap.techFlix.application.useCases.PublishVideoUseCase;
import br.com.fiap.techFlix.infrastructure.gateways.CategoryMapper;
import br.com.fiap.techFlix.infrastructure.gateways.CategoryRepositoryGateway;
import br.com.fiap.techFlix.infrastructure.persistence.CategoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    CategoryGateway categoryGateway(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        return new CategoryRepositoryGateway(categoryMapper, categoryRepository);
    }

    @Bean
    PublishVideoUseCase publishVideoUseCase(CategoryGateway categoryGateway, VideoGateway videoGateway) {
        return new PublishVideoUseCase(categoryGateway, videoGateway);
    }
}
