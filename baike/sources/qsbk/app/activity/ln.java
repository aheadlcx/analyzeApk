package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;

class ln implements OnClickListener {
    final /* synthetic */ FinishGroupActivity a;

    ln(FinishGroupActivity finishGroupActivity) {
        this.a = finishGroupActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.t.show();
        this.a.upload(this.a, Uri.parse(this.a.j));
    }
}
