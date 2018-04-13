package cn.v6.sixrooms.ui.phone;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.constants.CommonInts;
import cn.v6.sixrooms.listener.OpenFileChooserCallBack;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.room.fragment.RoomBaseFragment;
import cn.v6.sixrooms.socket.common.SocketUtil;
import cn.v6.sixrooms.utils.AppInfoUtils;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;
import java.io.File;

public abstract class BaseWebviewActivity extends SlidingActivity implements OpenFileChooserCallBack {
    protected static final int REQUEST_FILE_PICKER = 1;
    protected static final int REQUEST_FILE_PICKER_CAMERA = 2;
    protected Dialog alertDialog;
    protected String encpass = null;
    protected String eventUrl;
    protected String eventUrlFrom;
    protected String loginUid = null;
    protected DialogUtils mDialogUtils;
    protected ValueCallback<Uri> mFilePathCallback;
    protected ValueCallback<Uri[]> mFilePathCallbacks;
    protected WebView mWebView;
    protected File tempFile;
    protected MWebChromeClient webChromeClient;

    protected final class AppAndroid {
        final /* synthetic */ BaseWebviewActivity a;

        protected AppAndroid(BaseWebviewActivity baseWebviewActivity) {
            this.a = baseWebviewActivity;
        }

        @JavascriptInterface
        public final void appLogin() {
            this.a.showLoginDialog();
        }

        @JavascriptInterface
        public final void appEnterRoom(String str, String str2) {
            Intent intent = new Intent(this.a, RoomActivity.class);
            intent.putExtra("rid", str2);
            intent.putExtra(RoomBaseFragment.RUID_KEY, str);
            this.a.startActivity(intent);
        }

        @JavascriptInterface
        public final void appEnterUserInfo(String str) {
        }

        @JavascriptInterface
        public final String getLoginUid() {
            return this.a.loginUid;
        }

        @JavascriptInterface
        public final String getEncpass() {
            return this.a.encpass;
        }

        @JavascriptInterface
        public final void gotoHall() {
            this.a.finish();
        }

        @JavascriptInterface
        public final void finish() {
            this.a.finish();
        }
    }

    protected class MWebChromeClient extends WebChromeClient {
        final /* synthetic */ BaseWebviewActivity a;
        private OpenFileChooserCallBack b;

        public MWebChromeClient(BaseWebviewActivity baseWebviewActivity, OpenFileChooserCallBack openFileChooserCallBack) {
            this.a = baseWebviewActivity;
            this.b = openFileChooserCallBack;
        }

        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            this.a.showAlert(str2);
            jsResult.confirm();
            return true;
        }

        public void openFileChooser(ValueCallback<Uri> valueCallback, String str) {
            if (this.b != null) {
                this.b.openFileChooserCallBack(valueCallback, str);
            }
        }

        public void openFileChooserOfAndroid5(ValueCallback<Uri[]> valueCallback, String str) {
            if (this.b != null) {
                this.b.openFileChooserCallBackOfAndroid5(valueCallback, str);
            }
        }

        public void openFileChooser(ValueCallback<Uri> valueCallback) {
            openFileChooser(valueCallback, "");
        }

