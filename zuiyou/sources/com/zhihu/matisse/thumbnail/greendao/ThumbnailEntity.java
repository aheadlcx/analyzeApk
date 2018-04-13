package com.zhihu.matisse.thumbnail.greendao;

public class ThumbnailEntity {
    public static final int STATUS_FAILED = 3;
    public static final int STATUS_GENERATING = 1;
    public static final int STATUS_NONE = 0;
    public static final int STATUS_SUCCESS = 2;
    private Long id;
    private long lastGenerateTime;
    private long origId;
    private String origPath;
    private int status;
    private String thumbnailPath;

    public ThumbnailEntity(Long l, long j, String str, String str2, int i, long j2) {
        this.id = l;
        this.origId = j;
        this.origPath = str;
        this.thumbnailPath = str2;
        this.status = i;
        this.lastGenerateTime = j2;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public long getOrigId() {
        return this.origId;
    }

    public void setOrigId(long j) {
        this.origId = j;
    }

    public String getOrigPath() {
        return this.origPath;
    }

    public void setOrigPath(String str) {
        this.origPath = str;
    }

    public String getThumbnailPath() {
        return this.thumbnailPath;
    }

    public void setThumbnailPath(String str) {
        this.thumbnailPath = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public long getLastGenerateTime() {
        return this.lastGenerateTime;
    }

    public void setLastGenerateTime(long j) {
        this.lastGenerateTime = j;
    }
}
