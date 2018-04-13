package org.mozilla.javascript;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import org.mozilla.javascript.json.JsonParser;
import org.mozilla.javascript.json.JsonParser.ParseException;

public final class NativeJSON extends IdScriptableObject {
    private static final int Id_parse = 2;
    private static final int Id_stringify = 3;
    private static final int Id_toSource = 1;
    private static final Object JSON_TAG = "JSON";
    private static final int LAST_METHOD_ID = 3;
    private static final int MAX_ID = 3;
    private static final int MAX_STRINGIFY_GAP_LENGTH = 10;
    static final long serialVersionUID = -4567599697595654984L;

    private static class StringifyState {
        Context cx;
        String gap;
        String indent;
        List<Object> propertyList;
        Callable replacer;
        Scriptable scope;
        Object space;
        Stack<Scriptable> stack = new Stack();

        StringifyState(Context context, Scriptable scriptable, String str, String str2, Callable callable, List<Object> list, Object obj) {
            this.cx = context;
            this.scope = scriptable;
            this.indent = str;
            this.gap = str2;
            this.replacer = callable;
            this.propertyList = list;
            this.space = obj;
        }
    }

    static void init(Scriptable scriptable, boolean z) {
        NativeJSON nativeJSON = new NativeJSON();
        nativeJSON.activatePrototypeMap(3);
        nativeJSON.setPrototype(getObjectPrototype(scriptable));
        nativeJSON.setParentScope(scriptable);
        if (z) {
            nativeJSON.sealObject();
        }
        ScriptableObject.defineProperty(scriptable, "JSON", nativeJSON, 2);
    }

    private NativeJSON() {
    }

    public String getClassName() {
        return "JSON";
    }

