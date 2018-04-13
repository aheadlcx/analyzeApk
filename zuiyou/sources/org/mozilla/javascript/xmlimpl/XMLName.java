package org.mozilla.javascript.xmlimpl;

import com.tencent.tinker.android.dx.instruction.Opcodes;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.EcmaError;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Ref;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.xmlimpl.XmlNode.Namespace;

class XMLName extends Ref {
    static final long serialVersionUID = 3832176310755686977L;
    private boolean isAttributeName;
    private boolean isDescendants;
    private XmlNode$QName qname;
    private XMLObjectImpl xmlObject;

    private static boolean isNCNameStartChar(int i) {
        if ((i & -128) == 0) {
            if (i >= 97) {
                if (i <= 122) {
                    return true;
                }
                return false;
            } else if (i >= 65) {
                if (i <= 90 || i == 95) {
                    return true;
                }
                return false;
            }
        } else if ((i & -8192) == 0) {
            if (192 <= i && i <= Opcodes.OR_INT_LIT16) {
                return true;
            }
            if (Opcodes.ADD_INT_LIT8 <= i && i <= 246) {
                return true;
            }
            if (248 <= i && i <= 767) {
                return true;
            }
            if ((880 > i || i > 893) && 895 > i) {
                return false;
            }
            return true;
        }
        if (8204 <= i && i <= 8205) {
            return true;
        }
        if (8304 <= i && i <= 8591) {
            return true;
        }
        if (11264 <= i && i <= 12271) {
            return true;
        }
        if (12289 <= i && i <= 55295) {
            return true;
        }
        if (63744 <= i && i <= 64975) {
            return true;
        }
        if (65008 <= i && i <= 65533) {
            return true;
        }
        if (65536 > i || i > 983039) {
            return false;
        }
        return true;
    }

    private static boolean isNCNameChar(int i) {
        boolean z = false;
        if ((i & -128) == 0) {
            if (i >= 97) {
                if (i <= 122) {
                    return true;
                }
                return false;
            } else if (i >= 65) {
                if (i <= 90 || i == 95) {
                    return true;
                }
                return false;
            } else if (i < 48) {
                if (i == 45 || i == 46) {
                    z = true;
                }
                return z;
            } else if (i > 57) {
                return false;
            } else {
                return true;
            }
        } else if ((i & -8192) == 0) {
            if (isNCNameStartChar(i) || i == 183 || (Opcodes.FILL_ARRAY_DATA_PAYLOAD <= i && i <= 879)) {
                z = true;
            }
            return z;
        } else {
            if (isNCNameStartChar(i) || (8255 <= i && i <= 8256)) {
                z = true;
            }
            return z;
        }
    }

    static boolean accept(Object obj) {
        try {
            String scriptRuntime = ScriptRuntime.toString(obj);
            int length = scriptRuntime.length();
            if (length == 0 || !isNCNameStartChar(scriptRuntime.charAt(0))) {
                return false;
            }
            for (int i = 1; i != length; i++) {
                if (!isNCNameChar(scriptRuntime.charAt(i))) {
                    return false;
                }
            }
            return true;
        } catch (EcmaError e) {
            if ("TypeError".equals(e.getName())) {
                return false;
            }
            throw e;
        }
    }

    private XMLName() {
    }

    static XMLName formStar() {
        XMLName xMLName = new XMLName();
        xMLName.qname = XmlNode$QName.create(null, null);
        return xMLName;
    }

    @Deprecated
    static XMLName formProperty(Namespace namespace, String str) {
        if (str != null && str.equals("*")) {
            str = null;
        }
        XMLName xMLName = new XMLName();
        xMLName.qname = XmlNode$QName.create(namespace, str);
        return xMLName;
    }

    static XMLName formProperty(String str, String str2) {
        return formProperty(Namespace.create(str), str2);
    }

    static XMLName create(String str, String str2) {
        if (str2 == null) {
            throw new IllegalArgumentException();
        }
        int length = str2.length();
        if (length != 0) {
            char charAt = str2.charAt(0);
            if (charAt == '*') {
                if (length == 1) {
                    return formStar();
                }
            } else if (charAt == '@') {
                XMLName formProperty = formProperty("", str2.substring(1));
                formProperty.setAttributeName();
                return formProperty;
            }
        }
        return formProperty(str, str2);
    }

