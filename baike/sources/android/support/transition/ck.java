package android.support.transition;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.WindowId;

@RequiresApi(18)
class ck implements cl {
    private final WindowId a;

    ck(@NonNull View view) {
        this.a = view.getWindowId();
    }

    public boolean equals(Object obj) {
        return (obj instanceof ck) && ((ck) obj).a.equals(this.a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
