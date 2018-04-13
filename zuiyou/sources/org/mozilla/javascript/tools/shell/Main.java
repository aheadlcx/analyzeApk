package org.mozilla.javascript.tools.shell;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextAction;
import org.mozilla.javascript.GeneratedClassLoader;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.SecurityController;
import org.mozilla.javascript.commonjs.module.ModuleScope;
import org.mozilla.javascript.commonjs.module.Require;

public class Main {
    public static g a = new g();
    public static Global b = new Global();
    protected static int c = 0;
    static boolean d = true;
    static List<String> e = new ArrayList();
    static List<String> f;
    static String g;
    static boolean h = false;
    static boolean i = false;
    static Require j;
    private static e k;
    private static final ScriptCache l = new ScriptCache(32);

    static class ScriptCache extends LinkedHashMap<String, b> {
        int capacity;
        ReferenceQueue<Script> queue = new ReferenceQueue();

        ScriptCache(int i) {
            super(i + 1, 2.0f, true);
            this.capacity = i;
        }

        protected boolean removeEldestEntry(Entry<String, b> entry) {
            return size() > this.capacity;
        }

        b get(String str, byte[] bArr) {
            b bVar;
            while (true) {
                bVar = (b) this.queue.poll();
                if (bVar == null) {
                    break;
                }
                remove(bVar.a);
            }
            bVar = (b) get(str);
            if (bVar == null || Arrays.equals(bArr, bVar.b)) {
                return bVar;
            }
            remove(bVar.a);
            return null;
        }

        void put(String str, byte[] bArr, Script script) {
            put(str, new b(str, bArr, script, this.queue));
        }
    }

    private static class a implements ContextAction, c {
        String[] a;
        String b;
        private int c;

        a(int i) {
            this.c = i;
        }

        public Object run(Context context) {
            if (Main.i) {
                Main.j = Main.b.installRequire(context, Main.f, Main.h);
            }
            if (this.c == 1) {
                Main.a(context, this.a);
            } else if (this.c == 2) {
                Main.a(context, this.b);
            } else {
                throw Kit.codeBug();
            }
            return null;
        }

        public void a(Context context, int i) {
            if (this.c == 3) {
                System.exit(i);
                return;
            }
            throw Kit.codeBug();
        }
    }

    static class b extends SoftReference<Script> {
        String a;
        byte[] b;

        b(String str, byte[] bArr, Script script, ReferenceQueue<Script> referenceQueue) {
            super(script, referenceQueue);
            this.a = str;
            this.b = bArr;
        }
    }

    static {
        b.initQuitAction(new a(3));
    }

    static void a(Context context, String[] strArr) {
        Object obj = new Object[strArr.length];
        System.arraycopy(strArr, 0, obj, 0, strArr.length);
        b.defineProperty("arguments", context.newArray(b, obj), 2);
        for (String str : e) {
            try {
                b(context, str);
            } catch (IOException e) {
                Context.reportError(org.mozilla.javascript.tools.b.a("msg.couldnt.read.source", str, e.getMessage()));
                c = 4;
            } catch (RhinoException e2) {
                org.mozilla.javascript.tools.b.a(context.getErrorReporter(), e2);
                c = 3;
            } catch (VirtualMachineError e3) {
                e3.printStackTrace();
                Context.reportError(org.mozilla.javascript.tools.b.a("msg.uncaughtJSException", e3.toString()));
                c = 3;
            }
        }
    }

    static void a(Context context, String str) {
        try {
            Script compileString = context.compileString(str, "<command>", 1, null);
            if (compileString != null) {
                compileString.exec(context, a());
            }
        } catch (RhinoException e) {
            org.mozilla.javascript.tools.b.a(context.getErrorReporter(), e);
            c = 3;
        } catch (VirtualMachineError e2) {
            e2.printStackTrace();
            Context.reportError(org.mozilla.javascript.tools.b.a("msg.uncaughtJSException", e2.toString()));
            c = 3;
        }
    }

    static Scriptable a() {
        return a(null);
    }

