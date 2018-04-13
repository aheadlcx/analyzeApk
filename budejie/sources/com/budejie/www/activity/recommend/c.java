package com.budejie.www.activity.recommend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.adapter.c.c$a;
import com.budejie.www.bean.SuggestedFollowsListItem;
import com.budejie.www.c.g;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.util.an;
import com.budejie.www.util.as;
import com.budejie.www.util.as.a;
import com.budejie.www.util.b;
import com.budejie.www.widget.XListView;
import java.util.ArrayList;
import java.util.List;

public class c extends Fragment implements c$a {
    private XListView a;
    private com.budejie.www.adapter.c.c b;
    private Toast c;
    private List<SuggestedFollowsListItem> d;
    private SuggestedFollowsActivity e;
    private a f;
    private Handler g;
    private g h;
    private XListView.a i = new c$2(this);
    private net.tsz.afinal.a.a<String> j = new c$3(this);
    private net.tsz.afinal.a.a<String> k = new c$4(this);

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.e = (SuggestedFollowsActivity) activity;
        this.b = new com.budejie.www.adapter.c.c(getActivity(), this);
        this.d = new ArrayList();
        this.g = new Handler();
        this.h = new g(activity);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.suggested_follows_fragment_layout, null);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.a = (XListView) view.findViewById(R.id.listview);
        this.a.setPullLoadEnable(false);
        this.a.setPullRefreshEnable(true);
        this.a.setAdapter(this.b);
        this.a.setXListViewListener(this.i);
        if (this.b.getCount() <= 0) {
            this.g.postDelayed(new c$1(this), 200);
        }
    }

    public void onStart() {
        super.onStart();
        this.e.a("no_invite_data");
    }

    public void onResume() {
        super.onResume();
        if (this.b.getCount() > 0) {
            as.b().a(this.b);
        }
    }

    private void a() {
        this.f = as.b().c();
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", c(), this.j);
    }

    private void b() {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", c(), this.k);
    }

    public void a(SuggestedFollowsListItem suggestedFollowsListItem, int i) {
        try {
            if (an.a(this.e.a())) {
                b.a(getActivity(), suggestedFollowsListItem.uid, new c$5(this, suggestedFollowsListItem));
                return;
            }
            an.a(this.e, new Intent(this.e, SuggestedFollowsActivity.class));
        } catch (Exception e) {
        }
    }

    public void b(SuggestedFollowsListItem suggestedFollowsListItem, int i) {
        try {
            b.b(getActivity(), suggestedFollowsListItem.uid, new c$6(this, suggestedFollowsListItem));
        } catch (Exception e) {
        }
    }

    public void a(boolean z, SuggestedFollowsListItem suggestedFollowsListItem, int i) {
    }

    public void a(View view, SuggestedFollowsListItem suggestedFollowsListItem) {
        Bundle bundle = new Bundle();
        bundle.putString(PersonalProfileActivity.c, suggestedFollowsListItem.uid);
        this.e.b.a(7, bundle).onClick(view);
    }

    private net.tsz.afinal.a.b c() {
        return new j().a(this.e, getActivity().getSharedPreferences("weiboprefer", 0).getString("id", ""), this.f);
    }
}
