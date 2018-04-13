package org.mozilla.javascript;

import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;

public class NativeJavaTopPackage extends NativeJavaPackage implements Function, IdFunctionCall {
    private static final Object FTAG = "JavaTopPackage";
    private static final int Id_getClass = 1;
    private static final String[][] commonPackages;
    static final long serialVersionUID = -1455787259477709999L;

    static {
        r0 = new String[8][];
        r0[0] = new String[]{"java", Parameters.LANGUAGE, "reflect"};
        r0[1] = new String[]{"java", "io"};
        r0[2] = new String[]{"java", "math"};
        r0[3] = new String[]{"java", "net"};
        r0[4] = new String[]{"java", "util", "zip"};
        r0[5] = new String[]{"java", "text", "resources"};
        r0[6] = new String[]{"java", "applet"};
        r0[7] = new String[]{"javax", "swing"};
        commonPackages = r0;
    }

    NativeJavaTopPackage(ClassLoader classLoader) {
        super(true, "", classLoader);
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return construct(context, scriptable, objArr);
    }

    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        ClassLoader classLoader;
        if (objArr.length != 0) {
            Object obj = objArr[0];
            if (obj instanceof Wrapper) {
                obj = ((Wrapper) obj).unwrap();
            }
            if (obj instanceof ClassLoader) {
                classLoader = (ClassLoader) obj;
                if (classLoader != null) {
                    Context.reportRuntimeError0("msg.not.classloader");
                    return null;
                }
                Scriptable nativeJavaPackage = new NativeJavaPackage(true, "", classLoader);
                ScriptRuntime.setObjectProtoAndParent(nativeJavaPackage, scriptable);
                return nativeJavaPackage;
            }
        }
        classLoader = null;
        if (classLoader != null) {
            Scriptable nativeJavaPackage2 = new NativeJavaPackage(true, "", classLoader);
            ScriptRuntime.setObjectProtoAndParent(nativeJavaPackage2, scriptable);
            return nativeJavaPackage2;
        }
        Context.reportRuntimeError0("msg.not.classloader");
        return null;
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        int i = 0;
        Scriptable nativeJavaTopPackage = new NativeJavaTopPackage(context.getApplicationClassLoader());
        nativeJavaTopPackage.setPrototype(getObjectPrototype(scriptable));
        nativeJavaTopPackage.setParentScope(scriptable);
        for (int i2 = 0; i2 != commonPackages.length; i2++) {
            NativeJavaPackage nativeJavaPackage = nativeJavaTopPackage;
            for (int i3 = 0; i3 != commonPackages[i2].length; i3++) {
                nativeJavaPackage = nativeJavaPackage.forcePackage(commonPackages[i2][i3], scriptable);
            }
        }
        IdFunctionObject idFunctionObject = new IdFunctionObject(nativeJavaTopPackage, FTAG, 1, "getClass", 1, scriptable);
        String[] topPackageNames = ScriptRuntime.getTopPackageNames();
        NativeJavaPackage[] nativeJavaPackageArr = new NativeJavaPackage[topPackageNames.length];
        for (int i4 = 0; i4 < topPackageNames.length; i4++) {
            nativeJavaPackageArr[i4] = (NativeJavaPackage) nativeJavaTopPackage.get(topPackageNames[i4], nativeJavaTopPackage);
        }
        ScriptableObject scriptableObject = (ScriptableObject) scriptable;
        if (z) {
            idFunctionObject.sealObject();
        }
        idFunctionObject.exportAsScopeProperty();
        scriptableObject.defineProperty("Packages", nativeJavaTopPackage, 2);
        while (i < topPackageNames.length) {
            scriptableObject.defineProperty(topPackageNames[i], nativeJavaPackageArr[i], 2);
            i++;
        }
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (idFunctionObject.hasTag(FTAG) && idFunctionObject.methodId() == 1) {
            return js_getClass(context, scriptable, objArr);
        }
        throw idFunctionObject.unknown();
    }

    private Scriptable js_getClass(Context context, Scriptable scriptable, Object[] objArr) {
        if (objArr.length > 0 && (objArr[0] instanceof Wrapper)) {
            String name = ((Wrapper) objArr[0]).unwrap().getClass().getName();
            int i = 0;
            while (true) {
                Scriptable scriptable2;
                int indexOf = name.indexOf(46, i);
                Object obj = scriptable2.get(indexOf == -1 ? name.substring(i) : name.substring(i, indexOf), scriptable2);
                if (!(obj instanceof Scriptable)) {
                    break;
                }
                Scriptable scriptable3 = (Scriptable) obj;
                if (indexOf == -1) {
                    return scriptable3;
                }
                scriptable2 = scriptable3;
                i = indexOf + 1;
            }
        }
        throw Context.reportRuntimeError0("msg.not.java.obj");
    }
}
