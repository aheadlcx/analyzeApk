package qsbk.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.BaseTabActivity.ILoadingState;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.HttpTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.CircleTopic;
import qsbk.app.model.CircleTopicBanner;
import qsbk.app.model.CircleTopicCategory;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.CircleTopicManager.OnTopicUpdate;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.RefreshTipView;
import qsbk.app.widget.SizeNotifierRelativeLayout;
import qsbk.app.widget.TipsHelper;
import qsbk.app.widget.VerticalImageSpan;
import qsbk.app.widget.ptr.AlphaAnimOffsetListener;

public class CircleTopicsFragment extends StatisticFragment implements ILoadingState, IArticleList, OnTopicUpdate, PtrListener {
    public static final int BANNER = 4;
    public static final int CREATE = 2;
    public static final int HOT_CATEGORY = 5;
    public static final int LABEL = 1;
    public static final int RECENT = 3;
    public static final int TOPIC = 0;
    private View A;
    private boolean B = false;
    private String C = "";
    private SimpleHttpTask D;
    private boolean E = false;
    private boolean F = false;
    private boolean G = false;
    private boolean H = false;
    private boolean I = true;
    private Animation J;
    private Animation K;
    private SimpleDraweeView L;
    private String M;
    private ArrayList<CircleTopicCategory> N = new ArrayList();
    private boolean O;
    SizeNotifierRelativeLayout a;
    private final ArrayList<CircleTopic> b = new ArrayList(0);
    private final ArrayList<CircleTopic> c = new ArrayList(0);
    private int d = 1;
    private int e = 1;
    private boolean f = true;
    private View g;
    private EditText h;
    private TextView i;
    private ImageView j;
    private PtrLayout k;
    private ListView l;
    private TipsHelper m;
    private RefreshTipView n;
    private List<CircleTopicBanner> o = new ArrayList();
    private CircleTopicsFragment$OtherItem p;
    private CircleTopicsFragment$OtherItem q;
    private CircleTopicsFragment$OtherItem r;
    private CircleTopicsFragment$OtherItem s;
    private CircleTopicsFragment$OtherItem t;
    private int u = 0;
    private ArrayList<Object> v = new ArrayList();
    private CircleTopicsFragment$CircleTopicAdapter w;
    private BaseAdapter x;
    private String y;
    private View z;

    public static CircleTopicsFragment newInstance(boolean z) {
        return newInstance(z, false);
    }

