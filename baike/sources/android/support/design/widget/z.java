package android.support.design.widget;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback;
import android.view.View;

class z extends BottomSheetCallback {
    final /* synthetic */ BottomSheetDialog a;

    z(BottomSheetDialog bottomSheetDialog) {
        this.a = bottomSheetDialog;
    }

    public void onStateChanged(@NonNull View view, int i) {
        if (i == 5) {
            this.a.cancel();
        }
    }

    public void onSlide(@NonNull View view, float f) {
    }
}
