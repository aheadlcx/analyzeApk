package qsbk.app.slide;

import qsbk.app.utils.KeyboardUtils.OnKeyboardVisibleChangeListener;

class bn implements OnKeyboardVisibleChangeListener {
    final /* synthetic */ SlideActivity a;

    bn(SlideActivity slideActivity) {
        this.a = slideActivity;
    }

    public void onKeyboardVisible(boolean z) {
        this.a.r = z;
    }
}
