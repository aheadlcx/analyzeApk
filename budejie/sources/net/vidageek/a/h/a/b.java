package net.vidageek.a.h.a;

enum b {
    VOID(Void.TYPE),
    BOOLEAN(Boolean.TYPE),
    BYTE(Byte.TYPE),
    SHORT(Short.TYPE),
    CHAR(Character.TYPE),
    INT(Integer.TYPE),
    LONG(Long.TYPE),
    FLOAT(Float.TYPE),
    DOUBLE(Double.TYPE);
    
    private final Class<?> j;

    private b(Class<?> cls) {
        this.j = cls;
    }

    public static Class<?> a(String str) {
        for (b bVar : values()) {
            if (bVar.j.toString().equals(str)) {
                return bVar.j;
            }
        }
        return null;
    }
}
