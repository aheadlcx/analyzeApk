package org.apache.commons.httpclient.methods;

import java.io.IOException;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class ExpectContinueMethod extends HttpMethodBase {
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$methods$ExpectContinueMethod;

    protected abstract boolean hasRequestContent();

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$methods$ExpectContinueMethod == null) {
            class$ = class$("org.apache.commons.httpclient.methods.ExpectContinueMethod");
            class$org$apache$commons$httpclient$methods$ExpectContinueMethod = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$methods$ExpectContinueMethod;
        }
        LOG = LogFactory.getLog(class$);
    }

    public ExpectContinueMethod(String str) {
        super(str);
    }

    public boolean getUseExpectHeader() {
        return getParams().getBooleanParameter(HttpMethodParams.USE_EXPECT_CONTINUE, false);
    }

    public void setUseExpectHeader(boolean z) {
        getParams().setBooleanParameter(HttpMethodParams.USE_EXPECT_CONTINUE, z);
    }

    protected void addRequestHeaders(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        LOG.trace("enter ExpectContinueMethod.addRequestHeaders(HttpState, HttpConnection)");
        super.addRequestHeaders(httpState, httpConnection);
        Object obj = getRequestHeader("Expect") != null ? 1 : null;
        if (getParams().isParameterTrue(HttpMethodParams.USE_EXPECT_CONTINUE) && getEffectiveVersion().greaterEquals(HttpVersion.HTTP_1_1) && hasRequestContent()) {
            if (obj == null) {
                setRequestHeader("Expect", "100-continue");
            }
        } else if (obj != null) {
            removeRequestHeader("Expect");
        }
    }
}
