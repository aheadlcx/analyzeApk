package com.budejie.www.activity.phonenumber;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.c.m;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.widget.erroredittext.SetErrorAbleEditText;
import com.budejie.www.widget.erroredittext.b;
import com.budejie.www.widget.erroredittext.i;

public class c extends Fragment implements OnClickListener {
    TextWatcher a = new c$1(this);
    private Activity b;
    private m c;
    private View d;
    private SetErrorAbleEditText e;
    private String f;
    private b g;
    private ImageView h;
    private String i;
    private String j = "";
    private String k = "";
    private a l;
    private Toast m;
    private RelativeLayout n;
    private InputMethodManager o;
    private net.tsz.afinal.a.a<String> p = new c$2(this);

    public interface a {
        void a(String str, String str2);
    }

    public static c a(CharSequence charSequence) {
        c cVar = new c();
        Bundle bundle = new Bundle();
        bundle.putCharSequence("type", charSequence);
        cVar.setArguments(bundle);
        return cVar;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof a) {
            this.l = (a) activity;
            this.b = activity;
            this.c = new m(this.b);
            this.i = getArguments().getString("type");
            return;
        }
        throw new RuntimeException("host activity must implement PhoneChangePressInteface");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.phone_change_layout_two, null);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.d = getView();
        this.e = (SetErrorAbleEditText) this.d.findViewById(R.id.input_phone_number);
        this.g = new b(this.e);
        this.g.a(new com.budejie.www.widget.erroredittext.c("手机号码不能为空"));
        this.g.a(new i("手机号码不正确"));
        this.e.addTextChangedListener(this.a);
        this.h = (ImageView) this.d.findViewById(R.id.to_next_btn);
        this.h.setOnClickListener(this);
        this.n = (RelativeLayout) this.d.findViewById(R.id.layout_click);
        this.n.setOnClickListener(this);
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onClick(View view) {
        if (R.id.to_next_btn == view.getId()) {
            if (this.g.a()) {
                this.f = this.e.getText().toString();
                a(this.f, "86");
            }
        } else if (view != this.n) {
        } else {
            if (this.o != null) {
                this.o.hideSoftInputFromWindow(view.getWindowToken(), 0);
                return;
            }
            this.o = (InputMethodManager) this.b.getSystemService("input_method");
            this.o.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void a(String str, String str2) {
        BudejieApplication.a.a(RequstMethod.GET, j.l(), b(str, str2), this.p);
    }

    private net.tsz.afinal.a.b b(String str, String str2) {
        return new j().j(this.b, str, str2, this.i);
    }
}
