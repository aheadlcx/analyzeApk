package qsbk.app.activity.group;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class e implements OnClickListener {
    final /* synthetic */ SplashAdActivity a;

    e(SplashAdActivity splashAdActivity) {
        this.a = splashAdActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.a.postDelayed(this.a.e, 1000);
        dialogInterface.dismiss();
    }
}
