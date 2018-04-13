package org.apache.commons.httpclient;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpState {
    private static final Log LOG;
    public static final String PREEMPTIVE_DEFAULT = "false";
    public static final String PREEMPTIVE_PROPERTY = "httpclient.authentication.preemptive";
    static Class class$org$apache$commons$httpclient$HttpState;
    private int cookiePolicy = -1;
    private ArrayList cookies = new ArrayList();
    private HashMap credMap = new HashMap();
    private boolean preemptive = false;
    private HashMap proxyCred = new HashMap();

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$HttpState == null) {
            class$ = class$("org.apache.commons.httpclient.HttpState");
            class$org$apache$commons$httpclient$HttpState = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$HttpState;
        }
        LOG = LogFactory.getLog(class$);
    }

    public synchronized void addCookie(Cookie cookie) {
        LOG.trace("enter HttpState.addCookie(Cookie)");
        if (cookie != null) {
            Iterator it = this.cookies.iterator();
            while (it.hasNext()) {
                if (cookie.equals((Cookie) it.next())) {
                    it.remove();
                    break;
                }
            }
            if (!cookie.isExpired()) {
                this.cookies.add(cookie);
            }
        }
    }

    public synchronized void addCookies(Cookie[] cookieArr) {
        LOG.trace("enter HttpState.addCookies(Cookie[])");
        if (cookieArr != null) {
            for (Cookie addCookie : cookieArr) {
                addCookie(addCookie);
            }
        }
    }

    public synchronized Cookie[] getCookies() {
        LOG.trace("enter HttpState.getCookies()");
        return (Cookie[]) this.cookies.toArray(new Cookie[this.cookies.size()]);
    }

    public synchronized Cookie[] getCookies(String str, int i, String str2, boolean z) {
        ArrayList arrayList;
        LOG.trace("enter HttpState.getCookies(String, int, String, boolean)");
        CookieSpec defaultSpec = CookiePolicy.getDefaultSpec();
        arrayList = new ArrayList(this.cookies.size());
        int size = this.cookies.size();
        for (int i2 = 0; i2 < size; i2++) {
            Cookie cookie = (Cookie) this.cookies.get(i2);
            if (defaultSpec.match(str, i, str2, z, cookie)) {
                arrayList.add(cookie);
            }
        }
        return (Cookie[]) arrayList.toArray(new Cookie[arrayList.size()]);
    }

    public synchronized boolean purgeExpiredCookies() {
        LOG.trace("enter HttpState.purgeExpiredCookies()");
        return purgeExpiredCookies(new Date());
    }

    public synchronized boolean purgeExpiredCookies(Date date) {
        boolean z;
        LOG.trace("enter HttpState.purgeExpiredCookies(Date)");
        Iterator it = this.cookies.iterator();
        z = false;
        while (it.hasNext()) {
            if (((Cookie) it.next()).isExpired(date)) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public int getCookiePolicy() {
        return this.cookiePolicy;
    }

    public void setAuthenticationPreemptive(boolean z) {
        this.preemptive = z;
    }

    public boolean isAuthenticationPreemptive() {
        return this.preemptive;
    }

    public void setCookiePolicy(int i) {
        this.cookiePolicy = i;
    }

    public synchronized void setCredentials(String str, String str2, Credentials credentials) {
        LOG.trace("enter HttpState.setCredentials(String, String, Credentials)");
        this.credMap.put(new AuthScope(str2, -1, str, AuthScope.ANY_SCHEME), credentials);
    }

    public synchronized void setCredentials(AuthScope authScope, Credentials credentials) {
        if (authScope == null) {
            throw new IllegalArgumentException("Authentication scope may not be null");
        }
        LOG.trace("enter HttpState.setCredentials(AuthScope, Credentials)");
        this.credMap.put(authScope, credentials);
    }

    private static Credentials matchCredentials(HashMap hashMap, AuthScope authScope) {
        Credentials credentials = (Credentials) hashMap.get(authScope);
        if (credentials != null) {
            return credentials;
        }
        int i = -1;
        Object obj = null;
        for (AuthScope authScope2 : hashMap.keySet()) {
            int match = authScope.match(authScope2);
            if (match > i) {
                obj = authScope2;
                i = match;
            }
        }
        if (obj != null) {
            return (Credentials) hashMap.get(obj);
        }
        return credentials;
    }

    public synchronized Credentials getCredentials(String str, String str2) {
        LOG.trace("enter HttpState.getCredentials(String, String");
        return matchCredentials(this.credMap, new AuthScope(str2, -1, str, AuthScope.ANY_SCHEME));
    }

    public synchronized Credentials getCredentials(AuthScope authScope) {
        if (authScope == null) {
            throw new IllegalArgumentException("Authentication scope may not be null");
        }
        LOG.trace("enter HttpState.getCredentials(AuthScope)");
        return matchCredentials(this.credMap, authScope);
    }

    public synchronized void setProxyCredentials(String str, String str2, Credentials credentials) {
        LOG.trace("enter HttpState.setProxyCredentials(String, String, Credentials");
        this.proxyCred.put(new AuthScope(str2, -1, str, AuthScope.ANY_SCHEME), credentials);
    }

    public synchronized void setProxyCredentials(AuthScope authScope, Credentials credentials) {
        if (authScope == null) {
            throw new IllegalArgumentException("Authentication scope may not be null");
        }
        LOG.trace("enter HttpState.setProxyCredentials(AuthScope, Credentials)");
        this.proxyCred.put(authScope, credentials);
    }

    public synchronized Credentials getProxyCredentials(String str, String str2) {
        LOG.trace("enter HttpState.getCredentials(String, String");
        return matchCredentials(this.proxyCred, new AuthScope(str2, -1, str, AuthScope.ANY_SCHEME));
    }

    public synchronized Credentials getProxyCredentials(AuthScope authScope) {
        if (authScope == null) {
            throw new IllegalArgumentException("Authentication scope may not be null");
        }
        LOG.trace("enter HttpState.getProxyCredentials(AuthScope)");
        return matchCredentials(this.proxyCred, authScope);
    }

    public synchronized String toString() {
        StringBuffer stringBuffer;
        stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append(getCredentialsStringRepresentation(this.proxyCred));
        stringBuffer.append(" | ");
        stringBuffer.append(getCredentialsStringRepresentation(this.credMap));
        stringBuffer.append(" | ");
        stringBuffer.append(getCookiesStringRepresentation(this.cookies));
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    private static String getCredentialsStringRepresentation(Map map) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Object next : map.keySet()) {
            Credentials credentials = (Credentials) map.get(next);
            if (stringBuffer.length() > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(next);
            stringBuffer.append("#");
            stringBuffer.append(credentials.toString());
        }
        return stringBuffer.toString();
    }

    private static String getCookiesStringRepresentation(List list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Cookie cookie : list) {
            if (stringBuffer.length() > 0) {
                stringBuffer.append("#");
            }
            stringBuffer.append(cookie.toExternalForm());
        }
        return stringBuffer.toString();
    }

    public void clearCredentials() {
        this.credMap.clear();
    }

    public void clearProxyCredentials() {
        this.proxyCred.clear();
    }

    public synchronized void clearCookies() {
        this.cookies.clear();
    }

    public void clear() {
        clearCookies();
        clearCredentials();
        clearProxyCredentials();
    }
}
