package com.aminocom.sdk.model.client;

public class Thumbnail {
    private String url;
    private int width;
    private int height;

    public Thumbnail() {
    }

    public Thumbnail(String url) {
        this.width = 0;
        this.height = 0;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}