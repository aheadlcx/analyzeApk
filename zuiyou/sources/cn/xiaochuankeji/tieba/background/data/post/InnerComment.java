package cn.xiaochuankeji.tieba.background.data.post;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class InnerComment implements Serializable {
    private static final String S_KEY_MID = "mid";
    private static final String S_KEY_NAME = "mname";
    private static final String S_KEY_REVIEW = "review";
    private static final String S_KEY_RID = "rid";
    private static final String S_KEY_SID = "sid";
    private static final String S_KEY_SNAME = "sname";
    protected static final long serialVersionUID = 5461222630348582991L;
    private long mMid;
    private String mName;
    private String mReview;
    private long mRid;
    private long mSid;
    private String sName;

    public InnerComment(JSONObject jSONObject) {
        this.mName = jSONObject.optString(S_KEY_NAME);
        this.mReview = jSONObject.optString(S_KEY_REVIEW);
        this.sName = jSONObject.optString(S_KEY_SNAME);
        this.mMid = jSONObject.optLong(S_KEY_MID);
        this.mRid = jSONObject.optLong(S_KEY_RID);
        this.mSid = jSONObject.optLong("sid");
    }

    public JSONObject parse() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(S_KEY_NAME, this.mName);
            jSONObject.put(S_KEY_REVIEW, this.mReview);
            jSONObject.put(S_KEY_MID, this.mMid);
            jSONObject.put(S_KEY_RID, this.mRid);
            jSONObject.put(S_KEY_SNAME, this.sName);
            jSONObject.put("sid", this.mSid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String getName() {
        return this.mName;
    }

    public String getSourceName() {
        return this.sName;
    }

    public String getContent() {
        return this.mReview;
    }

    public long getMid() {
        return this.mMid;
    }

    public long getSid() {
        return this.mSid;
    }
}
