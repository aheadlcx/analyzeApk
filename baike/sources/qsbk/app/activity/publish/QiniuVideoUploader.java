package qsbk.app.activity.publish;

import android.net.Uri;
import com.qiniu.auth.Authorizer;
import com.qiniu.io.IO;
import com.qiniu.rs.UploadTaskExecutor;
import java.io.File;
import qsbk.app.QsbkApp;

public class QiniuVideoUploader {
    private OnUploadListener a;
    private String b;
    private Uri c;
    private String d;

    public interface OnUploadListener {
        void onUploadFail(Uri uri);

        void onUploadSuccess(Uri uri, String str);
    }

    public QiniuVideoUploader(String str, String str2, OnUploadListener onUploadListener) {
        this(str, Uri.fromFile(new File(str2)), onUploadListener);
    }

    public QiniuVideoUploader(String str, Uri uri, OnUploadListener onUploadListener) {
        this.d = IO.UNDEFINED_KEY;
        this.b = str;
        this.c = uri;
        this.a = onUploadListener;
    }

    public QiniuVideoUploader(String str, String str2, Uri uri, OnUploadListener onUploadListener) {
        this.d = IO.UNDEFINED_KEY;
        this.b = str;
        this.d = str2;
        this.c = uri;
        this.a = onUploadListener;
    }

    public void startUpload() {
        a();
    }

    private UploadTaskExecutor a() {
        Authorizer authorizer = new Authorizer();
        authorizer.setUploadToken(this.b);
        IO.putFile(QsbkApp.getInstance().getApplicationContext(), authorizer, this.d, this.c, null, new cg(this));
        return null;
    }
}
