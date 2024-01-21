package br.com.fiap.techFlix.domain.entities;

import br.com.fiap.techFlix.domain.validation.Validator;

public class BookmarkVideo {

    private String id;
    private User user;
    private Video video;

    public BookmarkVideo(String id, User user, Video video) {
        this(user, video);
        this.id = id;
    }

    public BookmarkVideo(User user, Video video) {
        Validator.objectNotNull(user, "bookmark video user");
        Validator.objectNotNull(video, "bookmark user");
        this.user = user;
        this.video = video;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Video getVideo() {
        return video;
    }
}
