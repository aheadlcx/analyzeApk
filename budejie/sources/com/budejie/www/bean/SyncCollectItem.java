package com.budejie.www.bean;

import java.util.ArrayList;

public class SyncCollectItem {
    String reason;
    String result;
    ArrayList<String> successIds;
    String uid;

    public String getResult() {
        return this.result;
    }

    public void setResult(String str) {
        this.result = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public ArrayList<String> getSuccessIds() {
        return this.successIds;
    }

    public void setSuccessIds(ArrayList<String> arrayList) {
        this.successIds = arrayList;
    }
}
