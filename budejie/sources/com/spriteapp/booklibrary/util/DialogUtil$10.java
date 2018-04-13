package com.spriteapp.booklibrary.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.spriteapp.booklibrary.listener.DialogListener;

class DialogUtil$10 implements OnClickListener {
    final /* synthetic */ DialogListener val$dialogListener;

    DialogUtil$10(DialogListener dialogListener) {
        this.val$dialogListener = dialogListener;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        if (this.val$dialogListener != null) {
            this.val$dialogListener.clickCancel();
        }
    }
}
