package com.budejie.www.activity.phonenumber;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.c.m;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.widget.erroredittext.SetErrorAbleEditText;
import com.budejie.www.widget.erroredittext.b;
import com.budejie.www.widget.erroredittext.c;
import com.budejie.www.widget.erroredittext.i;

public class g extends Fragment implements OnClickListener {
    TextWatcher a = new g$1(this);
    TextWatcher b = new g$2(this);
    private SetErrorAbleEditText c;
    private b d;
    private ImageView e;
    private ImageView f;
    private ImageView g;
    private String h = "";
    private Activity i;
    private m j;
    private String k;
    private SetErrorAbleEditText l;
    private b m;
    private String n;
    private String o = "";
    private String p = "";
    private String q;
    private String r;
    private Toast s;
    private String t;
    private boolean u;
    private boolean v;
    private a w;
    private InputMethodManager x;
    private net.tsz.afinal.a.a<String> y = new g$3(this);

    public interface a {
        void a(String str);
    }

    public static g a(CharSequence charSequence, CharSequence charSequence2) {
        g gVar = new g();
        Bundle bundle = new Bundle();
        bundle.putCharSequence("type", charSequence2);
        bundle.putCharSequence("source", charSequence);
        gVar.setArguments(bundle);
        return gVar;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof a) {
            this.w = (a) activity;
            this.n = getArguments().getString("type");
            this.t = getArguments().getString("source");
            this.k = getArguments().getString("nike_name");
            this.i = activity;
            this.j = new m(this.i);
            return;
        }
        throw new RuntimeException("host activity must implement RegisterSuccessInterface");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.new_sms_verify_layout, null);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        View view = getView();
        view.findViewById(R.id.traceroute_rootview).setOnClickListener(this);
        this.e = (ImageView) view.findViewById(R.id.to_next_btn);
        this.e.setOnClickListener(this);
        this.f = (ImageView) view.findViewById(R.id.next_left);
        this.g = (ImageView) view.findViewById(R.id.next_right);
        if ("phone_register".equals(this.t)) {
            this.e.setBackgroundResource(R.drawable.default_next_button);
            this.f.setVisibility(4);
        } else if ("third_party".equals(this.t) || "MyAccountActivity".equals(this.t) || "PostInviteRow".equals(this.t)) {
            this.e.setBackgroundResource(R.drawable.default_next_button);
            this.f.setBackgroundResource(R.drawable.new_next_view);
            this.g.setBackgroundResource(R.drawable.new_next_view);
        }
        this.l = (SetErrorAbleEditText) view.findViewById(R.id.input_phone_number);
        this.l.addTextChangedListener(this.a);
        this.m = new b(this.l);
        this.m.a(new c("手机号码不能为空"));
        this.m.a(new i("手机号码不正确"));
        CharSequence a = i.a(getActivity());
        if (!TextUtils.isEmpty(a)) {
            if (a.startsWith("+")) {
                a = a.substring(3, a.length());
            }
            this.l.setText(a);
        }
        this.c = (SetErrorAbleEditText) view.findViewById(R.id.input_verify_num);
        this.c.addTextChangedListener(this.b);
        this.d = new b(this.c);
        this.d.a(new c("密码不能为空"));
        this.d.a(new com.budejie.www.widget.erroredittext.g("密码格式不正确"));
    }

    public void onClick(View view) {
        if (view == this.e) {
            if (this.m.a() && this.d.a()) {
                this.q = this.l.getText().toString();
                this.r = this.c.getText().toString();
                a(this.q, "86");
            }
        } else if (view.getId() != R.id.traceroute_rootview) {
        } else {
            if (this.x != null) {
                this.x.hideSoftInputFromWindow(view.getWindowToken(), 0);
                return;
            }
            this.x = (InputMethodManager) this.i.getSystemService("input_method");
            this.x.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void a(String str, String str2) {
        BudejieApplication.a.a(RequstMethod.GET, j.l(), new j().j(this.i, str, str2, this.n), this.y);
    }
}
