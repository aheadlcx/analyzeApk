package cn.xiaochuankeji.tieba.webview;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class WebViewTipsFragment_ViewBinding implements Unbinder {
    private WebViewTipsFragment b;
    private View c;
    private View d;
    private View e;

    @UiThread
    public WebViewTipsFragment_ViewBinding(final WebViewTipsFragment webViewTipsFragment, View view) {
        this.b = webViewTipsFragment;
        View a = b.a(view, R.id.container, "method 'close'");
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ WebViewTipsFragment_ViewBinding c;

            public void a(View view) {
                webViewTipsFragment.close();
            }
        });
        a = b.a(view, R.id.save, "method 'save'");
        this.d = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ WebViewTipsFragment_ViewBinding c;

            public void a(View view) {
                webViewTipsFragment.save();
            }
        });
        a = b.a(view, R.id.cancel, "method 'cancelEvent'");
        this.e = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ WebViewTipsFragment_ViewBinding c;

            public void a(View view) {
                webViewTipsFragment.cancelEvent();
            }
        });
    }

    @CallSuper
    public void a() {
        if (this.b == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
        this.e.setOnClickListener(null);
        this.e = null;
    }
}
