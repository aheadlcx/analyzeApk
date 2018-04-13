package org.mozilla.javascript.xmlimpl;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.NativeWith;
import org.mozilla.javascript.Ref;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.xml.XMLObject;

abstract class XMLObjectImpl extends XMLObject {
    private static final int Id_addNamespace = 2;
    private static final int Id_appendChild = 3;
    private static final int Id_attribute = 4;
    private static final int Id_attributes = 5;
    private static final int Id_child = 6;
    private static final int Id_childIndex = 7;
    private static final int Id_children = 8;
    private static final int Id_comments = 9;
    private static final int Id_constructor = 1;
    private static final int Id_contains = 10;
    private static final int Id_copy = 11;
    private static final int Id_descendants = 12;
    private static final int Id_elements = 13;
    private static final int Id_hasComplexContent = 18;
    private static final int Id_hasOwnProperty = 17;
    private static final int Id_hasSimpleContent = 19;
    private static final int Id_inScopeNamespaces = 14;
    private static final int Id_insertChildAfter = 15;
    private static final int Id_insertChildBefore = 16;
    private static final int Id_length = 20;
    private static final int Id_localName = 21;
    private static final int Id_name = 22;
    private static final int Id_namespace = 23;
    private static final int Id_namespaceDeclarations = 24;
    private static final int Id_nodeKind = 25;
    private static final int Id_normalize = 26;
    private static final int Id_parent = 27;
    private static final int Id_prependChild = 28;
    private static final int Id_processingInstructions = 29;
    private static final int Id_propertyIsEnumerable = 30;
    private static final int Id_removeNamespace = 31;
    private static final int Id_replace = 32;
    private static final int Id_setChildren = 33;
    private static final int Id_setLocalName = 34;
    private static final int Id_setName = 35;
    private static final int Id_setNamespace = 36;
    private static final int Id_text = 37;
    private static final int Id_toSource = 39;
    private static final int Id_toString = 38;
    private static final int Id_toXMLString = 40;
    private static final int Id_valueOf = 41;
    private static final int MAX_PROTOTYPE_ID = 41;
    private static final Object XMLOBJECT_TAG = "XMLObject";
    private XMLLibImpl lib;
    private boolean prototypeFlag;

    abstract void addMatches(XMLList xMLList, XMLName xMLName);

    abstract XMLList child(int i);

    abstract XMLList child(XMLName xMLName);

    abstract XMLList children();

    abstract XMLList comments();

    abstract boolean contains(Object obj);

    abstract XMLObjectImpl copy();

    abstract void deleteXMLProperty(XMLName xMLName);

    abstract XMLList elements(XMLName xMLName);

    abstract boolean equivalentXml(Object obj);

    abstract XML getXML();

    abstract Object getXMLProperty(XMLName xMLName);

    abstract boolean hasComplexContent();

    abstract boolean hasOwnProperty(XMLName xMLName);

    abstract boolean hasSimpleContent();

    abstract boolean hasXMLProperty(XMLName xMLName);

    protected abstract Object jsConstructor(Context context, boolean z, Object[] objArr);

    abstract int length();

    abstract void normalize();

    abstract Object parent();

    abstract XMLList processingInstructions(XMLName xMLName);

    abstract boolean propertyIsEnumerable(Object obj);

    abstract void putXMLProperty(XMLName xMLName, Object obj);

    abstract XMLList text();

    abstract String toSource(int i);

    public abstract String toString();

    abstract String toXMLString();

    abstract Object valueOf();

    protected XMLObjectImpl(XMLLibImpl xMLLibImpl, Scriptable scriptable, XMLObject xMLObject) {
        initialize(xMLLibImpl, scriptable, xMLObject);
    }

    final void initialize(XMLLibImpl xMLLibImpl, Scriptable scriptable, XMLObject xMLObject) {
        setParentScope(scriptable);
        setPrototype(xMLObject);
        this.prototypeFlag = xMLObject == null;
        this.lib = xMLLibImpl;
    }

    final boolean isPrototype() {
        return this.prototypeFlag;
    }

    XMLLibImpl getLib() {
        return this.lib;
    }

    final XML newXML(XmlNode xmlNode) {
        return this.lib.newXML(xmlNode);
    }

