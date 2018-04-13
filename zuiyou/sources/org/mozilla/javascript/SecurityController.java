package org.mozilla.javascript;

public abstract class SecurityController {
    private static SecurityController global;

    public abstract GeneratedClassLoader createClassLoader(ClassLoader classLoader, Object obj);

    public abstract Object getDynamicSecurityDomain(Object obj);

    static SecurityController global() {
        return global;
    }

    public static boolean hasGlobal() {
        return global != null;
    }

    public static void initGlobal(SecurityController securityController) {
        if (securityController == null) {
            throw new IllegalArgumentException();
        } else if (global != null) {
            throw new SecurityException("Cannot overwrite already installed global SecurityController");
        } else {
            global = securityController;
        }
    }

    public static GeneratedClassLoader createLoader(ClassLoader classLoader, Object obj) {
        Context context = Context.getContext();
        if (classLoader == null) {
            classLoader = context.getApplicationClassLoader();
        }
        SecurityController securityController = context.getSecurityController();
        if (securityController == null) {
            return context.createClassLoader(classLoader);
        }
        return securityController.createClassLoader(classLoader, securityController.getDynamicSecurityDomain(obj));
    }

    public static Class<?> getStaticSecurityDomainClass() {
        SecurityController securityController = Context.getContext().getSecurityController();
        return securityController == null ? null : securityController.getStaticSecurityDomainClassInternal();
    }

    public Class<?> getStaticSecurityDomainClassInternal() {
        return null;
    }

    public Object callWithDomain(Object obj, Context context, Callable callable, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return execWithDomain(context, scriptable, new SecurityController$1(this, callable, scriptable2, objArr), obj);
    }

    @Deprecated
    public Object execWithDomain(Context context, Scriptable scriptable, Script script, Object obj) {
        throw new IllegalStateException("callWithDomain should be overridden");
    }
}
