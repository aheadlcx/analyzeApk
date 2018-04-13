package org.mozilla.javascript;

import java.lang.reflect.Field;

class FieldAndMethods extends NativeJavaMethod {
    static final long serialVersionUID = -9222428244284796755L;
    Field field;
    Object javaObject;

    FieldAndMethods(Scriptable scriptable, MemberBox[] memberBoxArr, Field field) {
        super(memberBoxArr);
        this.field = field;
        setParentScope(scriptable);
        setPrototype(ScriptableObject.getFunctionPrototype(scriptable));
    }

    public Object getDefaultValue(Class<?> cls) {
        if (cls == ScriptRuntime.FunctionClass) {
            return this;
        }
        try {
            Object obj = this.field.get(this.javaObject);
            Class type = this.field.getType();
            Context context = Context.getContext();
            obj = context.getWrapFactory().wrap(context, this, obj, type);
            if (obj instanceof Scriptable) {
                obj = ((Scriptable) obj).getDefaultValue(cls);
            }
            return obj;
        } catch (IllegalAccessException e) {
            throw Context.reportRuntimeError1("msg.java.internal.private", this.field.getName());
        }
    }
}
