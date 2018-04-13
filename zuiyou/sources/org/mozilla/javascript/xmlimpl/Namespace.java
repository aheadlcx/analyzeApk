package org.mozilla.javascript.xmlimpl;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;

class Namespace extends IdScriptableObject {
    private static final int Id_constructor = 1;
    private static final int Id_prefix = 1;
    private static final int Id_toSource = 3;
    private static final int Id_toString = 2;
    private static final int Id_uri = 2;
    private static final int MAX_INSTANCE_ID = 2;
    private static final int MAX_PROTOTYPE_ID = 3;
    private static final Object NAMESPACE_TAG = "Namespace";
    static final long serialVersionUID = -5765755238131301744L;
    private Namespace ns;
    private Namespace prototype;

    private Namespace() {
    }

    static Namespace create(Scriptable scriptable, Namespace namespace, Namespace namespace2) {
        Namespace namespace3 = new Namespace();
        namespace3.setParentScope(scriptable);
        namespace3.prototype = namespace;
        namespace3.setPrototype(namespace);
        namespace3.ns = namespace2;
        return namespace3;
    }

    final Namespace getDelegate() {
        return this.ns;
    }

    public void exportAsJSClass(boolean z) {
        exportAsJSClass(3, getParentScope(), z);
    }

    public String uri() {
        return this.ns.getUri();
    }

    public String prefix() {
        return this.ns.getPrefix();
    }

    public String toString() {
        return uri();
    }

    public String toLocaleString() {
        return toString();
    }

    private boolean equals(Namespace namespace) {
        return uri().equals(namespace.uri());
    }

    public boolean equals(Object obj) {
        if (obj instanceof Namespace) {
            return equals((Namespace) obj);
        }
        return false;
    }

    public int hashCode() {
        return uri().hashCode();
    }

    protected Object equivalentValues(Object obj) {
        if (obj instanceof Namespace) {
            return equals((Namespace) obj) ? Boolean.TRUE : Boolean.FALSE;
        } else {
            return Scriptable.NOT_FOUND;
        }
    }

    public String getClassName() {
        return "Namespace";
    }

    public Object getDefaultValue(Class<?> cls) {
        return uri();
    }

    protected int getMaxInstanceId() {
        return super.getMaxInstanceId() + 2;
    }

    protected int findInstanceIdInfo(String str) {
        int i;
        String str2;
        int i2 = 0;
        int length = str.length();
        if (length == 3) {
            i = 2;
            str2 = "uri";
        } else if (length == 6) {
            i = 1;
            str2 = RequestParameters.PREFIX;
        } else {
            str2 = null;
            i = 0;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            i2 = i;
        }
        if (i2 == 0) {
            return super.findInstanceIdInfo(str);
        }
        switch (i2) {
            case 1:
            case 2:
                return IdScriptableObject.instanceIdInfo(5, i2 + super.getMaxInstanceId());
            default:
                throw new IllegalStateException();
        }
    }

    protected String getInstanceIdName(int i) {
        switch (i - super.getMaxInstanceId()) {
            case 1:
                return RequestParameters.PREFIX;
            case 2:
                return "uri";
            default:
                return super.getInstanceIdName(i);
        }
    }

    protected Object getInstanceIdValue(int i) {
        switch (i - super.getMaxInstanceId()) {
            case 1:
                if (this.ns.getPrefix() == null) {
                    return Undefined.instance;
                }
                return this.ns.getPrefix();
            case 2:
                return this.ns.getUri();
            default:
                return super.getInstanceIdValue(i);
        }
    }

