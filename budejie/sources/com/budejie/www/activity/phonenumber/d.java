package com.budejie.www.activity.phonenumber;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.adapter.a.i;
import com.budejie.www.bean.RecommendUser;
import com.budejie.www.c.b;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.util.an;
import java.util.ArrayList;
import java.util.List;
import net.tsz.afinal.a.a;

public class d extends Fragment implements OnClickListener {
    public List<String> a;
    OnItemClickListener b = new d$1(this);
    private View c;
    private Activity d;
    private ListView e;
    private b f;
    private Dialog g;
    private List<RecommendUser> h;
    private a i = new d$2(this);

    public static d a() {
        d dVar = new d();
        dVar.setArguments(new Bundle());
        return dVar;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.d = activity;
        this.f = new b(this.d);
        this.h = new ArrayList();
        this.a = new ArrayList();
        this.g = new Dialog(this.d, R.style.dialogTheme);
        this.g.setContentView(R.layout.loaddialog);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.recommend_attention, null);
    }

    @SuppressLint({"NewApi"})
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.c = getView();
        this.e = (ListView) this.c.findViewById(R.id.listview);
        this.e.setOverScrollMode(2);
        d();
    }

    private void d() {
        if (g()) {
            this.e.setAdapter(new i(this.h, this.d));
        } else if (an.a(this.d)) {
            b();
            e();
        }
    }

    private synchronized void e() {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", f(), this.i);
    }

    private net.tsz.afinal.a.b f() {
        return new j().c(this.d);
    }

    private boolean g() {
        this.h = this.f.b();
        if (this.h == null || this.h.size() <= 0) {
            return false;
        }
        return true;
    }

    public void onClick(View view) {
    }

    public void b() {
        this.g.show();
    }

    public void c() {
        this.g.dismiss();
    }
}
