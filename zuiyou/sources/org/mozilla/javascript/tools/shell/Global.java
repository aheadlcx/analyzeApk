package org.mozilla.javascript.tools.shell;

import android.support.v4.app.NotificationCompat;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextAction;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.ImporterTopLevel;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Synchronizer;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.Wrapper;
import org.mozilla.javascript.commonjs.module.Require;
import org.mozilla.javascript.commonjs.module.RequireBuilder;
import org.mozilla.javascript.commonjs.module.provider.SoftCachingModuleScriptProvider;
import org.mozilla.javascript.commonjs.module.provider.UrlModuleSourceProvider;
import org.mozilla.javascript.serialize.ScriptableInputStream;
import org.mozilla.javascript.serialize.ScriptableOutputStream;
import org.mozilla.javascript.tools.b;

public class Global extends ImporterTopLevel {
    static final long serialVersionUID = 4029130780977538005L;
    boolean attemptedJLineLoad;
    private f console;
    private HashMap<String, String> doctestCanonicalizations;
    private PrintStream errStream;
    NativeArray history;
    private InputStream inStream;
    boolean initialized;
    private PrintStream outStream;
    private String[] prompts;
    private c quitAction;
    private boolean sealedStdLib;

    public Global() {
        this.sealedStdLib = false;
        this.prompts = new String[]{"js> ", "  > "};
    }

