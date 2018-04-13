package qsbk.app.activity.publish;

import android.net.Uri;
import com.qiniu.auth.Authorizer;
import com.qiniu.io.IO;
import com.qiniu.rs.PutExtra;
import java.io.File;
import qsbk.app.QsbkApp;

class ce implements a {
    final /* synthetic */ Authorizer a;
    final /* synthetic */ String b;
    final /* synthetic */ PutExtra c;
    final /* synthetic */ QiniuUploaderForCollect d;

    ce(QiniuUploaderForCollect qiniuUploaderForCollect, Authorizer authorizer, String str, PutExtra putExtra) {
        this.d = qiniuUploaderForCollect;
        this.a = authorizer;
        this.b = str;
        this.c = putExtra;
    }

    public void onDone(String str, int i, int i2) {
        IO.putFile(QsbkApp.getInstance().getApplicationContext(), this.a, this.b, Uri.fromFile(new File(str)), this.c, new cf(this, str, i, i2));
    }
}
