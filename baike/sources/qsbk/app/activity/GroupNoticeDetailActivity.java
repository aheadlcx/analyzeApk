package qsbk.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.im.TimeUtils;
import qsbk.app.im.datastore.GroupNoticeStore;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.GroupBriefInfo;
import qsbk.app.model.GroupNotice;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UserClickDelegate;

public class GroupNoticeDetailActivity extends BaseActionBarActivity {
    public static final int REQ_CODE = 158;
    private static GroupNotice a;
    private GroupNoticeStore b;
    private GroupNotice c;
    private RelativeLayout d;
    private ImageView e;
    private TextView f;
    private LinearLayout g;
    private ImageView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private View m;
    private TextView n;
    private TextView o;
    private View p;
    private int q;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return e();
    }

    public static void launch(Activity activity, int i, GroupNotice groupNotice) {
        Intent intent = new Intent(activity, GroupNoticeDetailActivity.class);
        intent.putExtra("pos", i);
        activity.startActivityForResult(intent, REQ_CODE);
        a = groupNotice;
    }

    protected String e() {
        return "群通知";
    }

    protected int a() {
        return R.layout.activity_group_notice_detail;
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day.GroupInfo);
        }
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.c = a;
        a = null;
        if (this.c == null) {
            finish();
        }
        this.q = getIntent().getIntExtra("pos", 0);
        f();
        g();
        setResult(0);
    }

    private void f() {
        this.d = (RelativeLayout) findViewById(R.id.group_notice_detail_lin);
        this.e = (ImageView) findViewById(R.id.avatar);
        this.f = (TextView) findViewById(R.id.name);
        this.g = (LinearLayout) findViewById(R.id.gender_age);
        this.h = (ImageView) findViewById(R.id.gender);
        this.i = (TextView) findViewById(R.id.age);
        this.j = (TextView) findViewById(R.id.info);
        this.k = (TextView) findViewById(R.id.msg);
        this.l = (TextView) findViewById(R.id.time);
        this.m = findViewById(R.id.button);
        this.n = (TextView) findViewById(R.id.cancel);
        this.o = (TextView) findViewById(R.id.submit);
        this.p = findViewById(R.id.divider);
    }

    private void a(int i) {
        if (i == 0) {
            OnClickListener nxVar = new nx(this);
            this.n.setText("拒绝");
            this.n.setOnClickListener(nxVar);
            this.o.setText("同意");
            this.o.setOnClickListener(nxVar);
            this.n.setVisibility(0);
            this.o.setEnabled(true);
            return;
        }
        this.n.setVisibility(8);
        this.n.setOnClickListener(null);
        TextView textView = this.o;
        CharSequence charSequence = i == 1 ? "已同意" : i == 2 ? "已拒绝" : "已申请";
        textView.setText(charSequence);
        this.o.setOnClickListener(null);
        this.o.setEnabled(false);
    }

    private void g() {
        int i = -12171438;
        int i2 = -12871305;
        int i3 = -15132130;
        this.d.setBackgroundColor(UIHelper.isNightTheme() ? -14803421 : -1);
        this.p.setBackgroundColor(UIHelper.isNightTheme() ? -15329253 : -1184275);
        this.j.setTextColor(UIHelper.isNightTheme() ? -12171438 : -6908266);
        this.k.setTextColor(UIHelper.isNightTheme() ? -12171426 : -10263970);
        TextView textView = this.l;
        if (!UIHelper.isNightTheme()) {
            i = -6908266;
        }
        textView.setTextColor(i);
        this.o.setTextColor(UIHelper.isNightTheme() ? -12871305 : -17899);
        textView = this.n;
        if (!UIHelper.isNightTheme()) {
            i2 = -17899;
        }
        textView.setTextColor(i2);
        this.o.setBackgroundColor(UIHelper.isNightTheme() ? -15132130 : -263173);
        textView = this.n;
        if (!UIHelper.isNightTheme()) {
            i3 = -263173;
        }
        textView.setBackgroundColor(i3);
        this.j.setText(this.c.title);
        this.l.setText(TimeUtils.formatTime(this.c.time));
        if (this.c.type == 1 || this.c.type == 2) {
            BaseUserInfo baseUserInfo = this.c.user;
            Object absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(baseUserInfo.userIcon, baseUserInfo.userId);
            if (TextUtils.isEmpty(absoluteUrlOfMediumUserIcon)) {
                this.e.setImageResource(UIHelper.getDefaultAvatar());
            } else {
                FrescoImageloader.displayAvatar(this.e, absoluteUrlOfMediumUserIcon);
            }
            this.e.setOnClickListener(new UserClickDelegate(baseUserInfo.userId, new nz(this, baseUserInfo)));
            this.f.setText(baseUserInfo.userName);
            this.g.setVisibility(0);
            if (!UIHelper.isNightTheme()) {
                if ("F".equalsIgnoreCase(baseUserInfo.gender)) {
                    this.g.setBackgroundResource(R.drawable.pinfo_female_bg);
                    this.h.setImageResource(R.drawable.pinfo_female);
                } else {
                    this.g.setBackgroundResource(R.drawable.pinfo_man_bg);
                    this.h.setImageResource(R.drawable.pinfo_male);
                }
                this.i.setTextColor(-1);
            } else if ("F".equalsIgnoreCase(baseUserInfo.gender)) {
                this.h.setImageResource(R.drawable.pinfo_female_dark);
                this.i.setTextColor(getResources().getColor(R.color.age_female));
            } else {
                this.h.setImageResource(R.drawable.pinfo_male_dark);
                this.i.setTextColor(getResources().getColor(R.color.age_male));
            }
            this.k.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            this.k.setText(this.c.reason);
            this.i.setText(baseUserInfo.age + "");
            a(this.c.act);
            return;
        }
        GroupBriefInfo groupBriefInfo = this.c.tribe;
        if (TextUtils.isEmpty(groupBriefInfo.icon)) {
            this.e.setImageResource(UIHelper.getDefaultAvatar());
        } else {
            FrescoImageloader.displayAvatar(this.e, groupBriefInfo.icon);
        }
        this.f.setText(groupBriefInfo.name);
        if (this.c.type == 3 || this.c.type == 4 || this.c.type == 10) {
            this.k.setText("处理人：" + this.c.handler);
        } else {
            this.k.setText("");
        }
        this.g.setVisibility(4);
        this.n.setVisibility(8);
        if (this.c.type == 4) {
            int i4;
            this.o.setText("重新申请");
            this.o.setEnabled(true);
            this.o.setOnClickListener(new oa(this));
            TextView textView2 = this.k;
            if (UIHelper.isNightTheme()) {
                i4 = R.drawable.group_attention_night;
            } else {
                i4 = R.drawable.group_attention;
            }
            textView2.setCompoundDrawablesWithIntrinsicBounds(i4, 0, 0, 0);
            return;
        }
        if (this.c.type == 3) {
            this.o.setText("已同意你加入");
        } else {
            this.m.setVisibility(8);
        }
        this.o.setOnClickListener(null);
        this.o.setEnabled(false);
        this.k.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = GroupNoticeStore.getInstance(QsbkApp.currentUser.userId);
    }

    public void onDestroy() {
        super.onDestroy();
        this.b = null;
    }
}
