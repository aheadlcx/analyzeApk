package com.budejie.www.activity.posts;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bdj.picture.edit.util.c;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.view.StaggeredManager;
import com.budejie.www.activity.view.TabWidget;
import com.budejie.www.adapter.e;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.TopNavigation;
import com.budejie.www.c.d;
import com.budejie.www.c.g;
import com.budejie.www.c.h;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.i;
import com.budejie.www.http.j;
import com.budejie.www.util.aa;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.widget.xrecyclerview.XRecyclerView;
import com.budejie.www.widget.xrecyclerview.XRecyclerView$d;
import com.umeng.onlineconfig.OnlineConfigAgent;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.tsz.afinal.a.a;

public class b extends Fragment {
    private boolean A = true;
    private boolean B;
    private long C = System.currentTimeMillis();
    private long D;
    private boolean E = false;
    private Observer<ArrayList<ListItemObject>> F;
    private String[] G;
    private Observer<ArrayList<ListItemObject>> H;
    private String[] I;
    private ArrayList<ListItemObject> J;
    private TabWidget K;
    private String L;
    private int M;
    private XRecyclerView$d N = new b$1(this);
    private OnClickListener O = new b$6(this);
    private a<String> P = new b$7(this);
    private a<String> Q = new b$12(this);
    private a<String> R = new b$2(this);
    private a<String> S = new b$4(this);
    public String a;
    public String b;
    public SharedPreferences c;
    private View d;
    private TopNavigation e;
    private XRecyclerView f;
    private PostsActivity g;
    private List<ListItemObject> h;
    private List<ListItemObject> i;
    private int j;
    private List<ListItemObject> k;
    private List<ListItemObject> l;
    private List<ListItemObject> m;
    private com.budejie.www.c.b n;
    private h o;
    private g p;
    private d q;
    private String r;
    private e s;
    private TextView t;
    private LinearLayout u;
    private int v;
    private int w;
    private long x;
    private BudejieApplication y;
    private String z;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.d != null) {
            if (this.d.getParent() != null) {
                ((ViewGroup) this.d.getParent()).removeView(this.d);
            }
            return this.d;
        }
        this.d = layoutInflater.inflate(R.layout.fragment_staggered_list, viewGroup, false);
        e();
        return this.d;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (this.g == null) {
            this.g = (PostsActivity) context;
        }
        if (this.y == null) {
            this.y = (BudejieApplication) this.g.getApplication();
        }
        this.c = this.g.getSharedPreferences("weiboprefer", 0);
    }

    private void e() {
        if (this.g != null) {
            this.i = new ArrayList();
            this.h = new ArrayList();
            f();
            this.n = new com.budejie.www.c.b(this.g);
            this.q = new d(this.g);
            this.p = new g(this.g);
            this.q = new d(this.g);
            this.o = new h(this.g);
            this.k = new ArrayList();
            this.l = new ArrayList();
            this.m = new ArrayList();
            if (c.a(this.c.getLong("recsys_np_save_date", 0))) {
                this.D = this.c.getLong("recsys_np_" + this.e.getKey(), 0);
            } else {
                this.D = 0;
            }
            this.b = this.z + this.j;
            this.a = this.b + "position";
            this.M = ai.a(this.g);
            l();
        }
    }

    private void f() {
        Bundle arguments = getArguments();
        this.e = (TopNavigation) arguments.getSerializable("TOP_NAVIGATION_KEY");
        this.j = arguments.getInt("page_type");
        this.z = arguments.getString("post_type");
        this.L = arguments.getString("TOP_NUM_KEY");
    }

    public XRecyclerView a() {
        return this.f;
    }

    public void onPause() {
        super.onPause();
        if (this.t != null) {
            this.t.clearAnimation();
            this.t.setVisibility(8);
        }
        if (!(this.f == null || this.s == null)) {
            g();
        }
        if (this.K != null) {
            this.K.c();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.K != null) {
            this.K.b();
        }
    }

    private void g() {
        if (this.g.getBudejieSettings().a.a().booleanValue()) {
            int firstVisiblePosition = this.f.getFirstVisiblePosition();
            if (!com.budejie.www.goddubbing.c.a.a(this.k) && firstVisiblePosition != -1) {
                if (firstVisiblePosition >= 0 && firstVisiblePosition < this.k.size()) {
                    ListItemObject listItemObject = (ListItemObject) this.k.get(firstVisiblePosition);
                    if (listItemObject != null) {
                        this.r = listItemObject.getWid();
                    }
                }
                this.g.getBudejieSettings().k.a(this.a, this.r);
            }
        }
    }

    private void h() {
        this.f = (XRecyclerView) this.d.findViewById(R.id.recycler_view);
        this.t = (TextView) this.d.findViewById(R.id.headToast);
        this.u = (LinearLayout) this.d.findViewById(R.id.hotToast);
        this.t.setOnClickListener(this.O);
        LayoutManager staggeredManager = new StaggeredManager(2, 1);
        staggeredManager.setGapStrategy(0);
        this.f.setLayoutManager(staggeredManager);
        this.f.addItemDecoration(new com.budejie.www.widget.xrecyclerview.b(an.b(this.g, 4.6f), an.b(this.g, 10.0f)));
        this.f.setLastRefreshTime(this.C);
        if (this.s == null) {
            this.s = new e(this.g, this.k);
        }
        this.K = new TabWidget(this.g, this.j, this.L);
        this.f.a(this.K);
        this.s.a(this.f.getHeaderViewCount());
        this.f.setAdapter(this.s);
        this.f.setLoadingMoreEnabled(true);
        this.f.setOnScrollDirectionListener(this.N);
        this.f.setLoadingListener(new b$5(this));
    }

    public void b() {
        if (this.K != null) {
            this.K.a();
        }
    }

    private void a(String str) {
        an.a(this.g, str, -1).show();
    }

    public void c() {
        if (!this.E) {
            this.E = true;
            BudejieApplication.a.a(RequstMethod.GET, a(this.e.url, String.valueOf(this.x)), new j(this.g), this.P);
        }
    }

    private void a(ArrayList<ListItemObject> arrayList) {
        String str = "";
        if (this.j == 1) {
            str = "essence";
        } else if (this.j == 2) {
            str = "new";
        }
        if (this.j == 1 || this.j == 2) {
            this.n.a(arrayList, null, false, this.e.getKey(), str);
        }
        an.a((List) arrayList, this.o, this.q, this.p);
    }

    private void b(ArrayList<ListItemObject> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ListItemObject listItemObject = (ListItemObject) it.next();
            listItemObject.setAddtime(listItemObject.getPasstime());
        }
        c((ArrayList) arrayList);
    }

    private void c(ArrayList<ListItemObject> arrayList) {
        Collection arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ListItemObject listItemObject = (ListItemObject) it.next();
            if (this.k.contains(listItemObject)) {
                arrayList2.add(listItemObject);
            }
        }
        if (arrayList2.size() > 0) {
            arrayList.removeAll(arrayList2);
        }
    }

    private String a(String str, String str2) {
        com.budejie.www.http.h hVar = new com.budejie.www.http.h(str);
        hVar.c(str2).a();
        return hVar.toString();
    }

    private void i() {
        j();
        k();
    }

    private void j() {
        if (this.g != null) {
            this.g.f();
        }
    }

    private void k() {
        BudejieApplication.a.a(RequstMethod.GET, a(this.e.url, "0"), new j(this.g), this.Q);
        i.a(this.g.getString(R.string.track_event_load_first), this.g.getString(R.string.track_event_load_first));
    }

    private void l() {
        if (m()) {
            h();
            b((int) R.color.new_main_background_color);
            this.f.c();
            return;
        }
        this.r = this.g.getBudejieSettings().k.a(this.a);
        Observable.just(Integer.valueOf(1)).map(new b$9(this)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new b$8(this));
    }

    private void b(int i) {
        if (isAdded() && this.M == 0) {
            try {
                this.f.setBackgroundColor(getResources().getColor(i));
            } catch (Exception e) {
                if (!TextUtils.isEmpty(e.getMessage())) {
                    aa.e("StaggeredListFragment", e.getMessage());
                }
            }
        }
    }

    private boolean m() {
        boolean z = true;
        if (this.j != 2 && this.j != 1) {
            return false;
        }
        this.C = this.c.getLong("last_refresh_" + this.e.getKey(), System.currentTimeMillis());
        if (((double) ((System.currentTimeMillis() - this.C) / 60000)) < n() * 60.0d) {
            z = false;
        }
        return z;
    }

    private double n() {
        Object obj = "";
        Object obj2 = "";
        try {
            if (this.j == 1) {
                obj2 = OnlineConfigAgent.getInstance().getConfigParams(this.g, "精华列表自动刷新时间间隔");
            } else if (this.j == 2) {
                obj2 = OnlineConfigAgent.getInstance().getConfigParams(this.g, "最新列表自动刷新时间间隔");
            }
            if (!TextUtils.isEmpty(obj2)) {
                obj = obj2.split(com.alipay.sdk.util.h.b)[0];
            }
            if (TextUtils.isEmpty(obj)) {
                return 48.0d;
            }
            return Double.parseDouble(obj);
        } catch (Exception e) {
            if (TextUtils.isEmpty(e.getMessage())) {
                return 48.0d;
            }
            aa.e("StaggeredListFragment", e.getMessage());
            return 48.0d;
        }
    }

    private void o() {
        if (!com.budejie.www.goddubbing.c.a.a(this.k) && !TextUtils.isEmpty(this.r)) {
            an.a(this.k, this.o, this.q, this.p);
            this.f.setPullRefreshEnabled(true);
            if (this.g.getBudejieSettings().a.a().booleanValue()) {
                Observable.just(Integer.valueOf(1)).map(new b$11(this)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new b$10(this));
            }
        }
    }

    private void a(List<ListItemObject> list) {
        if (!com.budejie.www.goddubbing.c.a.a(this.J)) {
            if (com.budejie.www.goddubbing.c.a.a(list)) {
                this.k.clear();
                this.k.addAll(this.J);
                this.s.notifyDataSetChanged();
                return;
            }
            com.budejie.www.util.d.b(this.g, this.t, String.format(this.g.getString(R.string.recommend_tip_click), new Object[]{String.valueOf(list.size())}));
            this.k.clear();
            list.addAll(this.J);
            this.k.addAll(list);
            String string = this.g.getString(TextUtils.isEmpty(((ListItemObject) list.get(0)).opends) ? R.string.track_event_load_first_bs : R.string.track_event_load_first_bfd);
            i.a(string, string);
            this.s.notifyDataSetChanged();
        }
    }

    private List<ListItemObject> p() {
        if (com.budejie.www.goddubbing.c.a.a(this.m) || this.m.size() < 10) {
            return null;
        }
        List<ListItemObject> arrayList = new ArrayList();
        Iterator it = this.m.iterator();
        while (it.hasNext()) {
            arrayList.add((ListItemObject) it.next());
            it.remove();
            if (arrayList.size() == 10) {
                break;
            }
        }
        return arrayList;
    }

    private void q() {
        this.f.a(getString(R.string.xlistview_footer_hint_suiji), new b$3(this));
    }

    private void r() {
        this.c.edit().putLong("recsys_np_" + this.e.getKey(), this.D).apply();
        this.c.edit().putLong("recsys_np_save_date", System.currentTimeMillis()).apply();
    }

    private void d(ArrayList<ListItemObject> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ListItemObject listItemObject = (ListItemObject) it.next();
            listItemObject.setAddtime(listItemObject.getPasstime());
        }
        this.h.clear();
        for (ListItemObject listItemObject2 : this.k) {
            if (!(listItemObject2 == null || listItemObject2.getWid() == null)) {
                this.h.add(listItemObject2);
            }
        }
        this.i.clear();
        it = arrayList.iterator();
        while (it.hasNext()) {
            listItemObject2 = (ListItemObject) it.next();
            if (!(listItemObject2 == null || listItemObject2.getWid() == null)) {
                this.i.add(listItemObject2);
            }
        }
        this.v = w();
    }

    private void e(ArrayList<ListItemObject> arrayList) {
        an.a((List) arrayList, this.o, this.q, this.p);
        if (this.j == 1) {
            this.n.d(this.e.getKey(), "essence");
            this.n.a(arrayList, null, false, this.e.getKey(), "essence");
        } else if (this.j == 2) {
            this.n.d(this.e.getKey(), "new");
            this.n.a(arrayList, null, false, this.e.getKey(), "new");
        }
    }

    private void s() {
        this.c.edit().putLong("np_" + this.e.getKey(), this.x).apply();
    }

    private void t() {
        if (this.j == 3) {
            u();
        } else if (this.v > 0) {
            com.budejie.www.util.d.a(this.g, this.t, String.format(this.g.getString(R.string.f5_toast2), new Object[]{Integer.valueOf(this.v), Integer.valueOf(this.w)}));
        } else if (this.v == 0) {
            com.budejie.www.util.d.b(this.g, this.t, this.g.getString(R.string.not_new_data_click));
        }
    }

    private void u() {
        if (!com.budejie.www.goddubbing.c.a.a(this.k)) {
            ListItemObject listItemObject = (ListItemObject) this.k.get(0);
            if (listItemObject != null) {
                String passtime = listItemObject.getPasstime();
                if (!TextUtils.isEmpty(passtime) && passtime.length() >= 10) {
                    com.budejie.www.util.d.a(this.g, this.t, this.g.getString(R.string.f5_suiji) + passtime.substring(0, 10));
                }
            }
        }
    }

    private void v() {
        if (this.g != null) {
            this.g.g();
        }
    }

    private int w() {
        int i = 0;
        for (ListItemObject listItemObject : this.i) {
            int i2;
            if (listItemObject == null || listItemObject.getWid() == null || this.h.contains(listItemObject)) {
                i2 = i;
            } else {
                i2 = i + 1;
            }
            i = i2;
        }
        return i;
    }

    public e d() {
        return this.s;
    }

    public ArrayList<ListItemObject> a(boolean z) {
        if (z) {
            return (ArrayList) this.k;
        }
        return (ArrayList) this.l;
    }

    public void a(int i) {
        if (this.f != null) {
            this.f.scrollToPosition(this.f.getHeaderViewCount() + i);
        }
    }
}
