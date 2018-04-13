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
import android.widget.GridView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.adapter.a.j;
import com.budejie.www.bean.RecommendSubscription;
import com.budejie.www.c.b;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.util.an;
import java.util.List;
import net.tsz.afinal.a.a;

public class e extends Fragment implements OnClickListener {
    private View a;
    private Activity b;
    private GridView c;
    private b d;
    private List<RecommendSubscription> e;
    private Dialog f;
    private j g;
    private a h = new e$1(this);

    public static e a() {
        e eVar = new e();
        eVar.setArguments(new Bundle());
        return eVar;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.b = activity;
        this.d = new b(this.b);
        this.f = new Dialog(this.b, R.style.dialogTheme);
        this.f.setContentView(R.layout.loaddialog);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.recommend_subscription, null);
    }

    @SuppressLint({"NewApi"})
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.a = getView();
        this.c = (GridView) this.a.findViewById(R.id.grid_view);
        this.c.setOverScrollMode(2);
        d();
    }

    private void d() {
        if (g()) {
            this.g = new j(this.b, this.e, this.c);
            this.c.setAdapter(this.g);
        } else if (an.a(this.b)) {
            b();
            e();
        }
    }

    private synchronized void e() {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", f(), this.h);
    }

    private net.tsz.afinal.a.b f() {
        return new com.budejie.www.http.j().b(this.b);
    }

    private boolean g() {
        this.e = this.d.c();
        if (this.e == null || this.e.size() <= 0) {
            return false;
        }
        for (int size = this.e.size() - 1; size >= 0; size--) {
            if ("1".equals(((RecommendSubscription) this.e.get(size)).getIs_sub())) {
                this.e.remove(size);
            }
        }
        return true;
    }

    public void onClick(View view) {
    }

    public void b() {
        this.f.show();
    }

    public void c() {
        this.f.dismiss();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.g != null) {
            this.g.a();
        }
    }
}
