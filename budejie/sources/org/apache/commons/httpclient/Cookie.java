package org.apache.commons.httpclient;

import java.io.Serializable;
import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.util.LangUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cookie extends NameValuePair implements Serializable, Comparator {
    private static final Log LOG;
    private static final RuleBasedCollator STRING_COLLATOR = ((RuleBasedCollator) Collator.getInstance(new Locale("en", "US", "")));
    static Class class$org$apache$commons$httpclient$Cookie;
    private String cookieComment;
    private String cookieDomain;
    private Date cookieExpiryDate;
    private String cookiePath;
    private int cookieVersion;
    private boolean hasDomainAttribute;
    private boolean hasPathAttribute;
    private boolean isSecure;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public Cookie() {
        this(null, "noname", null, null, null, false);
    }

    public Cookie(String str, String str2, String str3) {
        this(str, str2, str3, null, null, false);
    }

    public Cookie(String str, String str2, String str3, String str4, Date date, boolean z) {
        super(str2, str3);
        this.hasPathAttribute = false;
        this.hasDomainAttribute = false;
        this.cookieVersion = 0;
        LOG.trace("enter Cookie(String, String, String, String, Date, boolean)");
        if (str2 == null) {
            throw new IllegalArgumentException("Cookie name may not be null");
        } else if (str2.trim().equals("")) {
            throw new IllegalArgumentException("Cookie name may not be blank");
        } else {
            setPath(str4);
            setDomain(str);
            setExpiryDate(date);
            setSecure(z);
        }
    }

    public Cookie(String str, String str2, String str3, String str4, int i, boolean z) {
        this(str, str2, str3, str4, null, z);
        if (i < -1) {
            throw new IllegalArgumentException(new StringBuffer().append("Invalid max age:  ").append(Integer.toString(i)).toString());
        } else if (i >= 0) {
            setExpiryDate(new Date(System.currentTimeMillis() + (((long) i) * 1000)));
        }
    }

    public String getComment() {
        return this.cookieComment;
    }

    public void setComment(String str) {
        this.cookieComment = str;
    }

    public Date getExpiryDate() {
        return this.cookieExpiryDate;
    }

    public void setExpiryDate(Date date) {
        this.cookieExpiryDate = date;
    }

    public boolean isPersistent() {
        return this.cookieExpiryDate != null;
    }

    public String getDomain() {
        return this.cookieDomain;
    }

    public void setDomain(String str) {
        if (str != null) {
            int indexOf = str.indexOf(":");
            if (indexOf != -1) {
                str = str.substring(0, indexOf);
            }
            this.cookieDomain = str.toLowerCase();
        }
    }

    public String getPath() {
        return this.cookiePath;
    }

    public void setPath(String str) {
        this.cookiePath = str;
    }

    public boolean getSecure() {
        return this.isSecure;
    }

    public void setSecure(boolean z) {
        this.isSecure = z;
    }

    public int getVersion() {
        return this.cookieVersion;
    }

    public void setVersion(int i) {
        this.cookieVersion = i;
    }

    public boolean isExpired() {
        return this.cookieExpiryDate != null && this.cookieExpiryDate.getTime() <= System.currentTimeMillis();
    }

    public boolean isExpired(Date date) {
        return this.cookieExpiryDate != null && this.cookieExpiryDate.getTime() <= date.getTime();
    }

    public void setPathAttributeSpecified(boolean z) {
        this.hasPathAttribute = z;
    }

    public boolean isPathAttributeSpecified() {
        return this.hasPathAttribute;
    }

    public void setDomainAttributeSpecified(boolean z) {
        this.hasDomainAttribute = z;
    }

    public boolean isDomainAttributeSpecified() {
        return this.hasDomainAttribute;
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, getName()), this.cookieDomain), this.cookiePath);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cookie)) {
            return false;
        }
        Cookie cookie = (Cookie) obj;
        if (!(LangUtils.equals(getName(), cookie.getName()) && LangUtils.equals(this.cookieDomain, cookie.cookieDomain) && LangUtils.equals(this.cookiePath, cookie.cookiePath))) {
            z = false;
        }
        return z;
    }

    public String toExternalForm() {
        CookieSpec defaultSpec;
        if (getVersion() > 0) {
            defaultSpec = CookiePolicy.getDefaultSpec();
        } else {
            defaultSpec = CookiePolicy.getCookieSpec(CookiePolicy.NETSCAPE);
        }
        return defaultSpec.formatCookie(this);
    }

    public int compare(Object obj, Object obj2) {
        LOG.trace("enter Cookie.compare(Object, Object)");
        if (!(obj instanceof Cookie)) {
            throw new ClassCastException(obj.getClass().getName());
        } else if (obj2 instanceof Cookie) {
            Cookie cookie = (Cookie) obj;
            Cookie cookie2 = (Cookie) obj2;
            if (cookie.getPath() == null && cookie2.getPath() == null) {
                return 0;
            }
            if (cookie.getPath() == null) {
                if (cookie2.getPath().equals("/")) {
                    return 0;
                }
                return -1;
            } else if (cookie2.getPath() != null) {
                return STRING_COLLATOR.compare(cookie.getPath(), cookie2.getPath());
            } else {
                if (cookie.getPath().equals("/")) {
                    return 0;
                }
                return 1;
            }
        } else {
            throw new ClassCastException(obj2.getClass().getName());
        }
    }

    public String toString() {
        return toExternalForm();
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$Cookie == null) {
            class$ = class$("org.apache.commons.httpclient.Cookie");
            class$org$apache$commons$httpclient$Cookie = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$Cookie;
        }
        LOG = LogFactory.getLog(class$);
    }
}
