package butterknife;

import android.support.annotation.UiThread;

public interface Unbinder {
    public static final Unbinder a = new Unbinder() {
        public void a() {
        }
    };

    @UiThread
    void a();
}
