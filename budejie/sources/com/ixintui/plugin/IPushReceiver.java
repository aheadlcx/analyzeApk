package com.ixintui.plugin;

import android.content.Context;
import android.content.Intent;

public interface IPushReceiver {
    void onReceive(Context context, Intent intent);
}
