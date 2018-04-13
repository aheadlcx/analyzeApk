package org.apache.commons.httpclient.methods.multipart;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MultipartRequestEntity implements RequestEntity {
    private static byte[] MULTIPART_CHARS = EncodingUtil.getAsciiBytes("-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
    private static final String MULTIPART_FORM_CONTENT_TYPE = "multipart/form-data";
    static Class class$org$apache$commons$httpclient$methods$multipart$MultipartRequestEntity;
    private static final Log log;
    private byte[] multipartBoundary;
    private HttpMethodParams params;
    protected Part[] parts;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$methods$multipart$MultipartRequestEntity == null) {
            class$ = class$("org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity");
            class$org$apache$commons$httpclient$methods$multipart$MultipartRequestEntity = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$methods$multipart$MultipartRequestEntity;
        }
        log = LogFactory.getLog(class$);
    }

    private static byte[] generateMultipartBoundary() {
        Random random = new Random();
        byte[] bArr = new byte[(random.nextInt(11) + 30)];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = MULTIPART_CHARS[random.nextInt(MULTIPART_CHARS.length)];
        }
        return bArr;
    }

    public MultipartRequestEntity(Part[] partArr, HttpMethodParams httpMethodParams) {
        if (partArr == null) {
            throw new IllegalArgumentException("parts cannot be null");
        } else if (httpMethodParams == null) {
            throw new IllegalArgumentException("params cannot be null");
        } else {
            this.parts = partArr;
            this.params = httpMethodParams;
        }
    }

    protected byte[] getMultipartBoundary() {
        if (this.multipartBoundary == null) {
            String str = (String) this.params.getParameter(HttpMethodParams.MULTIPART_BOUNDARY);
            if (str != null) {
                this.multipartBoundary = EncodingUtil.getAsciiBytes(str);
            } else {
                this.multipartBoundary = generateMultipartBoundary();
            }
        }
        return this.multipartBoundary;
    }

    public boolean isRepeatable() {
        for (Part isRepeatable : this.parts) {
            if (!isRepeatable.isRepeatable()) {
                return false;
            }
        }
        return true;
    }

    public void writeRequest(OutputStream outputStream) throws IOException {
        Part.sendParts(outputStream, this.parts, getMultipartBoundary());
    }

    public long getContentLength() {
        try {
            return Part.getLengthOfParts(this.parts, getMultipartBoundary());
        } catch (Throwable e) {
            log.error("An exception occurred while getting the length of the parts", e);
            return 0;
        }
    }

    public String getContentType() {
        StringBuffer stringBuffer = new StringBuffer("multipart/form-data");
        stringBuffer.append("; boundary=");
        stringBuffer.append(EncodingUtil.getAsciiString(getMultipartBoundary()));
        return stringBuffer.toString();
    }
}