    public Global(Context context) {
        this.sealedStdLib = false;
        this.prompts = new String[]{"js> ", "  > "};
        init(context);
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    public void initQuitAction(c cVar) {
        if (cVar == null) {
            throw new IllegalArgumentException("quitAction is null");
        } else if (this.quitAction != null) {
            throw new IllegalArgumentException("The method is once-call.");
        } else {
            this.quitAction = cVar;
        }
    }

    public void init(ContextFactory contextFactory) {
        contextFactory.call(new ContextAction(this) {
            final /* synthetic */ Global a;

            {
                this.a = r1;
            }

            public Object run(Context context) {
                this.a.init(context);
                return null;
            }
        });
    }

    public void init(Context context) {
        initStandardObjects(context, this.sealedStdLib);
        defineFunctionProperties(new String[]{"defineClass", "deserialize", "doctest", "gc", "help", "load", "loadClass", SharePatchInfo.FINGER_PRINT, "quit", "readFile", "readUrl", "runCommand", "seal", "serialize", "spawn", "sync", "toint32", "version"}, Global.class, 2);
        Environment.defineClass(this);
        defineProperty("environment", new Environment(this), 2);
        this.history = (NativeArray) context.newArray(this, 0);
        defineProperty("history", this.history, 2);
        this.initialized = true;
    }

    public Require installRequire(Context context, List<String> list, boolean z) {
        RequireBuilder requireBuilder = new RequireBuilder();
        requireBuilder.setSandboxed(z);
        Iterable arrayList = new ArrayList();
        if (list != null) {
            for (String str : list) {
                try {
                    Object uri;
                    URI uri2 = new URI(str);
                    if (!uri2.isAbsolute()) {
                        uri2 = new File(str).toURI().resolve("");
                    }
                    if (uri2.toString().endsWith("/")) {
                        URI uri3 = uri2;
                    } else {
                        uri = new URI(uri2 + "/");
                    }
                    arrayList.add(uri);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        }
        requireBuilder.setModuleScriptProvider(new SoftCachingModuleScriptProvider(new UrlModuleSourceProvider(arrayList, null)));
        Require createRequire = requireBuilder.createRequire(context, this);
        createRequire.install(this);
        return createRequire;
    }

    public static void help(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        a(function).getOut().println(b.a("msg.help"));
    }

    public static void gc(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        System.gc();
    }

    public static Object print(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        PrintStream out = a(function).getOut();
        for (int i = 0; i < objArr.length; i++) {
            if (i > 0) {
                out.print(" ");
            }
            out.print(Context.toString(objArr[i]));
        }
        out.println();
        return Context.getUndefinedValue();
    }

    public static void quit(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        int i = 0;
        Global a = a(function);
        if (a.quitAction != null) {
            if (objArr.length != 0) {
                i = ScriptRuntime.toInt32(objArr[0]);
            }
            a.quitAction.a(context, i);
        }
    }

    public static double version(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        double languageVersion = (double) context.getLanguageVersion();
        if (objArr.length > 0) {
            context.setLanguageVersion((int) Context.toNumber(objArr[0]));
        }
        return languageVersion;
    }

    public static void load(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        int length = objArr.length;
        int i = 0;
        while (i < length) {
            String context2 = Context.toString(objArr[i]);
            try {
                Main.a(context, scriptable, context2);
                i++;
            } catch (IOException e) {
                throw Context.reportRuntimeError(b.a("msg.couldnt.read.source", context2, e.getMessage()));
            } catch (VirtualMachineError e2) {
                e2.printStackTrace();
                throw Context.reportRuntimeError(b.a("msg.uncaughtJSException", e2.toString()));
            }
        }
    }

    public static void defineClass(Context context, Scriptable scriptable, Object[] objArr, Function function) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Class a = a(objArr);
        if (Scriptable.class.isAssignableFrom(a)) {
            ScriptableObject.defineClass(scriptable, a);
            return;
        }
        throw reportRuntimeError("msg.must.implement.Scriptable");
    }

    public static void loadClass(Context context, Scriptable scriptable, Object[] objArr, Function function) throws IllegalAccessException, InstantiationException {
        Class a = a(objArr);
        if (Script.class.isAssignableFrom(a)) {
            ((Script) a.newInstance()).exec(context, scriptable);
            return;
        }
        throw reportRuntimeError("msg.must.implement.Script");
    }

    private static Class<?> a(Object[] objArr) {
        if (objArr.length == 0) {
            throw reportRuntimeError("msg.expected.string.arg");
        }
        Object obj = objArr[0];
        if (obj instanceof Wrapper) {
            obj = ((Wrapper) obj).unwrap();
            if (obj instanceof Class) {
                return (Class) obj;
            }
        }
        String context = Context.toString(objArr[0]);
        try {
            return Class.forName(context);
        } catch (ClassNotFoundException e) {
            throw reportRuntimeError("msg.class.not.found", context);
        }
    }

    public static void serialize(Context context, Scriptable scriptable, Object[] objArr, Function function) throws IOException {
        if (objArr.length < 2) {
            throw Context.reportRuntimeError("Expected an object to serialize and a filename to write the serialization to");
        }
        Object obj = objArr[0];
        ScriptableOutputStream scriptableOutputStream = new ScriptableOutputStream(new FileOutputStream(Context.toString(objArr[1])), ScriptableObject.getTopLevelScope(scriptable));
        scriptableOutputStream.writeObject(obj);
        scriptableOutputStream.close();
    }

    public static Object deserialize(Context context, Scriptable scriptable, Object[] objArr, Function function) throws IOException, ClassNotFoundException {
        if (objArr.length < 1) {
            throw Context.reportRuntimeError("Expected a filename to read the serialization from");
        }
        InputStream fileInputStream = new FileInputStream(Context.toString(objArr[0]));
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        ObjectInputStream scriptableInputStream = new ScriptableInputStream(fileInputStream, topLevelScope);
        Object readObject = scriptableInputStream.readObject();
        scriptableInputStream.close();
        return Context.toObject(readObject, topLevelScope);
    }

    public String[] getPrompts(Context context) {
        if (ScriptableObject.hasProperty(this, "prompts")) {
            Object property = ScriptableObject.getProperty(this, "prompts");
            if (property instanceof Scriptable) {
                Scriptable scriptable = (Scriptable) property;
                if (ScriptableObject.hasProperty(scriptable, 0) && ScriptableObject.hasProperty(scriptable, 1)) {
                    Object property2 = ScriptableObject.getProperty(scriptable, 0);
                    if (property2 instanceof Function) {
                        property2 = ((Function) property2).call(context, this, scriptable, new Object[0]);
                    }
                    this.prompts[0] = Context.toString(property2);
                    property2 = ScriptableObject.getProperty(scriptable, 1);
                    if (property2 instanceof Function) {
                        property2 = ((Function) property2).call(context, this, scriptable, new Object[0]);
                    }
                    this.prompts[1] = Context.toString(property2);
                }
            }
        }
        return this.prompts;
    }

    public static Object doctest(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        if (objArr.length == 0) {
            return Boolean.FALSE;
        }
        String context2 = Context.toString(objArr[0]);
        Global a = a(function);
        return new Integer(a.runDoctest(context, a, context2, null, 0));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int runDoctest(org.mozilla.javascript.Context r21, org.mozilla.javascript.Scriptable r22, java.lang.String r23, java.lang.String r24, int r25) {
        /*
        r20 = this;
        r2 = new java.util.HashMap;
        r2.<init>();
        r0 = r20;
        r0.doctestCanonicalizations = r2;
        r2 = "\r\n?|\n";
        r0 = r23;
        r12 = r0.split(r2);
        r0 = r20;
        r2 = r0.prompts;
        r3 = 0;
        r2 = r2[r3];
        r13 = r2.trim();
        r0 = r20;
        r2 = r0.prompts;
        r3 = 1;
        r2 = r2[r3];
        r14 = r2.trim();
        r3 = 0;
        r2 = 0;
    L_0x002a:
        r4 = r12.length;
        if (r2 >= r4) goto L_0x003e;
    L_0x002d:
        r4 = r12[r2];
        r4 = r4.trim();
        r4 = r4.startsWith(r13);
        if (r4 != 0) goto L_0x003e;
    L_0x0039:
        r2 = r2 + 1;
        goto L_0x002a;
    L_0x003c:
        r2 = r8;
        r3 = r9;
    L_0x003e:
        r4 = r12.length;
        if (r2 >= r4) goto L_0x022d;
    L_0x0041:
        r4 = r12[r2];
        r4 = r4.trim();
        r5 = r13.length();
        r4 = r4.substring(r5);
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r4 = r5.append(r4);
        r5 = "\n";
        r4 = r4.append(r5);
        r4 = r4.toString();
        r2 = r2 + 1;
    L_0x0065:
        r5 = r12.length;
        if (r2 >= r5) goto L_0x00aa;
    L_0x0068:
        r5 = r12[r2];
        r5 = r5.trim();
        r5 = r5.startsWith(r14);
        if (r5 == 0) goto L_0x00aa;
    L_0x0074:
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r4 = r5.append(r4);
        r5 = r12[r2];
        r5 = r5.trim();
        r6 = r14.length();
        r5 = r5.substring(r6);
        r4 = r4.append(r5);
        r4 = r4.toString();
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r4 = r5.append(r4);
        r5 = "\n";
        r4 = r4.append(r5);
        r4 = r4.toString();
        r2 = r2 + 1;
        goto L_0x0065;
    L_0x00aa:
        r5 = "";
        r10 = r5;
        r8 = r2;
    L_0x00af:
        r2 = r12.length;
        if (r8 >= r2) goto L_0x00dc;
    L_0x00b2:
        r2 = r12[r8];
        r2 = r2.trim();
        r2 = r2.startsWith(r13);
        if (r2 != 0) goto L_0x00dc;
    L_0x00be:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2 = r2.append(r10);
        r5 = r12[r8];
        r2 = r2.append(r5);
        r5 = "\n";
        r2 = r2.append(r5);
        r2 = r2.toString();
        r8 = r8 + 1;
        r10 = r2;
        goto L_0x00af;
    L_0x00dc:
        r15 = r20.getOut();
        r16 = r20.getErr();
        r17 = new java.io.ByteArrayOutputStream;
        r17.<init>();
        r18 = new java.io.ByteArrayOutputStream;
        r18.<init>();
        r2 = new java.io.PrintStream;
        r0 = r17;
        r2.<init>(r0);
        r0 = r20;
        r0.setOut(r2);
        r2 = new java.io.PrintStream;
        r0 = r18;
        r2.<init>(r0);
        r0 = r20;
        r0.setErr(r2);
        r11 = "";
        r19 = r21.getErrorReporter();
        r2 = new org.mozilla.javascript.tools.b;
        r5 = 0;
        r6 = r20.getErr();
        r2.<init>(r5, r6);
        r0 = r21;
        r0.setErrorReporter(r2);
        r9 = r3 + 1;
        r5 = "doctest input";
        r6 = 1;
        r7 = 0;
        r2 = r21;
        r3 = r22;
        r2 = r2.evaluateString(r3, r4, r5, r6, r7);	 Catch:{ RhinoException -> 0x01be }
        r3 = org.mozilla.javascript.Context.getUndefinedValue();	 Catch:{ RhinoException -> 0x01be }
        if (r2 == r3) goto L_0x022e;
    L_0x0131:
        r3 = r2 instanceof org.mozilla.javascript.Function;	 Catch:{ RhinoException -> 0x01be }
        if (r3 == 0) goto L_0x0142;
    L_0x0135:
        r3 = r4.trim();	 Catch:{ RhinoException -> 0x01be }
        r5 = "function";
        r3 = r3.startsWith(r5);	 Catch:{ RhinoException -> 0x01be }
        if (r3 != 0) goto L_0x022e;
    L_0x0142:
        r2 = org.mozilla.javascript.Context.toString(r2);	 Catch:{ RhinoException -> 0x01be }
    L_0x0146:
        r0 = r20;
        r0.setOut(r15);
        r0 = r20;
        r1 = r16;
        r0.setErr(r1);
        r0 = r21;
        r1 = r19;
        r0.setErrorReporter(r1);
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r2 = r3.append(r2);
        r3 = r18.toString();
        r2 = r2.append(r3);
        r3 = r17.toString();
        r2 = r2.append(r3);
        r2 = r2.toString();
    L_0x0176:
        r0 = r20;
        r3 = r0.a(r10, r2);
        if (r3 != 0) goto L_0x003c;
    L_0x017e:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r5 = "doctest failure running:\n";
        r3 = r3.append(r5);
        r3 = r3.append(r4);
        r4 = "expected: ";
        r3 = r3.append(r4);
        r3 = r3.append(r10);
        r4 = "actual: ";
        r3 = r3.append(r4);
        r2 = r3.append(r2);
        r3 = "\n";
        r2 = r2.append(r3);
        r2 = r2.toString();
        if (r24 == 0) goto L_0x0228;
    L_0x01b1:
        r3 = r25 + r8;
        r3 = r3 + -1;
        r4 = 0;
        r5 = 0;
        r0 = r24;
        r2 = org.mozilla.javascript.Context.reportRuntimeError(r2, r0, r3, r4, r5);
        throw r2;
    L_0x01be:
        r2 = move-exception;
        r3 = r21.getErrorReporter();	 Catch:{ all -> 0x01f7 }
        org.mozilla.javascript.tools.b.a(r3, r2);	 Catch:{ all -> 0x01f7 }
        r0 = r20;
        r0.setOut(r15);
        r0 = r20;
        r1 = r16;
        r0.setErr(r1);
        r0 = r21;
        r1 = r19;
        r0.setErrorReporter(r1);
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2 = r2.append(r11);
        r3 = r18.toString();
        r2 = r2.append(r3);
        r3 = r17.toString();
        r2 = r2.append(r3);
        r2 = r2.toString();
        goto L_0x0176;
    L_0x01f7:
        r2 = move-exception;
        r0 = r20;
        r0.setOut(r15);
        r0 = r20;
        r1 = r16;
        r0.setErr(r1);
        r0 = r21;
        r1 = r19;
        r0.setErrorReporter(r1);
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3 = r3.append(r11);
        r4 = r18.toString();
        r3 = r3.append(r4);
        r4 = r17.toString();
        r3 = r3.append(r4);
        r3.toString();
        throw r2;
    L_0x0228:
        r2 = org.mozilla.javascript.Context.reportRuntimeError(r2);
        throw r2;
    L_0x022d:
        return r3;
    L_0x022e:
        r2 = r11;
        goto L_0x0146;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.tools.shell.Global.runDoctest(org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable, java.lang.String, java.lang.String, int):int");
    }

    private boolean a(String str, String str2) {
        String trim = str.trim();
        Object replace = str2.trim().replace("\r\n", "\n");
        if (trim.equals(replace)) {
            return true;
        }
        String str3 = trim;
        for (Entry entry : this.doctestCanonicalizations.entrySet()) {
            str3 = str3.replace((CharSequence) entry.getKey(), (CharSequence) entry.getValue());
        }
        if (str3.equals(replace)) {
            return true;
        }
        Pattern compile = Pattern.compile("@[0-9a-fA-F]+");
        Matcher matcher = compile.matcher(str3);
        Matcher matcher2 = compile.matcher(replace);
        while (matcher.find()) {
            if (!matcher2.find()) {
                return false;
            }
            if (matcher2.start() != matcher.start()) {
                return false;
            }
            int start = matcher.start();
            if (!str3.substring(0, start).equals(replace.substring(0, start))) {
                return false;
            }
            CharSequence group = matcher.group();
            Object group2 = matcher2.group();
            trim = (String) this.doctestCanonicalizations.get(group);
            if (trim == null) {
                this.doctestCanonicalizations.put(group, group2);
                str3 = str3.replace(group, group2);
            } else if (!group2.equals(trim)) {
                return false;
            }
            if (str3.equals(replace)) {
                return true;
            }
        }
        return false;
    }

    public static Object spawn(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        Runnable dVar;
        Scriptable parentScope = function.getParentScope();
        if (objArr.length != 0 && (objArr[0] instanceof Function)) {
            Object[] objArr2;
            Object[] objArr3 = null;
            if (objArr.length > 1 && (objArr[1] instanceof Scriptable)) {
                objArr3 = context.getElements((Scriptable) objArr[1]);
            }
            if (objArr3 == null) {
                objArr2 = ScriptRuntime.emptyArgs;
            } else {
                objArr2 = objArr3;
            }
            dVar = new d(parentScope, (Function) objArr[0], objArr2);
        } else if (objArr.length == 0 || !(objArr[0] instanceof Script)) {
            throw reportRuntimeError("msg.spawn.args");
        } else {
            Object dVar2 = new d(parentScope, (Script) objArr[0]);
        }
        dVar.a = context.getFactory();
        Thread thread = new Thread(dVar);
        thread.start();
        return thread;
    }

    public static Object sync(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        if (objArr.length < 1 || objArr.length > 2 || !(objArr[0] instanceof Function)) {
            throw reportRuntimeError("msg.sync.args");
        }
        Object obj;
        if (objArr.length != 2 || objArr[1] == Undefined.instance) {
            obj = null;
        } else {
            obj = objArr[1];
        }
        return new Synchronizer((Function) objArr[0], obj);
    }

    public static Object runCommand(Context context, Scriptable scriptable, Object[] objArr, Function function) throws IOException {
        int length = objArr.length;
        if (length == 0 || (length == 1 && (objArr[0] instanceof Scriptable))) {
            throw reportRuntimeError("msg.runCommand.bad.args");
        }
        String[] strArr;
        File file;
        InputStream a;
        OutputStream b;
        OutputStream outputStream;
        Object property;
        Object property2;
        ByteArrayOutputStream byteArrayOutputStream;
        int i;
        Object[] elements;
        Scriptable scriptable2;
        OutputStream outputStream2;
        int i2;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        if (objArr[length - 1] instanceof Scriptable) {
            ByteArrayOutputStream byteArrayOutputStream3;
            ByteArrayOutputStream byteArrayOutputStream4;
            OutputStream outputStream3;
            Scriptable scriptable3 = (Scriptable) objArr[length - 1];
            int i3 = length - 1;
            Object property3 = ScriptableObject.getProperty(scriptable3, "env");
            if (property3 == Scriptable.NOT_FOUND) {
                strArr = null;
            } else if (property3 == null) {
                strArr = new String[0];
            } else if (property3 instanceof Scriptable) {
                Scriptable scriptable4 = (Scriptable) property3;
                Object[] propertyIds = ScriptableObject.getPropertyIds(scriptable4);
                String[] strArr2 = new String[propertyIds.length];
                for (int i4 = 0; i4 != propertyIds.length; i4++) {
                    String str;
                    Object property4;
                    Object obj = propertyIds[i4];
                    if (obj instanceof String) {
                        str = (String) obj;
                        property4 = ScriptableObject.getProperty(scriptable4, str);
                    } else {
                        length = ((Number) obj).intValue();
                        str = Integer.toString(length);
                        property4 = ScriptableObject.getProperty(scriptable4, length);
                    }
                    if (property4 == ScriptableObject.NOT_FOUND) {
                        property4 = Undefined.instance;
                    }
                    strArr2[i4] = str + '=' + ScriptRuntime.toString(property4);
                }
                strArr = strArr2;
            } else {
                throw reportRuntimeError("msg.runCommand.bad.env");
            }
            Object property5 = ScriptableObject.getProperty(scriptable3, SharePatchInfo.OAT_DIR);
            if (property5 != Scriptable.NOT_FOUND) {
                file = new File(ScriptRuntime.toString(property5));
            } else {
                file = null;
            }
            property5 = ScriptableObject.getProperty(scriptable3, "input");
            if (property5 != Scriptable.NOT_FOUND) {
                a = a(property5);
            } else {
                a = null;
            }
            Object property6 = ScriptableObject.getProperty(scriptable3, "output");
            if (property6 != Scriptable.NOT_FOUND) {
                b = b(property6);
                if (b == null) {
                    byteArrayOutputStream3 = new ByteArrayOutputStream();
                    outputStream = byteArrayOutputStream3;
                } else {
                    outputStream = b;
                    byteArrayOutputStream3 = null;
                }
            } else {
                byteArrayOutputStream3 = null;
                outputStream = null;
            }
            property = ScriptableObject.getProperty(scriptable3, NotificationCompat.CATEGORY_ERROR);
            if (property != Scriptable.NOT_FOUND) {
                OutputStream b2 = b(property);
                if (b2 == null) {
                    byteArrayOutputStream4 = new ByteArrayOutputStream();
                    outputStream3 = byteArrayOutputStream4;
                } else {
                    outputStream3 = b2;
                    byteArrayOutputStream4 = null;
                }
            } else {
                byteArrayOutputStream4 = null;
                outputStream3 = null;
            }
            property2 = ScriptableObject.getProperty(scriptable3, "args");
            OutputStream outputStream4;
            if (property2 != Scriptable.NOT_FOUND) {
                byteArrayOutputStream2 = byteArrayOutputStream4;
                byteArrayOutputStream = byteArrayOutputStream3;
                i = i3;
                elements = context.getElements(Context.toObject(property2, getTopLevelScope(scriptable)));
                property2 = property;
                property = property6;
                outputStream4 = outputStream3;
                scriptable2 = scriptable3;
                outputStream2 = outputStream4;
            } else {
                property2 = property;
                byteArrayOutputStream2 = byteArrayOutputStream4;
                byteArrayOutputStream = byteArrayOutputStream3;
                property = property6;
                elements = null;
                i = i3;
                outputStream4 = outputStream3;
                scriptable2 = scriptable3;
                outputStream2 = outputStream4;
            }
        } else {
            outputStream = null;
            property = null;
            a = null;
            scriptable2 = null;
            outputStream2 = null;
            byteArrayOutputStream = null;
            property2 = null;
            strArr = null;
            file = null;
            elements = null;
            i = length;
        }
        Global a2 = a(function);
        b = outputStream == null ? a2 != null ? a2.getOut() : System.out : outputStream;
        if (outputStream2 == null) {
            outputStream = a2 != null ? a2.getErr() : System.err;
        } else {
            outputStream = outputStream2;
        }
        String[] strArr3 = new String[(elements == null ? i : elements.length + i)];
        for (i2 = 0; i2 != i; i2++) {
            strArr3[i2] = ScriptRuntime.toString(objArr[i2]);
        }
        if (elements != null) {
            for (i2 = 0; i2 != elements.length; i2++) {
                strArr3[i + i2] = ScriptRuntime.toString(elements[i2]);
            }
        }
        int a3 = a(strArr3, strArr, file, a, b, outputStream);
        if (byteArrayOutputStream != null) {
            ScriptableObject.putProperty(scriptable2, "output", ScriptRuntime.toString(property) + byteArrayOutputStream.toString());
        }
        if (byteArrayOutputStream2 != null) {
            ScriptableObject.putProperty(scriptable2, NotificationCompat.CATEGORY_ERROR, ScriptRuntime.toString(property2) + byteArrayOutputStream2.toString());
        }
        return new Integer(a3);
    }

    public static void seal(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        int i = 0;
        int i2 = 0;
        while (i2 != objArr.length) {
            Object obj = objArr[i2];
            if ((obj instanceof ScriptableObject) && obj != Undefined.instance) {
                i2++;
            } else if (!(obj instanceof Scriptable) || obj == Undefined.instance) {
                throw reportRuntimeError("msg.shell.seal.not.object");
            } else {
                throw reportRuntimeError("msg.shell.seal.not.scriptable");
            }
        }
        while (i != objArr.length) {
            ((ScriptableObject) objArr[i]).sealObject();
            i++;
        }
    }

    public static Object readFile(Context context, Scriptable scriptable, Object[] objArr, Function function) throws IOException {
        if (objArr.length == 0) {
            throw reportRuntimeError("msg.shell.readFile.bad.args");
        }
        String scriptRuntime = ScriptRuntime.toString(objArr[0]);
        String str = null;
        if (objArr.length >= 2) {
            str = ScriptRuntime.toString(objArr[1]);
        }
        return a(scriptRuntime, str, true);
    }

    public static Object readUrl(Context context, Scriptable scriptable, Object[] objArr, Function function) throws IOException {
        if (objArr.length == 0) {
            throw reportRuntimeError("msg.shell.readUrl.bad.args");
        }
        String scriptRuntime = ScriptRuntime.toString(objArr[0]);
        String str = null;
        if (objArr.length >= 2) {
            str = ScriptRuntime.toString(objArr[1]);
        }
        return a(scriptRuntime, str, false);
    }

    public static Object toint32(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        Object obj = objArr.length != 0 ? objArr[0] : Undefined.instance;
        return obj instanceof Integer ? obj : ScriptRuntime.wrapInt(ScriptRuntime.toInt32(obj));
    }

    private boolean a(Charset charset) {
        if (!this.attemptedJLineLoad) {
            this.attemptedJLineLoad = true;
            this.console = f.a(this, charset);
        }
        if (this.console != null) {
            return true;
        }
        return false;
    }

    public f getConsole(Charset charset) {
        if (!a(charset)) {
            this.console = f.a(getIn(), getErr(), charset);
        }
        return this.console;
    }

    public InputStream getIn() {
        if (this.inStream == null && !this.attemptedJLineLoad && a(Charset.defaultCharset())) {
            this.inStream = this.console.a();
        }
        return this.inStream == null ? System.in : this.inStream;
    }

    public void setIn(InputStream inputStream) {
        this.inStream = inputStream;
    }

    public PrintStream getOut() {
        return this.outStream == null ? System.out : this.outStream;
    }

    public void setOut(PrintStream printStream) {
        this.outStream = printStream;
    }

    public PrintStream getErr() {
        return this.errStream == null ? System.err : this.errStream;
    }

    public void setErr(PrintStream printStream) {
        this.errStream = printStream;
    }

    public void setSealedStdLib(boolean z) {
        this.sealedStdLib = z;
    }

    private static Global a(Function function) {
        Scriptable parentScope = function.getParentScope();
        if (parentScope instanceof Global) {
            return (Global) parentScope;
        }
        throw reportRuntimeError("msg.bad.shell.function.scope", String.valueOf(parentScope));
    }

    private static int a(String[] strArr, String[] strArr2, File file, InputStream inputStream, OutputStream outputStream, OutputStream outputStream2) throws IOException {
        Process exec;
        b bVar;
        b bVar2 = null;
        if (strArr2 == null) {
            exec = Runtime.getRuntime().exec(strArr, null, file);
        } else {
            exec = Runtime.getRuntime().exec(strArr, strArr2, file);
        }
        if (inputStream != null) {
            try {
                b bVar3 = new b(false, inputStream, exec.getOutputStream());
                bVar3.start();
                bVar = bVar3;
            } catch (Throwable th) {
                exec.destroy();
            }
        } else {
            exec.getOutputStream().close();
            bVar = null;
        }
        if (outputStream != null) {
            bVar3 = new b(true, exec.getInputStream(), outputStream);
            bVar3.start();
        } else {
            exec.getInputStream().close();
            bVar3 = null;
        }
        if (outputStream2 != null) {
            bVar2 = new b(true, exec.getErrorStream(), outputStream2);
            bVar2.start();
        } else {
            exec.getErrorStream().close();
        }
        while (true) {
            try {
                break;
            } catch (InterruptedException e) {
            }
        }
        exec.waitFor();
        if (bVar3 != null) {
            bVar3.join();
        }
        if (bVar != null) {
            bVar.join();
        }
        if (bVar2 != null) {
            bVar2.join();
        }
        int exitValue = exec.exitValue();
        exec.destroy();
        return exitValue;
    }

    static void pipe(boolean z, InputStream inputStream, OutputStream outputStream) throws IOException {
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read;
                if (z) {
                    try {
                        read = inputStream.read(bArr, 0, 4096);
                    } catch (IOException e) {
                    }
                } else {
                    read = inputStream.read(bArr, 0, 4096);
                }
                if (read < 0) {
                    break;
                } else if (z) {
                    outputStream.write(bArr, 0, read);
                    outputStream.flush();
                } else {
                    try {
                        outputStream.write(bArr, 0, read);
                        outputStream.flush();
                    } catch (IOException e2) {
                    }
                }
            }
            if (z) {
                try {
                    inputStream.close();
                    return;
                } catch (IOException e3) {
                    return;
                }
            }
            outputStream.close();
        } catch (Throwable th) {
            if (z) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                }
            } else {
                outputStream.close();
            }
        }
    }

    private static InputStream a(Object obj) throws IOException {
        String str;
        InputStream inputStream = null;
        if (obj instanceof Wrapper) {
            Object unwrap = ((Wrapper) obj).unwrap();
            if (unwrap instanceof InputStream) {
                inputStream = (InputStream) unwrap;
                str = null;
            } else if (unwrap instanceof byte[]) {
                InputStream byteArrayInputStream = new ByteArrayInputStream((byte[]) unwrap);
                str = null;
                inputStream = byteArrayInputStream;
            } else if (unwrap instanceof Reader) {
                str = a((Reader) unwrap);
            } else if (unwrap instanceof char[]) {
                str = new String((char[]) unwrap);
            }
            if (inputStream == null) {
                return inputStream;
            }
            if (str == null) {
                str = ScriptRuntime.toString(obj);
            }
            return new ByteArrayInputStream(str.getBytes());
        }
        str = null;
        if (inputStream == null) {
            return inputStream;
        }
        if (str == null) {
            str = ScriptRuntime.toString(obj);
        }
        return new ByteArrayInputStream(str.getBytes());
    }

    private static OutputStream b(Object obj) {
        if (obj instanceof Wrapper) {
            Object unwrap = ((Wrapper) obj).unwrap();
            if (unwrap instanceof OutputStream) {
                return (OutputStream) unwrap;
            }
        }
        return null;
    }

    private static String a(String str, String str2, boolean z) throws IOException {
        int i;
        String str3;
        Reader inputStreamReader;
        InputStream inputStream = null;
        if (z) {
            File file = new File(str);
            if (!file.exists()) {
                throw new FileNotFoundException("File not found: " + str);
            } else if (file.canRead()) {
                long length = file.length();
                i = (int) length;
                if (((long) i) != length) {
                    throw new IOException("Too big file size: " + length);
                } else if (i == 0) {
                    str3 = "";
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return str3;
                } else {
                    inputStream = new FileInputStream(file);
                }
            } else {
                throw new IOException("Cannot read file: " + str);
            }
        }
        try {
            URLConnection openConnection = new URL(str).openConnection();
            inputStream = openConnection.getInputStream();
            int contentLength = openConnection.getContentLength();
            if (contentLength <= 0) {
                contentLength = 1024;
            }
            if (str2 == null) {
                String contentType = openConnection.getContentType();
                if (contentType != null) {
                    str2 = a(contentType);
                }
            }
            i = contentLength;
        } catch (Throwable th) {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        if (str2 == null) {
            inputStreamReader = new InputStreamReader(inputStream);
        } else {
            inputStreamReader = new InputStreamReader(inputStream, str2);
        }
        str3 = a(inputStreamReader, i);
        if (inputStream != null) {
            inputStream.close();
        }
        return str3;
    }

    private static String a(String str) {
        int indexOf = str.indexOf(59);
        if (indexOf >= 0) {
            int length = str.length();
            int i = indexOf + 1;
            while (i != length && str.charAt(i) <= ' ') {
                i++;
            }
            String str2 = "charset";
            if (str2.regionMatches(true, 0, str, i, str2.length())) {
                indexOf = str2.length() + i;
                while (indexOf != length && str.charAt(indexOf) <= ' ') {
                    indexOf++;
                }
                if (indexOf != length && str.charAt(indexOf) == '=') {
                    int i2 = indexOf + 1;
                    while (i2 != length && str.charAt(i2) <= ' ') {
                        i2++;
                    }
                    if (i2 != length) {
                        indexOf = length;
                        while (str.charAt(indexOf - 1) <= ' ') {
                            indexOf--;
                        }
                        return str.substring(i2, indexOf);
                    }
                }
            }
        }
        return null;
    }

    private static String a(Reader reader) throws IOException {
        return a(reader, 4096);
    }

    private static String a(Reader reader, int i) throws IOException {
        Object obj = new char[i];
        int i2 = 0;
        while (true) {
            int read = reader.read(obj, i2, obj.length - i2);
            if (read < 0) {
                return new String(obj, 0, i2);
            }
            i2 += read;
            if (i2 == obj.length) {
                Object obj2 = new char[(obj.length * 2)];
                System.arraycopy(obj, 0, obj2, 0, i2);
                obj = obj2;
            }
        }
    }

    static RuntimeException reportRuntimeError(String str) {
        return Context.reportRuntimeError(b.a(str));
    }

    static RuntimeException reportRuntimeError(String str, String str2) {
        return Context.reportRuntimeError(b.a(str, str2));
    }
}
