package qsbk.app.game;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.R;
import qsbk.app.activity.base.BaseWebviewActivity;
import qsbk.app.core.web.js.ExposeApi;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.core.web.plugin.PluginProxy;
import qsbk.app.core.web.route.IWebResponse;
import qsbk.app.core.web.route.RouteProxy;
import qsbk.app.core.web.ui.WebInterface;
import qsbk.app.model.EventWindow;
import qsbk.app.utils.LogUtil;
import qsbk.app.widget.CommonWebView;

public class GameIndexActivity extends BaseWebviewActivity implements WebInterface, DownListener {
    RouteProxy c;
    private ExposeApi d;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected String f() {
        return "游戏";
    }

    protected void onNewIntent(Intent intent) {
        LogUtil.d("on new intent");
        super.onNewIntent(intent);
    }

    protected String g() {
        return "http://www.ilongyuan.com.cn/qiubai/index.html";
    }

    @SuppressLint({"JavascriptInterface"})
    protected void a(CommonWebView commonWebView) {
        ContinueDownloader.instance().registerListener(this);
        CommonWebView commonWebView2 = (CommonWebView) findViewById(R.id.storeview);
        Map hashMap = new HashMap();
        hashMap.put("network", NetworkStatusPlugin.class);
        hashMap.put(EventWindow.JUMP_GAME, GamePlugin.class);
        PluginProxy pluginProxy = new PluginProxy(this, commonWebView2, hashMap);
        this.c = new RouteProxy(commonWebView2);
        this.d = new ExposeApi(pluginProxy, this.c);
        commonWebView2.setWebViewClient(new i(this));
        commonWebView2.setWebChromeClient(new j(this));
        commonWebView2.addJavascriptInterface(new Object(), "_androidWebview");
    }

    protected void onDestroy() {
        ContinueDownloader.instance().unRegisterListener(this);
        super.onDestroy();
    }

    public void startActivityForResult(Plugin plugin, Intent intent, int i) {
    }

    public void setFocusPlugin(Plugin plugin) {
    }

    public Activity getCurActivity() {
        return null;
    }

    private void a(String str, String str2, String str3, IWebResponse iWebResponse) {
        if (this.c != null) {
            this.c.reqWeb(str, str2, str3, iWebResponse);
        }
    }

    public void onDownload(String str, boolean z, String str2) {
        a(EventWindow.JUMP_GAME, "download_update", GamePlugin.getDownloadStateObject(str, z).toString(), new k(this));
    }

    public void onProgress(String str, long j, long j2) {
        a(EventWindow.JUMP_GAME, "download_update", GamePlugin.getDownloadingStateObject(str, j, j2).toString(), new l(this));
    }
}
