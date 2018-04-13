package mtopsdk.mtop.b;

import android.os.Handler;
import com.ali.auth.third.core.model.Constants;
import java.util.HashMap;
import java.util.Map;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.g;
import mtopsdk.common.util.l;
import mtopsdk.common.util.m;
import mtopsdk.mtop.common.MtopNetworkProp;
import mtopsdk.mtop.common.a.a;
import mtopsdk.mtop.common.d;
import mtopsdk.mtop.common.k;
import mtopsdk.mtop.domain.JsonTypeEnum;
import mtopsdk.mtop.domain.MethodEnum;
import mtopsdk.mtop.domain.MtopRequest;
import mtopsdk.mtop.domain.MtopResponse;
import mtopsdk.mtop.domain.ProtocolEnum;
import mtopsdk.mtop.util.e;
import mtopsdk.mtop.util.h;

public class b {
    private static final String TAG = "mtopsdk.MtopBuilder";
    private String customDomain = null;
    private String fullBaseUrl = null;
    private Handler handler = null;
    public k listener = null;
    public MtopNetworkProp mtopProp = new MtopNetworkProp();
    public MtopRequest request;
    public Object requestContext = null;
    protected h stat = new h();

    public b(Object obj, String str) {
        this.request = mtopsdk.mtop.util.b.a(obj);
        this.mtopProp.ttid = str;
    }

    public b(MtopRequest mtopRequest, String str) {
        this.request = mtopRequest;
        this.mtopProp.ttid = str;
    }

    public b(mtopsdk.mtop.domain.b bVar, String str) {
        this.request = mtopsdk.mtop.util.b.a(bVar);
        this.mtopProp.ttid = str;
    }

    private a createListenerProxy(k kVar) {
        return kVar == null ? new a(new mtopsdk.mtop.common.b()) : kVar instanceof d ? new mtopsdk.mtop.common.a.b(kVar) : new a(kVar);
    }

    private mtopsdk.mtop.a createMtopProxy(k kVar) {
        mtopsdk.mtop.a aVar = new mtopsdk.mtop.a(this.request, this.mtopProp, this.requestContext, kVar);
        if (this.request != null) {
            this.stat.p = this.request.getKey();
        }
        aVar.h = this.stat;
        if (this.customDomain != null) {
            aVar.b(this.customDomain);
        }
        if (this.fullBaseUrl != null) {
            aVar.a(this.fullBaseUrl);
        }
        return aVar;
    }

    private boolean isUseCache() {
        return this.mtopProp.useCache || (this.listener instanceof d);
    }

    private boolean isUseWua() {
        return this.mtopProp.wuaFlag >= 0;
    }

    public b addHttpQueryParameter(String str, String str2) {
        if (!l.b(str) && !l.b(str2)) {
            if (this.mtopProp.queryParameterMap == null) {
                this.mtopProp.queryParameterMap = new HashMap();
            }
            this.mtopProp.queryParameterMap.put(str, str2);
        } else if (m.a(TBSdkLog$LogEnable.DebugEnable)) {
            m.a(TAG, "[addHttpQueryParameter]add HttpQueryParameter error,key=" + str + ",value=" + str2);
        }
        return this;
    }

    public b addListener(k kVar) {
        this.listener = kVar;
        return this;
    }

    public b addMteeUa(String str) {
        addHttpQueryParameter(Constants.UA, str);
        return this;
    }

    public mtopsdk.mtop.common.a asyncRequest() {
        this.stat.a();
        mtopsdk.mtop.a createMtopProxy = createMtopProxy(this.listener);
        if (!g.b() || (!isUseCache() && !isUseWua())) {
            return createMtopProxy.a(this.handler);
        }
        mtopsdk.mtop.common.a aVar = new mtopsdk.mtop.common.a(null, createMtopProxy);
        e.b().submit(new c(this, aVar, createMtopProxy));
        return aVar;
    }

    public b forceRefreshCache() {
        this.mtopProp.forceRefreshCache = true;
        return this;
    }

    public Object getReqContext() {
        return this.requestContext;
    }

    public b handler(Handler handler) {
        this.handler = handler;
        return this;
    }

    public b headers(Map map) {
        if (!(map == null || map.isEmpty())) {
            if (this.mtopProp.requestHeaders != null) {
                this.mtopProp.requestHeaders.putAll(map);
            } else {
                this.mtopProp.requestHeaders = map;
            }
        }
        return this;
    }

    protected void mtopCommitStatData(boolean z) {
        this.stat.a = z;
    }

    public b protocol(ProtocolEnum protocolEnum) {
        if (protocolEnum != null) {
            this.mtopProp.protocol = protocolEnum;
        }
        return this;
    }

    public b reqContext(Object obj) {
        this.requestContext = obj;
        return this;
    }

    public b reqMethod(MethodEnum methodEnum) {
        if (methodEnum != null) {
            this.mtopProp.method = methodEnum;
        }
        return this;
    }

    public b retryTime(int i) {
        this.mtopProp.retryTimes = i;
        return this;
    }

    public b setBizId(int i) {
        this.mtopProp.bizId = i;
        return this;
    }

    public b setCacheControlNoCache() {
        Map map = this.mtopProp.requestHeaders;
        if (map == null) {
            map = new HashMap();
        }
        map.put("cache-control", "no-cache");
        this.mtopProp.requestHeaders = map;
        return this;
    }

    public b setConnectionTimeoutMilliSecond(int i) {
        if (i > 0) {
            this.mtopProp.connTimeout = i;
        }
        return this;
    }

    public b setCustomDomain(String str) {
        if (str != null) {
            this.customDomain = str;
        }
        return this;
    }

    public b setJsonType(JsonTypeEnum jsonTypeEnum) {
        if (jsonTypeEnum != null) {
            addHttpQueryParameter("type", jsonTypeEnum.getJsonType());
        }
        return this;
    }

    public b setReqUserId(String str) {
        this.mtopProp.reqUserId = str;
        return this;
    }

    public b setSocketTimeoutMilliSecond(int i) {
        if (i > 0) {
            this.mtopProp.socketTimeout = i;
        }
        return this;
    }

    public MtopResponse syncRequest() {
        this.stat.a();
        a createListenerProxy = createListenerProxy(this.listener);
        createMtopProxy(createListenerProxy).a(this.handler);
        synchronized (createListenerProxy) {
            try {
                if (createListenerProxy.b == null) {
                    createListenerProxy.wait(120000);
                }
            } catch (Throwable e) {
                m.b(TAG, "[apiCall] error", e);
            }
        }
        MtopResponse mtopResponse = createListenerProxy.b;
        if (createListenerProxy.c != null) {
            this.requestContext = createListenerProxy.c;
        }
        return mtopResponse == null ? new MtopResponse(this.request.getApiName(), this.request.getVersion(), "ANDROID_SYS_MTOP_APICALL_ASYNC_TIMEOUT", "MTOP异步调用超时") : mtopResponse;
    }

    public b ttid(String str) {
        this.mtopProp.ttid = str;
        return this;
    }

    public b useCache() {
        this.mtopProp.useCache = true;
        return this;
    }

    public b useWua() {
        return useWua(0);
    }

    public b useWua(int i) {
        this.mtopProp.wuaFlag = i;
        return this;
    }
}
