package cz.msebera.android.httpclient.conn.ssl;

import cz.msebera.android.httpclient.conn.util.InetAddressUtils;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

@Deprecated
public abstract class AbstractVerifier implements X509HostnameVerifier {
    static final String[] a = new String[]{"ac", "co", "com", "ed", "edu", "go", "gouv", "gov", "info", "lg", "ne", "net", "or", "org"};
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    static {
        Arrays.sort(a);
    }

    public final void verify(String str, SSLSocket sSLSocket) throws IOException {
        Args.notNull(str, "Host");
        SSLSession session = sSLSocket.getSession();
        if (session == null) {
            sSLSocket.getInputStream().available();
            session = sSLSocket.getSession();
            if (session == null) {
                sSLSocket.startHandshake();
                session = sSLSocket.getSession();
            }
        }
        verify(str, (X509Certificate) session.getPeerCertificates()[0]);
    }

    public final boolean verify(String str, SSLSession sSLSession) {
        try {
            verify(str, (X509Certificate) sSLSession.getPeerCertificates()[0]);
            return true;
        } catch (Throwable e) {
            if (this.log.isDebugEnabled()) {
                this.log.debug(e.getMessage(), e);
            }
            return false;
        }
    }

    public final void verify(String str, X509Certificate x509Certificate) throws SSLException {
        String[] strArr;
        int i = (InetAddressUtils.isIPv4Address(str) || InetAddressUtils.isIPv6Address(str)) ? 7 : 2;
        List a = DefaultHostnameVerifier.a(x509Certificate, i);
        String[] strArr2 = new DistinguishedNameParser(x509Certificate.getSubjectX500Principal()).findMostSpecific("cn") != null ? new String[]{new DistinguishedNameParser(x509Certificate.getSubjectX500Principal()).findMostSpecific("cn")} : null;
        if (a == null || a.isEmpty()) {
            strArr = null;
        } else {
            strArr = (String[]) a.toArray(new String[a.size()]);
        }
        verify(str, strArr2, strArr);
    }

    public final void verify(String str, String[] strArr, String[] strArr2, boolean z) throws SSLException {
        String a;
        String str2 = (strArr == null || strArr.length <= 0) ? null : strArr[0];
        List list;
        if (strArr2 == null || strArr2.length <= 0) {
            list = null;
        } else {
            list = Arrays.asList(strArr2);
        }
        if (InetAddressUtils.isIPv6Address(str)) {
            a = DefaultHostnameVerifier.a(str.toLowerCase(Locale.ROOT));
        } else {
            a = str;
        }
        String a2;
        if (r1 != null) {
            for (String a22 : r1) {
                if (InetAddressUtils.isIPv6Address(a22)) {
                    a22 = DefaultHostnameVerifier.a(a22);
                }
                if (a(a, a22, z)) {
                    return;
                }
            }
            throw new SSLException("Certificate for <" + str + "> doesn't match any " + "of the subject alternative names: " + r1);
        } else if (str2 != null) {
            if (InetAddressUtils.isIPv6Address(str2)) {
                a22 = DefaultHostnameVerifier.a(str2);
            } else {
                a22 = str2;
            }
            if (!a(a, a22, z)) {
                throw new SSLException("Certificate for <" + str + "> doesn't match " + "common name of the certificate subject: " + str2);
            }
        } else {
            throw new SSLException("Certificate subject for <" + str + "> doesn't contain " + "a common name and does not have alternative names");
        }
    }

    private static boolean a(String str, String str2, boolean z) {
        boolean z2 = true;
        if (str == null) {
            return false;
        }
        String toLowerCase = str.toLowerCase(Locale.ROOT);
        String toLowerCase2 = str2.toLowerCase(Locale.ROOT);
        String[] split = toLowerCase2.split("\\.");
        if (split.length < 3 || !split[0].endsWith("*") || (z && !a(split))) {
            boolean z3 = false;
        } else {
            int i = 1;
        }
        if (i == 0) {
            return toLowerCase.equals(toLowerCase2);
        }
        String str3 = split[0];
        if (str3.length() > 1) {
            String substring = str3.substring(0, str3.length() - 1);
            str3 = toLowerCase2.substring(str3.length());
            String substring2 = toLowerCase.substring(substring.length());
            if (toLowerCase.startsWith(substring) && substring2.endsWith(str3)) {
                z3 = true;
            } else {
                z3 = false;
            }
        } else {
            z3 = toLowerCase.endsWith(toLowerCase2.substring(1));
        }
        if (!z3 || (z && countDots(toLowerCase) != countDots(toLowerCase2))) {
            z2 = false;
        }
        return z2;
    }

    private static boolean a(String[] strArr) {
        if (strArr.length == 3 && strArr[2].length() == 2 && Arrays.binarySearch(a, strArr[1]) >= 0) {
            return false;
        }
        return true;
    }

    public static boolean acceptableCountryWildcard(String str) {
        return a(str.split("\\."));
    }

    public static String[] getCNs(X509Certificate x509Certificate) {
        if (new DistinguishedNameParser(x509Certificate.getSubjectX500Principal()).findMostSpecific("cn") == null) {
            return null;
        }
        return new String[]{new DistinguishedNameParser(x509Certificate.getSubjectX500Principal()).findMostSpecific("cn")};
    }

    public static String[] getDNSSubjectAlts(X509Certificate x509Certificate) {
        List a = DefaultHostnameVerifier.a(x509Certificate, 2);
        return (a == null || a.isEmpty()) ? null : (String[]) a.toArray(new String[a.size()]);
    }

    public static int countDots(String str) {
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            if (str.charAt(i) == '.') {
                i2++;
            }
            i++;
        }
        return i2;
    }
}
