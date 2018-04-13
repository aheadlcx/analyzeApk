package qsbk.app.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mobstat.Config;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.utils.SharePreferenceUtils;

public class CircleComment implements Parcelable, Serializable {
    public static final Creator<CircleComment> CREATOR = new f();
    public ArrayList<AtInfo> atInfoTexts = new ArrayList();
    public Image bigImage;
    public String content;
    public int createTime;
    public String icon;
    public String id;
    public int likeCount;
    public boolean liked;
    public int nickStatus;
    public CircleComment reply;
    public String role;
    public Image smallImage;
    public String uid;
    public String userName;

    protected CircleComment(Parcel parcel) {
        this.id = parcel.readString();
        this.content = parcel.readString();
        this.userName = parcel.readString();
        this.role = parcel.readString();
        this.icon = parcel.readString();
        this.uid = parcel.readString();
        this.reply = (CircleComment) parcel.readParcelable(CircleComment.class.getClassLoader());
        this.nickStatus = parcel.readInt();
        this.createTime = parcel.readInt();
        this.likeCount = parcel.readInt();
        this.liked = parcel.readByte() != (byte) 0;
        this.smallImage = (Image) parcel.readParcelable(Image.class.getClassLoader());
        this.bigImage = (Image) parcel.readParcelable(Image.class.getClassLoader());
        this.atInfoTexts = new ArrayList();
        parcel.readList(this.atInfoTexts, AtInfo.class.getClassLoader());
    }

    public static CircleComment newInstance(JSONObject jSONObject) {
        CircleComment circleComment = null;
        if (jSONObject != null) {
            circleComment = new CircleComment();
            circleComment.id = jSONObject.optString("id");
            circleComment.content = jSONObject.optString("content");
            JSONObject optJSONObject = jSONObject.optJSONObject("user");
            if (optJSONObject != null) {
                circleComment.uid = optJSONObject.optString("id");
                circleComment.icon = optJSONObject.optString("icon");
                circleComment.role = optJSONObject.optString(QsbkDatabase.ROLE);
                circleComment.userName = optJSONObject.optString(QsbkDatabase.LOGIN);
                circleComment.nickStatus = optJSONObject.optInt("nick_status");
            }
            optJSONObject = jSONObject.optJSONObject("comment");
            if (optJSONObject != null) {
                circleComment.reply = newInstance(optJSONObject);
            }
            circleComment.createTime = jSONObject.optInt(QsbkDatabase.CREATE_AT);
            circleComment.likeCount = jSONObject.optInt("like_count");
            circleComment.liked = jSONObject.optBoolean("liked");
            optJSONObject = jSONObject.optJSONObject("image");
            if (optJSONObject != null) {
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("high");
                if (optJSONObject2 != null) {
                    circleComment.bigImage = Image.parse(optJSONObject2);
                }
                optJSONObject = optJSONObject.optJSONObject(Config.EXCEPTION_MEMORY_LOW);
                if (optJSONObject != null) {
                    circleComment.smallImage = Image.parse(optJSONObject);
                }
            }
            try {
                JSONArray optJSONArray = jSONObject.optJSONArray("at_users");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        AtInfo atInfo = new AtInfo();
                        atInfo = AtInfo.constructJson(optJSONArray.getJSONObject(i));
                        if (atInfo != null) {
                            circleComment.atInfoTexts.add(atInfo);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (!circleComment.liked) {
                circleComment.liked = SharePreferenceUtils.getSharePreferencesBoolValue("circle_comment_like_" + circleComment.id);
            }
        }
        return circleComment;
    }

    public void setLiked(boolean z) {
        this.liked = z;
        SharePreferenceUtils.setSharePreferencesValue("circle_comment_like_" + this.id, z);
    }

    public void update(CircleComment circleComment) {
        this.id = circleComment.id;
        this.content = circleComment.content;
        this.userName = circleComment.userName;
        this.role = circleComment.role;
        this.icon = circleComment.icon;
        this.uid = circleComment.uid;
        this.reply = circleComment.reply;
        this.nickStatus = circleComment.nickStatus;
        this.createTime = circleComment.createTime;
        this.likeCount = circleComment.likeCount;
        this.liked = circleComment.liked;
        this.smallImage = circleComment.smallImage;
        this.bigImage = circleComment.bigImage;
        this.atInfoTexts = circleComment.atInfoTexts;
    }

    public boolean hasImage() {
        return this.smallImage != null;
    }

    public int hashCode() {
        return this.id != null ? this.id.hashCode() : super.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof CircleComment) && ((CircleComment) obj).id != null && ((CircleComment) obj).id.equals(this.id)) {
            return true;
        }
        return false;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.content);
        parcel.writeString(this.userName);
        parcel.writeString(this.role);
        parcel.writeString(this.icon);
        parcel.writeString(this.uid);
        parcel.writeParcelable(this.reply, i);
        parcel.writeInt(this.nickStatus);
        parcel.writeInt(this.createTime);
        parcel.writeInt(this.likeCount);
        parcel.writeByte(this.liked ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.smallImage, i);
        parcel.writeParcelable(this.bigImage, i);
        parcel.writeList(this.atInfoTexts);
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.id);
            jSONObject.put("content", this.content);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("id", this.uid);
            jSONObject2.put("icon", this.icon);
            jSONObject2.put(QsbkDatabase.ROLE, this.role);
            jSONObject2.put(QsbkDatabase.LOGIN, this.userName);
            jSONObject2.put("nick_status", this.nickStatus);
            jSONObject.put("user", jSONObject2);
            if (this.reply != null) {
                jSONObject.put("comment", this.reply.toJson());
            }
            jSONObject.put(QsbkDatabase.CREATE_AT, this.createTime);
            jSONObject.put("like_count", this.likeCount);
            jSONObject.put("liked", this.liked);
            jSONObject2 = new JSONObject();
            if (this.bigImage != null) {
                jSONObject2.put("high", this.bigImage.toJson());
            }
            if (this.smallImage != null) {
                jSONObject2.put(Config.EXCEPTION_MEMORY_LOW, this.smallImage.toJson());
            }
            jSONObject.put("image", jSONObject2);
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.atInfoTexts.size(); i++) {
                jSONArray.put(AtInfo.toJson((AtInfo) this.atInfoTexts.get(i)));
            }
            jSONObject.put("at_users", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
