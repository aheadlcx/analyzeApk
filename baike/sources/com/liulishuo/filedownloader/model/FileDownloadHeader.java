package com.liulishuo.filedownloader.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mobstat.Config;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileDownloadHeader implements Parcelable {
    public static final Creator<FileDownloadHeader> CREATOR = new a();
    private HashMap<String, List<String>> a;

    public void add(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (str.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        } else if (str2 == null) {
            throw new NullPointerException("value == null");
        } else {
            if (this.a == null) {
                this.a = new HashMap();
            }
            List list = (List) this.a.get(str);
            if (list == null) {
                list = new ArrayList();
                this.a.put(str, list);
            }
            if (!list.contains(str2)) {
                list.add(str2);
            }
        }
    }

    public void add(String str) {
        String[] split = str.split(Config.TRACE_TODAY_VISIT_SPLIT);
        add(split[0].trim(), split[1].trim());
    }

    public void removeAll(String str) {
        if (this.a != null) {
            this.a.remove(str);
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeMap(this.a);
    }

    public HashMap<String, List<String>> getHeaders() {
        return this.a;
    }

    protected FileDownloadHeader(Parcel parcel) {
        this.a = parcel.readHashMap(String.class.getClassLoader());
    }

    public String toString() {
        return this.a.toString();
    }
}
