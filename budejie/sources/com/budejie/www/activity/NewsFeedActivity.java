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
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.room.gift.GiftConfigUtil;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.ali.auth.third.core.model.Constants;
import com.alipay.sdk.util.j;
import com.budejie.www.R;
import com.budejie.www.activity.TipPopUp.TypeControl;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.activity.recommend.SuggestedFollowsActivity;
import com.budejie.www.activity.search.SearchMainActivity;
import com.budejie.www.activity.video.k;
import com.budejie.www.activity.view.RecommendView;
import com.budejie.www.bean.Fans;
import com.budejie.www.bean.ListInfo;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.ResultBean;
import com.budejie.www.bean.SuggestedFollowsListItem;
import com.budejie.www.bean.SuggestedFollowsResults;
import com.budejie.www.c.b;
import com.budejie.www.c.d;
import com.budejie.www.c.g;
import com.budejie.www.c.h;
import com.budejie.www.c.m;
import com.budejie.www.f.a;
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
import com.budejie.www.util.ao;
import com.budejie.www.util.as;
import com.budejie.www.util.au;
import com.budejie.www.util.p;
import com.budejie.www.util.z;
import com.budejie.www.widget.XListView;
import com.budejie.www.widget.curtain.FloatVideoLayout;
import com.google.gson.Gson;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.UiError;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.umeng.analytics.MobclickAgent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.HttpState;
import org.json.JSONObject;

