package qsbk.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ListView;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.BaseTabActivity.ILoadingState;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.CircleTopicManager.OnTopicUpdate;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.TipsHelper;

public class TopicsTopFragment extends BaseFragment implements ILoadingState, IArticleList, OnTopicUpdate, PtrListener {
    public static final int CREATE = 2;
    public static final int LABEL = 1;
    public static final int TOPIC = 0;
    private final ArrayList<CircleTopic> a = new ArrayList(0);
    private final ArrayList<CircleTopic> b = new ArrayList(0);
    private int c = 1;
    private int d = 1;
    private boolean e = true;
    private PtrLayout f;
    private ListView g;
    private TipsHelper h;
    private TopicsTopFragment$OtherItem i;
    private TopicsTopFragment$OtherItem j;
    private TopicsTopFragment$OtherItem k;
    private TopicsTopFragment$OtherItem l;
    private int m = 0;
    private ArrayList<Object> n = new ArrayList();
    private TopicsTopFragment$CircleTopicAdapter o;
    private String p;
    private View q;
    private boolean r = false;
    private String s = "";
    private SimpleHttpTask t;
    private boolean u = false;
    private boolean v = false;
    private boolean w = false;
    private boolean x = true;
    private Animation y;
    private Animation z;

    public static TopicsTopFragment newInstance(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("just_choose_topic", z);
        TopicsTopFragment topicsTopFragment = new TopicsTopFragment();
        topicsTopFragment.setArguments(bundle);
        return topicsTopFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.u = getArguments().getBoolean("just_choose_topic", false);
        CircleTopicManager.register(this);
    }

    public void onDetach() {
        super.onDetach();
        CircleTopicManager.unregister(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.circle_topic_rank, viewGroup, false);
        this.g = (ListView) inflate.findViewById(R.id.listview);
        this.q = inflate.findViewById(R.id.progressBar);
        b();
        this.f = (PtrLayout) inflate.findViewById(R.id.ptr);
        this.h = new TipsHelper(inflate.findViewById(R.id.tips));
        this.o = new TopicsTopFragment$CircleTopicAdapter(this, this.n, getActivity(), this.u);
        this.g.setAdapter(this.o);
        this.f.setLoadMoreEnable(false);
        this.f.setPtrListener(this);
        this.p = QsbkApp.currentUser == null ? null : QsbkApp.currentUser.userId;
        a();
        load();
        this.y = new AlphaAnimation(1.0f, 0.0f);
        this.y.setDuration(200);
        this.z = new AlphaAnimation(0.0f, 1.0f);
        this.z.setDuration(200);
        return inflate;
    }

    public void onResume() {
        super.onResume();
    }

    public void load() {
        if (this.s.length() == 0) {
            this.d = 1;
            a(1);
            return;
        }
        this.c = 1;
        a(this.s, 1);
    }

    public void refresh() {
        if (this.g != null) {
            this.g.setSelection(0);
            this.f.refresh();
        }
    }

    public boolean hasNewArticle() {
        return false;
    }

    private void a() {
        this.q.setVisibility(0);
    }

    private void b() {
        this.q.setVisibility(8);
    }

    private void a(int i) {
        if (i == 1) {
            this.v = true;
            this.h.hide();
        }
        new SimpleHttpTask(String.format(Constants.CIRCLE_TOPIC_TOP, new Object[]{Integer.valueOf(i)}), new ld(this, i)).execute();
    }

    private TopicsTopFragment$OtherItem a(String str) {
        TopicsTopFragment$OtherItem topicsTopFragment$OtherItem = new TopicsTopFragment$OtherItem();
        topicsTopFragment$OtherItem.type = 1;
        topicsTopFragment$OtherItem.msg = str;
        return topicsTopFragment$OtherItem;
    }

    private void a(String str, int i) {
        String format;
        this.s = str;
        if (this.t != null) {
            this.t.cancel(true);
            this.t = null;
        }
        try {
            format = String.format(Constants.CIRCLE_TOPIC_SEARCH, new Object[]{URLEncoder.encode(str, "UTF-8"), Integer.valueOf(i)});
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            format = String.format(Constants.CIRCLE_TOPIC_SEARCH, new Object[]{str, Integer.valueOf(i)});
        }
        if (i == 1) {
            this.w = true;
        }
        this.t = new SimpleHttpTask(format, new le(this, i));
        this.t.execute();
    }

    private void c() {
        this.n.clear();
        if (this.r) {
            int i;
            String format = String.format("#%s#", new Object[]{this.s});
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                if (format.equals(((CircleTopic) it.next()).content)) {
                    i = 1;
                    break;
                }
            }
            i = 0;
            if (i == 0) {
                if (this.j == null) {
                    this.j = a("无匹配话题");
                    this.k = new TopicsTopFragment$OtherItem();
                    this.k.type = 2;
                }
                this.n.add(this.j);
                this.k.msg = format;
                this.n.add(this.k);
            }
            if (this.b.size() > 0) {
                if (this.l == null) {
                    this.l = a(null);
                }
                this.l.msg = String.format("搜索结果 (%d)", new Object[]{Integer.valueOf(Math.max(this.b.size(), this.m))});
                this.n.add(this.l);
                this.n.addAll(this.b);
            }
        } else {
            if (this.i == null) {
                this.i = a("天梯（根据最近7天内活跃人数排名）");
            }
            this.n.add(this.i);
            TopicsTopFragment$CircleTopicAdapter.a(this.o, this.n.size());
            this.n.addAll(this.a);
        }
        this.o.notifyDataSetChanged();
    }

    public void onRefresh() {
        if (this.s.length() == 0) {
            this.d = 1;
            a(1);
        } else {
            this.c = 1;
            a(this.s, 1);
        }
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && (parentFragment instanceof QiuyouCircleFragment)) {
            ((QiuyouCircleFragment) parentFragment).showSmallTipsOnMainActitivty(false);
            ((QiuyouCircleFragment) parentFragment).setHeadVisible();
        }
    }

    public void onLoadMore() {
        if (this.s.length() == 0) {
            a(this.d + 1);
        } else {
            a(this.s, this.c + 1);
        }
    }

    private void a(TopicsTopFragment$OtherItem topicsTopFragment$OtherItem) {
        String str = QsbkApp.currentUser.userId;
        new SimpleHttpTask(String.format(Constants.PERSONAL_SCORE, new Object[]{str}), new lf(this, getActivity(), "加载中...", topicsTopFragment$OtherItem)).execute();
    }

    public boolean isLoading() {
        return this.v || this.w;
    }

    public void onTopicUpdate(Collection<CircleTopic> collection) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            CircleTopic circleTopic = (CircleTopic) it.next();
            for (CircleTopic circleTopic2 : collection) {
                if (circleTopic.id.equals(circleTopic2.id)) {
                    circleTopic.updateWith(circleTopic2);
                    break;
                }
            }
        }
        it = this.b.iterator();
        while (it.hasNext()) {
            circleTopic = (CircleTopic) it.next();
            for (CircleTopic circleTopic22 : collection) {
                if (circleTopic.id.equals(circleTopic22.id)) {
                    circleTopic.updateWith(circleTopic22);
                    break;
                }
            }
        }
        if (this.x) {
            c();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }

    public void scrollToTop() {
        if (this.g != null) {
            this.g.setSelection(0);
        }
    }
}
