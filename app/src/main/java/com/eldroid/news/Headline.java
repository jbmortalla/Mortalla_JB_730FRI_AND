package com.eldroid.news;

public class Headline {
    private String title;
    private String content;

    public Headline(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}