package cn.xiaochuankeji.tieba.background.danmaku;

import cn.xiaochuankeji.tieba.background.beans.Member;
import com.iflytek.cloud.SpeechConstant;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class DanmakuItem implements Serializable {
    private static final long serialVersionUID = 52962727288685773L;
    public long createTime;
    public boolean hasSound;
    public long id;
    public boolean isSound;
    public boolean isTop;
    public int liked;
    public int liken;
    public int likes;
    public Member member;
    public long pid;
    public int pos;
    public String screenshotThumbUrl;
    public String screenshotUrl;
    public boolean showLikes;
    public long soundDuration;
    public String soundUrl;
    public String text;
    public long vid;
    public int videoIndex;

    public static DanmakuItem fromJson(JSONObject jSONObject) {
        boolean z;
        boolean z2 = true;
        DanmakuItem danmakuItem = new DanmakuItem();
        danmakuItem.id = jSONObject.optLong("id");
        danmakuItem.pid = jSONObject.optLong("pid");
        danmakuItem.vid = jSONObject.optLong(SpeechConstant.ISV_VID);
        danmakuItem.member = new Member(jSONObject.optJSONObject("member"));
        danmakuItem.videoIndex = jSONObject.optInt(Parameters.PACKAGE_NAME);
        danmakuItem.text = jSONObject.optString("text");
        danmakuItem.hasSound = false;
        danmakuItem.soundUrl = jSONObject.optString("sound");
        danmakuItem.pos = jSONObject.optInt("snaptime");
        danmakuItem.screenshotUrl = jSONObject.optString("snapimg");
        danmakuItem.screenshotThumbUrl = jSONObject.optString("snapthum");
        danmakuItem.createTime = jSONObject.optLong("ct");
        danmakuItem.likes = jSONObject.optInt("likes");
        danmakuItem.liked = jSONObject.optInt("liked");
        danmakuItem.liken = jSONObject.optInt("liken");
        if (jSONObject.optInt("showlike") != 0) {
            z = true;
        } else {
            z = false;
        }
        danmakuItem.showLikes = z;
        if (jSONObject.optInt("isgod") == 1) {
            z = true;
        } else {
            z = false;
        }
        danmakuItem.isTop = z;
        danmakuItem.soundDuration = (long) jSONObject.optInt("dur");
        if (jSONObject.optInt("issound") != 1) {
            z2 = false;
        }
        danmakuItem.isSound = z2;
        return danmakuItem;
    }

    public static ArrayList<DanmakuItem> fromJsonArray(JSONArray jSONArray) {
        ArrayList<DanmakuItem> arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    arrayList.add(fromJson(optJSONObject));
                }
            }
        }
        return arrayList;
    }

    public boolean equals(Object obj) {
        if ((obj instanceof DanmakuItem) && this.id == ((DanmakuItem) obj).id) {
            return true;
        }
        return false;
    }
}