    static XMLName create(XmlNode$QName xmlNode$QName, boolean z, boolean z2) {
        XMLName xMLName = new XMLName();
        xMLName.qname = xmlNode$QName;
        xMLName.isAttributeName = z;
        xMLName.isDescendants = z2;
        return xMLName;
    }

    @Deprecated
    static XMLName create(XmlNode$QName xmlNode$QName) {
        return create(xmlNode$QName, false, false);
    }

    void initXMLObject(XMLObjectImpl xMLObjectImpl) {
        if (xMLObjectImpl == null) {
            throw new IllegalArgumentException();
        } else if (this.xmlObject != null) {
            throw new IllegalStateException();
        } else {
            this.xmlObject = xMLObjectImpl;
        }
    }

    String uri() {
        if (this.qname.getNamespace() == null) {
            return null;
        }
        return this.qname.getNamespace().getUri();
    }

    String localName() {
        if (this.qname.getLocalName() == null) {
            return "*";
        }
        return this.qname.getLocalName();
    }

    private void addDescendantChildren(XMLList xMLList, XML xml) {
        if (xml.isElement()) {
            XML[] children = xml.getChildren();
            for (int i = 0; i < children.length; i++) {
                if (matches(children[i])) {
                    xMLList.addToList(children[i]);
                }
                addDescendantChildren(xMLList, children[i]);
            }
        }
    }

    void addMatchingAttributes(XMLList xMLList, XML xml) {
        if (xml.isElement()) {
            XML[] attributes = xml.getAttributes();
            for (int i = 0; i < attributes.length; i++) {
                if (matches(attributes[i])) {
                    xMLList.addToList(attributes[i]);
                }
            }
        }
    }

    private void addDescendantAttributes(XMLList xMLList, XML xml) {
        if (xml.isElement()) {
            addMatchingAttributes(xMLList, xml);
            XML[] children = xml.getChildren();
            for (XML addDescendantAttributes : children) {
                addDescendantAttributes(xMLList, addDescendantAttributes);
            }
        }
    }

    XMLList matchDescendantAttributes(XMLList xMLList, XML xml) {
        xMLList.setTargets(xml, null);
        addDescendantAttributes(xMLList, xml);
        return xMLList;
    }

    XMLList matchDescendantChildren(XMLList xMLList, XML xml) {
        xMLList.setTargets(xml, null);
        addDescendantChildren(xMLList, xml);
        return xMLList;
    }

    void addDescendants(XMLList xMLList, XML xml) {
        if (isAttributeName()) {
            matchDescendantAttributes(xMLList, xml);
        } else {
            matchDescendantChildren(xMLList, xml);
        }
    }

    private void addAttributes(XMLList xMLList, XML xml) {
        addMatchingAttributes(xMLList, xml);
    }

    void addMatches(XMLList xMLList, XML xml) {
        if (isDescendants()) {
            addDescendants(xMLList, xml);
        } else if (isAttributeName()) {
            addAttributes(xMLList, xml);
        } else {
            XML[] children = xml.getChildren();
            if (children != null) {
                for (int i = 0; i < children.length; i++) {
                    if (matches(children[i])) {
                        xMLList.addToList(children[i]);
                    }
                }
            }
            xMLList.setTargets(xml, toQname());
        }
    }

    XMLList getMyValueOn(XML xml) {
        XMLList newXMLList = xml.newXMLList();
        addMatches(newXMLList, xml);
        return newXMLList;
    }

