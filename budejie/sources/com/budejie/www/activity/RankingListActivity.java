package com.budejie.www.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.ali.auth.third.core.model.Constants;
import com.alipay.sdk.util.j;
import com.androidex.util.ClickControlledSpinner;
import com.androidex.util.ClickControlledSpinner.OnClickMyListener;
import com.budejie.www.R;
import com.budejie.www.activity.TipPopUp.TypeControl;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.activity.video.k;
import com.budejie.www.bean.Fans;
import com.budejie.www.bean.ListInfo;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.ResultBean;
import com.budejie.www.bean.SuggestedFollowsListItem;
import com.budejie.www.c.b;
import com.budejie.www.c.d;
import com.budejie.www.c.g;
import com.budejie.www.c.h;
import com.budejie.www.c.m;
import com.budejie.www.f.c;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.f;
import com.budejie.www.http.i;
import com.budejie.www.http.n;
import com.budejie.www.type.FriendsFeedList;
import com.budejie.www.type.FriendsFeedList.Data;
import com.budejie.www.type.FriendsFeedList.Info;
import com.budejie.www.type.UpdateUserInfo;
import com.budejie.www.util.aa;
import com.budejie.www.util.ai;
import com.budejie.www.util.am;
import com.budejie.www.util.an;
import com.budejie.www.util.as;
import com.budejie.www.util.au;
import com.budejie.www.util.p;
import com.budejie.www.util.z;
import com.budejie.www.widget.XListView;
import com.google.gson.Gson;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.UiError;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.HttpState;
import org.json.JSONObject;

