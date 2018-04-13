package qsbk.app.im;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

class js implements OnGlobalLayoutListener {
    final /* synthetic */ VoiceUIHelper a;

    js(VoiceUIHelper voiceUIHelper) {
        this.a = voiceUIHelper;
    }

    public void onGlobalLayout() {
        int[] iArr = new int[2];
        this.a.g.getLocationInWindow(iArr);
        int[] iArr2 = new int[2];
        this.a.t.getLocationOnScreen(iArr2);
        this.a.t.setY(((((float) iArr[1]) + this.a.t.getY()) + ((float) this.a.g.getHeight())) - ((float) (iArr2[1] + this.a.t.getHeight())));
    }
}
