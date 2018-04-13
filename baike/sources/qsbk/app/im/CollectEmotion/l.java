package qsbk.app.im.CollectEmotion;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

class l implements OnCancelListener {
    final /* synthetic */ ShowCollectActivity a;

    l(ShowCollectActivity showCollectActivity) {
        this.a = showCollectActivity;
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.a.f != null) {
            this.a.f.cancel(true);
        }
    }
}
