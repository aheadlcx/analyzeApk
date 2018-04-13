package org.apache.commons.httpclient.params;

public class HostParams extends DefaultHttpParams {
    public static final String DEFAULT_HEADERS = "http.default-headers";

    public HostParams(HttpParams httpParams) {
        super(httpParams);
    }

    public void setVirtualHost(String str) {
        setParameter(HttpMethodParams.VIRTUAL_HOST, str);
    }

    public String getVirtualHost() {
        return (String) getParameter(HttpMethodParams.VIRTUAL_HOST);
    }
}
