package com.bruce.pickerview.popwindow;

import com.bruce.pickerview.LoopScrollListener;

class b implements LoopScrollListener {
    final /* synthetic */ DatePickerPopWin a;

    b(DatePickerPopWin datePickerPopWin) {
        this.a = datePickerPopWin;
    }

    public void onItemSelect(int i) {
        this.a.i = i;
        this.a.c();
    }
}
