package com.alibaba.baichuan.android.jsbridge.a;

import android.text.TextUtils;
import com.alibaba.baichuan.android.jsbridge.AlibcJsCallbackContext;
import com.alibaba.baichuan.android.jsbridge.AlibcJsResult;
import com.alibaba.baichuan.android.jsbridge.plugin.AlibcApiPlugin;
import com.alibaba.baichuan.android.trade.adapter.mtop.AlibcMtop;
import com.alibaba.baichuan.android.trade.adapter.mtop.NetworkRequest;
import com.alibaba.baichuan.android.trade.utils.StringUtils;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.sdk.data.a;
import com.tencent.open.GameAppOperation;
import java.util.HashMap;
import java.util.Map;

public class d extends AlibcApiPlugin {
    private NetworkRequest a(Map map) {
        NetworkRequest networkRequest = new NetworkRequest();
        networkRequest.apiName = StringUtils.obj2String(map.get("api"));
        networkRequest.apiVersion = StringUtils.obj2String(map.get(GameAppOperation.QQFAV_DATALINE_VERSION));
        networkRequest.needLogin = StringUtils.obj2Boolean(map.get("needLogin"));
        networkRequest.needWua = StringUtils.obj2Boolean(map.get("needWua"));
        networkRequest.needAuth = StringUtils.obj2Boolean(map.get("needAuth"));
        networkRequest.isPost = StringUtils.obj2Boolean(map.get("isPost"));
        networkRequest.extHeaders = StringUtils.obj2MapString(map.get("ext_headers"));
        networkRequest.timeOut = StringUtils.obj2Long(map.get(a.f)).intValue();
        networkRequest.requestType = networkRequest.hashCode();
        JSONObject parseObject = JSONObject.parseObject(StringUtils.obj2String(map.get("params")));
        if (parseObject != null) {
            Map hashMap = new HashMap();
            for (String str : parseObject.keySet()) {
                Object obj = parseObject.get(str);
                if (obj != null) {
                    hashMap.put(str, obj.toString());
                }
            }
            networkRequest.paramMap = hashMap;
        }
        return networkRequest;
    }

    public boolean execute(String str, String str2, AlibcJsCallbackContext alibcJsCallbackContext) {
        AlibcJsResult alibcJsResult;
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str) || alibcJsCallbackContext == null) {
            alibcJsResult = new AlibcJsResult("6");
            alibcJsResult.setResultCode("2");
            if (alibcJsCallbackContext == null) {
                return false;
            }
            alibcJsCallbackContext.error(alibcJsResult);
            return false;
        }
        try {
            NetworkRequest a = a(StringUtils.obj2MapObject(JSON.parseObject(str2)));
            if (a == null || !a.check()) {
                alibcJsResult = new AlibcJsResult("6");
                alibcJsResult.setResultCode("2");
                alibcJsCallbackContext.error(alibcJsResult);
                return false;
            }
            AlibcMtop.getInstance().sendRequest(new e(this, alibcJsCallbackContext), a);
            return true;
        } catch (Exception e) {
            AlibcLogger.e("AliBCNetWork", "isInstall parse params error, params: " + str2);
            return false;
        }
    }
}
