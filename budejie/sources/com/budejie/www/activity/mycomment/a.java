package com.budejie.www.activity.mycomment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.video.k;
import com.budejie.www.c.d;
import com.budejie.www.c.h;
import com.budejie.www.g.b;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.widget.XListView;
import java.util.List;

public class a extends Fragment {
    private XListView a;
    private e b;
    private d c;
    private String d;
    private b e;
    private d f;
    private h g;
    private String h = "0";
    private Handler i;
    private com.budejie.www.widget.XListView.a j = new a$2(this);
    private OnScrollListener k = new a$3(this);
    private com.budejie.www.activity.mycomment.e.a l = new a$4(this);
    private net.tsz.afinal.a.a<String> m = new a$5(this);
    private net.tsz.afinal.a.a<String> n = new a$6(this);
    private net.tsz.afinal.a.a<String> o = new a$7(this);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.d = getArguments().getString(HistoryOpenHelper.COLUMN_UID);
        this.b = new e(getActivity(), this.l, null);
        this.e = new b(getActivity());
        this.g = new h(getActivity());
        this.f = new d(getActivity());
        this.i = new Handler();
    }

    public void onResume() {
        super.onResume();
        this.e = new b(getActivity());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.mycomment_fragment, null);
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.a = (XListView) view.findViewById(R.id.myCommentListview);
        this.a.setPullRefreshEnable(true);
        this.a.setPullLoadEnable(false);
        this.a.setXListViewListener(this.j);
        this.a.setOnScrollListener(this.k);
        this.a.setAdapter(this.b);
        this.i.postDelayed(new a$1(this), 200);
    }

    public void onPause() {
        super.onPause();
        k.a(getActivity()).o();
    }

    public void onDestroy() {
        super.onDestroy();
        k.a(getActivity()).p();
    }

    private void a() {
        this.h = "0";
        BudejieApplication.a.a(RequstMethod.GET, j.a(this.d, this.h, true), new j(getActivity()), this.m);
    }

    private void b() {
        BudejieApplication.a.a(RequstMethod.GET, j.a(this.d, this.h, true), new j(getActivity()), this.n);
    }

    private void a(List<d> list) {
        d dVar = (d) list.get(list.size() - 1);
        if (dVar != null) {
            this.h = dVar.a.id;
        }
    }
}
