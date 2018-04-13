package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.httpclient.auth.AuthState;
import org.apache.commons.httpclient.params.HttpMethodParams;

public interface HttpMethod {
    void abort();

    void addRequestHeader(String str, String str2);

    void addRequestHeader(Header header);

    void addResponseFooter(Header header);

    int execute(HttpState httpState, HttpConnection httpConnection) throws HttpException, IOException;

    boolean getDoAuthentication();

    boolean getFollowRedirects();

    AuthState getHostAuthState();

    HostConfiguration getHostConfiguration();

    String getName();

    HttpMethodParams getParams();

    String getPath();

    AuthState getProxyAuthState();

    String getQueryString();

    Header getRequestHeader(String str);

    Header[] getRequestHeaders();

    Header[] getRequestHeaders(String str);

    byte[] getResponseBody() throws IOException;

    InputStream getResponseBodyAsStream() throws IOException;

    String getResponseBodyAsString() throws IOException;

    Header getResponseFooter(String str);

    Header[] getResponseFooters();

    Header getResponseHeader(String str);

    Header[] getResponseHeaders();

    Header[] getResponseHeaders(String str);

    int getStatusCode();

    StatusLine getStatusLine();

    String getStatusText();

    URI getURI() throws URIException;

    boolean hasBeenUsed();

    boolean isRequestSent();

    boolean isStrictMode();

    void recycle();

    void releaseConnection();

    void removeRequestHeader(String str);

    void removeRequestHeader(Header header);

    void setDoAuthentication(boolean z);

    void setFollowRedirects(boolean z);

    void setParams(HttpMethodParams httpMethodParams);

    void setPath(String str);

    void setQueryString(String str);

    void setQueryString(NameValuePair[] nameValuePairArr);

    void setRequestHeader(String str, String str2);

    void setRequestHeader(Header header);

    void setStrictMode(boolean z);

    void setURI(URI uri) throws URIException;

    boolean validate();
}
