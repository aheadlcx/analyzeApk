package com.alibaba.baichuan.android.trade.adapter.ut.performance;

import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.utils.a;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import com.alibaba.mtl.appmonitor.model.MeasureSet;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;

public class Point4Init extends PerformancePoint {
    private static final String c = Point4Init.class.getSimpleName();
    public long allTime;
    public long securityInitTime;
    public long utInitTime;

    public static MeasureSet getMeasureSet() {
        return MeasureSet.create().addMeasure(UserTrackerConstants.PM_SECURITY_INIT_TIME).addMeasure(UserTrackerConstants.PM_UT_INIT_TIME).addMeasure(UserTrackerConstants.PM_ALL_TIME);
    }

    public boolean checkData() {
        return checkTime(this.securityInitTime) && checkTime(this.utInitTime) && checkTime(this.allTime);
    }

    public MeasureValueSet getMeasureValues() {
        return MeasureValueSet.create().setValue(UserTrackerConstants.PM_SECURITY_INIT_TIME, (double) this.securityInitTime).setValue(UserTrackerConstants.PM_UT_INIT_TIME, (double) this.utInitTime).setValue(UserTrackerConstants.PM_ALL_TIME, (double) this.allTime);
    }

    public String getMonitorPoint() {
        return UserTrackerConstants.P_INIT;
    }

    public void timeBegin(String str) {
        if (str == null) {
            a.a(c, "timeBegin", "type is null");
            AlibcLogger.e(c, "timeBegin:type is null");
            return;
        }
        Object obj = -1;
        switch (str.hashCode()) {
            case -912160754:
                if (str.equals(UserTrackerConstants.PM_ALL_TIME)) {
                    obj = 2;
                    break;
                }
                break;
            case -395475012:
                if (str.equals(UserTrackerConstants.PM_UT_INIT_TIME)) {
                    obj = 1;
                    break;
                }
                break;
            case 2065809245:
                if (str.equals(UserTrackerConstants.PM_SECURITY_INIT_TIME)) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                this.securityInitTime = System.currentTimeMillis();
                return;
            case 1:
                this.utInitTime = System.currentTimeMillis();
                return;
            case 2:
                this.allTime = System.currentTimeMillis();
                return;
            default:
                a.a(c, "timeBegin", "type is not right");
                AlibcLogger.e(c, "timeBegin:type is not right");
                return;
        }
    }

    public void timeEnd(String str) {
        if (str == null) {
            a.a(c, "timeEnd", "type is null");
            AlibcLogger.e(c, "timeEnd:type is null");
            return;
        }
        Object obj = -1;
        switch (str.hashCode()) {
            case -912160754:
                if (str.equals(UserTrackerConstants.PM_ALL_TIME)) {
                    obj = 2;
                    break;
                }
                break;
            case -395475012:
                if (str.equals(UserTrackerConstants.PM_UT_INIT_TIME)) {
                    obj = 1;
                    break;
                }
                break;
            case 2065809245:
                if (str.equals(UserTrackerConstants.PM_SECURITY_INIT_TIME)) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                this.securityInitTime = System.currentTimeMillis() - this.securityInitTime;
                return;
            case 1:
                this.utInitTime = System.currentTimeMillis() - this.utInitTime;
                return;
            case 2:
                this.allTime = System.currentTimeMillis() - this.allTime;
                return;
            default:
                a.a(c, "timeEnd", "type is not right");
                AlibcLogger.e(c, "timeEnd:type is not right");
                return;
        }
    }

    public String toString() {
        return "Point4Init{securityInitTime=" + this.securityInitTime + ", utInitTime=" + this.utInitTime + '}';
    }
}
