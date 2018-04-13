package qsbk.app.activity.publish;

import android.net.Uri;
import qsbk.app.activity.publish.QiniuVideoUploader.OnUploadListener;

class z implements OnUploadListener {
    final /* synthetic */ CirclePublishActivity a;

    z(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void onUploadSuccess(Uri uri, String str) {
        this.a.X = str;
        this.a.submitContent();
    }

    public void onUploadFail(Uri uri) {
    }
}
