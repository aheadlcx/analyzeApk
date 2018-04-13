package org.mozilla.javascript;

import java.security.AccessController;
import org.mozilla.javascript.xml.XMLLib.Factory;

public class ContextFactory {
    private static ContextFactory global = new ContextFactory();
    private static volatile boolean hasCustomGlobal;
    private ClassLoader applicationClassLoader;
    private boolean disabledListening;
    private volatile Object listeners;
    private final Object listenersLock = new Object();
    private volatile boolean sealed;

    public static ContextFactory getGlobal() {
        return global;
    }

    public static boolean hasExplicitGlobal() {
        return hasCustomGlobal;
    }

    public static synchronized void initGlobal(ContextFactory contextFactory) {
        synchronized (ContextFactory.class) {
            if (contextFactory == null) {
                throw new IllegalArgumentException();
            } else if (hasCustomGlobal) {
                throw new IllegalStateException();
            } else {
                hasCustomGlobal = true;
                global = contextFactory;
            }
        }
    }

    public static synchronized ContextFactory$GlobalSetter getGlobalSetter() {
        ContextFactory$GlobalSetter contextFactory$1GlobalSetterImpl;
        synchronized (ContextFactory.class) {
            if (hasCustomGlobal) {
                throw new IllegalStateException();
            }
            hasCustomGlobal = true;
            contextFactory$1GlobalSetterImpl = new ContextFactory$1GlobalSetterImpl();
        }
        return contextFactory$1GlobalSetterImpl;
    }

    protected Context makeContext() {
        return new Context(this);
    }

    protected boolean hasFeature(Context context, int i) {
        boolean z = true;
        int languageVersion;
        switch (i) {
            case 1:
                languageVersion = context.getLanguageVersion();
                if (languageVersion == 100 || languageVersion == 110 || languageVersion == 120) {
                    return true;
                }
                return false;
            case 2:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                return false;
            case 3:
                return true;
            case 4:
                if (context.getLanguageVersion() != 120) {
                    z = false;
                }
                return z;
            case 5:
                return true;
            case 6:
                languageVersion = context.getLanguageVersion();
                if (languageVersion == 0 || languageVersion >= 160) {
                    return true;
                }
                return false;
            case 14:
                return true;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
    }

    private boolean isDom3Present() {
        Class classOrNull = Kit.classOrNull("org.w3c.dom.Node");
        if (classOrNull == null) {
            return false;
        }
        try {
            classOrNull.getMethod("getUserData", new Class[]{String.class});
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    protected Factory getE4xImplementationFactory() {
        if (isDom3Present()) {
            return Factory.create("org.mozilla.javascript.xmlimpl.XMLLibImpl");
        }
        return null;
    }

    protected GeneratedClassLoader createClassLoader(ClassLoader classLoader) {
        return (GeneratedClassLoader) AccessController.doPrivileged(new ContextFactory$1(this, classLoader));
    }

    public final ClassLoader getApplicationClassLoader() {
        return this.applicationClassLoader;
    }

    public final void initApplicationClassLoader(ClassLoader classLoader) {
        if (classLoader == null) {
            throw new IllegalArgumentException("loader is null");
        } else if (!Kit.testIfCanLoadRhinoClasses(classLoader)) {
            throw new IllegalArgumentException("Loader can not resolve Rhino classes");
        } else if (this.applicationClassLoader != null) {
            throw new IllegalStateException("applicationClassLoader can only be set once");
        } else {
            checkNotSealed();
            this.applicationClassLoader = classLoader;
        }
    }

    protected Object doTopCall(Callable callable, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Object call = callable.call(context, scriptable, scriptable2, objArr);
        return call instanceof ConsString ? call.toString() : call;
    }

    protected void observeInstructionCount(Context context, int i) {
    }

    protected void onContextCreated(Context context) {
        Object obj = this.listeners;
        int i = 0;
        while (true) {
            ContextFactory$Listener contextFactory$Listener = (ContextFactory$Listener) Kit.getListener(obj, i);
            if (contextFactory$Listener != null) {
                contextFactory$Listener.contextCreated(context);
                i++;
            } else {
                return;
            }
        }
    }

    protected void onContextReleased(Context context) {
        Object obj = this.listeners;
        int i = 0;
        while (true) {
            ContextFactory$Listener contextFactory$Listener = (ContextFactory$Listener) Kit.getListener(obj, i);
            if (contextFactory$Listener != null) {
                contextFactory$Listener.contextReleased(context);
                i++;
            } else {
                return;
            }
        }
    }

    public final void addListener(ContextFactory$Listener contextFactory$Listener) {
        checkNotSealed();
        synchronized (this.listenersLock) {
            if (this.disabledListening) {
                throw new IllegalStateException();
            }
            this.listeners = Kit.addListener(this.listeners, contextFactory$Listener);
        }
    }

    public final void removeListener(ContextFactory$Listener contextFactory$Listener) {
        checkNotSealed();
        synchronized (this.listenersLock) {
            if (this.disabledListening) {
                throw new IllegalStateException();
            }
            this.listeners = Kit.removeListener(this.listeners, contextFactory$Listener);
        }
    }

    final void disableContextListening() {
        checkNotSealed();
        synchronized (this.listenersLock) {
            this.disabledListening = true;
            this.listeners = null;
        }
    }

    public final boolean isSealed() {
        return this.sealed;
    }

    public final void seal() {
        checkNotSealed();
        this.sealed = true;
    }

    protected final void checkNotSealed() {
        if (this.sealed) {
            throw new IllegalStateException();
        }
    }

    public final Object call(ContextAction contextAction) {
        return Context.call(this, contextAction);
    }

    public Context enterContext() {
        return enterContext(null);
    }

    @Deprecated
    public final Context enter() {
        return enterContext(null);
    }

    @Deprecated
    public final void exit() {
        Context.exit();
    }

    public final Context enterContext(Context context) {
        return Context.enter(context, this);
    }
}
