package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Set;

public class NativeJavaPackage extends ScriptableObject {
    static final long serialVersionUID = 7445054382212031523L;
    private transient ClassLoader classLoader;
    private Set<String> negativeCache;
    private String packageName;

    NativeJavaPackage(boolean z, String str, ClassLoader classLoader) {
        this.negativeCache = null;
        this.packageName = str;
        this.classLoader = classLoader;
    }

    @Deprecated
    public NativeJavaPackage(String str, ClassLoader classLoader) {
        this(false, str, classLoader);
    }

    @Deprecated
    public NativeJavaPackage(String str) {
        this(false, str, Context.getCurrentContext().getApplicationClassLoader());
    }

    public String getClassName() {
        return "JavaPackage";
    }

    public boolean has(String str, Scriptable scriptable) {
        return true;
    }

    public boolean has(int i, Scriptable scriptable) {
        return false;
    }

    public void put(String str, Scriptable scriptable, Object obj) {
    }

    public void put(int i, Scriptable scriptable, Object obj) {
        throw Context.reportRuntimeError0("msg.pkg.int");
    }

    public Object get(String str, Scriptable scriptable) {
        return getPkgProperty(str, scriptable, true);
    }

    public Object get(int i, Scriptable scriptable) {
        return NOT_FOUND;
    }

    NativeJavaPackage forcePackage(String str, Scriptable scriptable) {
        Object obj = super.get(str, (Scriptable) this);
        if (obj != null && (obj instanceof NativeJavaPackage)) {
            return (NativeJavaPackage) obj;
        }
        NativeJavaPackage nativeJavaPackage = new NativeJavaPackage(true, this.packageName.length() == 0 ? str : this.packageName + "." + str, this.classLoader);
        ScriptRuntime.setObjectProtoAndParent(nativeJavaPackage, scriptable);
        super.put(str, (Scriptable) this, (Object) nativeJavaPackage);
        return nativeJavaPackage;
    }

    synchronized Object getPkgProperty(String str, Scriptable scriptable, boolean z) {
        Object obj;
        obj = super.get(str, scriptable);
        if (obj == NOT_FOUND) {
            if (this.negativeCache == null || !this.negativeCache.contains(str)) {
                String str2 = this.packageName.length() == 0 ? str : this.packageName + '.' + str;
                Context context = Context.getContext();
                ClassShutter classShutter = context.getClassShutter();
                if (classShutter == null || classShutter.visibleToScripts(str2)) {
                    Class classOrNull;
                    if (this.classLoader != null) {
                        classOrNull = Kit.classOrNull(this.classLoader, str2);
                    } else {
                        classOrNull = Kit.classOrNull(str2);
                    }
                    if (classOrNull != null) {
                        Scriptable wrapJavaClass = context.getWrapFactory().wrapJavaClass(context, ScriptableObject.getTopLevelScope(this), classOrNull);
                        wrapJavaClass.setPrototype(getPrototype());
                        obj = wrapJavaClass;
                        if (obj == null) {
                            if (z) {
                                if (this.negativeCache == null) {
                                    this.negativeCache = new HashSet();
                                }
                                this.negativeCache.add(str);
                            } else {
                                obj = new NativeJavaPackage(true, str2, this.classLoader);
                                ScriptRuntime.setObjectProtoAndParent(obj, getParentScope());
                            }
                        }
                        if (obj != null) {
                            super.put(str, scriptable, obj);
                        }
                    }
                }
                obj = null;
                if (obj == null) {
                    if (z) {
                        if (this.negativeCache == null) {
                            this.negativeCache = new HashSet();
                        }
                        this.negativeCache.add(str);
                    } else {
                        obj = new NativeJavaPackage(true, str2, this.classLoader);
                        ScriptRuntime.setObjectProtoAndParent(obj, getParentScope());
                    }
                }
                if (obj != null) {
                    super.put(str, scriptable, obj);
                }
            } else {
                obj = null;
            }
        }
        return obj;
    }

    public Object getDefaultValue(Class<?> cls) {
        return toString();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.classLoader = Context.getCurrentContext().getApplicationClassLoader();
    }

    public String toString() {
        return "[JavaPackage " + this.packageName + "]";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NativeJavaPackage)) {
            return false;
        }
        NativeJavaPackage nativeJavaPackage = (NativeJavaPackage) obj;
        if (this.packageName.equals(nativeJavaPackage.packageName) && this.classLoader == nativeJavaPackage.classLoader) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.classLoader == null ? 0 : this.classLoader.hashCode()) ^ this.packageName.hashCode();
    }
}
