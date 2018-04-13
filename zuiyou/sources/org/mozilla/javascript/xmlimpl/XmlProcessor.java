package org.mozilla.javascript.xmlimpl;

import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptRuntime;
import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

class XmlProcessor implements Serializable {
    private static final long serialVersionUID = 6903514433204808713L;
    private transient LinkedBlockingDeque<DocumentBuilder> documentBuilderPool;
    private transient DocumentBuilderFactory dom;
    private RhinoSAXErrorHandler errorHandler = new RhinoSAXErrorHandler();
    private boolean ignoreComments;
    private boolean ignoreProcessingInstructions;
    private boolean ignoreWhitespace;
    private int prettyIndent;
    private boolean prettyPrint;
    private transient TransformerFactory xform;

    private static class RhinoSAXErrorHandler implements Serializable, ErrorHandler {
        private static final long serialVersionUID = 6918417235413084055L;

        private RhinoSAXErrorHandler() {
        }

        private void throwError(SAXParseException sAXParseException) {
            throw ScriptRuntime.constructError("TypeError", sAXParseException.getMessage(), sAXParseException.getLineNumber() - 1);
        }

        public void error(SAXParseException sAXParseException) {
            throwError(sAXParseException);
        }

        public void fatalError(SAXParseException sAXParseException) {
            throwError(sAXParseException);
        }

        public void warning(SAXParseException sAXParseException) {
            Context.reportWarning(sAXParseException.getMessage());
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.dom = DocumentBuilderFactory.newInstance();
        this.dom.setNamespaceAware(true);
        this.dom.setIgnoringComments(false);
        this.xform = TransformerFactory.newInstance();
        this.documentBuilderPool = new LinkedBlockingDeque(Runtime.getRuntime().availableProcessors() * 2);
    }

    XmlProcessor() {
        setDefault();
        this.dom = DocumentBuilderFactory.newInstance();
        this.dom.setNamespaceAware(true);
        this.dom.setIgnoringComments(false);
        this.xform = TransformerFactory.newInstance();
        this.documentBuilderPool = new LinkedBlockingDeque(Runtime.getRuntime().availableProcessors() * 2);
    }

    final void setDefault() {
        setIgnoreComments(true);
        setIgnoreProcessingInstructions(true);
        setIgnoreWhitespace(true);
        setPrettyPrinting(true);
        setPrettyIndent(2);
    }

    final void setIgnoreComments(boolean z) {
        this.ignoreComments = z;
    }

    final void setIgnoreWhitespace(boolean z) {
        this.ignoreWhitespace = z;
    }

    final void setIgnoreProcessingInstructions(boolean z) {
        this.ignoreProcessingInstructions = z;
    }

    final void setPrettyPrinting(boolean z) {
        this.prettyPrint = z;
    }

    final void setPrettyIndent(int i) {
        this.prettyIndent = i;
    }

    final boolean isIgnoreComments() {
        return this.ignoreComments;
    }

    final boolean isIgnoreProcessingInstructions() {
        return this.ignoreProcessingInstructions;
    }

    final boolean isIgnoreWhitespace() {
        return this.ignoreWhitespace;
    }

    final boolean isPrettyPrinting() {
        return this.prettyPrint;
    }

    final int getPrettyIndent() {
        return this.prettyIndent;
    }

