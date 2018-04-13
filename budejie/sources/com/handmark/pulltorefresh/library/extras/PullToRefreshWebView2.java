package com.handmark.pulltorefresh.library.extras;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;
import java.util.concurrent.atomic.AtomicBoolean;

public class PullToRefreshWebView2 extends PullToRefreshWebView {
    private a b;
    private final AtomicBoolean c = new AtomicBoolean(false);
    private final AtomicBoolean d = new AtomicBoolean(false);

    final class a {
        final /* synthetic */ PullToRefreshWebView2 a;

        a(PullToRefreshWebView2 pullToRefreshWebView2) {
            this.a = pullToRefreshWebView2;
        }
    }

    public PullToRefreshWebView2(Context context) {
        super(context);
    }

    public PullToRefreshWebView2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PullToRefreshWebView2(Context context, Mode mode) {
        super(context, mode);
    }

    protected WebView createRefreshableView(Context context, AttributeSet attributeSet) {
        WebView createRefreshableView = super.createRefreshableView(context, attributeSet);
        this.b = new a(this);
        createRefreshableView.addJavascriptInterface(this.b, "ptr");
        return createRefreshableView;
    }

    protected boolean isReadyForPullStart() {
        ((WebView) getRefreshableView()).loadUrl("javascript:isReadyForPullDown();");
        return this.c.get();
    }

    protected boolean isReadyForPullEnd() {
        ((WebView) getRefreshableView()).loadUrl("javascript:isReadyForPullUp();");
        return this.d.get();
    }
}
