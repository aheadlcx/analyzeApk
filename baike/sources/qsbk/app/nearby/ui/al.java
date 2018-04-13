package qsbk.app.nearby.ui;

import qsbk.app.core.AsyncTask;
import qsbk.app.nearby.api.NearbyEngine;

class al extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ JobEditorActivity a;

    al(JobEditorActivity jobEditorActivity) {
        this.a = jobEditorActivity;
    }

    protected Void a(Void... voidArr) {
        NearbyEngine.instance().updateUserConfig(this.a);
        return null;
    }
}
