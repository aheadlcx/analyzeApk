package com.tencent.open;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Toast;
import com.alipay.sdk.util.h;
import com.qq.e.comm.constants.Constants.KEYS;
import com.tencent.connect.a.a;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.connect.share.QQShare;
import com.tencent.open.a.f;
import com.tencent.open.b.c;
import com.tencent.open.b.d;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.ThreadManager;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class GameAppOperation extends BaseApi {
    public static final String GAME_FRIEND_ADD_MESSAGE = "add_msg";
    public static final String GAME_FRIEND_LABEL = "friend_label";
    public static final String GAME_FRIEND_OPENID = "fopen_id";
    public static final String GAME_SIGNATURE = "signature";
    public static final String GAME_UNION_ID = "unionid";
    public static final String GAME_UNION_NAME = "union_name";
    public static final String GAME_ZONE_ID = "zoneid";
    public static final char PIC_SYMBOLE = '\u0014';
    public static final String QQFAV_DATALINE_APPNAME = "app_name";
    public static final String QQFAV_DATALINE_AUDIOURL = "audioUrl";
    public static final String QQFAV_DATALINE_DESCRIPTION = "description";
    public static final String QQFAV_DATALINE_FILEDATA = "file_data";
    public static final String QQFAV_DATALINE_IMAGEURL = "image_url";
    public static final String QQFAV_DATALINE_OPENID = "open_id";
    public static final String QQFAV_DATALINE_REQTYPE = "req_type";
    public static final String QQFAV_DATALINE_SHAREID = "share_id";
    public static final String QQFAV_DATALINE_SRCTYPE = "src_type";
    public static final String QQFAV_DATALINE_TITLE = "title";
    public static final int QQFAV_DATALINE_TYPE_AUDIO = 2;
    public static final int QQFAV_DATALINE_TYPE_DEFAULT = 1;
    public static final int QQFAV_DATALINE_TYPE_IMAGE_TEXT = 5;
    public static final int QQFAV_DATALINE_TYPE_TEXT = 6;
    public static final String QQFAV_DATALINE_URL = "url";
    public static final String QQFAV_DATALINE_VERSION = "version";
    public static final String SHARE_PRIZE_ACTIVITY_ID = "activityid";
    public static final String SHARE_PRIZE_IMAGE_URL = "imageUrl";
    public static final String SHARE_PRIZE_SHARE_ID = "shareid";
    public static final String SHARE_PRIZE_SHARE_ID_LIST = "shareid_list";
    public static final String SHARE_PRIZE_SUMMARY = "summary";
    public static final int SHARE_PRIZE_SUMMARY_MAX_LENGTH = 60;
    public static final String SHARE_PRIZE_TARGET_URL = "targetUrl";
    public static final String SHARE_PRIZE_TITLE = "title";
    public static final int SHARE_PRIZE_TITLE_MAX_LENGTH = 45;
    public static final String TROOPBAR_ID = "troopbar_id";

    public GameAppOperation(QQToken qQToken) {
        super(qQToken);
    }

    public void makeFriend(Activity activity, Bundle bundle) {
        f.c("openSDK_LOG.GameAppOperation", "-->makeFriend()  -- start");
        if (bundle == null) {
            f.e("openSDK_LOG.GameAppOperation", "-->makeFriend params is null");
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_MAKE_FRIEND, "18", "1");
            return;
        }
        String string = bundle.getString(GAME_FRIEND_OPENID);
        if (TextUtils.isEmpty(string)) {
            f.e("openSDK_LOG.GameAppOperation", "-->make friend, fOpenid is empty.");
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_MAKE_FRIEND, "18", "1");
            return;
        }
        Object string2 = bundle.getString(GAME_FRIEND_LABEL);
        Object string3 = bundle.getString(GAME_FRIEND_ADD_MESSAGE);
        Object applicationLable = Util.getApplicationLable(activity);
        Object openId = this.mToken.getOpenId();
        Object appId = this.mToken.getAppId();
        f.a("openSDK_LOG.GameAppOperation", "-->make friend, fOpenid: " + string + " | label: " + string2 + " | message: " + string3 + " | openid: " + openId + " | appid:" + appId);
        StringBuffer stringBuffer = new StringBuffer("mqqapi://gamesdk/add_friend?src_type=app&version=1");
        stringBuffer.append("&fopen_id=" + Base64.encodeToString(Util.getBytesUTF8(string), 2));
        if (!TextUtils.isEmpty(openId)) {
            stringBuffer.append("&open_id=" + Base64.encodeToString(Util.getBytesUTF8(openId), 2));
        }
        if (!TextUtils.isEmpty(appId)) {
            stringBuffer.append("&app_id=" + appId);
        }
        if (!TextUtils.isEmpty(string2)) {
            stringBuffer.append("&friend_label=" + Base64.encodeToString(Util.getBytesUTF8(string2), 2));
        }
        if (!TextUtils.isEmpty(string3)) {
            stringBuffer.append("&add_msg=" + Base64.encodeToString(Util.getBytesUTF8(string3), 2));
        }
        if (!TextUtils.isEmpty(applicationLable)) {
            stringBuffer.append("&app_name=" + Base64.encodeToString(Util.getBytesUTF8(applicationLable), 2));
        }
        f.a("openSDK_LOG.GameAppOperation", "-->make friend, url: " + stringBuffer.toString());
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(stringBuffer.toString()));
        if (!hasActivityForIntent(intent) || SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_5_1_0) < 0) {
            f.d("openSDK_LOG.GameAppOperation", "-->make friend, there is no activity.");
            a(activity);
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_MAKE_FRIEND, "18", "1");
        } else {
            f.c("openSDK_LOG.GameAppOperation", "-->makeFriend target activity found, qqver greater than 5.1.0");
            try {
                activity.startActivity(intent);
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_MAKE_FRIEND, "18", "0");
            } catch (Throwable e) {
                f.b("openSDK_LOG.GameAppOperation", "-->make friend, start activity exception.", e);
                a(activity);
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_MAKE_FRIEND, "18", "1");
            }
        }
        f.c("openSDK_LOG.GameAppOperation", "-->makeFriend()  -- end");
    }

    public void bindQQGroup(Activity activity, Bundle bundle) {
        f.c("openSDK_LOG.GameAppOperation", "-->bindQQGroup()  -- start");
        if (activity == null) {
            f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, activity is empty.");
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
        } else if (bundle == null) {
            Toast.makeText(activity, "Bundle参数为空", 0).show();
            f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, params is empty.");
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
        } else {
            Object applicationLable = Util.getApplicationLable(activity);
            StringBuffer stringBuffer = new StringBuffer("mqqapi://gamesdk/bind_group?src_type=app&version=1");
            if (!TextUtils.isEmpty(applicationLable)) {
                stringBuffer.append("&app_name=" + Base64.encodeToString(Util.getBytesUTF8(applicationLable), 2));
            }
            applicationLable = bundle.getString(GAME_UNION_ID);
            if (TextUtils.isEmpty(applicationLable)) {
                Toast.makeText(activity, "游戏公会ID为空", 0).show();
                f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, game union id is empty.");
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
                return;
            }
            stringBuffer.append("&unionid=" + Base64.encodeToString(Util.getBytesUTF8(applicationLable), 2));
            applicationLable = bundle.getString(GAME_UNION_NAME);
            if (TextUtils.isEmpty(applicationLable)) {
                Toast.makeText(activity, "游戏公会名称为空", 0).show();
                f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, game union name is empty.");
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
                return;
            }
            stringBuffer.append("&union_name=" + Base64.encodeToString(Util.getBytesUTF8(applicationLable), 2));
            applicationLable = bundle.getString(GAME_ZONE_ID);
            if (TextUtils.isEmpty(applicationLable)) {
                Toast.makeText(activity, "游戏区域ID为空", 0).show();
                f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, game zone id  is empty.");
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
                return;
            }
            stringBuffer.append("&zoneid=" + Base64.encodeToString(Util.getBytesUTF8(applicationLable), 2));
            applicationLable = bundle.getString(GAME_SIGNATURE);
            if (TextUtils.isEmpty(applicationLable)) {
                Toast.makeText(activity, "游戏签名为空", 0).show();
                f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, game signature is empty.");
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
                return;
            }
            stringBuffer.append("&signature=" + Base64.encodeToString(Util.getBytesUTF8(applicationLable), 2));
            applicationLable = this.mToken.getOpenId();
            if (TextUtils.isEmpty(applicationLable)) {
                Toast.makeText(activity, "Openid为空", 0).show();
                f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, openid is empty.");
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
                return;
            }
            stringBuffer.append("&openid=" + Base64.encodeToString(Util.getBytesUTF8(applicationLable), 2));
            Bundle composeActivityParams = composeActivityParams();
            for (String str : composeActivityParams.keySet()) {
                composeActivityParams.putString(str, Base64.encodeToString(Util.getBytesUTF8(composeActivityParams.getString(str)), 2));
            }
            stringBuffer.append("&" + Util.encodeUrl(composeActivityParams));
            f.a("openSDK_LOG.GameAppOperation", "-->bindQQGroup, url: " + stringBuffer.toString());
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(stringBuffer.toString()));
            if (!hasActivityForIntent(intent) || SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_5_1_0) < 0) {
                f.d("openSDK_LOG.GameAppOperation", "-->bind group, there is no activity, show download page.");
                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
                a(activity);
            } else {
                f.c("openSDK_LOG.GameAppOperation", "-->bingQQGroup target activity found, qqver > 5.1.0");
                try {
                    activity.startActivity(intent);
                    d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "0");
                } catch (Throwable e) {
                    f.b("openSDK_LOG.GameAppOperation", "-->bind group, start activity exception.", e);
                    d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
                    a(activity);
                }
            }
            f.c("openSDK_LOG.GameAppOperation", "-->bindQQGroup()  -- end");
        }
    }

    public void addToQQFavorites(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c("openSDK_LOG.GameAppOperation", "addToQQFavorites() -- start");
        int i = bundle.getInt("req_type", 1);
        if (a(activity, bundle, iUiListener)) {
            String string;
            StringBuffer stringBuffer = new StringBuffer("mqqapi://share/to_qqfav?src_type=app&version=1&file_type=news");
            Object string2 = bundle.getString(QQFAV_DATALINE_IMAGEURL);
            Object string3 = bundle.getString("title");
            Object string4 = bundle.getString("description");
            Object string5 = bundle.getString("url");
            Object string6 = bundle.getString(QQFAV_DATALINE_AUDIOURL);
            String applicationLable = Util.getApplicationLable(activity);
            if (applicationLable == null) {
                string = bundle.getString(QQFAV_DATALINE_APPNAME);
            } else {
                string = applicationLable;
            }
            ArrayList stringArrayList = bundle.getStringArrayList(QQFAV_DATALINE_FILEDATA);
            Object appId = this.mToken.getAppId();
            Object openId = this.mToken.getOpenId();
            f.a("openSDK_LOG.GameAppOperation", "addToQQFavorites openId:" + openId);
            if (!TextUtils.isEmpty(string2)) {
                stringBuffer.append("&image_url=" + Base64.encodeToString(Util.getBytesUTF8(string2), 2));
            }
            if (stringArrayList != null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                int size = stringArrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    try {
                        stringBuffer2.append(URLEncoder.encode(((String) stringArrayList.get(i2)).trim(), "UTF-8"));
                    } catch (Throwable e) {
                        e.printStackTrace();
                        f.b("openSDK_LOG.GameAppOperation", "UnsupportedEncodingException", e);
                        stringBuffer2.append(URLEncoder.encode(((String) stringArrayList.get(i2)).trim()));
                    }
                    if (i2 != size - 1) {
                        stringBuffer2.append(h.b);
                    }
                }
                stringBuffer.append("&file_data=" + Base64.encodeToString(Util.getBytesUTF8(stringBuffer2.toString()), 2));
            }
            if (!TextUtils.isEmpty(string3)) {
                stringBuffer.append("&title=" + Base64.encodeToString(Util.getBytesUTF8(string3), 2));
            }
            if (!TextUtils.isEmpty(string4)) {
                stringBuffer.append("&description=" + Base64.encodeToString(Util.getBytesUTF8(string4), 2));
            }
            if (!TextUtils.isEmpty(appId)) {
                stringBuffer.append("&share_id=" + appId);
            }
            if (!TextUtils.isEmpty(string5)) {
                stringBuffer.append("&url=" + Base64.encodeToString(Util.getBytesUTF8(string5), 2));
            }
            if (!TextUtils.isEmpty(string)) {
                if (string.length() > 20) {
                    string = string.substring(0, 20) + "...";
                }
                stringBuffer.append("&app_name=" + Base64.encodeToString(Util.getBytesUTF8(string), 2));
            }
            if (!TextUtils.isEmpty(openId)) {
                stringBuffer.append("&open_id=" + Base64.encodeToString(Util.getBytesUTF8(openId), 2));
            }
            if (!TextUtils.isEmpty(string6)) {
                stringBuffer.append("&audioUrl=" + Base64.encodeToString(Util.getBytesUTF8(string6), 2));
            }
            stringBuffer.append("&req_type=" + Base64.encodeToString(Util.getBytesUTF8(String.valueOf(i)), 2));
            f.a("openSDK_LOG.GameAppOperation", "addToQQFavorites url: " + stringBuffer.toString());
            a.a(Global.getContext(), this.mToken, "requireApi", SystemUtils.QQFAVORITES_CALLBACK_ACTION);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(stringBuffer.toString()));
            intent.putExtra("pkg_name", activity.getPackageName());
            if (UIListenerManager.getInstance().getListnerWithAction(SystemUtils.QQFAVORITES_CALLBACK_ACTION) != null) {
            }
            if (hasActivityForIntent(intent)) {
                if (SystemUtils.compareQQVersion(activity, "5.2.0") >= 0) {
                    try {
                        startAssistActivity(activity, (int) Constants.REQUEST_QQ_FAVORITES, intent, false);
                        a(Constants.VIA_REPORT_TYPE_QQFAVORITES, i, "0");
                    } catch (Throwable e2) {
                        f.b("openSDK_LOG.GameAppOperation", "-->addToQQFavorites, start activity exception.", e2);
                        a(Constants.VIA_REPORT_TYPE_QQFAVORITES, i, "1");
                        a(activity);
                    }
                    f.c("openSDK_LOG.GameAppOperation", "addToQQFavorites() --end");
                    return;
                }
            }
            f.d("openSDK_LOG.GameAppOperation", "-->addToQQFavorites, there is no activity, show download page.");
            a(Constants.VIA_REPORT_TYPE_QQFAVORITES, i, "1");
            a(activity);
            f.c("openSDK_LOG.GameAppOperation", "addToQQFavorites() --end");
            return;
        }
        f.e("openSDK_LOG.GameAppOperation", "-->addToQQFavorites check parames failed");
        a(Constants.VIA_REPORT_TYPE_QQFAVORITES, i, "1");
    }

    public void sendToMyComputer(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c("openSDK_LOG.GameAppOperation", "sendToMyComputer() --start");
        int i = bundle.getInt("req_type", 1);
        if (a(activity, bundle, iUiListener)) {
            String string;
            StringBuffer stringBuffer = new StringBuffer("mqqapi://share/to_qqdataline?src_type=app&version=1&file_type=news");
            Object string2 = bundle.getString(QQFAV_DATALINE_IMAGEURL);
            Object string3 = bundle.getString("title");
            Object string4 = bundle.getString("description");
            Object string5 = bundle.getString("url");
            Object string6 = bundle.getString(QQFAV_DATALINE_AUDIOURL);
            String applicationLable = Util.getApplicationLable(activity);
            if (applicationLable == null) {
                string = bundle.getString(QQFAV_DATALINE_APPNAME);
            } else {
                string = applicationLable;
            }
            ArrayList stringArrayList = bundle.getStringArrayList(QQFAV_DATALINE_FILEDATA);
            Object appId = this.mToken.getAppId();
            Object openId = this.mToken.getOpenId();
            f.a("openSDK_LOG.GameAppOperation", "openId:" + openId);
            if (!TextUtils.isEmpty(string2)) {
                stringBuffer.append("&image_url=" + Base64.encodeToString(Util.getBytesUTF8(string2), 2));
            }
            if (stringArrayList != null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                int size = stringArrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    try {
                        stringBuffer2.append(URLEncoder.encode(((String) stringArrayList.get(i2)).trim(), "UTF-8"));
                    } catch (Throwable e) {
                        e.printStackTrace();
                        f.b("openSDK_LOG.GameAppOperation", "UnsupportedEncodingException", e);
                        stringBuffer2.append(URLEncoder.encode(((String) stringArrayList.get(i2)).trim()));
                    }
                    if (i2 != size - 1) {
                        stringBuffer2.append(h.b);
                    }
                }
                stringBuffer.append("&file_data=" + Base64.encodeToString(Util.getBytesUTF8(stringBuffer2.toString()), 2));
            }
            if (!TextUtils.isEmpty(string3)) {
                stringBuffer.append("&title=" + Base64.encodeToString(Util.getBytesUTF8(string3), 2));
            }
            if (!TextUtils.isEmpty(string4)) {
                stringBuffer.append("&description=" + Base64.encodeToString(Util.getBytesUTF8(string4), 2));
            }
            if (!TextUtils.isEmpty(appId)) {
                stringBuffer.append("&share_id=" + appId);
            }
            if (!TextUtils.isEmpty(string5)) {
                stringBuffer.append("&url=" + Base64.encodeToString(Util.getBytesUTF8(string5), 2));
            }
            if (!TextUtils.isEmpty(string)) {
                if (string.length() > 20) {
                    string = string.substring(0, 20) + "...";
                }
                stringBuffer.append("&app_name=" + Base64.encodeToString(Util.getBytesUTF8(string), 2));
            }
            if (!TextUtils.isEmpty(openId)) {
                stringBuffer.append("&open_id=" + Base64.encodeToString(Util.getBytesUTF8(openId), 2));
            }
            if (!TextUtils.isEmpty(string6)) {
                stringBuffer.append("&audioUrl=" + Base64.encodeToString(Util.getBytesUTF8(string6), 2));
            }
            stringBuffer.append("&req_type=" + Base64.encodeToString(Util.getBytesUTF8(String.valueOf(i)), 2));
            f.a("openSDK_LOG.GameAppOperation", "sendToMyComputer url: " + stringBuffer.toString());
            a.a(Global.getContext(), this.mToken, "requireApi", SystemUtils.QQDATALINE_CALLBACK_ACTION);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(stringBuffer.toString()));
            intent.putExtra("pkg_name", activity.getPackageName());
            if (UIListenerManager.getInstance().setListnerWithAction(SystemUtils.QQDATALINE_CALLBACK_ACTION, iUiListener) != null) {
            }
            if (hasActivityForIntent(intent)) {
                if (SystemUtils.compareQQVersion(activity, "5.2.0") >= 0) {
                    try {
                        startAssistActivity(activity, (int) Constants.REQUEST_SEND_TO_MY_COMPUTER, intent, false);
                        a(Constants.VIA_REPORT_TYPE_DATALINE, i, "0");
                    } catch (Throwable e2) {
                        f.b("openSDK_LOG.GameAppOperation", "-->sendToMyComputer, start activity exception.", e2);
                        a(Constants.VIA_REPORT_TYPE_DATALINE, i, "1");
                        a(activity);
                    }
                    f.c("openSDK_LOG.GameAppOperation", "sendToMyComputer() --end");
                    return;
                }
            }
            f.d("openSDK_LOG.GameAppOperation", "-->sendToMyComputer, there is no activity, show download page.");
            a(Constants.VIA_REPORT_TYPE_DATALINE, i, "1");
            a(activity);
            f.c("openSDK_LOG.GameAppOperation", "sendToMyComputer() --end");
            return;
        }
        f.e("openSDK_LOG.GameAppOperation", "-->sendToMyComputer check parames failed");
        a(Constants.VIA_REPORT_TYPE_DATALINE, i, "1");
    }

    public void shareToTroopBar(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- start");
        String str = Constants.MSG_PARAM_ERROR;
        if (iUiListener == null) {
            f.e("openSDK_LOG.GameAppOperation", "listener is null!");
        } else if (activity == null || bundle == null) {
            String str2 = "activity or params is null!";
            f.e("openSDK_LOG.GameAppOperation", str2);
            iUiListener.onError(new UiError(-5, str, str2));
        } else {
            Object string = bundle.getString("title");
            if (TextUtils.isEmpty(string)) {
                iUiListener.onError(new UiError(-5, "传入参数不可以为空: title is null", null));
                f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- title is null");
            } else if (string.length() < 4 || string.length() > 25) {
                iUiListener.onError(new UiError(-5, "传入参数有误!: title size: 4 ~ 25", null));
                f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- title size: 4 ~ 25");
            } else {
                Object string2 = bundle.getString("description");
                if (TextUtils.isEmpty(string2)) {
                    iUiListener.onError(new UiError(-5, "传入参数不可以为空: description is null", null));
                    f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- description is null");
                } else if (string2.length() < 10 || string2.length() > 700) {
                    iUiListener.onError(new UiError(-5, "传入参数有误!: description size: 10 ~ 700", null));
                    f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- description size: 10 ~ 700");
                } else {
                    String str3;
                    ArrayList stringArrayList = bundle.getStringArrayList(QQFAV_DATALINE_FILEDATA);
                    Object stringBuffer = new StringBuffer();
                    if (stringArrayList != null && stringArrayList.size() > 0) {
                        str3 = "";
                        int size = stringArrayList.size();
                        if (size > 9) {
                            iUiListener.onError(new UiError(-5, "传入参数有误!: file_data size: 1 ~ 9", null));
                            f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- file_data size: 1 ~ 9");
                            return;
                        }
                        int i = 0;
                        while (i < size) {
                            str3 = ((String) stringArrayList.get(i)).trim();
                            if (!str3.startsWith("/")) {
                                iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_ERROR, "file_data应该为本地图片"));
                                f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar(): file_data应该为本地图片");
                                return;
                            } else if (!str3.startsWith("/") || new File(str3).exists()) {
                                i++;
                            } else {
                                iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_ERROR, "图片文件不存在"));
                                f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar(): 图片文件不存在");
                                return;
                            }
                        }
                        for (i = 0; i < size; i++) {
                            try {
                                stringBuffer.append(URLEncoder.encode(((String) stringArrayList.get(i)).trim(), "UTF-8"));
                            } catch (Throwable e) {
                                e.printStackTrace();
                                f.b("openSDK_LOG.GameAppOperation", "UnsupportedEncodingException: ", e);
                                stringBuffer.append(URLEncoder.encode(((String) stringArrayList.get(i)).trim()));
                            }
                            if (i != size - 1) {
                                stringBuffer.append(h.b);
                            }
                        }
                    }
                    Object string3 = bundle.getString(TROOPBAR_ID);
                    if (TextUtils.isEmpty(string3) || Util.isNumeric(string3)) {
                        StringBuffer stringBuffer2 = new StringBuffer("mqqapi://share/to_troopbar?src_type=app&version=1&file_type=news");
                        Object appId = this.mToken.getAppId();
                        Object openId = this.mToken.getOpenId();
                        f.a("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- openId: " + openId);
                        str3 = Util.getApplicationLable(activity);
                        if (!TextUtils.isEmpty(appId)) {
                            stringBuffer2.append("&share_id=" + appId);
                        }
                        if (!TextUtils.isEmpty(openId)) {
                            stringBuffer2.append("&open_id=" + Base64.encodeToString(Util.getBytesUTF8(openId), 2));
                        }
                        if (!TextUtils.isEmpty(str3)) {
                            if (str3.length() > 20) {
                                str3 = str3.substring(0, 20) + "...";
                            }
                            stringBuffer2.append("&app_name=" + Base64.encodeToString(Util.getBytesUTF8(str3), 2));
                        }
                        if (!TextUtils.isEmpty(string)) {
                            stringBuffer2.append("&title=" + Base64.encodeToString(Util.getBytesUTF8(string), 2));
                        }
                        if (!TextUtils.isEmpty(string2)) {
                            stringBuffer2.append("&description=" + Base64.encodeToString(Util.getBytesUTF8(string2), 2));
                        }
                        if (!TextUtils.isEmpty(string3)) {
                            stringBuffer2.append("&troopbar_id=" + Base64.encodeToString(Util.getBytesUTF8(string3), 2));
                        }
                        if (!TextUtils.isEmpty(stringBuffer)) {
                            stringBuffer2.append("&file_data=" + Base64.encodeToString(Util.getBytesUTF8(stringBuffer.toString()), 2));
                        }
                        f.a("openSDK_LOG.GameAppOperation", "shareToTroopBar, url: " + stringBuffer2.toString());
                        a.a(Global.getContext(), this.mToken, "requireApi", SystemUtils.TROOPBAR_CALLBACK_ACTION);
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse(stringBuffer2.toString()));
                        string3 = activity.getPackageName();
                        if (!TextUtils.isEmpty(string3)) {
                            intent.putExtra("pkg_name", string3);
                        }
                        if (UIListenerManager.getInstance().setListnerWithAction(SystemUtils.TROOPBAR_CALLBACK_ACTION, iUiListener) != null) {
                        }
                        if (!hasActivityForIntent(intent) || SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_5_3_0) < 0) {
                            f.d("openSDK_LOG.GameAppOperation", "-->shareToTroopBar, there is no activity, show download page.");
                            a(activity, SystemUtils.QQ_VERSION_NAME_5_3_0);
                            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_TROOPBAR, Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR, "18", "1");
                        } else {
                            try {
                                startAssistActivity(activity, (int) Constants.REQUEST_SHARE_TO_TROOP_BAR, intent, false);
                                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_TROOPBAR, Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR, "18", "0");
                            } catch (Throwable e2) {
                                f.b("openSDK_LOG.GameAppOperation", "-->shareToTroopBar, start activity exception.", e2);
                                d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_TROOPBAR, Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR, "18", "1");
                                a(activity, SystemUtils.QQ_VERSION_NAME_5_3_0);
                            }
                        }
                        f.c("openSDK_LOG.GameAppOperation", "shareToTroopBar() -- end");
                        return;
                    }
                    iUiListener.onError(new UiError(-6, "传入参数有误! troopbar_id 必须为数字", null));
                    f.e("openSDK_LOG.GameAppOperation", "shareToTroopBar(): troopbar_id 必须为数字");
                }
            }
        }
    }

    public void sharePrizeToQQ(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c("openSDK_LOG.GameAppOperation", "sharePrizeToQQ() -- start");
        String str = Constants.MSG_PARAM_ERROR;
        if (iUiListener == null) {
            f.e("openSDK_LOG.GameAppOperation", "listener is null!");
        } else if (activity == null || bundle == null) {
            r2 = "activity or params is null!";
            f.e("openSDK_LOG.GameAppOperation", r2);
            iUiListener.onError(new UiError(-5, str, r2));
        } else {
            Object string = bundle.getString("title");
            if (TextUtils.isEmpty(string)) {
                r2 = "sharePrizeToQQ failed, title is empty.";
                f.e("openSDK_LOG.GameAppOperation", r2);
                iUiListener.onError(new UiError(-5, str, r2));
                return;
            }
            Object string2 = bundle.getString("summary");
            if (TextUtils.isEmpty(string2)) {
                r2 = "sharePrizeToQQ failed, sumary is empty.";
                f.e("openSDK_LOG.GameAppOperation", r2);
                iUiListener.onError(new UiError(-5, str, r2));
                return;
            }
            String string3 = bundle.getString("imageUrl");
            if (TextUtils.isEmpty(string3) || !(string3.startsWith("http://") || string3.startsWith("https://"))) {
                r2 = "sharePrizeToQQ failed, imageUrl is empty or illegal.";
                f.e("openSDK_LOG.GameAppOperation", r2);
                iUiListener.onError(new UiError(-5, str, r2));
                return;
            }
            final Object string4 = bundle.getString(SHARE_PRIZE_ACTIVITY_ID);
            if (TextUtils.isEmpty(string4)) {
                r2 = "sharePrizeToQQ failed, activityId is empty.";
                f.e("openSDK_LOG.GameAppOperation", r2);
                iUiListener.onError(new UiError(-5, str, r2));
                return;
            }
            final Bundle bundle2 = new Bundle();
            bundle2.putString("title", string);
            bundle2.putString("summary", string2);
            bundle2.putString("imageUrl", string3);
            bundle2.putInt("req_type", 1);
            final IUiListener iUiListener2 = iUiListener;
            final Activity activity2 = activity;
            ThreadManager.executeOnSubThread(new Runnable(this) {
                final /* synthetic */ GameAppOperation e;

                public void run() {
                    Bundle a = this.e.a();
                    if (a == null) {
                        String str = "accesstoken or openid or appid is null, please login first!";
                        f.e("openSDK_LOG.GameAppOperation", str);
                        iUiListener2.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, str));
                        return;
                    }
                    a.putString(GameAppOperation.SHARE_PRIZE_ACTIVITY_ID, string4);
                    try {
                        JSONObject request = HttpUtils.request(this.e.mToken, activity2.getApplicationContext(), ServerSetting.URL_PRIZE_MAKE_SHARE_URL, a, "GET");
                        try {
                            int i = request.getInt(KEYS.RET);
                            int i2 = request.getInt("subCode");
                            if (i == 0 && i2 == 0) {
                                bundle2.putString("targetUrl", request.getString("share_url"));
                                new QQShare(activity2.getApplicationContext(), this.e.mToken).shareToQQ(activity2, bundle2, iUiListener2);
                                return;
                            }
                            str = request.getString("msg");
                            iUiListener2.onError(new UiError(i, "make_share_url error.", str));
                            f.c("openSDK_LOG.GameAppOperation", "code = " + i + ", subcode = " + "errormsg = " + str);
                        } catch (JSONException e) {
                            f.e("openSDK_LOG.GameAppOperation", "JSONException occur in make_share_url, errorMsg: " + e.getMessage());
                            iUiListener2.onError(new UiError(-4, Constants.MSG_JSON_ERROR, ""));
                        }
                    } catch (Throwable e2) {
                        f.b("openSDK_LOG.GameAppOperation", "Exception occur in make_share_url", e2);
                        iUiListener2.onError(new UiError(-2, Constants.MSG_IO_ERROR, e2.getMessage()));
                    }
                }
            });
            f.c("openSDK_LOG.GameAppOperation", "sharePrizeToQQ() -- end");
        }
    }

    public void queryUnexchangePrize(Context context, final Bundle bundle, final IUiListener iUiListener) {
        String str = Constants.MSG_PARAM_ERROR;
        if (iUiListener == null) {
            f.e("openSDK_LOG.GameAppOperation", "listener is null!");
        } else if (bundle == null) {
            r2 = "params is null!";
            f.e("openSDK_LOG.GameAppOperation", r2);
            iUiListener.onError(new UiError(-5, str, r2));
        } else if (this.mToken == null || !this.mToken.isSessionValid()) {
            r2 = "queryUnexchangePrize failed, auth token is illegal.";
            f.e("openSDK_LOG.GameAppOperation", r2);
            iUiListener.onError(new UiError(-5, str, r2));
        } else if (context == null && Global.getContext() == null) {
            r2 = "queryUnexchangePrize failed, context is null.";
            f.e("openSDK_LOG.GameAppOperation", r2);
            iUiListener.onError(new UiError(-5, str, r2));
        } else if (TextUtils.isEmpty(bundle.getString(SHARE_PRIZE_ACTIVITY_ID))) {
            r2 = "queryUnexchangePrize failed, activityId is empty.";
            f.e("openSDK_LOG.GameAppOperation", r2);
            iUiListener.onError(new UiError(-5, str, r2));
        } else {
            if (context == null) {
                context = Global.getContext();
            }
            ThreadManager.executeOnSubThread(new Runnable(this) {
                final /* synthetic */ GameAppOperation d;

                public void run() {
                    Bundle a = this.d.a();
                    if (a == null) {
                        String str = "accesstoken or openid or appid is null, please login first!";
                        f.e("openSDK_LOG.GameAppOperation", str);
                        iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, str));
                        return;
                    }
                    a.putAll(bundle);
                    try {
                        iUiListener.onComplete(HttpUtils.request(this.d.mToken, context, ServerSetting.URL_PRIZE_QUERY_UNEXCHANGE, a, "GET"));
                    } catch (Throwable e) {
                        f.b("openSDK_LOG.GameAppOperation", "Exception occur in queryUnexchangePrize", e);
                        iUiListener.onError(new UiError(-2, Constants.MSG_IO_ERROR, e.getMessage()));
                    }
                }
            });
        }
    }

    public void exchangePrize(Context context, Bundle bundle, final IUiListener iUiListener) {
        String str = Constants.MSG_PARAM_ERROR;
        if (iUiListener == null) {
            f.e("openSDK_LOG.GameAppOperation", "exchangePrize listener is null!");
        } else if (bundle == null) {
            r2 = "exchangePrize params is null!";
            f.e("openSDK_LOG.GameAppOperation", r2);
            iUiListener.onError(new UiError(-5, str, r2));
        } else if (this.mToken == null || !this.mToken.isSessionValid()) {
            r2 = "exchangePrize failed, auth token is illegal.";
            f.e("openSDK_LOG.GameAppOperation", r2);
            iUiListener.onError(new UiError(-5, str, r2));
        } else if (context == null && Global.getContext() == null) {
            r2 = "exchangePrize failed, context is null.";
            f.e("openSDK_LOG.GameAppOperation", r2);
            iUiListener.onError(new UiError(-5, str, r2));
        } else {
            ArrayList stringArrayList = bundle.getStringArrayList(SHARE_PRIZE_SHARE_ID_LIST);
            if (stringArrayList == null) {
                r2 = "exchangePrize failed, shareid_list is empty.";
                f.e("openSDK_LOG.GameAppOperation", r2);
                iUiListener.onError(new UiError(-5, str, r2));
                return;
            }
            final StringBuffer stringBuffer = new StringBuffer("");
            int size = stringArrayList.size();
            String str2 = "";
            for (int i = 0; i < size; i++) {
                str2 = (String) stringArrayList.get(i);
                if (!TextUtils.isEmpty(str2)) {
                    stringBuffer.append(str2);
                    if (i < size - 1) {
                        stringBuffer.append(",");
                    }
                }
            }
            if (context == null) {
                context = Global.getContext();
            }
            ThreadManager.executeOnSubThread(new Runnable(this) {
                final /* synthetic */ GameAppOperation d;

                public void run() {
                    Bundle a = this.d.a();
                    if (a == null) {
                        String str = "accesstoken or openid or appid is null, please login first!";
                        f.e("openSDK_LOG.GameAppOperation", str);
                        iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, str));
                        return;
                    }
                    a.putString(GameAppOperation.SHARE_PRIZE_SHARE_ID, stringBuffer.toString());
                    a.putString("imei", c.b(Global.getContext()));
                    try {
                        iUiListener.onComplete(HttpUtils.request(this.d.mToken, context, ServerSetting.URL_PRIZE_EXCHANGE, a, "GET"));
                    } catch (Throwable e) {
                        f.b("openSDK_LOG.GameAppOperation", "Exception occur in exchangePrize", e);
                        iUiListener.onError(new UiError(-2, Constants.MSG_IO_ERROR, e.getMessage()));
                    }
                }
            });
        }
    }

    private Bundle a() {
        if (this.mToken == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        CharSequence appId = this.mToken.getAppId();
        CharSequence openId = this.mToken.getOpenId();
        CharSequence accessToken = this.mToken.getAccessToken();
        if (TextUtils.isEmpty(appId) || TextUtils.isEmpty(openId) || TextUtils.isEmpty(accessToken)) {
            f.e("openSDK_LOG.GameAppOperation", "composeLoginStateParams fail, accesstoken or openid or appid is null");
            return null;
        }
        bundle.putString("appid", this.mToken.getAppId());
        bundle.putString("openid", this.mToken.getOpenId());
        bundle.putString("accesstoken", this.mToken.getAccessToken());
        return bundle;
    }

    public void isActivityAvailable(final Activity activity, final String str, final IUiListener iUiListener) {
        String str2 = Constants.MSG_PARAM_ERROR;
        String str3;
        if (TextUtils.isEmpty(str)) {
            str3 = "isActivityAvailable failed, activityId is null.";
            f.e("openSDK_LOG.GameAppOperation", str3);
            iUiListener.onError(new UiError(-5, str2, str3));
        } else if (this.mToken == null || !this.mToken.isSessionValid()) {
            str3 = "exchangePrize failed, auth token is illegal.";
            f.e("openSDK_LOG.GameAppOperation", str3);
            iUiListener.onError(new UiError(-5, str2, str3));
        } else {
            ThreadManager.executeOnSubThread(new Runnable(this) {
                final /* synthetic */ GameAppOperation d;

                public void run() {
                    Bundle a = this.d.a();
                    if (a == null) {
                        String str = "accesstoken or openid or appid is null, please login first!";
                        f.e("openSDK_LOG.GameAppOperation", str);
                        iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, str));
                        return;
                    }
                    a.putString(GameAppOperation.SHARE_PRIZE_ACTIVITY_ID, str);
                    try {
                        iUiListener.onComplete(HttpUtils.request(this.d.mToken, activity.getApplicationContext(), ServerSetting.URL_PRIZE_GET_ACTIVITY_STATE, a, "GET"));
                    } catch (Throwable e) {
                        f.b("openSDK_LOG.GameAppOperation", "Exception occur in make_share_url", e);
                        iUiListener.onError(new UiError(-6, "Exception occur in make_share_url", e.getMessage()));
                    }
                }
            });
        }
    }

    private boolean a(Activity activity, Bundle bundle, IUiListener iUiListener) {
        if (activity == null || bundle == null || iUiListener == null) {
            f.e("openSDK_LOG.GameAppOperation", "activity or params or listener is null!");
            return false;
        }
        int i = bundle.getInt("req_type", 1);
        if (TextUtils.isEmpty(bundle.getString(QQFAV_DATALINE_APPNAME))) {
            iUiListener.onError(new UiError(-5, "传入参数不可以为空: app_name", null));
            return false;
        }
        CharSequence string = bundle.getString("description");
        CharSequence string2 = bundle.getString("url");
        CharSequence string3 = bundle.getString(QQFAV_DATALINE_AUDIOURL);
        CharSequence string4 = bundle.getString(QQFAV_DATALINE_IMAGEURL);
        ArrayList stringArrayList = bundle.getStringArrayList(QQFAV_DATALINE_FILEDATA);
        switch (i) {
            case 1:
                if (TextUtils.isEmpty(string2) || TextUtils.isEmpty(string4)) {
                    iUiListener.onError(new UiError(-5, "传入参数不可以为空: image_url or url is null", null));
                    return false;
                }
            case 2:
                if (TextUtils.isEmpty(string2) || TextUtils.isEmpty(string4) || TextUtils.isEmpty(string3)) {
                    iUiListener.onError(new UiError(-5, "传入参数不可以为空: image_url or url or audioUrl is null", null));
                    return false;
                }
            case 5:
                if (stringArrayList != null && stringArrayList.size() != 0) {
                    String str = "";
                    int size = stringArrayList.size();
                    int i2 = 0;
                    while (i2 < size) {
                        str = ((String) stringArrayList.get(i2)).trim();
                        if (!str.startsWith("/") || new File(str).exists()) {
                            i2++;
                        } else {
                            iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR, null));
                            return false;
                        }
                    }
                    break;
                }
                iUiListener.onError(new UiError(-5, "传入参数不可以为空: fill_data is null", null));
                return false;
                break;
            case 6:
                if (TextUtils.isEmpty(string)) {
                    iUiListener.onError(new UiError(-5, "传入参数不可以为空: description is null", null));
                    return false;
                }
                break;
            default:
                iUiListener.onError(new UiError(-5, "传入参数有误!: unknow req_type", null));
                return false;
        }
        return true;
    }

    public void releaseResource() {
        f.c("openSDK_LOG.GameAppOperation", "releaseResource() -- start");
        f.c("openSDK_LOG.GameAppOperation", "releaseResource() -- end");
    }

    private void a(Activity activity) {
        a(activity, "");
    }

    private void a(Activity activity, String str) {
        new TDialog(activity, "", getCommonDownloadQQUrl(str), null, this.mToken).show();
    }

    private void a(String str, int i, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            f.a("openSDK_LOG.GameAppOperation", "reportForVia() error: reportType or result is null");
            return;
        }
        String str3;
        String str4 = "";
        switch (i) {
            case 1:
                str3 = "6";
                break;
            case 2:
                str3 = "3";
                break;
            case 5:
                str3 = "1";
                break;
            case 6:
                str3 = "5";
                break;
            default:
                f.e("openSDK_LOG.GameAppOperation", "GameAppOperation -- reportForVia() error: unknow type " + String.valueOf(i));
                return;
        }
        d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), "2", str, Constants.VIA_ACT_TYPE_TWENTY_EIGHT, str2, str3, "0", "", "");
    }
}
