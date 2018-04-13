package com.liulishuo.filedownloader.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class b implements Creator<FileDownloadTaskAtom> {
    b() {
    }

    public FileDownloadTaskAtom createFromParcel(Parcel parcel) {
        return new FileDownloadTaskAtom(parcel);
    }

    public FileDownloadTaskAtom[] newArray(int i) {
        return new FileDownloadTaskAtom[i];
    }
}
