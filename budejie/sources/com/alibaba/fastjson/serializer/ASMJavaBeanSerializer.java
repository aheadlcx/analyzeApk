package com.alibaba.fastjson.serializer;

public abstract class ASMJavaBeanSerializer implements ObjectSerializer {
    protected JavaBeanSerializer nature;

    public ASMJavaBeanSerializer(Class<?> cls) {
        this.nature = new JavaBeanSerializer(cls);
    }

    public JavaBeanSerializer getJavaBeanSerializer() {
        return this.nature;
    }
}
