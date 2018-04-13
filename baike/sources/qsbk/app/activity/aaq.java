package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

class aaq implements OnClickListener {
    final /* synthetic */ ReAuthActivity a;

    aaq(ReAuthActivity reAuthActivity) {
        this.a = reAuthActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent(this.a, ActionBarLoginActivity.class);
        intent.addFlags(67108864);
        this.a.startActivity(intent);
        this.a.finish();
    }
}
