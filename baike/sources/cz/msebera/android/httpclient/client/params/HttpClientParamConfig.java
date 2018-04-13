package cz.msebera.android.httpclient.client.params;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.auth.params.AuthPNames;
import cz.msebera.android.httpclient.client.config.RequestConfig;
import cz.msebera.android.httpclient.client.config.RequestConfig.Builder;
import cz.msebera.android.httpclient.conn.params.ConnRoutePNames;
import cz.msebera.android.httpclient.params.CoreConnectionPNames;
import cz.msebera.android.httpclient.params.CoreProtocolPNames;
import cz.msebera.android.httpclient.params.HttpParams;
import java.net.InetAddress;
import java.util.Collection;

@Deprecated
public final class HttpClientParamConfig {
    private HttpClientParamConfig() {
    }

    public static RequestConfig getRequestConfig(HttpParams httpParams) {
        boolean z;
        Builder redirectsEnabled = RequestConfig.custom().setSocketTimeout(httpParams.getIntParameter(CoreConnectionPNames.SO_TIMEOUT, 0)).setStaleConnectionCheckEnabled(httpParams.getBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, true)).setConnectTimeout(httpParams.getIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 0)).setExpectContinueEnabled(httpParams.getBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false)).setProxy((HttpHost) httpParams.getParameter(ConnRoutePNames.DEFAULT_PROXY)).setLocalAddress((InetAddress) httpParams.getParameter(ConnRoutePNames.LOCAL_ADDRESS)).setProxyPreferredAuthSchemes((Collection) httpParams.getParameter(AuthPNames.PROXY_AUTH_PREF)).setTargetPreferredAuthSchemes((Collection) httpParams.getParameter(AuthPNames.TARGET_AUTH_PREF)).setAuthenticationEnabled(httpParams.getBooleanParameter(ClientPNames.HANDLE_AUTHENTICATION, true)).setCircularRedirectsAllowed(httpParams.getBooleanParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, false)).setConnectionRequestTimeout((int) httpParams.getLongParameter("http.conn-manager.timeout", 0)).setCookieSpec((String) httpParams.getParameter(ClientPNames.COOKIE_POLICY)).setMaxRedirects(httpParams.getIntParameter(ClientPNames.MAX_REDIRECTS, 50)).setRedirectsEnabled(httpParams.getBooleanParameter(ClientPNames.HANDLE_REDIRECTS, true));
        if (httpParams.getBooleanParameter(ClientPNames.REJECT_RELATIVE_REDIRECT, false)) {
            z = false;
        } else {
            z = true;
        }
        return redirectsEnabled.setRelativeRedirectsAllowed(z).build();
    }
}
