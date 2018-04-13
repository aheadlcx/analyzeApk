package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;

public class PullToRefreshWebView extends PullToRefreshBase<WebView> {
    private static final PullToRefreshBase$OnRefreshListener<WebView> b = new g();
    private final WebChromeClient c = new h(this);

    @TargetApi(9)
    final class a extends WebView {
        final /* synthetic */ PullToRefreshWebView a;

        public a(PullToRefreshWebView pullToRefreshWebView, Context context, AttributeSet attributeSet) {
            this.a = pullToRefreshWebView;
            super(context, attributeSet);
        }

        protected final boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            boolean overScrollBy = super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            OverscrollHelper.overScrollBy(this.a, i, i3, i2, i4, (int) Math.max(0.0d, Math.floor((double) (((WebView) this.a.a).getScale() * ((float) ((WebView) this.a.a).getContentHeight()))) - ((double) ((getHeight() - getPaddingBottom()) - getPaddingTop()))), 2, 1.5f, z);
            return overScrollBy;
        }
    }

    public PullToRefreshWebView(Context context) {
        super(context);
        setOnRefreshListener(b);
        ((WebView) this.a).setWebChromeClient(this.c);
    }

    public PullToRefreshWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnRefreshListener(b);
        ((WebView) this.a).setWebChromeClient(this.c);
    }

    public PullToRefreshWebView(Context context, Mode mode) {
        super(context, mode);
        setOnRefreshListener(b);
        ((WebView) this.a).setWebChromeClient(this.c);
    }

    public PullToRefreshWebView(Context context, Mode mode, AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
        setOnRefreshListener(b);
        ((WebView) this.a).setWebChromeClient(this.c);
    }

    public final Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    protected WebView createRefreshableView(Context context, AttributeSet attributeSet) {
        WebView aVar;
        if (VERSION.SDK_INT >= 9) {
            aVar = new a(this, context, attributeSet);
        } else {
            aVar = new WebView(context, attributeSet);
        }
        aVar.setId(R.id.webview);
        return aVar;
    }

    protected boolean isReadyForPullStart() {
        return ((WebView) this.a).getScrollY() == 0;
    }

    protected boolean isReadyForPullEnd() {
        return ((double) ((WebView) this.a).getScrollY()) >= Math.floor((double) (((WebView) this.a).getScale() * ((float) ((WebView) this.a).getContentHeight()))) - ((double) ((WebView) this.a).getHeight());
    }

    protected void onPtrRestoreInstanceState(Bundle bundle) {
        super.onPtrRestoreInstanceState(bundle);
        ((WebView) this.a).restoreState(bundle);
    }

    protected void onPtrSaveInstanceState(Bundle bundle) {
        super.onPtrSaveInstanceState(bundle);
        ((WebView) this.a).saveState(bundle);
    }
}
