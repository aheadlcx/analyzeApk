package qsbk.app.im.group.vote.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONObject;

public class GroupManagerInfo {
    public String mGroupIconUrl;
    public int mGroupId;
    public String mGroupManagerOrder;
    public String mGroupManagerRetireDate;
    public int mGroupManagerRetireMemberNum;
    public String mGroupManagerTakeDate;
    public int mGroupManagerTakeMemberNum;
    public String mGroupName;
    public int mId;
    public int mUserId;

    public GroupManagerInfo(JSONObject jSONObject) {
        parseJsonObject(jSONObject);
    }

    public void parseJsonObject(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                this.mUserId = jSONObject.optInt("user_id");
                this.mGroupManagerOrder = jSONObject.optString("team_num");
                this.mGroupName = jSONObject.optString("t_name");
                long optInt = ((long) jSONObject.optInt("up_date")) * 1000;
                Date date = new Date(optInt);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                if (optInt > 0) {
                    this.mGroupManagerTakeDate = simpleDateFormat.format(date);
                } else {
                    this.mGroupManagerTakeDate = "--";
                }
                this.mGroupManagerTakeMemberNum = jSONObject.optInt("up_member");
                if (jSONObject.has("down_date")) {
                    optInt = ((long) jSONObject.optInt("down_date")) * 1000;
                    if (optInt > 0) {
                        date.setTime(optInt);
                        this.mGroupManagerRetireDate = simpleDateFormat.format(date);
                    } else {
                        this.mGroupManagerRetireDate = "--";
                    }
                    this.mGroupManagerRetireMemberNum = jSONObject.optInt("down_member");
                } else {
                    this.mGroupManagerRetireDate = null;
                }
                this.mGroupId = jSONObject.optInt("tribe_id");
                this.mId = jSONObject.optInt("id");
                this.mGroupIconUrl = jSONObject.getString("t_icon");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