    void setMyValueOn(XML xml, Object obj) {
        Object obj2;
        if (obj == null) {
            obj2 = "null";
        } else if (obj instanceof Undefined) {
            obj2 = "undefined";
        } else {
            obj2 = obj;
        }
        if (isAttributeName()) {
            xml.setAttribute(this, obj2);
        } else if (uri() == null && localName().equals("*")) {
            xml.setChildren(obj2);
        } else {
            Object makeXmlFromString;
            if (obj2 instanceof XMLObjectImpl) {
                XMLObjectImpl xMLObjectImpl = (XMLObjectImpl) obj2;
                if ((xMLObjectImpl instanceof XML) && ((XML) xMLObjectImpl).isAttribute()) {
                    makeXmlFromString = xml.makeXmlFromString(this, xMLObjectImpl.toString());
                } else {
                    XMLObjectImpl xMLObjectImpl2 = xMLObjectImpl;
                }
                if (makeXmlFromString instanceof XMLList) {
                    for (int i = 0; i < makeXmlFromString.length(); i++) {
                        XML item = ((XMLList) makeXmlFromString).item(i);
                        if (item.isAttribute()) {
                            ((XMLList) makeXmlFromString).replace(i, xml.makeXmlFromString(this, item.toString()));
                        }
                    }
                }
            } else {
                makeXmlFromString = xml.makeXmlFromString(this, ScriptRuntime.toString(obj2));
            }
            XMLList propertyList = xml.getPropertyList(this);
            if (propertyList.length() == 0) {
                xml.appendChild(makeXmlFromString);
                return;
            }
            for (int i2 = 1; i2 < propertyList.length(); i2++) {
                xml.removeChild(propertyList.item(i2).childIndex());
            }
            xml.replace(propertyList.item(0).childIndex(), makeXmlFromString);
        }
    }

    public boolean has(Context context) {
        if (this.xmlObject == null) {
            return false;
        }
        return this.xmlObject.hasXMLProperty(this);
    }

    public Object get(Context context) {
        if (this.xmlObject != null) {
            return this.xmlObject.getXMLProperty(this);
        }
        throw ScriptRuntime.undefReadError(Undefined.instance, toString());
    }

    public Object set(Context context, Object obj) {
        if (this.xmlObject == null) {
            throw ScriptRuntime.undefWriteError(Undefined.instance, toString(), obj);
        } else if (this.isDescendants) {
            throw Kit.codeBug();
        } else {
            this.xmlObject.putXMLProperty(this, obj);
            return obj;
        }
    }

    public boolean delete(Context context) {
        if (this.xmlObject == null) {
            return true;
        }
        this.xmlObject.deleteXMLProperty(this);
        if (this.xmlObject.hasXMLProperty(this)) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.isDescendants) {
            stringBuilder.append("..");
        }
        if (this.isAttributeName) {
            stringBuilder.append('@');
        }
        if (uri() == null) {
            stringBuilder.append('*');
            if (localName().equals("*")) {
                return stringBuilder.toString();
            }
        }
        stringBuilder.append('\"').append(uri()).append('\"');
        stringBuilder.append(':').append(localName());
        return stringBuilder.toString();
    }

    final XmlNode$QName toQname() {
        return this.qname;
    }

    final boolean matchesLocalName(String str) {
        return localName().equals("*") || localName().equals(str);
    }

    final boolean matchesElement(XmlNode$QName xmlNode$QName) {
        if ((uri() == null || uri().equals(xmlNode$QName.getNamespace().getUri())) && (localName().equals("*") || localName().equals(xmlNode$QName.getLocalName()))) {
            return true;
        }
        return false;
    }

    final boolean matches(XML xml) {
        XmlNode$QName nodeQname = xml.getNodeQname();
        Object obj = null;
        if (nodeQname.getNamespace() != null) {
            obj = nodeQname.getNamespace().getUri();
        }
        if (!this.isAttributeName) {
            if (uri() == null || (xml.isElement() && uri().equals(r0))) {
                if (localName().equals("*")) {
                    return true;
                }
                if (xml.isElement() && localName().equals(nodeQname.getLocalName())) {
                    return true;
                }
            }
            return false;
        } else if (!xml.isAttribute()) {
            return false;
        } else {
            if ((uri() == null || uri().equals(r0)) && (localName().equals("*") || localName().equals(nodeQname.getLocalName()))) {
                return true;
            }
            return false;
        }
    }

    boolean isAttributeName() {
        return this.isAttributeName;
    }

    void setAttributeName() {
        this.isAttributeName = true;
    }

    boolean isDescendants() {
        return this.isDescendants;
    }

    @Deprecated
    void setIsDescendants() {
        this.isDescendants = true;
    }
}
