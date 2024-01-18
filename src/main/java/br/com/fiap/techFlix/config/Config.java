package br.com.fiap.techFlix.config;

import br.com.fiap.techFlix.application.gateways.*;
import br.com.fiap.techFlix.application.useCases.*;
import br.com.fiap.techFlix.infrastructure.gateways.*;
import br.com.fiap.techFlix.infrastructure.persistence.*;
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
    FileGateway fileGateway(FileRepository fileRepository) {
        return new FileUploadGateway(fileRepository);
    }

    @Bean
    UserGateway userGateway(UserRepository userRepository) {
        return new UserRepositoryGateway(userRepository);
    }

    @Bean
    CreateUserUseCase createUserUseCase(UserGateway userGateway) {
        return new CreateUserUseCase(userGateway);
    }

    @Bean
    CreateCategoryUseCase createCategoryUseCase(CategoryGateway categoryGateway) {
        return new CreateCategoryUseCase(categoryGateway);
    }

    @Bean
    ListCategoryUseCase listCategoryUseCase(CategoryGateway categoryGateway) {
        return new ListCategoryUseCase(categoryGateway);
    }

    @Bean
    PublishVideoUseCase publishVideoUseCase(CategoryGateway categoryGateway, VideoGateway videoGateway) {
        return new PublishVideoUseCase(categoryGateway, videoGateway);
    }

    @Bean
    ListVideoUseCase listVideoUseCase(VideoGateway videoGateway) {
        return new ListVideoUseCase(videoGateway);
    }

    @Bean
    BookmarkVideoGateway bookmarkVideoGateway(BookmarkVideoRepository bookmarkVideoRepository) { return new BookmarkVideoRepositoryGateway(bookmarkVideoRepository);}

    @Bean
    CreateBookmarkVideo createBookmarkVideo(BookmarkVideoGateway bookmarkVideoGateway) { return new CreateBookmarkVideo(bookmarkVideoGateway);}

    @Bean
    ListBookmarkVideoUseCase listBookmarkVideoUserCase(BookmarkVideoGateway bookmarkVideoGateway) { return new ListBookmarkVideoUseCase(bookmarkVideoGateway); }
}
