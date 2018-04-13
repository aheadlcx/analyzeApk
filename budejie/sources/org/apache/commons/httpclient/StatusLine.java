package org.apache.commons.httpclient;

public class StatusLine {
    private final String httpVersion;
    private final String reasonPhrase;
    private final int statusCode;
    private final String statusLine;

    public StatusLine(String str) throws HttpException {
        int i = 0;
        int length = str.length();
        int i2 = 0;
        while (Character.isWhitespace(str.charAt(i))) {
            try {
                i2++;
                i++;
            } catch (NumberFormatException e) {
                throw new ProtocolException(new StringBuffer().append("Unable to parse status code from status line: '").append(str).append("'").toString());
            } catch (StringIndexOutOfBoundsException e2) {
                throw new HttpException(new StringBuffer().append("Status-Line '").append(str).append("' is not valid").toString());
            }
        }
        int i3 = i + 4;
        if ("HTTP".equals(str.substring(i, i3))) {
            i = str.indexOf(" ", i3);
            if (i <= 0) {
                throw new ProtocolException(new StringBuffer().append("Unable to parse HTTP-Version from the status line: '").append(str).append("'").toString());
            }
            this.httpVersion = str.substring(i2, i).toUpperCase();
            i2 = i;
            while (str.charAt(i2) == ' ') {
                i2++;
            }
            i = str.indexOf(" ", i2);
            if (i < 0) {
                i = length;
            }
            this.statusCode = Integer.parseInt(str.substring(i2, i));
            i++;
            if (i < length) {
                this.reasonPhrase = str.substring(i).trim();
            } else {
                this.reasonPhrase = "";
            }
            this.statusLine = str;
            return;
        }
        throw new HttpException(new StringBuffer().append("Status-Line '").append(str).append("' does not start with HTTP").toString());
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public final String getHttpVersion() {
        return this.httpVersion;
    }

    public final String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public final String toString() {
        return this.statusLine;
    }

    public static boolean startsWithHTTP(String str) {
        boolean z = false;
        int i = 0;
        while (Character.isWhitespace(str.charAt(i))) {
            try {
                i++;
            } catch (StringIndexOutOfBoundsException e) {
            }
        }
        z = "HTTP".equals(str.substring(i, i + 4));
        return z;
    }
}
