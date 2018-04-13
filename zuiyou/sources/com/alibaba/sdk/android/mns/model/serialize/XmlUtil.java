package com.alibaba.sdk.android.mns.model.serialize;

import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Node;

public class XmlUtil {
    private static TransformerFactory transFactory = TransformerFactory.newInstance();

    public static void output(Node node, String str, OutputStream outputStream) throws TransformerException {
        Transformer newTransformer = transFactory.newTransformer();
        newTransformer.setOutputProperty("encoding", str);
        Source dOMSource = new DOMSource();
        dOMSource.setNode(node);
        Result streamResult = new StreamResult();
        streamResult.setOutputStream(outputStream);
        newTransformer.transform(dOMSource, streamResult);
    }

    public static String xmlNodeToString(Node node, String str) throws TransformerException {
        Transformer newTransformer = transFactory.newTransformer();
        newTransformer.setOutputProperty("encoding", str);
        Writer stringWriter = new StringWriter();
        Source dOMSource = new DOMSource();
        dOMSource.setNode(node);
        newTransformer.transform(dOMSource, new StreamResult(stringWriter));
        return stringWriter.toString();
    }
}
