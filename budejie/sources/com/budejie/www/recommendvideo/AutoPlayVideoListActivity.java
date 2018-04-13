package com.budejie.www.recommendvideo;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.ali.auth.third.core.model.Constants;
import com.alipay.sdk.util.j;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.OauthWeiboBaseAct;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.activity.search.SearchMainActivity;
import com.budejie.www.activity.video.k;
import com.budejie.www.adapter.e.a;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.SuggestedFollowsListItem;
import com.budejie.www.http.f;
import com.budejie.www.http.n;
import com.budejie.www.util.aa;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.au;
import com.budejie.www.util.m;
import com.budejie.www.util.z;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.analytics.MobclickAgent;
import com.volokh.danylo.visibility_utils.a.b;
import com.volokh.danylo.visibility_utils.a.c;
import com.volokh.danylo.visibility_utils.a.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoPlayVideoListActivity extends OauthWeiboBaseAct implements OnClickListener, a {
    public static AlphaAnimation a = new AlphaAnimation(0.0f, 1.0f);
    public static AlphaAnimation b = new AlphaAnimation(1.0f, 0.0f);
    public static List<ListItemObject> g = new ArrayList();
    private static int x;
    private a A;
    private final c B = new d(new b(), g);
    private com.volokh.danylo.visibility_utils.scroll_utils.a C;
    private int D;
    private c.a E;
    public SharedPreferences c;
    public String d;
    public com.budejie.www.g.b e;
    public IWXAPI f;
    @SuppressLint({"HandlerLeak"})
    public Handler h = new Handler(this) {
        final /* synthetic */ AutoPlayVideoListActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = 0;
            int i2 = message.what;
            if (i2 != 601 && i2 == 817) {
                this.a.s.a(((Integer) message.obj).intValue());
            }
            String str;
            Map c;
            String str2;
            int i3;
            if (i2 == 929) {
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    an.a(this.a, this.a.getString(R.string.bind_failed), -1).show();
                    MobclickAgent.onEvent(this.a, "weibo_bind", "qzone_faild");
                } else {
                    try {
                        i = Integer.parseInt(str);
                    } catch (NumberFormatException e) {
                    }
                    if (i < 0) {
                        an.a(this.a, this.a.getString(R.string.bind_failed), -1).show();
                        MobclickAgent.onEvent(this.a, "weibo_bind", "qzone_faild");
                    } else {
                        c = z.c(str);
                        if (c == null || c.isEmpty()) {
                            an.a(this.a, this.a.getString(R.string.bind_failed), -1).show();
                            MobclickAgent.onEvent(this.a, "weibo_bind", "qzone_faild");
                        } else {
                            str2 = (String) c.get("result_msg");
                            if ("0".equals((String) c.get(j.c))) {
                                MobclickAgent.onEvent(this.a, "weibo_bind", "qzone_success");
                                this.a.d = (String) c.get("id");
                                this.a.o.a(this.a.d, c);
                                ai.a(this.a, this.a.d, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                this.a.n = this.a.p.a(this.a.d);
                                an.a(this.a, this.a.getString(R.string.bind_successed), -1).show();
                                i3 = this.a.c.getInt("position", -1);
                                if (-1 != i3) {
                                    this.a.p.a(this.a, (ListItemObject) this.a.A.getItem(i3), com.tencent.connect.common.Constants.SOURCE_QZONE, this.a.d, this.a.n, this.a.s, (Handler) this);
                                }
                            } else {
                                an.a(this.a, str2, -1).show();
                            }
                        }
                    }
                }
                this.a.h.sendEmptyMessage(815);
            } else if (i2 == 812) {
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    an.a(this.a, this.a.getString(R.string.bind_failed), -1).show();
                    MobclickAgent.onEvent(this.a, "weibo_bind", "sina_faild");
                } else {
                    try {
                        i = Integer.parseInt(str);
                    } catch (NumberFormatException e2) {
                    }
                    if (i < 0) {
                        an.a(this.a, this.a.getString(R.string.bind_failed), -1);
                        MobclickAgent.onEvent(this.a, "weibo_bind", "sina_faild");
                    } else {
                        MobclickAgent.onEvent(this.a, "weibo_bind", "sina_success");
                        c = z.c(str);
                        if (c == null || c.isEmpty()) {
                            MobclickAgent.onEvent(this.a, "weibo_bind", "sina_faild");
                            an.a(this.a, this.a.getString(R.string.bind_failed), -1).show();
                        } else {
                            str2 = (String) c.get("result_msg");
                            if ("0".equals((String) c.get(j.c))) {
                                this.a.d = (String) c.get("id");
                                this.a.o.a(this.a.d, c);
                                ai.a(this.a, this.a.d, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                if (OauthWeiboBaseAct.mAccessToken != null) {
                                    this.a.o.a(this.a.d, OauthWeiboBaseAct.mAccessToken.e());
                                }
                                this.a.n = this.a.p.a(this.a.d);
                                an.a(this.a, this.a.getString(R.string.bind_successed), -1).show();
                                i3 = this.a.c.getInt("position", -1);
                                if (!(-1 == i3 || this.a.A == null || this.a.A.getCount() <= i3)) {
                                    this.a.p.a(this.a, (ListItemObject) this.a.A.getItem(i3), "sina", this.a.d, this.a.n, this.a.s, (Handler) this);
                                    this.a.A.notifyDataSetChanged();
                                }
                            } else {
                                an.a(this.a, str2, -1).show();
                            }
                        }
                    }
                }
                this.a.h.sendEmptyMessage(815);
            } else if (i2 == 813) {
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    an.a(this.a, this.a.getString(R.string.bind_failed), -1).show();
                    MobclickAgent.onEvent(this.a, "weibo_bind", "tencent_faild");
                } else {
                    try {
                        i = Integer.parseInt(str);
                    } catch (NumberFormatException e3) {
                    }
                    if (i < 0) {
                        an.a(this.a, this.a.getString(R.string.bind_failed), -1).show();
                        MobclickAgent.onEvent(this.a, "weibo_bind", "tencent_faild");
                    } else {
                        c = z.c(str);
                        if (c == null || c.isEmpty()) {
                            MobclickAgent.onEvent(this.a, "weibo_bind", "tencent_faild");
                            an.a(this.a, this.a.getString(R.string.bind_failed), -1).show();
                        } else {
                            str2 = (String) c.get("result_msg");
                            if ("0".equals((String) c.get(j.c))) {
                                MobclickAgent.onEvent(this.a, "weibo_bind", "tencent_success");
                                this.a.d = (String) c.get("id");
                                this.a.o.a(this.a.d, c);
                                ai.a(this.a, this.a.d, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                this.a.n = this.a.p.a(this.a.d);
                                an.a(this.a, this.a.getString(R.string.bind_successed), -1).show();
                                i3 = this.a.c.getInt("position", -1);
                                if (-1 != i3) {
                                    this.a.p.a(this.a, (ListItemObject) this.a.A.getItem(i3), "qq", this.a.d, this.a.n, this.a.s, (Handler) this);
                                }
                            } else {
                                an.a(this.a, str2, -1).show();
                            }
                        }
                    }
                }
                this.a.h.sendEmptyMessage(815);
            } else if (i2 == 816) {
                this.a.k.g().ae.a();
                CharSequence string = ((Bundle) message.obj).getString(j.c);
                if (TextUtils.isEmpty(string)) {
                    an.a(this.a.j, this.a.j.getString(R.string.forwarfail), -1).show();
                } else if ("0".equals(string)) {
                    this.a.i.sendEmptyMessage(9);
                } else {
                    an.a(this.a.j, this.a.j.getString(R.string.forwarfail), -1).show();
                }
            } else if (i2 == 817) {
                this.a.s.a(((Integer) message.obj).intValue());
            } else if (i2 == 0 && SearchMainActivity.a != 0 && SearchMainActivity.a < AutoPlayVideoListActivity.g.size()) {
                if (SearchMainActivity.a > 1) {
                    this.a.y.setSelection(SearchMainActivity.a - 1);
                }
                ListItemObject listItemObject = (ListItemObject) this.a.A.getItem(SearchMainActivity.a);
                this.a.a(SearchMainActivity.a, com.budejie.www.adapter.b.a.a(listItemObject.getWidth(), listItemObject.getHeight()));
            }
        }
    };
    @SuppressLint({"HandlerLeak"})
    final Handler i = new Handler(this) {
        final /* synthetic */ AutoPlayVideoListActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            boolean z = false;
            int i = message.what;
            if (i == 4) {
                this.a.l.setLove(this.a.l.getLove() + 1);
            } else if (i == 5) {
                try {
                    this.a.t = ProgressDialog.show(this.a, "", (String) message.obj, true, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (i == 6) {
                if (this.a.t != null) {
                    this.a.t.cancel();
                }
            } else if (i == 7) {
                an.a(this.a, this.a.getString(R.string.already_collected), -1).show();
            } else if (i == 9) {
                if (this.a.l != null) {
                    try {
                        r0 = Integer.parseInt(this.a.l.getRepost()) + 1;
                    } catch (NumberFormatException e2) {
                    }
                    this.a.l.setRepost(String.valueOf(r0));
                    Map hashMap = new HashMap();
                    hashMap.put("type", an.g(this.a.l.getType()));
                    hashMap.put("title", this.a.l.getContent());
                    hashMap.put("label", this.a.l.getPlateNames());
                    an.a(this.a, hashMap, "E01_A04");
                    m.a(this.a.j, this.a.i, this.a.l);
                }
            } else if (i == 91) {
                if (this.a.l != null) {
                    try {
                        r0 = Integer.parseInt(this.a.l.getRepost()) + 1;
                    } catch (NumberFormatException e3) {
                    }
                    this.a.l.setRepost(String.valueOf(r0));
                }
            } else if (i == 10) {
                an.a(this.a, this.a.getString(R.string.collect_failed), -1).show();
            } else if (i == 11) {
                CharSequence b = ai.b(this.a);
                if (an.j(this.a) && an.k(this.a) && !b.equals("")) {
                    an.a(this.a, z);
                    sendEmptyMessage(13);
                } else {
                    an.a(this.a, (int) R.string.collected, (int) R.drawable.collect_tip).show();
                }
                if (!TextUtils.isEmpty(b)) {
                    this.a.u = "add";
                    this.a.v.a(this.a.u, ai.b(this.a), (String) message.obj, 971);
                }
            } else if (i == 12) {
                an.a(this.a, (int) R.string.collect_fail, (int) R.drawable.collect_tip).show();
            } else if (i == 100001) {
                an.a(this.a.j, this.a.j.getString(R.string.forwardAndCollect_succeed), -1).show();
                if (!TextUtils.isEmpty(ai.b(this.a))) {
                    this.a.u = "add";
                    this.a.v.a(this.a.u, ai.b(this.a), (String) message.obj, 971);
                }
            } else if (i == 829) {
                String str = (String) message.obj;
                this.a.r.a("collectTable", str);
                an.a(this.a, this.a.getString(R.string.delete_success), -1).show();
                this.a.u = "delete";
                this.a.v.a(this.a.u, ai.b(this.a), str, 971);
            }
            if (this.a.A != null) {
                this.a.A.notifyDataSetChanged();
            }
        }
    };
    private AutoPlayVideoListActivity j;
    private BudejieApplication k;
    private ListItemObject l;
    private f m;
    private HashMap<String, String> n;
    private com.budejie.www.c.m o;
    private n p;
    private com.budejie.www.c.d q;
    private com.budejie.www.c.b r;
    private com.elves.update.a s;
    private ProgressDialog t;
    private String u = "add";
    private com.budejie.www.http.b v;
    private long w = 0;
    private ListView y;
    private ImageView z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.j = this;
        setContentView(R.layout.activity_auto_play_video_list);
        c();
        d();
    }

    public void onResume() {
        super.onResume();
        if (!g.isEmpty()) {
            this.y.post(new Runnable(this) {
                final /* synthetic */ AutoPlayVideoListActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.B.a(this.a.C, this.a.y.getFirstVisiblePosition(), this.a.y.getLastVisiblePosition());
                }
            });
        }
    }

    protected void onPause() {
        super.onPause();
        k.a((Context) this).c(true);
    }

    protected void onDestroy() {
        super.onDestroy();
        k.a((Context) this).p();
    }

    private void c() {
        this.k = (BudejieApplication) this.j.getApplication();
        this.c = getSharedPreferences("weiboprefer", 0);
        this.d = this.c.getString("id", "");
        this.e = new com.budejie.www.g.b(this, this.mSsoHandler, this.mTencent, this);
        this.n = com.budejie.www.h.b.a().a(this.d, this);
        this.r = new com.budejie.www.c.b(this);
        this.q = new com.budejie.www.c.d(this);
        this.m = new f(this);
        this.s = new com.elves.update.a(this);
        this.p = new n(this);
        this.o = new com.budejie.www.c.m(this);
        this.f = WXAPIFactory.createWXAPI(this, "wx592fdc48acfbe290", true);
        this.f.registerApp("wx592fdc48acfbe290");
        this.v = com.budejie.www.http.b.a(this, null);
    }

    private void d() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.y = (ListView) findViewById(R.id.listview);
        this.z = (ImageView) findViewById(R.id.title_left);
        this.z.setOnClickListener(this);
        this.E = new c.a(this) {
            final /* synthetic */ AutoPlayVideoListActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                ListItemObject listItemObject = (ListItemObject) this.a.A.getItem(i);
                this.a.a(i, com.budejie.www.adapter.b.a.a(listItemObject.getWidth(), listItemObject.getHeight()));
            }
        };
        this.A = new a(this, this, this.E);
        this.A.a(g);
        this.y.setAdapter(this.A);
        this.C = new com.volokh.danylo.visibility_utils.scroll_utils.b(this.y);
        this.y.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ AutoPlayVideoListActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (System.currentTimeMillis() - this.a.w > 500) {
                    this.a.a(i, view.getHeight());
                    this.a.w = System.currentTimeMillis();
                }
            }
        });
        this.y.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ AutoPlayVideoListActivity a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                aa.b("AutoPlayVideoListActivity", "onScrollStateChanged firstVisibleItem=" + absListView.getFirstVisiblePosition() + ",LastVisiblePosition=" + absListView.getLastVisiblePosition());
                this.a.D = i;
                if (i == 0 && !AutoPlayVideoListActivity.g.isEmpty()) {
                    this.a.B.a(this.a.C, absListView.getFirstVisiblePosition(), absListView.getLastVisiblePosition());
                }
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                aa.b("AutoPlayVideoListActivity", "onScroll firstVisibleItem=" + i + ",visibleItemCount=" + i2);
                k.a(this.a.j).a(absListView.getFirstVisiblePosition(), absListView.getLastVisiblePosition(), this.a.y.getHeaderViewsCount());
                if (!AutoPlayVideoListActivity.g.isEmpty()) {
                    this.a.B.a(this.a.C, i, i2, this.a.D);
                }
            }
        });
        this.h.sendEmptyMessageDelayed(0, 100);
    }

    public void a(int i, int i2) {
        aa.b("AudoPlayVideoListActivity", "smoothScrollToPostion itemheight=" + i2);
        this.y.smoothScrollToPositionFromTop(i, (com.budejie.www.activity.video.a.b(this) - i2) / 2, 500);
    }

    public void e(View view, ListItemObject listItemObject) {
        view.setTag(listItemObject);
        this.e.a(3, null).onClick(view);
    }

    public void a(View view, ListItemObject listItemObject) {
        this.m.a("ding", this.h, listItemObject);
        this.m.a(listItemObject, this.h, "ding");
    }

    public void b(View view, ListItemObject listItemObject) {
        this.m.a("cai", this.h, listItemObject);
        this.m.a(listItemObject, this.h, "cai");
    }

    public void a(View view, ListItemObject listItemObject, int i) {
        listItemObject.setForwardNoCollect(false);
        this.l = listItemObject;
        Bundle bundle = new Bundle();
        bundle.putInt("position", i);
        bundle.putString(HistoryOpenHelper.COLUMN_UID, ai.b(this.j));
        bundle.putSerializable("weiboMap", this.n);
        bundle.putSerializable("data", listItemObject);
        view.setTag(listItemObject);
        this.e.a(5, bundle, this.i, this.f, this.o, this.p, this.s, this.c, this.h).onClick(view);
    }

    public void d(View view, ListItemObject listItemObject) {
        view.setTag(listItemObject);
        this.e.a(3, null).onClick(view);
    }

    public void a() {
    }

    public void a(View view, ListItemObject listItemObject, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(PersonalProfileActivity.c, listItemObject.getUid());
        bundle.putString(PersonalProfileActivity.d, str);
        this.e.a(7, bundle).onClick(view);
    }

    public void a_(String str) {
    }

    public OnClickListener b() {
        return null;
    }

    public void a(SuggestedFollowsListItem suggestedFollowsListItem) {
    }

    public void a(ListItemObject listItemObject, int i) {
    }

    public void c(View view, ListItemObject listItemObject) {
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left:
                e();
                return;
            default:
                return;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            e();
        }
        return false;
    }

    private void e() {
        if (SearchMainActivity.a != x) {
            SearchMainActivity.b = true;
            SearchMainActivity.a = x;
        }
        this.j.finish();
    }

    public static void a(int i) {
        x = i;
        if (g != null && x == g.size() - 1) {
            SearchMainActivity.c = true;
        }
    }

    public void onSuccess(com.sina.weibo.sdk.auth.b bVar) {
        try {
            this.d = this.c.getString("id", "");
            mAccessToken = bVar;
            if (mAccessToken.a()) {
                com.sina.weibo.sdk.auth.a.a(this, mAccessToken);
                au.a((int) R.string.oauthSuccess);
                this.p.a(mAccessToken, this.d, 812, this.h);
            }
        } catch (Exception e) {
            au.a((int) R.string.sina_shouquan_failed);
        }
    }
}
