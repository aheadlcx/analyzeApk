package qsbk.app.activity;

import android.net.Uri;
import java.util.Map;
import qsbk.app.QsbkApp;
import qsbk.app.activity.publish.QiniuUploader.OnUploadListener;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.utils.ToastAndDialog;

class fr implements OnUploadListener {
    final /* synthetic */ Map a;
    final /* synthetic */ SimpleHttpTask b;
    final /* synthetic */ CircleArticleActivity c;

    fr(CircleArticleActivity circleArticleActivity, Map map, SimpleHttpTask simpleHttpTask) {
        this.c = circleArticleActivity;
        this.a = map;
        this.b = simpleHttpTask;
    }

    public void onUploadSuccess(Uri uri, String str) {
        this.a.put("pic_url", str);
        this.b.setMapParams(this.a);
        this.b.execute();
    }

    public void onUploadFail(Uri uri) {
        ToastAndDialog.makeText(QsbkApp.getInstance(), "图片上传失败").show();
    }
}
