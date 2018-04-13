package qsbk.app.fragments;

import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.QiushiTopicListActivity;
import qsbk.app.adapter.QiushiTopicAdapter;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.QiushiTopic;
import qsbk.app.utils.QiushiTopicBus;
import qsbk.app.utils.QiushiTopicBus.QiushiTopicUpdateListener;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.QiushiTopicCell;
import qsbk.app.widget.QiushiTopicCell.OnSubcribeListener;

public class QiushiTopicListFragment extends Fragment implements OnScrollListener, QiushiTopicUpdateListener, PtrListener, OnSubcribeListener {
    public static final String TAG = QiushiTopicListFragment.class.getSimpleName();
    SimpleHttpTask a;
    BaseAdapter b;
    ArrayList<Object> c = new ArrayList();
    private boolean d;
    private boolean e;
    private PtrLayout f;
    private ListView g;
    private View h;
    private boolean i;
    private int j = 1;
    private int k;

    public static QiushiTopicListFragment newInstance(String str, boolean z, boolean z2) {
        QiushiTopicListFragment qiushiTopicListFragment = new QiushiTopicListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("server_api", str);
        bundle.putBoolean("show_subcribe", z);
        bundle.putBoolean("just_select", z2);
        qiushiTopicListFragment.setArguments(bundle);
        return qiushiTopicListFragment;
    }

    public static QiushiTopicListFragment newInstance(int i) {
        QiushiTopicListFragment qiushiTopicListFragment = new QiushiTopicListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(QiushiTopicListActivity.MODE, i);
        qiushiTopicListFragment.setArguments(bundle);
        return qiushiTopicListFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a();
    }

    private void a() {
        if (getArguments() != null) {
            this.k = getArguments().getInt(QiushiTopicListActivity.MODE);
            switch (this.k) {
                case 0:
                    this.d = true;
                    return;
                case 2:
                    this.e = true;
                    return;
                default:
                    return;
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.layout_ptr_listview, null);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        a(view);
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        QiushiTopicBus.register(this);
        this.f.post(new iz(this));
    }

    public void onDestroyView() {
        super.onDestroyView();
        QiushiTopicBus.unregister(this);
    }

    private void a(View view) {
        int i;
        int i2 = -14013903;
        this.f = (PtrLayout) view.findViewById(R.id.ptr);
        this.g = (ListView) view.findViewById(R.id.listview);
        this.f.setLoadMoreEnable(false);
        this.f.setPtrListener(this);
        this.f.setOnScrollListener(this);
        this.b = b();
        this.h = LayoutInflater.from(getActivity()).inflate(R.layout.qiushi_listview_foot, null);
        LinearLayout linearLayout = (LinearLayout) this.h.findViewById(R.id.foot_lin);
        View findViewById = this.h.findViewById(R.id.foot_left_line);
        View findViewById2 = this.h.findViewById(R.id.foot_right_line);
        TextView textView = (TextView) this.h.findViewById(R.id.foot_remind);
        linearLayout.setBackgroundColor(UIHelper.isNightTheme() ? -15132387 : -855310);
        if (UIHelper.isNightTheme()) {
            i = -14013903;
        } else {
            i = -2236961;
        }
        findViewById.setBackgroundColor(i);
        if (!UIHelper.isNightTheme()) {
            i2 = -2236961;
        }
        findViewById2.setBackgroundColor(i2);
        textView.setTextColor(UIHelper.isNightTheme() ? -12829625 : -5197644);
        textView.setText("没有更多了");
        this.g.addFooterView(this.h);
        this.h.setOnClickListener(new ja(this));
        this.h.setVisibility(this.i ? 0 : 8);
        this.g.setAdapter(this.b);
    }

    private BaseAdapter b() {
        return new QiushiTopicAdapter(this.c, getActivity(), this.d, this, this.e);
    }

    public void onRefresh() {
        this.j = 1;
        a(this.j);
    }

    public void onLoadMore() {
        a(this.j + 1);
    }

    private void a(int i) {
        String str = null;
        switch (this.k) {
            case 0:
                str = String.format(Constants.QIUSHI_TOPIC_ALL, new Object[]{Integer.valueOf(30), Integer.valueOf(i)});
                break;
            case 1:
                str = String.format(Constants.QIUSHI_TOPIC_SUBCRIBED, new Object[]{QsbkApp.currentUser.userId, Integer.valueOf(30), Integer.valueOf(i)});
                break;
            case 2:
                str = String.format(Constants.QIUSHI_TOPIC_SELECT, new Object[]{Integer.valueOf(30), Integer.valueOf(i)});
                break;
        }
        this.a = new SimpleHttpTask(str, new jb(this, i));
        this.a.execute();
        this.b.notifyDataSetChanged();
    }

    private void c() {
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    public void subcribe(QiushiTopic qiushiTopic, QiushiTopicCell qiushiTopicCell) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.QIUSHI_TOPIC_SUBCRIBE, new jc(this, qiushiTopic, qiushiTopicCell));
        Map hashMap = new HashMap();
        hashMap.put("topic_id", Integer.valueOf(qiushiTopic.id));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    public void unSubcribe(QiushiTopic qiushiTopic, QiushiTopicCell qiushiTopicCell) {
        new Builder(getActivity()).setCancelable(true).setMessage("是否取消订阅").setPositiveButton("不再订阅", new je(this, qiushiTopic)).setNegativeButton("继续订阅", new jd(this, qiushiTopicCell)).create().show();
    }

    public void onQiushiTopicUpdate(QiushiTopic qiushiTopic, Object obj) {
        if (!TAG.equals(obj) && this.c != null && this.c.size() > 0) {
            Object obj2 = null;
            for (int i = 0; i < this.c.size(); i++) {
                Object obj3 = this.c.get(i);
                if (obj3 instanceof QiushiTopic) {
                    QiushiTopic qiushiTopic2 = (QiushiTopic) obj3;
                    if (qiushiTopic2.id == qiushiTopic.id) {
                        qiushiTopic2.cloneFrom(qiushiTopic);
                    }
                    obj2 = 1;
                }
            }
            if (obj2 != null) {
                this.b.notifyDataSetChanged();
            }
        }
    }
}
