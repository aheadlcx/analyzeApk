package org.mozilla.javascript;

public class DefiningClassLoader extends ClassLoader implements GeneratedClassLoader {
    private final ClassLoader parentLoader;

    public DefiningClassLoader() {
        this.parentLoader = getClass().getClassLoader();
    }

    public DefiningClassLoader(ClassLoader classLoader) {
        this.parentLoader = classLoader;
    }

    public Class<?> defineClass(String str, byte[] bArr) {
        return super.defineClass(str, bArr, 0, bArr.length, SecurityUtilities.getProtectionDomain(getClass()));
    }

    public void linkClass(Class<?> cls) {
        resolveClass(cls);
    }

    public Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
        Class<?> findLoadedClass = findLoadedClass(str);
        if (findLoadedClass == null) {
            if (this.parentLoader != null) {
                findLoadedClass = this.parentLoader.loadClass(str);
            } else {
                findLoadedClass = findSystemClass(str);
            }
        }
        if (z) {
            resolveClass(findLoadedClass);
        }
        return findLoadedClass;
    }
}
