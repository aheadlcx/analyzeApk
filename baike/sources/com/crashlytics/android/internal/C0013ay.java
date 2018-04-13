package com.crashlytics.android.internal;

import cz.msebera.android.httpclient.entity.mime.MIME;
import cz.msebera.android.httpclient.message.TokenParser;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.text.Typography;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* renamed from: com.crashlytics.android.internal.ay */
public final class C0013ay {
    private static aB a = aB.a;
    private HttpURLConnection b = null;
    private URL c;
    private final String d;
    private aF e;
    private boolean f;
    private boolean g = true;
    private boolean h = false;
    private int i = 8192;

    private static String c(String str) {
        return (str == null || str.length() <= 0) ? "UTF-8" : str;
    }

    private static String c(CharSequence charSequence) throws aD {
        try {
            URL url = new URL(charSequence.toString());
            String host = url.getHost();
            int port = url.getPort();
            if (port != -1) {
                host = host + ':' + Integer.toString(port);
            }
            try {
                String toASCIIString = new URI(url.getProtocol(), host, url.getPath(), url.getQuery(), null).toASCIIString();
                int indexOf = toASCIIString.indexOf(63);
                if (indexOf > 0 && indexOf + 1 < toASCIIString.length()) {
                    toASCIIString = toASCIIString.substring(0, indexOf + 1) + toASCIIString.substring(indexOf + 1).replace(MqttTopic.SINGLE_LEVEL_WILDCARD, "%2B");
                }
                return toASCIIString;
            } catch (Throwable e) {
                IOException iOException = new IOException("Parsing URI failed");
                iOException.initCause(e);
                throw new aD(iOException);
            }
        } catch (IOException e2) {
            throw new aD(e2);
        }
    }

    private static String a(CharSequence charSequence, Map<?, ?> map) {
        String charSequence2 = charSequence.toString();
        if (map == null || map.isEmpty()) {
            return charSequence2;
        }
        StringBuilder stringBuilder = new StringBuilder(charSequence2);
        if (charSequence2.indexOf(58) + 2 == charSequence2.lastIndexOf(47)) {
            stringBuilder.append('/');
        }
        int indexOf = charSequence2.indexOf(63);
        int length = stringBuilder.length() - 1;
        if (indexOf == -1) {
            stringBuilder.append('?');
        } else if (indexOf < length && charSequence2.charAt(length) != Typography.amp) {
            stringBuilder.append(Typography.amp);
        }
        Iterator it = map.entrySet().iterator();
        Entry entry = (Entry) it.next();
        stringBuilder.append(entry.getKey().toString());
        stringBuilder.append('=');
        Object value = entry.getValue();
        if (value != null) {
            stringBuilder.append(value);
        }
        while (it.hasNext()) {
            stringBuilder.append(Typography.amp);
            entry = (Entry) it.next();
            stringBuilder.append(entry.getKey().toString());
            stringBuilder.append('=');
            value = entry.getValue();
            if (value != null) {
                stringBuilder.append(value);
            }
        }
        return stringBuilder.toString();
    }

    public static C0013ay a(CharSequence charSequence, Map<?, ?> map, boolean z) {
        return new C0013ay(C0013ay.c(C0013ay.a(charSequence, (Map) map)), "GET");
    }

    public static C0013ay b(CharSequence charSequence, Map<?, ?> map, boolean z) {
        return new C0013ay(C0013ay.c(C0013ay.a(charSequence, (Map) map)), "POST");
    }

    public static C0013ay a(CharSequence charSequence) throws aD {
        return new C0013ay(charSequence, "PUT");
    }

    public static C0013ay b(CharSequence charSequence) throws aD {
        return new C0013ay(charSequence, "DELETE");
    }

    private C0013ay(CharSequence charSequence, String str) throws aD {
        try {
            this.c = new URL(charSequence.toString());
            this.d = str;
        } catch (IOException e) {
            throw new aD(e);
        }
    }

    private HttpURLConnection e() {
        try {
            HttpURLConnection a = a.a(this.c);
            a.setRequestMethod(this.d);
            return a;
        } catch (IOException e) {
            throw new aD(e);
        }
    }

    public final String toString() {
        return a().getRequestMethod() + TokenParser.SP + a().getURL();
    }

    public final HttpURLConnection a() {
        if (this.b == null) {
            this.b = e();
        }
        return this.b;
    }

    public final int b() throws aD {
        try {
            g();
            return a().getResponseCode();
        } catch (IOException e) {
            throw new aD(e);
        }
    }

    private String d(String str) throws aD {
        h();
        int headerFieldInt = a().getHeaderFieldInt("Content-Length", -1);
        OutputStream byteArrayOutputStream = headerFieldInt > 0 ? new ByteArrayOutputStream(headerFieldInt) : new ByteArrayOutputStream();
        try {
            a(new BufferedInputStream(f(), this.i), byteArrayOutputStream);
            return byteArrayOutputStream.toString(C0013ay.c(str));
        } catch (IOException e) {
            throw new aD(e);
        }
    }

    public final String c() throws aD {
        return d(C0013ay.c(a("Content-Type"), "charset"));
    }

    private InputStream f() throws aD {
        InputStream inputStream;
        if (b() < 400) {
            try {
                inputStream = a().getInputStream();
            } catch (IOException e) {
                throw new aD(e);
            }
        }
        inputStream = a().getErrorStream();
        if (inputStream == null) {
            try {
                inputStream = a().getInputStream();
            } catch (IOException e2) {
                throw new aD(e2);
            }
        }
        return inputStream;
    }

