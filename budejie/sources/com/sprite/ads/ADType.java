package com.sprite.ads;

public enum ADType {
    BANNER("banner"),
    FEED("feed"),
    SPLASH("splash"),
    POPUP("popup"),
    MOVIE("movie");
    
    private String value;

    private ADType(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
