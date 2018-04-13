package qsbk.app.activity.publish;

import android.net.Uri;
import com.baidu.mobstat.Config;
import com.xiaomi.mipush.sdk.Constants;
import qsbk.app.activity.publish.QiniuUploader.OnUploadListener;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.media.MediaFormat;

class x implements OnUploadListener {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ CirclePublishActivity c;

    x(CirclePublishActivity circlePublishActivity, int i, String str) {
        this.c = circlePublishActivity;
        this.a = i;
        this.b = str;
    }

    public void onUploadSuccess(Uri uri, String str) {
        int i = ((ImageInfo) this.c.y.get(this.a)).mediaFormat == MediaFormat.IMAGE_GIF ? MediaFormat.IMAGE_GIF.upload : MediaFormat.IMAGE_STATIC.upload;
        if (this.a == 0) {
            this.c.B = str + Config.TRACE_TODAY_VISIT_SPLIT + i;
        } else {
            this.c.B = this.c.B + Constants.ACCEPT_TIME_SEPARATOR_SP + str + Config.TRACE_TODAY_VISIT_SPLIT + i;
        }
        if (!this.c.r()) {
            if (this.a == this.c.y.size() - 1) {
                this.c.submitContent();
            } else {
                this.c.uploadImage(this.a + 1, this.b);
            }
        }
    }

    public void onUploadFail(Uri uri) {
        this.c.a(this.a);
    }
}
