package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ml implements OnClickListener {
    final /* synthetic */ GroupInfoActivity a;

    ml(GroupInfoActivity groupInfoActivity) {
        this.a = groupInfoActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                this.a.e("disturb");
                break;
            case 1:
                this.a.e("cheat");
                break;
            case 2:
                this.a.e("politics");
                break;
            case 3:
                this.a.e("porn");
                break;
            case 4:
                this.a.e("other");
                break;
        }
        dialogInterface.dismiss();
    }
}
