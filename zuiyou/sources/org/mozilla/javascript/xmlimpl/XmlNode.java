package org.mozilla.javascript.xmlimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mozilla.javascript.Undefined;
import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;
import org.xml.sax.SAXException;

class XmlNode implements Serializable {
    private static final boolean DOM_LEVEL_3 = true;
    private static final String USER_DATA_XMLNODE_KEY = XmlNode.class.getName();
    private static final String XML_NAMESPACES_NAMESPACE_URI = "http://www.w3.org/2000/xmlns/";
    private static final long serialVersionUID = 1;
    private Node dom;
    private UserDataHandler events = new XmlNodeUserDataHandler();
    private XML xml;

    static abstract class Filter {
        static final Filter COMMENT = new Filter() {
            boolean accept(Node node) {
                return node.getNodeType() == (short) 8;
            }
        };
        static Filter ELEMENT = new Filter() {
            boolean accept(Node node) {
                return node.getNodeType() == (short) 1;
            }
        };
        static final Filter TEXT = new Filter() {
            boolean accept(Node node) {
                return node.getNodeType() == (short) 3;
            }
        };
        static Filter TRUE = new Filter() {
            boolean accept(Node node) {
                return true;
            }
        };

        abstract boolean accept(Node node);

        Filter() {
        }

        static Filter PROCESSING_INSTRUCTION(final XMLName xMLName) {
            return new Filter() {
                boolean accept(Node node) {
                    if (node.getNodeType() != (short) 7) {
                        return false;
                    }
                    return xMLName.matchesLocalName(((ProcessingInstruction) node).getTarget());
                }
            };
        }
    }

    static class InternalList implements Serializable {
        private static final long serialVersionUID = -3633151157292048978L;
        private List<XmlNode> list = new ArrayList();

        InternalList() {
        }

        private void _add(XmlNode xmlNode) {
            this.list.add(xmlNode);
        }

        XmlNode item(int i) {
            return (XmlNode) this.list.get(i);
        }

        void remove(int i) {
            this.list.remove(i);
        }

        void add(InternalList internalList) {
            for (int i = 0; i < internalList.length(); i++) {
                _add(internalList.item(i));
            }
        }

        void add(InternalList internalList, int i, int i2) {
            while (i < i2) {
                _add(internalList.item(i));
                i++;
            }
        }

        void add(XmlNode xmlNode) {
            _add(xmlNode);
        }

        void add(XML xml) {
            _add(xml.getAnnotation());
        }

        void addToList(Object obj) {
            if (!(obj instanceof Undefined)) {
                if (obj instanceof XMLList) {
                    XMLList xMLList = (XMLList) obj;
                    for (int i = 0; i < xMLList.length(); i++) {
                        _add(xMLList.item(i).getAnnotation());
                    }
                } else if (obj instanceof XML) {
                    _add(((XML) obj).getAnnotation());
                } else if (obj instanceof XmlNode) {
                    _add((XmlNode) obj);
                }
            }
        }

        int length() {
            return this.list.size();
        }
    }

    static class Namespace implements Serializable {
        static final Namespace GLOBAL = create("", "");
        private static final long serialVersionUID = 4073904386884677090L;
        private String prefix;
        private String uri;

        static Namespace create(String str, String str2) {
            if (str == null) {
                throw new IllegalArgumentException("Empty string represents default namespace prefix");
            } else if (str2 == null) {
                throw new IllegalArgumentException("Namespace may not lack a URI");
            } else {
                Namespace namespace = new Namespace();
                namespace.prefix = str;
                namespace.uri = str2;
                return namespace;
            }
        }

        static Namespace create(String str) {
            Namespace namespace = new Namespace();
            namespace.uri = str;
            if (str == null || str.length() == 0) {
                namespace.prefix = "";
            }
            return namespace;
        }

        private Namespace() {
        }

        public String toString() {
            if (this.prefix == null) {
                return "XmlNode.Namespace [" + this.uri + "]";
            }
            return "XmlNode.Namespace [" + this.prefix + "{" + this.uri + "}]";
        }

        boolean isUnspecifiedPrefix() {
            return this.prefix == null;
        }

        boolean is(Namespace namespace) {
            return this.prefix != null && namespace.prefix != null && this.prefix.equals(namespace.prefix) && this.uri.equals(namespace.uri);
        }

        boolean isEmpty() {
            return this.prefix != null && this.prefix.equals("") && this.uri.equals("");
        }

