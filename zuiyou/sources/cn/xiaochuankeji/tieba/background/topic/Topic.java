package cn.xiaochuankeji.tieba.background.topic;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import cn.htjyb.b.a;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class Topic implements Parcelable, Serializable {
    public static final Creator<Topic> CREATOR = new Creator<Topic>() {
        public Topic createFromParcel(Parcel parcel) {
            return new Topic(parcel);
        }

        public Topic[] newArray(int i) {
            return new Topic[i];
        }
    };
    private static final String kAddition = "addition";
    private static final String kKeyAttention = "atted";
    private static final String kKeyAttsTitle = "atts_title";
    private static final String kKeyNewPostCount = "newcnt";
    private static final String kKeyPartners = "partners";
    private static final String kKeyPostCount = "posts";
    private static final String kKeyPrevIcon = "icon";
    private static final String kKeySkin = "skin";
    private static final String kKeyTopic = "topic";
    private static final String kKeyTopicCover = "cover";
    private static final String kKeyTopicID = "id";
    private static final String kKeyTopicName = "topic";
    private static final long serialVersionUID = -4232086588342539127L;
    public String _addition;
    public String _attsTitle;
    public boolean _isAttention;
    public int _isadm;
    public int _newPostCount;
    public long _partners;
    public int _postCount;
    public int _skin;
    public long _topTime;
    public long _topicCoverID;
    public long _topicID;
    public String _topicName;
    public int _trank;
    public int _ups;
    public int apply_alert;
    public int apply_num;
    public String click_cb;
    public int enable_black;
    public int escort_apply_num;
    public int escort_enable;
    public int escort_recruiting;
    public int flag;
    public String list_show;
    public int post_report_count;
    public int recruiting;
    public int role;

    public Topic() {
        this._isadm = 0;
        this.flag = 1;
        this.escort_apply_num = 0;
        this._topicID = 0;
        this._topicName = "";
        this._topicCoverID = 0;
        this._postCount = 0;
        this._newPostCount = 0;
        this._addition = "";
        this._attsTitle = "";
        this._isAttention = false;
        this._topTime = 0;
        this._partners = 0;
        this._ups = 0;
        this._trank = 0;
        this._skin = 0;
    }

    public Topic(JSONObject jSONObject) {
        this();
        unserializeFrom(jSONObject);
    }

    public Topic(Intent intent) {
        this();
        Object stringExtra = intent.getStringExtra("topic");
        if (!TextUtils.isEmpty(stringExtra)) {
            try {
                unserializeFrom(new JSONObject(stringExtra));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    protected Topic(Parcel parcel) {
        boolean z = true;
        this._isadm = 0;
        this.flag = 1;
        this.escort_apply_num = 0;
        this._topicID = parcel.readLong();
        this._topicName = parcel.readString();
        this._topicCoverID = parcel.readLong();
        this._postCount = parcel.readInt();
        this._newPostCount = parcel.readInt();
        this._addition = parcel.readString();
        this._attsTitle = parcel.readString();
        if (parcel.readByte() == (byte) 0) {
            z = false;
        }
        this._isAttention = z;
        this._topTime = parcel.readLong();
        this._partners = parcel.readLong();
        this._ups = parcel.readInt();
        this._trank = parcel.readInt();
        this._isadm = parcel.readInt();
        this._skin = parcel.readInt();
        this.flag = parcel.readInt();
        this.role = parcel.readInt();
        this.recruiting = parcel.readInt();
        this.apply_num = parcel.readInt();
        this.apply_alert = parcel.readInt();
        this.escort_recruiting = parcel.readInt();
        this.escort_enable = parcel.readInt();
        this.escort_apply_num = parcel.readInt();
        this.list_show = parcel.readString();
        this.post_report_count = parcel.readInt();
        this.click_cb = parcel.readString();
        this.enable_black = parcel.readInt();
    }

    protected void unserializeFrom(JSONObject jSONObject) {
        if (jSONObject != null) {
            this._topicID = jSONObject.optLong("id");
            this._topicName = jSONObject.optString("topic");
            this._topicCoverID = jSONObject.optLong(kKeyTopicCover);
            this._postCount = jSONObject.optInt(kKeyPostCount);
            this._newPostCount = jSONObject.optInt(kKeyNewPostCount);
            this._addition = jSONObject.optString(kAddition);
            this._isAttention = jSONObject.optInt(kKeyAttention) != 0;
            this._topTime = jSONObject.optLong("top");
            this._attsTitle = jSONObject.optString(kKeyAttsTitle);
            this._partners = (long) jSONObject.optInt(kKeyPartners);
            this._skin = jSONObject.optInt(kKeySkin);
            JSONObject optJSONObject = jSONObject.optJSONObject("attinfo");
            if (optJSONObject != null) {
                this._ups = optJSONObject.optInt("up");
                this._trank = optJSONObject.optInt("trank");
            }
            this._isadm = jSONObject.optInt("isadm");
            this.flag = jSONObject.optInt("flag", 0);
            this.role = jSONObject.optInt("role", 0);
            this.recruiting = jSONObject.optInt("recruiting", 0);
            this.apply_num = jSONObject.optInt("apply_num", 0);
            this.apply_alert = jSONObject.optInt("apply_alert", 0);
            this.escort_apply_num = jSONObject.optInt("guard_apply_num", 0);
            this.escort_recruiting = jSONObject.optInt("guard_recruiting", 0);
            this.escort_enable = jSONObject.optInt("enable_guard", 0);
            this.click_cb = jSONObject.optString("click_cb");
            this.list_show = jSONObject.optString("list_show");
            this.post_report_count = jSONObject.optInt("post_report_count", 0);
            this.enable_black = jSONObject.optInt("enable_black", 0);
        }
    }

    public JSONObject serializeTo() throws JSONException {
        int i = 1;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this._topicID);
        jSONObject.put("topic", this._topicName);
        jSONObject.put(kKeyTopicCover, this._topicCoverID);
        jSONObject.put(kKeyPostCount, this._postCount);
        jSONObject.put(kKeyNewPostCount, this._newPostCount);
        jSONObject.put(kAddition, this._addition);
        String str = kKeyAttention;
        if (!this._isAttention) {
            i = 0;
        }
        jSONObject.put(str, i);
        jSONObject.put(kKeyAttsTitle, this._attsTitle);
        jSONObject.put(kKeyPartners, this._partners);
        jSONObject.put(kKeySkin, this._skin);
        jSONObject.put("top", this._topTime);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("up", this._ups);
        jSONObject2.put("trank", this._trank);
        jSONObject.put("attinfo", jSONObject2);
        jSONObject.put("isadm", this._isadm);
        jSONObject.put("flag", this.flag);
        jSONObject.put("role", this.role);
        jSONObject.put("recruiting", this.recruiting);
        jSONObject.put("apply_num", this.apply_num);
        jSONObject.put("apply_alert", this.apply_alert);
        jSONObject.put("guard_apply_num", this.escort_apply_num);
        jSONObject.put("guard_recruiting", this.escort_recruiting);
        jSONObject.put("guard_enable", this.escort_enable);
        jSONObject.put("list_show", this.list_show);
        jSONObject.put("click_cb", this.click_cb);
        return jSONObject;
    }

    public void fillToIntent(Intent intent) {
        try {
            intent.putExtra("topic", serializeTo().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public a topicCover() {
        return cn.xiaochuankeji.tieba.background.a.f().a(Type.kTopicCover, this._topicCoverID);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Topic)) {
            return false;
        }
        if (this._topicID != ((Topic) obj)._topicID) {
            return false;
        }
        return true;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this._topicID);
        parcel.writeString(this._topicName);
        parcel.writeLong(this._topicCoverID);
        parcel.writeInt(this._postCount);
        parcel.writeInt(this._newPostCount);
        parcel.writeString(this._addition);
        parcel.writeString(this._attsTitle);
        parcel.writeByte((byte) (this._isAttention ? 1 : 0));
        parcel.writeLong(this._topTime);
        parcel.writeLong(this._partners);
        parcel.writeInt(this._ups);
        parcel.writeInt(this._trank);
        parcel.writeInt(this._isadm);
        parcel.writeInt(this._skin);
        parcel.writeInt(this.flag);
        parcel.writeInt(this.role);
        parcel.writeInt(this.recruiting);
        parcel.writeInt(this.apply_num);
        parcel.writeInt(this.apply_alert);
        parcel.writeInt(this.escort_recruiting);
        parcel.writeInt(this.escort_enable);
        parcel.writeInt(this.escort_apply_num);
        parcel.writeString(this.list_show);
        parcel.writeInt(this.post_report_count);
        parcel.writeString(this.click_cb);
        parcel.writeInt(this.enable_black);
    }
}
