package br.com.fiap.techFlix.adapter.persistence.bookmarkvideo;

import br.com.fiap.techFlix.adapter.persistence.user.UserDocument;
import br.com.fiap.techFlix.adapter.persistence.video.VideoDocument;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookmarkVideos")
public class BookmarkVideoDocument {

    @Id
    private String id;
    private UserDocument user;
    private VideoDocument video;

    public BookmarkVideoDocument(String id, UserDocument user, VideoDocument video) {
        this.id = id;
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
