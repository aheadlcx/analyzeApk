package com.ali.auth.third.mtop.rpc;

import android.text.TextUtils;
import com.ali.auth.third.core.MemberSDK;
import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.model.LoginReturnData;
import com.ali.auth.third.core.model.RpcRequest;
import com.ali.auth.third.core.model.RpcResponse;
import com.ali.auth.third.core.rpc.protocol.RpcException;
import com.ali.auth.third.core.trace.SDKLogger;
import com.ali.auth.third.core.util.JSONUtils;
import com.ali.auth.third.core.util.ResourceUtils;
import com.alibaba.fastjson.JSON;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import mtopsdk.mtop.b.a;
import mtopsdk.mtop.b.b;
import mtopsdk.mtop.domain.MethodEnum;
import mtopsdk.mtop.domain.MtopRequest;
import mtopsdk.mtop.domain.MtopResponse;
import org.json.JSONObject;

public class MTOPWrapper {
    private static MTOPWrapper INSTANCE = null;
    private static final int MTOP_BIZ_CODE = 94;
    private static final String TAG = "login.MTOPWrapperImpl";

    public static synchronized MTOPWrapper getInstance() {
        MTOPWrapper mTOPWrapper;
        synchronized (MTOPWrapper.class) {
            if (INSTANCE == null) {
                INSTANCE = new MTOPWrapper();
            }
            mTOPWrapper = INSTANCE;
        }
        return mTOPWrapper;
    }

    public String post(RpcRequest rpcRequest) {
        return post(rpcRequest, LoginReturnData.class).toString();
    }

    public <V> RpcResponse<V> post(RpcRequest rpcRequest, Class<V> cls) {
        return post(rpcRequest, cls, null);
    }

    public <V> RpcResponse<V> post(RpcRequest rpcRequest, Class<V> cls, String str) {
        MtopResponse syncRequest;
        Throwable e;
        try {
            MtopRequest mtopRequest = new MtopRequest();
            mtopRequest.setApiName(rpcRequest.target);
            mtopRequest.setVersion(rpcRequest.version);
            mtopRequest.setNeedEcode(false);
            mtopRequest.setNeedSession(false);
            JSONObject jSONObject = new JSONObject();
            for (int i = 0; i < rpcRequest.paramNames.size(); i++) {
                jSONObject.put((String) rpcRequest.paramNames.get(i), rpcRequest.paramValues.get(i).toString());
            }
            mtopRequest.setData(jSONObject.toString());
            b connectionTimeoutMilliSecond = a.a(KernelContext.context).a(mtopRequest, MemberSDK.ttid).reqMethod(MethodEnum.POST).setBizId(94).setConnectionTimeoutMilliSecond(10000);
            if (!TextUtils.isEmpty(str)) {
                connectionTimeoutMilliSecond.setReqUserId(str);
            }
            connectionTimeoutMilliSecond.retryTime(1);
            syncRequest = connectionTimeoutMilliSecond.syncRequest();
            try {
                SDKLogger.d(TAG, "receive MtopResponse" + syncRequest);
            } catch (Exception e2) {
                e = e2;
                SDKLogger.e(TAG, "MtopResponse error", e);
                e.printStackTrace();
                if (syncRequest == null) {
                    return processMtopResponse(syncRequest, cls);
                }
                SDKLogger.e(TAG, "MtopResponse response=null");
                return null;
            }
        } catch (Exception e3) {
            e = e3;
            syncRequest = null;
            SDKLogger.e(TAG, "MtopResponse error", e);
            e.printStackTrace();
            if (syncRequest == null) {
                return processMtopResponse(syncRequest, cls);
            }
            SDKLogger.e(TAG, "MtopResponse response=null");
            return null;
        }
        if (syncRequest == null) {
            return processMtopResponse(syncRequest, cls);
        }
        SDKLogger.e(TAG, "MtopResponse response=null");
        return null;
    }

    private <V> RpcResponse<V> processMtopResponse(MtopResponse mtopResponse, Class<V> cls) {
        JSONObject optJSONObject;
        RpcResponse<V> rpcResponse;
        if (mtopResponse != null && mtopResponse.isApiSuccess()) {
            try {
                byte[] bytedata = mtopResponse.getBytedata();
                if (bytedata == null) {
                    return null;
                }
                optJSONObject = new JSONObject(new String(bytedata)).optJSONObject("data");
                rpcResponse = new RpcResponse();
                rpcResponse.code = optJSONObject.optInt(CheckCodeDO.CHECKCODE_USER_INPUT_KEY);
                rpcResponse.codeGroup = optJSONObject.optString("codeGroup");
                rpcResponse.message = optJSONObject.optString(com.baidu.mobads.openad.c.b.EVENT_MESSAGE);
                rpcResponse.actionType = optJSONObject.optString("actionType");
                if (TextUtils.isEmpty(optJSONObject.optString("returnValue"))) {
                    return rpcResponse;
                }
                rpcResponse.returnValue = JSONUtils.parseStringValue(optJSONObject.optString("returnValue"), cls);
                return rpcResponse;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else if (mtopResponse == null) {
            return null;
        } else {
            if (mtopResponse.isNetworkError()) {
                throw new RpcException(Integer.valueOf(7), ResourceUtils.getString(KernelContext.context, "aliusersdk_network_error"));
            } else if (mtopResponse.isApiLockedResult()) {
                throw new RpcException(Integer.valueOf(400), ResourceUtils.getString(KernelContext.context, "aliusersdk_network_error"));
            } else if (mtopResponse.is41XResult()) {
                throw new RpcException(Integer.valueOf(401), ResourceUtils.getString(KernelContext.context, "aliusersdk_network_error"));
            } else if (mtopResponse.isExpiredRequest()) {
                throw new RpcException(Integer.valueOf(402), ResourceUtils.getString(KernelContext.context, "aliusersdk_network_error"));
            } else if (mtopResponse.isIllegelSign()) {
                throw new RpcException(Integer.valueOf(403), ResourceUtils.getString(KernelContext.context, "aliusersdk_network_error"));
            } else if (mtopResponse.isSystemError()) {
                throw new RpcException(Integer.valueOf(406), ResourceUtils.getString(KernelContext.context, "aliusersdk_network_error"));
            } else if (mtopResponse.isSessionInvalid()) {
                throw new RpcException(Integer.valueOf(407), ResourceUtils.getString(KernelContext.context, "aliusersdk_session_error"));
            } else {
                try {
                    rpcResponse = new RpcResponse();
                    byte[] bytedata2 = mtopResponse.getBytedata();
                    if (bytedata2 == null) {
                        return rpcResponse;
                    }
                    optJSONObject = new JSONObject(new String(bytedata2)).optJSONObject("data");
                    if (optJSONObject == null) {
                        return rpcResponse;
                    }
                    rpcResponse.message = optJSONObject.optString(com.baidu.mobads.openad.c.b.EVENT_MESSAGE);
                    rpcResponse.actionType = optJSONObject.optString("actionType");
                    rpcResponse.code = optJSONObject.optInt(CheckCodeDO.CHECKCODE_USER_INPUT_KEY);
                    rpcResponse.codeGroup = optJSONObject.optString("codeGroup");
                    if (TextUtils.isEmpty(optJSONObject.optString("returnValue"))) {
                        return rpcResponse;
                    }
                    rpcResponse.returnValue = JSON.parseObject(optJSONObject.optString("returnValue"), cls);
                    return rpcResponse;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
        }
    }
}
