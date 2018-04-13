package qsbk.app.im.CollectEmotion;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.ImagesPickerForCollectActivity;

class o implements OnClickListener {
    final /* synthetic */ a a;

    o(a aVar) {
        this.a = aVar;
    }

    public void onClick(View view) {
        this.a.a.startActivityForResult(ImagesPickerForCollectActivity.prepareIntent(this.a.a), 102);
    }
}
