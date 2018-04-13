package com.sprite.ads.web;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.sprite.ads.R;

public class SpriteAdActivity extends Activity {
    SpriteWebView a;
    View b;
    private boolean c = false;

    public void onBackPressed() {
        if (this.a.canGoBack()) {
            this.a.goBack();
        } else {
            super.onBackPressed();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = new SpriteWebView(this);
        setContentView(R.layout.spad_web);
        Intent intent = getIntent();
        if (intent != null) {
            findViewById(R.id.back_btn).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SpriteAdActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.a.canGoBack()) {
                        this.a.a.goBack();
                        return;
                    }
                    this.a.a.loadUrl("about:blank");
                    this.a.finish();
                }
            });
            this.b = findViewById(R.id.close_btn);
            this.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SpriteAdActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.a.loadUrl("about:blank");
                    this.a.finish();
                }
            });
            this.a.setMyWebViewClient(new WebViewClient(this) {
                final /* synthetic */ SpriteAdActivity a;

                {
                    this.a = r1;
                }

                public void onPageFinished(WebView webView, String str) {
                    try {
                        if (webView.canGoBack() && !this.a.c) {
                            this.a.b.setVisibility(0);
                            this.a.c = true;
                        } else if (!this.a.a.canGoBack() && this.a.c) {
                            this.a.b.setVisibility(8);
                            this.a.c = false;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            ((FrameLayout) findViewById(R.id.web_container)).addView(this.a);
            Object stringExtra = intent.getStringExtra("url");
            if (!TextUtils.isEmpty(stringExtra)) {
                this.a.loadUrl(stringExtra);
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !this.a.canGoBack()) {
            return super.onKeyDown(i, keyEvent);
        }
        this.a.goBack();
        return true;
    }

    protected void onPause() {
        super.onPause();
        if (VERSION.SDK_INT >= 11) {
            this.a.onPause();
        }
    }

    protected void onResume() {
        super.onResume();
        if (VERSION.SDK_INT >= 11) {
            this.a.onResume();
        }
    }
}
