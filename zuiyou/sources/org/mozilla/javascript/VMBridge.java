package org.mozilla.javascript;

import java.lang.reflect.Member;
import java.util.Iterator;

public abstract class VMBridge {
    static final VMBridge instance = makeInstance();

    protected abstract Context getContext(Object obj);

    protected abstract ClassLoader getCurrentThreadClassLoader();

    protected abstract Object getThreadContextHelper();

    protected abstract boolean isVarArgs(Member member);

    protected abstract void setContext(Object obj, Context context);

    protected abstract boolean tryToMakeAccessible(Object obj);

    private static VMBridge makeInstance() {
        String[] strArr = new String[]{"org.mozilla.javascript.VMBridge_custom", "org.mozilla.javascript.jdk15.VMBridge_jdk15", "org.mozilla.javascript.jdk13.VMBridge_jdk13", "org.mozilla.javascript.jdk11.VMBridge_jdk11"};
        for (int i = 0; i != strArr.length; i++) {
            Class classOrNull = Kit.classOrNull(strArr[i]);
            if (classOrNull != null) {
                VMBridge vMBridge = (VMBridge) Kit.newInstanceOrNull(classOrNull);
                if (vMBridge != null) {
                    return vMBridge;
                }
            }
        }
        throw new IllegalStateException("Failed to create VMBridge instance");
    }

    protected Object getInterfaceProxyHelper(ContextFactory contextFactory, Class<?>[] clsArr) {
        throw Context.reportRuntimeError("VMBridge.getInterfaceProxyHelper is not supported");
    }

    protected Object newInterfaceProxy(Object obj, ContextFactory contextFactory, InterfaceAdapter interfaceAdapter, Object obj2, Scriptable scriptable) {
        throw Context.reportRuntimeError("VMBridge.newInterfaceProxy is not supported");
    }

    public Iterator<?> getJavaIterator(Context context, Scriptable scriptable, Object obj) {
        if (!(obj instanceof Wrapper)) {
            return null;
        }
        Object unwrap = ((Wrapper) obj).unwrap();
        if (unwrap instanceof Iterator) {
            return (Iterator) unwrap;
        }
        return null;
    }
}
