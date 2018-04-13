package android.support.transition;

import android.os.IBinder;
import android.support.annotation.RequiresApi;

@RequiresApi(14)
class cj implements cl {
    private final IBinder a;

    cj(IBinder iBinder) {
        this.a = iBinder;
    }

    public boolean equals(Object obj) {
        return (obj instanceof cj) && ((cj) obj).a.equals(this.a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
