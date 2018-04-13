package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import qsbk.app.activity.security.FindPasswordActivity;

class y implements OnClickListener {
    final /* synthetic */ x a;

    y(x xVar) {
        this.a = xVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                this.a.a.startActivity(new Intent(this.a.a, FindPasswordActivity.class));
                return;
            case 1:
                ResetPwdActivity.launch(this.a.a);
                return;
            default:
                return;
        }
    }
}
