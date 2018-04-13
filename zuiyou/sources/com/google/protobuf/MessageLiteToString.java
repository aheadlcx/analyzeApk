package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite.ExtendableMessage;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

final class MessageLiteToString {
    private static final String BUILDER_LIST_SUFFIX = "OrBuilderList";
    private static final String BYTES_SUFFIX = "Bytes";
    private static final String LIST_SUFFIX = "List";

    MessageLiteToString() {
    }

    static String toString(MessageLite messageLite, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("# ").append(str);
        reflectivePrintWithIndent(messageLite, stringBuilder, 0);
        return stringBuilder.toString();
    }

    private static void reflectivePrintWithIndent(MessageLite messageLite, StringBuilder stringBuilder, int i) {
        Map hashMap = new HashMap();
        Map hashMap2 = new HashMap();
        Set<String> treeSet = new TreeSet();
        for (Method method : messageLite.getClass().getDeclaredMethods()) {
            hashMap2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String replaceFirst : treeSet) {
            String str;
            Method method2;
            String replaceFirst2 = replaceFirst.replaceFirst("get", "");
            if (replaceFirst2.endsWith(LIST_SUFFIX) && !replaceFirst2.endsWith(BUILDER_LIST_SUFFIX)) {
                str = replaceFirst2.substring(0, 1).toLowerCase() + replaceFirst2.substring(1, replaceFirst2.length() - LIST_SUFFIX.length());
                method2 = (Method) hashMap.get("get" + replaceFirst2);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    printField(stringBuilder, i, camelCaseToSnakeCase(str), GeneratedMessageLite.invokeOrDie(method2, messageLite, new Object[0]));
                }
            }
            if (!(((Method) hashMap2.get("set" + replaceFirst2)) == null || (replaceFirst2.endsWith(BYTES_SUFFIX) && hashMap.containsKey("get" + replaceFirst2.substring(0, replaceFirst2.length() - BYTES_SUFFIX.length()))))) {
                str = replaceFirst2.substring(0, 1).toLowerCase() + replaceFirst2.substring(1);
                method2 = (Method) hashMap.get("get" + replaceFirst2);
                Method method3 = (Method) hashMap.get("has" + replaceFirst2);
                if (method2 != null) {
                    boolean booleanValue;
                    Object invokeOrDie = GeneratedMessageLite.invokeOrDie(method2, messageLite, new Object[0]);
                    if (method3 != null) {
                        booleanValue = ((Boolean) GeneratedMessageLite.invokeOrDie(method3, messageLite, new Object[0])).booleanValue();
                    } else if (isDefaultValue(invokeOrDie)) {
                        booleanValue = false;
                    } else {
                        booleanValue = true;
                    }
                    if (booleanValue) {
                        printField(stringBuilder, i, camelCaseToSnakeCase(str), invokeOrDie);
                    }
                }
            }
        }
        if (messageLite instanceof ExtendableMessage) {
            Iterator it = ((ExtendableMessage) messageLite).extensions.iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                printField(stringBuilder, i, "[" + ((ExtensionDescriptor) entry.getKey()).getNumber() + "]", entry.getValue());
            }
        }
        if (((GeneratedMessageLite) messageLite).unknownFields != null) {
            ((GeneratedMessageLite) messageLite).unknownFields.printWithIndent(stringBuilder, i);
        }
    }

    private static boolean isDefaultValue(Object obj) {
        if (obj instanceof Boolean) {
            boolean z;
            if (((Boolean) obj).booleanValue()) {
                z = false;
            } else {
                z = true;
            }
            return z;
        } else if (obj instanceof Integer) {
            if (((Integer) obj).intValue() != 0) {
                return false;
            }
            return true;
        } else if (obj instanceof Float) {
            if (((Float) obj).floatValue() != 0.0f) {
                return false;
            }
            return true;
        } else if (obj instanceof Double) {
            if (((Double) obj).doubleValue() != 0.0d) {
                return false;
            }
            return true;
        } else if (obj instanceof String) {
            return obj.equals("");
        } else {
            if (obj instanceof ByteString) {
                return obj.equals(ByteString.EMPTY);
            }
            if (obj instanceof MessageLite) {
                if (obj != ((MessageLite) obj).getDefaultInstanceForType()) {
                    return false;
                }
                return true;
            } else if (!(obj instanceof Enum)) {
                return false;
            } else {
                if (((Enum) obj).ordinal() != 0) {
                    return false;
                }
                return true;
            }
        }
    }

    static final void printField(StringBuilder stringBuilder, int i, String str, Object obj) {
        int i2 = 0;
        if (obj instanceof List) {
            for (Object printField : (List) obj) {
                printField(stringBuilder, i, str, printField);
            }
            return;
        }
        stringBuilder.append('\n');
        for (int i3 = 0; i3 < i; i3++) {
            stringBuilder.append(' ');
        }
        stringBuilder.append(str);
        if (obj instanceof String) {
            stringBuilder.append(": \"").append(TextFormatEscaper.escapeText((String) obj)).append('\"');
        } else if (obj instanceof ByteString) {
            stringBuilder.append(": \"").append(TextFormatEscaper.escapeBytes((ByteString) obj)).append('\"');
        } else if (obj instanceof GeneratedMessageLite) {
            stringBuilder.append(" {");
            reflectivePrintWithIndent((GeneratedMessageLite) obj, stringBuilder, i + 2);
            stringBuilder.append("\n");
            while (i2 < i) {
                stringBuilder.append(' ');
                i2++;
            }
            stringBuilder.append("}");
        } else {
            stringBuilder.append(": ").append(obj.toString());
        }
    }

    private static final String camelCaseToSnakeCase(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                stringBuilder.append("_");
            }
            stringBuilder.append(Character.toLowerCase(charAt));
        }
        return stringBuilder.toString();
    }
}
