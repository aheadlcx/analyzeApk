package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class om implements OnClickListener {
    final /* synthetic */ ImageClipActivity a;

    om(ImageClipActivity imageClipActivity) {
        this.a = imageClipActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
