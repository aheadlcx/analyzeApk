package cz.msebera.android.httpclient.params;

import cz.msebera.android.httpclient.HttpVersion;

@Deprecated
public class HttpProtocolParamBean extends HttpAbstractParamBean {
    public HttpProtocolParamBean(HttpParams httpParams) {
        super(httpParams);
    }

    public void setHttpElementCharset(String str) {
        HttpProtocolParams.setHttpElementCharset(this.a, str);
    }

    public void setContentCharset(String str) {
        HttpProtocolParams.setContentCharset(this.a, str);
    }

    public void setVersion(HttpVersion httpVersion) {
        HttpProtocolParams.setVersion(this.a, httpVersion);
    }

    public void setUserAgent(String str) {
        HttpProtocolParams.setUserAgent(this.a, str);
    }

    public void setUseExpectContinue(boolean z) {
        HttpProtocolParams.setUseExpectContinue(this.a, z);
    }
}
