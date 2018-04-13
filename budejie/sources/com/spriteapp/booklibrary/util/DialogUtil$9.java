package com.spriteapp.booklibrary.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.spriteapp.booklibrary.listener.DialogListener;

class DialogUtil$9 implements OnClickListener {
    final /* synthetic */ DialogListener val$dialogListener;

    DialogUtil$9(DialogListener dialogListener) {
        this.val$dialogListener = dialogListener;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.val$dialogListener != null) {
            this.val$dialogListener.clickSure();
        }
    }
}
