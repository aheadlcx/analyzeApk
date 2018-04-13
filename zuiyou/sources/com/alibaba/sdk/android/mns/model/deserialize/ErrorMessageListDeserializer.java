package com.alibaba.sdk.android.mns.model.deserialize;

import com.alibaba.sdk.android.common.ServiceException;
import com.alibaba.sdk.android.mns.common.MNSConstants;
import com.alibaba.sdk.android.mns.common.MNSHeaders;
import java.io.StringReader;
import okhttp3.aa;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

public class ErrorMessageListDeserializer extends XMLDeserializer<ServiceException> {
    public ServiceException deserialize(aa aaVar) throws Exception {
        Exception e;
        String str = null;
        int b = aaVar.b();
        String a = aaVar.a(MNSHeaders.MNS_HEADER_REQUEST_ID);
        String string;
        String str2;
        String str3;
        try {
            string = aaVar.g().string();
            try {
                Element documentElement = getDocumentBuilder().parse(new InputSource(new StringReader(string))).getDocumentElement();
                if (documentElement == null || !documentElement.getNodeName().equals(MNSConstants.ERROR_TAG)) {
                    str2 = null;
                    str3 = null;
                    return new ServiceException(b, str2, str3, a, str, string);
                }
                str3 = safeGetElementContent(documentElement, MNSConstants.ERROR_CODE_TAG, "");
                try {
                    str2 = safeGetElementContent(documentElement, "Message", "");
                    try {
                        a = safeGetElementContent(documentElement, MNSConstants.ERROR_REQUEST_ID_TAG, "");
                        str = safeGetElementContent(documentElement, MNSConstants.ERROR_HOST_ID_TAG, "");
                        return new ServiceException(b, str2, str3, a, str, string);
                    } catch (Exception e2) {
                        e = e2;
                    }
                } catch (Exception e3) {
                    e = e3;
                    str2 = null;
                    e.printStackTrace();
                    return new ServiceException(b, str2, str3, a, str, string);
                }
            } catch (Exception e4) {
                e = e4;
                str2 = null;
                str3 = null;
                e.printStackTrace();
                return new ServiceException(b, str2, str3, a, str, string);
            }
        } catch (Exception e5) {
            e = e5;
            string = null;
            str2 = null;
            str3 = null;
            e.printStackTrace();
            return new ServiceException(b, str2, str3, a, str, string);
        }
    }
}
