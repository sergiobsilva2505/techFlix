package br.com.fiap.techFlix.infrastructure.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookmarkVideos")
public class BookmarkVideoDocument {

    @Id
    private String id;
    private UserDocument user;
    private VideoDocument video;

    public BookmarkVideoDocument(UserDocument user, VideoDocument video) {
        this.user = user;
        this.video = video;
    }

    public String getId() {
        return id;
    }

    public UserDocument getUser() {
        return user;
    }

    public VideoDocument getVideo() {
        return video;
    }
}
