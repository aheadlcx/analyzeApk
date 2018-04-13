package org.apache.commons.httpclient.methods;

import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OptionsMethod extends HttpMethodBase {
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$methods$OptionsMethod;
    private Vector methodsAllowed = new Vector();

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$methods$OptionsMethod == null) {
            class$ = class$("org.apache.commons.httpclient.methods.OptionsMethod");
            class$org$apache$commons$httpclient$methods$OptionsMethod = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$methods$OptionsMethod;
        }
        LOG = LogFactory.getLog(class$);
    }

    public OptionsMethod(String str) {
        super(str);
    }

    public String getName() {
        return "OPTIONS";
    }

    public boolean isAllowed(String str) {
        checkUsed();
        return this.methodsAllowed.contains(str);
    }

    public Enumeration getAllowedMethods() {
        checkUsed();
        return this.methodsAllowed.elements();
    }

    protected void processResponseHeaders(HttpState httpState, HttpConnection httpConnection) {
        LOG.trace("enter OptionsMethod.processResponseHeaders(HttpState, HttpConnection)");
        NameValuePair responseHeader = getResponseHeader("allow");
        if (responseHeader != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(responseHeader.getValue(), ",");
            while (stringTokenizer.hasMoreElements()) {
                this.methodsAllowed.addElement(stringTokenizer.nextToken().trim().toUpperCase());
            }
        }
    }

    public boolean needContentLength() {
        return false;
    }
}
