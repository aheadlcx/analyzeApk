package com.google.tagmanager.protobuf.nano;

import com.facebook.common.util.UriUtil;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

public final class MessageNanoPrinter {
    private static final String INDENT = "  ";
    private static final int MAX_STRING_LEN = 200;

    private MessageNanoPrinter() {
    }

    public static <T extends MessageNano> String print(T t) {
        if (t == null) {
            return "null";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            print(t.getClass().getSimpleName(), t.getClass(), t, new StringBuffer(), stringBuffer);
            return stringBuffer.toString();
        } catch (IllegalAccessException e) {
            return "Error printing proto: " + e.getMessage();
        }
    }

    private static void print(String str, Class<?> cls, Object obj, StringBuffer stringBuffer, StringBuffer stringBuffer2) throws IllegalAccessException {
        if (MessageNano.class.isAssignableFrom(cls)) {
            if (obj != null) {
                stringBuffer2.append(stringBuffer).append(str);
                stringBuffer.append(INDENT);
                stringBuffer2.append(" <\n");
                for (Field field : cls.getFields()) {
                    int modifiers = field.getModifiers();
                    String name = field.getName();
                    if (!((modifiers & 1) != 1 || (modifiers & 8) == 8 || name.startsWith("_") || name.endsWith("_"))) {
                        Class type = field.getType();
                        Object obj2 = field.get(obj);
                        if (type.isArray()) {
                            Class componentType = type.getComponentType();
                            if (componentType == Byte.TYPE) {
                                print(name, type, obj2, stringBuffer, stringBuffer2);
                            } else {
                                int length = obj2 == null ? 0 : Array.getLength(obj2);
                                for (modifiers = 0; modifiers < length; modifiers++) {
                                    print(name, componentType, Array.get(obj2, modifiers), stringBuffer, stringBuffer2);
                                }
                            }
                        } else {
                            print(name, type, obj2, stringBuffer, stringBuffer2);
                        }
                    }
                }
                stringBuffer.delete(stringBuffer.length() - INDENT.length(), stringBuffer.length());
                stringBuffer2.append(stringBuffer).append(">\n");
            }
        } else if (obj != null) {
            stringBuffer2.append(stringBuffer).append(deCamelCaseify(str)).append(": ");
            if (obj instanceof String) {
                stringBuffer2.append("\"").append(sanitizeString((String) obj)).append("\"");
            } else {
                stringBuffer2.append(obj);
            }
            stringBuffer2.append("\n");
        }
    }

    private static String deCamelCaseify(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (i == 0) {
                stringBuffer.append(Character.toLowerCase(charAt));
            } else if (Character.isUpperCase(charAt)) {
                stringBuffer.append('_').append(Character.toLowerCase(charAt));
            } else {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer.toString();
    }

    private static String sanitizeString(String str) {
        if (!str.startsWith(UriUtil.HTTP_SCHEME) && str.length() > 200) {
            str = str.substring(0, 200) + "[...]";
        }
        return escapeString(str);
    }

    private static String escapeString(String str) {
        int length = str.length();
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt < ' ' || charAt > '~' || charAt == '\"' || charAt == '\'') {
                stringBuilder.append(String.format("\\u%04x", new Object[]{Integer.valueOf(charAt)}));
            } else {
                stringBuilder.append(charAt);
            }
        }
        return stringBuilder.toString();
    }
}
