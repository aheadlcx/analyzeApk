package com.a.a;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import cn.v6.sixrooms.R;
import com.budejie.www.R$styleable;

public class d extends Dialog {
    private String a = "http://static.geetest.com/static/appweb/app-index.html";
    private String b;
    private String c;
    private Boolean d;
    private String e = "embed";
    private Boolean f = Boolean.valueOf(false);
    private WebView g;
    private a h;

    public interface a {
        void closeGt();

        void gtResult(boolean z, String str);
    }

    public d(Context context, String str, String str2, Boolean bool) {
        boolean z = false;
        super(context, R.style.gt_dialog);
        this.b = str;
        this.c = str2;
        if (!bool.booleanValue()) {
            z = true;
        }
        this.d = Boolean.valueOf(z);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void a(a aVar) {
        this.h = aVar;
    }

    protected void onCreate(Bundle bundle) {
        boolean z = true;
        requestWindowFeature(1);
        super.onCreate(bundle);
        this.g = new WebView(getContext());
        setContentView(this.g);
        LayoutParams layoutParams = this.g.getLayoutParams();
        int b = b.b(getContext());
        int a = b.a(getContext());
        float f = getContext().getResources().getDisplayMetrics().density;
        if (b < a) {
            a = (b * 3) / 4;
        }
        a = (a * 4) / 5;
        if (((int) ((((float) a) / f) + 0.5f)) < R$styleable.Theme_Custom_history_post_content_text_color) {
            a = (int) (289.5f * f);
        }
        layoutParams.width = a;
        layoutParams.height = 0;
        WebSettings settings = this.g.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setJavaScriptEnabled(true);
        this.g.addJavascriptInterface(new d$b(this), "JSInterface");
        String a2 = a.a(getContext()).a();
        StringBuilder append = new StringBuilder().append(this.a).append("?gt=").append(this.b).append("&challenge=").append(this.c).append("&success=");
        if (this.d.booleanValue()) {
            z = false;
        }
        String stringBuilder = append.append(z).append("&product=").append(this.e).append("&debug=").append(this.f).append("&width=").append((int) ((((float) a) / f) + 0.5f)).append("&mobileInfo=").append(a2).toString();
        c.a(stringBuilder);
        this.g.loadUrl(stringBuilder);
        c.a(stringBuilder);
        this.g.setWebChromeClient(new f(this));
    }
}
