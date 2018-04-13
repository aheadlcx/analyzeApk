package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

class pl implements OnClickListener {
    final /* synthetic */ pk a;

    pl(pk pkVar) {
        this.a = pkVar;
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this.a.b.a, ImagePreviewActivity.class);
        intent.putExtra(ImagePreviewActivity.KEY_CHECKED_ARRAY, this.a.b.a.e);
        intent.putExtra(ImagePreviewActivity.KEY_PIC_ALL, this.a.b.a.e);
        intent.putExtra("KEY_PICK_IAMGE", this.a.b.a.u);
        this.a.b.a.startActivityForResult(intent, 99);
    }
}
