package br.com.fiap.techFlix.domain.entities.bookmarvideo;

import br.com.fiap.techFlix.domain.entities.user.User;
import br.com.fiap.techFlix.domain.entities.video.Video;
import br.com.fiap.techFlix.domain.validation.Validator;

public class BookmarkVideo {

    private String id;
    private User user;
    private Video video;

    public BookmarkVideo(String id, User user, Video video) {
        Validator.objectNotNull(id, "bookmark video id");
        Validator.objectNotNull(user, "bookmark video user");
        Validator.objectNotNull(video, "bookmark user");
        this.id = id;
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
