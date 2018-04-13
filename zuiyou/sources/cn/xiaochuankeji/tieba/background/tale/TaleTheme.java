package cn.xiaochuankeji.tieba.background.tale;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.alibaba.fastjson.annotation.JSONField;

public class TaleTheme implements Parcelable {
    public static final Creator<TaleTheme> CREATOR = new Creator<TaleTheme>() {
        public TaleTheme createFromParcel(Parcel parcel) {
            return new TaleTheme(parcel);
        }

        public TaleTheme[] newArray(int i) {
            return new TaleTheme[i];
        }
    };
    @JSONField(name = "article_cnt")
    public long articleCnt;
    @JSONField(name = "author")
    public TaleAuthor author;
    @JSONField(name = "ct")
    public long ct;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "mid")
    public long mid;
    @JSONField(name = "title")
    public String title;
    @JSONField(name = "ut")
    public long ut;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeLong(this.mid);
        parcel.writeLong(this.ct);
        parcel.writeLong(this.ut);
        parcel.writeString(this.title);
        parcel.writeLong(this.articleCnt);
        parcel.writeParcelable(this.author, i);
    }

    protected TaleTheme(Parcel parcel) {
        this.id = parcel.readLong();
        this.mid = parcel.readLong();
        this.ct = parcel.readLong();
        this.ut = parcel.readLong();
        this.title = parcel.readString();
        this.articleCnt = parcel.readLong();
        this.author = (TaleAuthor) parcel.readParcelable(TaleAuthor.class.getClassLoader());
    }
}
