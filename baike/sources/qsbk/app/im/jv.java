package qsbk.app.im;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.utils.ToastAndDialog;

class jv implements OnClickListener {
    final /* synthetic */ VoiceUIHelper a;

    jv(VoiceUIHelper voiceUIHelper) {
        this.a = voiceUIHelper;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        ToastAndDialog.makeNegativeToast(this.a.e, "取消授权").show();
    }
}
