package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;

class cf implements OnClickListener {
    final /* synthetic */ ImageControlView a;

    cf(ImageControlView imageControlView) {
        this.a = imageControlView;
    }

    public void onClick(View view) {
        ImageControlView.a(this.a).onSaveSelect(view);
    }
}
