package qsbk.app.slide;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.activity.BindPhoneActivity;

class s implements OnClickListener {
    final /* synthetic */ r a;

    s(r rVar) {
        this.a = rVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        BindPhoneActivity.launchForResult(this.a.a.getActivity(), 0);
    }
}
