package com.budejie.www.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.androidex.widget.RoundAsyncImageView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.androidex.widget.asyncimage.AsyncImageView.ProfileImageListener;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication.Status;
import com.budejie.www.activity.BudejieApplication.b;
import com.budejie.www.activity.TipPopUp.TypeControl;
import com.budejie.www.activity.a.e;
import com.budejie.www.activity.a.g;
import com.budejie.www.activity.a.h;
import com.budejie.www.activity.htmlpage.HtmlFeatureActivity;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.activity.labelsubscription.HorizontalListView;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.activity.video.k;
import com.budejie.www.bean.Fans;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.ResultBean;
import com.budejie.www.bean.SuggestedFollowsListItem;
import com.budejie.www.bean.UserItem;
import com.budejie.www.c.d;
import com.budejie.www.c.m;
import com.budejie.www.h.c;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.f;
import com.budejie.www.http.j;
import com.budejie.www.http.n;
import com.budejie.www.type.ProfileInfo;
import com.budejie.www.type.ProfileInfo.Data;
import com.budejie.www.util.aa;
import com.budejie.www.util.ai;
import com.budejie.www.util.am;
import com.budejie.www.util.an;
import com.budejie.www.util.as;
import com.budejie.www.util.au;
import com.budejie.www.util.p;
import com.budejie.www.util.u;
import com.budejie.www.util.z;
import com.budejie.www.widget.ExpertXListView;
import com.budejie.www.widget.XListView;
import com.elves.update.a;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.open.SocialConstants;
import com.umeng.analytics.MobclickAgent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.droidsonroids.gif.GifDrawable;

public class PersonalProfileActivity extends OauthWeiboBaseAct implements OnClickListener, b {
    public static String c = "visitID_kay";
    public static String d = "visitID_kay_type";
    public static String e = "visit_name_key";
    public static String f = "is_show_comment";
    public static Activity h;
    private Button A;
    private TextView B;
    private LinearLayout C;
    private ImageView D;
    private ImageView E;
    private RelativeLayout F;
    private RelativeLayout G;
    private AsyncImageView H;
    private TextView I;
    private TextView J;
    private RoundAsyncImageView K;
    private LinearLayout L;
    private LinearLayout M;
    private LinearLayout N;
    private LinearLayout O;
    private LinearLayout P;
    private LinearLayout Q;
    private View R;
    private RadioButton S;
    private RadioButton T;
    private RadioButton U;
    private TextView V;
    private ImageView W;
    private int X;
    private f Y;
    private Data Z;
    public d a;
    private g aA;
    private h aB;
    private com.budejie.www.activity.a.f aC;
    private View aD;
    private e aE;
    private Dialog aF;
    private boolean aG = false;
    private BudejieApplication aH;
    private Toast aI;
    private com.budejie.www.c.g aJ;
    private TextView aK;
    private TextView aL;
    private TextView aM;
    private UserItem aN;
    private RelativeLayout aO;
    private ImageView aP;
    private TextView aQ;
    private j aR;
    private boolean aS = true;
    private boolean aT;
    private int aU;
    private LinearLayout aV;
    private c aW;
    private HorizontalListView aX;
    private TextView aY;
    private ArrayList<b> aZ;
    private PersonalProfileActivity aa;
    private String ab;
    private String ac;
    private com.budejie.www.g.b ad;
    private HashMap<String, String> ae;
    private n af;
    private IWXAPI ag;
    private m ah;
    private a ai;
    private SharedPreferences aj;
    private ProgressDialog ak;
    private String al = "add";
    private com.budejie.www.http.b am;
    private com.budejie.www.c.b an;
    private ListItemObject ao;
    private String ap;
    private String aq;
    private boolean ar;
    private ExpertXListView as;
    private ImageView at;
    private AnimationDrawable au = null;
    private LinearLayout av;
    private TextView aw;
    private int ax;
    private int ay;
    private int az;
    public com.budejie.www.c.h b;
    private b ba;
    private OnScrollListener bb = new OnScrollListener(this) {
        final /* synthetic */ PersonalProfileActivity a;

        {
            this.a = r1;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            k.a(this.a.aa).a(this.a.as.getFirstVisiblePosition(), this.a.as.getLastVisiblePosition(), this.a.as.getHeaderViewsCount());
            if (this.a.as.getFirstVisiblePosition() != 0) {
                return;
            }
            if (this.a.as.getChildAt(0).getTop() == 0) {
                this.a.a(true);
            } else {
                this.a.a(false);
            }
        }
    };
    private XListView.a bc = new XListView.a(this) {
        final /* synthetic */ PersonalProfileActivity a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.n();
            if (this.a.bf) {
                this.a.q().a(this.a.bg);
            } else {
                this.a.q().a();
            }
        }

