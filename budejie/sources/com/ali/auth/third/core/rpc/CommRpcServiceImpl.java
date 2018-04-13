package com.ali.auth.third.core.rpc;

import android.text.TextUtils;
import com.ali.auth.third.core.model.RpcRequest;
import com.ali.auth.third.core.model.RpcResponse;
import com.ali.auth.third.core.service.RpcService;
import com.ali.auth.third.core.trace.SDKLogger;
import com.ali.auth.third.core.util.JSONUtils;
import com.baidu.mobads.openad.c.b;
import java.util.Iterator;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.json.JSONArray;
import org.json.JSONObject;

public class CommRpcServiceImpl implements RpcService {
    public String invoke(RpcRequest rpcRequest) {
        try {
            String str = rpcRequest.target;
            String str2 = rpcRequest.version;
            JSONArray jSONArray = new JSONArray();
            Iterator it = rpcRequest.paramValues.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
            return HttpConnectionHelper.post(str, str2, jSONArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> RpcResponse<T> invoke(RpcRequest rpcRequest, Class<T> cls) {
        try {
            String str = rpcRequest.target;
            String str2 = rpcRequest.version;
            JSONArray jSONArray = new JSONArray();
            Iterator it = rpcRequest.paramValues.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
            str = HttpConnectionHelper.post(str, str2, jSONArray.toString());
            SDKLogger.d("", "post response = " + str);
            JSONObject jSONObject = new JSONObject(str);
            RpcResponse<T> rpcResponse = new RpcResponse();
            rpcResponse.code = jSONObject.optInt(CheckCodeDO.CHECKCODE_USER_INPUT_KEY);
            rpcResponse.message = jSONObject.optString(b.EVENT_MESSAGE);
            rpcResponse.codeGroup = jSONObject.optString("codeGroup");
            rpcResponse.msgCode = jSONObject.optString("msgCode");
            rpcResponse.msgInfo = jSONObject.optString("msgInfo");
            rpcResponse.actionType = jSONObject.optString("actionType");
            if (TextUtils.isEmpty(jSONObject.optString("returnValue"))) {
                return rpcResponse;
            }
            rpcResponse.returnValue = JSONUtils.parseStringValue(jSONObject.optString("returnValue"), cls);
            return rpcResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void registerSessionInfo(String str, String str2) {
    }

    public String getDeviceId() {
        return "";
    }

    public void logout() {
    }
}
