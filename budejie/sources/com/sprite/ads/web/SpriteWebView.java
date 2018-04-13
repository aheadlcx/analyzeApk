package com.sprite.ads.web;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import com.sprite.ads.R$dimen;
import com.sprite.ads.R$drawable;
import com.sprite.ads.internal.utils.ViewUtil;

public class SpriteWebView extends WebView {
    private WebViewClient a;
    private ProgressBar b;
    private SpriteWebView$c c;
    private final int d = (ViewUtil.SCREEN_WIDTH / 2);
    private final int e = ((int) (((double) this.d) * 0.8d));

    public SpriteWebView(Context context) {
        super(context);
        this.b = new ProgressBar(context, null, 16842872);
        this.b.setLayoutParams(new LayoutParams(-1, getResources().getDimensionPixelOffset(R$dimen.progress_height)));
        this.b.setProgressDrawable(getResources().getDrawable(R$drawable.progressbar));
        this.b.setMax(this.d);
        addView(this.b);
        getSettings().setJavaScriptEnabled(true);
        setWebChromeClient(new SpriteWebView$1(this));
        setWebViewClient(new SpriteWebView$2(this));
        setDownloadListener(new SpriteWebView$3(this));
    }

    protected void a(String str) {
        a.a(getContext(), str);
    }

    public void loadUrl(String str) {
        startAnimation(new SpriteWebView$b(this, null));
        this.b.setVisibility(0);
        super.loadUrl(str);
    }

    public void setMyWebViewClient(WebViewClient webViewClient) {
        this.a = webViewClient;
    }
}
