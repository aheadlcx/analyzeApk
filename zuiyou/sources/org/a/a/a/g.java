package org.a.a.a;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;

public class g extends DefaultHandler implements i, XMLReader, LexicalHandler {
    private static char[] M = new char[]{'<', '/', '>'};
    private static String O = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-'()+,./:=?;!*#@$_%";
    private static boolean i = true;
    private static boolean j = false;
    private static boolean k = false;
    private static boolean l = true;
    private static boolean m = true;
    private static boolean n = false;
    private static boolean o = true;
    private static boolean p = false;
    private static boolean q = true;
    private HashMap A = new HashMap();
    private c B;
    private String C;
    private boolean D;
    private String E;
    private String F;
    private String G;
    private String H;
    private c I;
    private c J;
    private c K;
    private int L;
    private boolean N;
    private char[] P;
    private ContentHandler a = this;
    private LexicalHandler b = this;
    private DTDHandler c = this;
    private ErrorHandler d = this;
    private EntityResolver e = this;
    private k f;
    private j g;
    private b h;
    private boolean r = i;
    private boolean s = j;
    private boolean t = k;
    private boolean u = l;
    private boolean v = m;
    private boolean w = n;
    private boolean x = o;
    private boolean y = p;
    private boolean z = q;

    public g() {
        this.A.put("http://xml.org/sax/features/namespaces", a(i));
        this.A.put("http://xml.org/sax/features/namespace-prefixes", Boolean.FALSE);
        this.A.put("http://xml.org/sax/features/external-general-entities", Boolean.FALSE);
        this.A.put("http://xml.org/sax/features/external-parameter-entities", Boolean.FALSE);
        this.A.put("http://xml.org/sax/features/is-standalone", Boolean.FALSE);
        this.A.put("http://xml.org/sax/features/lexical-handler/parameter-entities", Boolean.FALSE);
        this.A.put("http://xml.org/sax/features/resolve-dtd-uris", Boolean.TRUE);
        this.A.put("http://xml.org/sax/features/string-interning", Boolean.TRUE);
        this.A.put("http://xml.org/sax/features/use-attributes2", Boolean.FALSE);
        this.A.put("http://xml.org/sax/features/use-locator2", Boolean.FALSE);
        this.A.put("http://xml.org/sax/features/use-entity-resolver2", Boolean.FALSE);
        this.A.put("http://xml.org/sax/features/validation", Boolean.FALSE);
        this.A.put("http://xml.org/sax/features/xmlns-uris", Boolean.FALSE);
        this.A.put("http://xml.org/sax/features/xmlns-uris", Boolean.FALSE);
        this.A.put("http://xml.org/sax/features/xml-1.1", Boolean.FALSE);
        this.A.put("http://www.ccil.org/~cowan/tagsoup/features/ignore-bogons", a(j));
        this.A.put("http://www.ccil.org/~cowan/tagsoup/features/bogons-empty", a(k));
        this.A.put("http://www.ccil.org/~cowan/tagsoup/features/root-bogons", a(l));
        this.A.put("http://www.ccil.org/~cowan/tagsoup/features/default-attributes", a(m));
        this.A.put("http://www.ccil.org/~cowan/tagsoup/features/translate-colons", a(n));
        this.A.put("http://www.ccil.org/~cowan/tagsoup/features/restart-elements", a(o));
        this.A.put("http://www.ccil.org/~cowan/tagsoup/features/ignorable-whitespace", a(p));
        this.A.put("http://www.ccil.org/~cowan/tagsoup/features/cdata-elements", a(q));
        this.B = null;
        this.C = null;
        this.D = false;
        this.E = null;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = 0;
        this.N = true;
        this.P = new char[2000];
    }

    private static Boolean a(boolean z) {
        return z ? Boolean.TRUE : Boolean.FALSE;
    }

    public boolean getFeature(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        Boolean bool = (Boolean) this.A.get(str);
        if (bool != null) {
            return bool.booleanValue();
        }
        throw new SAXNotRecognizedException(new StringBuffer().append("Unknown feature ").append(str).toString());
    }

