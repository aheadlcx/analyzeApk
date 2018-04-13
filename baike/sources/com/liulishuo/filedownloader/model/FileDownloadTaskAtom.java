package com.liulishuo.filedownloader.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

public class FileDownloadTaskAtom implements Parcelable {
    public static final Creator<FileDownloadTaskAtom> CREATOR = new b();
    private String a;
    private String b;
    private long c;
    private int d;

    public FileDownloadTaskAtom(String str, String str2, long j) {
        setUrl(str);
        setPath(str2);
        setTotalBytes(j);
    }

    public int getId() {
        if (this.d != 0) {
            return this.d;
        }
        int generateId = FileDownloadUtils.generateId(getUrl(), getPath());
        this.d = generateId;
        return generateId;
    }

    public String getUrl() {
        return this.a;
    }

    public void setUrl(String str) {
        this.a = str;
    }

    public String getPath() {
        return this.b;
    }

    public void setPath(String str) {
        this.b = str;
    }

    public long getTotalBytes() {
        return this.c;
    }

    public void setTotalBytes(long j) {
        this.c = j;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeLong(this.c);
    }

    protected FileDownloadTaskAtom(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readLong();
    }
}
