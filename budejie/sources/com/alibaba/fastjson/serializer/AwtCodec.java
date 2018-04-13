package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.umeng.analytics.pro.x;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.lang.reflect.Type;

public class AwtCodec implements ObjectDeserializer, ObjectSerializer {
    public static final AwtCodec instance = new AwtCodec();

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
            return;
        }
        if (obj instanceof Point) {
            Point point = (Point) obj;
            serializeWriter.writeFieldValue(writeClassName(serializeWriter, Point.class, '{'), "x", point.getX());
            serializeWriter.writeFieldValue(',', "y", point.getY());
        } else if (obj instanceof Font) {
            Font font = (Font) obj;
            serializeWriter.writeFieldValue(writeClassName(serializeWriter, Font.class, '{'), "name", font.getName());
            serializeWriter.writeFieldValue(',', x.P, font.getStyle());
            serializeWriter.writeFieldValue(',', "size", font.getSize());
        } else if (obj instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) obj;
            serializeWriter.writeFieldValue(writeClassName(serializeWriter, Rectangle.class, '{'), "x", rectangle.getX());
            serializeWriter.writeFieldValue(',', "y", rectangle.getY());
            serializeWriter.writeFieldValue(',', IndexEntry.COLUMN_NAME_WIDTH, rectangle.getWidth());
            serializeWriter.writeFieldValue(',', IndexEntry.COLUMN_NAME_HEIGHT, rectangle.getHeight());
        } else if (obj instanceof Color) {
            Color color = (Color) obj;
            serializeWriter.writeFieldValue(writeClassName(serializeWriter, Color.class, '{'), "r", color.getRed());
            serializeWriter.writeFieldValue(',', IXAdRequestInfo.GPS, color.getGreen());
            serializeWriter.writeFieldValue(',', "b", color.getBlue());
            if (color.getAlpha() > 0) {
                serializeWriter.writeFieldValue(',', "alpha", color.getAlpha());
            }
        } else {
            throw new JSONException("not support awt class : " + obj.getClass().getName());
        }
        serializeWriter.write(125);
    }

    protected char writeClassName(SerializeWriter serializeWriter, Class<?> cls, char c) {
        if (!serializeWriter.isEnabled(SerializerFeature.WriteClassName)) {
            return c;
        }
        serializeWriter.write(123);
        serializeWriter.writeFieldName(JSON.DEFAULT_TYPE_KEY);
        serializeWriter.writeString(cls.getName());
        return ',';
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 8) {
            jSONLexer.nextToken(16);
            return null;
        } else if (jSONLexer.token() == 12 || jSONLexer.token() == 16) {
            jSONLexer.nextToken();
            if (type == Point.class) {
                return parsePoint(defaultJSONParser);
            }
            if (type == Rectangle.class) {
                return parseRectangle(defaultJSONParser);
            }
            if (type == Color.class) {
                return parseColor(defaultJSONParser);
            }
            if (type == Font.class) {
                return parseFont(defaultJSONParser);
            }
            throw new JSONException("not support awt class : " + type);
        } else {
            throw new JSONException("syntax error");
        }
    }

    protected Font parseFont(DefaultJSONParser defaultJSONParser) {
        int i = 0;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        String str = null;
        int i2 = 0;
        while (jSONLexer.token() != 13) {
            if (jSONLexer.token() == 4) {
                String stringVal = jSONLexer.stringVal();
                jSONLexer.nextTokenWithColon(2);
                if (stringVal.equalsIgnoreCase("name")) {
                    if (jSONLexer.token() == 4) {
                        str = jSONLexer.stringVal();
                        jSONLexer.nextToken();
                    } else {
                        throw new JSONException("syntax error");
                    }
                } else if (stringVal.equalsIgnoreCase(x.P)) {
                    if (jSONLexer.token() == 2) {
                        i = jSONLexer.intValue();
                        jSONLexer.nextToken();
                    } else {
                        throw new JSONException("syntax error");
                    }
                } else if (!stringVal.equalsIgnoreCase("size")) {
                    throw new JSONException("syntax error, " + stringVal);
                } else if (jSONLexer.token() == 2) {
                    i2 = jSONLexer.intValue();
                    jSONLexer.nextToken();
                } else {
                    throw new JSONException("syntax error");
                }
                if (jSONLexer.token() == 16) {
                    jSONLexer.nextToken(4);
                }
            } else {
                throw new JSONException("syntax error");
            }
        }
        jSONLexer.nextToken();
        return new Font(str, i, i2);
    }

    protected Color parseColor(DefaultJSONParser defaultJSONParser) {
        int i = 0;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (jSONLexer.token() != 13) {
            if (jSONLexer.token() == 4) {
                String stringVal = jSONLexer.stringVal();
                jSONLexer.nextTokenWithColon(2);
                if (jSONLexer.token() == 2) {
                    int intValue = jSONLexer.intValue();
                    jSONLexer.nextToken();
                    if (stringVal.equalsIgnoreCase("r")) {
                        i4 = intValue;
                    } else if (stringVal.equalsIgnoreCase(IXAdRequestInfo.GPS)) {
                        i3 = intValue;
                    } else if (stringVal.equalsIgnoreCase("b")) {
                        i2 = intValue;
                    } else if (stringVal.equalsIgnoreCase("alpha")) {
                        i = intValue;
                    } else {
                        throw new JSONException("syntax error, " + stringVal);
                    }
                    if (jSONLexer.token() == 16) {
                        jSONLexer.nextToken(4);
                    }
                } else {
                    throw new JSONException("syntax error");
                }
            }
            throw new JSONException("syntax error");
        }
        jSONLexer.nextToken();
        return new Color(i4, i3, i2, i);
    }

    protected Rectangle parseRectangle(DefaultJSONParser defaultJSONParser) {
        int i = 0;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (jSONLexer.token() != 13) {
            if (jSONLexer.token() == 4) {
                String stringVal = jSONLexer.stringVal();
                jSONLexer.nextTokenWithColon(2);
                if (jSONLexer.token() == 2) {
                    int intValue = jSONLexer.intValue();
                    jSONLexer.nextToken();
                    if (stringVal.equalsIgnoreCase("x")) {
                        i4 = intValue;
                    } else if (stringVal.equalsIgnoreCase("y")) {
                        i3 = intValue;
                    } else if (stringVal.equalsIgnoreCase(IndexEntry.COLUMN_NAME_WIDTH)) {
                        i2 = intValue;
                    } else if (stringVal.equalsIgnoreCase(IndexEntry.COLUMN_NAME_HEIGHT)) {
                        i = intValue;
                    } else {
                        throw new JSONException("syntax error, " + stringVal);
                    }
                    if (jSONLexer.token() == 16) {
                        jSONLexer.nextToken(4);
                    }
                } else {
                    throw new JSONException("syntax error");
                }
            }
            throw new JSONException("syntax error");
        }
        jSONLexer.nextToken();
        return new Rectangle(i4, i3, i2, i);
    }

    protected Point parsePoint(DefaultJSONParser defaultJSONParser) {
        int i = 0;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i2 = 0;
        while (jSONLexer.token() != 13) {
            if (jSONLexer.token() == 4) {
                String stringVal = jSONLexer.stringVal();
                if (JSON.DEFAULT_TYPE_KEY.equals(stringVal)) {
                    defaultJSONParser.acceptType("java.awt.Point");
                } else {
                    jSONLexer.nextTokenWithColon(2);
                    if (jSONLexer.token() == 2) {
                        int intValue = jSONLexer.intValue();
                        jSONLexer.nextToken();
                        if (stringVal.equalsIgnoreCase("x")) {
                            i2 = intValue;
                        } else if (stringVal.equalsIgnoreCase("y")) {
                            i = intValue;
                        } else {
                            throw new JSONException("syntax error, " + stringVal);
                        }
                        if (jSONLexer.token() == 16) {
                            jSONLexer.nextToken(4);
                        }
                    } else {
                        throw new JSONException("syntax error : " + jSONLexer.tokenName());
                    }
                }
            }
            throw new JSONException("syntax error");
        }
        jSONLexer.nextToken();
        return new Point(i2, i);
    }

    public int getFastMatchToken() {
        return 12;
    }
}
