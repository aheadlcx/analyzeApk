package org.apache.commons.httpclient.params;

import java.io.Serializable;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DefaultHttpParams implements Serializable, Cloneable, HttpParams {
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$params$DefaultHttpParams;
    private static HttpParamsFactory httpParamsFactory = new DefaultHttpParamsFactory();
    private HttpParams defaults;
    private HashMap parameters;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$params$DefaultHttpParams == null) {
            class$ = class$("org.apache.commons.httpclient.params.DefaultHttpParams");
            class$org$apache$commons$httpclient$params$DefaultHttpParams = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$params$DefaultHttpParams;
        }
        LOG = LogFactory.getLog(class$);
    }

    public static HttpParams getDefaultParams() {
        return httpParamsFactory.getDefaultParams();
    }

    public static void setHttpParamsFactory(HttpParamsFactory httpParamsFactory) {
        if (httpParamsFactory == null) {
            throw new IllegalArgumentException("httpParamsFactory may not be null");
        }
        httpParamsFactory = httpParamsFactory;
    }

    public DefaultHttpParams(HttpParams httpParams) {
        this.defaults = null;
        this.parameters = null;
        this.defaults = httpParams;
    }

    public DefaultHttpParams() {
        this(getDefaultParams());
    }

    public synchronized HttpParams getDefaults() {
        return this.defaults;
    }

    public synchronized void setDefaults(HttpParams httpParams) {
        this.defaults = httpParams;
    }

    public synchronized Object getParameter(String str) {
        Object obj;
        if (this.parameters != null) {
            obj = this.parameters.get(str);
        } else {
            obj = null;
        }
        if (obj == null) {
            obj = this.defaults != null ? this.defaults.getParameter(str) : null;
        }
        return obj;
    }

    public synchronized void setParameter(String str, Object obj) {
        if (this.parameters == null) {
            this.parameters = new HashMap();
        }
        this.parameters.put(str, obj);
        if (LOG.isDebugEnabled()) {
            LOG.debug(new StringBuffer().append("Set parameter ").append(str).append(" = ").append(obj).toString());
        }
    }

    public synchronized void setParameters(String[] strArr, Object obj) {
        for (String parameter : strArr) {
            setParameter(parameter, obj);
        }
    }

    public long getLongParameter(String str, long j) {
        Object parameter = getParameter(str);
        return parameter == null ? j : ((Long) parameter).longValue();
    }

    public void setLongParameter(String str, long j) {
        setParameter(str, new Long(j));
    }

    public int getIntParameter(String str, int i) {
        Object parameter = getParameter(str);
        return parameter == null ? i : ((Integer) parameter).intValue();
    }

    public void setIntParameter(String str, int i) {
        setParameter(str, new Integer(i));
    }

    public double getDoubleParameter(String str, double d) {
        Object parameter = getParameter(str);
        return parameter == null ? d : ((Double) parameter).doubleValue();
    }

    public void setDoubleParameter(String str, double d) {
        setParameter(str, new Double(d));
    }

    public boolean getBooleanParameter(String str, boolean z) {
        Object parameter = getParameter(str);
        return parameter == null ? z : ((Boolean) parameter).booleanValue();
    }

    public void setBooleanParameter(String str, boolean z) {
        setParameter(str, new Boolean(z));
    }

    public boolean isParameterSet(String str) {
        return getParameter(str) != null;
    }

    public boolean isParameterSetLocally(String str) {
        return (this.parameters == null || this.parameters.get(str) == null) ? false : true;
    }

    public boolean isParameterTrue(String str) {
        return getBooleanParameter(str, false);
    }

    public boolean isParameterFalse(String str) {
        return !getBooleanParameter(str, false);
    }

    public void clear() {
        this.parameters = null;
    }

    public Object clone() throws CloneNotSupportedException {
        DefaultHttpParams defaultHttpParams = (DefaultHttpParams) super.clone();
        if (this.parameters != null) {
            defaultHttpParams.parameters = (HashMap) this.parameters.clone();
        }
        defaultHttpParams.setDefaults(this.defaults);
        return defaultHttpParams;
    }
}
