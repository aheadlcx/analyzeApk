package com.xiaomi.xmpush.thrift;

public enum f {
    MISC_CONFIG(1),
    PLUGIN_CONFIG(2);
    
    private final int c;

    private f(int i) {
        this.c = i;
    }

    public static f a(int i) {
        switch (i) {
            case 1:
                return MISC_CONFIG;
            case 2:
                return PLUGIN_CONFIG;
            default:
                return null;
        }
    }

    public int a() {
        return this.c;
    }
}
