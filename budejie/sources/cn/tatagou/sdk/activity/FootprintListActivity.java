package cn.tatagou.sdk.activity;

import android.support.v4.app.Fragment;
import cn.tatagou.sdk.fragment.FootprintFragment;

public class FootprintListActivity extends SingleFragmentActivity {
    protected Fragment createFragment() {
        return FootprintFragment.newInstance();
    }
}
