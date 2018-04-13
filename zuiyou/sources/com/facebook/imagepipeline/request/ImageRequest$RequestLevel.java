package com.facebook.imagepipeline.request;

public enum ImageRequest$RequestLevel {
    FULL_FETCH(1),
    DISK_CACHE(2),
    ENCODED_MEMORY_CACHE(3),
    BITMAP_MEMORY_CACHE(4);
    
    private int mValue;

    private ImageRequest$RequestLevel(int i) {
        this.mValue = i;
    }

    public int getValue() {
        return this.mValue;
    }

    public static ImageRequest$RequestLevel getMax(ImageRequest$RequestLevel imageRequest$RequestLevel, ImageRequest$RequestLevel imageRequest$RequestLevel2) {
        return imageRequest$RequestLevel.getValue() > imageRequest$RequestLevel2.getValue() ? imageRequest$RequestLevel : imageRequest$RequestLevel2;
    }
}
