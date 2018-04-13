package com.alibaba.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializable;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JSONPObject implements JSONSerializable {
    private String function;
    private final List<Object> parameters = new ArrayList();

    public JSONPObject(String str) {
        this.function = str;
    }

    public String getFunction() {
        return this.function;
    }

    public void setFunction(String str) {
        this.function = str;
    }

    public List<Object> getParameters() {
        return this.parameters;
    }

    public void addParameter(Object obj) {
        this.parameters.add(obj);
    }

    public String toJSONString() {
        return null;
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        serializeWriter.write(this.function);
        serializeWriter.write(40);
        for (int i2 = 0; i2 < this.parameters.size(); i2++) {
            if (i2 != 0) {
                serializeWriter.write(44);
            }
            jSONSerializer.write(this.parameters.get(i2));
        }
        serializeWriter.write(41);
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
