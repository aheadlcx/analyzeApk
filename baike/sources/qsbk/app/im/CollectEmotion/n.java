package qsbk.app.im.CollectEmotion;

import android.net.Uri;
import qsbk.app.activity.publish.QiniuUploaderForCollect.OnUploadListener;
import qsbk.app.im.LatestUsedCollectionData;
import qsbk.app.im.image.ImageUploader;
import qsbk.app.utils.ToastAndDialog;

class n implements OnUploadListener {
    final /* synthetic */ ShowCollectActivity a;

    n(ShowCollectActivity showCollectActivity) {
        this.a = showCollectActivity;
    }

    public void onUploadSuccess(Uri uri, String str, String str2, int i, int i2) {
        this.a.g.insert(new LatestUsedCollectionData(new CollectImageLocal(str2, ImageUploader.img_prefix + str, i2, i)));
        this.a.h.getAll();
        this.a.init();
        this.a.i();
        if (this.a.a != null) {
            this.a.a.notifyDataSetChanged();
        }
        this.a.setTitle(this.a.f());
        ToastAndDialog.makePositiveToast(this.a, "收藏成功！").show();
    }

    public void onUploadFail(Uri uri, String str) {
        this.a.i();
        ToastAndDialog.makeNegativeToast(this.a, "收藏失败，请重试...").show();
    }
}