public class RankingListActivity extends OauthWeiboBaseAct implements OnClickListener, OnClickMyListener, com.budejie.www.f.a, c, com.budejie.www.widget.XListView.a {
    private Dialog A;
    private Animation B;
    private f C;
    private d D;
    private h E;
    private Toast F;
    private Activity G;
    private b H;
    private String I;
    private String J;
    private g K;
    private LinearLayout L;
    private List<ListItemObject> M;
    private int N;
    private String O = "0";
    private net.tsz.afinal.a.a<String> P = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ RankingListActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
            if (!this.a.isFinishing() && this.a.l.isEmpty()) {
                try {
                    this.a.A.show();
                } catch (Exception e) {
                }
            }
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            this.a.k.c();
            this.a.k.b();
            if (this.a.A.isShowing()) {
                this.a.A.dismiss();
            }
            this.a.F = an.a(this.a.G, this.a.getString(R.string.load_failed), -1);
            this.a.F.show();
        }

        public void a(String str) {
            boolean z = true;
            super.onSuccess(str);
            k.a(this.a.G).p();
            aa.a("NewsFeedActivity", str);
            this.a.k.c();
            this.a.k.b();
            if (this.a.A.isShowing()) {
                this.a.A.dismiss();
            }
            try {
                ListInfo a = com.budejie.www.j.a.a(str);
                FriendsFeedList friendsFeedList = new FriendsFeedList();
                friendsFeedList.setCode(String.valueOf(a.code));
                if ("0".equals(friendsFeedList.getCode())) {
                    friendsFeedList.getClass();
                    Data data = new Data();
                    friendsFeedList.getClass();
                    Info info = new Info();
                    info.setReadid(String.valueOf(a.np));
                    if (a.np == 0) {
                        z = false;
                    }
                    info.setHasdata(z);
                    data.setInfo(info);
                    this.a.O = String.valueOf(a.np);
                    List<ListItemObject> a2 = com.budejie.www.j.a.a(this.a, str);
                    data.setList(a2);
                    friendsFeedList.setData(data);
                    if ((a2 == null || a2.size() <= 0) && this.a.l.getCount() <= 0) {
                        this.a.k.setVisibility(4);
                        this.a.k.setPullRefreshEnable(false);
                        this.a.k.setPullLoadEnable(false);
                    } else {
                        this.a.k.setVisibility(0);
                        this.a.k.setPullRefreshEnable(true);
                        this.a.k.setPullLoadEnable(true);
                    }
                    for (ListItemObject listItemObject : a2) {
                        listItemObject.setCmdShowTime(listItemObject.getAddtime());
                        listItemObject.setAddtime(listItemObject.getPasstime());
                    }
                    if (!this.a.ac) {
                        aa.a("NewsFeedActivity", "deleteNewCacheTable");
                        this.a.H.d("0");
                    }
                    aa.a("NewsFeedActivity", "database.insertNewCache");
                    this.a.H.a((List) a2, data.getInfo().getReadid(), data.getInfo().getHasdata(), "0");
                    an.a((List) a2, this.a.E, this.a.D);
                    this.a.M.clear();
                    this.a.M = a2;
                    if (this.a.a == null || !this.a.ac) {
                        this.a.l.a(a2);
                    } else {
                        this.a.l.b(a2);
                    }
                    this.a.a = data.getInfo();
                    if (this.a.a == null || !this.a.a.getHasdata()) {
                        this.a.k.setPullLoadEnable(false);
                    } else {
                        this.a.k.setPullLoadEnable(true);
                    }
                } else if ("2".equals(friendsFeedList.getCode())) {
                    this.a.k.setPullLoadEnable(false);
                    aa.a("NewsFeedActivity", "database.deleteNewCacheTable");
                    this.a.H.d("0");
                    this.a.l.b();
                    if (an.a(this.a.q)) {
                        this.a.k.setPullRefreshEnable(false);
                        this.a.k.setPullLoadEnable(false);
                        this.a.k.setVisibility(4);
                    }
                } else {
                    String string = this.a.getString(R.string.load_failed);
                    if (!(friendsFeedList == null || TextUtils.isEmpty(friendsFeedList.getMsg()))) {
                        string = friendsFeedList.getMsg();
                    }
                    this.a.F = an.a(this.a.G, string, -1);
                    this.a.F.show();
                    if (an.a(this.a.q)) {
                        this.a.k.setPullRefreshEnable(false);
                        this.a.k.setPullLoadEnable(false);
                        this.a.k.setVisibility(4);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.a.F = an.a(this.a.G, this.a.getString(R.string.load_failed), -1);
                this.a.F.show();
            }
        }
    };
    private ClickControlledSpinner Q;
    private ClickControlledSpinner R;
    private ClickControlledSpinner S;
    private a T;
    private a U;
    private a V;
    private String[][] W;
    private String[][] X;
    private String[][] Y;
    private OnItemSelectedListener Z = new OnItemSelectedListener(this) {
        final /* synthetic */ RankingListActivity a;

        {
            this.a = r1;
        }

        public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
            if (this.a.N > 2) {
                this.a.k.d();
                this.a.Q.setOnClicked(false);
                this.a.R.setOnClicked(false);
                this.a.S.setOnClicked(false);
                this.a.f.sendEmptyMessage(0);
            }
            this.a.N = this.a.N + 1;
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
            this.a.Q.setOnClicked(false);
            this.a.R.setOnClicked(false);
            this.a.S.setOnClicked(false);
            this.a.f.sendEmptyMessage(0);
        }
    };
    public Info a;
    private com.budejie.www.adapter.e.a aa = new com.budejie.www.adapter.e.a(this) {
        final /* synthetic */ RankingListActivity a;

        {
            this.a = r1;
        }

        public void a(View view, ListItemObject listItemObject) {
            TipPopUp.a(this.a, TypeControl.dingtie);
            this.a.C.a("ding", this.a.d, listItemObject);
            this.a.C.a(listItemObject, this.a.d, "ding");
        }

        public void b(View view, ListItemObject listItemObject) {
            this.a.C.a("cai", this.a.d, listItemObject);
            this.a.C.a(listItemObject, this.a.d, "cai");
        }

        public void a(View view, ListItemObject listItemObject, String str) {
            Bundle bundle = new Bundle();
            bundle.putString(PersonalProfileActivity.c, listItemObject.getUid());
            bundle.putString(PersonalProfileActivity.d, str);
            this.a.m.a(7, bundle).onClick(view);
        }

        public void c(View view, ListItemObject listItemObject) {
            p.a(this.a, listItemObject, this.a.e);
        }

        public void a(View view, ListItemObject listItemObject, int i) {
            this.a.u = listItemObject;
            Bundle bundle = new Bundle();
            bundle.putInt("position", i);
            bundle.putString(HistoryOpenHelper.COLUMN_UID, ai.b(this.a));
            bundle.putSerializable("weiboMap", this.a.s);
            bundle.putSerializable("data", listItemObject);
            view.setTag(listItemObject);
            this.a.m.a(5, bundle, this.a.e, this.a.r, this.a.n, this.a.o, this.a.p, this.a.q, this.a.d).onClick(view);
        }

        public void d(View view, ListItemObject listItemObject) {
            view.setTag(listItemObject);
            this.a.m.a(3, null).onClick(view);
        }

        public void a() {
        }

        public void e(View view, ListItemObject listItemObject) {
            view.setTag(listItemObject);
            this.a.m.a(3, null).onClick(view);
        }

        public void a_(String str) {
        }

        public OnClickListener b() {
            return null;
        }

        public void a(SuggestedFollowsListItem suggestedFollowsListItem) {
            if (an.a(this.a.G)) {
                int i = suggestedFollowsListItem.is_follow;
                if (i != 1 && i == 0) {
                    this.a.a(suggestedFollowsListItem);
                    return;
                }
                return;
            }
            an.a(this.a.G, this.a.G.getString(R.string.nonet), -1).show();
        }

        public void a(ListItemObject listItemObject, int i) {
            Intent intent = new Intent(this.a.G, CommonLabelActivity.class);
            PlateBean plateBean = listItemObject.getPlateBean(i);
            if (plateBean != null) {
                intent.putExtra("theme_name", plateBean.theme_name);
                intent.putExtra("theme_id", plateBean.theme_id);
                this.a.G.startActivity(intent);
            }
        }
    };
    private OnClickListener ab = new OnClickListener(this) {
        final /* synthetic */ RankingListActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view.getId() == R.id.mycomment_delete_cancelBtn) {
                this.a.z.dismiss();
            } else if (view.getId() == R.id.mycomment_delete_sureBtn) {
                this.a.z.dismiss();
                ArrayList a = this.a.D.a();
                if (a != null && !a.isEmpty()) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < a.size(); i++) {
                        if (i == a.size() - 1) {
                            stringBuffer.append((String) a.get(i));
                        } else {
                            stringBuffer.append((String) a.get(i)).append(",");
                        }
                    }
                    this.a.x.a("add", this.a.t, stringBuffer.toString(), 971);
                }
            }
        }
    };
    private boolean ac;
    private net.tsz.afinal.a.a<String> ad = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ RankingListActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onFailure(Throwable th, int i, String str) {
        }

        public void a(String str) {
            try {
                String followCount = ((UpdateUserInfo) new Gson().fromJson(str, UpdateUserInfo.class)).getFollowCount();
                if (TextUtils.isEmpty(followCount)) {
                    this.a.n.a("follow_count", "0", this.a.t);
                } else {
                    this.a.n.a("follow_count", followCount, this.a.t);
                }
                int i = 0;
                try {
                    i = Integer.parseInt(followCount);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (i != this.a.c) {
                    this.a.a("");
                }
                this.a.c = i;
            } catch (Exception e2) {
                e2.printStackTrace();
                if (!this.a.G.isFinishing()) {
                    this.a.F = an.a(this.a.G, this.a.getString(R.string.load_failed), -1);
                    this.a.F.show();
                }
            }
        }
    };
    private boolean ae = false;
    public SharedPreferences b;
    int c;
    @SuppressLint({"HandlerLeak"})
    Handler d = new Handler(this) {
        final /* synthetic */ RankingListActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            boolean z = false;
            int i = message.what;
            if (i == 814) {
                this.a.A.show();
            }
            String str;
            int parseInt;
            Map c;
            String str2;
            if (i == 929) {
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    an.a(this.a, this.a.getString(R.string.bind_failed), -1).show();
                    MobclickAgent.onEvent(this.a, "weibo_bind", "qzone_faild");
                } else {
                    try {
                        parseInt = Integer.parseInt(str);
                    } catch (NumberFormatException e) {
                    }
                    if (parseInt < 0) {
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
                                this.a.t = (String) c.get("id");
                                this.a.n.a(this.a.t, c);
                                ai.a(this.a.G, this.a.t, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                this.a.s = this.a.o.a(this.a.t);
                                an.a(this.a, this.a.getString(R.string.bind_successed), -1).show();
                                i = this.a.q.getInt("position", -1);
                                if (-1 != i) {
                                    this.a.o.a(this.a, (ListItemObject) this.a.l.getItem(i), com.tencent.connect.common.Constants.SOURCE_QZONE, this.a.t, this.a.s, this.a.p, (Handler) this);
                                }
                                if (this.a.i()) {
                                    if (this.a.z == null) {
                                        this.a.j();
                                    } else {
                                        this.a.z.show();
                                    }
                                }
                            } else {
                                an.a(this.a, str2, -1).show();
                            }
                        }
                    }
                }
                this.a.d.sendEmptyMessage(815);
            } else if (i == 812) {
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    an.a(this.a, this.a.getString(R.string.bind_failed), -1).show();
                    MobclickAgent.onEvent(this.a, "weibo_bind", "sina_faild");
                } else {
                    try {
                        parseInt = Integer.parseInt(str);
                    } catch (NumberFormatException e2) {
                    }
                    if (parseInt < 0) {
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
                                this.a.t = (String) c.get("id");
                                this.a.n.a(this.a.t, c);
                                ai.a(this.a.G, this.a.t, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                if (OauthWeiboBaseAct.mAccessToken != null) {
                                    this.a.n.a(this.a.t, OauthWeiboBaseAct.mAccessToken.e());
                                }
                                this.a.s = this.a.o.a(this.a.t);
                                an.a(this.a, this.a.getString(R.string.bind_successed), -1).show();
                                i = this.a.q.getInt("position", -1);
                                if (-1 != i) {
                                    this.a.o.a(this.a, (ListItemObject) this.a.l.getItem(i), "sina", this.a.t, this.a.s, this.a.p, (Handler) this);
                                }
                                this.a.l.notifyDataSetChanged();
                                if (this.a.i()) {
                                    if (this.a.z == null) {
                                        this.a.j();
                                    } else {
                                        this.a.z.show();
                                    }
                                }
                            } else {
                                an.a(this.a, str2, -1).show();
                            }
                        }
                    }
                }
                this.a.d.sendEmptyMessage(815);
            } else if (i == 813) {
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    an.a(this.a, this.a.getString(R.string.bind_failed), -1).show();
                    MobclickAgent.onEvent(this.a, "weibo_bind", "tencent_faild");
                } else {
                    try {
                        parseInt = Integer.parseInt(str);
                    } catch (NumberFormatException e3) {
                    }
                    if (parseInt < 0) {
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
                                this.a.t = (String) c.get("id");
                                this.a.n.a(this.a.t, c);
                                ai.a(this.a.G, this.a.t, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                this.a.s = this.a.o.a(this.a.t);
                                an.a(this.a, this.a.getString(R.string.bind_successed), -1).show();
                                i = this.a.q.getInt("position", -1);
                                if (-1 != i) {
                                    this.a.o.a(this.a, (ListItemObject) this.a.l.getItem(i), "qq", this.a.t, this.a.s, this.a.p, (Handler) this);
                                }
                                this.a.l.notifyDataSetChanged();
                                if (this.a.i()) {
                                    if (this.a.z == null) {
                                        this.a.j();
                                    } else {
                                        this.a.z.show();
                                    }
                                }
                            } else {
                                an.a(this.a, str2, -1).show();
                            }
                        }
                    }
                }
                this.a.d.sendEmptyMessage(815);
            } else if (i == 816) {
                Bundle bundle = (Bundle) message.obj;
                CharSequence string = bundle.getString(j.c);
                i = bundle.getInt("notificationId");
                if (TextUtils.isEmpty(string)) {
                    this.a.p.a(i, z, (int) R.string.forwarfail);
                } else if ("0".equals(string)) {
                    this.a.p.a(i, true, (int) R.string.forwardsuccess);
                } else {
                    this.a.p.a(i, z, (int) R.string.forwarfail);
                }
                new Thread(this) {
                    final /* synthetic */ AnonymousClass6 b;

                    public void run() {
                        try {
                            Thread.sleep(1000);
                            this.b.a.d.sendMessage(this.b.a.d.obtainMessage(817, Integer.valueOf(i)));
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
            } else if (i == 817) {
                this.a.p.a(((Integer) message.obj).intValue());
            }
        }
    };
    @SuppressLint({"HandlerLeak"})
    final Handler e = new Handler(this) {
        final /* synthetic */ RankingListActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 4) {
                this.a.u.setLove(this.a.u.getLove() + 1);
            } else if (i == 5) {
                this.a.v = ProgressDialog.show(this.a, "", (String) message.obj, true, true);
            } else if (i == 6) {
                this.a.v.cancel();
            } else if (i == 7) {
                an.a(this.a, this.a.getString(R.string.already_collected), -1).show();
            } else if (i == 9) {
                this.a.u.setRepost(String.valueOf(Integer.parseInt(this.a.u.getRepost()) + 1));
            } else if (i == 10) {
                an.a(this.a, this.a.getString(R.string.collect_failed), -1).show();
            } else if (i == 11) {
                CharSequence b = ai.b(this.a);
                if (an.j(this.a) && an.k(this.a) && !b.equals("")) {
                    an.a(this.a, false);
                    sendEmptyMessage(13);
                } else {
                    an.a(this.a, (int) R.string.collected, (int) R.drawable.collect_tip).show();
                }
                if (!TextUtils.isEmpty(b)) {
                    this.a.w = "add";
                    this.a.x.a(this.a.w, ai.b(this.a), (String) message.obj, 971);
                }
            } else if (i == 12) {
                an.a(this.a, (int) R.string.collect_fail, (int) R.drawable.collect_tip).show();
            } else if (i == 829) {
                r0 = (String) message.obj;
                this.a.y.a("collectTable", r0);
                an.a(this.a, this.a.getString(R.string.delete_success), -1).show();
                this.a.w = "delete";
                this.a.x.a(this.a.w, ai.b(this.a), r0, 971);
            } else if (i == 13) {
                an.b(this.a, this.a.k);
            } else if (i == 1001) {
                HashMap k = z.k((String) message.obj);
                if (k != null) {
                    r0 = (String) k.get("result_desc");
                    if (TextUtils.isEmpty(r0)) {
                        this.a.F = an.a(this.a.G, this.a.G.getString(R.string.operate_fail), -1);
                    } else {
                        this.a.F = an.a(this.a.G, r0, -1);
                    }
                } else {
                    this.a.F = an.a(this.a.G, this.a.G.getString(R.string.operate_fail), -1);
                }
                if (this.a.F != null) {
                    this.a.F.show();
                }
            } else if (i == 1006 && ((ListItemObject) message.obj) != null) {
                this.a.a((ListItemObject) message.obj);
            }
            this.a.l.notifyDataSetChanged();
        }
    };
    final Handler f = new Handler(this) {
        final /* synthetic */ RankingListActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            this.a.T.notifyDataSetChanged();
            this.a.U.notifyDataSetChanged();
            this.a.V.notifyDataSetChanged();
        }
    };
    private RelativeLayout g;
    private LinearLayout h;
    private Button i;
    private TextView j;
    private XListView k;
    private com.budejie.www.adapter.c.a l;
    private com.budejie.www.g.b m;
    private m n;
    private n o;
    private com.elves.update.a p;
    private SharedPreferences q;
    private IWXAPI r;
    private HashMap<String, String> s;
    private String t;
    private ListItemObject u;
    private ProgressDialog v;
    private String w = "add";
    private com.budejie.www.http.b x;
    private b y;
    private Dialog z;

    private class a extends ArrayAdapter<String> {
        Context a;
        ClickControlledSpinner b;
        String[] c = new String[0];
        final /* synthetic */ RankingListActivity d;

        public a(RankingListActivity rankingListActivity, Context context, int i, String[] strArr, ClickControlledSpinner clickControlledSpinner) {
            this.d = rankingListActivity;
            super(context, i, strArr);
            this.c = strArr;
            this.a = context;
            this.b = clickControlledSpinner;
        }

        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(this.a).inflate(R.layout.simple_spinner_dropdown_item, viewGroup, false);
            }
            TextView textView = (TextView) view.findViewById(16908308);
            textView.setText(this.c[i]);
            if (this.b.getSelectedItemPosition() == i) {
                textView.setTextColor(com.budejie.www.h.c.a().c(R.attr.ranking_list_title_color_selected));
            } else {
                textView.setTextColor(com.budejie.www.h.c.a().c(R.attr.ranking_list_title_color));
            }
            return view;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(this.a).inflate(R.layout.simple_spinner_item, viewGroup, false);
            }
            TextView textView = (TextView) view.findViewById(16908308);
            textView.setText(this.c[i]);
            if (this.b.isOnClicked()) {
                textView.setTextColor(com.budejie.www.h.c.a().c(R.attr.ranking_list_title_color_selected));
                this.b.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.ranking_list_title_bg_selected));
            } else {
                textView.setTextColor(com.budejie.www.h.c.a().c(R.attr.ranking_list_title_color));
                this.b.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.ranking_list_title_bg));
            }
            return view;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        aa.a("RankingListActivity", "onCreate()");
        try {
            setTheme(com.budejie.www.h.c.a().a(ai.a(this)));
            setContentView(R.layout.ranking_list_layout);
            this.G = this;
            this.N = 0;
            g();
            this.H = new b(this);
            this.l = new com.budejie.www.adapter.c.a((Activity) this, this.aa, true);
            this.M = new ArrayList();
            this.k.setAdapter(this.l);
            this.B = AnimationUtils.loadAnimation(this, R.anim.refresh_button_rotating);
            this.B.setInterpolator(new LinearInterpolator());
            this.A = new Dialog(this, R.style.dialogTheme);
            this.A.setContentView(R.layout.loaddialog);
            this.A.setCanceledOnTouchOutside(true);
            this.C = new f(this);
            this.E = new h(this);
            this.D = new d(this);
            e();
            this.c = this.n.f(this.t);
            this.b = this.G.getSharedPreferences("weiboprefer", 0);
            this.K = new g(this);
            d();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void d() {
        f();
    }

    protected void onResume() {
        super.onResume();
        this.m = new com.budejie.www.g.b(this, this.mSsoHandler, this.mTencent, this);
        i.a((int) R.string.track_screen_attention);
    }

    protected void onPause() {
        super.onPause();
        k.a(this.G).o();
    }

    protected void onDestroy() {
        super.onDestroy();
        k.a((Context) this).p();
    }

    private void e() {
        this.q = getSharedPreferences("weiboprefer", 0);
        this.t = this.q.getString("id", "");
        this.m = new com.budejie.www.g.b(this, this.mSsoHandler, this.mTencent, this);
        this.n = new m(this);
        this.p = new com.elves.update.a(this);
        this.x = com.budejie.www.http.b.a(this, this);
        this.y = new b(this);
        this.o = new n(this);
        this.r = WXAPIFactory.createWXAPI(this, "wx592fdc48acfbe290", true);
        this.r.registerApp("wx592fdc48acfbe290");
        this.s = this.o.a(this.t);
        this.A = new Dialog(this, R.style.dialogTheme);
        this.A.setContentView(R.layout.loaddialog);
        this.A.setCanceledOnTouchOutside(true);
    }

    private void a(boolean z) {
        CharSequence charSequence = "";
        if (this.a != null) {
            charSequence = this.a.getReadid();
        }
        if (TextUtils.isEmpty(charSequence) && !TextUtils.isEmpty(this.I)) {
            String str = this.I;
        }
        if (HttpState.PREEMPTIVE_DEFAULT.equals(this.J)) {
            this.k.setPullLoadEnable(false);
        } else if (an.a((Context) this)) {
            a(this.W[1][this.Q.getSelectedItemPosition()], this.X[1][this.R.getSelectedItemPosition()], this.Y[1][this.S.getSelectedItemPosition()], z);
        } else {
            an.a((Activity) this, getString(R.string.nonet), -1).show();
            this.k.b();
            this.k.c();
        }
    }

    private void f() {
        aa.a("NewsFeedActivity", "initCacheData()");
        List c = this.H.c("0");
        if (c == null || c.isEmpty()) {
            a("");
            return;
        }
        aa.a("NewsFeedActivity", "!newList.isEmpty()");
        this.k.setVisibility(0);
        ListItemObject listItemObject = (ListItemObject) c.get(c.size() - 1);
        this.I = listItemObject.getReadid();
        this.J = listItemObject.getHasData();
        an.a(c, this.E, this.D);
        this.l.a(c);
        this.M.clear();
        this.M = c;
        this.k.setPullRefreshEnable(true);
        this.k.setPullLoadEnable(true);
        a("");
    }

    private void g() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.L = (LinearLayout) getLayoutInflater().inflate(R.layout.ranking_list_head, null);
        h();
        this.g = (RelativeLayout) findViewById(R.id.title);
        this.h = (LinearLayout) findViewById(R.id.left_layout);
        com.budejie.www.activity.video.p.a(this, this.h);
        this.i = (Button) findViewById(R.id.title_left_btn);
        this.j = (TextView) findViewById(R.id.title_center_txt);
        this.h.setOnClickListener(this);
        this.k = (XListView) findViewById(R.id.listview);
        this.k.addHeaderView(this.L);
        this.k.setVisibility(4);
        this.k.setPullRefreshEnable(false);
        this.k.setPullLoadEnable(false);
        this.k.setXListViewListener(this);
        this.k.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ RankingListActivity a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                k.a(this.a.G).a(absListView.getFirstVisiblePosition(), absListView.getLastVisiblePosition(), this.a.k.getHeaderViewsCount());
            }
        });
        this.j.setText(R.string.title_ranking_list);
        this.i.setOnClickListener(this);
        this.i.setVisibility(0);
    }

    private void h() {
        this.Q = (ClickControlledSpinner) this.L.findViewById(R.id.typeSpinner);
        this.R = (ClickControlledSpinner) this.L.findViewById(R.id.timeSpinner);
        this.S = (ClickControlledSpinner) this.L.findViewById(R.id.sortSpinner);
        r0 = new String[2][];
        r0[0] = new String[]{"全部", "视频", "图片", "段子", "声音"};
        r0[1] = new String[]{"1", "41", com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, "29", "31"};
        this.W = r0;
        r0 = new String[2][];
        r0[0] = new String[]{"日榜", "周榜", "月榜", "季度榜", "年榜"};
        r0[1] = new String[]{"day", "week", "month", "quarter", "year"};
        this.X = r0;
        r0 = new String[2][];
        r0[0] = new String[]{"转发最多", "评论最多", "点赞最多"};
        r0[1] = new String[]{"forward", "comment", "up"};
        this.Y = r0;
        this.T = new a(this, this, R.layout.simple_spinner_item, this.W[0], this.Q);
        this.Q.setAdapter(this.T);
        this.U = new a(this, this, R.layout.simple_spinner_item, this.X[0], this.R);
        this.R.setAdapter(this.U);
        this.V = new a(this, this, R.layout.simple_spinner_item, this.Y[0], this.S);
        this.S.setAdapter(this.V);
        this.Q.setOnItemSelectedListener(this.Z);
        this.R.setOnItemSelectedListener(this.Z);
        this.S.setOnItemSelectedListener(this.Z);
        this.Q.setOnClickMyListener(this, this.Q);
        this.R.setOnClickMyListener(this, this.R);
        this.S.setOnClickMyListener(this, this.S);
    }

    private void a(String str, String str2, String str3, boolean z) {
        if (an.a((Context) this)) {
            if (!z) {
                this.O = "0";
            }
            BudejieApplication.a.a(RequstMethod.GET, com.budejie.www.http.j.a(str, str2, str3, this.O), new com.budejie.www.http.j(this.G), this.P);
            return;
        }
        an.a((Activity) this, getString(R.string.nonet), -1).show();
    }

    public void onClick(View view) {
        if (view == this.i) {
            finish();
        }
    }

    public void onrefreshTheme() {
        super.onrefreshTheme();
    }

    public void b() {
        this.ac = true;
        this.J = "";
        a(true);
    }

    public void a() {
        aa.b("NewsFeedActivity", "onRefresh()");
        this.a = null;
        this.I = "";
        this.ac = false;
        this.J = "";
        a(false);
    }

    public void a(int i, String str) {
    }

    public void a(int i) {
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 711) {
            this.d.sendEmptyMessage(814);
            bindTencent();
        } else if (i2 == 126 && intent.getBooleanExtra("success", false)) {
            aa.b("RankingListActivity", "onActivityResult---PERSON_REQUEST_CODE--success");
            a();
        }
    }

    public void onWbShareSuccess() {
        super.onWbShareSuccess();
        am.a(this.u);
        if (this.l != null) {
            this.l.notifyDataSetChanged();
        }
    }

    private void a(final ListItemObject listItemObject) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new com.budejie.www.http.j().e(this.G, listItemObject.getUid()), new net.tsz.afinal.a.a<String>(this) {
            final /* synthetic */ RankingListActivity b;

            public /* synthetic */ void onSuccess(Object obj) {
                a((String) obj);
            }

            public void a(String str) {
                try {
                    ResultBean s = z.s(str);
                    if (s != null) {
                        String msg = s.getMsg();
                        CharSequence code = s.getCode();
                        if (TextUtils.isEmpty(msg)) {
                            an.a(this.b.G, this.b.getString(R.string.operate_fail), -1).show();
                        } else {
                            an.a(this.b.G, msg, -1).show();
                        }
                        if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                            this.b.H.e("newCacheTable", listItemObject.getUid());
                            for (int size = this.b.M.size() - 1; size >= 0; size--) {
                                if (listItemObject.getUid().equals(((ListItemObject) this.b.M.get(size)).getUid())) {
                                    this.b.M.remove(size);
                                }
                            }
                            if (this.b.M.size() == 0) {
                                this.b.a();
                                return;
                            } else {
                                this.b.l.a(this.b.M);
                                return;
                            }
                        }
                        return;
                    }
                    an.a(this.b.G, this.b.getString(R.string.operate_fail), -1).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void a(final SuggestedFollowsListItem suggestedFollowsListItem) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new com.budejie.www.http.j().d(this.G, suggestedFollowsListItem.uid), new net.tsz.afinal.a.a<String>(this) {
            final /* synthetic */ RankingListActivity b;

            public /* synthetic */ void onSuccess(Object obj) {
                a((String) obj);
            }

            public void onFailure(Throwable th, int i, String str) {
                suggestedFollowsListItem.is_follow = 0;
                as.b().b(suggestedFollowsListItem);
            }

            public void a(String str) {
                try {
                    ResultBean s = z.s(str);
                    if (s != null) {
                        String msg = s.getMsg();
                        CharSequence code = s.getCode();
                        if (TextUtils.isEmpty(msg)) {
                            an.a(this.b.G, this.b.getString(R.string.operate_fail), -1).show();
                        } else {
                            an.a(this.b.G, msg, -1).show();
                        }
                        if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                            suggestedFollowsListItem.is_follow = 1;
                            this.b.K.a(new Fans(suggestedFollowsListItem));
                            as.b().a(suggestedFollowsListItem.uid, Integer.valueOf(1));
                        }
                        as.b().b(suggestedFollowsListItem);
                        return;
                    }
                    an.a(this.b.G, this.b.getString(R.string.operate_fail), -1).show();
                    suggestedFollowsListItem.is_follow = 0;
                    as.b().b(suggestedFollowsListItem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onComplete(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        super.onComplete(jSONObject);
        HashMap a = z.a(jSONObject);
        if (a != null && a.size() != 0) {
            this.q.edit().putString("openid", (String) a.get("qzone_uid")).putString("qzone_token", (String) a.get("qzone_token")).putString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN, (System.currentTimeMillis() + (Long.parseLong((String) a.get(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN)) * 1000)) + "").commit();
            this.t = this.q.getString("id", "");
            this.o.a((String) a.get("qzone_uid"), this.t, (String) a.get("qzone_token"), 929, this.d);
        }
    }

    public void onError(UiError uiError) {
        super.onError(uiError);
        Toast.makeText(this, "code:" + uiError.errorCode + ", msg:" + uiError.errorMessage + ", detail:" + uiError.errorDetail, 0).show();
    }

    public void onSuccess(com.sina.weibo.sdk.auth.b bVar) {
        try {
            this.t = this.q.getString("id", "");
            mAccessToken = bVar;
            if (mAccessToken.a()) {
                com.sina.weibo.sdk.auth.a.a(this, mAccessToken);
                au.a((int) R.string.oauthSuccess);
                this.o.a(mAccessToken, this.t, 812, this.d);
            }
        } catch (Exception e) {
            au.a((int) R.string.sina_shouquan_failed);
        }
    }

    public void bindTencent() {
        this.t = this.q.getString("id", "");
        String sharePersistent = Util.getSharePersistent(this, "ACCESS_TOKEN");
        this.o.a(Util.getSharePersistent(this, "NAME"), sharePersistent, Util.getSharePersistent(this, "OPEN_ID"), this.t, 813, this.d);
    }

    private boolean i() {
        ArrayList a = this.D.a();
        if (a == null || a.isEmpty()) {
            return false;
        }
        return true;
    }

    private void j() {
        this.z = new Dialog(this, R.style.dialogTheme);
        View inflate = getLayoutInflater().inflate(R.layout.mycomment_delete_dialog, null);
        ((TextView) inflate.findViewById(R.id.mycomment_delete_text)).setText(getString(R.string.mycollect_sync_text));
        Button button = (Button) inflate.findViewById(R.id.mycomment_delete_sureBtn);
        ((Button) inflate.findViewById(R.id.mycomment_delete_cancelBtn)).setOnClickListener(this.ab);
        button.setOnClickListener(this.ab);
        this.z.setContentView(inflate);
        Window window = this.z.getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.width = an.a((Context) this, 300);
        window.setAttributes(attributes);
    }

    public void a(String str) {
        aa.b("NewsFeedActivity", "onExternalRefresh()");
        if (this.k != null) {
            this.k.d();
        }
    }

    public void c() {
    }

    public void onSpinnerClick(ClickControlledSpinner clickControlledSpinner) {
        this.ae = true;
        this.Q.setOnClicked(false);
        this.R.setOnClicked(false);
        this.S.setOnClicked(false);
        if (this.Q == clickControlledSpinner) {
            this.Q.setOnClicked(true);
        }
        if (this.R == clickControlledSpinner) {
            this.R.setOnClicked(true);
        }
        if (this.S == clickControlledSpinner) {
            this.S.setOnClicked(true);
        }
        this.f.sendEmptyMessage(0);
    }

    public void onWindowFocusChanged(boolean z) {
        if (this.ae) {
            this.ae = false;
            return;
        }
        this.Q.setOnClicked(false);
        this.R.setOnClicked(false);
        this.S.setOnClicked(false);
        this.f.sendEmptyMessage(0);
    }
}
