package com.alibaba.sdk.android.mns.model.serialize;

import com.alibaba.sdk.android.mns.common.MNSConstants;
import com.alibaba.sdk.android.mns.model.QueueMeta;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class QueueMetaSerializer extends XMLSerializer<QueueMeta> {
    public String serialize(QueueMeta queueMeta, String str) throws Exception {
        Node newDocument = getDocumentBuilder().newDocument();
        Element createElementNS = newDocument.createElementNS(MNSConstants.DEFAULT_XML_NAMESPACE, MNSConstants.QUEUE_TAG);
        newDocument.appendChild(createElementNS);
        Node safeCreateContentElement = safeCreateContentElement(newDocument, MNSConstants.DELAY_SECONDS_TAG, queueMeta.getDelaySeconds(), null);
        if (safeCreateContentElement != null) {
            createElementNS.appendChild(safeCreateContentElement);
        }
        safeCreateContentElement = safeCreateContentElement(newDocument, MNSConstants.VISIBILITY_TIMEOUT, queueMeta.getVisibilityTimeout(), null);
        if (safeCreateContentElement != null) {
            createElementNS.appendChild(safeCreateContentElement);
        }
        safeCreateContentElement = safeCreateContentElement(newDocument, MNSConstants.MAX_MESSAGE_SIZE_TAG, queueMeta.getMaxMessageSize(), null);
        if (safeCreateContentElement != null) {
            createElementNS.appendChild(safeCreateContentElement);
        }
        safeCreateContentElement = safeCreateContentElement(newDocument, MNSConstants.MESSAGE_RETENTION_PERIOD_TAG, queueMeta.getMessageRetentionPeriod(), null);
        if (safeCreateContentElement != null) {
            createElementNS.appendChild(safeCreateContentElement);
        }
        safeCreateContentElement = safeCreateContentElement(newDocument, MNSConstants.POLLING_WAITSECONDS_TAG, queueMeta.getPollingWaitSeconds(), null);
        if (safeCreateContentElement != null) {
            createElementNS.appendChild(safeCreateContentElement);
        }
        safeCreateContentElement = safeCreateBooleanContentElement(newDocument, MNSConstants.LOGGING_ENABLED_TAG, queueMeta.getLoggingEnabled(), null);
        if (safeCreateContentElement != null) {
            createElementNS.appendChild(safeCreateContentElement);
        }
        return XmlUtil.xmlNodeToString(newDocument, str);
    }
}
