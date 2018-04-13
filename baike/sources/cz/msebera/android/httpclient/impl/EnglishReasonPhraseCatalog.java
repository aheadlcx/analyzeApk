package cz.msebera.android.httpclient.impl;

import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.ReasonPhraseCatalog;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.util.Args;
import java.util.Locale;

@Immutable
public class EnglishReasonPhraseCatalog implements ReasonPhraseCatalog {
    public static final EnglishReasonPhraseCatalog INSTANCE = new EnglishReasonPhraseCatalog();
    private static final String[][] a = new String[][]{null, new String[3], new String[8], new String[8], new String[25], new String[8]};

    static {
        a(200, "OK");
        a(201, "Created");
        a(202, "Accepted");
        a(HttpStatus.SC_NO_CONTENT, "No Content");
        a(301, "Moved Permanently");
        a(302, "Moved Temporarily");
        a(304, "Not Modified");
        a(400, "Bad Request");
        a(401, "Unauthorized");
        a(403, "Forbidden");
        a(404, "Not Found");
        a(500, "Internal Server Error");
        a(501, "Not Implemented");
        a(502, "Bad Gateway");
        a(503, "Service Unavailable");
        a(100, "Continue");
        a(307, "Temporary Redirect");
        a(405, "Method Not Allowed");
        a(HttpStatus.SC_CONFLICT, "Conflict");
        a(HttpStatus.SC_PRECONDITION_FAILED, "Precondition Failed");
        a(HttpStatus.SC_REQUEST_TOO_LONG, "Request Too Long");
        a(HttpStatus.SC_REQUEST_URI_TOO_LONG, "Request-URI Too Long");
        a(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, "Unsupported Media Type");
        a(300, "Multiple Choices");
        a(303, "See Other");
        a(HttpStatus.SC_USE_PROXY, "Use Proxy");
        a(402, "Payment Required");
        a(406, "Not Acceptable");
        a(407, "Proxy Authentication Required");
        a(408, "Request Timeout");
        a(101, "Switching Protocols");
        a(203, "Non Authoritative Information");
        a(HttpStatus.SC_RESET_CONTENT, "Reset Content");
        a(HttpStatus.SC_PARTIAL_CONTENT, "Partial Content");
        a(HttpStatus.SC_GATEWAY_TIMEOUT, "Gateway Timeout");
        a(505, "Http Version Not Supported");
        a(HttpStatus.SC_GONE, "Gone");
        a(HttpStatus.SC_LENGTH_REQUIRED, "Length Required");
        a(HttpStatus.SC_REQUESTED_RANGE_NOT_SATISFIABLE, "Requested Range Not Satisfiable");
        a(HttpStatus.SC_EXPECTATION_FAILED, "Expectation Failed");
        a(102, "Processing");
        a(HttpStatus.SC_MULTI_STATUS, "Multi-Status");
        a(HttpStatus.SC_UNPROCESSABLE_ENTITY, "Unprocessable Entity");
        a(HttpStatus.SC_INSUFFICIENT_SPACE_ON_RESOURCE, "Insufficient Space On Resource");
        a(HttpStatus.SC_METHOD_FAILURE, "Method Failure");
        a(HttpStatus.SC_LOCKED, "Locked");
        a(HttpStatus.SC_INSUFFICIENT_STORAGE, "Insufficient Storage");
        a(HttpStatus.SC_FAILED_DEPENDENCY, "Failed Dependency");
    }

    protected EnglishReasonPhraseCatalog() {
    }

    public String getReason(int i, Locale locale) {
        boolean z = i >= 100 && i < 600;
        Args.check(z, "Unknown category for status code " + i);
        int i2 = i / 100;
        int i3 = i - (i2 * 100);
        if (a[i2].length > i3) {
            return a[i2][i3];
        }
        return null;
    }

    private static void a(int i, String str) {
        int i2 = i / 100;
        a[i2][i - (i2 * 100)] = str;
    }
}
