package com.ixintui.plugin;

import android.content.Context;
import android.content.Intent;

public interface IMediateService {
    void onHandleIntent(Context context, Intent intent);
}
