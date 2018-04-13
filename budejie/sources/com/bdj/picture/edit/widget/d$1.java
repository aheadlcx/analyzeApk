package com.bdj.picture.edit.widget;

import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import java.util.TimerTask;

class d$1 extends TimerTask {
    final /* synthetic */ d a;

    d$1(d dVar) {
        this.a = dVar;
    }

    public void run() {
        Log.d("showSoftInput", "showSoftInput");
        ((InputMethodManager) this.a.a.getContext().getSystemService("input_method")).showSoftInput(this.a.a, 0);
    }
}
