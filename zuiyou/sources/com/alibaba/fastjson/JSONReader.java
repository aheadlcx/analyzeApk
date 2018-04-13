package com.alibaba.fastjson;

import cn.xiaochuankeji.tieba.push.proto.Push.Packet;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Map;

public class JSONReader implements Closeable {
    private JSONStreamContext context;
    private final DefaultJSONParser parser;
    private Reader reader;

    public JSONReader(Reader reader) {
        this(new JSONLexer(readAll(reader)));
        this.reader = reader;
    }

    static String readAll(Reader reader) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            char[] cArr = new char[2048];
            while (true) {
                int read = reader.read(cArr, 0, cArr.length);
                if (read < 0) {
                    return stringBuilder.toString();
                }
                stringBuilder.append(cArr, 0, read);
            }
        } catch (Throwable e) {
            throw new JSONException("read string from reader error", e);
        }
    }

    public JSONReader(JSONLexer jSONLexer) {
        this(new DefaultJSONParser(jSONLexer));
    }

    public JSONReader(DefaultJSONParser defaultJSONParser) {
        this.parser = defaultJSONParser;
    }

    public void config(Feature feature, boolean z) {
        this.parser.config(feature, z);
    }

    public void startObject() {
        if (this.context == null) {
            this.context = new JSONStreamContext(null, 1001);
        } else {
            startStructure();
            this.context = new JSONStreamContext(this.context, 1001);
        }
        this.parser.accept(12);
    }

    public void endObject() {
        this.parser.accept(13);
        endStructure();
    }

    public void startArray() {
        if (this.context == null) {
            this.context = new JSONStreamContext(null, 1004);
        } else {
            startStructure();
            this.context = new JSONStreamContext(this.context, 1004);
        }
        this.parser.accept(14);
    }

    public void endArray() {
        this.parser.accept(15);
        endStructure();
    }

    private void startStructure() {
        switch (this.context.state) {
            case 1001:
            case 1004:
                return;
            case 1002:
                this.parser.accept(17);
                return;
            case 1003:
            case Packet.CLIENTVER_FIELD_NUMBER /*1005*/:
                this.parser.accept(16);
                return;
            default:
                throw new JSONException("illegal state : " + this.context.state);
        }
    }

    private void endStructure() {
        this.context = this.context.parent;
        if (this.context != null) {
            int i;
            switch (this.context.state) {
                case 1001:
                case 1003:
                    i = 1002;
                    break;
                case 1002:
                    i = 1003;
                    break;
                case 1004:
                    i = Packet.CLIENTVER_FIELD_NUMBER;
                    break;
                default:
                    i = -1;
                    break;
            }
            if (i != -1) {
                this.context.state = i;
            }
        }
    }

    public boolean hasNext() {
        if (this.context == null) {
            throw new JSONException("context is null");
        }
        int token = this.parser.lexer.token();
        int i = this.context.state;
        switch (i) {
            case 1001:
            case 1003:
                if (token == 13) {
                    return false;
                }
                return true;
            case 1004:
            case Packet.CLIENTVER_FIELD_NUMBER /*1005*/:
                if (token != 15) {
                    return true;
                }
                return false;
            default:
                throw new JSONException("illegal state : " + i);
        }
    }

    public int peek() {
        return this.parser.lexer.token();
    }

    public void close() {
        this.parser.lexer.close();
        if (this.reader != null) {
            try {
                this.reader.close();
            } catch (Throwable e) {
                throw new JSONException("closed reader error", e);
            }
        }
    }

    public Integer readInteger() {
        Object parse;
        if (this.context == null) {
            parse = this.parser.parse();
        } else {
            readBefore();
            parse = this.parser.parse();
            readAfter();
        }
        return TypeUtils.castToInt(parse);
    }

    public Long readLong() {
        Object parse;
        if (this.context == null) {
            parse = this.parser.parse();
        } else {
            readBefore();
            parse = this.parser.parse();
            readAfter();
        }
        return TypeUtils.castToLong(parse);
    }

    public String readString() {
        Object parse;
        if (this.context == null) {
            parse = this.parser.parse();
        } else {
            readBefore();
            parse = this.parser.parse();
            readAfter();
        }
        return TypeUtils.castToString(parse);
    }

    public <T> T readObject(TypeReference<T> typeReference) {
        return readObject(typeReference.type);
    }

    public <T> T readObject(Type type) {
        if (this.context == null) {
            return this.parser.parseObject(type);
        }
        readBefore();
        T parseObject = this.parser.parseObject(type);
        readAfter();
        return parseObject;
    }

    public <T> T readObject(Class<T> cls) {
        if (this.context == null) {
            return this.parser.parseObject((Class) cls);
        }
        readBefore();
        T parseObject = this.parser.parseObject((Class) cls);
        readAfter();
        return parseObject;
    }

    public void readObject(Object obj) {
        if (this.context == null) {
            this.parser.parseObject(obj);
            return;
        }
        readBefore();
        this.parser.parseObject(obj);
        readAfter();
    }

    public Object readObject() {
        if (this.context == null) {
            return this.parser.parse();
        }
        readBefore();
        Object parse = this.parser.parse();
        readAfter();
        return parse;
    }

    public Object readObject(Map map) {
        if (this.context == null) {
            return this.parser.parseObject(map);
        }
        readBefore();
        Object parseObject = this.parser.parseObject(map);
        readAfter();
        return parseObject;
    }

    private void readBefore() {
        int i = this.context.state;
        switch (i) {
            case 1001:
            case 1004:
                return;
            case 1002:
                this.parser.accept(17);
                return;
            case 1003:
            case Packet.CLIENTVER_FIELD_NUMBER /*1005*/:
                this.parser.accept(16);
                return;
            default:
                throw new JSONException("illegal state : " + i);
        }
    }

    private void readAfter() {
        int i = 1002;
        int i2 = this.context.state;
        switch (i2) {
            case 1001:
            case 1003:
                break;
            case 1002:
                i = 1003;
                break;
            case 1004:
                i = Packet.CLIENTVER_FIELD_NUMBER;
                break;
            case Packet.CLIENTVER_FIELD_NUMBER /*1005*/:
                i = -1;
                break;
            default:
                throw new JSONException("illegal state : " + i2);
        }
        if (i != -1) {
            this.context.state = i;
        }
    }
}
