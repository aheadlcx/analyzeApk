package com.spriteapp.booklibrary.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;

class DialogUtil$3 implements OnKeyListener {
    DialogUtil$3() {
    }

    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return i == 4;
    }
}
