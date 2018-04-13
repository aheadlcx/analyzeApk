package org.mozilla.javascript.xmlimpl;

import com.alibaba.sdk.android.mns.common.MNSConstants;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.xml.XMLObject;
import org.w3c.dom.Node;
import tv.danmaku.ijk.media.player.FFmpegMediaMetadataRetriever;

class XML extends XMLObjectImpl {
    static final long serialVersionUID = -630969919086449092L;
    private XmlNode node;

    XML(XMLLibImpl xMLLibImpl, Scriptable scriptable, XMLObject xMLObject, XmlNode xmlNode) {
        super(xMLLibImpl, scriptable, xMLObject);
        initialize(xmlNode);
    }

    void initialize(XmlNode xmlNode) {
        this.node = xmlNode;
        this.node.setXml(this);
    }

    final XML getXML() {
        return this;
    }

    void replaceWith(XML xml) {
        if (this.node.parent() == null) {
            initialize(xml.node);
        } else {
            this.node.replaceWith(xml.node);
        }
    }

    XML makeXmlFromString(XMLName xMLName, String str) {
        try {
            return newTextElementXML(this.node, xMLName.toQname(), str);
        } catch (Exception e) {
            throw ScriptRuntime.typeError(e.getMessage());
        }
    }

    XmlNode getAnnotation() {
        return this.node;
    }

    public Object get(int i, Scriptable scriptable) {
        return i == 0 ? this : Scriptable.NOT_FOUND;
    }

    public boolean has(int i, Scriptable scriptable) {
        return i == 0;
    }

    public void put(int i, Scriptable scriptable, Object obj) {
        throw ScriptRuntime.typeError("Assignment to indexed XML is not allowed");
    }

    public Object[] getIds() {
        if (isPrototype()) {
            return new Object[0];
        }
        return new Object[]{Integer.valueOf(0)};
    }

    public void delete(int i) {
        if (i == 0) {
            remove();
        }
    }

    boolean hasXMLProperty(XMLName xMLName) {
        return getPropertyList(xMLName).length() > 0;
    }

    Object getXMLProperty(XMLName xMLName) {
        return getPropertyList(xMLName);
    }

    XmlNode$QName getNodeQname() {
        return this.node.getQname();
    }

    XML[] getChildren() {
        if (!isElement()) {
            return null;
        }
        XmlNode[] matchingChildren = this.node.getMatchingChildren(Filter.TRUE);
        XML[] xmlArr = new XML[matchingChildren.length];
        for (int i = 0; i < xmlArr.length; i++) {
            xmlArr[i] = toXML(matchingChildren[i]);
        }
        return xmlArr;
    }

    XML[] getAttributes() {
        XmlNode[] attributes = this.node.getAttributes();
        XML[] xmlArr = new XML[attributes.length];
        for (int i = 0; i < xmlArr.length; i++) {
            xmlArr[i] = toXML(attributes[i]);
        }
        return xmlArr;
    }

    XMLList getPropertyList(XMLName xMLName) {
        return xMLName.getMyValueOn(this);
    }

    void deleteXMLProperty(XMLName xMLName) {
        XMLList propertyList = getPropertyList(xMLName);
        for (int i = 0; i < propertyList.length(); i++) {
            propertyList.item(i).node.deleteMe();
        }
    }

    void putXMLProperty(XMLName xMLName, Object obj) {
        if (!isPrototype()) {
            xMLName.setMyValueOn(this, obj);
        }
    }

    boolean hasOwnProperty(XMLName xMLName) {
        if (isPrototype()) {
            if (findPrototypeId(xMLName.localName()) != 0) {
                return true;
            }
            return false;
        } else if (getPropertyList(xMLName).length() <= 0) {
            return false;
        } else {
            return true;
        }
    }

    protected Object jsConstructor(Context context, boolean z, Object[] objArr) {
        if (objArr.length == 0 || objArr[0] == null || objArr[0] == Undefined.instance) {
            objArr = new Object[]{""};
        }
        Object ecmaToXml = ecmaToXml(objArr[0]);
        if (z) {
            return ecmaToXml.copy();
        }
        return ecmaToXml;
    }

