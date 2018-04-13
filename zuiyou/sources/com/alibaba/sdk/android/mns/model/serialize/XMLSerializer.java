package com.alibaba.sdk.android.mns.model.serialize;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class XMLSerializer<T> extends BaseXMLSerializer<T> implements Serializer<T> {
    public Element safeCreateContentElement(Document document, String str, Object obj, String str2) {
        if (obj == null && str2 == null) {
            return null;
        }
        Element createElement = document.createElement(str);
        if (obj != null) {
            createElement.setTextContent(obj.toString());
            return createElement;
        }
        createElement.setTextContent(str2);
        return createElement;
    }

    public Element safeCreateBooleanContentElement(Document document, String str, Integer num, String str2) {
        if (num == null && str2 == null) {
            return null;
        }
        Element createElement = document.createElement(str);
        if (num == null) {
            createElement.setTextContent(str2);
            return createElement;
        } else if (num.intValue() > 0) {
            createElement.setTextContent("true");
            return createElement;
        } else {
            createElement.setTextContent("false");
            return createElement;
        }
    }
}
