package com.alibaba.baichuan.android.jsbridge.a;

import android.text.TextUtils;
import com.alibaba.baichuan.android.jsbridge.AlibcJsCallbackContext;
import com.alibaba.baichuan.android.jsbridge.AlibcJsResult;
import com.alibaba.baichuan.android.jsbridge.plugin.AlibcApiPlugin;
import com.alibaba.baichuan.android.trade.adapter.applink.AlibcApplink;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.baichuan.android.trade.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import java.util.Map;

public class a extends AlibcApiPlugin {
    public static String a = "AliBCAppLink";

    public boolean execute(String str, String str2, AlibcJsCallbackContext alibcJsCallbackContext) {
        String obj2String;
        Exception e;
        AlibcJsResult alibcJsResult;
        Map map = null;
        boolean z = false;
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str) || alibcJsCallbackContext == null) {
            AlibcJsResult alibcJsResult2 = new AlibcJsResult("6");
            alibcJsResult2.setResultCode("2");
            if (alibcJsCallbackContext != null) {
                alibcJsCallbackContext.error(alibcJsResult2);
            }
            return false;
        }
        try {
            Map obj2MapObject = StringUtils.obj2MapObject(JSON.parse(str2));
            obj2String = StringUtils.obj2String(obj2MapObject.get("type"));
            try {
                map = StringUtils.obj2MapObject(obj2MapObject.get("params"));
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                if (obj2String != null) {
                }
                alibcJsResult = new AlibcJsResult("6");
                alibcJsResult.setResultCode("2");
                alibcJsCallbackContext.error(alibcJsResult);
                return true;
            }
        } catch (Exception e3) {
            e = e3;
            obj2String = map;
            e.printStackTrace();
            if (obj2String != null) {
            }
            alibcJsResult = new AlibcJsResult("6");
            alibcJsResult.setResultCode("2");
            alibcJsCallbackContext.error(alibcJsResult);
            return true;
        }
        if (obj2String != null || map == null) {
            alibcJsResult = new AlibcJsResult("6");
            alibcJsResult.setResultCode("2");
            alibcJsCallbackContext.error(alibcJsResult);
            return true;
        }
        if (obj2String.equals(AlibcConstants.SHOP)) {
            z = AlibcApplink.getInstance().jumpShop(this.b, map);
        } else if (obj2String.equals(AlibcConstants.DETAIL)) {
            z = AlibcApplink.getInstance().jumpDetail(this.b, map);
        } else if (obj2String.equals("url")) {
            z = AlibcApplink.getInstance().jumpTBURI(this.b, map);
        }
        if (z) {
            alibcJsCallbackContext.success(AlibcJsResult.RET_SUCCESS);
            return true;
        }
        alibcJsCallbackContext.error(AlibcJsResult.RET_FAIL);
        return true;
    }
}
