package cz.msebera.android.httpclient.impl.auth;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.extras.Base64;
import cz.msebera.android.httpclient.util.CharsetUtils;
import cz.msebera.android.httpclient.util.EncodingUtils;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

@NotThreadSafe
final class NTLMEngineImpl implements NTLMEngine {
    private static final Charset a = CharsetUtils.lookup("UnicodeLittleUnmarked");
    private static final Charset b = Consts.ASCII;
    private static final SecureRandom c;
    private static final byte[] d;
    private static final d e = new d();

    protected static class CipherGen {
        protected final String a;
        protected final String b;
        protected final String c;
        protected final byte[] d;
        protected final String e;
        protected final byte[] f;
        protected byte[] g;
        protected byte[] h;
        protected byte[] i;
        protected byte[] j;
        protected byte[] k;
        protected byte[] l;
        protected byte[] m;
        protected byte[] n;
        protected byte[] o;
        protected byte[] p;
        protected byte[] q;
        protected byte[] r;
        protected byte[] s;
        protected byte[] t;
        protected byte[] u;
        protected byte[] v;
        protected byte[] w;
        protected byte[] x;
        protected byte[] y;
        protected byte[] z;

        public CipherGen(String str, String str2, String str3, byte[] bArr, String str4, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
            this.k = null;
            this.l = null;
            this.m = null;
            this.n = null;
            this.o = null;
            this.p = null;
            this.q = null;
            this.r = null;
            this.s = null;
            this.t = null;
            this.u = null;
            this.v = null;
            this.w = null;
            this.x = null;
            this.y = null;
            this.z = null;
            this.a = str;
            this.e = str4;
            this.b = str2;
            this.c = str3;
            this.d = bArr;
            this.f = bArr2;
            this.g = bArr3;
            this.h = bArr4;
            this.i = bArr5;
            this.j = bArr6;
        }

        public CipherGen(String str, String str2, String str3, byte[] bArr, String str4, byte[] bArr2) {
            this(str, str2, str3, bArr, str4, bArr2, null, null, null, null);
        }

        public byte[] getClientChallenge() throws NTLMEngineException {
            if (this.g == null) {
                this.g = NTLMEngineImpl.f();
            }
            return this.g;
        }

        public byte[] getClientChallenge2() throws NTLMEngineException {
            if (this.h == null) {
                this.h = NTLMEngineImpl.f();
            }
            return this.h;
        }

        public byte[] getSecondaryKey() throws NTLMEngineException {
            if (this.i == null) {
                this.i = NTLMEngineImpl.g();
            }
            return this.i;
        }

        public byte[] getLMHash() throws NTLMEngineException {
            if (this.k == null) {
                this.k = NTLMEngineImpl.h(this.c);
            }
            return this.k;
        }

        public byte[] getLMResponse() throws NTLMEngineException {
            if (this.l == null) {
                this.l = NTLMEngineImpl.d(getLMHash(), this.d);
            }
            return this.l;
        }

        public byte[] getNTLMHash() throws NTLMEngineException {
            if (this.m == null) {
                this.m = NTLMEngineImpl.i(this.c);
            }
            return this.m;
        }

        public byte[] getNTLMResponse() throws NTLMEngineException {
            if (this.n == null) {
                this.n = NTLMEngineImpl.d(getNTLMHash(), this.d);
            }
            return this.n;
        }

        public byte[] getLMv2Hash() throws NTLMEngineException {
            if (this.p == null) {
                this.p = NTLMEngineImpl.c(this.a, this.b, getNTLMHash());
            }
            return this.p;
        }

        public byte[] getNTLMv2Hash() throws NTLMEngineException {
            if (this.o == null) {
                this.o = NTLMEngineImpl.d(this.a, this.b, getNTLMHash());
            }
            return this.o;
        }

        public byte[] getTimestamp() {
            if (this.j == null) {
                long currentTimeMillis = 10000 * (System.currentTimeMillis() + 11644473600000L);
                this.j = new byte[8];
                for (int i = 0; i < 8; i++) {
                    this.j[i] = (byte) ((int) currentTimeMillis);
                    currentTimeMillis >>>= 8;
                }
            }
            return this.j;
        }

        public byte[] getNTLMv2Blob() throws NTLMEngineException {
            if (this.r == null) {
                this.r = NTLMEngineImpl.e(getClientChallenge2(), this.f, getTimestamp());
            }
            return this.r;
        }

        public byte[] getNTLMv2Response() throws NTLMEngineException {
            if (this.s == null) {
                this.s = NTLMEngineImpl.d(getNTLMv2Hash(), this.d, getNTLMv2Blob());
            }
            return this.s;
        }

        public byte[] getLMv2Response() throws NTLMEngineException {
            if (this.q == null) {
                this.q = NTLMEngineImpl.d(getLMv2Hash(), this.d, getClientChallenge());
            }
            return this.q;
        }

