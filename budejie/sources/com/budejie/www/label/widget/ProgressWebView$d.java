package com.budejie.www.label.widget;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.budejie.www.R;
import com.budejie.www.f.d;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

public class ProgressWebView$d extends WebChromeClient {
    final /* synthetic */ ProgressWebView a;

    public ProgressWebView$d(ProgressWebView progressWebView) {
        this.a = progressWebView;
    }

    public void onProgressChanged(WebView webView, int i) {
        int progress = ProgressWebView.a(this.a).getProgress();
        if (i < 10) {
            i = 10;
        }
        int b = (ProgressWebView.b(this.a) * i) / 100;
        if (b > progress) {
            if (ProgressWebView.c(this.a) != null) {
                ProgressWebView.c(this.a).cancel();
            }
            ProgressWebView.a(this.a, new ProgressWebView$b(this.a, progress, b));
            ProgressWebView.a(this.a).startAnimation(ProgressWebView.c(this.a));
        }
        if (i == 100 && ProgressWebView.c(this.a) != null) {
            ProgressWebView.c(this.a).setAnimationListener(new d(this) {
                final /* synthetic */ ProgressWebView$d a;

                {
                    this.a = r1;
                }

                public void onAnimationEnd(Animation animation) {
                    Animation loadAnimation = AnimationUtils.loadAnimation(this.a.a.getContext(), R.anim.web_progress_gone_anim);
                    ProgressWebView.a(this.a.a).setAnimation(loadAnimation);
                    loadAnimation.setAnimationListener(new d(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onAnimationEnd(Animation animation) {
                            ProgressWebView.a(this.a.a.a).setVisibility(8);
                        }
                    });
                }
            });
        }
        super.onProgressChanged(webView, i);
    }

    public void a(ValueCallback<Uri> valueCallback, String str) {
        if (ProgressWebView.d(this.a) != null) {
            ProgressWebView.d(this.a).a(valueCallback, str);
        }
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
        a(valueCallback, str);
    }

    public void onReceivedTitle(WebView webView, String str) {
        super.onReceivedTitle(webView, str);
        if (ProgressWebView.e(this.a) != null) {
            ProgressWebView.e(this.a).onReceivedTitle(webView, str);
        }
    }

    public void onReceivedIcon(WebView webView, Bitmap bitmap) {
        super.onReceivedIcon(webView, bitmap);
        if (ProgressWebView.e(this.a) != null) {
            ProgressWebView.e(this.a).onReceivedIcon(webView, bitmap);
        }
    }
}
