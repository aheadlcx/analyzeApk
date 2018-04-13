package qsbk.app.im;

import android.text.TextUtils;
import com.baidu.mobstat.Config;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.model.ChatMsgValueObj;
import qsbk.app.nearby.api.NearbyUser;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.json.JSONAble;

public class IMChatMsgSource extends JSONAble {
    public static final int FROM_ARTICLE = 2;
    public static final int FROM_CAFE = 4;
    public static final int FROM_COMMENT = 3;
    public static final int FROM_GROUP = 7;
    public static final int FROM_LIVE = 9;
    public static final int FROM_NEARBY = 1;
    public static final int FROM_QIUYOUCIRCLE = 8;
    public static final int FROM_RANDOM_DOOR = 6;
    public static final int FROM_SEARCH = 5;
    public static final String SOURCE = "source";
    public String from_id;
    public String to_id;
    public int type;
    public ChatMsgValueObj valueObj = new ChatMsgValueObj();

    public IMChatMsgSource(int i, String str, String str2) {
        this.type = i;
        this.to_id = str;
        this.from_id = QsbkApp.currentUser == null ? "" : QsbkApp.currentUser.userId;
        if (this.type == 1) {
            try {
                String[] split = str2.split(Config.TRACE_TODAY_VISIT_SPLIT);
                this.valueObj.distance = Integer.parseInt(split[0]);
                this.valueObj.haunt = split[1];
            } catch (Exception e) {
                this.valueObj.distance = 0;
                this.valueObj.haunt = "未知城市";
                e.printStackTrace();
            }
        } else if (this.type == 2 || this.type == 3) {
            this.valueObj.artid = str2;
        } else if (this.type == 7) {
            int indexOf = str2.indexOf(58);
            if (indexOf <= 0 || indexOf >= str2.length()) {
                this.valueObj.group_id = "";
                this.valueObj.group_name = str2;
                return;
            }
            this.valueObj.group_id = str2.substring(0, indexOf);
            this.valueObj.group_name = str2.substring(indexOf + 1);
        } else if (this.type != 9) {
        }
    }

    public static IMChatMsgSource getMsgSourceFromChatMsg(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                IMChatMsgSource iMChatMsgSource = new IMChatMsgSource();
                iMChatMsgSource.parseFromJSONObject(jSONObject);
                return iMChatMsgSource;
            } catch (Exception e) {
                LogUtil.d("get msg source from local fail");
                e.printStackTrace();
            }
        }
        return null;
    }

    public void parseFromJSONObject(JSONObject jSONObject) {
        JSONObject jSONObject2;
        super.parseFromJSONObject(jSONObject);
        try {
            jSONObject2 = new JSONObject(jSONObject.optString("value"));
        } catch (Exception e) {
            e.printStackTrace();
            jSONObject2 = null;
        }
        this.valueObj.parseFromJSONObject(jSONObject2);
    }

    public JSONObject encodeToJsonObject() {
        JSONObject encodeToJsonObject = super.encodeToJsonObject();
        try {
            encodeToJsonObject.putOpt("value", this.valueObj.encodeToJsonObject().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeToJsonObject;
    }

    public String getPresentText() {
        if (this.type == 1) {
            return "来自 附近 " + NearbyUser.getDistanceStr(this.valueObj.distance);
        }
        if (this.type == 2) {
            return String.format("来自 糗事%s >", new Object[]{this.valueObj.artid});
        } else if (this.type == 3) {
            return String.format("来自 糗事评论%s >", new Object[]{this.valueObj.artid});
        } else if (this.type == 5) {
            return "来自昵称搜索";
        } else {
            if (this.type == 4) {
                return "来自里屋";
            }
            if (this.type == 6) {
                return "来自任意门";
            }
            if (this.type == 7 && this.valueObj.group_name != null) {
                return String.format("来自群 %s", new Object[]{this.valueObj.group_name});
            } else if (this.type == 9) {
                return "来自直播";
            } else {
                return null;
            }
        }
    }
}
