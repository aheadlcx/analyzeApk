package qsbk.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.fragments.BaseFragment;
import qsbk.app.http.HttpTask;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;

public class GroupRankFragment extends BaseFragment implements PtrListener {
    private int a = -1;
    private int b = -1;
    private int c;
    private int d;
    private HttpTask e;
    private ArrayList<Object> f;
    private GroupRankFragment$GroupRankingAdapter g;
    private PtrLayout h;
    private ListView i;
    private View j;

    public static GroupRankFragment newInstance() {
        return new GroupRankFragment();
    }

    public static GroupRankFragment newInstance(int i) {
        GroupRankFragment groupRankFragment = new GroupRankFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("rank", i);
        groupRankFragment.setArguments(bundle);
        return groupRankFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.d = arguments.getInt("rank", -1);
        } else {
            this.d = -1;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.layout_group_rank, viewGroup, false);
        this.h = (PtrLayout) inflate.findViewById(R.id.ptr);
        this.i = (ListView) inflate.findViewById(R.id.listview);
        this.j = inflate.findViewById(R.id.id_loading_div);
        this.h.setLoadMoreEnable(false);
        this.h.setPtrListener(this);
        this.f = new ArrayList();
        this.g = new GroupRankFragment$GroupRankingAdapter(this, this.f);
        this.i.setAdapter(this.g);
        this.i.setOnItemClickListener(new oc(this));
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        onRefresh();
        super.onViewCreated(view, bundle);
    }

    private void a() {
        if ((this.f == null || this.f.size() == 0) && this.j != null) {
            this.j.setVisibility(0);
        }
    }

    private void b() {
        Activity activity = getActivity();
        if (activity instanceof BaseTabActivity) {
            ((BaseTabActivity) activity).hideLoadingIfFocus(this);
        } else {
            activity.setProgressBarIndeterminateVisibility(false);
        }
        if (this.j != null) {
            this.j.setVisibility(8);
        }
    }

    private void a(int i) {
        String str;
        if (i == -1) {
            str = Constants.URL_RANK_GROUP_LIST + "?rank=" + this.d;
        } else {
            str = Constants.URL_RANK_GROUP_LIST + "?page=" + i;
        }
        this.e = new HttpTask(str, str, new od(this, i));
        this.e.execute(new Void[0]);
    }

    public void onRefresh() {
        if (this.e != null) {
            this.e.cancel(true);
            this.e = null;
        }
        a();
        if (this.a == -1) {
            if (this.d > -1) {
                a(-1);
                return;
            }
            this.b = 1;
            this.a = 1;
            a(this.a);
        } else if (this.a <= 1) {
            a(1);
        } else {
            a(this.a - 1);
        }
    }

    public void onLoadMore() {
        if (this.e == null) {
            a(this.b);
        }
    }
}
