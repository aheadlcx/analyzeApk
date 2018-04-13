package qsbk.app.activity.publish;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.baidu.mobstat.StatService;
import com.facebook.common.util.UriUtil;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.Article;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.LogUtil;
import qsbk.app.video.VideoUploader;
import qsbk.app.video.VideoUploader.TokenResp;

public class QiushiPublishTask extends AsyncTask<Void, Void, List<UploadToken>> {
    public static final int RETRY_GETTOKEN = 0;
    public static final int RETRY_SUBMIT = -1;
    Article a;
    List<UploadToken> b = new ArrayList();
    ArrayList<String> c = new ArrayList();
    AsyncTask d;
    TokenResp e;

    public class UploadToken {
        String a;
        String b;
        String c;
        final /* synthetic */ QiushiPublishTask d;

        public UploadToken(QiushiPublishTask qiushiPublishTask, String str, String str2, String str3) {
            this.d = qiushiPublishTask;
            this.a = str;
            this.b = str2;
            this.c = str3;
        }
    }

    public QiushiPublishTask(Article article) {
        this.a = article;
    }

    protected List<UploadToken> a(Void... voidArr) {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.a.imageInfos == null || this.a.imageInfos.size() <= 0) {
            return null;
        }
        for (int i = 0; i < this.a.imageInfos.size(); i++) {
            ImageInfo imageInfo = (ImageInfo) this.a.imageInfos.get(i);
            stringBuilder.append(MediaFormat.getNetAlias(imageInfo.mediaFormat)).append(MqttTopic.TOPIC_LEVEL_SEPARATOR).append(imageInfo.width).append("x").append(imageInfo.height);
            if (i != this.a.imageInfos.size() - 1) {
                stringBuilder.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
            }
        }
        try {
            try {
                JSONObject jSONObject = new JSONObject(HttpClient.getIntentce().get(String.format(qsbk.app.Constants.ARTICLE_TOKENS, new Object[]{stringBuilder})));
                if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR, -1) == 0) {
                    JSONArray optJSONArray = jSONObject.optJSONArray("tokens");
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                        this.b.add(new UploadToken(this, jSONObject2.optString("token"), jSONObject2.optString(PayPWDUniversalActivity.KEY), jSONObject2.optString("bucket")));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                a(0);
            }
        } catch (QiushibaikeException e2) {
            e2.printStackTrace();
            a(0);
        }
        return this.b;
    }

    protected void a(List<UploadToken> list) {
        super.a(list);
        if (this.a.isWordsOnly()) {
            c();
        } else {
            upload(0);
        }
    }

    public void upload(int i) {
        if (this.a.isVideoArticle()) {
            a(UriUtil.getUriForFile(new File(this.a.filePath)));
            return;
        }
        Uri parse = Uri.parse(((ImageInfo) this.a.imageInfos.get(i)).url);
        if (this.b == null || this.b.size() <= i) {
            a(i);
        } else {
            uploadImage(i, (UploadToken) this.b.get(i), parse);
        }
    }

    private void a(Uri uri) {
        VideoUploader videoUploader = new VideoUploader();
        HashMap hashMap = new HashMap();
        hashMap.put("content", this.a.content);
        hashMap.put("user_id", QsbkApp.currentUser.userId);
        hashMap.put("uuid", DeviceUtils.getAndroidId());
        hashMap.put("anonymous", String.valueOf(this.a.anonymous ? 1 : 0));
        hashMap.put("source", HttpClient.getClientSource());
        if (this.a.publish_location != null) {
            hashMap.put(ParamKey.LONGITUDE, String.valueOf(this.a.publish_location.longitude));
            hashMap.put(ParamKey.LATITUDE, String.valueOf(this.a.publish_location.latitude));
            hashMap.put("city", this.a.publish_location.city);
            hashMap.put("district", this.a.publish_location.district);
        }
        if (PublishActivity.mIsFromLocal) {
            hashMap.put("video_source", "local");
            StatService.onEvent(QsbkApp.getInstance(), "local_video_upload", "upload");
        } else {
            hashMap.put("video_source", "record");
            if (this.a.mIsVideoFacingFront) {
                StatService.onEvent(QsbkApp.getInstance(), "record_front_video_upload", "upload");
            } else {
                StatService.onEvent(QsbkApp.getInstance(), "record_back_video_upload", "upload");
            }
        }
        if (!TextUtils.isEmpty(this.a.mHashOrigin)) {
            LogUtil.d("mHashOrigin:" + this.a.mHashOrigin);
            hashMap.put("hash_org", this.a.mHashOrigin.toLowerCase());
        }
        hashMap.put("display", this.a.display + "");
        if (this.a.qiushiTopic != null) {
            hashMap.put("topic_id", String.valueOf(this.a.qiushiTopic.id));
        }
        videoUploader.sendVideo(uri, new ch(this), hashMap, this.e);
    }

    public void uploadImage(int i, UploadToken uploadToken, Uri uri) {
        new QiniuUploader(uploadToken.a, uploadToken.b, uri, new ci(this, i)).startUpload();
    }

    private void a(int i, Uri uri, String str) {
        this.c.add(str);
        if (!d()) {
            if (i == this.b.size() - 1) {
                c();
            } else {
                upload(i + 1);
            }
        }
    }

    private void a(int i) {
        a(i, null);
    }

    private void a(int i, String str) {
        PublishActivity.isPublishingArticle.remove(this.a.uuid);
        PublishActivity.changeFromLocal(this.a);
        Intent intent = new Intent();
        intent.putExtra("sendData", this.a);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("err_msg", str);
        }
        intent.setAction("_KEY_PUBLISH_ARTICLE_FAILED_");
        LocalBroadcastManager.getInstance(QsbkApp.getInstance()).sendBroadcast(intent);
    }

    private void c() {
        if (this.d == null) {
            this.d = new cj(this);
            this.d.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
        }
    }

    public String getPostParams() {
        JSONObject jSONObject = new JSONObject();
        try {
            HashMap hashMap = new HashMap();
            jSONObject.put("content", this.a.content);
            jSONObject.put("anonymous", this.a.anonymous);
            if (this.a.publish_location != null) {
                jSONObject.put(ParamKey.LONGITUDE, this.a.publish_location.longitude);
                jSONObject.put(ParamKey.LATITUDE, this.a.publish_location.latitude);
                jSONObject.put("city", this.a.publish_location.city);
                jSONObject.put("district", this.a.publish_location.district);
            }
            jSONObject.put("display", this.a.display);
            jSONObject.put("allow_comment", this.a.isPublishArticle);
            if (this.a.qiushiTopic != null) {
                jSONObject.put("topic_id", this.a.qiushiTopic.id);
            }
            if (this.c != null && this.c.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                Iterator it = this.c.iterator();
                while (it.hasNext()) {
                    jSONArray.put((String) it.next());
                }
                jSONObject.put("attachments", jSONArray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    private void a(String str, JSONObject jSONObject) {
        PublishActivity.clearDraft();
        PublishActivity.isPublishingArticle.remove(this.a.uuid);
        PublishActivity.removeFromLocal(this.a);
        if (this.a.isWordsOnly()) {
            Intent intent = new Intent();
            intent.putExtra("sendData", this.a);
            intent.setAction("_KEY_PUBLISH_ARTICLE_SUCC_");
            LocalBroadcastManager.getInstance(QsbkApp.getInstance()).sendBroadcast(intent);
            return;
        }
        new Timer().schedule(new ck(this), Config.BPLUS_DELAY_TIME);
    }

    private boolean d() {
        return false;
    }
}
