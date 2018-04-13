package com.alibaba.baichuan.android.trade.adapter.mtop;

import android.text.TextUtils;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.AlibcContext.Environment;
import com.alibaba.baichuan.android.trade.adapter.mtop.NetworkClient.NetworkRequestListener;
import com.alibaba.baichuan.android.trade.adapter.ut.UserTrackerCompoment;
import com.alibaba.baichuan.android.trade.config.AlibcConfig;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.utils.json.JSONUtils;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import com.taobao.tao.remotebusiness.MtopBusiness;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import mtopsdk.common.util.m;
import mtopsdk.mtop.a.g;
import mtopsdk.mtop.b.d;
import mtopsdk.mtop.domain.EnvModeEnum;
import mtopsdk.mtop.domain.MethodEnum;
import mtopsdk.mtop.domain.MtopRequest;
import mtopsdk.mtop.domain.MtopResponse;
import org.json.JSONObject;

public class AlibcMtop implements NetworkClient {
    private boolean a;

    private static class a {
        public static AlibcMtop a = new AlibcMtop();
    }

    private AlibcMtop() {
    }

    private MtopRequest a(NetworkRequest networkRequest) {
        if (TextUtils.isEmpty(networkRequest.apiVersion)) {
            networkRequest.apiVersion = "1.0";
        }
        MtopRequest mtopRequest = new MtopRequest();
        mtopRequest.setApiName(networkRequest.apiName);
        mtopRequest.setVersion(networkRequest.apiVersion);
        mtopRequest.setNeedEcode(networkRequest.needLogin);
        mtopRequest.setNeedSession(true);
        if (networkRequest.paramMap != null) {
            JSONObject jsonObject = JSONUtils.getJsonObject(mtopRequest.getData());
            JSONObject jSONObject = jsonObject == null ? new JSONObject() : jsonObject;
            for (Entry entry : networkRequest.paramMap.entrySet()) {
                if (entry.getValue() != null) {
                    try {
                        jSONObject.put((String) entry.getKey(), ((Serializable) entry.getValue()).toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            mtopRequest.setData(jSONObject.toString());
        }
        return mtopRequest;
    }

    private void a(MtopResponse mtopResponse) {
        if (mtopResponse.isApiSuccess()) {
            AlibcLogger.d("AlibcMtop", "网络请求成功");
        } else if (mtopResponse.isSessionInvalid()) {
            AlibcLogger.e("AlibcMtop", "session 失效， do autologin or login business msg = " + mtopResponse.getRetMsg());
        } else if (mtopResponse.isSystemError() || mtopResponse.isNetworkError() || mtopResponse.isExpiredRequest() || mtopResponse.is41XResult() || mtopResponse.isApiLockedResult() || mtopResponse.isMtopSdkError()) {
            AlibcLogger.e("AlibcMtop", "系统错误，网络错误，防刷，防雪崩 msg =" + mtopResponse.getRetMsg());
        } else {
            AlibcLogger.e("AlibcMtop", "业务错误 msg =" + mtopResponse.getRetMsg());
        }
    }

    private void a(MtopResponse mtopResponse, String str, String str2) {
        String str3 = "";
        str3 = mtopResponse.isSessionInvalid() ? "session 失效， do autologin or login business" : (mtopResponse.isSystemError() || mtopResponse.isNetworkError() || mtopResponse.isExpiredRequest() || mtopResponse.is41XResult() || mtopResponse.isApiLockedResult() || mtopResponse.isMtopSdkError()) ? "系统错误，网络错误，防刷，防雪崩 " : "业务错误 ";
        UserTrackerCompoment.sendUseabilityFailureForOtherErrmsg(UserTrackerConstants.U_INVOKE, str3 + str2, UserTrackerConstants.ERRCODE_MTOP + str);
    }

    private NetworkResponse b(MtopResponse mtopResponse) {
        NetworkResponse networkResponse = new NetworkResponse();
        if (mtopResponse == null) {
            return networkResponse;
        }
        networkResponse.byteData = mtopResponse.getBytedata();
        networkResponse.httpCode = mtopResponse.getResponseCode() + "";
        networkResponse.errorCode = mtopResponse.getRetCode();
        networkResponse.errorMsg = mtopResponse.getRetMsg();
        networkResponse.isSuccess = mtopResponse.isApiSuccess();
        if (mtopResponse.getDataJsonObject() != null) {
            String jSONObject = mtopResponse.getDataJsonObject().toString();
            networkResponse.data = (Map) JSONUtils.parseStringValue(jSONObject, Map.class);
            networkResponse.jsonData = jSONObject;
        }
        return networkResponse;
    }

    public static AlibcMtop getInstance() {
        return a.a;
    }

    public synchronized void init() {
        if (!this.a) {
            this.a = true;
            m.b(false);
            m.a(AlibcContext.isDebuggable());
            d.a(0, 0);
            d.a(AlibcContext.sdkVersion);
            if (AlibcContext.environment == Environment.TEST) {
                mtopsdk.mtop.b.a.a(AlibcContext.context).a(EnvModeEnum.TEST);
            } else if (AlibcContext.environment == Environment.PRE) {
                mtopsdk.mtop.b.a.a(AlibcContext.context).a(EnvModeEnum.PREPARE);
            } else {
                mtopsdk.mtop.b.a.a(AlibcContext.context).a(EnvModeEnum.ONLINE);
            }
            mtopsdk.mtop.b.a.a(AlibcContext.context, AlibcConfig.getInstance().getWebTTID());
            g.a(AlibcConfig.getInstance().getWebTTID());
            AlibcLogger.d("AlibcMtop", "mtop init complete");
        }
    }

    public NetworkResponse sendRequest(NetworkRequest networkRequest) {
        if (networkRequest == null) {
            return null;
        }
        MtopBusiness build = MtopBusiness.build(a(networkRequest), AlibcConfig.getInstance().getWebTTID());
        if (networkRequest.needWua) {
            build.useWua();
        }
        if (networkRequest.needAuth && !AlibcContext.isVip) {
            build.setNeedAuth(networkRequest.authParams, true);
        }
        if (networkRequest.isPost) {
            build.reqMethod(MethodEnum.POST);
        }
        MtopResponse syncRequest = build.syncRequest();
        a(syncRequest);
        if (syncRequest.isApiSuccess()) {
            UserTrackerCompoment.sendUseabilitySuccess(UserTrackerConstants.U_INVOKE);
        } else {
            a(syncRequest, syncRequest.getRetCode(), "errmsg = " + syncRequest.getRetMsg() + " ,api = " + syncRequest.getApi());
        }
        return b(syncRequest);
    }

    public boolean sendRequest(NetworkRequestListener networkRequestListener, NetworkRequest networkRequest) {
        if (networkRequest == null) {
            if (networkRequestListener != null) {
                networkRequestListener.onError(-1, null);
            }
            return false;
        }
        MtopBusiness build = MtopBusiness.build(a(networkRequest), AlibcConfig.getInstance().getWebTTID());
        if (networkRequest.needWua) {
            build.useWua();
        }
        if (networkRequest.needAuth && !AlibcContext.isVip) {
            build.setNeedAuth(networkRequest.authParams, true);
        }
        if (networkRequest.isPost) {
            build.reqMethod(MethodEnum.POST);
        }
        if (networkRequest.extHeaders != null && networkRequest.extHeaders.size() > 0) {
            build.headers(networkRequest.extHeaders);
        }
        build.setSocketTimeoutMilliSecond(15000);
        build.setConnectionTimeoutMilliSecond(15000);
        build.registeListener(new a(this, networkRequestListener, networkRequest)).asyncRequest();
        return true;
    }
}
