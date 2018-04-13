package com.spriteapp.booklibrary.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class DialogUtil$7 implements OnClickListener {
    DialogUtil$7() {
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (DialogUtil.access$100() != null) {
            DialogUtil.access$100().deleteBook();
        }
    }
}
