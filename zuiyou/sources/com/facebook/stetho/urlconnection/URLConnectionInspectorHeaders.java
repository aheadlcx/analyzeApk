package com.facebook.stetho.urlconnection;

import android.util.Pair;
import com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorHeaders;
import java.util.ArrayList;

class URLConnectionInspectorHeaders implements InspectorHeaders {
    private final ArrayList<Pair<String, String>> mHeaders;

    public URLConnectionInspectorHeaders(ArrayList<Pair<String, String>> arrayList) {
        this.mHeaders = arrayList;
    }

    public int headerCount() {
        return this.mHeaders.size();
    }

    public String headerName(int i) {
        return (String) ((Pair) this.mHeaders.get(i)).first;
    }

    public String headerValue(int i) {
        return (String) ((Pair) this.mHeaders.get(i)).second;
    }

    public String firstHeaderValue(String str) {
        int headerCount = headerCount();
        for (int i = 0; i < headerCount; i++) {
            if (str.equalsIgnoreCase(headerName(i))) {
                return headerValue(i);
            }
        }
        return null;
    }
}
