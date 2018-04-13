package cn.xiaochuankeji.tieba.ui.mediabrowse.h5video;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.JsPromptResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.htjyb.netlib.NetworkMonitor;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuan.jsbridge.d;
import cn.xiaochuan.jsbridge.e;
import cn.xiaochuan.jsbridge.f;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.mediabrowse.h5video.H5VideoProxy.SrcResult;
import java.net.MalformedURLException;
import java.net.URL;

public class H5VideoWebViewContainer extends RelativeLayout {
    private f a;
    private View b;
    private View c;
    private View d;
    private View e;
    private TextView f;
    private TextView g;
    private ProgressBar h;
    private b i;
    private c j;
    private b k;
    private H5VideoProxy l;
    private boolean m;
    private a n;
    private boolean o = false;
    private LinearLayout p;
    private ImageView q;
    private AnimationDrawable r;

    public interface a {
        void a();

        void a(int i, String str);

        void a(String str);
    }

    class b extends e {
        final /* synthetic */ H5VideoWebViewContainer a;

        b(H5VideoWebViewContainer h5VideoWebViewContainer) {
            this.a = h5VideoWebViewContainer;
        }

        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            this.a.h.setProgress(i);
            this.a.f.setText("progress:" + i);
        }

        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            if (!this.a.i.b(str2)) {
                return false;
            }
            jsPromptResult.confirm("{\"code\": 200, \"result\":\"\"}");
            return true;
        }
    }

    class c extends d {
        final /* synthetic */ H5VideoWebViewContainer a;
        private boolean c;
        private boolean d;
        private int e;

        static /* synthetic */ int c(c cVar) {
            int i = cVar.e - 1;
            cVar.e = i;
            return i;
        }

        public c(H5VideoWebViewContainer h5VideoWebViewContainer, f fVar) {
            this.a = h5VideoWebViewContainer;
            super(fVar);
            h5VideoWebViewContainer.i = new b(h5VideoWebViewContainer.a);
            h5VideoWebViewContainer.l = new H5VideoProxy(h5VideoWebViewContainer.i);
        }

        public void a() {
            this.c = false;
            this.d = false;
            this.e = 0;
        }

        protected boolean a(WebView webView, String str) throws Exception {
            if (this.c || this.d) {
                return super.a(webView, str);
            }
            this.e++;
            webView.loadUrl(str);
            return false;
        }

        public void onPageFinished(WebView webView, String str) {
            if (!this.c && !this.d) {
                webView.postDelayed(new Runnable(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.e <= 0) {
                            if (!this.a.c) {
                                this.a.a.e();
                            }
                            this.a.c = true;
                            return;
                        }
                        c.c(this.a);
                    }
                }, 100);
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            this.d = true;
            this.a.a(i, str);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (sslErrorHandler != null) {
                sslErrorHandler.proceed();
            }
        }
    }

    public H5VideoWebViewContainer(Context context) {
        super(context);
    }

    public H5VideoWebViewContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public H5VideoWebViewContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setCallback(a aVar) {
        this.n = aVar;
    }

    public void a() {
        this.o = true;
    }

    public void b() {
        if (!this.m) {
            LayoutInflater.from(getContext()).inflate(R.layout.h5video_view_container, this);
            setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            this.a = new f(BaseApplication.getAppContext());
            addView(this.a, 0, new LayoutParams(-1, 0));
            d();
            this.b = findViewById(R.id.top_cover);
            this.h = (ProgressBar) findViewById(R.id.video_loading_pbar);
            this.b.setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ H5VideoWebViewContainer a;

                {
                    this.a = r1;
                }

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
            this.p = (LinearLayout) findViewById(R.id.llLoadingContainer);
            this.q = (ImageView) findViewById(R.id.ivLoading);
            this.r = (AnimationDrawable) this.q.getDrawable();
            this.d = findViewById(R.id.loading_prompt_label);
            this.e = findViewById(R.id.video_prompt_label);
            this.f = (TextView) findViewById(R.id.loading_progress_label);
            this.g = (TextView) findViewById(R.id.report_play_failed_label);
            if (this.o) {
                this.g.setText("不能播放？点此报错");
            } else {
                this.g.setText("不能播放？请点击此处");
            }
            this.g.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ H5VideoWebViewContainer a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    view.setEnabled(false);
                    if (this.a.n != null) {
                        this.a.n.a();
                    }
                }
            });
            this.c = findViewById(R.id.btn_retry);
            this.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ H5VideoWebViewContainer a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.c.setVisibility(4);
                    this.a.a(true, true);
                    this.a.a.reload();
                }
            });
            this.m = true;
        }
    }

    private boolean a(String str) {
        URL url;
        try {
            url = new URL(str);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            url = null;
        }
        if (url != null) {
            Object host = url.getHost();
            if (!TextUtils.isEmpty(host) && host.contains("miaopai")) {
                return true;
            }
        }
        return false;
    }

    public void a(final String str, final int i, final int i2) {
        if (a(str)) {
            a aVar = new a(str, new cn.xiaochuankeji.tieba.ui.mediabrowse.h5video.a.a(this) {
                final /* synthetic */ H5VideoWebViewContainer d;

                public void a(String str) {
                    if (TextUtils.isEmpty(str)) {
                        this.d.b(str, i, i2);
                        return;
                    }
                    this.d.a(false, false);
                    if (this.d.n != null) {
                        this.d.n.a(str);
                    }
                }
            });
            if (VERSION.SDK_INT < 11) {
                aVar.execute(new Void[0]);
            } else {
                aVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            }
            post(new Runnable(this) {
                final /* synthetic */ H5VideoWebViewContainer a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a(true, false);
                }
            });
            return;
        }
        b(str, i, i2);
    }

    private void b(String str, final int i, final int i2) {
        if (this.a != null) {
            post(new Runnable(this) {
                final /* synthetic */ H5VideoWebViewContainer c;

                public void run() {
                    int height = this.c.getHeight();
                    int a = cn.xiaochuankeji.tieba.ui.utils.e.a((float) i);
                    int a2 = cn.xiaochuankeji.tieba.ui.utils.e.a((float) i2);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.c.a.getLayoutParams();
                    layoutParams.height = a + a2;
                    layoutParams.topMargin = ((height - a2) / 2) - a;
                    this.c.a.setLayoutParams(layoutParams);
                    RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.c.b.getLayoutParams();
                    layoutParams2.height = a;
                    layoutParams2.topMargin = layoutParams.topMargin;
                    this.c.b.setLayoutParams(layoutParams2);
                    RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.c.e.getLayoutParams();
                    layoutParams3.topMargin = ((layoutParams2.height + layoutParams2.topMargin) - this.c.e.getHeight()) - cn.htjyb.c.a.a(50.0f, this.c.getContext());
                    this.c.e.setLayoutParams(layoutParams3);
                    layoutParams2 = (RelativeLayout.LayoutParams) this.c.g.getLayoutParams();
                    layoutParams2.topMargin = (layoutParams.height + layoutParams.topMargin) + cn.htjyb.c.a.a(60.0f, this.c.getContext());
                    this.c.g.setLayoutParams(layoutParams2);
                }
            });
            this.b.setVisibility(4);
            this.e.setVisibility(4);
            this.a.setVisibility(4);
            this.g.setVisibility(4);
            this.j.a();
            this.i.a();
            this.a.loadUrl(str);
            post(new Runnable(this) {
                final /* synthetic */ H5VideoWebViewContainer a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a(true, true);
                }
            });
        }
    }

    public void c() {
        if (this.a != null) {
            this.a.removeAllViews();
            this.a.destroy();
            this.a = null;
        }
    }

    private void d() {
        this.a.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ H5VideoWebViewContainer a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return motionEvent.getAction() == 2;
            }
        });
        cn.xiaochuan.jsbridge.a.a(this.a, null, "4.1.8.9");
        WebSettings settings = this.a.getSettings();
        this.j = new c(this, this.a);
        this.a.setWebViewClient(this.j);
        this.k = new b(this);
        this.a.setWebChromeClient(this.k);
        this.a.setHorizontalScrollBarEnabled(false);
        this.a.setVerticalScrollBarEnabled(false);
        this.a.setOverScrollMode(2);
        settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        settings.setUserAgentString("Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B137 Safari/601.1");
        this.i = new b(this.a);
        this.l = new H5VideoProxy(this.i);
    }

    private void a(boolean z, boolean z2) {
        if (z) {
            if (z2) {
                this.h.setVisibility(0);
            } else {
                this.h.setVisibility(8);
            }
            this.p.setVisibility(0);
            this.r.start();
            this.d.setVisibility(0);
            return;
        }
        this.h.setVisibility(8);
        this.r.stop();
        this.p.setVisibility(8);
        this.d.setVisibility(4);
    }

    private void e() {
        a(false, false);
        this.i.b();
        this.l.a();
        this.l.a(new c<SrcResult>(this) {
            final /* synthetic */ H5VideoWebViewContainer a;

            {
                this.a = r1;
            }

            public void a(SrcResult srcResult) {
                if (TextUtils.isEmpty(srcResult.src)) {
                    this.a.b.setVisibility(0);
                    this.a.e.setVisibility(0);
                    this.a.a.setVisibility(0);
                    this.a.g.setVisibility(0);
                } else if (this.a.n != null) {
                    this.a.n.a(srcResult.src);
                }
            }
        });
        this.l.b();
    }

    private void a(int i, String str) {
        if (!NetworkMonitor.a()) {
            g.a("没有网络，请连接~");
        }
        a(false, false);
        this.g.setVisibility(0);
        this.c.setVisibility(0);
        if (this.n != null) {
            this.n.a(i, str);
        }
    }
}