    static Scriptable a(String str) {
        if (!i) {
            return b;
        }
        URI toURI;
        if (str == null) {
            toURI = new File(System.getProperty("user.dir")).toURI();
        } else if (org.mozilla.javascript.tools.a.a(str) != null) {
            try {
                toURI = new URI(str);
            } catch (URISyntaxException e) {
                toURI = new File(str).toURI();
            }
        } else {
            toURI = new File(str).toURI();
        }
        return new ModuleScope(b, toURI, null);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(org.mozilla.javascript.Context r14, java.lang.String r15) throws java.io.IOException {
        /*
        r1 = 0;
        r12 = 3;
        r6 = 0;
        r4 = 1;
        if (r15 == 0) goto L_0x000f;
    L_0x0006:
        r0 = "-";
        r0 = r15.equals(r0);
        if (r0 == 0) goto L_0x00f1;
    L_0x000f:
        r7 = a();
        r0 = a;
        r0 = r0.a();
        if (r0 == 0) goto L_0x0089;
    L_0x001b:
        r0 = java.nio.charset.Charset.forName(r0);
    L_0x001f:
        r2 = b;
        r8 = r2.getConsole(r0);
        if (r15 != 0) goto L_0x002e;
    L_0x0027:
        r0 = r14.getImplementationVersion();
        r8.b(r0);
    L_0x002e:
        r2 = r6;
        r5 = r4;
    L_0x0030:
        if (r2 != 0) goto L_0x00ea;
    L_0x0032:
        r0 = b;
        r9 = r0.getPrompts(r14);
        if (r15 != 0) goto L_0x010b;
    L_0x003a:
        r0 = r9[r6];
    L_0x003c:
        r8.b();
        r3 = "";
        r13 = r3;
        r3 = r5;
        r5 = r0;
        r0 = r13;
    L_0x0046:
        r5 = r8.a(r5);	 Catch:{ IOException -> 0x008e }
        if (r5 != 0) goto L_0x009a;
    L_0x004c:
        r2 = r0;
        r5 = r3;
        r3 = r4;
    L_0x004f:
        r0 = "<stdin>";
        r9 = 0;
        r0 = r14.compileString(r2, r0, r5, r9);	 Catch:{ RhinoException -> 0x00ca, VirtualMachineError -> 0x00d5 }
        if (r0 == 0) goto L_0x0087;
    L_0x0059:
        r0 = r0.exec(r14, r7);	 Catch:{ RhinoException -> 0x00ca, VirtualMachineError -> 0x00d5 }
        r9 = org.mozilla.javascript.Context.getUndefinedValue();	 Catch:{ RhinoException -> 0x00ca, VirtualMachineError -> 0x00d5 }
        if (r0 == r9) goto L_0x007b;
    L_0x0063:
        r9 = r0 instanceof org.mozilla.javascript.Function;	 Catch:{ RhinoException -> 0x00ca, VirtualMachineError -> 0x00d5 }
        if (r9 == 0) goto L_0x0074;
    L_0x0067:
        r9 = r2.trim();	 Catch:{ RhinoException -> 0x00ca, VirtualMachineError -> 0x00d5 }
        r10 = "function";
        r9 = r9.startsWith(r10);	 Catch:{ RhinoException -> 0x00ca, VirtualMachineError -> 0x00d5 }
        if (r9 != 0) goto L_0x007b;
    L_0x0074:
        r0 = org.mozilla.javascript.Context.toString(r0);	 Catch:{ RhinoException -> 0x00c1, VirtualMachineError -> 0x00d5 }
        r8.b(r0);	 Catch:{ RhinoException -> 0x00c1, VirtualMachineError -> 0x00d5 }
    L_0x007b:
        r0 = b;	 Catch:{ RhinoException -> 0x00ca, VirtualMachineError -> 0x00d5 }
        r0 = r0.history;	 Catch:{ RhinoException -> 0x00ca, VirtualMachineError -> 0x00d5 }
        r10 = r0.getLength();	 Catch:{ RhinoException -> 0x00ca, VirtualMachineError -> 0x00d5 }
        r9 = (int) r10;	 Catch:{ RhinoException -> 0x00ca, VirtualMachineError -> 0x00d5 }
        r0.put(r9, r0, r2);	 Catch:{ RhinoException -> 0x00ca, VirtualMachineError -> 0x00d5 }
    L_0x0087:
        r2 = r3;
        goto L_0x0030;
    L_0x0089:
        r0 = java.nio.charset.Charset.defaultCharset();
        goto L_0x001f;
    L_0x008e:
        r5 = move-exception;
        r5 = r5.toString();
        r8.b(r5);
        r5 = r3;
        r3 = r2;
        r2 = r0;
        goto L_0x004f;
    L_0x009a:
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r0 = r10.append(r0);
        r0 = r0.append(r5);
        r5 = "\n";
        r0 = r0.append(r5);
        r0 = r0.toString();
        r3 = r3 + 1;
        r5 = r14.stringIsCompilableUnit(r0);
        if (r5 == 0) goto L_0x00be;
    L_0x00ba:
        r5 = r3;
        r3 = r2;
        r2 = r0;
        goto L_0x004f;
    L_0x00be:
        r5 = r9[r4];
        goto L_0x0046;
    L_0x00c1:
        r0 = move-exception;
        r9 = r14.getErrorReporter();	 Catch:{ RhinoException -> 0x00ca, VirtualMachineError -> 0x00d5 }
        org.mozilla.javascript.tools.b.a(r9, r0);	 Catch:{ RhinoException -> 0x00ca, VirtualMachineError -> 0x00d5 }
        goto L_0x007b;
    L_0x00ca:
        r0 = move-exception;
        r2 = r14.getErrorReporter();
        org.mozilla.javascript.tools.b.a(r2, r0);
        c = r12;
        goto L_0x0087;
    L_0x00d5:
        r0 = move-exception;
        r0.printStackTrace();
        r2 = "msg.uncaughtJSException";
        r0 = r0.toString();
        r0 = org.mozilla.javascript.tools.b.a(r2, r0);
        org.mozilla.javascript.Context.reportError(r0);
        c = r12;
        goto L_0x0087;
    L_0x00ea:
        r8.c();
        r8.b();
    L_0x00f0:
        return;
    L_0x00f1:
        r0 = i;
        if (r0 == 0) goto L_0x0103;
    L_0x00f5:
        r0 = g;
        r0 = r15.equals(r0);
        if (r0 == 0) goto L_0x0103;
    L_0x00fd:
        r0 = j;
        r0.requireMain(r14, r15);
        goto L_0x00f0;
    L_0x0103:
        r0 = a(r15);
        a(r14, r0, r15);
        goto L_0x00f0;
    L_0x010b:
        r0 = r1;
        goto L_0x003c;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.tools.shell.Main.b(org.mozilla.javascript.Context, java.lang.String):void");
    }

    public static void a(Context context, Scriptable scriptable, String str) throws IOException {
        if (k == null) {
            a(context, scriptable, str, null);
        } else {
            k.a(context, scriptable, str);
        }
    }

    static void a(Context context, Scriptable scriptable, String str, Object obj) throws IOException {
        boolean endsWith = str.endsWith(".class");
        Object a = a(str, !endsWith);
        byte[] a2 = a(a);
        String str2 = str + "_" + context.getOptimizationLevel();
        b bVar = l.get(str2, a2);
        Script script = bVar != null ? (Script) bVar.get() : null;
        if (script == null) {
            if (endsWith) {
                script = a(context, str, (byte[]) a, obj);
            } else {
                String str3 = (String) a;
                if (str3.length() > 0 && str3.charAt(0) == '#') {
                    for (int i = 1; i != str3.length(); i++) {
                        char charAt = str3.charAt(i);
                        if (charAt == '\n' || charAt == '\r') {
                            str3 = str3.substring(i);
                            break;
                        }
                    }
                }
                script = context.compileString(str3, str, 1, obj);
            }
            l.put(str2, a2, script);
        }
        if (script != null) {
            script.exec(context, scriptable);
        }
    }

    private static byte[] a(Object obj) {
        byte[] bArr = null;
        if (obj != null) {
            if (obj instanceof String) {
                try {
                    obj = ((String) obj).getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    obj = ((String) obj).getBytes();
                }
            } else {
                byte[] bArr2 = (byte[]) obj;
            }
            try {
                bArr = MessageDigest.getInstance("MD5").digest(obj);
            } catch (Throwable e2) {
                throw new RuntimeException(e2);
            }
        }
        return bArr;
    }

    private static Script a(Context context, String str, byte[] bArr, Object obj) throws FileNotFoundException {
        if (bArr == null) {
            throw new FileNotFoundException(str);
        }
        int lastIndexOf = str.lastIndexOf(47);
        if (lastIndexOf < 0) {
            lastIndexOf = 0;
        } else {
            lastIndexOf++;
        }
        int lastIndexOf2 = str.lastIndexOf(46);
        if (lastIndexOf2 < lastIndexOf) {
            lastIndexOf2 = str.length();
        }
        String substring = str.substring(lastIndexOf, lastIndexOf2);
        try {
            GeneratedClassLoader createLoader = SecurityController.createLoader(context.getApplicationClassLoader(), obj);
            Class defineClass = createLoader.defineClass(substring, bArr);
            createLoader.linkClass(defineClass);
            if (Script.class.isAssignableFrom(defineClass)) {
                return (Script) defineClass.newInstance();
            }
            throw Context.reportRuntimeError("msg.must.implement.Script");
        } catch (Throwable e) {
            Context.reportError(e.toString());
            throw new RuntimeException(e);
        } catch (Throwable e2) {
            Context.reportError(e2.toString());
            throw new RuntimeException(e2);
        }
    }

    private static Object a(String str, boolean z) throws IOException {
        return org.mozilla.javascript.tools.a.a(str, z, a.a());
    }
}