    public Scriptable getExtraMethodSource(Context context) {
        if (hasSimpleContent()) {
            return ScriptRuntime.toObjectOrNull(context, toString());
        }
        return null;
    }

    void removeChild(int i) {
        this.node.removeChild(i);
    }

    void normalize() {
        this.node.normalize();
    }

    private XML toXML(XmlNode xmlNode) {
        if (xmlNode.getXml() == null) {
            xmlNode.setXml(newXML(xmlNode));
        }
        return xmlNode.getXml();
    }

    void setAttribute(XMLName xMLName, Object obj) {
        if (!isElement()) {
            throw new IllegalStateException("Can only set attributes on elements.");
        } else if (xMLName.uri() == null && xMLName.localName().equals("*")) {
            throw ScriptRuntime.typeError("@* assignment not supported.");
        } else {
            this.node.setAttribute(xMLName.toQname(), ScriptRuntime.toString(obj));
        }
    }

    void remove() {
        this.node.deleteMe();
    }

    void addMatches(XMLList xMLList, XMLName xMLName) {
        xMLName.addMatches(xMLList, this);
    }

    XMLList elements(XMLName xMLName) {
        XMLList newXMLList = newXMLList();
        newXMLList.setTargets(this, xMLName.toQname());
        XmlNode[] matchingChildren = this.node.getMatchingChildren(Filter.ELEMENT);
        for (int i = 0; i < matchingChildren.length; i++) {
            if (xMLName.matches(toXML(matchingChildren[i]))) {
                newXMLList.addToList(toXML(matchingChildren[i]));
            }
        }
        return newXMLList;
    }

    XMLList child(XMLName xMLName) {
        XMLList newXMLList = newXMLList();
        XmlNode[] matchingChildren = this.node.getMatchingChildren(Filter.ELEMENT);
        for (int i = 0; i < matchingChildren.length; i++) {
            if (xMLName.matchesElement(matchingChildren[i].getQname())) {
                newXMLList.addToList(toXML(matchingChildren[i]));
            }
        }
        newXMLList.setTargets(this, xMLName.toQname());
        return newXMLList;
    }

    XML replace(XMLName xMLName, Object obj) {
        putXMLProperty(xMLName, obj);
        return this;
    }

    XMLList children() {
        XMLList newXMLList = newXMLList();
        newXMLList.setTargets(this, XMLName.formStar().toQname());
        XmlNode[] matchingChildren = this.node.getMatchingChildren(Filter.TRUE);
        for (XmlNode toXML : matchingChildren) {
            newXMLList.addToList(toXML(toXML));
        }
        return newXMLList;
    }

    XMLList child(int i) {
        XMLList newXMLList = newXMLList();
        newXMLList.setTargets(this, null);
        if (i >= 0 && i < this.node.getChildCount()) {
            newXMLList.addToList(getXmlChild(i));
        }
        return newXMLList;
    }

    XML getXmlChild(int i) {
        XmlNode child = this.node.getChild(i);
        if (child.getXml() == null) {
            child.setXml(newXML(child));
        }
        return child.getXml();
    }

    XML getLastXmlChild() {
        int childCount = this.node.getChildCount() - 1;
        if (childCount < 0) {
            return null;
        }
        return getXmlChild(childCount);
    }

    int childIndex() {
        return this.node.getChildIndex();
    }

    boolean contains(Object obj) {
        if (obj instanceof XML) {
            return equivalentXml(obj);
        }
        return false;
    }

    boolean equivalentXml(Object obj) {
        if (obj instanceof XML) {
            return this.node.toXmlString(getProcessor()).equals(((XML) obj).node.toXmlString(getProcessor()));
        }
        if (obj instanceof XMLList) {
            XMLList xMLList = (XMLList) obj;
            if (xMLList.length() == 1) {
                return equivalentXml(xMLList.getXML());
            }
            return false;
        } else if (!hasSimpleContent()) {
            return false;
        } else {
            return toString().equals(ScriptRuntime.toString(obj));
        }
    }

    XMLObjectImpl copy() {
        return newXML(this.node.copy());
    }