        public void b() {
            this.a.q().a(new Object[0]);
        }
    };
    private int bd = 0;
    private net.tsz.afinal.a.a<String> be = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ PersonalProfileActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onFailure(Throwable th, int i, String str) {
            an.a(this.a.aa, "举报失败", -1).show();
        }

        public void a(String str) {
            aa.a("PersonalProfileActivity", "json:" + str);
            try {
                Toast a;
                ResultBean s = z.s(str);
                if (s != null) {
                    String msg = s.getMsg();
                    if (TextUtils.isEmpty(msg)) {
                        a = an.a(this.a.aa, "已举报", -1);
                    } else {
                        a = an.a(this.a.aa, msg, -1);
                    }
                } else {
                    a = an.a(this.a.aa, "举报失败", -1);
                }
                if (a != null) {
                    a.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private boolean bf = false;
    private String bg = Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
    private net.tsz.afinal.a.a<String> bh = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ PersonalProfileActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
        }

        public void onFailure(Throwable th, int i, String str) {
        }

        public void a(String str) {
            try {
                ProfileInfo profileInfo = (ProfileInfo) new Gson().fromJson(str, ProfileInfo.class);
                if ("0".equals(profileInfo.getCode())) {
                    this.a.Z = profileInfo.getData();
                    this.a.ac = this.a.Z.getId();
                    if (!TextUtils.isEmpty(this.a.ac)) {
                        if (TextUtils.isEmpty(this.a.ap)) {
                            this.a.ap = this.a.ac;
                            this.a.aA.b(this.a.ap);
                            this.a.aB.b(this.a.ap);
                            this.a.aC.b(this.a.ap);
                            this.a.b("posts_type");
                        }
                        this.a.ab = this.a.Z.getRelationship();
                        if (this.a.f()) {
                            this.a.H.setOnClickListener(this.a.aa);
                            this.a.aE.a(true);
                            new Thread(this) {
                                final /* synthetic */ AnonymousClass17 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.c(this.a.a.Z);
                                }
                            }.start();
                        } else {
                            this.a.aE.a(false);
                        }
                        this.a.b(this.a.Z);
                        return;
                    } else if (this.a.aS) {
                        this.a.aS = false;
                        if (!TextUtils.isEmpty(this.a.aq)) {
                            this.a.w.setText(this.a.aq);
                        }
                        this.a.M.setVisibility(8);
                        this.a.H.setImageResource(R.drawable.profile_bg);
                        this.a.K.setImageResource(R.drawable.head_portrait);
                        this.a.K.setEnabled(false);
                        this.a.L.setClickable(false);
                        this.a.Q.setClickable(false);
                        this.a.as.setPullRefreshEnable(false);
                        this.a.as.setPullLoadEnable(false);
                        this.a.as.a();
                        this.a.as.removeHeaderView(this.a.R);
                        View textView = new TextView(this.a.aa);
                        textView.setLayoutParams(new LayoutParams(-1, com.budejie.www.adapter.b.a.g));
                        textView.setTextColor(c.a().c(R.attr.personal_not_have_user_text_prompt));
                        textView.setTextSize(18.0f);
                        textView.setGravity(1);
                        textView.setPadding(0, an.a(this.a.aa, 100), 0, 0);
                        textView.setText(R.string.profile_not_have_user_prompt_text);
                        this.a.as.addHeaderView(textView);
                        return;
                    } else {
                        return;
                    }
                }
                an.a(this.a.aa, profileInfo.getMsg(), -1).show();
            } catch (Exception e) {
                e.printStackTrace();
                if (this.a.aa != null) {
                    an.a(this.a.aa, this.a.getString(R.string.load_failed), -1).show();
                }
            }
        }
    };
    private SharedPreferences bi;
    public String g;
    p.c i = new p.c(this) {
        final /* synthetic */ PersonalProfileActivity a;

        {
            this.a = r1;
        }

        public void a(String str) {
            if (!TextUtils.isEmpty(str)) {
                if ("举报".equals(str)) {
                    this.a.s();
                } else if ("帖子筛选".equals(str)) {
                    p.a(this.a.aa, new String[]{"图片", "声音", "段子", "视频", "全部"}, this.a.k, true);
                } else if ("分享主页".equals(str)) {
                    this.a.t();
                } else if ("正序".equals(str)) {
                    this.a.aA.c("asc");
                } else if ("倒序".equals(str)) {
                    this.a.aA.c(SocialConstants.PARAM_APP_DESC);
                }
            }
        }
    };
    p.c j = new p.c(this) {
        final /* synthetic */ PersonalProfileActivity a;

        {
            this.a = r1;
        }

        public void a(String str) {
            if (!TextUtils.isEmpty(str)) {
                if ("编辑资料".equals(str)) {
                    this.a.startActivityForResult(new Intent(this.a.aa, MyAccountActivity.class), 1);
                } else if ("分享主页".equals(str)) {
                    this.a.t();
                }
            }
        }
    };
    p.c k = new p.c(this) {
        final /* synthetic */ PersonalProfileActivity a;

        {
            this.a = r1;
        }

        public void a(String str) {
            if (!TextUtils.isEmpty(str)) {
                if ("图片".equals(str)) {
                    this.a.bf = true;
                    this.a.bg = Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
                    this.a.bc.a();
                } else if ("段子".equals(str)) {
                    this.a.bf = true;
                    this.a.bg = "29";
                    this.a.bc.a();
                } else if ("声音".equals(str)) {
                    this.a.bf = true;
                    this.a.bg = "31";
                    this.a.bc.a();
                } else if ("视频".equals(str)) {
                    this.a.bf = true;
                    this.a.bg = "41";
                    this.a.bc.a();
                } else if ("全部".equals(str)) {
                    this.a.bf = true;
                    this.a.bg = "1";
                    this.a.bc.a();
                }
            }
        }
    };
    public OnClickListener l = new OnClickListener(this) {
        final /* synthetic */ PersonalProfileActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (this.a.Z != null) {
                Intent intent = new Intent(this.a.aa, ShowBigPicture.class);
                intent.putExtra("imgPath", this.a.Z.getProfileImageLarge());
                this.a.aa.startActivity(intent);
            }
        }
    };
    net.tsz.afinal.a.a<String> m = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ PersonalProfileActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onFailure(Throwable th, int i, String str) {
            au.a("操作失败");
            this.a.b();
        }

        public void a(String str) {
            boolean z = true;
            aa.a("PersonalProfileActivity", "json:" + str);
            try {
                ResultBean s = z.s(str);
                String string = this.a.getString(R.string.operate_fail);
                if (s != null) {
                    string = s.getMsg();
                    if ("0".equals(s.getCode())) {
                        if ("2".equals(this.a.ab) || "4".equals(this.a.ab)) {
                            as.b().a(this.a.ac, Integer.valueOf(0));
                            this.a.aJ.a(this.a.ac);
                        } else if ("3".equals(this.a.ab) || "0".equals(this.a.ab)) {
                            as.b().a(this.a.ac, Integer.valueOf(1));
                            this.a.aJ.a(new Fans(this.a.Z));
                        }
                        this.a.n();
                    }
                }
                an.a(this.a.aa, string, -1).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.a.b();
            if (this.a.aG) {
                PersonalProfileActivity personalProfileActivity = this.a;
                if (this.a.aG) {
                    z = false;
                }
                personalProfileActivity.aG = z;
            }
        }
    };
    Handler n = new Handler(this) {
        final /* synthetic */ PersonalProfileActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 816) {
                CharSequence string = ((Bundle) message.obj).getString(com.alipay.sdk.util.j.c);
                if (TextUtils.isEmpty(string)) {
                    this.a.aI = an.a(this.a.aa, this.a.aa.getString(R.string.forwarfail), -1);
                    this.a.aI.show();
                } else if ("0".equals(string)) {
                    this.a.p.sendEmptyMessage(9);
                } else {
                    this.a.aI = an.a(this.a.aa, this.a.aa.getString(R.string.forwarfail), -1);
                    this.a.aI.show();
                }
            } else if (i == 8160) {
                ResultBean x = z.x(((Bundle) message.obj).getString(com.alipay.sdk.util.j.c));
                if (x == null) {
                    return;
                }
                if (TextUtils.isEmpty(x.getResult())) {
                    an.a(this.a.aa, this.a.aa.getString(R.string.forwarfail), -1).show();
                } else if ("0".equals(x.getResult())) {
                    an.a(this.a.aa, this.a.aa.getString(R.string.forwardsuccess), -1).show();
                } else {
                    an.a(this.a.aa, this.a.aa.getString(R.string.forwarfail), -1).show();
                }
            }
        }
    };
    com.budejie.www.adapter.e.a o = new com.budejie.www.adapter.e.a(this) {
        final /* synthetic */ PersonalProfileActivity a;

        {
            this.a = r1;
        }

        public void a(View view, ListItemObject listItemObject) {
            TipPopUp.a(this.a.aa, TypeControl.dingtie);
            this.a.Y.a("ding", this.a.n, listItemObject);
            this.a.Y.a(listItemObject, this.a.n, "ding");
        }

        public void b(View view, ListItemObject listItemObject) {
            this.a.Y.a("cai", this.a.n, listItemObject);
            this.a.Y.a(listItemObject, this.a.n, "cai");
        }

        public void a(View view, ListItemObject listItemObject, String str) {
            if (this.a.g != "posts_type") {
                Bundle bundle = new Bundle();
                bundle.putString(PersonalProfileActivity.c, listItemObject.getUid());
                bundle.putString(PersonalProfileActivity.d, str);
                this.a.ad.a(7, bundle).onClick(view);
            }
        }

        public void c(View view, ListItemObject listItemObject) {
            boolean f = this.a.f();
            if (this.a.g != "posts_type") {
                f = false;
            }
            p.a(this.a.aa, listItemObject, this.a.p, f, this.a.aF);
        }

        public void a(View view, ListItemObject listItemObject, int i) {
            this.a.aa.ao = listItemObject;
            listItemObject.setForwardNoCollect(true);
            Bundle bundle = new Bundle();
            bundle.putInt("position", i);
            bundle.putString(HistoryOpenHelper.COLUMN_UID, ai.b(this.a.aa));
            bundle.putSerializable("weiboMap", this.a.ae);
            bundle.putSerializable("data", listItemObject);
            view.setTag(listItemObject);
            this.a.ad.a(5, bundle, this.a.p, this.a.ag, this.a.ah, this.a.af, this.a.ai, this.a.aj, this.a.n).onClick(view);
        }

        public void d(View view, ListItemObject listItemObject) {
            List arrayList = new ArrayList();
            arrayList.add(listItemObject);
            an.a(arrayList, this.a.b, this.a.a);
            view.setTag(arrayList.get(0));
            this.a.ad.a(3, null).onClick(view);
        }

        public void a() {
        }

        public void e(View view, ListItemObject listItemObject) {
            view.setTag(listItemObject);
            this.a.ad.a(3, null).onClick(view);
        }

        public void a_(String str) {
        }

        public OnClickListener b() {
            return null;
        }

        public void a(SuggestedFollowsListItem suggestedFollowsListItem) {
        }

        public void a(ListItemObject listItemObject, int i) {
            Intent intent = new Intent(this.a.aa, CommonLabelActivity.class);
            PlateBean plateBean = listItemObject.getPlateBean(i);
            if (plateBean != null) {
                intent.putExtra("theme_name", plateBean.theme_name);
                intent.putExtra("theme_id", plateBean.theme_id);
                this.a.aa.startActivity(intent);
            }
        }
    };
    final Handler p = new Handler(this) {
        final /* synthetic */ PersonalProfileActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 4) {
                this.a.ao.setLove(this.a.ao.getLove() + 1);
            } else if (i == 5) {
                this.a.ak = ProgressDialog.show(this.a.aa, "", (String) message.obj, true, true);
            } else if (i == 6) {
                this.a.ak.cancel();
            } else if (i == 7) {
                an.a(this.a.aa, this.a.aa.getString(R.string.already_collected), -1).show();
            } else if (i == 9) {
                this.a.ao.setRepost(String.valueOf(Integer.parseInt(this.a.ao.getRepost()) + 1));
            } else if (i == 91) {
                try {
                    if (this.a.ao != null) {
                        this.a.ao.setRepost(String.valueOf(Integer.parseInt(this.a.ao.getRepost()) + 1));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (i == 10) {
                an.a(this.a.aa, this.a.aa.getString(R.string.collect_failed), -1).show();
            } else if (i == 11) {
                CharSequence b = ai.b(this.a.aa);
                if (an.j(this.a.aa) && an.k(this.a.aa) && !b.equals("")) {
                    an.a(this.a.aa, false);
                    sendEmptyMessage(13);
                } else {
                    an.a(this.a.aa, (int) R.string.collected, (int) R.drawable.collect_tip).show();
                }
                if (!TextUtils.isEmpty(b)) {
                    this.a.al = "add";
                    this.a.am.a(this.a.al, ai.b(this.a.aa), (String) message.obj, 971);
                }
            } else if (i == 12) {
                an.a(this.a.aa, (int) R.string.collect_fail, (int) R.drawable.collect_tip).show();
            } else if (i == 829) {
                r0 = (String) message.obj;
                this.a.an.a("collectTable", r0);
                an.a(this.a.aa, this.a.aa.getString(R.string.delete_success), -1).show();
                this.a.al = "delete";
                this.a.am.a(this.a.al, ai.b(this.a.aa), r0, 971);
            } else if (i == 13) {
            } else {
                Toast a;
                if (i == 1001) {
                    HashMap k = z.k((String) message.obj);
                    if (k != null) {
                        r0 = (String) k.get("result_desc");
                        if (TextUtils.isEmpty(r0)) {
                            a = an.a(this.a.aa, this.a.aa.getString(R.string.operate_fail), -1);
                        } else {
                            a = an.a(this.a.aa, r0, -1);
                        }
                        if ("0".equals(k.get(com.alipay.sdk.util.j.c))) {
                            this.a.j();
                        }
                    } else {
                        a = an.a(this.a.aa, this.a.aa.getString(R.string.operate_fail), -1);
                    }
                    if (a != null) {
                        a.show();
                    }
                } else if (i == 1003) {
                    ResultBean s = z.s((String) message.obj);
                    if (this.a.aF.isShowing()) {
                        this.a.aF.dismiss();
                    }
                    if (s != null) {
                        r0 = s.getMsg();
                        if (TextUtils.isEmpty(r0)) {
                            a = an.a(this.a.aa, this.a.aa.getString(R.string.operate_fail), -1);
                        } else {
                            a = an.a(this.a.aa, r0, -1);
                        }
                        if ("1".equals(s.getCode())) {
                            this.a.j();
                        }
                    } else {
                        a = an.a(this.a.aa, this.a.aa.getString(R.string.operate_fail), -1);
                    }
                    if (a != null) {
                        a.show();
                    }
                    MobclickAgent.onEvent(this.a.aa, "E05-A05", "帖子置顶");
                } else if (i == 1004) {
                    Map q = z.q((String) message.obj);
                    if (this.a.aF.isShowing()) {
                        this.a.aF.dismiss();
                    }
                    if (q != null) {
                        r0 = (String) q.get("result_desc");
                        if (TextUtils.isEmpty(r0)) {
                            a = an.a(this.a.aa, this.a.aa.getString(R.string.operate_fail), -1);
                        } else {
                            a = an.a(this.a.aa, r0, -1);
                        }
                        if ("0".equals(q.get(com.alipay.sdk.util.j.c))) {
                            this.a.j();
                        }
                    } else {
                        a = an.a(this.a.aa, this.a.aa.getString(R.string.operate_fail), -1);
                    }
                    if (a != null) {
                        a.show();
                    }
                }
            }
        }
    };
    net.tsz.afinal.a.a<String> q = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ PersonalProfileActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
            this.a.a(this.a.getResources().getString(R.string.picture_loading));
        }

        public void onFailure(Throwable th, int i, String str) {
            this.a.b();
            an.a(this.a.aa, "修改失败", -1).show();
        }

        public void a(String str) {
            this.a.b();
            try {
                Toast a;
                ResultBean t = z.t(str);
                if (t != null) {
                    a = an.a(this.a.aa, t.getMsg(), -1);
                    if (t.getImageBean() != null) {
                        Object url = t.getImageBean().getUrl();
                        if (!TextUtils.isEmpty(url)) {
                            this.a.H.loadProfileImage(url);
                        }
                    }
                } else {
                    a = an.a(this.a.aa, "修改失败", -1);
                }
                if (a != null) {
                    a.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    ProfileImageListener r = new ProfileImageListener(this) {
        final /* synthetic */ PersonalProfileActivity a;

        {
            this.a = r1;
        }

        public void onProgressUpdate(String str, View view, int i) {
        }

        public void onLoadingStarted(String str, View view) {
        }

        public void onLoadingFailed(String str, View view, FailReason failReason) {
        }

        public void onLoadingComplete(String str, View view, Bitmap bitmap, GifDrawable gifDrawable) {
            if (this.a.H != null) {
                this.a.H.setImageBitmap(bitmap);
            }
        }

        public void onLoadingCancelled(String str, View view) {
        }
    };
    p.c s = new p.c(this) {
        final /* synthetic */ PersonalProfileActivity a;

        {
            this.a = r1;
        }

        public void a(String str) {
            if (!TextUtils.isEmpty(str)) {
                if ("拍照".equals(str)) {
                    p.c(this.a.aa);
                } else if ("从相册中选择".equals(str)) {
                    p.b(this.a.aa);
                }
            }
        }
    };
    private RelativeLayout t;
    private RelativeLayout u;
    private Button v;
    private TextView w;
    private LinearLayout x;
    private ImageView y;
    private ImageView z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        themeState = ai.a(this);
        setTheme(c.a().a(themeState));
        setContentView(R.layout.activity_personal_profile_new);
        com.budejie.www.widget.a.a((Activity) this);
        this.aR = new j();
        this.ap = getIntent().getStringExtra(c);
        this.aq = getIntent().getStringExtra(e);
        this.ar = getIntent().getBooleanExtra(f, false);
        this.aa = this;
        h = this;
        this.bi = getSharedPreferences("myinfo", 0);
        this.aH = (BudejieApplication) getApplication();
        this.ax = getWindowManager().getDefaultDisplay().getWidth();
        this.Y = new f(this);
        this.b = new com.budejie.www.c.h(this);
        this.a = new d(this);
        this.aJ = new com.budejie.www.c.g(this);
        this.aj = getSharedPreferences("weiboprefer", 0);
        this.am = com.budejie.www.http.b.a(this, null);
        this.an = new com.budejie.www.c.b(this);
        this.ai = new a(this);
        this.af = new n(this);
        this.ah = new m(this);
        this.ad = new com.budejie.www.g.b(this, this.mSsoHandler, this.mTencent, this);
        this.aN = this.ah.e(this.aj.getString("id", ""));
        i();
        l();
        try {
            m();
        } catch (Exception e) {
            e.printStackTrace();
        }
        o();
        p();
    }

    private void i() {
        this.aF = new Dialog(this, R.style.dialogTheme);
        this.aF.setContentView(R.layout.loaddialog);
        this.aF.setCanceledOnTouchOutside(true);
        this.aF.setCancelable(true);
    }

    public void a() {
        try {
            if (!isFinishing()) {
                a(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(String str) {
        if (!isFinishing() && this.aF != null) {
            String string = getResources().getString(R.string.downloading);
            if (TextUtils.isEmpty(str)) {
                str = string;
            }
            ((TextView) this.aF.findViewById(R.id.dialog_txt)).setText(str);
            this.aF.show();
        }
    }

    public void b() {
        if (this.aF != null && this.aF.isShowing()) {
            this.aF.dismiss();
        }
        this.as.b();
    }

    private void j() {
        this.bf = false;
        this.bc.a();
    }

    protected void onPause() {
        super.onPause();
        k.a(this.aa).o();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.aa = null;
        k.a((Context) this).p();
    }

    public void onBackPressed() {
        k();
        super.onBackPressed();
    }

    private void k() {
        Intent intent = new Intent();
        String str = "0";
        if ("2".equals(this.ab)) {
            str = "1";
        }
        intent.putExtra(UserTrackerConstants.USER_ID, this.ap);
        intent.putExtra("follow_status", str);
        setResult(2, intent);
        finish();
    }

    public void onResume() {
        super.onResume();
        this.aH.a((b) this);
        Status a = this.aH.a();
        if (a != null) {
            a(a);
        }
        if (this.aE != null) {
            this.aE.notifyDataSetChanged();
        }
        this.ad = new com.budejie.www.g.b(this, this.mSsoHandler, this.mTencent, this);
    }

    protected void onStop() {
        b();
        super.onStop();
    }

    public String c() {
        return this.ap;
    }

    private void l() {
        this.x = (LinearLayout) findViewById(R.id.TitleGapLayout);
        an.a(this.x);
        this.C = (LinearLayout) findViewById(R.id.TitleGapLayout_w);
        an.a(this.C);
        this.F = (RelativeLayout) findViewById(R.id.curtain_root_layout);
        this.t = (RelativeLayout) findViewById(R.id.TitleLayoutN);
        this.u = (RelativeLayout) findViewById(R.id.TitleLayoutW);
        this.v = (Button) findViewById(R.id.title_left_btn);
        this.A = (Button) findViewById(R.id.title_left_btn_w);
        this.w = (TextView) findViewById(R.id.title_center_txt);
        this.B = (TextView) findViewById(R.id.title_center_txt_w);
        this.y = (ImageView) findViewById(R.id.title_right_imgbtn);
        this.D = (ImageView) findViewById(R.id.title_right_imgbtn_w);
        this.z = (ImageView) findViewById(R.id.private_chat);
        this.E = (ImageView) findViewById(R.id.private_chat_w);
        this.u.setVisibility(0);
        this.t.setVisibility(8);
    }

    private void a(boolean z) {
        if (z) {
            this.u.setVisibility(0);
            this.t.setVisibility(8);
            return;
        }
        this.u.setVisibility(8);
        this.t.setVisibility(0);
    }

    private void m() {
        this.aT = true;
        this.aU = an.a((Context) this, 9);
        this.as = (ExpertXListView) findViewById(R.id.listview);
        this.as.setPullRefreshEnable(true);
        this.as.setPullLoadEnable(false);
        this.aD = this.as.getmHeaderViewContent();
        this.aK = (TextView) this.aD.findViewById(R.id.user_v);
        this.aL = (TextView) this.aD.findViewById(R.id.user_level);
        this.aL.setOnClickListener(this);
        this.aM = (TextView) this.aD.findViewById(R.id.UserCreditInfoTextView);
        this.G = (RelativeLayout) this.aD.findViewById(R.id.rl_pp_info);
        this.H = (AsyncImageView) this.aD.findViewById(R.id.aiv_pp_bg);
        this.I = (TextView) this.aD.findViewById(R.id.tv_pp_attentionCount);
        this.M = (LinearLayout) this.aD.findViewById(R.id.personal_bottom_layout);
        this.J = (TextView) this.aD.findViewById(R.id.tv_pp_fansCount);
        this.K = (RoundAsyncImageView) this.aD.findViewById(R.id.aiv_pp_portrait);
        this.aw = (TextView) this.aD.findViewById(R.id.praise_count);
        this.av = (LinearLayout) this.aD.findViewById(R.id.backpic);
        this.L = (LinearLayout) this.aD.findViewById(R.id.ll_pp_attention);
        this.Q = (LinearLayout) this.aD.findViewById(R.id.ll_pp_fans);
        this.at = (ImageView) findViewById(R.id.melodyview);
        this.au = (AnimationDrawable) this.at.getBackground();
        this.aO = (RelativeLayout) findViewById(R.id.rl_no_data);
        this.aP = (ImageView) findViewById(R.id.img_no_data);
        this.aQ = (TextView) findViewById(R.id.tv_no_data);
        this.N = (LinearLayout) findViewById(R.id.personal_attention);
        this.O = (LinearLayout) findViewById(R.id.personal_attention_cancle);
        this.P = (LinearLayout) findViewById(R.id.personal_attention_me);
        this.N.setOnClickListener(this);
        this.O.setOnClickListener(this);
        this.P.setOnClickListener(this);
        this.R = View.inflate(this.aa, R.layout.personal_profile_tab_header, null);
        this.S = (RadioButton) this.R.findViewById(R.id.mRbPosts);
        this.T = (RadioButton) this.R.findViewById(R.id.mRbShares);
        this.U = (RadioButton) this.R.findViewById(R.id.mRbComment);
        this.W = (ImageView) this.R.findViewById(R.id.cursor);
        this.V = (TextView) this.R.findViewById(R.id.CommentTipTextView);
        this.aV = (LinearLayout) this.R.findViewById(R.id.personal_muisc_album_view);
        this.aX = (HorizontalListView) this.R.findViewById(R.id.personal_music_album_list);
        this.aY = (TextView) this.R.findViewById(R.id.music_album_all_btn);
        this.X = this.ax / 3;
        ((LinearLayout.LayoutParams) this.W.getLayoutParams()).width = this.X;
        this.az = this.X;
        this.W.setVisibility(0);
        this.as.addHeaderView(this.R);
        this.as.setXListViewListener(this.bc);
        this.as.setOnScrollListener(this.bb);
        this.aA = (g) com.budejie.www.activity.a.a.a(this, "posts_type");
        this.aB = (h) com.budejie.www.activity.a.a.a(this, "share_type");
        this.aC = (com.budejie.www.activity.a.f) com.budejie.www.activity.a.a.a(this, "comments_type");
        this.aE = new e(this, this.aC.c(), this.o);
        this.aE.a(an.f(this.aa).equalsIgnoreCase(this.ap));
        this.aA.a(this.aE);
        this.aB.a(this.aE);
        this.aC.a(this.aE);
        this.as.setAdapter(this.aE);
        if (!TextUtils.isEmpty(this.ap)) {
            try {
                if (this.ar) {
                    this.U.setChecked(true);
                    b("comments_type");
                    return;
                }
                b("posts_type");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ExpertXListView d() {
        return this.as;
    }

    private void n() {
        NetWorkUtil netWorkUtil = BudejieApplication.a;
        RequstMethod requstMethod = RequstMethod.GET;
        String f = j.f();
        j jVar = new j();
        netWorkUtil.a(requstMethod, f, j.n(this, this.ap, this.aq), this.bh);
    }

    private void o() {
        this.ag = WXAPIFactory.createWXAPI(this, "wx592fdc48acfbe290", true);
        this.ag.registerApp("wx592fdc48acfbe290");
        this.ae = this.af.a(ai.b(this));
    }

    private void p() {
        this.v.setOnClickListener(this);
        this.y.setOnClickListener(this);
        this.A.setOnClickListener(this);
        this.D.setOnClickListener(this);
        this.z.setOnClickListener(this);
        this.E.setOnClickListener(this);
        this.K.setOnClickListener(this.l);
        this.L.setOnClickListener(this);
        this.Q.setOnClickListener(this);
        this.S.setOnClickListener(this);
        this.T.setOnClickListener(this);
        this.U.setOnClickListener(this);
        this.at.setOnClickListener(this);
    }

    private com.budejie.www.activity.a.d q() {
        if ("posts_type".equals(this.g)) {
            return this.aA;
        }
        if ("comments_type".equals(this.g)) {
            return this.aC;
        }
        if ("share_type".equals(this.g)) {
            return this.aB;
        }
        return this.aA;
    }

    private void b(String str) {
        if (!str.equals(this.g)) {
            k.a(this.aa).p();
            this.g = str;
            b();
            this.aE.b();
            if ("posts_type".equals(str)) {
                a(0);
                this.as.setPullLoadEnable(this.aA.b().c());
                if (this.aA.a.size() <= 0) {
                    j();
                } else {
                    a(str, false);
                    this.aE.a(this.aA.a);
                    if (this.Z != null) {
                        if (this.Z.getTiezi_count().equals("0")) {
                            this.V.setVisibility(8);
                        } else {
                            this.V.setVisibility(0);
                            this.V.setText("" + this.Z.getTiezi_count() + "条帖子");
                        }
                    }
                }
                if (this.aZ == null || this.aZ.size() <= 0) {
                    this.aV.setVisibility(8);
                } else {
                    this.aV.setVisibility(0);
                }
                c(str);
            } else if ("share_type".equals(str)) {
                a(1);
                this.as.setPullLoadEnable(this.aB.b().c());
                if (this.aB.a.size() <= 0) {
                    j();
                } else {
                    a(str, false);
                    this.aE.a(this.aB.a);
                    if (this.Z != null) {
                        if (this.Z.getShare_count().equals("0")) {
                            this.V.setVisibility(8);
                        } else {
                            this.V.setVisibility(0);
                            this.V.setText("" + this.Z.getShare_count() + "条分享");
                        }
                    }
                }
                this.aV.setVisibility(8);
                c(str);
            } else if ("comments_type".equals(str)) {
                a(2);
                this.as.setPullLoadEnable(this.aC.b().c());
                if (this.aC.a.size() <= 0) {
                    j();
                } else {
                    a(str, false);
                    this.aE.a(this.aC.a);
                    if (this.Z != null) {
                        if (this.Z.getComment_count().equals("0")) {
                            this.V.setVisibility(8);
                        } else {
                            this.V.setVisibility(0);
                            this.V.setText("" + this.Z.getComment_count() + "条评论");
                        }
                    }
                }
                this.aV.setVisibility(8);
                c(str);
            }
        }
    }

    public void a(String str, boolean z) {
        if (!z || themeState == 1) {
            this.aO.setVisibility(8);
            return;
        }
        Drawable drawable = null;
        CharSequence charSequence = "";
        if (f()) {
            if ("posts_type".equals(str)) {
                drawable = getResources().getDrawable(R.drawable.pp_my_post_none_data_iv);
                charSequence = getResources().getString(R.string.pp_my_post_tab_none_data);
            } else if ("share_type".equals(str)) {
                drawable = getResources().getDrawable(R.drawable.pp_my_share_none_data_iv);
                charSequence = getResources().getString(R.string.pp_my_share_tab_none_data);
            } else if ("comments_type".equals(str)) {
                drawable = getResources().getDrawable(R.drawable.pp_my_comment_none_data_iv);
                charSequence = getResources().getString(R.string.pp_my_comment_tab_none_data);
            }
        } else if ("posts_type".equals(str)) {
            drawable = getResources().getDrawable(R.drawable.pp_post_none_data_iv);
            charSequence = getResources().getString(R.string.pp_post_tab_none_data);
        } else if ("share_type".equals(str)) {
            drawable = getResources().getDrawable(R.drawable.pp_share_none_data_iv);
            charSequence = getResources().getString(R.string.pp_share_tab_none_data);
        } else if ("comments_type".equals(str)) {
            drawable = getResources().getDrawable(R.drawable.pp_comment_none_data_iv);
            charSequence = getResources().getString(R.string.pp_comment_tab_none_data);
        }
        this.aP.setImageDrawable(drawable);
        this.aQ.setText(charSequence);
        this.aO.setVisibility(0);
    }

    private void c(String str) {
        if ("posts_type".equals(str)) {
            if (this.aC.b().a() == 1) {
                this.aC.b().a(5);
            }
            if (this.aC.b().b() == 3) {
                this.aC.b().b(6);
            }
            if (this.aB.b().a() == 1) {
                this.aB.b().a(5);
            }
            if (this.aB.b().b() == 3) {
                this.aB.b().b(6);
            }
        } else if ("share_type".equals(str)) {
            if (this.aA.b().a() == 1) {
                this.aA.b().a(5);
            }
            if (this.aA.b().b() == 3) {
                this.aA.b().b(6);
            }
            if (this.aC.b().a() == 1) {
                this.aC.b().a(5);
            }
            if (this.aC.b().b() == 3) {
                this.aC.b().b(6);
            }
        } else if ("comments_type".equals(str)) {
            if (this.aA.b().a() == 1) {
                this.aA.b().a(5);
            }
            if (this.aA.b().b() == 3) {
                this.aA.b().b(6);
            }
            if (this.aB.b().a() == 1) {
                this.aB.b().a(5);
            }
            if (this.aB.b().b() == 3) {
                this.aB.b().b(6);
            }
        }
    }

    private void a(int i) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.W, View.TRANSLATION_X, new float[]{(float) (this.az * this.ay), (float) (this.az * i)});
        ofFloat.setDuration(300);
        this.ay = i;
        ofFloat.start();
    }

    private void b(Data data) {
        this.I.setText(data.getFollowCount());
        this.J.setText(data.getFansCount());
        if ((TextUtils.isEmpty(data.getJie_v()) || "0".equalsIgnoreCase(data.getJie_v())) && (TextUtils.isEmpty(data.getSina_v()) || "0".equalsIgnoreCase(data.getSina_v()))) {
            this.aK.setVisibility(8);
        } else {
            this.aK.setVisibility(0);
        }
        if (TextUtils.isEmpty(data.getLevel())) {
            this.aL.setText("等级：LV1");
        } else {
            this.aL.setText("等级：LV" + data.getLevel());
        }
        Drawable drawable;
        if ("m".equals(data.getSex())) {
            this.aT = true;
            drawable = getResources().getDrawable(R.drawable.personal_sex_men_w);
            drawable.setBounds(0, 0, this.aU, this.aU);
            this.w.setCompoundDrawables(null, null, drawable, null);
            drawable = getResources().getDrawable(R.drawable.personal_sex_men);
            drawable.setBounds(0, 0, this.aU, this.aU);
            this.B.setCompoundDrawables(null, null, drawable, null);
        } else {
            this.aT = false;
            drawable = getResources().getDrawable(R.drawable.personal_sex_women_w);
            drawable.setBounds(0, 0, this.aU, this.aU);
            this.w.setCompoundDrawables(null, null, drawable, null);
            drawable = getResources().getDrawable(R.drawable.personal_sex_women);
            drawable.setBounds(0, 0, this.aU, this.aU);
            this.B.setCompoundDrawables(null, null, drawable, null);
        }
        CharSequence introduction = data.getIntroduction();
        if (TextUtils.isEmpty(introduction)) {
            this.aM.setVisibility(8);
        } else {
            this.aM.setVisibility(0);
            this.aM.setText(introduction);
        }
        this.H.setImageListener(this.r);
        this.H.loadProfileImage(data.getBackgroundImage());
        aa.a("PersonalProfileActivity", "头像：" + data.getProfileImage());
        if (TextUtils.isEmpty(data.getProfileImage())) {
            this.K.setPostAvatarImage(this.aN.getProfile());
        } else {
            this.K.setPostAvatarImage(data.getProfileImage());
        }
        if (f()) {
            this.w.setText("我");
            this.B.setText("我");
            this.y.setVisibility(8);
            this.D.setVisibility(8);
            this.z.setVisibility(8);
            this.E.setVisibility(8);
        } else {
            this.w.setText(data.getUsername());
            this.B.setText(data.getUsername());
            this.y.setVisibility(0);
            this.D.setVisibility(0);
            this.z.setVisibility(0);
            this.E.setVisibility(0);
        }
        aa.a("PersonalProfileActivity", "relationship:" + this.ab);
        r();
        a(data);
        g();
        x();
        if ("posts_type".equals(this.g)) {
            if (data.getTiezi_count().equals("0")) {
                this.V.setVisibility(8);
            } else {
                this.V.setVisibility(0);
                this.V.setText("" + data.getTiezi_count() + "条帖子");
            }
            if (this.aZ == null || this.aZ.size() <= 0) {
                this.aV.setVisibility(8);
            } else {
                this.aV.setVisibility(0);
            }
        } else if ("share_type".equals(this.g)) {
            if (data.getShare_count().equals("0")) {
                this.V.setVisibility(8);
            } else {
                this.V.setVisibility(0);
                this.V.setText("" + data.getShare_count() + "条分享");
            }
            this.aV.setVisibility(8);
        }
        if ("comments_type".equals(this.g)) {
            if (data.getComment_count().equals("0")) {
                this.V.setVisibility(8);
            } else {
                this.V.setVisibility(0);
                this.V.setText("" + data.getComment_count() + "条评论");
            }
            this.aV.setVisibility(8);
        }
    }

    private void r() {
        if (f()) {
            this.P.setVisibility(0);
            this.M.setBackgroundResource(R.drawable.personal_focus_on_bg);
            this.N.setVisibility(8);
            this.O.setVisibility(8);
        } else if ("0".equals(this.ab) || "3".equals(this.ab)) {
            this.M.setVisibility(0);
            this.M.setBackgroundResource(R.drawable.personal_focus_bg);
            this.N.setVisibility(0);
            this.P.setVisibility(8);
            this.O.setVisibility(8);
            this.aG = true;
        } else if ("2".equals(this.ab) || "4".equals(this.ab)) {
            this.M.setVisibility(0);
            this.M.setBackgroundResource(R.drawable.personal_focus_on_bg);
            this.N.setVisibility(8);
            this.P.setVisibility(8);
            this.O.setVisibility(0);
            this.aG = false;
        }
    }

    public void a(Data data) {
        this.av.setVisibility(0);
        this.aw.setText(data.getTotal_cmt_like_count());
    }

    private void s() {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.aR.h(this.aa, this.ac), this.be);
    }

    private void t() {
        ListItemObject listItemObject = new ListItemObject();
        String str = "hi，我在百思不得姐安家了，速速来关注我吧！";
        if (f()) {
            str = OnlineConfigAgent.getInstance().getConfigParams(this.aa, "分享自己的主页");
        } else {
            str = OnlineConfigAgent.getInstance().getConfigParams(this.aa, "分享他人的个人主页");
        }
        String str2 = "";
        str2 = OnlineConfigAgent.getInstance().getConfigParams(this.aa, "分享个人主页着陆页");
        listItemObject.setIfPP(true);
        listItemObject.setContent(str);
        if (this.Z != null) {
            listItemObject.setImgUrl(this.Z.getProfileImage());
        }
        listItemObject.setWeixin_url(str2 + "?uid=" + this.ap);
        this.ad.a(this.ap, this.ag, this.aj, this.p, listItemObject, this.ae, this.ah, this.af, this.n, this.ai);
    }

    private void c(Data data) {
        Editor edit = this.bi.edit();
        edit.putString("id", data.getId());
        edit.putString("bgimage", data.getBackgroundImage());
        edit.putString("profileimage", data.getProfileImage());
        edit.putString("profileimage_large", data.getProfileImageLarge());
        edit.putString(HistoryOpenHelper.COLUMN_USERNAME, data.getUsername());
        edit.putString("sex", data.getSex());
        edit.putString("introduction", data.getIntroduction());
        edit.putString("fansadd", data.getFansAdd());
        edit.putString("fanscount", data.getFansCount());
        edit.putString("followcount", data.getFollowCount());
        edit.putString("praisetount", data.getPraise_count());
        edit.putString("relationship", data.getRelationship());
        edit.putString("settinpushcomment", data.getSettingPushComment());
        edit.putString("setttinpushfeed", data.getSettingPushFeed());
        edit.putString("settinpushO1", data.getSettingPushO1());
        edit.putString("settinpushO2", data.getSettingPushO2());
        edit.putString(HistoryOpenHelper.COLUMN_LEVEL, data.getLevel());
        edit.putString("credit", data.getCredit());
        edit.putString("experience", data.getExperience());
        edit.putString("sina_v", data.getSina_v());
        edit.putString("jie_v", data.getJie_v());
        edit.putString("v_desc", data.getV_desc());
        edit.putString("tiezi_count", data.getTiezi_count());
        edit.putString("share_count", data.getSettingPushComment());
        edit.putString("comment_count", data.getComment_count());
        edit.putBoolean("is_vip", data.is_vip);
        edit.putString("total_cmt_like_count", data.getTotal_cmt_like_count());
        edit.commit();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_btn_w:
            case R.id.title_left_btn:
                k();
                return;
            case R.id.private_chat_w:
            case R.id.private_chat:
                h();
                return;
            case R.id.title_right_imgbtn_w:
            case R.id.title_right_imgbtn:
            case R.id.title_right_btn:
                if (f()) {
                    startActivityForResult(new Intent(this.aa, MyAccountActivity.class), 1);
                    return;
                } else if ("comments_type".equals(this.g)) {
                    p.a(this.aa, new String[]{"举报"}, this.i, true);
                    return;
                } else {
                    p.a(this.aa, new String[]{"帖子筛选", "正序", "倒序", "举报"}, this.i, true);
                    return;
                }
            case R.id.melodyview:
                view.setTag(this.aH.d());
                this.ad.a(3, null).onClick(view);
                return;
            case R.id.user_level:
                if (this.aN != null && f()) {
                    Intent intent = new Intent(this, DetailContentActivity.class);
                    intent.putExtra("operator", "my_coins");
                    intent.putExtra("url", this.aN.getTrade_ruler());
                    intent.putExtra("trade_history", this.aN.getTrade_history());
                    startActivity(intent);
                    return;
                }
                return;
            case R.id.aiv_pp_bg:
                p.a(this.aa, "photo", this.s);
                return;
            case R.id.ll_pp_fans:
                if (an.a(this.aa)) {
                    u.a(this, this.ac);
                    return;
                } else {
                    an.a(this.aa, this.aa.getString(R.string.nonet), -1).show();
                    return;
                }
            case R.id.ll_pp_attention:
                if (an.a(this.aa)) {
                    u.b(this, this.ac);
                    return;
                } else {
                    an.a(this.aa, this.aa.getString(R.string.nonet), -1).show();
                    return;
                }
            case R.id.personal_attention:
                u();
                return;
            case R.id.personal_attention_cancle:
                u();
                return;
            case R.id.personal_attention_me:
                startActivityForResult(new Intent(this.aa, MyAccountActivity.class), 1);
                return;
            case R.id.mRbPosts:
                b("posts_type");
                return;
            case R.id.mRbShares:
                b("share_type");
                return;
            case R.id.mRbComment:
                b("comments_type");
                return;
            default:
                return;
        }
    }

    private void u() {
        if (!an.a(this.aa)) {
            an.a(this.aa, this.aa.getString(R.string.nonet), -1).show();
        } else if (TextUtils.isEmpty(ai.b(this.aa))) {
            an.a(this.aa, 0, null, null, 0);
        } else {
            v();
        }
    }

    private void v() {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", w(), this.m);
        a();
    }

    private net.tsz.afinal.a.b w() {
        if (this.Z != null) {
            if ("2".equals(this.ab) || "4".equals(this.ab)) {
                SearchActivity.a(false, this.Z.getId());
                if (u.a() != null) {
                    u.a().setRelationship("0");
                }
                return this.aR.e(this, this.ac);
            } else if ("3".equals(this.ab) || "0".equals(this.ab)) {
                SearchActivity.a(true, this.Z.getId());
                if (u.a() != null) {
                    u.a().setRelationship("2");
                }
                return this.aR.d(this, this.ac);
            }
        }
        return null;
    }

    public com.budejie.www.adapter.e.a e() {
        return this.o;
    }

    public void onWbShareSuccess() {
        super.onWbShareSuccess();
        am.a(this.ao);
        if (this.aE != null) {
            this.aE.notifyDataSetChanged();
        }
    }

    public boolean f() {
        if ("1".equals(this.ab)) {
            return true;
        }
        return false;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 716 && i2 == -1) {
            p.a(this.aa, p.a, 640, 800);
        } else if (i == 714 && i2 == -1) {
            if (intent != null) {
                p.a(this.aa, intent, 640, 800);
            }
        } else if (i == 728 && i2 == -1) {
            String stringExtra = intent.getStringExtra("image-path");
            if (!TextUtils.isEmpty(stringExtra)) {
                d(stringExtra);
            }
        } else if (i == 1 && i2 == 719) {
            j();
        }
    }

    private void d(String str) {
        BudejieApplication.a.a(RequstMethod.POST, "http://api.budejie.com/api/api_open.php", this.aR.i(this.aa, str), this.q);
    }

    public void onrefreshTheme() {
        super.onrefreshTheme();
        this.F.setBackgroundResource(com.budejie.www.util.j.m);
        this.aK.setBackgroundResource(com.budejie.www.util.j.bo);
    }

    public void a(Status status) {
        int i = 0;
        switch (status) {
            case start:
                this.au.stop();
                this.au.start();
                this.at.setVisibility(0);
                return;
            case stop:
                this.au.stop();
                ImageView imageView = this.at;
                if (!an.b) {
                    i = 8;
                }
                imageView.setVisibility(i);
                return;
            case end:
                this.au.stop();
                this.at.setVisibility(8);
                return;
            default:
                return;
        }
    }

    public void g() {
        this.ba = new b();
        this.ba.a = "http://xt.budejie.com/xuantu/album/new/";
        this.aY.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PersonalProfileActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                String str = "http://xt.budejie.com/xuantu/list/album/" + this.a.ap + "/";
                Intent intent = new Intent();
                intent.setClass(this.a.aa, HtmlFeatureActivity.class);
                intent.setData(Uri.parse(str));
                this.a.aa.startActivity(intent);
            }
        });
        this.aZ = new ArrayList();
        if (f()) {
            this.aZ.add(this.ba);
        }
        this.aW = new c(this, this.aZ);
        this.aX.setAdapter(this.aW);
        this.aX.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ PersonalProfileActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                try {
                    String str = ((b) this.a.aZ.get(i)).a;
                    Intent intent = new Intent();
                    intent.setClass(this.a.aa, HtmlFeatureActivity.class);
                    intent.setData(Uri.parse(str));
                    this.a.aa.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void x() {
        BudejieApplication.a.a(RequstMethod.GET, j.k(this.ap), new j(this.aa), new net.tsz.afinal.a.a<String>(this) {
            final /* synthetic */ PersonalProfileActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onSuccess(Object obj) {
                a((String) obj);
            }

            public void a(String str) {
                super.onSuccess(str);
                if (!TextUtils.isEmpty(str)) {
                    d dVar = (d) z.a(str, d.class);
                    if (dVar != null && dVar.a != null) {
                        this.a.aZ = dVar.a;
                        if (this.a.f()) {
                            this.a.aZ.add(this.a.ba);
                        }
                        if (this.a.aZ != null && this.a.aZ.size() > 0) {
                            this.a.aW.a(this.a.aZ);
                            if ("posts_type".equals(this.a.g)) {
                                this.a.aV.setVisibility(0);
                            }
                        }
                    }
                }
            }
        });
    }

    public void h() {
        if (an.a(this.aj)) {
            Intent intent = new Intent();
            intent.setClass(this, HtmlFeatureActivity.class);
            intent.setData(Uri.parse("http://gang.api.budejie.com/talk/home/" + ai.b(this) + "_" + this.ac));
            startActivity(intent);
            return;
        }
        an.a((Activity) this, 0, null, null, 0);
    }
}
