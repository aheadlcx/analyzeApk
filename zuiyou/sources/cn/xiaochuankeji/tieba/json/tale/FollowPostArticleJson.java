package cn.xiaochuankeji.tieba.json.tale;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import cn.xiaochuankeji.tieba.background.tale.TaleAuthor;
import com.alibaba.fastjson.annotation.JSONField;

public class FollowPostArticleJson implements Parcelable {
    public static final Creator<FollowPostArticleJson> CREATOR = new Creator<FollowPostArticleJson>() {
        public FollowPostArticleJson createFromParcel(Parcel parcel) {
            return new FollowPostArticleJson(parcel);
        }

        public FollowPostArticleJson[] newArray(int i) {
            return new FollowPostArticleJson[i];
        }
    };
    @JSONField(name = "author")
    public TaleAuthor author;
    @JSONField(name = "comment_cnt")
    public int commentCount;
    @JSONField(name = "content")
    public String content;
    @JSONField(name = "cover")
    public long coverId;
    @JSONField(name = "ct")
    public long createTime;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "content_img_count")
    public int imgCount;
    @JSONField(name = "like_cnt")
    public int likeCount;
    @JSONField(name = "liked")
    public int liked;
    @JSONField(name = "share_cnt")
    public int shareCount;
    @JSONField(name = "abstract")
    public String summary;
    @JSONField(name = "theme")
    public FollowPostThemeJson theme;
    public int type;

    protected FollowPostArticleJson(Parcel parcel) {
        this.id = parcel.readLong();
        this.createTime = parcel.readLong();
        this.imgCount = parcel.readInt();
        this.summary = parcel.readString();
        this.coverId = parcel.readLong();
        this.theme = (FollowPostThemeJson) parcel.readParcelable(FollowPostThemeJson.class.getClassLoader());
        this.content = parcel.readString();
        this.author = (TaleAuthor) parcel.readParcelable(TaleAuthor.class.getClassLoader());
        this.shareCount = parcel.readInt();
        this.likeCount = parcel.readInt();
        this.commentCount = parcel.readInt();
        this.liked = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeLong(this.createTime);
        parcel.writeInt(this.imgCount);
        parcel.writeString(this.summary);
        parcel.writeLong(this.coverId);
        parcel.writeParcelable(this.theme, i);
        parcel.writeString(this.content);
        parcel.writeParcelable(this.author, i);
        parcel.writeInt(this.shareCount);
        parcel.writeInt(this.likeCount);
        parcel.writeInt(this.commentCount);
        parcel.writeInt(this.liked);
    }

    public int describeContents() {
        return 0;
    }
}
