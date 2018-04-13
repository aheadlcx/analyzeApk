package qsbk.app.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import qsbk.app.activity.NearByActivity;

class kv implements OnClickListener {
    final /* synthetic */ Activity a;
    final /* synthetic */ RandomDoorUsersFragment b;

    kv(RandomDoorUsersFragment randomDoorUsersFragment, Activity activity) {
        this.b = randomDoorUsersFragment;
        this.a = activity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.startActivity(new Intent(this.a, NearByActivity.class));
        this.a.finish();
    }
}
