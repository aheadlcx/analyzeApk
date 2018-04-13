package org.mozilla.javascript.optimizer;

import org.mozilla.javascript.Callable;
import org.mozilla.javascript.ConsString;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.NativeFunction;
import org.mozilla.javascript.NativeGenerator;
import org.mozilla.javascript.NativeIterator;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;

public final class OptRuntime extends ScriptRuntime {
    public static final Double minusOneObj = new Double(-1.0d);
    public static final Double oneObj = new Double(1.0d);
    public static final Double zeroObj = new Double(0.0d);

    public static Object call0(Callable callable, Scriptable scriptable, Context context, Scriptable scriptable2) {
        return callable.call(context, scriptable2, scriptable, ScriptRuntime.emptyArgs);
    }

    public static Object call1(Callable callable, Scriptable scriptable, Object obj, Context context, Scriptable scriptable2) {
        return callable.call(context, scriptable2, scriptable, new Object[]{obj});
    }

    public static Object call2(Callable callable, Scriptable scriptable, Object obj, Object obj2, Context context, Scriptable scriptable2) {
        return callable.call(context, scriptable2, scriptable, new Object[]{obj, obj2});
    }

    public static Object callN(Callable callable, Scriptable scriptable, Object[] objArr, Context context, Scriptable scriptable2) {
        return callable.call(context, scriptable2, scriptable, objArr);
    }

    public static Object callName(Object[] objArr, String str, Context context, Scriptable scriptable) {
        return ScriptRuntime.getNameFunctionAndThis(str, context, scriptable).call(context, scriptable, ScriptRuntime.lastStoredScriptable(context), objArr);
    }

    public static Object callName0(String str, Context context, Scriptable scriptable) {
        return ScriptRuntime.getNameFunctionAndThis(str, context, scriptable).call(context, scriptable, ScriptRuntime.lastStoredScriptable(context), ScriptRuntime.emptyArgs);
    }

    public static Object callProp0(Object obj, String str, Context context, Scriptable scriptable) {
        return ScriptRuntime.getPropFunctionAndThis(obj, str, context, scriptable).call(context, scriptable, ScriptRuntime.lastStoredScriptable(context), ScriptRuntime.emptyArgs);
    }

    public static Object add(Object obj, double d) {
        Object defaultValue;
        if (obj instanceof Scriptable) {
            defaultValue = ((Scriptable) obj).getDefaultValue(null);
        } else {
            defaultValue = obj;
        }
        if (defaultValue instanceof CharSequence) {
            return new ConsString((CharSequence) defaultValue, ScriptRuntime.toString(d));
        }
        return wrapDouble(ScriptRuntime.toNumber(defaultValue) + d);
    }

    public static Object add(double d, Object obj) {
        Object defaultValue;
        if (obj instanceof Scriptable) {
            defaultValue = ((Scriptable) obj).getDefaultValue(null);
        } else {
            defaultValue = obj;
        }
        if (defaultValue instanceof CharSequence) {
            return new ConsString(ScriptRuntime.toString(d), (CharSequence) defaultValue);
        }
        return wrapDouble(ScriptRuntime.toNumber(defaultValue) + d);
    }

    @Deprecated
    public static Object elemIncrDecr(Object obj, double d, Context context, int i) {
        return elemIncrDecr(obj, d, context, ScriptRuntime.getTopCallScope(context), i);
    }

    public static Object elemIncrDecr(Object obj, double d, Context context, Scriptable scriptable, int i) {
        return ScriptRuntime.elemIncrDecr(obj, new Double(d), context, scriptable, i);
    }

    public static Object[] padStart(Object[] objArr, int i) {
        Object obj = new Object[(objArr.length + i)];
        System.arraycopy(objArr, 0, obj, i, objArr.length);
        return obj;
    }

    public static void initFunction(NativeFunction nativeFunction, int i, Scriptable scriptable, Context context) {
        ScriptRuntime.initFunction(context, scriptable, nativeFunction, i, false);
    }

    public static Object callSpecial(Context context, Callable callable, Scriptable scriptable, Object[] objArr, Scriptable scriptable2, Scriptable scriptable3, int i, String str, int i2) {
        return ScriptRuntime.callSpecial(context, callable, scriptable, objArr, scriptable2, scriptable3, i, str, i2);
    }

    public static Object newObjectSpecial(Context context, Object obj, Object[] objArr, Scriptable scriptable, Scriptable scriptable2, int i) {
        return ScriptRuntime.newSpecial(context, obj, objArr, scriptable, i);
    }

    public static Double wrapDouble(double d) {
        if (d == 0.0d) {
            if (1.0d / d > 0.0d) {
                return zeroObj;
            }
        } else if (d == 1.0d) {
            return oneObj;
        } else {
            if (d == -1.0d) {
                return minusOneObj;
            }
            if (d != d) {
                return NaNobj;
            }
        }
        return new Double(d);
    }

    static String encodeIntArray(int[] iArr) {
        int i = 0;
        if (iArr == null) {
            return null;
        }
        int length = iArr.length;
        char[] cArr = new char[((length * 2) + 1)];
        cArr[0] = '\u0001';
        while (i != length) {
            int i2 = iArr[i];
            int i3 = (i * 2) + 1;
            cArr[i3] = (char) (i2 >>> 16);
            cArr[i3 + 1] = (char) i2;
            i++;
        }
        return new String(cArr);
    }

    private static int[] decodeIntArray(String str, int i) {
        int i2 = 0;
        if (i == 0) {
            if (str == null) {
                return null;
            }
            throw new IllegalArgumentException();
        } else if (str.length() == (i * 2) + 1 || str.charAt(0) == '\u0001') {
            int[] iArr = new int[i];
            while (i2 != i) {
                int i3 = (i2 * 2) + 1;
                iArr[i2] = str.charAt(i3 + 1) | (str.charAt(i3) << 16);
                i2++;
            }
            return iArr;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static Scriptable newArrayLiteral(Object[] objArr, String str, int i, Context context, Scriptable scriptable) {
        return ScriptRuntime.newArrayLiteral(objArr, decodeIntArray(str, i), context, scriptable);
    }

    public static void main(Script script, String[] strArr) {
        ContextFactory.getGlobal().call(new OptRuntime$1(strArr, script));
    }

    public static void throwStopIteration(Object obj) {
        throw new JavaScriptException(NativeIterator.getStopIterationObject((Scriptable) obj), "", 0);
    }

    public static Scriptable createNativeGenerator(NativeFunction nativeFunction, Scriptable scriptable, Scriptable scriptable2, int i, int i2) {
        return new NativeGenerator(scriptable, nativeFunction, new OptRuntime$GeneratorState(scriptable2, i, i2));
    }

    public static Object[] getGeneratorStackState(Object obj) {
        OptRuntime$GeneratorState optRuntime$GeneratorState = (OptRuntime$GeneratorState) obj;
        if (optRuntime$GeneratorState.stackState == null) {
            optRuntime$GeneratorState.stackState = new Object[optRuntime$GeneratorState.maxStack];
        }
        return optRuntime$GeneratorState.stackState;
    }

    public static Object[] getGeneratorLocalsState(Object obj) {
        OptRuntime$GeneratorState optRuntime$GeneratorState = (OptRuntime$GeneratorState) obj;
        if (optRuntime$GeneratorState.localsState == null) {
            optRuntime$GeneratorState.localsState = new Object[optRuntime$GeneratorState.maxLocals];
        }
        return optRuntime$GeneratorState.localsState;
    }
}
