package cn.xiaochuankeji.tieba.push.d;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.analyse.log.LogCommandModel;
import cn.xiaochuankeji.tieba.analyse.log.b;
import cn.xiaochuankeji.tieba.analyse.log.d;
import cn.xiaochuankeji.tieba.api.log.LogUploadService;
import cn.xiaochuankeji.tieba.network.c;
import cn.xiaochuankeji.tieba.push.b.e;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meizu.cloud.pushsdk.networking.common.ANConstants;

public class a {
    static void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                com.izuiyou.a.a.a.c("Debug", "start log collection: " + str);
                LogCommandModel logCommandModel = (LogCommandModel) JSON.parseObject(str, LogCommandModel.class);
                if (TextUtils.isEmpty(logCommandModel.opid)) {
                    com.izuiyou.a.a.a.d("Debug", " invaild opid !!");
                } else {
                    a(logCommandModel).a();
                }
            } catch (Exception e) {
                com.izuiyou.a.a.a.d("Debug", "data can not parse to debug data " + e);
            }
        }
    }

    static void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                com.izuiyou.a.a.a.c("Debug", "start debug process: " + str);
                JSONObject parseObject = JSON.parseObject(str);
                String string = parseObject.getString("opid");
                int intValue = parseObject.getIntValue("type");
                if (intValue == 1) {
                    a(string, e.f());
                } else if (intValue == 2) {
                    a(string, e.d(parseObject.getIntValue("session_type")));
                } else if (intValue == 3) {
                    a(string, e.a(e.a(parseObject.getLongValue("self_id"), parseObject.getLongValue("other_id"))));
                } else {
                    a(string, -9);
                }
            } catch (Exception e) {
                com.izuiyou.a.a.a.d("Debug", "data can not parse to debug data " + e);
            }
        }
    }

    private static void a(String str, int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("opid", str);
        if (i == -9) {
            jSONObject.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(-9));
            jSONObject.put("msg", "un-support debug type");
        } else if (i == 1) {
            jSONObject.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(1));
            jSONObject.put("msg", ANConstants.SUCCESS);
        } else if (i == -1) {
            jSONObject.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(-1));
            jSONObject.put("msg", "stop with exception");
        } else {
            jSONObject.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(i));
            jSONObject.put("msg", "stop with business error");
        }
        ((LogUploadService) c.b(LogUploadService.class)).reportResetStatus(jSONObject).a(rx.f.a.c()).a(new rx.e<String>() {
            public /* synthetic */ void onNext(Object obj) {
                a((String) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(String str) {
            }
        });
    }

    private static cn.xiaochuankeji.tieba.analyse.log.a a(LogCommandModel logCommandModel) {
        switch (logCommandModel.type) {
            case 1:
                return new b(logCommandModel);
            case 2:
                return new cn.xiaochuankeji.tieba.analyse.log.c(logCommandModel);
            case 3:
                return new d(logCommandModel);
            default:
                return new b(logCommandModel);
        }
    }
}