    public final C0013ay a(int i) {
        a().setConnectTimeout(10000);
        return this;
    }

    public final C0013ay a(String str, String str2) {
        a().setRequestProperty(str, str2);
        return this;
    }

    public final C0013ay a(Entry<String, String> entry) {
        return a((String) entry.getKey(), (String) entry.getValue());
    }

    public final String a(String str) throws aD {
        h();
        return a().getHeaderField(str);
    }

    private static String c(String str, String str2) {
        if (str == null || str.length() == 0) {
            return null;
        }
        int length = str.length();
        int indexOf = str.indexOf(59) + 1;
        if (indexOf == 0 || indexOf == length) {
            return null;
        }
        int indexOf2 = str.indexOf(59, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = indexOf;
            indexOf = length;
        } else {
            int i = indexOf2;
            indexOf2 = indexOf;
            indexOf = i;
        }
        while (indexOf2 < indexOf) {
            int indexOf3 = str.indexOf(61, indexOf2);
            if (indexOf3 != -1 && indexOf3 < indexOf && str2.equals(str.substring(indexOf2, indexOf3).trim())) {
                String trim = str.substring(indexOf3 + 1, indexOf).trim();
                indexOf3 = trim.length();
                if (indexOf3 != 0) {
                    if (indexOf3 > 2 && '\"' == trim.charAt(0) && '\"' == trim.charAt(indexOf3 - 1)) {
                        return trim.substring(1, indexOf3 - 1);
                    }
                    return trim;
                }
            }
            indexOf++;
            indexOf2 = str.indexOf(59, indexOf);
            if (indexOf2 == -1) {
                indexOf2 = length;
            }
            i = indexOf2;
            indexOf2 = indexOf;
            indexOf = i;
        }
        return null;
    }

    public final C0013ay a(boolean z) {
        a().setUseCaches(false);
        return this;
    }

    private C0013ay a(InputStream inputStream, OutputStream outputStream) throws IOException {
        return (C0013ay) new bz(this, inputStream, this.g, inputStream, outputStream).call();
    }

    private C0013ay g() throws IOException {
        if (this.e != null) {
            if (this.f) {
                this.e.a("\r\n--00content0boundary00--\r\n");
            }
            if (this.g) {
                try {
                    this.e.close();
                } catch (IOException e) {
                }
            } else {
                this.e.close();
            }
            this.e = null;
        }
        return this;
    }

    private C0013ay h() throws aD {
        try {
            return g();
        } catch (IOException e) {
            throw new aD(e);
        }
    }

    private C0013ay i() throws IOException {
        if (this.e == null) {
            a().setDoOutput(true);
            this.e = new aF(a().getOutputStream(), C0013ay.c(a().getRequestProperty("Content-Type"), "charset"), this.i);
        }
        return this;
    }

    private C0013ay j() throws IOException {
        if (this.f) {
            this.e.a("\r\n--00content0boundary00\r\n");
        } else {
            this.f = true;
            a("Content-Type", "multipart/form-data; boundary=00content0boundary00").i();
            this.e.a("--00content0boundary00\r\n");
        }
        return this;
    }

    private C0013ay a(String str, String str2, String str3) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("form-data; name=\"").append(str);
        if (str2 != null) {
            stringBuilder.append("\"; filename=\"").append(str2);
        }
        stringBuilder.append('\"');
        d(MIME.CONTENT_DISPOSITION, stringBuilder.toString());
        if (str3 != null) {
            d("Content-Type", str3);
        }
        return d((CharSequence) "\r\n");
    }

    public final C0013ay b(String str, String str2) {
        return b(str, null, str2);
    }

    private C0013ay b(String str, String str2, String str3) throws aD {
        return a(str, str2, null, str3);
    }

    private C0013ay a(String str, String str2, String str3, String str4) throws aD {
        try {
            j();
            a(str, str2, null);
            this.e.a(str4);
            return this;
        } catch (IOException e) {
            throw new aD(e);
        }
    }

    public final C0013ay a(String str, Number number) throws aD {
        return b(str, null, number != null ? number.toString() : null);
    }

    public final C0013ay a(String str, String str2, String str3, File file) throws aD {
        IOException e;
        Throwable th;
        InputStream bufferedInputStream;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                C0013ay a = a(str, str2, str3, bufferedInputStream);
                try {
                    bufferedInputStream.close();
                } catch (IOException e2) {
                }
                return a;
            } catch (IOException e3) {
                e = e3;
                try {
                    throw new aD(e);
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e = e5;
            bufferedInputStream = null;
            throw new aD(e);
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            throw th;
        }
    }

    public final C0013ay a(String str, String str2, String str3, InputStream inputStream) throws aD {
        try {
            j();
            a(str, str2, str3);
            a(inputStream, this.e);
            return this;
        } catch (IOException e) {
            throw new aD(e);
        }
    }

    private C0013ay d(String str, String str2) throws aD {
        return d((CharSequence) str).d((CharSequence) ": ").d((CharSequence) str2).d((CharSequence) "\r\n");
    }

    private C0013ay d(CharSequence charSequence) throws aD {
        try {
            i();
            this.e.a(charSequence.toString());
            return this;
        } catch (IOException e) {
            throw new aD(e);
        }
    }

    public final String d() {
        return a().getRequestMethod();
    }
}
