package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.ImageInfo;

class pu implements OnClickListener {
    final /* synthetic */ ImageInfo a;
    final /* synthetic */ c b;

    pu(c cVar, ImageInfo imageInfo) {
        this.b = cVar;
        this.a = imageInfo;
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this.b.a, ShowCollectImageActivity.class);
        intent.putExtra("uri", this.a.url.toString());
        this.b.a.startActivityForResult(intent, 99);
    }
}
