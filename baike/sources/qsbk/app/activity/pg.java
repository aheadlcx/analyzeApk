package qsbk.app.activity;

import qsbk.app.imagepicker.ImagePickerManager.OnInitCompletedListener;

class pg implements OnInitCompletedListener {
    final /* synthetic */ ImagesPickerActivity a;

    pg(ImagesPickerActivity imagesPickerActivity) {
        this.a = imagesPickerActivity;
    }

    public void onCompleted() {
        if (!this.a.isFinishing()) {
            this.a.runOnUiThread(new ph(this));
        }
    }
}
