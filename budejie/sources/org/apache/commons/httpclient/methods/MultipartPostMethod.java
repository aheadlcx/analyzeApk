package org.apache.commons.httpclient.methods;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.entity.mime.MIME;

public class MultipartPostMethod extends ExpectContinueMethod {
    private static final Log LOG;
    public static final String MULTIPART_FORM_CONTENT_TYPE = "multipart/form-data";
    static Class class$org$apache$commons$httpclient$methods$MultipartPostMethod;
    private final List parameters = new ArrayList();

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$methods$MultipartPostMethod == null) {
            class$ = class$("org.apache.commons.httpclient.methods.MultipartPostMethod");
            class$org$apache$commons$httpclient$methods$MultipartPostMethod = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$methods$MultipartPostMethod;
        }
        LOG = LogFactory.getLog(class$);
    }

    public MultipartPostMethod(String str) {
        super(str);
    }

    protected boolean hasRequestContent() {
        return true;
    }

    public String getName() {
        return "POST";
    }

    public void addParameter(String str, String str2) {
        LOG.trace("enter addParameter(String parameterName, String parameterValue)");
        this.parameters.add(new StringPart(str, str2));
    }

    public void addParameter(String str, File file) throws FileNotFoundException {
        LOG.trace("enter MultipartPostMethod.addParameter(String parameterName, File parameterFile)");
        this.parameters.add(new FilePart(str, file));
    }

    public void addParameter(String str, String str2, File file) throws FileNotFoundException {
        LOG.trace("enter MultipartPostMethod.addParameter(String parameterName, String fileName, File parameterFile)");
        this.parameters.add(new FilePart(str, str2, file));
    }

    public void addPart(Part part) {
        LOG.trace("enter addPart(Part part)");
        this.parameters.add(part);
    }

    public Part[] getParts() {
        return (Part[]) this.parameters.toArray(new Part[this.parameters.size()]);
    }

    protected void addContentLengthRequestHeader(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        LOG.trace("enter EntityEnclosingMethod.addContentLengthRequestHeader(HttpState, HttpConnection)");
        if (getRequestHeader("Content-Length") == null) {
            addRequestHeader("Content-Length", String.valueOf(getRequestContentLength()));
        }
        removeRequestHeader("Transfer-Encoding");
    }

    protected void addContentTypeRequestHeader(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        LOG.trace("enter EntityEnclosingMethod.addContentTypeRequestHeader(HttpState, HttpConnection)");
        if (!this.parameters.isEmpty()) {
            StringBuffer stringBuffer = new StringBuffer(MULTIPART_FORM_CONTENT_TYPE);
            if (Part.getBoundary() != null) {
                stringBuffer.append("; boundary=");
                stringBuffer.append(Part.getBoundary());
            }
            setRequestHeader(MIME.CONTENT_TYPE, stringBuffer.toString());
        }
    }

    protected void addRequestHeaders(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        LOG.trace("enter MultipartPostMethod.addRequestHeaders(HttpState state, HttpConnection conn)");
        super.addRequestHeaders(httpState, httpConnection);
        addContentLengthRequestHeader(httpState, httpConnection);
        addContentTypeRequestHeader(httpState, httpConnection);
    }

    protected boolean writeRequestBody(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        LOG.trace("enter MultipartPostMethod.writeRequestBody(HttpState state, HttpConnection conn)");
        Part.sendParts(httpConnection.getRequestOutputStream(), getParts());
        return true;
    }

    protected long getRequestContentLength() throws IOException {
        LOG.trace("enter MultipartPostMethod.getRequestContentLength()");
        return Part.getLengthOfParts(getParts());
    }

    public void recycle() {
        LOG.trace("enter MultipartPostMethod.recycle()");
        super.recycle();
        this.parameters.clear();
    }
}
