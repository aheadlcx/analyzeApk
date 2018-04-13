package com.alibaba.fastjson;

import cn.v6.sixrooms.room.statistic.StatiscEvent;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ASMJavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.ASMJavaBeanSerializer;
import com.alibaba.fastjson.serializer.FieldSerializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.util.IOUtils;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

public class JSONPath implements JSONAware {
    private static int CACHE_SIZE = 1024;
    private static ConcurrentMap<String, JSONPath> pathCache = new ConcurrentHashMap(128, 0.75f, 1);
    private ParserConfig parserConfig;
    private final String path;
    private Segement[] segments;
    private SerializeConfig serializeConfig;

    interface Segement {
        Object eval(JSONPath jSONPath, Object obj, Object obj2);
    }

    static class ArrayAccessSegement implements Segement {
        private final int index;

        public ArrayAccessSegement(int i) {
            this.index = i;
        }

        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.getArrayItem(obj2, this.index);
        }

        public boolean setValue(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.setArrayItem(jSONPath, obj, this.index, obj2);
        }
    }

    interface Filter {
        boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3);
    }

    public static class FilterSegement implements Segement {
        private final Filter filter;

        public FilterSegement(Filter filter) {
            this.filter = filter;
        }

        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            if (obj2 == null) {
                return null;
            }
            List arrayList = new ArrayList();
            if (obj2 instanceof Iterable) {
                for (Object next : (Iterable) obj2) {
                    if (this.filter.apply(jSONPath, obj, obj2, next)) {
                        arrayList.add(next);
                    }
                }
                return arrayList;
            } else if (this.filter.apply(jSONPath, obj, obj2, obj2)) {
                return obj2;
            } else {
                return null;
            }
        }
    }

    static class IntBetweenSegement implements Filter {
        private final long endValue;
        private final boolean not;
        private final String propertyName;
        private final long startValue;

        public IntBetweenSegement(String str, long j, long j2, boolean z) {
            this.propertyName = str;
            this.startValue = j;
            this.endValue = j2;
            this.not = z;
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, false);
            if (propertyValue == null) {
                return false;
            }
            if (propertyValue instanceof Number) {
                long longValue = ((Number) propertyValue).longValue();
                if (longValue >= this.startValue && longValue <= this.endValue) {
                    return !this.not;
                }
            }
            return this.not;
        }
    }

    static class IntInSegement implements Filter {
        private final boolean not;
        private final String propertyName;
        private final long[] values;

        public IntInSegement(String str, long[] jArr, boolean z) {
            this.propertyName = str;
            this.values = jArr;
            this.not = z;
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, false);
            if (propertyValue == null) {
                return false;
            }
            if (propertyValue instanceof Number) {
                long longValue = ((Number) propertyValue).longValue();
                for (long j : this.values) {
                    if (j == longValue) {
                        return !this.not;
                    }
                }
            }
            return this.not;
        }
    }

    static class IntObjInSegement implements Filter {
        private final boolean not;
        private final String propertyName;
        private final Long[] values;

        public IntObjInSegement(String str, Long[] lArr, boolean z) {
            this.propertyName = str;
            this.values = lArr;
            this.not = z;
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            boolean z = true;
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, false);
            int i;
            if (propertyValue == null) {
                Long[] lArr = this.values;
                int length = lArr.length;
                i = 0;
                while (i < length) {
                    if (lArr[i] != null) {
                        i++;
                    } else if (this.not) {
                        return false;
                    } else {
                        return true;
                    }
                }
                return this.not;
            }
            if (propertyValue instanceof Number) {
                long longValue = ((Number) propertyValue).longValue();
                for (Long l : this.values) {
                    if (l != null && l.longValue() == longValue) {
                        if (this.not) {
                            z = false;
                        }
                        return z;
                    }
                }
            }
            return this.not;
        }
    }

    static class IntOpSegement implements Filter {
        private final Operator op;
        private final String propertyName;
        private final long value;

        public IntOpSegement(String str, long j, Operator operator) {
            this.propertyName = str;
            this.value = j;
            this.op = operator;
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, false);
            if (propertyValue == null) {
                return false;
            }
            if (!(propertyValue instanceof Number)) {
                return false;
            }
            long longValue = ((Number) propertyValue).longValue();
            if (this.op == Operator.EQ) {
                return longValue == this.value;
            } else if (this.op == Operator.NE) {
                if (longValue == this.value) {
                    return false;
                }
                return true;
            } else if (this.op == Operator.GE) {
                if (longValue < this.value) {
                    return false;
                }
                return true;
            } else if (this.op == Operator.GT) {
                if (longValue <= this.value) {
                    return false;
                }
                return true;
            } else if (this.op == Operator.LE) {
                if (longValue > this.value) {
                    return false;
                }
                return true;
            } else if (this.op != Operator.LT) {
                return false;
            } else {
                if (longValue >= this.value) {
                    return false;
                }
                return true;
            }
        }
    }

    static class JSONPathParser {
        private char ch;
        private int level;
        private final String path;
        private int pos;

        public JSONPathParser(String str) {
            this.path = str;
            next();
        }

        void next() {
            String str = this.path;
            int i = this.pos;
            this.pos = i + 1;
            this.ch = str.charAt(i);
        }

        boolean isEOF() {
            return this.pos >= this.path.length();
        }

        Segement readSegement() {
            if (this.level == 0 && this.path.length() == 1) {
                if (isDigitFirst(this.ch)) {
                    return new ArrayAccessSegement(this.ch - 48);
                }
                if ((this.ch >= 'a' && this.ch <= 'z') || (this.ch >= 'A' && this.ch <= 'Z')) {
                    return new PropertySegement(Character.toString(this.ch));
                }
            }
            while (!isEOF()) {
                skipWhitespace();
                if (this.ch == '@') {
                    next();
                    return SelfSegement.instance;
                } else if (this.ch == '$') {
                    next();
                } else if (this.ch == '.' || this.ch == '/') {
                    next();
                    if (this.ch == '*') {
                        if (!isEOF()) {
                            next();
                        }
                        return WildCardSegement.instance;
                    } else if (isDigitFirst(this.ch)) {
                        return parseArrayAccess(false);
                    } else {
                        String readName = readName();
                        if (this.ch != '(') {
                            return new PropertySegement(readName);
                        }
                        next();
                        if (this.ch == ')') {
                            if (!isEOF()) {
                                next();
                            }
                            if ("size".equals(readName)) {
                                return SizeSegement.instance;
                            }
                            throw new UnsupportedOperationException();
                        }
                        throw new UnsupportedOperationException();
                    }
                } else if (this.ch == '[') {
                    return parseArrayAccess(true);
                } else {
                    if (this.level == 0) {
                        return new PropertySegement(readName());
                    }
                    throw new UnsupportedOperationException();
                }
            }
            return null;
        }

        public final void skipWhitespace() {
            while (this.ch <= ' ') {
                if (this.ch == ' ' || this.ch == '\r' || this.ch == '\n' || this.ch == '\t' || this.ch == '\f' || this.ch == '\b') {
                    next();
                } else {
                    return;
                }
            }
        }

        Segement parseArrayAccess(boolean z) {
            boolean z2;
            boolean z3 = true;
            if (z) {
                accept('[');
            }
            if (this.ch == '?') {
                next();
                accept('(');
                if (this.ch == '@') {
                    next();
                    accept('.');
                }
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2 || IOUtils.firstIdentifier(this.ch)) {
                String readName = readName();
                skipWhitespace();
                if (z2 && this.ch == ')') {
                    next();
                    if (z) {
                        accept(']');
                    }
                    return new FilterSegement(new NotNullSegement(readName));
                } else if (z && this.ch == ']') {
                    next();
                    return new FilterSegement(new NotNullSegement(readName));
                } else {
                    Operator readOp = readOp();
                    skipWhitespace();
                    Object readValue;
                    if (readOp == Operator.BETWEEN || readOp == Operator.NOT_BETWEEN) {
                        if (readOp != Operator.NOT_BETWEEN) {
                            z3 = false;
                        }
                        Object readValue2 = readValue();
                        if ("and".equalsIgnoreCase(readName())) {
                            readValue = readValue();
                            if (readValue2 == null || readValue == null) {
                                throw new JSONPathException(this.path);
                            } else if (JSONPath.isInt(readValue2.getClass()) && JSONPath.isInt(readValue.getClass())) {
                                return new FilterSegement(new IntBetweenSegement(readName, ((Number) readValue2).longValue(), ((Number) readValue).longValue(), z3));
                            } else {
                                throw new JSONPathException(this.path);
                            }
                        }
                        throw new JSONPathException(this.path);
                    } else if (readOp == Operator.IN || readOp == Operator.NOT_IN) {
                        boolean z4 = readOp == Operator.NOT_IN;
                        accept('(');
                        List arrayList = new ArrayList();
                        arrayList.add(readValue());
                        while (true) {
                            skipWhitespace();
                            if (this.ch != ',') {
                                break;
                            }
                            next();
                            arrayList.add(readValue());
                        }
                        accept(')');
                        if (z2) {
                            accept(')');
                        }
                        if (z) {
                            accept(']');
                        }
                        boolean z5 = true;
                        z2 = true;
                        r5 = true;
                        for (Object next : arrayList) {
                            if (next != null) {
                                Class cls = next.getClass();
                                if (!(!r5 || cls == Byte.class || cls == Short.class || cls == Integer.class || cls == Long.class)) {
                                    z2 = false;
                                    r5 = false;
                                }
                                if (z5 && cls != String.class) {
                                    z5 = false;
                                }
                            } else if (r5) {
                                r5 = false;
                            }
                        }
                        if (arrayList.size() == 1 && arrayList.get(0) == null) {
                            if (z4) {
                                return new FilterSegement(new NotNullSegement(readName));
                            }
                            return new FilterSegement(new NullSegement(readName));
                        } else if (r5) {
                            if (arrayList.size() == 1) {
                                return new FilterSegement(new IntOpSegement(readName, ((Number) arrayList.get(0)).longValue(), z4 ? Operator.NE : Operator.EQ));
                            }
                            long[] jArr = new long[arrayList.size()];
                            for (r3 = 0; r3 < jArr.length; r3++) {
                                jArr[r3] = ((Number) arrayList.get(r3)).longValue();
                            }
                            return new FilterSegement(new IntInSegement(readName, jArr, z4));
                        } else if (z5) {
                            if (arrayList.size() == 1) {
                                return new FilterSegement(new StringOpSegement(readName, (String) arrayList.get(0), z4 ? Operator.NE : Operator.EQ));
                            }
                            String[] strArr = new String[arrayList.size()];
                            arrayList.toArray(strArr);
                            return new FilterSegement(new StringInSegement(readName, strArr, z4));
                        } else if (z2) {
                            Long[] lArr = new Long[arrayList.size()];
                            for (r3 = 0; r3 < lArr.length; r3++) {
                                Number number = (Number) arrayList.get(r3);
                                if (number != null) {
                                    lArr[r3] = Long.valueOf(number.longValue());
                                }
                            }
                            return new FilterSegement(new IntObjInSegement(readName, lArr, z4));
                        } else {
                            throw new UnsupportedOperationException();
                        }
                    } else if (this.ch == '\'' || this.ch == '\"') {
                        String readString = readString();
                        if (z2) {
                            accept(')');
                        }
                        if (z) {
                            accept(']');
                        }
                        if (readOp == Operator.RLIKE) {
                            return new FilterSegement(new RlikeSegement(readName, readString, false));
                        }
                        if (readOp == Operator.NOT_RLIKE) {
                            return new FilterSegement(new RlikeSegement(readName, readString, true));
                        }
                        Operator operator;
                        if (readOp == Operator.LIKE || readOp == Operator.NOT_LIKE) {
                            while (readString.indexOf("%%") != -1) {
                                readString = readString.replaceAll("%%", "%");
                            }
                            r5 = readOp == Operator.NOT_LIKE;
                            int indexOf = readString.indexOf(37);
                            if (indexOf != -1) {
                                String str;
                                Object split = readString.split("%");
                                String[] strArr2 = null;
                                if (indexOf == 0) {
                                    if (readString.charAt(readString.length() - 1) == '%') {
                                        strArr2 = new String[(split.length - 1)];
                                        System.arraycopy(split, 1, strArr2, 0, strArr2.length);
                                        str = null;
                                        readString = null;
                                    } else {
                                        readString = split[split.length - 1];
                                        if (split.length > 2) {
                                            strArr2 = new String[(split.length - 2)];
                                            System.arraycopy(split, 1, strArr2, 0, strArr2.length);
                                            str = readString;
                                            readString = null;
                                        } else {
                                            str = readString;
                                            readString = null;
                                        }
                                    }
                                } else if (readString.charAt(readString.length() - 1) == '%') {
                                    readValue = split;
                                    readString = null;
                                    str = null;
                                } else if (split.length == 1) {
                                    readString = split[0];
                                    str = null;
                                } else if (split.length == 2) {
                                    readString = split[0];
                                    str = split[1];
                                } else {
                                    String str2 = split[0];
                                    readString = split[split.length - 1];
                                    strArr2 = new String[(split.length - 2)];
                                    System.arraycopy(split, 1, strArr2, 0, strArr2.length);
                                    str = readString;
                                    readString = str2;
                                }
                                return new FilterSegement(new MatchSegement(readName, readString, str, strArr2, r5));
                            } else if (readOp == Operator.LIKE) {
                                operator = Operator.EQ;
                            } else {
                                operator = Operator.NE;
                            }
                        } else {
                            operator = readOp;
                        }
                        return new FilterSegement(new StringOpSegement(readName, readString, operator));
                    } else if (isDigitFirst(this.ch)) {
                        long readLongValue = readLongValue();
                        if (z2) {
                            accept(')');
                        }
                        if (z) {
                            accept(']');
                        }
                        return new FilterSegement(new IntOpSegement(readName, readLongValue, readOp));
                    } else {
                        if (this.ch == 'n') {
                            if ("null".equals(readName())) {
                                if (z2) {
                                    accept(')');
                                }
                                accept(']');
                                if (readOp == Operator.EQ) {
                                    return new FilterSegement(new NullSegement(readName));
                                }
                                if (readOp == Operator.NE) {
                                    return new FilterSegement(new NotNullSegement(readName));
                                }
                                throw new UnsupportedOperationException();
                            }
                        }
                        throw new UnsupportedOperationException();
                    }
                }
            }
            int i;
            int i2 = this.pos - 1;
            while (this.ch != ']' && this.ch != '/' && !isEOF()) {
                next();
            }
            if (z) {
                i = this.pos - 1;
            } else if (this.ch == '/') {
                i = this.pos - 1;
            } else {
                i = this.pos;
            }
            Segement buildArraySegement = buildArraySegement(this.path.substring(i2, i));
            if (!z || isEOF()) {
                return buildArraySegement;
            }
            accept(']');
            return buildArraySegement;
        }

        protected long readLongValue() {
            int i = this.pos - 1;
            if (this.ch == '+' || this.ch == '-') {
                next();
            }
            while (this.ch >= '0' && this.ch <= '9') {
                next();
            }
            return Long.parseLong(this.path.substring(i, this.pos - 1));
        }

        protected Object readValue() {
            skipWhitespace();
            if (isDigitFirst(this.ch)) {
                return Long.valueOf(readLongValue());
            }
            if (this.ch == '\"' || this.ch == '\'') {
                return readString();
            }
            if (this.ch == 'n') {
                if ("null".equals(readName())) {
                    return null;
                }
                throw new JSONPathException(this.path);
            }
            throw new UnsupportedOperationException();
        }

        static boolean isDigitFirst(char c) {
            return c == '-' || c == '+' || (c >= '0' && c <= '9');
        }

        protected Operator readOp() {
            Operator operator = null;
            if (this.ch == '=') {
                next();
                operator = Operator.EQ;
            } else if (this.ch == '!') {
                next();
                accept('=');
                operator = Operator.NE;
            } else if (this.ch == '<') {
                next();
                if (this.ch == '=') {
                    next();
                    operator = Operator.LE;
                } else {
                    operator = Operator.LT;
                }
            } else if (this.ch == '>') {
                next();
                if (this.ch == '=') {
                    next();
                    operator = Operator.GE;
                } else {
                    operator = Operator.GT;
                }
            }
            if (operator != null) {
                return operator;
            }
            String readName = readName();
            if ("not".equalsIgnoreCase(readName)) {
                skipWhitespace();
                readName = readName();
                if ("like".equalsIgnoreCase(readName)) {
                    return Operator.NOT_LIKE;
                }
                if ("rlike".equalsIgnoreCase(readName)) {
                    return Operator.NOT_RLIKE;
                }
                if (StatiscEvent.IN.equalsIgnoreCase(readName)) {
                    return Operator.NOT_IN;
                }
                if ("between".equalsIgnoreCase(readName)) {
                    return Operator.NOT_BETWEEN;
                }
                throw new UnsupportedOperationException();
            } else if ("like".equalsIgnoreCase(readName)) {
                return Operator.LIKE;
            } else {
                if ("rlike".equalsIgnoreCase(readName)) {
                    return Operator.RLIKE;
                }
                if (StatiscEvent.IN.equalsIgnoreCase(readName)) {
                    return Operator.IN;
                }
                if ("between".equalsIgnoreCase(readName)) {
                    return Operator.BETWEEN;
                }
                throw new UnsupportedOperationException();
            }
        }

        String readName() {
            skipWhitespace();
            if (this.ch == '\\' || IOUtils.firstIdentifier(this.ch)) {
                StringBuilder stringBuilder = new StringBuilder();
                while (!isEOF()) {
                    if (this.ch != '\\') {
                        if (!IOUtils.isIdent(this.ch)) {
                            break;
                        }
                        stringBuilder.append(this.ch);
                        next();
                    } else {
                        next();
                        stringBuilder.append(this.ch);
                        if (isEOF()) {
                            break;
                        }
                        next();
                    }
                }
                if (isEOF() && IOUtils.isIdent(this.ch)) {
                    stringBuilder.append(this.ch);
                }
                return stringBuilder.toString();
            }
            throw new JSONPathException("illeal jsonpath syntax. " + this.path);
        }

        String readString() {
            char c = this.ch;
            next();
            int i = this.pos - 1;
            while (this.ch != c && !isEOF()) {
                next();
            }
            String substring = this.path.substring(i, isEOF() ? this.pos : this.pos - 1);
            accept(c);
            return substring;
        }

        void accept(char c) {
            if (this.ch != c) {
                throw new JSONPathException("expect '" + c + ", but '" + this.ch + "'");
            } else if (!isEOF()) {
                next();
            }
        }

        public Segement[] explain() {
            if (this.path == null || this.path.isEmpty()) {
                throw new IllegalArgumentException();
            }
            Object obj = new Segement[8];
            while (true) {
                Segement readSegement = readSegement();
                if (readSegement == null) {
                    break;
                }
                int i = this.level;
                this.level = i + 1;
                obj[i] = readSegement;
            }
            if (this.level == obj.length) {
                return obj;
            }
            Object obj2 = new Segement[this.level];
            System.arraycopy(obj, 0, obj2, 0, this.level);
            return obj2;
        }

        Segement buildArraySegement(String str) {
            int i = 0;
            int length = str.length();
            char charAt = str.charAt(0);
            char charAt2 = str.charAt(length - 1);
            int indexOf = str.indexOf(44);
            String[] split;
            if (str.length() <= 2 || charAt != '\'' || charAt2 != '\'') {
                length = str.indexOf(58);
                if (indexOf == -1 && length == -1) {
                    return new ArrayAccessSegement(Integer.parseInt(str));
                }
                if (indexOf != -1) {
                    split = str.split(",");
                    int[] iArr = new int[split.length];
                    while (i < split.length) {
                        iArr[i] = Integer.parseInt(split[i]);
                        i++;
                    }
                    return new MultiIndexSegement(iArr);
                } else if (length != -1) {
                    int i2;
                    String[] split2 = str.split(":");
                    int[] iArr2 = new int[split2.length];
                    for (length = 0; length < split2.length; length++) {
                        String str2 = split2[length];
                        if (!str2.isEmpty()) {
                            iArr2[length] = Integer.parseInt(str2);
                        } else if (length == 0) {
                            iArr2[length] = 0;
                        } else {
                            throw new UnsupportedOperationException();
                        }
                    }
                    int i3 = iArr2[0];
                    if (iArr2.length > 1) {
                        i = iArr2[1];
                    } else {
                        i = -1;
                    }
                    if (iArr2.length == 3) {
                        i2 = iArr2[2];
                    } else {
                        i2 = 1;
                    }
                    if (i >= 0 && i < i3) {
                        throw new UnsupportedOperationException("end must greater than or equals start. start " + i3 + ",  end " + i);
                    } else if (i2 > 0) {
                        return new RangeSegement(i3, i, i2);
                    } else {
                        throw new UnsupportedOperationException("step must greater than zero : " + i2);
                    }
                } else {
                    throw new UnsupportedOperationException();
                }
            } else if (indexOf == -1) {
                return new PropertySegement(str.substring(1, length - 1));
            } else {
                split = str.split(",");
                String[] strArr = new String[split.length];
                while (i < split.length) {
                    String str3 = split[i];
                    strArr[i] = str3.substring(1, str3.length() - 1);
                    i++;
                }
                return new MultiPropertySegement(strArr);
            }
        }
    }

    static class MatchSegement implements Filter {
        private final String[] containsValues;
        private final String endsWithValue;
        private final int minLength;
        private final boolean not;
        private final String propertyName;
        private final String startsWithValue;

        public MatchSegement(String str, String str2, String str3, String[] strArr, boolean z) {
            int length;
            int i = 0;
            this.propertyName = str;
            this.startsWithValue = str2;
            this.endsWithValue = str3;
            this.containsValues = strArr;
            this.not = z;
            if (str2 != null) {
                length = str2.length() + 0;
            } else {
                length = 0;
            }
            if (str3 != null) {
                length += str3.length();
            }
            if (strArr != null) {
                while (i < strArr.length) {
                    int length2 = strArr[i].length() + length;
                    i++;
                    length = length2;
                }
            }
            this.minLength = length;
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, false);
            if (propertyValue == null) {
                return false;
            }
            String obj4 = propertyValue.toString();
            if (obj4.length() < this.minLength) {
                return this.not;
            }
            int i;
            if (this.startsWithValue == null) {
                i = 0;
            } else if (!obj4.startsWith(this.startsWithValue)) {
                return this.not;
            } else {
                i = this.startsWithValue.length() + 0;
            }
            if (this.containsValues != null) {
                int i2 = i;
                for (String str : this.containsValues) {
                    i2 = obj4.indexOf(str, i2);
                    if (i2 == -1) {
                        return this.not;
                    }
                    i2 += str.length();
                }
            }
            if (this.endsWithValue != null && !obj4.endsWith(this.endsWithValue)) {
                return this.not;
            }
            if (this.not) {
                return false;
            }
            return true;
        }
    }

    static class MultiIndexSegement implements Segement {
        private final int[] indexes;

        public MultiIndexSegement(int[] iArr) {
            this.indexes = iArr;
        }

        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            List arrayList = new ArrayList(this.indexes.length);
            for (int arrayItem : this.indexes) {
                arrayList.add(jSONPath.getArrayItem(obj2, arrayItem));
            }
            return arrayList;
        }
    }

    static class MultiPropertySegement implements Segement {
        private final String[] propertyNames;

        public MultiPropertySegement(String[] strArr) {
            this.propertyNames = strArr;
        }

        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            List arrayList = new ArrayList(this.propertyNames.length);
            for (String propertyValue : this.propertyNames) {
                arrayList.add(jSONPath.getPropertyValue(obj2, propertyValue, true));
            }
            return arrayList;
        }
    }

    static class NotNullSegement implements Filter {
        private final String propertyName;

        public NotNullSegement(String str) {
            this.propertyName = str;
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            if (jSONPath.getPropertyValue(obj3, this.propertyName, false) != null) {
                return true;
            }
            return false;
        }
    }

    static class NullSegement implements Filter {
        private final String propertyName;

        public NullSegement(String str) {
            this.propertyName = str;
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            if (jSONPath.getPropertyValue(obj3, this.propertyName, false) == null) {
                return true;
            }
            return false;
        }
    }

    enum Operator {
        EQ,
        NE,
        GT,
        GE,
        LT,
        LE,
        LIKE,
        NOT_LIKE,
        RLIKE,
        NOT_RLIKE,
        IN,
        NOT_IN,
        BETWEEN,
        NOT_BETWEEN
    }

    static class PropertySegement implements Segement {
        private final String propertyName;

        public PropertySegement(String str) {
            this.propertyName = str;
        }

        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.getPropertyValue(obj2, this.propertyName, true);
        }

        public void setValue(JSONPath jSONPath, Object obj, Object obj2) {
            jSONPath.setPropertyValue(obj, this.propertyName, obj2);
        }
    }

    static class RangeSegement implements Segement {
        private final int end;
        private final int start;
        private final int step;

        public RangeSegement(int i, int i2, int i3) {
            this.start = i;
            this.end = i2;
            this.step = i3;
        }

        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            int intValue = SizeSegement.instance.eval(jSONPath, obj, obj2).intValue();
            int i = this.start >= 0 ? this.start : this.start + intValue;
            int i2 = this.end >= 0 ? this.end : this.end + intValue;
            List arrayList = new ArrayList(((i2 - i) / this.step) + 1);
            while (i <= i2 && i < intValue) {
                arrayList.add(jSONPath.getArrayItem(obj2, i));
                i += this.step;
            }
            return arrayList;
        }
    }

    static class RlikeSegement implements Filter {
        private final boolean not;
        private final Pattern pattern;
        private final String propertyName;

        public RlikeSegement(String str, String str2, boolean z) {
            this.propertyName = str;
            this.pattern = Pattern.compile(str2);
            this.not = z;
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, false);
            if (propertyValue == null) {
                return false;
            }
            boolean matches = this.pattern.matcher(propertyValue.toString()).matches();
            if (!this.not) {
                return matches;
            }
            if (matches) {
                return false;
            }
            return true;
        }
    }

    static class SelfSegement implements Segement {
        public static final SelfSegement instance = new SelfSegement();

        SelfSegement() {
        }

        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            return obj2;
        }
    }

    static class SizeSegement implements Segement {
        public static final SizeSegement instance = new SizeSegement();

        SizeSegement() {
        }

        public Integer eval(JSONPath jSONPath, Object obj, Object obj2) {
            return Integer.valueOf(jSONPath.evalSize(obj2));
        }
    }

    static class StringInSegement implements Filter {
        private final boolean not;
        private final String propertyName;
        private final String[] values;

        public StringInSegement(String str, String[] strArr, boolean z) {
            this.propertyName = str;
            this.values = strArr;
            this.not = z;
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            String propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, false);
            String[] strArr = this.values;
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                String str = strArr[i];
                if (str == propertyValue) {
                    if (this.not) {
                        return false;
                    }
                    return true;
                } else if (str == null || !str.equals(propertyValue)) {
                    i++;
                } else if (this.not) {
                    return false;
                } else {
                    return true;
                }
            }
            return this.not;
        }
    }

    static class StringOpSegement implements Filter {
        private final Operator op;
        private final String propertyName;
        private final String value;

        public StringOpSegement(String str, String str2, Operator operator) {
            this.propertyName = str;
            this.value = str2;
            this.op = operator;
        }

        public boolean apply(JSONPath jSONPath, Object obj, Object obj2, Object obj3) {
            Object propertyValue = jSONPath.getPropertyValue(obj3, this.propertyName, false);
            if (this.op == Operator.EQ) {
                return this.value.equals(propertyValue);
            }
            if (this.op == Operator.NE) {
                if (this.value.equals(propertyValue)) {
                    return false;
                }
                return true;
            } else if (propertyValue == null) {
                return false;
            } else {
                int compareTo = this.value.compareTo(propertyValue.toString());
                if (this.op == Operator.GE) {
                    if (compareTo > 0) {
                        return false;
                    }
                    return true;
                } else if (this.op == Operator.GT) {
                    if (compareTo >= 0) {
                        return false;
                    }
                    return true;
                } else if (this.op == Operator.LE) {
                    if (compareTo < 0) {
                        return false;
                    }
                    return true;
                } else if (this.op != Operator.LT) {
                    return false;
                } else {
                    if (compareTo <= 0) {
                        return false;
                    }
                    return true;
                }
            }
        }
    }

    static class WildCardSegement implements Segement {
        public static WildCardSegement instance = new WildCardSegement();

        WildCardSegement() {
        }

        public Object eval(JSONPath jSONPath, Object obj, Object obj2) {
            return jSONPath.getPropertyValues(obj2);
        }
    }

    public JSONPath(String str) {
        this(str, SerializeConfig.getGlobalInstance(), ParserConfig.getGlobalInstance());
    }

    public JSONPath(String str, SerializeConfig serializeConfig, ParserConfig parserConfig) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.path = str;
        this.serializeConfig = serializeConfig;
        this.parserConfig = parserConfig;
    }

    protected void init() {
        if (this.segments == null) {
            if ("*".equals(this.path)) {
                this.segments = new Segement[]{WildCardSegement.instance};
                return;
            }
            this.segments = new JSONPathParser(this.path).explain();
        }
    }

    public Object eval(Object obj) {
        if (obj == null) {
            return null;
        }
        init();
        Object obj2 = obj;
        for (Segement eval : this.segments) {
            obj2 = eval.eval(this, obj, obj2);
        }
        return obj2;
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        init();
        Object obj2 = obj;
        for (Segement eval : this.segments) {
            obj2 = eval.eval(this, obj, obj2);
            if (obj2 == null) {
                return false;
            }
        }
        return true;
    }

    public boolean containsValue(Object obj, Object obj2) {
        Object eval = eval(obj);
        if (eval == obj2) {
            return true;
        }
        if (eval == null) {
            return false;
        }
        if (!(eval instanceof Iterable)) {
            return eq(eval, obj2);
        }
        for (Object eq : (Iterable) eval) {
            if (eq(eq, obj2)) {
                return true;
            }
        }
        return false;
    }

    public int size(Object obj) {
        if (obj == null) {
            return -1;
        }
        init();
        Object obj2 = obj;
        for (Segement eval : this.segments) {
            obj2 = eval.eval(this, obj, obj2);
        }
        return evalSize(obj2);
    }

    public void arrayAdd(Object obj, Object... objArr) {
        int i = 0;
        if (objArr != null && objArr.length != 0 && obj != null) {
            init();
            Object obj2 = null;
            int i2 = 0;
            Object obj3 = obj;
            while (i2 < this.segments.length) {
                if (i2 == this.segments.length - 1) {
                    obj2 = obj3;
                }
                Object eval = this.segments[i2].eval(this, obj, obj3);
                i2++;
                obj3 = eval;
            }
            if (obj3 == null) {
                throw new JSONPathException("value not found in path " + this.path);
            } else if (obj3 instanceof Collection) {
                Collection collection = (Collection) obj3;
                int length = objArr.length;
                while (i < length) {
                    collection.add(objArr[i]);
                    i++;
                }
            } else {
                Class cls = obj3.getClass();
                if (cls.isArray()) {
                    int length2 = Array.getLength(obj3);
                    Object newInstance = Array.newInstance(cls.getComponentType(), objArr.length + length2);
                    System.arraycopy(obj3, 0, newInstance, 0, length2);
                    while (i < objArr.length) {
                        Array.set(newInstance, length2 + i, objArr[i]);
                        i++;
                    }
                    Segement segement = this.segments[this.segments.length - 1];
                    if (segement instanceof PropertySegement) {
                        ((PropertySegement) segement).setValue(this, obj2, newInstance);
                        return;
                    } else if (segement instanceof ArrayAccessSegement) {
                        ((ArrayAccessSegement) segement).setValue(this, obj2, newInstance);
                        return;
                    } else {
                        throw new UnsupportedOperationException();
                    }
                }
                throw new UnsupportedOperationException();
            }
        }
    }

    public boolean set(Object obj, Object obj2) {
        if (obj == null) {
            return false;
        }
        init();
        Object obj3 = obj;
        for (int i = 0; i < this.segments.length; i++) {
            if (i == this.segments.length - 1) {
                break;
            }
            obj3 = this.segments[i].eval(this, obj, obj3);
            if (obj3 == null) {
                obj3 = null;
                break;
            }
        }
        obj3 = null;
        if (obj3 == null) {
            return false;
        }
        Segement segement = this.segments[this.segments.length - 1];
        if (segement instanceof PropertySegement) {
            ((PropertySegement) segement).setValue(this, obj3, obj2);
            return true;
        } else if (segement instanceof ArrayAccessSegement) {
            return ((ArrayAccessSegement) segement).setValue(this, obj3, obj2);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public static Object eval(Object obj, String str) {
        return compile(str).eval(obj);
    }

    public static int size(Object obj, String str) {
        JSONPath compile = compile(str);
        return compile.evalSize(compile.eval(obj));
    }

    public static boolean contains(Object obj, String str) {
        if (obj == null) {
            return false;
        }
        return compile(str).contains(obj);
    }

    public static boolean containsValue(Object obj, String str, Object obj2) {
        return compile(str).containsValue(obj, obj2);
    }

    public static void arrayAdd(Object obj, String str, Object... objArr) {
        compile(str).arrayAdd(obj, objArr);
    }

    public static boolean set(Object obj, String str, Object obj2) {
        return compile(str).set(obj, obj2);
    }

    public static JSONPath compile(String str) {
        JSONPath jSONPath = (JSONPath) pathCache.get(str);
        if (jSONPath != null) {
            return jSONPath;
        }
        jSONPath = new JSONPath(str);
        if (pathCache.size() >= CACHE_SIZE) {
            return jSONPath;
        }
        pathCache.putIfAbsent(str, jSONPath);
        return (JSONPath) pathCache.get(str);
    }

    public static Object read(String str, String str2) {
        return compile(str2).eval(JSON.parse(str));
    }

    public String getPath() {
        return this.path;
    }

    protected Object getArrayItem(Object obj, int i) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            if (i >= 0) {
                if (i < list.size()) {
                    return list.get(i);
                }
                return null;
            } else if (Math.abs(i) <= list.size()) {
                return list.get(list.size() + i);
            } else {
                return null;
            }
        } else if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            if (i >= 0) {
                if (i < length) {
                    return Array.get(obj, i);
                }
                return null;
            } else if (Math.abs(i) <= length) {
                return Array.get(obj, length + i);
            } else {
                return null;
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public boolean setArrayItem(JSONPath jSONPath, Object obj, int i, Object obj2) {
        if (obj instanceof List) {
            List list = (List) obj;
            if (i >= 0) {
                list.set(i, obj2);
            } else {
                list.set(list.size() + i, obj2);
            }
        } else if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            if (i >= 0) {
                if (i < length) {
                    Array.set(obj, i, obj2);
                }
            } else if (Math.abs(i) <= length) {
                Array.set(obj, length + i, obj2);
            }
        } else {
            throw new UnsupportedOperationException();
        }
        return true;
    }

    protected Collection<Object> getPropertyValues(Object obj) {
        JavaBeanSerializer javaBeanSerializer = getJavaBeanSerializer(obj.getClass());
        if (javaBeanSerializer != null) {
            try {
                return javaBeanSerializer.getFieldValues(obj);
            } catch (Throwable e) {
                throw new JSONPathException("jsonpath error, path " + this.path, e);
            }
        } else if (obj instanceof Map) {
            return ((Map) obj).values();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    static boolean eq(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        if (obj.getClass() == obj2.getClass()) {
            return obj.equals(obj2);
        }
        if (!(obj instanceof Number)) {
            return obj.equals(obj2);
        }
        if (obj2 instanceof Number) {
            return eqNotNull((Number) obj, (Number) obj2);
        }
        return false;
    }

    static boolean eqNotNull(Number number, Number number2) {
        Class cls = number.getClass();
        boolean isInt = isInt(cls);
        Class cls2 = number.getClass();
        boolean isInt2 = isInt(cls2);
        if (!isInt || !isInt2) {
            boolean isDouble = isDouble(cls);
            boolean isDouble2 = isDouble(cls2);
            if ((!isDouble || !isDouble2) && ((!isDouble || !isInt) && (!isDouble2 || !isInt))) {
                return false;
            }
            if (number.doubleValue() != number2.doubleValue()) {
                return false;
            }
            return true;
        } else if (number.longValue() == number2.longValue()) {
            return true;
        } else {
            return false;
        }
    }

    protected static boolean isDouble(Class<?> cls) {
        return cls == Float.class || cls == Double.class;
    }

    protected static boolean isInt(Class<?> cls) {
        return cls == Byte.class || cls == Short.class || cls == Integer.class || cls == Long.class;
    }

    protected Object getPropertyValue(Object obj, String str, boolean z) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Map) {
            return ((Map) obj).get(str);
        }
        JavaBeanSerializer javaBeanSerializer = getJavaBeanSerializer(obj.getClass());
        if (javaBeanSerializer != null) {
            try {
                FieldSerializer fieldSerializer = javaBeanSerializer.getFieldSerializer(str);
                if (fieldSerializer != null) {
                    return fieldSerializer.getPropertyValue(obj);
                }
                return null;
            } catch (Throwable e) {
                throw new JSONPathException("jsonpath error, path " + this.path + ", segement " + str, e);
            }
        } else if (obj instanceof List) {
            List list = (List) obj;
            List arrayList = new ArrayList(list.size());
            for (int i = 0; i < list.size(); i++) {
                arrayList.add(getPropertyValue(list.get(i), str, z));
            }
            return arrayList;
        } else {
            throw new JSONPathException("jsonpath error, path " + this.path + ", segement " + str);
        }
    }

    protected boolean setPropertyValue(Object obj, String str, Object obj2) {
        if (obj instanceof Map) {
            ((Map) obj).put(str, obj2);
            return true;
        } else if (obj instanceof List) {
            for (Object next : (List) obj) {
                if (next != null) {
                    setPropertyValue(next, str, obj2);
                }
            }
            return true;
        } else {
            JavaBeanDeserializer javaBeanDeserializer;
            ObjectDeserializer deserializer = this.parserConfig.getDeserializer(obj.getClass());
            if (deserializer instanceof JavaBeanDeserializer) {
                javaBeanDeserializer = (JavaBeanDeserializer) deserializer;
            } else if (deserializer instanceof ASMJavaBeanDeserializer) {
                javaBeanDeserializer = ((ASMJavaBeanDeserializer) deserializer).getInnterSerializer();
            } else {
                javaBeanDeserializer = null;
            }
            if (javaBeanDeserializer != null) {
                FieldDeserializer fieldDeserializer = javaBeanDeserializer.getFieldDeserializer(str);
                if (fieldDeserializer == null) {
                    return false;
                }
                fieldDeserializer.setValue(obj, obj2);
                return true;
            }
            throw new UnsupportedOperationException();
        }
    }

    protected JavaBeanSerializer getJavaBeanSerializer(Class<?> cls) {
        ObjectSerializer objectWriter = this.serializeConfig.getObjectWriter(cls);
        if (objectWriter instanceof JavaBeanSerializer) {
            return (JavaBeanSerializer) objectWriter;
        }
        return objectWriter instanceof ASMJavaBeanSerializer ? ((ASMJavaBeanSerializer) objectWriter).getJavaBeanSerializer() : null;
    }

    int evalSize(Object obj) {
        int i = 0;
        if (obj == null) {
            return -1;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).size();
        }
        if (obj instanceof Object[]) {
            return ((Object[]) obj).length;
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj);
        }
        if (obj instanceof Map) {
            for (Object obj2 : ((Map) obj).values()) {
                if (obj2 != null) {
                    i++;
                }
            }
            return i;
        }
        JavaBeanSerializer javaBeanSerializer = getJavaBeanSerializer(obj.getClass());
        if (javaBeanSerializer == null) {
            return -1;
        }
        try {
            List fieldValues = javaBeanSerializer.getFieldValues(obj);
            for (int i2 = 0; i2 < fieldValues.size(); i2++) {
                if (fieldValues.get(i2) != null) {
                    i++;
                }
            }
            return i;
        } catch (Throwable e) {
            throw new JSONException("evalSize error : " + this.path, e);
        }
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        jSONSerializer.write(this.path);
    }

    public String toJSONString() {
        return JSON.toJSONString(this.path);
    }
}
