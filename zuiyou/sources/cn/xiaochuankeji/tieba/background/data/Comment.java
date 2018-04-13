package cn.xiaochuankeji.tieba.background.data;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import cn.htjyb.b.a;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.data.post.InnerComment;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.utils.d;
import com.iflytek.cloud.SpeechConstant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Comment implements Serializable {
    protected static final long serialVersionUID = 5461231930348582991L;
    public String _commentContent;
    public long _createTime;
    private int _forbidReply;
    public int _gender;
    public long _id;
    public int _likeCount;
    public long _pid;
    public long _prid;
    public boolean _sourceCommentDeleted;
    public String _sourceContent;
    public long _sourceID;
    public String _sourceWriterName;
    public int _status;
    public long _writerAvatarID;
    public long _writerID;
    public String _writerName;
    public CommentSound commentSound;
    public String guideText;
    public int hide;
    public int liked;
    private ArrayList<ServerImage> mImages;
    private int mInnerCommentCount;
    private ArrayList<InnerComment> mInnerComments;
    private HashMap<Long, ServerVideo> mServerVideos;
    private ArrayList<ServerImage> mSourceImages;
    private HashMap<Long, ServerVideo> mSourceServerVideos;
    public boolean notifyHasImg;
    public boolean notifyHasSound;
    public boolean notifyHasVideo;
    public CommentSound sourceCommentSound;
    public long sourceMid;

    public Comment() {
        this._sourceCommentDeleted = false;
        this.notifyHasSound = false;
        this.notifyHasVideo = false;
        this.notifyHasImg = false;
        this.commentSound = null;
        this.sourceCommentSound = null;
        this.mServerVideos = new HashMap();
        this.mSourceServerVideos = new HashMap();
        this.mInnerComments = new ArrayList();
        this.guideText = "";
        this.hide = 0;
        this._id = 0;
        this._pid = 0;
        this._writerID = 0;
        this._writerName = "";
        this._writerAvatarID = 0;
        this._gender = 0;
        this._commentContent = "";
        this._createTime = 0;
        this._likeCount = 0;
        this._sourceID = 0;
        this.sourceMid = 0;
        this._sourceWriterName = "";
        this._sourceContent = "";
        this.liked = -1;
        this.hide = 0;
    }

    public Comment(JSONObject jSONObject) {
        this();
        if (jSONObject != null) {
            parseJSONObject(jSONObject);
        }
    }

    protected void parseJSONObject(JSONObject jSONObject) {
        boolean z;
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        Iterator it;
        ServerImage serverImage;
        JSONObject optJSONObject3;
        boolean z2 = true;
        int i = 0;
        this.notifyHasSound = jSONObject.optInt("has_sound") == 1;
        if (jSONObject.optInt("has_img") == 1) {
            z = true;
        } else {
            z = false;
        }
        this.notifyHasImg = z;
        if (jSONObject.optInt("has_video") == 1) {
            z = true;
        } else {
            z = false;
        }
        this.notifyHasVideo = z;
        this._id = jSONObject.optLong("id");
        this._writerID = jSONObject.optLong("mid");
        this._writerName = jSONObject.optString("mname");
        this._writerAvatarID = jSONObject.optLong("avatar");
        this._gender = jSONObject.optInt("gender");
        this._commentContent = jSONObject.optString("review");
        this._createTime = (long) jSONObject.optInt("ct");
        this._likeCount = jSONObject.optInt("likes");
        this._status = jSONObject.optInt(NotificationCompat.CATEGORY_STATUS);
        this._sourceID = jSONObject.optLong(SpeechConstant.IST_SESSION_ID);
        this._pid = jSONObject.optLong("pid");
        if (jSONObject.has("smid")) {
            this.sourceMid = jSONObject.optLong("smid");
        }
        if (jSONObject.has("click_guide")) {
            optJSONObject = jSONObject.optJSONObject("click_guide");
            if (optJSONObject != null) {
                this.guideText = optJSONObject.optString("text", "");
            }
        }
        this._sourceWriterName = jSONObject.optString("sname");
        this._sourceContent = jSONObject.optString("sreview");
        this.liked = jSONObject.optInt("liked", 0);
        this.hide = jSONObject.optInt("hide", 0);
        if (jSONObject.optInt("sdel_flag", 0) != 1) {
            z2 = false;
        }
        this._sourceCommentDeleted = z2;
        this.mImages = a(jSONObject, "imgs");
        if (this.mImages != null && this.mImages.size() > 0) {
            optJSONObject2 = jSONObject.optJSONObject("videos");
            if (optJSONObject2 != null) {
                it = this.mImages.iterator();
                while (it.hasNext()) {
                    serverImage = (ServerImage) it.next();
                    optJSONObject3 = optJSONObject2.optJSONObject(String.valueOf(serverImage.postImageId));
                    if (optJSONObject3 != null) {
                        this.mServerVideos.put(Long.valueOf(serverImage.postImageId), new ServerVideo(optJSONObject3));
                        serverImage.parseVideoJSONObject(optJSONObject3);
                    }
                }
            }
        }
        this.mSourceImages = a(jSONObject, "simgs");
        if (this.mSourceImages != null && this.mSourceImages.size() > 0) {
            optJSONObject2 = jSONObject.optJSONObject("svideos");
            if (optJSONObject2 != null) {
                it = this.mSourceImages.iterator();
                while (it.hasNext()) {
                    serverImage = (ServerImage) it.next();
                    optJSONObject3 = optJSONObject2.optJSONObject(String.valueOf(serverImage.postImageId));
                    if (optJSONObject3 != null) {
                        this.mSourceServerVideos.put(Long.valueOf(serverImage.postImageId), new ServerVideo(optJSONObject3));
                        serverImage.parseVideoJSONObject(optJSONObject3);
                    }
                }
            }
        }
        optJSONObject = jSONObject.optJSONObject("audio");
        if (optJSONObject != null) {
            this.commentSound = new CommentSound(optJSONObject);
        }
        optJSONObject = jSONObject.optJSONObject("saudio");
        if (optJSONObject != null) {
            this.sourceCommentSound = new CommentSound(optJSONObject);
        }
        this.mInnerCommentCount = jSONObject.optInt("subreviewcnt");
        JSONArray optJSONArray = jSONObject.optJSONArray("subreview");
        if (optJSONArray != null) {
            while (i < optJSONArray.length()) {
                optJSONObject2 = optJSONArray.optJSONObject(i);
                if (optJSONObject2 != null) {
                    this.mInnerComments.add(new InnerComment(optJSONObject2));
                }
                i++;
            }
        }
        this._prid = jSONObject.optLong("prid");
        this._forbidReply = jSONObject.optInt("disable_reply");
    }

    private ArrayList<ServerImage> a(JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        ArrayList<ServerImage> arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(new ServerImage(optJSONObject));
            }
        }
        return arrayList;
    }

    public JSONObject serializeTo() throws JSONException {
        int i;
        int i2 = 1;
        int i3 = 0;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("has_sound", this.notifyHasSound ? 1 : 0);
        String str = "has_img";
        if (this.notifyHasImg) {
            i = 1;
        } else {
            i = 0;
        }
        jSONObject.put(str, i);
        String str2 = "has_video";
        if (!this.notifyHasVideo) {
            i2 = 0;
        }
        jSONObject.put(str2, i2);
        jSONObject.put("id", this._id);
        jSONObject.put("mid", this._writerID);
        jSONObject.put("mname", this._writerName);
        jSONObject.put("avatar", this._writerAvatarID);
        jSONObject.put("gender", this._gender);
        jSONObject.put("review", this._commentContent);
        jSONObject.put("ct", this._createTime);
        jSONObject.put("likes", this._likeCount);
        jSONObject.put(SpeechConstant.IST_SESSION_ID, this._sourceID);
        jSONObject.put("smid", this.sourceMid);
        jSONObject.put("sname", this._sourceWriterName);
        jSONObject.put("sreview", this._sourceContent);
        jSONObject.put("liked", this.liked);
        jSONObject.put("hide", this.hide);
        jSONObject.put(NotificationCompat.CATEGORY_STATUS, this._status);
        jSONObject.put("prid", this._prid);
        jSONObject.put("disable_reply", this._forbidReply);
        jSONObject.put("pid", this._pid);
        if (!TextUtils.isEmpty(this.guideText)) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("text", this.guideText);
            jSONObject.put("click_guide", jSONObject2);
        }
        if (this.commentSound != null) {
            jSONObject.put("audio", this.commentSound.getJSONObject());
        }
        if (this.sourceCommentSound != null) {
            jSONObject.put("saudio", this.sourceCommentSound.getJSONObject());
        }
        a(jSONObject, "imgs", this.mImages);
        a(jSONObject, "simgs", this.mSourceImages);
        JSONObject jSONObject3 = new JSONObject();
        for (Entry entry : this.mServerVideos.entrySet()) {
            jSONObject3.put(String.valueOf(((Long) entry.getKey()).longValue()), ((ServerVideo) entry.getValue()).serializeTo());
        }
        jSONObject.put("videos", jSONObject3);
        jSONObject3 = new JSONObject();
        for (Entry entry2 : this.mSourceServerVideos.entrySet()) {
            jSONObject3.put(String.valueOf(((Long) entry2.getKey()).longValue()), ((ServerVideo) entry2.getValue()).serializeTo());
        }
        jSONObject.put("svideos", jSONObject3);
        jSONObject.put("subreviewcnt", this.mInnerCommentCount);
        i2 = this.mInnerComments.size();
        JSONArray jSONArray = new JSONArray();
        while (i2 > 0 && i3 < i2) {
            jSONArray.put(((InnerComment) this.mInnerComments.get(i3)).parse());
            i3++;
        }
        if (jSONArray.length() > 0) {
            jSONObject.put("subreview", jSONArray);
        }
        return jSONObject;
    }

    private void a(JSONObject jSONObject, String str, ArrayList<ServerImage> arrayList) throws JSONException {
        if (arrayList != null) {
            JSONArray jSONArray = new JSONArray();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put(((ServerImage) it.next()).serializeTo());
            }
            jSONObject.put(str, jSONArray);
        }
    }

    public boolean isSecondLevelComment() {
        return !TextUtils.isEmpty(this._sourceWriterName);
    }

    public boolean isInnerComment() {
        return this._prid > 0;
    }

    public boolean hasForbidReply() {
        return this._forbidReply == 1;
    }

    public a getAvatar() {
        return cn.xiaochuankeji.tieba.background.a.f().a(d.a(this._gender), this._writerAvatarID);
    }

    public ArrayList<ServerImage> getCommentImage() {
        return this.mImages;
    }

    public HashMap<Long, ServerVideo> getImgVideos() {
        return this.mServerVideos;
    }

    public ArrayList<ServerImage> getSourceCommentImage() {
        return this.mSourceImages;
    }

    public boolean hasSourceImage() {
        if (this.mSourceImages == null || this.mSourceImages.size() < 1) {
            return false;
        }
        return true;
    }

    public boolean hasImage() {
        if (this.mImages == null || this.mImages.size() < 1) {
            return false;
        }
        return true;
    }

    public long getFirstImgId() {
        if (this.mImages == null || this.mImages.size() == 0) {
            return 0;
        }
        return ((ServerImage) this.mImages.get(0)).postImageId;
    }

    public Member getWriterMember() {
        Member member = new Member(this._writerID);
        member.setAvatarID(this._writerAvatarID);
        member.setName(this._writerName);
        return member;
    }

    public int getSecondCommentCount() {
        return this.mInnerCommentCount;
    }

    public ArrayList<InnerComment> getSecondCommentList() {
        return this.mInnerComments;
    }

    public boolean isGod() {
        return this._status == 3;
    }

    public String toString() {
        return "Comment{_id=" + this._id + ", _pid=" + this._pid + ", _writerID=" + this._writerID + ", _writerName='" + this._writerName + '\'' + ", _writerAvatarID=" + this._writerAvatarID + ", _gender=" + this._gender + ", _commentContent='" + this._commentContent + '\'' + ", _createTime=" + this._createTime + ", _likeCount=" + this._likeCount + ", _sourceID=" + this._sourceID + ", sourceMid=" + this.sourceMid + ", _sourceWriterName='" + this._sourceWriterName + '\'' + ", _sourceContent='" + this._sourceContent + '\'' + ", liked=" + this.liked + ", hide=" + this.hide + ", _sourceCommentDeleted=" + this._sourceCommentDeleted + ", notifyHasSound=" + this.notifyHasSound + ", notifyHasVideo=" + this.notifyHasVideo + ", notifyHasImg=" + this.notifyHasImg + ", commentSound=" + this.commentSound + ", sourceCommentSound=" + this.sourceCommentSound + ", mImages=" + this.mImages + ", mSourceImages=" + this.mSourceImages + ", mServerVideos=" + this.mServerVideos + ", mSourceServerVideos=" + this.mSourceServerVideos + ", mInnerCommentCount=" + this.mInnerCommentCount + ", mInnerComments=" + this.mInnerComments + ", _prid=" + this._prid + ", _forbidReply=" + this._forbidReply + '}';
    }

    public boolean isHide() {
        return this.hide == 1;
    }

    public void copyLink() {
        d.a("#最右#请你围观一条神奇的评论，不好看算我输。请戳链接>>" + cn.xiaochuankeji.tieba.background.utils.d.a.a(this._pid, this._id) + "?zy_to=applink&to=applink");
        g.a("复制成功");
    }
}
