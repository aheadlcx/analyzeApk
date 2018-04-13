package com.jlzx.comment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.umeng.onlineconfig.OnlineConfigAgent;

class PopStrategyImp extends PopStrategy {
    private static final String CompleteKey = "Completed";
    private static final String RejectKey = "rejectCount";
    private static final String StartTotalKey = "StartTotalCount";
    private Context appcontext = null;
    private int hasCompleted = 0;
    private int rejectCount = 0;
    private int startTotalCount = 0;
    private int thresholdNum = 7;

    public PopStrategyImp(Context context, int i) {
        this.appcontext = context.getApplicationContext();
        this.thresholdNum = i;
        ReadData(context);
    }

    public boolean IsOpen() {
        return this.thresholdNum != 0 && this.hasCompleted == 0;
    }

    public boolean CheckCondition() {
        int i = this.startTotalCount + 1;
        this.startTotalCount = i;
        SetSTotalCount(i);
        return CheckWifi() && this.startTotalCount >= this.thresholdNum;
    }

    public void Reject() {
        if (this.rejectCount > 0) {
            ResetCount();
            return;
        }
        int i = this.rejectCount + 1;
        this.rejectCount = i;
        SetRejectCount(i);
        SetSTotalCount(0);
    }

    public void Comment() {
        ResetCount();
    }

    public Context GetAppContext() {
        return this.appcontext;
    }

    private SharedPreferences GetPreferences() {
        return this.appcontext.getSharedPreferences("MarketComment", 2);
    }

    private void ReadData(Context context) {
        try {
            this.hasCompleted = GetPreferences().getInt(CompleteKey, 0);
            this.startTotalCount = GetPreferences().getInt(StartTotalKey, 0);
            this.rejectCount = GetPreferences().getInt(RejectKey, 0);
            this.thresholdNum = Integer.parseInt(OnlineConfigAgent.getInstance().getConfigParams(context, "market_comment_maxcount"));
        } catch (Exception e) {
        }
    }

    private void SetSTotalCount(int i) {
        if (EditCount(StartTotalKey, i)) {
            this.startTotalCount = i;
        }
    }

    private void SetRejectCount(int i) {
        if (EditCount(RejectKey, i)) {
            this.rejectCount = i;
        }
    }

    private void SetCompleted(int i) {
        if (EditCount(CompleteKey, i)) {
            this.hasCompleted = i;
        }
    }

    private boolean EditCount(String str, int i) {
        Editor edit = GetPreferences().edit();
        edit.putInt(str, i);
        return edit.commit();
    }

    private void ResetCount() {
        SetSTotalCount(0);
        SetRejectCount(0);
        SetCompleted(1);
        this.thresholdNum = 0;
    }
}
