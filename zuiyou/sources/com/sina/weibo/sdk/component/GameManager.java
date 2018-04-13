package com.sina.weibo.sdk.component;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.exception.WeiboHttpException;
import com.sina.weibo.sdk.net.HttpManager;
import com.sina.weibo.sdk.net.NetStateManager;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.LogUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;

public class GameManager {
    private static final String BOUNDARY = HttpManager.getBoundry();
    public static final String DEFAULT_CHARSET = "UTF-8";
    private static final String HTTP_METHOD_GET = "GET";
    private static final String HTTP_METHOD_POST = "POST";
    private static String INVITATION_ONE_FRINED_URL = "http://widget.weibo.com/invitation/appinfo.php?";
    private static String INVITATION_URL = "http://widget.weibo.com/invitation/app.php?";
    private static final String MULTIPART_FORM_DATA = "multipart/form-data";
    private static final String TAG = "GameManager";
    private static StringBuffer URL = new StringBuffer("https://api.weibo.com/2/proxy/darwin/graph/game/");
    private static String URL_ACHIEVEMENT_ADD_UPDATE = (URL + "achievement/add.json");
    private static String URL_ACHIEVEMENT_READ_PLAYER_FRIENDS = (URL + "score/read_player_friends.json");
    private static String URL_ACHIEVEMENT_READ_PLAYER_SCORE = (URL + "score/read_player.json");
    private static String URL_ACHIEVEMENT_RELATION_ADD_UPDATE = (URL + "achievement/gain/add.json");
    private static String URL_ACHIEVEMENT_SCORE_ADD_UPDATE = (URL + "score/add.json");
    private static String URL_ACHIEVEMENT_USER_GAIN = (URL + "achievement/user_gain.json");

