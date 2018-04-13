package qsbk.app.thirdparty.ui;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class b implements OnTouchListener {
    final /* synthetic */ ThirdPartyDialog a;

    b(ThirdPartyDialog thirdPartyDialog) {
        this.a = thirdPartyDialog;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.a.cancel();
        return false;
    }
}
