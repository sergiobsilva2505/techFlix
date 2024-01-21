package br.com.fiap.techFlix.infrastructure.controllers;

import br.com.fiap.techFlix.application.useCases.CreateBookmarkVideoUseCase;
import br.com.fiap.techFlix.application.useCases.DeleteBookmarkVideoUseCase;
import br.com.fiap.techFlix.application.useCases.ListBookmarkVideoUseCase;
import br.com.fiap.techFlix.domain.entities.BookmarkVideo;
import br.com.fiap.techFlix.infrastructure.gateways.BookmarkVideoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class VideoBookmarkController {

    private final ListBookmarkVideoUseCase listBookmarkVideoUseCase;
    private final CreateBookmarkVideoUseCase createBookmarkVideoUseCase;
    private final DeleteBookmarkVideoUseCase deleteBookmarkVideoUseCase;

    public VideoBookmarkController(ListBookmarkVideoUseCase listBookmarkVideoUseCase, CreateBookmarkVideoUseCase createBookmarkVideoUseCase, DeleteBookmarkVideoUseCase deleteBookmarkVideoUseCase) {
        this.listBookmarkVideoUseCase = listBookmarkVideoUseCase;
        this.createBookmarkVideoUseCase = createBookmarkVideoUseCase;
        this.deleteBookmarkVideoUseCase = deleteBookmarkVideoUseCase;
    }

    @PostMapping("/bookmarks/video/{videoId}/user/{userId}")
    public ResponseEntity<String> createBookmarkVideo(@PathVariable String videoId, @PathVariable String userId) {
        BookmarkVideo bookmarkVideo = createBookmarkVideoUseCase.createBookmarkVideo(videoId, userId);

        URI uri = URI.create("/bookmarks/" + bookmarkVideo.getId());
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/bookmarks/video/{videoId}/user/{userId}")
    public ResponseEntity<Void> deleteBookmarkVideo(@PathVariable String videoId, @PathVariable String userId) {
        deleteBookmarkVideoUseCase.deleteBookmarkVideo(videoId, userId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/bookmarks/{id}")
    public BookmarkVideoShowDTO getBookmarkVideoById(@PathVariable String id) {
        return BookmarkVideoMapper.toView(listBookmarkVideoUseCase.listBookmarkVideo(id));
    }

    @GetMapping("/bookmarks")
    public List<BookmarkVideoShowDTO> getAllBookmarkVideo() {
        List<BookmarkVideo> bookmarkVideos = listBookmarkVideoUseCase.listAllBookmarkVideo();
        return bookmarkVideos.stream().map(BookmarkVideoMapper::toView).toList();
    }
}
