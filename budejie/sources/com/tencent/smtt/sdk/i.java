package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.sdk.QbSdk.PreInitCallback;
import com.tencent.smtt.sdk.TbsDownloader.TbsDownloaderCallback;

final class i implements TbsDownloaderCallback {
    final /* synthetic */ Context a;
    final /* synthetic */ PreInitCallback b;

    i(Context context, PreInitCallback preInitCallback) {
        this.a = context;
        this.b = preInitCallback;
    }

    public void onNeedDownloadFinish(boolean z, int i) {
        if (TbsShareManager.findCoreForThirdPartyApp(this.a) != 0 || TbsShareManager.getCoreDisabled()) {
            QbSdk.preInit(this.a, this.b);
        } else {
            TbsShareManager.forceToLoadX5ForThirdApp(this.a, false);
        }
    }
}
