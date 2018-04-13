package com.alibaba.baichuan.android.trade.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.ali.auth.third.ui.context.CallbackContext;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.c.b.d;
import com.alibaba.baichuan.android.trade.callback.AlibcCallbackContext;
import com.alibaba.baichuan.android.trade.utils.c;
import com.alibaba.baichuan.android.trade.utils.i;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import java.io.Serializable;
import java.util.Map;

public class a extends Activity {
    public static int e = c.a("OPEN_H5_TRADE");
    private static final String h = a.class.getSimpleName();
    protected WebView a;
    protected d b;
    public TextView c;
    public boolean d;
    private View f;
    private View g;

    protected void onActivityResult(int i, int i2, Intent intent) {
        CallbackContext.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        if (this.b.a()) {
            this.b.e();
        } else {
            finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        String str = null;
        super.onCreate(bundle);
        if (!AlibcContext.isShowTitleBar) {
            requestWindowFeature(1);
        }
        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(i.a(this, "com_taobao_nb_sdk_web_view_activity"), null);
        Serializable serializableExtra = getIntent() != null ? getIntent().getSerializableExtra("ui_contextParams") : bundle != null ? bundle.getSerializable("ui_contextParams") : null;
        com.alibaba.baichuan.android.trade.b.a aVar = AlibcCallbackContext.showProcessContext;
        AlibcCallbackContext.showProcessContext = null;
        if (aVar == null) {
            finish();
            return;
        }
        CharSequence string;
        this.a = new WebView(this);
        this.b = new d(this, aVar, this.a, serializableExtra instanceof Map ? (Map) serializableExtra : null, false);
        linearLayout.addView(this.a, new LayoutParams(-1, -1));
        setContentView(linearLayout);
        this.c = (TextView) findViewById(i.a(this, "id", "com_taobao_nb_sdk_web_view_title_bar_title"));
        this.g = findViewById(i.a(this, "id", "com_taobao_nb_sdk_web_view_title_bar_close_button"));
        if (this.g != null) {
            this.g.setOnClickListener(new b(this));
        }
        View findViewById = findViewById(i.a(this, "id", "com_taobao_nb_sdk_web_view_title_bar_back_button"));
        if (findViewById != null) {
            findViewById.setOnClickListener(new c(this));
        }
        findViewById = findViewById(i.a(this, "id", "com_taobao_tae_sdk_web_view_title_bar_more_button"));
        if (findViewById != null) {
            findViewById.setOnClickListener(new d(this));
        }
        this.f = findViewById(i.a(this, "id", "com_taobao_tae_sdk_web_view_title_bar"));
        if (bundle != null) {
            string = bundle.getString("title");
            str = bundle.getString("title");
        } else {
            string = null;
        }
        if (TextUtils.isEmpty(string) && aVar != null) {
            string = aVar.d;
        }
        if (TextUtils.isEmpty(string)) {
            this.d = true;
        } else {
            this.d = false;
            this.c.setText(string);
        }
        if (TextUtils.isEmpty(str)) {
            str = getIntent().getStringExtra("url");
        }
        AlibcLogger.d(h, "AliTrade SDK WebView首次加载的url=" + str);
        this.a.loadUrl(str);
    }

    protected void onDestroy() {
        if (this.a != null) {
            ViewGroup viewGroup = (ViewGroup) this.a.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.a);
            }
            this.a.removeAllViews();
            this.a.destroy();
        }
        super.onDestroy();
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("url", this.a.getUrl());
        bundle.putString("title", this.c.getText().toString());
        bundle.putSerializable("ui_contextParams", (Serializable) this.b.b());
    }
}
