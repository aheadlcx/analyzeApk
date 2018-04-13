package org.apache.commons.httpclient.methods;

import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GetMethod extends HttpMethodBase {
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$methods$GetMethod;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$methods$GetMethod == null) {
            class$ = class$("org.apache.commons.httpclient.methods.GetMethod");
            class$org$apache$commons$httpclient$methods$GetMethod = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$methods$GetMethod;
        }
        LOG = LogFactory.getLog(class$);
    }

    public GetMethod() {
        setFollowRedirects(true);
    }

    public GetMethod(String str) {
        super(str);
        LOG.trace("enter GetMethod(String)");
        setFollowRedirects(true);
    }

    public String getName() {
        return "GET";
    }

    public void recycle() {
        LOG.trace("enter GetMethod.recycle()");
        super.recycle();
        setFollowRedirects(true);
    }
}