    public static CircleTopicsFragment newInstance(boolean z, boolean z2) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("just_choose_topic", z);
        bundle.putBoolean("is_no_clock", z2);
        CircleTopicsFragment circleTopicsFragment = new CircleTopicsFragment();
        circleTopicsFragment.setArguments(bundle);
        return circleTopicsFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.E = getArguments().getBoolean("just_choose_topic", false);
        this.F = getArguments().getBoolean("is_no_clock", false);
        CircleTopicManager.register(this);
    }

    public void onDetach() {
        super.onDetach();
        CircleTopicManager.unregister(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String str = null;
        View inflate = layoutInflater.inflate(R.layout.activity_circle_topic, viewGroup, false);
        this.a = (SizeNotifierRelativeLayout) inflate.findViewById(R.id.frame);
        this.l = (ListView) inflate.findViewById(R.id.listview);
        this.g = layoutInflater.inflate(R.layout.activity_circle_topic_search, this.l, false);
        this.h = (EditText) this.g.findViewById(R.id.search);
        this.i = (TextView) this.g.findViewById(R.id.hint);
        this.j = (ImageView) this.g.findViewById(R.id.clear_input);
        this.L = (SimpleDraweeView) this.g.findViewById(R.id.black_room);
        this.h.setText(this.C);
        this.i.setText(getHintString("搜索内容"));
        this.h.setOnFocusChangeListener(new ba(this));
        this.n = (RefreshTipView) inflate.findViewById(R.id.refresh_tip);
        this.n.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.shape_tip_dark : R.drawable.shape_tip);
        this.n.setOnClickListener(new bf(this));
        this.n.setVisibility(8);
        if (!this.E) {
            this.n.showDelay(RefreshTipView.FIRST_REFRESH_INTERVAL);
        }
        this.a.setSizeNotifierRelativeLayoutDelegate(new bh(this));
        this.h.addTextChangedListener(new bi(this));
        this.h.setOnEditorActionListener(new bj(this));
        this.j.setOnClickListener(new bk(this));
        FrescoImageloader.displayImage(this.L, null, (int) R.drawable.ic_black_room, R.drawable.ic_black_room, false, UIHelper.dip2px(this.L.getContext(), 4.0f));
        this.L.setOnClickListener(new bl(this));
        this.z = inflate.findViewById(R.id.login_layout);
        this.A = inflate.findViewById(R.id.progressBar);
        b();
        this.k = (PtrLayout) inflate.findViewById(R.id.ptr);
        this.m = new TipsHelper(inflate.findViewById(R.id.tips));
        this.l.addHeaderView(this.g);
        this.x = new CircleTopicsFragment$a(this, this.F);
        this.w = new CircleTopicsFragment$CircleTopicAdapter(this, this.v, getActivity(), this.F);
        this.l.setAdapter(this.w);
        this.k.setLoadMoreEnable(false);
        this.k.setPtrListener(this);
        if (QsbkApp.currentUser != null) {
            str = QsbkApp.currentUser.userId;
        }
        this.y = str;
        a();
        load();
        this.J = new AlphaAnimation(1.0f, 0.0f);
        this.J.setDuration(200);
        this.K = new AlphaAnimation(0.0f, 1.0f);
        this.K.setDuration(200);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && (parentFragment instanceof QiuyouCircleFragment)) {
            this.k.setOnScrollOffsetListener(new AlphaAnimOffsetListener(((QiuyouCircleFragment) parentFragment).getActivityNotification()));
        }
    }

    public void onResume() {
        super.onResume();
        Object obj = QsbkApp.currentUser == null ? null : QsbkApp.currentUser.userId;
        this.z.setVisibility(8);
        this.l.setVisibility(0);
        if (!TextUtils.equals(this.y, obj)) {
            this.y = obj;
            this.b.clear();
            this.w.notifyDataSetChanged();
            load();
        }
    }

    public void load() {
        if (this.C.length() == 0) {
            this.e = 1;
            a(1);
            if (!this.E) {
                e();
                d();
            }
            c();
            return;
        }
        this.d = 1;
        a(this.C, 1);
    }

    public void refresh() {
        if (this.l != null) {
            this.l.setSelection(0);
            this.k.refresh();
        }
    }

    public boolean hasNewArticle() {
        return false;
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z && this.k != null) {
            this.l.setSelection(0);
            this.k.autoRefresh();
        }
    }

    private void a() {
        this.A.setVisibility(0);
    }

    private void b() {
        this.A.setVisibility(8);
    }

    private void a(int i) {
        if (i == 1) {
            this.G = true;
            this.m.hide();
        }
        new SimpleHttpTask(String.format(this.E ? Constants.CIRCLE_TOPIC_RANK : Constants.CIRCLE_TOPIC_LAST, new Object[]{Integer.valueOf(i)}), new bm(this, i)).execute();
    }

    private void c() {
        CircleTopicManager.getInstance().loadLRUTopics(new bn(this));
    }

    private void d() {
        new HttpTask(null, Constants.CIRCLE_TOPIC_BANNER, new bb(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void e() {
        new HttpTask(null, Constants.CIRCLE_TOPIC_CATEGORY_HOT, new bc(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private CircleTopicsFragment$OtherItem a(String str) {
        CircleTopicsFragment$OtherItem circleTopicsFragment$OtherItem = new CircleTopicsFragment$OtherItem();
        circleTopicsFragment$OtherItem.type = 1;
        circleTopicsFragment$OtherItem.msg = str;
        return circleTopicsFragment$OtherItem;
    }

    public CharSequence getHintString(String str) {
        CharSequence spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append("搜");
        spannableStringBuilder.setSpan(new VerticalImageSpan(getResources().getDrawable(R.drawable.icon_search)), 0, spannableStringBuilder.length(), 0);
        spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str);
        return spannableStringBuilder;
    }

    private void a(String str, int i) {
        String format;
        this.C = str;
        if (this.D != null) {
            this.D.cancel(true);
            this.D = null;
        }
        try {
            format = String.format(Constants.CIRCLE_TOPIC_SEARCH, new Object[]{URLEncoder.encode(str, "UTF-8"), Integer.valueOf(i)});
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            format = String.format(Constants.CIRCLE_TOPIC_SEARCH, new Object[]{str, Integer.valueOf(i)});
        }
        if (i == 1) {
            this.H = true;
        }
        this.D = new SimpleHttpTask(format, new bd(this, i));
        this.D.execute();
    }

    private void f() {
        this.v.clear();
        if (this.B) {
            int i;
            String format = String.format("#%s#", new Object[]{this.C});
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                if (format.equals(((CircleTopic) it.next()).content)) {
                    i = 1;
                    break;
                }
            }
            i = 0;
            if (i == 0) {
                if (this.r == null) {
                    this.r = a("无匹配话题");
                    this.s = new CircleTopicsFragment$OtherItem();
                    this.s.type = 2;
                }
                this.v.add(this.r);
                this.s.msg = format;
                this.v.add(this.s);
            }
            if (this.c.size() > 0) {
                if (this.t == null) {
                    this.t = a(null);
                }
                this.t.msg = String.format("搜索结果 (%d)", new Object[]{Integer.valueOf(Math.max(this.c.size(), this.u))});
                this.v.add(this.t);
                this.v.addAll(this.c);
            }
        } else {
            if (this.o != null && this.o.size() > 0) {
                this.v.add(0, this.o);
            }
            if (CircleTopicManager.getInstance().getLruTopics() != null && CircleTopicManager.getInstance().getLruTopics().size() > 0) {
                if (this.p == null) {
                    this.p = a("最近使用");
                }
                this.v.add(CircleTopicManager.getInstance().getLruTopics());
            }
            if (this.N != null && this.N.size() > 0) {
                this.v.add(this.N);
            }
            if (this.q == null) {
                this.q = a("最近更新");
            }
            this.v.add(this.q);
            CircleTopicsFragment$CircleTopicAdapter.a(this.w, this.v.size());
            this.v.addAll(this.b);
        }
        this.x.notifyDataSetChanged();
        this.w.notifyDataSetChanged();
    }

    public void onRefresh() {
        if (this.C.length() == 0) {
            this.e = 1;
            a(1);
            c();
            d();
            e();
        } else {
            this.d = 1;
            a(this.C, 1);
        }
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && (parentFragment instanceof QiuyouCircleFragment)) {
            ((QiuyouCircleFragment) parentFragment).showSmallTipsOnMainActitivty(false);
            ((QiuyouCircleFragment) parentFragment).setHeadVisible();
        }
    }

    public void onLoadMore() {
        if (this.C.length() == 0) {
            a(this.e + 1);
        } else {
            a(this.C, this.d + 1);
        }
    }

    private void a(CircleTopicsFragment$OtherItem circleTopicsFragment$OtherItem) {
        String str = QsbkApp.currentUser.userId;
        new SimpleHttpTask(String.format(Constants.PERSONAL_SCORE, new Object[]{str}), new be(this, getActivity(), "加载中...", circleTopicsFragment$OtherItem)).execute();
    }

    public boolean isLoading() {
        return this.G || this.H;
    }

    public void onTopicUpdate(Collection<CircleTopic> collection) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            CircleTopic circleTopic = (CircleTopic) it.next();
            for (CircleTopic circleTopic2 : collection) {
                if (circleTopic.id.equals(circleTopic2.id)) {
                    circleTopic.updateWith(circleTopic2);
                    break;
                }
            }
        }
        it = this.c.iterator();
        while (it.hasNext()) {
            circleTopic = (CircleTopic) it.next();
            for (CircleTopic circleTopic22 : collection) {
                if (circleTopic.id.equals(circleTopic22.id)) {
                    circleTopic.updateWith(circleTopic22);
                    break;
                }
            }
        }
        if (this.w == null) {
            return;
        }
        if (this.I) {
            f();
        } else {
            this.w.notifyDataSetChanged();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }

    public void scrollToTop() {
        if (this.l != null) {
            this.l.setSelection(0);
        }
    }
}
