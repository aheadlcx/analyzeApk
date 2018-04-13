package qsbk.app.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.alipay.sdk.sys.a;
import com.baidu.mobstat.StatService;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.qiushibaike.statsdk.StatSDK;
import com.sina.weibo.BuildConfig;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.activity.publish.CirclePublishActivity;
import qsbk.app.activity.security.AccessTokenKeeper;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.fragments.ShareToImType;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.Article;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.CircleTopic;
import qsbk.app.model.PicUrl;
import qsbk.app.model.QiushiTopic;
import qsbk.app.model.Qsjx;
import qsbk.app.model.ShareMsgItem;
import qsbk.app.report.ArticleReporter;
import qsbk.app.share.message.QiushiShareMsg;
import qsbk.app.share.message.ShareMsgData;
import qsbk.app.share.message.ShareToIMMessageActivity;
import qsbk.app.thirdparty.ThirdOauth2AccessToken;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.thirdparty.ThirdPartyParameters;
import qsbk.app.utils.CircleArticleBus;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.SplashAdManager.SplashAdItem;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

public class ShareUtils {
    public static final String ARTICLE_CONTENT = "content";
    public static final String ARTICLE_DESCRIPTION = "description";
    public static final String ARTICLE_ID = "id";
    public static final String ARTICLE_IMAGE = "image";
    public static final int BIND_STATE_FAIL = 2;
    public static final int BIND_STATE_OK = 3;
    public static final int BIND_STATE_UNBIND = 1;
    public static final int QIUSHI_ARTICLE_BRIEF_MAX_NUMBER = 30;
    public static final int QIUSHI_SHARE_CONTENT_PLAIN_TEXT_SUMMARY_MAX_NUMBER = 15;
    public static final String QIUSHI_SHARE_TYPE = "qiu_shi_share_type";
    public static final int QIUSHI_SHARE_TYPE_HAS_GIF = 4;
    public static final int QIUSHI_SHARE_TYPE_HAS_IMAGE = 2;
    public static final int QIUSHI_SHARE_TYPE_HAS_VIDEO = 3;
    public static final int QIUSHI_SHARE_TYPE_PURE_TEXT = 1;
    public static String QQ_ACCESS_TOKEN = "";
    public static final int REDEND = 100;
    public static String RENREN_ACCESS_TOKEN = "";
    public static final int SENT_TO_QIUYOU_CIRCLE = 101;
    public static final int SHARE_ANONYMOUS = 13;
    public static final int SHARE_BLOCK = 19;
    public static final int SHARE_CHAT_MESSAGE = 9;
    public static final int SHARE_COLLECT = 6;
    public static final int SHARE_COPY = 5;
    public static final int SHARE_DELETE = 11;
    public static final int SHARE_DOWNLOAD = 12;
    public static final int SHARE_FORBID = 14;
    public static final int SHARE_NOT_INTEREST = 16;
    public static final int SHARE_NO_PASS_DELETE = 102;
    public static final int SHARE_PASSED_RESEND = 103;
    public static final int SHARE_QIUYOUQUAN = 15;
    public static final int SHARE_QQ = 3;
    public static final int SHARE_QZONE = 10;
    public static final int SHARE_REMOVE = 18;
    public static final int SHARE_REPORT = 7;
    public static final int SHARE_REPORT_TOPIC = 20;
    public static final int SHARE_SINA = 1;
    public static final int SHARE_TOP = 17;
    public static final String SHARE_TO_QIUYOU_NAME = "SHARE_TO_QIUYOU_NAME";
    public static final int SHARE_WEIXIN_PY = 4;
    public static final int SHARE_WEIXIN_PYQ = 8;
    public static String SINA_ACCESS_TOKEN = "";
    public static final int TO_DIALOG_AUTHORIZE = 2;
    public static final int TO_REPORT = 3;
    public static final int TO_SHARE = 1;
    private static final String b = ShareUtils.class.getSimpleName();
    public static final String defaultIconUrl = "http://pic.qiushibaike.com/Fid_zElg6adanSQ5_PWJ6qdUvQoj.png";
    public static ArrayList<WeakReference<ShareUtils$OnShareListener>> listeners;
    public static boolean mIsArticleShareToFriends = false;
    public static volatile boolean mbShareToQiuyouSucess = false;
    Handler a = new s(this);
    private ArticleReporter c = null;

    public ShareUtils() {
        if (QsbkApp.currentUser != null) {
            SINA_ACCESS_TOKEN = QsbkApp.currentUser.userId + "_sina_access_token";
            QQ_ACCESS_TOKEN = QsbkApp.currentUser.userId + "_qq_access_token";
            RENREN_ACCESS_TOKEN = QsbkApp.currentUser.userId + "_renren_access_token";
        }
    }

    public static void registerShareListener(ShareUtils$OnShareListener shareUtils$OnShareListener) {
        if (shareUtils$OnShareListener != null) {
            if (listeners == null) {
                listeners = new ArrayList();
            }
            listeners.add(new WeakReference(shareUtils$OnShareListener));
        }
    }

    public static void unregisterShareListener(ShareUtils$OnShareListener shareUtils$OnShareListener) {
        if (listeners != null) {
            for (int size = listeners.size() - 1; size >= 0; size--) {
                ShareUtils$OnShareListener shareUtils$OnShareListener2 = (ShareUtils$OnShareListener) ((WeakReference) listeners.get(size)).get();
                if (shareUtils$OnShareListener2 == null || shareUtils$OnShareListener2 == shareUtils$OnShareListener) {
                    listeners.remove(size);
                }
            }
            if (listeners.size() == 0) {
                listeners = null;
            }
        }
    }

