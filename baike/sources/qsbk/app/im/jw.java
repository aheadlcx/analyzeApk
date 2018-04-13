package qsbk.app.im;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

class jw implements OnClickListener {
    final /* synthetic */ VoiceUIHelper a;

    jw(VoiceUIHelper voiceUIHelper) {
        this.a = voiceUIHelper;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.e.startActivity(new Intent("android.settings.SETTINGS"));
    }
}
