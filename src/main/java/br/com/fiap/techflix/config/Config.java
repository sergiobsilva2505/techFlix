package br.com.fiap.techflix.config;

import br.com.fiap.techflix.application.gateways.bookmark.BookmarkVideoGateway;
import br.com.fiap.techflix.application.gateways.category.CategoryGateway;
import br.com.fiap.techflix.application.gateways.file.FileGateway;
import br.com.fiap.techflix.application.gateways.user.UserGateway;
import br.com.fiap.techflix.application.gateways.video.VideoGateway;
import br.com.fiap.techflix.application.usecases.bookmark.CreateBookmarkVideoUseCase;
import br.com.fiap.techflix.application.usecases.bookmark.DeleteBookmarkVideoUseCase;
import br.com.fiap.techflix.application.usecases.bookmark.ListBookmarkVideoUseCase;
import br.com.fiap.techflix.application.usecases.category.CreateCategoryUseCase;
import br.com.fiap.techflix.application.usecases.category.ListCategoryUseCase;
import br.com.fiap.techflix.application.usecases.user.CreateUserUseCase;
import br.com.fiap.techflix.application.usecases.user.ListUserUseCase;
import br.com.fiap.techflix.application.usecases.video.*;
import br.com.fiap.techflix.adapter.persistence.bookmarkvideo.BookmarkVideoGatewayAdapter;
import br.com.fiap.techflix.adapter.persistence.category.CategoryGatewayAdapter;
import br.com.fiap.techflix.adapter.persistence.file.FileGatewayAdapter;
import br.com.fiap.techflix.adapter.persistence.user.UserGatewayAdapter;
import br.com.fiap.techflix.adapter.persistence.video.VideoGatewayAdapter;
import br.com.fiap.techflix.adapter.persistence.bookmarkvideo.BookmarkVideoRepository;
import br.com.fiap.techflix.adapter.persistence.category.CategoryRepository;
import br.com.fiap.techflix.adapter.persistence.file.FileRepository;
import br.com.fiap.techflix.adapter.persistence.user.UserRepository;
import br.com.fiap.techflix.adapter.persistence.video.VideoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    CategoryGateway categoryGateway(CategoryRepository categoryRepository) {
        return new CategoryGatewayAdapter(categoryRepository);
    }

    @Bean
    VideoGateway videoGateway(BookmarkVideoRepository bookmarkVideoRepository, VideoRepository videoRepository) {
        return new VideoGatewayAdapter(bookmarkVideoRepository, videoRepository);
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
    DeleteBookmarkVideoUseCase deleteBookmarkVideoUseCase(BookmarkVideoGateway bookmarkVideoGateway, VideoGateway videoGateway) {
        return new DeleteBookmarkVideoUseCase(bookmarkVideoGateway, videoGateway);
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
    UpdateVideoUseCase updateVideoUseCase(CategoryGateway categoryGateway, VideoGateway videoGateway) {
        return new UpdateVideoUseCase(categoryGateway, videoGateway);
    }

    @Bean
    DeleteVideoUseCase deleteVideoUseCase(VideoGateway videoGateway) {
        return new DeleteVideoUseCase(videoGateway);
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
    VideoStatisticsUseCase videoStatisticsUseCase(VideoGateway videoGateway) {
        return new VideoStatisticsUseCase(videoGateway);
    }

    @Bean
    WatchVideoUseCase watchVideoUseCase(VideoGateway videoGateway) {
        return new WatchVideoUseCase(videoGateway);
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
