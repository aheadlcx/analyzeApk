package com.meizu.cloud.pushsdk.handler;

import android.content.Context;
import android.content.Intent;

public abstract class AbstractAppLogicListener implements AppLogicListener {
    public void onMessage(Context context, Intent intent) {
    }
}
