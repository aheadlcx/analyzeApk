package qsbk.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.nearby.api.NearbyEngine;
import qsbk.app.nearby.ui.InfoCompleteActivity;
import qsbk.app.utils.UIHelper;

public class CreateNewGroupActivity extends BaseCreateGroupActivity {
    public static final int REQ_GROUP_COMPLETE = 4;
    private boolean A = false;
    private int B;
    private int C;
    private int D;
    private int E;
    int a = 1;
    private TextView b;
    private ImageView c;
    private TextView d;
    private ImageView e;
    private View f;
    private ImageView g;
    private View h;
    private TextView i;
    private TextView j;
    private String k = null;
    private TextView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private ImageView r;
    private ImageView s;
    private TextView t;
    private TextView u;
    private TextView v;
    private ImageView w;
    private ImageView x;
    private boolean y = false;
    private boolean z = false;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return e();
    }

    protected String e() {
        return "建群";
    }

    protected int a() {
        return R.layout.activity_create_new_group;
    }

    protected void a(Bundle bundle) {
        int i;
        int i2 = -4359140;
        int i3 = -9802626;
        int i4 = -12171438;
        int i5 = -10263970;
        setActionbarBackable();
        this.b = (TextView) findViewById(R.id.next_create_tv);
        this.c = (ImageView) findViewById(R.id.qb_age_img);
        this.d = (TextView) findViewById(R.id.qb_age_info);
        this.e = (ImageView) findViewById(R.id.person_info_img);
        this.f = findViewById(R.id.person_info_set);
        this.g = (ImageView) findViewById(R.id.location_img);
        this.h = findViewById(R.id.location_set);
        this.j = (TextView) findViewById(R.id.tips);
        this.k = getString(R.string.create_group_qb_age_fmt);
        this.i = (TextView) findViewById(R.id.age);
        this.i.setText(String.format(this.k, new Object[]{Integer.valueOf(100)}));
        this.i.setTextColor(UIHelper.isNightTheme() ? -12171438 : -10263970);
        TextView textView = this.d;
        if (UIHelper.isNightTheme()) {
            i = -4359140;
        } else {
            i = -11215958;
        }
        textView.setTextColor(i);
        this.x = (ImageView) findViewById(R.id.create_new_group_head_img);
        this.x.setImageResource(UIHelper.isNightTheme() ? R.drawable.create_new_group_head_night : R.drawable.create_new_group_head);
        this.o = (TextView) findViewById(R.id.personal_info_remind);
        textView = this.o;
        if (UIHelper.isNightTheme()) {
            i = -9802626;
        } else {
            i = -10263970;
        }
        textView.setTextColor(i);
        this.p = (TextView) findViewById(R.id.create_new_group_location_remind);
        textView = this.p;
        if (UIHelper.isNightTheme()) {
            i = -9802626;
        } else {
            i = -10263970;
        }
        textView.setTextColor(i);
        this.l = (TextView) findViewById(R.id.create_new_group_remind);
        this.l.setTextColor(UIHelper.isNightTheme() ? -9802626 : -12894910);
        this.m = (TextView) findViewById(R.id.create_new_group_sub_remind);
        textView = this.m;
        if (UIHelper.isNightTheme()) {
            i = -12171438;
        } else {
            i = -10263970;
        }
        textView.setTextColor(i);
        this.n = (TextView) findViewById(R.id.create_new_group_condition);
        TextView textView2 = this.n;
        if (!UIHelper.isNightTheme()) {
            i3 = -10263970;
        }
        textView2.setTextColor(i3);
        this.s = (ImageView) findViewById(R.id.person_group_limit_img);
        this.t = (TextView) findViewById(R.id.person_group_limit_remind);
        this.u = (TextView) findViewById(R.id.person_group_limit_info);
        textView2 = this.t;
        if (UIHelper.isNightTheme()) {
            i5 = -12171438;
        }
        textView2.setTextColor(i5);
        TextView textView3 = this.u;
        if (UIHelper.isNightTheme()) {
            i = -4359140;
        } else {
            i = -11215958;
        }
        textView3.setTextColor(i);
        this.q = (TextView) findViewById(R.id.personal_info_set_remind);
        textView3 = this.q;
        if (UIHelper.isNightTheme()) {
            i = -4359140;
        } else {
            i = -11215958;
        }
        textView3.setTextColor(i);
        this.r = (ImageView) findViewById(R.id.personal_info_set_img);
        this.r.setImageResource(UIHelper.isNightTheme() ? R.drawable.goto_set_night : R.drawable.goto_set);
        this.v = (TextView) findViewById(R.id.personal_info_set_remind);
        textView2 = this.v;
        if (!UIHelper.isNightTheme()) {
            i2 = -11215958;
        }
        textView2.setTextColor(i2);
        this.w = (ImageView) findViewById(R.id.personal_info_set_img);
        this.w.setImageResource(UIHelper.isNightTheme() ? R.drawable.goto_set_night : R.drawable.goto_set);
        textView2 = this.j;
        if (!UIHelper.isNightTheme()) {
            i4 = -6908266;
        }
        textView2.setTextColor(i4);
        g();
    }

    private void f() {
        int i = R.drawable.create_suit;
        int i2 = R.drawable.create_not_suit_night;
        this.i.setText(String.format(this.k, new Object[]{Integer.valueOf(this.C)}));
        if (this.y) {
            this.c.setImageResource(UIHelper.isNightTheme() ? R.drawable.create_suit_night : R.drawable.create_suit);
            this.d.setVisibility(4);
        } else {
            this.c.setImageResource(UIHelper.isNightTheme() ? R.drawable.create_not_suit_night : R.drawable.create_not_suit);
            this.d.setText("（你还有" + this.B + "天才能建群）");
            this.d.setVisibility(0);
        }
        if (this.A) {
            this.s.setImageResource(UIHelper.isNightTheme() ? R.drawable.create_suit_night : R.drawable.create_suit);
            this.u.setVisibility(4);
            this.t.setText("未达加群上限");
        } else {
            this.s.setImageResource(UIHelper.isNightTheme() ? R.drawable.create_not_suit_night : R.drawable.create_not_suit);
            this.u.setText(String.format("已加入%s个群,最多可加入%s个群", new Object[]{Integer.valueOf(this.E), Integer.valueOf(this.D)}));
            this.u.setVisibility(0);
            this.t.setText("已达加群上限");
        }
        if (this.z) {
            this.e.setImageResource(UIHelper.isNightTheme() ? R.drawable.create_suit_night : R.drawable.create_suit);
            this.f.setVisibility(4);
        } else {
            this.e.setImageResource(UIHelper.isNightTheme() ? R.drawable.create_not_suit_night : R.drawable.create_not_suit);
            this.f.setVisibility(0);
            this.f.setOnClickListener(new jq(this));
        }
        boolean isLocationServiceEnabled = NearbyEngine.instance().isLocationServiceEnabled(getApplicationContext());
        if (isLocationServiceEnabled) {
            ImageView imageView = this.g;
            if (UIHelper.isNightTheme()) {
                i = R.drawable.create_suit_night;
            }
            imageView.setImageResource(i);
            this.h.setVisibility(4);
        } else {
            ImageView imageView2 = this.g;
            if (!UIHelper.isNightTheme()) {
                i2 = R.drawable.create_not_suit;
            }
            imageView2.setImageResource(i2);
            this.h.setVisibility(0);
            this.h.setOnClickListener(new jr(this));
        }
        if (this.y && this.z && isLocationServiceEnabled && this.A) {
            this.b.setVisibility(0);
            this.b.setText("立即建群");
            this.b.setTextColor(UIHelper.isNightTheme() ? -5066062 : -1);
            this.b.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.used_btn_yellow_night : R.drawable.used_btn_yellow);
            this.b.setOnClickListener(new js(this));
            this.j.setVisibility(0);
            return;
        }
        this.b.setVisibility(0);
        this.b.setText("不满足建群条件");
        this.b.setTextColor(UIHelper.isNightTheme() ? -9802626 : -6908266);
        this.b.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.not_used_button_night : R.drawable.not_used_button);
        this.j.setVisibility(4);
    }

    private void g() {
        new SimpleHttpTask(Constants.URL_CREATE_GROUP, new jt(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    protected void onResume() {
        super.onResume();
        if (this.a == 1) {
            this.a++;
        } else {
            f();
        }
    }

    private void i() {
        Intent intent = new Intent(getApplicationContext(), InfoCompleteActivity.class);
        intent.putExtra(InfoCompleteActivity.ACTION_KEY_FROM, 1);
        startActivityForResult(intent, 4);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 4 && i2 == -1) {
            this.z = true;
            f();
        }
        super.onActivityResult(i, i2, intent);
    }
}
