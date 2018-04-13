package qsbk.app.api;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import com.qiniu.auth.Authorizer;
import com.qiniu.io.IO;
import com.qiniu.rs.PutExtra;
import com.qiniu.rs.UploadTaskExecutor;
import java.io.File;
import java.util.HashMap;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ImageClipActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.utils.ToastAndDialog;

public class BigCoverHelper {
    public static final String KEY_IMG_URI_PATH = "IMG_URI_PATH";
    public static final String PHOTO_DIR = (Environment.getExternalStorageDirectory() + "/qsbk.app/photo");
    public static final int REQCODE_ALBUM = 161;
    public static final int REQCODE_CAREMA = 160;
    public static final int REQCODE_CORP = 162;
    private static final String a = BigCoverHelper.class.getSimpleName();
    private Uri b;
    private Activity c;

    public interface UploadListener {
        void onFail(int i, String str);

        void onSuccess(Uri uri, String str);

        void onUploading(long j, long j2);
    }

    public BigCoverHelper(Activity activity, Bundle bundle) {
        this.c = activity;
        if (bundle != null && !TextUtils.isEmpty(bundle.getString(KEY_IMG_URI_PATH))) {
            this.b = Uri.parse(bundle.getString(KEY_IMG_URI_PATH));
        }
    }

    public Uri createCameraImageUri() {
        File file = new File(PHOTO_DIR);
        if (!file.exists()) {
            file.mkdir();
        }
        return Uri.fromFile(new File(PHOTO_DIR, "IMG_" + System.currentTimeMillis() + ".jpg"));
    }

    public void setImageUri(Uri uri) {
        this.b = uri;
    }

    public String getImageURIPath() {
        return this.b == null ? null : this.b.getPath();
    }

    public void startCrop() {
        if (this.b == null) {
            throw new RuntimeException("ImageUri not set yet.");
        }
        Intent intent = new Intent(this.c, ImageClipActivity.class);
        intent.putExtra("userinfo", QsbkApp.currentUser);
        intent.putExtra("uri", this.b.toString());
        this.c.startActivityForResult(intent, 162);
    }

    public void startCamera() {
        if (this.b == null) {
            throw new RuntimeException("ImageUri should be set before.");
        } else if (Environment.getExternalStorageState().equals("mounted")) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra("output", this.b);
            this.c.startActivityForResult(intent, REQCODE_CAREMA);
        } else {
            ToastAndDialog.makeNegativeToast(this.c, "没有SD卡...", Integer.valueOf(1)).show();
        }
    }

    public void startAlbum() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            Intent intent = new Intent("android.intent.action.GET_CONTENT", null);
            intent.setType("image/*");
            this.c.startActivityForResult(intent, 161);
            return;
        }
        ToastAndDialog.makeNegativeToast(this.c, "没有SD卡...", Integer.valueOf(1)).show();
    }

    private UploadTaskExecutor a(String str, Uri uri, UploadListener uploadListener) {
        String str2 = IO.UNDEFINED_KEY;
        PutExtra putExtra = new PutExtra();
        putExtra.params = new HashMap();
        Authorizer authorizer = new Authorizer();
        authorizer.setUploadToken(str);
        return IO.putFile(QsbkApp.getInstance().getApplicationContext(), authorizer, str2, uri, putExtra, new a(this, str, uploadListener, uri));
    }

    public void upload(UploadListener uploadListener, Uri uri) {
        new c(this, uploadListener, uri).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (!TextUtils.isEmpty(getImageURIPath())) {
            bundle.putString(KEY_IMG_URI_PATH, getImageURIPath());
        }
    }
}