        boolean isDefault() {
            return this.prefix != null && this.prefix.equals("");
        }

        boolean isGlobal() {
            return this.uri != null && this.uri.equals("");
        }

        private void setPrefix(String str) {
            if (str == null) {
                throw new IllegalArgumentException();
            }
            this.prefix = str;
        }

        String getPrefix() {
            return this.prefix;
        }

        String getUri() {
            return this.uri;
        }
    }

    private static class Namespaces {
        private Map<String, String> map = new HashMap();
        private Map<String, String> uriToPrefix = new HashMap();

        Namespaces() {
        }

        void declare(Namespace namespace) {
            if (this.map.get(namespace.prefix) == null) {
                this.map.put(namespace.prefix, namespace.uri);
            }
            if (this.uriToPrefix.get(namespace.uri) == null) {
                this.uriToPrefix.put(namespace.uri, namespace.prefix);
            }
        }

        Namespace getNamespaceByUri(String str) {
            if (this.uriToPrefix.get(str) == null) {
                return null;
            }
            return Namespace.create(str, (String) this.uriToPrefix.get(str));
        }

        Namespace getNamespace(String str) {
            if (this.map.get(str) == null) {
                return null;
            }
            return Namespace.create(str, (String) this.map.get(str));
        }

        Namespace[] getNamespaces() {
            ArrayList arrayList = new ArrayList();
            for (String str : this.map.keySet()) {
                Namespace create = Namespace.create(str, (String) this.map.get(str));
                if (!create.isEmpty()) {
                    arrayList.add(create);
                }
            }
            return (Namespace[]) arrayList.toArray(new Namespace[arrayList.size()]);
        }
    }

    static class XmlNodeUserDataHandler implements Serializable, UserDataHandler {
        private static final long serialVersionUID = 4666895518900769588L;

        XmlNodeUserDataHandler() {
        }

        public void handle(short s, String str, Object obj, Node node, Node node2) {
        }
    }

    private static XmlNode getUserData(Node node) {
        return (XmlNode) node.getUserData(USER_DATA_XMLNODE_KEY);
    }

    private static void setUserData(Node node, XmlNode xmlNode) {
        node.setUserData(USER_DATA_XMLNODE_KEY, xmlNode, xmlNode.events);
    }

    private static XmlNode createImpl(Node node) {
        if (node instanceof Document) {
            throw new IllegalArgumentException();
        } else if (getUserData(node) != null) {
            return getUserData(node);
        } else {
            XmlNode xmlNode = new XmlNode();
            xmlNode.dom = node;
            setUserData(node, xmlNode);
            return xmlNode;
        }
    }

    static XmlNode newElementWithText(XmlProcessor xmlProcessor, XmlNode xmlNode, XmlNode$QName xmlNode$QName, String str) {
        if (xmlNode instanceof Document) {
            throw new IllegalArgumentException("Cannot use Document node as reference");
        }
        Document ownerDocument;
        Node node;
        if (xmlNode != null) {
            ownerDocument = xmlNode.dom.getOwnerDocument();
        } else {
            ownerDocument = xmlProcessor.newDocument();
        }
        if (xmlNode != null) {
            node = xmlNode.dom;
        } else {
            node = null;
        }
        Namespace namespace = xmlNode$QName.getNamespace();
        node = (namespace == null || namespace.getUri().length() == 0) ? ownerDocument.createElementNS(null, xmlNode$QName.getLocalName()) : ownerDocument.createElementNS(namespace.getUri(), xmlNode$QName.qualify(node));
        if (str != null) {
            node.appendChild(ownerDocument.createTextNode(str));
        }
        return createImpl(node);
    }

    static XmlNode createText(XmlProcessor xmlProcessor, String str) {
        return createImpl(xmlProcessor.newDocument().createTextNode(str));
    }

    static XmlNode createElementFromNode(Node node) {
        if (node instanceof Document) {
            node = ((Document) node).getDocumentElement();
        }
        return createImpl(node);
    }

    static XmlNode createElement(XmlProcessor xmlProcessor, String str, String str2) throws SAXException {
        return createImpl(xmlProcessor.toXml(str, str2));
    }

    static XmlNode createEmpty(XmlProcessor xmlProcessor) {
        return createText(xmlProcessor, "");
    }

    private static XmlNode copy(XmlNode xmlNode) {
        return createImpl(xmlNode.dom.cloneNode(true));
    }

