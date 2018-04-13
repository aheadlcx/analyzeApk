package com.tencent.connect.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.alipay.sdk.util.h;
import com.tencent.connect.a.a;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.open.TDialog;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.utils.i;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Set;
import org.json.JSONObject;

public class QzonePublish extends BaseApi {
    public static final String HULIAN_CALL_BACK = "hulian_call_back";
    public static final String HULIAN_EXTRA_SCENE = "hulian_extra_scene";
    public static final String PUBLISH_TO_QZONE_APP_NAME = "appName";
    public static final String PUBLISH_TO_QZONE_EXTMAP = "extMap";
    public static final String PUBLISH_TO_QZONE_IMAGE_URL = "imageUrl";
    public static final String PUBLISH_TO_QZONE_KEY_TYPE = "req_type";
    public static final String PUBLISH_TO_QZONE_SUMMARY = "summary";
    public static final int PUBLISH_TO_QZONE_TYPE_PUBLISHMOOD = 3;
    public static final int PUBLISH_TO_QZONE_TYPE_PUBLISHVIDEO = 4;
    public static final String PUBLISH_TO_QZONE_VIDEO_DURATION = "videoDuration";
    public static final String PUBLISH_TO_QZONE_VIDEO_PATH = "videoPath";
    public static final String PUBLISH_TO_QZONE_VIDEO_SIZE = "videoSize";

    public QzonePublish(Context context, QQToken qQToken) {
        super(qQToken);
    }