    public static String AddOrUpdateGameAchievement(Context context, WeiboParameters weiboParameters) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        weiboParameters.put("updated_time", simpleDateFormat.format(date));
        if (TextUtils.isEmpty((String) weiboParameters.get(WBConstants.GAME_PARAMS_GAME_CREATE_TIME))) {
            weiboParameters.put(WBConstants.GAME_PARAMS_GAME_CREATE_TIME, simpleDateFormat.format(date));
        }
        String readRsponse = HttpManager.readRsponse(requestHttpExecute(context, URL_ACHIEVEMENT_ADD_UPDATE, "POST", weiboParameters));
        LogUtil.d(TAG, "Response : " + readRsponse);
        return readRsponse;
    }

    public static String addOrUpdateGameAchievementRelation(Context context, WeiboParameters weiboParameters) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        weiboParameters.put("updated_time", simpleDateFormat.format(date));
        if (TextUtils.isEmpty((String) weiboParameters.get(WBConstants.GAME_PARAMS_GAME_CREATE_TIME))) {
            weiboParameters.put(WBConstants.GAME_PARAMS_GAME_CREATE_TIME, simpleDateFormat.format(date));
        }
        String readRsponse = HttpManager.readRsponse(requestHttpExecute(context, URL_ACHIEVEMENT_RELATION_ADD_UPDATE, "POST", weiboParameters));
        LogUtil.d(TAG, "Response : " + readRsponse);
        return readRsponse;
    }

    public static String addOrUpdateAchievementScore(Context context, String str, String str2, String str3, String str4, String str5) {
        WeiboParameters weiboParameters = new WeiboParameters("");
        if (!TextUtils.isEmpty(str)) {
            weiboParameters.put("access_token", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            weiboParameters.put("source", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            weiboParameters.put(WBConstants.GAME_PARAMS_GAME_ID, str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            weiboParameters.put("uid", str4);
        }
        if (!TextUtils.isEmpty(str5)) {
            weiboParameters.put(WBConstants.GAME_PARAMS_SCORE, str5);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        weiboParameters.put("updated_time", simpleDateFormat.format(date));
        if (TextUtils.isEmpty((String) weiboParameters.get(WBConstants.GAME_PARAMS_GAME_CREATE_TIME))) {
            weiboParameters.put(WBConstants.GAME_PARAMS_GAME_CREATE_TIME, simpleDateFormat.format(date));
        }
        String readRsponse = HttpManager.readRsponse(requestHttpExecute(context, URL_ACHIEVEMENT_SCORE_ADD_UPDATE, "POST", weiboParameters));
        LogUtil.d(TAG, "Response : " + readRsponse);
        return readRsponse;
    }

    public static String readPlayerScoreInfo(Context context, String str, String str2, String str3, String str4) {
        WeiboParameters weiboParameters = new WeiboParameters("");
        if (!TextUtils.isEmpty(str)) {
            weiboParameters.put("access_token", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            weiboParameters.put("source", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            weiboParameters.put(WBConstants.GAME_PARAMS_GAME_ID, str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            weiboParameters.put("uid", str4);
        }
        String readRsponse = HttpManager.readRsponse(requestHttpExecute(context, URL_ACHIEVEMENT_READ_PLAYER_SCORE, "GET", weiboParameters));
        LogUtil.d(TAG, "Response : " + readRsponse);
        return readRsponse;
    }

    public static String readPlayerFriendsScoreInfo(Context context, String str, String str2, String str3, String str4) {
        WeiboParameters weiboParameters = new WeiboParameters("");
        if (!TextUtils.isEmpty(str)) {
            weiboParameters.put("access_token", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            weiboParameters.put("source", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            weiboParameters.put(WBConstants.GAME_PARAMS_GAME_ID, str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            weiboParameters.put("uid", str4);
        }
        weiboParameters.put(WBConstants.GAME_PARAMS_GAME_CREATE_TIME, new Timestamp(new Date().getTime()));
        String readRsponse = HttpManager.readRsponse(requestHttpExecute(context, URL_ACHIEVEMENT_READ_PLAYER_FRIENDS, "GET", weiboParameters));
        LogUtil.d(TAG, "Response : " + readRsponse);
        return readRsponse;
    }

    public static String readPlayerAchievementGain(Context context, String str, String str2, String str3, String str4) {
        WeiboParameters weiboParameters = new WeiboParameters("");
        if (!TextUtils.isEmpty(str)) {
            weiboParameters.put("access_token", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            weiboParameters.put("source", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            weiboParameters.put(WBConstants.GAME_PARAMS_GAME_ID, str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            weiboParameters.put("uid", str4);
        }
        weiboParameters.put(WBConstants.GAME_PARAMS_GAME_CREATE_TIME, new Timestamp(new Date().getTime()));
        String readRsponse = HttpManager.readRsponse(requestHttpExecute(context, URL_ACHIEVEMENT_USER_GAIN, "GET", weiboParameters));
        LogUtil.d(TAG, "Response : " + readRsponse);
        return readRsponse;
    }

    public void invatationWeiboFriendsByList(Context context, String str, String str2, String str3, WeiboAuthListener weiboAuthListener) {
        WeiboParameters weiboParameters = new WeiboParameters(str2);
        weiboParameters.put("access_token", str);
        weiboParameters.put("source", str2);
        String stringBuilder = new StringBuilder(String.valueOf(INVITATION_URL.toString())).append(weiboParameters.encodeUrl()).toString();
        GameRequestParam gameRequestParam = new GameRequestParam(context);
        gameRequestParam.setAppKey(str2);
        gameRequestParam.setToken(str);
        gameRequestParam.setLauncher(BrowserLauncher.GAME);
        gameRequestParam.setUrl(stringBuilder);
        gameRequestParam.setAuthListener(weiboAuthListener);
        Intent intent = new Intent(context, WeiboSdkBrowser.class);
        Bundle createRequestParamBundle = gameRequestParam.createRequestParamBundle();
        createRequestParamBundle.putString("key_specify_title", str3);
        intent.putExtras(createRequestParamBundle);
        context.startActivity(intent);
    }

    public void invatationWeiboFriendsInOnePage(Context context, String str, String str2, String str3, WeiboAuthListener weiboAuthListener, ArrayList<String> arrayList) {
        String str4;
        StringBuffer stringBuffer = new StringBuffer();
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                str4 = (String) arrayList.get(i);
                if (i == 0) {
                    stringBuffer.append(str4);
                } else {
                    stringBuffer.append("," + str4);
                }
            }
        }
        WeiboParameters weiboParameters = new WeiboParameters(str2);
        weiboParameters.put("access_token", str);
        weiboParameters.put("source", str2);
        str4 = new StringBuilder(String.valueOf(INVITATION_ONE_FRINED_URL.toString())).append(weiboParameters.encodeUrl()).append("&uids=").append(stringBuffer.toString()).toString();
        GameRequestParam gameRequestParam = new GameRequestParam(context);
        gameRequestParam.setAppKey(str2);
        gameRequestParam.setToken(str);
        gameRequestParam.setLauncher(BrowserLauncher.GAME);
        gameRequestParam.setUrl(str4);
        gameRequestParam.setAuthListener(weiboAuthListener);
        Intent intent = new Intent(context, WeiboSdkBrowser.class);
        Bundle createRequestParamBundle = gameRequestParam.createRequestParamBundle();
        createRequestParamBundle.putString("key_specify_title", str3);
        intent.putExtras(createRequestParamBundle);
        context.startActivity(intent);
    }

    private static HttpResponse requestHttpExecute(Context context, String str, String str2, WeiboParameters weiboParameters) {
        Throwable e;
        HttpClient httpClient;
        ByteArrayOutputStream byteArrayOutputStream = null;
        HttpClient newHttpClient;
        try {
            newHttpClient = HttpManager.getNewHttpClient();
            try {
                HttpUriRequest httpGet;
                newHttpClient.getParams().setParameter("http.route.default-proxy", NetStateManager.getAPN());
                if (str2.equals("GET")) {
                    String stringBuilder = new StringBuilder(String.valueOf(str)).append("?").append(weiboParameters.encodeUrl()).toString();
                    httpGet = new HttpGet(stringBuilder);
                    LogUtil.d(TAG, "requestHttpExecute GET Url : " + stringBuilder);
                } else if (str2.equals("POST")) {
                    LogUtil.d(TAG, "requestHttpExecute POST Url : " + str);
                    HttpPost httpPost = new HttpPost(str);
                    OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    OutputStream outputStream;
                    try {
                        Object obj;
                        if (weiboParameters.hasBinaryData()) {
                            httpPost.setHeader("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
                            HttpManager.buildParams(byteArrayOutputStream2, weiboParameters);
                        } else {
                            obj = weiboParameters.get("content-type");
                            if (obj == null || !(obj instanceof String)) {
                                httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
                            } else {
                                weiboParameters.remove("content-type");
                                httpPost.setHeader("Content-Type", (String) obj);
                            }
                            String encodeUrl = weiboParameters.encodeUrl();
                            LogUtil.d(TAG, "requestHttpExecute POST postParam : " + encodeUrl);
                            byteArrayOutputStream2.write(encodeUrl.getBytes("UTF-8"));
                        }
                        httpPost.setEntity(new ByteArrayEntity(byteArrayOutputStream2.toByteArray()));
                        obj = httpPost;
                        outputStream = byteArrayOutputStream2;
                    } catch (IOException e2) {
                        e = e2;
                        outputStream = byteArrayOutputStream2;
                        httpClient = newHttpClient;
                        try {
                            throw new WeiboException(e);
                        } catch (Throwable th) {
                            e = th;
                            newHttpClient = httpClient;
                            if (byteArrayOutputStream != null) {
                                try {
                                    byteArrayOutputStream.close();
                                } catch (IOException e3) {
                                }
                            }
                            HttpManager.shutdownHttpClient(newHttpClient);
                            throw e;
                        }
                    } catch (Throwable th2) {
                        e = th2;
                        outputStream = byteArrayOutputStream2;
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        HttpManager.shutdownHttpClient(newHttpClient);
                        throw e;
                    }
                } else if (str2.equals("DELETE")) {
                    httpGet = new HttpDelete(str);
                } else {
                    httpGet = null;
                }
                HttpResponse execute = newHttpClient.execute(httpGet);
                int statusCode = execute.getStatusLine().getStatusCode();
                if (statusCode != 200) {
                    throw new WeiboHttpException(HttpManager.readRsponse(execute), statusCode);
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e4) {
                    }
                }
                HttpManager.shutdownHttpClient(newHttpClient);
                return execute;
            } catch (IOException e5) {
                e = e5;
                httpClient = newHttpClient;
                throw new WeiboException(e);
            } catch (Throwable th3) {
                e = th3;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                HttpManager.shutdownHttpClient(newHttpClient);
                throw e;
            }
        } catch (IOException e6) {
            e = e6;
            Object obj2 = null;
            throw new WeiboException(e);
        } catch (Throwable th4) {
            e = th4;
            newHttpClient = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            HttpManager.shutdownHttpClient(newHttpClient);
            throw e;
        }
    }
}
