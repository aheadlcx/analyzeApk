package org.apache.commons.httpclient.methods;

import java.util.Iterator;
import java.util.Vector;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PostMethod extends EntityEnclosingMethod {
    public static final String FORM_URL_ENCODED_CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$methods$PostMethod;
    private Vector params = new Vector();

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$methods$PostMethod == null) {
            class$ = class$("org.apache.commons.httpclient.methods.PostMethod");
            class$org$apache$commons$httpclient$methods$PostMethod = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$methods$PostMethod;
        }
        LOG = LogFactory.getLog(class$);
    }

    public PostMethod(String str) {
        super(str);
    }

    public String getName() {
        return "POST";
    }

    protected boolean hasRequestContent() {
        LOG.trace("enter PostMethod.hasRequestContent()");
        if (this.params.isEmpty()) {
            return super.hasRequestContent();
        }
        return true;
    }

    protected void clearRequestBody() {
        LOG.trace("enter PostMethod.clearRequestBody()");
        this.params.clear();
        super.clearRequestBody();
    }

    protected RequestEntity generateRequestEntity() {
        if (this.params.isEmpty()) {
            return super.generateRequestEntity();
        }
        return new ByteArrayRequestEntity(EncodingUtil.getAsciiBytes(EncodingUtil.formUrlEncode(getParameters(), getRequestCharSet())), FORM_URL_ENCODED_CONTENT_TYPE);
    }

    public void setParameter(String str, String str2) {
        LOG.trace("enter PostMethod.setParameter(String, String)");
        removeParameter(str);
        addParameter(str, str2);
    }

    public NameValuePair getParameter(String str) {
        LOG.trace("enter PostMethod.getParameter(String)");
        if (str == null) {
            return null;
        }
        Iterator it = this.params.iterator();
        while (it.hasNext()) {
            NameValuePair nameValuePair = (NameValuePair) it.next();
            if (str.equals(nameValuePair.getName())) {
                return nameValuePair;
            }
        }
        return null;
    }

    public NameValuePair[] getParameters() {
        LOG.trace("enter PostMethod.getParameters()");
        int size = this.params.size();
        Object[] toArray = this.params.toArray();
        NameValuePair[] nameValuePairArr = new NameValuePair[size];
        for (int i = 0; i < size; i++) {
            nameValuePairArr[i] = (NameValuePair) toArray[i];
        }
        return nameValuePairArr;
    }

    public void addParameter(String str, String str2) throws IllegalArgumentException {
        LOG.trace("enter PostMethod.addParameter(String, String)");
        if (str == null || str2 == null) {
            throw new IllegalArgumentException("Arguments to addParameter(String, String) cannot be null");
        }
        super.clearRequestBody();
        this.params.add(new NameValuePair(str, str2));
    }

    public void addParameter(NameValuePair nameValuePair) throws IllegalArgumentException {
        LOG.trace("enter PostMethod.addParameter(NameValuePair)");
        if (nameValuePair == null) {
            throw new IllegalArgumentException("NameValuePair may not be null");
        }
        addParameter(nameValuePair.getName(), nameValuePair.getValue());
    }

    public void addParameters(NameValuePair[] nameValuePairArr) {
        LOG.trace("enter PostMethod.addParameters(NameValuePair[])");
        if (nameValuePairArr == null) {
            LOG.warn("Attempt to addParameters(null) ignored");
            return;
        }
        super.clearRequestBody();
        for (Object add : nameValuePairArr) {
            this.params.add(add);
        }
    }

    public boolean removeParameter(String str) throws IllegalArgumentException {
        LOG.trace("enter PostMethod.removeParameter(String)");
        if (str == null) {
            throw new IllegalArgumentException("Argument passed to removeParameter(String) cannot be null");
        }
        Iterator it = this.params.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (str.equals(((NameValuePair) it.next()).getName())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public boolean removeParameter(String str, String str2) throws IllegalArgumentException {
        LOG.trace("enter PostMethod.removeParameter(String, String)");
        if (str == null) {
            throw new IllegalArgumentException("Parameter name may not be null");
        } else if (str2 == null) {
            throw new IllegalArgumentException("Parameter value may not be null");
        } else {
            Iterator it = this.params.iterator();
            while (it.hasNext()) {
                NameValuePair nameValuePair = (NameValuePair) it.next();
                if (str.equals(nameValuePair.getName()) && str2.equals(nameValuePair.getValue())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    }

    public void setRequestBody(NameValuePair[] nameValuePairArr) throws IllegalArgumentException {
        LOG.trace("enter PostMethod.setRequestBody(NameValuePair[])");
        if (nameValuePairArr == null) {
            throw new IllegalArgumentException("Array of parameters may not be null");
        }
        clearRequestBody();
        addParameters(nameValuePairArr);
    }
}
