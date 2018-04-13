package qsbk.app.im.image;

import android.net.Uri;
import com.qiniu.auth.Authorizer;
import com.qiniu.io.IO;
import com.qiniu.rs.PutExtra;
import com.qiniu.rs.UploadTaskExecutor;
import java.io.File;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;

public class ImageUploader {
    public static final String im_get_token = "http://im.qiushibaike.com/pic/uptoken/";
    public static final String img_prefix = "http://qiubai-im.qiushibaike.com/";
    private String a;

    public interface UploadImageCallback {
        void onFaiure(Uri uri, String str, Object obj);

        void onProgress(Uri uri, long j, long j2, Object obj);

        void onStart(Uri uri, Object obj);

        void onSuccess(Uri uri, String str, Object obj);
    }

    public class ImageUploadTask extends UploadTask {
        final /* synthetic */ ImageUploader a;
        private AsyncTask b;
        private UploadTaskExecutor c;

        public ImageUploadTask(ImageUploader imageUploader) {
            this.a = imageUploader;
        }

        public void setTokenTask(AsyncTask asyncTask) {
            this.b = asyncTask;
        }

        public void setUploadTaskExecutor(UploadTaskExecutor uploadTaskExecutor) {
            this.c = uploadTaskExecutor;
        }

        public void cancel(boolean z) {
            if (this.b != null) {
                this.b.cancel(z);
            }
            if (this.c != null) {
                this.c.cancel();
            }
        }
    }

    public ImageUploader(String str) {
        this.a = str;
    }

    public UploadTask sendImage(Uri uri, UploadImageCallback uploadImageCallback, Object obj) {
        UploadTask imageUploadTask = new ImageUploadTask(this);
        AsyncTask cVar = new c(this, uploadImageCallback, uri, obj, imageUploadTask);
        imageUploadTask.setTokenTask(cVar);
        cVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        return imageUploadTask;
    }

    private String a(String str) {
        String str2 = "";
        try {
            str2 = new JSONObject(HttpClient.getIntentce().get(im_get_token + str)).optString("uptoken");
        } catch (QiushibaikeException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return str2;
    }

    private UploadTaskExecutor a(String str, Uri uri, String str2, UploadImageCallback uploadImageCallback, Object obj) {
        String str3 = IO.UNDEFINED_KEY;
        PutExtra putExtra = new PutExtra();
        putExtra.params = new HashMap();
        Authorizer authorizer = new Authorizer();
        authorizer.setUploadToken(str);
        Uri fromFile = Uri.fromFile(new File(str2));
        return IO.putFile(QsbkApp.getInstance().getApplicationContext(), authorizer, str3, fromFile, putExtra, new d(this, str2, str, uploadImageCallback, uri, obj));
    }
}
