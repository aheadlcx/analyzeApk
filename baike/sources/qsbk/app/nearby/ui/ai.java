package qsbk.app.nearby.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ai implements OnClickListener {
    final /* synthetic */ InfoCompleteActivity a;

    ai(InfoCompleteActivity infoCompleteActivity) {
        this.a = infoCompleteActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                this.a.s.setImageUri(this.a.s.createCameraImageUri());
                this.a.s.startCamera();
                return;
            case 1:
                this.a.s.startAlbum();
                return;
            default:
                return;
        }
    }
}
