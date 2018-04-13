package com.tencent.weibo.sdk.android.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.tencent.connect.common.Constants;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.BaseVO;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import com.tencent.weibo.sdk.android.network.ReqParam;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class WeiboAPI extends BaseAPI {
    private static final String SERVER_URL_ADD = "https://open.t.qq.com/api/t/add_multi";
    private static final String SERVER_URL_ADDPIC = "https://open.t.qq.com/api/t/add_pic";
    private static final String SERVER_URL_ADDPICURL = "https://open.t.qq.com/api/t/add_pic_url";
    private static final String SERVER_URL_ADDWEIBO = "https://open.t.qq.com/api/t/add";
    private static final String SERVER_URL_RLIST = "https://open.t.qq.com/api/t/re_list";
    private static final String SERVER_URL_VIDEO = "https://open.t.qq.com/api/t/getvideoinfo";

    public WeiboAPI(AccountModel accountModel) {
        super(accountModel);
    }

    public void reAddWeibo(Context context, String str, String str2, String str3, String str4, String str5, String str6, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i) {
        ReqParam reqParam = new ReqParam();
        reqParam.addParam(Constants.PARAM_SCOPE, "all");
        reqParam.addParam("content", str);
        reqParam.addParam("pic_url", str2);
        reqParam.addParam("video_url", str3);
        reqParam.addParam("music_url", str4);
        reqParam.addParam("music_title", str5);
        reqParam.addParam("music_author", str6);
        reqParam.addParam("clientip", Util.getLocalIPAddress(context));
        reqParam.addParam("oauth_version", "2.a");
        reqParam.addParam("oauth_consumer_key", Util.getSharePersistent(context, "CLIENT_ID"));
        reqParam.addParam("openid", Util.getSharePersistent(context, "OPEN_ID"));
        reqParam.addParam("pageflag", "0");
        reqParam.addParam("type", "0");
        reqParam.addParam("format", "json");
        reqParam.addParam("reqnum", "30");
        reqParam.addParam("pagetime", "0");
        reqParam.addParam("contenttype", "0");
        startRequest(context, SERVER_URL_ADD, reqParam, httpCallback, cls, "POST", i);
    }

    public void getVideoInfo(Context context, String str, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i) {
        ReqParam reqParam = new ReqParam();
        reqParam.addParam(Constants.PARAM_SCOPE, "all");
        reqParam.addParam("clientip", Util.getLocalIPAddress(context));
        reqParam.addParam("oauth_version", "2.a");
        reqParam.addParam("oauth_consumer_key", Util.getSharePersistent(context, "CLIENT_ID"));
        reqParam.addParam("openid", Util.getSharePersistent(context, "OPEN_ID"));
        reqParam.addParam("format", "json");
        reqParam.addParam("video_url", str);
        startRequest(context, SERVER_URL_VIDEO, reqParam, httpCallback, cls, "POST", i);
    }

    public void addWeibo(Context context, String str, String str2, double d, double d2, int i, int i2, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i3) {
        ReqParam reqParam = new ReqParam();
        reqParam.addParam("oauth_version", "2.a");
        reqParam.addParam("oauth_consumer_key", Util.getSharePersistent(context, "CLIENT_ID"));
        reqParam.addParam("openid", Util.getSharePersistent(context, "OPEN_ID"));
        reqParam.addParam(Constants.PARAM_SCOPE, "all");
        reqParam.addParam("format", str2);
        reqParam.addParam("content", str);
        reqParam.addParam("clientip", Util.getLocalIPAddress(context));
        if (d != 0.0d) {
            reqParam.addParam("longitude", Double.valueOf(d));
        }
        if (d2 != 0.0d) {
            reqParam.addParam("latitude", Double.valueOf(d2));
        }
        reqParam.addParam("syncflag", Integer.valueOf(i));
        reqParam.addParam("compatibleflag", Integer.valueOf(i2));
        startRequest(context, SERVER_URL_ADDWEIBO, reqParam, httpCallback, cls, "POST", i3);
    }

    public void addPic(Context context, String str, String str2, double d, double d2, Bitmap bitmap, int i, int i2, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i3) {
        ReqParam reqParam = new ReqParam();
        reqParam.addParam("oauth_version", "2.a");
        reqParam.addParam("oauth_consumer_key", Util.getSharePersistent(context, "CLIENT_ID"));
        reqParam.addParam("openid", Util.getSharePersistent(context, "OPEN_ID"));
        reqParam.addParam(Constants.PARAM_SCOPE, "all");
        reqParam.addParam("format", str2);
        if (str == null || "".equals(str)) {
            str = "#分享图片#";
        }
        reqParam.addParam("content", str);
        reqParam.addParam("clientip", Util.getLocalIPAddress(context));
        if (d != 0.0d) {
            reqParam.addParam("longitude", Double.valueOf(d));
        }
        if (d2 != 0.0d) {
            reqParam.addParam("latitude", Double.valueOf(d2));
        }
        reqParam.addParam("syncflag", Integer.valueOf(i));
        reqParam.addParam("compatibleflag", Integer.valueOf(i2));
        reqParam.setBitmap(bitmap);
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
        reqParam.addParam("pic", byteArrayOutputStream.toByteArray());
        startRequest(context, SERVER_URL_ADDPIC, reqParam, httpCallback, cls, "POST", i3);
    }

    public void addPicUrl(Context context, String str, String str2, double d, double d2, String str3, int i, int i2, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i3) {
        ReqParam reqParam = new ReqParam();
        reqParam.addParam("oauth_version", "2.a");
        reqParam.addParam("oauth_consumer_key", Util.getSharePersistent(context, "CLIENT_ID"));
        reqParam.addParam("openid", Util.getSharePersistent(context, "OPEN_ID"));
        reqParam.addParam(Constants.PARAM_SCOPE, "all");
        reqParam.addParam("format", str2);
        reqParam.addParam("content", str);
        reqParam.addParam("clientip", Util.getLocalIPAddress(context));
        if (d != 0.0d) {
            reqParam.addParam("longitude", Double.valueOf(d));
        }
        if (d2 != 0.0d) {
            reqParam.addParam("latitude", Double.valueOf(d2));
        }
        reqParam.addParam("syncflag", Integer.valueOf(i));
        reqParam.addParam("compatibleflag", Integer.valueOf(i2));
        reqParam.addParam("pic_url", str3);
        startRequest(context, SERVER_URL_ADDPICURL, reqParam, httpCallback, cls, "POST", i3);
    }

    public void reList(Context context, String str, int i, String str2, int i2, String str3, int i3, String str4, HttpCallback httpCallback, Class<? extends BaseVO> cls, int i4) {
        ReqParam reqParam = new ReqParam();
        reqParam.addParam("oauth_version", "2.a");
        reqParam.addParam("oauth_consumer_key", Util.getSharePersistent(context, "CLIENT_ID"));
        reqParam.addParam("openid", Util.getSharePersistent(context, "OPEN_ID"));
        reqParam.addParam(Constants.PARAM_SCOPE, "all");
        reqParam.addParam("clientip", Util.getLocalIPAddress(context));
        reqParam.addParam("format", str);
        reqParam.addParam("flag", Integer.valueOf(i));
        reqParam.addParam("rootid", str2);
        reqParam.addParam("pageflag", Integer.valueOf(i2));
        reqParam.addParam("pagetime", str3);
        reqParam.addParam("reqnum", Integer.valueOf(i3));
        reqParam.addParam("twitterid", str4);
        startRequest(context, SERVER_URL_RLIST, reqParam, httpCallback, cls, "GET", i4);
    }
}
