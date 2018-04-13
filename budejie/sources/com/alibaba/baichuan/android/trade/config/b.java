package com.alibaba.baichuan.android.trade.config;

import android.content.Context;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.adapter.ut.UserTrackerCompoment;
import com.alibaba.baichuan.android.trade.constants.ConfigConstant;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.utils.code.Md5Utils;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Map;

public class b {
    private String a = ConfigConstant.getConfigUrl();
    private Context b;
    private a c;
    private final long d = 5000000;

    public interface a {
        void a(com.alibaba.baichuan.android.trade.config.a.a aVar, String str);

        void a(String str);
    }

    public b(Context context, a aVar) {
        this.b = context;
        this.c = aVar;
        b();
    }

    private void a(String str) {
        a(null, str);
    }

    private void a(String str, String str2) {
        UserTrackerCompoment.sendUseabilityFailure(UserTrackerConstants.U_FETCH_CONFIG, str2, str);
    }

    private boolean a(com.alibaba.baichuan.android.trade.config.a.a aVar) {
        if (aVar.b()) {
            String b = b(aVar);
            aVar.c();
            Map map = (Map) aVar.a.get(ConfigConstant.CHECK_GROUP_NAME);
            if (map != null) {
                String str = (String) map.get("sign");
                if (str != null && str.equals(b)) {
                    return true;
                }
            }
        }
        return false;
    }

    private String b(com.alibaba.baichuan.android.trade.config.a.a aVar) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : aVar.a.keySet()) {
            stringBuilder.append(str);
            Map map = (Map) aVar.a.get(str);
            for (String str2 : map.keySet()) {
                stringBuilder.append(str2).append((String) map.get(str2));
            }
        }
        try {
            stringBuilder.append(ConfigConstant.MD5_SALT);
            char[] toCharArray = stringBuilder.toString().toCharArray();
            Arrays.sort(toCharArray);
            return Md5Utils.md5Digest(new String(toCharArray).getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            AlibcLogger.e("AlibcConfigPullProcessor", "生成摘要错误" + e.getMessage());
            return null;
        }
    }

    private void b() {
        this.a = ConfigConstant.getConfigUrl();
    }

    private void c() {
        AlibcContext.executorService.a(new c(this), 2000);
    }

    private void d() {
        UserTrackerCompoment.sendUseabilitySuccess(UserTrackerConstants.U_FETCH_CONFIG);
    }

    public void a() {
        AlibcLogger.d("AlibcConfigPullProcessor", "开启拉取网络配置");
        if (com.alibaba.baichuan.android.trade.utils.b.a.a(this.b)) {
            c();
            return;
        }
        this.c.a("没有网络，无法拉取config配置");
        AlibcLogger.i("AlibcConfigPullProcessor", "没有网络，无法拉取config配置");
    }
}
