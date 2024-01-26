package br.com.fiap.techFlix.adapter.persistence.bookmarkvideo;

import br.com.fiap.techFlix.application.ports.UserBookmarkedCategoriesPort;

public class UserBookmarkedCategories implements UserBookmarkedCategoriesPort {

    private String name;
    private int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
