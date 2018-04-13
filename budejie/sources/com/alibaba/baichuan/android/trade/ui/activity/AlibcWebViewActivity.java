package com.alibaba.baichuan.android.trade.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.model.TradeResult;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;

public class AlibcWebViewActivity extends a {
    private static final String f = AlibcWebViewActivity.class.getSimpleName();

    public void a(TradeResult tradeResult) {
        if (this.b.c.e != null) {
            AlibcContext.executorService.b(new e(this, tradeResult));
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (AlibcContext.isDebuggable()) {
            AlibcLogger.d(f, "remove cookies");
        }
    }

    protected void onNewIntent(Intent intent) {
        if (intent != null && intent.getExtras() != null) {
            String stringExtra = intent.getStringExtra("url");
            if (stringExtra != null) {
                this.a.loadUrl(stringExtra);
            }
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("caller", getIntent().getStringExtra("caller"));
    }
}