        public byte[] getNTLM2SessionResponse() throws NTLMEngineException {
            if (this.t == null) {
                this.t = NTLMEngineImpl.a(getNTLMHash(), this.d, getClientChallenge());
            }
            return this.t;
        }

        public byte[] getLM2SessionResponse() throws NTLMEngineException {
            if (this.u == null) {
                Object clientChallenge = getClientChallenge();
                this.u = new byte[24];
                System.arraycopy(clientChallenge, 0, this.u, 0, clientChallenge.length);
                Arrays.fill(this.u, clientChallenge.length, this.u.length, (byte) 0);
            }
            return this.u;
        }

        public byte[] getLMUserSessionKey() throws NTLMEngineException {
            if (this.v == null) {
                this.v = new byte[16];
                System.arraycopy(getLMHash(), 0, this.v, 0, 8);
                Arrays.fill(this.v, 8, 16, (byte) 0);
            }
            return this.v;
        }

        public byte[] getNTLMUserSessionKey() throws NTLMEngineException {
            if (this.w == null) {
                b bVar = new b();
                bVar.a(getNTLMHash());
                this.w = bVar.a();
            }
            return this.w;
        }

        public byte[] getNTLMv2UserSessionKey() throws NTLMEngineException {
            if (this.x == null) {
                byte[] nTLMv2Hash = getNTLMv2Hash();
                byte[] bArr = new byte[16];
                System.arraycopy(getNTLMv2Response(), 0, bArr, 0, 16);
                this.x = NTLMEngineImpl.a(bArr, nTLMv2Hash);
            }
            return this.x;
        }

        public byte[] getNTLM2SessionResponseUserSessionKey() throws NTLMEngineException {
            if (this.y == null) {
                Object lM2SessionResponse = getLM2SessionResponse();
                byte[] bArr = new byte[(this.d.length + lM2SessionResponse.length)];
                System.arraycopy(this.d, 0, bArr, 0, this.d.length);
                System.arraycopy(lM2SessionResponse, 0, bArr, this.d.length, lM2SessionResponse.length);
                this.y = NTLMEngineImpl.a(bArr, getNTLMUserSessionKey());
            }
            return this.y;
        }

        public byte[] getLanManagerSessionKey() throws NTLMEngineException {
            if (this.z == null) {
                try {
                    byte[] bArr = new byte[14];
                    System.arraycopy(getLMHash(), 0, bArr, 0, 8);
                    Arrays.fill(bArr, 8, bArr.length, (byte) -67);
                    Key a = NTLMEngineImpl.g(bArr, 0);
                    Key a2 = NTLMEngineImpl.g(bArr, 7);
                    Object obj = new byte[8];
                    System.arraycopy(getLMResponse(), 0, obj, 0, obj.length);
                    Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
                    instance.init(1, a);
                    Object doFinal = instance.doFinal(obj);
                    instance = Cipher.getInstance("DES/ECB/NoPadding");
                    instance.init(1, a2);
                    Object doFinal2 = instance.doFinal(obj);
                    this.z = new byte[16];
                    System.arraycopy(doFinal, 0, this.z, 0, doFinal.length);
                    System.arraycopy(doFinal2, 0, this.z, doFinal.length, doFinal2.length);
                } catch (Throwable e) {
                    throw new NTLMEngineException(e.getMessage(), e);
                }
            }
            return this.z;
        }
    }

    static class a {
        protected byte[] a;
        protected byte[] b;
        protected MessageDigest c;

        a(byte[] bArr) throws NTLMEngineException {
            try {
                this.c = MessageDigest.getInstance("MD5");
                this.a = new byte[64];
                this.b = new byte[64];
                int length = bArr.length;
                if (length > 64) {
                    this.c.update(bArr);
                    bArr = this.c.digest();
                    length = bArr.length;
                }
                int i = 0;
                while (i < length) {
                    this.a[i] = (byte) (bArr[i] ^ 54);
                    this.b[i] = (byte) (bArr[i] ^ 92);
                    i++;
                }
                for (length = i; length < 64; length++) {
                    this.a[length] = (byte) 54;
                    this.b[length] = (byte) 92;
                }
                this.c.reset();
                this.c.update(this.a);
            } catch (Throwable e) {
                throw new NTLMEngineException("Error getting md5 message digest implementation: " + e.getMessage(), e);
            }
        }

        byte[] a() {
            byte[] digest = this.c.digest();
            this.c.update(this.b);
            return this.c.digest(digest);
        }

        void a(byte[] bArr) {
            this.c.update(bArr);
        }
    }

    static class b {
        protected int a = 1732584193;
        protected int b = -271733879;
        protected int c = -1732584194;
        protected int d = 271733878;
        protected long e = 0;
        protected byte[] f = new byte[64];

