package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.ToastUtil;

class da implements OnClickListener {
    final /* synthetic */ LaiseeEventGetFragment a;

    da(LaiseeEventGetFragment laiseeEventGetFragment) {
        this.a = laiseeEventGetFragment;
    }

    public void onClick(View view) {
        ToastUtil.Short("红包记录请去小纸条查看，坨坨已经把红包发给你");
        this.a.getActivity().finish();
    }
}
