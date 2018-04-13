package org.mozilla.javascript;

import java.util.EnumMap;

public class TopLevel extends IdScriptableObject {
    static final /* synthetic */ boolean $assertionsDisabled = (!TopLevel.class.desiredAssertionStatus());
    static final long serialVersionUID = -4648046356662472260L;
    private EnumMap<Builtins, BaseFunction> ctors;
    private EnumMap<NativeErrors, BaseFunction> errors;

    public enum Builtins {
        Object,
        Array,
        Function,
        String,
        Number,
        Boolean,
        RegExp,
        Error
    }

    enum NativeErrors {
        Error,
        EvalError,
        RangeError,
        ReferenceError,
        SyntaxError,
        TypeError,
        URIError,
        InternalError,
        JavaException
    }

    public String getClassName() {
        return "global";
    }

    public void cacheBuiltins() {
        int i = 0;
        this.ctors = new EnumMap(Builtins.class);
        for (Enum enumR : Builtins.values()) {
            Object property = ScriptableObject.getProperty(this, enumR.name());
            if (property instanceof BaseFunction) {
                this.ctors.put(enumR, (BaseFunction) property);
            }
        }
        this.errors = new EnumMap(NativeErrors.class);
        NativeErrors[] values = NativeErrors.values();
        int length = values.length;
        while (i < length) {
            Enum enumR2 = values[i];
            property = ScriptableObject.getProperty(this, enumR2.name());
            if (property instanceof BaseFunction) {
                this.errors.put(enumR2, (BaseFunction) property);
            }
            i++;
        }
    }

    public static Function getBuiltinCtor(Context context, Scriptable scriptable, Builtins builtins) {
        if ($assertionsDisabled || scriptable.getParentScope() == null) {
            if (scriptable instanceof TopLevel) {
                Function builtinCtor = ((TopLevel) scriptable).getBuiltinCtor(builtins);
                if (builtinCtor != null) {
                    return builtinCtor;
                }
            }
            return ScriptRuntime.getExistingCtor(context, scriptable, builtins.name());
        }
        throw new AssertionError();
    }

    static Function getNativeErrorCtor(Context context, Scriptable scriptable, NativeErrors nativeErrors) {
        if ($assertionsDisabled || scriptable.getParentScope() == null) {
            if (scriptable instanceof TopLevel) {
                Function nativeErrorCtor = ((TopLevel) scriptable).getNativeErrorCtor(nativeErrors);
                if (nativeErrorCtor != null) {
                    return nativeErrorCtor;
                }
            }
            return ScriptRuntime.getExistingCtor(context, scriptable, nativeErrors.name());
        }
        throw new AssertionError();
    }

    public static Scriptable getBuiltinPrototype(Scriptable scriptable, Builtins builtins) {
        if ($assertionsDisabled || scriptable.getParentScope() == null) {
            if (scriptable instanceof TopLevel) {
                Scriptable builtinPrototype = ((TopLevel) scriptable).getBuiltinPrototype(builtins);
                if (builtinPrototype != null) {
                    return builtinPrototype;
                }
            }
            return ScriptableObject.getClassPrototype(scriptable, builtins.name());
        }
        throw new AssertionError();
    }

    public BaseFunction getBuiltinCtor(Builtins builtins) {
        return this.ctors != null ? (BaseFunction) this.ctors.get(builtins) : null;
    }

    BaseFunction getNativeErrorCtor(NativeErrors nativeErrors) {
        return this.errors != null ? (BaseFunction) this.errors.get(nativeErrors) : null;
    }

    public Scriptable getBuiltinPrototype(Builtins builtins) {
        Object prototypeProperty;
        BaseFunction builtinCtor = getBuiltinCtor(builtins);
        if (builtinCtor != null) {
            prototypeProperty = builtinCtor.getPrototypeProperty();
        } else {
            prototypeProperty = null;
        }
        if (prototypeProperty instanceof Scriptable) {
            return (Scriptable) prototypeProperty;
        }
        return null;
    }
}