        b() {
        }

        void a(byte[] bArr) {
            int i = (int) (this.e & 63);
            int i2 = 0;
            while ((bArr.length - i2) + i >= this.f.length) {
                int length = this.f.length - i;
                System.arraycopy(bArr, i2, this.f, i, length);
                this.e += (long) length;
                i2 += length;
                b();
                i = 0;
            }
            if (i2 < bArr.length) {
                int length2 = bArr.length - i2;
                System.arraycopy(bArr, i2, this.f, i, length2);
                this.e += (long) length2;
                i2 = i + length2;
            }
        }

        byte[] a() {
            int i = (int) (this.e & 63);
            i = i < 56 ? 56 - i : 120 - i;
            byte[] bArr = new byte[(i + 8)];
            bArr[0] = Byte.MIN_VALUE;
            for (int i2 = 0; i2 < 8; i2++) {
                bArr[i + i2] = (byte) ((int) ((this.e * 8) >>> (i2 * 8)));
            }
            a(bArr);
            byte[] bArr2 = new byte[16];
            NTLMEngineImpl.a(bArr2, this.a, 0);
            NTLMEngineImpl.a(bArr2, this.b, 4);
            NTLMEngineImpl.a(bArr2, this.c, 8);
            NTLMEngineImpl.a(bArr2, this.d, 12);
            return bArr2;
        }

        protected void b() {
            int i;
            int[] iArr = new int[16];
            for (i = 0; i < 16; i++) {
                iArr[i] = (((this.f[i * 4] & 255) + ((this.f[(i * 4) + 1] & 255) << 8)) + ((this.f[(i * 4) + 2] & 255) << 16)) + ((this.f[(i * 4) + 3] & 255) << 24);
            }
            i = this.a;
            int i2 = this.b;
            int i3 = this.c;
            int i4 = this.d;
            a(iArr);
            b(iArr);
            c(iArr);
            this.a = i + this.a;
            this.b += i2;
            this.c += i3;
            this.d += i4;
        }

        protected void a(int[] iArr) {
            this.a = NTLMEngineImpl.a((this.a + NTLMEngineImpl.a(this.b, this.c, this.d)) + iArr[0], 3);
            this.d = NTLMEngineImpl.a((this.d + NTLMEngineImpl.a(this.a, this.b, this.c)) + iArr[1], 7);
            this.c = NTLMEngineImpl.a((this.c + NTLMEngineImpl.a(this.d, this.a, this.b)) + iArr[2], 11);
            this.b = NTLMEngineImpl.a((this.b + NTLMEngineImpl.a(this.c, this.d, this.a)) + iArr[3], 19);
            this.a = NTLMEngineImpl.a((this.a + NTLMEngineImpl.a(this.b, this.c, this.d)) + iArr[4], 3);
            this.d = NTLMEngineImpl.a((this.d + NTLMEngineImpl.a(this.a, this.b, this.c)) + iArr[5], 7);
            this.c = NTLMEngineImpl.a((this.c + NTLMEngineImpl.a(this.d, this.a, this.b)) + iArr[6], 11);
            this.b = NTLMEngineImpl.a((this.b + NTLMEngineImpl.a(this.c, this.d, this.a)) + iArr[7], 19);
            this.a = NTLMEngineImpl.a((this.a + NTLMEngineImpl.a(this.b, this.c, this.d)) + iArr[8], 3);
            this.d = NTLMEngineImpl.a((this.d + NTLMEngineImpl.a(this.a, this.b, this.c)) + iArr[9], 7);
            this.c = NTLMEngineImpl.a((this.c + NTLMEngineImpl.a(this.d, this.a, this.b)) + iArr[10], 11);
            this.b = NTLMEngineImpl.a((this.b + NTLMEngineImpl.a(this.c, this.d, this.a)) + iArr[11], 19);
            this.a = NTLMEngineImpl.a((this.a + NTLMEngineImpl.a(this.b, this.c, this.d)) + iArr[12], 3);
            this.d = NTLMEngineImpl.a((this.d + NTLMEngineImpl.a(this.a, this.b, this.c)) + iArr[13], 7);
            this.c = NTLMEngineImpl.a((this.c + NTLMEngineImpl.a(this.d, this.a, this.b)) + iArr[14], 11);
            this.b = NTLMEngineImpl.a((this.b + NTLMEngineImpl.a(this.c, this.d, this.a)) + iArr[15], 19);
        }

