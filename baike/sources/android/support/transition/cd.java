package android.support.transition;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;

@RequiresApi(18)
class cd extends cc {
    cd() {
    }

    public by getOverlay(@NonNull View view) {
        return new bx(view);
    }

    public cl getWindowId(@NonNull View view) {
        return new ck(view);
    }
}
