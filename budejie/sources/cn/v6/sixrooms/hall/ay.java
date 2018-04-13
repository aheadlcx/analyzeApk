package cn.v6.sixrooms.hall;

import android.view.View;
import android.view.View.OnClickListener;

final class ay implements OnClickListener {
    final /* synthetic */ MineFragment a;

    ay(MineFragment mineFragment) {
        this.a = mineFragment;
    }

    public final void onClick(View view) {
        this.a.getActivity().finish();
    }
}
