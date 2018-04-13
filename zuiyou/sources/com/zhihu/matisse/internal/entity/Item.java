package com.zhihu.matisse.internal.entity;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.provider.MediaStore.Files;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.zhihu.matisse.MimeType;

public class Item implements Parcelable {
    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Nullable
        public Item createFromParcel(Parcel parcel) {
            return new Item(parcel);
        }

        public Item[] newArray(int i) {
            return new Item[i];
        }
    };
    public static final String ITEM_DISPLAY_NAME_CAPTURE = "Capture";
    public static final long ITEM_ID_CAPTURE = -1;
    public final long duration;
    public final int height;
    public final long id;
    public final String mimeType;
    public final String path;
    public final long size;
    public final long time;
    public final Uri uri;
    public String videoThumbnail;
    public final int width;

    private Item(long j, String str, String str2, long j2, int i, int i2, long j3, long j4) {
        Uri uri;
        this.id = j;
        this.mimeType = str;
        if (isImage()) {
            uri = Media.EXTERNAL_CONTENT_URI;
        } else if (isVideo()) {
            uri = Video.Media.EXTERNAL_CONTENT_URI;
        } else {
            uri = Files.getContentUri("external");
        }
        this.uri = ContentUris.withAppendedId(uri, j);
        this.path = str2;
        this.size = j2;
        this.width = i;
        this.height = i2;
        this.duration = j3;
        this.time = j4;
    }

    private Item(Parcel parcel) {
        this.id = parcel.readLong();
        this.mimeType = parcel.readString();
        this.uri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.path = parcel.readString();
        this.size = parcel.readLong();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.duration = parcel.readLong();
        this.time = parcel.readLong();
    }

    public static Item valueOf(Cursor cursor) {
        return new Item(cursor.getLong(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("mime_type")), cursor.getString(cursor.getColumnIndex("_data")), cursor.getLong(cursor.getColumnIndex("_size")), cursor.getInt(cursor.getColumnIndex("width")), cursor.getInt(cursor.getColumnIndex("height")), cursor.getLong(cursor.getColumnIndex("duration")), cursor.getLong(cursor.getColumnIndex("datetaken")));
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeString(this.mimeType);
        parcel.writeParcelable(this.uri, 0);
        parcel.writeString(this.path);
        parcel.writeLong(this.size);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeLong(this.duration);
        parcel.writeLong(this.time);
    }

    public Uri getContentUri() {
        return this.uri;
    }

    public boolean isCapture() {
        return this.id == -1;
    }

    public boolean isImage() {
        try {
            if (TextUtils.isEmpty(this.mimeType) || this.mimeType.equals(MimeType.JPEG.toString()) || this.mimeType.equals(MimeType.PNG.toString()) || this.mimeType.equals(MimeType.GIF.toString()) || this.mimeType.equals(MimeType.BMP.toString()) || this.mimeType.equals(MimeType.WEBP.toString())) {
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            return true;
        }
    }

    public boolean isGif() {
        if (TextUtils.isEmpty(this.mimeType)) {
            return false;
        }
        return this.mimeType.equals(MimeType.GIF.toString());
    }

    public boolean isVideo() {
        if (TextUtils.isEmpty(this.mimeType)) {
            return false;
        }
        if (this.mimeType.equals(MimeType.MPEG.toString()) || this.mimeType.equals(MimeType.MP4.toString()) || this.mimeType.equals(MimeType.QUICKTIME.toString()) || this.mimeType.equals(MimeType.THREEGPP.toString()) || this.mimeType.equals(MimeType.THREEGPP2.toString()) || this.mimeType.equals(MimeType.MKV.toString()) || this.mimeType.equals(MimeType.WEBM.toString()) || this.mimeType.equals(MimeType.TS.toString()) || this.mimeType.equals(MimeType.AVI.toString())) {
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Item)) {
            return false;
        }
        Item item = (Item) obj;
        if (this.uri.equals(item.uri) || this.id == item.id) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = Long.valueOf(this.id).hashCode() + 31;
        if (!TextUtils.isEmpty(this.mimeType)) {
            hashCode = (hashCode * 31) + this.mimeType.hashCode();
        }
        return (((((hashCode * 31) + this.uri.hashCode()) * 31) + Long.valueOf(this.size).hashCode()) * 31) + Long.valueOf(this.duration).hashCode();
    }
}
