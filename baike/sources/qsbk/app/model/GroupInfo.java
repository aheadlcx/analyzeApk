package qsbk.app.model;

import android.text.TextUtils;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.xiaomi.mipush.sdk.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.utils.json.JsonPrivate;

public class GroupInfo extends QbBean implements Serializable {
    public static final int APPLIED = 1;
    public static final int JOINED = 2;
    public static final int NOT_JOIN = 0;
    public boolean autoIn;
    public int createdAt;
    public int creatorId;
    @JsonPrivate
    public long dbid;
    public String description;
    public int distance;
    public String icon;
    public int iconUpdatedAt;
    public int id;
    public boolean isOwner;
    public int joinStatus;
    public int joinedGroup;
    public String latitude;
    public int leftODay;
    public int leftOMember;
    public int level;
    public int limitMember;
    public String location;
    public String longitude;
    public ArrayList<GroupInfo$MemberInfo> memberList;
    public int memberNum;
    public String name;
    public boolean notifySwitch;
    public int ownedAt;
    public int ownedTerm;
    public int ownerId;
    public ArrayList<GroupInfo$PicInfo> picList;
    public int rank;
    public int right;
    public int status;
    public int statusUpdatedAt;
    public boolean titleEnable;
    public String[] titles;

    public GroupInfo() {
        this.joinedGroup = 0;
        this.memberList = new ArrayList(0);
        this.picList = new ArrayList(0);
    }

    public GroupInfo(JSONObject jSONObject) {
        this.joinedGroup = 0;
        a(jSONObject);
    }

    public GroupInfo(GroupBriefInfo groupBriefInfo) {
        this();
        this.id = groupBriefInfo.id;
        this.status = groupBriefInfo.status;
        this.isOwner = groupBriefInfo.isOwner;
        this.description = groupBriefInfo.description;
        this.level = groupBriefInfo.level;
        this.memberNum = groupBriefInfo.memberNum;
        this.statusUpdatedAt = groupBriefInfo.statusUpdatedAt;
        this.distance = groupBriefInfo.distance;
        this.limitMember = groupBriefInfo.limitMember;
        this.joinStatus = groupBriefInfo.joinStatus;
        this.name = groupBriefInfo.name;
        this.icon = groupBriefInfo.icon;
        this.location = groupBriefInfo.location;
        this.right = groupBriefInfo.right;
        this.rank = groupBriefInfo.rank;
    }

    private void a(JSONObject jSONObject) {
        boolean z = true;
        if (jSONObject != null) {
            try {
                int i;
                this.id = jSONObject.getInt("id");
                this.creatorId = jSONObject.optInt("creator_id");
                this.ownerId = jSONObject.optInt("owner_id");
                this.ownedAt = jSONObject.optInt("owner_at");
                this.ownedTerm = jSONObject.optInt("owned_term");
                this.icon = jSONObject.getString("icon");
                if (!(this.icon == null || this.icon.endsWith("/format/webp"))) {
                    this.icon += "/format/webp";
                }
                this.iconUpdatedAt = jSONObject.optInt("icon_updated_at");
                this.name = jSONObject.getString("name");
                this.description = jSONObject.optString("description");
                this.location = jSONObject.optString("location");
                this.longitude = jSONObject.optString(ParamKey.LONGITUDE);
                this.latitude = jSONObject.optString(ParamKey.LATITUDE);
                this.level = jSONObject.optInt("level");
                this.status = jSONObject.optInt("status");
                this.statusUpdatedAt = jSONObject.optInt("status_updated_at");
                this.createdAt = jSONObject.optInt(QsbkDatabase.CREATE_AT);
                this.limitMember = jSONObject.optInt("limit_member");
                this.leftODay = jSONObject.optInt("left_oday");
                this.distance = jSONObject.optInt("distance");
                JSONArray optJSONArray = jSONObject.optJSONArray("member_list");
                this.memberList = new ArrayList();
                if (optJSONArray != null) {
                    for (i = 0; i < optJSONArray.length(); i++) {
                        this.memberList.add(new GroupInfo$MemberInfo(optJSONArray.getJSONObject(i)));
                    }
                }
                optJSONArray = jSONObject.optJSONArray("pic_list");
                this.picList = new ArrayList();
                if (optJSONArray != null) {
                    for (i = 0; i < optJSONArray.length(); i++) {
                        this.picList.add(new GroupInfo$PicInfo(optJSONArray.getJSONObject(i)));
                    }
                }
                this.autoIn = jSONObject.optBoolean("auto_in");
                this.notifySwitch = jSONObject.optInt("switch", 1) != 0;
                this.joinStatus = jSONObject.optInt("join_status");
                this.memberNum = jSONObject.optInt("member_num");
                this.isOwner = jSONObject.optBoolean("is_owner");
                this.leftOMember = jSONObject.optInt("left_omember");
                this.rank = jSONObject.optInt("rank", -1);
                this.right = jSONObject.optInt("right", 0);
                if (jSONObject.optInt("title_sw") == 0) {
                    z = false;
                }
                this.titleEnable = z;
                Object optString = jSONObject.optString("title");
                if (!TextUtils.isEmpty(optString)) {
                    this.titles = optString.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    if (this.titles.length != 6) {
                        this.titles = null;
                    }
                }
                if (this.titles == null) {
                    this.titles = new String[]{"天王", "地虎", "小鸡", "蘑菇", "宝塔", "河妖"};
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String[] getTitlesIfEnable() {
        return this.titleEnable ? this.titles : null;
    }
}