        protected void b(int[] iArr) {
            this.a = NTLMEngineImpl.a(((this.a + NTLMEngineImpl.b(this.b, this.c, this.d)) + iArr[0]) + 1518500249, 3);
            this.d = NTLMEngineImpl.a(((this.d + NTLMEngineImpl.b(this.a, this.b, this.c)) + iArr[4]) + 1518500249, 5);
            this.c = NTLMEngineImpl.a(((this.c + NTLMEngineImpl.b(this.d, this.a, this.b)) + iArr[8]) + 1518500249, 9);
            this.b = NTLMEngineImpl.a(((this.b + NTLMEngineImpl.b(this.c, this.d, this.a)) + iArr[12]) + 1518500249, 13);
            this.a = NTLMEngineImpl.a(((this.a + NTLMEngineImpl.b(this.b, this.c, this.d)) + iArr[1]) + 1518500249, 3);
            this.d = NTLMEngineImpl.a(((this.d + NTLMEngineImpl.b(this.a, this.b, this.c)) + iArr[5]) + 1518500249, 5);
            this.c = NTLMEngineImpl.a(((this.c + NTLMEngineImpl.b(this.d, this.a, this.b)) + iArr[9]) + 1518500249, 9);
            this.b = NTLMEngineImpl.a(((this.b + NTLMEngineImpl.b(this.c, this.d, this.a)) + iArr[13]) + 1518500249, 13);
            this.a = NTLMEngineImpl.a(((this.a + NTLMEngineImpl.b(this.b, this.c, this.d)) + iArr[2]) + 1518500249, 3);
            this.d = NTLMEngineImpl.a(((this.d + NTLMEngineImpl.b(this.a, this.b, this.c)) + iArr[6]) + 1518500249, 5);
            this.c = NTLMEngineImpl.a(((this.c + NTLMEngineImpl.b(this.d, this.a, this.b)) + iArr[10]) + 1518500249, 9);
            this.b = NTLMEngineImpl.a(((this.b + NTLMEngineImpl.b(this.c, this.d, this.a)) + iArr[14]) + 1518500249, 13);
            this.a = NTLMEngineImpl.a(((this.a + NTLMEngineImpl.b(this.b, this.c, this.d)) + iArr[3]) + 1518500249, 3);
            this.d = NTLMEngineImpl.a(((this.d + NTLMEngineImpl.b(this.a, this.b, this.c)) + iArr[7]) + 1518500249, 5);
            this.c = NTLMEngineImpl.a(((this.c + NTLMEngineImpl.b(this.d, this.a, this.b)) + iArr[11]) + 1518500249, 9);
            this.b = NTLMEngineImpl.a(((this.b + NTLMEngineImpl.b(this.c, this.d, this.a)) + iArr[15]) + 1518500249, 13);
        }

        protected void c(int[] iArr) {
            this.a = NTLMEngineImpl.a(((this.a + NTLMEngineImpl.c(this.b, this.c, this.d)) + iArr[0]) + 1859775393, 3);
            this.d = NTLMEngineImpl.a(((this.d + NTLMEngineImpl.c(this.a, this.b, this.c)) + iArr[8]) + 1859775393, 9);
            this.c = NTLMEngineImpl.a(((this.c + NTLMEngineImpl.c(this.d, this.a, this.b)) + iArr[4]) + 1859775393, 11);
            this.b = NTLMEngineImpl.a(((this.b + NTLMEngineImpl.c(this.c, this.d, this.a)) + iArr[12]) + 1859775393, 15);
            this.a = NTLMEngineImpl.a(((this.a + NTLMEngineImpl.c(this.b, this.c, this.d)) + iArr[2]) + 1859775393, 3);
            this.d = NTLMEngineImpl.a(((this.d + NTLMEngineImpl.c(this.a, this.b, this.c)) + iArr[10]) + 1859775393, 9);
            this.c = NTLMEngineImpl.a(((this.c + NTLMEngineImpl.c(this.d, this.a, this.b)) + iArr[6]) + 1859775393, 11);
            this.b = NTLMEngineImpl.a(((this.b + NTLMEngineImpl.c(this.c, this.d, this.a)) + iArr[14]) + 1859775393, 15);
            this.a = NTLMEngineImpl.a(((this.a + NTLMEngineImpl.c(this.b, this.c, this.d)) + iArr[1]) + 1859775393, 3);
            this.d = NTLMEngineImpl.a(((this.d + NTLMEngineImpl.c(this.a, this.b, this.c)) + iArr[9]) + 1859775393, 9);
            this.c = NTLMEngineImpl.a(((this.c + NTLMEngineImpl.c(this.d, this.a, this.b)) + iArr[5]) + 1859775393, 11);
            this.b = NTLMEngineImpl.a(((this.b + NTLMEngineImpl.c(this.c, this.d, this.a)) + iArr[13]) + 1859775393, 15);
            this.a = NTLMEngineImpl.a(((this.a + NTLMEngineImpl.c(this.b, this.c, this.d)) + iArr[3]) + 1859775393, 3);
            this.d = NTLMEngineImpl.a(((this.d + NTLMEngineImpl.c(this.a, this.b, this.c)) + iArr[11]) + 1859775393, 9);
            this.c = NTLMEngineImpl.a(((this.c + NTLMEngineImpl.c(this.d, this.a, this.b)) + iArr[7]) + 1859775393, 11);
            this.b = NTLMEngineImpl.a(((this.b + NTLMEngineImpl.c(this.c, this.d, this.a)) + iArr[15]) + 1859775393, 15);
        }
    }

