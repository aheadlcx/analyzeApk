package org.mozilla.javascript;

import com.alibaba.sdk.android.mns.common.MNSConstants;
import java.util.Locale;
import org.mozilla.javascript.NativeIterator.StopIteration;
import org.mozilla.javascript.TopLevel.Builtins;
import org.mozilla.javascript.TopLevel.NativeErrors;
import org.mozilla.javascript.typedarrays.NativeArrayBuffer;
import org.mozilla.javascript.typedarrays.NativeDataView;
import org.mozilla.javascript.v8dtoa.DoubleConversion;
import org.mozilla.javascript.v8dtoa.FastDtoa;
import org.mozilla.javascript.xml.XMLLib;
import org.mozilla.javascript.xml.XMLObject;

public class ScriptRuntime {
    public static final Class<?> BooleanClass = Kit.classOrNull("java.lang.Boolean");
    public static final Class<?> ByteClass = Kit.classOrNull("java.lang.Byte");
    public static final Class<?> CharacterClass = Kit.classOrNull("java.lang.Character");
    public static final Class<?> ClassClass = Kit.classOrNull("java.lang.Class");
    public static final Class<?> ContextClass = Kit.classOrNull("org.mozilla.javascript.Context");
    public static final Class<?> ContextFactoryClass = Kit.classOrNull("org.mozilla.javascript.ContextFactory");
    private static final String DEFAULT_NS_TAG = "__default_namespace__";
    public static final Class<?> DateClass = Kit.classOrNull("java.util.Date");
    public static final Class<?> DoubleClass = Kit.classOrNull("java.lang.Double");
    public static final int ENUMERATE_ARRAY = 2;
    public static final int ENUMERATE_ARRAY_NO_ITERATOR = 5;
    public static final int ENUMERATE_KEYS = 0;
    public static final int ENUMERATE_KEYS_NO_ITERATOR = 3;
    public static final int ENUMERATE_VALUES = 1;
    public static final int ENUMERATE_VALUES_NO_ITERATOR = 4;
    public static final Class<?> FloatClass = Kit.classOrNull("java.lang.Float");
    public static final Class<?> FunctionClass = Kit.classOrNull("org.mozilla.javascript.Function");
    public static final Class<?> IntegerClass = Kit.classOrNull("java.lang.Integer");
    private static final Object LIBRARY_SCOPE_KEY = "LIBRARY_SCOPE";
    public static final Class<?> LongClass = Kit.classOrNull("java.lang.Long");
    public static final double NaN = Double.longBitsToDouble(9221120237041090560L);
    public static final Double NaNobj = new Double(NaN);
    public static final Class<?> NumberClass = Kit.classOrNull("java.lang.Number");
    public static final Class<?> ObjectClass = Kit.classOrNull("java.lang.Object");
    public static Locale ROOT_LOCALE = new Locale("");
    public static final Class<Scriptable> ScriptableClass = Scriptable.class;
    public static final Class<?> ScriptableObjectClass = Kit.classOrNull("org.mozilla.javascript.ScriptableObject");
    public static final Class<?> ShortClass = Kit.classOrNull("java.lang.Short");
    public static final Class<?> StringClass = Kit.classOrNull("java.lang.String");
    public static final Object[] emptyArgs = new Object[0];
    public static final String[] emptyStrings = new String[0];
    public static ScriptRuntime$MessageProvider messageProvider = new ScriptRuntime$DefaultMessageProvider(null);
    public static final double negativeZero = Double.longBitsToDouble(Long.MIN_VALUE);

    protected ScriptRuntime() {
    }

    @Deprecated
    public static BaseFunction typeErrorThrower() {
        return typeErrorThrower(Context.getCurrentContext());
    }

    public static BaseFunction typeErrorThrower(Context context) {
        if (context.typeErrorThrower == null) {
            BaseFunction scriptRuntime$1 = new ScriptRuntime$1();
            setFunctionProtoAndParent(scriptRuntime$1, context.topCallScope);
            scriptRuntime$1.preventExtensions();
            context.typeErrorThrower = scriptRuntime$1;
        }
        return context.typeErrorThrower;
    }

    public static boolean isRhinoRuntimeType(Class<?> cls) {
        boolean z = false;
        if (!cls.isPrimitive()) {
            if (cls == StringClass || cls == BooleanClass || NumberClass.isAssignableFrom(cls) || ScriptableClass.isAssignableFrom(cls)) {
                z = true;
            }
            return z;
        } else if (cls != Character.TYPE) {
            return true;
        } else {
            return false;
        }
    }

    public static ScriptableObject initSafeStandardObjects(Context context, ScriptableObject scriptableObject, boolean z) {
        ScriptableObject nativeObject;
        if (scriptableObject == null) {
            nativeObject = new NativeObject();
        } else {
            nativeObject = scriptableObject;
        }
        nativeObject.associateValue(LIBRARY_SCOPE_KEY, nativeObject);
        new ClassCache().associate(nativeObject);
        BaseFunction.init(nativeObject, z);
        NativeObject.init(nativeObject, z);
        Scriptable objectPrototype = ScriptableObject.getObjectPrototype(nativeObject);
        ScriptableObject.getClassPrototype(nativeObject, "Function").setPrototype(objectPrototype);
        if (nativeObject.getPrototype() == null) {
            nativeObject.setPrototype(objectPrototype);
        }
        NativeError.init(nativeObject, z);
        NativeGlobal.init(context, nativeObject, z);
        NativeArray.init(nativeObject, z);
        if (context.getOptimizationLevel() > 0) {
            NativeArray.setMaximumInitialCapacity(200000);
        }
        NativeString.init(nativeObject, z);
        NativeBoolean.init(nativeObject, z);
        NativeNumber.init(nativeObject, z);
        NativeDate.init(nativeObject, z);
        NativeMath.init(nativeObject, z);
        NativeJSON.init(nativeObject, z);
        NativeWith.init(nativeObject, z);
        NativeCall.init(nativeObject, z);
        NativeScript.init(nativeObject, z);
        NativeIterator.init(nativeObject, z);
        boolean z2 = context.hasFeature(6) && context.getE4xImplementationFactory() != null;
        LazilyLoadedCtor lazilyLoadedCtor = new LazilyLoadedCtor(nativeObject, "RegExp", "org.mozilla.javascript.regexp.NativeRegExp", z, true);
        lazilyLoadedCtor = new LazilyLoadedCtor(nativeObject, "Continuation", "org.mozilla.javascript.NativeContinuation", z, true);
        if (z2) {
            String implementationClassName = context.getE4xImplementationFactory().getImplementationClassName();
            lazilyLoadedCtor = new LazilyLoadedCtor(nativeObject, MNSConstants.DEFAULT_NOTIFY_CONTENT_TYPE, implementationClassName, z, true);
            lazilyLoadedCtor = new LazilyLoadedCtor(nativeObject, "XMLList", implementationClassName, z, true);
            lazilyLoadedCtor = new LazilyLoadedCtor(nativeObject, "Namespace", implementationClassName, z, true);
            lazilyLoadedCtor = new LazilyLoadedCtor(nativeObject, "QName", implementationClassName, z, true);
        }
        if (context.getLanguageVersion() >= 180 && context.hasFeature(14)) {
            lazilyLoadedCtor = new LazilyLoadedCtor(nativeObject, NativeArrayBuffer.CLASS_NAME, "org.mozilla.javascript.typedarrays.NativeArrayBuffer", z, true);
            lazilyLoadedCtor = new LazilyLoadedCtor(nativeObject, "Int8Array", "org.mozilla.javascript.typedarrays.NativeInt8Array", z, true);
            lazilyLoadedCtor = new LazilyLoadedCtor(nativeObject, "Uint8Array", "org.mozilla.javascript.typedarrays.NativeUint8Array", z, true);
            lazilyLoadedCtor = new LazilyLoadedCtor(nativeObject, "Uint8ClampedArray", "org.mozilla.javascript.typedarrays.NativeUint8ClampedArray", z, true);
            lazilyLoadedCtor = new LazilyLoadedCtor(nativeObject, "Int16Array", "org.mozilla.javascript.typedarrays.NativeInt16Array", z, true);
            lazilyLoadedCtor = new LazilyLoadedCtor(nativeObject, "Uint16Array", "org.mozilla.javascript.typedarrays.NativeUint16Array", z, true);
            lazilyLoadedCtor = new LazilyLoadedCtor(nativeObject, "Int32Array", "org.mozilla.javascript.typedarrays.NativeInt32Array", z, true);
            lazilyLoadedCtor = new LazilyLoadedCtor(nativeObject, "Uint32Array", "org.mozilla.javascript.typedarrays.NativeUint32Array", z, true);
            lazilyLoadedCtor = new LazilyLoadedCtor(nativeObject, "Float32Array", "org.mozilla.javascript.typedarrays.NativeFloat32Array", z, true);
            lazilyLoadedCtor = new LazilyLoadedCtor(nativeObject, "Float64Array", "org.mozilla.javascript.typedarrays.NativeFloat64Array", z, true);
            lazilyLoadedCtor = new LazilyLoadedCtor(nativeObject, NativeDataView.CLASS_NAME, "org.mozilla.javascript.typedarrays.NativeDataView", z, true);
        }
        if (nativeObject instanceof TopLevel) {
            ((TopLevel) nativeObject).cacheBuiltins();
        }
        return nativeObject;
    }

    public static ScriptableObject initStandardObjects(Context context, ScriptableObject scriptableObject, boolean z) {
        ScriptableObject initSafeStandardObjects = initSafeStandardObjects(context, scriptableObject, z);
        LazilyLoadedCtor lazilyLoadedCtor = new LazilyLoadedCtor(initSafeStandardObjects, "Packages", "org.mozilla.javascript.NativeJavaTopPackage", z, true);
        lazilyLoadedCtor = new LazilyLoadedCtor(initSafeStandardObjects, "getClass", "org.mozilla.javascript.NativeJavaTopPackage", z, true);
        lazilyLoadedCtor = new LazilyLoadedCtor(initSafeStandardObjects, "JavaAdapter", "org.mozilla.javascript.JavaAdapter", z, true);
        lazilyLoadedCtor = new LazilyLoadedCtor(initSafeStandardObjects, "JavaImporter", "org.mozilla.javascript.ImporterTopLevel", z, true);
        for (String lazilyLoadedCtor2 : getTopPackageNames()) {
            lazilyLoadedCtor = new LazilyLoadedCtor(initSafeStandardObjects, lazilyLoadedCtor2, "org.mozilla.javascript.NativeJavaTopPackage", z, true);
        }
        return initSafeStandardObjects;
    }

    static String[] getTopPackageNames() {
        if ("Dalvik".equals(System.getProperty("java.vm.name"))) {
            return new String[]{"java", "javax", "org", "com", "edu", "net", "android"};
        }
        return new String[]{"java", "javax", "org", "com", "edu", "net"};
    }

    public static ScriptableObject getLibraryScopeOrNull(Scriptable scriptable) {
        return (ScriptableObject) ScriptableObject.getTopScopeValue(scriptable, LIBRARY_SCOPE_KEY);
    }

    public static boolean isJSLineTerminator(int i) {
        if ((57296 & i) != 0) {
            return false;
        }
        if (i == 10 || i == 13 || i == 8232 || i == 8233) {
            return true;
        }
        return false;
    }

    public static boolean isJSWhitespaceOrLineTerminator(int i) {
        return isStrWhiteSpaceChar(i) || isJSLineTerminator(i);
    }

    static boolean isStrWhiteSpaceChar(int i) {
        switch (i) {
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 32:
            case 160:
            case 8232:
            case 8233:
            case 65279:
                return true;
            default:
                return Character.getType(i) == 12;
        }
    }

