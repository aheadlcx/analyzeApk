package com.tencent.map.b;

public final class n {
    public byte[] a;
    public String b = "GBK";

    public final String toString() {
        try {
            return new String(this.a, this.b);
        } catch (Exception e) {
            return "";
        }
    }
}
