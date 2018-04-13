package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import qsbk.app.QsbkApp;
import qsbk.app.model.GroupBriefInfo;
import qsbk.app.utils.ToastAndDialog;

class ca implements OnClickListener {
    final /* synthetic */ GroupBriefInfo a;
    final /* synthetic */ Bundle b;
    final /* synthetic */ ContactMyGroupFragment c;

    ca(ContactMyGroupFragment contactMyGroupFragment, GroupBriefInfo groupBriefInfo, Bundle bundle) {
        this.c = contactMyGroupFragment;
        this.a = groupBriefInfo;
        this.b = bundle;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        ContactMyGroupFragment.a(this.c, this.a, this.b);
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已分享给" + this.a.name, Integer.valueOf(0)).show();
        this.c.getActivity().finish();
    }
}
