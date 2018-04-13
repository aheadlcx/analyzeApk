package qsbk.app.im.group.vote.bean;

import org.json.JSONArray;

public class DayActivityInfo {
    public int mActivities;
    public int mTimeStamp;

    public DayActivityInfo(int i, int i2) {
        this.mTimeStamp = i;
        this.mActivities = i2;
    }

    public DayActivityInfo(JSONArray jSONArray) {
        parseJsonArray(jSONArray);
    }

    public void parseJsonArray(JSONArray jSONArray) {
        if (jSONArray != null) {
            this.mTimeStamp = jSONArray.optInt(0);
            this.mActivities = jSONArray.optInt(1);
        }
    }

    public void setDayActivity(int i) {
        this.mActivities = i;
    }

    public long getTimeChamp() {
        return (long) this.mTimeStamp;
    }

    public void setTimeChamp(int i) {
        this.mTimeStamp = i;
    }

    public int getDayActivities() {
        return this.mActivities;
    }
}
