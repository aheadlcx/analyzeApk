package qsbk.app.live.ui.bag;

import android.content.Intent;
import android.support.v4.app.Fragment;
import com.r0adkll.slidr.Slidr;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.R;

public class BagActivity extends BaseActivity {
    protected int getLayoutId() {
        return R.layout.activity_bag;
    }

    protected void initView() {
        setTitle("我的背包");
        setUp();
        Slidr.attach(this, AppUtils.getEdgeSlidrConfig());
        findViewById(R.id.tv_market).setOnClickListener(new a(this));
    }

    protected void initData() {
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1101) {
            Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.fragment);
            if (findFragmentById != null) {
                findFragmentById.onActivityResult(i, i2, intent);
            }
        }
    }
}
