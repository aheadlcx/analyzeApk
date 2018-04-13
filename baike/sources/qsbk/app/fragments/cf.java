package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.ToastAndDialog;

class cf implements OnClickListener {
    final /* synthetic */ BaseUserInfo a;
    final /* synthetic */ ContactQiuYouFragment b;

    cf(ContactQiuYouFragment contactQiuYouFragment, BaseUserInfo baseUserInfo) {
        this.b = contactQiuYouFragment;
        this.a = baseUserInfo;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DebugUtil.debug(ContactQiuYouFragment.l, "确定, which=" + i);
        this.b.c(this.a);
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已分享给" + this.a.userName, Integer.valueOf(0)).show();
        this.b.getActivity().finish();
    }
}
