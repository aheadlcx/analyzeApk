package com.budejie.www.g;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.tencent.smtt.sdk.DownloadListener;

public class e implements DownloadListener {
    Context a;

    public e(Context context) {
        this.a = context;
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        Uri parse = Uri.parse(str);
        if (parse != null) {
            this.a.startActivity(new Intent("android.intent.action.VIEW", parse));
        }
    }
}