    private XmlNode() {
    }

    String debug() {
        XmlProcessor xmlProcessor = new XmlProcessor();
        xmlProcessor.setIgnoreComments(false);
        xmlProcessor.setIgnoreProcessingInstructions(false);
        xmlProcessor.setIgnoreWhitespace(false);
        xmlProcessor.setPrettyPrinting(false);
        return xmlProcessor.ecmaToXmlString(this.dom);
    }

    public String toString() {
        return "XmlNode: type=" + this.dom.getNodeType() + " dom=" + this.dom.toString();
    }

    XML getXml() {
        return this.xml;
    }

    void setXml(XML xml) {
        this.xml = xml;
    }

    int getChildCount() {
        return this.dom.getChildNodes().getLength();
    }

    XmlNode parent() {
        Node parentNode = this.dom.getParentNode();
        if ((parentNode instanceof Document) || parentNode == null) {
            return null;
        }
        return createImpl(parentNode);
    }

    int getChildIndex() {
        int i = -1;
        if (!(isAttributeType() || parent() == null)) {
            NodeList childNodes = this.dom.getParentNode().getChildNodes();
            i = 0;
            while (i < childNodes.getLength()) {
                if (childNodes.item(i) != this.dom) {
                    i++;
                }
            }
            throw new RuntimeException("Unreachable.");
        }
        return i;
    }

    void removeChild(int i) {
        this.dom.removeChild(this.dom.getChildNodes().item(i));
    }

    String toXmlString(XmlProcessor xmlProcessor) {
        return xmlProcessor.ecmaToXmlString(this.dom);
    }

    String ecmaValue() {
        if (isTextType()) {
            return ((Text) this.dom).getData();
        }
        if (isAttributeType()) {
            return ((Attr) this.dom).getValue();
        }
        if (isProcessingInstructionType()) {
            return ((ProcessingInstruction) this.dom).getData();
        }
        if (isCommentType()) {
            return ((Comment) this.dom).getNodeValue();
        }
        if (isElementType()) {
            throw new RuntimeException("Unimplemented ecmaValue() for elements.");
        }
        throw new RuntimeException("Unimplemented for node " + this.dom);
    }

    void deleteMe() {
        if (this.dom instanceof Attr) {
            Attr attr = (Attr) this.dom;
            attr.getOwnerElement().getAttributes().removeNamedItemNS(attr.getNamespaceURI(), attr.getLocalName());
        } else if (this.dom.getParentNode() != null) {
            this.dom.getParentNode().removeChild(this.dom);
        }
    }

    void normalize() {
        this.dom.normalize();
    }

    void insertChildAt(int i, XmlNode xmlNode) {
        Node node = this.dom;
        Node importNode = node.getOwnerDocument().importNode(xmlNode.dom, true);
        if (node.getChildNodes().getLength() < i) {
            throw new IllegalArgumentException("index=" + i + " length=" + node.getChildNodes().getLength());
        } else if (node.getChildNodes().getLength() == i) {
            node.appendChild(importNode);
        } else {
            node.insertBefore(importNode, node.getChildNodes().item(i));
        }
    }

    void insertChildrenAt(int i, XmlNode[] xmlNodeArr) {
        for (int i2 = 0; i2 < xmlNodeArr.length; i2++) {
            insertChildAt(i + i2, xmlNodeArr[i2]);
        }
    }

    XmlNode getChild(int i) {
        return createImpl(this.dom.getChildNodes().item(i));
    }

