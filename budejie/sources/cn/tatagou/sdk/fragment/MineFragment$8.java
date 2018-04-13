package cn.tatagou.sdk.fragment;

import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.android.TtgInterface;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.util.d;
import cn.tatagou.sdk.util.l;

class MineFragment$8 implements OnClickListener {
    final /* synthetic */ MineFragment a;

    MineFragment$8(MineFragment mineFragment) {
        this.a = mineFragment;
    }

    public void onClick(View view) {
        long uptimeMillis = SystemClock.uptimeMillis();
        long h = uptimeMillis - MineFragment.h(this.a);
        MineFragment.a(this.a, uptimeMillis);
        if (h < 600) {
            MineFragment.i(this.a);
            if (4 == MineFragment.j(this.a)) {
                MineFragment.a(this.a, 0);
                if (view.getId() == R.id.ttg_ly_mine) {
                    if (TtgSDK.getContext() != null) {
                        l.showToastCenter(TtgSDK.getContext(), "正在清除缓存数据");
                    }
                    TtgInterface.clearCache();
                    TtgInterface.clearDBCache();
                    return;
                } else if (this.a.isAdded()) {
                    l.showToastCenter(this.a.getActivity(), TtgSDK.isDebug ? "2.4.4.6\nURL:" + d.a : "2.4.4.6");
                    return;
                } else {
                    return;
                }
            }
            return;
        }
        MineFragment.a(this.a, 0);
    }
}
