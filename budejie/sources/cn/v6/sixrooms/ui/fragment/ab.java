package cn.v6.sixrooms.ui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import cn.v6.sixrooms.R;

final class ab implements OnClickListener {
    final /* synthetic */ MsgVerifyFragment a;

    ab(MsgVerifyFragment msgVerifyFragment) {
        this.a = msgVerifyFragment;
    }

    public final void onClick(View view) {
        if (TextUtils.isEmpty(this.a.getCode())) {
            MsgVerifyFragment.d(this.a).showToast(this.a.getActivity().getResources().getString(R.string.phone_verify_empty));
            return;
        }
        switch (MsgVerifyFragment.b(this.a)) {
            case 1:
                MsgVerifyFragment.e(this.a).bindPhone();
                return;
            case 2:
                MsgVerifyFragment.f(this.a).unbindPhone();
                return;
            case 3:
                MsgVerifyFragment.g(this.a).remoteLogin();
                return;
            default:
                return;
        }
    }
}
