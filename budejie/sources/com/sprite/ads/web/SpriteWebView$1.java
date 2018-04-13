package com.sprite.ads.web;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.sprite.ads.R$anim;

class SpriteWebView$1 extends WebChromeClient {
    final /* synthetic */ SpriteWebView a;

    SpriteWebView$1(SpriteWebView spriteWebView) {
        this.a = spriteWebView;
    }

    public void onProgressChanged(WebView webView, int i) {
        int progress = SpriteWebView.a(this.a).getProgress();
        if (i < 10) {
            i = 10;
        }
        int b = (SpriteWebView.b(this.a) * i) / 100;
        if (b > progress) {
            if (SpriteWebView.c(this.a) != null) {
                SpriteWebView.c(this.a).cancel();
            }
            SpriteWebView.a(this.a, new SpriteWebView$c(this.a, progress, b));
            SpriteWebView.a(this.a).startAnimation(SpriteWebView.c(this.a));
        }
        if (i == 100 && SpriteWebView.c(this.a) != null) {
            SpriteWebView.c(this.a).setAnimationListener(new SpriteWebView$a(this) {
                final /* synthetic */ SpriteWebView$1 a;

                {
                    this.a = r3;
                    SpriteWebView spriteWebView = r3.a;
                }

                public void onAnimationEnd(Animation animation) {
                    Animation loadAnimation = AnimationUtils.loadAnimation(this.a.a.getContext(), R$anim.web_progress_gone_anim);
                    SpriteWebView.a(this.a.a).setAnimation(loadAnimation);
                    loadAnimation.setAnimationListener(new SpriteWebView$a(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r3;
                            SpriteWebView spriteWebView = r3.a.a;
                        }

                        public void onAnimationEnd(Animation animation) {
                            SpriteWebView.a(this.a.a.a).setVisibility(8);
                        }
                    });
                }
            });
        }
    }
}