    XML xmlFromNode(XmlNode xmlNode) {
        if (xmlNode.getXml() == null) {
            xmlNode.setXml(newXML(xmlNode));
        }
        return xmlNode.getXml();
    }

    final XMLList newXMLList() {
        return this.lib.newXMLList();
    }

    final XMLList newXMLListFrom(Object obj) {
        return this.lib.newXMLListFrom(obj);
    }

    final XmlProcessor getProcessor() {
        return this.lib.getProcessor();
    }

    final QName newQName(String str, String str2, String str3) {
        return this.lib.newQName(str, str2, str3);
    }

    final QName newQName(XmlNode$QName xmlNode$QName) {
        return this.lib.newQName(xmlNode$QName);
    }

    final Namespace createNamespace(Namespace namespace) {
        if (namespace == null) {
            return null;
        }
        return this.lib.createNamespaces(new Namespace[]{namespace})[0];
    }

    final Namespace[] createNamespaces(Namespace[] namespaceArr) {
        return this.lib.createNamespaces(namespaceArr);
    }

    public final Scriptable getPrototype() {
        return super.getPrototype();
    }

    public final void setPrototype(Scriptable scriptable) {
        super.setPrototype(scriptable);
    }

    public final Scriptable getParentScope() {
        return super.getParentScope();
    }

    public final void setParentScope(Scriptable scriptable) {
        super.setParentScope(scriptable);
    }

    public final Object getDefaultValue(Class<?> cls) {
        return toString();
    }

    public final boolean hasInstance(Scriptable scriptable) {
        return super.hasInstance(scriptable);
    }

    private XMLList getMatches(XMLName xMLName) {
        XMLList newXMLList = newXMLList();
        addMatches(newXMLList, xMLName);
        return newXMLList;
    }

    protected final Object equivalentValues(Object obj) {
        return equivalentXml(obj) ? Boolean.TRUE : Boolean.FALSE;
    }

    public final boolean has(Context context, Object obj) {
        if (context == null) {
            context = Context.getCurrentContext();
        }
        XMLName toXMLNameOrIndex = this.lib.toXMLNameOrIndex(context, obj);
        if (toXMLNameOrIndex == null) {
            return has((int) ScriptRuntime.lastUint32Result(context), this);
        }
        return hasXMLProperty(toXMLNameOrIndex);
    }

    public boolean has(String str, Scriptable scriptable) {
        return hasXMLProperty(this.lib.toXMLNameFromString(Context.getCurrentContext(), str));
    }

    public final Object get(Context context, Object obj) {
        if (context == null) {
            context = Context.getCurrentContext();
        }
        XMLName toXMLNameOrIndex = this.lib.toXMLNameOrIndex(context, obj);
        if (toXMLNameOrIndex != null) {
            return getXMLProperty(toXMLNameOrIndex);
        }
        Object obj2 = get((int) ScriptRuntime.lastUint32Result(context), this);
        if (obj2 == Scriptable.NOT_FOUND) {
            return Undefined.instance;
        }
        return obj2;
    }

    public Object get(String str, Scriptable scriptable) {
        return getXMLProperty(this.lib.toXMLNameFromString(Context.getCurrentContext(), str));
    }

    public final void put(Context context, Object obj, Object obj2) {
        if (context == null) {
            context = Context.getCurrentContext();
        }
        XMLName toXMLNameOrIndex = this.lib.toXMLNameOrIndex(context, obj);
        if (toXMLNameOrIndex == null) {
            put((int) ScriptRuntime.lastUint32Result(context), this, obj2);
        } else {
            putXMLProperty(toXMLNameOrIndex, obj2);
        }
    }

    public void put(String str, Scriptable scriptable, Object obj) {
        putXMLProperty(this.lib.toXMLNameFromString(Context.getCurrentContext(), str), obj);
    }

    public final boolean delete(Context context, Object obj) {
        if (context == null) {
            context = Context.getCurrentContext();
        }
        XMLName toXMLNameOrIndex = this.lib.toXMLNameOrIndex(context, obj);
        if (toXMLNameOrIndex == null) {
            delete((int) ScriptRuntime.lastUint32Result(context));
        } else {
            deleteXMLProperty(toXMLNameOrIndex);
        }
        return true;
    }

    public void delete(String str) {
        deleteXMLProperty(this.lib.toXMLNameFromString(Context.getCurrentContext(), str));
    }

