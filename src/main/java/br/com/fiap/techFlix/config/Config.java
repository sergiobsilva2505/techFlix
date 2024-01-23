package br.com.fiap.techFlix.config;

import br.com.fiap.techFlix.application.gateways.bookmark.BookmarkVideoGateway;
import br.com.fiap.techFlix.application.gateways.category.CategoryGateway;
import br.com.fiap.techFlix.application.gateways.file.FileGateway;
import br.com.fiap.techFlix.application.gateways.user.UserGateway;
import br.com.fiap.techFlix.application.gateways.video.VideoGateway;
import br.com.fiap.techFlix.application.useCases.bookmark.CreateBookmarkVideoUseCase;
import br.com.fiap.techFlix.application.useCases.bookmark.DeleteBookmarkVideoUseCase;
import br.com.fiap.techFlix.application.useCases.bookmark.ListBookmarkVideoUseCase;
import br.com.fiap.techFlix.application.useCases.category.CreateCategoryUseCase;
import br.com.fiap.techFlix.application.useCases.category.ListCategoryUseCase;
import br.com.fiap.techFlix.application.useCases.user.CreateUserUseCase;
import br.com.fiap.techFlix.application.useCases.user.ListUserUseCase;
import br.com.fiap.techFlix.application.useCases.video.*;
import br.com.fiap.techFlix.adapter.persistence.bookmarkvideo.BookmarkVideoGatewayAdapter;
import br.com.fiap.techFlix.adapter.persistence.category.CategoryGatewayAdapter;
import br.com.fiap.techFlix.adapter.persistence.file.FileGatewayAdapter;
import br.com.fiap.techFlix.adapter.persistence.user.UserGatewayAdapter;
import br.com.fiap.techFlix.adapter.persistence.video.VideoGatewayAdapter;
import br.com.fiap.techFlix.adapter.persistence.bookmarkvideo.BookmarkVideoRepository;
import br.com.fiap.techFlix.adapter.persistence.category.CategoryRepository;
import br.com.fiap.techFlix.adapter.persistence.file.FileRepository;
import br.com.fiap.techFlix.adapter.persistence.user.UserRepository;
import br.com.fiap.techFlix.adapter.persistence.video.VideoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class Config {

    @Bean
    CategoryGateway categoryGateway(CategoryRepository categoryRepository) {
        return new CategoryGatewayAdapter(categoryRepository);
    }

    @Bean
    VideoGateway videoGateway(MongoTemplate mongoTemplate, VideoRepository videoRepository) {
        return new VideoGatewayAdapter(mongoTemplate, videoRepository);
    }

    @Bean
    FileGateway fileGateway(FileRepository fileRepository) {
        return new FileGatewayAdapter(fileRepository);
    }

    @Bean
    UserGateway userGateway(UserRepository userRepository) {
        return new UserGatewayAdapter(userRepository);
    }

    @Bean
    CreateUserUseCase createUserUseCase(UserGateway userGateway) {
        return new CreateUserUseCase(userGateway);
    }

    @Bean
    ListUserUseCase listUsersUseCase(UserGateway userGateway) {
        return new ListUserUseCase(userGateway);
    }

    @Bean
    DeleteBookmarkVideoUseCase deleteBookmarkVideoUseCase(BookmarkVideoGateway bookmarkVideoGateway) {
        return new DeleteBookmarkVideoUseCase(bookmarkVideoGateway);
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
    SearchVideoUseCase searchVideoUseCase(VideoGateway videoGateway) {
        return new SearchVideoUseCase(videoGateway);
    }

    @Bean
    VideoRecommendationsUseCase videoRecommendationsUseCase(VideoGateway videoGateway) {
        return new VideoRecommendationsUseCase(videoGateway);
    }

    @Bean
    BookmarkVideoGateway bookmarkVideoGateway(BookmarkVideoRepository bookmarkVideoRepository) {
        return new BookmarkVideoGatewayAdapter(bookmarkVideoRepository);
    }

    @Bean
    CreateBookmarkVideoUseCase createBookmarkVideo(BookmarkVideoGateway bookmarkVideoGateway, UserGateway userGateway, VideoGateway videoGateway) {
        return new CreateBookmarkVideoUseCase(bookmarkVideoGateway, userGateway, videoGateway);
    }

    @Bean
    ListBookmarkVideoUseCase listBookmarkVideoUserCase(BookmarkVideoGateway bookmarkVideoGateway) {
        return new ListBookmarkVideoUseCase(bookmarkVideoGateway);
    }
}
