package mtopsdk.mtop.util;

import com.alibaba.wireless.security.SecExceptionCode;
import com.taobao.tao.remotebusiness.listener.c;
import mtopsdk.common.util.m;
import mtopsdk.mtop.a;
import mtopsdk.mtop.a.g;
import mtopsdk.mtop.domain.MtopResponse;

public class l {
    public static MtopResponse a(MtopResponse mtopResponse, a aVar) {
        a(mtopResponse);
        aVar.e.correctTimeStamp = true;
        return aVar.a();
    }

    public static Result a(MtopResponse mtopResponse, MtopResponse mtopResponse2) {
        Result result = new Result(mtopResponse);
        if (mtopResponse.getResponseCode() != 304 || mtopResponse2 == null) {
            result.setSuccess(false);
        } else {
            result.setModel(mtopResponse2);
        }
        return result;
    }

    private static void a(MtopResponse mtopResponse) {
        if (mtopResponse != null && mtopResponse.getHeaderFields() != null) {
            try {
                String a = c.a(mtopResponse.getHeaderFields(), "x-systime");
                if (mtopsdk.common.util.l.a(a)) {
                    mtopsdk.xstate.a.a("t_offset", String.valueOf(Long.parseLong(a) - (System.currentTimeMillis() / 1000)));
                }
            } catch (Throwable e) {
                m.b("mtopsdk.ResponseHandlerUtil", "[computeTimeOffset]parse systime from mtop response data error", e);
            }
        }
    }

    public static Result b(MtopResponse mtopResponse, a aVar) {
        Result result = new Result(mtopResponse);
        int responseCode = mtopResponse.getResponseCode();
        if (responseCode == 420 || responseCode == SecExceptionCode.SEC_ERROR_DYN_ENC_UNKNOWN_ERROR || responseCode == SecExceptionCode.SEC_ERROR_DYN_STORE_UNKNOWN_ERROR) {
            if (aVar != null) {
                mtopsdk.mtop.antiattack.c.b(aVar.d().getKey(), g.a());
            }
            mtopResponse.setRetCode("ANDROID_SYS_API_FLOW_LIMIT_LOCKED");
            mtopResponse.setRetMsg("哎哟喂,被挤爆啦,请稍后重试");
        } else if (responseCode == 419) {
            mtopResponse.setRetCode("ANDROID_SYS_API_41X_ANTI_ATTACK");
            mtopResponse.setRetMsg("哎哟喂,被挤爆啦,请稍后重试!");
        } else {
            result.setSuccess(false);
        }
        return result;
    }
}
