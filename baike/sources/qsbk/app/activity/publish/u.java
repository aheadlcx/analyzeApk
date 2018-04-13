package qsbk.app.activity.publish;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import qsbk.app.nearby.ui.InfoCompleteActivity;

class u implements OnClickListener {
    final /* synthetic */ s a;

    u(s sVar) {
        this.a = sVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent(this.a.h.getApplicationContext(), InfoCompleteActivity.class);
        intent.putExtra(InfoCompleteActivity.ACTION_KEY_FROM, 1);
        this.a.h.startActivity(intent);
    }
}
