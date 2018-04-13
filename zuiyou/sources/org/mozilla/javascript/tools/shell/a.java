package org.mozilla.javascript.tools.shell;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

class a implements InvocationHandler {
    private Method a;
    private Scriptable b;

    a(Class<?> cls, Scriptable scriptable) throws NoSuchMethodException {
        this.b = scriptable;
        this.a = cls.getMethod("complete", new Class[]{String.class, Integer.TYPE, List.class});
    }

    public Object invoke(Object obj, Method method, Object[] objArr) {
        if (method.equals(this.a)) {
            return Integer.valueOf(a((String) objArr[0], ((Integer) objArr[1]).intValue(), (List) objArr[2]));
        }
        throw new NoSuchMethodError(method.toString());
    }

    public int a(String str, int i, List<String> list) {
        int i2 = 0;
        int i3 = i - 1;
        while (i3 >= 0) {
            char charAt = str.charAt(i3);
            if (!Character.isJavaIdentifierPart(charAt) && charAt != '.') {
                break;
            }
            i3--;
        }
        String[] split = str.substring(i3 + 1, i).split("\\.", -1);
        Scriptable scriptable = this.b;
        int i4 = 0;
        while (i4 < split.length - 1) {
            Object obj = scriptable.get(split[i4], this.b);
            if (!(obj instanceof Scriptable)) {
                return str.length();
            }
            i4++;
            scriptable = (Scriptable) obj;
        }
        Object[] allIds = scriptable instanceof ScriptableObject ? ((ScriptableObject) scriptable).getAllIds() : scriptable.getIds();
        String str2 = split[split.length - 1];
        while (i2 < allIds.length) {
            if (allIds[i2] instanceof String) {
                obj = (String) allIds[i2];
                if (obj.startsWith(str2)) {
                    if (scriptable.get((String) obj, scriptable) instanceof Function) {
                        obj = obj + "(";
                    }
                    list.add(obj);
                }
            }
            i2++;
        }
        return str.length() - str2.length();
    }
}