    static class c {
        private byte[] a = null;
        private int b = 0;

        c() {
        }

        c(String str, int i) throws NTLMEngineException {
            int i2 = 0;
            this.a = Base64.decode(str.getBytes(NTLMEngineImpl.b), 2);
            if (this.a.length < NTLMEngineImpl.d.length) {
                throw new NTLMEngineException("NTLM message decoding error - packet too short");
            }
            while (i2 < NTLMEngineImpl.d.length) {
                if (this.a[i2] != NTLMEngineImpl.d[i2]) {
                    throw new NTLMEngineException("NTLM message expected - instead got unrecognized bytes");
                }
                i2++;
            }
            i2 = a(NTLMEngineImpl.d.length);
            if (i2 != i) {
                throw new NTLMEngineException("NTLM type " + Integer.toString(i) + " message expected - instead got type " + Integer.toString(i2));
            }
            this.b = this.a.length;
        }

        protected int a() {
            return this.b;
        }

        protected void a(byte[] bArr, int i) throws NTLMEngineException {
            if (this.a.length < bArr.length + i) {
                throw new NTLMEngineException("NTLM: Message too short");
            }
            System.arraycopy(this.a, i, bArr, 0, bArr.length);
        }

        protected int a(int i) throws NTLMEngineException {
            return NTLMEngineImpl.d(this.a, i);
        }

        protected byte[] b(int i) throws NTLMEngineException {
            return NTLMEngineImpl.f(this.a, i);
        }

        protected void a(int i, int i2) {
            this.a = new byte[i];
            this.b = 0;
            a(NTLMEngineImpl.d);
            d(i2);
        }

        protected void a(byte b) {
            this.a[this.b] = b;
            this.b++;
        }

        protected void a(byte[] bArr) {
            if (bArr != null) {
                for (byte b : bArr) {
                    this.a[this.b] = b;
                    this.b++;
                }
            }
        }

        protected void c(int i) {
            a((byte) (i & 255));
            a((byte) ((i >> 8) & 255));
        }

        protected void d(int i) {
            a((byte) (i & 255));
            a((byte) ((i >> 8) & 255));
            a((byte) ((i >> 16) & 255));
            a((byte) ((i >> 24) & 255));
        }

        String b() {
            byte[] bArr;
            if (this.a.length > this.b) {
                bArr = new byte[this.b];
                System.arraycopy(this.a, 0, bArr, 0, this.b);
            } else {
                bArr = this.a;
            }
            return EncodingUtils.getAsciiString(Base64.encode(bArr, 2));
        }
    }

    static class d extends c {
        private final byte[] a = null;
        private final byte[] b = null;

        d() {
        }

        String b() {
            a(40, 1);
            d(-1576500735);
            c(0);
            c(0);
            d(40);
            c(0);
            c(0);
            d(40);
            c(261);
            d(2600);
            c(3840);
            if (this.a != null) {
                a(this.a);
            }
            if (this.b != null) {
                a(this.b);
            }
            return super.b();
        }
    }

    static class e extends c {
        protected byte[] a = new byte[8];
        protected String b;
        protected byte[] c;
        protected int d;

        e(String str) throws NTLMEngineException {
            super(str, 2);
            a(this.a, 24);
            this.d = a(20);
            if ((this.d & 1) == 0) {
                throw new NTLMEngineException("NTLM type 2 message indicates no support for Unicode. Flags are: " + Integer.toString(this.d));
            }
            byte[] b;
            this.b = null;
            if (a() >= 20) {
                b = b(12);
                if (b.length != 0) {
                    try {
                        this.b = new String(b, "UnicodeLittleUnmarked");
                    } catch (Throwable e) {
                        throw new NTLMEngineException(e.getMessage(), e);
                    }
                }
            }
            this.c = null;
            if (a() >= 48) {
                b = b(40);
                if (b.length != 0) {
                    this.c = b;
                }
            }
        }

        byte[] c() {
            return this.a;
        }

        String d() {
            return this.b;
        }

        byte[] e() {
            return this.c;
        }

        int f() {
            return this.d;
        }
    }

    static class f extends c {
        protected int a;
        protected byte[] b;
        protected byte[] c;
        protected byte[] d;
        protected byte[] e;
        protected byte[] f;
        protected byte[] g;

