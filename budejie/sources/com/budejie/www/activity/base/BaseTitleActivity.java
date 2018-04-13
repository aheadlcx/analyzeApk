package com.budejie.www.activity.base;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.h.c;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.umeng.analytics.MobclickAgent;

public class BaseTitleActivity extends FragmentActivity implements OnClickListener {
    public static int g = 0;
    private Activity a;
    private Dialog b;
    private LinearLayout c;
    private RelativeLayout d;
    private RelativeLayout e;
    public int f = 0;
    protected TextView h;
    private RelativeLayout i;
    private TextView j;
    private TextView k;
    private TextView l;
    private RelativeLayout m;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        an.f((Activity) this);
        this.a = this;
        g = ai.a(this.a);
        setTheme(c.a().a(g));
        setContentView(R.layout.base_title_layout);
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.b = new Dialog(this, R.style.dialogTheme);
        this.b.setContentView(R.layout.loaddialog);
        d();
    }

    protected void d() {
        this.m = (RelativeLayout) findViewById(R.id.title_bar);
        this.c = (LinearLayout) findViewById(R.id.left_layout);
        this.d = (RelativeLayout) findViewById(R.id.title_center_layout);
        this.e = (RelativeLayout) findViewById(R.id.right_layout);
        this.i = (RelativeLayout) findViewById(R.id.content);
        this.h = (TextView) findViewById(R.id.title_left_btn);
        this.j = (TextView) findViewById(R.id.title_right_btn);
        this.k = (TextView) findViewById(R.id.title_left_publish_btn);
        this.l = (TextView) findViewById(R.id.title_center_tv);
        this.e.setOnClickListener(this);
        this.c.setOnClickListener(this);
    }

    public void a(boolean z) {
        if (z) {
            this.c.setVisibility(4);
        } else {
            this.c.setVisibility(0);
        }
    }

    public void b(boolean z) {
        if (z) {
            this.e.setVisibility(4);
        } else {
            this.e.setVisibility(0);
        }
    }

    public void c(boolean z) {
        if (z) {
            findViewById(R.id.titleLayout).setVisibility(8);
        } else {
            findViewById(R.id.titleLayout).setVisibility(0);
        }
    }

    public void a(String str) {
        this.l.setText(str);
    }

    public void a(int i) {
        this.l.setText(i);
    }

    public void b(String str) {
        this.h.setText(str);
    }

    public void b(int i) {
        this.h.setText(i);
    }

    public void c(String str) {
        this.j.setText(str);
    }

    public void c(int i) {
        this.j.setText(i);
    }

    public void d(int i) {
        Drawable drawable = getResources().getDrawable(i);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.j.setCompoundDrawables(null, null, drawable, null);
        this.e.setVisibility(0);
    }

    public void e(int i) {
        if (this.i != null) {
            this.i.addView(LayoutInflater.from(this).inflate(i, null), new LayoutParams(-1, -1));
        }
    }

    public void f(int i) {
        this.j.setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
    }

    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public void e() {
        this.b.show();
    }

    public void f() {
        this.b.dismiss();
    }

    public void onClick(View view) {
        if (view == this.c) {
            onLeftClick(this.h);
        } else if (view == this.e) {
            onRightClick(this.j);
        }
    }

    public void onLeftClick(View view) {
        finish();
    }

    public void onRightClick(View view) {
    }
}
