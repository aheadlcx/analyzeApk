package com.alibaba.baichuan.android.trade.ui.activity;

import android.app.Activity;
import android.content.Intent;
import com.alibaba.baichuan.android.trade.component.b;
import com.alibaba.baichuan.android.trade.config.AlibcConfig;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.model.ApplinkOpenType;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import java.util.Map;

public class AlibcNativeTaobaoActivity extends Activity {
    public static String a() {
        return "alisdk://";
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        if (intent != null) {
            setIntent(null);
            String stringExtra = intent.getStringExtra("actionName");
            if (UserTrackerConstants.P_SHOWNATIVE.equals(stringExtra)) {
                String stringExtra2 = intent.getStringExtra("id");
                Map map = (Map) intent.getSerializableExtra("actionParameters");
                String str = intent.getSerializableExtra("pid") != null ? (String) intent.getSerializableExtra("pid") : null;
                ApplinkOpenType applinkOpenType = (ApplinkOpenType) intent.getSerializableExtra("type");
                AlibcLogger.i("BaichuanTLOG", "AlibcNativeTaobaoActivity.start()--Back From NativeTaobao: action:showNative itemId:" + (stringExtra2 == null ? "null" : stringExtra2) + " taokePid:" + (str == null ? "null" : str));
                if (!b.a(this, applinkOpenType, null, stringExtra2, AlibcConfig.getInstance().getIsvCode(), str, a(), map)) {
                    finish();
                    return;
                }
                return;
            }
            if (isTaskRoot()) {
                String str2 = "BaichuanTLOG";
                StringBuilder append = new StringBuilder().append("AlibcNativeTaobaoActivity.start()--Back From NativeTaobao(jump through scheme url): action:");
                if (stringExtra == null) {
                    stringExtra = "null";
                }
                AlibcLogger.i(str2, append.append(stringExtra).toString());
                Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
                launchIntentForPackage.addFlags(67108864);
                startActivity(launchIntentForPackage);
            }
            finish();
            return;
        }
        finish();
    }
}
