package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;

class cd implements OnClickListener {
    final /* synthetic */ ImageControlView a;

    cd(ImageControlView imageControlView) {
        this.a = imageControlView;
    }

    public void onClick(View view) {
        ImageControlView.a(this.a).onCommentSelect(view);
    }
}
