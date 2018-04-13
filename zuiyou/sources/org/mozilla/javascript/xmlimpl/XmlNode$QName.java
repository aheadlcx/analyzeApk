package org.mozilla.javascript.xmlimpl;

import java.io.Serializable;
import org.mozilla.javascript.xmlimpl.XmlNode.Namespace;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

class XmlNode$QName implements Serializable {
    private static final long serialVersionUID = -6587069811691451077L;
    private String localName;
    private Namespace namespace;

    static XmlNode$QName create(Namespace namespace, String str) {
        if (str == null || !str.equals("*")) {
            XmlNode$QName xmlNode$QName = new XmlNode$QName();
            xmlNode$QName.namespace = namespace;
            xmlNode$QName.localName = str;
            return xmlNode$QName;
        }
        throw new RuntimeException("* is not valid localName");
    }

    @Deprecated
    static XmlNode$QName create(String str, String str2, String str3) {
        return create(Namespace.create(str3, str), str2);
    }

    static String qualify(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("prefix must not be null");
        } else if (str.length() > 0) {
            return str + ":" + str2;
        } else {
            return str2;
        }
    }

    private XmlNode$QName() {
    }

    public String toString() {
        return "XmlNode.QName [" + this.localName + "," + this.namespace + "]";
    }

    private boolean equals(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equals(str2);
    }

    private boolean namespacesEqual(Namespace namespace, Namespace namespace2) {
        if (namespace == null && namespace2 == null) {
            return true;
        }
        if (namespace == null || namespace2 == null) {
            return false;
        }
        return equals(namespace.getUri(), namespace2.getUri());
    }

    final boolean equals(XmlNode$QName xmlNode$QName) {
        if (namespacesEqual(this.namespace, xmlNode$QName.namespace) && equals(this.localName, xmlNode$QName.localName)) {
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj instanceof XmlNode$QName) {
            return equals((XmlNode$QName) obj);
        }
        return false;
    }

    public int hashCode() {
        return this.localName == null ? 0 : this.localName.hashCode();
    }

    void lookupPrefix(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("node must not be null");
        }
        String str;
        int i;
        Node node2;
        String lookupPrefix = node.lookupPrefix(this.namespace.getUri());
        if (lookupPrefix == null) {
            Object lookupNamespaceURI = node.lookupNamespaceURI(null);
            if (lookupNamespaceURI == null) {
                lookupNamespaceURI = "";
            }
            if (this.namespace.getUri().equals(lookupNamespaceURI)) {
                str = "";
                i = 0;
                while (str == null) {
                    StringBuilder append = new StringBuilder().append("e4x_");
                    int i2 = i + 1;
                    lookupPrefix = append.append(i).toString();
                    if (node.lookupNamespaceURI(lookupPrefix) == null) {
                        node2 = node;
                        while (node2.getParentNode() != null && (node2.getParentNode() instanceof Element)) {
                            node2 = node2.getParentNode();
                        }
                        ((Element) node2).setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + lookupPrefix, this.namespace.getUri());
                        str = lookupPrefix;
                    }
                    i = i2;
                }
                Namespace.access$200(this.namespace, str);
            }
        }
        str = lookupPrefix;
        i = 0;
        while (str == null) {
            StringBuilder append2 = new StringBuilder().append("e4x_");
            int i22 = i + 1;
            lookupPrefix = append2.append(i).toString();
            if (node.lookupNamespaceURI(lookupPrefix) == null) {
                node2 = node;
                while (node2.getParentNode() != null) {
                    node2 = node2.getParentNode();
                }
                ((Element) node2).setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + lookupPrefix, this.namespace.getUri());
                str = lookupPrefix;
            }
            i = i22;
        }
        Namespace.access$200(this.namespace, str);
    }

    String qualify(Node node) {
        if (this.namespace.getPrefix() == null) {
            if (node != null) {
                lookupPrefix(node);
            } else if (this.namespace.getUri().equals("")) {
                Namespace.access$200(this.namespace, "");
            } else {
                Namespace.access$200(this.namespace, "");
            }
        }
        return qualify(this.namespace.getPrefix(), this.localName);
    }

    void setAttribute(Element element, String str) {
        if (this.namespace.getPrefix() == null) {
            lookupPrefix(element);
        }
        element.setAttributeNS(this.namespace.getUri(), qualify(this.namespace.getPrefix(), this.localName), str);
    }

    Namespace getNamespace() {
        return this.namespace;
    }

    String getLocalName() {
        return this.localName;
    }
}