    boolean hasSimpleContent() {
        if (isComment() || isProcessingInstruction()) {
            return false;
        }
        if (isText() || this.node.isAttributeType() || !this.node.hasChildElement()) {
            return true;
        }
        return false;
    }

    boolean hasComplexContent() {
        return !hasSimpleContent();
    }

    int length() {
        return 1;
    }

    boolean is(XML xml) {
        return this.node.isSameNode(xml.node);
    }

    Object nodeKind() {
        return ecmaClass();
    }

    Object parent() {
        if (this.node.parent() == null) {
            return null;
        }
        return newXML(this.node.parent());
    }

    boolean propertyIsEnumerable(Object obj) {
        if (obj instanceof Integer) {
            if (((Integer) obj).intValue() == 0) {
                return true;
            }
            return false;
        } else if (!(obj instanceof Number)) {
            return ScriptRuntime.toString(obj).equals("0");
        } else {
            double doubleValue = ((Number) obj).doubleValue();
            if (doubleValue != 0.0d || 1.0d / doubleValue <= 0.0d) {
                return false;
            }
            return true;
        }
    }

    Object valueOf() {
        return this;
    }

    XMLList comments() {
        XMLList newXMLList = newXMLList();
        this.node.addMatchingChildren(newXMLList, Filter.COMMENT);
        return newXMLList;
    }

    XMLList text() {
        XMLList newXMLList = newXMLList();
        this.node.addMatchingChildren(newXMLList, Filter.TEXT);
        return newXMLList;
    }

    XMLList processingInstructions(XMLName xMLName) {
        XMLList newXMLList = newXMLList();
        this.node.addMatchingChildren(newXMLList, Filter.PROCESSING_INSTRUCTION(xMLName));
        return newXMLList;
    }

    private XmlNode[] getNodesForInsert(Object obj) {
        int i = 0;
        if (obj instanceof XML) {
            return new XmlNode[]{((XML) obj).node};
        } else if (obj instanceof XMLList) {
            XMLList xMLList = (XMLList) obj;
            XmlNode[] xmlNodeArr = new XmlNode[xMLList.length()];
            while (i < xMLList.length()) {
                xmlNodeArr[i] = xMLList.item(i).node;
                i++;
            }
            return xmlNodeArr;
        } else {
            return new XmlNode[]{XmlNode.createText(getProcessor(), ScriptRuntime.toString(obj))};
        }
    }

    XML replace(int i, Object obj) {
        XMLList child = child(i);
        if (child.length() > 0) {
            insertChildAfter(child.item(0), obj);
            removeChild(i);
        }
        return this;
    }

    XML prependChild(Object obj) {
        if (this.node.isParentType()) {
            this.node.insertChildrenAt(0, getNodesForInsert(obj));
        }
        return this;
    }

    XML appendChild(Object obj) {
        if (this.node.isParentType()) {
            this.node.insertChildrenAt(this.node.getChildCount(), getNodesForInsert(obj));
        }
        return this;
    }

    private int getChildIndexOf(XML xml) {
        for (int i = 0; i < this.node.getChildCount(); i++) {
            if (this.node.getChild(i).isSameNode(xml.node)) {
                return i;
            }
        }
        return -1;
    }

    XML insertChildBefore(XML xml, Object obj) {
        if (xml == null) {
            appendChild(obj);
        } else {
            XmlNode[] nodesForInsert = getNodesForInsert(obj);
            int childIndexOf = getChildIndexOf(xml);
            if (childIndexOf != -1) {
                this.node.insertChildrenAt(childIndexOf, nodesForInsert);
            }
        }
        return this;
    }

    XML insertChildAfter(XML xml, Object obj) {
        if (xml == null) {
            prependChild(obj);
        } else {
            XmlNode[] nodesForInsert = getNodesForInsert(obj);
            int childIndexOf = getChildIndexOf(xml);
            if (childIndexOf != -1) {
                this.node.insertChildrenAt(childIndexOf + 1, nodesForInsert);
            }
        }
        return this;
    }

    XML setChildren(Object obj) {
        if (isElement()) {
            while (this.node.getChildCount() > 0) {
                this.node.removeChild(0);
            }
            this.node.insertChildrenAt(0, getNodesForInsert(obj));
        }
        return this;
    }

