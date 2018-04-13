package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class po implements OnClickListener {
    final /* synthetic */ ImagesPickerForCollectActivity a;

    po(ImagesPickerForCollectActivity imagesPickerForCollectActivity) {
        this.a = imagesPickerForCollectActivity;
    }

    public void onClick(View view) {
        if (this.a.m) {
            this.a.j();
        } else {
            this.a.i();
        }
    }
}