    public static Boolean wrapBoolean(boolean z) {
        return z ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Integer wrapInt(int i) {
        return Integer.valueOf(i);
    }

    public static Number wrapNumber(double d) {
        if (d != d) {
            return NaNobj;
        }
        return new Double(d);
    }

    public static boolean toBoolean(Object obj) {
        Object obj2 = obj;
        while (!(obj2 instanceof Boolean)) {
            if (obj2 == null || obj2 == Undefined.instance) {
                return false;
            }
            if (obj2 instanceof CharSequence) {
                return ((CharSequence) obj2).length() != 0;
            } else if (obj2 instanceof Number) {
                double doubleValue = ((Number) obj2).doubleValue();
                if (doubleValue != doubleValue || doubleValue == 0.0d) {
                    return false;
                }
                return true;
            } else if (!(obj2 instanceof Scriptable)) {
                warnAboutNonJSObject(obj2);
                return true;
            } else if ((obj2 instanceof ScriptableObject) && ((ScriptableObject) obj2).avoidObjectDetection()) {
                return false;
            } else {
                if (Context.getContext().isVersionECMA1()) {
                    return true;
                }
                obj2 = ((Scriptable) obj2).getDefaultValue(BooleanClass);
                if (obj2 instanceof Scriptable) {
                    throw errorWithClassName("msg.primitive.expected", obj2);
                }
            }
        }
        return ((Boolean) obj2).booleanValue();
    }

    public static double toNumber(Object obj) {
        Object obj2 = obj;
        while (!(obj2 instanceof Number)) {
            if (obj2 == null) {
                return 0.0d;
            }
            if (obj2 == Undefined.instance) {
                return NaN;
            }
            if (obj2 instanceof String) {
                return toNumber((String) obj2);
            }
            if (obj2 instanceof CharSequence) {
                return toNumber(obj2.toString());
            }
            if (obj2 instanceof Boolean) {
                return ((Boolean) obj2).booleanValue() ? 1.0d : 0.0d;
            } else if (obj2 instanceof Scriptable) {
                obj2 = ((Scriptable) obj2).getDefaultValue(NumberClass);
                if (obj2 instanceof Scriptable) {
                    throw errorWithClassName("msg.primitive.expected", obj2);
                }
            } else {
                warnAboutNonJSObject(obj2);
                return NaN;
            }
        }
        return ((Number) obj2).doubleValue();
    }

    public static double toNumber(Object[] objArr, int i) {
        return i < objArr.length ? toNumber(objArr[i]) : NaN;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static double stringToNumber(java.lang.String r20, int r21, int r22) {
        /*
        r4 = 57;
        r3 = 97;
        r2 = 65;
        r6 = r20.length();
        r5 = 10;
        r0 = r22;
        if (r0 >= r5) goto L_0x0159;
    L_0x0010:
        r4 = r22 + 48;
        r4 = r4 + -1;
        r4 = (char) r4;
        r5 = r4;
    L_0x0016:
        r4 = 10;
        r0 = r22;
        if (r0 <= r4) goto L_0x0026;
    L_0x001c:
        r2 = r22 + 97;
        r2 = r2 + -10;
        r3 = (char) r2;
        r2 = r22 + 65;
        r2 = r2 + -10;
        r2 = (char) r2;
    L_0x0026:
        r10 = 0;
        r15 = r21;
    L_0x002a:
        if (r15 >= r6) goto L_0x005a;
    L_0x002c:
        r0 = r20;
        r4 = r0.charAt(r15);
        r7 = 48;
        if (r7 > r4) goto L_0x0044;
    L_0x0036:
        if (r4 > r5) goto L_0x0044;
    L_0x0038:
        r4 = r4 + -48;
    L_0x003a:
        r0 = r22;
        r8 = (double) r0;
        r8 = r8 * r10;
        r10 = (double) r4;
        r10 = r10 + r8;
        r4 = r15 + 1;
        r15 = r4;
        goto L_0x002a;
    L_0x0044:
        r7 = 97;
        if (r7 > r4) goto L_0x004f;
    L_0x0048:
        if (r4 >= r3) goto L_0x004f;
    L_0x004a:
        r4 = r4 + -97;
        r4 = r4 + 10;
        goto L_0x003a;
    L_0x004f:
        r7 = 65;
        if (r7 > r4) goto L_0x005a;
    L_0x0053:
        if (r4 >= r2) goto L_0x005a;
    L_0x0055:
        r4 = r4 + -65;
        r4 = r4 + 10;
        goto L_0x003a;
    L_0x005a:
        r0 = r21;
        if (r0 != r15) goto L_0x0061;
    L_0x005e:
        r10 = NaN;
    L_0x0060:
        return r10;
    L_0x0061:
        r2 = 4845873199050653696; // 0x4340000000000000 float:0.0 double:9.007199254740992E15;
        r2 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1));
        if (r2 < 0) goto L_0x0060;
    L_0x0067:
        r2 = 10;
        r0 = r22;
        if (r0 != r2) goto L_0x007e;
    L_0x006d:
        r0 = r20;
        r1 = r21;
        r2 = r0.substring(r1, r15);	 Catch:{ NumberFormatException -> 0x007a }
        r10 = java.lang.Double.parseDouble(r2);	 Catch:{ NumberFormatException -> 0x007a }
        goto L_0x0060;
    L_0x007a:
        r2 = move-exception;
        r10 = NaN;
        goto L_0x0060;
    L_0x007e:
        r2 = 2;
        r0 = r22;
        if (r0 == r2) goto L_0x009a;
    L_0x0083:
        r2 = 4;
        r0 = r22;
        if (r0 == r2) goto L_0x009a;
    L_0x0088:
        r2 = 8;
        r0 = r22;
        if (r0 == r2) goto L_0x009a;
    L_0x008e:
        r2 = 16;
        r0 = r22;
        if (r0 == r2) goto L_0x009a;
    L_0x0094:
        r2 = 32;
        r0 = r22;
        if (r0 != r2) goto L_0x0060;
    L_0x009a:
        r9 = 1;
        r2 = 0;
        r3 = 0;
        r8 = 53;
        r6 = 0;
        r5 = 0;
        r4 = 0;
        r12 = r21;
    L_0x00a5:
        r13 = 1;
        if (r9 != r13) goto L_0x0154;
    L_0x00a8:
        if (r12 != r15) goto L_0x00b1;
    L_0x00aa:
        switch(r3) {
            case 0: goto L_0x00ae;
            case 1: goto L_0x0060;
            case 2: goto L_0x0060;
            case 3: goto L_0x0140;
            case 4: goto L_0x014a;
            default: goto L_0x00ad;
        };
    L_0x00ad:
        goto L_0x0060;
    L_0x00ae:
        r10 = 0;
        goto L_0x0060;
    L_0x00b1:
        r9 = r12 + 1;
        r0 = r20;
        r2 = r0.charAt(r12);
        r12 = 48;
        if (r12 > r2) goto L_0x00e7;
    L_0x00bd:
        r12 = 57;
        if (r2 > r12) goto L_0x00e7;
    L_0x00c1:
        r2 = r2 + -48;
    L_0x00c3:
        r12 = r2;
        r13 = r9;
        r2 = r22;
    L_0x00c7:
        r14 = r2 >> 1;
        r2 = r12 & r14;
        if (r2 == 0) goto L_0x00f5;
    L_0x00cd:
        r2 = 1;
    L_0x00ce:
        switch(r3) {
            case 0: goto L_0x00f7;
            case 1: goto L_0x010a;
            case 2: goto L_0x0123;
            case 3: goto L_0x012f;
            case 4: goto L_0x0152;
            default: goto L_0x00d1;
        };
    L_0x00d1:
        r2 = r4;
        r18 = r5;
        r4 = r6;
        r6 = r8;
        r7 = r3;
        r3 = r18;
        r8 = r10;
    L_0x00da:
        r10 = r8;
        r8 = r6;
        r9 = r14;
        r18 = r3;
        r3 = r7;
        r6 = r4;
        r5 = r18;
        r4 = r2;
        r2 = r12;
        r12 = r13;
        goto L_0x00a5;
    L_0x00e7:
        r12 = 97;
        if (r12 > r2) goto L_0x00f2;
    L_0x00eb:
        r12 = 122; // 0x7a float:1.71E-43 double:6.03E-322;
        if (r2 > r12) goto L_0x00f2;
    L_0x00ef:
        r2 = r2 + -87;
        goto L_0x00c3;
    L_0x00f2:
        r2 = r2 + -55;
        goto L_0x00c3;
    L_0x00f5:
        r2 = 0;
        goto L_0x00ce;
    L_0x00f7:
        if (r2 == 0) goto L_0x00d1;
    L_0x00f9:
        r2 = r8 + -1;
        r8 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r3 = 1;
        r18 = r4;
        r19 = r5;
        r4 = r6;
        r6 = r2;
        r7 = r3;
        r2 = r18;
        r3 = r19;
        goto L_0x00da;
    L_0x010a:
        r16 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r10 = r10 * r16;
        if (r2 == 0) goto L_0x0114;
    L_0x0110:
        r16 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r10 = r10 + r16;
    L_0x0114:
        r8 = r8 + -1;
        if (r8 != 0) goto L_0x00d1;
    L_0x0118:
        r3 = 2;
        r18 = r4;
        r4 = r6;
        r6 = r8;
        r7 = r3;
        r3 = r2;
        r8 = r10;
        r2 = r18;
        goto L_0x00da;
    L_0x0123:
        r6 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r3 = 3;
        r18 = r5;
        r4 = r6;
        r6 = r8;
        r7 = r3;
        r3 = r18;
        r8 = r10;
        goto L_0x00da;
    L_0x012f:
        if (r2 == 0) goto L_0x0152;
    L_0x0131:
        r2 = 4;
    L_0x0132:
        r16 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r6 = r6 * r16;
        r3 = r5;
        r18 = r4;
        r4 = r6;
        r6 = r8;
        r7 = r2;
        r2 = r18;
        r8 = r10;
        goto L_0x00da;
    L_0x0140:
        r2 = r4 & r5;
        if (r2 == 0) goto L_0x0147;
    L_0x0144:
        r2 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r10 = r10 + r2;
    L_0x0147:
        r10 = r10 * r6;
        goto L_0x0060;
    L_0x014a:
        if (r4 == 0) goto L_0x014f;
    L_0x014c:
        r2 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r10 = r10 + r2;
    L_0x014f:
        r10 = r10 * r6;
        goto L_0x0060;
    L_0x0152:
        r2 = r3;
        goto L_0x0132;
    L_0x0154:
        r13 = r12;
        r12 = r2;
        r2 = r9;
        goto L_0x00c7;
    L_0x0159:
        r5 = r4;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.ScriptRuntime.stringToNumber(java.lang.String, int, int):double");
    }

    public static double toNumber(String str) {
        int length = str.length();
        int i = 0;
        while (i != length) {
            char charAt = str.charAt(i);
            if (isStrWhiteSpaceChar(charAt)) {
                i++;
            } else {
                char charAt2;
                if (charAt == '0') {
                    if (i + 2 < length) {
                        charAt2 = str.charAt(i + 1);
                        if (charAt2 == 'x' || charAt2 == 'X') {
                            return stringToNumber(str, i + 2, 16);
                        }
                    }
                } else if ((charAt == '+' || charAt == '-') && i + 3 < length && str.charAt(i + 1) == '0') {
                    charAt2 = str.charAt(i + 2);
                    if (charAt2 == 'x' || charAt2 == 'X') {
                        double stringToNumber = stringToNumber(str, i + 3, 16);
                        if (charAt == '-') {
                            return -stringToNumber;
                        }
                        return stringToNumber;
                    }
                }
                length--;
                while (true) {
                    charAt2 = str.charAt(length);
                    if (!isStrWhiteSpaceChar(charAt2)) {
                        break;
                    }
                    length--;
                }
                if (charAt2 == 'y') {
                    if (charAt == '+' || charAt == '-') {
                        i++;
                    }
                    if (i + 7 == length && str.regionMatches(i, "Infinity", 0, 8)) {
                        return charAt == '-' ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
                    } else {
                        return NaN;
                    }
                }
                String substring = str.substring(i, length + 1);
                for (length = substring.length() - 1; length >= 0; length--) {
                    char charAt3 = substring.charAt(length);
                    if (('0' > charAt3 || charAt3 > '9') && charAt3 != '.' && charAt3 != 'e' && charAt3 != 'E' && charAt3 != '+' && charAt3 != '-') {
                        return NaN;
                    }
                }
                try {
                    return Double.parseDouble(substring);
                } catch (NumberFormatException e) {
                    return NaN;
                }
            }
        }
        return 0.0d;
    }

    public static Object[] padArguments(Object[] objArr, int i) {
        if (i < objArr.length) {
            return objArr;
        }
        Object[] objArr2 = new Object[i];
        int i2 = 0;
        while (i2 < objArr.length) {
            objArr2[i2] = objArr[i2];
            i2++;
        }
        while (i2 < i) {
            objArr2[i2] = Undefined.instance;
            i2++;
        }
        return objArr2;
    }

    public static String escapeString(String str) {
        return escapeString(str, '\"');
    }

    public static String escapeString(String str, char c) {
        if (!(c == '\"' || c == '\'')) {
            Kit.codeBug();
        }
        int length = str.length();
        StringBuilder stringBuilder = null;
        for (int i = 0; i != length; i++) {
            char charAt = str.charAt(i);
            if (' ' > charAt || charAt > '~' || charAt == c || charAt == '\\') {
                StringBuilder stringBuilder2;
                if (stringBuilder == null) {
                    stringBuilder2 = new StringBuilder(length + 3);
                    stringBuilder2.append(str);
                    stringBuilder2.setLength(i);
                } else {
                    stringBuilder2 = stringBuilder;
                }
                int i2 = -1;
                switch (charAt) {
                    case '\b':
                        i2 = 98;
                        break;
                    case '\t':
                        i2 = 116;
                        break;
                    case '\n':
                        i2 = 110;
                        break;
                    case '\u000b':
                        i2 = 118;
                        break;
                    case '\f':
                        i2 = 102;
                        break;
                    case '\r':
                        i2 = 114;
                        break;
                    case ' ':
                        i2 = 32;
                        break;
                    case '\\':
                        i2 = 92;
                        break;
                }
                if (i2 >= 0) {
                    stringBuilder2.append('\\');
                    stringBuilder2.append((char) i2);
                    stringBuilder = stringBuilder2;
                } else if (charAt == c) {
                    stringBuilder2.append('\\');
                    stringBuilder2.append(c);
                    stringBuilder = stringBuilder2;
                } else {
                    if (charAt < 'Ā') {
                        stringBuilder2.append("\\x");
                        i2 = 2;
                    } else {
                        stringBuilder2.append("\\u");
                        i2 = 4;
                    }
                    for (int i3 = (i2 - 1) * 4; i3 >= 0; i3 -= 4) {
                        i2 = (charAt >> i3) & 15;
                        stringBuilder2.append((char) (i2 < 10 ? i2 + 48 : i2 + 87));
                    }
                    stringBuilder = stringBuilder2;
                }
            } else if (stringBuilder != null) {
                stringBuilder.append((char) charAt);
            }
        }
        return stringBuilder == null ? str : stringBuilder.toString();
    }

    static boolean isValidIdentifierName(String str) {
        boolean z = true;
        int length = str.length();
        if (length == 0 || !Character.isJavaIdentifierStart(str.charAt(0))) {
            return false;
        }
        for (int i = 1; i != length; i++) {
            if (!Character.isJavaIdentifierPart(str.charAt(i))) {
                return false;
            }
        }
        if (TokenStream.isKeyword(str)) {
            z = false;
        }
        return z;
    }

    public static CharSequence toCharSequence(Object obj) {
        if (obj instanceof NativeString) {
            return ((NativeString) obj).toCharSequence();
        }
        return obj instanceof CharSequence ? (CharSequence) obj : toString(obj);
    }

    public static String toString(Object obj) {
        Object obj2 = obj;
        while (obj2 != null) {
            if (obj2 == Undefined.instance) {
                return "undefined";
            }
            if (obj2 instanceof String) {
                return (String) obj2;
            }
            if (obj2 instanceof CharSequence) {
                return obj2.toString();
            }
            if (obj2 instanceof Number) {
                return numberToString(((Number) obj2).doubleValue(), 10);
            }
            if (!(obj2 instanceof Scriptable)) {
                return obj2.toString();
            }
            obj2 = ((Scriptable) obj2).getDefaultValue(StringClass);
            if (obj2 instanceof Scriptable) {
                throw errorWithClassName("msg.primitive.expected", obj2);
            }
        }
        return "null";
    }

    static String defaultObjectToString(Scriptable scriptable) {
        return "[object " + scriptable.getClassName() + ']';
    }

    public static String toString(Object[] objArr, int i) {
        return i < objArr.length ? toString(objArr[i]) : "undefined";
    }

    public static String toString(double d) {
        return numberToString(d, 10);
    }

    public static String numberToString(double d, int i) {
        if (i < 2 || i > 36) {
            throw Context.reportRuntimeError1("msg.bad.radix", Integer.toString(i));
        } else if (d != d) {
            return "NaN";
        } else {
            if (d == Double.POSITIVE_INFINITY) {
                return "Infinity";
            }
            if (d == Double.NEGATIVE_INFINITY) {
                return "-Infinity";
            }
            if (d == 0.0d) {
                return "0";
            }
            if (i != 10) {
                return DToA.JS_dtobasestr(i, d);
            }
            String numberToString = FastDtoa.numberToString(d);
            if (numberToString != null) {
                return numberToString;
            }
            StringBuilder stringBuilder = new StringBuilder();
            DToA.JS_dtostr(stringBuilder, 0, 0, d);
            return stringBuilder.toString();
        }
    }

    static String uneval(Context context, Scriptable scriptable, Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj == Undefined.instance) {
            return "undefined";
        }
        if (obj instanceof CharSequence) {
            String escapeString = escapeString(obj.toString());
            StringBuilder stringBuilder = new StringBuilder(escapeString.length() + 2);
            stringBuilder.append('\"');
            stringBuilder.append(escapeString);
            stringBuilder.append('\"');
            return stringBuilder.toString();
        } else if (obj instanceof Number) {
            double doubleValue = ((Number) obj).doubleValue();
            if (doubleValue != 0.0d || 1.0d / doubleValue >= 0.0d) {
                return toString(doubleValue);
            }
            return "-0";
        } else if (obj instanceof Boolean) {
            return toString(obj);
        } else {
            if (obj instanceof Scriptable) {
                Scriptable scriptable2 = (Scriptable) obj;
                if (ScriptableObject.hasProperty(scriptable2, "toSource")) {
                    Object property = ScriptableObject.getProperty(scriptable2, "toSource");
                    if (property instanceof Function) {
                        return toString(((Function) property).call(context, scriptable, scriptable2, emptyArgs));
                    }
                }
                return toString(obj);
            }
            warnAboutNonJSObject(obj);
            return obj.toString();
        }
    }

    static String defaultObjectToSource(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Object obj;
        boolean z;
        if (context.iterating == null) {
            context.iterating = new ObjToIntMap(31);
            obj = 1;
            z = false;
        } else {
            z = context.iterating.has(scriptable2);
            obj = null;
        }
        StringBuilder stringBuilder = new StringBuilder(128);
        if (obj != null) {
            stringBuilder.append("(");
        }
        stringBuilder.append('{');
        if (!z) {
            context.iterating.intern(scriptable2);
            Object[] ids = scriptable2.getIds();
            for (int i = 0; i < ids.length; i++) {
                Object obj2 = ids[i];
                if (obj2 instanceof Integer) {
                    int intValue = ((Integer) obj2).intValue();
                    obj2 = scriptable2.get(intValue, scriptable2);
                    if (obj2 != Scriptable.NOT_FOUND) {
                        if (i > 0) {
                            stringBuilder.append(", ");
                        }
                        stringBuilder.append(intValue);
                        stringBuilder.append(':');
                        stringBuilder.append(uneval(context, scriptable, obj2));
                    } else {
                        continue;
                    }
                } else {
                    try {
                        String str = (String) obj2;
                        Object obj3 = scriptable2.get(str, scriptable2);
                        if (obj3 != Scriptable.NOT_FOUND) {
                            if (i > 0) {
                                stringBuilder.append(", ");
                            }
                            if (isValidIdentifierName(str)) {
                                stringBuilder.append(str);
                                obj2 = obj3;
                            } else {
                                stringBuilder.append('\'');
                                stringBuilder.append(escapeString(str, '\''));
                                stringBuilder.append('\'');
                                obj2 = obj3;
                            }
                            stringBuilder.append(':');
                            stringBuilder.append(uneval(context, scriptable, obj2));
                        }
                    } catch (Throwable th) {
                        if (obj != null) {
                            context.iterating = null;
                        }
                    }
                }
            }
        }
        if (obj != null) {
            context.iterating = null;
        }
        stringBuilder.append('}');
        if (obj != null) {
            stringBuilder.append(')');
        }
        return stringBuilder.toString();
    }

    public static Scriptable toObject(Scriptable scriptable, Object obj) {
        if (obj instanceof Scriptable) {
            return (Scriptable) obj;
        }
        return toObject(Context.getContext(), scriptable, obj);
    }

    @Deprecated
    public static Scriptable toObjectOrNull(Context context, Object obj) {
        if (obj instanceof Scriptable) {
            return (Scriptable) obj;
        }
        if (obj == null || obj == Undefined.instance) {
            return null;
        }
        return toObject(context, getTopCallScope(context), obj);
    }

    public static Scriptable toObjectOrNull(Context context, Object obj, Scriptable scriptable) {
        if (obj instanceof Scriptable) {
            return (Scriptable) obj;
        }
        if (obj == null || obj == Undefined.instance) {
            return null;
        }
        return toObject(context, scriptable, obj);
    }

    @Deprecated
    public static Scriptable toObject(Scriptable scriptable, Object obj, Class<?> cls) {
        if (obj instanceof Scriptable) {
            return (Scriptable) obj;
        }
        return toObject(Context.getContext(), scriptable, obj);
    }

    public static Scriptable toObject(Context context, Scriptable scriptable, Object obj) {
        if (obj instanceof Scriptable) {
            return (Scriptable) obj;
        }
        ScriptableObject nativeString;
        if (obj instanceof CharSequence) {
            nativeString = new NativeString((CharSequence) obj);
            setBuiltinProtoAndParent(nativeString, scriptable, Builtins.String);
            return nativeString;
        } else if (obj instanceof Number) {
            nativeString = new NativeNumber(((Number) obj).doubleValue());
            setBuiltinProtoAndParent(nativeString, scriptable, Builtins.Number);
            return nativeString;
        } else if (obj instanceof Boolean) {
            nativeString = new NativeBoolean(((Boolean) obj).booleanValue());
            setBuiltinProtoAndParent(nativeString, scriptable, Builtins.Boolean);
            return nativeString;
        } else if (obj == null) {
            throw typeError0("msg.null.to.object");
        } else if (obj == Undefined.instance) {
            throw typeError0("msg.undef.to.object");
        } else {
            Object wrap = context.getWrapFactory().wrap(context, scriptable, obj, null);
            if (wrap instanceof Scriptable) {
                return (Scriptable) wrap;
            }
            throw errorWithClassName("msg.invalid.type", obj);
        }
    }

    @Deprecated
    public static Scriptable toObject(Context context, Scriptable scriptable, Object obj, Class<?> cls) {
        return toObject(context, scriptable, obj);
    }

    @Deprecated
    public static Object call(Context context, Object obj, Object obj2, Object[] objArr, Scriptable scriptable) {
        if (obj instanceof Function) {
            Function function = (Function) obj;
            Scriptable toObjectOrNull = toObjectOrNull(context, obj2, scriptable);
            if (toObjectOrNull != null) {
                return function.call(context, scriptable, toObjectOrNull, objArr);
            }
            throw undefCallError(toObjectOrNull, "function");
        }
        throw notFunctionError(toString(obj));
    }

    public static Scriptable newObject(Context context, Scriptable scriptable, String str, Object[] objArr) {
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        Function existingCtor = getExistingCtor(context, topLevelScope, str);
        if (objArr == null) {
            objArr = emptyArgs;
        }
        return existingCtor.construct(context, topLevelScope, objArr);
    }

    public static Scriptable newBuiltinObject(Context context, Scriptable scriptable, Builtins builtins, Object[] objArr) {
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        Function builtinCtor = TopLevel.getBuiltinCtor(context, topLevelScope, builtins);
        if (objArr == null) {
            objArr = emptyArgs;
        }
        return builtinCtor.construct(context, topLevelScope, objArr);
    }

    static Scriptable newNativeError(Context context, Scriptable scriptable, NativeErrors nativeErrors, Object[] objArr) {
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        Function nativeErrorCtor = TopLevel.getNativeErrorCtor(context, topLevelScope, nativeErrors);
        if (objArr == null) {
            objArr = emptyArgs;
        }
        return nativeErrorCtor.construct(context, topLevelScope, objArr);
    }

    public static double toInteger(Object obj) {
        return toInteger(toNumber(obj));
    }

    public static double toInteger(double d) {
        if (d != d) {
            return 0.0d;
        }
        if (d == 0.0d || d == Double.POSITIVE_INFINITY || d == Double.NEGATIVE_INFINITY) {
            return d;
        }
        if (d > 0.0d) {
            return Math.floor(d);
        }
        return Math.ceil(d);
    }

    public static double toInteger(Object[] objArr, int i) {
        return i < objArr.length ? toInteger(objArr[i]) : 0.0d;
    }

    public static int toInt32(Object obj) {
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return toInt32(toNumber(obj));
    }

    public static int toInt32(Object[] objArr, int i) {
        return i < objArr.length ? toInt32(objArr[i]) : 0;
    }

    public static int toInt32(double d) {
        return DoubleConversion.doubleToInt32(d);
    }

    public static long toUint32(double d) {
        return ((long) DoubleConversion.doubleToInt32(d)) & 4294967295L;
    }

    public static long toUint32(Object obj) {
        return toUint32(toNumber(obj));
    }

    public static char toUint16(Object obj) {
        return (char) DoubleConversion.doubleToInt32(toNumber(obj));
    }

    public static Object setDefaultNamespace(Object obj, Context context) {
        Scriptable scriptable = context.currentActivationCall;
        if (scriptable == null) {
            scriptable = getTopCallScope(context);
        }
        Object toDefaultXmlNamespace = currentXMLLib(context).toDefaultXmlNamespace(context, obj);
        if (scriptable.has(DEFAULT_NS_TAG, scriptable)) {
            scriptable.put(DEFAULT_NS_TAG, scriptable, toDefaultXmlNamespace);
        } else {
            ScriptableObject.defineProperty(scriptable, DEFAULT_NS_TAG, toDefaultXmlNamespace, 6);
        }
        return Undefined.instance;
    }

    public static Object searchDefaultNamespace(Context context) {
        Object obj;
        Scriptable scriptable = context.currentActivationCall;
        if (scriptable == null) {
            scriptable = getTopCallScope(context);
        }
        while (true) {
            Scriptable parentScope = scriptable.getParentScope();
            if (parentScope == null) {
                break;
            }
            obj = scriptable.get(DEFAULT_NS_TAG, scriptable);
            if (obj != Scriptable.NOT_FOUND) {
                return obj;
            }
            scriptable = parentScope;
        }
        obj = ScriptableObject.getProperty(scriptable, DEFAULT_NS_TAG);
        if (obj == Scriptable.NOT_FOUND) {
            return null;
        }
        return obj;
    }

    public static Object getTopLevelProp(Scriptable scriptable, String str) {
        return ScriptableObject.getProperty(ScriptableObject.getTopLevelScope(scriptable), str);
    }

    static Function getExistingCtor(Context context, Scriptable scriptable, String str) {
        Object property = ScriptableObject.getProperty(scriptable, str);
        if (property instanceof Function) {
            return (Function) property;
        }
        if (property == Scriptable.NOT_FOUND) {
            throw Context.reportRuntimeError1("msg.ctor.not.found", str);
        }
        throw Context.reportRuntimeError1("msg.not.ctor", str);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long indexFromString(java.lang.String r11) {
        /*
        r2 = -1;
        r9 = 9;
        r8 = -214748364; // 0xfffffffff3333334 float:-1.4197688E31 double:NaN;
        r4 = 1;
        r0 = 0;
        r7 = r11.length();
        if (r7 <= 0) goto L_0x006b;
    L_0x000f:
        r1 = r11.charAt(r0);
        r5 = 45;
        if (r1 != r5) goto L_0x006d;
    L_0x0017:
        if (r7 <= r4) goto L_0x006d;
    L_0x0019:
        r1 = r11.charAt(r4);
        r5 = 48;
        if (r1 != r5) goto L_0x0023;
    L_0x0021:
        r0 = r2;
    L_0x0022:
        return r0;
    L_0x0023:
        r6 = r4;
        r5 = r4;
    L_0x0025:
        r4 = r1 + -48;
        if (r4 < 0) goto L_0x006b;
    L_0x0029:
        if (r4 > r9) goto L_0x006b;
    L_0x002b:
        if (r6 == 0) goto L_0x004b;
    L_0x002d:
        r1 = 11;
    L_0x002f:
        if (r7 > r1) goto L_0x006b;
    L_0x0031:
        r1 = -r4;
        r5 = r5 + 1;
        if (r1 == 0) goto L_0x004e;
    L_0x0036:
        if (r5 == r7) goto L_0x004e;
    L_0x0038:
        r4 = r11.charAt(r5);
        r4 = r4 + -48;
        if (r4 < 0) goto L_0x004e;
    L_0x0040:
        if (r4 > r9) goto L_0x004e;
    L_0x0042:
        r0 = r1 * 10;
        r0 = r0 - r4;
        r5 = r5 + 1;
        r10 = r1;
        r1 = r0;
        r0 = r10;
        goto L_0x0036;
    L_0x004b:
        r1 = 10;
        goto L_0x002f;
    L_0x004e:
        r10 = r0;
        r0 = r1;
        r1 = r10;
        if (r5 != r7) goto L_0x006b;
    L_0x0053:
        if (r1 > r8) goto L_0x005d;
    L_0x0055:
        if (r1 != r8) goto L_0x006b;
    L_0x0057:
        if (r6 == 0) goto L_0x0067;
    L_0x0059:
        r1 = 8;
    L_0x005b:
        if (r4 > r1) goto L_0x006b;
    L_0x005d:
        r2 = 4294967295; // 0xffffffff float:NaN double:2.1219957905E-314;
        if (r6 == 0) goto L_0x0069;
    L_0x0064:
        r0 = (long) r0;
        r0 = r0 & r2;
        goto L_0x0022;
    L_0x0067:
        r1 = 7;
        goto L_0x005b;
    L_0x0069:
        r0 = -r0;
        goto L_0x0064;
    L_0x006b:
        r0 = r2;
        goto L_0x0022;
    L_0x006d:
        r6 = r0;
        r5 = r0;
        goto L_0x0025;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.ScriptRuntime.indexFromString(java.lang.String):long");
    }

    public static long testUint32String(String str) {
        long j = 0;
        int length = str.length();
        if (1 > length || length > 10) {
            return -1;
        }
        int charAt = str.charAt(0) - 48;
        if (charAt == 0) {
            if (length != 1) {
                j = -1;
            }
            return j;
        } else if (1 > charAt || charAt > 9) {
            return -1;
        } else {
            long j2 = (long) charAt;
            int i = 1;
            while (i != length) {
                int charAt2 = str.charAt(i) - 48;
                if (charAt2 < 0 || charAt2 > 9) {
                    return -1;
                }
                i++;
                j2 = ((long) charAt2) + (j2 * 10);
            }
            if ((j2 >>> 32) == 0) {
                return j2;
            }
            return -1;
        }
    }

    static Object getIndexObject(String str) {
        long indexFromString = indexFromString(str);
        if (indexFromString >= 0) {
            return Integer.valueOf((int) indexFromString);
        }
        return str;
    }

    static Object getIndexObject(double d) {
        int i = (int) d;
        if (((double) i) == d) {
            return Integer.valueOf(i);
        }
        return toString(d);
    }

    static String toStringIdOrIndex(Context context, Object obj) {
        if (obj instanceof Number) {
            double doubleValue = ((Number) obj).doubleValue();
            int i = (int) doubleValue;
            if (((double) i) != doubleValue) {
                return toString(obj);
            }
            storeIndexResult(context, i);
            return null;
        }
        if (obj instanceof String) {
            obj = (String) obj;
        } else {
            obj = toString(obj);
        }
        long indexFromString = indexFromString(obj);
        if (indexFromString < 0) {
            return obj;
        }
        storeIndexResult(context, (int) indexFromString);
        return null;
    }

    @Deprecated
    public static Object getObjectElem(Object obj, Object obj2, Context context) {
        return getObjectElem(obj, obj2, context, getTopCallScope(context));
    }

    public static Object getObjectElem(Object obj, Object obj2, Context context, Scriptable scriptable) {
        Scriptable toObjectOrNull = toObjectOrNull(context, obj, scriptable);
        if (toObjectOrNull != null) {
            return getObjectElem(toObjectOrNull, obj2, context);
        }
        throw undefReadError(obj, obj2);
    }

    public static Object getObjectElem(Scriptable scriptable, Object obj, Context context) {
        Object obj2;
        if (scriptable instanceof XMLObject) {
            obj2 = ((XMLObject) scriptable).get(context, obj);
        } else {
            String toStringIdOrIndex = toStringIdOrIndex(context, obj);
            if (toStringIdOrIndex == null) {
                obj2 = ScriptableObject.getProperty(scriptable, lastIndexResult(context));
            } else {
                obj2 = ScriptableObject.getProperty(scriptable, toStringIdOrIndex);
            }
        }
        if (obj2 == Scriptable.NOT_FOUND) {
            return Undefined.instance;
        }
        return obj2;
    }

    @Deprecated
    public static Object getObjectProp(Object obj, String str, Context context) {
        return getObjectProp(obj, str, context, getTopCallScope(context));
    }

    public static Object getObjectProp(Object obj, String str, Context context, Scriptable scriptable) {
        Scriptable toObjectOrNull = toObjectOrNull(context, obj, scriptable);
        if (toObjectOrNull != null) {
            return getObjectProp(toObjectOrNull, str, context);
        }
        throw undefReadError(obj, str);
    }

    public static Object getObjectProp(Scriptable scriptable, String str, Context context) {
        Object property = ScriptableObject.getProperty(scriptable, str);
        if (property != Scriptable.NOT_FOUND) {
            return property;
        }
        if (context.hasFeature(11)) {
            Context.reportWarning(getMessage1("msg.ref.undefined.prop", str));
        }
        return Undefined.instance;
    }

    @Deprecated
    public static Object getObjectPropNoWarn(Object obj, String str, Context context) {
        return getObjectPropNoWarn(obj, str, context, getTopCallScope(context));
    }

    public static Object getObjectPropNoWarn(Object obj, String str, Context context, Scriptable scriptable) {
        Scriptable toObjectOrNull = toObjectOrNull(context, obj, scriptable);
        if (toObjectOrNull == null) {
            throw undefReadError(obj, str);
        }
        Object property = ScriptableObject.getProperty(toObjectOrNull, str);
        if (property == Scriptable.NOT_FOUND) {
            return Undefined.instance;
        }
        return property;
    }

    @Deprecated
    public static Object getObjectIndex(Object obj, double d, Context context) {
        return getObjectIndex(obj, d, context, getTopCallScope(context));
    }

    public static Object getObjectIndex(Object obj, double d, Context context, Scriptable scriptable) {
        Scriptable toObjectOrNull = toObjectOrNull(context, obj, scriptable);
        if (toObjectOrNull == null) {
            throw undefReadError(obj, toString(d));
        }
        int i = (int) d;
        if (((double) i) == d) {
            return getObjectIndex(toObjectOrNull, i, context);
        }
        return getObjectProp(toObjectOrNull, toString(d), context);
    }

    public static Object getObjectIndex(Scriptable scriptable, int i, Context context) {
        Object property = ScriptableObject.getProperty(scriptable, i);
        if (property == Scriptable.NOT_FOUND) {
            return Undefined.instance;
        }
        return property;
    }

    @Deprecated
    public static Object setObjectElem(Object obj, Object obj2, Object obj3, Context context) {
        return setObjectElem(obj, obj2, obj3, context, getTopCallScope(context));
    }

    public static Object setObjectElem(Object obj, Object obj2, Object obj3, Context context, Scriptable scriptable) {
        Scriptable toObjectOrNull = toObjectOrNull(context, obj, scriptable);
        if (toObjectOrNull != null) {
            return setObjectElem(toObjectOrNull, obj2, obj3, context);
        }
        throw undefWriteError(obj, obj2, obj3);
    }

    public static Object setObjectElem(Scriptable scriptable, Object obj, Object obj2, Context context) {
        if (scriptable instanceof XMLObject) {
            ((XMLObject) scriptable).put(context, obj, obj2);
        } else {
            String toStringIdOrIndex = toStringIdOrIndex(context, obj);
            if (toStringIdOrIndex == null) {
                ScriptableObject.putProperty(scriptable, lastIndexResult(context), obj2);
            } else {
                ScriptableObject.putProperty(scriptable, toStringIdOrIndex, obj2);
            }
        }
        return obj2;
    }

    @Deprecated
    public static Object setObjectProp(Object obj, String str, Object obj2, Context context) {
        return setObjectProp(obj, str, obj2, context, getTopCallScope(context));
    }

    public static Object setObjectProp(Object obj, String str, Object obj2, Context context, Scriptable scriptable) {
        Scriptable toObjectOrNull = toObjectOrNull(context, obj, scriptable);
        if (toObjectOrNull != null) {
            return setObjectProp(toObjectOrNull, str, obj2, context);
        }
        throw undefWriteError(obj, str, obj2);
    }

    public static Object setObjectProp(Scriptable scriptable, String str, Object obj, Context context) {
        ScriptableObject.putProperty(scriptable, str, obj);
        return obj;
    }

    @Deprecated
    public static Object setObjectIndex(Object obj, double d, Object obj2, Context context) {
        return setObjectIndex(obj, d, obj2, context, getTopCallScope(context));
    }

    public static Object setObjectIndex(Object obj, double d, Object obj2, Context context, Scriptable scriptable) {
        Scriptable toObjectOrNull = toObjectOrNull(context, obj, scriptable);
        if (toObjectOrNull == null) {
            throw undefWriteError(obj, String.valueOf(d), obj2);
        }
        int i = (int) d;
        if (((double) i) == d) {
            return setObjectIndex(toObjectOrNull, i, obj2, context);
        }
        return setObjectProp(toObjectOrNull, toString(d), obj2, context);
    }

    public static Object setObjectIndex(Scriptable scriptable, int i, Object obj, Context context) {
        ScriptableObject.putProperty(scriptable, i, obj);
        return obj;
    }

    public static boolean deleteObjectElem(Scriptable scriptable, Object obj, Context context) {
        String toStringIdOrIndex = toStringIdOrIndex(context, obj);
        if (toStringIdOrIndex == null) {
            int lastIndexResult = lastIndexResult(context);
            scriptable.delete(lastIndexResult);
            if (scriptable.has(lastIndexResult, scriptable)) {
                return false;
            }
            return true;
        }
        scriptable.delete(toStringIdOrIndex);
        if (scriptable.has(toStringIdOrIndex, scriptable)) {
            return false;
        }
        return true;
    }

    public static boolean hasObjectElem(Scriptable scriptable, Object obj, Context context) {
        String toStringIdOrIndex = toStringIdOrIndex(context, obj);
        if (toStringIdOrIndex == null) {
            return ScriptableObject.hasProperty(scriptable, lastIndexResult(context));
        }
        return ScriptableObject.hasProperty(scriptable, toStringIdOrIndex);
    }

    public static Object refGet(Ref ref, Context context) {
        return ref.get(context);
    }

    @Deprecated
    public static Object refSet(Ref ref, Object obj, Context context) {
        return refSet(ref, obj, context, getTopCallScope(context));
    }

    public static Object refSet(Ref ref, Object obj, Context context, Scriptable scriptable) {
        return ref.set(context, scriptable, obj);
    }

    public static Object refDel(Ref ref, Context context) {
        return wrapBoolean(ref.delete(context));
    }

    static boolean isSpecialProperty(String str) {
        return str.equals("__proto__") || str.equals("__parent__");
    }

    @Deprecated
    public static Ref specialRef(Object obj, String str, Context context) {
        return specialRef(obj, str, context, getTopCallScope(context));
    }

    public static Ref specialRef(Object obj, String str, Context context, Scriptable scriptable) {
        return SpecialRef.createSpecial(context, scriptable, obj, str);
    }

    @Deprecated
    public static Object delete(Object obj, Object obj2, Context context) {
        return delete(obj, obj2, context, false);
    }

    @Deprecated
    public static Object delete(Object obj, Object obj2, Context context, boolean z) {
        return delete(obj, obj2, context, getTopCallScope(context), z);
    }

    public static Object delete(Object obj, Object obj2, Context context, Scriptable scriptable, boolean z) {
        Scriptable toObjectOrNull = toObjectOrNull(context, obj, scriptable);
        if (toObjectOrNull != null) {
            return wrapBoolean(deleteObjectElem(toObjectOrNull, obj2, context));
        }
        if (z) {
            return Boolean.TRUE;
        }
        throw undefDeleteError(obj, obj2);
    }

    public static Object name(Context context, Scriptable scriptable, String str) {
        Scriptable parentScope = scriptable.getParentScope();
        if (parentScope != null) {
            return nameOrFunction(context, scriptable, parentScope, str, false);
        }
        Object topScopeName = topScopeName(context, scriptable, str);
        if (topScopeName != Scriptable.NOT_FOUND) {
            return topScopeName;
        }
        throw notFoundError(scriptable, str);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object nameOrFunction(org.mozilla.javascript.Context r5, org.mozilla.javascript.Scriptable r6, org.mozilla.javascript.Scriptable r7, java.lang.String r8, boolean r9) {
        /*
        r1 = 0;
        r0 = r6;
    L_0x0002:
        r2 = r0 instanceof org.mozilla.javascript.NativeWith;
        if (r2 == 0) goto L_0x0049;
    L_0x0006:
        r0 = r0.getPrototype();
        r2 = r0 instanceof org.mozilla.javascript.xml.XMLObject;
        if (r2 == 0) goto L_0x003f;
    L_0x000e:
        r0 = (org.mozilla.javascript.xml.XMLObject) r0;
        r2 = r0.has(r8, r0);
        if (r2 == 0) goto L_0x0025;
    L_0x0016:
        r1 = r0.get(r8, r0);
    L_0x001a:
        if (r9 == 0) goto L_0x0071;
    L_0x001c:
        r2 = r1 instanceof org.mozilla.javascript.Callable;
        if (r2 != 0) goto L_0x006e;
    L_0x0020:
        r0 = notFunctionError(r1, r8);
        throw r0;
    L_0x0025:
        if (r1 != 0) goto L_0x0075;
    L_0x0027:
        r1 = r0;
    L_0x0028:
        r0 = r7.getParentScope();
        if (r0 != 0) goto L_0x0077;
    L_0x002e:
        r0 = topScopeName(r5, r7, r8);
        r2 = org.mozilla.javascript.Scriptable.NOT_FOUND;
        if (r0 != r2) goto L_0x006b;
    L_0x0036:
        if (r1 == 0) goto L_0x003a;
    L_0x0038:
        if (r9 == 0) goto L_0x0067;
    L_0x003a:
        r0 = notFoundError(r7, r8);
        throw r0;
    L_0x003f:
        r2 = org.mozilla.javascript.ScriptableObject.getProperty(r0, r8);
        r3 = org.mozilla.javascript.Scriptable.NOT_FOUND;
        if (r2 == r3) goto L_0x0075;
    L_0x0047:
        r1 = r2;
        goto L_0x001a;
    L_0x0049:
        r2 = r0 instanceof org.mozilla.javascript.NativeCall;
        if (r2 == 0) goto L_0x005d;
    L_0x004d:
        r2 = r0.get(r8, r0);
        r0 = org.mozilla.javascript.Scriptable.NOT_FOUND;
        if (r2 == r0) goto L_0x0028;
    L_0x0055:
        if (r9 == 0) goto L_0x0072;
    L_0x0057:
        r0 = org.mozilla.javascript.ScriptableObject.getTopLevelScope(r7);
        r1 = r2;
        goto L_0x001a;
    L_0x005d:
        r2 = org.mozilla.javascript.ScriptableObject.getProperty(r0, r8);
        r3 = org.mozilla.javascript.Scriptable.NOT_FOUND;
        if (r2 == r3) goto L_0x0028;
    L_0x0065:
        r1 = r2;
        goto L_0x001a;
    L_0x0067:
        r0 = r1.get(r8, r1);
    L_0x006b:
        r1 = r0;
        r0 = r7;
        goto L_0x001a;
    L_0x006e:
        storeScriptable(r5, r0);
    L_0x0071:
        return r1;
    L_0x0072:
        r0 = r6;
        r1 = r2;
        goto L_0x001a;
    L_0x0075:
        r0 = r1;
        goto L_0x0027;
    L_0x0077:
        r4 = r0;
        r0 = r7;
        r7 = r4;
        goto L_0x0002;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.ScriptRuntime.nameOrFunction(org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable, org.mozilla.javascript.Scriptable, java.lang.String, boolean):java.lang.Object");
    }

    private static Object topScopeName(Context context, Scriptable scriptable, String str) {
        if (context.useDynamicScope) {
            scriptable = checkDynamicScope(context.topCallScope, scriptable);
        }
        return ScriptableObject.getProperty(scriptable, str);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.mozilla.javascript.Scriptable bind(org.mozilla.javascript.Context r4, org.mozilla.javascript.Scriptable r5, java.lang.String r6) {
        /*
        r1 = 0;
        r0 = r5.getParentScope();
        if (r0 == 0) goto L_0x0057;
    L_0x0007:
        r2 = r0;
    L_0x0008:
        r0 = r5 instanceof org.mozilla.javascript.NativeWith;
        if (r0 == 0) goto L_0x0046;
    L_0x000c:
        r0 = r5.getPrototype();
        r3 = r0 instanceof org.mozilla.javascript.xml.XMLObject;
        if (r3 == 0) goto L_0x0038;
    L_0x0014:
        r0 = (org.mozilla.javascript.xml.XMLObject) r0;
        r3 = r0.has(r4, r6);
        if (r3 == 0) goto L_0x001d;
    L_0x001c:
        return r0;
    L_0x001d:
        if (r1 != 0) goto L_0x003e;
    L_0x001f:
        r1 = r2.getParentScope();
        if (r1 != 0) goto L_0x0040;
    L_0x0025:
        r1 = r0;
    L_0x0026:
        r0 = r4.useDynamicScope;
        if (r0 == 0) goto L_0x0030;
    L_0x002a:
        r0 = r4.topCallScope;
        r2 = checkDynamicScope(r0, r2);
    L_0x0030:
        r0 = org.mozilla.javascript.ScriptableObject.hasProperty(r2, r6);
        if (r0 == 0) goto L_0x0055;
    L_0x0036:
        r0 = r2;
        goto L_0x001c;
    L_0x0038:
        r3 = org.mozilla.javascript.ScriptableObject.hasProperty(r0, r6);
        if (r3 != 0) goto L_0x001c;
    L_0x003e:
        r0 = r1;
        goto L_0x001f;
    L_0x0040:
        r5 = r2;
        r2 = r1;
        r1 = r0;
        goto L_0x0008;
    L_0x0044:
        r5 = r2;
        r2 = r0;
    L_0x0046:
        r0 = org.mozilla.javascript.ScriptableObject.hasProperty(r5, r6);
        if (r0 == 0) goto L_0x004e;
    L_0x004c:
        r0 = r5;
        goto L_0x001c;
    L_0x004e:
        r0 = r2.getParentScope();
        if (r0 != 0) goto L_0x0044;
    L_0x0054:
        goto L_0x0026;
    L_0x0055:
        r0 = r1;
        goto L_0x001c;
    L_0x0057:
        r2 = r5;
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.ScriptRuntime.bind(org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable, java.lang.String):org.mozilla.javascript.Scriptable");
    }

    public static Object setName(Scriptable scriptable, Object obj, Context context, Scriptable scriptable2, String str) {
        if (scriptable != null) {
            ScriptableObject.putProperty(scriptable, str, obj);
        } else {
            if (context.hasFeature(11) || context.hasFeature(8)) {
                Context.reportWarning(getMessage1("msg.assn.create.strict", str));
            }
            Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable2);
            if (context.useDynamicScope) {
                topLevelScope = checkDynamicScope(context.topCallScope, topLevelScope);
            }
            topLevelScope.put(str, topLevelScope, obj);
        }
        return obj;
    }

    public static Object strictSetName(Scriptable scriptable, Object obj, Context context, Scriptable scriptable2, String str) {
        if (scriptable != null) {
            ScriptableObject.putProperty(scriptable, str, obj);
            return obj;
        }
        throw constructError("ReferenceError", "Assignment to undefined \"" + str + "\" in strict mode");
    }

    public static Object setConst(Scriptable scriptable, Object obj, Context context, String str) {
        if (scriptable instanceof XMLObject) {
            scriptable.put(str, scriptable, obj);
        } else {
            ScriptableObject.putConstProperty(scriptable, str, obj);
        }
        return obj;
    }

    public static Scriptable toIterator(Context context, Scriptable scriptable, Scriptable scriptable2, boolean z) {
        if (!ScriptableObject.hasProperty(scriptable2, NativeIterator.ITERATOR_PROPERTY_NAME)) {
            return null;
        }
        Object property = ScriptableObject.getProperty(scriptable2, NativeIterator.ITERATOR_PROPERTY_NAME);
        if (property instanceof Callable) {
            Callable callable = (Callable) property;
            Object[] objArr = new Object[1];
            objArr[0] = z ? Boolean.TRUE : Boolean.FALSE;
            property = callable.call(context, scriptable, scriptable2, objArr);
            if (property instanceof Scriptable) {
                return (Scriptable) property;
            }
            throw typeError0("msg.iterator.primitive");
        }
        throw typeError0("msg.invalid.iterator");
    }

    @Deprecated
    public static Object enumInit(Object obj, Context context, boolean z) {
        return enumInit(obj, context, z ? 1 : 0);
    }

    @Deprecated
    public static Object enumInit(Object obj, Context context, int i) {
        return enumInit(obj, context, getTopCallScope(context), i);
    }

    public static Object enumInit(Object obj, Context context, Scriptable scriptable, int i) {
        ScriptRuntime$IdEnumeration scriptRuntime$IdEnumeration = new ScriptRuntime$IdEnumeration(null);
        scriptRuntime$IdEnumeration.obj = toObjectOrNull(context, obj, scriptable);
        if (scriptRuntime$IdEnumeration.obj == null) {
            return scriptRuntime$IdEnumeration;
        }
        scriptRuntime$IdEnumeration.enumType = i;
        scriptRuntime$IdEnumeration.iterator = null;
        if (!(i == 3 || i == 4 || i == 5)) {
            scriptRuntime$IdEnumeration.iterator = toIterator(context, scriptRuntime$IdEnumeration.obj.getParentScope(), scriptRuntime$IdEnumeration.obj, i == 0);
        }
        if (scriptRuntime$IdEnumeration.iterator == null) {
            enumChangeObject(scriptRuntime$IdEnumeration);
        }
        return scriptRuntime$IdEnumeration;
    }

    public static void setEnumNumbers(Object obj, boolean z) {
        ((ScriptRuntime$IdEnumeration) obj).enumNumbers = z;
    }

    public static Boolean enumNext(Object obj) {
        ScriptRuntime$IdEnumeration scriptRuntime$IdEnumeration = (ScriptRuntime$IdEnumeration) obj;
        Object property;
        if (scriptRuntime$IdEnumeration.iterator != null) {
            property = ScriptableObject.getProperty(scriptRuntime$IdEnumeration.iterator, "next");
            if (!(property instanceof Callable)) {
                return Boolean.FALSE;
            }
            try {
                scriptRuntime$IdEnumeration.currentId = ((Callable) property).call(Context.getContext(), scriptRuntime$IdEnumeration.iterator.getParentScope(), scriptRuntime$IdEnumeration.iterator, emptyArgs);
                return Boolean.TRUE;
            } catch (JavaScriptException e) {
                if (e.getValue() instanceof StopIteration) {
                    return Boolean.FALSE;
                }
                throw e;
            }
        }
        while (scriptRuntime$IdEnumeration.obj != null) {
            if (scriptRuntime$IdEnumeration.index == scriptRuntime$IdEnumeration.ids.length) {
                scriptRuntime$IdEnumeration.obj = scriptRuntime$IdEnumeration.obj.getPrototype();
                enumChangeObject(scriptRuntime$IdEnumeration);
            } else {
                Object[] objArr = scriptRuntime$IdEnumeration.ids;
                int i = scriptRuntime$IdEnumeration.index;
                scriptRuntime$IdEnumeration.index = i + 1;
                property = objArr[i];
                if (scriptRuntime$IdEnumeration.used == null || !scriptRuntime$IdEnumeration.used.has(property)) {
                    if (property instanceof String) {
                        String str = (String) property;
                        if (scriptRuntime$IdEnumeration.obj.has(str, scriptRuntime$IdEnumeration.obj)) {
                            scriptRuntime$IdEnumeration.currentId = str;
                        }
                    } else {
                        int intValue = ((Number) property).intValue();
                        if (scriptRuntime$IdEnumeration.obj.has(intValue, scriptRuntime$IdEnumeration.obj)) {
                            scriptRuntime$IdEnumeration.currentId = scriptRuntime$IdEnumeration.enumNumbers ? Integer.valueOf(intValue) : String.valueOf(intValue);
                        }
                    }
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    public static Object enumId(Object obj, Context context) {
        ScriptRuntime$IdEnumeration scriptRuntime$IdEnumeration = (ScriptRuntime$IdEnumeration) obj;
        if (scriptRuntime$IdEnumeration.iterator != null) {
            return scriptRuntime$IdEnumeration.currentId;
        }
        switch (scriptRuntime$IdEnumeration.enumType) {
            case 0:
            case 3:
                return scriptRuntime$IdEnumeration.currentId;
            case 1:
            case 4:
                return enumValue(obj, context);
            case 2:
            case 5:
                return context.newArray(ScriptableObject.getTopLevelScope(scriptRuntime$IdEnumeration.obj), new Object[]{scriptRuntime$IdEnumeration.currentId, enumValue(obj, context)});
            default:
                throw Kit.codeBug();
        }
    }

    public static Object enumValue(Object obj, Context context) {
        ScriptRuntime$IdEnumeration scriptRuntime$IdEnumeration = (ScriptRuntime$IdEnumeration) obj;
        String toStringIdOrIndex = toStringIdOrIndex(context, scriptRuntime$IdEnumeration.currentId);
        if (toStringIdOrIndex != null) {
            return scriptRuntime$IdEnumeration.obj.get(toStringIdOrIndex, scriptRuntime$IdEnumeration.obj);
        }
        return scriptRuntime$IdEnumeration.obj.get(lastIndexResult(context), scriptRuntime$IdEnumeration.obj);
    }

    private static void enumChangeObject(ScriptRuntime$IdEnumeration scriptRuntime$IdEnumeration) {
        Object[] objArr = null;
        while (scriptRuntime$IdEnumeration.obj != null) {
            objArr = scriptRuntime$IdEnumeration.obj.getIds();
            if (objArr.length != 0) {
                break;
            }
            scriptRuntime$IdEnumeration.obj = scriptRuntime$IdEnumeration.obj.getPrototype();
        }
        if (!(scriptRuntime$IdEnumeration.obj == null || scriptRuntime$IdEnumeration.ids == null)) {
            Object[] objArr2 = scriptRuntime$IdEnumeration.ids;
            int length = objArr2.length;
            if (scriptRuntime$IdEnumeration.used == null) {
                scriptRuntime$IdEnumeration.used = new ObjToIntMap(length);
            }
            for (int i = 0; i != length; i++) {
                scriptRuntime$IdEnumeration.used.intern(objArr2[i]);
            }
        }
        scriptRuntime$IdEnumeration.ids = objArr;
        scriptRuntime$IdEnumeration.index = 0;
    }

    public static Callable getNameFunctionAndThis(String str, Context context, Scriptable scriptable) {
        Scriptable parentScope = scriptable.getParentScope();
        if (parentScope != null) {
            return (Callable) nameOrFunction(context, scriptable, parentScope, str, true);
        }
        Object topScopeName = topScopeName(context, scriptable, str);
        if (topScopeName instanceof Callable) {
            storeScriptable(context, scriptable);
            return (Callable) topScopeName;
        } else if (topScopeName == Scriptable.NOT_FOUND) {
            throw notFoundError(scriptable, str);
        } else {
            throw notFunctionError(topScopeName, str);
        }
    }

    @Deprecated
    public static Callable getElemFunctionAndThis(Object obj, Object obj2, Context context) {
        return getElemFunctionAndThis(obj, obj2, context, getTopCallScope(context));
    }

    public static Callable getElemFunctionAndThis(Object obj, Object obj2, Context context, Scriptable scriptable) {
        String toStringIdOrIndex = toStringIdOrIndex(context, obj2);
        if (toStringIdOrIndex != null) {
            return getPropFunctionAndThis(obj, toStringIdOrIndex, context, scriptable);
        }
        int lastIndexResult = lastIndexResult(context);
        Scriptable toObjectOrNull = toObjectOrNull(context, obj, scriptable);
        if (toObjectOrNull == null) {
            throw undefCallError(obj, String.valueOf(lastIndexResult));
        }
        Object property = ScriptableObject.getProperty(toObjectOrNull, lastIndexResult);
        if (property instanceof Callable) {
            storeScriptable(context, toObjectOrNull);
            return (Callable) property;
        }
        throw notFunctionError(property, obj2);
    }

    @Deprecated
    public static Callable getPropFunctionAndThis(Object obj, String str, Context context) {
        return getPropFunctionAndThis(obj, str, context, getTopCallScope(context));
    }

    public static Callable getPropFunctionAndThis(Object obj, String str, Context context, Scriptable scriptable) {
        return getPropFunctionAndThisHelper(obj, str, context, toObjectOrNull(context, obj, scriptable));
    }

    private static Callable getPropFunctionAndThisHelper(Object obj, String str, Context context, Scriptable scriptable) {
        if (scriptable == null) {
            throw undefCallError(obj, str);
        }
        Object property;
        Object property2 = ScriptableObject.getProperty(scriptable, str);
        if (!(property2 instanceof Callable)) {
            property = ScriptableObject.getProperty(scriptable, "__noSuchMethod__");
            if (property instanceof Callable) {
                property = new ScriptRuntime$NoSuchMethodShim((Callable) property, str);
                if (property instanceof Callable) {
                    throw notFunctionError(scriptable, property, str);
                }
                storeScriptable(context, scriptable);
                return (Callable) property;
            }
        }
        property = property2;
        if (property instanceof Callable) {
            storeScriptable(context, scriptable);
            return (Callable) property;
        }
        throw notFunctionError(scriptable, property, str);
    }

    public static Callable getValueFunctionAndThis(Object obj, Context context) {
        if (obj instanceof Callable) {
            Callable callable = (Callable) obj;
            Scriptable scriptable = null;
            if (callable instanceof Scriptable) {
                scriptable = ((Scriptable) callable).getParentScope();
            }
            if (scriptable == null) {
                if (context.topCallScope == null) {
                    throw new IllegalStateException();
                }
                scriptable = context.topCallScope;
            }
            if (!(scriptable.getParentScope() == null || (scriptable instanceof NativeWith) || !(scriptable instanceof NativeCall))) {
                scriptable = ScriptableObject.getTopLevelScope(scriptable);
            }
            storeScriptable(context, scriptable);
            return callable;
        }
        throw notFunctionError(obj);
    }

    public static Ref callRef(Callable callable, Scriptable scriptable, Object[] objArr, Context context) {
        if (callable instanceof RefCallable) {
            RefCallable refCallable = (RefCallable) callable;
            Ref refCall = refCallable.refCall(context, scriptable, objArr);
            if (refCall != null) {
                return refCall;
            }
            throw new IllegalStateException(refCallable.getClass().getName() + ".refCall() returned null");
        }
        throw constructError("ReferenceError", getMessage1("msg.no.ref.from.function", toString((Object) callable)));
    }

    public static Scriptable newObject(Object obj, Context context, Scriptable scriptable, Object[] objArr) {
        if (obj instanceof Function) {
            return ((Function) obj).construct(context, scriptable, objArr);
        }
        throw notFunctionError(obj);
    }

    public static Object callSpecial(Context context, Callable callable, Scriptable scriptable, Object[] objArr, Scriptable scriptable2, Scriptable scriptable3, int i, String str, int i2) {
        if (i == 1) {
            if (scriptable.getParentScope() == null && NativeGlobal.isEvalFunction(callable)) {
                return evalSpecial(context, scriptable2, scriptable3, objArr, str, i2);
            }
        } else if (i != 2) {
            throw Kit.codeBug();
        } else if (NativeWith.isWithFunction(callable)) {
            throw Context.reportRuntimeError1("msg.only.from.new", "With");
        }
        return callable.call(context, scriptable2, scriptable, objArr);
    }

    public static Object newSpecial(Context context, Object obj, Object[] objArr, Scriptable scriptable, int i) {
        if (i == 1) {
            if (NativeGlobal.isEvalFunction(obj)) {
                throw typeError1("msg.not.ctor", "eval");
            }
        } else if (i != 2) {
            throw Kit.codeBug();
        } else if (NativeWith.isWithFunction(obj)) {
            return NativeWith.newWithSpecial(context, scriptable, objArr);
        }
        return newObject(obj, context, scriptable, objArr);
    }

    public static Object applyOrCall(boolean z, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Scriptable topCallScope;
        Object[] objArr2;
        int length = objArr.length;
        Callable callable = getCallable(scriptable2);
        Scriptable scriptable3 = null;
        if (length != 0) {
            scriptable3 = toObjectOrNull(context, objArr[0], scriptable);
        }
        if (scriptable3 == null) {
            topCallScope = getTopCallScope(context);
        } else {
            topCallScope = scriptable3;
        }
        if (z) {
            if (length <= 1) {
                objArr2 = emptyArgs;
            } else {
                objArr2 = getApplyArguments(context, objArr[1]);
            }
        } else if (length <= 1) {
            objArr2 = emptyArgs;
        } else {
            objArr2 = new Object[(length - 1)];
            System.arraycopy(objArr, 1, objArr2, 0, length - 1);
        }
        return callable.call(context, scriptable, topCallScope, objArr2);
    }

    static Object[] getApplyArguments(Context context, Object obj) {
        if (obj == null || obj == Undefined.instance) {
            return emptyArgs;
        }
        if ((obj instanceof NativeArray) || (obj instanceof Arguments)) {
            return context.getElements((Scriptable) obj);
        }
        throw typeError0("msg.arg.isnt.array");
    }

    static Callable getCallable(Scriptable scriptable) {
        if (scriptable instanceof Callable) {
            return (Callable) scriptable;
        }
        Object defaultValue = scriptable.getDefaultValue(FunctionClass);
        if (defaultValue instanceof Callable) {
            return (Callable) defaultValue;
        }
        throw notFunctionError(defaultValue, scriptable);
    }

    public static Object evalSpecial(Context context, Scriptable scriptable, Object obj, Object[] objArr, String str, int i) {
        if (objArr.length < 1) {
            return Undefined.instance;
        }
        Object obj2 = objArr[0];
        if (obj2 instanceof CharSequence) {
            if (str == null) {
                int[] iArr = new int[1];
                str = Context.getSourcePositionFromStack(iArr);
                if (str != null) {
                    i = iArr[0];
                } else {
                    str = "";
                }
            }
            String makeUrlForGeneratedScript = makeUrlForGeneratedScript(true, str, i);
            ErrorReporter forEval = DefaultErrorReporter.forEval(context.getErrorReporter());
            Evaluator createInterpreter = Context.createInterpreter();
            if (createInterpreter == null) {
                throw new JavaScriptException("Interpreter not present", str, i);
            }
            Script compileString = context.compileString(obj2.toString(), createInterpreter, forEval, makeUrlForGeneratedScript, 1, null);
            createInterpreter.setEvalScriptFlag(compileString);
            return ((Callable) compileString).call(context, scriptable, (Scriptable) obj, emptyArgs);
        } else if (context.hasFeature(11) || context.hasFeature(9)) {
            throw Context.reportRuntimeError0("msg.eval.nonstring.strict");
        } else {
            Context.reportWarning(getMessage0("msg.eval.nonstring"));
            return obj2;
        }
    }

    public static String typeof(Object obj) {
        if (obj == null) {
            return "object";
        }
        if (obj == Undefined.instance) {
            return "undefined";
        }
        if (obj instanceof ScriptableObject) {
            return ((ScriptableObject) obj).getTypeOf();
        }
        if (obj instanceof Scriptable) {
            return obj instanceof Callable ? "function" : "object";
        } else {
            if (obj instanceof CharSequence) {
                return "string";
            }
            if (obj instanceof Number) {
                return "number";
            }
            if (obj instanceof Boolean) {
                return "boolean";
            }
            throw errorWithClassName("msg.invalid.type", obj);
        }
    }

    public static String typeofName(Scriptable scriptable, String str) {
        Context context = Context.getContext();
        Scriptable bind = bind(context, scriptable, str);
        if (bind == null) {
            return "undefined";
        }
        return typeof(getObjectProp(bind, str, context));
    }

    public static Object add(Object obj, Object obj2, Context context) {
        if ((obj instanceof Number) && (obj2 instanceof Number)) {
            return wrapNumber(((Number) obj).doubleValue() + ((Number) obj2).doubleValue());
        }
        Object addValues;
        Object defaultValue;
        if (obj instanceof XMLObject) {
            addValues = ((XMLObject) obj).addValues(context, true, obj2);
            if (addValues != Scriptable.NOT_FOUND) {
                return addValues;
            }
        }
        if (obj2 instanceof XMLObject) {
            addValues = ((XMLObject) obj2).addValues(context, false, obj);
            if (addValues != Scriptable.NOT_FOUND) {
                return addValues;
            }
        }
        if (obj instanceof Scriptable) {
            addValues = ((Scriptable) obj).getDefaultValue(null);
        } else {
            addValues = obj;
        }
        if (obj2 instanceof Scriptable) {
            defaultValue = ((Scriptable) obj2).getDefaultValue(null);
        } else {
            defaultValue = obj2;
        }
        if ((addValues instanceof CharSequence) || (defaultValue instanceof CharSequence)) {
            return new ConsString(toCharSequence(addValues), toCharSequence(defaultValue));
        }
        if ((addValues instanceof Number) && (defaultValue instanceof Number)) {
            return wrapNumber(((Number) defaultValue).doubleValue() + ((Number) addValues).doubleValue());
        }
        return wrapNumber(toNumber(defaultValue) + toNumber(addValues));
    }

    public static CharSequence add(CharSequence charSequence, Object obj) {
        return new ConsString(charSequence, toCharSequence(obj));
    }

    public static CharSequence add(Object obj, CharSequence charSequence) {
        return new ConsString(toCharSequence(obj), charSequence);
    }

    @Deprecated
    public static Object nameIncrDecr(Scriptable scriptable, String str, int i) {
        return nameIncrDecr(scriptable, str, Context.getContext(), i);
    }

    public static Object nameIncrDecr(Scriptable scriptable, String str, Context context, int i) {
        do {
            if (context.useDynamicScope && scriptable.getParentScope() == null) {
                scriptable = checkDynamicScope(context.topCallScope, scriptable);
            }
            Scriptable scriptable2 = scriptable;
            do {
                if ((scriptable2 instanceof NativeWith) && (scriptable2.getPrototype() instanceof XMLObject)) {
                    break;
                }
                Object obj = scriptable2.get(str, scriptable);
                if (obj != Scriptable.NOT_FOUND) {
                    return doScriptableIncrDecr(scriptable2, str, scriptable, obj, i);
                }
                scriptable2 = scriptable2.getPrototype();
            } while (scriptable2 != null);
            scriptable = scriptable.getParentScope();
        } while (scriptable != null);
        throw notFoundError(scriptable, str);
    }

    @Deprecated
    public static Object propIncrDecr(Object obj, String str, Context context, int i) {
        return propIncrDecr(obj, str, context, getTopCallScope(context), i);
    }

    public static Object propIncrDecr(Object obj, String str, Context context, Scriptable scriptable, int i) {
        Scriptable toObjectOrNull = toObjectOrNull(context, obj, scriptable);
        if (toObjectOrNull == null) {
            throw undefReadError(obj, str);
        }
        Scriptable scriptable2 = toObjectOrNull;
        do {
            Object obj2 = scriptable2.get(str, toObjectOrNull);
            if (obj2 != Scriptable.NOT_FOUND) {
                return doScriptableIncrDecr(scriptable2, str, toObjectOrNull, obj2, i);
            }
            scriptable2 = scriptable2.getPrototype();
        } while (scriptable2 != null);
        toObjectOrNull.put(str, toObjectOrNull, NaNobj);
        return NaNobj;
    }

    private static Object doScriptableIncrDecr(Scriptable scriptable, String str, Scriptable scriptable2, Object obj, int i) {
        double doubleValue;
        Object obj2;
        Object obj3 = (i & 2) != 0 ? 1 : null;
        if (obj instanceof Number) {
            doubleValue = ((Number) obj).doubleValue();
            obj2 = obj;
        } else {
            doubleValue = toNumber(obj);
            if (obj3 != null) {
                obj2 = wrapNumber(doubleValue);
            } else {
                obj2 = obj;
            }
        }
        if ((i & 1) == 0) {
            doubleValue += 1.0d;
        } else {
            doubleValue -= 1.0d;
        }
        Number wrapNumber = wrapNumber(doubleValue);
        scriptable.put(str, scriptable2, wrapNumber);
        if (obj3 != null) {
            return obj2;
        }
        return wrapNumber;
    }

    @Deprecated
    public static Object elemIncrDecr(Object obj, Object obj2, Context context, int i) {
        return elemIncrDecr(obj, obj2, context, getTopCallScope(context), i);
    }

    public static Object elemIncrDecr(Object obj, Object obj2, Context context, Scriptable scriptable, int i) {
        Object obj3;
        double doubleValue;
        Object objectElem = getObjectElem(obj, obj2, context, scriptable);
        Object obj4 = (i & 2) != 0 ? 1 : null;
        if (objectElem instanceof Number) {
            obj3 = objectElem;
            doubleValue = ((Number) objectElem).doubleValue();
        } else {
            double toNumber = toNumber(objectElem);
            if (obj4 != null) {
                Number wrapNumber = wrapNumber(toNumber);
                doubleValue = toNumber;
            } else {
                obj3 = objectElem;
                doubleValue = toNumber;
            }
        }
        if ((i & 1) == 0) {
            doubleValue += 1.0d;
        } else {
            doubleValue -= 1.0d;
        }
        Object wrapNumber2 = wrapNumber(doubleValue);
        setObjectElem(obj, obj2, wrapNumber2, context, scriptable);
        if (obj4 != null) {
            return obj3;
        }
        return wrapNumber2;
    }

    @Deprecated
    public static Object refIncrDecr(Ref ref, Context context, int i) {
        return refIncrDecr(ref, context, getTopCallScope(context), i);
    }

    public static Object refIncrDecr(Ref ref, Context context, Scriptable scriptable, int i) {
        Object obj;
        double doubleValue;
        Object obj2 = ref.get(context);
        Object obj3 = (i & 2) != 0 ? 1 : null;
        if (obj2 instanceof Number) {
            obj = obj2;
            doubleValue = ((Number) obj2).doubleValue();
        } else {
            double toNumber = toNumber(obj2);
            if (obj3 != null) {
                Number wrapNumber = wrapNumber(toNumber);
                doubleValue = toNumber;
            } else {
                obj = obj2;
                doubleValue = toNumber;
            }
        }
        if ((i & 1) == 0) {
            doubleValue += 1.0d;
        } else {
            doubleValue -= 1.0d;
        }
        Object wrapNumber2 = wrapNumber(doubleValue);
        ref.set(context, scriptable, wrapNumber2);
        if (obj3 != null) {
            return obj;
        }
        return wrapNumber2;
    }

    public static Object toPrimitive(Object obj) {
        return toPrimitive(obj, null);
    }

    public static Object toPrimitive(Object obj, Class<?> cls) {
        if (obj instanceof Scriptable) {
            obj = ((Scriptable) obj).getDefaultValue(cls);
            if (obj instanceof Scriptable) {
                throw typeError0("msg.bad.default.value");
            }
        }
        return obj;
    }

    public static boolean eq(Object obj, Object obj2) {
        double d = 1.0d;
        boolean z = true;
        Object equivalentValues;
        if (obj == null || obj == Undefined.instance) {
            if (obj2 == null || obj2 == Undefined.instance) {
                return true;
            }
            if (!(obj2 instanceof ScriptableObject)) {
                return false;
            }
            equivalentValues = ((ScriptableObject) obj2).equivalentValues(obj);
            if (equivalentValues != Scriptable.NOT_FOUND) {
                return ((Boolean) equivalentValues).booleanValue();
            }
            return false;
        } else if (obj instanceof Number) {
            return eqNumber(((Number) obj).doubleValue(), obj2);
        } else {
            if (obj == obj2) {
                return true;
            }
            if (obj instanceof CharSequence) {
                return eqString((CharSequence) obj, obj2);
            }
            if (obj instanceof Boolean) {
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (obj2 instanceof Boolean) {
                    return booleanValue == ((Boolean) obj2).booleanValue();
                }
                if (obj2 instanceof ScriptableObject) {
                    equivalentValues = ((ScriptableObject) obj2).equivalentValues(obj);
                    if (equivalentValues != Scriptable.NOT_FOUND) {
                        return ((Boolean) equivalentValues).booleanValue();
                    }
                }
                return eqNumber(booleanValue ? 1.0d : 0.0d, obj2);
            } else if (!(obj instanceof Scriptable)) {
                warnAboutNonJSObject(obj);
                if (obj != obj2) {
                    z = false;
                }
                return z;
            } else if (obj2 instanceof Scriptable) {
                if (obj instanceof ScriptableObject) {
                    equivalentValues = ((ScriptableObject) obj).equivalentValues(obj2);
                    if (equivalentValues != Scriptable.NOT_FOUND) {
                        return ((Boolean) equivalentValues).booleanValue();
                    }
                }
                if (obj2 instanceof ScriptableObject) {
                    equivalentValues = ((ScriptableObject) obj2).equivalentValues(obj);
                    if (equivalentValues != Scriptable.NOT_FOUND) {
                        return ((Boolean) equivalentValues).booleanValue();
                    }
                }
                if (!(obj instanceof Wrapper) || !(obj2 instanceof Wrapper)) {
                    return false;
                }
                equivalentValues = ((Wrapper) obj).unwrap();
                Object unwrap = ((Wrapper) obj2).unwrap();
                if (equivalentValues == unwrap || (isPrimitive(equivalentValues) && isPrimitive(unwrap) && eq(equivalentValues, unwrap))) {
                    return true;
                }
                return false;
            } else if (obj2 instanceof Boolean) {
                if (obj instanceof ScriptableObject) {
                    equivalentValues = ((ScriptableObject) obj).equivalentValues(obj2);
                    if (equivalentValues != Scriptable.NOT_FOUND) {
                        return ((Boolean) equivalentValues).booleanValue();
                    }
                }
                if (!((Boolean) obj2).booleanValue()) {
                    d = 0.0d;
                }
                return eqNumber(d, obj);
            } else if (obj2 instanceof Number) {
                return eqNumber(((Number) obj2).doubleValue(), obj);
            } else {
                if (obj2 instanceof CharSequence) {
                    return eqString((CharSequence) obj2, obj);
                }
                return false;
            }
        }
    }

    public static boolean isPrimitive(Object obj) {
        return obj == null || obj == Undefined.instance || (obj instanceof Number) || (obj instanceof String) || (obj instanceof Boolean);
    }

    static boolean eqNumber(double d, Object obj) {
        Object obj2 = obj;
        while (obj2 != null && obj2 != Undefined.instance) {
            if (obj2 instanceof Number) {
                return d == ((Number) obj2).doubleValue();
            } else if (obj2 instanceof CharSequence) {
                if (d != toNumber(obj2)) {
                    return false;
                }
                return true;
            } else if (obj2 instanceof Boolean) {
                if (d != (((Boolean) obj2).booleanValue() ? 1.0d : 0.0d)) {
                    return false;
                }
                return true;
            } else if (obj2 instanceof Scriptable) {
                if (obj2 instanceof ScriptableObject) {
                    Object equivalentValues = ((ScriptableObject) obj2).equivalentValues(wrapNumber(d));
                    if (equivalentValues != Scriptable.NOT_FOUND) {
                        return ((Boolean) equivalentValues).booleanValue();
                    }
                }
                obj2 = toPrimitive(obj2);
            } else {
                warnAboutNonJSObject(obj2);
                return false;
            }
        }
        return false;
    }

    private static boolean eqString(CharSequence charSequence, Object obj) {
        Object obj2 = obj;
        while (obj2 != null && obj2 != Undefined.instance) {
            if (obj2 instanceof CharSequence) {
                CharSequence charSequence2 = (CharSequence) obj2;
                boolean z = charSequence.length() == charSequence2.length() && charSequence.toString().equals(charSequence2.toString());
                return z;
            } else if (obj2 instanceof Number) {
                if (toNumber(charSequence.toString()) != ((Number) obj2).doubleValue()) {
                    return false;
                }
                return true;
            } else if (obj2 instanceof Boolean) {
                if (toNumber(charSequence.toString()) != (((Boolean) obj2).booleanValue() ? 1.0d : 0.0d)) {
                    return false;
                }
                return true;
            } else if (obj2 instanceof Scriptable) {
                if (obj2 instanceof ScriptableObject) {
                    Object equivalentValues = ((ScriptableObject) obj2).equivalentValues(charSequence.toString());
                    if (equivalentValues != Scriptable.NOT_FOUND) {
                        return ((Boolean) equivalentValues).booleanValue();
                    }
                }
                obj2 = toPrimitive(obj2);
            } else {
                warnAboutNonJSObject(obj2);
                return false;
            }
        }
        return false;
    }

    public static boolean shallowEq(Object obj, Object obj2) {
        if (obj == obj2) {
            if (!(obj instanceof Number)) {
                return true;
            }
            double doubleValue = ((Number) obj).doubleValue();
            if (doubleValue != doubleValue) {
                return false;
            }
            return true;
        } else if (obj == null || obj == Undefined.instance) {
            return false;
        } else {
            if (obj instanceof Number) {
                if (obj2 instanceof Number) {
                    if (((Number) obj).doubleValue() != ((Number) obj2).doubleValue()) {
                        return false;
                    }
                    return true;
                }
            } else if (obj instanceof CharSequence) {
                if (obj2 instanceof CharSequence) {
                    return obj.toString().equals(obj2.toString());
                }
            } else if (obj instanceof Boolean) {
                if (obj2 instanceof Boolean) {
                    return obj.equals(obj2);
                }
            } else if (!(obj instanceof Scriptable)) {
                warnAboutNonJSObject(obj);
                if (obj != obj2) {
                    return false;
                }
                return true;
            } else if ((obj instanceof Wrapper) && (obj2 instanceof Wrapper)) {
                if (((Wrapper) obj).unwrap() != ((Wrapper) obj2).unwrap()) {
                    return false;
                }
                return true;
            }
            return false;
        }
    }

    public static boolean instanceOf(Object obj, Object obj2, Context context) {
        if (!(obj2 instanceof Scriptable)) {
            throw typeError0("msg.instanceof.not.object");
        } else if (obj instanceof Scriptable) {
            return ((Scriptable) obj2).hasInstance((Scriptable) obj);
        } else {
            return false;
        }
    }

    public static boolean jsDelegatesTo(Scriptable scriptable, Scriptable scriptable2) {
        for (Scriptable prototype = scriptable.getPrototype(); prototype != null; prototype = prototype.getPrototype()) {
            if (prototype.equals(scriptable2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean in(Object obj, Object obj2, Context context) {
        if (obj2 instanceof Scriptable) {
            return hasObjectElem((Scriptable) obj2, obj, context);
        }
        throw typeError0("msg.in.not.object");
    }

    public static boolean cmp_LT(Object obj, Object obj2) {
        double doubleValue;
        double doubleValue2;
        if ((obj instanceof Number) && (obj2 instanceof Number)) {
            doubleValue = ((Number) obj).doubleValue();
            doubleValue2 = ((Number) obj2).doubleValue();
        } else {
            if (obj instanceof Scriptable) {
                obj = ((Scriptable) obj).getDefaultValue(NumberClass);
            }
            if (obj2 instanceof Scriptable) {
                obj2 = ((Scriptable) obj2).getDefaultValue(NumberClass);
            }
            if (!(obj instanceof CharSequence) || !(obj2 instanceof CharSequence)) {
                doubleValue = toNumber(obj);
                doubleValue2 = toNumber(obj2);
            } else if (obj.toString().compareTo(obj2.toString()) >= 0) {
                return false;
            } else {
                return true;
            }
        }
        if (doubleValue < doubleValue2) {
            return true;
        }
        return false;
    }

    public static boolean cmp_LE(Object obj, Object obj2) {
        double doubleValue;
        double doubleValue2;
        if ((obj instanceof Number) && (obj2 instanceof Number)) {
            doubleValue = ((Number) obj).doubleValue();
            doubleValue2 = ((Number) obj2).doubleValue();
        } else {
            if (obj instanceof Scriptable) {
                obj = ((Scriptable) obj).getDefaultValue(NumberClass);
            }
            if (obj2 instanceof Scriptable) {
                obj2 = ((Scriptable) obj2).getDefaultValue(NumberClass);
            }
            if (!(obj instanceof CharSequence) || !(obj2 instanceof CharSequence)) {
                doubleValue = toNumber(obj);
                doubleValue2 = toNumber(obj2);
            } else if (obj.toString().compareTo(obj2.toString()) > 0) {
                return false;
            } else {
                return true;
            }
        }
        if (doubleValue <= doubleValue2) {
            return true;
        }
        return false;
    }

    public static ScriptableObject getGlobal(Context context) {
        String str = "org.mozilla.javascript.tools.shell.Global";
        Class classOrNull = Kit.classOrNull("org.mozilla.javascript.tools.shell.Global");
        if (classOrNull != null) {
            try {
                return (ScriptableObject) classOrNull.getConstructor(new Class[]{ContextClass}).newInstance(new Object[]{context});
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
        return new ImporterTopLevel(context);
    }

    public static boolean hasTopCall(Context context) {
        return context.topCallScope != null;
    }

    public static Scriptable getTopCallScope(Context context) {
        Scriptable scriptable = context.topCallScope;
        if (scriptable != null) {
            return scriptable;
        }
        throw new IllegalStateException();
    }

    public static Object doTopCall(Callable callable, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (scriptable == null) {
            throw new IllegalArgumentException();
        } else if (context.topCallScope != null) {
            throw new IllegalStateException();
        } else {
            context.topCallScope = ScriptableObject.getTopLevelScope(scriptable);
            context.useDynamicScope = context.hasFeature(7);
            try {
                Object doTopCall = context.getFactory().doTopCall(callable, context, scriptable, scriptable2, objArr);
                context.topCallScope = null;
                context.cachedXMLLib = null;
                if (context.currentActivationCall == null) {
                    return doTopCall;
                }
                throw new IllegalStateException();
            } catch (Throwable th) {
                context.topCallScope = null;
                context.cachedXMLLib = null;
                if (context.currentActivationCall != null) {
                    IllegalStateException illegalStateException = new IllegalStateException();
                }
            }
        }
    }

    static Scriptable checkDynamicScope(Scriptable scriptable, Scriptable scriptable2) {
        if (scriptable == scriptable2) {
            return scriptable;
        }
        Scriptable scriptable3 = scriptable;
        do {
            scriptable3 = scriptable3.getPrototype();
            if (scriptable3 == scriptable2) {
                return scriptable;
            }
        } while (scriptable3 != null);
        return scriptable2;
    }

    public static void addInstructionCount(Context context, int i) {
        context.instructionCount += i;
        if (context.instructionCount > context.instructionThreshold) {
            context.observeInstructionCount(context.instructionCount);
            context.instructionCount = 0;
        }
    }

    public static void initScript(NativeFunction nativeFunction, Scriptable scriptable, Context context, Scriptable scriptable2, boolean z) {
        if (context.topCallScope == null) {
            throw new IllegalStateException();
        }
        int paramAndVarCount = nativeFunction.getParamAndVarCount();
        if (paramAndVarCount != 0) {
            Scriptable scriptable3 = scriptable2;
            while (scriptable3 instanceof NativeWith) {
                scriptable3 = scriptable3.getParentScope();
            }
            while (true) {
                int i = paramAndVarCount - 1;
                if (paramAndVarCount != 0) {
                    String paramOrVarName = nativeFunction.getParamOrVarName(i);
                    boolean paramOrVarConst = nativeFunction.getParamOrVarConst(i);
                    if (ScriptableObject.hasProperty(scriptable2, paramOrVarName)) {
                        ScriptableObject.redefineProperty(scriptable2, paramOrVarName, paramOrVarConst);
                    } else if (paramOrVarConst) {
                        ScriptableObject.defineConstProperty(scriptable3, paramOrVarName);
                    } else if (z) {
                        scriptable3.put(paramOrVarName, scriptable3, Undefined.instance);
                    } else {
                        ScriptableObject.defineProperty(scriptable3, paramOrVarName, Undefined.instance, 4);
                    }
                    paramAndVarCount = i;
                } else {
                    return;
                }
            }
        }
    }

    public static Scriptable createFunctionActivation(NativeFunction nativeFunction, Scriptable scriptable, Object[] objArr) {
        return new NativeCall(nativeFunction, scriptable, objArr);
    }

    public static void enterActivationFunction(Context context, Scriptable scriptable) {
        if (context.topCallScope == null) {
            throw new IllegalStateException();
        }
        NativeCall nativeCall = (NativeCall) scriptable;
        nativeCall.parentActivationCall = context.currentActivationCall;
        context.currentActivationCall = nativeCall;
    }

    public static void exitActivationFunction(Context context) {
        NativeCall nativeCall = context.currentActivationCall;
        context.currentActivationCall = nativeCall.parentActivationCall;
        nativeCall.parentActivationCall = null;
    }

    static NativeCall findFunctionActivation(Context context, Function function) {
        for (NativeCall nativeCall = context.currentActivationCall; nativeCall != null; nativeCall = nativeCall.parentActivationCall) {
            if (nativeCall.function == function) {
                return nativeCall;
            }
        }
        return null;
    }

    public static Scriptable newCatchScope(Throwable th, Scriptable scriptable, String str, Context context, Scriptable scriptable2) {
        Object value;
        if (th instanceof JavaScriptException) {
            value = ((JavaScriptException) th).getValue();
            Object obj = null;
        } else if (scriptable != null) {
            value = ((NativeObject) scriptable).getAssociatedValue(th);
            if (value == null) {
                Kit.codeBug();
                r0 = 1;
            } else {
                r0 = 1;
            }
        } else {
            NativeErrors valueOf;
            RhinoException rhinoException;
            String errorMessage;
            Object obj2;
            Object[] objArr;
            RhinoException rhinoException2;
            if (th instanceof EcmaError) {
                rhinoException2 = (EcmaError) th;
                valueOf = NativeErrors.valueOf(rhinoException2.getName());
                rhinoException = rhinoException2;
                errorMessage = rhinoException2.getErrorMessage();
                obj2 = null;
            } else if (th instanceof WrappedException) {
                rhinoException2 = (WrappedException) th;
                Throwable wrappedException = rhinoException2.getWrappedException();
                Throwable th2 = wrappedException;
                valueOf = NativeErrors.JavaException;
                rhinoException = rhinoException2;
                errorMessage = wrappedException.getClass().getName() + ": " + wrappedException.getMessage();
                Throwable th3 = th2;
            } else if (th instanceof EvaluatorException) {
                rhinoException2 = (EvaluatorException) th;
                valueOf = NativeErrors.InternalError;
                rhinoException = rhinoException2;
                errorMessage = rhinoException2.getMessage();
                obj2 = null;
            } else if (context.hasFeature(13)) {
                rhinoException = new WrappedException(th);
                NativeErrors nativeErrors = NativeErrors.JavaException;
                errorMessage = th.toString();
                valueOf = nativeErrors;
                obj2 = null;
            } else {
                throw Kit.codeBug();
            }
            String sourceName = rhinoException.sourceName();
            if (sourceName == null) {
                sourceName = "";
            }
            if (rhinoException.lineNumber() > 0) {
                objArr = new Object[]{errorMessage, sourceName, Integer.valueOf(rhinoException.lineNumber())};
            } else {
                objArr = new Object[]{errorMessage, sourceName};
            }
            value = newNativeError(context, scriptable2, valueOf, objArr);
            if (value instanceof NativeError) {
                ((NativeError) value).setStackProvider(rhinoException);
            }
            if (obj2 != null && isVisible(context, obj2)) {
                ScriptableObject.defineProperty(value, "javaException", context.getWrapFactory().wrap(context, scriptable2, obj2, null), 5);
            }
            if (isVisible(context, rhinoException)) {
                ScriptableObject.defineProperty(value, "rhinoException", context.getWrapFactory().wrap(context, scriptable2, rhinoException, null), 5);
            }
            r0 = 1;
        }
        Scriptable nativeObject = new NativeObject();
        nativeObject.defineProperty(str, value, 4);
        if (isVisible(context, th)) {
            nativeObject.defineProperty("__exception__", Context.javaToJS(th, scriptable2), 6);
        }
        if (obj != null) {
            nativeObject.associateValue(th, value);
        }
        return nativeObject;
    }

    public static Scriptable wrapException(Throwable th, Scriptable scriptable, Context context) {
        String name;
        String errorMessage;
        Object obj;
        Object obj2;
        Object[] objArr;
        if (th instanceof EcmaError) {
            th = (EcmaError) th;
            name = th.getName();
            errorMessage = th.getErrorMessage();
            obj = null;
            obj2 = name;
        } else if (th instanceof WrappedException) {
            WrappedException wrappedException = (WrappedException) th;
            Throwable wrappedException2 = wrappedException.getWrappedException();
            r4 = "JavaException";
            r2 = wrappedException2;
            errorMessage = wrappedException2.getClass().getName() + ": " + wrappedException2.getMessage();
        } else if (th instanceof EvaluatorException) {
            EvaluatorException evaluatorException = (EvaluatorException) th;
            errorMessage = evaluatorException.getMessage();
            obj = null;
            r4 = "InternalError";
        } else if (context.hasFeature(13)) {
            r2 = new WrappedException(th);
            errorMessage = th.toString();
            r4 = "JavaException";
            th = r2;
            obj = null;
        } else {
            throw Kit.codeBug();
        }
        name = th.sourceName();
        if (name == null) {
            name = "";
        }
        if (th.lineNumber() > 0) {
            objArr = new Object[]{errorMessage, name, Integer.valueOf(th.lineNumber())};
        } else {
            objArr = new Object[]{errorMessage, name};
        }
        Scriptable newObject = context.newObject(scriptable, obj2, objArr);
        ScriptableObject.putProperty(newObject, "name", obj2);
        if (newObject instanceof NativeError) {
            ((NativeError) newObject).setStackProvider(th);
        }
        if (obj != null && isVisible(context, obj)) {
            ScriptableObject.defineProperty(newObject, "javaException", context.getWrapFactory().wrap(context, scriptable, obj, null), 5);
        }
        if (isVisible(context, th)) {
            ScriptableObject.defineProperty(newObject, "rhinoException", context.getWrapFactory().wrap(context, scriptable, th, null), 5);
        }
        return newObject;
    }

    private static boolean isVisible(Context context, Object obj) {
        ClassShutter classShutter = context.getClassShutter();
        return classShutter == null || classShutter.visibleToScripts(obj.getClass().getName());
    }

    public static Scriptable enterWith(Object obj, Context context, Scriptable scriptable) {
        Scriptable toObjectOrNull = toObjectOrNull(context, obj, scriptable);
        if (toObjectOrNull == null) {
            throw typeError1("msg.undef.with", toString(obj));
        } else if (toObjectOrNull instanceof XMLObject) {
            return ((XMLObject) toObjectOrNull).enterWith(scriptable);
        } else {
            return new NativeWith(scriptable, toObjectOrNull);
        }
    }

    public static Scriptable leaveWith(Scriptable scriptable) {
        return ((NativeWith) scriptable).getParentScope();
    }

    public static Scriptable enterDotQuery(Object obj, Scriptable scriptable) {
        if (obj instanceof XMLObject) {
            return ((XMLObject) obj).enterDotQuery(scriptable);
        }
        throw notXmlError(obj);
    }

    public static Object updateDotQuery(boolean z, Scriptable scriptable) {
        return ((NativeWith) scriptable).updateDotQuery(z);
    }

    public static Scriptable leaveDotQuery(Scriptable scriptable) {
        return ((NativeWith) scriptable).getParentScope();
    }

    public static void setFunctionProtoAndParent(BaseFunction baseFunction, Scriptable scriptable) {
        baseFunction.setParentScope(scriptable);
        baseFunction.setPrototype(ScriptableObject.getFunctionPrototype(scriptable));
    }

    public static void setObjectProtoAndParent(ScriptableObject scriptableObject, Scriptable scriptable) {
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        scriptableObject.setParentScope(topLevelScope);
        scriptableObject.setPrototype(ScriptableObject.getClassPrototype(topLevelScope, scriptableObject.getClassName()));
    }

    public static void setBuiltinProtoAndParent(ScriptableObject scriptableObject, Scriptable scriptable, Builtins builtins) {
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        scriptableObject.setParentScope(topLevelScope);
        scriptableObject.setPrototype(TopLevel.getBuiltinPrototype(topLevelScope, builtins));
    }

    public static void initFunction(Context context, Scriptable scriptable, NativeFunction nativeFunction, int i, boolean z) {
        String functionName;
        if (i == 1) {
            functionName = nativeFunction.getFunctionName();
            if (functionName != null && functionName.length() != 0) {
                if (z) {
                    scriptable.put(functionName, scriptable, nativeFunction);
                } else {
                    ScriptableObject.defineProperty(scriptable, functionName, nativeFunction, 4);
                }
            }
        } else if (i == 3) {
            functionName = nativeFunction.getFunctionName();
            if (functionName != null && functionName.length() != 0) {
                while (scriptable instanceof NativeWith) {
                    scriptable = scriptable.getParentScope();
                }
                scriptable.put(functionName, scriptable, nativeFunction);
            }
        } else {
            throw Kit.codeBug();
        }
    }

    public static Scriptable newArrayLiteral(Object[] objArr, int[] iArr, Context context, Scriptable scriptable) {
        int length;
        int i = 0;
        int length2 = objArr.length;
        if (iArr != null) {
            length = iArr.length;
        } else {
            length = 0;
        }
        int i2 = length2 + length;
        int i3;
        int i4;
        if (i2 <= 1 || length * 2 >= i2) {
            Scriptable newArray = context.newArray(scriptable, i2);
            i3 = 0;
            i4 = 0;
            while (i3 != i2) {
                if (i4 == length || iArr[i4] != i3) {
                    ScriptableObject.putProperty(newArray, i3, objArr[i]);
                    i++;
                } else {
                    i4++;
                }
                i3++;
            }
            return newArray;
        }
        if (length != 0) {
            Object[] objArr2 = new Object[i2];
            i3 = 0;
            i4 = 0;
            while (i3 != i2) {
                if (i4 == length || iArr[i4] != i3) {
                    objArr2[i3] = objArr[i];
                    i++;
                } else {
                    objArr2[i3] = Scriptable.NOT_FOUND;
                    i4++;
                }
                i3++;
            }
            objArr = objArr2;
        }
        return context.newArray(scriptable, objArr);
    }

    @Deprecated
    public static Scriptable newObjectLiteral(Object[] objArr, Object[] objArr2, Context context, Scriptable scriptable) {
        return newObjectLiteral(objArr, objArr2, null, context, scriptable);
    }

    public static Scriptable newObjectLiteral(Object[] objArr, Object[] objArr2, int[] iArr, Context context, Scriptable scriptable) {
        Scriptable newObject = context.newObject(scriptable);
        int length = objArr.length;
        int i = 0;
        while (i != length) {
            Object obj = objArr[i];
            int i2 = iArr == null ? 0 : iArr[i];
            Object obj2 = objArr2[i];
            if (!(obj instanceof String)) {
                newObject.put(((Integer) obj).intValue(), newObject, obj2);
            } else if (i2 != 0) {
                boolean z;
                ScriptableObject scriptableObject = (ScriptableObject) newObject;
                Callable callable = (Callable) obj2;
                if (i2 == 1) {
                    z = true;
                } else {
                    z = false;
                }
                scriptableObject.setGetterOrSetter((String) obj, 0, callable, z);
            } else if (isSpecialProperty((String) obj)) {
                specialRef(newObject, (String) obj, context, scriptable).set(context, scriptable, obj2);
            } else {
                newObject.put((String) obj, newObject, obj2);
            }
            i++;
        }
        return newObject;
    }

    public static boolean isArrayObject(Object obj) {
        return (obj instanceof NativeArray) || (obj instanceof Arguments);
    }

    public static Object[] getArrayElements(Scriptable scriptable) {
        long lengthProperty = NativeArray.getLengthProperty(Context.getContext(), scriptable);
        if (lengthProperty > 2147483647L) {
            throw new IllegalArgumentException();
        }
        int i = (int) lengthProperty;
        if (i == 0) {
            return emptyArgs;
        }
        Object[] objArr = new Object[i];
        for (int i2 = 0; i2 < i; i2++) {
            Object property = ScriptableObject.getProperty(scriptable, i2);
            if (property == Scriptable.NOT_FOUND) {
                property = Undefined.instance;
            }
            objArr[i2] = property;
        }
        return objArr;
    }

    static void checkDeprecated(Context context, String str) {
        int languageVersion = context.getLanguageVersion();
        if (languageVersion >= 140 || languageVersion == 0) {
            String message1 = getMessage1("msg.deprec.ctor", str);
            if (languageVersion == 0) {
                Context.reportWarning(message1);
                return;
            }
            throw Context.reportRuntimeError(message1);
        }
    }

    public static String getMessage0(String str) {
        return getMessage(str, null);
    }

    public static String getMessage1(String str, Object obj) {
        return getMessage(str, new Object[]{obj});
    }

    public static String getMessage2(String str, Object obj, Object obj2) {
        return getMessage(str, new Object[]{obj, obj2});
    }

    public static String getMessage3(String str, Object obj, Object obj2, Object obj3) {
        return getMessage(str, new Object[]{obj, obj2, obj3});
    }

    public static String getMessage4(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        return getMessage(str, new Object[]{obj, obj2, obj3, obj4});
    }

    public static String getMessage(String str, Object[] objArr) {
        return messageProvider.getMessage(str, objArr);
    }

    public static EcmaError constructError(String str, String str2) {
        int[] iArr = new int[1];
        return constructError(str, str2, Context.getSourcePositionFromStack(iArr), iArr[0], null, 0);
    }

    public static EcmaError constructError(String str, String str2, int i) {
        int[] iArr = new int[1];
        String sourcePositionFromStack = Context.getSourcePositionFromStack(iArr);
        if (iArr[0] != 0) {
            iArr[0] = iArr[0] + i;
        }
        return constructError(str, str2, sourcePositionFromStack, iArr[0], null, 0);
    }

    public static EcmaError constructError(String str, String str2, String str3, int i, String str4, int i2) {
        return new EcmaError(str, str2, str3, i, str4, i2);
    }

    public static EcmaError typeError(String str) {
        return constructError("TypeError", str);
    }

    public static EcmaError typeError0(String str) {
        return typeError(getMessage0(str));
    }

    public static EcmaError typeError1(String str, String str2) {
        return typeError(getMessage1(str, str2));
    }

    public static EcmaError typeError2(String str, String str2, String str3) {
        return typeError(getMessage2(str, str2, str3));
    }

    public static EcmaError typeError3(String str, String str2, String str3, String str4) {
        return typeError(getMessage3(str, str2, str3, str4));
    }

    public static RuntimeException undefReadError(Object obj, Object obj2) {
        return typeError2("msg.undef.prop.read", toString(obj), toString(obj2));
    }

    public static RuntimeException undefCallError(Object obj, Object obj2) {
        return typeError2("msg.undef.method.call", toString(obj), toString(obj2));
    }

    public static RuntimeException undefWriteError(Object obj, Object obj2, Object obj3) {
        return typeError3("msg.undef.prop.write", toString(obj), toString(obj2), toString(obj3));
    }

    private static RuntimeException undefDeleteError(Object obj, Object obj2) {
        throw typeError2("msg.undef.prop.delete", toString(obj), toString(obj2));
    }

    public static RuntimeException notFoundError(Scriptable scriptable, String str) {
        throw constructError("ReferenceError", getMessage1("msg.is.not.defined", str));
    }

    public static RuntimeException notFunctionError(Object obj) {
        return notFunctionError(obj, obj);
    }

    public static RuntimeException notFunctionError(Object obj, Object obj2) {
        String obj3 = obj2 == null ? "null" : obj2.toString();
        if (obj == Scriptable.NOT_FOUND) {
            return typeError1("msg.function.not.found", obj3);
        }
        return typeError2("msg.isnt.function", obj3, typeof(obj));
    }

    public static RuntimeException notFunctionError(Object obj, Object obj2, String str) {
        String scriptRuntime = toString(obj);
        if (obj instanceof NativeFunction) {
            int indexOf = scriptRuntime.indexOf(123, scriptRuntime.indexOf(41));
            if (indexOf > -1) {
                scriptRuntime = scriptRuntime.substring(0, indexOf + 1) + "...}";
            }
        }
        if (obj2 == Scriptable.NOT_FOUND) {
            return typeError2("msg.function.not.found.in", str, scriptRuntime);
        }
        return typeError3("msg.isnt.function.in", str, scriptRuntime, typeof(obj2));
    }

    private static RuntimeException notXmlError(Object obj) {
        throw typeError1("msg.isnt.xml.object", toString(obj));
    }

    private static void warnAboutNonJSObject(Object obj) {
        String str = "RHINO USAGE WARNING: Missed Context.javaToJS() conversion:\nRhino runtime detected object " + obj + " of class " + obj.getClass().getName() + " where it expected String, Number, Boolean or Scriptable instance. Please check your code for missing Context.javaToJS() call.";
        Context.reportWarning(str);
        System.err.println(str);
    }

    public static RegExpProxy getRegExpProxy(Context context) {
        return context.getRegExpProxy();
    }

    public static void setRegExpProxy(Context context, RegExpProxy regExpProxy) {
        if (regExpProxy == null) {
            throw new IllegalArgumentException();
        }
        context.regExpProxy = regExpProxy;
    }

    public static RegExpProxy checkRegExpProxy(Context context) {
        RegExpProxy regExpProxy = getRegExpProxy(context);
        if (regExpProxy != null) {
            return regExpProxy;
        }
        throw Context.reportRuntimeError0("msg.no.regexp");
    }

    public static Scriptable wrapRegExp(Context context, Scriptable scriptable, Object obj) {
        return context.getRegExpProxy().wrapRegExp(context, scriptable, obj);
    }

    private static XMLLib currentXMLLib(Context context) {
        if (context.topCallScope == null) {
            throw new IllegalStateException();
        }
        XMLLib xMLLib = context.cachedXMLLib;
        if (xMLLib == null) {
            xMLLib = XMLLib.extractFromScope(context.topCallScope);
            if (xMLLib == null) {
                throw new IllegalStateException();
            }
            context.cachedXMLLib = xMLLib;
        }
        return xMLLib;
    }

    public static String escapeAttributeValue(Object obj, Context context) {
        return currentXMLLib(context).escapeAttributeValue(obj);
    }

    public static String escapeTextValue(Object obj, Context context) {
        return currentXMLLib(context).escapeTextValue(obj);
    }

    public static Ref memberRef(Object obj, Object obj2, Context context, int i) {
        if (obj instanceof XMLObject) {
            return ((XMLObject) obj).memberRef(context, obj2, i);
        }
        throw notXmlError(obj);
    }

    public static Ref memberRef(Object obj, Object obj2, Object obj3, Context context, int i) {
        if (obj instanceof XMLObject) {
            return ((XMLObject) obj).memberRef(context, obj2, obj3, i);
        }
        throw notXmlError(obj);
    }

    public static Ref nameRef(Object obj, Context context, Scriptable scriptable, int i) {
        return currentXMLLib(context).nameRef(context, obj, scriptable, i);
    }

    public static Ref nameRef(Object obj, Object obj2, Context context, Scriptable scriptable, int i) {
        return currentXMLLib(context).nameRef(context, obj, obj2, scriptable, i);
    }

    private static void storeIndexResult(Context context, int i) {
        context.scratchIndex = i;
    }

    static int lastIndexResult(Context context) {
        return context.scratchIndex;
    }

    public static void storeUint32Result(Context context, long j) {
        if ((j >>> 32) != 0) {
            throw new IllegalArgumentException();
        }
        context.scratchUint32 = j;
    }

    public static long lastUint32Result(Context context) {
        long j = context.scratchUint32;
        if ((j >>> 32) == 0) {
            return j;
        }
        throw new IllegalStateException();
    }

    private static void storeScriptable(Context context, Scriptable scriptable) {
        if (context.scratchScriptable != null) {
            throw new IllegalStateException();
        }
        context.scratchScriptable = scriptable;
    }

    public static Scriptable lastStoredScriptable(Context context) {
        Scriptable scriptable = context.scratchScriptable;
        context.scratchScriptable = null;
        return scriptable;
    }

    static String makeUrlForGeneratedScript(boolean z, String str, int i) {
        if (z) {
            return str + '#' + i + "(eval)";
        }
        return str + '#' + i + "(Function)";
    }

    static boolean isGeneratedScript(String str) {
        return str.indexOf("(eval)") >= 0 || str.indexOf("(Function)") >= 0;
    }

    private static RuntimeException errorWithClassName(String str, Object obj) {
        return Context.reportRuntimeError1(str, obj.getClass().getName());
    }

    public static JavaScriptException throwError(Context context, Scriptable scriptable, String str) {
        int[] iArr = new int[]{0};
        return new JavaScriptException(newBuiltinObject(context, scriptable, Builtins.Error, new Object[]{str, r1, Integer.valueOf(iArr[0])}), Context.getSourcePositionFromStack(iArr), iArr[0]);
    }
}
