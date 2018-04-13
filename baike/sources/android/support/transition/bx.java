package android.support.transition;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewOverlay;

@RequiresApi(18)
class bx implements by {
    private final ViewOverlay a;

    bx(@NonNull View view) {
        this.a = view.getOverlay();
    }

    public void add(@NonNull Drawable drawable) {
        this.a.add(drawable);
    }

    public void clear() {
        this.a.clear();
    }

    public void remove(@NonNull Drawable drawable) {
        this.a.remove(drawable);
    }
}
