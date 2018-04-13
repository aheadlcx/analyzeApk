package qsbk.app.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.utils.SharePreferenceUtils;

public class Comment implements Parcelable, Serializable {
    public static final Creator<Comment> CREATOR = new g();
    public static final int IMG_MAX_HEIGHT_DP = 180;
    public static final int IMG_MAX_WIDTH_DP = 100;
    public static final String LOCAL_COMMENT_ID = "-1";
    public ArrayList<AtInfo> atInfoTexts = new ArrayList();
    public Image bigImage;
    public String content;
    public int createAt;
    public int floor;
    public String icon;
    public String id;
    public int likeCount;
    public boolean liked;
    public Comment reply;
    public String role;
    public Image smallImage;
    public String uid;
    public String userName;

    public static Comment newInstance(JSONObject jSONObject) {
        JSONException e;
        if (jSONObject == null) {
            return null;
        }
        Comment comment;
        try {
            comment = new Comment();
            try {
                comment.floor = jSONObject.optInt("floor");
                comment.id = jSONObject.optString("id");
                comment.content = jSONObject.optString("content");
                JSONObject optJSONObject = jSONObject.optJSONObject("user");
                if (optJSONObject != null) {
                    comment.uid = optJSONObject.optString("id");
                    comment.icon = optJSONObject.optString("icon");
                    comment.role = optJSONObject.optString(QsbkDatabase.ROLE);
                    comment.userName = optJSONObject.optString(QsbkDatabase.LOGIN);
                }
                comment.likeCount = jSONObject.optInt("like_count");
                comment.liked = SharePreferenceUtils.getSharePreferencesBoolValue("comment_like_" + comment.id);
                if (comment.liked && comment.likeCount == 0) {
                    comment.likeCount = 1;
                }
                comment.createAt = jSONObject.optInt(QsbkDatabase.CREATE_AT);
                optJSONObject = jSONObject.optJSONObject("at_infos");
                if (optJSONObject != null) {
                    Iterator keys = optJSONObject.keys();
                    while (keys.hasNext()) {
                        AtInfo atInfo = new AtInfo();
                        String obj = keys.next().toString();
                        if (!TextUtils.isEmpty(obj)) {
                            atInfo.name = obj;
                            atInfo.uid = String.valueOf(optJSONObject.getInt(obj));
                            comment.atInfoTexts.add(atInfo);
                        }
                    }
                }
                comment.createAt = jSONObject.optInt(QsbkDatabase.CREATE_AT);
                optJSONObject = jSONObject.optJSONObject("high");
                if (optJSONObject != null) {
                    comment.bigImage = Image.parse(optJSONObject);
                }
                optJSONObject = jSONObject.optJSONObject(Config.EXCEPTION_MEMORY_LOW);
                if (optJSONObject != null) {
                    comment.smallImage = Image.parse(optJSONObject);
                }
                optJSONObject = jSONObject.optJSONObject("refer");
                if (optJSONObject == null) {
                    return comment;
                }
                comment.reply = newInstance(optJSONObject);
                return comment;
            } catch (JSONException e2) {
                e = e2;
                e.printStackTrace();
                return comment;
            }
        } catch (JSONException e3) {
            JSONException jSONException = e3;
            comment = null;
            e = jSONException;
            e.printStackTrace();
            return comment;
        }
    }

    public boolean hasImage() {
        return this.smallImage != null;
    }

    public JSONObject toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("floor", this.floor);
            jSONObject.put("id", this.id);
            jSONObject.put("content", this.content);
            if (this.uid != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("id", this.uid);
                jSONObject2.put("icon", this.icon);
                jSONObject2.put(QsbkDatabase.ROLE, this.role);
                jSONObject2.put(QsbkDatabase.LOGIN, this.userName);
                jSONObject.put("user", jSONObject2);
            }
            jSONObject.put("like_count", this.likeCount);
            jSONObject.put(QsbkDatabase.CREATE_AT, this.createAt);
            if (this.smallImage != null) {
                jSONObject.put(Config.EXCEPTION_MEMORY_LOW, this.smallImage.toJson());
            }
            if (this.bigImage != null) {
                jSONObject.put("high", this.bigImage.toJson());
            }
            if (this.reply == null) {
                return jSONObject;
            }
            jSONObject.put("refer", this.reply.toJson());
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setLiked(boolean z) {
        this.liked = z;
        SharePreferenceUtils.setSharePreferencesValue("comment_like_" + this.id, z);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.content);
        parcel.writeString(this.icon);
        parcel.writeString(this.id);
        parcel.writeString(this.role);
        parcel.writeString(this.uid);
        parcel.writeString(this.userName);
        parcel.writeInt(this.floor);
        parcel.writeInt(this.likeCount);
        parcel.writeInt(this.liked ? 1 : 0);
        parcel.writeInt(this.createAt);
        parcel.writeParcelable(this.reply, i);
    }

    public String toString() {
        return "Comment [floor=" + this.floor + ", id=" + this.id + ", content=" + this.content + ", userName=" + this.userName + ", role=" + this.role + ", icon=" + this.icon + ", uid=" + this.uid + "]";
    }

    public int hashCode() {
        return this.id != null ? this.id.hashCode() : super.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof Comment) && ((Comment) obj).id != null && ((Comment) obj).id.equals(this.id)) {
            return true;
        }
        return false;
    }
}
