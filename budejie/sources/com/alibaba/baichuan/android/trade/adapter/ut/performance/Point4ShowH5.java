package com.alibaba.baichuan.android.trade.adapter.ut.performance;

import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.utils.a;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import com.alibaba.mtl.appmonitor.model.MeasureSet;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;

public class Point4ShowH5 extends PagePerformancePoint {
    private static final String c = Point4ShowH5.class.getSimpleName();
    public long allTime;
    public long analysisTime;
    public long taokeTime;
    public long urlHandleTime;
    public long urlLoadTime;

    public static MeasureSet getMeasureSet() {
        return MeasureSet.create().addMeasure(UserTrackerConstants.PM_ANALYSIS_TIME).addMeasure(UserTrackerConstants.PM_TAOKE_TIME).addMeasure(UserTrackerConstants.PM_URL_HANDLE_TIME).addMeasure(UserTrackerConstants.PM_URL_LOAD_TIME).addMeasure(UserTrackerConstants.PM_ALL_TIME);
    }

    public boolean checkData() {
        return checkTime(this.analysisTime) && checkTime(this.taokeTime) && checkTime(this.urlHandleTime) && checkTime(this.urlLoadTime) && checkTime(this.allTime);
    }

    public MeasureValueSet getMeasureValues() {
        return MeasureValueSet.create().setValue(UserTrackerConstants.PM_ANALYSIS_TIME, (double) this.analysisTime).setValue(UserTrackerConstants.PM_TAOKE_TIME, (double) this.taokeTime).setValue(UserTrackerConstants.PM_URL_HANDLE_TIME, (double) this.urlHandleTime).setValue(UserTrackerConstants.PM_URL_LOAD_TIME, (double) this.urlLoadTime).setValue(UserTrackerConstants.PM_ALL_TIME, (double) this.allTime);
    }

    public String getMonitorPoint() {
        return UserTrackerConstants.P_SHOWH5;
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
                    obj = 4;
                    break;
                }
                break;
            case -738921630:
                if (str.equals(UserTrackerConstants.PM_URL_LOAD_TIME)) {
                    obj = 3;
                    break;
                }
                break;
            case 750896100:
                if (str.equals(UserTrackerConstants.PM_URL_HANDLE_TIME)) {
                    obj = 2;
                    break;
                }
                break;
            case 930452841:
                if (str.equals(UserTrackerConstants.PM_TAOKE_TIME)) {
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
                this.taokeTime = System.currentTimeMillis();
                return;
            case 2:
                this.urlHandleTime = System.currentTimeMillis();
                return;
            case 3:
                this.urlLoadTime = System.currentTimeMillis();
                return;
            case 4:
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
                    obj = 4;
                    break;
                }
                break;
            case -738921630:
                if (str.equals(UserTrackerConstants.PM_URL_LOAD_TIME)) {
                    obj = 3;
                    break;
                }
                break;
            case 750896100:
                if (str.equals(UserTrackerConstants.PM_URL_HANDLE_TIME)) {
                    obj = 2;
                    break;
                }
                break;
            case 930452841:
                if (str.equals(UserTrackerConstants.PM_TAOKE_TIME)) {
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
                this.taokeTime = System.currentTimeMillis() - this.taokeTime;
                return;
            case 2:
                this.urlHandleTime = System.currentTimeMillis() - this.urlHandleTime;
                return;
            case 3:
                this.urlLoadTime = System.currentTimeMillis() - this.urlLoadTime;
                return;
            case 4:
                this.allTime = System.currentTimeMillis() - this.allTime;
                return;
            default:
                a.a(c, "timeEnd", "type is not right");
                AlibcLogger.e(c, "timeEnd:type is not right");
                return;
        }
    }

    public String toString() {
        return "Point4ShowH5{analysisTime=" + this.analysisTime + ", taokeTime=" + this.taokeTime + ", urlHandleTime=" + this.urlHandleTime + ", urlLoadTime=" + this.urlLoadTime + ", allTime=" + this.allTime + '}';
    }
}
