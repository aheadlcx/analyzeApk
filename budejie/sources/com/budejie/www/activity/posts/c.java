package com.budejie.www.activity.posts;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.budejie.www.R;
import com.budejie.www.bean.TopNavigation;
import com.budejie.www.c.m;
import com.budejie.www.g.b;
import com.budejie.www.g.e;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.http.n;
import com.budejie.www.label.widget.ProgressWebView;
import com.budejie.www.util.ai;
import com.sina.weibo.sdk.auth.a.a;
import com.sina.weibo.sdk.auth.d;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.RenderPriority;
import com.tencent.tauth.Tencent;
import java.util.HashMap;

public class c extends Fragment implements d {
    public Tencent a;
    public a b;
    ValueCallback<Uri> c;
    String d = "";
    Handler e = new c$3(this);
    private final String f = "WebFragment";
    private View g;
    private boolean h = false;
    private ProgressWebView i;
    private ImageView j;
    private b k;
    private Toast l;
    private n m;
    private m n;
    private HashMap<String, String> o;
    private String p;
    private SharedPreferences q;
    private com.elves.update.a r;
    private Activity s;
    private IWXAPI t;
    private com.budejie.www.activity.htmlpage.c u;
    private String v;
    private TopNavigation w;
    private boolean x = false;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("WebFragment", "onAttach");
        this.s = getActivity();
        this.q = this.s.getSharedPreferences("weiboprefer", 0);
        this.r = new com.elves.update.a(this.s);
        this.n = new m(this.s);
        this.m = new n(this.s);
        this.p = ai.b(this.s);
        this.o = this.m.a(this.p);
        this.a = Tencent.createInstance("100336987", this.s);
        this.b = new a(this.s);
        this.k = new b(this.s, this.b, this.a, this);
        this.t = WXAPIFactory.createWXAPI(this.s, "wx592fdc48acfbe290", true);
        this.t.registerApp("wx592fdc48acfbe290");
        this.w = (TopNavigation) getArguments().getSerializable("TOP_NAVIGATION_KEY");
        this.v = this.w.url;
        this.v = NetWorkUtil.b(this.s, this.v);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d("WebFragment", "onCreate");
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Log.d("WebFragment", "onCreateView");
        if (this.g == null || this.g.getParent() == null) {
            this.g = layoutInflater.inflate(R.layout.fragment_web_layout, viewGroup, false);
            this.h = false;
            return this.g;
        }
        ((ViewGroup) this.g.getParent()).removeView(this.g);
        this.h = true;
        return this.g;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        Log.d("WebFragment", "onViewCreated");
        if (!this.h) {
            this.i = (ProgressWebView) view.findViewById(R.id.htmlWebView);
            this.j = (ImageView) view.findViewById(R.id.BackImageView);
            b();
        }
    }

    private void a() {
        if (this.s != null) {
            ((PostsActivity) this.s).g();
        }
    }

    private void b() {
        a();
        this.i.setWebCallbackClientInterface(new c$a(this));
        WebSettings settings = this.i.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(false);
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        this.u = new com.budejie.www.activity.htmlpage.c(this.s, this.e, this.k, this.r, this.n, this.m, this.o, this.t);
        this.i.addJavascriptInterface(this.u, AlibcConstants.PF_ANDROID);
        this.i.setDownloadListener(new e(getActivity()));
        this.i.setWebViewClient(new c$1(this));
        Log.d("WebFragment", "loadUrl mUrl=" + this.v);
        this.i.loadUrl(this.v);
        this.j.setOnClickListener(new c$2(this));
    }

    public void onSuccess(com.sina.weibo.sdk.auth.b bVar) {
    }

    public void cancel() {
    }

    public void onFailure(com.sina.weibo.sdk.auth.e eVar) {
    }
}
