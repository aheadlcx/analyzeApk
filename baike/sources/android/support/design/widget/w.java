package android.support.design.widget;

import android.view.View;
import android.view.View.OnClickListener;

class w implements OnClickListener {
    final /* synthetic */ BottomSheetDialog a;

    w(BottomSheetDialog bottomSheetDialog) {
        this.a = bottomSheetDialog;
    }

    public void onClick(View view) {
        if (this.a.a && this.a.isShowing() && this.a.a()) {
            this.a.cancel();
        }
    }
}
