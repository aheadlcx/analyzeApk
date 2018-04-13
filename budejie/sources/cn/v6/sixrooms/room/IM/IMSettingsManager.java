package cn.v6.sixrooms.room.IM;

import cn.v6.sixrooms.utils.JsonParseUtils;
import com.tencent.open.wpa.WPA;
import org.json.JSONException;
import org.json.JSONObject;

public class IMSettingsManager extends IMBaseManager {
    private static volatile IMSettingsManager a;
    private int b = 1;
    private int c = 1;
    private int d;
    private int e;
    private int f;

    public static IMSettingsManager getInstance() {
        if (a == null) {
            synchronized (IMSettingsManager.class) {
                if (a == null) {
                    a = new IMSettingsManager();
                }
            }
        }
        return a;
    }

    public boolean getFriend() {
        return this.b != 1;
    }

    public void setFriend(int i) {
        this.b = i;
    }

    public boolean getHid() {
        return this.c != 1;
    }

    public void setHid(int i) {
        this.c = i;
    }

    public int getSysmsg() {
        return this.d;
    }

    public void setSysmsg(int i) {
        this.d = i;
    }

    public boolean getGroup() {
        return this.e != 1;
    }

    public void setGroup(int i) {
        this.e = i;
    }

    public int getSound() {
        return this.f;
    }

    public void setSound(int i) {
        this.f = i;
    }

    public void setIMSettings(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.b = JsonParseUtils.getInt(jSONObject, "friend");
            this.c = JsonParseUtils.getInt(jSONObject, "hid");
            this.d = JsonParseUtils.getInt(jSONObject, "sysmsg");
            this.e = JsonParseUtils.getInt(jSONObject, WPA.CHAT_TYPE_GROUP);
            this.f = JsonParseUtils.getInt(jSONObject, "sound");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setIMSettingsFromLoginInfo(String str) {
        try {
            setIMSettings(JsonParseUtils.getString(new JSONObject(str), "iminfo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        a = null;
    }

    public void clearAll() {
    }
}
