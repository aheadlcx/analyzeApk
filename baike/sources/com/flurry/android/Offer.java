package com.flurry.android;

public final class Offer {
    private long a;
    private String b;
    private String c;
    private int d;
    private AdImage e;

    Offer(long j, AdImage adImage, String str, String str2, int i) {
        this.a = j;
        this.b = str;
        this.e = adImage;
        this.c = str2;
        this.d = i;
    }

    public final long getId() {
        return this.a;
    }

    public final String getName() {
        return this.b;
    }

    public final String getDescription() {
        return this.c;
    }

    public final int getPrice() {
        return this.d;
    }

    public final String getUrl() {
        return "";
    }

    public final AdImage getImage() {
        return this.e;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[id=" + this.a + ",name=" + this.b + ",price=" + this.d + ", image size: " + this.e.e.length);
        return stringBuilder.toString();
    }
}
