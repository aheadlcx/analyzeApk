package com.alipay.android.phone.mrpc.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;

public class HttpUrlRequest extends Request {
    private String b;
    private byte[] c;
    private String d;
    private ArrayList<Header> e;
    private Map<String, String> f;
    private boolean g;

    public HttpUrlRequest(String str) {
        this.b = str;
        this.e = new ArrayList();
        this.f = new HashMap();
        this.d = "application/x-www-form-urlencoded";
    }

    public HttpUrlRequest(String str, byte[] bArr, ArrayList<Header> arrayList, HashMap<String, String> hashMap) {
        this.b = str;
        this.c = bArr;
        this.e = arrayList;
        this.f = hashMap;
        this.d = "application/x-www-form-urlencoded";
    }

    public void addHeader(Header header) {
        this.e.add(header);
    }

    public void addTags(String str, String str2) {
        if (this.f == null) {
            this.f = new HashMap();
        }
        this.f.put(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        HttpUrlRequest httpUrlRequest = (HttpUrlRequest) obj;
        if (this.c == null) {
            if (httpUrlRequest.c != null) {
                return false;
            }
        } else if (!this.c.equals(httpUrlRequest.c)) {
            return false;
        }
        return this.b == null ? httpUrlRequest.b == null : this.b.equals(httpUrlRequest.b);
    }

    public String getContentType() {
        return this.d;
    }

    public ArrayList<Header> getHeaders() {
        return this.e;
    }

    public String getKey() {
        return getUrl() + Integer.toHexString(getReqData().hashCode());
    }

    public byte[] getReqData() {
        return this.c;
    }

    public String getTag(String str) {
        return this.f == null ? null : (String) this.f.get(str);
    }

    public String getUrl() {
        return this.b;
    }

    public int hashCode() {
        int i = 1;
        if (this.f != null && this.f.containsKey("id")) {
            i = ((String) this.f.get("id")).hashCode() + 31;
        }
        return (this.b == null ? 0 : this.b.hashCode()) + (i * 31);
    }

    public boolean isResetCookie() {
        return this.g;
    }

    public void setContentType(String str) {
        this.d = str;
    }

    public void setHeaders(ArrayList<Header> arrayList) {
        this.e = arrayList;
    }

    public void setReqData(byte[] bArr) {
        this.c = bArr;
    }

    public void setResetCookie(boolean z) {
        this.g = z;
    }

    public void setTags(Map<String, String> map) {
        this.f = map;
    }

    public String setUrl(String str) {
        this.b = str;
        return str;
    }

    public String toString() {
        return String.format("Url : %s,HttpHeader: %s", new Object[]{getUrl(), getHeaders()});
    }
}
