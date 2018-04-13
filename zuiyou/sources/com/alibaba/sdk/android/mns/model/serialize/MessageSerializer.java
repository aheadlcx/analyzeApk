package com.alibaba.sdk.android.mns.model.serialize;

import com.alibaba.sdk.android.mns.common.MNSConstants;
import com.alibaba.sdk.android.mns.model.Message;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class MessageSerializer extends XMLSerializer<Message> {
    public String serialize(Message message, String str) throws Exception {
        Node newDocument = getDocumentBuilder().newDocument();
        Element createElementNS = newDocument.createElementNS(MNSConstants.DEFAULT_XML_NAMESPACE, "Message");
        newDocument.appendChild(createElementNS);
        Node safeCreateContentElement = safeCreateContentElement(newDocument, MNSConstants.MESSAGE_BODY_TAG, message.getMessageBody(), null);
        if (safeCreateContentElement != null) {
            createElementNS.appendChild(safeCreateContentElement);
        }
        safeCreateContentElement = safeCreateContentElement(newDocument, MNSConstants.DELAY_SECONDS_TAG, message.getDelaySeconds(), null);
        if (safeCreateContentElement != null) {
            createElementNS.appendChild(safeCreateContentElement);
        }
        safeCreateContentElement = safeCreateContentElement(newDocument, MNSConstants.PRIORITY_TAG, message.getPriority(), null);
        if (safeCreateContentElement != null) {
            createElementNS.appendChild(safeCreateContentElement);
        }
        return XmlUtil.xmlNodeToString(newDocument, str);
    }
}
