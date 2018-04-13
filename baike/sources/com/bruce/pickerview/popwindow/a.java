package com.bruce.pickerview.popwindow;

import com.bruce.pickerview.LoopScrollListener;

class a implements LoopScrollListener {
    final /* synthetic */ DatePickerPopWin a;

    a(DatePickerPopWin datePickerPopWin) {
        this.a = datePickerPopWin;
    }

    public void onItemSelect(int i) {
        this.a.h = i;
        this.a.c();
    }
}
