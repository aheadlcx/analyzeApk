package com.jlzx.comment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

abstract class PopStrategy {
    public abstract boolean CheckCondition();

    public abstract void Comment();

    public abstract Context GetAppContext();

    public abstract boolean IsOpen();

    public abstract void Reject();

    PopStrategy() {
    }

    protected boolean CheckWifi() {
        NetworkInfo[] allNetworkInfo = ((ConnectivityManager) GetAppContext().getSystemService("connectivity")).getAllNetworkInfo();
        boolean z = false;
        for (int i = 0; i < allNetworkInfo.length; i++) {
            if (allNetworkInfo[i].getState() == State.CONNECTED) {
                if (allNetworkInfo[i].getType() == 0) {
                    z = false;
                }
                if (allNetworkInfo[i].getType() == 1) {
                    z = true;
                }
            }
        }
        return z;
    }
}
