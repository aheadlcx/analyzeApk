package cn.tatagou.sdk.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import cn.tatagou.sdk.fragment.MineFragment;

public class TtgMineActivity extends SingleFragmentActivity {
    private static final String TAG = TtgMineActivity.class.getSimpleName();
    private MineFragment mMineFragment;

    protected Fragment createFragment() {
        this.mMineFragment = MineFragment.newInstance(true);
        return this.mMineFragment;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.mMineFragment != null) {
            this.mMineFragment.onActivityResult(i, i2, intent);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (this.mMineFragment != null) {
            this.mMineFragment.onBackPressed();
        }
    }
}