    public Object getFunctionProperty(Context context, int i) {
        if (isPrototype()) {
            return super.get(i, this);
        }
        Scriptable prototype = getPrototype();
        if (prototype instanceof XMLObject) {
            return ((XMLObject) prototype).getFunctionProperty(context, i);
        }
        return NOT_FOUND;
    }

    public Object getFunctionProperty(Context context, String str) {
        if (isPrototype()) {
            return super.get(str, this);
        }
        Scriptable prototype = getPrototype();
        if (prototype instanceof XMLObject) {
            return ((XMLObject) prototype).getFunctionProperty(context, str);
        }
        return NOT_FOUND;
    }

    public Ref memberRef(Context context, Object obj, int i) {
        boolean z = true;
        boolean z2 = (i & 2) != 0;
        if ((i & 4) == 0) {
            z = false;
        }
        if (z2 || z) {
            Ref create = XMLName.create(this.lib.toNodeQName(context, obj, z2), z2, z);
            create.initXMLObject(this);
            return create;
        }
        throw Kit.codeBug();
    }

    public Ref memberRef(Context context, Object obj, Object obj2, int i) {
        boolean z = true;
        boolean z2 = (i & 2) != 0;
        if ((i & 4) == 0) {
            z = false;
        }
        Ref create = XMLName.create(this.lib.toNodeQName(context, obj, obj2), z2, z);
        create.initXMLObject(this);
        return create;
    }

    public NativeWith enterWith(Scriptable scriptable) {
        return new XMLWithScope(this.lib, scriptable, this);
    }

    public NativeWith enterDotQuery(Scriptable scriptable) {
        NativeWith xMLWithScope = new XMLWithScope(this.lib, scriptable, this);
        xMLWithScope.initAsDotQuery();
        return xMLWithScope;
    }

    public final Object addValues(Context context, boolean z, Object obj) {
        if (obj instanceof XMLObject) {
            XMLObject xMLObject;
            if (z) {
                obj = (XMLObject) obj;
                xMLObject = this;
            } else {
                xMLObject = (XMLObject) obj;
                XMLObjectImpl xMLObjectImpl = this;
            }
            return this.lib.addXMLObjects(context, xMLObject, obj);
        } else if (obj == Undefined.instance) {
            return ScriptRuntime.toString(this);
        } else {
            return super.addValues(context, z, obj);
        }
    }

