package qsbk.app.activity.publish;

import android.net.Uri;
import com.qiniu.auth.Authorizer;
import com.qiniu.io.IO;
import java.io.File;
import qsbk.app.QsbkApp;

class cc implements a {
    final /* synthetic */ Authorizer a;
    final /* synthetic */ QiniuUploader b;

    cc(QiniuUploader qiniuUploader, Authorizer authorizer) {
        this.b = qiniuUploader;
        this.a = authorizer;
    }

    public void onDone(String str) {
        Uri fromFile;
        File file = new File(str);
        if (file.exists()) {
            fromFile = Uri.fromFile(file);
        } else {
            fromFile = null;
        }
        IO.putFile(QsbkApp.getInstance().getApplicationContext(), this.a, this.b.d, fromFile, null, new cd(this));
    }
}
