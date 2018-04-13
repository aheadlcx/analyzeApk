package qsbk.app.model;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.im.TimeUtils;
import qsbk.app.utils.DateUtil;

public class LaiseeRecord {
    public String icon;
    public int lucky;
    public String money;
    public String time;
    public String userId;
    public String userName;
    public int voiceDuration;
    public String voiceUrl;

    public static LaiseeRecord parse(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        LaiseeRecord laiseeRecord = new LaiseeRecord();
        laiseeRecord.userId = jSONObject.optString("user_id");
        laiseeRecord.userName = jSONObject.optString(QsbkDatabase.LOGIN);
        laiseeRecord.icon = jSONObject.optString("icon");
        laiseeRecord.time = jSONObject.optString(QsbkDatabase.CREATE_AT);
        laiseeRecord.money = jSONObject.optString(PayPWDUniversalActivity.MONEY);
        laiseeRecord.lucky = jSONObject.optInt("lucky");
        laiseeRecord.voiceDuration = jSONObject.optInt("voice_duration");
        laiseeRecord.voiceUrl = jSONObject.optString("voice_url");
        return laiseeRecord;
    }

    public static ArrayList<LaiseeRecord> paseJsonArray(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        ArrayList<LaiseeRecord> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                arrayList.add(parse(jSONArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public boolean isLucky() {
        return this.lucky == 1;
    }

    public boolean isVoiceRecord() {
        return !TextUtils.isEmpty(this.voiceUrl);
    }

    public String getTimeString() {
        StringBuilder stringBuilder = new StringBuilder();
        long j = 0;
        try {
            j = Long.parseLong(this.time) * 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        if (TimeUtils.isSameDay(Calendar.getInstance(), instance)) {
            return DateUtil.getHHMM(instance);
        }
        return DateUtil.getMMddHHmm(instance);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LaiseeRecord laiseeRecord = (LaiseeRecord) obj;
        if (this.userId != null) {
            if (!this.userId.equals(laiseeRecord.userId)) {
                return false;
            }
        } else if (laiseeRecord.userId != null) {
            return false;
        }
        if (this.userName != null) {
            if (!this.userName.equals(laiseeRecord.userName)) {
                return false;
            }
        } else if (laiseeRecord.userName != null) {
            return false;
        }
        if (this.icon != null) {
            if (!this.icon.equals(laiseeRecord.icon)) {
                return false;
            }
        } else if (laiseeRecord.icon != null) {
            return false;
        }
        if (this.time != null) {
            if (!this.time.equals(laiseeRecord.time)) {
                return false;
            }
        } else if (laiseeRecord.time != null) {
            return false;
        }
        if (this.money != null) {
            z = this.money.equals(laiseeRecord.money);
        } else if (laiseeRecord.money != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = (this.userId != null ? this.userId.hashCode() : 0) * 31;
        if (this.userName != null) {
            hashCode = this.userName.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.icon != null) {
            hashCode = this.icon.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.time != null) {
            hashCode = this.time.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.money != null) {
            i = this.money.hashCode();
        }
        return hashCode + i;
    }
}
