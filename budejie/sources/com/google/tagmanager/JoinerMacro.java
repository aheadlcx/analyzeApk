package com.google.tagmanager;

import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class JoinerMacro extends FunctionCallImplementation {
    private static final String ARG0 = Key.ARG0.toString();
    private static final String DEFAULT_ITEM_SEPARATOR = "";
    private static final String DEFAULT_KEY_VALUE_SEPARATOR = "=";
    private static final String ESCAPE = Key.ESCAPE.toString();
    private static final String ID = FunctionType.JOINER.toString();
    private static final String ITEM_SEPARATOR = Key.ITEM_SEPARATOR.toString();
    private static final String KEY_VALUE_SEPARATOR = Key.KEY_VALUE_SEPARATOR.toString();

    private enum EscapeType {
        NONE,
        URL,
        BACKSLASH
    }

    public static String getFunctionId() {
        return ID;
    }

    public JoinerMacro() {
        super(ID, ARG0);
    }

    public boolean isCacheable() {
        return true;
    }

    public Value evaluate(Map<String, Value> map) {
        Value value = (Value) map.get(ARG0);
        if (value == null) {
            return Types.getDefaultValue();
        }
        EscapeType escapeType;
        Value value2 = (Value) map.get(ITEM_SEPARATOR);
        String valueToString = value2 != null ? Types.valueToString(value2) : "";
        value2 = (Value) map.get(KEY_VALUE_SEPARATOR);
        String valueToString2 = value2 != null ? Types.valueToString(value2) : "=";
        EscapeType escapeType2 = EscapeType.NONE;
        value2 = (Value) map.get(ESCAPE);
        Set set;
        if (value2 != null) {
            String valueToString3 = Types.valueToString(value2);
            if ("url".equals(valueToString3)) {
                escapeType = EscapeType.URL;
                set = null;
            } else if ("backslash".equals(valueToString3)) {
                escapeType = EscapeType.BACKSLASH;
                set = new HashSet();
                addTo(set, valueToString);
                addTo(set, valueToString2);
                set.remove(Character.valueOf('\\'));
            } else {
                Log.e("Joiner: unsupported escape type: " + valueToString3);
                return Types.getDefaultValue();
            }
        }
        set = null;
        escapeType = escapeType2;
        StringBuilder stringBuilder = new StringBuilder();
        switch (value.type) {
            case 2:
                Object obj = 1;
                Value[] valueArr = value.listItem;
                int length = valueArr.length;
                int i = 0;
                while (i < length) {
                    Value value3 = valueArr[i];
                    if (obj == null) {
                        stringBuilder.append(valueToString);
                    }
                    append(stringBuilder, Types.valueToString(value3), escapeType, set);
                    i++;
                    obj = null;
                }
                break;
            case 3:
                for (int i2 = 0; i2 < value.mapKey.length; i2++) {
                    if (i2 > 0) {
                        stringBuilder.append(valueToString);
                    }
                    String valueToString4 = Types.valueToString(value.mapKey[i2]);
                    String valueToString5 = Types.valueToString(value.mapValue[i2]);
                    append(stringBuilder, valueToString4, escapeType, set);
                    stringBuilder.append(valueToString2);
                    append(stringBuilder, valueToString5, escapeType, set);
                }
                break;
            default:
                append(stringBuilder, Types.valueToString(value), escapeType, set);
                break;
        }
        return Types.objectToValue(stringBuilder.toString());
    }

    private void addTo(Set<Character> set, String str) {
        for (int i = 0; i < str.length(); i++) {
            set.add(Character.valueOf(str.charAt(i)));
        }
    }

    private void append(StringBuilder stringBuilder, String str, EscapeType escapeType, Set<Character> set) {
        stringBuilder.append(escape(str, escapeType, set));
    }

    private String escape(String str, EscapeType escapeType, Set<Character> set) {
        switch (escapeType) {
            case URL:
                try {
                    return ValueEscapeUtil.urlEncode(str);
                } catch (Throwable e) {
                    Log.e("Joiner: unsupported encoding", e);
                    return str;
                }
            case BACKSLASH:
                String replace = str.replace("\\", "\\\\");
                String str2 = replace;
                for (Character ch : set) {
                    CharSequence ch2 = ch.toString();
                    str2 = str2.replace(ch2, "\\" + ch2);
                }
                return str2;
            default:
                return str;
        }
    }
}
