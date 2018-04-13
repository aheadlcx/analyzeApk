package org.apache.commons.httpclient.methods;

public class PutMethod extends EntityEnclosingMethod {
    public PutMethod(String str) {
        super(str);
    }

    public String getName() {
        return "PUT";
    }
}
