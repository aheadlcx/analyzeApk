package com.sina.weibo.sdk.register.mobile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import cn.xiaochuankeji.aop.permission.c;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.component.WeiboSdkBrowser;
import com.sina.weibo.sdk.component.view.ResizeableLayout;
import com.sina.weibo.sdk.component.view.ResizeableLayout.SizeChangeListener;
import com.sina.weibo.sdk.component.view.TitleBar;
import com.sina.weibo.sdk.component.view.TitleBar.ListenerOnTitleBtnClicked;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.NetUtils;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.NetworkHelper;
import com.sina.weibo.sdk.utils.ResourceManager;
import com.sina.weibo.sdk.utils.UIUtils;
import com.tencent.connect.common.Constants;
import com.umeng.analytics.b.g;
import java.util.Locale;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;
import org.json.JSONObject;

public class MobileRegisterActivity extends Activity implements OnClickListener, OnFocusChangeListener, SizeChangeListener {
    private static final String APPKEY_NOT_SET_CN = "您的app_key没有设置";
    private static final String APPKEY_NOT_SET_EN = "your appkey not set";
    private static final String APPKEY_NOT_SET_TW = "您的app_key沒有設置";
    private static final String CANCEL_EN = "Cancel";
    private static final String CANCEL_ZH_CN = "取消";
    private static final String CANCEL_ZH_TW = "取消";
    private static final String CHINA_CN = "中国";
    private static final String CHINA_EN = "China";
    private static final String CHINA_TW = "中國";
    private static final String CODE_LENGTH_CN = "你的验证码不是6位数";
    private static final String CODE_LENGTH_EN = "Your code isn’t 6-digit long";
    private static final String CODE_LENGTH_TW = "你的驗證碼不是6位數";
    private static final int DEFAULT_BG_COLOR = -855310;
    private static final int DEFAULT_CLEAR_BTN = 22;
    private static final int DEFAULT_TEXT_PADDING = 12;
    private static final int DEFAULT_TIPS_TEXT_SIZE = 13;
    private static final int DEFAULT__RIGHT_TRIANGLE = 13;
    private static final int EMPTY_VIEW_TEXT_COLOR = -4342339;
    private static final int GET_CODE_BTN_ID = 3;
    private static final String GET_CODE_CN = "获取验证码";
    private static final String GET_CODE_EN = "Get code";
    private static final String GET_CODE_TW = "獲取驗證碼";
    private static final String HELP_INFO_CN = "请确认国家和地区并填写手机号码";
    private static final String HELP_INFO_EN = "Confirm your country/region and enter your mobile number";
    private static final String HELP_INFO_TW = "請確認國家和地區并填寫手機號";
    private static final String INPUT_AUTH_CODE_CN = "请输入验证码";
    private static final String INPUT_AUTH_CODE_EN = "Verification code";
    private static final String INPUT_AUTH_CODE_TW = "請輸入驗證碼";
    private static final String INPUT_PHONE_NUM_CN = "请输入手机号码";
    private static final String INPUT_PHONE_NUM_EN = "Your mobile number";
    private static final String INPUT_PHONE_NUM_TW = "請輸入手機號";
    private static final int LINK_TEXT_COLOR = -8224126;
    private static final int MIAN_LINK_TEXT_COLOR = -11502161;
    private static final String NETWORK_ERROR_CN = "您的网络不可用，请稍后";
    private static final String NETWORK_ERROR_EN = "your network is  disabled  try again later";
    private static final String NETWORK_ERROR_TW = "您的網絡不可用，請稍後";
    private static final String OK_EN = "OK";
    private static final String OK_ZH_CN = "确定";
    private static final String OK_ZH_TW = "確定";
    private static final String PHONE_ERROR_CN = "您的手机号不是11位数";
    private static final String PHONE_ERROR_EN = "Your phone number isn’t 11-digit long";
    private static final String PHONE_ERROR_TW = "您的手機號不是11位數";
    private static final int PHONE_NUM_CLEAR_BTN_ID = 4;
    public static final String REGISTER_TITLE = "register_title";
    private static final int RESIZEABLE_INPUTMETHODHIDE = 0;
    private static final int RESIZEABLE_INPUTMETHODSHOW = 1;
    public static final String RESPONSE_EXPIRES = "expires";
    public static final String RESPONSE_OAUTH_TOKEN = "oauth_token";
    private static final int SELECT_COUNTRY_REQUEST_CODE = 0;
    private static final String SEND_MSG = "http://api.weibo.com/oauth2/sms_authorize/send";
    private static final String SEND_SUBMIT = "http://api.weibo.com/oauth2/sms_authorize/submit";
    private static final String SERVER_ERROR_CN = "服务器忙,请稍后再试";
    private static final String SERVER_ERROR_EN = "the server is busy, please  wait";
    private static final String SERVER_ERROR_TW = "服務器忙,請稍後再試";
    private static final String SINA_NOTICE_EN = "By clicking ok, you hereby agree to Weibo Online Service Agreement and Privacy Policy";
    private static final String SINA_NOTICE_ZH_CN = "点击“确定”表示你同意服务使用协议和隐私条款。";
    private static final String SINA_NOTICE_ZH_TW = "點擊“確定”標示你同意服務使用協議和隱私條款。";
    private static final String SINA_PRIVATE_URL = "http://m.weibo.cn/reg/privacyagreement?from=h5&wm=3349";
    private static final String SINA_PROTOCOL_URL = "http://weibo.cn/dpool/ttt/h5/regagreement.php?from=h5";
    private static final String SINA_SERVICE_EN = "Service By Sina WeiBo";
    private static final String SINA_SERVICE_ZH_CN = "此服务由微博提供";
    private static final String SINA_SERVICE_ZH_TW = "此服務由微博提供";
    private static final String TAG = MobileRegisterActivity.class.getName();
    private static final int TITLE_BAR_ID = 1;
    private static final String TITLE_CN = "验证码登录";
    private static final String TITLE_EN = "Login";
    private static final String TITLE_TW = "驗證碼登錄";
    private static final int TRIANGLE_ID = 2;
    private static final String WAIT_CN = "正在处理中.....";
    private static final String WAIT_EN = "please wait .... ";
    private static final String WAIT_TW = "正在處理中.....";
    private static final /* synthetic */ a ajc$tjp_0 = null;
    private String cfrom;
    private String mAppkey;
    private Button mBtnRegist;
    private EditText mCheckCode;
    private CountDownTimer mCountDownTimer;
    private TextView mCountryCode;
    private String mCountryCodeStr;
    private RelativeLayout mCountryLayout;
    private TextView mCountryName;
    private String mCountryNameStr;
    private Button mGetCodeBtn;
    private TextView mInfoText;
    private InputHandler mInputHandler = new InputHandler();
    private String mKeyHash;
    private ProgressDialog mLoadingDlg;
    private int mMaxHeight = 0;
    private String mPackageName;
    private EditText mPhoneNum;
    private ImageView mPhoneNumClearBtn;
    private ScrollView mRegistScrollview;
    private LinearLayout mRegiter_llt;
    private String mSpecifyTitle;
    private TextView mTips;
    private TitleBar titleBar;

