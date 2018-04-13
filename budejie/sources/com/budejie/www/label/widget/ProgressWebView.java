package com.budejie.www.label.widget;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import com.budejie.www.R;
import com.budejie.www.adapter.b.a;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

public class ProgressWebView extends WebView {
    private ProgressBar b;
    private ProgressWebView$b c;
    private final int d = (a.a / 2);
    private final int e = ((int) (((double) this.d) * 0.8d));
    private c f;
    private WebChromeClient g;

    public interface c {
        void a(ValueCallback<Uri> valueCallback, String str);
    }

    public ProgressWebView(Context context) {
        super(context);
        b(context);
    }

    public ProgressWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b(context);
    }

    private void b(Context context) {
        this.b = new ProgressBar(context, null, 16842872);
        this.b.setLayoutParams(new LayoutParams(-1, getResources().getDimensionPixelOffset(R.dimen.progress_height)));
        this.b.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar));
        this.b.setMax(this.d);
        addView(this.b);
        setWebChromeClient(new ProgressWebView$d(this));
    }

    public void setMyWebChromeClient(WebChromeClient webChromeClient) {
        this.g = webChromeClient;
    }

    public void setWebCallbackClientInterface(c cVar) {
        this.f = cVar;
    }

    public void loadUrl(String str) {
        startAnimation(new ProgressWebView$a(this, null));
        this.b.setVisibility(0);
        super.loadUrl(str);
    }
}
