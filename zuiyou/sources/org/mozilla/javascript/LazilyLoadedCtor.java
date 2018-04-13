package org.mozilla.javascript;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;

public final class LazilyLoadedCtor implements Serializable {
    private static final int STATE_BEFORE_INIT = 0;
    private static final int STATE_INITIALIZING = 1;
    private static final int STATE_WITH_VALUE = 2;
    private static final long serialVersionUID = 1;
    private final String className;
    private Object initializedValue;
    private final boolean privileged;
    private final String propertyName;
    private final ScriptableObject scope;
    private final boolean sealed;
    private int state;

    public LazilyLoadedCtor(ScriptableObject scriptableObject, String str, String str2, boolean z) {
        this(scriptableObject, str, str2, z, false);
    }

    LazilyLoadedCtor(ScriptableObject scriptableObject, String str, String str2, boolean z, boolean z2) {
        this.scope = scriptableObject;
        this.propertyName = str;
        this.className = str2;
        this.sealed = z;
        this.privileged = z2;
        this.state = 0;
        scriptableObject.addLazilyInitializedValue(str, 0, this, 2);
    }

    void init() {
        synchronized (this) {
            if (this.state == 1) {
                throw new IllegalStateException("Recursive initialization for " + this.propertyName);
            }
            if (this.state == 0) {
                this.state = 1;
                Object obj = Scriptable.NOT_FOUND;
                try {
                    obj = buildValue();
                } finally {
                    this.initializedValue = obj;
                    this.state = 2;
                }
            }
        }
    }

    Object getValue() {
        if (this.state == 2) {
            return this.initializedValue;
        }
        throw new IllegalStateException(this.propertyName);
    }

    private Object buildValue() {
        if (this.privileged) {
            return AccessController.doPrivileged(new PrivilegedAction<Object>() {
                public Object run() {
                    return LazilyLoadedCtor.this.buildValue0();
                }
            });
        }
        return buildValue0();
    }

    private Object buildValue0() {
        Class cast = cast(Kit.classOrNull(this.className));
        if (cast != null) {
            try {
                BaseFunction buildClassCtor = ScriptableObject.buildClassCtor(this.scope, cast, this.sealed, false);
                if (buildClassCtor != null) {
                    return buildClassCtor;
                }
                Object obj = this.scope.get(this.propertyName, this.scope);
                if (obj != Scriptable.NOT_FOUND) {
                    return obj;
                }
            } catch (InvocationTargetException e) {
                r0 = e.getTargetException();
                Throwable targetException;
                if (targetException instanceof RuntimeException) {
                    throw ((RuntimeException) targetException);
                }
            } catch (RhinoException e2) {
            } catch (InstantiationException e3) {
            } catch (IllegalAccessException e4) {
            } catch (SecurityException e5) {
            }
        }
        return Scriptable.NOT_FOUND;
    }

    private Class<? extends Scriptable> cast(Class<?> cls) {
        return cls;
    }
}
