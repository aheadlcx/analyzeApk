package qsbk.app.activity.group;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class d implements OnClickListener {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ SplashAdActivity c;

    d(SplashAdActivity splashAdActivity, Context context, String str) {
        this.c = splashAdActivity;
        this.a = context;
        this.b = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.c.a.postDelayed(this.c.e, 1000);
        this.c.b(this.a, this.b);
        dialogInterface.dismiss();
    }
}
