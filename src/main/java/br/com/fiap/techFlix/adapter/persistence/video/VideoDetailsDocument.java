package br.com.fiap.techFlix.adapter.persistence.video;

public class VideoDetailsDocument {

    private int likes;
    private int views;

    public VideoDetailsDocument() {
    }

    public VideoDetailsDocument(int likes, int views) {
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
