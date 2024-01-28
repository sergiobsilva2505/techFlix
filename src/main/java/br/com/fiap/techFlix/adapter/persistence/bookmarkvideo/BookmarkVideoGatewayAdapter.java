package br.com.fiap.techFlix.adapter.persistence.bookmarkvideo;

import br.com.fiap.techFlix.adapter.web.PageDTO;
import br.com.fiap.techFlix.adapter.web.bookmarkvideo.BookmarkVideoMapper;
import br.com.fiap.techFlix.application.gateways.bookmark.BookmarkVideoGateway;
import br.com.fiap.techFlix.application.ports.PagePort;
import br.com.fiap.techFlix.domain.entities.bookmarvideo.BookmarkVideo;
import br.com.fiap.techFlix.domain.entities.user.User;
import br.com.fiap.techFlix.domain.entities.video.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public class BookmarkVideoGatewayAdapter implements BookmarkVideoGateway {

    private final BookmarkVideoRepository bookmarkVideoRepository;

    public BookmarkVideoGatewayAdapter(BookmarkVideoRepository bookmarkVideoRepository) {
        this.bookmarkVideoRepository = bookmarkVideoRepository;
    }

    @Override
    public BookmarkVideo create(User user, Video video) {
        BookmarkVideoDocument saved = bookmarkVideoRepository.save(BookmarkVideoMapper.toPersistence(user, video));
        return BookmarkVideoMapper.toDomain(saved);
    }

    @Override
    public Optional<BookmarkVideo> findById(String bookmarkId) {
        return bookmarkVideoRepository.findById(bookmarkId).map(BookmarkVideoMapper::toDomain);
    }

    @Override
    public PagePort<BookmarkVideo> allBookmarkVideo(int page, int size) {
        Page<BookmarkVideo> bookmarkVideos = bookmarkVideoRepository.findAll(PageRequest.of(page, size)).map(BookmarkVideoMapper::toDomain);

        return new PageDTO<>(bookmarkVideos);
    }

    @Override
    public Optional<BookmarkVideo> findByVideoIdAndUserId(String videoId, String userId) {
        return bookmarkVideoRepository.findByVideoIdAndUserId(videoId, userId).map(BookmarkVideoMapper::toDomain);
    }

    @Override
    public boolean existsByVideoIdAndUserId(String videoId, String userId) {
        return bookmarkVideoRepository.existsByUser_IdAndVideo_Id(userId, videoId);
    }

    @Override
    public void deleteById(String id) {
        bookmarkVideoRepository.deleteById(id);
    }
}
