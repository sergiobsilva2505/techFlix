package br.com.fiap.techFlix.domain.entities.video;

import br.com.fiap.techFlix.domain.validation.Validator;

public class VideoDetails {

    private int likes;
    private int views;

    public VideoDetails(int likes, int views) {
        Validator.greaterThanOrEqual(likes, 0, "Video likes");
        Validator.greaterThanOrEqual(views, 0, "Video views");
        this.likes = likes;
        this.views = views;
    }

    public int getLikes() {
        return likes;
    }

    public int getViews() {
        return views;
    }
}
