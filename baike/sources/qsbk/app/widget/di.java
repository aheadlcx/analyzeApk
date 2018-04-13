package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;

class di implements OnClickListener {
    final /* synthetic */ PunchCardCell a;

    di(PunchCardCell punchCardCell) {
        this.a = punchCardCell;
    }

    public void onClick(View view) {
        this.a.goCardWeb();
    }
}
