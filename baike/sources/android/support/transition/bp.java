package android.support.transition;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

@RequiresApi(18)
class bp implements bq {
    private final ViewGroupOverlay a;

    bp(@NonNull ViewGroup viewGroup) {
        this.a = viewGroup.getOverlay();
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

    public void add(@NonNull View view) {
        this.a.add(view);
    }

    public void remove(@NonNull View view) {
        this.a.remove(view);
    }
}
