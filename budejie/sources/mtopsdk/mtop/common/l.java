package mtopsdk.mtop.common;

import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.taobao.tao.remotebusiness.listener.c;
import java.io.IOException;
import java.util.Map;
import mtopsdk.a.b.e;
import mtopsdk.a.b.g;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.m;
import mtopsdk.mtop.a;
import mtopsdk.mtop.domain.MtopResponse;
import mtopsdk.mtop.util.Result;

public class l {
    public static MtopResponse a(e eVar, MtopResponse mtopResponse, a aVar) {
        if (eVar != null) {
            return a(null, mtopResponse, aVar, new m(eVar.a(), eVar.b(), eVar.c()));
        }
        MtopResponse mtopResponse2 = new MtopResponse("ANDROID_SYS_NETWORK_ERROR", UserTrackerConstants.EM_NETWORK_ERROR);
        if (aVar == null) {
            return mtopResponse2;
        }
        mtopResponse2.setApi(aVar.d.getApiName());
        mtopResponse2.setV(aVar.d.getVersion());
        return mtopResponse2;
    }

    public static MtopResponse a(MtopResponse mtopResponse) {
        if (!(mtopResponse == null || mtopResponse.getHeaderFields() == null)) {
            String a = c.a(mtopResponse.getHeaderFields(), "x-retcode");
            if (mtopsdk.common.util.l.a(a)) {
                mtopResponse.setRetCode(a);
            } else {
                mtopResponse.parseJsonByte();
            }
        }
        return mtopResponse;
    }

    public static MtopResponse a(MtopResponse mtopResponse, MtopResponse mtopResponse2, a aVar, m mVar) {
        String g;
        MtopResponse mtopResponse3 = mtopResponse == null ? new MtopResponse() : mtopResponse;
        if (aVar != null) {
            mtopResponse3.setApi(aVar.d.getApiName());
            mtopResponse3.setV(aVar.d.getVersion());
            g = aVar.h.g();
        } else {
            g = null;
        }
        if (mVar == null) {
            m.d("mtopsdk.MtopNetworkResultParser", g, "[parseNetworkRlt]network response is invalid");
            mtopResponse3.setRetCode("ANDROID_SYS_NETWORK_ERROR");
            mtopResponse3.setRetMsg(UserTrackerConstants.EM_NETWORK_ERROR);
            return mtopResponse3;
        }
        int i = mVar.a;
        Map map = mVar.b;
        mtopResponse3.setResponseCode(i);
        mtopResponse3.setHeaderFields(map);
        g gVar = mVar.c;
        if (gVar != null) {
            try {
                mtopResponse3.setBytedata(gVar.c());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (i < 0) {
            if (-200 == i) {
                mtopResponse3.setRetCode("ANDROID_SYS_NO_NETWORK");
                mtopResponse3.setRetMsg("无网络");
            } else {
                mtopResponse3.setRetCode("ANDROID_SYS_NETWORK_ERROR");
                mtopResponse3.setRetMsg(UserTrackerConstants.EM_NETWORK_ERROR);
            }
            if (!m.a(TBSdkLog$LogEnable.ErrorEnable)) {
                return mtopResponse3;
            }
            StringBuilder stringBuilder = new StringBuilder(128);
            stringBuilder.append("[parseNetworkRlt] api=").append(mtopResponse3.getApi());
            stringBuilder.append(",v=").append(mtopResponse3.getV());
            stringBuilder.append(",retCode =").append(mtopResponse3.getRetCode());
            stringBuilder.append(",responseCode =").append(i);
            stringBuilder.append(",responseHeader=").append(map);
            m.d("mtopsdk.MtopNetworkResultParser", g, stringBuilder.toString());
            return mtopResponse3;
        }
        mtopsdk.mtop.unit.a.a(map, g);
        Result b = mtopsdk.mtop.util.l.b(mtopResponse3, aVar);
        if (b != null && b.isSuccess()) {
            return (MtopResponse) b.getModel();
        }
        b = mtopsdk.mtop.util.l.a(mtopResponse3, mtopResponse2);
        if (b != null && b.isSuccess()) {
            return (MtopResponse) b.getModel();
        }
        if (mtopResponse3.getBytedata() == null) {
            mtopResponse3.setRetCode("ANDROID_SYS_JSONDATA_BLANK");
            mtopResponse3.setRetMsg("返回JSONDATA为空");
            return mtopResponse3;
        }
        mtopResponse3 = a(mtopResponse3);
        return (!mtopResponse3.isExpiredRequest() || aVar == null || aVar.e().correctTimeStamp) ? mtopResponse3 : mtopsdk.mtop.util.l.a(mtopResponse3, aVar);
    }
}
