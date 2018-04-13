package qsbk.app.live.debug;

import android.support.v4.app.Fragment;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.live.LivePullLauncher;
import qsbk.app.live.R;

public class LiveDebugMainActivity extends BaseActivity {
    protected int getLayoutId() {
        return R.layout.activity_debug_main;
    }

    protected void initView() {
    }

    protected void initData() {
        a(LiveDebugListFragment.newInstance(), LivePullLauncher.STSOURCE_livelist);
    }

    protected void a(Fragment fragment, String str) {
        if (getSupportFragmentManager().findFragmentByTag(str) == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, str).commitAllowingStateLoss();
        }
    }
}
