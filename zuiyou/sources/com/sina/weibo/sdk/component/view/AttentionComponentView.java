package com.sina.weibo.sdk.component.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.iflytek.cloud.SpeechUtility;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.cmd.WbAppActivator;
import com.sina.weibo.sdk.component.WeiboSdkBrowser;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import com.sina.weibo.sdk.component.WidgetRequestParam.WidgetRequestCallback;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.NetUtils;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.ResourceManager;
import com.sina.weibo.sdk.utils.Utility;
import com.tencent.connect.common.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public class AttentionComponentView extends FrameLayout {
    private static final String ALREADY_ATTEND_EN = "Following";
    private static final String ALREADY_ATTEND_ZH_CN = "已关注";
    private static final String ALREADY_ATTEND_ZH_TW = "已關注";
    private static final String ATTEND_EN = "Follow";
    private static final String ATTEND_ZH_CN = "关注";
    private static final String ATTEND_ZH_TW = "關注";
    private static final String ATTENTION_H5 = "http://widget.weibo.com/relationship/followsdk.php";
    private static final String FRIENDSHIPS_SHOW_URL = "https://api.weibo.com/2/friendships/show.json";
    private static final String TAG = AttentionComponentView.class.getName();
    private FrameLayout flButton;
    private RequestParam mAttentionParam;
    private TextView mButton;
    private volatile boolean mIsLoadingState = false;
    private ProgressBar pbLoading;

    public static class RequestParam {
        private String mAccessToken;
        private String mAppKey;
        private String mAttentionScreenName;
        private String mAttentionUid;
        private WeiboAuthListener mAuthlistener;

        private RequestParam() {
        }

        public static RequestParam createRequestParam(String str, String str2, String str3, String str4, WeiboAuthListener weiboAuthListener) {
            RequestParam requestParam = new RequestParam();
            requestParam.mAppKey = str;
            requestParam.mAccessToken = str2;
            requestParam.mAttentionUid = str3;
            requestParam.mAttentionScreenName = str4;
            requestParam.mAuthlistener = weiboAuthListener;
            return requestParam;
        }

        public static RequestParam createRequestParam(String str, String str2, String str3, WeiboAuthListener weiboAuthListener) {
            RequestParam requestParam = new RequestParam();
            requestParam.mAppKey = str;
            requestParam.mAttentionUid = str2;
            requestParam.mAttentionScreenName = str3;
            requestParam.mAuthlistener = weiboAuthListener;
            return requestParam;
        }

        private boolean hasAuthoriz() {
            if (TextUtils.isEmpty(this.mAccessToken)) {
                return false;
            }
            return true;
        }
    }

    public AttentionComponentView(Context context) {
        super(context);
        init(context);
    }

    public AttentionComponentView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public AttentionComponentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void init(Context context) {
        Drawable createStateListDrawable = ResourceManager.createStateListDrawable(context, "common_button_white.9.png", "common_button_white_highlighted.9.png");
        this.flButton = new FrameLayout(context);
        this.flButton.setBackgroundDrawable(createStateListDrawable);
        this.flButton.setPadding(0, ResourceManager.dp2px(getContext(), 6), ResourceManager.dp2px(getContext(), 2), ResourceManager.dp2px(getContext(), 6));
        this.flButton.setLayoutParams(new LayoutParams(ResourceManager.dp2px(getContext(), 66), -2));
        addView(this.flButton);
        this.mButton = new TextView(getContext());
        this.mButton.setIncludeFontPadding(false);
        this.mButton.setSingleLine(true);
        this.mButton.setTextSize(2, 13.0f);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.mButton.setLayoutParams(layoutParams);
        this.flButton.addView(this.mButton);
        this.pbLoading = new ProgressBar(getContext(), null, 16842873);
        this.pbLoading.setVisibility(8);
        layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.pbLoading.setLayoutParams(layoutParams);
        this.flButton.addView(this.pbLoading);
        this.flButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AttentionComponentView.this.execAttented();
            }
        });
        showFollowButton(false);
    }

    public void setAttentionParam(RequestParam requestParam) {
        this.mAttentionParam = requestParam;
        if (requestParam.hasAuthoriz()) {
            loadAttentionState(requestParam);
        }
    }

    private void startLoading() {
        this.flButton.setEnabled(false);
        this.mButton.setVisibility(8);
        this.pbLoading.setVisibility(0);
    }

    private void stopLoading() {
        this.flButton.setEnabled(true);
        this.mButton.setVisibility(0);
        this.pbLoading.setVisibility(8);
    }

    private void showFollowButton(boolean z) {
        stopLoading();
        if (z) {
            this.mButton.setText(ResourceManager.getString(getContext(), ALREADY_ATTEND_EN, ALREADY_ATTEND_ZH_CN, ALREADY_ATTEND_ZH_TW));
            this.mButton.setTextColor(-13421773);
            this.mButton.setCompoundDrawablesWithIntrinsicBounds(ResourceManager.getDrawable(getContext(), "timeline_relationship_icon_attention.png"), null, null, null);
            this.flButton.setEnabled(false);
            return;
        }
        this.mButton.setText(ResourceManager.getString(getContext(), ATTEND_EN, ATTEND_ZH_CN, ATTEND_ZH_TW));
        this.mButton.setTextColor(-32256);
        this.mButton.setCompoundDrawablesWithIntrinsicBounds(ResourceManager.getDrawable(getContext(), "timeline_relationship_icon_addattention.png"), null, null, null);
        this.flButton.setEnabled(true);
    }

    private void loadAttentionState(RequestParam requestParam) {
        if (!this.mIsLoadingState) {
            WbAppActivator.getInstance(getContext(), requestParam.mAppKey).activateApp();
            this.mIsLoadingState = true;
            startLoading();
            WeiboParameters weiboParameters = new WeiboParameters(requestParam.mAppKey);
            weiboParameters.put("access_token", requestParam.mAccessToken);
            weiboParameters.put("target_id", requestParam.mAttentionUid);
            weiboParameters.put("target_screen_name", requestParam.mAttentionScreenName);
            NetUtils.internalHttpRequest(getContext(), FRIENDSHIPS_SHOW_URL, weiboParameters, Constants.HTTP_GET, new RequestListener() {
                public void onWeiboException(WeiboException weiboException) {
                    LogUtil.d(AttentionComponentView.TAG, "error : " + weiboException.getMessage());
                    AttentionComponentView.this.mIsLoadingState = false;
                }

                public void onComplete(String str) {
                    LogUtil.d(AttentionComponentView.TAG, "json : " + str);
                    try {
                        final JSONObject optJSONObject = new JSONObject(str).optJSONObject("target");
                        AttentionComponentView.this.getHandler().post(new Runnable() {
                            public void run() {
                                if (optJSONObject != null) {
                                    AttentionComponentView.this.showFollowButton(optJSONObject.optBoolean("followed_by", false));
                                }
                                AttentionComponentView.this.mIsLoadingState = false;
                            }
                        });
                    } catch (JSONException e) {
                    }
                }
            });
        }
    }

    private void execAttented() {
        WidgetRequestParam widgetRequestParam = new WidgetRequestParam(getContext());
        widgetRequestParam.setUrl(ATTENTION_H5);
        widgetRequestParam.setSpecifyTitle(ResourceManager.getString(getContext(), ATTEND_EN, ATTEND_ZH_CN, ATTEND_ZH_TW));
        widgetRequestParam.setAppKey(this.mAttentionParam.mAppKey);
        widgetRequestParam.setAttentionFuid(this.mAttentionParam.mAttentionUid);
        widgetRequestParam.setAuthListener(this.mAttentionParam.mAuthlistener);
        widgetRequestParam.setToken(this.mAttentionParam.mAccessToken);
        widgetRequestParam.setWidgetRequestCallback(new WidgetRequestCallback() {
            public void onWebViewResult(String str) {
                Object string = Utility.parseUri(str).getString(SpeechUtility.TAG_RESOURCE_RESULT);
                if (!TextUtils.isEmpty(string)) {
                    try {
                        long parseInt = (long) Integer.parseInt(string);
                        if (parseInt == 1) {
                            AttentionComponentView.this.showFollowButton(true);
                        } else if (parseInt == 0) {
                            AttentionComponentView.this.showFollowButton(false);
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            }
        });
        Bundle createRequestParamBundle = widgetRequestParam.createRequestParamBundle();
        Intent intent = new Intent(getContext(), WeiboSdkBrowser.class);
        intent.putExtras(createRequestParamBundle);
        getContext().startActivity(intent);
    }

    private void requestAsync(Context context, String str, WeiboParameters weiboParameters, String str2, RequestListener requestListener) {
        NetUtils.internalHttpRequest(context, str, weiboParameters, str2, requestListener);
    }
}
