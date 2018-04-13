package org.mozilla.javascript;

public class ImporterTopLevel extends TopLevel {
    private static final Object IMPORTER_TAG = "Importer";
    private static final int Id_constructor = 1;
    private static final int Id_importClass = 2;
    private static final int Id_importPackage = 3;
    private static final int MAX_PROTOTYPE_ID = 3;
    static final long serialVersionUID = -9095380847465315412L;
    private ObjArray importedPackages;
    private boolean topScopeFlag;

    public ImporterTopLevel() {
        this.importedPackages = new ObjArray();
    }

    public ImporterTopLevel(Context context) {
        this(context, false);
    }

    public ImporterTopLevel(Context context, boolean z) {
        this.importedPackages = new ObjArray();
        initStandardObjects(context, z);
    }

    public String getClassName() {
        return this.topScopeFlag ? "global" : "JavaImporter";
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        new ImporterTopLevel().exportAsJSClass(3, scriptable, z);
    }

    public void initStandardObjects(Context context, boolean z) {
        context.initStandardObjects(this, z);
        this.topScopeFlag = true;
        IdFunctionObject exportAsJSClass = exportAsJSClass(3, this, false);
        if (z) {
            exportAsJSClass.sealObject();
        }
        delete("constructor");
    }

    public boolean has(String str, Scriptable scriptable) {
        return super.has(str, scriptable) || getPackageProperty(str, scriptable) != NOT_FOUND;
    }

    public Object get(String str, Scriptable scriptable) {
        Object obj = super.get(str, scriptable);
        if (obj != NOT_FOUND) {
            return obj;
        }
        return getPackageProperty(str, scriptable);
    }

    private Object getPackageProperty(String str, Scriptable scriptable) {
        Object obj = NOT_FOUND;
        synchronized (this.importedPackages) {
            Object[] toArray = this.importedPackages.toArray();
        }
        Object obj2 = obj;
        for (Object obj3 : toArray) {
            obj3 = ((NativeJavaPackage) obj3).getPkgProperty(str, scriptable, false);
            if (!(obj3 == null || (obj3 instanceof NativeJavaPackage))) {
                if (obj2 == NOT_FOUND) {
                    obj2 = obj3;
                } else {
                    throw Context.reportRuntimeError2("msg.ambig.import", obj2.toString(), obj3.toString());
                }
            }
        }
        return obj2;
    }

    @Deprecated
    public void importPackage(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        js_importPackage(objArr);
    }

    private Object js_construct(Scriptable scriptable, Object[] objArr) {
        ImporterTopLevel importerTopLevel = new ImporterTopLevel();
        for (int i = 0; i != objArr.length; i++) {
            Object obj = objArr[i];
            if (obj instanceof NativeJavaClass) {
                importerTopLevel.importClass((NativeJavaClass) obj);
            } else if (obj instanceof NativeJavaPackage) {
                importerTopLevel.importPackage((NativeJavaPackage) obj);
            } else {
                throw Context.reportRuntimeError1("msg.not.class.not.pkg", Context.toString(obj));
            }
        }
        importerTopLevel.setParentScope(scriptable);
        importerTopLevel.setPrototype(this);
        return importerTopLevel;
    }

    private Object js_importClass(Object[] objArr) {
        int i = 0;
        while (i != objArr.length) {
            Object obj = objArr[i];
            if (obj instanceof NativeJavaClass) {
                importClass((NativeJavaClass) obj);
                i++;
            } else {
                throw Context.reportRuntimeError1("msg.not.class", Context.toString(obj));
            }
        }
        return Undefined.instance;
    }

    private Object js_importPackage(Object[] objArr) {
        int i = 0;
        while (i != objArr.length) {
            Object obj = objArr[i];
            if (obj instanceof NativeJavaPackage) {
                importPackage((NativeJavaPackage) obj);
                i++;
            } else {
                throw Context.reportRuntimeError1("msg.not.pkg", Context.toString(obj));
            }
        }
        return Undefined.instance;
    }

    private void importPackage(NativeJavaPackage nativeJavaPackage) {
        if (nativeJavaPackage != null) {
            synchronized (this.importedPackages) {
                for (int i = 0; i != this.importedPackages.size(); i++) {
                    if (nativeJavaPackage.equals(this.importedPackages.get(i))) {
                        return;
                    }
                }
                this.importedPackages.add(nativeJavaPackage);
            }
        }
    }

    private void importClass(NativeJavaClass nativeJavaClass) {
        String name = nativeJavaClass.getClassObject().getName();
        name = name.substring(name.lastIndexOf(46) + 1);
        NativeJavaClass nativeJavaClass2 = get(name, this);
        if (nativeJavaClass2 == NOT_FOUND || nativeJavaClass2 == nativeJavaClass) {
            put(name, this, nativeJavaClass);
            return;
        }
        throw Context.reportRuntimeError1("msg.prop.defined", name);
    }

    protected void initPrototypeId(int i) {
        String str;
        int i2 = 1;
        switch (i) {
            case 1:
                i2 = 0;
                str = "constructor";
                break;
            case 2:
                str = "importClass";
                break;
            case 3:
                str = "importPackage";
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(IMPORTER_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(IMPORTER_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        switch (methodId) {
            case 1:
                return js_construct(scriptable, objArr);
            case 2:
                return realThis(scriptable2, idFunctionObject).js_importClass(objArr);
            case 3:
                return realThis(scriptable2, idFunctionObject).js_importPackage(objArr);
            default:
                throw new IllegalArgumentException(String.valueOf(methodId));
        }
    }

    private ImporterTopLevel realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        if (this.topScopeFlag) {
            return this;
        }
        if (scriptable instanceof ImporterTopLevel) {
            return (ImporterTopLevel) scriptable;
        }
        throw incompatibleCallError(idFunctionObject);
    }

    protected int findPrototypeId(String str) {
        int i;
        String str2;
        int length = str.length();
        if (length == 11) {
            char charAt = str.charAt(0);
            if (charAt == 'c') {
                i = 1;
                str2 = "constructor";
            } else {
                if (charAt == 'i') {
                    i = 2;
                    str2 = "importClass";
                }
                str2 = null;
                i = 0;
            }
        } else {
            if (length == 13) {
                i = 3;
                str2 = "importPackage";
            }
            str2 = null;
            i = 0;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }
}
