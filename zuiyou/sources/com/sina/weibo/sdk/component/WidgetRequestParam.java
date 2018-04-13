package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.utils.MD5;
import com.sina.weibo.sdk.utils.Utility;

public class WidgetRequestParam extends BrowserRequestParamBase {
    public static final String EXTRA_KEY_WIDGET_CALLBACK = "key_widget_callback";
    public static final String REQ_PARAM_ATTENTION_FUID = "fuid";
    public static final String REQ_PARAM_COMMENT_CATEGORY = "category";
    public static final String REQ_PARAM_COMMENT_CONTENT = "content";
    public static final String REQ_PARAM_COMMENT_TOPIC = "q";
    private String mAppKey;
    private String mAppPackage;
    private String mAttentionFuid;
    private WeiboAuthListener mAuthListener;
    private String mAuthListenerKey;
    private String mCommentCategory;
    private String mCommentContent;
    private String mCommentTopic;
    private String mHashKey;
    private String mToken;
    private WidgetRequestCallback mWidgetRequestCallback;
    private String mWidgetRequestCallbackKey;

    public interface WidgetRequestCallback {
        void onWebViewResult(String str);
    }

    public WidgetRequestParam(Context context) {
        super(context);
        this.mLaucher = BrowserLauncher.WIDGET;
    }

    protected void onSetupRequestParam(Bundle bundle) {
        this.mAppKey = bundle.getString("source");
        this.mAppPackage = bundle.getString("packagename");
        this.mHashKey = bundle.getString("key_hash");
        this.mToken = bundle.getString("access_token");
        this.mAttentionFuid = bundle.getString(REQ_PARAM_ATTENTION_FUID);
        this.mCommentTopic = bundle.getString(REQ_PARAM_COMMENT_TOPIC);
        this.mCommentContent = bundle.getString("content");
        this.mCommentCategory = bundle.getString("category");
        this.mAuthListenerKey = bundle.getString(AuthRequestParam.EXTRA_KEY_LISTENER);
        if (!TextUtils.isEmpty(this.mAuthListenerKey)) {
            this.mAuthListener = WeiboCallbackManager.getInstance(this.mContext).getWeiboAuthListener(this.mAuthListenerKey);
        }
        this.mWidgetRequestCallbackKey = bundle.getString(EXTRA_KEY_WIDGET_CALLBACK);
        if (!TextUtils.isEmpty(this.mWidgetRequestCallbackKey)) {
            this.mWidgetRequestCallback = WeiboCallbackManager.getInstance(this.mContext).getWidgetRequestCallback(this.mWidgetRequestCallbackKey);
        }
        this.mUrl = buildUrl(this.mUrl);
    }

    public void onCreateRequestParamBundle(Bundle bundle) {
        this.mAppPackage = this.mContext.getPackageName();
        if (!TextUtils.isEmpty(this.mAppPackage)) {
            this.mHashKey = MD5.hexdigest(Utility.getSign(this.mContext, this.mAppPackage));
        }
        bundle.putString("access_token", this.mToken);
        bundle.putString("source", this.mAppKey);
        bundle.putString("packagename", this.mAppPackage);
        bundle.putString("key_hash", this.mHashKey);
        bundle.putString(REQ_PARAM_ATTENTION_FUID, this.mAttentionFuid);
        bundle.putString(REQ_PARAM_COMMENT_TOPIC, this.mCommentTopic);
        bundle.putString("content", this.mCommentContent);
        bundle.putString("category", this.mCommentCategory);
        WeiboCallbackManager instance = WeiboCallbackManager.getInstance(this.mContext);
        if (this.mAuthListener != null) {
            this.mAuthListenerKey = instance.genCallbackKey();
            instance.setWeiboAuthListener(this.mAuthListenerKey, this.mAuthListener);
            bundle.putString(AuthRequestParam.EXTRA_KEY_LISTENER, this.mAuthListenerKey);
        }
        if (this.mWidgetRequestCallback != null) {
            this.mWidgetRequestCallbackKey = instance.genCallbackKey();
            instance.setWidgetRequestCallback(this.mWidgetRequestCallbackKey, this.mWidgetRequestCallback);
            bundle.putString(EXTRA_KEY_WIDGET_CALLBACK, this.mWidgetRequestCallbackKey);
        }
    }

