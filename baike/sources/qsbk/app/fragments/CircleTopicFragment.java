package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collection;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.BaseTabActivity.ILoadingState;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.adapter.QiuYouCircleAdapter;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.CircleTopic;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.share.ShareUtils;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;
import qsbk.app.utils.CircleArticleBus;
import qsbk.app.utils.CircleArticleBus.OnArticleUpdateListener;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.CircleTopicManager.OnTopicUpdate;
import qsbk.app.utils.ReadCircle;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.ArticleMoreOperationbar;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.TipsHelper;

public class CircleTopicFragment extends BaseFragment implements ILoadingState, IArticleList, ShareUtils$OnCircleShareStartListener, OnArticleUpdateListener, OnTopicUpdate, PtrListener {
    public static final int TYPE_ALL = 0;
    public static final int TYPE_RECENT = 1;
    String a;
    private int b = 1;
    private boolean c = true;
    private PtrLayout d;
    private ListView e;
    private TipsHelper f;
    private ArrayList<Object> g = new ArrayList();
    private QiuYouCircleAdapter h;
    private final BroadcastReceiver i = new am(this);
    private boolean j = false;
    private boolean k = true;
    private CircleTopic l;
    private int m;
    private int n;
    private String o;
    private boolean p;

    public static CircleTopicFragment newInstance(String str, int i, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("topic_id", str);
        bundle.putInt("type", i);
        bundle.putString("circle_article_id", str2);
        CircleTopicFragment circleTopicFragment = new CircleTopicFragment();
        circleTopicFragment.setArguments(bundle);
        return circleTopicFragment;
    }

    public static CircleTopicFragment newInstance(String str, int i) {
        return newInstance(str, i, null);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.a = arguments.getString("topic_id");
            this.n = arguments.getInt("type");
            this.o = arguments.getString("circle_article_id");
        }
        CircleTopicManager.register(this);
        CircleArticleBus.register(this);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.i, new IntentFilter(MyInfoActivity.CHANGE_REMARK));
    }

    public void onDestroy() {
        CircleTopicManager.unregister(this);
        CircleArticleBus.unregister(this);
        if (this.i != null) {
            LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.i);
        }
        super.onDestroy();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_circle_topic, viewGroup, false);
        this.e = (ListView) inflate.findViewById(R.id.id_stickynavlayout_innerscrollview);
        b();
        this.d = (PtrLayout) inflate.findViewById(R.id.ptr);
        this.f = new TipsHelper(inflate.findViewById(R.id.tips));
        this.h = new QiuYouCircleAdapter(this.g, getActivity(), this);
        this.e.setAdapter(this.h);
        this.d.setLoadMoreEnable(false);
        this.d.setPtrListener(this);
        if (UIHelper.isNightTheme()) {
            this.e.setDivider(new ColorDrawable(-16777216));
            this.e.setDividerHeight((int) (getResources().getDisplayMetrics().density / 2.0f));
        } else {
            this.e.setDivider(null);
            this.e.setDividerHeight(0);
        }
        ReadCircle.trackListView(this.d);
        a();
        load();
        return inflate;
    }

    public void onResume() {
        super.onResume();
    }

    public void load() {
        this.b = 1;
        a(1);
    }

    public void refresh() {
        if (this.e != null) {
            this.e.setSelection(0);
            this.d.refresh();
        }
    }

    public boolean hasNewArticle() {
        return false;
    }

    private void a() {
    }

    private void b() {
    }

    private void a(int i) {
        if (i == 1) {
            this.j = true;
            this.f.hide();
        }
        LocationHelper.loadCache();
        String str = this.n == 0 ? Constants.CIRCLE_TOPIC_LIST_ALL : Constants.CIRCLE_TOPIC_LIST_RECENT;
        if (!TextUtils.isEmpty(this.o)) {
            str = str + "&article_id=" + this.o;
        }
        new SimpleHttpTask(String.format(str, new Object[]{this.a, Integer.valueOf(this.b), Double.valueOf(LocationHelper.getLatitude()), Double.valueOf(LocationHelper.getLongitude())}), new an(this, i)).execute();
    }

    public void onRefresh() {
        this.b = 1;
        a(1);
    }

    public void onLoadMore() {
        a(this.b + 1);
    }

    public boolean isLoading() {
        return this.j;
    }

    public void onTopicUpdate(Collection<CircleTopic> collection) {
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }

    public void scrollToTop() {
        if (this.e != null) {
            this.e.setSelection(0);
        }
    }

    public void onCircleShareStart(CircleArticle circleArticle) {
        ShareUtils.openShareDialog(this, 1, circleArticle, this.l);
    }

    public void onCircleShareStart(CircleArticle circleArticle, String str, View view) {
        ShareUtils.openShareDialog(this, 1, circleArticle, this.l, str);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && intent != null) {
            CircleArticle circleArticle = (CircleArticle) intent.getSerializableExtra("circleArticle");
            if (circleArticle != null) {
                ArticleMoreOperationbar.handleShare(i2, (Fragment) this, circleArticle);
            } else if (this.l != null) {
                ShareUtils.doShare(i2, getActivity(), this, this.l, null, true);
            }
        }
    }

    public void onArticleCreate(CircleArticle circleArticle) {
        boolean z = false;
        if (circleArticle != null && circleArticle.topic != null && circleArticle.topic.equals(this.l)) {
            Object obj;
            int i;
            this.f.hide();
            for (int i2 = 0; i2 < this.g.size(); i2++) {
                obj = this.g.get(i2);
                if ((obj instanceof CircleArticle) && !((CircleArticle) obj).isTop) {
                    i = i2;
                    break;
                }
            }
            i = -1;
            if (i == -1) {
                i = this.g.size();
            }
            this.g.add(i, circleArticle);
            if (this.g.size() > 0) {
                obj = this.g.get(0);
                if ((obj instanceof CircleArticle) && ((CircleArticle) obj).isTop) {
                    z = true;
                }
            }
            this.p = z;
            this.h.notifyDataSetChanged();
        }
    }

    public void onArticleUpdate(CircleArticle circleArticle) {
        if (this.g != null && this.g.size() != 0) {
            for (int i = 0; i < this.g.size(); i++) {
                Object obj = this.g.get(i);
                if (obj instanceof CircleArticle) {
                    CircleArticle circleArticle2 = (CircleArticle) obj;
                    if (TextUtils.equals(circleArticle2.id, circleArticle.id)) {
                        circleArticle2.updateWith(circleArticle);
                        break;
                    }
                }
            }
            if (this.h != null) {
                this.h.notifyDataSetChanged();
            }
        }
    }

    public void onArticleDelete(CircleArticle circleArticle) {
        if (this.g != null && this.g.size() != 0 && this.g.contains(circleArticle)) {
            this.g.remove(circleArticle);
            if (this.h != null) {
                this.h.notifyDataSetChanged();
            }
        }
    }
}
