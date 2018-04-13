package cn.xiaochuankeji.tieba.json.tale;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import cn.xiaochuankeji.tieba.background.tale.TaleAuthor;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class FollowPostThemeJson implements Parcelable {
    public static final Creator<FollowPostThemeJson> CREATOR = new Creator<FollowPostThemeJson>() {
        public FollowPostThemeJson createFromParcel(Parcel parcel) {
            return new FollowPostThemeJson(parcel);
        }

        public FollowPostThemeJson[] newArray(int i) {
            return new FollowPostThemeJson[i];
        }
    };
    @JSONField(name = "author")
    public TaleAuthor author;
    @JSONField(name = "cover_article_authors")
    public List<TaleAuthor> authors;
    @JSONField(name = "ct")
    public long createTime;
    @JSONField(name = "folded_article_cnt")
    public long folded_article_cnt;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "article_cnt")
    public long postCount;
    @JSONField(name = "cover_articles")
    public List<FollowPostArticleJson> postList;
    @JSONField(name = "title")
    public String title;

    protected FollowPostThemeJson(Parcel parcel) {
        this.id = parcel.readLong();
        this.createTime = parcel.readLong();
        this.title = parcel.readString();
        this.author = (TaleAuthor) parcel.readParcelable(TaleAuthor.class.getClassLoader());
        this.postCount = parcel.readLong();
        this.folded_article_cnt = parcel.readLong();
        this.postList = parcel.createTypedArrayList(FollowPostArticleJson.CREATOR);
        this.authors = parcel.createTypedArrayList(TaleAuthor.CREATOR);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeLong(this.createTime);
        parcel.writeString(this.title);
        parcel.writeParcelable(this.author, i);
        parcel.writeLong(this.postCount);
        parcel.writeLong(this.folded_article_cnt);
        parcel.writeTypedList(this.postList);
        parcel.writeTypedList(this.authors);
    }

    public int describeContents() {
        return 0;
    }
}
