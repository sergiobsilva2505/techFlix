package br.com.fiap.techFlix.config;

import br.com.fiap.techFlix.application.gateways.CategoryGateway;
import br.com.fiap.techFlix.application.gateways.VideoGateway;
import br.com.fiap.techFlix.application.useCases.PublishVideoUseCase;
import br.com.fiap.techFlix.infrastructure.gateways.*;
import br.com.fiap.techFlix.infrastructure.persistence.CategoryRepository;
import br.com.fiap.techFlix.infrastructure.persistence.VideoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    CategoryGateway categoryGateway(CategoryRepository categoryRepository) {
        return new CategoryRepositoryGateway(categoryRepository);
    }

    @Bean
    VideoGateway videoGateway(VideoRepository videoRepository) {
        return new VideoRepositoryGateway(videoRepository);
    }

    @Bean
    PublishVideoUseCase publishVideoUseCase(CategoryGateway categoryGateway, VideoGateway videoGateway) {
        return new PublishVideoUseCase(categoryGateway, videoGateway);
    }
}
