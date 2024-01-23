package br.com.fiap.techFlix.adapter.web.bookmarkvideo;

import br.com.fiap.techFlix.application.ports.PagePort;
import br.com.fiap.techFlix.application.useCases.bookmark.CreateBookmarkVideoUseCase;
import br.com.fiap.techFlix.application.useCases.bookmark.DeleteBookmarkVideoUseCase;
import br.com.fiap.techFlix.application.useCases.bookmark.ListBookmarkVideoUseCase;
import br.com.fiap.techFlix.domain.entities.bookmarvideo.BookmarkVideo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
        BookmarkVideo bookmarkVideo = createBookmarkVideoUseCase.createBookmarkVideo(userId, videoId);

        URI uri = URI.create("/bookmarks/" + bookmarkVideo.getId());
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/bookmarks/video/{videoId}/user/{userId}")
    public ResponseEntity<Void> deleteBookmarkVideo(@PathVariable String videoId, @PathVariable String userId) {
        deleteBookmarkVideoUseCase.deleteBookmarkVideo(videoId, userId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/bookmarks/{id}")
    public ResponseEntity<BookmarkVideoShowDTO> getBookmarkVideoById(@PathVariable String id) {
        BookmarkVideoShowDTO videoShowDTO = BookmarkVideoMapper.toView(listBookmarkVideoUseCase.listBookmarkVideo(id));

        return ResponseEntity.ok(videoShowDTO);
    }

    @GetMapping("/bookmarks")
    public ResponseEntity<PagePort<BookmarkVideoShowDTO>> getAllBookmarkVideo(@RequestParam(defaultValue = "0") int page,
                                                                              @RequestParam(defaultValue = "10") int size) {
        PagePort<BookmarkVideoShowDTO> dtoPagePort = listBookmarkVideoUseCase.listAllBookmarkVideo(page, size);

        return ResponseEntity.ok(dtoPagePort);
    }
}
