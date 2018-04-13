package qsbk.app.video;

import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import com.qiniu.auth.Authorizer;
import com.qiniu.io.IO;
import com.qiniu.rs.PutExtra;
import com.qiniu.rs.UploadTaskExecutor;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.im.image.UploadTask;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.LogUtil;

public class VideoUploader {

    public interface UploadVideoListener {
        void onFaiure(Uri uri, String str, Object obj);

        void onProgress(Uri uri, long j, long j2, Object obj);

        void onStart(Uri uri, Object obj);

        void onSuccess(Uri uri, String str, Object obj);

        void onTokenResp(TokenResp tokenResp);
    }

    public class TokenResp {
        final /* synthetic */ VideoUploader a;
        public String ip;
        public String key;
        public String token;

        public TokenResp(VideoUploader videoUploader, String str, String str2, String str3) {
            this.a = videoUploader;
            this.token = str;
            this.key = str2;
            this.ip = str3;
        }
    }

    public class VideoUploadTask extends UploadTask {
        final /* synthetic */ VideoUploader a;
        private AsyncTask b;
        private UploadTaskExecutor c;

        public VideoUploadTask(VideoUploader videoUploader) {
            this.a = videoUploader;
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

    public UploadTask sendVideo(Uri uri, UploadVideoListener uploadVideoListener, Object obj, TokenResp tokenResp) {
        UploadTask videoUploadTask = new VideoUploadTask(this);
        AsyncTask buVar = new bu(this, uploadVideoListener, uri, obj, tokenResp, videoUploadTask);
        videoUploadTask.setTokenTask(buVar);
        buVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        return videoUploadTask;
    }

    private TokenResp a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("count", Integer.valueOf(1));
        } catch (Exception e) {
        }
        try {
            String post = HttpClient.getIntentce().post("http://video.qiushibaike.com/video/getapptoken", jSONObject.toString());
            LogUtil.e("getapptoken:" + post);
            JSONObject jSONObject2 = new JSONObject(post);
            if (jSONObject2.optInt(NotificationCompat.CATEGORY_ERROR, -1) != 0) {
                return null;
            }
            JSONArray optJSONArray = jSONObject2.optJSONArray("upTokenList");
            if (optJSONArray.length() <= 0) {
                return null;
            }
            jSONObject2 = optJSONArray.getJSONObject(0);
            return new TokenResp(this, jSONObject2.optString("upToken"), jSONObject2.optString("video_name"), jSONObject2.optString("ip"));
        } catch (QiushibaikeException e2) {
            e2.printStackTrace();
            return null;
        } catch (JSONException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public HashMap<String, String> transHashParam(HashMap<String, String> hashMap) {
        HashMap<String, String> hashMap2 = new HashMap();
        for (String str : hashMap.keySet()) {
            hashMap2.put("x:" + str, hashMap.get(str));
        }
        return hashMap2;
    }

    private UploadTaskExecutor a(TokenResp tokenResp, Uri uri, UploadVideoListener uploadVideoListener, Object obj) {
        PutExtra putExtra = new PutExtra();
        HashMap hashMap = (HashMap) obj;
        hashMap.put("ip", tokenResp.ip);
        putExtra.params = transHashParam(hashMap);
        LogUtil.d("extra:" + hashMap);
        Authorizer authorizer = new Authorizer();
        authorizer.setUploadToken(tokenResp.token);
        return IO.putFile(QsbkApp.getInstance().getApplicationContext(), authorizer, tokenResp.key, uri, putExtra, new bv(this, tokenResp, uploadVideoListener, uri, obj));
    }
}
