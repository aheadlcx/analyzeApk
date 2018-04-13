package org.mozilla.javascript.xmlimpl;

import java.io.Serializable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Ref;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.Wrapper;
import org.mozilla.javascript.xml.XMLLib;
import org.mozilla.javascript.xml.XMLObject;
import org.mozilla.javascript.xmlimpl.XmlNode.Namespace;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public final class XMLLibImpl extends XMLLib implements Serializable {
    private static final long serialVersionUID = 1;
    private Scriptable globalScope;
    private Namespace namespacePrototype;
    private XmlProcessor options = new XmlProcessor();
    private QName qnamePrototype;
    private XMLList xmlListPrototype;
    private XML xmlPrototype;

    public static Node toDomNode(Object obj) {
        if (obj instanceof XML) {
            return ((XML) obj).toDomNode();
        }
        throw new IllegalArgumentException("xmlObject is not an XML object in JavaScript.");
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        XMLLib xMLLibImpl = new XMLLibImpl(scriptable);
        if (xMLLibImpl.bindToScope(scriptable) == xMLLibImpl) {
            xMLLibImpl.exportToScope(z);
        }
    }

    public void setIgnoreComments(boolean z) {
        this.options.setIgnoreComments(z);
    }

    public void setIgnoreWhitespace(boolean z) {
        this.options.setIgnoreWhitespace(z);
    }

    public void setIgnoreProcessingInstructions(boolean z) {
        this.options.setIgnoreProcessingInstructions(z);
    }

    public void setPrettyPrinting(boolean z) {
        this.options.setPrettyPrinting(z);
    }

    public void setPrettyIndent(int i) {
        this.options.setPrettyIndent(i);
    }

    public boolean isIgnoreComments() {
        return this.options.isIgnoreComments();
    }

    public boolean isIgnoreProcessingInstructions() {
        return this.options.isIgnoreProcessingInstructions();
    }

    public boolean isIgnoreWhitespace() {
        return this.options.isIgnoreWhitespace();
    }

    public boolean isPrettyPrinting() {
        return this.options.isPrettyPrinting();
    }

    public int getPrettyIndent() {
        return this.options.getPrettyIndent();
    }

    private XMLLibImpl(Scriptable scriptable) {
        this.globalScope = scriptable;
    }

    @Deprecated
    QName qnamePrototype() {
        return this.qnamePrototype;
    }

    @Deprecated
    Scriptable globalScope() {
        return this.globalScope;
    }

    XmlProcessor getProcessor() {
        return this.options;
    }

    private void exportToScope(boolean z) {
        this.xmlPrototype = newXML(XmlNode.createText(this.options, ""));
        this.xmlListPrototype = newXMLList();
        this.namespacePrototype = Namespace.create(this.globalScope, null, Namespace.GLOBAL);
        this.qnamePrototype = QName.create(this, this.globalScope, null, XmlNode$QName.create(Namespace.create(""), ""));
        this.xmlPrototype.exportAsJSClass(z);
        this.xmlListPrototype.exportAsJSClass(z);
        this.namespacePrototype.exportAsJSClass(z);
        this.qnamePrototype.exportAsJSClass(z);
    }

    @Deprecated
    XMLName toAttributeName(Context context, Object obj) {
        if (obj instanceof XMLName) {
            return (XMLName) obj;
        }
        if (obj instanceof QName) {
            return XMLName.create(((QName) obj).getDelegate(), true, false);
        }
        if ((obj instanceof Boolean) || (obj instanceof Number) || obj == Undefined.instance || obj == null) {
            throw badXMLName(obj);
        }
        String str;
        if (obj instanceof String) {
            str = (String) obj;
        } else {
            str = ScriptRuntime.toString(obj);
        }
        if (str != null && str.equals("*")) {
            str = null;
        }
        return XMLName.create(XmlNode$QName.create(Namespace.create(""), str), true, false);
    }

    private static RuntimeException badXMLName(Object obj) {
        String str;
        if (obj instanceof Number) {
            str = "Can not construct XML name from number: ";
        } else if (obj instanceof Boolean) {
            str = "Can not construct XML name from boolean: ";
        } else if (obj == Undefined.instance || obj == null) {
            str = "Can not construct XML name from ";
        } else {
            throw new IllegalArgumentException(obj.toString());
        }
        return ScriptRuntime.typeError(str + ScriptRuntime.toString(obj));
    }

    XMLName toXMLNameFromString(Context context, String str) {
        return XMLName.create(getDefaultNamespaceURI(context), str);
    }

    XMLName toXMLName(Context context, Object obj) {
        if (obj instanceof XMLName) {
            return (XMLName) obj;
        }
        if (obj instanceof QName) {
            QName qName = (QName) obj;
            return XMLName.formProperty(qName.uri(), qName.localName());
        } else if (obj instanceof String) {
            return toXMLNameFromString(context, (String) obj);
        } else {
            if (!(obj instanceof Boolean) && !(obj instanceof Number) && obj != Undefined.instance && obj != null) {
                return toXMLNameFromString(context, ScriptRuntime.toString(obj));
            }
            throw badXMLName(obj);
        }
    }

    XMLName toXMLNameOrIndex(Context context, Object obj) {
        if (obj instanceof XMLName) {
            return (XMLName) obj;
        }
        long testUint32String;
        if (obj instanceof String) {
            XMLName xMLName;
            String str = (String) obj;
            testUint32String = ScriptRuntime.testUint32String(str);
            if (testUint32String >= 0) {
                ScriptRuntime.storeUint32Result(context, testUint32String);
                xMLName = null;
            } else {
                xMLName = toXMLNameFromString(context, str);
            }
            return xMLName;
        } else if (obj instanceof Number) {
            double doubleValue = ((Number) obj).doubleValue();
            r4 = (long) doubleValue;
            if (((double) r4) != doubleValue || 0 > r4 || r4 > 4294967295L) {
                throw badXMLName(obj);
            }
            ScriptRuntime.storeUint32Result(context, r4);
            return null;
        } else if (obj instanceof QName) {
            QName qName = (QName) obj;
            String uri = qName.uri();
            Object obj2 = null;
            if (uri != null && uri.length() == 0) {
                r4 = ScriptRuntime.testUint32String(uri);
                if (r4 >= 0) {
                    ScriptRuntime.storeUint32Result(context, r4);
                    obj2 = 1;
                }
            }
            if (obj2 == null) {
                return XMLName.formProperty(uri, qName.localName());
            }
            return null;
        } else if ((obj instanceof Boolean) || obj == Undefined.instance || obj == null) {
            throw badXMLName(obj);
        } else {
            String scriptRuntime = ScriptRuntime.toString(obj);
            testUint32String = ScriptRuntime.testUint32String(scriptRuntime);
            if (testUint32String < 0) {
                return toXMLNameFromString(context, scriptRuntime);
            }
            ScriptRuntime.storeUint32Result(context, testUint32String);
            return null;
        }
    }

    Object addXMLObjects(Context context, XMLObject xMLObject, XMLObject xMLObject2) {
        XMLList xMLList;
        XMLList newXMLList = newXMLList();
        if (xMLObject instanceof XMLList) {
            xMLList = (XMLList) xMLObject;
            if (xMLList.length() == 1) {
                newXMLList.addToList(xMLList.item(0));
                xMLList = newXMLList;
            } else {
                xMLList = newXMLListFrom(xMLObject);
            }
        } else {
            newXMLList.addToList(xMLObject);
            xMLList = newXMLList;
        }
        if (xMLObject2 instanceof XMLList) {
            XMLList xMLList2 = (XMLList) xMLObject2;
            for (int i = 0; i < xMLList2.length(); i++) {
                xMLList.addToList(xMLList2.item(i));
            }
        } else if (xMLObject2 instanceof XML) {
            xMLList.addToList(xMLObject2);
        }
        return xMLList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.mozilla.javascript.Ref xmlPrimaryReference(org.mozilla.javascript.Context r4, org.mozilla.javascript.xmlimpl.XMLName r5, org.mozilla.javascript.Scriptable r6) {
        /*
        r3 = this;
        r1 = 0;
    L_0x0001:
        r0 = r6 instanceof org.mozilla.javascript.xmlimpl.XMLWithScope;
        if (r0 == 0) goto L_0x0021;
    L_0x0005:
        r0 = r6.getPrototype();
        r0 = (org.mozilla.javascript.xmlimpl.XMLObjectImpl) r0;
        r2 = r0.hasXMLProperty(r5);
        if (r2 == 0) goto L_0x0017;
    L_0x0011:
        if (r0 == 0) goto L_0x0016;
    L_0x0013:
        r5.initXMLObject(r0);
    L_0x0016:
        return r5;
    L_0x0017:
        if (r1 != 0) goto L_0x0021;
    L_0x0019:
        r6 = r6.getParentScope();
        if (r6 == 0) goto L_0x0011;
    L_0x001f:
        r1 = r0;
        goto L_0x0001;
    L_0x0021:
        r0 = r1;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xmlimpl.XMLLibImpl.xmlPrimaryReference(org.mozilla.javascript.Context, org.mozilla.javascript.xmlimpl.XMLName, org.mozilla.javascript.Scriptable):org.mozilla.javascript.Ref");
    }

    Namespace castToNamespace(Context context, Object obj) {
        return this.namespacePrototype.castToNamespace(obj);
    }

    private String getDefaultNamespaceURI(Context context) {
        return getDefaultNamespace(context).uri();
    }

    Namespace newNamespace(String str) {
        return this.namespacePrototype.newNamespace(str);
    }

    Namespace getDefaultNamespace(Context context) {
        if (context == null) {
            context = Context.getCurrentContext();
            if (context == null) {
                return this.namespacePrototype;
            }
        }
        Object searchDefaultNamespace = ScriptRuntime.searchDefaultNamespace(context);
        if (searchDefaultNamespace == null) {
            return this.namespacePrototype;
        }
        if (searchDefaultNamespace instanceof Namespace) {
            return (Namespace) searchDefaultNamespace;
        }
        return this.namespacePrototype;
    }

    Namespace[] createNamespaces(Namespace[] namespaceArr) {
        Namespace[] namespaceArr2 = new Namespace[namespaceArr.length];
        for (int i = 0; i < namespaceArr.length; i++) {
            namespaceArr2[i] = this.namespacePrototype.newNamespace(namespaceArr[i].getPrefix(), namespaceArr[i].getUri());
        }
        return namespaceArr2;
    }

    QName constructQName(Context context, Object obj, Object obj2) {
        return this.qnamePrototype.constructQName(this, context, obj, obj2);
    }

    QName newQName(String str, String str2, String str3) {
        return this.qnamePrototype.newQName(this, str, str2, str3);
    }

    QName constructQName(Context context, Object obj) {
        return this.qnamePrototype.constructQName(this, context, obj);
    }

    QName castToQName(Context context, Object obj) {
        return this.qnamePrototype.castToQName(this, context, obj);
    }

    QName newQName(XmlNode$QName xmlNode$QName) {
        return QName.create(this, this.globalScope, this.qnamePrototype, xmlNode$QName);
    }

    XML newXML(XmlNode xmlNode) {
        return new XML(this, this.globalScope, this.xmlPrototype, xmlNode);
    }

    final XML newXMLFromJs(Object obj) {
        String str;
        if (obj == null || obj == Undefined.instance) {
            str = "";
        } else if (obj instanceof XMLObjectImpl) {
            str = ((XMLObjectImpl) obj).toXMLString();
        } else {
            str = ScriptRuntime.toString(obj);
        }
        if (str.trim().startsWith("<>")) {
            throw ScriptRuntime.typeError("Invalid use of XML object anonymous tags <></>.");
        } else if (str.indexOf("<") == -1) {
            return newXML(XmlNode.createText(this.options, str));
        } else {
            return parse(str);
        }
    }

    private XML parse(String str) {
        try {
            return newXML(XmlNode.createElement(this.options, getDefaultNamespaceURI(Context.getCurrentContext()), str));
        } catch (SAXException e) {
            throw ScriptRuntime.typeError("Cannot parse XML: " + e.getMessage());
        }
    }

    final XML ecmaToXml(Object obj) {
        if (obj == null || obj == Undefined.instance) {
            throw ScriptRuntime.typeError("Cannot convert " + obj + " to XML");
        } else if (obj instanceof XML) {
            return (XML) obj;
        } else {
            if (obj instanceof XMLList) {
                XMLList xMLList = (XMLList) obj;
                if (xMLList.getXML() != null) {
                    return xMLList.getXML();
                }
                throw ScriptRuntime.typeError("Cannot convert list of >1 element to XML");
            }
            Object unwrap;
            if (obj instanceof Wrapper) {
                unwrap = ((Wrapper) obj).unwrap();
            } else {
                unwrap = obj;
            }
            if (unwrap instanceof Node) {
                return newXML(XmlNode.createElementFromNode((Node) unwrap));
            }
            String scriptRuntime = ScriptRuntime.toString(unwrap);
            if (scriptRuntime.length() <= 0 || scriptRuntime.charAt(0) != '<') {
                return newXML(XmlNode.createText(this.options, scriptRuntime));
            }
            return parse(scriptRuntime);
        }
    }

    final XML newTextElementXML(XmlNode xmlNode, XmlNode$QName xmlNode$QName, String str) {
        return newXML(XmlNode.newElementWithText(this.options, xmlNode, xmlNode$QName, str));
    }

    XMLList newXMLList() {
        return new XMLList(this, this.globalScope, this.xmlListPrototype);
    }

    final XMLList newXMLListFrom(Object obj) {
        int i = 0;
        XMLList newXMLList = newXMLList();
        if (obj == null || (obj instanceof Undefined)) {
            return newXMLList;
        }
        if (obj instanceof XML) {
            newXMLList.getNodeList().add((XML) obj);
            return newXMLList;
        } else if (obj instanceof XMLList) {
            newXMLList.getNodeList().add(((XMLList) obj).getNodeList());
            return newXMLList;
        } else {
            String trim = ScriptRuntime.toString(obj).trim();
            if (!trim.startsWith("<>")) {
                trim = "<>" + trim + "</>";
            }
            trim = "<fragment>" + trim.substring(2);
            if (trim.endsWith("</>")) {
                XMLList children = newXMLFromJs(trim.substring(0, trim.length() - 3) + "</fragment>").children();
                while (i < children.getNodeList().length()) {
                    newXMLList.getNodeList().add((XML) children.item(i).copy());
                    i++;
                }
                return newXMLList;
            }
            throw ScriptRuntime.typeError("XML with anonymous tag missing end anonymous tag");
        }
    }

    XmlNode$QName toNodeQName(Context context, Object obj, Object obj2) {
        String localName;
        Namespace namespace;
        if (obj2 instanceof QName) {
            localName = ((QName) obj2).localName();
        } else {
            localName = ScriptRuntime.toString(obj2);
        }
        if (obj == Undefined.instance) {
            if ("*".equals(localName)) {
                namespace = null;
            } else {
                namespace = getDefaultNamespace(context).getDelegate();
            }
        } else if (obj == null) {
            namespace = null;
        } else if (obj instanceof Namespace) {
            namespace = ((Namespace) obj).getDelegate();
        } else {
            namespace = this.namespacePrototype.constructNamespace(obj).getDelegate();
        }
        if (localName != null && localName.equals("*")) {
            localName = null;
        }
        return XmlNode$QName.create(namespace, localName);
    }

    XmlNode$QName toNodeQName(Context context, String str, boolean z) {
        Namespace delegate = getDefaultNamespace(context).getDelegate();
        if (str != null && str.equals("*")) {
            return XmlNode$QName.create(null, null);
        }
        if (z) {
            return XmlNode$QName.create(Namespace.GLOBAL, str);
        }
        return XmlNode$QName.create(delegate, str);
    }

    XmlNode$QName toNodeQName(Context context, Object obj, boolean z) {
        if (obj instanceof XMLName) {
            return ((XMLName) obj).toQname();
        }
        if (obj instanceof QName) {
            return ((QName) obj).getDelegate();
        }
        if ((obj instanceof Boolean) || (obj instanceof Number) || obj == Undefined.instance || obj == null) {
            throw badXMLName(obj);
        }
        String str;
        if (obj instanceof String) {
            str = (String) obj;
        } else {
            str = ScriptRuntime.toString(obj);
        }
        return toNodeQName(context, str, z);
    }

    public boolean isXMLName(Context context, Object obj) {
        return XMLName.accept(obj);
    }

    public Object toDefaultXmlNamespace(Context context, Object obj) {
        return this.namespacePrototype.constructNamespace(obj);
    }

    public String escapeTextValue(Object obj) {
        return this.options.escapeTextValue(obj);
    }

    public String escapeAttributeValue(Object obj) {
        return this.options.escapeAttributeValue(obj);
    }

    public Ref nameRef(Context context, Object obj, Scriptable scriptable, int i) {
        if ((i & 2) != 0) {
            return xmlPrimaryReference(context, toAttributeName(context, obj), scriptable);
        }
        throw Kit.codeBug();
    }

    public Ref nameRef(Context context, Object obj, Object obj2, Scriptable scriptable, int i) {
        XMLName create = XMLName.create(toNodeQName(context, obj, obj2), false, false);
        if (!((i & 2) == 0 || create.isAttributeName())) {
            create.setAttributeName();
        }
        return xmlPrimaryReference(context, create, scriptable);
    }
}