    final void exportAsJSClass(boolean z) {
        this.prototypeFlag = true;
        exportAsJSClass(41, getParentScope(), z);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected int findPrototypeId(java.lang.String r9) {
        /*
        r8 = this;
        r2 = 7;
        r6 = 99;
        r3 = 3;
        r4 = 2;
        r0 = 0;
        r1 = 0;
        r5 = r9.length();
        switch(r5) {
            case 4: goto L_0x001b;
            case 5: goto L_0x0044;
            case 6: goto L_0x004c;
            case 7: goto L_0x006a;
            case 8: goto L_0x0096;
            case 9: goto L_0x00f3;
            case 10: goto L_0x0125;
            case 11: goto L_0x0140;
            case 12: goto L_0x0179;
            case 13: goto L_0x000e;
            case 14: goto L_0x01ba;
            case 15: goto L_0x01c4;
            case 16: goto L_0x01ce;
            case 17: goto L_0x01ee;
            case 18: goto L_0x000e;
            case 19: goto L_0x000e;
            case 20: goto L_0x021a;
            case 21: goto L_0x0224;
            case 22: goto L_0x022e;
            default: goto L_0x000e;
        };
    L_0x000e:
        r2 = r1;
        r1 = r0;
    L_0x0010:
        if (r2 == 0) goto L_0x0238;
    L_0x0012:
        if (r2 == r9) goto L_0x0238;
    L_0x0014:
        r2 = r2.equals(r9);
        if (r2 != 0) goto L_0x0238;
    L_0x001a:
        return r0;
    L_0x001b:
        r2 = r9.charAt(r0);
        if (r2 != r6) goto L_0x002a;
    L_0x0021:
        r1 = "copy";
        r2 = 11;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x002a:
        r3 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
        if (r2 != r3) goto L_0x0037;
    L_0x002e:
        r1 = "name";
        r2 = 22;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0037:
        r3 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        if (r2 != r3) goto L_0x000e;
    L_0x003b:
        r1 = "text";
        r2 = 37;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0044:
        r1 = "child";
        r2 = 6;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x004c:
        r2 = r9.charAt(r0);
        r3 = 108; // 0x6c float:1.51E-43 double:5.34E-322;
        if (r2 != r3) goto L_0x005d;
    L_0x0054:
        r1 = "length";
        r2 = 20;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x005d:
        r3 = 112; // 0x70 float:1.57E-43 double:5.53E-322;
        if (r2 != r3) goto L_0x000e;
    L_0x0061:
        r1 = "parent";
        r2 = 27;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x006a:
        r2 = r9.charAt(r0);
        r3 = 114; // 0x72 float:1.6E-43 double:5.63E-322;
        if (r2 != r3) goto L_0x007b;
    L_0x0072:
        r1 = "replace";
        r2 = 32;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x007b:
        r3 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        if (r2 != r3) goto L_0x0088;
    L_0x007f:
        r1 = "setName";
        r2 = 35;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0088:
        r3 = 118; // 0x76 float:1.65E-43 double:5.83E-322;
        if (r2 != r3) goto L_0x000e;
    L_0x008c:
        r1 = "valueOf";
        r2 = 41;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0096:
        r3 = r9.charAt(r4);
        switch(r3) {
            case 83: goto L_0x00a1;
            case 100: goto L_0x00c1;
            case 101: goto L_0x00cb;
            case 105: goto L_0x00d5;
            case 109: goto L_0x00df;
            case 110: goto L_0x00e9;
            default: goto L_0x009d;
        };
    L_0x009d:
        r2 = r1;
        r1 = r0;
        goto L_0x0010;
    L_0x00a1:
        r2 = r9.charAt(r2);
        r3 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r2 != r3) goto L_0x00b3;
    L_0x00a9:
        r1 = "toSource";
        r2 = 39;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x00b3:
        r3 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        if (r2 != r3) goto L_0x000e;
    L_0x00b7:
        r1 = "toString";
        r2 = 38;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x00c1:
        r1 = "nodeKind";
        r2 = 25;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x00cb:
        r1 = "elements";
        r2 = 13;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x00d5:
        r1 = "children";
        r2 = 8;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x00df:
        r1 = "comments";
        r2 = 9;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x00e9:
        r1 = "contains";
        r2 = 10;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x00f3:
        r2 = r9.charAt(r4);
        switch(r2) {
            case 99: goto L_0x00fe;
            case 109: goto L_0x0108;
            case 114: goto L_0x0112;
            case 116: goto L_0x011c;
            default: goto L_0x00fa;
        };
    L_0x00fa:
        r2 = r1;
        r1 = r0;
        goto L_0x0010;
    L_0x00fe:
        r1 = "localName";
        r2 = 21;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0108:
        r1 = "namespace";
        r2 = 23;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0112:
        r1 = "normalize";
        r2 = 26;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x011c:
        r1 = "attribute";
        r2 = 4;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0125:
        r3 = r9.charAt(r0);
        r4 = 97;
        if (r3 != r4) goto L_0x0136;
    L_0x012d:
        r1 = "attributes";
        r2 = 5;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0136:
        if (r3 != r6) goto L_0x000e;
    L_0x0138:
        r1 = "childIndex";
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0140:
        r2 = r9.charAt(r0);
        switch(r2) {
            case 97: goto L_0x014b;
            case 99: goto L_0x0152;
            case 100: goto L_0x015b;
            case 115: goto L_0x0165;
            case 116: goto L_0x016f;
            default: goto L_0x0147;
        };
    L_0x0147:
        r2 = r1;
        r1 = r0;
        goto L_0x0010;
    L_0x014b:
        r1 = "appendChild";
        r2 = r1;
        r1 = r3;
        goto L_0x0010;
    L_0x0152:
        r1 = "constructor";
        r2 = 1;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x015b:
        r1 = "descendants";
        r2 = 12;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0165:
        r1 = "setChildren";
        r2 = 33;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x016f:
        r1 = "toXMLString";
        r2 = 40;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0179:
        r2 = r9.charAt(r0);
        r5 = 97;
        if (r2 != r5) goto L_0x0188;
    L_0x0181:
        r1 = "addNamespace";
        r2 = r1;
        r1 = r4;
        goto L_0x0010;
    L_0x0188:
        r4 = 112; // 0x70 float:1.57E-43 double:5.53E-322;
        if (r2 != r4) goto L_0x0196;
    L_0x018c:
        r1 = "prependChild";
        r2 = 28;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0196:
        r4 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        if (r2 != r4) goto L_0x000e;
    L_0x019a:
        r2 = r9.charAt(r3);
        r3 = 76;
        if (r2 != r3) goto L_0x01ac;
    L_0x01a2:
        r1 = "setLocalName";
        r2 = 34;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x01ac:
        r3 = 78;
        if (r2 != r3) goto L_0x000e;
    L_0x01b0:
        r1 = "setNamespace";
        r2 = 36;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x01ba:
        r1 = "hasOwnProperty";
        r2 = 17;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x01c4:
        r1 = "removeNamespace";
        r2 = 31;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x01ce:
        r2 = r9.charAt(r0);
        r3 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        if (r2 != r3) goto L_0x01e0;
    L_0x01d6:
        r1 = "hasSimpleContent";
        r2 = 19;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x01e0:
        r3 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        if (r2 != r3) goto L_0x000e;
    L_0x01e4:
        r1 = "insertChildAfter";
        r2 = 15;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x01ee:
        r2 = r9.charAt(r3);
        r3 = 67;
        if (r2 != r3) goto L_0x0200;
    L_0x01f6:
        r1 = "hasComplexContent";
        r2 = 18;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0200:
        if (r2 != r6) goto L_0x020c;
    L_0x0202:
        r1 = "inScopeNamespaces";
        r2 = 14;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x020c:
        r3 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r2 != r3) goto L_0x000e;
    L_0x0210:
        r1 = "insertChildBefore";
        r2 = 16;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x021a:
        r1 = "propertyIsEnumerable";
        r2 = 30;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0224:
        r1 = "namespaceDeclarations";
        r2 = 24;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x022e:
        r1 = "processingInstructions";
        r2 = 29;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0010;
    L_0x0238:
        r0 = r1;
        goto L_0x001a;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xmlimpl.XMLObjectImpl.findPrototypeId(java.lang.String):int");
    }

    protected void initPrototypeId(int i) {
        String str;
        int i2 = 0;
        switch (i) {
            case 1:
                IdFunctionObject xMLCtor;
                if (this instanceof XML) {
                    xMLCtor = new XMLCtor((XML) this, XMLOBJECT_TAG, i, 1);
                } else {
                    xMLCtor = new IdFunctionObject(this, XMLOBJECT_TAG, i, 1);
                }
                initPrototypeConstructor(xMLCtor);
                return;
            case 2:
                str = "addNamespace";
                i2 = 1;
                break;
            case 3:
                str = "appendChild";
                i2 = 1;
                break;
            case 4:
                str = "attribute";
                i2 = 1;
                break;
            case 5:
                str = "attributes";
                break;
            case 6:
                str = "child";
                i2 = 1;
                break;
            case 7:
                str = "childIndex";
                break;
            case 8:
                str = "children";
                break;
            case 9:
                str = "comments";
                break;
            case 10:
                str = "contains";
                i2 = 1;
                break;
            case 11:
                str = "copy";
                break;
            case 12:
                str = "descendants";
                i2 = 1;
                break;
            case 13:
                str = "elements";
                i2 = 1;
                break;
            case 14:
                str = "inScopeNamespaces";
                break;
            case 15:
                str = "insertChildAfter";
                i2 = 2;
                break;
            case 16:
                str = "insertChildBefore";
                i2 = 2;
                break;
            case 17:
                str = "hasOwnProperty";
                i2 = 1;
                break;
            case 18:
                str = "hasComplexContent";
                break;
            case 19:
                str = "hasSimpleContent";
                break;
            case 20:
                str = "length";
                break;
            case 21:
                str = "localName";
                break;
            case 22:
                str = "name";
                break;
            case 23:
                str = "namespace";
                i2 = 1;
                break;
            case 24:
                str = "namespaceDeclarations";
                break;
            case 25:
                str = "nodeKind";
                break;
            case 26:
                str = "normalize";
                break;
            case 27:
                str = "parent";
                break;
            case 28:
                str = "prependChild";
                i2 = 1;
                break;
            case 29:
                str = "processingInstructions";
                i2 = 1;
                break;
            case 30:
                str = "propertyIsEnumerable";
                i2 = 1;
                break;
            case 31:
                str = "removeNamespace";
                i2 = 1;
                break;
            case 32:
                str = "replace";
                i2 = 2;
                break;
            case 33:
                str = "setChildren";
                i2 = 1;
                break;
            case 34:
                str = "setLocalName";
                i2 = 1;
                break;
            case 35:
                str = "setName";
                i2 = 1;
                break;
            case 36:
                str = "setNamespace";
                i2 = 1;
                break;
            case 37:
                str = "text";
                break;
            case 38:
                str = "toString";
                break;
            case 39:
                str = "toSource";
                i2 = 1;
                break;
            case 40:
                str = "toXMLString";
                i2 = 1;
                break;
            case 41:
                str = "valueOf";
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(XMLOBJECT_TAG, i, str, i2);
    }

    private Object[] toObjectArray(Object[] objArr) {
        Object[] objArr2 = new Object[objArr.length];
        for (int i = 0; i < objArr2.length; i++) {
            objArr2[i] = objArr[i];
        }
        return objArr2;
    }

    private void xmlMethodNotFound(Object obj, String str) {
        throw ScriptRuntime.notFunctionError(obj, str);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        boolean z = false;
        if (!idFunctionObject.hasTag(XMLOBJECT_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        if (methodId == 1) {
            if (scriptable2 == null) {
                z = true;
            }
            return jsConstructor(context, z, objArr);
        } else if (scriptable2 instanceof XMLObjectImpl) {
            XMLObjectImpl xMLObjectImpl = (XMLObjectImpl) scriptable2;
            XML xml = xMLObjectImpl.getXML();
            XMLName toXMLNameOrIndex;
            Object arg;
            String scriptRuntime;
            switch (methodId) {
                case 2:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "addNamespace");
                    }
                    return xml.addNamespace(this.lib.castToNamespace(context, arg(objArr, 0)));
                case 3:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "appendChild");
                    }
                    return xml.appendChild(arg(objArr, 0));
                case 4:
                    return xMLObjectImpl.getMatches(XMLName.create(this.lib.toNodeQName(context, arg(objArr, 0), true), true, false));
                case 5:
                    return xMLObjectImpl.getMatches(XMLName.create(XmlNode$QName.create(null, null), true, false));
                case 6:
                    toXMLNameOrIndex = this.lib.toXMLNameOrIndex(context, arg(objArr, 0));
                    if (toXMLNameOrIndex == null) {
                        return xMLObjectImpl.child((int) ScriptRuntime.lastUint32Result(context));
                    }
                    return xMLObjectImpl.child(toXMLNameOrIndex);
                case 7:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "childIndex");
                    }
                    return ScriptRuntime.wrapInt(xml.childIndex());
                case 8:
                    return xMLObjectImpl.children();
                case 9:
                    return xMLObjectImpl.comments();
                case 10:
                    return ScriptRuntime.wrapBoolean(xMLObjectImpl.contains(arg(objArr, 0)));
                case 11:
                    return xMLObjectImpl.copy();
                case 12:
                    return xMLObjectImpl.getMatches(XMLName.create(objArr.length == 0 ? XmlNode$QName.create(null, null) : this.lib.toNodeQName(context, objArr[0], false), false, true));
                case 13:
                    return xMLObjectImpl.elements(objArr.length == 0 ? XMLName.formStar() : this.lib.toXMLName(context, objArr[0]));
                case 14:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "inScopeNamespaces");
                    }
                    return context.newArray(scriptable, toObjectArray(xml.inScopeNamespaces()));
                case 15:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "insertChildAfter");
                    }
                    arg = arg(objArr, 0);
                    if (arg == null || (arg instanceof XML)) {
                        return xml.insertChildAfter((XML) arg, arg(objArr, 1));
                    }
                    return Undefined.instance;
                case 16:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "insertChildBefore");
                    }
                    arg = arg(objArr, 0);
                    if (arg == null || (arg instanceof XML)) {
                        return xml.insertChildBefore((XML) arg, arg(objArr, 1));
                    }
                    return Undefined.instance;
                case 17:
                    return ScriptRuntime.wrapBoolean(xMLObjectImpl.hasOwnProperty(this.lib.toXMLName(context, arg(objArr, 0))));
                case 18:
                    return ScriptRuntime.wrapBoolean(xMLObjectImpl.hasComplexContent());
                case 19:
                    return ScriptRuntime.wrapBoolean(xMLObjectImpl.hasSimpleContent());
                case 20:
                    return ScriptRuntime.wrapInt(xMLObjectImpl.length());
                case 21:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "localName");
                    }
                    return xml.localName();
                case 22:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "name");
                    }
                    return xml.name();
                case 23:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "namespace");
                    }
                    if (objArr.length > 0) {
                        scriptRuntime = ScriptRuntime.toString(objArr[0]);
                    } else {
                        scriptRuntime = null;
                    }
                    arg = xml.namespace(scriptRuntime);
                    if (arg == null) {
                        return Undefined.instance;
                    }
                    return arg;
                case 24:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "namespaceDeclarations");
                    }
                    return context.newArray(scriptable, toObjectArray(xml.namespaceDeclarations()));
                case 25:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "nodeKind");
                    }
                    return xml.nodeKind();
                case 26:
                    xMLObjectImpl.normalize();
                    return Undefined.instance;
                case 27:
                    return xMLObjectImpl.parent();
                case 28:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "prependChild");
                    }
                    return xml.prependChild(arg(objArr, 0));
                case 29:
                    return xMLObjectImpl.processingInstructions(objArr.length > 0 ? this.lib.toXMLName(context, objArr[0]) : XMLName.formStar());
                case 30:
                    return ScriptRuntime.wrapBoolean(xMLObjectImpl.propertyIsEnumerable(arg(objArr, 0)));
                case 31:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "removeNamespace");
                    }
                    return xml.removeNamespace(this.lib.castToNamespace(context, arg(objArr, 0)));
                case 32:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "replace");
                    }
                    toXMLNameOrIndex = this.lib.toXMLNameOrIndex(context, arg(objArr, 0));
                    Object arg2 = arg(objArr, 1);
                    if (toXMLNameOrIndex == null) {
                        return xml.replace((int) ScriptRuntime.lastUint32Result(context), arg2);
                    }
                    return xml.replace(toXMLNameOrIndex, arg2);
                case 33:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "setChildren");
                    }
                    return xml.setChildren(arg(objArr, 0));
                case 34:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "setLocalName");
                    }
                    arg = arg(objArr, 0);
                    if (arg instanceof QName) {
                        scriptRuntime = ((QName) arg).localName();
                    } else {
                        scriptRuntime = ScriptRuntime.toString(arg);
                    }
                    xml.setLocalName(scriptRuntime);
                    return Undefined.instance;
                case 35:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "setName");
                    }
                    xml.setName(this.lib.constructQName(context, objArr.length != 0 ? objArr[0] : Undefined.instance));
                    return Undefined.instance;
                case 36:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "setNamespace");
                    }
                    xml.setNamespace(this.lib.castToNamespace(context, arg(objArr, 0)));
                    return Undefined.instance;
                case 37:
                    return xMLObjectImpl.text();
                case 38:
                    return xMLObjectImpl.toString();
                case 39:
                    return xMLObjectImpl.toSource(ScriptRuntime.toInt32(objArr, 0));
                case 40:
                    return xMLObjectImpl.toXMLString();
                case 41:
                    return xMLObjectImpl.valueOf();
                default:
                    throw new IllegalArgumentException(String.valueOf(methodId));
            }
        } else {
            throw IdScriptableObject.incompatibleCallError(idFunctionObject);
        }
    }

    private static Object arg(Object[] objArr, int i) {
        return i < objArr.length ? objArr[i] : Undefined.instance;
    }

    final XML newTextElementXML(XmlNode xmlNode, XmlNode$QName xmlNode$QName, String str) {
        return this.lib.newTextElementXML(xmlNode, xmlNode$QName, str);
    }

    final XML newXMLFromJs(Object obj) {
        return this.lib.newXMLFromJs(obj);
    }

    final XML ecmaToXml(Object obj) {
        return this.lib.ecmaToXml(obj);
    }

    final String ecmaEscapeAttributeValue(String str) {
        String escapeAttributeValue = this.lib.escapeAttributeValue(str);
        return escapeAttributeValue.substring(1, escapeAttributeValue.length() - 1);
    }

    final XML createEmptyXML() {
        return newXML(XmlNode.createEmpty(getProcessor()));
    }
}