    public static void Share(Context context, String str, int i) {
        ShareUtils shareUtils = new ShareUtils();
        Map hashMap = new HashMap();
        ToastAndDialog.makeNeutralToast(QsbkApp.mContext, "分享中...", Integer.valueOf(1)).show();
        CharSequence charSequence = "";
        String str2 = "";
        switch (i) {
            case 1:
                charSequence = getWeiboAccessTokenKey();
                break;
        }
        LogUtil.e("share token:" + charSequence);
        StatService.onEvent(QsbkApp.mContext, str2, "pass", 1);
        if (TextUtils.isEmpty(charSequence)) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "绑定信息出错，请重新绑定", Integer.valueOf(1)).show();
        } else {
            new z("qbk-ShareUtl", str, hashMap, shareUtils, i).start();
        }
    }

    public static void shareWeibo(Qsjx qsjx) {
        WeiboShareActivity.shareToWeiBo(qsjx.getShareTitle(), TextUtils.isEmpty(qsjx.detail) ? qsjx.getShareTitle() : qsjx.detail, qsjx.link, qsjx.picUrl);
    }

    public static void shareWeibo(QiushiTopic qiushiTopic) {
        WeiboShareActivity.shareToWeiBo(qiushiTopic.content, "", qiushiTopic.getWebUrl(), qiushiTopic.icon);
    }

    public static String getWeiboAccessTokenKey() {
        ThirdOauth2AccessToken weiboAccessToken = getWeiboAccessToken();
        if (weiboAccessToken != null) {
            return weiboAccessToken.getToken();
        }
        return null;
    }

    public static ThirdOauth2AccessToken getWeiboAccessToken() {
        String str = null;
        ThirdOauth2AccessToken readAccessToken = AccessTokenKeeper.readAccessToken(QsbkApp.getInstance());
        if (readAccessToken != null && readAccessToken.getType() == 1) {
            return readAccessToken;
        }
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(SINA_ACCESS_TOKEN);
        if (TextUtils.isEmpty(sharePreferencesValue)) {
            return null;
        }
        long j = 0;
        String[] split = sharePreferencesValue.split(a.b);
        if (split.length > 0) {
            str = split[0];
            j = Long.valueOf(split[1].split("=")[1]).longValue();
        }
        ThirdOauth2AccessToken thirdOauth2AccessToken = new ThirdOauth2AccessToken();
        thirdOauth2AccessToken.setToken(str);
        thirdOauth2AccessToken.setExpiresTime(j);
        return thirdOauth2AccessToken;
    }

    public static boolean needNetwork(int i) {
        return i == 4 || i == 3 || i == 10 || i == 8 || i == 1;
    }

    public static boolean checkAndAlertNetworkStatus(Context context) {
        if (HttpUtils.isNetworkConnected(context)) {
            return true;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, context.getResources().getString(R.string.network_not_connected), Integer.valueOf(0)).show();
        return false;
    }

    public static void openShareDialog(Fragment fragment, Activity activity, int i, ShareMsgItem shareMsgItem) {
        if (fragment != null) {
            fragment.startActivityForResult(getIntent(fragment.getContext(), shareMsgItem), i);
        } else {
            activity.startActivityForResult(getIntent(activity, shareMsgItem), i);
        }
    }

    public static Intent getIntent(Context context, ShareMsgItem shareMsgItem) {
        Intent intent = new Intent(context, WebShareActivity.class);
        intent.putExtra("share_msg_item", shareMsgItem);
        return intent;
    }

    @Deprecated
    public static void openShareDialog(Fragment fragment, Activity activity, int i, boolean z) {
        Intent intent = new Intent(activity, NewShareActivity.class);
        intent.putExtra(NewShareActivity.KEY_COLLECTED, z);
        fragment.startActivityForResult(intent, i);
    }

    public static void openShareDialog(Fragment fragment, Activity activity, int i, boolean z, Article article, boolean z2) {
        Intent intent = new Intent(activity, NewShareActivity.class);
        intent.putExtra(NewShareActivity.FROM_MANAGEQIUSHI, z2);
        intent.putExtra("article", article);
        fragment.startActivityForResult(intent, i);
    }

    public static void openShareDialog(Fragment fragment, Activity activity, int i, boolean z, Article article, View view) {
        openShareDialogOrientation(fragment, activity, i, z, article, false, view);
    }

    public static void openShareDialogOrientation(Fragment fragment, Activity activity, int i, boolean z, Article article, boolean z2, View view) {
        Intent intent = new Intent(activity, z2 ? LandscapeNewShareActivity.class : NewShareActivity.class);
        intent.putExtra(NewShareActivity.KEY_COLLECTED, z);
        intent.putExtra("article", article);
        if (view != null) {
            intent.putExtra(NewShareActivity.KEY_SHARE_BTN_LOCATION, UIHelper.getRectOnScreen(view));
        }
        if (fragment != null) {
            fragment.startActivityForResult(intent, i);
        } else {
            activity.startActivityForResult(intent, i);
        }
    }

    public static void openShareDialogOrientation(Fragment fragment, Activity activity, int i, boolean z, Article article, boolean z2) {
        openShareDialogOrientation(fragment, activity, i, z, article, z2, null);
    }

    public static void openShareDialog(Activity activity, int i, CircleArticle circleArticle) {
        Intent intent = new Intent(activity, NewShareActivity.class);
        intent.putExtra(NewShareActivity.FROM_CIRCLE_ARTICLE, true);
        intent.putExtra("circleArticle", circleArticle);
        activity.startActivityForResult(intent, i);
    }

    public static void openShareDialog(Activity activity, int i, CircleArticle circleArticle, String str) {
        openShareDialog(activity, i, circleArticle, str, false);
    }

    public static void openShareDialog(Activity activity, int i, CircleArticle circleArticle, String str, boolean z) {
        Intent intent = new Intent(activity, z ? LandscapeNewShareActivity.class : NewShareActivity.class);
        intent.putExtra(NewShareActivity.FROM_CIRCLE_ARTICLE, true);
        intent.putExtra(NewShareActivity.FROM_CIRCLE_ARTICLE_TYPE, str);
        intent.putExtra("circleArticle", circleArticle);
        activity.startActivityForResult(intent, i);
    }

    public static void openShareDialog(Fragment fragment, int i, CircleArticle circleArticle) {
        openShareDialog(fragment, i, circleArticle, "");
    }

    public static void openShareDialog(Fragment fragment, int i, CircleArticle circleArticle, String str) {
        openShareDialog(fragment, i, circleArticle, str, null);
    }

    public static void openShareDialog(Fragment fragment, int i, CircleArticle circleArticle, String str, View view) {
        Intent intent = new Intent(fragment.getActivity(), NewShareActivity.class);
        intent.putExtra(NewShareActivity.FROM_CIRCLE_ARTICLE, true);
        intent.putExtra(NewShareActivity.FROM_CIRCLE_ARTICLE_TYPE, str);
        if (view != null) {
            intent.putExtra(NewShareActivity.KEY_SHARE_BTN_LOCATION, UIHelper.getRectOnScreen(view));
        }
        intent.putExtra("circleArticle", circleArticle);
        fragment.startActivityForResult(intent, i);
    }

    public static void openShareDialog(Context context, CircleArticle circleArticle) {
        Intent intent = new Intent(context, NewShareActivity.class);
        intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        intent.putExtra(NewShareActivity.FROM_CIRCLE_ARTICLE, true);
        intent.putExtra("circleArticle", circleArticle);
        context.startActivity(intent);
    }

    public static void openShareDialog(Activity activity, int i, CircleTopic circleTopic) {
        Intent intent = new Intent(activity, NewShareActivity.class);
        intent.putExtra(NewShareActivity.FROM_CIRCLE_TOPIC, true);
        intent.putExtra("circleTopic", circleTopic);
        activity.startActivityForResult(intent, i);
    }

    public static void openShareDialog(Activity activity, int i, CircleArticle circleArticle, CircleTopic circleTopic) {
        Intent intent = new Intent(activity, NewShareActivity.class);
        intent.putExtra(NewShareActivity.FROM_CIRCLE_ARTICLE, true);
        intent.putExtra("circleArticle", circleArticle);
        intent.putExtra("circleTopic", circleTopic);
        intent.putExtra(NewShareActivity.FROM_CIRCLE_TOPIC_ITEM, true);
        activity.startActivityForResult(intent, i);
    }

    public static void openShareDialog(Fragment fragment, int i, CircleArticle circleArticle, CircleTopic circleTopic) {
        Intent intent = new Intent(fragment.getActivity(), NewShareActivity.class);
        intent.putExtra(NewShareActivity.FROM_CIRCLE_ARTICLE, true);
        intent.putExtra("circleArticle", circleArticle);
        intent.putExtra("circleTopic", circleTopic);
        intent.putExtra(NewShareActivity.FROM_CIRCLE_TOPIC_ITEM, true);
        fragment.startActivityForResult(intent, i);
    }

    public static void openShareDialog(Activity activity, int i, CircleArticle circleArticle, CircleTopic circleTopic, String str) {
        Intent intent = new Intent(activity, NewShareActivity.class);
        intent.putExtra(NewShareActivity.FROM_CIRCLE_ARTICLE, true);
        intent.putExtra("circleArticle", circleArticle);
        intent.putExtra("circleTopic", circleTopic);
        intent.putExtra(NewShareActivity.FROM_CIRCLE_ARTICLE_TYPE, str);
        intent.putExtra(NewShareActivity.FROM_CIRCLE_TOPIC_ITEM, true);
        activity.startActivityForResult(intent, i);
    }

    public static void openShareDialog(Fragment fragment, int i, CircleArticle circleArticle, CircleTopic circleTopic, String str) {
        Intent intent = new Intent(fragment.getActivity(), NewShareActivity.class);
        intent.putExtra(NewShareActivity.FROM_CIRCLE_ARTICLE, true);
        intent.putExtra("circleArticle", circleArticle);
        intent.putExtra("circleTopic", circleTopic);
        intent.putExtra(NewShareActivity.FROM_CIRCLE_ARTICLE_TYPE, str);
        intent.putExtra(NewShareActivity.FROM_CIRCLE_TOPIC_ITEM, true);
        fragment.startActivityForResult(intent, i);
    }

    public static void openShareDialog(Activity activity, int i, boolean z, Article article) {
        Intent intent = new Intent(activity, NewShareActivity.class);
        intent.putExtra(NewShareActivity.KEY_COLLECTED, z);
        intent.putExtra("article", article);
        activity.startActivityForResult(intent, i);
    }

    public static void openShareDialog(Activity activity, int i) {
        Intent intent = new Intent(activity, NewShareActivity.class);
        intent.putExtra(NewShareActivity.FROM_QIUSHI_HIGHLIGHT, true);
        activity.startActivityForResult(intent, i);
    }

    public static void openShareDialog(Activity activity, QiushiTopic qiushiTopic, int i) {
        Intent intent = new Intent(activity, NewShareActivity.class);
        intent.putExtra(NewShareActivity.FROM_QIUSHI_TOPIC, true);
        intent.putExtra(SplashAdItem.AD_QIUSHI_TOPIC, qiushiTopic);
        activity.startActivityForResult(intent, i);
    }

    public static void openShareDialog(Fragment fragment, QiushiTopic qiushiTopic, int i) {
        Intent intent = new Intent(fragment.getActivity(), NewShareActivity.class);
        intent.putExtra(NewShareActivity.FROM_CIRCLE_ARTICLE, true);
        intent.putExtra(SplashAdItem.AD_QIUSHI_TOPIC, qiushiTopic);
        intent.putExtra(NewShareActivity.FROM_QIUSHI_TOPIC, true);
        fragment.startActivityForResult(intent, i);
    }

    public static boolean isWxInstalled(Context context) {
        return WXAPIFactory.createWXAPI(context, Constants.APP_ID).isWXAppInstalled();
    }

    private static void a(Activity activity, String str, Article article) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("share_count", 0);
        int i = sharedPreferences.getInt(article.id, 0);
        if (i < 10) {
            sharedPreferences.edit().putInt(article.id, i + 1).apply();
            String str2 = Constants.CONTENT_DOMAINS + "article/share";
            Map hashMap = new HashMap();
            hashMap.put("article_id", article.id);
            hashMap.put("channel", str);
            SimpleHttpTask simpleHttpTask = new SimpleHttpTask(str2, null);
            simpleHttpTask.setMapParams(hashMap);
            simpleHttpTask.execute();
            article.shareCount++;
            if (listeners != null) {
                for (i = listeners.size() - 1; i >= 0; i--) {
                    ShareUtils$OnShareListener shareUtils$OnShareListener = (ShareUtils$OnShareListener) ((WeakReference) listeners.get(i)).get();
                    if (shareUtils$OnShareListener != null) {
                        shareUtils$OnShareListener.onShared(article);
                    }
                }
            }
        }
    }

    private static void a(Activity activity, String str, CircleArticle circleArticle) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("share_count", 0);
        int i = sharedPreferences.getInt(circleArticle.id, 0);
        if (i < 10) {
            sharedPreferences.edit().putInt(circleArticle.id, i + 1).apply();
            Map hashMap = new HashMap();
            hashMap.put("article_id", circleArticle.id);
            hashMap.put("channel", str);
            SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.CIRCLE_ARTICLE_SHARE, null);
            simpleHttpTask.setMapParams(hashMap);
            simpleHttpTask.execute();
            circleArticle.shareCount++;
            CircleArticleBus.updateArticle(circleArticle, null);
        }
    }

    public static void shareArticle2QiuyouCircle(Context context, Article article) {
        if (context != null && article != null) {
            if (QsbkApp.currentUser == null) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "登录后才能分享哦！", Integer.valueOf(0)).show();
                LoginPermissionClickDelegate.startLoginActivity(context);
                return;
            }
            CirclePublishActivity.launch(context, new QYQShareInfo(article));
        }
    }

    public static void shareArticle2QiuyouCircle(Context context, CircleArticle circleArticle) {
        if (context != null && circleArticle != null && QsbkApp.currentUser == null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "登录后才能分享哦！", Integer.valueOf(0)).show();
            LoginPermissionClickDelegate.startLoginActivity(context);
        }
    }

    public static void webShareToCircle(Context context, ShareMsgItem shareMsgItem) {
        if (context != null && shareMsgItem != null) {
            if (QsbkApp.currentUser == null) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "登录后才能分享哦！", Integer.valueOf(0)).show();
                context.startActivity(new Intent(context, ActionBarLoginActivity.class));
                return;
            }
            CirclePublishActivity.launch(context, new QYQShareInfo(shareMsgItem, shareMsgItem.shareFor == 5 ? QYQShareInfo.TYPE_NEWS : null));
        }
    }

    private static void a(String str, Article article) {
        StatService.onEvent(QsbkApp.mContext, "artact_" + str, article.id + "");
        StatSDK.onEvent(QsbkApp.mContext, "artact_" + str, article.id + "");
    }

    private static void a(String str, CircleTopic circleTopic) {
        StatService.onEvent(QsbkApp.mContext, "circle_topic_" + str, circleTopic.id + "");
        StatSDK.onEvent(QsbkApp.mContext, "circle_topic_" + str, circleTopic.id + "");
    }

    private static void a(String str, CircleArticle circleArticle) {
        StatService.onEvent(QsbkApp.mContext, "circle_article_" + str, circleArticle.id + "");
        StatSDK.onEvent(QsbkApp.mContext, "circle_article_" + str, circleArticle.id + "");
    }

    private static void a(String str, Qsjx qsjx) {
        StatService.onEvent(QsbkApp.mContext, "qsjx_" + str, qsjx.msgId + "");
        StatSDK.onEvent(QsbkApp.mContext, "qsjx_" + str, qsjx.msgId + "");
    }

    private static void a(String str, QiushiTopic qiushiTopic) {
        StatService.onEvent(QsbkApp.mContext, "qsjx_" + str, qiushiTopic.id + "");
        StatSDK.onEvent(QsbkApp.mContext, "qsjx_" + str, qiushiTopic.id + "");
    }

    public static void doWebShare(int i, Activity activity, Fragment fragment, ShareMsgItem shareMsgItem) {
        if (activity == null || !needNetwork(i) || checkAndAlertNetworkStatus(activity)) {
            ShareUtils shareUtils = new ShareUtils();
            switch (i) {
                case 1:
                    a(activity, fragment, shareMsgItem, i, shareUtils);
                    return;
                case 3:
                    b(activity, shareMsgItem);
                    return;
                case 4:
                case 8:
                    a(activity, i, shareMsgItem);
                    if (i != 4 && i == 8) {
                        return;
                    }
                    return;
                case 9:
                    a(activity, shareMsgItem, i);
                    return;
                case 10:
                    a(activity, shareMsgItem);
                    return;
                case 15:
                    webShareToCircle(activity, shareMsgItem);
                    return;
                case 101:
                    return;
                default:
                    return;
            }
        }
    }

    public static void doShare(int i, Activity activity, Fragment fragment, Article article, View view) {
        if (activity == null || !needNetwork(i) || checkAndAlertNetworkStatus(activity)) {
            ShareUtils shareUtils = new ShareUtils();
            switch (i) {
                case 3:
                    a(activity, ThirdPartyConstants.THIRDPARTY_TYLE_QQ, article);
                    b(activity, article);
                    a(ThirdPartyConstants.THIRDPARTY_TYLE_QQ, article);
                    return;
                case 4:
                case 8:
                    a(activity, i, article);
                    if (i == 4) {
                        a(activity, ThirdPartyConstants.THIRDPARTY_TYLE_WX, article);
                        a("py", article);
                        return;
                    } else if (i == 8) {
                        a(activity, "wzone", article);
                        a("pyq", article);
                        return;
                    } else {
                        return;
                    }
                case 5:
                    shareUtils.Share(activity, article.content, article.image, i, -1);
                    a("copy", article);
                    return;
                case 6:
                    shareUtils.tryCollection(view, activity, article.id);
                    a("collect", article);
                    return;
                case 7:
                    if (fragment != null) {
                        shareUtils.getArticleReporter(activity).chooseReportOption(fragment);
                    } else {
                        shareUtils.getArticleReporter(activity).chooseReportOption();
                    }
                    a("report", article);
                    return;
                case 9:
                    DebugUtil.debug(b, "doShare, code=" + i);
                    a(activity, article, i);
                    a(activity, "qbim", article);
                    a("qbim", article);
                    return;
                case 10:
                    a(activity, com.tencent.connect.common.Constants.SOURCE_QZONE, article);
                    a(activity, article);
                    a(com.tencent.connect.common.Constants.SOURCE_QZONE, article);
                    return;
                case 11:
                case 12:
                case 13:
                case 14:
                case 100:
                case 101:
                case 102:
                case 103:
                case 9999:
                case 10000:
                    return;
                case 15:
                    a(activity, "qyq", article);
                    return;
                default:
                    a(activity, fragment, article, i, shareUtils);
                    return;
            }
        }
    }

    public static void doShare(int i, Activity activity, Fragment fragment, CircleArticle circleArticle) {
        LogUtil.e("do web share to circle topic");
        if (activity == null || !needNetwork(i) || checkAndAlertNetworkStatus(activity)) {
            ShareUtils shareUtils = new ShareUtils();
            switch (i) {
                case 1:
                    a(activity, "sina", circleArticle);
                    a(activity, fragment, circleArticle, i, shareUtils);
                    return;
                case 3:
                    b(activity, circleArticle);
                    a(activity, ThirdPartyConstants.THIRDPARTY_TYLE_QQ, circleArticle);
                    a(ThirdPartyConstants.THIRDPARTY_TYLE_QQ, circleArticle);
                    return;
                case 4:
                case 8:
                    a(activity, i, circleArticle);
                    if (i == 4) {
                        a(activity, ThirdPartyConstants.THIRDPARTY_TYLE_WX, circleArticle);
                        a("py", circleArticle);
                        return;
                    } else if (i == 8) {
                        a(activity, "wzone", circleArticle);
                        a("pyq", circleArticle);
                        return;
                    } else {
                        return;
                    }
                case 9:
                    DebugUtil.debug(b, "doShare, code=" + i);
                    a(activity, circleArticle, i);
                    a(activity, "qbim", circleArticle);
                    a("qbim", circleArticle);
                    return;
                case 10:
                    a(activity, circleArticle);
                    a(com.tencent.connect.common.Constants.SOURCE_QZONE, circleArticle);
                    a(activity, com.tencent.connect.common.Constants.SOURCE_QZONE, circleArticle);
                    return;
                default:
                    return;
            }
        }
    }

    public static void doShare(int i, Activity activity, Fragment fragment, CircleTopic circleTopic, View view, boolean z) {
        LogUtil.e("do web share to circle topic");
        if (activity == null || !needNetwork(i) || checkAndAlertNetworkStatus(activity)) {
            ShareUtils shareUtils = new ShareUtils();
            switch (i) {
                case 1:
                    a(activity, fragment, circleTopic, i, shareUtils);
                    return;
                case 3:
                    b(activity, circleTopic);
                    a(ThirdPartyConstants.THIRDPARTY_TYLE_QQ, circleTopic);
                    return;
                case 4:
                case 8:
                    a(activity, i, circleTopic);
                    if (i == 4) {
                        a("py", circleTopic);
                        return;
                    } else if (i == 8) {
                        a("pyq", circleTopic);
                        return;
                    } else {
                        return;
                    }
                case 9:
                    DebugUtil.debug(b, "doShare, code=" + i);
                    a(activity, circleTopic, i);
                    a("qbim", circleTopic);
                    return;
                case 10:
                    a(activity, circleTopic);
                    a(com.tencent.connect.common.Constants.SOURCE_QZONE, circleTopic);
                    return;
                default:
                    return;
            }
        }
    }

    public static void doShare(int i, Activity activity, Qsjx qsjx) {
        switch (i) {
            case 1:
                shareWeibo(qsjx);
                a("weibo", qsjx);
                return;
            case 3:
                b(activity, qsjx);
                a(ThirdPartyConstants.THIRDPARTY_TYLE_QQ, qsjx);
                return;
            case 4:
            case 8:
                a(activity, i, qsjx);
                if (i == 4) {
                    a("py", qsjx);
                    return;
                } else if (i == 8) {
                    a("pyq", qsjx);
                    return;
                } else {
                    return;
                }
            case 9:
                DebugUtil.debug(b, "doShare, code=" + i);
                a(activity, qsjx, i);
                a("qbim", qsjx);
                return;
            case 10:
                a(activity, qsjx);
                a(com.tencent.connect.common.Constants.SOURCE_QZONE, qsjx);
                return;
            case 15:
                shareQSJX2QiuyouCircle((Context) activity, qsjx);
                return;
            default:
                return;
        }
    }

    public static void doShare(int i, Activity activity, QiushiTopic qiushiTopic) {
        switch (i) {
            case 1:
                shareWeibo(qiushiTopic);
                a("weibo", qiushiTopic);
                return;
            case 3:
                b(activity, qiushiTopic);
                a(ThirdPartyConstants.THIRDPARTY_TYLE_QQ, qiushiTopic);
                return;
            case 4:
            case 8:
                a(activity, i, qiushiTopic);
                if (i == 4) {
                    a("py", qiushiTopic);
                    return;
                } else {
                    a("pyq", qiushiTopic);
                    return;
                }
            case 9:
                DebugUtil.debug(b, "doShare, code=" + i);
                a(activity, qiushiTopic, i);
                a("qbim", qiushiTopic);
                return;
            case 10:
                a(activity, qiushiTopic);
                a(com.tencent.connect.common.Constants.SOURCE_QZONE, qiushiTopic);
                return;
            case 15:
                shareQSJX2QiuyouCircle((Context) activity, qiushiTopic);
                return;
            default:
                return;
        }
    }

    private static void a(Activity activity, Qsjx qsjx) {
        Object obj;
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        String shareTitle = qsjx.getShareTitle();
        if (TextUtils.isEmpty(shareTitle)) {
            shareTitle = qsjx.detail;
        }
        String str = qsjx.detail;
        if (TextUtils.isEmpty(str)) {
            str = shareTitle;
        }
        if (str.length() > 19) {
            str = str.substring(0, 19) + "...";
        }
        bundle.putString("title", shareTitle);
        bundle.putString("summary", str);
        bundle.putString("targetUrl", qsjx.link);
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(qsjx.picUrl)) {
            obj = defaultIconUrl;
        } else {
            obj = qsjx.picUrl;
        }
        arrayList.add(obj);
        if (arrayList.size() > 0) {
            bundle.putStringArrayList("imageUrl", arrayList);
        }
        ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, activity).shareToQzone(activity, bundle, new aa());
    }

    private static void a(Activity activity, QiushiTopic qiushiTopic) {
        Object obj;
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        bundle.putString("title", qiushiTopic.content);
        bundle.putString("targetUrl", qiushiTopic.getWebUrl());
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(qiushiTopic.icon)) {
            obj = defaultIconUrl;
        } else {
            obj = qiushiTopic.icon;
        }
        arrayList.add(obj);
        if (arrayList.size() > 0) {
            bundle.putStringArrayList("imageUrl", arrayList);
        }
        ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, activity).shareToQzone(activity, bundle, new ab());
    }

    private static void b(Activity activity, Qsjx qsjx) {
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        String shareTitle = qsjx.getShareTitle();
        if (TextUtils.isEmpty(shareTitle)) {
            shareTitle = qsjx.detail;
        }
        String str = qsjx.detail;
        if (TextUtils.isEmpty(str)) {
            str = shareTitle;
        }
        if (str != null && str.length() > 19) {
            str = str.substring(0, 19) + "...";
        }
        bundle.putString("title", shareTitle);
        bundle.putString("summary", str);
        bundle.putString("targetUrl", qsjx.link);
        bundle.putString("imageUrl", qsjx.picUrl);
        ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, activity).shareToQQ(activity, bundle, new ac());
    }

    private static void b(Activity activity, QiushiTopic qiushiTopic) {
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        bundle.putString("title", qiushiTopic.content);
        bundle.putString("summary", "");
        bundle.putString("targetUrl", qiushiTopic.getWebUrl());
        bundle.putString("imageUrl", qiushiTopic.icon);
        ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, activity).shareToQQ(activity, bundle, new ad());
    }

    private static void a(Activity activity, int i, Qsjx qsjx) {
        if (isWxInstalled(activity)) {
            Intent intent = new Intent(activity, FakeWXEntryActivity.class);
            intent.putExtra("type", 4);
            intent.putExtra("where", i);
            intent.putExtra(QYQShareInfo.TYPE_QSJX, qsjx);
            activity.startActivity(intent);
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "微信未安装，请先安装微信", Integer.valueOf(0)).show();
    }

    private static void a(Activity activity, int i, QiushiTopic qiushiTopic) {
        if (isWxInstalled(activity)) {
            Intent intent = new Intent(activity, FakeWXEntryActivity.class);
            intent.putExtra("type", 5);
            intent.putExtra("where", i);
            intent.putExtra(SplashAdItem.AD_QIUSHI_TOPIC, qiushiTopic);
            activity.startActivity(intent);
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "微信未安装，请先安装微信", Integer.valueOf(0)).show();
    }

    public static void shareQSJX2QiuyouCircle(Context context, Qsjx qsjx) {
        if (context != null && qsjx != null) {
            if (QsbkApp.currentUser == null) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "登录后才能分享哦！", Integer.valueOf(0)).show();
                LoginPermissionClickDelegate.startLoginActivity(context);
                return;
            }
            CirclePublishActivity.launch(context, new QYQShareInfo(qsjx));
        }
    }

    public static void shareQSJX2QiuyouCircle(Context context, QiushiTopic qiushiTopic) {
        if (context != null && qiushiTopic != null) {
            if (QsbkApp.currentUser == null) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "登录后才能分享哦！", Integer.valueOf(0)).show();
                LoginPermissionClickDelegate.startLoginActivity(context);
                return;
            }
            CirclePublishActivity.launch(context, new QYQShareInfo(qiushiTopic));
        }
    }

    private static void a(Activity activity, int i, Article article) {
        Log.e(b, "shareToWeixin2222, code=" + i + ",article=" + article.toString());
        if (isWxInstalled(activity)) {
            Intent intent = new Intent(activity, FakeWXEntryActivity.class);
            String content = article.getContent();
            if (content != null && content.length() > 100) {
                content = content.substring(0, 100);
            }
            intent.putExtra("content", content);
            intent.putExtra("articleId", article.id);
            intent.putExtra("where", i);
            intent.putExtra("is_share_with_mini_program", true);
            if (article.isVideoArticle()) {
                content = article.absPicPath;
                if (!(content == null || content.indexOf(".jpg") == -1)) {
                    content = content.substring(0, content.indexOf(".jpg")) + "_share.jpg";
                }
                intent.putExtra("image", content);
                intent.putExtra("url", String.format("https://www.qiushibaike.com/share/%1$s?source=wx", new Object[]{article.id}));
            } else if (article.isImageArticle()) {
                intent.putExtra("image", article.getImageUrl());
            }
            activity.startActivity(intent);
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "微信未安装，请先安装微信", Integer.valueOf(0)).show();
    }

    private static void a(Activity activity, int i, CircleTopic circleTopic) {
        if (isWxInstalled(activity)) {
            Intent intent = new Intent(activity, FakeWXEntryActivity.class);
            String str = circleTopic.content;
            if (str != null && str.length() > 100) {
                str = str.substring(0, 100);
            }
            intent.putExtra("content", str);
            intent.putExtra("articleId", circleTopic.id);
            intent.putExtra("where", i);
            intent.putExtra("type", 2);
            intent.putExtra("circleTopic", circleTopic);
            if (circleTopic.icon != null) {
                intent.putExtra("image", circleTopic.icon.url);
            } else if (circleTopic.picUrls.size() > 0) {
                intent.putExtra("image", ((PicUrl) circleTopic.picUrls.get(0)).url);
            } else if (circleTopic.picUrls.size() == 0) {
                intent.putExtra("image", defaultIconUrl);
            }
            activity.startActivity(intent);
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "微信未安装，请先安装微信", Integer.valueOf(0)).show();
    }

    private static void a(Activity activity, int i, CircleArticle circleArticle) {
        if (isWxInstalled(activity)) {
            Intent intent = new Intent(activity, FakeWXEntryActivity.class);
            String str = circleArticle.content;
            if (str != null && str.length() > 100) {
                str = str.substring(0, 100);
            }
            intent.putExtra("content", str);
            intent.putExtra("articleId", circleArticle.id);
            intent.putExtra("where", i);
            intent.putExtra("type", 3);
            if (circleArticle.isVideoArticle()) {
                intent.putExtra("image", circleArticle.video.picUrl);
                intent.putExtra("url", String.format(Constants.CIRCLE_ARTICLE_TOUCH + "&source=qzone", new Object[]{circleArticle.id}));
            } else if (circleArticle.hasImage()) {
                intent.putExtra("image", ((PicUrl) circleArticle.picUrls.get(0)).url);
            } else {
                intent.putExtra("image", defaultIconUrl);
            }
            activity.startActivity(intent);
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "微信未安装，请先安装微信", Integer.valueOf(0)).show();
    }

    private static void a(Activity activity, int i, ShareMsgItem shareMsgItem) {
        if (isWxInstalled(activity)) {
            Intent intent = new Intent(activity, FakeWXEntryActivity.class);
            intent.putExtra("share_item", shareMsgItem);
            intent.putExtra("type", 1);
            intent.putExtra("where", i);
            activity.startActivity(intent);
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "微信未安装，请先安装微信", Integer.valueOf(0)).show();
    }

    private static void a(Activity activity, ShareMsgItem shareMsgItem) {
        Object obj;
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        String str = shareMsgItem.title;
        if (TextUtils.isEmpty(str)) {
            str = shareMsgItem.content;
        }
        String str2 = shareMsgItem.content;
        if (TextUtils.isEmpty(str2)) {
            str2 = str;
        }
        if (str2.length() > 19) {
            str2 = str2.substring(0, 19) + "...";
        }
        bundle.putString("title", str);
        bundle.putString("summary", str2);
        bundle.putString("targetUrl", shareMsgItem.link);
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(shareMsgItem.imageUrl)) {
            obj = defaultIconUrl;
        } else {
            obj = shareMsgItem.imageUrl;
        }
        arrayList.add(obj);
        if (arrayList.size() > 0) {
            bundle.putStringArrayList("imageUrl", arrayList);
        }
        ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, activity).shareToQzone(activity, bundle, new ae());
    }

    private static void a(Activity activity, Article article) {
        DebugUtil.debug(b, "shareToQZone, article=" + article);
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        String str = article.content;
        if (article.content.length() > 19) {
            str = article.content.substring(0, 19) + "...";
        }
        bundle.putString("summary", str);
        bundle.putString("targetUrl", String.format("https://www.qiushibaike.com/share/%1$s?source=qzone", new Object[]{article.id}));
        ArrayList arrayList = new ArrayList();
        if (article.isVideoArticle()) {
            Object obj = article.absPicPath;
            if (!(obj == null || obj.indexOf(".jpg") == -1)) {
                obj = obj.substring(0, obj.indexOf(".jpg")) + "_share.jpg";
            }
            arrayList.add(obj);
            bundle.putString("title", "给你看一段好笑视频，点击查看");
        } else if (article.isWordsOnly()) {
            arrayList.add(defaultIconUrl);
            bundle.putString("title", "给你看一条好笑糗事，点击查看");
        } else {
            arrayList.add(QsbkApp.absoluteUrlOfMediumContentImage(article.id, article.image));
            bundle.putString("title", "给你看一张好笑糗图，点击查看");
        }
        if (arrayList.size() > 0) {
            bundle.putStringArrayList("imageUrl", arrayList);
        }
        ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, activity).shareToQzone(activity, bundle, new af());
    }

    private static void a(Activity activity, CircleTopic circleTopic) {
        String str;
        int i = 0;
        DebugUtil.debug(b, "shareToQZone, article=" + circleTopic);
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        String str2 = circleTopic.content;
        if (circleTopic.content.length() > 19) {
            str = circleTopic.content.substring(0, 19) + "...";
        } else {
            str = str2;
        }
        bundle.putString("summary", circleTopic.intro);
        bundle.putString("targetUrl", String.format(Constants.CIRCLE_TOPIC_TOUCH + "&source=qzone", new Object[]{circleTopic.id}));
        ArrayList arrayList = new ArrayList();
        if (circleTopic.icon != null) {
            arrayList.add(circleTopic.icon.url);
            bundle.putString("title", str);
        } else if (circleTopic.picUrls.size() > 0) {
            while (i < circleTopic.picUrls.size()) {
                arrayList.add(((PicUrl) circleTopic.picUrls.get(i)).url);
                i++;
            }
            bundle.putString("title", str);
        } else if (circleTopic.picUrls.size() == 0) {
            arrayList.add(defaultIconUrl);
            bundle.putString("title", str);
        }
        if (arrayList.size() > 0) {
            bundle.putStringArrayList("imageUrl", arrayList);
        }
        ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, activity).shareToQzone(activity, bundle, new ag());
    }

    private static void a(Activity activity, CircleArticle circleArticle) {
        DebugUtil.debug(b, "shareToQZone, article=" + circleArticle);
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        String str = "给你看一条好笑视频，点击查看";
        bundle.putString("summary", circleArticle.content);
        bundle.putString("targetUrl", String.format(Constants.CIRCLE_ARTICLE_TOUCH + "&source=qzone", new Object[]{circleArticle.id}));
        ArrayList arrayList = new ArrayList();
        if (circleArticle.isVideoArticle() && circleArticle.video.picUrl != null) {
            arrayList.add(circleArticle.video.picUrl);
        }
        if (circleArticle.hasImage()) {
            for (int i = 0; i < circleArticle.picUrls.size(); i++) {
                arrayList.add(((PicUrl) circleArticle.picUrls.get(i)).url);
            }
        } else {
            arrayList.add(defaultIconUrl);
        }
        bundle.putString("title", str);
        if (arrayList.size() > 0) {
            bundle.putStringArrayList("imageUrl", arrayList);
        }
        ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, activity).shareToQzone(activity, bundle, new t());
    }

    private static void b(Activity activity, ShareMsgItem shareMsgItem) {
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        String str = shareMsgItem.title;
        if (TextUtils.isEmpty(str)) {
            str = shareMsgItem.content;
        }
        String str2 = shareMsgItem.content;
        if (TextUtils.isEmpty(str2)) {
            str2 = str;
        }
        if (str2.length() > 19) {
            str2 = str2.substring(0, 19) + "...";
        }
        bundle.putString("summary", str2);
        bundle.putString("title", str);
        bundle.putString("targetUrl", shareMsgItem.link);
        bundle.putString("imageUrl", shareMsgItem.imageUrl);
        ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, activity).shareToQQ(activity, bundle, new u());
    }

    private static void b(Activity activity, Article article) {
        DebugUtil.debug(b, "shareToQQ, article=" + article);
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        String str = article.content;
        if (article.content.length() > 19) {
            str = article.content.substring(0, 19) + "...";
        }
        bundle.putString("summary", str);
        bundle.putString("targetUrl", String.format("https://www.qiushibaike.com/share/%1$s?source=qqapp", new Object[]{article.id}));
        if (article.isVideoArticle()) {
            str = article.absPicPath;
            if (!(str == null || str.indexOf(".jpg") == -1)) {
                str = str.substring(0, str.indexOf(".jpg")) + "_share.jpg";
            }
            bundle.putString("imageUrl", str);
            bundle.putString("title", "给你看一条好笑视频，点击查看");
        } else if (article.isWordsOnly()) {
            bundle.putString("title", "给你看一条好笑糗事，点击查看");
            bundle.putString("imageUrl", defaultIconUrl);
        } else {
            bundle.putString("title", "给你看一张好笑糗图，点击查看");
            bundle.putString("imageUrl", QsbkApp.absoluteUrlOfMediumContentImage(article.id, article.image));
        }
        ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, activity).shareToQQ(activity, bundle, new v());
    }

    private static void b(Activity activity, CircleTopic circleTopic) {
        String str;
        DebugUtil.debug(b, "shareToQQ, article=" + circleTopic);
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        String str2 = circleTopic.content;
        if (circleTopic.content.length() > 19) {
            str = circleTopic.content.substring(0, 19) + "...";
        } else {
            str = str2;
        }
        bundle.putString("summary", circleTopic.intro);
        bundle.putString("targetUrl", String.format(Constants.CIRCLE_TOPIC_TOUCH + "&source=qqapp", new Object[]{circleTopic.id}));
        if (circleTopic.icon != null) {
            bundle.putString("imageUrl", circleTopic.icon.url);
            bundle.putString("title", str);
        } else if (circleTopic.picUrls.size() > 0) {
            bundle.putString("imageUrl", ((PicUrl) circleTopic.picUrls.get(0)).url);
            bundle.putString("title", str);
        } else if (circleTopic.picUrls.size() == 0) {
            bundle.putString("imageUrl", defaultIconUrl);
            bundle.putString("title", str);
        }
        ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, activity).shareToQQ(activity, bundle, new w());
    }

    private static void b(Activity activity, CircleArticle circleArticle) {
        DebugUtil.debug(b, "shareToQQ, article=" + circleArticle);
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        String str = "给你看一条好笑视频，点击查看";
        bundle.putString("summary", circleArticle.content);
        bundle.putString("targetUrl", String.format(Constants.CIRCLE_ARTICLE_TOUCH + "&source=qqapp", new Object[]{circleArticle.id}));
        if (circleArticle.isVideoArticle() && circleArticle.video.picUrl != null) {
            bundle.putString("imageUrl", circleArticle.video.picUrl);
        } else if (circleArticle.hasImage()) {
            bundle.putString("imageUrl", ((PicUrl) circleArticle.picUrls.get(0)).url);
        } else {
            bundle.putString("imageUrl", defaultIconUrl);
        }
        bundle.putString("title", str);
        ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, activity).shareToQQ(activity, bundle, new x());
    }

    private static void a(Activity activity, ShareMsgItem shareMsgItem, int i) {
        mbShareToQiuyouSucess = false;
        if (QsbkApp.currentUser == null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "登录后才能分享哦！", Integer.valueOf(0)).show();
            LoginPermissionClickDelegate.startLoginActivity(activity);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("share_type", shareMsgItem.shareFor == 5 ? ShareToImType.TYPE_NEWS.getValue() : ShareToImType.TYPE_WEB.getValue());
        bundle.putSerializable("share_item", shareMsgItem);
        Intent intent = new Intent(activity, ShareToIMMessageActivity.class);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, i);
    }

    private static void a(Activity activity, Article article, int i) {
        DebugUtil.debug(b, "shareToChatMessage, article=" + article + ",code=" + i);
        mbShareToQiuyouSucess = false;
        if (QsbkApp.currentUser == null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "登录后才能分享哦！", Integer.valueOf(0)).show();
            activity.startActivity(new Intent(activity, ActionBarLoginActivity.class));
            return;
        }
        DebugUtil.debug(b, "id=" + article.id + ",content=" + article.content + ",img=" + article.image + ",isVideo=" + article.isVideoArticle() + ",absPicPath=" + article.absPicPath);
        Bundle bundle = new Bundle();
        bundle.putString("id", article.id);
        bundle.putString("content", article.getContent());
        bundle.putInt("share_type", ShareToImType.TYPE_ARTICLE.getValue());
        if (article.isVideoArticle()) {
            String str = article.absPicPath;
            if (article.isGIFArticle()) {
                bundle.putInt(QIUSHI_SHARE_TYPE, 4);
            } else {
                bundle.putInt(QIUSHI_SHARE_TYPE, 3);
                if (!(str == null || str.indexOf(".jpg") == -1)) {
                    str = str.substring(0, str.indexOf(".jpg")) + "_share.jpg";
                }
            }
            bundle.putString("image", str);
        } else {
            bundle.putString("image", QsbkApp.absoluteUrlOfMediumContentImage(article.id, article.image));
            if (article.isWordsOnly()) {
                bundle.putInt(QIUSHI_SHARE_TYPE, 1);
            } else {
                bundle.putInt(QIUSHI_SHARE_TYPE, 2);
            }
        }
        DebugUtil.debug(b, "bundle=" + bundle.toString() + ",code=" + i);
        Intent intent = new Intent(activity, ShareToIMMessageActivity.class);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, i);
    }

    private static void a(Activity activity, CircleTopic circleTopic, int i) {
        mbShareToQiuyouSucess = false;
        if (QsbkApp.currentUser == null) {
            Toast.makeText(QsbkApp.mContext, "登录后才能分享哦！", 0).show();
            activity.startActivity(new Intent(activity, ActionBarLoginActivity.class));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("id", circleTopic.id);
        bundle.putString("content", circleTopic.content);
        bundle.putSerializable("circleTopic", circleTopic);
        bundle.putBoolean("isFromCircleTopic", true);
        bundle.putInt("share_type", ShareToImType.TYPE_CIRCLE_TOPIC.getValue());
        if (circleTopic.icon != null) {
            bundle.putString("image", circleTopic.icon.url);
        } else if (circleTopic.picUrls.size() > 0) {
            bundle.putString("image", ((PicUrl) circleTopic.picUrls.get(0)).url);
        } else if (circleTopic.picUrls.size() == 0) {
            bundle.putString("image", defaultIconUrl);
        }
        Intent intent = new Intent(activity, ShareToIMMessageActivity.class);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, i);
    }

    private static void a(Activity activity, CircleArticle circleArticle, int i) {
        DebugUtil.debug(b, "shareToChatMessage, article=" + circleArticle + ",code=" + i);
        mbShareToQiuyouSucess = false;
        if (QsbkApp.currentUser == null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "登录后才能分享哦！", Integer.valueOf(0)).show();
            activity.startActivity(new Intent(activity, ActionBarLoginActivity.class));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("id", circleArticle.id);
        bundle.putString("content", circleArticle.content);
        bundle.putInt("share_type", ShareToImType.TYPE_CIRCLE_ARTICLE.getValue());
        if (circleArticle.isVideoArticle()) {
            bundle.putInt(QIUSHI_SHARE_TYPE, 3);
            bundle.putString("image", circleArticle.video.picUrl);
        } else if (circleArticle.hasGIF()) {
            bundle.putInt(QIUSHI_SHARE_TYPE, 4);
            bundle.putString("image", ((PicUrl) circleArticle.picUrls.get(0)).url);
        } else if (circleArticle.hasImage()) {
            bundle.putInt(QIUSHI_SHARE_TYPE, 2);
            bundle.putString("image", ((PicUrl) circleArticle.picUrls.get(0)).url);
        } else {
            bundle.putInt(QIUSHI_SHARE_TYPE, 1);
        }
        Intent intent = new Intent(activity, ShareToIMMessageActivity.class);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, i);
    }

    private static void a(Activity activity, Qsjx qsjx, int i) {
        if (QsbkApp.currentUser == null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "登录后才能分享哦！", Integer.valueOf(0)).show();
            activity.startActivity(new Intent(activity, ActionBarLoginActivity.class));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("share_type", ShareToImType.TYPE_QSJX.getValue());
        bundle.putSerializable(QYQShareInfo.TYPE_QSJX, qsjx);
        Intent intent = new Intent(activity, ShareToIMMessageActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    private static void a(Activity activity, QiushiTopic qiushiTopic, int i) {
        if (QsbkApp.currentUser == null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "登录后才能分享哦！", Integer.valueOf(0)).show();
            activity.startActivity(new Intent(activity, ActionBarLoginActivity.class));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("share_type", ShareToImType.TYPE_QIUSHI_TOPIC.getValue());
        bundle.putSerializable(SplashAdItem.AD_QIUSHI_TOPIC, qiushiTopic);
        Intent intent = new Intent(activity, ShareToIMMessageActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public static String makeMsgData(Bundle bundle) {
        QiushiShareMsg qiushiShareMsg = new QiushiShareMsg();
        qiushiShareMsg.setArticleId(bundle.getString("id"));
        String string = bundle.getString("content");
        if (string.length() > 30) {
            qiushiShareMsg.setPlainText(string.substring(0, 30));
        } else {
            qiushiShareMsg.setPlainText(string);
        }
        qiushiShareMsg.setShareMsgType(bundle.getInt(QIUSHI_SHARE_TYPE, 1) + "");
        qiushiShareMsg.setCoverImageUrl(bundle.getString("image"));
        string = QiushiShareMsg.toJsonString(qiushiShareMsg);
        DebugUtil.debug("QiushiShare", "makeMsgData," + qiushiShareMsg.encodeToJsonObject().toString());
        return string;
    }

    public static String makeShareMsgData(Bundle bundle) {
        ShareMsgData shareMsgData = new ShareMsgData();
        CircleTopic circleTopic = (CircleTopic) bundle.getSerializable("circleTopic");
        shareMsgData.setCircleTopic(circleTopic);
        String string = bundle.getString("content");
        if (string.length() > 30) {
            shareMsgData.setTitle(string.substring(0, 30));
        } else {
            shareMsgData.setTitle(string);
        }
        if (circleTopic.icon != null) {
            shareMsgData.setPreviewImageURL(circleTopic.icon.url);
        } else if (circleTopic.picUrls.size() > 0) {
            shareMsgData.setPreviewImageURL(((PicUrl) circleTopic.picUrls.get(0)).url);
        } else if (circleTopic.picUrls.size() == 0) {
            shareMsgData.setPreviewImageURL(defaultIconUrl);
        }
        return ShareMsgData.toJsonString(shareMsgData);
    }

    public static String getQiushiShareSummary(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString("content");
        if (string.length() > 15) {
            return string.substring(0, 15);
        }
        return string;
    }

    private static void a(Activity activity, Fragment fragment, ShareMsgItem shareMsgItem, int i, ShareUtils shareUtils) {
        DebugUtil.debug(b, "shareToOtherCases, code=" + i);
        if (QsbkApp.currentUser == null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "登录后才能分享哦！", Integer.valueOf(0)).show();
            LoginPermissionClickDelegate.startLoginActivity(activity);
            return;
        }
        Integer checkAccessToken = shareUtils.checkAccessToken(i);
        Intent intent = new Intent(activity, AuthorizeActivity.class);
        if (i == 1) {
            intent.putExtra(QsbkDatabase.TARGET, "sina");
        }
        intent.putExtra("resultCode", i);
        switch (checkAccessToken.intValue()) {
            case 1:
                shareUtils.buidBindUrl(Integer.valueOf(i));
                if (fragment != null) {
                    fragment.startActivityForResult(intent, 2);
                    return;
                } else {
                    activity.startActivityForResult(intent, 2);
                    return;
                }
            case 2:
                shareUtils.buidBindUrl(Integer.valueOf(i));
                ToastAndDialog.makeNegativeToast(activity, "绑定信息过期，请重新绑定", Integer.valueOf(0)).show();
                if (fragment != null) {
                    fragment.startActivityForResult(intent, 2);
                    return;
                } else {
                    activity.startActivityForResult(intent, 2);
                    return;
                }
            case 3:
                shareUtils.WebShare(activity, shareMsgItem, i);
                return;
            default:
                return;
        }
    }

    private static void a(Activity activity, Fragment fragment, CircleTopic circleTopic, int i, ShareUtils shareUtils) {
        DebugUtil.debug(b, "shareToOtherCases, code=" + i);
        if (QsbkApp.currentUser == null) {
            Toast.makeText(QsbkApp.mContext, "登录后才能分享哦！", 0).show();
            activity.startActivity(new Intent(activity, ActionBarLoginActivity.class));
            return;
        }
        Integer checkAccessToken = shareUtils.checkAccessToken(i);
        Intent intent = new Intent(activity, AuthorizeActivity.class);
        if (i == 1) {
            intent.putExtra(QsbkDatabase.TARGET, "sina");
        }
        intent.putExtra("resultCode", i);
        switch (checkAccessToken.intValue()) {
            case 1:
                shareUtils.buidBindUrl(Integer.valueOf(i));
                if (fragment != null) {
                    fragment.startActivityForResult(intent, 2);
                    return;
                } else {
                    activity.startActivityForResult(intent, 2);
                    return;
                }
            case 2:
                shareUtils.buidBindUrl(Integer.valueOf(i));
                Toast.makeText(activity, "绑定信息过期，请重新绑定", 0).show();
                if (fragment != null) {
                    fragment.startActivityForResult(intent, 2);
                    return;
                } else {
                    activity.startActivityForResult(intent, 2);
                    return;
                }
            case 3:
                String str = circleTopic.id;
                shareUtils.Share((Context) activity, circleTopic, i);
                a("weibo", circleTopic);
                return;
            default:
                return;
        }
    }

    private static void a(Activity activity, Fragment fragment, CircleArticle circleArticle, int i, ShareUtils shareUtils) {
        DebugUtil.debug(b, "shareToOtherCases, code=" + i);
        if (QsbkApp.currentUser == null) {
            Toast.makeText(QsbkApp.mContext, "登录后才能分享哦！", 0).show();
            activity.startActivity(new Intent(activity, ActionBarLoginActivity.class));
            return;
        }
        Integer checkAccessToken = shareUtils.checkAccessToken(i);
        Intent intent = new Intent(activity, AuthorizeActivity.class);
        if (i == 1) {
            intent.putExtra(QsbkDatabase.TARGET, "sina");
        }
        intent.putExtra("resultCode", i);
        switch (checkAccessToken.intValue()) {
            case 1:
                shareUtils.buidBindUrl(Integer.valueOf(i));
                if (fragment != null) {
                    fragment.startActivityForResult(intent, 2);
                    return;
                } else {
                    activity.startActivityForResult(intent, 2);
                    return;
                }
            case 2:
                shareUtils.buidBindUrl(Integer.valueOf(i));
                Toast.makeText(activity, "绑定信息过期，请重新绑定", 0).show();
                if (fragment != null) {
                    fragment.startActivityForResult(intent, 2);
                    return;
                } else {
                    activity.startActivityForResult(intent, 2);
                    return;
                }
            case 3:
                String str = circleArticle.id;
                shareUtils.Share((Context) activity, circleArticle, i);
                a("weibo", circleArticle);
                return;
            default:
                return;
        }
    }

    private static void a(Activity activity, Fragment fragment, Article article, int i, ShareUtils shareUtils) {
        DebugUtil.debug(b, "shareToOtherCases, code=" + i);
        if (QsbkApp.currentUser == null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "登录后才能分享哦！", Integer.valueOf(0)).show();
            activity.startActivity(new Intent(activity, ActionBarLoginActivity.class));
            return;
        }
        Integer checkAccessToken = shareUtils.checkAccessToken(i);
        Intent intent = new Intent(activity, AuthorizeActivity.class);
        if (i == 1) {
            intent.putExtra(QsbkDatabase.TARGET, "sina");
        }
        intent.putExtra("resultCode", i);
        switch (checkAccessToken.intValue()) {
            case 1:
                shareUtils.buidBindUrl(Integer.valueOf(i));
                if (fragment != null) {
                    fragment.startActivityForResult(intent, 2);
                    return;
                } else {
                    activity.startActivityForResult(intent, 2);
                    return;
                }
            case 2:
                shareUtils.buidBindUrl(Integer.valueOf(i));
                ToastAndDialog.makeNegativeToast(activity, "绑定信息过期，请重新绑定", Integer.valueOf(0)).show();
                if (fragment != null) {
                    fragment.startActivityForResult(intent, 2);
                    return;
                } else {
                    activity.startActivityForResult(intent, 2);
                    return;
                }
            case 3:
                String str = article.id;
                shareUtils.Share((Context) activity, article, i);
                a(activity, ThirdPartyConstants.THIRDPARTY_TYLE_SINA, article);
                a("weibo", article);
                return;
            default:
                return;
        }
    }

    public static void collection(String str, boolean z) {
        String format;
        if (z) {
            format = String.format(Constants.COLLECT, new Object[]{str});
        } else {
            format = String.format(Constants.DELETE_COLLECT, new Object[]{str});
        }
        new SimpleHttpTask(2, format, new y(z, str)).executeOnExecutor(SimpleHttpTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public static boolean isWeiboInstall() {
        boolean z = true;
        PackageManager packageManager = QsbkApp.getInstance().getPackageManager();
        if (packageManager == null) {
            return false;
        }
        try {
            packageManager.getPackageInfo(BuildConfig.APPLICATION_ID, 1);
        } catch (NameNotFoundException e) {
            z = false;
        }
        return z;
    }

    public void Share(Context context, String str, String str2, int i, int i2) {
        switch (i) {
            case 5:
                a(context, str);
                StatService.onEvent(QsbkApp.mContext, "copy", "pass", 1);
                return;
            default:
                return;
        }
    }

    public void WebShare(Context context, ShareMsgItem shareMsgItem, int i) {
        HashMap hashMap = new HashMap();
        ToastAndDialog.makeNeutralToast(QsbkApp.mContext, "分享中...", Integer.valueOf(1)).show();
        String str = "";
        switch (i) {
            case 1:
                if (TextUtils.isEmpty(getWeiboAccessTokenKey()) && !isWeiboInstall()) {
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "绑定信息出错，请重新绑定", Integer.valueOf(1)).show();
                    break;
                }
                StringBuffer stringBuffer;
                CharSequence charSequence = shareMsgItem.imageUrl;
                String str2 = defaultIconUrl;
                if (!TextUtils.isEmpty(charSequence)) {
                    CharSequence charSequence2 = charSequence;
                }
                StringBuffer stringBuffer2 = new StringBuffer(shareMsgItem.content);
                if (stringBuffer2.toString().length() >= 80) {
                    stringBuffer = new StringBuffer(stringBuffer2.substring(0, 80));
                    stringBuffer.append("...");
                } else {
                    stringBuffer = stringBuffer2;
                }
                stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                stringBuffer.append(shareMsgItem.link);
                stringBuffer.append(" (@糗事百科)");
                WeiboShareActivity.shareToWeiBo("", stringBuffer.toString(), null, str2);
                break;
                break;
        }
        StatService.onEvent(QsbkApp.mContext, str, "pass", 1);
    }

    public void Share(Context context, Article article, int i) {
        HashMap hashMap = new HashMap();
        ToastAndDialog.makeNeutralToast(QsbkApp.mContext, "分享中...", Integer.valueOf(1)).show();
        String str = "";
        switch (i) {
            case 1:
                Object weiboAccessTokenKey = getWeiboAccessTokenKey();
                if (!TextUtils.isEmpty(weiboAccessTokenKey) || isWeiboInstall()) {
                    StringBuffer stringBuffer;
                    StringBuffer stringBuffer2;
                    if (!article.isWordsOnly()) {
                        if (!article.isVideoArticle()) {
                            String absoluteUrlOfMediumContentImage = QsbkApp.absoluteUrlOfMediumContentImage(article.id, article.image);
                            stringBuffer = new StringBuffer(article.getContent());
                            if (stringBuffer.toString().length() >= 80) {
                                stringBuffer2 = new StringBuffer(stringBuffer.substring(0, 80));
                                stringBuffer2.append("...");
                            } else {
                                stringBuffer2 = stringBuffer;
                            }
                            stringBuffer2.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + String.format(Constants.QIUSHI_SHARE_WEIBO_URL, new Object[]{article.id}));
                            WeiboShareActivity.shareToWeiBo("", stringBuffer2.toString(), null, absoluteUrlOfMediumContentImage);
                            break;
                        }
                        String str2 = article.absPicPath;
                        if (!(str2 == null || str2.indexOf(".jpg") == -1)) {
                            str2 = str2.substring(0, str2.indexOf(".jpg")) + "_share.jpg";
                        }
                        StringBuffer stringBuffer3 = new StringBuffer(article.getContent());
                        if (stringBuffer3.toString().length() >= 80) {
                            stringBuffer = new StringBuffer(stringBuffer3.substring(0, 80));
                            stringBuffer.append("...");
                        } else {
                            stringBuffer = stringBuffer3;
                        }
                        stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + String.format(Constants.QIUSHI_SHARE_WEIBO_URL, new Object[]{article.id}));
                        stringBuffer.append(" (@糗事百科)");
                        WeiboShareActivity.shareToWeiBo("", stringBuffer.toString(), null, str2);
                        break;
                    }
                    ThirdPartyParameters thirdPartyParameters = new ThirdPartyParameters();
                    thirdPartyParameters.add("access_token", weiboAccessTokenKey);
                    stringBuffer = new StringBuffer(article.getContent());
                    if (stringBuffer.toString().length() >= 80) {
                        stringBuffer2 = new StringBuffer(stringBuffer.substring(0, 80));
                        LogUtil.e(stringBuffer2.toString());
                        stringBuffer2.append("...");
                    } else {
                        stringBuffer2 = stringBuffer;
                    }
                    stringBuffer2.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + String.format(Constants.QIUSHI_SHARE_WEIBO_URL, new Object[]{article.id}));
                    stringBuffer2.append(" (@糗事百科)");
                    thirdPartyParameters.add("status", stringBuffer2.toString());
                    WeiboShareActivity.shareToWeiBo("", stringBuffer2.toString(), null, null);
                    break;
                }
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "绑定信息出错，请重新绑定", Integer.valueOf(1)).show();
                break;
                break;
        }
        StatService.onEvent(QsbkApp.mContext, str, "pass", 1);
    }

    public void Share(Context context, CircleTopic circleTopic, int i) {
        ToastAndDialog.makeNeutralToast(QsbkApp.mContext, "分享中...", Integer.valueOf(1)).show();
        String str = "";
        switch (i) {
            case 1:
                if (!TextUtils.isEmpty(getWeiboAccessTokenKey()) || isWeiboInstall()) {
                    StringBuffer stringBuffer;
                    if (circleTopic.picUrls.size() != 0 || circleTopic.icon != null) {
                        String str2 = "";
                        if (circleTopic.icon != null) {
                            str2 = circleTopic.icon.url;
                        } else if (circleTopic.picUrls.size() > 0) {
                            str2 = ((PicUrl) circleTopic.picUrls.get(0)).url;
                        } else {
                            str2 = defaultIconUrl;
                        }
                        StringBuffer stringBuffer2 = new StringBuffer(circleTopic.content + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + circleTopic.intro);
                        if (stringBuffer2.toString().length() >= 80) {
                            stringBuffer = new StringBuffer(stringBuffer2.substring(0, 80));
                            stringBuffer.append("...");
                        } else {
                            stringBuffer = stringBuffer2;
                        }
                        stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + String.format(Constants.CIRCLE_TOPIC_TOUCH, new Object[]{circleTopic.id}));
                        stringBuffer.append(" (@糗事百科)");
                        WeiboShareActivity.shareToWeiBo("", stringBuffer.toString(), null, str2);
                        break;
                    }
                    StringBuffer stringBuffer3;
                    stringBuffer = new StringBuffer(circleTopic.content + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + circleTopic.intro);
                    if (stringBuffer.toString().length() >= 80) {
                        stringBuffer3 = new StringBuffer(stringBuffer.substring(0, 80));
                        stringBuffer3.append("...");
                    } else {
                        stringBuffer3 = stringBuffer;
                    }
                    stringBuffer3.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + String.format(Constants.CIRCLE_TOPIC_TOUCH, new Object[]{circleTopic.id}));
                    stringBuffer3.append(" (@糗事百科)");
                    WeiboShareActivity.shareToWeiBo("", stringBuffer3.toString(), null, null);
                    break;
                }
                Toast.makeText(QsbkApp.mContext, "绑定信息出错，请重新绑定", 1).show();
                break;
                break;
        }
        StatService.onEvent(QsbkApp.mContext, str, "pass", 1);
    }

    public void Share(Context context, CircleArticle circleArticle, int i) {
        HashMap hashMap = new HashMap();
        ToastAndDialog.makeNeutralToast(QsbkApp.mContext, "分享中...", Integer.valueOf(1)).show();
        String str = "";
        switch (i) {
            case 1:
                if (!TextUtils.isEmpty(getWeiboAccessTokenKey()) || isWeiboInstall()) {
                    StringBuffer stringBuffer;
                    if (!circleArticle.hasImage() && !circleArticle.isVideoArticle()) {
                        StringBuffer stringBuffer2;
                        stringBuffer = new StringBuffer(circleArticle.content + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + circleArticle.content);
                        if (stringBuffer.toString().length() >= 80) {
                            stringBuffer2 = new StringBuffer(stringBuffer.substring(0, 80));
                            stringBuffer2.append("...");
                        } else {
                            stringBuffer2 = stringBuffer;
                        }
                        stringBuffer2.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + String.format(Constants.CIRCLE_ARTICLE_TOUCH, new Object[]{circleArticle.id}));
                        stringBuffer2.append(" (@糗事百科)");
                        WeiboShareActivity.shareToWeiBo("", stringBuffer2.toString(), null, null);
                        break;
                    }
                    String str2 = "";
                    if (circleArticle.isVideoArticle()) {
                        str2 = circleArticle.video.picUrl;
                    } else if (circleArticle.hasImage()) {
                        str2 = ((PicUrl) circleArticle.picUrls.get(0)).url;
                    } else {
                        str2 = defaultIconUrl;
                    }
                    StringBuffer stringBuffer3 = new StringBuffer(circleArticle.content);
                    if (stringBuffer3.toString().length() >= 80) {
                        stringBuffer = new StringBuffer(stringBuffer3.substring(0, 80));
                        stringBuffer.append("...");
                    } else {
                        stringBuffer = stringBuffer3;
                    }
                    stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + String.format(Constants.CIRCLE_ARTICLE_TOUCH, new Object[]{circleArticle.id}));
                    stringBuffer.append(" (@糗事百科)");
                    WeiboShareActivity.shareToWeiBo("", stringBuffer.toString(), null, str2);
                    break;
                }
                Toast.makeText(QsbkApp.mContext, "绑定信息出错，请重新绑定", 1).show();
                break;
                break;
        }
        StatService.onEvent(QsbkApp.mContext, str, "pass", 1);
    }

    public byte[] Bitmap2Bytes(Bitmap bitmap) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public String getTarget(int i) {
        String str = "";
        switch (i) {
            case 1:
                return "新浪微博";
            default:
                return str;
        }
    }

    private void a(Context context, String str) {
        ((ClipboardManager) context.getSystemService("clipboard")).setText(str + "@糗事百科");
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "糗事已复制", Integer.valueOf(0)).show();
    }

    public Integer checkAccessToken(int i) {
        int i2 = 3;
        int i3 = 2;
        String str = "";
        switch (i) {
            case 1:
                ThirdOauth2AccessToken weiboAccessToken = getWeiboAccessToken();
                if (weiboAccessToken == null || weiboAccessToken.getExpiresTime() > System.currentTimeMillis() || isWeiboInstall()) {
                    i3 = 3;
                }
                return Integer.valueOf(i3);
            case 3:
                str = QQ_ACCESS_TOKEN;
                break;
        }
        try {
            Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(str);
            if (TextUtils.isEmpty(sharePreferencesValue)) {
                i2 = 1;
            } else if (Long.valueOf(sharePreferencesValue.split(a.b)[1].split("=")[1]).longValue() <= System.currentTimeMillis()) {
                i2 = 2;
            }
        } catch (Exception e) {
            i2 = 1;
        }
        return Integer.valueOf(i2);
    }

    public void tryCollection(View view, Activity activity, String str) {
        if (QsbkApp.currentUser == null) {
            LoginPermissionClickDelegate.startLoginActivity(activity);
        } else if (!HttpUtils.isNetworkConnected(activity)) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, QsbkApp.mContext.getResources().getString(R.string.network_not_connected), Integer.valueOf(0)).show();
        } else if (view == null) {
            collection(str, true);
        } else if (view.getTag().equals("enable")) {
            view.setTag("active");
            collection(str, true);
        } else {
            view.setTag("enable");
            collection(str, false);
        }
    }

    public void buidBindUrl(Integer num) {
        if (num.intValue() == 1) {
            ShareUrl.weiboUrl = ShareUrl.sinaUrl;
        } else if (num.intValue() == 3) {
            ShareUrl.weiboUrl = ShareUrl.qqZoneUrl;
        } else {
            ShareUrl.weiboUrl = ShareUrl.renrenUrl;
        }
    }

    public ArticleReporter getArticleReporter(Activity activity) {
        if (this.c == null) {
            this.c = new ArticleReporter(activity);
        }
        return this.c;
    }
}
