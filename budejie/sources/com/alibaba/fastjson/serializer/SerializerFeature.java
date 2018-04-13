package com.alibaba.fastjson.serializer;

public enum SerializerFeature {
    QuoteFieldNames,
    UseSingleQuotes,
    WriteMapNullValue,
    WriteEnumUsingToString,
    WriteEnumUsingName,
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
    BrowserCompatible,
    WriteDateUseDateFormat,
    NotWriteRootClassName,
    DisableCheckSpecialChar,
    BeanToArray,
    WriteNonStringKeyAsString,
    NotWriteDefaultValue,
    BrowserSecure,
    IgnoreNonFieldGetter,
    WriteNonStringValueAsString;
    
    public static final SerializerFeature[] EMPTY = null;
    public final int mask;

    static {
        EMPTY = new SerializerFeature[0];
    }

    public final int getMask() {
        return this.mask;
    }

    public static boolean isEnabled(int i, SerializerFeature serializerFeature) {
        return (serializerFeature.mask & i) != 0;
    }

    public static boolean isEnabled(int i, int i2, SerializerFeature serializerFeature) {
        int i3 = serializerFeature.mask;
        return ((i & i3) == 0 && (i3 & i2) == 0) ? false : true;
    }

    public static int config(int i, SerializerFeature serializerFeature, boolean z) {
        if (z) {
            return serializerFeature.mask | i;
        }
        return (serializerFeature.mask ^ -1) & i;
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
