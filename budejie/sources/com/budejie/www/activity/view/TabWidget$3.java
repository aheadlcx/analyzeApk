package com.budejie.www.activity.view;

import android.os.Handler;
import android.os.Message;

class TabWidget$3 extends Handler {
    final /* synthetic */ TabWidget a;

    TabWidget$3(TabWidget tabWidget) {
        this.a = tabWidget;
    }

    public void handleMessage(Message message) {
        TabWidget.h(this.a).setCurrentItem(TabWidget.c(this.a) + 1, true);
        this.a.b();
    }
}
