package cn.xiaochuankeji.tieba.ui.hollow.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.alibaba.fastjson.annotation.JSONField;

public class RoomDataBean implements Parcelable {
    public static final Creator<RoomDataBean> CREATOR = new Creator<RoomDataBean>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public RoomDataBean a(Parcel parcel) {
            return new RoomDataBean(parcel);
        }

        public RoomDataBean[] a(int i) {
            return new RoomDataBean[i];
        }
    };
    @JSONField(name = "audio")
    public AudioDataBean audio;
    @JSONField(name = "ct")
    public long createTime;
    @JSONField(name = "emotion")
    public EmotionDataBean emotion;
    @JSONField(name = "hugged")
    public int hugged;
    @JSONField(name = "hugs")
    public long hugs;
    @JSONField(name = "id")
    public long id;
    @JSONField(name = "xmember")
    public MemberDataBean member;
    @JSONField(name = "msg_count")
    public long msgCount;
    @JSONField(name = "self")
    public long self;
    @JSONField(name = "subject")
    public String subject;

    protected RoomDataBean(Parcel parcel) {
        this.id = parcel.readLong();
        this.subject = parcel.readString();
        this.msgCount = parcel.readLong();
        this.createTime = parcel.readLong();
        this.self = parcel.readLong();
        this.audio = (AudioDataBean) parcel.readParcelable(AudioDataBean.class.getClassLoader());
        this.emotion = (EmotionDataBean) parcel.readParcelable(EmotionDataBean.class.getClassLoader());
        this.member = (MemberDataBean) parcel.readParcelable(MemberDataBean.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeString(this.subject);
        parcel.writeLong(this.msgCount);
        parcel.writeLong(this.createTime);
        parcel.writeLong(this.self);
        parcel.writeParcelable(this.audio, i);
        parcel.writeParcelable(this.emotion, i);
        parcel.writeParcelable(this.member, i);
    }

    public String toString() {
        return "RoomDataBean{id=" + this.id + ", subject='" + this.subject + '\'' + ", audio=" + this.audio + ", emotion=" + this.emotion + ", member=" + this.member + ", msgCount=" + this.msgCount + ", createTime=" + this.createTime + ", self=" + this.self + '}';
    }

    public static HollowRecommendItemBean a(RoomDataBean roomDataBean) {
        HollowRecommendItemBean hollowRecommendItemBean = new HollowRecommendItemBean();
        hollowRecommendItemBean.id = roomDataBean.id;
        hollowRecommendItemBean.emotion = roomDataBean.emotion;
        hollowRecommendItemBean.audio = roomDataBean.audio;
        hollowRecommendItemBean.member = roomDataBean.member;
        hollowRecommendItemBean.msgCount = (int) roomDataBean.msgCount;
        hollowRecommendItemBean.subject = roomDataBean.subject;
        hollowRecommendItemBean.hugged = roomDataBean.hugged;
        hollowRecommendItemBean.hugs = roomDataBean.hugs;
        return hollowRecommendItemBean;
    }
}