    private String buildUrl(String str) {
        Builder buildUpon = Uri.parse(str).buildUpon();
        buildUpon.appendQueryParameter("version", WBConstants.WEIBO_SDK_VERSION_CODE);
        if (!TextUtils.isEmpty(this.mAppKey)) {
            buildUpon.appendQueryParameter("source", this.mAppKey);
        }
        if (!TextUtils.isEmpty(this.mToken)) {
            buildUpon.appendQueryParameter("access_token", this.mToken);
        }
        Object aid = Utility.getAid(this.mContext, this.mAppKey);
        if (!TextUtils.isEmpty(aid)) {
            buildUpon.appendQueryParameter("aid", aid);
        }
        if (!TextUtils.isEmpty(this.mAppPackage)) {
            buildUpon.appendQueryParameter("packagename", this.mAppPackage);
        }
        if (!TextUtils.isEmpty(this.mHashKey)) {
            buildUpon.appendQueryParameter("key_hash", this.mHashKey);
        }
        if (!TextUtils.isEmpty(this.mAttentionFuid)) {
            buildUpon.appendQueryParameter(REQ_PARAM_ATTENTION_FUID, this.mAttentionFuid);
        }
        if (!TextUtils.isEmpty(this.mCommentTopic)) {
            buildUpon.appendQueryParameter(REQ_PARAM_COMMENT_TOPIC, this.mCommentTopic);
        }
        if (!TextUtils.isEmpty(this.mCommentContent)) {
            buildUpon.appendQueryParameter("content", this.mCommentContent);
        }
        if (!TextUtils.isEmpty(this.mCommentCategory)) {
            buildUpon.appendQueryParameter("category", this.mCommentCategory);
        }
        return buildUpon.build().toString();
    }

    public String getAttentionFuid() {
        return this.mAttentionFuid;
    }

    public void setAttentionFuid(String str) {
        this.mAttentionFuid = str;
    }

    public String getCommentContent() {
        return this.mCommentContent;
    }

    public void setCommentContent(String str) {
        this.mCommentContent = str;
    }

    public String getCommentTopic() {
        return this.mCommentTopic;
    }

    public void setCommentTopic(String str) {
        this.mCommentTopic = str;
    }

    public String getCommentCategory() {
        return this.mCommentCategory;
    }

    public void setCommentCategory(String str) {
        this.mCommentCategory = str;
    }

    public String getToken() {
        return this.mToken;
    }

    public void setToken(String str) {
        this.mToken = str;
    }

    public String getAppKey() {
        return this.mAppKey;
    }

    public void setAppKey(String str) {
        this.mAppKey = str;
    }

    public WeiboAuthListener getAuthListener() {
        return this.mAuthListener;
    }

    public String getAuthListenerKey() {
        return this.mAuthListenerKey;
    }

    public void setAuthListener(WeiboAuthListener weiboAuthListener) {
        this.mAuthListener = weiboAuthListener;
    }

    public WidgetRequestCallback getWidgetRequestCallback() {
        return this.mWidgetRequestCallback;
    }

    public String getWidgetRequestCallbackKey() {
        return this.mWidgetRequestCallbackKey;
    }

    public void setWidgetRequestCallback(WidgetRequestCallback widgetRequestCallback) {
        this.mWidgetRequestCallback = widgetRequestCallback;
    }

    public void execRequest(Activity activity, int i) {
        if (i == 3) {
            WeiboSdkBrowser.closeBrowser(activity, this.mAuthListenerKey, this.mWidgetRequestCallbackKey);
        }
    }
}
