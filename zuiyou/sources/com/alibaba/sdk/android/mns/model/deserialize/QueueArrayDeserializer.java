package com.alibaba.sdk.android.mns.model.deserialize;

import com.alibaba.sdk.android.mns.common.MNSConstants;
import com.alibaba.sdk.android.mns.model.PagingListResult;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import okhttp3.aa;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class QueueArrayDeserializer extends AbstractQueueMetaDeserializer<PagingListResult<String>> {
    public PagingListResult<String> deserialize(aa aaVar) throws Exception {
        try {
            return parseQueueList(getDocumentBuilder().parse(new InputSource(new StringReader(aaVar.g().string()))));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PagingListResult<String> parseQueueList(Document document) {
        NodeList elementsByTagName = document.getElementsByTagName(MNSConstants.QUEUE_TAG);
        List arrayList = new ArrayList();
        for (int i = 0; i < elementsByTagName.getLength(); i++) {
            String safeGetElementContent = safeGetElementContent((Element) elementsByTagName.item(i), MNSConstants.QUEUE_URL_TAG, null);
            if (safeGetElementContent != null) {
                arrayList.add(safeGetElementContent);
            }
        }
        if (arrayList.size() <= 0) {
            return null;
        }
        PagingListResult<String> pagingListResult = new PagingListResult();
        NodeList elementsByTagName2 = document.getElementsByTagName(MNSConstants.NEXT_MARKER_TAG);
        if (elementsByTagName2.getLength() > 0) {
            pagingListResult.setMarker(elementsByTagName2.item(0).getTextContent());
        }
        pagingListResult.setResult(arrayList);
        return pagingListResult;
    }
}
