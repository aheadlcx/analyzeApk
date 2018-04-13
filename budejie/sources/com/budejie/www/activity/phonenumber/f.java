package com.budejie.www.activity.phonenumber;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.c.m;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.widget.erroredittext.SetErrorAbleEditText;
import com.budejie.www.widget.erroredittext.b;
import com.budejie.www.widget.erroredittext.c;
import com.budejie.www.widget.erroredittext.g;

public class f extends Fragment implements OnClickListener {
    private SetErrorAbleEditText a;
    private SetErrorAbleEditText b;
    private b c;
    private b d;
    private RelativeLayout e;
    private Button f;
    private String g;
    private Activity h;
    private a i;
    private m j;
    private String k;
    private net.tsz.afinal.a.a<String> l;

    public interface a {
        void a();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof a) {
            this.i = (a) activity;
            this.g = getArguments().getString("req");
            this.k = getArguments().getString("nike_name");
            this.h = activity;
            this.j = new m(this.h);
            return;
        }
        throw new RuntimeException("host activity must implement RegisterSuccessInterface");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.register_layout, null);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        View view = getView();
        this.a = (SetErrorAbleEditText) view.findViewById(R.id.input_pswd);
        this.c = new b(this.a);
        this.c.a(new c("密码不能为空"));
        this.c.a(new g("密码格式不正确"));
        this.b = (SetErrorAbleEditText) view.findViewById(R.id.input_nice_name);
        this.d = new b(this.b);
        this.d.a(new c("昵称不能为空"));
        this.d.a(new com.budejie.www.widget.erroredittext.f("昵称输入有误"));
        this.e = (RelativeLayout) view.findViewById(R.id.input_nikename_layout);
        if (TextUtils.isEmpty(this.k)) {
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
        }
        this.f = (Button) view.findViewById(R.id.commit_btn);
        this.f.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view == this.f && this.c.a()) {
            String obj = this.a.getText().toString();
            String str = "";
            if (TextUtils.isEmpty(this.k) && this.d.a()) {
                str = this.b.getText().toString();
            }
            a(str, obj, this.g);
        }
    }

    private void a(String str, String str2, String str3) {
        BudejieApplication.a.a(RequstMethod.GET, j.l(), new j().i(this.h, str, str2, str3), this.l);
    }
}
