package com.alibaba.sdk.android.mns.model.deserialize;

import com.alibaba.sdk.android.mns.common.MNSConstants;
import com.alibaba.sdk.android.mns.model.Message;
import java.io.StringReader;
import java.util.Date;
import okhttp3.aa;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

public class MessageDeserializer extends XMLDeserializer<Message> {
    public Message deserialize(aa aaVar) throws Exception {
        Message message = new Message();
        try {
            Element documentElement = getDocumentBuilder().parse(new InputSource(new StringReader(aaVar.g().string()))).getDocumentElement();
            if (documentElement != null && documentElement.getNodeName().equals("Message")) {
                String safeGetElementContent = safeGetElementContent(documentElement, MNSConstants.MESSAGE_ID_TAG, null);
                if (safeGetElementContent != null) {
                    message.setMessageId(safeGetElementContent);
                }
                safeGetElementContent = safeGetElementContent(documentElement, MNSConstants.MESSAGE_BODY_MD5_TAG, null);
                if (safeGetElementContent != null) {
                    message.setMessageBodyMd5(safeGetElementContent);
                }
                safeGetElementContent = safeGetElementContent(documentElement, MNSConstants.RECEIPT_HANDLE_TAG, null);
                if (safeGetElementContent != null) {
                    message.setReceiptHandle(safeGetElementContent);
                }
                safeGetElementContent = safeGetElementContent(documentElement, MNSConstants.MESSAGE_BODY_TAG, null);
                if (safeGetElementContent != null) {
                    message.setMessageBody(safeGetElementContent);
                }
                safeGetElementContent = safeGetElementContent(documentElement, MNSConstants.ENQUEUE_TIME_TAG, null);
                if (safeGetElementContent != null) {
                    message.setEnqueueTime(new Date(Long.parseLong(safeGetElementContent)));
                }
                safeGetElementContent = safeGetElementContent(documentElement, MNSConstants.NEXT_VISIBLE_TIME_TAG, null);
                if (safeGetElementContent != null) {
                    message.setNextVisibleTime(new Date(Long.parseLong(safeGetElementContent)));
                }
                safeGetElementContent = safeGetElementContent(documentElement, MNSConstants.FIRST_DEQUEUE_TIME_TAG, null);
                if (safeGetElementContent != null) {
                    message.setFirstDequeueTime(new Date(Long.parseLong(safeGetElementContent)));
                }
                safeGetElementContent = safeGetElementContent(documentElement, MNSConstants.DEQUEUE_COUNT_TAG, null);
                if (safeGetElementContent != null) {
                    message.setDequeueCount(Integer.parseInt(safeGetElementContent));
                }
                String safeGetElementContent2 = safeGetElementContent(documentElement, MNSConstants.PRIORITY_TAG, null);
                if (safeGetElementContent2 == null) {
                    return message;
                }
                message.setPriority(Integer.parseInt(safeGetElementContent2));
                return message;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
