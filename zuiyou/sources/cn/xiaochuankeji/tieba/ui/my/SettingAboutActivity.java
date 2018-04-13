package cn.xiaochuankeji.tieba.ui.my;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.htjyb.c.e;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuan.jsbridge.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.debug.DebugOptionsActivity;
import cn.xiaochuankeji.tieba.ui.my.licence.LicenceActivity;
import cn.xiaochuankeji.tieba.webview.WebActivity;

public class SettingAboutActivity extends h implements OnClickListener {
    private ImageView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;

    public static void a(Context context) {
        context.startActivity(new Intent(context, SettingAboutActivity.class));
    }

    protected int a() {
        return R.layout.activity_about;
    }

    protected void i_() {
        this.d = (ImageView) findViewById(R.id.logo_zuiyou);
        this.e = (TextView) findViewById(R.id.tvVersion);
        this.f = (TextView) findViewById(R.id.tvCommunityRules);
        this.g = (TextView) findViewById(R.id.tvCommunityManageRules);
        this.h = (TextView) findViewById(R.id.tvUserRules);
        this.i = (TextView) findViewById(R.id.tvRightOfPrivacyRules);
        this.j = (TextView) findViewById(R.id.tvKnowledgeRules);
        this.k = (TextView) findViewById(R.id.tvReliefRules);
        this.f.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.k.setOnClickListener(this);
        findViewById(R.id.license).setOnClickListener(this);
        this.e.setText("V " + e.a(BaseApplication.getAppContext()));
        this.f.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ SettingAboutActivity a;

            {
                this.a = r1;
            }

            public boolean onLongClick(View view) {
                DebugOptionsActivity.a(this.a);
                return true;
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvCommunityRules:
                WebActivity.a(this, b.a(null, a.d("https://$$/help/appointment.html")));
                return;
            case R.id.tvCommunityManageRules:
                WebActivity.a(this, b.a(null, a.d("https://$$/help/rule.html")));
                return;
            case R.id.tvUserRules:
                WebActivity.a(this, b.a(null, a.d("https://$$/help/user.html")));
                return;
            case R.id.tvRightOfPrivacyRules:
                WebActivity.a(this, b.a(null, a.d("https://$$/help/private.html")));
                return;
            case R.id.tvKnowledgeRules:
                WebActivity.a(this, b.a(null, a.d("https://$$/help/knowledge.html")));
                return;
            case R.id.tvReliefRules:
                WebActivity.a(this, b.a(null, a.d("https://$$/help/relief.html")));
                return;
            case R.id.license:
                LicenceActivity.a(this);
                return;
            default:
                return;
        }
    }
}
