package com.spriteapp.booklibrary.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class DialogUtil$2 implements OnClickListener {
    DialogUtil$2() {
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        if (DialogUtil.access$000() != null) {
            DialogUtil.access$000().clickCancelPay();
        }
    }
}
