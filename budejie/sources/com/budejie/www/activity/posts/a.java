package com.budejie.www.activity.posts;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.v6.sixrooms.room.gift.GiftConfigUtil;
import com.bdj.picture.edit.util.c;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.BudejieApplication.Status;
import com.budejie.www.activity.video.k;
import com.budejie.www.activity.view.AudioLayout;
import com.budejie.www.activity.view.TabWidget;
import com.budejie.www.ad.AdManager;
import com.budejie.www.adapter.c.b;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.OperationButton;
import com.budejie.www.bean.OperationItem;
import com.budejie.www.bean.SuggestedFollowsListItem;
import com.budejie.www.bean.TopNavigation;
import com.budejie.www.bean.UserItem;
import com.budejie.www.c.d;
import com.budejie.www.c.g;
import com.budejie.www.c.h;
import com.budejie.www.c.i;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.http.n;
import com.budejie.www.util.aa;
import com.budejie.www.util.ae;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.widget.XListView;
import com.nostra13.universalimageloader.core.d.e;
import com.tencent.connect.common.Constants;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

public class a extends Fragment {
    private BudejieApplication A;
    private int B = 3;
    private int C = 7;
    private int D = 9;
    private List<SuggestedFollowsListItem> E;
    private boolean F = false;
    private ListItemObject G;
    private ListItemObject H;
    private boolean I;
    private int J = 0;
    private String K;
    private UserItem L;
    private n M;
    private boolean N = false;
    private List<ListItemObject> O;
    private List<ListItemObject> P;
    private List<ListItemObject> Q;
    private LinearLayout R;
    private boolean S;
    private j T;
    private List<OperationItem> U;
    private i V;
    private int W = 0;
    private boolean X = false;
    private boolean Y = false;
    private long Z;
    public String a;
    private int aa;
    private TopNavigation ab;
    private ListItemObject ac;
    private int ad = 3;
    private boolean ae = false;
    private long af;
    private String ag;
    private BudejieApplication ah;
    private boolean ai;
    private OnClickListener aj = new a$12(this);
    private OnScrollListener ak = new a$13(this);
    private com.budejie.www.widget.XListView.a al = new a$14(this);
    private net.tsz.afinal.a.a<String> am = new a$15(this);
    private net.tsz.afinal.a.a<String> an = new a$2(this);
    private net.tsz.afinal.a.a<String> ao = new a$3(this);
    private net.tsz.afinal.a.a<String> ap = new a$4(this);
    private net.tsz.afinal.a.a<String> aq = new a$5(this);
    private OnClickListener ar = new a$6(this);
    private boolean as;
    public String b;
    public SharedPreferences c;
    View d;
    boolean e = false;
    private boolean f = true;
    private boolean g = true;
    private XListView h;
    private TextView i;
    private b j;
    private String k;
    private int l;
    private String m;
    private long n;
    private int o;
    private int p;
    private List<ListItemObject> q;
    private List<ListItemObject> r;
    private Handler s;
    private PostsActivity t;
    private FeedADTools u;
    private com.budejie.www.c.b v;
    private h w;
    private g x;
    private d y;
    private TabWidget z;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (this.d == null) {
            this.t = (PostsActivity) activity;
            this.T = new j();
            this.c = activity.getSharedPreferences("weiboprefer", 0);
            this.K = this.c.getString("id", "");
            this.M = new n(this.t);
            this.A = (BudejieApplication) this.t.getApplication();
            Bundle arguments = getArguments();
            this.k = arguments.getString("post_type");
            this.ab = (TopNavigation) arguments.getSerializable("TOP_NAVIGATION_KEY");
            this.ag = arguments.getString("TOP_NUM_KEY");
            this.l = arguments.getInt("page_type");
            this.a = this.k + this.l;
            this.b = this.a + "position";
            aa.a("PostsFeaturesFragment", "帖子类型:" + this.k + ",界面类型:" + this.l);
            this.j = new b(this.t, (PostsActivity) activity, 0);
            this.s = new Handler();
            this.q = new ArrayList();
            this.r = new ArrayList();
            this.v = new com.budejie.www.c.b(activity);
            this.w = new h(activity);
            this.x = new g(activity);
            this.y = new d(activity);
            this.V = new i(activity);
            this.U = this.V.a();
            if (c.a(y())) {
                this.af = x();
            } else {
                this.af = 0;
            }
            i();
            j();
            k();
            this.P = new ArrayList();
            this.Q = new ArrayList();
        }
    }

    private void i() {
        if (!AdManager.isAdClosed()) {
            this.u = new FeedADTools(getActivity());
            this.u.a(this.ab.entrytype, new a$1(this));
        }
    }

    private void j() {
        Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this.t, "new_item_show_position");
        Object configParams2 = OnlineConfigAgent.getInstance().getConfigParams(this.t, "new_item_show_interval");
        Object configParams3 = OnlineConfigAgent.getInstance().getConfigParams(this.t, "new_operation_item_show_position");
        if (!(TextUtils.isEmpty(configParams) || "0".equals(configParams))) {
            this.B = Integer.parseInt(configParams);
        }
        if (!TextUtils.isEmpty(configParams2)) {
            this.C = Integer.parseInt(configParams2.trim());
        }
        if (!TextUtils.isEmpty(configParams3)) {
            this.D = Integer.parseInt(configParams3.trim());
        }
    }

    private void k() {
        long j = 0;
        if (!l()) {
            try {
                aa.b("PostsFeaturesFragment", "initCacheData() LIST_HISTORY_POSITION_KEY =" + this.b);
                this.m = this.t.getBudejieSettings().k.a(this.b);
                if (this.l == 1) {
                    this.q = this.v.a(this.ab.getKey(), null, "essence");
                } else if (this.l == 2) {
                    this.q = this.v.a(this.ab.getKey(), null, "new");
                }
                if (!(this.q == null || this.q.isEmpty())) {
                    this.f = false;
                }
                if (!this.f) {
                    j = this.c.getLong("np_" + this.ab.getKey(), 0);
                }
                this.Z = j;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<ListItemObject> a(boolean z) {
        if (z) {
            return (ArrayList) this.q;
        }
        return (ArrayList) this.O;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.d != null) {
            if (this.d.getParent() != null) {
                ((ViewGroup) this.d.getParent()).removeView(this.d);
            }
            this.e = true;
            return this.d;
        }
        this.d = layoutInflater.inflate(R.layout.fragment_posts_layout, viewGroup, false);
        this.e = false;
        return this.d;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (!this.e) {
            this.i = (TextView) view.findViewById(R.id.headToast);
            this.i.setBackgroundResource(com.budejie.www.util.j.bc);
            com.budejie.www.util.c.a(this.i);
            this.i.setTextColor(getResources().getColor(com.budejie.www.util.j.bd));
            this.R = (LinearLayout) view.findViewById(R.id.hotToast);
            this.i.setOnClickListener(this.aj);
            this.h = (XListView) view.findViewById(R.id.listview);
            this.z = new TabWidget(this.t, this.l, this.ag);
            this.h.a(this.z);
            this.h.setLastRefreshTime(this.c.getLong("last_refresh_" + this.ab.getKey(), System.currentTimeMillis()));
            this.h.setAdapter(this.j);
            this.h.setPullRefreshEnable(true);
            this.h.setOnScrollListener(new e(com.nostra13.universalimageloader.core.d.a(), false, true, this.ak));
            this.h.setXListViewListener(this.al);
            this.h.setOnScrollDirectionListener(new a$8(this));
            if (!this.f && this.l != 4) {
                b(this.m);
            } else if (this.l != 4 || an.a(this.t.g)) {
                if (this.l == 4) {
                    b(this.m);
                }
                this.s.postDelayed(new a$9(this), 200);
            }
            if (this.j == null || this.j.isEmpty()) {
                this.h.setPullLoadEnable(false);
            } else {
                this.h.setPullLoadEnable(true);
            }
        }
    }

    public void onResume() {
        super.onResume();
        if (this.z != null) {
            this.z.b();
        }
        if (getUserVisibleHint()) {
            this.h.post(new a$10(this));
        }
        if (this.ab.url.endsWith("/31/")) {
            this.j.notifyDataSetChanged();
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (!(z || this.t == null)) {
            k.a(this.t).i();
        }
        if (this.ai && !z) {
            b();
        }
        this.ai = z;
        if (isResumed() && z) {
            this.h.post(new a$11(this));
        }
    }

    public void a() {
        if (this.z != null) {
            this.z.a();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.h.getAdapter() != null) {
            z();
        }
        C();
        if (this.z != null) {
            this.z.c();
        }
        E();
        b();
    }

    public void b() {
        if (isAdded()) {
            if (this.ah == null) {
                BudejieApplication budejieApplication = (BudejieApplication) getActivity().getApplication();
                this.ah = budejieApplication;
                if (budejieApplication == null) {
                    return;
                }
            }
            ae.a(this.ah, this.t);
            com.budejie.www.service.MediaPlayerServer.a f = this.ah.f();
            if (f == null) {
                return;
            }
            if (f.a() || f.n()) {
                com.budejie.www.f.b j = f.j();
                Object obj = (j == null || !(j instanceof AudioLayout)) ? null : 1;
                if (obj != null) {
                    f.d();
                    this.ah.a(Status.end);
                }
            }
        }
    }

    private boolean l() {
        if (this.l == 2 || this.l == 1) {
            long j = this.c.getLong("last_refresh_" + this.ab.getKey(), System.currentTimeMillis());
            if (((double) ((System.currentTimeMillis() - j) / 60000)) >= 60.0d * m()) {
                return true;
            }
        }
        return false;
    }

    private double m() {
        Object obj = "";
        Object obj2 = "";
        String[] strArr = new String[5];
        try {
            if (this.l == 1) {
                obj2 = OnlineConfigAgent.getInstance().getConfigParams(this.t, "精华列表自动刷新时间间隔");
            } else if (this.l == 2) {
                obj2 = OnlineConfigAgent.getInstance().getConfigParams(this.t, "最新列表自动刷新时间间隔");
            }
            if (!TextUtils.isEmpty(obj2)) {
                obj = obj2.split(com.alipay.sdk.util.h.b)[0];
            }
            if (TextUtils.isEmpty(obj)) {
                return 48.0d;
            }
            return Double.parseDouble(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return 48.0d;
        }
    }

    public XListView c() {
        return this.h;
    }

    public b d() {
        return this.j;
    }

    private void n() {
        D();
        aa.a("TAG", this.l + "");
        if (this.l != 4) {
            p();
            o();
        } else if (!an.a(this.t.g) || !an.a(this.t)) {
            this.h.a(false);
            E();
        } else if (this.A.b != null) {
            p();
            o();
        } else if (!isDetached()) {
            this.I = true;
        }
    }

    private void o() {
        if (this.u == null || !this.u.a()) {
            i();
        }
    }

    private void p() {
        BudejieApplication.a.a(RequstMethod.GET, a(this.ab.url, "0"), new j(this.t), this.an);
        com.budejie.www.http.i.a(this.t.getString(R.string.track_event_load_first), this.t.getString(R.string.track_event_load_first));
    }

    public void e() {
        if (this.l == 4 && this.A.b == null) {
            this.h.c();
        } else if (this.ae) {
            this.ae = false;
        } else {
            this.ae = true;
            BudejieApplication.a.a(RequstMethod.GET, a(this.ab.url, String.valueOf(this.Z)), new j(this.t), this.aq);
        }
    }

    private void q() {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new j().d(this.t, this.K == null ? "" : this.K, GiftConfigUtil.SUPER_GIRL_GIFT_TAG), this.am);
    }

    private List<ListItemObject> r() {
        if (this.r.size() < 10) {
            return null;
        }
        return s();
    }

    private List<ListItemObject> s() {
        List<ListItemObject> arrayList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            arrayList.add((ListItemObject) this.r.get(0));
            this.r.remove(0);
        }
        return arrayList;
    }

    private int t() {
        int i = 0;
        for (ListItemObject listItemObject : this.Q) {
            int i2;
            if (listItemObject.getWid() == null || this.P.contains(listItemObject)) {
                i2 = i;
            } else {
                i2 = i + 1;
            }
            i = i2;
        }
        return i;
    }

    public boolean a(List<ListItemObject> list) {
        return list == null || list.isEmpty();
    }

    private void u() {
        this.c.edit().putLong("np_" + this.ab.getKey(), this.Z).commit();
    }

    private long v() {
        return this.c.getLong("maxid_" + this.ab.getKey(), 0);
    }

    private void w() {
        this.c.edit().putLong("recsys_np_" + this.ab.getKey(), this.af).commit();
        this.c.edit().putLong("recsys_np_save_date", System.currentTimeMillis()).commit();
    }

    private long x() {
        return this.c.getLong("recsys_np_" + this.ab.getKey(), 0);
    }

    private long y() {
        return this.c.getLong("recsys_np_save_date", 0);
    }

    private void b(String str) {
        aa.a("PostsFeaturesFragment", "取出的贴子id:" + str);
        if (this.q != null && !this.q.isEmpty()) {
            an.a(this.q, this.w, this.y, this.x);
            a(this.q, this.ab.god_topic_type);
            this.n = v();
            aa.a("PostsFeaturesFragment", "读取的maxid为：" + this.n);
            this.j.a(this.q);
            this.h.setPullLoadEnable(true);
            this.O = this.q;
            aa.a("adv", "缓存:" + this.q.size() + ",page:" + this.l);
            a(0, this.N);
            this.s.postDelayed(new a$7(this, str), 300);
        }
    }

    private void z() {
        try {
            if (this.t.getBudejieSettings().a.a().booleanValue()) {
                int firstVisiblePosition = this.h.getFirstVisiblePosition();
                if (this.h.getChildAt(0).getBottom() <= an.a(this.t, 45)) {
                    firstVisiblePosition++;
                }
                int headerViewsCount = firstVisiblePosition - this.h.getHeaderViewsCount();
                String str = null;
                if (headerViewsCount < this.j.getCount() && headerViewsCount > 0) {
                    str = ((ListItemObject) this.j.getItem(headerViewsCount)).getWid();
                    if (TextUtils.isEmpty(str) && headerViewsCount > 0) {
                        str = ((ListItemObject) this.j.getItem(headerViewsCount - 1)).getWid();
                        if (TextUtils.isEmpty(str) && headerViewsCount < this.j.getCount() - 1) {
                            str = ((ListItemObject) this.j.getItem(headerViewsCount + 1)).getWid();
                        }
                    }
                }
                aa.b("PostsFeaturesFragment", "saveReadPosition() LIST_HISTORY_POSITION_KEY =" + this.b);
                aa.b("PostsFeaturesFragment", "saveReadPosition() wid = " + str);
                this.t.getBudejieSettings().k.a(this.b, str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(ArrayList<ListItemObject> arrayList) {
        aa.b("PostsFeaturesFragment", "editData()");
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ListItemObject listItemObject = (ListItemObject) it.next();
            listItemObject.setAddtime(listItemObject.getPasstime());
        }
        if (this.l == 1) {
            this.v.d(this.ab.getKey(), "essence");
            this.v.a(arrayList, null, false, this.ab.getKey(), "essence");
        } else if (this.l == 2) {
            this.v.d(this.ab.getKey(), "new");
            this.v.a(arrayList, null, false, this.ab.getKey(), "new");
        }
        an.a((List) arrayList, this.w, this.y, this.x);
        this.P.clear();
        for (ListItemObject listItemObject2 : this.q) {
            if (listItemObject2.getWid() != null) {
                this.P.add(listItemObject2);
            }
        }
        this.Q.clear();
        it = arrayList.iterator();
        while (it.hasNext()) {
            listItemObject2 = (ListItemObject) it.next();
            if (listItemObject2.getWid() != null) {
                this.Q.add(listItemObject2);
            }
        }
        this.q.clear();
        this.q.addAll(arrayList);
        this.N = false;
        this.X = false;
        this.Y = false;
        a(this.q, this.ab.god_topic_type);
        b(this.q);
        this.O = this.q;
    }

    private int A() {
        try {
            Long a = this.A.g().W.a();
            Long a2 = this.A.g().X.a();
            Long a3 = this.A.g().Z.a();
            Long a4 = this.A.g().Y.a();
            Long a5 = this.A.g().aa.a();
            aa.a("PostsFeaturesFragment", "share:" + a);
            aa.a("PostsFeaturesFragment", "invite:" + a2);
            aa.a("PostsFeaturesFragment", "Age:" + a3);
            aa.a("PostsFeaturesFragment", "Gender:" + a4);
            aa.a("PostsFeaturesFragment", "Eduction:" + a5);
            long currentTimeMillis = System.currentTimeMillis();
            List arrayList = new ArrayList();
            if (arrayList.size() <= 0) {
                return 0;
            }
            if (an.a(this.t.g)) {
                this.L = an.g(this.t);
                if (!TextUtils.isEmpty(this.L.getSex()) && (("f".equals(this.L.getSex()) || "m".equals(this.L.getSex())) && arrayList.indexOf(Integer.valueOf(6)) != -1)) {
                    arrayList.remove(arrayList.indexOf(Integer.valueOf(6)));
                }
                if (!(TextUtils.isEmpty(this.L.getBirthday()) || arrayList.indexOf(Integer.valueOf(5)) == -1)) {
                    arrayList.remove(arrayList.indexOf(Integer.valueOf(5)));
                }
                if (!(TextUtils.isEmpty(this.L.getDegree()) || arrayList.indexOf(Integer.valueOf(7)) == -1)) {
                    arrayList.remove(arrayList.indexOf(Integer.valueOf(7)));
                }
                if (!(TextUtils.isEmpty(this.L.getAge_group()) || arrayList.indexOf(Integer.valueOf(5)) == -1)) {
                    arrayList.remove(arrayList.indexOf(Integer.valueOf(5)));
                }
                if (!(f() || arrayList.indexOf(Integer.valueOf(4)) == -1)) {
                    arrayList.remove(arrayList.indexOf(Integer.valueOf(4)));
                }
            } else {
                if (arrayList.indexOf(Integer.valueOf(4)) != -1) {
                    arrayList.remove(arrayList.indexOf(Integer.valueOf(4)));
                }
                if (arrayList.indexOf(Integer.valueOf(5)) != -1) {
                    arrayList.remove(arrayList.indexOf(Integer.valueOf(5)));
                }
                if (arrayList.indexOf(Integer.valueOf(7)) != -1) {
                    arrayList.remove(arrayList.indexOf(Integer.valueOf(7)));
                }
                if (arrayList.indexOf(Integer.valueOf(6)) != -1) {
                    arrayList.remove(arrayList.indexOf(Integer.valueOf(6)));
                }
            }
            if (arrayList.size() <= 0) {
                return 0;
            }
            int indexOf = arrayList.indexOf(Integer.valueOf(ai.i(this.t, this.k)));
            indexOf = indexOf == -1 ? 0 : indexOf < arrayList.size() + -1 ? indexOf + 1 : 0;
            Integer num = (Integer) arrayList.get(indexOf);
            ai.b(this.t, this.k, num.intValue());
            if (num.intValue() == 2) {
                this.A.g().W.a(Long.valueOf(currentTimeMillis));
            } else if (num.intValue() == 6) {
                this.A.g().Y.a(Long.valueOf(currentTimeMillis));
            } else if (num.intValue() == 7) {
                this.A.g().aa.a(Long.valueOf(currentTimeMillis));
            } else if (num.intValue() == 5) {
                this.A.g().Z.a(Long.valueOf(currentTimeMillis));
            } else if (num.intValue() == 4) {
                this.A.g().X.a(Long.valueOf(currentTimeMillis));
            }
            return num.intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void b(ArrayList<ListItemObject> arrayList) {
        aa.b("PostsFeaturesFragment", "editMoreData()");
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ListItemObject listItemObject = (ListItemObject) it.next();
            listItemObject.setAddtime(listItemObject.getPasstime());
        }
        String str = "";
        if (this.l == 1) {
            str = "essence";
        } else if (this.l == 2) {
            str = "new";
        }
        if (this.l == 1 || this.l == 2) {
            this.v.a(arrayList, null, false, this.ab.getKey(), str);
        }
        an.a((List) arrayList, this.w, this.y, this.x);
        c((ArrayList) arrayList);
        this.q.addAll(arrayList);
        this.N = true;
        this.O = arrayList;
        b(this.q);
        a(this.aa, this.N);
    }

    private void c(ArrayList<ListItemObject> arrayList) {
        Collection arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ListItemObject listItemObject = (ListItemObject) it.next();
            if (this.q.contains(listItemObject)) {
                arrayList2.add(listItemObject);
                aa.a("PostsFeaturesFragment", "移除重复帖子：" + listItemObject.getImgUrl() + "," + listItemObject.getContent());
            }
        }
        if (arrayList2.size() > 0) {
            arrayList.removeAll(arrayList2);
        }
    }

    private void a(List<ListItemObject> list, String str) {
        Object obj = null;
        if (!this.as && list != null && !list.isEmpty() && !TextUtils.isEmpty(str)) {
            if (this.ac == null) {
                String format = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());
                SharedPreferences sharedPreferences = this.t.getSharedPreferences(this.t.getString(R.string.HISTORY_TODAY_HOT_POST_NAME), 0);
                if (sharedPreferences.getBoolean(format, false)) {
                    Object string = sharedPreferences.getString(this.t.getString(R.string.HISTORY_GOD_STRING), obj);
                    if (!TextUtils.isEmpty(string)) {
                        try {
                            obj = new JSONObject(string).optString(str);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (TextUtils.isEmpty(obj)) {
                        this.as = true;
                        return;
                    }
                    try {
                        JSONObject jSONObject = new JSONObject(obj);
                        if (jSONObject.has("last_year")) {
                            List a = com.budejie.www.j.a.a(this.t, jSONObject.optJSONArray("last_year"));
                            a.addAll(com.budejie.www.j.a.a(this.t, jSONObject.optJSONArray("recent")));
                            this.ac = new ListItemObject();
                            this.ac.setType("29");
                            this.ac.setmMultiHistoryData(a);
                        } else {
                            this.ac = com.budejie.www.j.a.a(jSONObject);
                        }
                        this.ac.setmHistoryTodayHotPost(true);
                        obj = OnlineConfigAgent.getInstance().getConfigParams(this.t, this.t.getString(R.string.HISTORY_TODAY_HOT_POST_UMENG_NAME));
                        if (!(TextUtils.isEmpty(obj) || "0".equals(obj))) {
                            this.ad = Integer.parseInt(obj);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        this.as = true;
                    }
                } else {
                    return;
                }
            }
            if (this.ac != null && list.size() > this.ad) {
                list.add(this.ad, this.ac);
            }
        }
    }

    private void b(List<ListItemObject> list) {
        int i = 0;
        if (list != null && !list.isEmpty()) {
            if (this.l == 1 || this.l == 2) {
                int i2;
                int size = list.size();
                if (this.D <= size) {
                    i2 = this.D - 1;
                } else {
                    i2 = 0;
                }
                if (this.B <= size) {
                    i = this.B - 1;
                }
                if (i2 > i) {
                    a((List) list, i);
                    b((List) list, i2);
                    return;
                }
                b((List) list, i2);
                a((List) list, i);
            }
        }
    }

    private void a(List<ListItemObject> list, int i) {
        if (i > 0 && list.size() > i && !this.X) {
            int i2 = this.B % 20;
            if (!this.N || this.O.size() >= i2) {
                aa.b("PostsFeaturesFragment", "addSystemOperation newPosition :" + i);
                int A = A();
                if (A != 0) {
                    this.G = new ListItemObject();
                    this.G.setNewItemType(A);
                    if (A == 3) {
                        q();
                    }
                    list.add(i, this.G);
                    if (this.N) {
                        this.O.add(i2 - 1, this.G);
                    }
                    this.X = true;
                    this.J = A;
                }
            }
        }
    }

    private void b(List<ListItemObject> list, int i) {
        if (i > 0 && list.size() > i && !this.Y) {
            int i2 = this.D % 20;
            if (!this.N || (!this.O.isEmpty() && this.O.size() >= i2)) {
                aa.b("PostsFeaturesFragment", "addEditorOperation oprPosition :" + i);
                this.U = this.V.a();
                if (i != 0 && this.U != null && this.U.size() > 0) {
                    this.H = new ListItemObject();
                    this.H.setNewItemType(8);
                    this.H.setOperation((OperationItem) this.U.get(this.W));
                    if (i <= list.size()) {
                        list.add(i, this.H);
                    }
                    if (this.N) {
                        this.O.add(i2 - 1, this.H);
                    }
                    this.Y = true;
                }
            }
        }
    }

    private void b(int i) {
        if (this.l == 3) {
            B();
        } else {
            String str = "";
            this.p = i - this.t.getBudejieSettings().n.a(this.a);
            if (this.p > Integer.parseInt(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ)) {
                this.p = Integer.parseInt(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
            }
            this.p = t();
            if (this.p > 0) {
                com.budejie.www.util.d.a(this.t, this.i, String.format(this.t.getString(R.string.f5_toast2), new Object[]{Integer.valueOf(this.p), Integer.valueOf(i)}));
            } else if (this.p == 0) {
                com.budejie.www.util.d.b(this.t, this.i, this.t.getString(R.string.not_new_data_click));
            }
        }
        this.t.getBudejieSettings().n.a(this.a, i);
    }

    private void B() {
        try {
            if (this.q != null && this.q.size() > 0) {
                ListItemObject listItemObject = (ListItemObject) this.q.get(0);
                if (listItemObject != null) {
                    Object passtime = listItemObject.getPasstime();
                    if (!TextUtils.isEmpty(passtime)) {
                        com.budejie.www.util.d.a(this.t, this.i, this.t.getString(R.string.f5_suiji) + passtime.substring(0, 10));
                        String str = "";
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private void C() {
        this.i.clearAnimation();
        this.i.setVisibility(8);
    }

    private void D() {
        if (this.t != null) {
            this.t.f();
        }
    }

    private void E() {
        if (this.t != null) {
            this.t.g();
        }
    }

    private String a(String str, String str2) {
        com.budejie.www.http.h hVar = new com.budejie.www.http.h(str);
        hVar.c(str2).a();
        return hVar.toString();
    }

    public boolean f() {
        if (this.M.d(this.t)) {
            return false;
        }
        return true;
    }

    public String a(String str) {
        if (this.U != null && this.U.size() > 0) {
            List list = ((OperationItem) this.U.get(this.W)).operationButtonList;
            if (list != null && list.size() > 0) {
                int size = list.size();
                int i = 0;
                while (i < size) {
                    OperationButton operationButton = (OperationButton) list.get(i);
                    if (operationButton != null && str.equals(((OperationButton) list.get(i)).jump_type)) {
                        return operationButton.value;
                    }
                    i++;
                }
            }
        }
        return null;
    }

    public void b(boolean z) {
        if (this.U != null && this.U.size() > 0) {
            if (this.W < this.U.size() - 1) {
                this.W++;
            } else {
                this.W = 0;
            }
            this.H.setOperation((OperationItem) this.U.get(this.W));
            if (z) {
                this.j.notifyDataSetChanged();
            }
        }
    }

    void g() {
        if (this.q != null && !this.q.isEmpty()) {
            for (int i = 0; i < this.q.size(); i++) {
                if (((ListItemObject) this.q.get(i)).getAdItem() != null) {
                    this.q.remove(i);
                }
            }
            this.j.a(this.q);
            this.aa = 0;
        }
    }

    void h() {
        a(this.aa, this.N);
    }

    void a(int i, boolean z) {
        if (!com.budejie.www.goddubbing.c.a.a(this.q)) {
            if (AdManager.isAdClosed()) {
                if (z) {
                    this.j.b(this.O);
                } else {
                    this.j.a(this.q);
                }
            } else if (z) {
                if (!"50".equals(this.ab.entrytype)) {
                    this.aa = this.u.a(this.q, this.O, i);
                }
                this.j.b(this.O);
            } else {
                if (!(!getUserVisibleHint() || com.budejie.www.goddubbing.c.a.a(this.q) || this.q.get(0) == null)) {
                    k.a(this.t, ((ListItemObject) this.q.get(0)).getVideouri());
                }
                if (!"50".equals(this.ab.entrytype)) {
                    this.aa = this.u.a(this.q, i);
                }
                this.j.a(this.q);
            }
        }
    }

    public void a(int i) {
        if (this.h != null) {
            this.h.setSelection(this.h.getHeaderViewsCount() + i);
        }
    }
}
