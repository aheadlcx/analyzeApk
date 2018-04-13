package com.alibaba.fastjson.serializer;

public enum SerializerFeature {
    QuoteFieldNames,
    UseSingleQuotes,
    WriteMapNullValue,
    WriteEnumUsingToString,
    UseISO8601DateFormat,
    WriteNullListAsEmpty,
    WriteNullStringAsEmpty,
    WriteNullNumberAsZero,
    WriteNullBooleanAsFalse,
    SkipTransientField,
    SortField,
    WriteTabAsSpecial,
    PrettyFormat,
    WriteClassName,
    DisableCircularReferenceDetect,
    WriteSlashAsSpecial,
    WriteDateUseDateFormat,
    NotWriteRootClassName,
    DisableCheckSpecialChar,
    BeanToArray,
    WriteNonStringKeyAsString,
    NotWriteDefaultValue;
    
    public static final SerializerFeature[] EMPTY = null;
    public final int mask;

    static {
        EMPTY = new SerializerFeature[0];
    }

    public static int of(SerializerFeature[] serializerFeatureArr) {
        int i = 0;
        if (serializerFeatureArr != null) {
            int i2 = 0;
            while (i2 < serializerFeatureArr.length) {
                int i3 = serializerFeatureArr[i2].mask | i;
                i2++;
                i = i3;
            }
        }
        return i;
    }
}
