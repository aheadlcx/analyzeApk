package org.mozilla.javascript;

public final class NativeGenerator extends IdScriptableObject {
    public static final int GENERATOR_CLOSE = 2;
    public static final int GENERATOR_SEND = 0;
    private static final Object GENERATOR_TAG = "Generator";
    public static final int GENERATOR_THROW = 1;
    private static final int Id___iterator__ = 5;
    private static final int Id_close = 1;
    private static final int Id_next = 2;
    private static final int Id_send = 3;
    private static final int Id_throw = 4;
    private static final int MAX_PROTOTYPE_ID = 5;
    private static final long serialVersionUID = 1645892441041347273L;
    private boolean firstTime = true;
    private NativeFunction function;
    private int lineNumber;
    private String lineSource;
    private boolean locked;
    private Object savedState;

    private static class CloseGeneratorAction implements ContextAction {
        private NativeGenerator generator;

        CloseGeneratorAction(NativeGenerator nativeGenerator) {
            this.generator = nativeGenerator;
        }

        public Object run(Context context) {
            return ScriptRuntime.doTopCall(new Callable() {
                public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
                    return ((NativeGenerator) scriptable2).resume(context, scriptable, 2, new GeneratorClosedException());
                }
            }, context, ScriptableObject.getTopLevelScope(this.generator), this.generator, null);
        }
    }

    public static class GeneratorClosedException extends RuntimeException {
        private static final long serialVersionUID = 2561315658662379681L;
    }

    static NativeGenerator init(ScriptableObject scriptableObject, boolean z) {
        NativeGenerator nativeGenerator = new NativeGenerator();
        if (scriptableObject != null) {
            nativeGenerator.setParentScope(scriptableObject);
            nativeGenerator.setPrototype(getObjectPrototype(scriptableObject));
        }
        nativeGenerator.activatePrototypeMap(5);
        if (z) {
            nativeGenerator.sealObject();
        }
        if (scriptableObject != null) {
            scriptableObject.associateValue(GENERATOR_TAG, nativeGenerator);
        }
        return nativeGenerator;
    }

    private NativeGenerator() {
    }

    public NativeGenerator(Scriptable scriptable, NativeFunction nativeFunction, Object obj) {
        this.function = nativeFunction;
        this.savedState = obj;
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        setParentScope(topLevelScope);
        setPrototype((NativeGenerator) ScriptableObject.getTopScopeValue(topLevelScope, GENERATOR_TAG));
    }

    public String getClassName() {
        return "Generator";
    }

    protected void initPrototypeId(int i) {
        String str;
        int i2 = 1;
        switch (i) {
            case 1:
                str = "close";
                break;
            case 2:
                str = "next";
                break;
            case 3:
                str = "send";
                i2 = 0;
                break;
            case 4:
                str = "throw";
                i2 = 0;
                break;
            case 5:
                str = NativeIterator.ITERATOR_PROPERTY_NAME;
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(GENERATOR_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(GENERATOR_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        if (scriptable2 instanceof NativeGenerator) {
            NativeGenerator nativeGenerator = (NativeGenerator) scriptable2;
            switch (methodId) {
                case 1:
                    return nativeGenerator.resume(context, scriptable, 2, new GeneratorClosedException());
                case 2:
                    nativeGenerator.firstTime = false;
                    return nativeGenerator.resume(context, scriptable, 0, Undefined.instance);
                case 3:
                    Object obj = objArr.length > 0 ? objArr[0] : Undefined.instance;
                    if (!nativeGenerator.firstTime || obj.equals(Undefined.instance)) {
                        return nativeGenerator.resume(context, scriptable, 0, obj);
                    }
                    throw ScriptRuntime.typeError0("msg.send.newborn");
                case 4:
                    return nativeGenerator.resume(context, scriptable, 1, objArr.length > 0 ? objArr[0] : Undefined.instance);
                case 5:
                    return scriptable2;
                default:
                    throw new IllegalArgumentException(String.valueOf(methodId));
            }
        }
        throw IdScriptableObject.incompatibleCallError(idFunctionObject);
    }

    private Object resume(Context context, Scriptable scriptable, int i, Object obj) {
        if (this.savedState != null) {
            Object resumeGenerator;
            try {
                synchronized (this) {
                    if (this.locked) {
                        throw ScriptRuntime.typeError0("msg.already.exec.gen");
                    }
                    this.locked = true;
                }
                resumeGenerator = this.function.resumeGenerator(context, scriptable, i, this.savedState, obj);
                synchronized (this) {
                    this.locked = false;
                }
                if (i != 2) {
                    return resumeGenerator;
                }
                this.savedState = null;
                return resumeGenerator;
            } catch (GeneratorClosedException e) {
                try {
                    resumeGenerator = Undefined.instance;
                    synchronized (this) {
                        this.locked = false;
                        if (i != 2) {
                            return resumeGenerator;
                        }
                        this.savedState = null;
                        return resumeGenerator;
                    }
                } catch (Throwable th) {
                    synchronized (this) {
                        this.locked = false;
                        if (i == 2) {
                            this.savedState = null;
                        }
                    }
                }
            } catch (RhinoException e2) {
                this.lineNumber = e2.lineNumber();
                this.lineSource = e2.lineSource();
                this.savedState = null;
                throw e2;
            }
        } else if (i == 2) {
            return Undefined.instance;
        } else {
            if (i != 1) {
                obj = NativeIterator.getStopIterationObject(scriptable);
            }
            throw new JavaScriptException(obj, this.lineSource, this.lineNumber);
        }
    }

    protected int findPrototypeId(String str) {
        int i;
        String str2;
        int length = str.length();
        if (length == 4) {
            char charAt = str.charAt(0);
            if (charAt == 'n') {
                i = 2;
                str2 = "next";
            } else {
                if (charAt == 's') {
                    i = 3;
                    str2 = "send";
                }
                str2 = null;
                i = 0;
            }
        } else if (length == 5) {
            char charAt2 = str.charAt(0);
            if (charAt2 == 'c') {
                i = 1;
                str2 = "close";
            } else {
                if (charAt2 == 't') {
                    i = 4;
                    str2 = "throw";
                }
                str2 = null;
                i = 0;
            }
        } else {
            if (length == 12) {
                str2 = NativeIterator.ITERATOR_PROPERTY_NAME;
                i = 5;
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
