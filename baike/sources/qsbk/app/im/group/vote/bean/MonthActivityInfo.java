package qsbk.app.im.group.vote.bean;

import android.content.Context;
import java.util.ArrayList;

public class MonthActivityInfo {
    public ArrayList<DayActivityInfo> mDayActivityInfos = new ArrayList();
    public int mMonth;

    public MonthActivityInfo(Context context) {
    }

    public MonthActivityInfo(int i) {
        this.mMonth = i;
    }

    public MonthActivityInfo(int i, ArrayList<DayActivityInfo> arrayList) {
        this.mMonth = i;
        this.mDayActivityInfos = arrayList;
    }

    public void appendDayData(DayActivityInfo dayActivityInfo) {
        this.mDayActivityInfos.add(dayActivityInfo);
    }

    public int getmMonth() {
        return this.mMonth;
    }

    public void setmMonth(int i) {
        this.mMonth = i;
    }

    public ArrayList<DayActivityInfo> getmMonthActivityInfos() {
        return this.mDayActivityInfos;
    }

    public void setmMonthActivityInfos(ArrayList<DayActivityInfo> arrayList) {
        this.mDayActivityInfos = arrayList;
    }
}
