package qsbk.app.activity;

import qsbk.app.imagepicker.ImagePickerManager.OnInitCompletedListener;

class pr implements OnInitCompletedListener {
    final /* synthetic */ ImagesPickerForCollectActivity a;

    pr(ImagesPickerForCollectActivity imagesPickerForCollectActivity) {
        this.a = imagesPickerForCollectActivity;
    }

    public void onCompleted() {
        if (!this.a.isFinishing()) {
            this.a.runOnUiThread(new ps(this));
        }
    }
}
