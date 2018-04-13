package com.alibaba.baichuan.android.trade.config;

import com.alibaba.baichuan.android.trade.config.a.a;
import com.alibaba.baichuan.android.trade.constants.ConfigConstant;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.utils.http.HttpHelper;
import com.alibaba.baichuan.android.trade.utils.http.HttpHelper.HttpHelpterException;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import org.json.JSONException;
import org.json.JSONObject;

class c implements Runnable {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public void run() {
        String str = null;
        try {
            if (!ConfigConstant.getConfigUrl().equals(this.a.a)) {
                this.a.a = ConfigConstant.getConfigUrl();
            }
            AlibcLogger.d("AlibcConfigPullProcessor", "开始从网络拉取config数据,url为：" + this.a.a);
            String str2 = HttpHelper.get(this.a.a, null);
            JSONObject jSONObject = new JSONObject(str2);
            AlibcLogger.d("AlibcConfigPullProcessor", "网络拉取的config数据为" + (jSONObject != null ? jSONObject.toString() : null));
            a aVar = new a();
            aVar.a(jSONObject);
            if (aVar == null || !this.a.a(aVar)) {
                AlibcLogger.e("AlibcConfigPullProcessor", "config文件校验失败");
                this.a.a(UserTrackerConstants.EM_CHECK_FAILURE);
                this.a.c.a("config文件校验失败");
                return;
            }
            AlibcLogger.d("AlibcConfigPullProcessor", "网络拉取config数据成功");
            this.a.d();
            this.a.c.a(aVar, str2);
        } catch (HttpHelpterException e) {
            AlibcLogger.e("AlibcConfigPullProcessor", "获取Http网络错误" + e.getMessage());
            if (e.statusCode != HttpHelper.INVALID_RESPONSE_CODE) {
                str = String.valueOf(e.statusCode);
            }
            this.a.a(str, UserTrackerConstants.EM_NETWORK_ERROR);
            this.a.c.a(e.getMessage());
        } catch (JSONException e2) {
            AlibcLogger.e("AlibcConfigPullProcessor", "解析JSON出错" + e2.getMessage());
            this.a.a(UserTrackerConstants.EM_ANALYSE_FAILURE);
            this.a.c.a(e2.getMessage());
        }
    }
}
