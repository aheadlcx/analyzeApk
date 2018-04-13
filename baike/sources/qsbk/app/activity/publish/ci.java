package qsbk.app.activity.publish;

import android.net.Uri;
import qsbk.app.activity.publish.QiniuUploader.OnUploadListener;

class ci implements OnUploadListener {
    final /* synthetic */ int a;
    final /* synthetic */ QiushiPublishTask b;

    ci(QiushiPublishTask qiushiPublishTask, int i) {
        this.b = qiushiPublishTask;
        this.a = i;
    }

    public void onUploadSuccess(Uri uri, String str) {
        this.b.a(this.a, uri, str);
    }

    public void onUploadFail(Uri uri) {
        this.b.a(this.a);
    }
}
