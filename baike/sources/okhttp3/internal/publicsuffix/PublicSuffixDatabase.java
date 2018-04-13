package okhttp3.internal.publicsuffix;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.IDN;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.internal.Util;
import okio.GzipSource;
import okio.Okio;

public final class PublicSuffixDatabase {
    public static final String PUBLIC_SUFFIX_RESOURCE = "publicsuffixes.gz";
    private static final byte[] a = new byte[]{(byte) 42};
    private static final String[] b = new String[0];
    private static final String[] c = new String[]{"*"};
    private static final PublicSuffixDatabase d = new PublicSuffixDatabase();
    private final AtomicBoolean e = new AtomicBoolean(false);
    private final CountDownLatch f = new CountDownLatch(1);
    private byte[] g;
    private byte[] h;

    private void a() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x000e in list [B:11:0x0020]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r5 = this;
        r0 = 0;
        r1 = r0;
    L_0x0002:
        r5.b();	 Catch:{ InterruptedIOException -> 0x000f, IOException -> 0x0013, all -> 0x0028 }
        if (r1 == 0) goto L_0x000e;
    L_0x0007:
        r0 = java.lang.Thread.currentThread();
        r0.interrupt();
    L_0x000e:
        return;
    L_0x000f:
        r0 = move-exception;
        r0 = 1;
        r1 = r0;
        goto L_0x0002;
    L_0x0013:
        r0 = move-exception;
        r2 = okhttp3.internal.platform.Platform.get();	 Catch:{ InterruptedIOException -> 0x000f, IOException -> 0x0013, all -> 0x0028 }
        r3 = 5;	 Catch:{ InterruptedIOException -> 0x000f, IOException -> 0x0013, all -> 0x0028 }
        r4 = "Failed to read public suffix list";	 Catch:{ InterruptedIOException -> 0x000f, IOException -> 0x0013, all -> 0x0028 }
        r2.log(r3, r4, r0);	 Catch:{ InterruptedIOException -> 0x000f, IOException -> 0x0013, all -> 0x0028 }
        if (r1 == 0) goto L_0x000e;
    L_0x0020:
        r0 = java.lang.Thread.currentThread();
        r0.interrupt();
        goto L_0x000e;
    L_0x0028:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0032;
    L_0x002b:
        r1 = java.lang.Thread.currentThread();
        r1.interrupt();
    L_0x0032:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.publicsuffix.PublicSuffixDatabase.a():void");
    }

    public static PublicSuffixDatabase get() {
        return d;
    }

    public String getEffectiveTldPlusOne(String str) {
        if (str == null) {
            throw new NullPointerException("domain == null");
        }
        String[] split = IDN.toUnicode(str).split("\\.");
        String[] a = a(split);
        if (split.length == a.length && a[0].charAt(0) != '!') {
            return null;
        }
        int length;
        if (a[0].charAt(0) == '!') {
            length = split.length - a.length;
        } else {
            length = split.length - (a.length + 1);
        }
        StringBuilder stringBuilder = new StringBuilder();
        String[] split2 = str.split("\\.");
        for (length = 
/*
Method generation error in method: okhttp3.internal.publicsuffix.PublicSuffixDatabase.getEffectiveTldPlusOne(java.lang.String):java.lang.String, dex: classes2.dex
jadx.core.utils.exceptions.CodegenException: Error generate insn: PHI: (r0_6 'length' int) = (r0_5 'length' int), (r0_13 'length' int) binds: {(r0_5 'length' int)=B:11:0x0031, (r0_13 'length' int)=B:16:0x0050} in method: okhttp3.internal.publicsuffix.PublicSuffixDatabase.getEffectiveTldPlusOne(java.lang.String):java.lang.String, dex: classes2.dex
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:184)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:187)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:320)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:257)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:220)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:75)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:12)
	at jadx.core.ProcessClass.process(ProcessClass.java:40)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
Caused by: jadx.core.utils.exceptions.CodegenException: PHI can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:537)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:509)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 23 more

*/

        private String[] a(String[] strArr) {
            int i;
            String a;
            String str;
            String str2 = null;
            int i2 = 0;
            if (this.e.get() || !this.e.compareAndSet(false, true)) {
                try {
                    this.f.await();
                } catch (InterruptedException e) {
                }
            } else {
                a();
            }
            synchronized (this) {
                if (this.g == null) {
                    throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.");
                }
            }
            Object obj = new byte[strArr.length][];
            for (i = 0; i < strArr.length; i++) {
                obj[i] = strArr[i].getBytes(Util.UTF_8);
            }
            for (i = 0; i < obj.length; i++) {
                a = a(this.g, obj, i);
                if (a != null) {
                    break;
                }
            }
            a = null;
            if (obj.length > 1) {
                byte[][] bArr = (byte[][]) obj.clone();
                for (int i3 = 0; i3 < bArr.length - 1; i3++) {
                    bArr[i3] = a;
                    String a2 = a(this.g, bArr, i3);
                    if (a2 != null) {
                        str = a2;
                        break;
                    }
                }
            }
            str = null;
            if (str != null) {
                while (i2 < obj.length - 1) {
                    String a3 = a(this.h, obj, i2);
                    if (a3 != null) {
                        str2 = a3;
                        break;
                    }
                    i2++;
                }
            }
            if (str2 != null) {
                return ("!" + str2).split("\\.");
            }
            if (a == null && str == null) {
                return c;
            }
            String[] split;
            String[] split2;
            if (a != null) {
                split = a.split("\\.");
            } else {
                split = b;
            }
            if (str != null) {
                split2 = str.split("\\.");
            } else {
                split2 = b;
            }
            return split.length > split2.length ? split : split2;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static java.lang.String a(byte[] r12, byte[][] r13, int r14) {
            /*
            r5 = 0;
            r4 = r12.length;
            r6 = 0;
        L_0x0003:
            if (r5 >= r4) goto L_0x0098;
        L_0x0005:
            r0 = r5 + r4;
            r0 = r0 / 2;
        L_0x0009:
            r1 = -1;
            if (r0 <= r1) goto L_0x0015;
        L_0x000c:
            r1 = r12[r0];
            r2 = 10;
            if (r1 == r2) goto L_0x0015;
        L_0x0012:
            r0 = r0 + -1;
            goto L_0x0009;
        L_0x0015:
            r9 = r0 + 1;
            r0 = 1;
        L_0x0018:
            r1 = r9 + r0;
            r1 = r12[r1];
            r2 = 10;
            if (r1 == r2) goto L_0x0023;
        L_0x0020:
            r0 = r0 + 1;
            goto L_0x0018;
        L_0x0023:
            r1 = r9 + r0;
            r10 = r1 - r9;
            r8 = 0;
            r7 = 0;
            r2 = 0;
            r3 = r14;
        L_0x002b:
            if (r2 == 0) goto L_0x0044;
        L_0x002d:
            r1 = 46;
            r2 = 0;
        L_0x0030:
            r11 = r9 + r7;
            r11 = r12[r11];
            r11 = r11 & 255;
            r11 = r1 - r11;
            if (r11 == 0) goto L_0x004b;
        L_0x003a:
            r1 = r7;
            r2 = r8;
        L_0x003c:
            if (r11 >= 0) goto L_0x0067;
        L_0x003e:
            r0 = r9 + -1;
            r1 = r5;
        L_0x0041:
            r4 = r0;
            r5 = r1;
            goto L_0x0003;
        L_0x0044:
            r1 = r13[r3];
            r1 = r1[r8];
            r1 = r1 & 255;
            goto L_0x0030;
        L_0x004b:
            r7 = r7 + 1;
            r1 = r8 + 1;
            if (r7 != r10) goto L_0x0054;
        L_0x0051:
            r2 = r1;
            r1 = r7;
            goto L_0x003c;
        L_0x0054:
            r8 = r13[r3];
            r8 = r8.length;
            if (r8 != r1) goto L_0x0065;
        L_0x0059:
            r2 = r13.length;
            r2 = r2 + -1;
            if (r3 != r2) goto L_0x0061;
        L_0x005e:
            r2 = r1;
            r1 = r7;
            goto L_0x003c;
        L_0x0061:
            r3 = r3 + 1;
            r1 = -1;
            r2 = 1;
        L_0x0065:
            r8 = r1;
            goto L_0x002b;
        L_0x0067:
            if (r11 <= 0) goto L_0x006f;
        L_0x0069:
            r0 = r0 + r9;
            r0 = r0 + 1;
            r1 = r0;
            r0 = r4;
            goto L_0x0041;
        L_0x006f:
            r7 = r10 - r1;
            r1 = r13[r3];
            r1 = r1.length;
            r2 = r1 - r2;
            r1 = r3 + 1;
        L_0x0078:
            r3 = r13.length;
            if (r1 >= r3) goto L_0x0082;
        L_0x007b:
            r3 = r13[r1];
            r3 = r3.length;
            r2 = r2 + r3;
            r1 = r1 + 1;
            goto L_0x0078;
        L_0x0082:
            if (r2 >= r7) goto L_0x0088;
        L_0x0084:
            r0 = r9 + -1;
            r1 = r5;
            goto L_0x0041;
        L_0x0088:
            if (r2 <= r7) goto L_0x0090;
        L_0x008a:
            r0 = r0 + r9;
            r0 = r0 + 1;
            r1 = r0;
            r0 = r4;
            goto L_0x0041;
        L_0x0090:
            r0 = new java.lang.String;
            r1 = okhttp3.internal.Util.UTF_8;
            r0.<init>(r12, r9, r10, r1);
        L_0x0097:
            return r0;
        L_0x0098:
            r0 = r6;
            goto L_0x0097;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.publicsuffix.PublicSuffixDatabase.a(byte[], byte[][], int):java.lang.String");
        }

        private void b() throws IOException {
            InputStream resourceAsStream = PublicSuffixDatabase.class.getResourceAsStream(PUBLIC_SUFFIX_RESOURCE);
            if (resourceAsStream != null) {
                Closeable buffer = Okio.buffer(new GzipSource(Okio.source(resourceAsStream)));
                try {
                    byte[] bArr = new byte[buffer.readInt()];
                    buffer.readFully(bArr);
                    byte[] bArr2 = new byte[buffer.readInt()];
                    buffer.readFully(bArr2);
                    synchronized (this) {
                        this.g = bArr;
                        this.h = bArr2;
                    }
                    this.f.countDown();
                } finally {
                    Util.closeQuietly(buffer);
                }
            }
        }
    }
