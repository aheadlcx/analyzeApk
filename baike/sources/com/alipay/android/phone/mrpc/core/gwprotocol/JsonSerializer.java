package com.alipay.android.phone.mrpc.core.gwprotocol;

import com.alipay.a.a.f;
import com.alipay.android.phone.mrpc.core.RpcException;
import com.baidu.mobstat.Config;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class JsonSerializer extends AbstractSerializer {
    public static final String VERSION = "1.0.0";
    private int c;
    private Object d;

    public JsonSerializer(int i, String str, Object obj) {
        super(str, obj);
        this.c = i;
    }

    public int getId() {
        return this.c;
    }

    public byte[] packet() {
        try {
            List arrayList = new ArrayList();
            if (this.d != null) {
                arrayList.add(new BasicNameValuePair("extParam", f.a(this.d)));
            }
            arrayList.add(new BasicNameValuePair("operationType", this.a));
            arrayList.add(new BasicNameValuePair("id", this.c));
            new StringBuilder("mParams is:").append(this.b);
            arrayList.add(new BasicNameValuePair("requestData", this.b == null ? "[]" : f.a(this.b)));
            return URLEncodedUtils.format(arrayList, "utf-8").getBytes();
        } catch (Throwable e) {
            Throwable th = e;
            throw new RpcException(Integer.valueOf(9), new StringBuilder("request  =").append(this.b).append(Config.TRACE_TODAY_VISIT_SPLIT).append(th).toString() == null ? "" : th.getMessage(), th);
        }
    }

    public void setExtParam(Object obj) {
        this.d = obj;
    }

    public void setId(int i) {
        this.c = i;
    }
}
