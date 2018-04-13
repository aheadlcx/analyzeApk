package org.mozilla.javascript;

import org.mozilla.javascript.TopLevel.Builtins;

final class ScriptableObject$GetterSlot extends ScriptableObject$Slot {
    static final long serialVersionUID = -4900574849788797588L;
    Object getter;
    Object setter;

    ScriptableObject$GetterSlot(String str, int i, int i2) {
        super(str, i, i2);
    }

    ScriptableObject getPropertyDescriptor(Context context, Scriptable scriptable) {
        boolean z;
        boolean z2 = true;
        int attributes = getAttributes();
        ScriptableObject nativeObject = new NativeObject();
        ScriptRuntime.setBuiltinProtoAndParent(nativeObject, scriptable, Builtins.Object);
        String str = "enumerable";
        if ((attributes & 2) == 0) {
            z = true;
        } else {
            z = false;
        }
        nativeObject.defineProperty(str, Boolean.valueOf(z), 0);
        String str2 = "configurable";
        if ((attributes & 4) != 0) {
            z2 = false;
        }
        nativeObject.defineProperty(str2, Boolean.valueOf(z2), 0);
        if (this.getter != null) {
            nativeObject.defineProperty("get", this.getter, 0);
        }
        if (this.setter != null) {
            nativeObject.defineProperty("set", this.setter, 0);
        }
        return nativeObject;
    }

    boolean setValue(Object obj, Scriptable scriptable, Scriptable scriptable2) {
        if (this.setter != null) {
            Context context = Context.getContext();
            if (this.setter instanceof MemberBox) {
                Object[] objArr;
                MemberBox memberBox = (MemberBox) this.setter;
                Class[] clsArr = memberBox.argTypes;
                Object convertArg = FunctionObject.convertArg(context, scriptable2, obj, FunctionObject.getTypeTag(clsArr[clsArr.length - 1]));
                if (memberBox.delegateTo == null) {
                    objArr = new Object[]{convertArg};
                } else {
                    objArr = new Object[]{scriptable2, convertArg};
                    scriptable2 = memberBox.delegateTo;
                }
                memberBox.invoke(scriptable2, objArr);
            } else if (this.setter instanceof Function) {
                Function function = (Function) this.setter;
                function.call(context, function.getParentScope(), scriptable2, new Object[]{obj});
            }
            return true;
        } else if (this.getter == null) {
            return super.setValue(obj, scriptable, scriptable2);
        } else {
            if (!Context.getContext().hasFeature(11)) {
                return true;
            }
            throw ScriptRuntime.typeError1("msg.set.prop.no.setter", this.name);
        }
    }

    Object getValue(Scriptable scriptable) {
        if (this.getter != null) {
            if (this.getter instanceof MemberBox) {
                Object[] objArr;
                MemberBox memberBox = (MemberBox) this.getter;
                if (memberBox.delegateTo == null) {
                    objArr = ScriptRuntime.emptyArgs;
                } else {
                    objArr = new Object[]{scriptable};
                    scriptable = memberBox.delegateTo;
                }
                return memberBox.invoke(scriptable, objArr);
            } else if (this.getter instanceof Function) {
                Function function = (Function) this.getter;
                return function.call(Context.getContext(), function.getParentScope(), scriptable, ScriptRuntime.emptyArgs);
            }
        }
        Object obj = this.value;
        if (!(obj instanceof LazilyLoadedCtor)) {
            return obj;
        }
        obj = (LazilyLoadedCtor) obj;
        try {
            obj.init();
            return obj;
        } finally {
            obj = obj.getValue();
            this.value = obj;
        }
    }

    void markDeleted() {
        super.markDeleted();
        this.getter = null;
        this.setter = null;
    }
}
