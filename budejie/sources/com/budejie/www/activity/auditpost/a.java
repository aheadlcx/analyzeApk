package com.budejie.www.activity.auditpost;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.BudejieApplication.Status;
import com.budejie.www.activity.video.k;
import com.budejie.www.bean.CommentItem;
import com.budejie.www.bean.TouGaoItem;
import com.budejie.www.c.e;
import com.budejie.www.http.c;
import com.budejie.www.label.widget.ProgressWebView;
import com.budejie.www.util.ac;
import com.budejie.www.util.an;
import com.budejie.www.widget.XListView;
import com.google.analytics.tracking.android.HitTypes;
import com.tencent.connect.common.Constants;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings$LayoutAlgorithm;
import com.tencent.smtt.sdk.WebSettings.RenderPriority;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.util.ArrayList;
import java.util.List;

public class a extends Fragment {
    private TouGaoItem a;
    private XListView b;
    private c c;
    private String d;
    private int e = 0;
    private AuditPostsActivity f;
    private e g;
    private d<TouGaoItem> h;
    private ImageView i;
    private ImageView j;
    private ArrayList<CommentItem> k = new ArrayList();
    private c l;
    private String m;
    private String n;
    private int o = -1;
    private com.budejie.www.widget.XListView.a p = new a$2(this);
    private OnScrollListener q = new a$3(this);
    private net.tsz.afinal.a.a<String> r = new a$4(this);
    private OnClickListener s = new a$5(this);

    public static a a(TouGaoItem touGaoItem) {
        a aVar = new a();
        Bundle bundle = new Bundle();
        bundle.putSerializable(HitTypes.ITEM, touGaoItem);
        aVar.setArguments(bundle);
        return aVar;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f = (AuditPostsActivity) activity;
        this.a = (TouGaoItem) getArguments().getSerializable(HitTypes.ITEM);
        this.g = new e(activity);
        this.l = new c(this.f, null);
        this.c = new c(getActivity(), this.g, new ArrayList(), this.s);
        this.d = OnlineConfigAgent.getInstance().getConfigParams(activity, "评论列表-请求条数");
        if (TextUtils.isEmpty(this.d)) {
            this.d = "20";
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.audit_posts_fragment, viewGroup, false);
    }

    @SuppressLint({"NewApi"})
    public void onViewCreated(View view, Bundle bundle) {
        this.i = (ImageView) view.findViewById(R.id.iv_ding);
        this.j = (ImageView) view.findViewById(R.id.iv_cai);
        ProgressWebView progressWebView = (ProgressWebView) view.findViewById(R.id.web_view);
        if ("51".equals(this.a.getType())) {
            if (VERSION.SDK_INT >= 11) {
                progressWebView.setLayerType(1, null);
            }
            progressWebView.setWebViewClient(new a$1(this));
            WebSettings settings = progressWebView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setBuiltInZoomControls(false);
            settings.setRenderPriority(RenderPriority.HIGH);
            settings.setAppCacheEnabled(true);
            settings.setDatabaseEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setLoadWithOverviewMode(true);
            settings.setLayoutAlgorithm(WebSettings$LayoutAlgorithm.SINGLE_COLUMN);
            progressWebView.resumeTimers();
            progressWebView.setDownloadListener(new com.budejie.www.g.e(this.f));
            progressWebView.loadUrl(this.a.getRichObject().getSourceUrl() + "&auditMode=1");
            progressWebView.setVisibility(0);
            return;
        }
        progressWebView.setVisibility(8);
        this.b = (XListView) view.findViewById(R.id.cmtlistview);
        this.b.setPullRefreshEnable(false);
        this.b.setPullLoadEnable(false);
        this.b.setXListViewListener(this.p);
        this.b.setOnScrollListener(this.q);
        this.h = i();
        if (this.h != null) {
            this.b.addHeaderView(this.h.a());
        }
        this.b.setAdapter(this.c);
        f();
    }

    public void onPause() {
        super.onPause();
        k.a(getActivity()).o();
    }

    public void a(boolean z) {
    }

    public void a() {
    }

    private void f() {
        if (an.a(this.f)) {
            this.o = 0;
            this.n = "0";
            this.m = "0";
            this.l.a(this.f, this.a.getDataId(), "0", "0", this.r);
            return;
        }
        an.a(this.f, this.f.getString(R.string.nonet), -1).show();
    }

    private void g() {
        this.o = 2;
        this.l.a(this.f, this.a.getDataId(), this.m, "2", this.r);
    }

    private void h() {
        this.o = 1;
        this.l.a(this.f, this.a.getDataId(), this.n, "1", this.r);
    }

    private d<TouGaoItem> i() {
        if ("31".equals(this.a.getType())) {
            return e.a(getActivity(), HeadViewType.VOICE_VIEW, this.a);
        }
        if ("41".equals(this.a.getType())) {
            return e.a(getActivity(), HeadViewType.VIDEO_VIEW, this.a);
        }
        if (Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(this.a.getType())) {
            return e.a(getActivity(), HeadViewType.GIF_VIEW, this.a);
        }
        return e.a(getActivity(), HeadViewType.TEXT_VIEW, this.a);
    }

    public void b() {
        if (isResumed() && getUserVisibleHint() && (this.h instanceof h)) {
            ((h) this.h).b();
        }
    }

    public void c() {
        if (this.i != null) {
            this.i.setVisibility(0);
        }
    }

    public void d() {
        if (this.j != null) {
            this.j.setVisibility(0);
        }
    }

    private void a(List<CommentItem> list) {
        List a = this.g.a();
        if (a.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                CommentItem commentItem = (CommentItem) list.get(i);
                if (!(commentItem == null || commentItem.getId() == null)) {
                    for (int i2 = 0; i2 < a.size(); i2++) {
                        String str = (String) a.get(i2);
                        if (!TextUtils.isEmpty(str)) {
                            String[] split = str.split("#");
                            String str2 = split[0];
                            str = split[1];
                            if (str2 != null && str2.equals(commentItem.getId())) {
                                commentItem.setDingOrCai(str);
                            }
                        }
                    }
                }
                if (!(commentItem == null || commentItem.getReplyList() == null || commentItem.getReplyList().size() <= 0)) {
                    for (int i3 = 0; i3 < commentItem.getReplyList().size(); i3++) {
                        CommentItem commentItem2 = (CommentItem) commentItem.getReplyList().get(i3);
                        for (int i4 = 0; i4 < a.size(); i4++) {
                            String str3 = (String) a.get(i4);
                            if (!TextUtils.isEmpty(str3)) {
                                String[] split2 = str3.split("#");
                                String str4 = split2[0];
                                str3 = split2[1];
                                if (str4 != null && str4.equals(commentItem2.getId())) {
                                    commentItem2.setDingOrCai(str3);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void e() {
        BudejieApplication budejieApplication = (BudejieApplication) this.f.getApplication();
        if (budejieApplication.a() == null || budejieApplication.a() == Status.end) {
            ac a = ac.a(this.f);
            if (a.c()) {
                a.i();
            }
        }
    }

    private void j() {
        if (this.c == null) {
            this.c = new c(getActivity(), this.g, this.k, this.s);
            this.b.setAdapter(this.c);
            return;
        }
        this.c.a(this.k);
    }
}
