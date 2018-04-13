package qsbk.app.live.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.Constants;
import qsbk.app.core.utils.DeviceUtils;
import qsbk.app.core.web.NativeJsPluginManager;
import qsbk.app.core.web.js.ExposeApi;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.core.web.ui.QsbkWebView;
import qsbk.app.core.web.ui.QsbkWebViewClient;
import qsbk.app.core.web.ui.WebInterface;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.live.R;

public class LiveStartUpDialog extends BaseDialog implements WebInterface {
    private Activity c;
    private QsbkWebView d;
    private String e;

    public LiveStartUpDialog(Activity activity, String str) {
        super(activity);
        this.c = activity;
        this.e = str;
    }

    protected int c() {
        return R.layout.live_start_up_dialog;
    }

    protected void d() {
        i();
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            LayoutParams attributes = window.getAttributes();
            attributes.width = -1;
            attributes.height = -1;
            window.setAttributes(attributes);
        }
        this.d = (QsbkWebView) findViewById(R.id.webview);
        this.d.setBackgroundColor(0);
        this.d.setLayerType(1, null);
        this.d.init(this, NativeJsPluginManager.getInstance().getPluginMap());
        ExposeApi exposeApi = this.d.getExposeApi();
        this.d.setWebViewClient(new QsbkWebViewClient());
        this.d.setWebChromeClient(new hn(this, exposeApi));
    }

    protected void e() {
        Map hashMap = new HashMap();
        hashMap.put("app_ver", DeviceUtils.getAppVersion());
        hashMap.put("device_info", DeviceUtils.getDeviceIdInfo());
        hashMap.put("model", Build.FINGERPRINT);
        if (AppUtils.getInstance().getUserInfoProvider().isLogin()) {
            hashMap.put("qbtoken", AppUtils.getInstance().getUserInfoProvider().getToken());
            hashMap.put("user_id", AppUtils.getInstance().getUserInfoProvider().getUserId() + "");
            hashMap.put("source", AppUtils.getInstance().getUserInfoProvider().getUserOrigin() + "");
            hashMap.put("app", Constants.APP_FLAG + "");
        }
        this.d.loadUrl(this.e, hashMap);
    }

    public void startActivityForResult(Plugin plugin, Intent intent, int i) {
        this.c.startActivityForResult(intent, i);
    }

    public void setFocusPlugin(Plugin plugin) {
    }

    public Activity getCurActivity() {
        return this.c;
    }

    protected int a() {
        return 48;
    }

    public void dismiss() {
        this.d.onDestroy();
        super.dismiss();
    }
}
