package cn.xiaochuankeji.tieba.ui.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.my.account.InputPhoneNumberActivity;
import cn.xiaochuankeji.tieba.ui.my.account.ModifyPasswordActivity;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class AccountSafeActivity extends h {
    private WebImageView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private View j;
    private NavigationBar k;

    public static void a(Context context) {
        context.startActivity(new Intent(context, AccountSafeActivity.class));
    }

    protected int a() {
        return R.layout.activity_account_safe;
    }

    protected boolean a(Bundle bundle) {
        return true;
    }

    protected void i_() {
        this.d = (WebImageView) findViewById(R.id.iv_avatar_member);
        this.e = (TextView) findViewById(R.id.tv_nickname);
        this.f = (TextView) findViewById(R.id.tv_login_type);
        this.g = (TextView) findViewById(R.id.tv_bindphone);
        this.h = (TextView) findViewById(R.id.tv_go_bind);
        this.i = (TextView) findViewById(R.id.tv_update_password);
        this.j = findViewById(R.id.go_set_password);
        j();
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AccountSafeActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (a.g().q().isBind()) {
                    UpdatePhoneTipActivity.a(this.a);
                } else {
                    InputPhoneNumberActivity.a(this.a, 113, "certify");
                }
            }
        });
        this.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AccountSafeActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                ModifyPasswordActivity.a(this.a);
            }
        });
        this.k = (NavigationBar) findViewById(R.id.navBar);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        j();
    }

    protected void onResume() {
        super.onResume();
        j();
    }

    private void j() {
        cn.xiaochuankeji.tieba.background.modules.a.a g = a.g();
        Member q = g.q();
        this.d.setWebImage(b.a(q.getId(), q.getAvatarID()));
        this.e.setText(g.d() ? "未登录" : q.getName());
        if (g.d()) {
            this.g.setVisibility(4);
        } else if (TextUtils.isEmpty(q.getPhone())) {
            this.g.setText("未绑定手机");
        } else {
            this.g.setText(q.getDisplayPhone());
        }
        if (!TextUtils.isEmpty(q.getPhone())) {
            this.h.setText("更换绑定");
        }
        if (!TextUtils.isEmpty(q.getPhone()) && q.getOpenType() <= 0) {
            this.h.setText("更换手机");
        }
        CharSequence charSequence = "手机";
        if (q.getOpenType() == 1) {
            charSequence = "QQ登录";
        } else if (q.getOpenType() == 2) {
            charSequence = "微信登录";
        } else if (q.getOpenType() == 3) {
            charSequence = "微博登录";
        }
        if (g.r()) {
            charSequence = "手机";
        }
        int i = a.a().getInt("kLoginBySMS", -1);
        if (q.getOpenType() > 0) {
            this.j.setVisibility(8);
        } else if (i == 1) {
            this.j.setVisibility(8);
        } else {
            this.j.setVisibility(0);
        }
        this.f.setText(charSequence);
    }
}
