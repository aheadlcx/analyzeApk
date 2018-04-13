package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;

class bl implements OnClickListener {
    final /* synthetic */ CircleVideoCell a;

    bl(CircleVideoCell circleVideoCell) {
        this.a = circleVideoCell;
    }

    public void onClick(View view) {
        this.a.onClick();
    }
}
