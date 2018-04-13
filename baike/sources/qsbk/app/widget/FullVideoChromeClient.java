package qsbk.app.widget;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebView;
import android.widget.FrameLayout;
import qsbk.app.utils.LogUtil;

public abstract class FullVideoChromeClient extends WebChromeClient {
    private CustomViewCallback a;
    private int b = 1;
    private View c;

    public abstract Activity getActivity();

    public abstract FrameLayout getVideoContaner();

    public abstract WebView getWebView();

    public abstract void setActionbarVisible(boolean z);

    public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
        LogUtil.d("on show custom view");
        onShowCustomView(view, this.b, customViewCallback);
        super.onShowCustomView(view, customViewCallback);
    }

    public void onShowCustomView(View view, int i, CustomViewCallback customViewCallback) {
        if (this.c != null) {
            customViewCallback.onCustomViewHidden();
            return;
        }
        this.c = view;
        this.a = customViewCallback;
        this.b = getActivity().getRequestedOrientation();
        FrameLayout videoContaner = getVideoContaner();
        videoContaner.addView(this.c);
        videoContaner.setVisibility(0);
        videoContaner.bringToFront();
        getWebView().setVisibility(8);
        LogUtil.d("hide actionbar");
        setActionbarVisible(false);
        getActivity().setRequestedOrientation(0);
        getActivity().getWindow().setFlags(1024, 1024);
    }

    public void onHideCustomView() {
        if (this.c != null) {
            FrameLayout videoContaner = getVideoContaner();
            videoContaner.removeView(this.c);
            this.c = null;
            videoContaner.setVisibility(8);
            try {
                this.a.onCustomViewHidden();
            } catch (Exception e) {
            }
            getWebView().setVisibility(0);
            setActionbarVisible(true);
            Activity activity = getActivity();
            if (activity != null) {
                activity.setRequestedOrientation(1);
                LayoutParams attributes = activity.getWindow().getAttributes();
                attributes.flags &= -1025;
                activity.getWindow().setAttributes(attributes);
                activity.getWindow().clearFlags(512);
            }
        }
    }
}
