package org.a.a.a;

import com.tencent.bugly.beta.tinker.TinkerReport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.lang.reflect.Array;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class e implements j, Locator {
    static short[][] f;
    static int g;
    private static int[] h = new int[]{1, 47, 5, 22, 1, 61, 4, 3, 1, 62, 6, 28, 1, 0, 27, 1, 1, -1, 6, 21, 1, 32, 4, 24, 1, 10, 4, 24, 1, 9, 4, 24, 2, 39, 7, 34, 2, 0, 27, 2, 2, -1, 8, 21, 2, 32, 29, 2, 2, 10, 29, 2, 2, 9, 29, 2, 3, 34, 28, 31, 3, 39, 28, 2, 3, 62, 8, 28, 3, 0, 27, 32, 3, -1, 8, 21, 3, 32, 28, 3, 3, 10, 28, 3, 3, 9, 28, 3, 4, 67, 28, 5, 4, 0, 28, 19, 4, -1, 28, 21, 5, 68, 28, 6, 5, 0, 28, 19, 5, -1, 28, 21, 6, 65, 28, 7, 6, 0, 28, 19, 6, -1, 28, 21, 7, 84, 28, 8, 7, 0, 28, 19, 7, -1, 28, 21, 8, 65, 28, 9, 8, 0, 28, 19, 8, -1, 28, 21, 9, 91, 28, 12, 9, 0, 28, 19, 9, -1, 28, 21, 10, 60, 27, 11, 10, 0, 27, 10, 10, -1, 23, 21, 11, 47, 32, 25, 11, 0, 27, 10, 11, -1, 32, 21, 12, 93, 27, 13, 12, 0, 27, 12, 12, -1, 28, 21, 13, 93, 27, 14, 13, 0, 27, 12, 13, -1, 28, 21, 14, 62, 9, 28, 14, 93, 27, 14, 14, 0, 27, 12, 14, -1, 28, 21, 15, 45, 28, 16, 15, 0, 27, 16, 15, -1, 10, 21, 16, 45, 28, 17, 16, 0, 27, 16, 16, -1, 10, 21, 17, 45, 28, 18, 17, 0, 20, 16, 17, -1, 10, 21, 18, 45, 22, 18, 18, 62, 10, 28, 18, 0, 21, 16, 18, -1, 10, 21, 19, 45, 28, 15, 19, 62, 28, 28, 19, 91, 28, 4, 19, 0, 27, 20, 19, -1, 28, 21, 20, 62, 11, 28, 20, 0, 27, 20, 20, -1, 28, 21, 22, 62, 12, 28, 22, 0, 27, 1, 22, 32, 28, 34, 22, 10, 28, 34, 22, 9, 28, 34, 23, 0, 13, 23, 23, -1, 13, 21, 24, 61, 28, 3, 24, 62, 3, 28, 24, 0, 2, 1, 24, -1, 3, 21, 24, 32, 28, 24, 24, 10, 28, 24, 24, 9, 28, 24, 25, 62, 15, 28, 25, 0, 27, 25, 25, -1, 15, 21, 25, 32, 28, 25, 25, 10, 28, 25, 25, 9, 28, 25, 26, 47, 28, 22, 26, 62, 17, 28, 26, 0, 27, 26, 26, -1, 28, 21, 26, 32, 16, 34, 26, 10, 16, 34, 26, 9, 16, 34, 27, 0, 13, 27, 27, -1, 13, 21, 28, 38, 14, 23, 28, 60, 23, 33, 28, 0, 27, 28, 28, -1, 23, 21, 29, 62, 24, 28, 29, 0, 27, 29, 29, -1, 24, 21, 30, 62, 26, 28, 30, 0, 27, 30, 30, -1, 26, 21, 30, 32, 25, 29, 30, 10, 25, 29, 30, 9, 25, 29, 31, 34, 7, 34, 31, 0, 27, 31, 31, -1, 8, 21, 31, 32, 29, 31, 31, 10, 29, 31, 31, 9, 29, 31, 32, 62, 8, 28, 32, 0, 27, 32, 32, -1, 8, 21, 32, 32, 7, 34, 32, 10, 7, 34, 32, 9, 7, 34, 33, 33, 28, 19, 33, 47, 28, 25, 33, 60, 27, 33, 33, 63, 28, 30, 33, 0, 27, 26, 33, -1, 19, 21, 33, 32, 18, 28, 33, 10, 18, 28, 33, 9, 18, 28, 34, 47, 28, 22, 34, 62, 30, 28, 34, 0, 27, 1, 34, -1, 30, 21, 34, 32, 28, 34, 34, 10, 28, 34, 34, 9, 28, 34, 35, 0, 13, 35, 35, -1, 13, 21};
    private static final String[] i = new String[]{"", "A_ADUP", "A_ADUP_SAVE", "A_ADUP_STAGC", "A_ANAME", "A_ANAME_ADUP", "A_ANAME_ADUP_STAGC", "A_AVAL", "A_AVAL_STAGC", "A_CDATA", "A_CMNT", "A_DECL", "A_EMPTYTAG", "A_ENTITY", "A_ENTITY_START", "A_ETAG", "A_GI", "A_GI_STAGC", "A_LT", "A_LT_PCDATA", "A_MINUS", "A_MINUS2", "A_MINUS3", "A_PCDATA", "A_PI", "A_PITARGET", "A_PITARGET_PI", "A_SAVE", "A_SKIP", "A_SP", "A_STAGC", "A_UNGET", "A_UNSAVE_PCDATA"};
    private static final String[] j = new String[]{"", "S_ANAME", "S_APOS", "S_AVAL", "S_BB", "S_BBC", "S_BBCD", "S_BBCDA", "S_BBCDAT", "S_BBCDATA", "S_CDATA", "S_CDATA2", "S_CDSECT", "S_CDSECT1", "S_CDSECT2", "S_COM", "S_COM2", "S_COM3", "S_COM4", "S_DECL", "S_DECL2", "S_DONE", "S_EMPTYTAG", "S_ENT", "S_EQ", "S_ETAG", "S_GI", "S_NCR", "S_PCDATA", "S_PI", "S_PITARGET", "S_QUOT", "S_STAGC", "S_TAG", "S_TAGWS", "S_XNCR"};
    int a;
    int b;
    char[] c = new char[200];
    int d;
    int[] e = new int[]{8364, 65533, 8218, 402, 8222, 8230, 8224, 8225, 710, 8240, TinkerReport.KEY_LOADED_PACKAGE_CHECK_LIB_META, 8249, 338, 65533, 381, 65533, 65533, 8216, 8217, 8220, 8221, 8226, 8211, 8212, 732, 8482, TinkerReport.KEY_LOADED_PACKAGE_CHECK_APK_TINKER_ID_NOT_FOUND, 8250, 339, 65533, 382, 376};
    private String k;
    private String l;
    private int m;
    private int n;
    private int o;
    private int p;

    static {
        int i;
        int i2 = -1;
        int i3 = -1;
        for (i = 0; i < h.length; i += 4) {
            if (h[i] > i3) {
                i3 = h[i];
            }
            if (h[i + 1] > i2) {
                i2 = h[i + 1];
            }
        }
        g = i2 + 1;
        f = (short[][]) Array.newInstance(Short.TYPE, new int[]{i3 + 1, i2 + 3});
        for (int i4 = 0; i4 <= i3; i4++) {
            for (int i5 = -2; i5 <= i2; i5++) {
                int i6 = 0;
                int i7 = -1;
                for (i = 0; i < h.length; i += 4) {
                    if (i4 != h[i]) {
                        if (i6 != 0) {
                            break;
                        }
                    } else if (h[i + 1] == 0) {
                        i6 = h[i + 2];
                        i7 = i;
                    } else if (h[i + 1] == i5) {
                        i6 = h[i + 2];
                        i7 = i;
                        break;
                    }
                }
                f[i4][i5 + 2] = (short) i7;
            }
        }
    }

    private void a(PushbackReader pushbackReader, int i) throws IOException {
        if (i != -1) {
            pushbackReader.unread(i);
        }
    }

    public int getLineNumber() {
        return this.m;
    }

    public int getColumnNumber() {
        return this.n;
    }

    public String getPublicId() {
        return this.k;
    }

    public String getSystemId() {
        return this.l;
    }

    public void a(String str, String str2) {
        this.k = str;
        this.l = str2;
        this.p = 0;
        this.o = 0;
        this.n = 0;
        this.m = 0;
    }

    public void a(Reader reader, i iVar) throws IOException, SAXException {
        PushbackReader pushbackReader;
        this.a = 28;
        if (reader instanceof BufferedReader) {
            pushbackReader = new PushbackReader(reader, 5);
        } else {
            pushbackReader = new PushbackReader(new BufferedReader(reader), 5);
        }
        int read = pushbackReader.read();
        if (read != 65279) {
            a(pushbackReader, read);
        }
        while (this.a != 21) {
            int read2 = pushbackReader.read();
            if (read2 >= 128 && read2 <= 159) {
                read2 = this.e[read2 - 128];
            }
            if (read2 == 13) {
                read2 = pushbackReader.read();
                if (read2 != 10) {
                    a(pushbackReader, read2);
                    read2 = 10;
                }
            }
            if (read2 == 10) {
                this.o++;
                this.p = 0;
            } else {
                this.p++;
            }
            if (read2 >= 32 || read2 == 10 || read2 == 9 || read2 == -1) {
                read = (read2 < -1 || read2 >= g) ? -2 : read2;
                short s = f[this.a][read + 2];
                if (s != (short) -1) {
                    read = h[s + 2];
                    this.b = h[s + 3];
                } else {
                    read = 0;
                }
                switch (read) {
                    case 0:
                        throw new Error(new StringBuffer().append("HTMLScanner can't cope with ").append(Integer.toString(read2)).append(" in state ").append(Integer.toString(this.a)).toString());
                    case 1:
                        iVar.a(this.c, 0, this.d);
                        this.d = 0;
                        break;
                    case 2:
                        iVar.a(this.c, 0, this.d);
                        this.d = 0;
                        a(read2, iVar);
                        break;
                    case 3:
                        iVar.a(this.c, 0, this.d);
                        this.d = 0;
                        iVar.n(this.c, 0, this.d);
                        break;
                    case 4:
                        iVar.b(this.c, 0, this.d);
                        this.d = 0;
                        break;
                    case 5:
                        iVar.b(this.c, 0, this.d);
                        this.d = 0;
                        iVar.a(this.c, 0, this.d);
                        break;
                    case 6:
                        iVar.b(this.c, 0, this.d);
                        this.d = 0;
                        iVar.a(this.c, 0, this.d);
                        iVar.n(this.c, 0, this.d);
                        break;
                    case 7:
                        iVar.c(this.c, 0, this.d);
                        this.d = 0;
                        break;
                    case 8:
                        iVar.c(this.c, 0, this.d);
                        this.d = 0;
                        iVar.n(this.c, 0, this.d);
                        break;
                    case 9:
                        b();
                        if (this.d > 1) {
                            this.d -= 2;
                        }
                        iVar.k(this.c, 0, this.d);
                        this.d = 0;
                        break;
                    case 10:
                        b();
                        iVar.p(this.c, 0, this.d);
                        this.d = 0;
                        break;
                    case 11:
                        iVar.i(this.c, 0, this.d);
                        this.d = 0;
                        break;
                    case 12:
                        b();
                        if (this.d > 0) {
                            iVar.j(this.c, 0, this.d);
                        }
                        this.d = 0;
                        iVar.o(this.c, 0, this.d);
                        break;
                    case 13:
                        b();
                        char c = (char) read2;
                        if (this.a != 23 || c != '#') {
                            if (this.a != 27 || (c != 'x' && c != 'X')) {
                                if (this.a != 23 || !Character.isLetterOrDigit(c)) {
                                    if (this.a != 27 || !Character.isDigit(c)) {
                                        if (this.a == 35 && (Character.isDigit(c) || "abcdefABCDEF".indexOf(c) != -1)) {
                                            a(read2, iVar);
                                            break;
                                        }
                                        iVar.d(this.c, 1, this.d - 1);
                                        read = iVar.a();
                                        if (read != 0) {
                                            this.d = 0;
                                            if (read >= 128 && read <= 159) {
                                                read = this.e[read - 128];
                                            }
                                            if (read >= 32 && (read < 55296 || read > 57343)) {
                                                if (read <= 65535) {
                                                    a(read, iVar);
                                                } else {
                                                    read -= 65536;
                                                    a((read >> 10) + 55296, iVar);
                                                    a((read & 1023) + 56320, iVar);
                                                }
                                            }
                                            if (read2 != 59) {
                                                a(pushbackReader, read2);
                                                this.p--;
                                            }
                                        } else {
                                            a(pushbackReader, read2);
                                            this.p--;
                                        }
                                        this.b = 28;
                                        break;
                                    }
                                    a(read2, iVar);
                                    break;
                                }
                                a(read2, iVar);
                                break;
                            }
                            this.b = 35;
                            a(read2, iVar);
                            break;
                        }
                        this.b = 27;
                        a(read2, iVar);
                        break;
                        break;
                    case 14:
                        iVar.k(this.c, 0, this.d);
                        this.d = 0;
                        a(read2, iVar);
                        break;
                    case 15:
                        iVar.f(this.c, 0, this.d);
                        this.d = 0;
                        break;
                    case 16:
                        iVar.j(this.c, 0, this.d);
                        this.d = 0;
                        break;
                    case 17:
                        iVar.j(this.c, 0, this.d);
                        this.d = 0;
                        iVar.n(this.c, 0, this.d);
                        break;
                    case 18:
                        b();
                        a(60, iVar);
                        a(read2, iVar);
                        break;
                    case 19:
                        b();
                        a(60, iVar);
                        iVar.k(this.c, 0, this.d);
                        this.d = 0;
                        break;
                    case 20:
                        break;
                    case 21:
                        a(45, iVar);
                        a(32, iVar);
                        break;
                    case 22:
                        a(45, iVar);
                        a(32, iVar);
                        break;
                    case 23:
                        b();
                        iVar.k(this.c, 0, this.d);
                        this.d = 0;
                        break;
                    case 24:
                        b();
                        iVar.m(this.c, 0, this.d);
                        this.d = 0;
                        break;
                    case 25:
                        iVar.l(this.c, 0, this.d);
                        this.d = 0;
                        break;
                    case 26:
                        iVar.l(this.c, 0, this.d);
                        this.d = 0;
                        iVar.m(this.c, 0, this.d);
                        break;
                    case 27:
                        a(read2, iVar);
                        break;
                    case 28:
                        break;
                    case 29:
                        a(32, iVar);
                        break;
                    case 30:
                        iVar.n(this.c, 0, this.d);
                        this.d = 0;
                        break;
                    case 31:
                        a(pushbackReader, read2);
                        this.p--;
                        break;
                    case 32:
                        if (this.d > 0) {
                            this.d--;
                        }
                        iVar.k(this.c, 0, this.d);
                        this.d = 0;
                        break;
                    default:
                        throw new Error(new StringBuffer().append("Can't process state ").append(read).toString());
                }
                a(45, iVar);
                a(read2, iVar);
                this.a = this.b;
                continue;
            }
        }
        iVar.e(this.c, 0, 0);
    }

    private void b() {
        this.n = this.p;
        this.m = this.o;
    }

    public void a() {
        this.b = 10;
    }

    private void a(int i, i iVar) throws IOException, SAXException {
        if (this.d >= this.c.length - 20) {
            if (this.a == 28 || this.a == 10) {
                iVar.k(this.c, 0, this.d);
                this.d = 0;
            } else {
                Object obj = new char[(this.c.length * 2)];
                System.arraycopy(this.c, 0, obj, 0, this.d + 1);
                this.c = obj;
            }
        }
        char[] cArr = this.c;
        int i2 = this.d;
        this.d = i2 + 1;
        cArr[i2] = (char) i;
    }
}
