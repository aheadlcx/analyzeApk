package qsbk.app.utils;

import qsbk.app.QsbkApp;

class ar extends OnScrollDirectionListener {
    final /* synthetic */ QiuyouCircleFragmentNotificationViewHelper a;

    ar(QiuyouCircleFragmentNotificationViewHelper qiuyouCircleFragmentNotificationViewHelper) {
        this.a = qiuyouCircleFragmentNotificationViewHelper;
    }

    public void onScrollUp() {
        if (QsbkApp.currentUser != null && this.a.g != null && this.a.g.getVisibility() != 8) {
            this.a.d.removeCallbacks(this.a.h);
            this.a.d.removeCallbacks(this.a.i);
            this.a.d.post(this.a.i);
        }
    }

    public void onScrollDown() {
        if (QsbkApp.currentUser != null && this.a.g != null && this.a.g.getVisibility() != 0 && this.a.c.canShowQiuyouquanNotificationView()) {
            this.a.d.removeCallbacks(this.a.h);
            this.a.d.removeCallbacks(this.a.i);
            this.a.d.post(this.a.h);
        }
    }
}
