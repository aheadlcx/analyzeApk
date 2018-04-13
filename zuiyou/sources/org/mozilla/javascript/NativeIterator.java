package org.mozilla.javascript;

import java.util.Iterator;

public final class NativeIterator extends IdScriptableObject {
    public static final String ITERATOR_PROPERTY_NAME = "__iterator__";
    private static final Object ITERATOR_TAG = "Iterator";
    private static final int Id___iterator__ = 3;
    private static final int Id_constructor = 1;
    private static final int Id_next = 2;
    private static final int MAX_PROTOTYPE_ID = 3;
    private static final String STOP_ITERATION = "StopIteration";
    private static final long serialVersionUID = -4136968203581667681L;
    private Object objectIterator;

    static class StopIteration extends NativeObject {
        private static final long serialVersionUID = 2485151085722377663L;

        StopIteration() {
        }

        public String getClassName() {
            return NativeIterator.STOP_ITERATION;
        }

        public boolean hasInstance(Scriptable scriptable) {
            return scriptable instanceof StopIteration;
        }
    }

    public static class WrappedJavaIterator {
        private Iterator<?> iterator;
        private Scriptable scope;

        WrappedJavaIterator(Iterator<?> it, Scriptable scriptable) {
            this.iterator = it;
            this.scope = scriptable;
        }

        public Object next() {
            if (this.iterator.hasNext()) {
                return this.iterator.next();
            }
            throw new JavaScriptException(NativeIterator.getStopIterationObject(this.scope), null, 0);
        }

        public Object __iterator__(boolean z) {
            return this;
        }
    }

    static void init(ScriptableObject scriptableObject, boolean z) {
        new NativeIterator().exportAsJSClass(3, scriptableObject, z);
        NativeGenerator.init(scriptableObject, z);
        NativeObject stopIteration = new StopIteration();
        stopIteration.setPrototype(getObjectPrototype(scriptableObject));
        stopIteration.setParentScope(scriptableObject);
        if (z) {
            stopIteration.sealObject();
        }
        ScriptableObject.defineProperty(scriptableObject, STOP_ITERATION, stopIteration, 2);
        scriptableObject.associateValue(ITERATOR_TAG, stopIteration);
    }

    private NativeIterator() {
    }

    private NativeIterator(Object obj) {
        this.objectIterator = obj;
    }

    public static Object getStopIterationObject(Scriptable scriptable) {
        return ScriptableObject.getTopScopeValue(ScriptableObject.getTopLevelScope(scriptable), ITERATOR_TAG);
    }

    public String getClassName() {
        return "Iterator";
    }

    protected void initPrototypeId(int i) {
        int i2;
        String str;
        switch (i) {
            case 1:
                i2 = 2;
                str = "constructor";
                break;
            case 2:
                i2 = 0;
                str = "next";
                break;
            case 3:
                i2 = 1;
                str = ITERATOR_PROPERTY_NAME;
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(ITERATOR_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(ITERATOR_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        if (methodId == 1) {
            return jsConstructor(context, scriptable, scriptable2, objArr);
        }
        if (scriptable2 instanceof NativeIterator) {
            NativeIterator nativeIterator = (NativeIterator) scriptable2;
            switch (methodId) {
                case 2:
                    return nativeIterator.next(context, scriptable);
                case 3:
                    return scriptable2;
                default:
                    throw new IllegalArgumentException(String.valueOf(methodId));
            }
        }
        throw IdScriptableObject.incompatibleCallError(idFunctionObject);
    }

    private static Object jsConstructor(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (objArr.length == 0 || objArr[0] == null || objArr[0] == Undefined.instance) {
            throw ScriptRuntime.typeError1("msg.no.properties", ScriptRuntime.toString(objArr.length == 0 ? Undefined.instance : objArr[0]));
        }
        Object toIterator;
        Scriptable toObject = ScriptRuntime.toObject(context, scriptable, objArr[0]);
        boolean z = objArr.length > 1 && ScriptRuntime.toBoolean(objArr[1]);
        if (scriptable2 != null) {
            Iterator javaIterator = VMBridge.instance.getJavaIterator(context, scriptable, toObject);
            if (javaIterator != null) {
                Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
                return context.getWrapFactory().wrap(context, topLevelScope, new WrappedJavaIterator(javaIterator, topLevelScope), WrappedJavaIterator.class);
            }
            toIterator = ScriptRuntime.toIterator(context, scriptable, toObject, z);
            if (toIterator != null) {
                return toIterator;
            }
        }
        Object enumInit = ScriptRuntime.enumInit(toObject, context, scriptable, z ? 3 : 5);
        ScriptRuntime.setEnumNumbers(enumInit, true);
        toIterator = new NativeIterator(enumInit);
        toIterator.setPrototype(ScriptableObject.getClassPrototype(scriptable, toIterator.getClassName()));
        toIterator.setParentScope(scriptable);
        return toIterator;
    }

    private Object next(Context context, Scriptable scriptable) {
        if (ScriptRuntime.enumNext(this.objectIterator).booleanValue()) {
            return ScriptRuntime.enumId(this.objectIterator, context);
        }
        throw new JavaScriptException(getStopIterationObject(scriptable), null, 0);
    }

    protected int findPrototypeId(String str) {
        int i;
        String str2;
        int length = str.length();
        if (length == 4) {
            i = 2;
            str2 = "next";
        } else if (length == 11) {
            i = 1;
            str2 = "constructor";
        } else if (length == 12) {
            i = 3;
            str2 = ITERATOR_PROPERTY_NAME;
        } else {
            str2 = null;
            i = 0;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }
}