    public void publishToQzone(Activity activity, Bundle bundle, IUiListener iUiListener) {
        int i = 0;
        f.c("openSDK_LOG.QzonePublish", "publishToQzone() -- start");
        if (bundle == null) {
            iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_NULL_ERROR, null));
            f.e("openSDK_LOG.QzonePublish", "-->publishToQzone, params is null");
            d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_PARAM_NULL_ERROR);
        } else if (i.f((Context) activity)) {
            Object a = i.a((Context) activity);
            if (a == null) {
                a = bundle.getString("appName");
            } else if (a.length() > 20) {
                a = a.substring(0, 20) + "...";
            }
            if (!TextUtils.isEmpty(a)) {
                bundle.putString("appName", a);
            }
            int i2 = bundle.getInt("req_type");
            if (i2 == 3) {
                ArrayList stringArrayList = bundle.getStringArrayList("imageUrl");
                if (stringArrayList != null && stringArrayList.size() > 0) {
                    while (i < stringArrayList.size()) {
                        if (!i.h((String) stringArrayList.get(i))) {
                            stringArrayList.remove(i);
                            i--;
                        }
                        i++;
                    }
                    bundle.putStringArrayList("imageUrl", stringArrayList);
                }
                b(activity, bundle, iUiListener);
                f.c("openSDK_LOG.QzonePublish", "publishToQzone() --end");
            } else if (i2 == 4) {
                String string = bundle.getString(PUBLISH_TO_QZONE_VIDEO_PATH);
                if (i.h(string)) {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setOnPreparedListener(new d(this, string, bundle, activity, iUiListener));
                    mediaPlayer.setOnErrorListener(new e(this, iUiListener));
                    try {
                        mediaPlayer.setDataSource(string);
                        mediaPlayer.prepareAsync();
                        return;
                    } catch (Exception e) {
                        f.e("openSDK_LOG.QzonePublish", "publishToQzone() exception(s) occurred when preparing mediaplayer");
                        iUiListener.onError(new UiError(-5, Constants.MSG_PUBLISH_VIDEO_ERROR, null));
                        return;
                    }
                }
                f.e("openSDK_LOG.QzonePublish", "publishToQzone() video url invalid");
                iUiListener.onError(new UiError(-5, Constants.MSG_PUBLISH_VIDEO_ERROR, null));
            } else {
                iUiListener.onError(new UiError(-5, Constants.MSG_SHARE_TYPE_ERROR, null));
                f.e("openSDK_LOG.QzonePublish", "publishToQzone() error--end请选择支持的分享类型");
                d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "publishToQzone() 请选择支持的分享类型");
            }
        } else {
            iUiListener.onError(new UiError(-15, Constants.MSG_PARAM_VERSION_TOO_LOW, null));
            f.e("openSDK_LOG.QzonePublish", "-->publishToQzone, this is not support below qq 5.9.5");
            d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "publicToQzone, this is not support below qq 5.9.5");
            new TDialog(activity, "", a(""), null, this.b).show();
        }
    }

    private void b(Activity activity, Bundle bundle, IUiListener iUiListener) {
        String jSONObject;
        CharSequence appId;
        String openId;
        String str;
        StringBuffer stringBuffer;
        int size;
        int i;
        String str2;
        Intent intent;
        f.c("openSDK_LOG.QzonePublish", "doPublishToQzone() --start");
        StringBuffer stringBuffer2 = new StringBuffer("mqqapi://qzone/publish?src_type=app&version=1&file_type=news");
        ArrayList stringArrayList = bundle.getStringArrayList("imageUrl");
        Object string = bundle.getString("summary");
        int i2 = bundle.getInt("req_type", 3);
        Object string2 = bundle.getString("appName");
        String string3 = bundle.getString(PUBLISH_TO_QZONE_VIDEO_PATH);
        int i3 = bundle.getInt(PUBLISH_TO_QZONE_VIDEO_DURATION);
        long j = bundle.getLong(PUBLISH_TO_QZONE_VIDEO_SIZE);
        String str3 = "";
        try {
            Bundle bundle2 = bundle.getBundle("extMap");
            if (bundle2 != null) {
                Set<String> keySet = bundle2.keySet();
                JSONObject jSONObject2 = new JSONObject();
                for (String jSONObject3 : keySet) {
                    if (!TextUtils.isEmpty(bundle2.getString(jSONObject3))) {
                        jSONObject2.put(jSONObject3, bundle2.getString(jSONObject3));
                    }
                }
                if (jSONObject2.length() > 0) {
                    jSONObject3 = jSONObject2.toString();
                    str3 = jSONObject3;
                    appId = this.b.getAppId();
                    openId = this.b.getOpenId();
                    f.a("openSDK_LOG.QzonePublish", "openId:" + openId);
                    jSONObject3 = "";
                    if (3 == i2 && stringArrayList != null) {
                        str = "7";
                        stringBuffer = new StringBuffer();
                        size = stringArrayList.size();
                        for (i = 0; i < size; i++) {
                            stringBuffer.append(URLEncoder.encode((String) stringArrayList.get(i)));
                            if (i != size - 1) {
                                stringBuffer.append(h.b);
                            }
                        }
                        stringBuffer2.append("&image_url=" + Base64.encodeToString(i.i(stringBuffer.toString()), 2));
                        jSONObject3 = str;
                    }
                    if (4 == i2) {
                        jSONObject3 = Constants.VIA_SHARE_TYPE_PUBLISHVIDEO;
                        stringBuffer2.append("&videoPath=" + Base64.encodeToString(i.i(string3), 2));
                        stringBuffer2.append("&videoDuration=" + Base64.encodeToString(i.i(String.valueOf(i3)), 2));
                        stringBuffer2.append("&videoSize=" + Base64.encodeToString(i.i(String.valueOf(j)), 2));
                    }
                    str2 = jSONObject3;
                    if (!TextUtils.isEmpty(string)) {
                        stringBuffer2.append("&description=" + Base64.encodeToString(i.i(string), 2));
                    }
                    if (!TextUtils.isEmpty(appId)) {
                        stringBuffer2.append("&share_id=" + appId);
                    }
                    if (!TextUtils.isEmpty(string2)) {
                        stringBuffer2.append("&app_name=" + Base64.encodeToString(i.i(string2), 2));
                    }
                    if (!i.e(openId)) {
                        stringBuffer2.append("&open_id=" + Base64.encodeToString(i.i(openId), 2));
                    }
                    if (!TextUtils.isEmpty(r3)) {
                        stringBuffer2.append("&share_qzone_ext_str=" + Base64.encodeToString(i.i(r3), 2));
                    }
                    stringBuffer2.append("&req_type=" + Base64.encodeToString(i.i(String.valueOf(i2)), 2));
                    f.a("openSDK_LOG.QzonePublish", "doPublishToQzone, url: " + stringBuffer2.toString());
                    a.a(com.tencent.open.utils.d.a(), this.b, "requireApi", "shareToNativeQQ");
                    intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(stringBuffer2.toString()));
                    intent.putExtra("pkg_name", activity.getPackageName());
                    if (a(intent)) {
                        f.e("openSDK_LOG.QzonePublish", "doPublishToQzone() target activity not found");
                        d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent fail");
                        d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_SHARE_TO_QZONE, Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, "3", "1", str2, "0", "1", "0");
                    } else {
                        a(activity, Constants.REQUEST_QZONE_SHARE, intent, false);
                        d.a().a(0, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent success");
                        d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_SHARE_TO_QZONE, Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, "3", "1", str2, "0", "1", "0");
                    }
                    f.c("openSDK_LOG", "doPublishToQzone() --end");
                }
            }
            jSONObject3 = str3;
            str3 = jSONObject3;
        } catch (Throwable e) {
            f.b("openSDK_LOG.QzonePublish", "publishToQzone()  --error parse extmap", e);
        }
        appId = this.b.getAppId();
        openId = this.b.getOpenId();
        f.a("openSDK_LOG.QzonePublish", "openId:" + openId);
        jSONObject3 = "";
        str = "7";
        stringBuffer = new StringBuffer();
        size = stringArrayList.size();
        for (i = 0; i < size; i++) {
            stringBuffer.append(URLEncoder.encode((String) stringArrayList.get(i)));
            if (i != size - 1) {
                stringBuffer.append(h.b);
            }
        }
        stringBuffer2.append("&image_url=" + Base64.encodeToString(i.i(stringBuffer.toString()), 2));
        jSONObject3 = str;
        if (4 == i2) {
            jSONObject3 = Constants.VIA_SHARE_TYPE_PUBLISHVIDEO;
            stringBuffer2.append("&videoPath=" + Base64.encodeToString(i.i(string3), 2));
            stringBuffer2.append("&videoDuration=" + Base64.encodeToString(i.i(String.valueOf(i3)), 2));
            stringBuffer2.append("&videoSize=" + Base64.encodeToString(i.i(String.valueOf(j)), 2));
        }
        str2 = jSONObject3;
        if (TextUtils.isEmpty(string)) {
            stringBuffer2.append("&description=" + Base64.encodeToString(i.i(string), 2));
        }
        if (TextUtils.isEmpty(appId)) {
            stringBuffer2.append("&share_id=" + appId);
        }
        if (TextUtils.isEmpty(string2)) {
            stringBuffer2.append("&app_name=" + Base64.encodeToString(i.i(string2), 2));
        }
        if (i.e(openId)) {
            stringBuffer2.append("&open_id=" + Base64.encodeToString(i.i(openId), 2));
        }
        if (TextUtils.isEmpty(r3)) {
            stringBuffer2.append("&share_qzone_ext_str=" + Base64.encodeToString(i.i(r3), 2));
        }
        stringBuffer2.append("&req_type=" + Base64.encodeToString(i.i(String.valueOf(i2)), 2));
        f.a("openSDK_LOG.QzonePublish", "doPublishToQzone, url: " + stringBuffer2.toString());
        a.a(com.tencent.open.utils.d.a(), this.b, "requireApi", "shareToNativeQQ");
        intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(stringBuffer2.toString()));
        intent.putExtra("pkg_name", activity.getPackageName());
        if (a(intent)) {
            f.e("openSDK_LOG.QzonePublish", "doPublishToQzone() target activity not found");
            d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent fail");
            d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_SHARE_TO_QZONE, Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, "3", "1", str2, "0", "1", "0");
        } else {
            a(activity, Constants.REQUEST_QZONE_SHARE, intent, false);
            d.a().a(0, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent success");
            d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_SHARE_TO_QZONE, Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, "3", "1", str2, "0", "1", "0");
        }
        f.c("openSDK_LOG", "doPublishToQzone() --end");
    }
}
