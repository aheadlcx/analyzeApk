package org.apache.commons.httpclient;

import cn.v6.sixrooms.room.gift.BoxingVoteBean;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConnectMethod extends HttpMethodBase {
    private static final Log LOG;
    public static final String NAME = "CONNECT";
    static Class class$org$apache$commons$httpclient$ConnectMethod;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public ConnectMethod() {
        LOG.trace("enter ConnectMethod()");
    }

    public ConnectMethod(HttpMethod httpMethod) {
        LOG.trace("enter ConnectMethod(HttpMethod)");
    }

    public String getName() {
        return NAME;
    }

    protected void addCookieRequestHeader(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
    }

    protected void addRequestHeaders(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        LOG.trace("enter ConnectMethod.addRequestHeaders(HttpState, HttpConnection)");
        addUserAgentRequestHeader(httpState, httpConnection);
        addHostRequestHeader(httpState, httpConnection);
        addProxyConnectionHeader(httpState, httpConnection);
    }

    public int execute(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        LOG.trace("enter ConnectMethod.execute(HttpState, HttpConnection)");
        int execute = super.execute(httpState, httpConnection);
        if (LOG.isDebugEnabled()) {
            LOG.debug(new StringBuffer().append("CONNECT status code ").append(execute).toString());
        }
        return execute;
    }

    protected void writeRequestLine(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        int port = httpConnection.getPort();
        if (port == -1) {
            port = httpConnection.getProtocol().getDefaultPort();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getName());
        stringBuffer.append(' ');
        stringBuffer.append(httpConnection.getHost());
        if (port > -1) {
            stringBuffer.append(':');
            stringBuffer.append(port);
        }
        stringBuffer.append(" ");
        stringBuffer.append(getEffectiveVersion());
        String stringBuffer2 = stringBuffer.toString();
        httpConnection.printLine(stringBuffer2, getParams().getHttpElementCharset());
        if (Wire.HEADER_WIRE.enabled()) {
            Wire.HEADER_WIRE.output(stringBuffer2);
        }
    }

    protected boolean shouldCloseConnection(HttpConnection httpConnection) {
        if (getStatusCode() != 200) {
            return super.shouldCloseConnection(httpConnection);
        }
        NameValuePair nameValuePair = null;
        if (!httpConnection.isTransparent()) {
            nameValuePair = getResponseHeader("proxy-connection");
        }
        if (nameValuePair == null) {
            nameValuePair = getResponseHeader("connection");
        }
        if (nameValuePair != null && nameValuePair.getValue().equalsIgnoreCase(BoxingVoteBean.BOXING_VOTE_STATE_CLOSE) && LOG.isWarnEnabled()) {
            LOG.warn(new StringBuffer().append("Invalid header encountered '").append(nameValuePair.toExternalForm()).append("' in response ").append(getStatusLine().toString()).toString());
        }
        return false;
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$ConnectMethod == null) {
            class$ = class$("org.apache.commons.httpclient.ConnectMethod");
            class$org$apache$commons$httpclient$ConnectMethod = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$ConnectMethod;
        }
        LOG = LogFactory.getLog(class$);
    }
}
