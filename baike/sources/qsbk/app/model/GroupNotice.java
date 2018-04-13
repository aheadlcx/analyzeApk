package qsbk.app.model;

import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GroupNotice extends QbBean {
    public static final int INVALID_ID = -1;
    public int act;
    public String detail;
    public int gid;
    public String handler;
    public int id;
    public int iid;
    public JSONObject json;
    public String msgid;
    public String reason;
    public ArrayList<GroupBriefInfo> recommendTribes;
    public int state;
    public long time;
    public String title;
    public GroupBriefInfo tribe;
    public int type;
    public BaseUserInfo user;

    public GroupNotice(long j) {
        this(-1, 4, j);
    }

    public GroupNotice(int i, int i2, long j) {
        this.id = i;
        this.state = i2;
        this.time = j;
    }

    public boolean isActionNotice() {
        return this.type == 1 || this.type == 2 || this.type == 4;
    }

    public void parse(String str, String str2) {
        try {
            a(new JSONObject(str), str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void a(JSONObject jSONObject, String str) {
        this.msgid = str;
        this.json = jSONObject;
        this.type = jSONObject.optInt("type");
        this.gid = jSONObject.optInt("gid");
        this.title = jSONObject.optString("title");
        JSONObject optJSONObject = jSONObject.optJSONObject(Laisee.TYPE_TRIBE);
        if (optJSONObject != null) {
            this.tribe = new GroupBriefInfo(optJSONObject);
            this.handler = optJSONObject.optString("owner");
            this.gid = this.tribe.id;
        }
        optJSONObject = jSONObject.optJSONObject("user");
        if (optJSONObject != null) {
            this.user = new BaseUserInfo();
            this.user.parseBaseInfo(optJSONObject);
            this.iid = optJSONObject.optInt("iid", -1);
        } else {
            this.iid = -1;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        this.recommendTribes = new ArrayList();
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    this.recommendTribes.add(new GroupBriefInfo(optJSONArray.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        this.detail = jSONObject.optString("detail");
        this.reason = jSONObject.optString("reason");
        this.act = jSONObject.optInt(SocialConstants.PARAM_ACT, 0);
        if (this.type == 12 && this.user == null) {
            this.type = 13;
        }
        if (this.type == 12) {
            this.act = -1;
        }
    }
}
