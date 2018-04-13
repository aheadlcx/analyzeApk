package org.apache.commons.httpclient;

import com.umeng.analytics.pro.dm;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpParser {
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$HttpParser;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$HttpParser == null) {
            class$ = class$("org.apache.commons.httpclient.HttpParser");
            class$org$apache$commons$httpclient$HttpParser = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$HttpParser;
        }
        LOG = LogFactory.getLog(class$);
    }

    private HttpParser() {
    }

    public static byte[] readRawLine(InputStream inputStream) throws IOException {
        LOG.trace("enter HttpParser.readRawLine()");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int read;
        do {
            read = inputStream.read();
            if (read < 0) {
                break;
            }
            byteArrayOutputStream.write(read);
        } while (read != 10);
        if (byteArrayOutputStream.size() == 0) {
            return null;
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static String readLine(InputStream inputStream, String str) throws IOException {
        int i = 1;
        LOG.trace("enter HttpParser.readLine(InputStream, String)");
        byte[] readRawLine = readRawLine(inputStream);
        if (readRawLine == null) {
            return null;
        }
        int length = readRawLine.length;
        if (length <= 0 || readRawLine[length - 1] != (byte) 10) {
            i = 0;
        } else if (length > 1 && readRawLine[length - 2] == dm.k) {
            i = 2;
        }
        return EncodingUtil.getString(readRawLine, 0, length - i, str);
    }

    public static String readLine(InputStream inputStream) throws IOException {
        LOG.trace("enter HttpParser.readLine(InputStream)");
        return readLine(inputStream, "US-ASCII");
    }

    public static Header[] parseHeaders(InputStream inputStream, String str) throws IOException, HttpException {
        StringBuffer stringBuffer = null;
        LOG.trace("enter HeaderParser.parseHeaders(InputStream, String)");
        ArrayList arrayList = new ArrayList();
        String str2 = null;
        while (true) {
            String readLine = readLine(inputStream, str);
            if (readLine == null || readLine.trim().length() < 1) {
                if (str2 != null) {
                    arrayList.add(new Header(str2, stringBuffer.toString()));
                }
            } else if (readLine.charAt(0) != ' ' && readLine.charAt(0) != '\t') {
                if (str2 != null) {
                    arrayList.add(new Header(str2, stringBuffer.toString()));
                }
                int indexOf = readLine.indexOf(":");
                if (indexOf < 0) {
                    throw new ProtocolException(new StringBuffer().append("Unable to parse header: ").append(readLine).toString());
                }
                str2 = readLine.substring(0, indexOf).trim();
                stringBuffer = new StringBuffer(readLine.substring(indexOf + 1).trim());
            } else if (stringBuffer != null) {
                stringBuffer.append(' ');
                stringBuffer.append(readLine.trim());
            }
        }
        if (str2 != null) {
            arrayList.add(new Header(str2, stringBuffer.toString()));
        }
        return (Header[]) arrayList.toArray(new Header[arrayList.size()]);
    }

    public static Header[] parseHeaders(InputStream inputStream) throws IOException, HttpException {
        LOG.trace("enter HeaderParser.parseHeaders(InputStream, String)");
        return parseHeaders(inputStream, "US-ASCII");
    }
}