public class NewsFeedActivity extends OauthWeiboBaseAct implements a, c, XListView.a {
    private Toast A;
    private Activity B;
    private b C;
    private String D;
    private String E;
    private LinearLayout F;
    private TextView G;
    private LinearLayout H;
    private Button I;
    private Button J;
    private RecommendView K;
    private RelativeLayout L;
    private List<SuggestedFollowsListItem> M;
    private g N;
    private LinearLayout O;
    private List<ListItemObject> P;
    private ImageView Q;
    private int R;
    private net.tsz.afinal.a.a<String> S = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ NewsFeedActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
        }

        public void a(String str) {
            super.onSuccess(str);
            try {
                SuggestedFollowsResults suggestedFollowsResults = (SuggestedFollowsResults) new Gson().fromJson(str, SuggestedFollowsResults.class);
                if (suggestedFollowsResults != null) {
                    this.a.M = suggestedFollowsResults.list;
                    if (this.a.M != null && this.a.M.size() > 0) {
                        this.a.K.setFollowsItemList(this.a.M);
                        this.a.g.notifyDataSetChanged();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private net.tsz.afinal.a.a<String> T = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ NewsFeedActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
            if (!this.a.isFinishing() && this.a.g.isEmpty()) {
                try {
                    this.a.v.show();
                } catch (Exception e) {
                }
            }
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            this.a.f.c();
            this.a.f.b();
            if (this.a.v.isShowing()) {
                this.a.v.dismiss();
            }
            this.a.A = an.a(this.a.B, this.a.getString(R.string.load_failed), -1);
            this.a.A.show();
        }

        public void a(String str) {
            boolean z = true;
            super.onSuccess(str);
            aa.a("NewsFeedActivity", str);
            k.a(this.a.B).h();
            this.a.f.c();
            this.a.f.b();
            if (this.a.v.isShowing()) {
                this.a.v.dismiss();
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
                    List<ListItemObject> a2 = com.budejie.www.j.a.a(this.a, str);
                    data.setList(a2);
                    friendsFeedList.setData(data);
                    if ((a2 == null || a2.size() <= 0) && this.a.g.getCount() <= 0) {
                        this.a.F.setVisibility(0);
                        this.a.f.setVisibility(4);
                        this.a.f.setPullRefreshEnable(false);
                        this.a.f.setPullLoadEnable(false);
                    } else {
                        this.a.F.setVisibility(8);
                        this.a.f.setVisibility(0);
                        this.a.f.setPullRefreshEnable(true);
                        this.a.f.setPullLoadEnable(true);
                    }
                    for (ListItemObject listItemObject : a2) {
                        listItemObject.setCmdShowTime(listItemObject.getAddtime());
                        listItemObject.setAddtime(listItemObject.getPasstime());
                    }
                    if (!this.a.X) {
                        aa.a("NewsFeedActivity", "deleteNewCacheTable");
                        this.a.C.d("0");
                    }
                    aa.a("NewsFeedActivity", "database.insertNewCache");
                    this.a.C.a((List) a2, data.getInfo().getReadid(), data.getInfo().getHasdata(), "0");
                    an.a((List) a2, this.a.z, this.a.y);
                    this.a.P.clear();
                    this.a.P = a2;
                    if (this.a.a == null || !this.a.X) {
                        this.a.g.a(a2);
                    } else {
                        this.a.g.b(a2);
                    }
                    this.a.a = data.getInfo();
                    if (this.a.a == null || !this.a.a.getHasdata()) {
                        this.a.f.setPullLoadEnable(false);
                    } else {
                        this.a.f.setPullLoadEnable(true);
                    }
                } else if ("2".equals(friendsFeedList.getCode())) {
                    this.a.f.setPullLoadEnable(false);
                    aa.a("NewsFeedActivity", "database.deleteNewCacheTable");
                    this.a.C.d("0");
                    this.a.g.b();
                    if (an.a(this.a.l)) {
                        this.a.f.setPullRefreshEnable(false);
                        this.a.f.setPullLoadEnable(false);
                        this.a.F.setVisibility(0);
                        this.a.f.setVisibility(4);
                    }
                } else {
                    String string = this.a.getString(R.string.load_failed);
                    if (!(friendsFeedList == null || TextUtils.isEmpty(friendsFeedList.getMsg()))) {
                        string = friendsFeedList.getMsg();
                    }
                    this.a.A = an.a(this.a.B, string, -1);
                    this.a.A.show();
                    if (an.a(this.a.l)) {
                        this.a.f.setPullRefreshEnable(false);
                        this.a.f.setPullLoadEnable(false);
                        this.a.F.setVisibility(0);
                        this.a.f.setVisibility(4);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.a.A = an.a(this.a.B, this.a.getString(R.string.load_failed), -1);
                this.a.A.show();
            }
        }
    };
    private OnCheckedChangeListener U = new OnCheckedChangeListener(this) {
        final /* synthetic */ NewsFeedActivity a;

        {
            this.a = r1;
        }

        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            Fragment findFragmentById = this.a.getSupportFragmentManager().findFragmentById(R.id.subscription_fragment_container);
            LayoutParams layoutParams = (LayoutParams) this.a.Q.getLayoutParams();
            if (i == R.id.news_feed) {
                layoutParams.leftMargin = this.a.R;
                this.a.Q.setLayoutParams(layoutParams);
                if (findFragmentById != null) {
                    ((com.budejie.www.activity.labelsubscription.c) findFragmentById).d();
                    this.a.getSupportFragmentManager().beginTransaction().hide(findFragmentById).commitAllowingStateLoss();
                    ao.a(this.a.B);
                }
            }
        }
    };
    private com.budejie.www.adapter.e.a V = new com.budejie.www.adapter.e.a(this) {
        final /* synthetic */ NewsFeedActivity a;

        {
            this.a = r1;
        }

        public void a(View view, ListItemObject listItemObject) {
            TipPopUp.a(this.a, TypeControl.dingtie);
            this.a.x.a("ding", this.a.d, listItemObject);
            this.a.x.a(listItemObject, this.a.d, "ding");
        }

        public void b(View view, ListItemObject listItemObject) {
            this.a.x.a("cai", this.a.d, listItemObject);
            this.a.x.a(listItemObject, this.a.d, "cai");
        }

        public void a(View view, ListItemObject listItemObject, String str) {
            Bundle bundle = new Bundle();
            bundle.putString(PersonalProfileActivity.c, listItemObject.getUid());
            bundle.putString(PersonalProfileActivity.d, str);
            this.a.h.a(7, bundle).onClick(view);
        }

        public void c(View view, ListItemObject listItemObject) {
            p.a(this.a, listItemObject, this.a.e, 0);
        }

        public void a(View view, ListItemObject listItemObject, int i) {
            this.a.p = listItemObject;
            Bundle bundle = new Bundle();
            bundle.putInt("position", i);
            bundle.putString(HistoryOpenHelper.COLUMN_UID, ai.b(this.a));
            bundle.putSerializable("weiboMap", this.a.n);
            bundle.putSerializable("data", listItemObject);
            view.setTag(listItemObject);
            this.a.h.a(5, bundle, this.a.e, this.a.m, this.a.i, this.a.j, this.a.k, this.a.l, this.a.d).onClick(view);
        }

        public void d(View view, ListItemObject listItemObject) {
            view.setTag(listItemObject);
            this.a.h.a(3, null).onClick(view);
        }

        public void a() {
        }

        public void e(View view, ListItemObject listItemObject) {
            view.setTag(listItemObject);
            this.a.h.a(3, null).onClick(view);
        }

        public void a_(String str) {
        }

        public OnClickListener b() {
            return null;
        }

        public void a(SuggestedFollowsListItem suggestedFollowsListItem) {
            if (an.a(this.a.B)) {
                int i = suggestedFollowsListItem.is_follow;
                if (i != 1 && i == 0) {
                    this.a.a(suggestedFollowsListItem);
                    return;
                }
                return;
            }
            an.a(this.a.B, this.a.B.getString(R.string.nonet), -1).show();
        }

        public void a(ListItemObject listItemObject, int i) {
            Intent intent = new Intent(this.a.B, CommonLabelActivity.class);
            PlateBean plateBean = listItemObject.getPlateBean(i);
            if (plateBean != null) {
                intent.putExtra("theme_name", plateBean.theme_name);
                intent.putExtra("theme_id", plateBean.theme_id);
                this.a.B.startActivity(intent);
            }
        }
    };
    private OnClickListener W = new OnClickListener(this) {
        final /* synthetic */ NewsFeedActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view.getId() == R.id.mycomment_delete_cancelBtn) {
                this.a.u.dismiss();
            } else if (view.getId() == R.id.mycomment_delete_sureBtn) {
                this.a.u.dismiss();
                ArrayList a = this.a.y.a();
                if (a != null && !a.isEmpty()) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < a.size(); i++) {
                        if (i == a.size() - 1) {
                            stringBuffer.append((String) a.get(i));
                        } else {
                            stringBuffer.append((String) a.get(i)).append(",");
                        }
                    }
                    this.a.s.a("add", this.a.o, stringBuffer.toString(), 971);
                }
            }
        }
    };
    private boolean X;
    private net.tsz.afinal.a.a<String> Y = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ NewsFeedActivity a;

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
                    this.a.i.a("follow_count", "0", this.a.o);
                } else {
                    this.a.i.a("follow_count", followCount, this.a.o);
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
                if (!this.a.B.isFinishing()) {
                    this.a.A = an.a(this.a.B, this.a.getString(R.string.load_failed), -1);
                    this.a.A.show();
                }
            }
        }
    };
    public Info a;
    public SharedPreferences b;
    int c;
    @SuppressLint({"HandlerLeak"})
    Handler d = new Handler(this) {
        final /* synthetic */ NewsFeedActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            boolean z = false;
            int i = message.what;
            if (i == 814) {
                this.a.v.show();
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
                                this.a.o = (String) c.get("id");
                                this.a.i.a(this.a.o, c);
                                ai.a(this.a.B, this.a.o, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                this.a.n = this.a.j.a(this.a.o);
                                an.a(this.a, this.a.getString(R.string.bind_successed), -1).show();
                                i = this.a.l.getInt("position", -1);
                                if (-1 != i) {
                                    this.a.j.a(this.a, (ListItemObject) this.a.g.getItem(i), com.tencent.connect.common.Constants.SOURCE_QZONE, this.a.o, this.a.n, this.a.k, (Handler) this);
                                }
                                if (this.a.n()) {
                                    if (this.a.u == null) {
                                        this.a.o();
                                    } else {
                                        this.a.u.show();
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
                                this.a.o = (String) c.get("id");
                                this.a.i.a(this.a.o, c);
                                ai.a(this.a.B, this.a.o, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                if (OauthWeiboBaseAct.mAccessToken != null) {
                                    this.a.i.a(this.a.o, OauthWeiboBaseAct.mAccessToken.e());
                                }
                                this.a.n = this.a.j.a(this.a.o);
                                an.a(this.a, this.a.getString(R.string.bind_successed), -1).show();
                                i = this.a.l.getInt("position", -1);
                                if (-1 != i) {
                                    this.a.j.a(this.a, (ListItemObject) this.a.g.getItem(i), "sina", this.a.o, this.a.n, this.a.k, (Handler) this);
                                }
                                this.a.g.notifyDataSetChanged();
                                if (this.a.n()) {
                                    if (this.a.u == null) {
                                        this.a.o();
                                    } else {
                                        this.a.u.show();
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
                                this.a.o = (String) c.get("id");
                                this.a.i.a(this.a.o, c);
                                ai.a(this.a.B, this.a.o, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                this.a.n = this.a.j.a(this.a.o);
                                an.a(this.a, this.a.getString(R.string.bind_successed), -1).show();
                                i = this.a.l.getInt("position", -1);
                                if (-1 != i) {
                                    this.a.j.a(this.a, (ListItemObject) this.a.g.getItem(i), "qq", this.a.o, this.a.n, this.a.k, (Handler) this);
                                }
                                this.a.g.notifyDataSetChanged();
                                if (this.a.n()) {
                                    if (this.a.u == null) {
                                        this.a.o();
                                    } else {
                                        this.a.u.show();
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
                    this.a.k.a(i, z, (int) R.string.forwarfail);
                } else if ("0".equals(string)) {
                    this.a.k.a(i, true, (int) R.string.forwardsuccess);
                } else {
                    this.a.k.a(i, z, (int) R.string.forwarfail);
                }
                new Thread(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public void run() {
                        try {
                            Thread.sleep(1000);
                            this.b.a.d.sendMessage(this.b.a.d.obtainMessage(817, Integer.valueOf(i)));
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
            } else if (i == 817) {
                this.a.k.a(((Integer) message.obj).intValue());
            }
        }
    };
    @SuppressLint({"HandlerLeak"})
    final Handler e = new Handler(this) {
        final /* synthetic */ NewsFeedActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 4) {
                this.a.p.setLove(this.a.p.getLove() + 1);
            } else if (i == 5) {
                this.a.q = ProgressDialog.show(this.a, "", (String) message.obj, true, true);
            } else if (i == 6) {
                this.a.q.cancel();
            } else if (i == 7) {
                an.a(this.a, this.a.getString(R.string.already_collected), -1).show();
            } else if (i == 9) {
                this.a.p.setRepost(String.valueOf(Integer.parseInt(this.a.p.getRepost()) + 1));
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
                    this.a.r = "add";
                    this.a.s.a(this.a.r, ai.b(this.a), (String) message.obj, 971);
                }
            } else if (i == 12) {
                an.a(this.a, (int) R.string.collect_fail, (int) R.drawable.collect_tip).show();
            } else if (i == 829) {
                r0 = (String) message.obj;
                this.a.t.a("collectTable", r0);
                an.a(this.a, this.a.getString(R.string.delete_success), -1).show();
                this.a.r = "delete";
                this.a.s.a(this.a.r, ai.b(this.a), r0, 971);
            } else if (i == 13) {
                an.b(this.a, this.a.f);
            } else if (i == 1001) {
                HashMap k = z.k((String) message.obj);
                if (k != null) {
                    r0 = (String) k.get("result_desc");
                    if (TextUtils.isEmpty(r0)) {
                        this.a.A = an.a(this.a.B, this.a.B.getString(R.string.operate_fail), -1);
                    } else {
                        this.a.A = an.a(this.a.B, r0, -1);
                    }
                } else {
                    this.a.A = an.a(this.a.B, this.a.B.getString(R.string.operate_fail), -1);
                }
                if (this.a.A != null) {
                    this.a.A.show();
                }
            } else if (i == 1006 && ((ListItemObject) message.obj) != null) {
                this.a.a((ListItemObject) message.obj);
            }
            this.a.g.notifyDataSetChanged();
        }
    };
    private XListView f;
    private com.budejie.www.adapter.c.a g;
    private com.budejie.www.g.b h;
    private m i;
    private n j;
    private com.elves.update.a k;
    private SharedPreferences l;
    private IWXAPI m;
    private HashMap<String, String> n;
    private String o;
    private ListItemObject p;
    private ProgressDialog q;
    private String r = "add";
    private com.budejie.www.http.b s;
    private b t;
    private Dialog u;
    private Dialog v;
    private Animation w;
    private f x;
    private d y;
    private h z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        aa.a("NewsFeedActivity", "onCreate()");
        try {
            setTheme(com.budejie.www.h.c.a().a(ai.a(this)));
            setContentView(R.layout.news_feed_layout);
            this.B = this;
            j();
            this.C = new b(this);
            this.g = new com.budejie.www.adapter.c.a(this, this.V);
            this.P = new ArrayList();
            this.f.setAdapter(this.g);
            this.w = AnimationUtils.loadAnimation(this, R.anim.refresh_button_rotating);
            this.w.setInterpolator(new LinearInterpolator());
            this.v = new Dialog(this, R.style.dialogTheme);
            this.v.setContentView(R.layout.loaddialog);
            this.v.setCanceledOnTouchOutside(true);
            this.x = new f(this);
            this.z = new h(this);
            this.y = new d(this);
            f();
            this.c = this.i.f(this.o);
            this.b = this.B.getSharedPreferences("weiboprefer", 0);
            this.N = new g(this);
            d();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void d() {
        i();
        this.X = getIntent().getBooleanExtra("isLoadCache", true);
    }

    protected void onResume() {
        super.onResume();
        try {
            e();
            this.h = new com.budejie.www.g.b(this, this.mSsoHandler, this.mTencent, this);
            i.a((int) R.string.track_screen_attention);
        } catch (Exception e) {
            MobclickAgent.onEvent(this.B, "cacheException", "NewsFeedActivity onResume:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
        FloatVideoLayout.a((Context) this, false);
    }

    private void e() {
        aa.a("NewsFeedActivity", "onResume()");
        this.e.postDelayed(new Runnable(this) {
            final /* synthetic */ NewsFeedActivity a;

            {
                this.a = r1;
            }

            public void run() {
                if (HomeGroup.w != null && HomeGroup.w.getVisibility() == 8) {
                    HomeGroup.w.a();
                }
            }
        }, 500);
        if (an.a(this.l)) {
            this.H.setVisibility(8);
            this.I.setVisibility(0);
            String string = this.l.getString("id", this.o);
            if (!(TextUtils.isEmpty(string) || string.equals(this.o))) {
                this.C.d("0");
                this.g.b();
                this.o = string;
            }
            if (!this.X || (this.g != null && this.g.getCount() == 0)) {
                aa.b("NewsFeedActivity", "onResume--onRefresh");
                a();
            }
            if (an.a((Context) this)) {
                if (TextUtils.isEmpty(this.o)) {
                    this.o = this.l.getString("id", "");
                }
                com.budejie.www.http.j jVar = new com.budejie.www.http.j();
                BudejieApplication.a.a(RequstMethod.GET, com.budejie.www.http.j.e(), com.budejie.www.http.j.s(this.B, this.o), this.Y);
                return;
            }
            an.a((Activity) this, getString(R.string.nonet), -1).show();
            return;
        }
        this.F.setVisibility(8);
        this.H.setVisibility(0);
        this.f.setVisibility(4);
        this.I.setVisibility(8);
        aa.a("NewsFeedActivity", "deleteNewCacheTable");
        this.C.d("0");
        this.g.b();
    }

    protected void onPause() {
        super.onPause();
        k.a(this.B).o();
    }

    protected void onDestroy() {
        super.onDestroy();
        k.a((Context) this).p();
    }

    private void f() {
        this.l = getSharedPreferences("weiboprefer", 0);
        this.o = this.l.getString("id", "");
        this.h = new com.budejie.www.g.b(this, this.mSsoHandler, this.mTencent, this);
        this.i = new m(this);
        this.k = new com.elves.update.a(this);
        this.s = com.budejie.www.http.b.a(this, this);
        this.t = new b(this);
        this.j = new n(this);
        this.m = WXAPIFactory.createWXAPI(this, "wx592fdc48acfbe290", true);
        this.m.registerApp("wx592fdc48acfbe290");
        this.n = this.j.a(this.o);
        this.v = new Dialog(this, R.style.dialogTheme);
        this.v.setContentView(R.layout.loaddialog);
        this.v.setCanceledOnTouchOutside(true);
    }

    private void g() {
        aa.a("NewsFeedActivity", "loadData()");
        if (an.a(this.l)) {
            String str = "";
            if (this.a != null) {
                str = this.a.getReadid();
            }
            if (TextUtils.isEmpty(str) && !TextUtils.isEmpty(this.D)) {
                str = this.D;
            }
            if (HttpState.PREEMPTIVE_DEFAULT.equals(this.E)) {
                this.f.setPullLoadEnable(false);
            } else if (an.a((Context) this)) {
                BudejieApplication.a.a(RequstMethod.GET, com.budejie.www.http.j.b(str), new com.budejie.www.http.j(this.B), this.T);
                h();
            } else {
                an.a((Activity) this, getString(R.string.nonet), -1).show();
                this.f.b();
                this.f.c();
            }
        }
    }

    private void h() {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new com.budejie.www.http.j().d(this.B, this.o == null ? "" : this.o, GiftConfigUtil.SUPER_GIRL_GIFT_TAG), this.S);
    }

    private void i() {
        aa.a("NewsFeedActivity", "initCacheData()");
        List c = this.C.c("0");
        if (c == null || c.isEmpty()) {
            a("");
            return;
        }
        aa.a("NewsFeedActivity", "!newList.isEmpty()");
        this.F.setVisibility(8);
        this.f.setVisibility(0);
        ListItemObject listItemObject = (ListItemObject) c.get(c.size() - 1);
        this.D = listItemObject.getReadid();
        this.E = listItemObject.getHasData();
        an.a(c, this.z, this.y);
        this.g.a(c);
        this.P.clear();
        this.P = c;
        this.f.setPullRefreshEnable(true);
        this.f.setPullLoadEnable(true);
        a("");
    }

    private void j() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.O = (LinearLayout) getLayoutInflater().inflate(R.layout.my_neews_head, null);
        this.K = (RecommendView) this.O.findViewById(R.id.first_view);
        this.L = (RelativeLayout) this.O.findViewById(R.id.enter_sggested_follows);
        this.K.setRowClickHandler(this.V);
        this.L.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NewsFeedActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.followLayout$Click(null);
            }
        });
        View.inflate(this, R.layout.dynamic_tab, (RelativeLayout) findViewById(R.id.title_center_layout));
        this.I = (Button) findViewById(R.id.bt_setting);
        this.J = (Button) findViewById(R.id.bt_left);
        if (com.budejie.www.h.c.a().a(themeState) == R.style.ThemeBlack) {
            this.J.setBackgroundResource(R.drawable.btn_left_selector_night);
        } else {
            this.J.setBackgroundResource(R.drawable.btn_dynamic_left_selector);
        }
        this.I.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.recommend_title_search_icon));
        this.I.setVisibility(0);
        this.J.setVisibility(0);
        this.f = (XListView) findViewById(R.id.listview);
        this.f.addHeaderView(this.O);
        this.f.setVisibility(4);
        this.f.setPullRefreshEnable(false);
        this.f.setPullLoadEnable(false);
        this.f.setXListViewListener(this);
        this.f.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ NewsFeedActivity a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                k.a(this.a.B).a(absListView.getFirstVisiblePosition(), absListView.getLastVisiblePosition(), this.a.f.getHeaderViewsCount());
            }
        });
        l();
        m();
        k();
    }

    private void k() {
        try {
            if (getBudejieSettings().e.a().booleanValue()) {
                CharSequence configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "顶部导航是否自动隐藏");
                if (TextUtils.isEmpty(configParams) || !Constants.SERVICE_SCOPE_FLAG_VALUE.equals(configParams)) {
                    getBudejieSettings().d.a(Boolean.valueOf(false));
                } else {
                    getBudejieSettings().d.a(Boolean.valueOf(true));
                }
            }
            if (((BudejieApplication) getApplication()).g().f.a().booleanValue()) {
                HomeGroup.a((Context) this, 0);
            } else {
                HomeGroup.a((Context) this, getResources().getDimensionPixelOffset(R.dimen.navigation_height));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void l() {
        this.F = (LinearLayout) findViewById(R.id.hintEmptyLayout);
        this.G = (TextView) findViewById(R.id.recommendMsgTextView);
        this.G.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NewsFeedActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.startActivity(new Intent(this.a, SuggestedFollowsActivity.class));
            }
        });
    }

    private void m() {
        this.H = (LinearLayout) findViewById(R.id.unLoginLayout);
        ((Button) findViewById(R.id.loginButton)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NewsFeedActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                an.a(this.a.B, 1, StatisticCodeTable.MORE, "person", 126);
            }
        });
        ((Button) findViewById(R.id.registButton)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NewsFeedActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                an.a(this.a.B, 2, StatisticCodeTable.MORE, "person", 126);
            }
        });
    }

    public void noticeLayout$Click(View view) {
        startActivity(new Intent(this, SearchMainActivity.class));
    }

    public void followLayout$Click(View view) {
        startActivity(new Intent(this, SuggestedFollowsActivity.class));
    }

    public void b() {
        this.X = true;
        this.E = "";
        g();
    }

    public void a() {
        aa.b("NewsFeedActivity", "onRefresh()");
        this.a = null;
        this.D = "";
        this.X = false;
        this.E = "";
        g();
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
            aa.b("NewsFeedActivity", "onActivityResult---PERSON_REQUEST_CODE--success");
            a();
        }
    }

    public void onWbShareSuccess() {
        super.onWbShareSuccess();
        am.a(this.p);
        if (this.g != null) {
            this.g.notifyDataSetChanged();
        }
    }

    private void a(final ListItemObject listItemObject) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new com.budejie.www.http.j().e(this.B, listItemObject.getUid()), new net.tsz.afinal.a.a<String>(this) {
            final /* synthetic */ NewsFeedActivity b;

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
                            an.a(this.b.B, this.b.getString(R.string.operate_fail), -1).show();
                        } else {
                            an.a(this.b.B, msg, -1).show();
                        }
                        if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                            this.b.C.e("newCacheTable", listItemObject.getUid());
                            for (int size = this.b.P.size() - 1; size >= 0; size--) {
                                if (listItemObject.getUid().equals(((ListItemObject) this.b.P.get(size)).getUid())) {
                                    this.b.P.remove(size);
                                }
                            }
                            if (this.b.P.size() == 0) {
                                this.b.a();
                                return;
                            } else {
                                this.b.g.a(this.b.P);
                                return;
                            }
                        }
                        return;
                    }
                    an.a(this.b.B, this.b.getString(R.string.operate_fail), -1).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void a(final SuggestedFollowsListItem suggestedFollowsListItem) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new com.budejie.www.http.j().d(this.B, suggestedFollowsListItem.uid), new net.tsz.afinal.a.a<String>(this) {
            final /* synthetic */ NewsFeedActivity b;

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
                            an.a(this.b.B, this.b.getString(R.string.operate_fail), -1).show();
                        } else {
                            an.a(this.b.B, msg, -1).show();
                        }
                        if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                            suggestedFollowsListItem.is_follow = 1;
                            this.b.N.a(new Fans(suggestedFollowsListItem));
                            as.b().a(suggestedFollowsListItem.uid, Integer.valueOf(1));
                        }
                        as.b().b(suggestedFollowsListItem);
                        return;
                    }
                    an.a(this.b.B, this.b.getString(R.string.operate_fail), -1).show();
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
            this.l.edit().putString("openid", (String) a.get("qzone_uid")).putString("qzone_token", (String) a.get("qzone_token")).putString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN, (System.currentTimeMillis() + (Long.parseLong((String) a.get(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN)) * 1000)) + "").commit();
            this.o = this.l.getString("id", "");
            this.j.a((String) a.get("qzone_uid"), this.o, (String) a.get("qzone_token"), 929, this.d);
        }
    }

    public void onError(UiError uiError) {
        super.onError(uiError);
        Toast.makeText(this, "code:" + uiError.errorCode + ", msg:" + uiError.errorMessage + ", detail:" + uiError.errorDetail, 0).show();
    }

    public void onSuccess(com.sina.weibo.sdk.auth.b bVar) {
        try {
            this.o = this.l.getString("id", "");
            mAccessToken = bVar;
            if (mAccessToken.a()) {
                com.sina.weibo.sdk.auth.a.a(this, mAccessToken);
                au.a((int) R.string.oauthSuccess);
                this.j.a(mAccessToken, this.o, 812, this.d);
            }
        } catch (Exception e) {
            au.a((int) R.string.sina_shouquan_failed);
        }
    }

    public void bindTencent() {
        this.o = this.l.getString("id", "");
        String sharePersistent = Util.getSharePersistent(this, "ACCESS_TOKEN");
        this.j.a(Util.getSharePersistent(this, "NAME"), sharePersistent, Util.getSharePersistent(this, "OPEN_ID"), this.o, 813, this.d);
    }

    private boolean n() {
        ArrayList a = this.y.a();
        if (a == null || a.isEmpty()) {
            return false;
        }
        return true;
    }

    private void o() {
        this.u = new Dialog(this, R.style.dialogTheme);
        View inflate = getLayoutInflater().inflate(R.layout.mycomment_delete_dialog, null);
        ((TextView) inflate.findViewById(R.id.mycomment_delete_text)).setText(getString(R.string.mycollect_sync_text));
        Button button = (Button) inflate.findViewById(R.id.mycomment_delete_sureBtn);
        ((Button) inflate.findViewById(R.id.mycomment_delete_cancelBtn)).setOnClickListener(this.W);
        button.setOnClickListener(this.W);
        this.u.setContentView(inflate);
        Window window = this.u.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = an.a((Context) this, 300);
        window.setAttributes(attributes);
    }

    public void a(String str) {
        aa.b("NewsFeedActivity", "onExternalRefresh()");
        if (this.f != null) {
            this.f.d();
        }
    }

    public void c() {
    }
}
