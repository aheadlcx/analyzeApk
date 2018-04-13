package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.VideoImmersionCircleActivity;

class bc implements OnClickListener {
    final /* synthetic */ CircleVideoCell a;

    bc(CircleVideoCell circleVideoCell) {
        this.a = circleVideoCell;
    }

    public void onClick(View view) {
        VideoImmersionCircleActivity.launch(this.a.getContext(), this.a.getItem(), this.a.player.getCurrentTime(), true);
    }
}
