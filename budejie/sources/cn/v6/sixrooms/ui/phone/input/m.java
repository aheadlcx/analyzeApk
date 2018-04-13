package cn.v6.sixrooms.ui.phone.input;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

final class m implements OnTouchListener {
    final /* synthetic */ PrivateInputDialog a;

    m(PrivateInputDialog privateInputDialog) {
        this.a = privateInputDialog;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            if (this.a.d.getPullDownViewRect().contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY()) && this.a.d != null) {
                this.a.d.hidePrivateChatView();
            }
        }
        return false;
    }
}
