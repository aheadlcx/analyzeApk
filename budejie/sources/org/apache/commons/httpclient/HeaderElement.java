package org.apache.commons.httpclient;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.httpclient.util.ParameterParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HeaderElement extends NameValuePair {
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$HeaderElement;
    private NameValuePair[] parameters;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public HeaderElement() {
        this(null, null, null);
    }

    public HeaderElement(String str, String str2) {
        this(str, str2, null);
    }

    public HeaderElement(String str, String str2, NameValuePair[] nameValuePairArr) {
        super(str, str2);
        this.parameters = null;
        this.parameters = nameValuePairArr;
    }

    public HeaderElement(char[] cArr, int i, int i2) {
        this();
        if (cArr != null) {
            List parse = new ParameterParser().parse(cArr, i, i2, ';');
            if (parse.size() > 0) {
                NameValuePair nameValuePair = (NameValuePair) parse.remove(0);
                setName(nameValuePair.getName());
                setValue(nameValuePair.getValue());
                if (parse.size() > 0) {
                    this.parameters = (NameValuePair[]) parse.toArray(new NameValuePair[parse.size()]);
                }
            }
        }
    }

    public HeaderElement(char[] cArr) {
        this(cArr, 0, cArr.length);
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$HeaderElement == null) {
            class$ = class$("org.apache.commons.httpclient.HeaderElement");
            class$org$apache$commons$httpclient$HeaderElement = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$HeaderElement;
        }
        LOG = LogFactory.getLog(class$);
    }

    public NameValuePair[] getParameters() {
        return this.parameters;
    }

    public static final HeaderElement[] parseElements(char[] cArr) {
        LOG.trace("enter HeaderElement.parseElements(char[])");
        if (cArr == null) {
            return new HeaderElement[0];
        }
        List arrayList = new ArrayList();
        int length = cArr.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i3 < length) {
            int i4;
            char c = cArr[i3];
            if (c == '\"') {
                i4 = i == 0 ? 1 : 0;
            } else {
                i4 = i;
            }
            NameValuePair nameValuePair = null;
            if (i4 == 0 && c == ',') {
                nameValuePair = new HeaderElement(cArr, i2, i3);
                i2 = i3 + 1;
            } else if (i3 == length - 1) {
                nameValuePair = new HeaderElement(cArr, i2, length);
            }
            if (!(nameValuePair == null || nameValuePair.getName() == null)) {
                arrayList.add(nameValuePair);
            }
            i3++;
            i = i4;
        }
        return (HeaderElement[]) arrayList.toArray(new HeaderElement[arrayList.size()]);
    }

    public static final HeaderElement[] parseElements(String str) {
        LOG.trace("enter HeaderElement.parseElements(String)");
        if (str == null) {
            return new HeaderElement[0];
        }
        return parseElements(str.toCharArray());
    }

    public static final HeaderElement[] parse(String str) throws HttpException {
        LOG.trace("enter HeaderElement.parse(String)");
        if (str == null) {
            return new HeaderElement[0];
        }
        return parseElements(str.toCharArray());
    }

    public NameValuePair getParameterByName(String str) {
        LOG.trace("enter HeaderElement.getParameterByName(String)");
        if (str == null) {
            throw new IllegalArgumentException("Name may not be null");
        }
        NameValuePair[] parameters = getParameters();
        if (parameters != null) {
            for (NameValuePair nameValuePair : parameters) {
                if (nameValuePair.getName().equalsIgnoreCase(str)) {
                    return nameValuePair;
                }
            }
        }
        return null;
    }
}
