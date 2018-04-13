package cn.v6.sixrooms.room.red;

import android.os.Handler;
import android.os.Message;

final class b extends Handler {
    final /* synthetic */ DragRedPackagePopupWindow a;

    b(DragRedPackagePopupWindow dragRedPackagePopupWindow) {
        this.a = dragRedPackagePopupWindow;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                if (DragRedPackagePopupWindow.a(this.a) > 0) {
                    DragRedPackagePopupWindow.b(this.a);
                    DragRedPackagePopupWindow.c(this.a).setText(DragRedPackagePopupWindow.a(this.a) + "秒");
                    if (DragRedPackagePopupWindow.a(this.a) != 0) {
                        DragRedPackagePopupWindow.g(this.a).sendEmptyMessageDelayed(1, 1000);
                        return;
                    } else if (DragRedPackagePopupWindow.d(this.a)) {
                        DragRedPackagePopupWindow.f(this.a);
                        return;
                    } else {
                        DragRedPackagePopupWindow.e(this.a);
                        return;
                    }
                }
                return;
            case 2:
                DragRedPackagePopupWindow.h(this.a);
                DragRedPackagePopupWindow.i(this.a)[1].setAlpha(1.0f);
                DragRedPackagePopupWindow.a(DragRedPackagePopupWindow.i(this.a)[1]);
                return;
            case 3:
                this.a.setResult(3);
                return;
            default:
                return;
        }
    }
}
