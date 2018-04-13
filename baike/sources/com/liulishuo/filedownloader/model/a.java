package com.liulishuo.filedownloader.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class a implements Creator<FileDownloadHeader> {
    a() {
    }

    public FileDownloadHeader createFromParcel(Parcel parcel) {
        return new FileDownloadHeader(parcel);
    }

    public FileDownloadHeader[] newArray(int i) {
        return new FileDownloadHeader[i];
    }
}
