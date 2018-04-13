package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;

class ce implements OnClickListener {
    final /* synthetic */ ImageControlView a;

    ce(ImageControlView imageControlView) {
        this.a = imageControlView;
    }

    public void onClick(View view) {
        ImageControlView.a(this.a).onShareSelect(view);
    }
}
