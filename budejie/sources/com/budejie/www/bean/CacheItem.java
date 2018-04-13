package com.budejie.www.bean;

import java.io.File;

public class CacheItem implements Comparable<CacheItem> {
    File file;
    long time;

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public int compareTo(CacheItem cacheItem) {
        if (this.time > cacheItem.time) {
            return 1;
        }
        return 0;
    }
}