    private String toXmlNewlines(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '\r') {
                stringBuilder.append(str.charAt(i));
            } else if (str.charAt(i + 1) != '\n') {
                stringBuilder.append('\n');
            }
        }
        return stringBuilder.toString();
    }

    private DocumentBuilderFactory getDomFactory() {
        return this.dom;
    }

    private DocumentBuilder getDocumentBuilderFromPool() throws ParserConfigurationException {
        DocumentBuilder documentBuilder = (DocumentBuilder) this.documentBuilderPool.pollFirst();
        if (documentBuilder == null) {
            documentBuilder = getDomFactory().newDocumentBuilder();
        }
        documentBuilder.setErrorHandler(this.errorHandler);
        return documentBuilder;
    }

    private void returnDocumentBuilderToPool(DocumentBuilder documentBuilder) {
        try {
            documentBuilder.reset();
            this.documentBuilderPool.offerFirst(documentBuilder);
        } catch (UnsupportedOperationException e) {
        }
    }

    private void addProcessingInstructionsTo(List<Node> list, Node node) {
        if (node instanceof ProcessingInstruction) {
            list.add(node);
        }
        if (node.getChildNodes() != null) {
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                addProcessingInstructionsTo(list, node.getChildNodes().item(i));
            }
        }
    }

    private void addCommentsTo(List<Node> list, Node node) {
        if (node instanceof Comment) {
            list.add(node);
        }
        if (node.getChildNodes() != null) {
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                addProcessingInstructionsTo(list, node.getChildNodes().item(i));
            }
        }
    }

    private void addTextNodesToRemoveAndTrim(List<Node> list, Node node) {
        if (node instanceof Text) {
            Text text = (Text) node;
            text.setData(text.getData().trim());
            if (text.getData().length() == 0) {
                list.add(node);
            }
        }
        if (node.getChildNodes() != null) {
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                addTextNodesToRemoveAndTrim(list, node.getChildNodes().item(i));
            }
        }
    }

    final Node toXml(String str, String str2) throws SAXException {
        DocumentBuilder documentBuilderFromPool;
        Throwable th;
        Throwable th2;
        DocumentBuilder documentBuilder = null;
        try {
            String str3 = "<parent xmlns=\"" + str + "\">" + str2 + "</parent>";
            documentBuilderFromPool = getDocumentBuilderFromPool();
            try {
                List<Node> arrayList;
                Node createTextNode;
                Document parse = documentBuilderFromPool.parse(new InputSource(new StringReader(str3)));
                if (this.ignoreProcessingInstructions) {
                    arrayList = new ArrayList();
                    addProcessingInstructionsTo(arrayList, parse);
                    for (Node createTextNode2 : arrayList) {
                        createTextNode2.getParentNode().removeChild(createTextNode2);
                    }
                }
                if (this.ignoreComments) {
                    arrayList = new ArrayList();
                    addCommentsTo(arrayList, parse);
                    for (Node createTextNode22 : arrayList) {
                        createTextNode22.getParentNode().removeChild(createTextNode22);
                    }
                }
                if (this.ignoreWhitespace) {
                    arrayList = new ArrayList();
                    addTextNodesToRemoveAndTrim(arrayList, parse);
                    for (Node createTextNode222 : arrayList) {
                        createTextNode222.getParentNode().removeChild(createTextNode222);
                    }
                }
                NodeList childNodes = parse.getDocumentElement().getChildNodes();
                if (childNodes.getLength() > 1) {
                    throw ScriptRuntime.constructError("SyntaxError", "XML objects may contain at most one node.");
                }
                if (childNodes.getLength() == 0) {
                    createTextNode222 = parse.createTextNode("");
                    if (documentBuilderFromPool != null) {
                        returnDocumentBuilderToPool(documentBuilderFromPool);
                    }
                } else {
                    createTextNode222 = childNodes.item(0);
                    parse.getDocumentElement().removeChild(createTextNode222);
                    if (documentBuilderFromPool != null) {
                        returnDocumentBuilderToPool(documentBuilderFromPool);
                    }
                }
                return createTextNode222;
            } catch (IOException e) {
                documentBuilder = documentBuilderFromPool;
                try {
                    throw new RuntimeException("Unreachable.");
                } catch (Throwable th3) {
                    th = th3;
                    documentBuilderFromPool = documentBuilder;
                    th2 = th;
                    if (documentBuilderFromPool != null) {
                        returnDocumentBuilderToPool(documentBuilderFromPool);
                    }
                    throw th2;
                }
            } catch (ParserConfigurationException e2) {
                th2 = e2;
                try {
                    throw new RuntimeException(th2);
                } catch (Throwable th4) {
                    th2 = th4;
                    if (documentBuilderFromPool != null) {
                        returnDocumentBuilderToPool(documentBuilderFromPool);
                    }
                    throw th2;
                }
            }
        } catch (IOException e3) {
            throw new RuntimeException("Unreachable.");
        } catch (Throwable th32) {
            th = th32;
            documentBuilderFromPool = null;
            th2 = th;
            throw new RuntimeException(th2);
        } catch (Throwable th322) {
            th = th322;
            documentBuilderFromPool = null;
            th2 = th;
            if (documentBuilderFromPool != null) {
                returnDocumentBuilderToPool(documentBuilderFromPool);
            }
            throw th2;
        }
    }

    Document newDocument() {
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = getDocumentBuilderFromPool();
            Document newDocument = documentBuilder.newDocument();
            if (documentBuilder != null) {
                returnDocumentBuilderToPool(documentBuilder);
            }
            return newDocument;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            if (documentBuilder != null) {
                returnDocumentBuilderToPool(documentBuilder);
            }
        }
    }

    private String toString(Node node) {
        Source dOMSource = new DOMSource(node);
        Writer stringWriter = new StringWriter();
        Result streamResult = new StreamResult(stringWriter);
        try {
            Transformer newTransformer = this.xform.newTransformer();
            newTransformer.setOutputProperty("omit-xml-declaration", "yes");
            newTransformer.setOutputProperty("indent", "no");
            newTransformer.setOutputProperty(PushConstants.MZ_PUSH_MESSAGE_METHOD, "xml");
            newTransformer.transform(dOMSource, streamResult);
            return toXmlNewlines(stringWriter.toString());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        }
    }

    String escapeAttributeValue(Object obj) {
        String scriptRuntime = ScriptRuntime.toString(obj);
        if (scriptRuntime.length() == 0) {
            return "";
        }
        Node createElement = newDocument().createElement("a");
        createElement.setAttribute("b", scriptRuntime);
        scriptRuntime = toString(createElement);
        return scriptRuntime.substring(scriptRuntime.indexOf(34) + 1, scriptRuntime.lastIndexOf(34));
    }

    String escapeTextValue(Object obj) {
        if (obj instanceof XMLObjectImpl) {
            return ((XMLObjectImpl) obj).toXMLString();
        }
        String scriptRuntime = ScriptRuntime.toString(obj);
        if (scriptRuntime.length() == 0) {
            return scriptRuntime;
        }
        Node createElement = newDocument().createElement("a");
        createElement.setTextContent(scriptRuntime);
        scriptRuntime = toString(createElement);
        int indexOf = scriptRuntime.indexOf(62) + 1;
        int lastIndexOf = scriptRuntime.lastIndexOf(60);
        return indexOf < lastIndexOf ? scriptRuntime.substring(indexOf, lastIndexOf) : "";
    }

    private String escapeElementValue(String str) {
        return escapeTextValue(str);
    }

    private String elementToXmlString(Element element) {
        Element element2 = (Element) element.cloneNode(true);
        if (this.prettyPrint) {
            beautifyElement(element2, 0);
        }
        return toString(element2);
    }

    final String ecmaToXmlString(Node node) {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.prettyPrint) {
        }
        if (node instanceof Text) {
            String data = ((Text) node).getData();
            if (this.prettyPrint) {
                data = data.trim();
            }
            stringBuilder.append(escapeElementValue(data));
            return stringBuilder.toString();
        } else if (node instanceof Attr) {
            stringBuilder.append(escapeAttributeValue(((Attr) node).getValue()));
            return stringBuilder.toString();
        } else if (node instanceof Comment) {
            stringBuilder.append("<!--" + ((Comment) node).getNodeValue() + "-->");
            return stringBuilder.toString();
        } else if (node instanceof ProcessingInstruction) {
            ProcessingInstruction processingInstruction = (ProcessingInstruction) node;
            stringBuilder.append("<?" + processingInstruction.getTarget() + " " + processingInstruction.getData() + "?>");
            return stringBuilder.toString();
        } else {
            stringBuilder.append(elementToXmlString((Element) node));
            return stringBuilder.toString();
        }
    }

    private void beautifyElement(Element element, int i) {
        int i2;
        int i3 = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('\n');
        for (i2 = 0; i2 < i; i2++) {
            stringBuilder.append(' ');
        }
        String stringBuilder2 = stringBuilder.toString();
        for (i2 = 0; i2 < this.prettyIndent; i2++) {
            stringBuilder.append(' ');
        }
        String stringBuilder3 = stringBuilder.toString();
        ArrayList arrayList = new ArrayList();
        int i4 = 0;
        for (i2 = 0; i2 < element.getChildNodes().getLength(); i2++) {
            if (i2 == 1) {
                i4 = 1;
            }
            if (element.getChildNodes().item(i2) instanceof Text) {
                arrayList.add(element.getChildNodes().item(i2));
            } else {
                arrayList.add(element.getChildNodes().item(i2));
                i4 = 1;
            }
        }
        if (i4 != 0) {
            for (int i5 = 0; i5 < arrayList.size(); i5++) {
                element.insertBefore(element.getOwnerDocument().createTextNode(stringBuilder3), (Node) arrayList.get(i5));
            }
        }
        NodeList childNodes = element.getChildNodes();
        ArrayList arrayList2 = new ArrayList();
        while (i3 < childNodes.getLength()) {
            if (childNodes.item(i3) instanceof Element) {
                arrayList2.add((Element) childNodes.item(i3));
            }
            i3++;
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            beautifyElement((Element) it.next(), this.prettyIndent + i);
        }
        if (i4 != 0) {
            element.appendChild(element.getOwnerDocument().createTextNode(stringBuilder2));
        }
    }
}
