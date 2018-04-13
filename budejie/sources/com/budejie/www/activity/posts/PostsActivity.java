package com.budejie.www.activity.posts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.ali.auth.third.core.model.Constants;
import com.ali.auth.third.login.LoginConstants;
import com.alibaba.baichuan.android.jsbridge.AlibcJsResult;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.HomeGroup;
import com.budejie.www.activity.MyAccountActivity;
import com.budejie.www.activity.OAuthWeiboActivity;
import com.budejie.www.activity.OauthWeiboBaseAct;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.activity.QiHooActivity;
import com.budejie.www.activity.TipPopUp;
import com.budejie.www.activity.TipPopUp.TypeControl;
import com.budejie.www.activity.auditpost.AuditPostsActivity;
import com.budejie.www.activity.htmlpage.HtmlFeatureActivity;
import com.budejie.www.activity.htmlpage.b;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.activity.labelsubscription.LabelSubscribeActivity;
import com.budejie.www.activity.phonenumber.PhoneNumberLoginActivity;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.activity.recommend.SuggestedFollowsActivity;
import com.budejie.www.activity.search.SearchMainActivity;
import com.budejie.www.activity.video.k;
import com.budejie.www.activity.view.PostsRelativeLayout;
import com.budejie.www.activity.view.TopNavigationTabIndicator.ScrollViewCustom;
import com.budejie.www.activity.view.TopNavigationTabIndicator.TabPageIndicator;
import com.budejie.www.activity.view.TopNavigationTabIndicator.TypeIndicator;
import com.budejie.www.bean.Fans;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.Navigation;
import com.budejie.www.bean.ResultBean;
import com.budejie.www.bean.SuggestedFollowsListItem;
import com.budejie.www.bean.TopNavigation;
import com.budejie.www.busevent.LoadMoreEvent;
import com.budejie.www.busevent.LoadMoreEvent.LoadMoreAction;
import com.budejie.www.busevent.LoginAction;
import com.budejie.www.c.d;
import com.budejie.www.c.g;
import com.budejie.www.c.m;
import com.budejie.www.f.c;
import com.budejie.www.f.e;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.f;
import com.budejie.www.http.i;
import com.budejie.www.http.n;
import com.budejie.www.util.ai;
import com.budejie.www.util.am;
import com.budejie.www.util.an;
import com.budejie.www.util.as;
import com.budejie.www.util.au;
import com.budejie.www.util.j;
import com.budejie.www.util.p;
import com.budejie.www.util.z;
import com.budejie.www.widget.FixedViewPager;
import com.budejie.www.widget.NavigationBar;
import com.budejie.www.widget.XListView;
import com.budejie.www.widget.curtain.FloatVideoLayout;
import com.budejie.www.widget.xrecyclerview.XRecyclerView;
import com.spriteapp.booklibrary.ui.fragment.NativeBookStoreFragment;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.umeng.analytics.MobclickAgent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import de.greenrobot.event.EventBus;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostsActivity extends QiHooActivity implements OnClickListener, b, com.budejie.www.adapter.e.a, com.budejie.www.f.a, c {
    public static boolean a = false;
    public static int k = an.z(BudejieApplication.g);
    public static int l = 3;
    private ListItemObject A;
    private String B = "add";
    private m C;
    private g D;
    private n E;
    private com.elves.update.a F;
    private com.budejie.www.http.b G;
    private com.budejie.www.c.b H;
    private HashMap<String, String> I;
    private d J;
    private Dialog K;
    private f L;
    private com.budejie.www.http.m M;
    private ProgressDialog N;
    private FixedViewPager O;
    private List<Fragment> P;
    private Map<String, Integer> Q;
    private Map<Integer, String> R;
    private a S;
    private Activity T;
    private Dialog U;
    private ArrayList<TopNavigation> V;
    private ListItemObject W;
    private Intent X;
    private Intent Y;
    private Intent Z;
    private ImageButton aa;
    private ImageView ab;
    private ImageButton ac;
    private BudejieApplication ad;
    private boolean ae = true;
    private ScrollViewCustom af;
    private boolean ag;
    private LoginAction ah;
    private String ai;
    private String aj;
    private int ak = 0;
    private com.budejie.www.activity.view.TopNavigationTabIndicator.ScrollViewCustom.a al = new com.budejie.www.activity.view.TopNavigationTabIndicator.ScrollViewCustom.a(this) {
        final /* synthetic */ PostsActivity a;

        {
            this.a = r1;
        }

        public void a() {
            Log.d("myOnScrollStopListner", "onScrollStoped");
        }

        public void b() {
            Log.d("myOnScrollStopListner", "onScrollToLeftEdge");
            try {
                this.a.o.setBackgroundResource(j.bK);
                this.a.n.setBackgroundResource(j.bJ);
            } catch (Exception e) {
            }
        }

        public void c() {
            Log.d("myOnScrollStopListner", "onScrollToRightEdge");
            try {
                this.a.o.setBackgroundResource(j.bJ);
                this.a.n.setBackgroundResource(j.bL);
            } catch (Exception e) {
            }
        }

        public void d() {
            Log.d("myOnScrollStopListner", "onScrollToMiddle");
            try {
                this.a.o.setBackgroundResource(j.bK);
                this.a.n.setBackgroundResource(j.bL);
            } catch (Exception e) {
            }
        }

        public void e() {
            this.a.o.setBackgroundResource(R.drawable.transparent);
            this.a.n.setBackgroundResource(R.drawable.transparent);
        }
    };
    private e am = new e(this) {
        final /* synthetic */ PostsActivity a;

        {
            this.a = r1;
        }

        public void a() {
            XListView g = this.a.s();
            if (g != null) {
                g.d();
            } else {
                XRecyclerView j = this.a.t();
                if (j != null) {
                    j.c();
                }
                this.a.u();
            }
            MobclickAgent.onEvent(this.a.T, "E01-A01", "点击刷新按钮");
        }

        public void b() {
            MobclickAgent.onEvent(this.a.T, "E01-A01", "点击穿越按钮");
            this.a.T.startActivity(this.a.X);
        }

        public void c() {
            MobclickAgent.onEvent(this.a.T, "E01-A01", "游戏大厅入口");
            Intent intent = new Intent();
            intent.setClass(this.a.T, HtmlFeatureActivity.class);
            intent.setData(Uri.parse(this.a.ai));
            this.a.T.startActivity(intent);
        }

        public void d() {
            MobclickAgent.onEvent(this.a.T, "E01-A01", "审帖入口");
            this.a.e();
        }
    };
    private OnClickListener an = new OnClickListener(this) {
        final /* synthetic */ PostsActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view.getId() == R.id.mycomment_delete_cancelBtn) {
                this.a.K.dismiss();
            } else if (view.getId() == R.id.mycomment_delete_sureBtn) {
                this.a.K.dismiss();
                ArrayList a = this.a.J.a();
                if (a != null && !a.isEmpty()) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < a.size(); i++) {
                        if (i == a.size() - 1) {
                            stringBuffer.append((String) a.get(i));
                        } else {
                            stringBuffer.append((String) a.get(i)).append(",");
                        }
                    }
                    this.a.G.a("add", this.a.h, stringBuffer.toString(), 971);
                }
            }
        }
    };
    private BroadcastReceiver ao = new BroadcastReceiver(this) {
        final /* synthetic */ PostsActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.budejie.www.publishcomments.action")) {
                switch (intent.getIntExtra(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, 1)) {
                    case 1:
                        if (this.a.W != null) {
                            this.a.W.setComment("1");
                            if (this.a.r() != null) {
                                this.a.r().notifyDataSetChanged();
                            }
                        }
                        this.a.unregisterReceiver(this.a.ao);
                        TipPopUp.a(context, TypeControl.pinglun);
                        return;
                    case 3:
                        this.a.unregisterReceiver(this.a.ao);
                        return;
                    default:
                        return;
                }
            }
        }
    };
    private TabPageIndicator.b ap;
    private OnClickListener aq;
    public String b;
    public String d;
    public PostsRelativeLayout e;
    public NavigationBar f;
    public SharedPreferences g;
    public String h;
    public com.budejie.www.g.b i;
    public IWXAPI j;
    public TypeIndicator m;
    public ImageView n;
    public ImageView o;
    @SuppressLint({"HandlerLeak"})
    final Handler p = new Handler(this) {
        final /* synthetic */ PostsActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            boolean z = false;
            int i = message.what;
            if (i == 4) {
                this.a.A.setLove(this.a.A.getLove() + 1);
            } else if (i == 5) {
                this.a.N = ProgressDialog.show(this.a, "", (String) message.obj, true, true);
            } else if (i == 6) {
                this.a.N.cancel();
            } else if (i == 7) {
                an.a(this.a, this.a.getString(R.string.already_collected), -1).show();
            } else if (i == 9) {
                if (this.a.A != null) {
                    try {
                        r0 = Integer.parseInt(this.a.A.getRepost()) + 1;
                    } catch (NumberFormatException e) {
                    }
                    this.a.A.setRepost(String.valueOf(r0));
                    Map hashMap = new HashMap();
                    hashMap.put("type", an.g(this.a.A.getType()));
                    hashMap.put("title", this.a.A.getContent());
                    hashMap.put("label", this.a.A.getPlateNames());
                    an.a(this.a, hashMap, "E01_A04");
                    com.budejie.www.util.m.a(this.a.T, this.a.p, this.a.A);
                }
            } else if (i == 91) {
                if (this.a.A != null) {
                    try {
                        r0 = Integer.parseInt(this.a.A.getRepost()) + 1;
                    } catch (NumberFormatException e2) {
                    }
                    this.a.A.setRepost(String.valueOf(r0));
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
                    this.a.B = "add";
                    this.a.G.a(this.a.B, ai.b(this.a), (String) message.obj, 971);
                }
            } else if (i == 12) {
                an.a(this.a, (int) R.string.collect_fail, (int) R.drawable.collect_tip).show();
            } else if (i == 100001) {
                an.a(this.a.T, this.a.T.getString(R.string.forwardAndCollect_succeed), -1).show();
                if (!TextUtils.isEmpty(ai.b(this.a))) {
                    this.a.B = "add";
                    this.a.G.a(this.a.B, ai.b(this.a), (String) message.obj, 971);
                }
            } else if (i == 829) {
                r0 = (String) message.obj;
                this.a.H.a("collectTable", r0);
                an.a(this.a, this.a.getString(R.string.delete_success), -1).show();
                this.a.B = "delete";
                this.a.G.a(this.a.B, ai.b(this.a), r0, 971);
            } else if (i == 13) {
                an.b(this.a, this.a.s());
            } else if (i == 1001) {
                Toast a;
                HashMap k = z.k((String) message.obj);
                if (k != null) {
                    r0 = (String) k.get("result_desc");
                    if (TextUtils.isEmpty(r0)) {
                        a = an.a(this.a, this.a.getString(R.string.operate_fail), -1);
                    } else {
                        a = an.a(this.a, r0, -1);
                    }
                } else {
                    a = an.a(this.a, this.a.getString(R.string.operate_fail), -1);
                }
                if (a != null) {
                    a.show();
                }
            } else if (i != 100002 && i == 1006) {
                SuggestedFollowsListItem suggestedFollowsListItem = new SuggestedFollowsListItem();
                suggestedFollowsListItem.uid = ((ListItemObject) message.obj).getUid();
                this.a.a(suggestedFollowsListItem, (ListItemObject) message.obj);
            }
            if (this.a.r() != null) {
                this.a.r().notifyDataSetChanged();
            }
        }
    };
    @SuppressLint({"HandlerLeak"})
    public Handler q = new Handler(this) {
        final /* synthetic */ PostsActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = false;
            int i2 = message.what;
            if (i2 == 601) {
                Bundle bundle = (Bundle) message.obj;
                Object string = bundle.getString(com.alipay.sdk.util.j.c);
                final int i3 = bundle.getInt("notificationId");
                if (TextUtils.isEmpty(string)) {
                    this.a.F.a(i3, (boolean) i, (int) R.string.forwarfail);
                } else {
                    HashMap l = z.l(string);
                    if ("0".equals(l.get(com.alipay.sdk.util.j.c))) {
                        this.a.F.a(i3, true, (String) l.get("msg"));
                        TipPopUp.a(this.a.T, TypeControl.share, TypeControl.zhuanqu);
                    } else {
                        this.a.F.a(i3, true, (String) l.get("msg"));
                        Toast.makeText(this.a.T, (CharSequence) l.get("msg"), i).show();
                    }
                }
                new Thread(this) {
                    final /* synthetic */ AnonymousClass6 b;

                    public void run() {
                        try {
                            Thread.sleep(1000);
                            this.b.a.q.sendMessage(this.b.a.q.obtainMessage(817, Integer.valueOf(i3)));
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
            } else if (i2 == 817) {
                this.a.F.a(((Integer) message.obj).intValue());
            }
            if (i2 == 814) {
            }
            String str;
            Map c;
            String str2;
            int i4;
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
                            if ("0".equals((String) c.get(com.alipay.sdk.util.j.c))) {
                                MobclickAgent.onEvent(this.a, "weibo_bind", "qzone_success");
                                this.a.h = (String) c.get("id");
                                this.a.C.a(this.a.h, c);
                                ai.a(this.a, this.a.h, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                this.a.I = this.a.E.a(this.a.h);
                                an.a(this.a, this.a.getString(R.string.bind_successed), -1).show();
                                i4 = this.a.g.getInt("position", -1);
                                if (-1 != i4) {
                                    this.a.E.a(this.a, (ListItemObject) this.a.r().getItem(i4), com.tencent.connect.common.Constants.SOURCE_QZONE, this.a.h, this.a.I, this.a.F, (Handler) this);
                                }
                                if (this.a.v()) {
                                    if (this.a.K == null) {
                                        this.a.w();
                                    } else {
                                        this.a.K.show();
                                    }
                                }
                            } else {
                                an.a(this.a, str2, -1).show();
                            }
                        }
                    }
                }
                this.a.q.sendEmptyMessage(815);
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
                            if ("0".equals((String) c.get(com.alipay.sdk.util.j.c))) {
                                this.a.h = (String) c.get("id");
                                this.a.C.a(this.a.h, c);
                                ai.a(this.a, this.a.h, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                if (OauthWeiboBaseAct.mAccessToken != null) {
                                    this.a.C.a(this.a.h, OauthWeiboBaseAct.mAccessToken.e());
                                }
                                this.a.I = this.a.E.a(this.a.h);
                                an.a(this.a, this.a.getString(R.string.bind_successed), -1).show();
                                Fragment d = this.a.d();
                                if (d instanceof com.budejie.www.activity.label.c) {
                                    com.budejie.www.activity.label.c cVar = (com.budejie.www.activity.label.c) d;
                                    if (!(cVar.a == null || cVar.a.getHuodongBean() == null)) {
                                        this.a.E.b(this.a.T, cVar.a.getHuodongBean(), this.a.I, this.a.F, this.a.q);
                                        return;
                                    }
                                }
                                i4 = this.a.g.getInt("position", -1);
                                if (-1 != i4) {
                                    com.budejie.www.adapter.c.b s = this.a.r();
                                    if (s != null && s.getCount() > i4) {
                                        this.a.E.a(this.a, (ListItemObject) s.getItem(i4), "sina", this.a.h, this.a.I, this.a.F, (Handler) this);
                                        this.a.r().notifyDataSetChanged();
                                    }
                                }
                                if (this.a.v()) {
                                    if (this.a.K == null) {
                                        this.a.w();
                                    } else {
                                        this.a.K.show();
                                    }
                                }
                            } else {
                                an.a(this.a, str2, -1).show();
                            }
                        }
                    }
                }
                this.a.q.sendEmptyMessage(815);
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
                            if ("0".equals((String) c.get(com.alipay.sdk.util.j.c))) {
                                MobclickAgent.onEvent(this.a, "weibo_bind", "tencent_success");
                                this.a.h = (String) c.get("id");
                                this.a.C.a(this.a.h, c);
                                ai.a(this.a, this.a.h, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                this.a.I = this.a.E.a(this.a.h);
                                an.a(this.a, this.a.getString(R.string.bind_successed), -1).show();
                                i4 = this.a.g.getInt("position", -1);
                                if (-1 != i4) {
                                    this.a.E.a(this.a, (ListItemObject) this.a.r().getItem(i4), "qq", this.a.h, this.a.I, this.a.F, (Handler) this);
                                }
                                this.a.r().notifyDataSetChanged();
                                if (this.a.v()) {
                                    if (this.a.K == null) {
                                        this.a.w();
                                    } else {
                                        this.a.K.show();
                                    }
                                }
                            } else {
                                an.a(this.a, str2, -1).show();
                            }
                        }
                    }
                }
                this.a.q.sendEmptyMessage(815);
            } else if (i2 == 816) {
                this.a.ad.g().ae.a();
                CharSequence string2 = ((Bundle) message.obj).getString(com.alipay.sdk.util.j.c);
                if (TextUtils.isEmpty(string2)) {
                    an.a(this.a.T, this.a.T.getString(R.string.forwarfail), -1).show();
                } else if ("0".equals(string2)) {
                    this.a.p.sendEmptyMessage(9);
                } else {
                    an.a(this.a.T, this.a.T.getString(R.string.forwarfail), -1).show();
                }
            } else if (i2 == 817) {
                this.a.F.a(((Integer) message.obj).intValue());
            }
        }
    };
    private String r;
    private ImageView s;
    private RelativeLayout t;
    private Button u;
    private RelativeLayout v;
    private ImageView w;
    private AsyncImageView x;
    private ImageView y;
    private Button z;

    public class a extends FragmentStatePagerAdapter {
        final /* synthetic */ PostsActivity a;

        public a(PostsActivity postsActivity, FragmentManager fragmentManager) {
            this.a = postsActivity;
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            return (Fragment) this.a.P.get(i);
        }

        public int getCount() {
            return this.a.P.size();
        }

        public Parcelable saveState() {
            return null;
        }

        public int getItemPosition(Object obj) {
            return -2;
        }
    }

    @SuppressLint({"NewApi"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 11) {
            StrictMode.setThreadPolicy(new Builder().permitAll().build());
        }
        setTheme(com.budejie.www.h.c.a().a(ai.a(this)));
        setContentView(R.layout.activity_posts_layout);
        com.budejie.www.widget.a.a((Activity) this);
        this.T = this;
        this.ad = (BudejieApplication) this.T.getApplication();
        this.P = new ArrayList();
        i();
        h();
        j();
        p();
        try {
            n();
        } catch (Exception e) {
            MobclickAgent.onEvent(this.T, "cacheException", "initViewPager:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
        this.i = new com.budejie.www.g.b(this, this.mSsoHandler, this.mTencent, this);
        k = an.d((Activity) this);
        Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "长图折叠屏数");
        if (!TextUtils.isEmpty(configParams)) {
            l = 0;
            try {
                l = Integer.parseInt(configParams);
            } catch (NumberFormatException e2) {
            }
        }
        if (this.w != null) {
            this.w.setVisibility(8);
        }
        EventBus.getDefault().register(this, 0);
    }

    public void onEventMainThread(com.budejie.www.busevent.a aVar) {
        if (this.P != null && !this.P.isEmpty()) {
            for (Fragment fragment : this.P) {
                if (fragment instanceof a) {
                    ((a) fragment).g();
                }
            }
        }
    }

    public void onEventBackgroundThread(LoadMoreEvent loadMoreEvent) {
        if (this.P != null && !this.P.isEmpty() && !TextUtils.isEmpty(this.d) && LoadMoreAction.LOAD_START == loadMoreEvent.a() && this.d.equals(loadMoreEvent.b())) {
            Fragment d = d();
            if (d instanceof a) {
                ((a) d).e();
            } else if (d instanceof b) {
                ((b) d).c();
            }
        }
    }

    public void onEventMainThread(LoginAction loginAction) {
        this.ag = true;
        this.ah = loginAction;
    }

    public void a(LoginAction loginAction) {
        int i = 0;
        if ("tag_essence".equals(this.d) || "tag_new".equals(this.d)) {
            int i2;
            if (loginAction != LoginAction.LOGIN) {
                ArrayList arrayList = HomeGroup.z.menus;
                if ("tag_essence".equals(this.d)) {
                    i2 = 0;
                } else {
                    i2 = 1;
                }
                this.V = (ArrayList) ((Navigation) arrayList.get(i2)).submenus.clone();
                List arrayList2 = new ArrayList();
                for (int i3 = 0; i3 < this.V.size(); i3++) {
                    arrayList2.add(this.P.get(((Integer) this.Q.get(((TopNavigation) this.V.get(i3)).getKey())).intValue()));
                }
                this.P = arrayList2;
                this.Q.clear();
                this.R.clear();
                while (i < this.V.size()) {
                    String key = ((TopNavigation) this.V.get(i)).getKey();
                    this.Q.put(key, Integer.valueOf(i));
                    this.R.put(Integer.valueOf(i), key);
                    i++;
                }
                i = 1;
            }
            if (i != 0) {
                try {
                    i2 = this.O.getCurrentItem();
                    this.S.notifyDataSetChanged();
                    this.m.a = this.V;
                    this.m.a();
                    TypeIndicator typeIndicator = this.m;
                    if (this.V.size() - 1 < i2) {
                        i2 = this.V.size() - 1;
                    }
                    typeIndicator.setCurrentItem(i2);
                    this.S.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.e("tangjian", "fragment更新异常");
                    e.printStackTrace();
                }
            }
            Log.i("tangjian", "PostsActivity执行了！");
        }
    }

    private void a(TopNavigation topNavigation) {
        Fragment a = com.budejie.www.activity.label.c.a(topNavigation.url, topNavigation.getKey(), "tag_essence".equals(this.d) ? StatisticCodeTable.HOT : "new");
        a.setRetainInstance(false);
        this.P.add(a);
        this.Q.put(topNavigation.getKey(), Integer.valueOf(this.P.indexOf(a)));
        this.R.put(Integer.valueOf(this.P.indexOf(a)), topNavigation.getKey());
    }

    private void h() {
        try {
            if (HomeGroup.z.menus.get(2) != null) {
                this.X = new Intent(this, PostsActivity.class);
                this.X.putExtra("navigation_key", (Serializable) HomeGroup.z.menus.get(2));
                this.X.putExtra("tag_all", "tag_suiji");
                this.Y = new Intent(this, PostsActivity.class);
                this.Y.putExtra("post_type", (Serializable) HomeGroup.z.menus.get(2));
                this.Y.putExtra("tag_all", "tag_nearby");
                this.Z = new Intent(this, LabelSubscribeActivity.class);
            }
        } catch (Exception e) {
            MobclickAgent.onEvent(this.T, "cacheException", "PostsActivity createRandomIntentAndNearByIntent:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    private void i() {
        this.g = getSharedPreferences("weiboprefer", 0);
        Intent intent = getIntent();
        this.d = intent.getStringExtra("tag_all") == null ? "tag_essence" : intent.getStringExtra("tag_all");
        this.b = intent.getStringExtra("notify_action_key");
        Navigation navigation = (Navigation) intent.getSerializableExtra("navigation_key");
        if (navigation != null) {
            ArrayList arrayList = navigation.submenus;
            if (arrayList != null) {
                this.V = (ArrayList) arrayList.clone();
                this.r = intent.getStringExtra("post_type") == null ? ((TopNavigation) this.V.get(0)).getKey() : intent.getStringExtra("post_type");
                this.ai = OnlineConfigAgent.getInstance().getConfigParams(this, "jingxuan_left_game_url");
                this.aj = OnlineConfigAgent.getInstance().getConfigParams(this, "精华_左导航");
                ((BudejieApplication) getApplication()).a(this.d, this);
            }
        }
    }

    private void j() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.e = (PostsRelativeLayout) findViewById(R.id.postsLayout);
        this.f = (NavigationBar) findViewById(R.id.navigation_bar);
        this.f.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.title_background));
        this.aa = (ImageButton) findViewById(R.id.bottom_refresh_btn);
        if ("tag_nearby".equals(this.d)) {
            this.aa.setVisibility(0);
            this.aa.setOnClickListener(m());
        } else {
            this.aa.setVisibility(8);
            this.aa.setOnClickListener(null);
        }
        o();
        if ("tag_essence".equals(this.d)) {
            b(this.am);
            e(this.am);
        } else if ("tag_new".equals(this.d)) {
            c(this.am);
            f(this.am);
        } else if ("tag_suiji".equals(this.d)) {
            a(this.am);
            d(this.am);
            k();
        } else if ("tag_nearby".equals(this.d)) {
            a(this.am);
        } else if ("tag_ranking".equals(this.d)) {
            a(this.am);
        }
        this.m = (TypeIndicator) findViewById(R.id.indicator);
        this.O = (FixedViewPager) findViewById(R.id.viewpager);
        this.n = (ImageView) findViewById(R.id.iv_top_navigation_right);
        this.o = (ImageView) findViewById(R.id.iv_top_navigation_left);
        this.af = (ScrollViewCustom) findViewById(R.id.horizontal_scroll_view);
        this.af.setOnScrollStopListner(this.al);
        this.m.a(this.O, this.V, this.af);
        x();
        this.m.setOnTabSelectedListener(this.ap);
        y();
        this.m.a(this.aq);
        l();
    }

    private void k() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.content_layout);
        LayoutParams layoutParams = (LayoutParams) relativeLayout.getLayoutParams();
        layoutParams.setMargins(0, 0, 0, 0);
        relativeLayout.setLayoutParams(layoutParams);
    }

    private void l() {
        try {
            if (getBudejieSettings().e.a().booleanValue()) {
                CharSequence configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "顶部导航是否自动隐藏");
                if (TextUtils.isEmpty(configParams) || !Constants.SERVICE_SCOPE_FLAG_VALUE.equals(configParams)) {
                    getBudejieSettings().d.a(Boolean.valueOf(false));
                } else {
                    getBudejieSettings().d.a(Boolean.valueOf(true));
                }
            }
            if (this.ad.g().f.a().booleanValue()) {
                HomeGroup.a(this.T, 0);
            } else {
                HomeGroup.a(this.T, getResources().getDimensionPixelOffset(R.dimen.navigation_height));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private OnClickListener m() {
        return new OnClickListener(this) {
            final /* synthetic */ PostsActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.am != null) {
                    this.a.am.a();
                } else {
                    this.a.finish();
                }
            }
        };
    }

    private int d(String str) {
        if (str != null && str.equals("tag_new")) {
            return 2;
        }
        if (str != null && str.equals("tag_nearby")) {
            return 4;
        }
        if (str != null && str.equals("tag_suiji")) {
            return 3;
        }
        if (str == null || !str.equals("tag_ranking")) {
            return 1;
        }
        return 5;
    }

    @SuppressLint({"UseSparseArrays"})
    private void n() {
        this.Q = new HashMap();
        this.R = new HashMap();
        Iterator it = this.V.iterator();
        while (it.hasNext()) {
            TopNavigation topNavigation = (TopNavigation) it.next();
            Bundle bundle = new Bundle();
            Fragment b;
            if ("topic".equals(topNavigation.type)) {
                if ("BDJ_To_SixRooms".equals(topNavigation.url)) {
                    b = com.d.a.b();
                } else if ("BDJ_To_HuaXiReader".equals(topNavigation.url)) {
                    b = com.b.a.b();
                } else {
                    if (topNavigation.display_num == 2) {
                        b = new b();
                    } else {
                        b = new a();
                    }
                    bundle.putInt("page_type", d(this.d));
                    bundle.putString("post_type", topNavigation.getKey());
                    bundle.putString("TOP_NUM_KEY", (this.P.size() + 1) + "");
                    bundle.putSerializable("TOP_NAVIGATION_KEY", topNavigation);
                    b.setArguments(bundle);
                }
                b.setRetainInstance(false);
                this.P.add(b);
                this.Q.put(topNavigation.getKey(), Integer.valueOf(this.P.indexOf(b)));
                this.R.put(Integer.valueOf(this.P.indexOf(b)), topNavigation.getKey());
            } else if (LoginConstants.H5_LOGIN.equals(topNavigation.type)) {
                if ("BDJ_To_SixRooms".equals(topNavigation.url)) {
                    b = com.d.a.b();
                } else if ("BDJ_To_HuaXiReader".equals(topNavigation.url)) {
                    b = com.b.a.b();
                } else {
                    b = new c();
                    bundle.putSerializable("TOP_NAVIGATION_KEY", topNavigation);
                    b.setArguments(bundle);
                }
                b.setRetainInstance(false);
                this.P.add(b);
                this.Q.put(topNavigation.getKey(), Integer.valueOf(this.P.indexOf(b)));
                this.R.put(Integer.valueOf(this.P.indexOf(b)), topNavigation.getKey());
            } else {
                a(topNavigation);
            }
        }
        this.S = new a(this, getSupportFragmentManager());
        this.O.setAdapter(this.S);
        if (an.c(this.T)) {
            this.O.setOffscreenPageLimit(1);
        }
        this.m.setOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ PostsActivity a;

            {
                this.a = r1;
            }

            public void onPageSelected(int i) {
                try {
                    k.a(this.a).p();
                    this.a.r = (String) this.a.R.get(Integer.valueOf(i));
                    if ("tag_essence".equals(this.a.d)) {
                        this.a.ad.g().ag.a(this.a.r);
                        i.a(new StringBuilder(this.a.getString(R.string.main_bottom_essence)).append("-").append(((TopNavigation) this.a.V.get(i)).name).toString());
                    } else if ("tag_new".equals(this.a.d)) {
                        this.a.ad.g().ah.a(this.a.r);
                        i.a(new StringBuilder(this.a.getString(R.string.main_bottom_latest)).append("-").append(((TopNavigation) this.a.V.get(i)).name).toString());
                    } else if ("tag_suiji".equals(this.a.d)) {
                        i.a(new StringBuilder(this.a.getString(R.string.track_screen_through)).append("-").append(((TopNavigation) this.a.V.get(i)).name).toString());
                    } else if ("tag_ranking".equals(this.a.d)) {
                        i.a(new StringBuilder(this.a.getString(R.string.track_screen_ranking)).append("-").append(((TopNavigation) this.a.V.get(i)).name).toString());
                    }
                    if (this.a.m.getVisibility() == 8) {
                        this.a.m.b();
                    }
                    if (this.a.q() && this.a.s() != null) {
                        this.a.s().d();
                    }
                } catch (Exception e) {
                    MobclickAgent.onEvent(this.a.T, "cacheException", "PostsActivity onPageSelected:" + e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }

            public void onPageScrolled(int i, float f, int i2) {
                if (i2 != 0) {
                    k.a(this.a.T).d(true);
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        b(this.r);
    }

    private void a(e eVar) {
        try {
            this.z = (Button) getLayoutInflater().inflate(R.layout.navigation_return_view, null);
            this.z.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ PostsActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.finish();
                }
            });
            this.f.setLeftView(this.z);
        } catch (Exception e) {
            MobclickAgent.onEvent(this.T, "cacheException", "PostsActivity setLeftRetrunView:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    private void o() {
        LinearLayout linearLayout;
        if ("tag_essence".equals(this.d) || "tag_new".equals(this.d)) {
            linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.navigation_middle_item, null);
            linearLayout.setOnClickListener(m());
            this.ab = (ImageView) linearLayout.findViewById(R.id.top_navigation_middle);
            this.ac = (ImageButton) linearLayout.findViewById(R.id.top_refresh_btn);
            this.ab.setImageResource(com.budejie.www.h.c.a().b(R.attr.top_navigation_middle));
            this.ac.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.top_refresh_btn));
            this.f.setMiddleView(linearLayout);
        } else if ("tag_nearby".equals(this.d)) {
            linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.navigation_middle_item_textview, null);
            r1 = (TextView) linearLayout.findViewById(R.id.title_center_txt);
            r1.setTextColor(getResources().getColor(j.be));
            r1.setText("附近");
            this.f.setMiddleView(linearLayout);
        } else if ("tag_suiji".equals(this.d)) {
            linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.navigation_middle_item_textview, null);
            r1 = (TextView) linearLayout.findViewById(R.id.title_center_txt);
            r1.setTextColor(getResources().getColor(j.be));
            r1.setText("穿越");
            this.f.setMiddleView(linearLayout);
        } else if ("tag_ranking".equals(this.d)) {
            linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.navigation_middle_item_textview, null);
            r1 = (TextView) linearLayout.findViewById(R.id.title_center_txt);
            r1.setTextColor(getResources().getColor(j.be));
            r1.setText("每日排行");
            this.f.setMiddleView(linearLayout);
        }
    }

    private void b(final e eVar) {
        try {
            if (this.t == null) {
                this.t = (RelativeLayout) getLayoutInflater().inflate(R.layout.navigation_suiji_view, null);
                this.s = (ImageView) this.t.findViewById(R.id.iv_random);
                this.s.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ PostsActivity b;

                    public void onClick(View view) {
                        if (eVar != null) {
                            eVar.b();
                        } else {
                            this.b.finish();
                        }
                    }
                });
            }
            if (this.f != null) {
                this.f.setRightView(this.t);
            }
        } catch (Exception e) {
            MobclickAgent.onEvent(this.T, "cacheException", "PostsActivity setRightSuijiView:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    private void c(e eVar) {
        try {
            if (this.v == null) {
                this.v = (RelativeLayout) getLayoutInflater().inflate(R.layout.title_right_search, null);
                this.u = (Button) this.v.findViewById(R.id.SrearchTitleButton);
                this.u.setVisibility(0);
                this.u.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ PostsActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        MobclickAgent.onEvent(this.a.T, "E02_A14", "新帖搜素入口");
                        this.a.startActivity(new Intent(this.a, SearchMainActivity.class));
                    }
                });
            }
            if (this.f != null) {
                this.f.setRightView(this.v);
            }
        } catch (Exception e) {
            MobclickAgent.onEvent(this.T, "cacheException", "PostsActivity setRightSuijiView:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    private void d(final e eVar) {
        try {
            if (this.t == null) {
                this.t = (RelativeLayout) getLayoutInflater().inflate(R.layout.navigation_suiji_view, null);
                this.s = (ImageView) this.t.findViewById(R.id.iv_random);
                this.s.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ PostsActivity b;

                    public void onClick(View view) {
                        if (eVar != null) {
                            eVar.a();
                        } else {
                            this.b.finish();
                        }
                    }
                });
            }
            if (this.f != null) {
                this.f.setRightView(this.t);
            }
        } catch (Exception e) {
            MobclickAgent.onEvent(this.T, "cacheException", "PostsActivity setRightSuijiRefreshView:" + e.getLocalizedMessage());
        }
    }

    private void e(final e eVar) {
        if (this.ai != null && !this.ai.equals("null")) {
            if (this.x == null) {
                this.x = (AsyncImageView) getLayoutInflater().inflate(R.layout.left_label_entrance, null);
                if (this.aj == null || this.aj.equals("") || this.aj.equals("null")) {
                    this.x.setImageResource(com.budejie.www.h.c.a().b(R.attr.top_games_btn_bg));
                } else {
                    this.x.setAsyncCacheImage(this.aj);
                }
                this.x.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ PostsActivity b;

                    public void onClick(View view) {
                        if (eVar != null) {
                            eVar.c();
                        } else {
                            this.b.finish();
                        }
                    }
                });
            }
            if (this.f != null) {
                this.f.setLeftView(this.x);
            }
        }
    }

    private void f(final e eVar) {
        if (this.y == null) {
            this.y = (ImageView) getLayoutInflater().inflate(R.layout.left_label_entrance, null);
            this.y.setImageResource(com.budejie.www.h.c.a().b(R.attr.top_audit_btn_bg));
            this.y.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ PostsActivity b;

                public void onClick(View view) {
                    if (eVar != null) {
                        eVar.d();
                    } else {
                        this.b.finish();
                    }
                }
            });
        }
        if (this.f != null) {
            this.f.setLeftView(this.y);
        }
    }

    protected void onResume() {
        super.onResume();
        try {
            if (a) {
                a = false;
                if (!(this.O == null || com.budejie.www.goddubbing.c.a.a(this.P))) {
                    int currentItem = this.O.getCurrentItem();
                    if (c(currentItem)) {
                        a((Fragment) this.P.get(currentItem));
                    }
                    if (c(currentItem - 1)) {
                        a((Fragment) this.P.get(currentItem - 1));
                    }
                    if (c(currentItem + 1)) {
                        a((Fragment) this.P.get(currentItem + 1));
                    }
                }
            }
            if (!this.ae || (this.ae && this.O.getCurrentItem() == 0)) {
                if ("tag_essence".equals(this.d)) {
                    i.a(new StringBuilder(getString(R.string.main_bottom_essence)).append("-").append(((TopNavigation) this.V.get(this.O.getCurrentItem())).name).toString());
                } else if ("tag_new".equals(this.d)) {
                    i.a(new StringBuilder(getString(R.string.main_bottom_latest)).append("-").append(((TopNavigation) this.V.get(this.O.getCurrentItem())).name).toString());
                } else if ("tag_suiji".equals(this.d)) {
                    i.a(new StringBuilder(getString(R.string.track_screen_through)).append("-").append(((TopNavigation) this.V.get(this.O.getCurrentItem())).name).toString());
                } else if ("tag_ranking".equals(this.d)) {
                    i.a(new StringBuilder(getString(R.string.track_screen_ranking)).append("-").append(((TopNavigation) this.V.get(this.O.getCurrentItem())).name).toString());
                }
            }
            this.ae = false;
        } catch (Exception e) {
            MobclickAgent.onEvent(this.T, "cacheException", "PostsActivity onResume:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
        this.m.postDelayed(new Runnable(this) {
            final /* synthetic */ PostsActivity a;

            {
                this.a = r1;
            }

            public void run() {
                FloatVideoLayout.a(this.a.T, !this.a.f.getMaskLayout().isShown());
            }
        }, 200);
    }

    private boolean c(int i) {
        if (com.budejie.www.goddubbing.c.a.a(this.P)) {
            return false;
        }
        int size = this.P.size();
        if (i < 0 || i >= size) {
            return false;
        }
        return true;
    }

    private void a(Fragment fragment) {
        if (fragment != null && (fragment instanceof b)) {
            com.budejie.www.adapter.e d = ((b) fragment).d();
            if (d != null) {
                d.notifyDataSetChanged();
            }
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && this.ag) {
            a(this.ah);
            this.ah = null;
            this.ag = false;
        }
    }

    protected void onPause() {
        super.onPause();
        try {
            this.f.setFocusable(true);
            this.f.setFocusableInTouchMode(true);
            this.f.requestFocus();
            k.a((Context) this).o();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        k.a((Context) this).p();
        EventBus.getDefault().unregister(this);
        ((BudejieApplication) getApplication()).a(this.d);
    }

    private void p() {
        this.h = this.g.getString("id", "");
        this.C = new m(this);
        this.D = new g(this);
        this.F = new com.elves.update.a(this);
        this.G = com.budejie.www.http.b.a(this, this);
        this.H = new com.budejie.www.c.b(this);
        this.E = new n(this);
        this.j = WXAPIFactory.createWXAPI(this, "wx592fdc48acfbe290", true);
        this.j.registerApp("wx592fdc48acfbe290");
        this.I = com.budejie.www.h.b.a().a(this.h, this);
        this.J = new d(this);
        this.L = new f(this);
        this.M = new com.budejie.www.http.m();
    }

    private boolean q() {
        Fragment d = d();
        if (!(d instanceof a)) {
            return false;
        }
        if (((a) d).a(true) == null || ((a) d).a(true).size() <= 0) {
            return true;
        }
        return false;
    }

    private com.budejie.www.adapter.c.b r() {
        Fragment d = d();
        if (d instanceof a) {
            return ((a) d).d();
        }
        return null;
    }

    private XListView s() {
        Fragment d = d();
        if (d instanceof a) {
            return ((a) d).c();
        }
        if (d instanceof com.budejie.www.activity.label.c) {
            return ((com.budejie.www.activity.label.c) d).c();
        }
        return null;
    }

    private XRecyclerView t() {
        Fragment d = d();
        if (d instanceof b) {
            return ((b) d).a();
        }
        return null;
    }

    public Fragment d() {
        return b(this.O.getCurrentItem());
    }

    public Fragment b(int i) {
        return (Fragment) this.P.get(i);
    }

    public void a(Intent intent, boolean z) {
        if (intent != null) {
            this.d = intent.getStringExtra("tag_all") == null ? "tag_essence" : intent.getStringExtra("tag_all");
            this.r = intent.getStringExtra("post_type") == null ? ((TopNavigation) this.V.get(0)).getKey() : intent.getStringExtra("post_type");
            if ((TextUtils.isEmpty(this.d) || !this.d.equals("tag_nearby") || an.a(this.g)) && !TextUtils.isEmpty(this.r)) {
                b(this.r);
            }
        }
    }

    public void b(String str) {
        XListView xListView = null;
        try {
            XRecyclerView xRecyclerView;
            int intValue = this.Q.get(str) == null ? 0 : ((Integer) this.Q.get(str)).intValue();
            this.O.setCurrentItem(intValue);
            Fragment b = b(intValue);
            if (b instanceof a) {
                xListView = ((a) b).c();
                xRecyclerView = null;
            } else if (b instanceof com.budejie.www.activity.label.c) {
                xListView = ((com.budejie.www.activity.label.c) b).c();
                ViewGroup viewGroup = null;
            } else if (b instanceof b) {
                xRecyclerView = ((b) b).a();
            } else {
                if (b instanceof NativeBookStoreFragment) {
                    com.spriteapp.booklibrary.widget.xrecyclerview.XRecyclerView recyclerView = ((NativeBookStoreFragment) b).getRecyclerView();
                    if (recyclerView != null) {
                        recyclerView.refresh();
                    }
                }
                xRecyclerView = null;
            }
            if (xListView != null) {
                xListView.d();
            }
            if (xRecyclerView != null) {
                xRecyclerView.c();
            }
        } catch (Exception e) {
            MobclickAgent.onEvent(this.T, "cacheException", "PostsActivity onSelected:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public void e(View view, ListItemObject listItemObject) {
        view.setTag(listItemObject);
        this.i.a(3, null).onClick(view);
    }

    public void a(View view, ListItemObject listItemObject, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(PersonalProfileActivity.c, listItemObject.getUid());
        bundle.putString(PersonalProfileActivity.d, str);
        this.i.a(7, bundle).onClick(view);
    }

    public void c(View view, ListItemObject listItemObject) {
        p.a((Activity) this, listItemObject, this.p);
    }

    public void a(View view, ListItemObject listItemObject) {
        TipPopUp.a((Context) this, TypeControl.dingtie);
        this.L.a("ding", this.q, listItemObject);
        if (listItemObject.getStatus() == 1 && listItemObject.getIsIsRecsysData()) {
            this.M.a((Activity) this, ai.b(this), listItemObject.getWid(), "1");
        } else {
            this.L.a(listItemObject, this.q, "ding");
        }
    }

    public void b(View view, ListItemObject listItemObject) {
        this.L.a("cai", this.q, listItemObject);
        if (listItemObject.getStatus() == 1 && listItemObject.getIsIsRecsysData()) {
            this.M.a((Activity) this, ai.b(this), listItemObject.getWid(), "2");
        } else {
            this.L.a(listItemObject, this.q, "cai");
        }
    }

    public void a(View view, ListItemObject listItemObject, int i) {
        listItemObject.setForwardNoCollect(false);
        this.A = listItemObject;
        Bundle bundle = new Bundle();
        bundle.putInt("position", i);
        bundle.putString(HistoryOpenHelper.COLUMN_UID, ai.b(this));
        bundle.putSerializable("weiboMap", this.I);
        bundle.putSerializable("data", listItemObject);
        view.setTag(listItemObject);
        this.i.a(5, bundle, this.p, this.j, this.C, this.E, this.F, this.g, this.q).onClick(view);
    }

    public void d(View view, ListItemObject listItemObject) {
        view.setTag(listItemObject);
        this.i.a(3, null).onClick(view);
        Log.i("PostsActivity", "主屏按钮-" + e(this.r) + "评论");
        MobclickAgent.onEvent((Context) this, "主屏按钮", e(this.r) + "评论 ");
        this.W = listItemObject;
        registerReceiver(this.ao, new IntentFilter("com.budejie.www.publishcomments.action"));
    }

    public void e() {
        if (an.a((Context) this)) {
            if (an.a(this.g)) {
                Intent intent = new Intent(this, AuditPostsActivity.class);
                intent.putExtra("post_type", "1");
                startActivity(intent);
            } else {
                an.a((Activity) this, 1, "tiezi", "shenhe", 128);
            }
            if (!this.g.getBoolean("shenheUpdate", false)) {
                this.g.edit().putBoolean("shenheUpdate", true).commit();
            }
            MobclickAgent.onEvent(this, "sheheTiezi");
            return;
        }
        an.a((Activity) this, getString(R.string.nonet), -1).show();
    }

    public void a() {
        if (an.a((Context) this)) {
            XListView s = s();
            if (s != null) {
                s.d();
                return;
            }
            return;
        }
        an.a((Activity) this, getString(R.string.nonet), -1).show();
    }

    private void u() {
        Fragment d = d();
        if (d instanceof NativeBookStoreFragment) {
            com.spriteapp.booklibrary.widget.xrecyclerview.XRecyclerView recyclerView = ((NativeBookStoreFragment) d).getRecyclerView();
            if (recyclerView != null) {
                recyclerView.refresh();
            }
        }
    }

    public void f() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.refresh_button_rotating);
        loadAnimation.setInterpolator(new LinearInterpolator());
        if ("tag_essence".equals(this.d) || "tag_new".equals(this.d)) {
            if (this.ac != null) {
                this.ac.startAnimation(loadAnimation);
            }
        } else if (("tag_nearby".equals(this.d) || "tag_ranking".equals(this.d)) && this.aa != null) {
            this.aa.startAnimation(loadAnimation);
        }
        if (this.m.getVisibility() == 8) {
            this.m.b();
        }
    }

    public void g() {
        if ("tag_essence".equals(this.d) || "tag_new".equals(this.d)) {
            if (this.ac != null) {
                this.ac.clearAnimation();
            }
        } else if (("tag_nearby".equals(this.d) || "tag_ranking".equals(this.d)) && this.aa != null) {
            this.aa.clearAnimation();
        }
    }

    public void a(String str) {
        this.d = str;
        if (this.d == null || "tag_new".equals(this.d)) {
            c(this.am);
        } else if ("tag_essence".equals(this.d)) {
            b(this.am);
        }
        b(this.r);
    }

    public void c() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment instanceof a) {
                    ((a) fragment).a();
                } else if (fragment instanceof b) {
                    ((b) fragment).b();
                }
            }
        }
    }

    public void a(int i, String str) {
    }

    public void a(int i) {
    }

    public void onWbShareSuccess() {
        super.onWbShareSuccess();
        am.a(this.A);
        if (r() != null) {
            r().notifyDataSetChanged();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        boolean z = false;
        super.onActivityResult(i, i2, intent);
        if (i2 == 711) {
            this.q.sendEmptyMessage(814);
            bindTencent();
        } else if (i2 == 125) {
            startActivity(new Intent(this, AuditPostsActivity.class));
        } else if (i2 == 128) {
            if (intent.getBooleanExtra("success", false)) {
                startActivity(new Intent(this, AuditPostsActivity.class));
            }
        } else if (i == R$styleable.Theme_Custom_divider_horizontal_bg || i2 == R$styleable.Theme_Custom_divider_horizontal_bg) {
            if (intent != null) {
                z = intent.getBooleanExtra("success", false);
            }
            if (z) {
                Fragment d = d();
                if (d instanceof a) {
                    a aVar = (a) d;
                    if (aVar.c() != null) {
                        aVar.c().d();
                    }
                }
            }
        }
    }

    public void onComplete(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        super.onComplete(jSONObject);
        HashMap a = z.a(jSONObject);
        if (a != null && a.size() != 0) {
            this.g.edit().putString("openid", (String) a.get("qzone_uid")).putString("qzone_token", (String) a.get("qzone_token")).putString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN, (System.currentTimeMillis() + (Long.parseLong((String) a.get(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN)) * 1000)) + "").commit();
            this.h = this.g.getString("id", "");
            this.E.a((String) a.get("qzone_uid"), this.h, (String) a.get("qzone_token"), 929, this.q);
        }
    }

    public void onError(UiError uiError) {
        super.onError(uiError);
        Toast.makeText(this, "code:" + uiError.errorCode + ", msg:" + uiError.errorMessage + ", detail:" + uiError.errorDetail, 0).show();
    }

    public void onSuccess(com.sina.weibo.sdk.auth.b bVar) {
        try {
            this.h = this.g.getString("id", "");
            mAccessToken = bVar;
            if (mAccessToken.a()) {
                com.sina.weibo.sdk.auth.a.a(this, mAccessToken);
                au.a((int) R.string.oauthSuccess);
                this.E.a(mAccessToken, this.h, 812, this.q);
            }
        } catch (Exception e) {
            au.a((int) R.string.sina_shouquan_failed);
        }
    }

    public void bindTencent() {
        this.h = this.g.getString("id", "");
        String sharePersistent = Util.getSharePersistent(this, "ACCESS_TOKEN");
        this.E.a(Util.getSharePersistent(this, "NAME"), sharePersistent, Util.getSharePersistent(this, "OPEN_ID"), this.h, 813, this.q);
    }

    private boolean v() {
        ArrayList a = this.J.a();
        if (a == null || a.isEmpty()) {
            return false;
        }
        return true;
    }

    private void w() {
        this.K = new Dialog(this, R.style.dialogTheme);
        View inflate = getLayoutInflater().inflate(R.layout.mycomment_delete_dialog, null);
        ((TextView) inflate.findViewById(R.id.mycomment_delete_text)).setText(getString(R.string.mycollect_sync_text));
        Button button = (Button) inflate.findViewById(R.id.mycomment_delete_sureBtn);
        ((Button) inflate.findViewById(R.id.mycomment_delete_cancelBtn)).setOnClickListener(this.an);
        button.setOnClickListener(this.an);
        this.K.setContentView(inflate);
        Window window = this.K.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = an.a((Context) this, 300);
        window.setAttributes(attributes);
    }

    public void a_(String str) {
        try {
            if ("sina".equals(str)) {
                if (this.mSsoHandler == null) {
                    this.mSsoHandler = new com.sina.weibo.sdk.auth.a.a(this);
                }
                this.mSsoHandler.a((com.sina.weibo.sdk.auth.d) this);
            } else if ("qq".equals(str)) {
                if (this.mTencent == null) {
                    this.mTencent = Tencent.createInstance("100336987", this);
                }
                this.mTencent.login((Activity) this, "get_simple_userinfo,get_user_profile,add_share,add_topic,list_album,upload_pic,add_album", (IUiListener) this);
            } else if ("tencent".equals(str)) {
                auth(Long.valueOf(Util.getConfig().getProperty("APP_KEY")).longValue(), Util.getConfig().getProperty("APP_KEY_SEC"));
            } else if ("phone".equals(str)) {
                startActivity(new Intent(this, PhoneNumberLoginActivity.class));
            } else if ("login".equals(str)) {
                startActivity(new Intent(this, OAuthWeiboActivity.class));
            } else if ("refersh".equals(str)) {
                r0 = d();
                if (r0 instanceof a) {
                    ((a) r0).b(true);
                }
            } else if ("1".equals(str)) {
                try {
                    r0 = d();
                    if (r0 instanceof a) {
                        r0 = ((a) r0).a(str);
                        r1 = new Intent(this, CommonLabelActivity.class);
                        r1.putExtra("theme_id", Integer.valueOf(r0));
                        startActivity(r1);
                    }
                } catch (Exception e) {
                }
            } else if ("2".equals(str)) {
                startActivity(new Intent(this, LabelSubscribeActivity.class));
            } else if ("3".equals(str)) {
                r0 = d();
                if (r0 instanceof a) {
                    r0 = ((a) r0).a(str);
                    r1 = new Intent(this, PersonalProfileActivity.class);
                    r1.putExtra(PersonalProfileActivity.c, r0);
                    startActivity(r1);
                }
            } else if ("4".equals(str)) {
                r0 = d();
                if (r0 instanceof a) {
                    r1 = ((a) r0).a(str);
                    boolean endsWith = r1.endsWith(".apk");
                    if (!endsWith && r1.contains("?")) {
                        endsWith = r1.substring(0, r1.indexOf("?")).endsWith(".apk");
                    }
                    if (endsWith) {
                        p.a((Activity) this, r1);
                        return;
                    }
                    Intent intent = new Intent(this, HtmlFeatureActivity.class);
                    intent.setData(Uri.parse(r1));
                    startActivity(intent);
                }
            } else if ("5".equals(str)) {
                try {
                    r0 = d();
                    if (r0 instanceof a) {
                        com.budejie.www.util.a.a((Activity) this, null, new JSONObject(((a) r0).a(str)).getString("id"), false);
                    }
                } catch (JSONException e2) {
                    Log.e("", "ljj-->onLoginClick:" + e2.toString());
                }
            } else if ("6".equals(str)) {
                try {
                    r0 = d();
                    if (r0 instanceof a) {
                        JSONObject jSONObject = new JSONObject(((a) r0).a(str));
                        JSONArray jSONArray = jSONObject.getJSONArray("content_type");
                        if (jSONArray == null || jSONArray.length() <= 0) {
                            an.a((Activity) this, "暂时没有发帖的类型!", -1).show();
                            return;
                        }
                        int length = jSONArray.length();
                        String[] strArr = new String[length];
                        for (int i = 0; i < length; i++) {
                            strArr[i] = jSONArray.getString(i);
                        }
                        Integer valueOf = Integer.valueOf(jSONObject.getInt("theme_id"));
                        r1 = jSONObject.getString("theme_name");
                        Map hashMap = new HashMap();
                        hashMap.put("theme_id", valueOf);
                        hashMap.put("theme_name", r1);
                        p.a((Activity) this, strArr, hashMap);
                    }
                } catch (JSONException e22) {
                    Log.e("", "ljj-->onLoginClick:" + e22.toString());
                }
            } else if (AlibcJsResult.CLOSED.equals(str)) {
                startActivity(new Intent(this, SuggestedFollowsActivity.class));
            } else if ("8".equals(str)) {
                startActivity(new Intent(this, MyAccountActivity.class));
            }
        } catch (Exception e3) {
            MobclickAgent.onEvent(this.T, "cacheException", "PostsActivity onLoginClick:" + e3.getLocalizedMessage());
            e3.printStackTrace();
        }
    }

    public OnClickListener b() {
        this.h = this.g.getString("id", "");
        if (this.I == null) {
            this.I = this.E.a(this.h);
        }
        Bundle bundle = new Bundle();
        bundle.putString(HistoryOpenHelper.COLUMN_UID, this.h);
        bundle.putSerializable("weiboMap", this.I);
        return this.i.a(5, bundle, this.p, this.j, this.C, this.E, this.F, this.g, this.p);
    }

    public void a(SuggestedFollowsListItem suggestedFollowsListItem) {
        if (an.a((Context) this)) {
            MobclickAgent.onEvent(this.T, "E01-A04", "关注点击次数");
            int i = suggestedFollowsListItem.is_follow;
            if (i == 1) {
                a(suggestedFollowsListItem, null);
                return;
            } else if (i == 0) {
                b(suggestedFollowsListItem);
                return;
            } else {
                return;
            }
        }
        an.a((Activity) this, getString(R.string.nonet), -1).show();
    }

    private void b(final SuggestedFollowsListItem suggestedFollowsListItem) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new com.budejie.www.http.j().d(this.T, suggestedFollowsListItem.uid), new net.tsz.afinal.a.a<String>(this) {
            final /* synthetic */ PostsActivity b;

            public /* synthetic */ void onSuccess(Object obj) {
                a((String) obj);
            }

            public void onFailure(Throwable th, int i, String str) {
                suggestedFollowsListItem.is_follow = 0;
                as.b().b(suggestedFollowsListItem);
            }

            public void a(String str) {
                try {
                    Toast a;
                    ResultBean s = z.s(str);
                    if (s != null) {
                        String msg = s.getMsg();
                        CharSequence code = s.getCode();
                        if (TextUtils.isEmpty(msg)) {
                            a = an.a(this.b.T, this.b.getString(R.string.operate_fail), -1);
                        } else {
                            a = an.a(this.b.T, msg, -1);
                        }
                        if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                            suggestedFollowsListItem.is_follow = 1;
                            this.b.D.a(new Fans(suggestedFollowsListItem));
                            as.b().a(suggestedFollowsListItem.uid, Integer.valueOf(1));
                        }
                        as.b().b(suggestedFollowsListItem);
                    } else {
                        a = an.a(this.b.T, this.b.getString(R.string.operate_fail), -1);
                        suggestedFollowsListItem.is_follow = 0;
                        as.b().b(suggestedFollowsListItem);
                    }
                    if (a != null) {
                        a.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void a(final SuggestedFollowsListItem suggestedFollowsListItem, final ListItemObject listItemObject) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new com.budejie.www.http.j().e(this.T, suggestedFollowsListItem.uid), new net.tsz.afinal.a.a<String>(this) {
            final /* synthetic */ PostsActivity c;

            public /* synthetic */ void onSuccess(Object obj) {
                a((String) obj);
            }

            public void a(String str) {
                try {
                    Toast a;
                    ResultBean s = z.s(str);
                    if (s != null) {
                        String msg = s.getMsg();
                        CharSequence code = s.getCode();
                        if (TextUtils.isEmpty(msg)) {
                            a = an.a(this.c.T, this.c.getString(R.string.operate_fail), -1);
                        } else {
                            a = an.a(this.c.T, msg, -1);
                        }
                        if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                            if (listItemObject != null) {
                                listItemObject.setShwFollow(true);
                            }
                            suggestedFollowsListItem.is_follow = 0;
                            this.c.D.a(suggestedFollowsListItem.uid);
                            as.b().a(suggestedFollowsListItem.uid, Integer.valueOf(0));
                            this.c.r().notifyDataSetChanged();
                            a = an.a(this.c.T, this.c.getString(R.string.had_cancel_attention), -1);
                        }
                    } else {
                        a = an.a(this.c.T, this.c.getString(R.string.operate_fail), -1);
                    }
                    if (a != null) {
                        a.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onClick(View view) {
        try {
            ((BudejieApplication) getApplication()).g().o.a(Long.valueOf(new Date().getTime()));
            switch (view.getId()) {
                case R.id.bt_after:
                    this.U.cancel();
                    return;
                case R.id.bt_look:
                    this.U.cancel();
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            MobclickAgent.onEvent(this.T, "cacheException", "PostsActivity onClick:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
        MobclickAgent.onEvent(this.T, "cacheException", "PostsActivity onClick:" + e.getLocalizedMessage());
        e.printStackTrace();
    }

    private void x() {
        this.ap = new TabPageIndicator.b(this) {
            final /* synthetic */ PostsActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                String str = "tag_essence".equals(this.a.d) ? "顶部导航_精华" : "tag_new".equals(this.a.d) ? "顶部导航_最新" : "";
                MobclickAgent.onEvent(this.a.T, str, ((TopNavigation) this.a.V.get(i)).name);
                MobclickAgent.onEvent(this.a.T, "E01-A02", str + "_" + ((TopNavigation) this.a.V.get(i)).name);
                Log.i("PostsActivity", "点击了顶部导航：" + str + "-" + ((TopNavigation) this.a.V.get(i)).name);
            }
        };
    }

    private void y() {
        this.aq = new OnClickListener(this) {
            final /* synthetic */ PostsActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.b(this.a.r);
            }
        };
    }

    public void a(ListItemObject listItemObject, int i) {
        Intent intent = new Intent(this.T, CommonLabelActivity.class);
        PlateBean plateBean = listItemObject.getPlateBean(i);
        if (plateBean != null) {
            intent.putExtra("theme_name", plateBean.theme_name);
            intent.putExtra("theme_id", plateBean.theme_id);
            this.T.startActivity(intent);
        }
    }

    private String e(String str) {
        String str2 = "";
        Iterator it = this.V.iterator();
        while (it.hasNext()) {
            String str3;
            TopNavigation topNavigation = (TopNavigation) it.next();
            if (topNavigation.getKey().equals(str)) {
                str3 = topNavigation.name;
            } else {
                str3 = str2;
            }
            str2 = str3;
        }
        return str2;
    }

    public ArrayList<ListItemObject> a(boolean z) {
        Fragment d = d();
        if (d instanceof a) {
            return ((a) d).a(z);
        }
        if (d instanceof b) {
            return ((b) d).a(z);
        }
        return null;
    }

    public void c(String str) {
        if (!TextUtils.isEmpty(str)) {
            List a = a(true);
            if (!com.budejie.www.goddubbing.c.a.a(a)) {
                for (int i = 0; i < a.size(); i++) {
                    if (str.equals(((ListItemObject) a.get(i)).getWid())) {
                        this.ak = i;
                        break;
                    }
                }
                if (this.ak != 0) {
                    Fragment d = d();
                    if (d instanceof a) {
                        ((a) d).a(this.ak);
                        this.ak = 0;
                    } else if (d instanceof b) {
                        ((b) d).a(this.ak);
                        this.ak = 0;
                    }
                }
            }
        }
    }
}
