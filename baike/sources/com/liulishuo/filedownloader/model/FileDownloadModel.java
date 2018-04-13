package com.liulishuo.filedownloader.model;

import android.content.ContentValues;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

public class FileDownloadModel {
    public static final int DEFAULT_CALLBACK_PROGRESS_TIMES = 100;
    public static final String ERR_MSG = "errMsg";
    public static final String ETAG = "etag";
    public static final String FILENAME = "filename";
    public static final String ID = "_id";
    public static final String PATH = "path";
    public static final String PATH_AS_DIRECTORY = "pathAsDirectory";
    public static final String SOFAR = "sofar";
    public static final String STATUS = "status";
    public static final String TOTAL = "total";
    public static final String URL = "url";
    private int a;
    private String b;
    private String c;
    private boolean d;
    private String e;
    private byte f;
    private long g;
    private long h;
    private String i;
    private String j;
    private boolean k;

    public void setId(int i) {
        this.a = i;
    }

    public void setUrl(String str) {
        this.b = str;
    }

    public void setPath(String str, boolean z) {
        this.c = str;
        this.d = z;
    }

    public void setStatus(byte b) {
        this.f = b;
    }

    public void setSoFar(long j) {
        this.g = j;
    }

    public void setTotal(long j) {
        this.k = j > 2147483647L;
        this.h = j;
    }

    public int getId() {
        return this.a;
    }

    public String getUrl() {
        return this.b;
    }

    public String getPath() {
        return this.c;
    }

    public String getTargetFilePath() {
        return FileDownloadUtils.getTargetFilePath(getPath(), isPathAsDirectory(), getFilename());
    }

    public String getTempFilePath() {
        if (getTargetFilePath() == null) {
            return null;
        }
        return FileDownloadUtils.getTempPath(getTargetFilePath());
    }

    public byte getStatus() {
        return this.f;
    }

    public long getSoFar() {
        return this.g;
    }

    public long getTotal() {
        return this.h;
    }

    public String getETag() {
        return this.j;
    }

    public void setETag(String str) {
        this.j = str;
    }

    public String getErrMsg() {
        return this.i;
    }

    public void setErrMsg(String str) {
        this.i = str;
    }

    public void setFilename(String str) {
        this.e = str;
    }

    public boolean isPathAsDirectory() {
        return this.d;
    }

    public String getFilename() {
        return this.e;
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, Integer.valueOf(getId()));
        contentValues.put("url", getUrl());
        contentValues.put("path", getPath());
        contentValues.put("status", Byte.valueOf(getStatus()));
        contentValues.put(SOFAR, Long.valueOf(getSoFar()));
        contentValues.put("total", Long.valueOf(getTotal()));
        contentValues.put(ERR_MSG, getErrMsg());
        contentValues.put("etag", getETag());
        contentValues.put(PATH_AS_DIRECTORY, Boolean.valueOf(isPathAsDirectory()));
        if (isPathAsDirectory() && getFilename() != null) {
            contentValues.put(FILENAME, getFilename());
        }
        return contentValues;
    }

    public boolean isLargeFile() {
        return this.k;
    }

    public String toString() {
        return FileDownloadUtils.formatString("id[%d], url[%s], path[%s], status[%d], sofar[%d], total[%d], etag[%s], %s", Integer.valueOf(this.a), this.b, this.c, Byte.valueOf(this.f), Long.valueOf(this.g), Long.valueOf(this.h), this.j, super.toString());
    }
}
