package cz.msebera.android.httpclient.cookie;

import cz.msebera.android.httpclient.params.HttpParams;

@Deprecated
public interface CookieSpecFactory {
    CookieSpec newInstance(HttpParams httpParams);
}
