package com.budejie.www.activity.phonenumber;

import android.app.Activity;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.c.m;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.util.an;
import com.budejie.www.util.i;
import com.budejie.www.widget.erroredittext.SetErrorAbleEditText;
import com.budejie.www.widget.erroredittext.c;
import java.lang.ref.SoftReference;

public class k extends Fragment implements OnClickListener {
    private static final Uri b = Uri.parse("content://sms/");
    private net.tsz.afinal.a.a<String> A = new k$4(this);
    private net.tsz.afinal.a.a<String> B = new k$5(this);
    private net.tsz.afinal.a.a<String> C = new k$6(this);
    TextWatcher a = new k$2(this);
    private TextView c;
    private SetErrorAbleEditText d;
    private com.budejie.www.widget.erroredittext.b e;
    private ImageView f;
    private View g;
    private Activity h;
    private String i;
    private String j;
    private String k;
    private String l = "";
    private String m = "";
    private String n = "";
    private String o = "";
    private l p;
    private Toast q;
    private m r;
    private b s;
    private a t;
    private ImageView u;
    private ImageView v;
    private String w;
    private boolean x;
    private InputMethodManager y;
    private net.tsz.afinal.a.a<String> z = new k$3(this);

    public interface b {
        void a();
    }

    public interface a {
        void b();
    }

    public static k a(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, boolean z) {
        k kVar = new k();
        Bundle bundle = new Bundle();
        bundle.putCharSequence("seq", charSequence);
        bundle.putCharSequence("type", charSequence2);
        bundle.putCharSequence("source", charSequence3);
        bundle.putBoolean("isChangePhone", z);
        kVar.setArguments(bundle);
        return kVar;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.i = getArguments().getString("type");
        this.l = getArguments().getString("seq");
        this.w = getArguments().getString("source");
        this.x = getArguments().getBoolean("isChangePhone");
        if (this.x) {
            if (activity instanceof a) {
                this.t = (a) activity;
            } else {
                throw new RuntimeException("host activity must implement ChangeSuccessInterface");
            }
        } else if (activity instanceof b) {
            this.s = (b) activity;
        } else {
            throw new RuntimeException("host activity must implement RegisterSuccessInterface");
        }
        this.h = activity;
        this.r = new m(this.h);
        this.j = i.a().g();
        this.k = i.a().h();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.sms_verify_layout_new, null);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.g = getView();
        this.g.findViewById(R.id.traceroute_rootview).setOnClickListener(this);
        this.f = (ImageView) this.g.findViewById(R.id.to_next_btn);
        this.f.setEnabled(false);
        this.f.setOnClickListener(this);
        this.u = (ImageView) this.g.findViewById(R.id.next_left);
        this.v = (ImageView) this.g.findViewById(R.id.next_right);
        if ("phone_register".equals(this.w)) {
            this.f.setBackgroundResource(R.drawable.default_next_button);
            this.v.setBackgroundResource(R.drawable.next_view);
            this.u.setVisibility(4);
        } else if ("third_party".equals(this.w) || "MyAccountActivity".equals(this.w) || "PostInviteRow".equals(this.w)) {
            this.f.setBackgroundResource(R.drawable.default_finish_button);
            this.v.setVisibility(4);
        }
        this.d = (SetErrorAbleEditText) this.g.findViewById(R.id.input_verify_num);
        this.d.setOnFocusChangeListener(new k$1(this));
        this.d.addTextChangedListener(this.a);
        this.e = new com.budejie.www.widget.erroredittext.b(this.d);
        this.e.a(new c("验证码不能为空"));
        this.c = (TextView) this.g.findViewById(R.id.again_verify);
        this.c.setOnClickListener(this);
        this.p = i.a().e();
        if (this.p != null) {
            this.p.a(new SoftReference(this.c));
        }
        a();
        this.p.start();
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    private void a() {
        if (this.p == null) {
            this.p = new l(this.c, 60000, 1000);
            i.a().a(this.p);
        }
    }

    public void onClick(View view) {
        if (view == this.c) {
            a();
            this.p.start();
            a(this.j, "86");
        } else if (view == this.f) {
            if (this.e.a()) {
                String obj = this.d.getText().toString();
                if (TextUtils.isEmpty(this.l)) {
                    this.q = an.a(this.h, "请先获取验证码", -1);
                    this.q.show();
                    return;
                }
                a(this.j, "86", obj, this.l);
            }
        } else if (view.getId() != R.id.traceroute_rootview) {
        } else {
            if (this.y != null) {
                this.y.hideSoftInputFromWindow(view.getWindowToken(), 0);
                return;
            }
            this.y = (InputMethodManager) this.h.getSystemService("input_method");
            this.y.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void a(String str, String str2) {
        BudejieApplication.a.a(RequstMethod.GET, j.l(), new j().j(this.h, str, str2, this.i), this.z);
    }

    private void a(String str) {
        BudejieApplication.a.a(RequstMethod.GET, "https://api.budejie.com/api/api_open.php", new j().n(this.h, str), this.A);
    }

    private void a(String str, String str2, String str3, String str4) {
        BudejieApplication.a.a(RequstMethod.GET, j.m(), new j().e(this.h, str, str2, str3, str4), this.B);
    }

    private void a(String str, String str2, String str3) {
        BudejieApplication.a.a(RequstMethod.GET, j.k(), new j().i(this.h, str, str2, str3), this.C);
    }
}
