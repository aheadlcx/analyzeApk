package org.apache.commons.httpclient;

public class HttpVersion implements Comparable {
    public static final HttpVersion HTTP_0_9 = new HttpVersion(0, 9);
    public static final HttpVersion HTTP_1_0 = new HttpVersion(1, 0);
    public static final HttpVersion HTTP_1_1 = new HttpVersion(1, 1);
    private int major = 0;
    private int minor = 0;

    public HttpVersion(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("HTTP major version number may not be negative");
        }
        this.major = i;
        if (i2 < 0) {
            throw new IllegalArgumentException("HTTP minor version number may not be negative");
        }
        this.minor = i2;
    }

    public int getMajor() {
        return this.major;
    }

    public int getMinor() {
        return this.minor;
    }

    public int hashCode() {
        return (this.major * 100000) + this.minor;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof HttpVersion) {
            return equals((HttpVersion) obj);
        }
        return false;
    }

    public int compareTo(HttpVersion httpVersion) {
        if (httpVersion == null) {
            throw new IllegalArgumentException("Version parameter may not be null");
        }
        int major = getMajor() - httpVersion.getMajor();
        if (major == 0) {
            return getMinor() - httpVersion.getMinor();
        }
        return major;
    }

    public int compareTo(Object obj) {
        return compareTo((HttpVersion) obj);
    }

    public boolean equals(HttpVersion httpVersion) {
        return compareTo(httpVersion) == 0;
    }

    public boolean greaterEquals(HttpVersion httpVersion) {
        return compareTo(httpVersion) >= 0;
    }

    public boolean lessEquals(HttpVersion httpVersion) {
        return compareTo(httpVersion) <= 0;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("HTTP/");
        stringBuffer.append(this.major);
        stringBuffer.append('.');
        stringBuffer.append(this.minor);
        return stringBuffer.toString();
    }

    public static HttpVersion parse(String str) throws ProtocolException {
        if (str == null) {
            throw new IllegalArgumentException("String may not be null");
        } else if (str.startsWith("HTTP/")) {
            int length = "HTTP/".length();
            int indexOf = str.indexOf(".", length);
            if (indexOf == -1) {
                throw new ProtocolException(new StringBuffer().append("Invalid HTTP version number: ").append(str).toString());
            }
            try {
                try {
                    return new HttpVersion(Integer.parseInt(str.substring(length, indexOf)), Integer.parseInt(str.substring(indexOf + 1, str.length())));
                } catch (NumberFormatException e) {
                    throw new ProtocolException(new StringBuffer().append("Invalid HTTP minor version number: ").append(str).toString());
                }
            } catch (NumberFormatException e2) {
                throw new ProtocolException(new StringBuffer().append("Invalid HTTP major version number: ").append(str).toString());
            }
        } else {
            throw new ProtocolException(new StringBuffer().append("Invalid HTTP version string: ").append(str).toString());
        }
    }
}