    protected void initPrototypeId(int i) {
        int i2 = 3;
        if (i <= 3) {
            String str;
            switch (i) {
                case 1:
                    i2 = 0;
                    str = "toSource";
                    break;
                case 2:
                    i2 = 2;
                    str = "parse";
                    break;
                case 3:
                    str = "stringify";
                    break;
                default:
                    throw new IllegalStateException(String.valueOf(i));
            }
            initPrototypeMethod(JSON_TAG, i, str, i2);
            return;
        }
        throw new IllegalStateException(String.valueOf(i));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object execIdCall(org.mozilla.javascript.IdFunctionObject r5, org.mozilla.javascript.Context r6, org.mozilla.javascript.Scriptable r7, org.mozilla.javascript.Scriptable r8, java.lang.Object[] r9) {
        /*
        r4 = this;
        r2 = 0;
        r3 = 1;
        r0 = 0;
        r1 = JSON_TAG;
        r1 = r5.hasTag(r1);
        if (r1 != 0) goto L_0x0010;
    L_0x000b:
        r0 = super.execIdCall(r5, r6, r7, r8, r9);
    L_0x000f:
        return r0;
    L_0x0010:
        r1 = r5.methodId();
        switch(r1) {
            case 1: goto L_0x0021;
            case 2: goto L_0x0025;
            case 3: goto L_0x003e;
            default: goto L_0x0017;
        };
    L_0x0017:
        r0 = new java.lang.IllegalStateException;
        r1 = java.lang.String.valueOf(r1);
        r0.<init>(r1);
        throw r0;
    L_0x0021:
        r0 = "JSON";
        goto L_0x000f;
    L_0x0025:
        r1 = org.mozilla.javascript.ScriptRuntime.toString(r9, r2);
        r2 = r9.length;
        if (r2 <= r3) goto L_0x002e;
    L_0x002c:
        r0 = r9[r3];
    L_0x002e:
        r2 = r0 instanceof org.mozilla.javascript.Callable;
        if (r2 == 0) goto L_0x0039;
    L_0x0032:
        r0 = (org.mozilla.javascript.Callable) r0;
        r0 = parse(r6, r7, r1, r0);
        goto L_0x000f;
    L_0x0039:
        r0 = parse(r6, r7, r1);
        goto L_0x000f;
    L_0x003e:
        r1 = r9.length;
        switch(r1) {
            case 0: goto L_0x0050;
            case 1: goto L_0x004e;
            case 2: goto L_0x0045;
            default: goto L_0x0042;
        };
    L_0x0042:
        r0 = 2;
        r0 = r9[r0];
    L_0x0045:
        r1 = r9[r3];
    L_0x0047:
        r2 = r9[r2];
    L_0x0049:
        r0 = stringify(r6, r7, r2, r1, r0);
        goto L_0x000f;
    L_0x004e:
        r1 = r0;
        goto L_0x0047;
    L_0x0050:
        r1 = r0;
        r2 = r0;
        goto L_0x0049;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeJSON.execIdCall(org.mozilla.javascript.IdFunctionObject, org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable, org.mozilla.javascript.Scriptable, java.lang.Object[]):java.lang.Object");
    }

    private static Object parse(Context context, Scriptable scriptable, String str) {
        try {
            return new JsonParser(context, scriptable).parseValue(str);
        } catch (ParseException e) {
            throw ScriptRuntime.constructError("SyntaxError", e.getMessage());
        }
    }

    public static Object parse(Context context, Scriptable scriptable, String str, Callable callable) {
        Object parse = parse(context, scriptable, str);
        Scriptable newObject = context.newObject(scriptable);
        newObject.put("", newObject, parse);
        return walk(context, scriptable, callable, newObject, "");
    }

    private static Object walk(Context context, Scriptable scriptable, Callable callable, Scriptable scriptable2, Object obj) {
        Object obj2;
        if (obj instanceof Number) {
            obj2 = scriptable2.get(((Number) obj).intValue(), scriptable2);
        } else {
            obj2 = scriptable2.get((String) obj, scriptable2);
        }
        if (obj2 instanceof Scriptable) {
            Scriptable scriptable3 = (Scriptable) obj2;
            if (scriptable3 instanceof NativeArray) {
                long length = ((NativeArray) scriptable3).getLength();
                for (long j = 0; j < length; j++) {
                    Object walk;
                    if (j > 2147483647L) {
                        String l = Long.toString(j);
                        walk = walk(context, scriptable, callable, scriptable3, l);
                        if (walk == Undefined.instance) {
                            scriptable3.delete(l);
                        } else {
                            scriptable3.put(l, scriptable3, walk);
                        }
                    } else {
                        int i = (int) j;
                        walk = walk(context, scriptable, callable, scriptable3, Integer.valueOf(i));
                        if (walk == Undefined.instance) {
                            scriptable3.delete(i);
                        } else {
                            scriptable3.put(i, scriptable3, walk);
                        }
                    }
                }
            } else {
                for (Object obj3 : scriptable3.getIds()) {
                    Object walk2 = walk(context, scriptable, callable, scriptable3, obj3);
                    if (walk2 == Undefined.instance) {
                        if (obj3 instanceof Number) {
                            scriptable3.delete(((Number) obj3).intValue());
                        } else {
                            scriptable3.delete((String) obj3);
                        }
                    } else if (obj3 instanceof Number) {
                        scriptable3.put(((Number) obj3).intValue(), scriptable3, walk2);
                    } else {
                        scriptable3.put((String) obj3, scriptable3, walk2);
                    }
                }
            }
        }
        return callable.call(context, scriptable, scriptable2, new Object[]{obj, obj2});
    }

    private static String repeat(char c, int i) {
        char[] cArr = new char[i];
        Arrays.fill(cArr, c);
        return new String(cArr);
    }

    public static Object stringify(Context context, Scriptable scriptable, Object obj, Object obj2, Object obj3) {
        int min;
        Object obj4;
        Object valueOf;
        String repeat;
        String str = "";
        String str2 = "";
        List list = null;
        Callable callable = null;
        if (obj2 instanceof Callable) {
            callable = (Callable) obj2;
        } else if (obj2 instanceof NativeArray) {
            list = new LinkedList();
            NativeArray nativeArray = (NativeArray) obj2;
            for (Integer intValue : nativeArray.getIndexIds()) {
                obj4 = nativeArray.get(intValue.intValue(), nativeArray);
                if ((obj4 instanceof String) || (obj4 instanceof Number)) {
                    list.add(obj4);
                } else if ((obj4 instanceof NativeString) || (obj4 instanceof NativeNumber)) {
                    list.add(ScriptRuntime.toString(obj4));
                }
            }
        }
        if (obj3 instanceof NativeNumber) {
            valueOf = Double.valueOf(ScriptRuntime.toNumber(obj3));
        } else if (obj3 instanceof NativeString) {
            valueOf = ScriptRuntime.toString(obj3);
        } else {
            valueOf = obj3;
        }
        if (valueOf instanceof Number) {
            min = Math.min(10, (int) ScriptRuntime.toInteger(valueOf));
            repeat = min > 0 ? repeat(' ', min) : "";
            obj4 = Integer.valueOf(min);
        } else {
            if (valueOf instanceof String) {
                str2 = (String) valueOf;
                if (str2.length() > 10) {
                    repeat = str2.substring(0, 10);
                    obj4 = valueOf;
                }
            }
            repeat = str2;
            obj4 = valueOf;
        }
        StringifyState stringifyState = new StringifyState(context, scriptable, str, repeat, callable, list, obj4);
        Scriptable nativeObject = new NativeObject();
        nativeObject.setParentScope(scriptable);
        nativeObject.setPrototype(ScriptableObject.getObjectPrototype(scriptable));
        nativeObject.defineProperty("", obj, 0);
        return str("", nativeObject, stringifyState);
    }

    private static Object str(Object obj, Scriptable scriptable, StringifyState stringifyState) {
        Object property;
        Object call;
        if (obj instanceof String) {
            property = getProperty(scriptable, (String) obj);
        } else {
            property = getProperty(scriptable, ((Number) obj).intValue());
        }
        if ((property instanceof Scriptable) && (getProperty((Scriptable) property, "toJSON") instanceof Callable)) {
            property = callMethod(stringifyState.cx, (Scriptable) property, "toJSON", new Object[]{obj});
        }
        if (stringifyState.replacer != null) {
            call = stringifyState.replacer.call(stringifyState.cx, stringifyState.scope, scriptable, new Object[]{obj, property});
        } else {
            call = property;
        }
        if (call instanceof NativeNumber) {
            property = Double.valueOf(ScriptRuntime.toNumber(call));
        } else if (call instanceof NativeString) {
            property = ScriptRuntime.toString(call);
        } else if (call instanceof NativeBoolean) {
            property = ((NativeBoolean) call).getDefaultValue(ScriptRuntime.BooleanClass);
        } else {
            property = call;
        }
        if (property == null) {
            return "null";
        }
        if (property.equals(Boolean.TRUE)) {
            return "true";
        }
        if (property.equals(Boolean.FALSE)) {
            return "false";
        }
        if (property instanceof CharSequence) {
            return quote(property.toString());
        }
        if (property instanceof Number) {
            double doubleValue = ((Number) property).doubleValue();
            if (doubleValue != doubleValue || doubleValue == Double.POSITIVE_INFINITY || doubleValue == Double.NEGATIVE_INFINITY) {
                return "null";
            }
            return ScriptRuntime.toString(property);
        } else if (!(property instanceof Scriptable) || (property instanceof Callable)) {
            return Undefined.instance;
        } else {
            if (property instanceof NativeArray) {
                return ja((NativeArray) property, stringifyState);
            }
            return jo((Scriptable) property, stringifyState);
        }
    }

    private static String join(Collection<Object> collection, String str) {
        if (collection == null || collection.isEmpty()) {
            return "";
        }
        Iterator it = collection.iterator();
        if (!it.hasNext()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(it.next().toString());
        while (it.hasNext()) {
            stringBuilder.append(str).append(it.next().toString());
        }
        return stringBuilder.toString();
    }

    private static String jo(Scriptable scriptable, StringifyState stringifyState) {
        if (stringifyState.stack.search(scriptable) != -1) {
            throw ScriptRuntime.typeError0("msg.cyclic.value");
        }
        String str;
        stringifyState.stack.push(scriptable);
        String str2 = stringifyState.indent;
        stringifyState.indent += stringifyState.gap;
        Object[] toArray;
        if (stringifyState.propertyList != null) {
            toArray = stringifyState.propertyList.toArray();
        } else {
            toArray = scriptable.getIds();
        }
        Collection linkedList = new LinkedList();
        for (Object obj : r0) {
            Object str3 = str(obj, scriptable, stringifyState);
            if (str3 != Undefined.instance) {
                String str4 = quote(obj.toString()) + ":";
                if (stringifyState.gap.length() > 0) {
                    str4 = str4 + " ";
                }
                linkedList.add(str4 + str3);
            }
        }
        if (linkedList.isEmpty()) {
            str = "{}";
        } else if (stringifyState.gap.length() == 0) {
            str = '{' + join(linkedList, ",") + '}';
        } else {
            str = "{\n" + stringifyState.indent + join(linkedList, ",\n" + stringifyState.indent) + '\n' + str2 + '}';
        }
        stringifyState.stack.pop();
        stringifyState.indent = str2;
        return str;
    }

    private static String ja(NativeArray nativeArray, StringifyState stringifyState) {
        if (stringifyState.stack.search(nativeArray) != -1) {
            throw ScriptRuntime.typeError0("msg.cyclic.value");
        }
        String str;
        stringifyState.stack.push(nativeArray);
        String str2 = stringifyState.indent;
        stringifyState.indent += stringifyState.gap;
        Collection linkedList = new LinkedList();
        long length = nativeArray.getLength();
        for (long j = 0; j < length; j = 1 + j) {
            Object str3;
            if (j > 2147483647L) {
                str3 = str(Long.toString(j), nativeArray, stringifyState);
            } else {
                str3 = str(Integer.valueOf((int) j), nativeArray, stringifyState);
            }
            if (str3 == Undefined.instance) {
                linkedList.add("null");
            } else {
                linkedList.add(str3);
            }
        }
        if (linkedList.isEmpty()) {
            str = "[]";
        } else if (stringifyState.gap.length() == 0) {
            str = '[' + join(linkedList, ",") + ']';
        } else {
            str = "[\n" + stringifyState.indent + join(linkedList, ",\n" + stringifyState.indent) + '\n' + str2 + ']';
        }
        stringifyState.stack.pop();
        stringifyState.indent = str2;
        return str;
    }

    private static String quote(String str) {
        StringBuilder stringBuilder = new StringBuilder(str.length() + 2);
        stringBuilder.append('\"');
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case '\b':
                    stringBuilder.append("\\b");
                    break;
                case '\t':
                    stringBuilder.append("\\t");
                    break;
                case '\n':
                    stringBuilder.append("\\n");
                    break;
                case '\f':
                    stringBuilder.append("\\f");
                    break;
                case '\r':
                    stringBuilder.append("\\r");
                    break;
                case '\"':
                    stringBuilder.append("\\\"");
                    break;
                case '\\':
                    stringBuilder.append("\\\\");
                    break;
                default:
                    if (charAt >= ' ') {
                        stringBuilder.append(charAt);
                        break;
                    }
                    stringBuilder.append("\\u");
                    stringBuilder.append(String.format("%04x", new Object[]{Integer.valueOf(charAt)}));
                    break;
            }
        }
        stringBuilder.append('\"');
        return stringBuilder.toString();
    }

    protected int findPrototypeId(String str) {
        int i;
        String str2;
        switch (str.length()) {
            case 5:
                i = 2;
                str2 = "parse";
                break;
            case 8:
                i = 1;
                str2 = "toSource";
                break;
            case 9:
                i = 3;
                str2 = "stringify";
                break;
            default:
                str2 = null;
                i = 0;
                break;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }
}
