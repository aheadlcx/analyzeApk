package com.budejie.www.activity.phonenumber;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.phonenumber.f.a;
import com.budejie.www.c.m;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.widget.erroredittext.SetErrorAbleEditText;
import com.budejie.www.widget.erroredittext.b;
import com.budejie.www.widget.erroredittext.c;
import com.budejie.www.widget.erroredittext.g;

public class h extends Fragment implements OnClickListener {
    private SetErrorAbleEditText a;
    private SetErrorAbleEditText b;
    private b c;
    private b d;
    private Button e;
    private String f = "";
    private Activity g;
    private a h;
    private m i;
    private net.tsz.afinal.a.a<String> j = new h$1(this);

    public static h a(CharSequence charSequence) {
        h hVar = new h();
        Bundle bundle = new Bundle();
        bundle.putCharSequence("req", charSequence);
        hVar.setArguments(bundle);
        return hVar;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof a) {
            this.h = (a) activity;
            this.f = getArguments().getString("req");
            this.g = activity;
            this.i = new m(this.g);
            return;
        }
        throw new RuntimeException("host activity must implement RegisterSuccessInterface");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.retrieve_pswd_layout, null);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        View view = getView();
        this.a = (SetErrorAbleEditText) view.findViewById(R.id.retrieve_inputpswd);
        this.c = new b(this.a);
        this.c.a(new c("密码不能为空"));
        this.c.a(new g("密码格式不正确"));
        this.b = (SetErrorAbleEditText) view.findViewById(R.id.retrieve_inputpswd_again);
        this.d = new b(this.b);
        this.d.a(new c("密码不能为空"));
        this.d.a(new g("密码格式不正确"));
        this.e = (Button) view.findViewById(R.id.ok_btn);
        this.e.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view == this.e && this.c.a() && this.d.a()) {
            a(this.a.getText().toString(), this.b.getText().toString(), this.f);
        }
    }

    private void a(String str, String str2, String str3) {
        BudejieApplication.a.a(RequstMethod.GET, j.n(), new j().k(this.g, str, str2, str3), this.j);
    }
}
