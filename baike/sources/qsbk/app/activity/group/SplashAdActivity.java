package qsbk.app.activity.group;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.baidu.mobstat.StatService;
import com.qiushibaike.statsdk.StatSDK;
import java.io.File;
import qsbk.app.R;
import qsbk.app.activity.MainActivity;
import qsbk.app.ad.feedsad.FeedsAdUtils;
import qsbk.app.ad.feedsad.qbad.QbAdApkDownloader;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;
import qsbk.app.utils.SplashAdManager;
import qsbk.app.utils.SplashAdManager.SplashAdItem;
import qsbk.app.utils.UIHelper;

public class SplashAdActivity extends Activity {
    private TextView a;
    private ImageView b;
    private SplashAdItem c;
    private int d = 3;
    private Runnable e = new a(this);

    public static void launch(Context context, SplashAdItem splashAdItem) {
        Intent intent = new Intent(context, SplashAdActivity.class);
        intent.putExtra("item", splashAdItem);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_splash_ad);
        this.c = (SplashAdItem) getIntent().getSerializableExtra("item");
        if (this.c == null) {
            a();
            finish();
            return;
        }
        this.d = Math.max(2, this.c.delay);
        this.a = (TextView) findViewById(R.id.splash_ad_btn);
        this.b = (ImageView) findViewById(R.id.splash_ad_image);
        FrescoImageloader.displayImage(this.b, "file://" + new File(SplashAdManager.getDataDir(), "I" + this.c.picUrl.hashCode()).getAbsolutePath());
        this.a.setOnClickListener(new b(this));
        findViewById(R.id.root).setOnClickListener(new c(this));
        this.e.run();
        StatSDK.onEvent(this, "splash_ad_show", String.valueOf(this.c.id));
        StatService.onEvent(this, "splash_ad_show", String.valueOf(this.c.id));
    }

    private void a(Context context, String str) {
        if (context != null) {
            String network = HttpUtils.getNetwork(context);
            if (FeedsAdUtils.needShowConfirm(network)) {
                this.a.removeCallbacks(this.e);
                new Builder(context).setTitle("温馨提示").setCancelable(true).setMessage("当前为" + network + "网络，开始下载应用？").setNegativeButton("取消", new e(this)).setPositiveButton("确认", new d(this, context, str)).create().show();
                return;
            }
            b(context, str);
        }
    }

    private void b(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            String substring;
            ReportAdForMedalUtil.report(AD_PROVIDER.QIUBAI, AD_TYPE.DOWNLOAD);
            int indexOf = str.indexOf(63);
            if (indexOf != -1) {
                substring = str.substring(0, indexOf);
            } else {
                substring = str;
            }
            if (substring.endsWith(".apk")) {
                QbAdApkDownloader.instance().downloadFile(context, str, "应用");
                return;
            }
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        getWindow().getDecorView().setBackgroundDrawable(null);
    }

    private void a() {
        UIHelper.setActivityFullscreen(this, false);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.FROM_SPLASH, true);
        startActivity(intent);
    }
}
