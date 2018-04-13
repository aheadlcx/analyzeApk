package com.ixintui.push;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.ixintui.plugin.IMediateService;
import com.ixintui.pushsdk.a.a;

public class MediateService extends IntentService {
    private IMediateService a;

    public MediateService() {
        super("MediateService");
    }

    protected void onHandleIntent(Intent intent) {
        if (this.a == null) {
            this.a = (IMediateService) a.a((Context) this, "com.ixintui.push.MediateServiceImpl");
        }
        if (this.a != null) {
            this.a.onHandleIntent(this, intent);
        }
    }
}
