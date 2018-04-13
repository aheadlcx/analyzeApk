package com.budejie.www.activity.label;

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
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.activity.TipPopUp;
import com.budejie.www.activity.TipPopUp.TypeControl;
import com.budejie.www.activity.base.BaseCompatTitleActivity;
import com.budejie.www.activity.label.enumeration.CommonLabelOperator;
import com.budejie.www.activity.label.enumeration.CommonLabelOperator.CommonLabelAction;
import com.budejie.www.activity.label.enumeration.PlatePostEnum;
import com.budejie.www.activity.label.enumeration.UpdateLabelPostStatus;
import com.budejie.www.activity.label.enumeration.UserBanOperator;
import com.budejie.www.activity.label.response.CommonInfoBean;
import com.budejie.www.activity.label.response.LabelUser;
import com.budejie.www.activity.label.response.LabelUser.ListBean;
import com.budejie.www.activity.label.response.ToTopResponse;
import com.budejie.www.activity.label.response.ToTopResponse.InfoBean;
import com.budejie.www.activity.label.response.UserBanResponse;
import com.budejie.www.activity.label.view.AppBarStateChangeListener;
import com.budejie.www.activity.label.view.AppBarStateChangeListener$State;
import com.budejie.www.activity.label.view.LabelTabLayout;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.bean.CommentItem;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.ResultBean;
import com.budejie.www.bean.SuggestedFollowsListItem;
import com.budejie.www.c.b;
import com.budejie.www.c.g;
import com.budejie.www.c.m;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.f;
import com.budejie.www.http.j;
import com.budejie.www.http.n;
import com.budejie.www.type.SearchItem;
import com.budejie.www.util.ah;
import com.budejie.www.util.ai;
import com.budejie.www.util.am;
import com.budejie.www.util.an;
import com.budejie.www.util.as;
import com.budejie.www.util.au;
import com.budejie.www.util.p;
import com.budejie.www.util.z;
import com.bumptech.glide.g.a.c;
import com.bumptech.glide.g.b.d;
import com.bumptech.glide.i;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.analytics.MobclickAgent;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.json.JSONException;
import org.json.JSONObject;

