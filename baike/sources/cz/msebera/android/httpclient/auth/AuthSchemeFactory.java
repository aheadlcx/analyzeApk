package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.params.HttpParams;

@Deprecated
public interface AuthSchemeFactory {
    AuthScheme newInstance(HttpParams httpParams);
}
