package qsbk.app.video;

import android.view.View;
import android.view.View.OnClickListener;

class z implements OnClickListener {
    final /* synthetic */ VideoEditActivity a;

    z(VideoEditActivity videoEditActivity) {
        this.a = videoEditActivity;
    }

    public void onClick(View view) {
        this.a.t.dismiss();
    }
}
