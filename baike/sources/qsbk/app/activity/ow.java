package qsbk.app.activity;

import android.content.Intent;
import java.io.File;
import qsbk.app.imagepicker.ImageFolderInfo;
import qsbk.app.model.ImageInfo;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.ResultActivityListener;

class ow implements ResultActivityListener {
    final /* synthetic */ ImagesPickerActivity a;

    ow(ImagesPickerActivity imagesPickerActivity) {
        this.a = imagesPickerActivity;
    }

    public void onResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            ImageInfo imageInfo = new ImageInfo(this.a.v.toString());
            FileUtils.notifyFileChanged(this.a, new File(this.a.v.getPath()));
            if (this.a.a != null && this.a.a.size() > 0) {
                ((ImageFolderInfo) this.a.a.get(0)).addImage(0, imageInfo);
            }
            this.a.b.add(1, imageInfo);
            this.a.e.add(imageInfo);
            this.a.l();
            this.a.d.notifyDataSetChanged();
        }
    }
}
