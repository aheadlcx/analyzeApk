package cn.xiaochuankeji.tieba.background.utils;

import android.content.Context;
import android.text.TextUtils;
import cn.xiaochuan.base.BaseApplication;
import com.iflytek.aiui.AIUIConstant;
import com.umeng.analytics.MobclickAgent;
import java.util.HashMap;

public class h {
    private static HashMap<String, String> a = new HashMap();

    public static void a(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            MobclickAgent.onEvent(context, str);
            return;
        }
        a.clear();
        a.put(AIUIConstant.KEY_TAG, str2);
        MobclickAgent.onEvent(context, str, a);
    }

    public static void a(String str, String str2) {
        a(BaseApplication.getAppContext(), str, str2);
    }
}