    protected int findPrototypeId(String str) {
        int i;
        String str2;
        int length = str.length();
        if (length == 8) {
            char charAt = str.charAt(3);
            if (charAt == 'o') {
                i = 3;
                str2 = "toSource";
            } else {
                if (charAt == 't') {
                    i = 2;
                    str2 = "toString";
                }
                str2 = null;
                i = 0;
            }
        } else {
            if (length == 11) {
                i = 1;
                str2 = "constructor";
            }
            str2 = null;
            i = 0;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }

    protected void initPrototypeId(int i) {
        String str;
        int i2 = 0;
        switch (i) {
            case 1:
                i2 = 2;
                str = "constructor";
                break;
            case 2:
                str = "toString";
                break;
            case 3:
                str = "toSource";
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(NAMESPACE_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(NAMESPACE_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        switch (methodId) {
            case 1:
                return jsConstructor(context, scriptable2 == null, objArr);
            case 2:
                return realThis(scriptable2, idFunctionObject).toString();
            case 3:
                return realThis(scriptable2, idFunctionObject).js_toSource();
            default:
                throw new IllegalArgumentException(String.valueOf(methodId));
        }
    }

    private Namespace realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        if (scriptable instanceof Namespace) {
            return (Namespace) scriptable;
        }
        throw IdScriptableObject.incompatibleCallError(idFunctionObject);
    }

    Namespace newNamespace(String str) {
        return create(getParentScope(), this.prototype == null ? this : this.prototype, Namespace.create(str));
    }

    Namespace newNamespace(String str, String str2) {
        if (str == null) {
            return newNamespace(str2);
        }
        return create(getParentScope(), this.prototype == null ? this : this.prototype, Namespace.create(str, str2));
    }

    Namespace constructNamespace(Object obj) {
        String uri;
        String str = null;
        if (obj instanceof Namespace) {
            Namespace namespace = (Namespace) obj;
            str = namespace.prefix();
            uri = namespace.uri();
        } else if (obj instanceof QName) {
            QName qName = (QName) obj;
            uri = qName.uri();
            if (uri != null) {
                str = qName.prefix();
            } else {
                uri = qName.toString();
            }
        } else {
            uri = ScriptRuntime.toString(obj);
            if (uri.length() == 0) {
                str = "";
            }
        }
        return newNamespace(str, uri);
    }

    Namespace castToNamespace(Object obj) {
        if (obj instanceof Namespace) {
            return (Namespace) obj;
        }
        return constructNamespace(obj);
    }

    private Namespace constructNamespace(Object obj, Object obj2) {
        String uri;
        String str;
        if (obj2 instanceof QName) {
            QName qName = (QName) obj2;
            uri = qName.uri();
            if (uri == null) {
                uri = qName.toString();
            }
        } else {
            uri = ScriptRuntime.toString(obj2);
        }
        if (uri.length() == 0) {
            if (obj == Undefined.instance) {
                str = "";
            } else {
                str = ScriptRuntime.toString(obj);
                if (str.length() != 0) {
                    throw ScriptRuntime.typeError("Illegal prefix '" + str + "' for 'no namespace'.");
                }
            }
        } else if (obj == Undefined.instance) {
            str = "";
        } else if (XMLName.accept(obj)) {
            str = ScriptRuntime.toString(obj);
        } else {
            str = "";
        }
        return newNamespace(str, uri);
    }

    private Namespace constructNamespace() {
        return newNamespace("", "");
    }

    private Object jsConstructor(Context context, boolean z, Object[] objArr) {
        if (!z && objArr.length == 1) {
            return castToNamespace(objArr[0]);
        }
        if (objArr.length == 0) {
            return constructNamespace();
        }
        if (objArr.length == 1) {
            return constructNamespace(objArr[0]);
        }
        return constructNamespace(objArr[0], objArr[1]);
    }

    private String js_toSource() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('(');
        toSourceImpl(this.ns.getPrefix(), this.ns.getUri(), stringBuilder);
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    static void toSourceImpl(String str, String str2, StringBuilder stringBuilder) {
        stringBuilder.append("new Namespace(");
        if (str2.length() != 0) {
            stringBuilder.append('\'');
            if (str != null) {
                stringBuilder.append(ScriptRuntime.escapeString(str, '\''));
                stringBuilder.append("', '");
            }
            stringBuilder.append(ScriptRuntime.escapeString(str2, '\''));
            stringBuilder.append('\'');
        } else if (!"".equals(str)) {
            throw new IllegalArgumentException(str);
        }
        stringBuilder.append(')');
    }
}
