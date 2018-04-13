package in.srain.cube.views.ptr.header;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;

class d implements Callback {
    final /* synthetic */ MaterialProgressDrawable a;

    d(MaterialProgressDrawable materialProgressDrawable) {
        this.a = materialProgressDrawable;
    }

    public void invalidateDrawable(Drawable drawable) {
        this.a.invalidateSelf();
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        this.a.scheduleSelf(runnable, j);
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        this.a.unscheduleSelf(runnable);
    }
}
