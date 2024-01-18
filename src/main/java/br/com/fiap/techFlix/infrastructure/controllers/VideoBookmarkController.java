package br.com.fiap.techFlix.infrastructure.controllers;

import br.com.fiap.techFlix.application.useCases.CreateBookmarkVideo;
import br.com.fiap.techFlix.application.useCases.ListBookmarkVideoUseCase;
import br.com.fiap.techFlix.domain.entities.BookmarkVideo;
import br.com.fiap.techFlix.infrastructure.gateways.BookmarkVideoMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class VideoBookmarkController {

    private final ListBookmarkVideoUseCase listBookmarkVideoUseCase;
    private final CreateBookmarkVideo createBookmarkVideo;

    public VideoBookmarkController(ListBookmarkVideoUseCase listBookmarkVideoUseCase, CreateBookmarkVideo createBookmarkVideo) {
        this.listBookmarkVideoUseCase = listBookmarkVideoUseCase;
        this.createBookmarkVideo = createBookmarkVideo;
    }

    @PostMapping("/bookmarks")
    public ResponseEntity<String> createBookmarkVideo(@Valid @RequestBody BookmarkVideoCreateDTO bookmarkVideoCreateDTO) {
        BookmarkVideo bookmarkVideo = createBookmarkVideo.createBookmarkVideo(bookmarkVideoCreateDTO);

        URI uri = URI.create("/bookmarks/" + bookmarkVideo.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/bookmarks/{id}")
    public BookmarkVideoShowDTO getBookmarkVideoById(@PathVariable String id) {
        return BookmarkVideoMapper.toView(listBookmarkVideoUseCase.listBookmarkVideo(id));
    }
}