    private void addInScopeNamespace(Namespace namespace) {
        if (!isElement() || namespace.prefix() == null) {
            return;
        }
        if (namespace.prefix().length() != 0 || namespace.uri().length() != 0) {
            if (this.node.getQname().getNamespace().getPrefix().equals(namespace.prefix())) {
                this.node.invalidateNamespacePrefix();
            }
            this.node.declareNamespace(namespace.prefix(), namespace.uri());
        }
    }

    Namespace[] inScopeNamespaces() {
        return createNamespaces(this.node.getInScopeNamespaces());
    }

    private Namespace adapt(Namespace namespace) {
        if (namespace.prefix() == null) {
            return Namespace.create(namespace.uri());
        }
        return Namespace.create(namespace.prefix(), namespace.uri());
    }

    XML removeNamespace(Namespace namespace) {
        if (isElement()) {
            this.node.removeNamespace(adapt(namespace));
        }
        return this;
    }

    XML addNamespace(Namespace namespace) {
        addInScopeNamespace(namespace);
        return this;
    }

    QName name() {
        if (isText() || isComment()) {
            return null;
        }
        if (isProcessingInstruction()) {
            return newQName("", this.node.getQname().getLocalName(), null);
        }
        return newQName(this.node.getQname());
    }

    Namespace[] namespaceDeclarations() {
        return createNamespaces(this.node.getNamespaceDeclarations());
    }

    Namespace namespace(String str) {
        if (str == null) {
            return createNamespace(this.node.getNamespaceDeclaration());
        }
        return createNamespace(this.node.getNamespaceDeclaration(str));
    }

    String localName() {
        if (name() == null) {
            return null;
        }
        return name().localName();
    }

    void setLocalName(String str) {
        if (!isText() && !isComment()) {
            this.node.setLocalName(str);
        }
    }

    void setName(QName qName) {
        if (!isText() && !isComment()) {
            if (isProcessingInstruction()) {
                this.node.setLocalName(qName.localName());
            } else {
                this.node.renameNode(qName.getDelegate());
            }
        }
    }

    void setNamespace(Namespace namespace) {
        if (!isText() && !isComment() && !isProcessingInstruction()) {
            setName(newQName(namespace.uri(), localName(), namespace.prefix()));
        }
    }

    final String ecmaClass() {
        if (this.node.isTextType()) {
            return "text";
        }
        if (this.node.isAttributeType()) {
            return "attribute";
        }
        if (this.node.isCommentType()) {
            return FFmpegMediaMetadataRetriever.METADATA_KEY_COMMENT;
        }
        if (this.node.isProcessingInstructionType()) {
            return "processing-instruction";
        }
        if (this.node.isElementType()) {
            return "element";
        }
        throw new RuntimeException("Unrecognized type: " + this.node);
    }

    public String getClassName() {
        return MNSConstants.DEFAULT_NOTIFY_CONTENT_TYPE;
    }

    private String ecmaValue() {
        return this.node.ecmaValue();
    }

    private String ecmaToString() {
        if (isAttribute() || isText()) {
            return ecmaValue();
        }
        if (!hasSimpleContent()) {
            return toXMLString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.node.getChildCount(); i++) {
            XmlNode child = this.node.getChild(i);
            if (!(child.isProcessingInstructionType() || child.isCommentType())) {
                stringBuilder.append(new XML(getLib(), getParentScope(), (XMLObject) getPrototype(), child).toString());
            }
        }
        return stringBuilder.toString();
    }

    public String toString() {
        return ecmaToString();
    }

    String toSource(int i) {
        return toXMLString();
    }

    String toXMLString() {
        return this.node.ecmaToXMLString(getProcessor());
    }

    final boolean isAttribute() {
        return this.node.isAttributeType();
    }

    final boolean isComment() {
        return this.node.isCommentType();
    }

    final boolean isText() {
        return this.node.isTextType();
    }

    final boolean isElement() {
        return this.node.isElementType();
    }

    final boolean isProcessingInstruction() {
        return this.node.isProcessingInstructionType();
    }

    Node toDomNode() {
        return this.node.toDomNode();
    }
}
