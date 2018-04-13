package org.apache.commons.httpclient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class Wire {
    public static Wire CONTENT_WIRE = new Wire(LogFactory.getLog("httpclient.wire.content"));
    public static Wire HEADER_WIRE = new Wire(LogFactory.getLog("httpclient.wire.header"));
    private Log log;

    private Wire(Log log) {
        this.log = log;
    }

    private void wire(String str, InputStream inputStream) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            int read = inputStream.read();
            if (read == -1) {
                break;
            } else if (read == 13) {
                stringBuffer.append("[\\r]");
            } else if (read == 10) {
                stringBuffer.append("[\\n]\"");
                stringBuffer.insert(0, "\"");
                stringBuffer.insert(0, str);
                this.log.debug(stringBuffer.toString());
                stringBuffer.setLength(0);
            } else if (read < 32 || read > 127) {
                stringBuffer.append("[0x");
                stringBuffer.append(Integer.toHexString(read));
                stringBuffer.append("]");
            } else {
                stringBuffer.append((char) read);
            }
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.append("\"");
            stringBuffer.insert(0, "\"");
            stringBuffer.insert(0, str);
            this.log.debug(stringBuffer.toString());
        }
    }

    public boolean enabled() {
        return this.log.isDebugEnabled();
    }

    public void output(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("Output may not be null");
        }
        wire(">> ", inputStream);
    }

    public void input(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("Input may not be null");
        }
        wire("<< ", inputStream);
    }

    public void output(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            throw new IllegalArgumentException("Output may not be null");
        }
        wire(">> ", new ByteArrayInputStream(bArr, i, i2));
    }

    public void input(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            throw new IllegalArgumentException("Input may not be null");
        }
        wire("<< ", new ByteArrayInputStream(bArr, i, i2));
    }

    public void output(byte[] bArr) throws IOException {
        if (bArr == null) {
            throw new IllegalArgumentException("Output may not be null");
        }
        wire(">> ", new ByteArrayInputStream(bArr));
    }

    public void input(byte[] bArr) throws IOException {
        if (bArr == null) {
            throw new IllegalArgumentException("Input may not be null");
        }
        wire("<< ", new ByteArrayInputStream(bArr));
    }

    public void output(int i) throws IOException {
        output(new byte[]{(byte) i});
    }

    public void input(int i) throws IOException {
        input(new byte[]{(byte) i});
    }

    public void output(String str) throws IOException {
        if (str == null) {
            throw new IllegalArgumentException("Output may not be null");
        }
        output(str.getBytes());
    }

    public void input(String str) throws IOException {
        if (str == null) {
            throw new IllegalArgumentException("Input may not be null");
        }
        input(str.getBytes());
    }
}