        public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
            openFileChooser(valueCallback, str);
        }

        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
            openFileChooserOfAndroid5(valueCallback, "");
            return true;
        }
    }

    protected class ReOnCancelListener implements OnCancelListener {
        final /* synthetic */ BaseWebviewActivity a;

        protected ReOnCancelListener(BaseWebviewActivity baseWebviewActivity) {
            this.a = baseWebviewActivity;
        }

        public void onCancel(DialogInterface dialogInterface) {
            if (this.a.mFilePathCallback != null) {
                this.a.mFilePathCallback.onReceiveValue(null);
                this.a.mFilePathCallback = null;
            }
            if (this.a.mFilePathCallbacks != null) {
                this.a.mFilePathCallbacks.onReceiveValue(null);
                this.a.mFilePathCallbacks = null;
            }
        }
    }

    protected abstract void reload();

    public void openFileChooserCallBack(ValueCallback<Uri> valueCallback, String str) {
        this.mFilePathCallback = valueCallback;
        showOptions();
    }

    public void openFileChooserCallBackOfAndroid5(ValueCallback<Uri[]> valueCallback, String str) {
        this.mFilePathCallbacks = valueCallback;
        showOptions();
    }

    protected void showOptions() {
        Builder builder = new Builder(this);
        builder.setOnCancelListener(new ReOnCancelListener(this));
        builder.setTitle("Image Chooser");
        builder.setItems(R.array.options, new c(this));
        builder.show();
    }

    protected void showAlert(String str) {
        if (this.mDialogUtils == null) {
            this.mDialogUtils = new DialogUtils(this);
        }
        if (!isFinishing()) {
            this.alertDialog = this.mDialogUtils.createDiaglog(str);
            this.alertDialog.show();
        }
    }

    protected void loadUserAgent(WebSettings webSettings) {
        if (this.eventUrl.contains("v.6.cn")) {
            webSettings.setUserAgentString(SocketUtil.encryptContent(AppInfoUtils.getAppInfo()));
        } else {
            webSettings.setUserAgentString(webSettings.getUserAgentString());
        }
    }

    protected void initJsVariable() {
        String str;
        String str2 = null;
        if (GlobleValue.getUserBean() == null) {
            str = null;
        } else {
            str = GlobleValue.getUserBean().getId();
        }
        this.loginUid = str;
        this.encpass = SaveUserInfoUtils.getEncpass(this);
        if (!TextUtils.isEmpty(this.encpass)) {
            str2 = this.encpass;
        }
        this.encpass = str2;
    }

    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        getWindow().setSoftInputMode(16);
        initJsVariable();
        this.webChromeClient = new MWebChromeClient(this, this);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        Uri uri;
        super.onActivityResult(i, i2, intent);
        if (i == CommonInts.USER_MANAGER_REQUEST_CODE) {
            initJsVariable();
            reload();
        }
        if (i == 1) {
            if (this.mFilePathCallback != null) {
                Object obj;
                if (intent == null || i2 != -1) {
                    obj = null;
                } else {
                    obj = intent.getData();
                }
                if (obj != null) {
                    this.mFilePathCallback.onReceiveValue(obj);
                } else {
                    this.mFilePathCallback.onReceiveValue(null);
                }
            }
            if (this.mFilePathCallbacks != null) {
                if (intent == null || i2 != -1) {
                    uri = null;
                } else {
                    uri = intent.getData();
                }
                if (uri != null) {
                    this.mFilePathCallbacks.onReceiveValue(new Uri[]{uri});
                } else {
                    this.mFilePathCallbacks.onReceiveValue(null);
                }
            }
        }
        if (i == 2) {
            if (this.tempFile == null || !this.tempFile.exists()) {
                if (this.mFilePathCallback != null) {
                    this.mFilePathCallback.onReceiveValue(null);
                }
                if (this.mFilePathCallbacks != null) {
                    this.mFilePathCallbacks.onReceiveValue(null);
                }
            } else {
                uri = Uri.fromFile(this.tempFile);
                if (this.mFilePathCallback != null) {
                    if (uri != null) {
                        this.mFilePathCallback.onReceiveValue(uri);
                    } else {
                        this.mFilePathCallback.onReceiveValue(null);
                    }
                }
                if (this.mFilePathCallbacks != null) {
                    if (uri != null) {
                        this.mFilePathCallbacks.onReceiveValue(new Uri[]{uri});
                    } else {
                        this.mFilePathCallbacks.onReceiveValue(null);
                    }
                }
            }
        }
        this.mFilePathCallback = null;
        this.mFilePathCallbacks = null;
    }

    protected void setWebViewAttribute() {
        if (this.mWebView != null) {
            this.mWebView.clearCache(true);
            this.mWebView.setOnLongClickListener(new d(this));
            WebSettings settings = this.mWebView.getSettings();
            this.mWebView.addJavascriptInterface(new AppAndroid(this), "appAndroid");
            settings.setDomStorageEnabled(true);
            settings.setJavaScriptEnabled(true);
            loadUserAgent(settings);
        }
    }

    protected void initSlidingMenu() {
        SlidingMenu slidingMenu = getSlidingMenu();
        setBehindContentView(R.layout.phone_room_behind);
        slidingMenu.setMode(0);
        slidingMenu.setTouchModeAbove(0);
        slidingMenu.setOnOpenedListener(new e(this));
    }
}
