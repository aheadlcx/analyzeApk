package qsbk.app.utils;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class a implements OnClickListener {
    final /* synthetic */ BrightnessSetting a;

    a(BrightnessSetting brightnessSetting) {
        this.a = brightnessSetting;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
