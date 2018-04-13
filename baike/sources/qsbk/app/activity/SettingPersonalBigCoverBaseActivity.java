package qsbk.app.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import com.qiniu.auth.Authorizer;
import com.qiniu.io.IO;
import com.qiniu.rs.PutExtra;
import com.qiniu.rs.UploadTaskExecutor;
import java.util.HashMap;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpTask;
import qsbk.app.model.UserInfo;

public abstract class SettingPersonalBigCoverBaseActivity extends Activity {
    public static final String PHOTO_DIR = (Environment.getExternalStorageDirectory() + "/qsbk.app/photo");
    private static final String b = SettingPersonalBigCoverBaseActivity.class.getSimpleName();
    protected UserInfo a;
    private Uri c;
    private Handler d = new Handler();
    private ProgressDialog e;

    protected abstract void a(Uri uri);

    protected void onStart() {
        super.onStart();
    }

    private void d() {
        if (this.e == null) {
            this.e = new ProgressDialog(this, 3);
            this.e.setProgressStyle(0);
            this.e.setMessage("上传大罩中，请稍等...");
            this.e.setCancelable(false);
        }
    }

    private void e() {
        this.d.post(new acm(this));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null && bundle.getString("mImageUri") != null) {
            this.c = Uri.parse(bundle.getString("mImageUri"));
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.c != null) {
            bundle.putString("mImageUri", this.c.toString());
        }
    }

    protected void a() {
        if (this.a != null) {
            Intent intent = new Intent(this, ImageClipActivity.class);
            intent.putExtra("userinfo", this.a);
            intent.putExtra("uri", this.c.toString());
            startActivityForResult(intent, 2);
        }
    }

    private UploadTaskExecutor a(String str, Uri uri) {
        String str2 = IO.UNDEFINED_KEY;
        PutExtra putExtra = new PutExtra();
        putExtra.params = new HashMap();
        Authorizer authorizer = new Authorizer();
        authorizer.setUploadToken(str);
        return IO.putFile(QsbkApp.getInstance().getApplicationContext(), authorizer, str2, uri, putExtra, new acn(this));
    }

    protected void b() {
        if (this.e != null) {
            this.e.cancel();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 0:
                a();
                return;
            case 1:
                if (intent != null) {
                    this.c = intent.getData();
                    a();
                    return;
                }
                return;
            case 2:
                if (intent != null) {
                    this.c = (Uri) intent.getParcelableExtra("uri");
                    e();
                    f();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void f() {
        String str = Constants.URL_GET_TOKEN;
        String str2 = Constants.URL_GET_TOKEN;
        Object[] objArr = new Object[1];
        QsbkApp.getInstance();
        objArr[0] = QsbkApp.currentUser.userId;
        new HttpTask(str, String.format(str2, objArr), new acp(this)).execute(new Void[0]);
    }

    private void a(String str) {
        new Builder(this).setMessage(str).setPositiveButton("重试", new acr(this)).setNegativeButton("取消", new acq(this)).show();
    }
}
