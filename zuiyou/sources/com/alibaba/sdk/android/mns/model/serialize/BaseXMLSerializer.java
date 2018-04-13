package com.alibaba.sdk.android.mns.model.serialize;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class BaseXMLSerializer<T> {
    protected static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private static ThreadLocal<DocumentBuilder> sps = new ThreadLocal();

    protected DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
        DocumentBuilder documentBuilder = (DocumentBuilder) sps.get();
        if (documentBuilder != null) {
            return documentBuilder;
        }
        documentBuilder = factory.newDocumentBuilder();
        sps.set(documentBuilder);
        return documentBuilder;
    }
}
