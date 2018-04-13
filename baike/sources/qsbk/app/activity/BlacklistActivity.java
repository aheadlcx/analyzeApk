package qsbk.app.activity;

import android.os.Bundle;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.fragments.BlacklistFragment;

public class BlacklistActivity extends BaseActionBarActivity {
    protected /* synthetic */ CharSequence getCustomTitle() {
        return c();
    }

    protected String c() {
        return "黑名单";
    }

    protected int a() {
        return R.layout.activity_fragment_container;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        getSupportFragmentManager().beginTransaction().add(R.id.container, new BlacklistFragment()).commitAllowingStateLoss();
    }
}
