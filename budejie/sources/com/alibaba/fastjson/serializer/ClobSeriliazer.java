package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.sql.Clob;

public class ClobSeriliazer implements ObjectSerializer {
    public static final ClobSeriliazer instance = new ClobSeriliazer();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        if (obj == null) {
            try {
                jSONSerializer.writeNull();
                return;
            } catch (Throwable e) {
                throw new IOException("write clob error", e);
            }
        }
        Reader characterStream = ((Clob) obj).getCharacterStream();
        StringWriter stringWriter = new StringWriter();
        char[] cArr = new char[1024];
        while (true) {
            int read = characterStream.read(cArr);
            if (read != -1) {
                stringWriter.write(cArr, 0, read);
            } else {
                characterStream.close();
                jSONSerializer.write(stringWriter.toString());
                return;
            }
        }
    }
}
