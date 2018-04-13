package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.activity.BindPhoneActivity;

class dv implements OnClickListener {
    final /* synthetic */ du a;

    dv(du duVar) {
        this.a = duVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        BindPhoneActivity.launchForResult(this.a.a.getActivity(), 11);
    }
}
