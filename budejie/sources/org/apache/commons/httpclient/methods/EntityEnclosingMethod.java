package org.apache.commons.httpclient.methods;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.apache.commons.httpclient.ChunkedOutputStream;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.ProtocolException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.entity.mime.MIME;

public abstract class EntityEnclosingMethod extends ExpectContinueMethod {
    public static final long CONTENT_LENGTH_AUTO = -2;
    public static final long CONTENT_LENGTH_CHUNKED = -1;
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$methods$EntityEnclosingMethod;
    private boolean chunked = false;
    private int repeatCount = 0;
    private long requestContentLength = -2;
    private RequestEntity requestEntity;
    private InputStream requestStream = null;
    private String requestString = null;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$methods$EntityEnclosingMethod == null) {
            class$ = class$("org.apache.commons.httpclient.methods.EntityEnclosingMethod");
            class$org$apache$commons$httpclient$methods$EntityEnclosingMethod = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$methods$EntityEnclosingMethod;
        }
        LOG = LogFactory.getLog(class$);
    }

    public EntityEnclosingMethod() {
        setFollowRedirects(false);
    }

    public EntityEnclosingMethod(String str) {
        super(str);
        setFollowRedirects(false);
    }

    protected boolean hasRequestContent() {
        LOG.trace("enter EntityEnclosingMethod.hasRequestContent()");
        return (this.requestEntity == null && this.requestStream == null && this.requestString == null) ? false : true;
    }

    protected void clearRequestBody() {
        LOG.trace("enter EntityEnclosingMethod.clearRequestBody()");
        this.requestStream = null;
        this.requestString = null;
        this.requestEntity = null;
    }

    protected byte[] generateRequestBody() {
        LOG.trace("enter EntityEnclosingMethod.renerateRequestBody()");
        return null;
    }

    protected RequestEntity generateRequestEntity() {
        byte[] generateRequestBody = generateRequestBody();
        if (generateRequestBody != null) {
            this.requestEntity = new ByteArrayRequestEntity(generateRequestBody);
        } else if (this.requestStream != null) {
            this.requestEntity = new InputStreamRequestEntity(this.requestStream, this.requestContentLength);
            this.requestStream = null;
        } else if (this.requestString != null) {
            String requestCharSet = getRequestCharSet();
            try {
                this.requestEntity = new StringRequestEntity(this.requestString, null, requestCharSet);
            } catch (UnsupportedEncodingException e) {
                if (LOG.isWarnEnabled()) {
                    LOG.warn(new StringBuffer().append(requestCharSet).append(" not supported").toString());
                }
                this.requestEntity = new StringRequestEntity(this.requestString);
            }
        }
        return this.requestEntity;
    }

    public boolean getFollowRedirects() {
        return false;
    }

    public void setFollowRedirects(boolean z) {
        if (z) {
            throw new IllegalArgumentException("Entity enclosing requests cannot be redirected without user intervention");
        }
        super.setFollowRedirects(false);
    }

    public void setRequestContentLength(int i) {
        LOG.trace("enter EntityEnclosingMethod.setRequestContentLength(int)");
        this.requestContentLength = (long) i;
    }

    public String getRequestCharSet() {
        if (getRequestHeader(MIME.CONTENT_TYPE) != null) {
            return super.getRequestCharSet();
        }
        if (this.requestEntity != null) {
            return getContentCharSet(new Header(MIME.CONTENT_TYPE, this.requestEntity.getContentType()));
        }
        return super.getRequestCharSet();
    }

    public void setRequestContentLength(long j) {
        LOG.trace("enter EntityEnclosingMethod.setRequestContentLength(int)");
        this.requestContentLength = j;
    }

    public void setContentChunked(boolean z) {
        this.chunked = z;
    }

    protected long getRequestContentLength() {
        LOG.trace("enter EntityEnclosingMethod.getRequestContentLength()");
        if (!hasRequestContent()) {
            return 0;
        }
        if (this.chunked) {
            return -1;
        }
        if (this.requestEntity == null) {
            this.requestEntity = generateRequestEntity();
        }
        if (this.requestEntity != null) {
            return this.requestEntity.getContentLength();
        }
        return 0;
    }

    protected void addRequestHeaders(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        LOG.trace("enter EntityEnclosingMethod.addRequestHeaders(HttpState, HttpConnection)");
        super.addRequestHeaders(httpState, httpConnection);
        addContentLengthRequestHeader(httpState, httpConnection);
        if (getRequestHeader(MIME.CONTENT_TYPE) == null) {
            RequestEntity requestEntity = getRequestEntity();
            if (requestEntity != null && requestEntity.getContentType() != null) {
                setRequestHeader(MIME.CONTENT_TYPE, requestEntity.getContentType());
            }
        }
    }

    protected void addContentLengthRequestHeader(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        LOG.trace("enter EntityEnclosingMethod.addContentLengthRequestHeader(HttpState, HttpConnection)");
        if (getRequestHeader("content-length") == null && getRequestHeader("Transfer-Encoding") == null) {
            long requestContentLength = getRequestContentLength();
            if (requestContentLength >= 0) {
                addRequestHeader("Content-Length", String.valueOf(requestContentLength));
            } else if (getEffectiveVersion().greaterEquals(HttpVersion.HTTP_1_1)) {
                addRequestHeader("Transfer-Encoding", "chunked");
            } else {
                throw new ProtocolException(new StringBuffer().append(getEffectiveVersion()).append(" does not support chunk encoding").toString());
            }
        }
    }

    public void setRequestBody(InputStream inputStream) {
        LOG.trace("enter EntityEnclosingMethod.setRequestBody(InputStream)");
        clearRequestBody();
        this.requestStream = inputStream;
    }

    public void setRequestBody(String str) {
        LOG.trace("enter EntityEnclosingMethod.setRequestBody(String)");
        clearRequestBody();
        this.requestString = str;
    }

    protected boolean writeRequestBody(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        LOG.trace("enter EntityEnclosingMethod.writeRequestBody(HttpState, HttpConnection)");
        if (hasRequestContent()) {
            if (this.requestEntity == null) {
                this.requestEntity = generateRequestEntity();
            }
            if (this.requestEntity == null) {
                LOG.debug("Request body is empty");
            } else {
                long requestContentLength = getRequestContentLength();
                if (this.repeatCount <= 0 || this.requestEntity.isRepeatable()) {
                    OutputStream chunkedOutputStream;
                    this.repeatCount++;
                    OutputStream requestOutputStream = httpConnection.getRequestOutputStream();
                    if (requestContentLength < 0) {
                        chunkedOutputStream = new ChunkedOutputStream(requestOutputStream);
                    } else {
                        chunkedOutputStream = requestOutputStream;
                    }
                    this.requestEntity.writeRequest(chunkedOutputStream);
                    if (chunkedOutputStream instanceof ChunkedOutputStream) {
                        ((ChunkedOutputStream) chunkedOutputStream).finish();
                    }
                    chunkedOutputStream.flush();
                    LOG.debug("Request body sent");
                } else {
                    throw new ProtocolException("Unbuffered entity enclosing request can not be repeated.");
                }
            }
        }
        LOG.debug("Request body has not been specified");
        return true;
    }

    public void recycle() {
        LOG.trace("enter EntityEnclosingMethod.recycle()");
        clearRequestBody();
        this.requestContentLength = -2;
        this.repeatCount = 0;
        this.chunked = false;
        super.recycle();
    }

    public RequestEntity getRequestEntity() {
        return generateRequestEntity();
    }

    public void setRequestEntity(RequestEntity requestEntity) {
        clearRequestBody();
        this.requestEntity = requestEntity;
    }
}