    public class AjcClosure1 extends org.aspectj.a.a.a {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            MobileRegisterActivity.onCreate_aroundBody0((MobileRegisterActivity) objArr2[0], (Bundle) objArr2[1], (org.aspectj.lang.a) objArr2[2]);
            return null;
        }
    }

    private class CodeTextWatcher implements TextWatcher {
        private CodeTextWatcher() {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(MobileRegisterActivity.this.mPhoneNum.getText().toString()) || TextUtils.isEmpty(MobileRegisterActivity.this.mCheckCode.getText().toString())) {
                MobileRegisterActivity.this.disableRegisterBtn();
            } else {
                MobileRegisterActivity.this.enableRegisterBtn();
            }
        }
    }

    private class InputHandler extends Handler {
        private InputHandler() {
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    MobileRegisterActivity.this.mInfoText.setVisibility(0);
                    MobileRegisterActivity.this.mCountryLayout.setVisibility(0);
                    return;
                case 1:
                    MobileRegisterActivity.this.mInfoText.setVisibility(8);
                    MobileRegisterActivity.this.mCountryLayout.setVisibility(8);
                    return;
                default:
                    return;
            }
        }
    }

    private class PhoneNumTextWatcher implements TextWatcher {
        private PhoneNumTextWatcher() {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (TextUtils.isEmpty(MobileRegisterActivity.this.mPhoneNum.getText().toString())) {
                MobileRegisterActivity.this.mPhoneNumClearBtn.setVisibility(4);
            } else {
                MobileRegisterActivity.this.mPhoneNumClearBtn.setVisibility(0);
            }
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(MobileRegisterActivity.this.mPhoneNum.getText().toString()) || TextUtils.isEmpty(MobileRegisterActivity.this.mCheckCode.getText().toString())) {
                MobileRegisterActivity.this.disableRegisterBtn();
            } else {
                MobileRegisterActivity.this.enableRegisterBtn();
            }
        }
    }

    private class WBSdkUrlClickSpan extends ClickableSpan {
        private Context context;
        private String url;

        public WBSdkUrlClickSpan(Context context, String str) {
            this.context = context;
            this.url = str;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.context, WeiboSdkBrowser.class);
            Bundle bundle = new Bundle();
            bundle.putString("key_url", this.url);
            intent.putExtras(bundle);
            MobileRegisterActivity.this.startActivity(intent);
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setColor(MobileRegisterActivity.MIAN_LINK_TEXT_COLOR);
            textPaint.setUnderlineText(false);
        }
    }

    private static /* synthetic */ void ajc$preClinit() {
        b bVar = new b("MobileRegisterActivity.java", MobileRegisterActivity.class);
        ajc$tjp_0 = bVar.a("method-execution", bVar.a("4", "onCreate", "com.sina.weibo.sdk.register.mobile.MobileRegisterActivity", "android.os.Bundle", "bundle", "", "void"), 201);
    }

    static {
        ajc$preClinit();
    }

    static final /* synthetic */ void onCreate_aroundBody0(MobileRegisterActivity mobileRegisterActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        Bundle extras = mobileRegisterActivity.getIntent().getExtras();
        if (extras == null) {
            UIUtils.showToast(mobileRegisterActivity.getApplicationContext(), (CharSequence) "Pass wrong params!!", 0);
            mobileRegisterActivity.finish();
        }
        mobileRegisterActivity.mAppkey = extras.getString(WBConstants.SSO_APP_KEY);
        mobileRegisterActivity.mPackageName = extras.getString("packagename");
        mobileRegisterActivity.mKeyHash = extras.getString("key_hash");
        if (TextUtils.isEmpty(mobileRegisterActivity.mAppkey)) {
            UIUtils.showToast(mobileRegisterActivity.getApplicationContext(), ResourceManager.getString(mobileRegisterActivity, APPKEY_NOT_SET_EN, APPKEY_NOT_SET_CN, APPKEY_NOT_SET_TW), 0);
            mobileRegisterActivity.finish();
        }
        String string = extras.getString(REGISTER_TITLE);
        if (TextUtils.isEmpty(string)) {
            string = ResourceManager.getString(mobileRegisterActivity, TITLE_EN, TITLE_CN, TITLE_TW);
        }
        mobileRegisterActivity.mSpecifyTitle = string;
        mobileRegisterActivity.mCountryCodeStr = Country.CHINA_CODE;
        mobileRegisterActivity.mCountryNameStr = ResourceManager.getString(mobileRegisterActivity, CHINA_EN, CHINA_CN, CHINA_TW);
        mobileRegisterActivity.initView();
        mobileRegisterActivity.mCountDownTimer = new CountDownTimer(60000, 1000) {
            public void onTick(long j) {
                MobileRegisterActivity.this.mGetCodeBtn.setText(new StringBuilder(String.valueOf(ResourceManager.getString(MobileRegisterActivity.this.getApplicationContext(), MobileRegisterActivity.GET_CODE_EN, MobileRegisterActivity.GET_CODE_CN, MobileRegisterActivity.GET_CODE_TW))).append("(").append(j / 1000).append("s)").toString());
            }

            public void onFinish() {
                MobileRegisterActivity.this.mGetCodeBtn.setText(ResourceManager.getString(MobileRegisterActivity.this.getApplicationContext(), MobileRegisterActivity.GET_CODE_EN, MobileRegisterActivity.GET_CODE_CN, MobileRegisterActivity.GET_CODE_TW));
                MobileRegisterActivity.this.enableGetCodeBtn();
            }
        };
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(ajc$tjp_0, this, this, bundle);
        c.c().a(new AjcClosure1(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    private void initView() {
        View resizeableLayout = new ResizeableLayout(this);
        resizeableLayout.setLayoutParams(new LayoutParams(-1, -1));
        resizeableLayout.setBackgroundColor(DEFAULT_BG_COLOR);
        this.titleBar = new TitleBar(this);
        this.titleBar.setId(1);
        this.titleBar.setLeftBtnText(ResourceManager.getString(this, CANCEL_EN, "取消", "取消"));
        this.titleBar.setTitleBarText(this.mSpecifyTitle);
        this.titleBar.setTitleBarClickListener(new ListenerOnTitleBtnClicked() {
            public void onLeftBtnClicked() {
                MobileRegisterActivity.this.setResult(0);
                MobileRegisterActivity.this.finish();
            }
        });
        resizeableLayout.addView(this.titleBar);
        View view = new View(this);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, ResourceManager.dp2px(this, 2));
        view.setBackgroundDrawable(ResourceManager.getNinePatchDrawable(this, "weibosdk_common_shadow_top.9.png"));
        layoutParams.addRule(3, 1);
        view.setLayoutParams(layoutParams);
        resizeableLayout.addView(view);
        this.mRegistScrollview = new ScrollView(this);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.topMargin = ResourceManager.dp2px(this, 47);
        this.mRegistScrollview.setBackgroundColor(DEFAULT_BG_COLOR);
        this.mRegistScrollview.setLayoutParams(layoutParams2);
        this.mRegiter_llt = new LinearLayout(this);
        layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        this.mRegiter_llt.setOrientation(1);
        this.mRegiter_llt.setLayoutParams(layoutParams2);
        this.mInfoText = new TextView(this);
        this.mInfoText.setTextSize(2, 13.0f);
        this.mInfoText.setHeight(ResourceManager.dp2px(this, 44));
        this.mInfoText.setGravity(17);
        this.mInfoText.setTextColor(LINK_TEXT_COLOR);
        this.mInfoText.setText(ResourceManager.getString(this, HELP_INFO_EN, HELP_INFO_CN, HELP_INFO_TW));
        this.mInfoText.setFocusable(true);
        this.mInfoText.setFocusableInTouchMode(true);
        this.mRegiter_llt.addView(this.mInfoText);
        this.mCountryLayout = new RelativeLayout(this);
        layoutParams2 = new RelativeLayout.LayoutParams(-1, ResourceManager.dp2px(this, 48));
        this.mCountryLayout.setBackgroundDrawable(ResourceManager.createStateListDrawable(this, "login_country_background.9.png", "login_country_background_highlighted.9.png"));
        this.mCountryLayout.setLayoutParams(layoutParams2);
        this.mCountryCode = new TextView(this);
        this.mCountryCode.setTextSize(2, 17.0f);
        this.mCountryCode.setText(Country.CHINA_CODE);
        this.mCountryCode.setTextColor(-11382190);
        this.mCountryCode.setGravity(3);
        this.mCountryCode.setGravity(16);
        layoutParams2 = new RelativeLayout.LayoutParams(-2, ResourceManager.dp2px(this, 48));
        layoutParams2.leftMargin = ResourceManager.dp2px(this, 15);
        layoutParams2.addRule(9);
        this.mCountryCode.setLayoutParams(layoutParams2);
        view = new ImageView(this);
        view.setId(2);
        view.setImageDrawable(ResourceManager.getDrawable(this, "triangle.png"));
        layoutParams = new RelativeLayout.LayoutParams(ResourceManager.dp2px(this, 13), ResourceManager.dp2px(this, 13));
        layoutParams.rightMargin = ResourceManager.dp2px(this, 15);
        layoutParams.addRule(11);
        layoutParams.addRule(15);
        view.setLayoutParams(layoutParams);
        this.mCountryName = new TextView(this);
        this.mCountryName.setTextSize(2, 17.0f);
        this.mCountryName.setTextColor(-11382190);
        this.mCountryName.setText(this.mCountryNameStr);
        this.mCountryName.setGravity(16);
        layoutParams = new RelativeLayout.LayoutParams(-2, ResourceManager.dp2px(this, 48));
        layoutParams.rightMargin = ResourceManager.dp2px(this, 118);
        layoutParams.addRule(0, 2);
        layoutParams.addRule(15);
        this.mCountryName.setLayoutParams(layoutParams);
        this.mCountryLayout.addView(this.mCountryCode);
        this.mCountryLayout.addView(this.mCountryName);
        this.mCountryLayout.addView(view);
        this.mRegiter_llt.addView(this.mCountryLayout);
        view = new LinearLayout(this);
        layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = ResourceManager.dp2px(this, 10);
        view.setLayoutParams(layoutParams);
        view.setOrientation(1);
        View relativeLayout = new RelativeLayout(this);
        LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, ResourceManager.dp2px(this, 50));
        layoutParams3.gravity = 16;
        relativeLayout.setBackgroundDrawable(ResourceManager.getNinePatchDrawable(this, "login_top_background.9.png"));
        relativeLayout.setLayoutParams(layoutParams3);
        this.mPhoneNumClearBtn = new ImageView(this);
        this.mPhoneNumClearBtn.setId(4);
        this.mPhoneNumClearBtn.setImageDrawable(ResourceManager.createStateListDrawable(this, "search_clear_btn_normal.png", "search_clear_btn_down.png"));
        layoutParams3 = new RelativeLayout.LayoutParams(ResourceManager.dp2px(this, 22), ResourceManager.dp2px(this, 22));
        layoutParams3.rightMargin = ResourceManager.dp2px(this, 15);
        layoutParams3.addRule(11);
        layoutParams3.addRule(15);
        this.mPhoneNumClearBtn.setVisibility(4);
        this.mPhoneNumClearBtn.setLayoutParams(layoutParams3);
        relativeLayout.addView(this.mPhoneNumClearBtn);
        this.mPhoneNum = new EditText(this);
        this.mPhoneNum.setTextSize(2, 16.0f);
        this.mPhoneNum.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.mPhoneNum.setHint(ResourceManager.getString(this, INPUT_PHONE_NUM_EN, INPUT_PHONE_NUM_CN, INPUT_PHONE_NUM_TW));
        this.mPhoneNum.setHintTextColor(EMPTY_VIEW_TEXT_COLOR);
        this.mPhoneNum.setBackgroundDrawable(null);
        this.mPhoneNum.setSelected(false);
        layoutParams3 = new RelativeLayout.LayoutParams(-1, ResourceManager.dp2px(this, 50));
        layoutParams3.topMargin = ResourceManager.dp2px(this, 0);
        layoutParams3.bottomMargin = ResourceManager.dp2px(this, 0);
        layoutParams3.leftMargin = ResourceManager.dp2px(this, 0);
        layoutParams3.rightMargin = ResourceManager.dp2px(this, 0);
        layoutParams3.addRule(0, 4);
        this.mPhoneNum.setLayoutParams(layoutParams3);
        relativeLayout.addView(this.mPhoneNum);
        View relativeLayout2 = new RelativeLayout(this);
        LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, ResourceManager.dp2px(this, 50));
        relativeLayout2.setBackgroundDrawable(ResourceManager.getNinePatchDrawable(this, "login_bottom_background.9.png"));
        relativeLayout2.setLayoutParams(layoutParams4);
        this.mGetCodeBtn = new Button(this);
        this.mGetCodeBtn.setId(3);
        this.mGetCodeBtn.setBackgroundDrawable(ResourceManager.createStateListDrawable(this, "get_code_button.9.png", "get_code_button_highlighted.9.png"));
        layoutParams4 = new RelativeLayout.LayoutParams(-2, ResourceManager.dp2px(this, 29));
        layoutParams4.rightMargin = ResourceManager.dp2px(this, 12);
        layoutParams4.addRule(11);
        layoutParams4.addRule(15);
        this.mGetCodeBtn.setPadding(18, 0, 18, 0);
        this.mGetCodeBtn.setLayoutParams(layoutParams4);
        this.mGetCodeBtn.setText(ResourceManager.getString(this, GET_CODE_EN, GET_CODE_CN, GET_CODE_TW));
        this.mGetCodeBtn.setTextSize(15.0f);
        enableGetCodeBtn();
        relativeLayout2.addView(this.mGetCodeBtn);
        this.mCheckCode = new EditText(this);
        this.mCheckCode.setTextSize(2, 16.0f);
        this.mCheckCode.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.mCheckCode.setHintTextColor(EMPTY_VIEW_TEXT_COLOR);
        this.mCheckCode.setHint(ResourceManager.getString(this, INPUT_AUTH_CODE_EN, INPUT_AUTH_CODE_CN, INPUT_AUTH_CODE_TW));
        this.mCheckCode.setBackgroundDrawable(null);
        layoutParams4 = new RelativeLayout.LayoutParams(-1, ResourceManager.dp2px(this, 48));
        layoutParams4.addRule(0, 3);
        this.mCheckCode.setLayoutParams(layoutParams4);
        relativeLayout2.addView(this.mCheckCode);
        view.addView(relativeLayout);
        view.addView(relativeLayout2);
        this.mRegiter_llt.addView(view);
        this.mGetCodeBtn.setOnClickListener(this);
        this.mTips = new TextView(this);
        this.mTips.setTextSize(2, 13.0f);
        this.mTips.setTextColor(-2014941);
        this.mTips.setText("");
        this.mTips.setVisibility(4);
        layoutParams2 = new LinearLayout.LayoutParams(-1, ResourceManager.dp2px(this, 36));
        layoutParams2.leftMargin = ResourceManager.dp2px(this, 12);
        this.mTips.setGravity(16);
        this.mTips.setLayoutParams(layoutParams2);
        this.mRegiter_llt.addView(this.mTips);
        this.mBtnRegist = genOKBtn();
        disableRegisterBtn();
        this.mRegiter_llt.addView(this.mBtnRegist);
        view = genSinaServiceTv();
        relativeLayout = genProtocalInfoTv();
        this.mRegiter_llt.addView(view);
        this.mRegiter_llt.addView(relativeLayout);
        this.mRegistScrollview.addView(this.mRegiter_llt);
        resizeableLayout.addView(this.mRegistScrollview);
        initLoadingDlg();
        this.mPhoneNum.setInputType(2);
        this.mPhoneNum.addTextChangedListener(new PhoneNumTextWatcher());
        this.mCheckCode.setInputType(2);
        this.mCheckCode.addTextChangedListener(new CodeTextWatcher());
        this.mPhoneNumClearBtn.setOnClickListener(this);
        this.mPhoneNum.setOnFocusChangeListener(this);
        this.mBtnRegist.setOnClickListener(this);
        this.mCountryLayout.setOnClickListener(this);
        resizeableLayout.setSizeChangeListener(this);
        setContentView(resizeableLayout);
    }

    private Button genOKBtn() {
        Button button = new Button(this);
        button.setBackgroundDrawable(ResourceManager.createStateListDrawable(this, "common_button_big_blue.9.png", "common_button_big_blue_highlighted.9.png", "common_button_big_blue_disable.9.png"));
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, ResourceManager.dp2px(this, 46));
        int dp2px = ResourceManager.dp2px(this, 12);
        layoutParams.rightMargin = dp2px;
        layoutParams.leftMargin = dp2px;
        button.setText(ResourceManager.getString(this, OK_EN, OK_ZH_CN, OK_ZH_TW));
        button.setTextSize(17.0f);
        button.setLayoutParams(layoutParams);
        return button;
    }

    private TextView genSinaServiceTv() {
        TextView textView = new TextView(this);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = ResourceManager.dp2px(this, 12);
        layoutParams.leftMargin = ResourceManager.dp2px(this, 12);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(13.0f);
        textView.setGravity(3);
        textView.setTextColor(LINK_TEXT_COLOR);
        textView.setText(ResourceManager.getString(this, SINA_SERVICE_EN, SINA_SERVICE_ZH_CN, SINA_SERVICE_ZH_TW));
        return textView;
    }

    private TextView genProtocalInfoTv() {
        CharSequence charSequence;
        int indexOf;
        int length;
        int indexOf2;
        int length2;
        TextView textView = new TextView(this);
        textView.setTextSize(2, 13.0f);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = ResourceManager.dp2px(this, 8);
        layoutParams.leftMargin = ResourceManager.dp2px(this, 12);
        layoutParams.rightMargin = ResourceManager.dp2px(this, 12);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(13.0f);
        textView.setGravity(3);
        textView.setTextColor(LINK_TEXT_COLOR);
        Locale language = ResourceManager.getLanguage();
        String str = SINA_NOTICE_ZH_CN;
        str = "zh_CN";
        String str2;
        String str3;
        if (Locale.SIMPLIFIED_CHINESE.equals(language)) {
            charSequence = SINA_NOTICE_ZH_CN;
            str2 = "服务使用协议";
            str3 = "隐私条款";
            indexOf = SINA_NOTICE_ZH_CN.indexOf(str2);
            length = indexOf + str2.length();
            indexOf2 = SINA_NOTICE_ZH_CN.indexOf(str3);
            length2 = str3.length() + indexOf2;
        } else if (Locale.TRADITIONAL_CHINESE.equals(language)) {
            charSequence = SINA_NOTICE_ZH_TW;
            str = "zh_HK";
            str2 = "服務使用協議";
            str3 = "隱私條款";
            indexOf = SINA_NOTICE_ZH_TW.indexOf(str2);
            length = indexOf + str2.length();
            indexOf2 = SINA_NOTICE_ZH_TW.indexOf(str3);
            length2 = str3.length() + indexOf2;
        } else {
            charSequence = SINA_NOTICE_EN;
            str = "en_US";
            str2 = "Service Agreement";
            str3 = "Privacy Policy";
            indexOf = SINA_NOTICE_EN.indexOf(str2);
            length = indexOf + str2.length();
            indexOf2 = SINA_NOTICE_EN.indexOf(str3);
            length2 = str3.length() + indexOf2;
        }
        CharSequence spannableStringBuilder = new SpannableStringBuilder(charSequence);
        if (!(indexOf == -1 || length == -1)) {
            spannableStringBuilder.setSpan(new WBSdkUrlClickSpan(this, "http://weibo.cn/dpool/ttt/h5/regagreement.php?from=h5&lang=" + str), indexOf, length, 33);
        }
        if (!(indexOf2 == -1 || length2 == -1)) {
            spannableStringBuilder.setSpan(new WBSdkUrlClickSpan(this, "http://m.weibo.cn/reg/privacyagreement?from=h5&wm=3349&lang=" + str), indexOf2, length2, 33);
        }
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setFocusable(false);
        return textView;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 0:
                if (intent != null) {
                    this.mCountryCodeStr = intent.getStringExtra("code");
                    this.mCountryNameStr = intent.getStringExtra("name");
                    this.mCountryCode.setText(this.mCountryCodeStr);
                    this.mCountryName.setText(this.mCountryNameStr);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onFocusChange(View view, boolean z) {
        if (view == this.mPhoneNum && !z) {
            if (verifyPhoneNum(this.mPhoneNum.getText().toString())) {
                this.mTips.setVisibility(4);
                return;
            }
            this.mTips.setText(ResourceManager.getString(this, PHONE_ERROR_EN, PHONE_ERROR_CN, PHONE_ERROR_TW));
            this.mTips.setVisibility(0);
        }
    }

    private boolean doCheckOnGetMsg(String str) {
        if (!NetworkHelper.isNetworkAvailable(this)) {
            showNetFail();
            return false;
        } else if (verifyPhoneNum(str)) {
            this.mTips.setVisibility(4);
            return true;
        } else {
            this.mTips.setVisibility(0);
            this.mTips.setText(ResourceManager.getString(getApplicationContext(), PHONE_ERROR_EN, PHONE_ERROR_CN, PHONE_ERROR_TW));
            return false;
        }
    }

    private boolean verifyPhoneNum(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!Country.CHINA_CODE.equals(this.mCountryCodeStr)) {
            return true;
        }
        if (str.trim().length() == 11) {
            return true;
        }
        return false;
    }

    private boolean doCheckOnSubmit(String str) {
        if (!NetworkHelper.isNetworkAvailable(this)) {
            showNetFail();
            return false;
        } else if (verifyCheckCode(str)) {
            this.mTips.setVisibility(4);
            return true;
        } else {
            this.mTips.setVisibility(0);
            this.mTips.setText(ResourceManager.getString(getApplicationContext(), CODE_LENGTH_EN, CODE_LENGTH_CN, CODE_LENGTH_TW));
            UIUtils.showToast(getApplicationContext(), ResourceManager.getString(getApplicationContext(), CODE_LENGTH_EN, CODE_LENGTH_CN, CODE_LENGTH_TW), 0);
            return false;
        }
    }

    private boolean verifyCheckCode(String str) {
        if (TextUtils.isEmpty(str) || str.length() != 6) {
            return false;
        }
        return true;
    }

    private void disableGetCodeBtn() {
        this.mGetCodeBtn.setEnabled(false);
        this.mGetCodeBtn.setTextColor(EMPTY_VIEW_TEXT_COLOR);
    }

    private void enableGetCodeBtn() {
        this.mGetCodeBtn.setEnabled(true);
        this.mGetCodeBtn.setTextColor(MIAN_LINK_TEXT_COLOR);
    }

    private void disableRegisterBtn() {
        this.mBtnRegist.setTextColor(1308622847);
        this.mBtnRegist.setEnabled(false);
    }

    private void enableRegisterBtn() {
        this.mBtnRegist.setEnabled(true);
        this.mBtnRegist.setTextColor(-1);
    }

    private void showNetFail() {
        UIUtils.showToast(getApplicationContext(), ResourceManager.getString(getApplicationContext(), NETWORK_ERROR_EN, NETWORK_ERROR_CN, NETWORK_ERROR_TW), 0);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        setResult(0);
        finish();
        return true;
    }

    public void dismiss() {
        if (this.mLoadingDlg != null && this.mLoadingDlg.isShowing()) {
            this.mLoadingDlg.dismiss();
        }
    }

    private void initLoadingDlg() {
        this.mLoadingDlg = new ProgressDialog(this);
        this.mLoadingDlg.setCanceledOnTouchOutside(false);
        this.mLoadingDlg.requestWindowFeature(1);
        this.mLoadingDlg.setMessage(ResourceManager.getString(this, WAIT_EN, WAIT_CN, WAIT_TW));
    }

    public void getMsg(String str, String str2) {
        WeiboParameters weiboParameters = new WeiboParameters(this.mAppkey);
        weiboParameters.put(g.a, this.mAppkey);
        weiboParameters.put("packagename", this.mPackageName);
        weiboParameters.put("key_hash", this.mKeyHash);
        String str3 = "phone";
        if (!Country.CHINA_CODE.equals(str2)) {
            str = new StringBuilder(String.valueOf(str2)).append(str).toString();
        }
        weiboParameters.put(str3, str);
        weiboParameters.put("version", WBConstants.WEIBO_SDK_VERSION_CODE);
        NetUtils.internalHttpRequest(this, SEND_MSG, weiboParameters, Constants.HTTP_GET, new RequestListener() {
            public void onWeiboException(WeiboException weiboException) {
                LogUtil.d(MobileRegisterActivity.TAG, "get onWeiboException " + weiboException.getMessage());
                CharSequence string = ResourceManager.getString(MobileRegisterActivity.this.getApplicationContext(), MobileRegisterActivity.SERVER_ERROR_EN, MobileRegisterActivity.SERVER_ERROR_CN, MobileRegisterActivity.SERVER_ERROR_TW);
                try {
                    JSONObject jSONObject = new JSONObject(weiboException.getMessage());
                    if (!TextUtils.isEmpty(jSONObject.optString("error_description"))) {
                        string = jSONObject.optString("error_description");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                UIUtils.showToast(MobileRegisterActivity.this.getApplicationContext(), string, 1);
            }

            public void onComplete(String str) {
                LogUtil.d(MobileRegisterActivity.TAG, "get onComplete : " + str);
                if (str != null) {
                    try {
                        MobileRegisterActivity.this.cfrom = (String) new JSONObject(str).get("cfrom");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void submit(final String str, String str2) {
        WeiboParameters weiboParameters = new WeiboParameters(this.mAppkey);
        weiboParameters.put(g.a, this.mAppkey);
        weiboParameters.put("packagename", this.mPackageName);
        weiboParameters.put("key_hash", this.mKeyHash);
        weiboParameters.put("phone", str);
        weiboParameters.put("version", WBConstants.WEIBO_SDK_VERSION_CODE);
        weiboParameters.put("code", str2);
        weiboParameters.put("cfrom", this.cfrom);
        this.mLoadingDlg.show();
        NetUtils.internalHttpRequest(this, SEND_SUBMIT, weiboParameters, Constants.HTTP_GET, new RequestListener() {
            public void onWeiboException(WeiboException weiboException) {
                LogUtil.d(MobileRegisterActivity.TAG, "get onWeiboException " + weiboException.getMessage());
                CharSequence string = ResourceManager.getString(MobileRegisterActivity.this.getApplicationContext(), MobileRegisterActivity.SERVER_ERROR_EN, MobileRegisterActivity.SERVER_ERROR_CN, MobileRegisterActivity.SERVER_ERROR_TW);
                try {
                    JSONObject jSONObject = new JSONObject(weiboException.getMessage());
                    if (!TextUtils.isEmpty(jSONObject.optString("error_description"))) {
                        string = jSONObject.optString("error_description");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                MobileRegisterActivity.this.mTips.setVisibility(0);
                MobileRegisterActivity.this.mTips.setText(string);
                MobileRegisterActivity.this.dismiss();
            }

            public void onComplete(String str) {
                MobileRegisterActivity.this.dismiss();
                LogUtil.d(MobileRegisterActivity.TAG, "get onComplete : " + str);
                if (str != null) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("uid", jSONObject.optString("uid"));
                        bundle.putString(Oauth2AccessToken.KEY_PHONE_NUM, str);
                        bundle.putString("access_token", jSONObject.optString(MobileRegisterActivity.RESPONSE_OAUTH_TOKEN));
                        bundle.putString("expires_in", jSONObject.optString(MobileRegisterActivity.RESPONSE_EXPIRES));
                        intent.putExtras(bundle);
                        MobileRegisterActivity.this.setResult(-1, intent);
                        MobileRegisterActivity.this.finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void onClick(View view) {
        String editable;
        String charSequence;
        if (view == this.mGetCodeBtn) {
            editable = this.mPhoneNum.getText().toString();
            charSequence = this.mCountryCode.getText().toString();
            if (doCheckOnGetMsg(editable)) {
                this.mCountDownTimer.start();
                disableGetCodeBtn();
                getMsg(editable, charSequence);
            }
        } else if (view == this.mPhoneNumClearBtn) {
            this.mPhoneNum.setText("");
        } else if (view == this.mBtnRegist) {
            editable = this.mPhoneNum.getText().toString();
            charSequence = this.mCheckCode.getText().toString();
            if (doCheckOnSubmit(charSequence)) {
                submit(editable, charSequence);
            }
        } else if (view == this.mCountryLayout) {
            this.mTips.setVisibility(4);
            Intent intent = new Intent();
            intent.setClass(this, SelectCountryActivity.class);
            startActivityForResult(intent, 0);
        }
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (displayMetrics.widthPixels <= displayMetrics.heightPixels) {
            this.mMaxHeight = this.mMaxHeight < i2 ? i2 : this.mMaxHeight;
            int i5 = 0;
            if (i2 < i4) {
                i5 = 1;
            } else if (i2 > i4 && i2 < this.mMaxHeight) {
                i5 = 1;
            } else if (i2 == i4 && i2 != this.mMaxHeight) {
                i5 = 1;
            }
            this.mInputHandler.sendEmptyMessage(i5);
        }
    }
}
