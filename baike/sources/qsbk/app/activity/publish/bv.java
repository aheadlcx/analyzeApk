package qsbk.app.activity.publish;

import android.view.View;
import android.view.View.OnClickListener;

class bv implements OnClickListener {
    final /* synthetic */ Publish_Image a;

    bv(Publish_Image publish_Image) {
        this.a = publish_Image;
    }

    public void onClick(View view) {
        this.a.setResult(0);
        this.a.finish();
    }
}