    boolean hasChildElement() {
        NodeList childNodes = this.dom.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i).getNodeType() == (short) 1) {
                return true;
            }
        }
        return false;
    }

    boolean isSameNode(XmlNode xmlNode) {
        return this.dom == xmlNode.dom;
    }

    private String toUri(String str) {
        return str == null ? "" : str;
    }

    private void addNamespaces(Namespaces namespaces, Element element) {
        if (element == null) {
            throw new RuntimeException("element must not be null");
        }
        String toUri = toUri(element.lookupNamespaceURI(null));
        Object obj = "";
        if (element.getParentNode() != null) {
            obj = toUri(element.getParentNode().lookupNamespaceURI(null));
        }
        if (!(toUri.equals(obj) && (element.getParentNode() instanceof Element))) {
            namespaces.declare(Namespace.create("", toUri));
        }
        NamedNodeMap attributes = element.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Attr attr = (Attr) attributes.item(i);
            if (attr.getPrefix() != null && attr.getPrefix().equals("xmlns")) {
                namespaces.declare(Namespace.create(attr.getLocalName(), attr.getValue()));
            }
        }
    }

    private Namespaces getAllNamespaces() {
        Node ownerElement;
        Namespaces namespaces = new Namespaces();
        Node node = this.dom;
        if (node instanceof Attr) {
            ownerElement = ((Attr) node).getOwnerElement();
        } else {
            ownerElement = node;
        }
        while (ownerElement != null) {
            if (ownerElement instanceof Element) {
                addNamespaces(namespaces, (Element) ownerElement);
            }
            ownerElement = ownerElement.getParentNode();
        }
        namespaces.declare(Namespace.create("", ""));
        return namespaces;
    }

    Namespace[] getInScopeNamespaces() {
        return getAllNamespaces().getNamespaces();
    }

    Namespace[] getNamespaceDeclarations() {
        if (!(this.dom instanceof Element)) {
            return new Namespace[0];
        }
        Namespaces namespaces = new Namespaces();
        addNamespaces(namespaces, (Element) this.dom);
        return namespaces.getNamespaces();
    }

    Namespace getNamespaceDeclaration(String str) {
        if (str.equals("") && (this.dom instanceof Attr)) {
            return Namespace.create("", "");
        }
        return getAllNamespaces().getNamespace(str);
    }

    Namespace getNamespaceDeclaration() {
        if (this.dom.getPrefix() == null) {
            return getNamespaceDeclaration("");
        }
        return getNamespaceDeclaration(this.dom.getPrefix());
    }

    final XmlNode copy() {
        return copy(this);
    }

    final boolean isParentType() {
        return isElementType();
    }

    final boolean isTextType() {
        return this.dom.getNodeType() == (short) 3 || this.dom.getNodeType() == (short) 4;
    }

    final boolean isAttributeType() {
        return this.dom.getNodeType() == (short) 2;
    }

    final boolean isProcessingInstructionType() {
        return this.dom.getNodeType() == (short) 7;
    }

    final boolean isCommentType() {
        return this.dom.getNodeType() == (short) 8;
    }

    final boolean isElementType() {
        return this.dom.getNodeType() == (short) 1;
    }

    final void renameNode(XmlNode$QName xmlNode$QName) {
        this.dom = this.dom.getOwnerDocument().renameNode(this.dom, xmlNode$QName.getNamespace().getUri(), xmlNode$QName.qualify(this.dom));
    }

    void invalidateNamespacePrefix() {
        if (this.dom instanceof Element) {
            String prefix = this.dom.getPrefix();
            renameNode(XmlNode$QName.create(this.dom.getNamespaceURI(), this.dom.getLocalName(), null));
            NamedNodeMap attributes = this.dom.getAttributes();
            for (int i = 0; i < attributes.getLength(); i++) {
                if (attributes.item(i).getPrefix().equals(prefix)) {
                    createImpl(attributes.item(i)).renameNode(XmlNode$QName.create(attributes.item(i).getNamespaceURI(), attributes.item(i).getLocalName(), null));
                }
            }
            return;
        }
        throw new IllegalStateException();
    }

    private void declareNamespace(Element element, String str, String str2) {
        if (str.length() > 0) {
            element.setAttributeNS(XML_NAMESPACES_NAMESPACE_URI, "xmlns:" + str, str2);
        } else {
            element.setAttribute("xmlns", str2);
        }
    }

    void declareNamespace(String str, String str2) {
        if (!(this.dom instanceof Element)) {
            throw new IllegalStateException();
        } else if (this.dom.lookupNamespaceURI(str2) == null || !this.dom.lookupNamespaceURI(str2).equals(str)) {
            declareNamespace((Element) this.dom, str, str2);
        }
    }

    private Namespace getDefaultNamespace() {
        return Namespace.create("", this.dom.lookupNamespaceURI(null) == null ? "" : this.dom.lookupNamespaceURI(null));
    }

    private String getExistingPrefixFor(Namespace namespace) {
        if (getDefaultNamespace().getUri().equals(namespace.getUri())) {
            return "";
        }
        return this.dom.lookupPrefix(namespace.getUri());
    }

    private Namespace getNodeNamespace() {
        String namespaceURI = this.dom.getNamespaceURI();
        String prefix = this.dom.getPrefix();
        if (namespaceURI == null) {
            namespaceURI = "";
        }
        if (prefix == null) {
            prefix = "";
        }
        return Namespace.create(prefix, namespaceURI);
    }

    Namespace getNamespace() {
        return getNodeNamespace();
    }

    void removeNamespace(Namespace namespace) {
        if (!namespace.is(getNodeNamespace())) {
            NamedNodeMap attributes = this.dom.getAttributes();
            int i = 0;
            while (i < attributes.getLength()) {
                if (!namespace.is(createImpl(attributes.item(i)).getNodeNamespace())) {
                    i++;
                } else {
                    return;
                }
            }
            String existingPrefixFor = getExistingPrefixFor(namespace);
            if (existingPrefixFor == null) {
                return;
            }
            if (namespace.isUnspecifiedPrefix()) {
                declareNamespace(existingPrefixFor, getDefaultNamespace().getUri());
            } else if (existingPrefixFor.equals(namespace.getPrefix())) {
                declareNamespace(existingPrefixFor, getDefaultNamespace().getUri());
            }
        }
    }

    private void setProcessingInstructionName(String str) {
        ProcessingInstruction processingInstruction = (ProcessingInstruction) this.dom;
        processingInstruction.getParentNode().replaceChild(processingInstruction, processingInstruction.getOwnerDocument().createProcessingInstruction(str, processingInstruction.getData()));
    }

    final void setLocalName(String str) {
        if (this.dom instanceof ProcessingInstruction) {
            setProcessingInstructionName(str);
            return;
        }
        String prefix = this.dom.getPrefix();
        if (prefix == null) {
            prefix = "";
        }
        this.dom = this.dom.getOwnerDocument().renameNode(this.dom, this.dom.getNamespaceURI(), XmlNode$QName.qualify(prefix, str));
    }

    final XmlNode$QName getQname() {
        return XmlNode$QName.create(this.dom.getNamespaceURI() == null ? "" : this.dom.getNamespaceURI(), this.dom.getLocalName(), this.dom.getPrefix() == null ? "" : this.dom.getPrefix());
    }

    void addMatchingChildren(XMLList xMLList, Filter filter) {
        NodeList childNodes = this.dom.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            XmlNode createImpl = createImpl(item);
            if (filter.accept(item)) {
                xMLList.addToList(createImpl);
            }
        }
    }

    XmlNode[] getMatchingChildren(Filter filter) {
        ArrayList arrayList = new ArrayList();
        NodeList childNodes = this.dom.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (filter.accept(item)) {
                arrayList.add(createImpl(item));
            }
        }
        return (XmlNode[]) arrayList.toArray(new XmlNode[arrayList.size()]);
    }

    XmlNode[] getAttributes() {
        NamedNodeMap attributes = this.dom.getAttributes();
        if (attributes == null) {
            throw new IllegalStateException("Must be element.");
        }
        XmlNode[] xmlNodeArr = new XmlNode[attributes.getLength()];
        for (int i = 0; i < attributes.getLength(); i++) {
            xmlNodeArr[i] = createImpl(attributes.item(i));
        }
        return xmlNodeArr;
    }

    String getAttributeValue() {
        return ((Attr) this.dom).getValue();
    }

    void setAttribute(XmlNode$QName xmlNode$QName, String str) {
        if (this.dom instanceof Element) {
            xmlNode$QName.setAttribute((Element) this.dom, str);
            return;
        }
        throw new IllegalStateException("Can only set attribute on elements.");
    }

    void replaceWith(XmlNode xmlNode) {
        Node node = xmlNode.dom;
        if (node.getOwnerDocument() != this.dom.getOwnerDocument()) {
            node = this.dom.getOwnerDocument().importNode(node, true);
        }
        this.dom.getParentNode().replaceChild(node, this.dom);
    }

    String ecmaToXMLString(XmlProcessor xmlProcessor) {
        if (!isElementType()) {
            return xmlProcessor.ecmaToXmlString(this.dom);
        }
        Element element = (Element) this.dom.cloneNode(true);
        Namespace[] inScopeNamespaces = getInScopeNamespaces();
        for (int i = 0; i < inScopeNamespaces.length; i++) {
            declareNamespace(element, inScopeNamespaces[i].getPrefix(), inScopeNamespaces[i].getUri());
        }
        return xmlProcessor.ecmaToXmlString(element);
    }

    Node toDomNode() {
        return this.dom;
    }
}
