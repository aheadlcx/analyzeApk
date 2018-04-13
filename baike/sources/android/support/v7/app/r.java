package android.support.v7.app;

import android.app.UiModeManager;
import android.content.Context;
import android.support.annotation.RequiresApi;
import android.view.ActionMode;
import android.view.Window;
import android.view.Window.Callback;

@RequiresApi(23)
class r extends p {
    private final UiModeManager t;

    class a extends a {
        final /* synthetic */ r d;

        a(r rVar, Callback callback) {
            this.d = rVar;
            super(rVar, callback);
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            if (this.d.isHandleNativeActionModesEnabled()) {
                switch (i) {
                    case 0:
                        return a(callback);
                }
            }
            return super.onWindowStartingActionMode(callback, i);
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            return null;
        }
    }

    r(Context context, Window window, AppCompatCallback appCompatCallback) {
        super(context, window, appCompatCallback);
        this.t = (UiModeManager) context.getSystemService("uimode");
    }

    Callback a(Callback callback) {
        return new a(this, callback);
    }

    int a(int i) {
        if (i == 0 && this.t.getNightMode() == 0) {
            return -1;
        }
        return super.a(i);
    }
}
