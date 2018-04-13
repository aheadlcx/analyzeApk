package org.apache.commons.httpclient.methods;

import java.io.IOException;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.ProtocolException;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HeadMethod extends HttpMethodBase {
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$methods$HeadMethod;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$methods$HeadMethod == null) {
            class$ = class$("org.apache.commons.httpclient.methods.HeadMethod");
            class$org$apache$commons$httpclient$methods$HeadMethod = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$methods$HeadMethod;
        }
        LOG = LogFactory.getLog(class$);
    }

    public HeadMethod() {
        setFollowRedirects(true);
    }

    public HeadMethod(String str) {
        super(str);
        setFollowRedirects(true);
    }

    public String getName() {
        return "HEAD";
    }

    public void recycle() {
        super.recycle();
        setFollowRedirects(true);
    }

    protected void readResponseBody(HttpState httpState, HttpConnection httpConnection) throws HttpException, IOException {
        LOG.trace("enter HeadMethod.readResponseBody(HttpState, HttpConnection)");
        int intParameter = getParams().getIntParameter(HttpMethodParams.HEAD_BODY_CHECK_TIMEOUT, -1);
        if (intParameter < 0) {
            responseBodyConsumed();
            return;
        }
        boolean isResponseAvailable;
        if (LOG.isDebugEnabled()) {
            LOG.debug(new StringBuffer().append("Check for non-compliant response body. Timeout in ").append(intParameter).append(" ms").toString());
        }
        try {
            isResponseAvailable = httpConnection.isResponseAvailable(intParameter);
        } catch (Throwable e) {
            LOG.debug("An IOException occurred while testing if a response was available, we will assume one is not.", e);
            isResponseAvailable = false;
        }
        if (!isResponseAvailable) {
            return;
        }
        if (getParams().isParameterTrue(HttpMethodParams.REJECT_HEAD_BODY)) {
            throw new ProtocolException("Body content may not be sent in response to HTTP HEAD request");
        }
        LOG.warn("Body content returned in response to HTTP HEAD");
        super.readResponseBody(httpState, httpConnection);
    }

    public int getBodyCheckTimeout() {
        return getParams().getIntParameter(HttpMethodParams.HEAD_BODY_CHECK_TIMEOUT, -1);
    }

    public void setBodyCheckTimeout(int i) {
        getParams().setIntParameter(HttpMethodParams.HEAD_BODY_CHECK_TIMEOUT, i);
    }
}
