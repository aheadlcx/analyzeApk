package com.budejie.www.activity.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.widget.XListView;

public class a extends Fragment {
    protected int a;
    protected RelativeLayout b;
    protected XListView c;
    protected LinearLayout d;
    protected ListView e;
    protected String f;
    protected com.budejie.www.g.a g;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_search_label_page, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        e();
        d();
    }

    private void d() {
        this.f = "";
    }

    private void e() {
        this.c = (XListView) getView().findViewById(R.id.SearchLabelListview);
        this.e = (ListView) getView().findViewById(R.id.HotSearchListview);
        this.b = (RelativeLayout) getView().findViewById(R.id.LoadingLayout);
        this.d = (LinearLayout) getView().findViewById(R.id.HistoryLayout);
    }

    public void a(String str) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), str, 0).show();
        }
    }

    public void a() {
        this.f = "";
        if (this.a == 0) {
            if (this.d != null) {
                this.d.setVisibility(0);
            }
            if (this.e != null) {
                this.e.setSelection(0);
            }
        }
        if (this.c != null) {
            this.c.setVisibility(8);
        }
        if (this.b != null) {
            this.b.setVisibility(8);
        }
    }

    public void b() {
        if (this.a == 0 && this.d != null) {
            this.d.setVisibility(8);
        }
        if (this.c != null) {
            this.c.setVisibility(8);
        }
        if (this.b != null) {
            this.b.setVisibility(0);
        }
    }

    public void c() {
        if (this.a == 0) {
            this.d.setVisibility(8);
        }
        if (this.c != null) {
            this.c.setVisibility(0);
        }
        if (this.b != null) {
            this.b.setVisibility(8);
        }
    }

    public void a(com.budejie.www.g.a aVar) {
        this.g = aVar;
    }
}
