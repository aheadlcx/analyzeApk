package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

class oz implements OnClickListener {
    final /* synthetic */ oy a;

    oz(oy oyVar) {
        this.a = oyVar;
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this.a.a, ImagePreviewActivity.class);
        intent.putExtra(ImagePreviewActivity.KEY_CHECKED_ARRAY, this.a.a.e);
        intent.putExtra(ImagePreviewActivity.KEY_PIC_ALL, this.a.a.e);
        intent.putExtra("KEY_PICK_IAMGE", this.a.a.u);
        this.a.a.startActivityForResult(intent, 99);
    }
}
