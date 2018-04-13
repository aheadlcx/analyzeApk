package qsbk.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.adapter.CircleTopicListAdapter;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.TipsHelper;

public class CircleTopicListFragment extends Fragment implements PtrListener {
    ArrayList<CircleTopic> a = new ArrayList();
    SimpleHttpTask b;
    private PtrLayout c;
    private ListView d;
    private BaseAdapter e;
    private View f;
    private boolean g = false;
    private boolean h;
    private int i;
    private boolean j;
    private TipsHelper k;
    private ProgressBar l;
    private String m;

    public static CircleTopicListFragment newInstance(String str) {
        CircleTopicListFragment circleTopicListFragment = new CircleTopicListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category_id", str);
        circleTopicListFragment.setArguments(bundle);
        return circleTopicListFragment;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.m = arguments.getString("category_id");
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.layout_ptr_listview, null);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        a(view);
        b();
    }

    private void a(View view) {
        int i;
        int i2 = -14013903;
        this.c = (PtrLayout) view.findViewById(R.id.ptr);
        this.d = (ListView) view.findViewById(R.id.listview);
        this.c.setLoadMoreEnable(false);
        this.c.setPtrListener(this);
        this.e = a();
        this.k = new TipsHelper(view.findViewById(R.id.tips));
        this.l = (ProgressBar) view.findViewById(R.id.progress);
        this.f = LayoutInflater.from(getActivity()).inflate(R.layout.qiushi_listview_foot, null);
        LinearLayout linearLayout = (LinearLayout) this.f.findViewById(R.id.foot_lin);
        View findViewById = this.f.findViewById(R.id.foot_left_line);
        View findViewById2 = this.f.findViewById(R.id.foot_right_line);
        TextView textView = (TextView) this.f.findViewById(R.id.foot_remind);
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
        this.d.addFooterView(this.f);
        this.f.setVisibility(this.g ? 0 : 8);
        this.d.setAdapter(this.e);
        this.c.post(new ao(this));
    }

    private BaseAdapter a() {
        return new CircleTopicListAdapter(this.a, getActivity());
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    private void a(int i) {
        this.h = true;
        if (i == 1) {
            this.k.hide();
        }
        String format = String.format(Constants.CIRCLE_TOPIC_CATEGORY_LIST, new Object[]{this.m, Integer.valueOf(i)});
        if (!(this.b == null || this.b.isCancelled())) {
            this.b.cancel(true);
        }
        this.b = new SimpleHttpTask(format, new ap(this, i));
        this.b.execute();
    }

    private void b() {
        this.l.setVisibility(0);
    }

    private void c() {
        if (this.l != null && this.l.isShown()) {
            this.l.setVisibility(8);
        }
    }

    public void onRefresh() {
        if (!this.h) {
            a(1);
        }
    }

    public void onLoadMore() {
        if (!this.h) {
            a(this.i + 1);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.b != null && !this.b.isCancelled()) {
            this.b.cancel(true);
        }
    }
}
