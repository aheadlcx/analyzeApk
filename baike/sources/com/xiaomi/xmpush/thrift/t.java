package com.xiaomi.xmpush.thrift;

public enum t {
    RegIdExpired(0),
    PackageUnregistered(1),
    Init(2);
    
    private final int d;

    private t(int i) {
        this.d = i;
    }

    public static t a(int i) {
        switch (i) {
            case 0:
                return RegIdExpired;
            case 1:
                return PackageUnregistered;
            case 2:
                return Init;
            default:
                return null;
        }
    }

    public int a() {
        return this.d;
    }
}
