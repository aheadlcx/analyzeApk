package mtopsdk.mtop.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import mtopsdk.common.util.m;
import mtopsdk.mtop.domain.MtopRequest;
import mtopsdk.mtop.domain.MtopResponse;
import mtopsdk.mtop.domain.a;

public class b {
    public static MtopRequest a(Object obj) {
        return obj == null ? null : k.a(obj);
    }

    public static MtopRequest a(mtopsdk.mtop.domain.b bVar) {
        return bVar == null ? null : k.a(bVar);
    }

    public static a a(MtopResponse mtopResponse, Class cls) {
        if (cls != null && mtopResponse != null) {
            return a(mtopResponse.getBytedata(), cls);
        }
        m.d("mtopsdk.MtopConvert", "outClass is null or response is null");
        return null;
    }

    public static a a(byte[] bArr, Class cls) {
        if (cls == null || bArr == null || bArr.length == 0) {
            m.d("mtopsdk.MtopConvert", "[jsonToOutputDO]outClass is null or jsondata is blank");
            return null;
        }
        try {
            return (a) JSON.parseObject(bArr, cls, new Feature[0]);
        } catch (Throwable th) {
            m.b("mtopsdk.MtopConvert", "[jsonToOutputDO]invoke JSON.parseObject error ---Class=" + cls.getName(), th);
            return null;
        }
    }
}