public class CommonLabelActivity extends BaseCompatTitleActivity implements com.budejie.www.f.a, com.budejie.www.i.b.a {
    private static List<SearchItem> ak;
    public static String g;
    public static boolean h = false;
    public static SearchItem i;
    private b A;
    private f B;
    private com.budejie.www.g.b C;
    private ImageView D;
    private boolean E = false;
    private Activity F;
    private Dialog G;
    private g H;
    private j I;
    private LinearLayout J;
    private int[] K = new int[]{R.id.item_head_post_1, R.id.item_head_post_2, R.id.item_head_post_3, R.id.item_head_post_4, R.id.item_head_post_5};
    private int[] L = new int[]{R.id.item_head_post_content_1, R.id.item_head_post_content_2, R.id.item_head_post_content_3, R.id.item_head_post_content_4, R.id.item_head_post_content_5};
    private boolean M;
    private TextView N;
    private RelativeLayout O;
    private Toolbar P;
    private ViewPager Q;
    private List<Fragment> R;
    private a S;
    private TextView T;
    private AsyncImageView U;
    private com.budejie.www.i.a.a V;
    private TextView W;
    private TextView X;
    private View Y;
    private TextView Z;
    private LabelUser aa;
    private com.budejie.www.widget.f ab;
    private com.budejie.www.activity.label.b.a ac;
    private com.budejie.www.activity.label.b.a ad;
    private com.budejie.www.activity.label.b.a ae;
    private Context af;
    private boolean ag;
    private TextView ah;
    private ImageView ai;
    private int aj;
    private com.budejie.www.activity.label.view.LabelTabLayout.a al = new com.budejie.www.activity.label.view.LabelTabLayout.a(this) {
        final /* synthetic */ CommonLabelActivity a;

        {
            this.a = r1;
        }

        public void a(int i) {
            this.a.b(i);
        }
    };
    private List<ViewGroup> am;
    private OnClickListener an = new OnClickListener(this) {
        final /* synthetic */ CommonLabelActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (TextUtils.isEmpty(ai.b(this.a.F))) {
                an.a(this.a.F, 1, null, null, 10);
            } else {
                BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", this.a.I.a(this.a.F, this.a.p.getTheme_id() + "", this.a.E), this.a.j);
            }
        }
    };
    private net.tsz.afinal.a.a<String> ao = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ CommonLabelActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void a(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(CheckCodeDO.CHECKCODE_USER_INPUT_KEY) && jSONObject.getInt(CheckCodeDO.CHECKCODE_USER_INPUT_KEY) == -2) {
                    return;
                }
            } catch (JSONException e) {
            }
            this.a.p = f.a(str);
            if (this.a.p == null) {
                au.a((int) R.string.load_failed);
            } else {
                this.a.s();
            }
        }

        public void onFailure(Throwable th, int i, String str) {
            au.a((int) R.string.load_failed);
        }
    };
    private net.tsz.afinal.a.a<String> ap = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ CommonLabelActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void a(String str) {
            this.a.p = f.a(str);
            if (this.a.p == null) {
                au.a((int) R.string.load_failed);
                return;
            }
            if ("1".equals(this.a.p.getIs_sub())) {
                this.a.E = true;
            }
            this.a.r();
        }

        public void onFailure(Throwable th, int i, String str) {
            au.a((int) R.string.load_failed);
        }
    };
    net.tsz.afinal.a.a<String> j = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ CommonLabelActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
            if (!this.a.F.isFinishing()) {
                this.a.G.show();
            }
        }

        public void a(String str) {
            if (this.a.G.isShowing()) {
                this.a.G.cancel();
            }
            try {
                ResultBean s = z.s(str);
                if (s != null) {
                    if ("0".equals(s.getCode())) {
                        au.a((int) R.string.operate_success);
                        this.a.E = !this.a.E;
                        this.a.p.setIs_sub(this.a.E ? "1" : "0");
                        if (this.a.p.getSub_number().matches("[0-9]+")) {
                            if (this.a.E) {
                                this.a.p.setSub_number((Integer.parseInt(this.a.p.getSub_number()) + 1) + "");
                            } else {
                                this.a.p.setSub_number((Integer.parseInt(this.a.p.getSub_number()) - 1) + "");
                            }
                        }
                        this.a.t();
                        this.a.r();
                    } else if ("-3".equals(s.getCode())) {
                        au.a((int) R.string.label_subscribe_failed_text);
                    } else {
                        au.a((int) R.string.operate_fail);
                    }
                }
            } catch (Exception e) {
                au.a((int) R.string.operate_fail);
            }
        }

        public void onFailure(Throwable th, int i, String str) {
            if (this.a.G.isShowing()) {
                this.a.G.cancel();
            }
            au.a((int) R.string.operate_fail);
        }
    };
    public com.budejie.www.adapter.e.a k = new com.budejie.www.adapter.e.a(this) {
        final /* synthetic */ CommonLabelActivity a;

        {
            this.a = r1;
        }

        public void b(View view, ListItemObject listItemObject) {
            this.a.B.a("cai", this.a.l, listItemObject);
            this.a.B.a(listItemObject, this.a.l, "cai");
        }

        public OnClickListener b() {
            return null;
        }

        public void a(View view, ListItemObject listItemObject, String str) {
            Bundle bundle = new Bundle();
            bundle.putString(PersonalProfileActivity.c, listItemObject.getUid());
            bundle.putString(PersonalProfileActivity.d, str);
            this.a.C.a(7, bundle).onClick(view);
        }

        public void c(View view, ListItemObject listItemObject) {
            p.a(this.a, listItemObject, this.a.m);
        }

        public void a(View view, ListItemObject listItemObject) {
            TipPopUp.a(this.a.F, TypeControl.dingtie);
            this.a.B.a("ding", this.a.l, listItemObject);
            this.a.B.a(listItemObject, this.a.l, "ding");
        }

        public void a_(String str) {
        }

        public void a(View view, ListItemObject listItemObject, int i) {
            this.a.r = listItemObject;
            Bundle bundle = new Bundle();
            bundle.putInt("position", i);
            bundle.putString(HistoryOpenHelper.COLUMN_UID, ai.b(this.a));
            bundle.putSerializable("weiboMap", this.a.x);
            bundle.putSerializable("data", listItemObject);
            view.setTag(listItemObject);
            this.a.C.a(5, bundle, this.a.m, this.a.t, this.a.w, this.a.v, this.a.y, this.a.s, this.a.l).onClick(view);
        }

        public void e(View view, ListItemObject listItemObject) {
            view.setTag(listItemObject);
            this.a.C.a(3, null).onClick(view);
        }

        public void d(View view, ListItemObject listItemObject) {
            view.setTag(listItemObject);
            this.a.C.a(3, null).onClick(view);
        }

        public void a() {
        }

        public void a(SuggestedFollowsListItem suggestedFollowsListItem) {
        }

        public void a(ListItemObject listItemObject, int i) {
            if (!TextUtils.isEmpty(CommonLabelActivity.g)) {
                Intent intent = new Intent(this.a.F, CommonLabelActivity.class);
                PlateBean plateBean = listItemObject.getPlateBean(i);
                if (plateBean != null && !CommonLabelActivity.g.equals(plateBean.theme_name)) {
                    intent.putExtra("theme_name", plateBean.theme_name);
                    intent.putExtra("theme_id", plateBean.theme_id);
                    this.a.F.startActivity(intent);
                }
            }
        }
    };
    @SuppressLint({"HandlerLeak"})
    Handler l = new Handler(this) {
        final /* synthetic */ CommonLabelActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 817) {
                this.a.y.a(((Integer) message.obj).intValue());
            } else if (i == 816) {
                Bundle bundle = (Bundle) message.obj;
                CharSequence string = bundle.getString(com.alipay.sdk.util.j.c);
                i = bundle.getInt("notificationId");
                if (TextUtils.isEmpty(string)) {
                    this.a.y.a(i, false, (int) R.string.forwarfail);
                } else if ("0".equals(string)) {
                    this.a.y.a(i, true, (int) R.string.forwardsuccess);
                } else {
                    this.a.y.a(i, false, (int) R.string.forwarfail);
                }
                new Thread(this) {
                    final /* synthetic */ AnonymousClass4 b;

                    public void run() {
                        try {
                            Thread.sleep(1000);
                            this.b.a.l.sendMessage(this.b.a.l.obtainMessage(817, Integer.valueOf(i)));
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
            }
        }
    };
    @SuppressLint({"HandlerLeak"})
    final Handler m = new Handler(this) {
        final /* synthetic */ CommonLabelActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 4) {
                this.a.r.setLove(this.a.r.getLove() + 1);
            } else if (i == 5) {
                this.a.z = ProgressDialog.show(this.a, "", (String) message.obj, true, true);
            } else if (i == 6) {
                this.a.z.cancel();
            } else if (i == 7) {
                an.a(this.a, this.a.getString(R.string.already_collected), -1).show();
            } else if (i == 9) {
                this.a.r.setRepost(String.valueOf(Integer.parseInt(this.a.r.getRepost()) + 1));
            } else if (i == 10) {
                an.a(this.a, this.a.getString(R.string.collect_failed), -1).show();
            } else if (i == 11) {
                CharSequence b = ai.b(this.a);
                an.a(this.a, (int) R.string.collected, (int) R.drawable.collect_tip).show();
                if (!TextUtils.isEmpty(b)) {
                    r0 = (String) message.obj;
                    this.a.u.a("add", ai.b(this.a), r0, 971);
                }
            } else if (i == 12) {
                an.a(this.a, (int) R.string.collect_fail, (int) R.drawable.collect_tip).show();
            } else if (i == 829) {
                r0 = (String) message.obj;
                this.a.A.a("collectTable", r0);
                an.a(this.a, this.a.getString(R.string.delete_success), -1).show();
                this.a.u.a("delete", ai.b(this.a), r0, 971);
            } else if (i == 1001) {
                HashMap k = z.k((String) message.obj);
                if (k != null) {
                    r0 = (String) k.get("result_desc");
                    if (TextUtils.isEmpty(r0)) {
                        au.a((int) R.string.operate_fail);
                    } else {
                        au.a(r0);
                    }
                } else {
                    au.a((int) R.string.operate_fail);
                }
            } else if (i == 1006) {
                SuggestedFollowsListItem suggestedFollowsListItem = new SuggestedFollowsListItem();
                suggestedFollowsListItem.uid = ((ListItemObject) message.obj).getUid();
                this.a.a(suggestedFollowsListItem, (ListItemObject) message.obj);
            }
            this.a.d().notifyDataSetChanged();
        }
    };
    private l n;
    private AsyncImageView o;
    private LabelBean p;
    private String q;
    private ListItemObject r;
    private SharedPreferences s;
    private IWXAPI t;
    private com.budejie.www.http.b u;
    private n v;
    private m w;
    private HashMap<String, String> x;
    private com.elves.update.a y;
    private ProgressDialog z;

    private class a extends FragmentPagerAdapter {
        final /* synthetic */ CommonLabelActivity a;

        private a(CommonLabelActivity commonLabelActivity, FragmentManager fragmentManager) {
            this.a = commonLabelActivity;
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            return (Fragment) this.a.R.get(i);
        }

        public int getCount() {
            return this.a.R.size();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        an.f((Activity) this);
        setContentView((int) R.layout.activity_label_details);
        this.F = this;
        EventBus.getDefault().register(this);
        this.V = new com.budejie.www.i.a.a();
        this.V.a((com.budejie.www.i.b.a) this);
        this.ag = ai.a(this) == 0;
        j();
        i();
        this.V.a(g);
        k();
        v();
        setSupportActionBar(this.P);
    }

    protected void onResume() {
        super.onResume();
        this.C = new com.budejie.www.g.b(this, this.b, this.a, this);
    }

    public LabelBean c() {
        return this.p;
    }

    public l d() {
        return this.n;
    }

    private void i() {
        boolean z = true;
        Intent intent = getIntent();
        g = intent.getStringExtra("theme_id");
        this.q = intent.getStringExtra("theme_name");
        if (intent.getIntExtra("colum_set", 1) != 2) {
            z = false;
        }
        this.M = z;
        this.aj = intent.getIntExtra("tab_position_tag", 0);
        this.T = (TextView) findViewById(R.id.title_text_view);
        if (this.T != null) {
            this.T.setText("");
        }
        c(this.q);
    }

    private void c(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.length() > 5) {
                str = str.substring(0, 5) + "...";
            }
            this.T.setText(str);
        }
    }

    private void j() {
        this.I = new j();
        this.s = getSharedPreferences("weiboprefer", 0);
        String string = this.s.getString("id", "");
        this.u = com.budejie.www.http.b.a(this, this);
        this.H = new g(this);
        this.v = new n(this);
        this.w = new m(this);
        this.x = this.v.a(string);
        this.y = new com.elves.update.a(this);
        this.A = new b(this);
        this.B = new f(this);
        this.C = new com.budejie.www.g.b(this, this.b, this.a, this);
        this.t = WXAPIFactory.createWXAPI(this, "wx592fdc48acfbe290", true);
        this.t.registerApp("wx592fdc48acfbe290");
    }

    private void k() {
        this.n = new l(this, this.k);
        q();
        this.G = new Dialog(this, R.style.dialogTheme);
        this.G.setCanceledOnTouchOutside(true);
        this.G.setContentView(R.layout.loaddialog);
        ImageView imageView = (ImageView) findViewById(R.id.post_image_view);
        if (imageView != null) {
            imageView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ CommonLabelActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.u();
                }
            });
        }
        this.P = (Toolbar) findViewById(R.id.toolbar);
        this.Q = (ViewPager) findViewById(R.id.view_pager);
        if (this.Q != null) {
            this.Q.setOffscreenPageLimit(3);
        }
        m();
        LabelTabLayout labelTabLayout = (LabelTabLayout) findViewById(R.id.label_tab_layout);
        if (labelTabLayout != null) {
            labelTabLayout.setTabListener(this.al);
            labelTabLayout.a(this.Q, this.aj, null);
        }
        this.ah = (TextView) findViewById(R.id.back_text_view);
        this.ai = (ImageView) findViewById(R.id.back_image_view);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.back_layout);
        if (linearLayout != null) {
            linearLayout.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ CommonLabelActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.finish();
                }
            });
        }
        l();
    }

    private void l() {
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        if (appBarLayout != null) {
            appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener(this) {
                final /* synthetic */ CommonLabelActivity a;

                {
                    this.a = r1;
                }

                public void a(AppBarLayout appBarLayout, AppBarStateChangeListener$State appBarStateChangeListener$State) {
                    if (appBarStateChangeListener$State == AppBarStateChangeListener$State.EXPANDED) {
                        this.a.ai.setImageResource(R.drawable.label_title_back_image_default_selector);
                        this.a.ah.setTextColor(this.a.getResources().getColorStateList(R.color.label_title_left_back_text_default_selector));
                        if (!this.a.ag) {
                            this.a.T.setTextColor(this.a.getResources().getColor(R.color.white));
                        }
                    } else if (appBarStateChangeListener$State != AppBarStateChangeListener$State.COLLAPSED) {
                    } else {
                        if (this.a.ag) {
                            this.a.ai.setImageResource(R.drawable.collect_left_btn);
                            this.a.ah.setTextColor(this.a.getResources().getColorStateList(R.color.label_title_left_back_text_day_selector));
                            return;
                        }
                        this.a.ai.setImageResource(R.drawable.collect_left_btn_night);
                        this.a.ah.setTextColor(this.a.getResources().getColorStateList(R.color.label_title_left_back_text_night_selector));
                        this.a.T.setTextColor(this.a.getResources().getColor(R.color.title_text_color_night));
                    }
                }
            });
        }
    }

    private void b(int i) {
        if (!com.budejie.www.goddubbing.c.a.a(this.R) && i >= 0 && i < this.R.size()) {
            Fragment fragment = (Fragment) this.R.get(i);
            if (fragment instanceof com.budejie.www.activity.label.b.a) {
                ((com.budejie.www.activity.label.b.a) fragment).a();
            } else if (fragment instanceof com.budejie.www.activity.label.b.b) {
                ((com.budejie.www.activity.label.b.b) fragment).b();
            }
        }
    }

    private void m() {
        if (com.budejie.www.goddubbing.c.a.a(this.R)) {
            n();
        }
        this.S = new a(getSupportFragmentManager());
        this.Q.setAdapter(this.S);
    }

    private void n() {
        this.R = new ArrayList();
        this.ac = new com.budejie.www.activity.label.b.a();
        Bundle o = o();
        o.putString("post_type_tag", PlatePostEnum.NEW_TYPE.getValue());
        this.ac.setArguments(o);
        this.R.add(this.ac);
        this.ad = new com.budejie.www.activity.label.b.a();
        o = o();
        o.putString("post_type_tag", PlatePostEnum.ESSENCE_TYPE.getValue());
        this.ad.setArguments(o);
        this.R.add(this.ad);
        this.ae = new com.budejie.www.activity.label.b.a();
        o = o();
        o.putString("post_type_tag", PlatePostEnum.HOT_TYPE.getValue());
        this.ae.setArguments(o);
        this.R.add(this.ae);
        com.budejie.www.activity.label.b.b bVar = new com.budejie.www.activity.label.b.b();
        bVar.setArguments(o());
        this.R.add(bVar);
    }

    private Bundle o() {
        Bundle bundle = new Bundle();
        bundle.putString("theme_id", g);
        bundle.putString("theme_name", this.q);
        bundle.putInt("colum_set", this.M ? 2 : 1);
        return bundle;
    }

    public void a(Throwable th) {
    }

    public void a(Object obj) {
    }

    public void a(UserBanResponse userBanResponse) {
        if (userBanResponse != null) {
            ak = userBanResponse.getList();
        }
    }

    public void a(CommonInfoBean commonInfoBean) {
        if (commonInfoBean != null) {
            au.a(commonInfoBean.getInfo());
            if (this.V != null) {
                this.V.a(g);
            }
        }
    }

    public static boolean a(String str) {
        return (i == null || TextUtils.isEmpty(str) || !str.equals(i.getId())) ? false : true;
    }

    public static boolean b(String str) {
        if (com.budejie.www.goddubbing.c.a.a(ak) || TextUtils.isEmpty(str)) {
            return false;
        }
        for (SearchItem id : ak) {
            if (str.equals(id.getId())) {
                return true;
            }
        }
        return false;
    }

    public void onEventMainThread(CommonLabelOperator commonLabelOperator) {
        if (this.V != null && commonLabelOperator != null) {
            ListItemObject listItemObject = commonLabelOperator.data;
            if (listItemObject != null) {
                this.af = commonLabelOperator.context;
                String wid = listItemObject.getWid();
                CommonLabelAction commonLabelAction = commonLabelOperator.operatorAction;
                com.budejie.www.i.a.a aVar;
                String str;
                String value;
                if (commonLabelAction == CommonLabelAction.ADD_ESSENCE) {
                    aVar = this.V;
                    str = g;
                    if (h.a(listItemObject, g)) {
                        value = PlatePostEnum.REMOVE_ESSENCE.getValue();
                    } else {
                        value = PlatePostEnum.ADD_ESSENCE.getValue();
                    }
                    aVar.a(str, value, wid);
                } else if (commonLabelAction == CommonLabelAction.TO_TOP) {
                    aVar = this.V;
                    str = g;
                    if (h.b(listItemObject, g)) {
                        value = PlatePostEnum.POST_REMOVE_FROM_TOP.getValue();
                    } else {
                        value = PlatePostEnum.POST_TO_TOP.getValue();
                    }
                    aVar.b(wid, str, value);
                } else if (commonLabelAction == CommonLabelAction.DELETE_POST) {
                    this.V.a(g, PlatePostEnum.REMOVE_FROM_PLATE.getValue(), wid);
                }
            }
        }
    }

    public void onEventMainThread(UserBanOperator userBanOperator) {
        if (this.V != null && userBanOperator != null) {
            CommentItem commentItem = userBanOperator.getCommentItem();
            if (commentItem != null) {
                this.V.c(commentItem.getUid(), g, userBanOperator.getOperationType());
            }
        }
    }

    public void a(LabelUser labelUser) {
        this.aa = labelUser;
        p();
    }

    public void b(CommonInfoBean commonInfoBean) {
        if (commonInfoBean != null) {
            if (this.af == null || !(this.af instanceof CommonLabelActivity)) {
                au.a(commonInfoBean.getInfo());
            } else {
                a(true, commonInfoBean.getInfo());
            }
            EventBus.getDefault().post(UpdateLabelPostStatus.ESSENCE_OPERATOR);
        }
    }

    public void a(ToTopResponse toTopResponse) {
        if (toTopResponse != null) {
            InfoBean info = toTopResponse.getInfo();
            if (info != null) {
                boolean z = info.getCode() == PlatePostEnum.TO_TOP_SUCCESS.getCode();
                if (this.af == null || !(this.af instanceof CommonLabelActivity)) {
                    au.a(info.getResult());
                } else {
                    a(z, info.getResult());
                }
                if (z) {
                    EventBus.getDefault().post(UpdateLabelPostStatus.TO_TOP_OPERATOR);
                }
            }
        }
    }

    private void a(boolean z, String str) {
        if (this.ab == null) {
            this.ab = new com.budejie.www.widget.f(this, R.style.dialogTheme);
        }
        this.ab.a(z, str, 2000);
        this.ab.show();
    }

    private void p() {
        if (an.d() && this.aa != null) {
            ListBean list = this.aa.getList();
            if (list != null) {
                Object<SearchItem> master = list.getMaster();
                Object f = an.f((Context) this);
                if (!TextUtils.isEmpty(f) && !com.budejie.www.goddubbing.c.a.a(master)) {
                    for (SearchItem id : master) {
                        if (f.equals(id.getId())) {
                            h = true;
                            return;
                        }
                    }
                }
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        h = false;
        i = null;
        g = null;
        if (ak != null) {
            ak.clear();
            ak = null;
        }
    }

    public void a_() {
        if (!this.M) {
            b();
        }
    }

    public int f() {
        return this.aj;
    }

    public void g() {
        a();
    }

    public Context h() {
        return this;
    }

    private void q() {
        View findViewById = findViewById(R.id.header_layout);
        if (findViewById != null) {
            this.o = (AsyncImageView) findViewById.findViewById(R.id.labelDetailsImg);
            this.U = (AsyncImageView) findViewById.findViewById(R.id.label_image_view);
            this.D = (ImageView) findViewById.findViewById(R.id.lebel_add_btn);
            this.J = (LinearLayout) findViewById.findViewById(R.id.label_detail_head_post_layout);
            this.N = (TextView) findViewById.findViewById(R.id.post_standard_textView);
            this.O = (RelativeLayout) findViewById.findViewById(R.id.standard_layout);
            this.W = (TextView) findViewById.findViewById(R.id.label_name_text_view);
            this.X = (TextView) findViewById.findViewById(R.id.label_person_text_view);
            this.Y = findViewById.findViewById(R.id.label_divide_line);
            this.Z = (TextView) findViewById.findViewById(R.id.label_post_num_text_view);
        }
    }

    private void r() {
        this.D.setVisibility(this.E ? 8 : 0);
        if (!this.E) {
            this.D.setOnClickListener(this.an);
        }
    }

    private void s() {
        int i = 0;
        if (this.p != null) {
            if ("1".equals(this.p.getIs_sub())) {
                this.E = true;
            }
            c(this.p.getTheme_name());
            final Object info = this.p.getInfo();
            RelativeLayout relativeLayout = this.O;
            if (TextUtils.isEmpty(info)) {
                i = 8;
            }
            relativeLayout.setVisibility(i);
            this.N.setText(info);
            this.O.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ CommonLabelActivity b;

                public void onClick(View view) {
                    Intent intent = new Intent(this.b, PostStandardLookActivity.class);
                    intent.putExtra("standardTextTag", info);
                    this.b.startActivity(intent);
                }
            });
            try {
                String image_list = this.p.getImage_list();
                this.U.setAsyncCacheImage(image_list, R.drawable.lable_default_bg);
                if (!TextUtils.isEmpty(image_list)) {
                    i.a((FragmentActivity) this).a(com.lt.a.a((Context) this).a(image_list)).a(new com.budejie.www.d.b.a(this, 15)).a((int) R.drawable.label_default_gaussian_blur_image).b((int) R.drawable.label_default_gaussian_blur_image).a(DiskCacheStrategy.RESULT).a(new d(this, this.o) {
                        final /* synthetic */ CommonLabelActivity a;

                        public /* synthetic */ void onResourceReady(Object obj, c cVar) {
                            a((com.bumptech.glide.load.resource.a.b) obj, cVar);
                        }

                        public void a(com.bumptech.glide.load.resource.a.b bVar, c<? super com.bumptech.glide.load.resource.a.b> cVar) {
                            super.a(bVar, cVar);
                            this.a.o.setImageDrawable(bVar);
                        }

                        protected void a(com.bumptech.glide.load.resource.a.b bVar) {
                        }
                    });
                }
            } catch (Exception e) {
            } catch (OutOfMemoryError e2) {
            }
            t();
            r();
        }
    }

    private void t() {
        int i = 0;
        if (this.p != null) {
            int i2;
            PlateBean plateBean = new PlateBean();
            if (!TextUtils.isEmpty(this.p.sub_number)) {
                try {
                    plateBean.sub_number = Integer.parseInt(this.p.sub_number);
                } catch (NumberFormatException e) {
                    plateBean.sub_number = 0;
                }
            }
            if (!TextUtils.isEmpty(this.p.getPost_number())) {
                try {
                    plateBean.post_num = Integer.parseInt(this.p.getPost_number());
                } catch (NumberFormatException e2) {
                    plateBean.post_num = 0;
                }
            }
            this.W.setText(this.p.getTheme_name());
            if (plateBean.sub_number <= 0 || plateBean.post_num <= 0) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            View view = this.Y;
            if (i2 == 0) {
                i = 8;
            }
            view.setVisibility(i);
            if (plateBean.sub_number > 0) {
                this.X.setText(ah.a(plateBean));
            }
            if (plateBean.post_num > 0) {
                this.Z.setText(ah.a(plateBean.post_num, getResources().getString(R.string.million_text)) + "帖子");
            }
        }
    }

    public void a(List<ListItemObject> list) {
        if (!com.budejie.www.goddubbing.c.a.a(list)) {
            findViewById(R.id.standard_line_view).setVisibility(0);
        }
        if (this.am == null) {
            this.am = new ArrayList();
        }
        for (ViewGroup visibility : this.am) {
            visibility.setVisibility(8);
        }
        this.am.clear();
        int i = 0;
        while (i < list.size() && i < 5) {
            final ListItemObject listItemObject = (ListItemObject) list.get(i);
            RelativeLayout relativeLayout = (RelativeLayout) this.J.findViewById(this.K[i]);
            ((TextView) this.J.findViewById(this.L[i])).setText(listItemObject.getContent());
            relativeLayout.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ CommonLabelActivity b;

                public void onClick(View view) {
                    com.budejie.www.util.a.a(this.b.F, listItemObject, "", false);
                }
            });
            relativeLayout.setVisibility(0);
            this.am.add(relativeLayout);
            i++;
        }
    }

    private void u() {
        if (!an.e(this.F) && this.p != null) {
            MobclickAgent.onEvent(this.F, "标签详情", "底部发帖按钮点击次数");
            Object content_type = this.p.getContent_type();
            if (TextUtils.isEmpty(content_type) || ",".equals(content_type)) {
                an.a((Activity) this, "暂时没有发帖的类型!", -1).show();
                return;
            }
            String[] split = content_type.split(",");
            Map hashMap = new HashMap();
            hashMap.put("theme_id", Integer.valueOf(this.p.getTheme_id()));
            hashMap.put("theme_name", this.p.getTheme_name());
            p.a((Activity) this, split, hashMap);
        }
    }

    public void a(int i, String str) {
    }

    public void a(int i) {
    }

    private void v() {
        a_();
        BudejieApplication.a.a(RequstMethod.POST, "http://api.budejie.com/api/api_open.php", this.I.g(this.F, g, this.q), this.ao);
    }

    public void onWbShareSuccess() {
        super.onWbShareSuccess();
        am.a(this.r);
        if (this.n != null) {
            this.n.notifyDataSetChanged();
        }
    }

    private void a(final SuggestedFollowsListItem suggestedFollowsListItem, final ListItemObject listItemObject) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new j().e(this.F, suggestedFollowsListItem.uid), new net.tsz.afinal.a.a<String>(this) {
            final /* synthetic */ CommonLabelActivity c;

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
                            au.a((int) R.string.operate_fail);
                        } else {
                            au.a(msg);
                        }
                        if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                            if (listItemObject != null) {
                                listItemObject.setShwFollow(true);
                            }
                            suggestedFollowsListItem.is_follow = 0;
                            this.c.H.a(suggestedFollowsListItem.uid);
                            as.b().a(suggestedFollowsListItem.uid, Integer.valueOf(0));
                            this.c.d().notifyDataSetChanged();
                            au.a((int) R.string.had_cancel_attention);
                            return;
                        }
                        return;
                    }
                    au.a((int) R.string.operate_fail);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 10) {
            BudejieApplication.a.a(RequstMethod.POST, "http://api.budejie.com/api/api_open.php", this.I.g(this.F, g, this.q), this.ap);
        }
    }
}
