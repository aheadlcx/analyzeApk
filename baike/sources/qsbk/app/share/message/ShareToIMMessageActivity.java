package qsbk.app.share.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.DebugUtil;

public class ShareToIMMessageActivity extends BaseActionBarActivity {
    private static final String a = ShareToIMMessageActivity.class.getSimpleName();
    private View b;
    private Bundle c;
    private String d;
    private FragmentManager e;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 1) {
            String stringExtra = intent.getStringExtra(ShareUtils.SHARE_TO_QIUYOU_NAME);
            Intent intent2 = new Intent();
            intent2.putExtra(ShareUtils.SHARE_TO_QIUYOU_NAME, stringExtra);
            setResult(1, intent2);
        }
        finish();
    }

    protected String f() {
        return getResources().getString(R.string.choose_qiuyou);
    }

    protected int a() {
        return R.layout.activity_share_to_im_message;
    }

    protected void a(Bundle bundle) {
        DebugUtil.debug(a, "init");
        setActionbarBackable();
        if (TextUtils.isEmpty(this.d)) {
            this.d = QsbkApp.currentUser == null ? null : QsbkApp.currentUser.userId;
        }
        this.c = getIntent().getExtras();
        if (this.c == null) {
            finish();
            return;
        }
        g();
        i();
    }

    protected void g() {
        this.e = getSupportFragmentManager();
        this.e.beginTransaction().replace(R.id.content, RecentContactsFragment.newInstance(this.c)).commit();
    }

    private void i() {
        this.b = findViewById(R.id.rl_share_to_my_qiuyou);
        this.b.setOnClickListener(new k(this));
    }
}
