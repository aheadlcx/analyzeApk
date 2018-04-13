package org.mozilla.javascript.xmlimpl;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.xmlimpl.XmlNode.Namespace;

final class QName extends IdScriptableObject {
    private static final int Id_constructor = 1;
    private static final int Id_localName = 1;
    private static final int Id_toSource = 3;
    private static final int Id_toString = 2;
    private static final int Id_uri = 2;
    private static final int MAX_INSTANCE_ID = 2;
    private static final int MAX_PROTOTYPE_ID = 3;
    private static final Object QNAME_TAG = "QName";
    static final long serialVersionUID = 416745167693026750L;
    private XmlNode$QName delegate;
    private XMLLibImpl lib;
    private QName prototype;

    private QName() {
    }

    static QName create(XMLLibImpl xMLLibImpl, Scriptable scriptable, QName qName, XmlNode$QName xmlNode$QName) {
        QName qName2 = new QName();
        qName2.lib = xMLLibImpl;
        qName2.setParentScope(scriptable);
        qName2.prototype = qName;
        qName2.setPrototype(qName);
        qName2.delegate = xmlNode$QName;
        return qName2;
    }

    void exportAsJSClass(boolean z) {
        exportAsJSClass(3, getParentScope(), z);
    }

    public String toString() {
        if (this.delegate.getNamespace() == null) {
            return "*::" + localName();
        }
        if (this.delegate.getNamespace().isGlobal()) {
            return localName();
        }
        return uri() + "::" + localName();
    }

    public String localName() {
        if (this.delegate.getLocalName() == null) {
            return "*";
        }
        return this.delegate.getLocalName();
    }

    String prefix() {
        if (this.delegate.getNamespace() == null) {
            return null;
        }
        return this.delegate.getNamespace().getPrefix();
    }

    String uri() {
        if (this.delegate.getNamespace() == null) {
            return null;
        }
        return this.delegate.getNamespace().getUri();
    }

    @Deprecated
    final XmlNode$QName toNodeQname() {
        return this.delegate;
    }

    final XmlNode$QName getDelegate() {
        return this.delegate;
    }

    public boolean equals(Object obj) {
        if (obj instanceof QName) {
            return equals((QName) obj);
        }
        return false;
    }

    public int hashCode() {
        return this.delegate.hashCode();
    }

    protected Object equivalentValues(Object obj) {
        if (obj instanceof QName) {
            return equals((QName) obj) ? Boolean.TRUE : Boolean.FALSE;
        } else {
            return Scriptable.NOT_FOUND;
        }
    }

    private boolean equals(QName qName) {
        return this.delegate.equals(qName.delegate);
    }

    public String getClassName() {
        return "QName";
    }

    public Object getDefaultValue(Class<?> cls) {
        return toString();
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
        } else if (length == 9) {
            i = 1;
            str2 = "localName";
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
                return instanceIdInfo(5, i2 + super.getMaxInstanceId());
            default:
                throw new IllegalStateException();
        }
    }

    protected String getInstanceIdName(int i) {
        switch (i - super.getMaxInstanceId()) {
            case 1:
                return "localName";
            case 2:
                return "uri";
            default:
                return super.getInstanceIdName(i);
        }
    }

    protected Object getInstanceIdValue(int i) {
        switch (i - super.getMaxInstanceId()) {
            case 1:
                return localName();
            case 2:
                return uri();
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
        initPrototypeMethod(QNAME_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(QNAME_TAG)) {
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

    private QName realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        if (scriptable instanceof QName) {
            return (QName) scriptable;
        }
        throw incompatibleCallError(idFunctionObject);
    }

    QName newQName(XMLLibImpl xMLLibImpl, String str, String str2, String str3) {
        QName qName;
        QName qName2 = this.prototype;
        if (qName2 == null) {
            qName = this;
        } else {
            qName = qName2;
        }
        Namespace create = str3 != null ? Namespace.create(str3, str) : str != null ? Namespace.create(str) : null;
        if (str2 != null && str2.equals("*")) {
            str2 = null;
        }
        return create(xMLLibImpl, getParentScope(), qName, XmlNode$QName.create(create, str2));
    }

    QName constructQName(XMLLibImpl xMLLibImpl, Context context, Object obj, Object obj2) {
        String str;
        Object obj3;
        Namespace namespace;
        String str2;
        String str3 = null;
        if (obj2 instanceof QName) {
            if (obj == Undefined.instance) {
                return (QName) obj2;
            }
            ((QName) obj2).localName();
        }
        if (obj2 == Undefined.instance) {
            str = "";
        } else {
            str = ScriptRuntime.toString(obj2);
        }
        if (obj != Undefined.instance) {
            obj3 = obj;
        } else if ("*".equals(str)) {
            obj3 = null;
        } else {
            obj3 = xMLLibImpl.getDefaultNamespace(context);
        }
        if (obj3 == null) {
            namespace = null;
        } else if (obj3 instanceof Namespace) {
            namespace = (Namespace) obj3;
        } else {
            namespace = xMLLibImpl.newNamespace(ScriptRuntime.toString(obj3));
        }
        if (obj3 == null) {
            str2 = null;
        } else {
            str3 = namespace.uri();
            str2 = namespace.prefix();
        }
        return newQName(xMLLibImpl, str3, str, str2);
    }

    QName constructQName(XMLLibImpl xMLLibImpl, Context context, Object obj) {
        return constructQName(xMLLibImpl, context, Undefined.instance, obj);
    }

    QName castToQName(XMLLibImpl xMLLibImpl, Context context, Object obj) {
        if (obj instanceof QName) {
            return (QName) obj;
        }
        return constructQName(xMLLibImpl, context, obj);
    }

    private Object jsConstructor(Context context, boolean z, Object[] objArr) {
        if (!z && objArr.length == 1) {
            return castToQName(this.lib, context, objArr[0]);
        }
        if (objArr.length == 0) {
            return constructQName(this.lib, context, Undefined.instance);
        }
        if (objArr.length == 1) {
            return constructQName(this.lib, context, objArr[0]);
        }
        return constructQName(this.lib, context, objArr[0], objArr[1]);
    }

    private String js_toSource() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('(');
        toSourceImpl(uri(), localName(), prefix(), stringBuilder);
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    private static void toSourceImpl(String str, String str2, String str3, StringBuilder stringBuilder) {
        stringBuilder.append("new QName(");
        if (str != null || str3 != null) {
            Namespace.toSourceImpl(str3, str, stringBuilder);
            stringBuilder.append(", ");
        } else if (!"*".equals(str2)) {
            stringBuilder.append("null, ");
        }
        stringBuilder.append('\'');
        stringBuilder.append(ScriptRuntime.escapeString(str2, '\''));
        stringBuilder.append("')");
    }
}
