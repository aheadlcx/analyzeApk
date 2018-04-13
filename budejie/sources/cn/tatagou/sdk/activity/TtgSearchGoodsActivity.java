package cn.tatagou.sdk.activity;

import android.support.v4.app.Fragment;
import cn.tatagou.sdk.fragment.TtgSearchGoodsFragment;

public class TtgSearchGoodsActivity extends SingleFragmentActivity {
    private TtgSearchGoodsFragment a;

    protected Fragment createFragment() {
        this.a = TtgSearchGoodsFragment.newInstance();
        return this.a;
    }
}
