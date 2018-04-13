package com.alibaba.sdk.android.mns.model.deserialize;

import com.alibaba.sdk.android.mns.model.QueueMeta;
import java.io.StringReader;
import okhttp3.aa;
import org.xml.sax.InputSource;

public class QueueMetaDeserializer extends AbstractQueueMetaDeserializer<QueueMeta> {
    public QueueMeta deserialize(aa aaVar) throws Exception {
        try {
            return parseQueueMeta(getDocumentBuilder().parse(new InputSource(new StringReader(aaVar.g().string()))).getDocumentElement());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
