package qsbk.app.nearby.ui;

import android.content.Context;
import qsbk.app.core.AsyncTask;
import qsbk.app.nearby.api.NearbyEngine;

class a extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ Context a;
    final /* synthetic */ HometownDialogFragment b;

    a(HometownDialogFragment hometownDialogFragment, Context context) {
        this.b = hometownDialogFragment;
        this.a = context;
    }

    protected Void a(Void... voidArr) {
        NearbyEngine.instance().updateUserConfig(this.a);
        return null;
    }
}
