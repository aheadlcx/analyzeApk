package org.mozilla.classfile;

final class TypeInfo {
    static final int DOUBLE = 3;
    static final int FLOAT = 2;
    static final int INTEGER = 1;
    static final int LONG = 4;
    static final int NULL = 5;
    static final int OBJECT_TAG = 7;
    static final int TOP = 0;
    static final int UNINITIALIZED_THIS = 6;
    static final int UNINITIALIZED_VAR_TAG = 8;

    private TypeInfo() {
    }

    static final int OBJECT(int i) {
        return ((65535 & i) << 8) | 7;
    }

    static final int OBJECT(String str, ConstantPool constantPool) {
        return OBJECT(constantPool.addClass(str));
    }

    static final int UNINITIALIZED_VARIABLE(int i) {
        return ((65535 & i) << 8) | 8;
    }

    static final int getTag(int i) {
        return i & 255;
    }

    static final int getPayload(int i) {
        return i >>> 8;
    }

    static final String getPayloadAsType(int i, ConstantPool constantPool) {
        if (getTag(i) == 7) {
            return (String) constantPool.getConstantData(getPayload(i));
        }
        throw new IllegalArgumentException("expecting object type");
    }

    static final int fromType(String str, ConstantPool constantPool) {
        if (str.length() != 1) {
            return OBJECT(str, constantPool);
        }
        switch (str.charAt(0)) {
            case 'B':
            case 'C':
            case 'I':
            case 'S':
            case 'Z':
                return 1;
            case 'D':
                return 3;
            case 'F':
                return 2;
            case 'J':
                return 4;
            default:
                throw new IllegalArgumentException("bad type");
        }
    }

    static boolean isTwoWords(int i) {
        return i == 3 || i == 4;
    }

    static int merge(int i, int i2, ConstantPool constantPool) {
        Object obj = 1;
        int tag = getTag(i);
        int tag2 = getTag(i2);
        Object obj2 = tag == 7 ? 1 : null;
        if (tag2 != 7) {
            obj = null;
        }
        if (i == i2 || (obj2 != null && i2 == 5)) {
            return i;
        }
        if (tag == 0 || tag2 == 0) {
            return 0;
        }
        if (i == 5 && r1 != null) {
            return i2;
        }
        if (!(obj2 == null || r1 == null)) {
            String payloadAsType = getPayloadAsType(i, constantPool);
            String payloadAsType2 = getPayloadAsType(i2, constantPool);
            String str = (String) constantPool.getConstantData(2);
            String str2 = (String) constantPool.getConstantData(4);
            if (payloadAsType.equals(str)) {
                payloadAsType = str2;
            }
            if (!payloadAsType2.equals(str)) {
                str2 = payloadAsType2;
            }
            Class classFromInternalName = getClassFromInternalName(payloadAsType);
            Class classFromInternalName2 = getClassFromInternalName(str2);
            if (classFromInternalName.isAssignableFrom(classFromInternalName2)) {
                return i;
            }
            if (classFromInternalName2.isAssignableFrom(classFromInternalName)) {
                return i2;
            }
            if (classFromInternalName2.isInterface() || classFromInternalName.isInterface()) {
                return OBJECT("java/lang/Object", constantPool);
            }
            for (classFromInternalName2 = classFromInternalName2.getSuperclass(); classFromInternalName2 != null; classFromInternalName2 = classFromInternalName2.getSuperclass()) {
                if (classFromInternalName2.isAssignableFrom(classFromInternalName)) {
                    return OBJECT(ClassFileWriter.getSlashedForm(classFromInternalName2.getName()), constantPool);
                }
            }
        }
        throw new IllegalArgumentException("bad merge attempt between " + toString(i, constantPool) + " and " + toString(i2, constantPool));
    }

    static String toString(int i, ConstantPool constantPool) {
        int tag = getTag(i);
        switch (tag) {
            case 0:
                return "top";
            case 1:
                return "int";
            case 2:
                return "float";
            case 3:
                return "double";
            case 4:
                return "long";
            case 5:
                return "null";
            case 6:
                return "uninitialized_this";
            default:
                if (tag == 7) {
                    return getPayloadAsType(i, constantPool);
                }
                if (tag == 8) {
                    return "uninitialized";
                }
                throw new IllegalArgumentException("bad type");
        }
    }

    static Class<?> getClassFromInternalName(String str) {
        try {
            return Class.forName(str.replace('/', '.'));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    static String toString(int[] iArr, ConstantPool constantPool) {
        return toString(iArr, iArr.length, constantPool);
    }

    static String toString(int[] iArr, int i, ConstantPool constantPool) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(toString(iArr[i2], constantPool));
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    static void print(int[] iArr, int[] iArr2, ConstantPool constantPool) {
        print(iArr, iArr.length, iArr2, iArr2.length, constantPool);
    }

    static void print(int[] iArr, int i, int[] iArr2, int i2, ConstantPool constantPool) {
        System.out.print("locals: ");
        System.out.println(toString(iArr, i, constantPool));
        System.out.print("stack: ");
        System.out.println(toString(iArr2, i2, constantPool));
        System.out.println();
    }
}
