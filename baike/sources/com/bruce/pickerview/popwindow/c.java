package com.bruce.pickerview.popwindow;

import com.bruce.pickerview.LoopScrollListener;

class c implements LoopScrollListener {
    final /* synthetic */ DatePickerPopWin a;

    c(DatePickerPopWin datePickerPopWin) {
        this.a = datePickerPopWin;
    }

    public void onItemSelect(int i) {
        this.a.j = i;
    }
}
