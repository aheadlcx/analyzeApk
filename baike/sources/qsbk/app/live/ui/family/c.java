package qsbk.app.live.ui.family;

import qsbk.app.core.widget.DialogFragment;
import qsbk.app.core.widget.SimpleDialog.Builder;

class c extends Builder {
    final /* synthetic */ b a;

    c(b bVar, int i) {
        this.a = bVar;
        super(i);
    }

    public void onPositiveActionClicked(DialogFragment dialogFragment) {
        super.onPositiveActionClicked(dialogFragment);
        this.a.a.finish();
    }
}
