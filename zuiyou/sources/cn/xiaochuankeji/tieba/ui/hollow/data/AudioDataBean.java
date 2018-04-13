package cn.xiaochuankeji.tieba.ui.hollow.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.alibaba.fastjson.annotation.JSONField;

public class AudioDataBean implements Parcelable {
    public static final Creator<AudioDataBean> CREATOR = new Creator<AudioDataBean>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public AudioDataBean a(Parcel parcel) {
            return new AudioDataBean(parcel);
        }

        public AudioDataBean[] a(int i) {
            return new AudioDataBean[i];
        }
    };
    @JSONField(name = "dur")
    public long dur;
    @JSONField(name = "uri")
    public String uri;
    @JSONField(name = "url")
    public String url;

    protected AudioDataBean(Parcel parcel) {
        this.uri = parcel.readString();
        this.url = parcel.readString();
        this.dur = parcel.readLong();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.uri);
        parcel.writeString(this.url);
        parcel.writeLong(this.dur);
    }

    public boolean a(AudioDataBean audioDataBean) {
        boolean z = audioDataBean != null;
        if (this.uri != null) {
            z = z && this.uri.equals(audioDataBean.uri);
        }
        if (this.url == null) {
            return z;
        }
        if (z && this.url.equals(audioDataBean.url)) {
            return true;
        }
        return false;
    }
}
