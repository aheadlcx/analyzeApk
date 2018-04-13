package cn.xiaochuankeji.tieba.ui.post.postdetail;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseActivity;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class b extends FrameLayout {
    private static String g;
    private WebView a;
    private Post b;
    private b c;
    private int d;
    private String e;
    private String f;

    public interface b {
        void a(boolean z);
    }

    class a extends FilterInputStream {
        final /* synthetic */ b a;
        private byte[] b = "<script language=\"javascript\">function invokeNative(action, params) {\n    var iframe = document.createElement('iframe');\n    iframe.style.display = 'none';\n    var urlString = 'zuiyou://' + action;\n    if(params) {\n        var paramsString = '';\n        var first = true;\n        for(var key in params) {\n            if(first == false) {\n                paramsString += '&';\n            }\n            first = false;\n            paramsString += key + '=' + params[key];\n        }\n        urlString += '?' + paramsString;\n    }\n    iframe.src = urlString;\n    document.body.appendChild(iframe);\n    setTimeout(function() {\n        iframe.remove();\n    }, 100);\n};\nfunction fixedEncodeURI (str) {\n    return encodeURI(str).replace(/[!*'();:@&=+$,/?#]/g, function(c) {\n        return '%' + c.charCodeAt(0).toString(16);\n    });\n}\n\nfunction interceptVideo() {\n    var cover = document.createElement(\"div\");\n    cover.style.position = \"absolute\";\n    cover.style.top = 0;\n    cover.style.left = 0;\n    cover.style.right = 0;\n    cover.style.bottom = 0;\n    cover.style.zIndex = 1000;\n    document.body.appendChild(cover);\n\n    cover.onclick = function() {\n       var video = document.getElementsByTagName(\"video\")[0];\n       if(video) {\n           invokeNative(\"video\", {\"src\" : fixedEncodeURI(video.src)});\n       }\n    };\n}\n\ninterceptVideo();\n</script>".getBytes();
        private int c;

        protected a(b bVar, InputStream inputStream) {
            this.a = bVar;
            super(inputStream);
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = super.read(bArr, i, i2);
            if (read >= 0) {
                return read;
            }
            int length = this.b.length;
            if (this.c >= length) {
                return read;
            }
            read = Math.min(length - this.c, i2);
            System.arraycopy(this.b, this.c, bArr, i, read);
            this.c += read;
            return read;
        }
    }

    class c extends WebChromeClient {
        final /* synthetic */ b a;

        c(b bVar) {
            this.a = bVar;
        }

        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            g.a(str2);
            jsResult.confirm();
            return true;
        }
    }

    class d extends WebViewClient {
        final /* synthetic */ b a;
        private boolean b;
        private boolean c;
        private int d;

        d(b bVar) {
            this.a = bVar;
        }

        static /* synthetic */ int c(d dVar) {
            int i = dVar.d - 1;
            dVar.d = i;
            return i;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Uri parse = Uri.parse(str);
            if (parse.getScheme().equals("zuiyou")) {
                String host = parse.getHost();
                if (host.equals("complete")) {
                    this.a.b();
                    return true;
                } else if (host.equals("click")) {
                    this.a.b(parse.getQueryParameter("name"));
                    return true;
                } else if (!host.equals("video")) {
                    return true;
                } else {
                    this.a.c(parse.getQueryParameter("src"));
                    return true;
                }
            } else if (this.b || this.c) {
                return true;
            } else {
                this.d++;
                webView.loadUrl(str);
                return false;
            }
        }

        public void onPageFinished(WebView webView, String str) {
            if (!this.b && !this.c) {
                webView.postDelayed(new Runnable(this) {
                    final /* synthetic */ d a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.d <= 0) {
                            if (!this.a.b) {
                                this.a.a.c();
                            }
                            this.a.b = true;
                            return;
                        }
                        d.c(this.a);
                    }
                }, 100);
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            this.c = true;
            this.a.a(i, str);
        }

        @TargetApi(11)
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            try {
                URL url = new URL(str);
                if (url.getHost().equals("v.qq.com") && url.getPath().endsWith(".html")) {
                    HttpURLConnection a = cn.xiaochuankeji.tieba.d.g.a(url, this.a.e);
                    return new WebResourceResponse(a.getContentType(), a.getContentEncoding(), new a(this.a, a.getInputStream()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return super.shouldInterceptRequest(webView, str);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (sslErrorHandler != null) {
                sslErrorHandler.proceed();
            }
        }
    }

    public b(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.f = cn.htjyb.c.a.b.a(context.getAssets(), "weixin_webpage_injector.js", "UTF-8");
        b(context);
    }

    private void b(Context context) {
        this.a = (WebView) LayoutInflater.from(context).inflate(R.layout.webview, null);
        this.a.setBackgroundResource(R.color.white);
        this.a.setWebViewClient(new d(this));
        this.a.setWebChromeClient(new c(this));
        this.a.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public boolean onLongClick(View view) {
                return true;
            }
        });
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        setOverScrollMode(2);
        WebSettings settings = this.a.getSettings();
        settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        this.e = settings.getUserAgentString();
        this.d = cn.htjyb.c.a.d(context);
        addView(this.a, new LayoutParams(this.d, -2));
    }

    private void a(boolean z) {
        ViewGroup.LayoutParams layoutParams;
        if (getLayoutParams().height == -2) {
            layoutParams = new LayoutParams(this.d, -2);
        } else {
            layoutParams = new LayoutParams(this.d, -1);
        }
        this.a.setLayoutParams(layoutParams);
    }

    public void a(String str, Post post, b bVar) {
        this.a.loadUrl(str);
        this.b = post;
        this.c = bVar;
    }

    public void a() {
        if (this.a != null) {
            this.a.removeAllViews();
            this.a.destroy();
            this.a = null;
        }
    }

    private void a(int i, String str) {
        if (this.a != null) {
            this.a.loadUrl("javascript:(function() {   document.body.style.backgroundColor = 'white';})()");
            a(false);
            if (this.c != null) {
                this.c.a(false);
            }
        }
    }

    private void b() {
        a(true);
        if (this.c != null) {
            this.c.a(true);
        }
    }

    private void b(String str) {
        if (!TextUtils.isEmpty(str) && str.equals("topic")) {
            TopicDetailActivity.a(getContext(), this.b._topic, "postdetail");
        }
    }

    private void c(String str) {
        if (!TextUtils.isEmpty(str)) {
            PictureImpl pictureImpl = new PictureImpl(str, Type.kVideo, 0);
            ArrayList arrayList = new ArrayList();
            arrayList.add(pictureImpl);
            MediaBrowseActivity.a(getContext(), 0, this.b, arrayList, true, EntranceType.PostDetail);
        }
    }

    private void c() {
        if (this.a != null) {
            d();
        }
    }

    private void d() {
        if (TextUtils.isEmpty(g)) {
            new AsyncTask<Void, Void, String>(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((Void[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    a((String) obj);
                }

                protected String a(Void... voidArr) {
                    try {
                        String a = cn.htjyb.c.b.a(new URL(cn.xiaochuankeji.tieba.background.utils.c.a.c().B()).openStream(), Charset.forName("utf-8"));
                        if (c.a.c.e().c()) {
                            return "var IS_NIGHT_MODE = true;" + a;
                        }
                        return a;
                    } catch (Exception e) {
                        return this.a.f;
                    }
                }

                protected void a(String str) {
                    b.g = str;
                    if (this.a.a != null) {
                        this.a.a.loadUrl("javascript:" + str);
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else if (this.a != null) {
            this.a.loadUrl("javascript:" + g);
        }
    }
}
