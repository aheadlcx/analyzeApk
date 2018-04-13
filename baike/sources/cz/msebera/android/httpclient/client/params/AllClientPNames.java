package cz.msebera.android.httpclient.client.params;

import cz.msebera.android.httpclient.auth.params.AuthPNames;
import cz.msebera.android.httpclient.conn.params.ConnConnectionPNames;
import cz.msebera.android.httpclient.conn.params.ConnManagerPNames;
import cz.msebera.android.httpclient.conn.params.ConnRoutePNames;
import cz.msebera.android.httpclient.cookie.params.CookieSpecPNames;
import cz.msebera.android.httpclient.params.CoreConnectionPNames;
import cz.msebera.android.httpclient.params.CoreProtocolPNames;

@Deprecated
public interface AllClientPNames extends AuthPNames, ClientPNames, ConnConnectionPNames, ConnManagerPNames, ConnRoutePNames, CookieSpecPNames, CoreConnectionPNames, CoreProtocolPNames {
}
