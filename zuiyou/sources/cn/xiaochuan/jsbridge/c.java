package cn.xiaochuan.jsbridge;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.webkit.WebView;
import android.webkit.WebView.HitTestResult;

public class c implements OnLongClickListener {
    public boolean onLongClick(View view) {
        HitTestResult hitTestResult = ((WebView) view).getHitTestResult();
        if (hitTestResult.getType() == 5) {
            Object extra = hitTestResult.getExtra();
            if (!TextUtils.isEmpty(extra)) {
                return a(extra);
            }
        }
        return false;
    }

    public boolean a(@NonNull String str) {
        return false;
    }
}
