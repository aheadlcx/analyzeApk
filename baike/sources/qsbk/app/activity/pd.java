package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class pd implements OnClickListener {
    final /* synthetic */ ImagesPickerActivity a;

    pd(ImagesPickerActivity imagesPickerActivity) {
        this.a = imagesPickerActivity;
    }

    public void onClick(View view) {
        if (this.a.r) {
            this.a.j();
        } else {
            this.a.i();
        }
    }
}
