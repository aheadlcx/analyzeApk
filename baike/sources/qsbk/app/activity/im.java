package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import qsbk.app.nearby.ui.InfoCompleteActivity;

class im implements OnClickListener {
    final /* synthetic */ il a;

    im(il ilVar) {
        this.a = ilVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent(this.a.a.getApplicationContext(), InfoCompleteActivity.class);
        intent.putExtra(InfoCompleteActivity.ACTION_KEY_FROM, 1);
        this.a.a.startActivity(intent);
    }
}
