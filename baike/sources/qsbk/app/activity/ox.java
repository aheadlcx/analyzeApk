package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

class ox implements OnClickListener {
    final /* synthetic */ ImagesPickerActivity a;

    ox(ImagesPickerActivity imagesPickerActivity) {
        this.a = imagesPickerActivity;
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this.a, ImagePreviewActivity.class);
        intent.putExtra(ImagePreviewActivity.KEY_CHECKED_ARRAY, this.a.e);
        intent.putExtra(ImagePreviewActivity.KEY_PIC_ALL, this.a.e);
        intent.putExtra("KEY_PICK_IAMGE", this.a.u);
        this.a.startActivityForResult(intent, 99);
    }
}