    public void setFeature(String str, boolean z) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (((Boolean) this.A.get(str)) == null) {
            throw new SAXNotRecognizedException(new StringBuffer().append("Unknown feature ").append(str).toString());
        }
        if (z) {
            this.A.put(str, Boolean.TRUE);
        } else {
            this.A.put(str, Boolean.FALSE);
        }
        if (str.equals("http://xml.org/sax/features/namespaces")) {
            this.r = z;
        } else if (str.equals("http://www.ccil.org/~cowan/tagsoup/features/ignore-bogons")) {
            this.s = z;
        } else if (str.equals("http://www.ccil.org/~cowan/tagsoup/features/bogons-empty")) {
            this.t = z;
        } else if (str.equals("http://www.ccil.org/~cowan/tagsoup/features/root-bogons")) {
            this.u = z;
        } else if (str.equals("http://www.ccil.org/~cowan/tagsoup/features/default-attributes")) {
            this.v = z;
        } else if (str.equals("http://www.ccil.org/~cowan/tagsoup/features/translate-colons")) {
            this.w = z;
        } else if (str.equals("http://www.ccil.org/~cowan/tagsoup/features/restart-elements")) {
            this.x = z;
        } else if (str.equals("http://www.ccil.org/~cowan/tagsoup/features/ignorable-whitespace")) {
            this.y = z;
        } else if (str.equals("http://www.ccil.org/~cowan/tagsoup/features/cdata-elements")) {
            this.z = z;
        }
    }

    public Object getProperty(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (str.equals("http://xml.org/sax/properties/lexical-handler")) {
            return this.b == this ? null : this.b;
        } else {
            if (str.equals("http://www.ccil.org/~cowan/tagsoup/properties/scanner")) {
                return this.g;
            }
            if (str.equals("http://www.ccil.org/~cowan/tagsoup/properties/schema")) {
                return this.f;
            }
            if (str.equals("http://www.ccil.org/~cowan/tagsoup/properties/auto-detector")) {
                return this.h;
            }
            throw new SAXNotRecognizedException(new StringBuffer().append("Unknown property ").append(str).toString());
        }
    }

    public void setProperty(String str, Object obj) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (str.equals("http://xml.org/sax/properties/lexical-handler")) {
            if (obj == null) {
                this.b = this;
            } else if (obj instanceof LexicalHandler) {
                this.b = (LexicalHandler) obj;
            } else {
                throw new SAXNotSupportedException("Your lexical handler is not a LexicalHandler");
            }
        } else if (str.equals("http://www.ccil.org/~cowan/tagsoup/properties/scanner")) {
            if (obj instanceof j) {
                this.g = (j) obj;
                return;
            }
            throw new SAXNotSupportedException("Your scanner is not a Scanner");
        } else if (str.equals("http://www.ccil.org/~cowan/tagsoup/properties/schema")) {
            if (obj instanceof k) {
                this.f = (k) obj;
                return;
            }
            throw new SAXNotSupportedException("Your schema is not a Schema");
        } else if (!str.equals("http://www.ccil.org/~cowan/tagsoup/properties/auto-detector")) {
            throw new SAXNotRecognizedException(new StringBuffer().append("Unknown property ").append(str).toString());
        } else if (obj instanceof b) {
            this.h = (b) obj;
        } else {
            throw new SAXNotSupportedException("Your auto-detector is not an AutoDetector");
        }
    }

    public void setEntityResolver(EntityResolver entityResolver) {
        if (entityResolver == null) {
            entityResolver = this;
        }
        this.e = entityResolver;
    }

    public EntityResolver getEntityResolver() {
        return this.e == this ? null : this.e;
    }

    public void setDTDHandler(DTDHandler dTDHandler) {
        if (dTDHandler == null) {
            dTDHandler = this;
        }
        this.c = dTDHandler;
    }

    public DTDHandler getDTDHandler() {
        return this.c == this ? null : this.c;
    }

    public void setContentHandler(ContentHandler contentHandler) {
        if (contentHandler == null) {
            contentHandler = this;
        }
        this.a = contentHandler;
    }

    public ContentHandler getContentHandler() {
        return this.a == this ? null : this.a;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        if (errorHandler == null) {
            errorHandler = this;
        }
        this.d = errorHandler;
    }

    public ErrorHandler getErrorHandler() {
        return this.d == this ? null : this.d;
    }

    public void parse(InputSource inputSource) throws IOException, SAXException {
        b();
        Reader a = a(inputSource);
        this.a.startDocument();
        this.g.a(inputSource.getPublicId(), inputSource.getSystemId());
        if (this.g instanceof Locator) {
            this.a.setDocumentLocator((Locator) this.g);
        }
        if (!this.f.b().equals("")) {
            this.a.startPrefixMapping(this.f.c(), this.f.b());
        }
        this.g.a(a, (i) this);
    }

    public void parse(String str) throws IOException, SAXException {
        parse(new InputSource(str));
    }

    private void b() {
        if (this.f == null) {
            this.f = new f();
        }
        if (this.g == null) {
            this.g = new e();
        }
        if (this.h == null) {
            this.h = new h(this);
        }
        this.I = new c(this.f.a("<root>"), this.v);
        this.K = new c(this.f.a("<pcdata>"), this.v);
        this.B = null;
        this.C = null;
        this.H = null;
        this.J = null;
        this.L = 0;
        this.N = true;
        this.F = null;
        this.E = null;
        this.G = null;
    }

    private Reader a(InputSource inputSource) throws SAXException, IOException {
        Reader characterStream = inputSource.getCharacterStream();
        InputStream byteStream = inputSource.getByteStream();
        String encoding = inputSource.getEncoding();
        String publicId = inputSource.getPublicId();
        String systemId = inputSource.getSystemId();
        if (characterStream != null) {
            return characterStream;
        }
        if (byteStream == null) {
            byteStream = a(publicId, systemId);
        }
        if (encoding == null) {
            return this.h.a(byteStream);
        }
        try {
            return new InputStreamReader(byteStream, encoding);
        } catch (UnsupportedEncodingException e) {
            return new InputStreamReader(byteStream);
        }
    }

    private InputStream a(String str, String str2) throws IOException, SAXException {
        return new URL(new URL("file", "", new StringBuffer().append(System.getProperty("user.dir")).append("/.").toString()), str2).openConnection().getInputStream();
    }

    public void a(char[] cArr, int i, int i2) throws SAXException {
        if (this.B != null && this.C != null) {
            this.B.a(this.C, null, this.C);
            this.C = null;
        }
    }

    public void b(char[] cArr, int i, int i2) throws SAXException {
        if (this.B != null) {
            this.C = r(cArr, i, i2).toLowerCase();
        }
    }

    public void c(char[] cArr, int i, int i2) throws SAXException {
        if (this.B != null && this.C != null) {
            this.B.a(this.C, null, a(new String(cArr, i, i2)));
            this.C = null;
        }
    }

    private String a(String str) {
        int length = str.length();
        char[] cArr = new char[length];
        int i = 0;
        int i2 = 0;
        int i3 = -1;
        while (i < length) {
            char charAt = str.charAt(i);
            int i4 = i2 + 1;
            cArr[i2] = charAt;
            if (charAt == '&' && i3 == -1) {
                i3 = i4;
            } else if (!(i3 == -1 || Character.isLetter(charAt) || Character.isDigit(charAt) || charAt == '#')) {
                if (charAt == ';') {
                    i2 = q(cArr, i3, (i4 - i3) - 1);
                    if (i2 > 65535) {
                        i4 = i2 - 65536;
                        cArr[i3 - 1] = (char) ((i4 >> 10) + 55296);
                        cArr[i3] = (char) ((i4 & 1023) + 56320);
                        i3++;
                    } else if (i2 != 0) {
                        cArr[i3 - 1] = (char) i2;
                    } else {
                        i3 = i4;
                    }
                    i4 = i3;
                    i3 = -1;
                } else {
                    i3 = -1;
                }
            }
            i++;
            i2 = i4;
        }
        return new String(cArr, 0, i2);
    }

    public void d(char[] cArr, int i, int i2) throws SAXException {
        this.L = q(cArr, i, i2);
    }

    private int q(char[] cArr, int i, int i2) {
        int i3 = 0;
        if (i2 < 1) {
            return i3;
        }
        if (cArr[i] != '#') {
            return this.f.b(new String(cArr, i, i2));
        }
        if (i2 <= 1 || !(cArr[i + 1] == 'x' || cArr[i + 1] == 'X')) {
            try {
                return Integer.parseInt(new String(cArr, i + 1, i2 - 1), 10);
            } catch (NumberFormatException e) {
                return i3;
            }
        }
        try {
            return Integer.parseInt(new String(cArr, i + 2, i2 - 2), 16);
        } catch (NumberFormatException e2) {
            return i3;
        }
    }

    public void e(char[] cArr, int i, int i2) throws SAXException {
        if (this.N) {
            c(this.K);
        }
        while (this.I.b() != null) {
            c();
        }
        if (!this.f.b().equals("")) {
            this.a.endPrefixMapping(this.f.c());
        }
        this.a.endDocument();
    }

    public void f(char[] cArr, int i, int i2) throws SAXException {
        if (!g(cArr, i, i2)) {
            h(cArr, i, i2);
        }
    }

    public boolean g(char[] cArr, int i, int i2) throws SAXException {
        String c = this.I.c();
        if (this.z && (this.I.g() & 2) != 0) {
            boolean z = i2 == c.length();
            if (z) {
                for (int i3 = 0; i3 < i2; i3++) {
                    if (Character.toLowerCase(cArr[i + i3]) != Character.toLowerCase(c.charAt(i3))) {
                        z = false;
                        break;
                    }
                }
            }
            if (!z) {
                this.a.characters(M, 0, 2);
                this.a.characters(cArr, i, i2);
                this.a.characters(M, 2, 1);
                this.g.a();
                return true;
            }
        }
        return false;
    }

    public void h(char[] cArr, int i, int i2) throws SAXException {
        this.B = null;
        if (i2 != 0) {
            d a = this.f.a(r(cArr, i, i2));
            if (a != null) {
                Object a2 = a.a();
            } else {
                return;
            }
        }
        a2 = this.I.c();
        Object obj = null;
        c cVar = this.I;
        while (cVar != null && !cVar.c().equals(r0)) {
            if ((cVar.g() & 4) != 0) {
                obj = 1;
            }
            cVar = cVar.b();
        }
        if (cVar != null && cVar.b() != null && cVar.b().b() != null) {
            if (obj != null) {
                cVar.k();
            } else {
                while (this.I != cVar) {
                    d();
                }
                c();
            }
            while (this.I.l()) {
                c();
            }
            a(null);
        }
    }

    private void a(c cVar) throws SAXException {
        while (this.J != null && this.I.b(this.J)) {
            if (cVar == null || this.J.b(cVar)) {
                c b = this.J.b();
                b(this.J);
                this.J = b;
            } else {
                return;
            }
        }
    }

    private void c() throws SAXException {
        if (this.I != null) {
            String c = this.I.c();
            String e = this.I.e();
            String d = this.I.d();
            String b = b(c);
            if (!this.r) {
                d = "";
                e = d;
            }
            this.a.endElement(d, e, c);
            if (b(b, d)) {
                this.a.endPrefixMapping(b);
            }
            Attributes a = this.I.a();
            for (int length = a.getLength() - 1; length >= 0; length--) {
                c = a.getURI(length);
                b = b(a.getQName(length));
                if (b(b, c)) {
                    this.a.endPrefixMapping(b);
                }
            }
            this.I = this.I.b();
        }
    }

    private void d() throws SAXException {
        c cVar = this.I;
        c();
        if (this.x && (cVar.g() & 1) != 0) {
            cVar.i();
            cVar.a(this.J);
            this.J = cVar;
        }
    }

    private void b(c cVar) throws SAXException {
        String c = cVar.c();
        String e = cVar.e();
        String d = cVar.d();
        String b = b(c);
        cVar.j();
        if (!this.r) {
            d = "";
            e = d;
        }
        if (this.N && e.equalsIgnoreCase(this.G)) {
            try {
                this.e.resolveEntity(this.E, this.F);
            } catch (IOException e2) {
            }
        }
        if (b(b, d)) {
            this.a.startPrefixMapping(b, d);
        }
        Attributes a = cVar.a();
        int length = a.getLength();
        for (int i = 0; i < length; i++) {
            String uri = a.getURI(i);
            String b2 = b(a.getQName(i));
            if (b(b2, uri)) {
                this.a.startPrefixMapping(b2, uri);
            }
        }
        this.a.startElement(d, e, c, cVar.a());
        cVar.a(this.I);
        this.I = cVar;
        this.N = false;
        if (this.z && (this.I.g() & 2) != 0) {
            this.g.a();
        }
    }

    private String b(String str) {
        int indexOf = str.indexOf(58);
        String str2 = "";
        if (indexOf != -1) {
            return str.substring(0, indexOf);
        }
        return str2;
    }

    private boolean b(String str, String str2) {
        return (str.equals("") || str2.equals("") || str2.equals(this.f.b())) ? false : true;
    }

    public void i(char[] cArr, int i, int i2) throws SAXException {
        String str;
        String str2;
        String str3 = null;
        String[] d = d(new String(cArr, i, i2));
        if (d.length > 0 && "DOCTYPE".equalsIgnoreCase(d[0])) {
            if (!this.D) {
                this.D = true;
                if (d.length > 1) {
                    str = d[1];
                    if (d.length > 3 && "SYSTEM".equals(d[2])) {
                        str2 = d[3];
                        str3 = c(str3);
                        str2 = c(str2);
                        if (str == null) {
                            str3 = e(str3);
                            this.b.startDTD(str, str3, str2);
                            this.b.endDTD();
                            this.G = str;
                            this.E = str3;
                            if (!(this.g instanceof Locator)) {
                                this.F = ((Locator) this.g).getSystemId();
                                this.F = new URL(new URL(this.F), str2).toString();
                            }
                        }
                    } else if (d.length <= 3 || !"PUBLIC".equals(d[2])) {
                        str2 = null;
                        str3 = c(str3);
                        str2 = c(str2);
                        if (str == null) {
                            str3 = e(str3);
                            this.b.startDTD(str, str3, str2);
                            this.b.endDTD();
                            this.G = str;
                            this.E = str3;
                            if (!(this.g instanceof Locator)) {
                                this.F = ((Locator) this.g).getSystemId();
                                try {
                                    this.F = new URL(new URL(this.F), str2).toString();
                                } catch (Exception e) {
                                    return;
                                }
                            }
                        }
                    } else {
                        str3 = d[3];
                        str2 = d.length > 4 ? d[4] : "";
                        str3 = c(str3);
                        str2 = c(str2);
                        if (str == null) {
                            str3 = e(str3);
                            this.b.startDTD(str, str3, str2);
                            this.b.endDTD();
                            this.G = str;
                            this.E = str3;
                            if (!(this.g instanceof Locator)) {
                                this.F = ((Locator) this.g).getSystemId();
                                this.F = new URL(new URL(this.F), str2).toString();
                            }
                        }
                    }
                }
            }
            return;
        }
        str2 = null;
        str = null;
        str3 = c(str3);
        str2 = c(str2);
        if (str == null) {
            str3 = e(str3);
            this.b.startDTD(str, str3, str2);
            this.b.endDTD();
            this.G = str;
            this.E = str3;
            if (!(this.g instanceof Locator)) {
                this.F = ((Locator) this.g).getSystemId();
                this.F = new URL(new URL(this.F), str2).toString();
            }
        }
    }

    private static String c(String str) {
        if (str == null) {
            return str;
        }
        int length = str.length();
        if (length == 0) {
            return str;
        }
        char charAt = str.charAt(0);
        if (charAt != str.charAt(length - 1)) {
            return str;
        }
        if (charAt == '\'' || charAt == '\"') {
            return str.substring(1, str.length() - 1);
        }
        return str;
    }

    private static String[] d(String str) throws IllegalArgumentException {
        String trim = str.trim();
        if (trim.length() == 0) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList();
        int length = trim.length();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i4 < length) {
            char charAt = trim.charAt(i4);
            if (i2 == 0 && charAt == '\'' && r6 != 92) {
                if (i3 == 0) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                if (i5 < 0) {
                    i5 = i4;
                }
            } else if (i3 == 0 && charAt == '\"' && r6 != 92) {
                if (i2 == 0) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                if (i5 < 0) {
                    i5 = i4;
                }
            } else if (i3 == 0 && i2 == 0) {
                if (Character.isWhitespace(charAt)) {
                    if (i5 >= 0) {
                        arrayList.add(trim.substring(i5, i4));
                    }
                    i5 = -1;
                } else if (i5 < 0 && charAt != ' ') {
                    i5 = i4;
                }
            }
            i4++;
            char c = charAt;
        }
        arrayList.add(trim.substring(i5, i4));
        return (String[]) arrayList.toArray(new String[0]);
    }

    private String e(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length);
        Object obj = 1;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (O.indexOf(charAt) != -1) {
                stringBuffer.append(charAt);
                obj = null;
            } else if (obj == null) {
                stringBuffer.append(' ');
                int i2 = 1;
            }
        }
        return stringBuffer.toString().trim();
    }

    public void j(char[] cArr, int i, int i2) throws SAXException {
        int i3 = -1;
        if (this.B == null) {
            String r = r(cArr, i, i2);
            if (r != null) {
                d a = this.f.a(r);
                if (a == null) {
                    if (!this.s) {
                        int i4 = this.t ? 0 : -1;
                        if (!this.u) {
                            i3 = Integer.MAX_VALUE;
                        }
                        this.f.a(r, i4, i3, 0);
                        if (!this.u) {
                            this.f.a(r, this.f.a().a());
                        }
                        a = this.f.a(r);
                    } else {
                        return;
                    }
                }
                this.B = new c(a, this.v);
            }
        }
    }

    public void k(char[] cArr, int i, int i2) throws SAXException {
        if (i2 != 0) {
            Object obj = 1;
            for (int i3 = 0; i3 < i2; i3++) {
                if (!Character.isWhitespace(cArr[i + i3])) {
                    obj = null;
                }
            }
            if (obj == null || this.I.b(this.K)) {
                c(this.K);
                this.a.characters(cArr, i, i2);
            } else if (this.y) {
                this.a.ignorableWhitespace(cArr, i, i2);
            }
        }
    }

    public void l(char[] cArr, int i, int i2) throws SAXException {
        if (this.B == null) {
            this.H = r(cArr, i, i2).replace(':', '_');
        }
    }

    public void m(char[] cArr, int i, int i2) throws SAXException {
        if (this.B == null && this.H != null && !"xml".equalsIgnoreCase(this.H)) {
            if (i2 > 0 && cArr[i2 - 1] == '?') {
                i2--;
            }
            this.a.processingInstruction(this.H, new String(cArr, i, i2));
            this.H = null;
        }
    }

    public void n(char[] cArr, int i, int i2) throws SAXException {
        if (this.B != null) {
            c(this.B);
            if (this.I.f() == 0) {
                h(cArr, i, i2);
            }
        }
    }

    public void o(char[] cArr, int i, int i2) throws SAXException {
        if (this.B != null) {
            c(this.B);
            h(cArr, i, i2);
        }
    }

    public void p(char[] cArr, int i, int i2) throws SAXException {
        this.b.comment(cArr, i, i2);
    }

    private void c(c cVar) throws SAXException {
        while (true) {
            c cVar2 = this.I;
            while (cVar2 != null && !cVar2.b(cVar)) {
                cVar2 = cVar2.b();
            }
            if (cVar2 == null) {
                d h = cVar.h();
                if (h == null) {
                    break;
                }
                cVar2 = new c(h, this.v);
                cVar2.a(cVar);
                cVar = cVar2;
            } else {
                break;
            }
        }
        if (cVar2 != null) {
            while (this.I != cVar2 && this.I != null && this.I.b() != null && this.I.b().b() != null) {
                d();
            }
            while (cVar != null) {
                cVar2 = cVar.b();
                if (!cVar.c().equals("<pcdata>")) {
                    b(cVar);
                }
                a(cVar2);
                cVar = cVar2;
            }
            this.B = null;
        }
    }

    public int a() {
        return this.L;
    }

    private String r(char[] cArr, int i, int i2) {
        int i3;
        StringBuffer stringBuffer = new StringBuffer(i2 + 2);
        Object obj = 1;
        Object obj2 = null;
        while (true) {
            int i4 = i2 - 1;
            if (i2 <= 0) {
                break;
            }
            Object obj3;
            char c = cArr[i];
            if (Character.isLetter(c) || c == '_') {
                stringBuffer.append(c);
                obj3 = null;
                obj = obj2;
            } else if (Character.isDigit(c) || c == '-' || c == '.') {
                if (obj != null) {
                    stringBuffer.append('_');
                }
                stringBuffer.append(c);
                obj3 = null;
                obj = obj2;
            } else if (c == ':' && obj2 == null) {
                if (obj != null) {
                    stringBuffer.append('_');
                }
                if (this.w) {
                    c = '_';
                }
                stringBuffer.append(c);
                i3 = 1;
                int i5 = 1;
            } else {
                obj3 = obj;
                obj = obj2;
            }
            i++;
            obj2 = obj;
            i2 = i4;
            obj = obj3;
        }
        i3 = stringBuffer.length();
        if (i3 == 0 || stringBuffer.charAt(i3 - 1) == ':') {
            stringBuffer.append('_');
        }
        return stringBuffer.toString().intern();
    }

    public void comment(char[] cArr, int i, int i2) throws SAXException {
    }

    public void endCDATA() throws SAXException {
    }

    public void endDTD() throws SAXException {
    }

    public void endEntity(String str) throws SAXException {
    }

    public void startCDATA() throws SAXException {
    }

    public void startDTD(String str, String str2, String str3) throws SAXException {
    }

    public void startEntity(String str) throws SAXException {
    }
}
