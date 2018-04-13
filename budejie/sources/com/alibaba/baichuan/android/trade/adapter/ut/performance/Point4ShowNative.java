package com.alibaba.baichuan.android.trade.adapter.ut.performance;

import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.utils.a;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import com.alibaba.mtl.appmonitor.model.MeasureSet;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;

public class Point4ShowNative extends PagePerformancePoint {
    private static final String c = Point4ShowNative.class.getSimpleName();
    public long allTime;
    public long analysisTime;
    public long goTaobaoTime;

    public static MeasureSet getMeasureSet() {
        return MeasureSet.create().addMeasure(UserTrackerConstants.PM_ANALYSIS_TIME).addMeasure(UserTrackerConstants.PM_GO_TAOBAO_TIME).addMeasure(UserTrackerConstants.PM_ALL_TIME);
    }

    public boolean checkData() {
        return checkTime(this.analysisTime) && checkTime(this.goTaobaoTime) && checkTime(this.allTime);
    }

    public MeasureValueSet getMeasureValues() {
        return MeasureValueSet.create().setValue(UserTrackerConstants.PM_ANALYSIS_TIME, (double) this.analysisTime).setValue(UserTrackerConstants.PM_GO_TAOBAO_TIME, (double) this.goTaobaoTime).setValue(UserTrackerConstants.PM_ALL_TIME, (double) this.allTime);
    }

    public String getMonitorPoint() {
        return UserTrackerConstants.P_SHOWNATIVE;
    }

    public void timeBegin(String str) {
        if (str == null) {
            a.a(c, "timeBegin", "type is null");
            AlibcLogger.e(c, "timeBegin:type is null");
            return;
        }
        Object obj = -1;
        switch (str.hashCode()) {
            case -1748292663:
                if (str.equals(UserTrackerConstants.PM_ANALYSIS_TIME)) {
                    obj = null;
                    break;
                }
                break;
            case -912160754:
                if (str.equals(UserTrackerConstants.PM_ALL_TIME)) {
                    obj = 2;
                    break;
                }
                break;
            case -197564381:
                if (str.equals(UserTrackerConstants.PM_GO_TAOBAO_TIME)) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                this.analysisTime = System.currentTimeMillis();
                return;
            case 1:
                this.goTaobaoTime = System.currentTimeMillis();
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
            case -1748292663:
                if (str.equals(UserTrackerConstants.PM_ANALYSIS_TIME)) {
                    obj = null;
                    break;
                }
                break;
            case -912160754:
                if (str.equals(UserTrackerConstants.PM_ALL_TIME)) {
                    obj = 2;
                    break;
                }
                break;
            case -197564381:
                if (str.equals(UserTrackerConstants.PM_GO_TAOBAO_TIME)) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                this.analysisTime = System.currentTimeMillis() - this.analysisTime;
                return;
            case 1:
                this.goTaobaoTime = System.currentTimeMillis() - this.goTaobaoTime;
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
        return "Point4ShowNative{analysisTime=" + this.analysisTime + ", goTaobaoTime=" + this.goTaobaoTime + ", allTime=" + this.allTime + '}';
    }
}
