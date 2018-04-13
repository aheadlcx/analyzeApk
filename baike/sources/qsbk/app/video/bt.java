package qsbk.app.video;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

class bt implements OnClickListener {
    final /* synthetic */ VideoRecordActivity a;

    bt(VideoRecordActivity videoRecordActivity) {
        this.a = videoRecordActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.startActivity(new Intent("android.settings.SETTINGS"));
    }
}
