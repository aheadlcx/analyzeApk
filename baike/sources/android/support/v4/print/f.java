package android.support.v4.print;

import android.os.CancellationSignal.OnCancelListener;

class f implements OnCancelListener {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public void onCancel() {
        this.a.e.a();
        this.a.cancel(false);
    }
}
