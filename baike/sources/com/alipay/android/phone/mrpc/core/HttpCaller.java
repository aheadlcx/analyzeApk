package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

public class HttpCaller extends AbstractRpcCaller {
    private Config g;

    public HttpCaller(Config config, Method method, int i, String str, byte[] bArr, boolean z) {
        super(method, i, str, bArr, "application/x-www-form-urlencoded", z);
        this.g = config;
    }

    private int a(int i) {
        switch (i) {
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            case 7:
                return 8;
            case 8:
                return 15;
            case 9:
                return 16;
            default:
                return i;
        }
    }

    private Transport a() {
        return this.g.getTransport();
    }

    private void a(HttpUrlRequest httpUrlRequest) {
        httpUrlRequest.addHeader(new BasicHeader("uuid", UUID.randomUUID().toString()));
        List<Header> headers = this.g.getRpcParams().getHeaders();
        if (headers != null && !headers.isEmpty()) {
            for (Header addHeader : headers) {
                httpUrlRequest.addHeader(addHeader);
            }
        }
    }

    public Object call() {
        Throwable e;
        HttpUrlRequest httpUrlRequest = new HttpUrlRequest(this.g.getUrl());
        httpUrlRequest.setReqData(this.b);
        httpUrlRequest.setContentType(this.e);
        httpUrlRequest.setResetCookie(this.f);
        httpUrlRequest.addTags("id", String.valueOf(this.d));
        httpUrlRequest.addTags("operationType", this.c);
        httpUrlRequest.addTags("gzip", String.valueOf(this.g.isGzip()));
        a(httpUrlRequest);
        new StringBuilder("threadid = ").append(Thread.currentThread().getId()).append("; ").append(httpUrlRequest.toString());
        try {
            Response response = (Response) a().execute(httpUrlRequest).get();
            if (response != null) {
                return response.getResData();
            }
            throw new RpcException(Integer.valueOf(9), "response is null");
        } catch (Throwable e2) {
            throw new RpcException(Integer.valueOf(13), "", e2);
        } catch (Throwable e22) {
            Throwable th = e22;
            e22 = th.getCause();
            if (e22 == null || !(e22 instanceof HttpException)) {
                throw new RpcException(Integer.valueOf(9), "", th);
            }
            HttpException httpException = (HttpException) e22;
            throw new RpcException(Integer.valueOf(a(httpException.getCode())), httpException.getMsg());
        } catch (Throwable e222) {
            throw new RpcException(Integer.valueOf(13), "", e222);
        }
    }
}
