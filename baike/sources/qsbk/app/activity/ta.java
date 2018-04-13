package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;

class ta implements OnClickListener {
    final /* synthetic */ MainActivity$c a;

    ta(MainActivity$c mainActivity$c) {
        this.a = mainActivity$c;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            Intent intent = new Intent("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", this.a.a.getPackageName());
            intent.putExtra("app_uid", this.a.a.getApplicationInfo().uid);
            this.a.a.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                Intent intent2 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent2.setData(Uri.fromParts("package", this.a.a.getPackageName(), null));
                this.a.a.startActivity(intent2);
            } catch (Exception e2) {
                e.printStackTrace();
            }
        }
    }
}
