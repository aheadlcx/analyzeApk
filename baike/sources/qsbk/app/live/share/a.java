package qsbk.app.live.share;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

class a implements OnCancelListener {
    final /* synthetic */ ImageShareActivity a;

    a(ImageShareActivity imageShareActivity) {
        this.a = imageShareActivity;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.a.finish();
    }
}
