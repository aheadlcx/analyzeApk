package qsbk.app.nearby.ui;

import qsbk.app.nearby.ui.SizeChangeScrollView.OnSizeChangeListener;
import qsbk.app.utils.LogUtil;

class r implements OnSizeChangeListener {
    final /* synthetic */ InfoCompleteActivity a;

    r(InfoCompleteActivity infoCompleteActivity) {
        this.a = infoCompleteActivity;
    }

    public void onSizeChange(int i, int i2, int i3, int i4) {
        LogUtil.d("focus status change");
    }
}
