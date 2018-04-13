package cn.tatagou.sdk.activity;

import android.support.v4.app.Fragment;
import cn.tatagou.sdk.fragment.FeedbackFragment;

public class FeedBackActivity extends SingleFragmentActivity {
    protected Fragment createFragment() {
        return FeedbackFragment.newInstance();
    }
}
