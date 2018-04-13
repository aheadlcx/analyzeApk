package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class jw implements OnClickListener {
    final /* synthetic */ EditGenderActivity a;

    jw(EditGenderActivity editGenderActivity) {
        this.a = editGenderActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