        f(String str, String str2, String str3, String str4, byte[] bArr, int i, String str5, byte[] bArr2) throws NTLMEngineException {
            byte[] lanManagerSessionKey;
            this.a = i;
            String c = NTLMEngineImpl.f(str2);
            String d = NTLMEngineImpl.g(str);
            CipherGen cipherGen = new CipherGen(d, str3, str4, bArr, str5, bArr2);
            if ((8388608 & i) != 0 && bArr2 != null && str5 != null) {
                try {
                    this.f = cipherGen.getNTLMv2Response();
                    this.e = cipherGen.getLMv2Response();
                    if ((i & 128) != 0) {
                        lanManagerSessionKey = cipherGen.getLanManagerSessionKey();
                    } else {
                        lanManagerSessionKey = cipherGen.getNTLMv2UserSessionKey();
                    }
                } catch (NTLMEngineException e) {
                    this.f = new byte[0];
                    this.e = cipherGen.getLMResponse();
                    if ((i & 128) != 0) {
                        lanManagerSessionKey = cipherGen.getLanManagerSessionKey();
                    } else {
                        lanManagerSessionKey = cipherGen.getLMUserSessionKey();
                    }
                }
            } else if ((524288 & i) != 0) {
                this.f = cipherGen.getNTLM2SessionResponse();
                this.e = cipherGen.getLM2SessionResponse();
                if ((i & 128) != 0) {
                    lanManagerSessionKey = cipherGen.getLanManagerSessionKey();
                } else {
                    lanManagerSessionKey = cipherGen.getNTLM2SessionResponseUserSessionKey();
                }
            } else {
                this.f = cipherGen.getNTLMResponse();
                this.e = cipherGen.getLMResponse();
                if ((i & 128) != 0) {
                    lanManagerSessionKey = cipherGen.getLanManagerSessionKey();
                } else {
                    lanManagerSessionKey = cipherGen.getNTLMUserSessionKey();
                }
            }
            if ((i & 16) == 0) {
                this.g = null;
            } else if ((1073741824 & i) != 0) {
                this.g = NTLMEngineImpl.b(cipherGen.getSecondaryKey(), lanManagerSessionKey);
            } else {
                this.g = lanManagerSessionKey;
            }
            if (NTLMEngineImpl.a == null) {
                throw new NTLMEngineException("Unicode not supported");
            }
            this.c = c != null ? c.getBytes(NTLMEngineImpl.a) : null;
            this.b = d != null ? d.toUpperCase(Locale.ROOT).getBytes(NTLMEngineImpl.a) : null;
            this.d = str3.getBytes(NTLMEngineImpl.a);
        }

        String b() {
            int length;
            int i = 0;
            int length2 = this.f.length;
            int length3 = this.e.length;
            int length4 = this.b != null ? this.b.length : 0;
            if (this.c != null) {
                length = this.c.length;
            } else {
                length = 0;
            }
            int length5 = this.d.length;
            if (this.g != null) {
                i = this.g.length;
            }
            int i2 = length3 + 72;
            int i3 = i2 + length2;
            int i4 = i3 + length4;
            int i5 = i4 + length5;
            int i6 = i5 + length;
            a(i6 + i, 3);
            c(length3);
            c(length3);
            d(72);
            c(length2);
            c(length2);
            d(i2);
            c(length4);
            c(length4);
            d(i3);
            c(length5);
            c(length5);
            d(i4);
            c(length);
            c(length);
            d(i5);
            c(i);
            c(i);
            d(i6);
            d(((((((((((((this.a & 128) | (this.a & 512)) | (this.a & 524288)) | 33554432) | (this.a & 32768)) | (this.a & 32)) | (this.a & 16)) | (this.a & 536870912)) | (this.a & Integer.MIN_VALUE)) | (this.a & 1073741824)) | (this.a & 8388608)) | (this.a & 1)) | (this.a & 4));
            c(261);
            d(2600);
            c(3840);
            a(this.e);
            a(this.f);
            a(this.b);
            a(this.d);
            a(this.c);
            if (this.g != null) {
                a(this.g);
            }
            return super.b();
        }
    }

    NTLMEngineImpl() {
    }

    static {
        SecureRandom secureRandom = null;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (Exception e) {
        }
        c = secureRandom;
        Object bytes = "NTLMSSP".getBytes(Consts.ASCII);
        d = new byte[(bytes.length + 1)];
        System.arraycopy(bytes, 0, d, 0, bytes.length);
        d[bytes.length] = (byte) 0;
    }

    static String a(String str, String str2) throws NTLMEngineException {
        return e.b();
    }

    static String a(String str, String str2, String str3, String str4, byte[] bArr, int i, String str5, byte[] bArr2) throws NTLMEngineException {
        return new f(str4, str3, str, str2, bArr, i, str5, bArr2).b();
    }

