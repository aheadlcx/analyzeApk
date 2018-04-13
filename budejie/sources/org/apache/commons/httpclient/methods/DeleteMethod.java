package org.apache.commons.httpclient.methods;

import org.apache.commons.httpclient.HttpMethodBase;

public class DeleteMethod extends HttpMethodBase {
    public DeleteMethod(String str) {
        super(str);
    }

    public String getName() {
        return "DELETE";
    }
}
