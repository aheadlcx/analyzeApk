package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import qsbk.app.QsbkApp;
import qsbk.app.model.GroupBriefInfo;
import qsbk.app.utils.ToastAndDialog;

class fz implements OnClickListener {
    final /* synthetic */ GroupBriefInfo a;
    final /* synthetic */ Bundle b;
    final /* synthetic */ MyGroupFragment c;

    fz(MyGroupFragment myGroupFragment, GroupBriefInfo groupBriefInfo, Bundle bundle) {
        this.c = myGroupFragment;
        this.a = groupBriefInfo;
        this.b = bundle;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        MyGroupFragment.a(this.c, this.a, this.b);
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已分享给" + this.a.name, Integer.valueOf(0)).show();
        this.c.getActivity().finish();
    }
}
