package qsbk.app.fragments;

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
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.adapter.CircleTopicCollectionsAdapter;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.CircleTopicEpisode;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;

public class CircleTopicRecommendFragment extends Fragment implements OnScrollListener, PtrListener {
    public static final String TAG = CircleTopicRecommendFragment.class.getSimpleName();
    SimpleHttpTask a;
    BaseAdapter b;
    ArrayList<CircleTopicEpisode> c = new ArrayList();
    private PtrLayout d;
    private ListView e;
    private View f;
    private int g = 1;

    public static CircleTopicRecommendFragment newInstance() {
        CircleTopicRecommendFragment circleTopicRecommendFragment = new CircleTopicRecommendFragment();
        circleTopicRecommendFragment.setArguments(new Bundle());
        return circleTopicRecommendFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
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
        this.d.post(new aq(this));
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    private void a(View view) {
        this.d = (PtrLayout) view.findViewById(R.id.ptr);
        this.e = (ListView) view.findViewById(R.id.listview);
        this.d.setLoadMoreEnable(false);
        this.d.setPtrListener(this);
        this.d.setOnScrollListener(this);
        this.b = a();
        this.f = LayoutInflater.from(getActivity()).inflate(R.layout.qiushi_listview_foot, null);
        LinearLayout linearLayout = (LinearLayout) this.f.findViewById(R.id.foot_lin);
        View findViewById = this.f.findViewById(R.id.foot_left_line);
        View findViewById2 = this.f.findViewById(R.id.foot_right_line);
        TextView textView = (TextView) this.f.findViewById(R.id.foot_remind);
        linearLayout.setBackgroundColor(UIHelper.isNightTheme() ? -15132387 : -855310);
        findViewById.setBackgroundColor(0);
        findViewById2.setBackgroundColor(0);
        textView.setTextColor(UIHelper.isNightTheme() ? -12829625 : -5197644);
        textView.setText("没有更多了");
        this.e.addFooterView(this.f);
        this.f.setVisibility(8);
        this.e.setAdapter(this.b);
    }

    private BaseAdapter a() {
        return new CircleTopicCollectionsAdapter(this.c, getActivity());
    }

    public void onRefresh() {
        this.g = 1;
        a(this.g);
    }

    public void onLoadMore() {
        a(this.g + 1);
    }

    private void a(int i) {
        this.a = new SimpleHttpTask(Constants.CIRCLE_TOPIC_ALL_COLLECTIONS, new ar(this, i));
        this.a.execute();
        this.b.notifyDataSetChanged();
    }

    private void b() {
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }
}
