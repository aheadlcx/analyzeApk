package cz.msebera.android.httpclient.conn.ssl;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.conn.util.InetAddressUtils;
import cz.msebera.android.httpclient.conn.util.PublicSuffixMatcher;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;

@Immutable
public final class DefaultHostnameVerifier implements HostnameVerifier {
    private final PublicSuffixMatcher a;
    public HttpClientAndroidLog log;

    public DefaultHostnameVerifier(PublicSuffixMatcher publicSuffixMatcher) {
        this.log = new HttpClientAndroidLog(getClass());
        this.a = publicSuffixMatcher;
    }

    public DefaultHostnameVerifier() {
        this(null);
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
        boolean isIPv4Address = InetAddressUtils.isIPv4Address(str);
        boolean isIPv6Address = InetAddressUtils.isIPv6Address(str);
        int i = (isIPv4Address || isIPv6Address) ? 7 : 2;
        List a = a(x509Certificate, i);
        if (a == null || a.isEmpty()) {
            String findMostSpecific = new DistinguishedNameParser(x509Certificate.getSubjectX500Principal()).findMostSpecific("cn");
            if (findMostSpecific == null) {
                throw new SSLException("Certificate subject for <" + str + "> doesn't contain " + "a common name and does not have alternative names");
            }
            a(str, findMostSpecific, this.a);
        } else if (isIPv4Address) {
            a(str, a);
        } else if (isIPv6Address) {
            b(str, a);
        } else {
            a(str, a, this.a);
        }
    }

    static void a(String str, List<String> list) throws SSLException {
        int i = 0;
        while (i < list.size()) {
            if (!str.equals((String) list.get(i))) {
                i++;
            } else {
                return;
            }
        }
        throw new SSLException("Certificate for <" + str + "> doesn't match any " + "of the subject alternative names: " + list);
    }

    static void b(String str, List<String> list) throws SSLException {
        String a = a(str);
        int i = 0;
        while (i < list.size()) {
            if (!a.equals(a((String) list.get(i)))) {
                i++;
            } else {
                return;
            }
        }
        throw new SSLException("Certificate for <" + str + "> doesn't match any " + "of the subject alternative names: " + list);
    }

    static void a(String str, List<String> list, PublicSuffixMatcher publicSuffixMatcher) throws SSLException {
        String toLowerCase = str.toLowerCase(Locale.ROOT);
        int i = 0;
        while (i < list.size()) {
            if (!b(toLowerCase, ((String) list.get(i)).toLowerCase(Locale.ROOT), publicSuffixMatcher)) {
                i++;
            } else {
                return;
            }
        }
        throw new SSLException("Certificate for <" + str + "> doesn't match any " + "of the subject alternative names: " + list);
    }

    static void a(String str, String str2, PublicSuffixMatcher publicSuffixMatcher) throws SSLException {
        if (!b(str, str2, publicSuffixMatcher)) {
            throw new SSLException("Certificate for <" + str + "> doesn't match " + "common name of the certificate subject: " + str2);
        }
    }

    static boolean a(String str, String str2) {
        if (str2 == null || !str.endsWith(str2)) {
            return false;
        }
        if (str.length() == str2.length() || str.charAt((str.length() - str2.length()) - 1) == '.') {
            return true;
        }
        return false;
    }

    private static boolean a(String str, String str2, PublicSuffixMatcher publicSuffixMatcher, boolean z) {
        if (publicSuffixMatcher != null && str.contains(".") && !a(str, publicSuffixMatcher.getDomainRoot(str2))) {
            return false;
        }
        int indexOf = str2.indexOf(42);
        if (indexOf == -1) {
            return str.equalsIgnoreCase(str2);
        }
        String substring = str2.substring(0, indexOf);
        String substring2 = str2.substring(indexOf + 1);
        if (!substring.isEmpty() && !str.startsWith(substring)) {
            return false;
        }
        if (!substring2.isEmpty() && !str.endsWith(substring2)) {
            return false;
        }
        if (z && str.substring(substring.length(), str.length() - substring2.length()).contains(".")) {
            return false;
        }
        return true;
    }

    static boolean b(String str, String str2, PublicSuffixMatcher publicSuffixMatcher) {
        return a(str, str2, publicSuffixMatcher, true);
    }

    static List<String> a(X509Certificate x509Certificate, int i) {
        Collection subjectAlternativeNames;
        List<String> list = null;
        try {
            subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
        } catch (CertificateParsingException e) {
            subjectAlternativeNames = null;
        }
        if (r0 != null) {
            for (List list2 : r0) {
                List<String> arrayList;
                if (((Integer) list2.get(0)).intValue() == i) {
                    String str = (String) list2.get(1);
                    if (list == null) {
                        arrayList = new ArrayList();
                    } else {
                        arrayList = list;
                    }
                    arrayList.add(str);
                } else {
                    arrayList = list;
                }
                list = arrayList;
            }
        }
        return list;
    }

    static String a(String str) {
        if (str != null) {
            try {
                str = InetAddress.getByName(str).getHostAddress();
            } catch (UnknownHostException e) {
            }
        }
        return str;
    }
}
