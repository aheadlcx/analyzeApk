package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;

public class DoubleSerializer implements ObjectSerializer {
    public static final DoubleSerializer instance = new DoubleSerializer();
    private DecimalFormat decimalFormat;

    public DoubleSerializer() {
        this.decimalFormat = null;
    }

    public DoubleSerializer(DecimalFormat decimalFormat) {
        this.decimalFormat = null;
        this.decimalFormat = decimalFormat;
    }

    public DoubleSerializer(String str) {
        this(new DecimalFormat(str));
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj != null) {
            double doubleValue = ((Double) obj).doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                serializeWriter.writeNull();
                return;
            }
            CharSequence d;
            if (this.decimalFormat == null) {
                d = Double.toString(doubleValue);
                if (d.endsWith(".0")) {
                    d = d.substring(0, d.length() - 2);
                }
            } else {
                d = this.decimalFormat.format(doubleValue);
            }
            serializeWriter.append(d);
            if (serializeWriter.isEnabled(SerializerFeature.WriteClassName)) {
                serializeWriter.write(68);
            }
        } else if (serializeWriter.isEnabled(SerializerFeature.WriteNullNumberAsZero)) {
            serializeWriter.write(48);
        } else {
            serializeWriter.writeNull();
        }
    }
}
