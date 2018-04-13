package qsbk.app.core.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.R;

class b implements OnClickListener {
    final /* synthetic */ DialogFragment a;

    b(DialogFragment dialogFragment) {
        this.a = dialogFragment;
    }

    public void onClick(View view) {
        if (this.a.j != null) {
            if (view.getId() == this.a.j.getContentView().findViewById(R.id.positive).getId()) {
                this.a.j.onPositiveActionClicked(this.a);
            }
            if (view.getId() == this.a.j.getContentView().findViewById(R.id.negative).getId()) {
                this.a.j.onNegativeActionClicked(this.a);
            }
        }
    }
}
