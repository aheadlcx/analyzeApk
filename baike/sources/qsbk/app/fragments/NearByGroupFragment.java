package qsbk.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.BaseTabActivity.ILoadingState;
import qsbk.app.adapter.GroupAdapter;
import qsbk.app.http.HttpTask;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.nearby.api.LocationHelper.LocationCallBack;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;

public class NearByGroupFragment extends BaseNearbyFragment implements ILoadingState, LocationCallBack, PtrListener {
    private PtrLayout a;
    private ListView b;
    private ArrayList<Object> c;
    private GroupAdapter d;
    private HttpTask e;
    private int f = 1;
    private int g;

    protected String a() {
        return getString(R.string.view_nearby_group);
    }

    public void onFillContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.layout_ptr_listview, viewGroup, false);
        this.a = (PtrLayout) inflate.findViewById(R.id.ptr);
        this.b = (ListView) inflate.findViewById(R.id.listview);
        viewGroup.addView(inflate);
        this.a.setLoadMoreEnable(false);
        this.a.setPtrListener(this);
        this.c = new ArrayList();
        this.d = new GroupAdapter(this.c, getActivity(), 0);
        this.b.setAdapter(this.d);
        this.b.setOnItemClickListener(new ha(this));
    }

    protected void e() {
        this.n = true;
        if ((this.c == null || this.c.size() == 0) && this.h != null) {
            this.h.setVisibility(0);
        }
    }

    private void f() {
        e();
        String str = Constants.URL_NEARBY_GROUP_LIST;
        r1 = new Object[3];
        LocationHelper locationHelper = this.l;
        r1[1] = Double.valueOf(LocationHelper.getLatitude());
        locationHelper = this.l;
        r1[2] = Double.valueOf(LocationHelper.getLongitude());
        str = String.format(str, r1);
        this.e = new HttpTask(str, str, new hb(this));
        this.e.execute(new Void[0]);
    }

    public void onRefresh() {
        if (this.e != null) {
            this.e.cancel(true);
            this.e = null;
        }
        this.f = 1;
        f();
    }

    public void onLoadMore() {
        f();
    }

    public void onLocateDone() {
        f();
    }
}
