package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import qsbk.app.nearby.ui.InfoCompleteActivity;

class fp implements OnClickListener {
    final /* synthetic */ fo a;

    fp(fo foVar) {
        this.a = foVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent(this.a.b.getApplicationContext(), InfoCompleteActivity.class);
        intent.putExtra(InfoCompleteActivity.ACTION_KEY_FROM, 1);
        this.a.b.startActivity(intent);
    }
}
