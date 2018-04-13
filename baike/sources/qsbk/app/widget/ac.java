package qsbk.app.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class ac implements OnClickListener {
    ac() {
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
