package cn.tatagou.sdk.activity;

import android.support.v4.app.Fragment;
import cn.tatagou.sdk.fragment.TrackListFragment;

public class TrackListActivity extends SingleFragmentActivity {
    protected Fragment createFragment() {
        return TrackListFragment.newInstance();
    }
}