    private static String e(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(".");
        if (indexOf != -1) {
            return str.substring(0, indexOf);
        }
        return str;
    }

    private static String f(String str) {
        return e(str);
    }

    private static String g(String str) {
        return e(str);
    }

    private static int d(byte[] bArr, int i) throws NTLMEngineException {
        if (bArr.length >= i + 4) {
            return (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16)) | ((bArr[i + 3] & 255) << 24);
        }
        throw new NTLMEngineException("NTLM authentication - buffer too small for DWORD");
    }

    private static int e(byte[] bArr, int i) throws NTLMEngineException {
        if (bArr.length >= i + 2) {
            return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8);
        }
        throw new NTLMEngineException("NTLM authentication - buffer too small for WORD");
    }

    private static byte[] f(byte[] bArr, int i) throws NTLMEngineException {
        int e = e(bArr, i);
        int d = d(bArr, i + 4);
        if (bArr.length < d + e) {
            throw new NTLMEngineException("NTLM authentication - buffer too small for data item");
        }
        Object obj = new byte[e];
        System.arraycopy(bArr, d, obj, 0, e);
        return obj;
    }

    private static byte[] f() throws NTLMEngineException {
        if (c == null) {
            throw new NTLMEngineException("Random generator not available");
        }
        byte[] bArr = new byte[8];
        synchronized (c) {
            c.nextBytes(bArr);
        }
        return bArr;
    }

    private static byte[] g() throws NTLMEngineException {
        if (c == null) {
            throw new NTLMEngineException("Random generator not available");
        }
        byte[] bArr = new byte[16];
        synchronized (c) {
            c.nextBytes(bArr);
        }
        return bArr;
    }

    static byte[] a(byte[] bArr, byte[] bArr2) throws NTLMEngineException {
        a aVar = new a(bArr2);
        aVar.a(bArr);
        return aVar.a();
    }

    static byte[] b(byte[] bArr, byte[] bArr2) throws NTLMEngineException {
        try {
            Cipher instance = Cipher.getInstance("RC4");
            instance.init(1, new SecretKeySpec(bArr2, "RC4"));
            return instance.doFinal(bArr);
        } catch (Throwable e) {
            throw new NTLMEngineException(e.getMessage(), e);
        }
    }

    static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) throws NTLMEngineException {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr2);
            instance.update(bArr3);
            byte[] bArr4 = new byte[8];
            System.arraycopy(instance.digest(), 0, bArr4, 0, 8);
            return d(bArr, bArr4);
        } catch (Throwable e) {
            if (e instanceof NTLMEngineException) {
                throw ((NTLMEngineException) e);
            }
            throw new NTLMEngineException(e.getMessage(), e);
        }
    }

    private static byte[] h(String str) throws NTLMEngineException {
        try {
            Object bytes = str.toUpperCase(Locale.ROOT).getBytes(Consts.ASCII);
            Object obj = new byte[14];
            System.arraycopy(bytes, 0, obj, 0, Math.min(bytes.length, 14));
            Key g = g(obj, 0);
            Key g2 = g(obj, 7);
            byte[] bytes2 = "KGS!@#$%".getBytes(Consts.ASCII);
            Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
            instance.init(1, g);
            bytes = instance.doFinal(bytes2);
            instance.init(1, g2);
            Object doFinal = instance.doFinal(bytes2);
            obj = new byte[16];
            System.arraycopy(bytes, 0, obj, 0, 8);
            System.arraycopy(doFinal, 0, obj, 8, 8);
            return obj;
        } catch (Throwable e) {
            throw new NTLMEngineException(e.getMessage(), e);
        }
    }

    private static byte[] i(String str) throws NTLMEngineException {
        if (a == null) {
            throw new NTLMEngineException("Unicode not supported");
        }
        byte[] bytes = str.getBytes(a);
        b bVar = new b();
        bVar.a(bytes);
        return bVar.a();
    }

    private static byte[] c(String str, String str2, byte[] bArr) throws NTLMEngineException {
        if (a == null) {
            throw new NTLMEngineException("Unicode not supported");
        }
        a aVar = new a(bArr);
        aVar.a(str2.toUpperCase(Locale.ROOT).getBytes(a));
        if (str != null) {
            aVar.a(str.toUpperCase(Locale.ROOT).getBytes(a));
        }
        return aVar.a();
    }

    private static byte[] d(String str, String str2, byte[] bArr) throws NTLMEngineException {
        if (a == null) {
            throw new NTLMEngineException("Unicode not supported");
        }
        a aVar = new a(bArr);
        aVar.a(str2.toUpperCase(Locale.ROOT).getBytes(a));
        if (str != null) {
            aVar.a(str.getBytes(a));
        }
        return aVar.a();
    }

    private static byte[] d(byte[] bArr, byte[] bArr2) throws NTLMEngineException {
        try {
            Object obj = new byte[21];
            System.arraycopy(bArr, 0, obj, 0, 16);
            Key g = g(obj, 0);
            Key g2 = g(obj, 7);
            Key g3 = g(obj, 14);
            Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
            instance.init(1, g);
            Object doFinal = instance.doFinal(bArr2);
            instance.init(1, g2);
            Object doFinal2 = instance.doFinal(bArr2);
            instance.init(1, g3);
            obj = instance.doFinal(bArr2);
            Object obj2 = new byte[24];
            System.arraycopy(doFinal, 0, obj2, 0, 8);
            System.arraycopy(doFinal2, 0, obj2, 8, 8);
            System.arraycopy(obj, 0, obj2, 16, 8);
            return obj2;
        } catch (Throwable e) {
            throw new NTLMEngineException(e.getMessage(), e);
        }
    }

    private static byte[] d(byte[] bArr, byte[] bArr2, byte[] bArr3) throws NTLMEngineException {
        a aVar = new a(bArr);
        aVar.a(bArr2);
        aVar.a(bArr3);
        Object a = aVar.a();
        Object obj = new byte[(a.length + bArr3.length)];
        System.arraycopy(a, 0, obj, 0, a.length);
        System.arraycopy(bArr3, 0, obj, a.length, bArr3.length);
        return obj;
    }

    private static byte[] e(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        Object obj = new byte[]{(byte) 1, (byte) 1, (byte) 0, (byte) 0};
        Object obj2 = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
        Object obj3 = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
        Object obj4 = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
        Object obj5 = new byte[((((((obj.length + obj2.length) + bArr3.length) + 8) + obj3.length) + bArr2.length) + obj4.length)];
        System.arraycopy(obj, 0, obj5, 0, obj.length);
        int length = obj.length + 0;
        System.arraycopy(obj2, 0, obj5, length, obj2.length);
        length += obj2.length;
        System.arraycopy(bArr3, 0, obj5, length, bArr3.length);
        length += bArr3.length;
        System.arraycopy(bArr, 0, obj5, length, 8);
        length += 8;
        System.arraycopy(obj3, 0, obj5, length, obj3.length);
        length += obj3.length;
        System.arraycopy(bArr2, 0, obj5, length, bArr2.length);
        length += bArr2.length;
        System.arraycopy(obj4, 0, obj5, length, obj4.length);
        length += obj4.length;
        return obj5;
    }

    private static Key g(byte[] bArr, int i) {
        r0 = new byte[7];
        System.arraycopy(bArr, i, r0, 0, 7);
        byte[] bArr2 = new byte[]{r0[0], (byte) ((r0[0] << 7) | ((r0[1] & 255) >>> 1)), (byte) ((r0[1] << 6) | ((r0[2] & 255) >>> 2)), (byte) ((r0[2] << 5) | ((r0[3] & 255) >>> 3)), (byte) ((r0[3] << 4) | ((r0[4] & 255) >>> 4)), (byte) ((r0[4] << 3) | ((r0[5] & 255) >>> 5)), (byte) ((r0[5] << 2) | ((r0[6] & 255) >>> 6)), (byte) (r0[6] << 1)};
        a(bArr2);
        return new SecretKeySpec(bArr2, "DES");
    }

    private static void a(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            Object obj;
            byte b = bArr[i];
            if ((((b >>> 1) ^ ((((((b >>> 7) ^ (b >>> 6)) ^ (b >>> 5)) ^ (b >>> 4)) ^ (b >>> 3)) ^ (b >>> 2))) & 1) == 0) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj != null) {
                bArr[i] = (byte) (bArr[i] | 1);
            } else {
                bArr[i] = (byte) (bArr[i] & -2);
            }
        }
    }

    static void a(byte[] bArr, int i, int i2) {
        bArr[i2] = (byte) (i & 255);
        bArr[i2 + 1] = (byte) ((i >> 8) & 255);
        bArr[i2 + 2] = (byte) ((i >> 16) & 255);
        bArr[i2 + 3] = (byte) ((i >> 24) & 255);
    }

    static int a(int i, int i2, int i3) {
        return (i & i2) | ((i ^ -1) & i3);
    }

    static int b(int i, int i2, int i3) {
        return ((i & i2) | (i & i3)) | (i2 & i3);
    }

    static int c(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    static int a(int i, int i2) {
        return (i << i2) | (i >>> (32 - i2));
    }

    public String generateType1Msg(String str, String str2) throws NTLMEngineException {
        return a(str2, str);
    }

    public String generateType3Msg(String str, String str2, String str3, String str4, String str5) throws NTLMEngineException {
        e eVar = new e(str5);
        return a(str, str2, str4, str3, eVar.c(), eVar.f(), eVar.d(), eVar.e());
    }
}
