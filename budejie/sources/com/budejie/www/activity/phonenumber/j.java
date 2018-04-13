package com.budejie.www.activity.phonenumber;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.util.an;
import com.budejie.www.util.i;
import com.budejie.www.widget.erroredittext.SetErrorAbleEditText;
import com.budejie.www.widget.erroredittext.b;
import com.budejie.www.widget.erroredittext.c;
import java.lang.ref.SoftReference;

public class j extends Fragment implements OnClickListener {
    private static final Uri a = Uri.parse("content://sms/");
    private TextView b;
    private TextView c;
    private SetErrorAbleEditText d;
    private SetErrorAbleEditText e;
    private b f;
    private b g;
    private Button h;
    private View i;
    private Activity j;
    private a k;
    private String l;
    private String m;
    private String n = "";
    private String o = "";
    private String p = "";
    private String q = "";
    private l r;
    private Toast s;
    private net.tsz.afinal.a.a<String> t = new j$1(this);
    private net.tsz.afinal.a.a<String> u = new j$2(this);

    public interface a {
        void a(String str);
    }

    public static j a(CharSequence charSequence) {
        j jVar = new j();
        Bundle bundle = new Bundle();
        bundle.putCharSequence("type", charSequence);
        jVar.setArguments(bundle);
        return jVar;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof a) {
            this.k = (a) activity;
            this.j = activity;
            this.l = getArguments().getString("type");
            this.n = i.a().f();
            this.m = i.a().g();
            return;
        }
        throw new RuntimeException("host activity must implement VerifySuccessInterface");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.sms_verify_layout, null);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.i = getView();
        this.d = (SetErrorAbleEditText) this.i.findViewById(R.id.input_phone_number);
        this.f = new b(this.d);
        this.f.a(new c("手机号码不能为空"));
        this.f.a(new com.budejie.www.widget.erroredittext.i("手机号码不正确"));
        this.e = (SetErrorAbleEditText) this.i.findViewById(R.id.input_verify_num);
        this.g = new b(this.e);
        this.g.a(new c("验证码不能为空"));
        CharSequence a = i.a(getActivity());
        if (!(TextUtils.isEmpty(a) || "000000000000000".equals(a))) {
            if (a.startsWith("+")) {
                a = a.substring(3, a.length());
            }
            Log.e("wuzhenlin", "phoneNumber = " + a);
            this.d.setText(a);
        }
        this.b = (TextView) this.i.findViewById(R.id.county_num);
        this.c = (TextView) this.i.findViewById(R.id.obtain_verify_num);
        this.c.setOnClickListener(this);
        this.h = (Button) this.i.findViewById(R.id.to_next_btn);
        this.h.setOnClickListener(this);
        this.r = i.a().e();
        if (this.r != null) {
            this.r.a(new SoftReference(this.c));
        }
        if (!TextUtils.isEmpty(this.m)) {
            this.d.setText(this.m);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onClick(View view) {
        if (view == this.c) {
            if (this.f.a()) {
                if (this.r == null) {
                    this.r = new l(this.c, 60000, 1000);
                    i.a().a(this.r);
                }
                this.r.start();
                this.m = this.d.getText().toString();
                a(this.m, "86");
            }
        } else if (view == this.h && this.g.a()) {
            String obj = this.e.getText().toString();
            if (TextUtils.isEmpty(this.n)) {
                this.s = an.a(this.j, "请先获取验证码", -1);
                this.s.show();
                return;
            }
            a(this.m, "86", obj, this.n);
        }
    }

    private void a(String str, String str2) {
        BudejieApplication.a.a(RequstMethod.GET, com.budejie.www.http.j.l(), new com.budejie.www.http.j().j(this.j, str, str2, this.l), this.t);
    }

    private void a(String str, String str2, String str3, String str4) {
        BudejieApplication.a.a(RequstMethod.GET, com.budejie.www.http.j.m(), new com.budejie.www.http.j().e(this.j, str, str2, str3, str4), this.u);
    }
}
