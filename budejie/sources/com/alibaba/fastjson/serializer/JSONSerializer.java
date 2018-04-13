package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.FieldInfo;
import java.io.Writer;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class JSONSerializer {
    protected List<AfterFilter> afterFilters;
    protected List<BeforeFilter> beforeFilters;
    private final SerializeConfig config;
    protected SerialContext context;
    protected List<ContextValueFilter> contextValueFilters;
    private DateFormat dateFormat;
    private String dateFormatPattern;
    private String indent;
    private int indentCount;
    protected List<LabelFilter> labelFilters;
    protected Locale locale;
    protected List<NameFilter> nameFilters;
    public final SerializeWriter out;
    protected List<PropertyFilter> propertyFilters;
    protected List<PropertyPreFilter> propertyPreFilters;
    protected IdentityHashMap<Object, SerialContext> references;
    protected TimeZone timeZone;
    protected List<ValueFilter> valueFilters;

    public JSONSerializer() {
        this(new SerializeWriter(), SerializeConfig.getGlobalInstance());
    }

    public JSONSerializer(SerializeWriter serializeWriter) {
        this(serializeWriter, SerializeConfig.getGlobalInstance());
    }

    public JSONSerializer(SerializeConfig serializeConfig) {
        this(new SerializeWriter(), serializeConfig);
    }

    public JSONSerializer(SerializeWriter serializeWriter, SerializeConfig serializeConfig) {
        this.beforeFilters = null;
        this.afterFilters = null;
        this.propertyFilters = null;
        this.valueFilters = null;
        this.nameFilters = null;
        this.propertyPreFilters = null;
        this.labelFilters = null;
        this.contextValueFilters = null;
        this.indentCount = 0;
        this.indent = "\t";
        this.references = null;
        this.timeZone = JSON.defaultTimeZone;
        this.locale = JSON.defaultLocale;
        this.out = serializeWriter;
        this.config = serializeConfig;
    }

    public String getDateFormatPattern() {
        if (this.dateFormat instanceof SimpleDateFormat) {
            return ((SimpleDateFormat) this.dateFormat).toPattern();
        }
        return this.dateFormatPattern;
    }

    public DateFormat getDateFormat() {
        if (this.dateFormat == null && this.dateFormatPattern != null) {
            this.dateFormat = new SimpleDateFormat(this.dateFormatPattern, this.locale);
            this.dateFormat.setTimeZone(this.timeZone);
        }
        return this.dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
        if (this.dateFormatPattern != null) {
            this.dateFormatPattern = null;
        }
    }

    public void setDateFormat(String str) {
        this.dateFormatPattern = str;
        if (this.dateFormat != null) {
            this.dateFormat = null;
        }
    }

    public SerialContext getContext() {
        return this.context;
    }

    public void setContext(SerialContext serialContext) {
        this.context = serialContext;
    }

    public void setContext(SerialContext serialContext, Object obj, Object obj2, int i) {
        setContext(serialContext, obj, obj2, i, 0);
    }

    public void setContext(SerialContext serialContext, Object obj, Object obj2, int i, int i2) {
        if (!this.out.disableCircularReferenceDetect) {
            this.context = new SerialContext(serialContext, obj, obj2, i, i2);
            if (this.references == null) {
                this.references = new IdentityHashMap();
            }
            this.references.put(obj, this.context);
        }
    }

    public void setContext(Object obj, Object obj2) {
        setContext(this.context, obj, obj2, 0);
    }

    public void popContext() {
        if (this.context != null) {
            this.context = this.context.parent;
        }
    }

    public final boolean isWriteClassName(Type type, Object obj) {
        return this.out.isEnabled(SerializerFeature.WriteClassName) && !(type == null && this.out.isEnabled(SerializerFeature.NotWriteRootClassName) && this.context.parent == null);
    }

    public boolean containsReference(Object obj) {
        return this.references != null && this.references.containsKey(obj);
    }

    public void writeReference(Object obj) {
        SerialContext serialContext = this.context;
        if (obj == serialContext.object) {
            this.out.write("{\"$ref\":\"@\"}");
            return;
        }
        SerialContext serialContext2 = serialContext.parent;
        if (serialContext2 == null || obj != serialContext2.object) {
            while (serialContext.parent != null) {
                serialContext = serialContext.parent;
            }
            if (obj == serialContext.object) {
                this.out.write("{\"$ref\":\"$\"}");
                return;
            }
            this.out.write("{\"$ref\":\"");
            this.out.write(((SerialContext) this.references.get(obj)).toString());
            this.out.write("\"}");
            return;
        }
        this.out.write("{\"$ref\":\"..\"}");
    }

    public List<ValueFilter> getValueFilters() {
        if (this.valueFilters == null) {
            this.valueFilters = new ArrayList();
        }
        return this.valueFilters;
    }

    public boolean checkValue() {
        return (this.valueFilters != null && this.valueFilters.size() > 0) || ((this.contextValueFilters != null && this.contextValueFilters.size() > 0) || this.out.writeNonStringValueAsString);
    }

    public List<ContextValueFilter> getContextValueFilters() {
        if (this.contextValueFilters == null) {
            this.contextValueFilters = new ArrayList();
        }
        return this.contextValueFilters;
    }

    public int getIndentCount() {
        return this.indentCount;
    }

    public void incrementIndent() {
        this.indentCount++;
    }

    public void decrementIdent() {
        this.indentCount--;
    }

    public void println() {
        this.out.write(10);
        for (int i = 0; i < this.indentCount; i++) {
            this.out.write(this.indent);
        }
    }

    public List<BeforeFilter> getBeforeFilters() {
        if (this.beforeFilters == null) {
            this.beforeFilters = new ArrayList();
        }
        return this.beforeFilters;
    }

    public List<AfterFilter> getAfterFilters() {
        if (this.afterFilters == null) {
            this.afterFilters = new ArrayList();
        }
        return this.afterFilters;
    }

    public boolean hasNameFilters() {
        return this.nameFilters != null && this.nameFilters.size() > 0;
    }

    public List<NameFilter> getNameFilters() {
        if (this.nameFilters == null) {
            this.nameFilters = new ArrayList();
        }
        return this.nameFilters;
    }

    public List<PropertyPreFilter> getPropertyPreFilters() {
        if (this.propertyPreFilters == null) {
            this.propertyPreFilters = new ArrayList();
        }
        return this.propertyPreFilters;
    }

    public List<LabelFilter> getLabelFilters() {
        if (this.labelFilters == null) {
            this.labelFilters = new ArrayList();
        }
        return this.labelFilters;
    }

    public List<PropertyFilter> getPropertyFilters() {
        if (this.propertyFilters == null) {
            this.propertyFilters = new ArrayList();
        }
        return this.propertyFilters;
    }

    public SerializeWriter getWriter() {
        return this.out;
    }

    public String toString() {
        return this.out.toString();
    }

    public void config(SerializerFeature serializerFeature, boolean z) {
        this.out.config(serializerFeature, z);
    }

    public boolean isEnabled(SerializerFeature serializerFeature) {
        return this.out.isEnabled(serializerFeature);
    }

    public void writeNull() {
        this.out.writeNull();
    }

    public SerializeConfig getMapping() {
        return this.config;
    }

    public static void write(Writer writer, Object obj) {
        SerializeWriter serializeWriter = new SerializeWriter();
        try {
            new JSONSerializer(serializeWriter).write(obj);
            serializeWriter.writeTo(writer);
            serializeWriter.close();
        } catch (Throwable e) {
            throw new JSONException(e.getMessage(), e);
        } catch (Throwable th) {
            serializeWriter.close();
        }
    }

    public static void write(SerializeWriter serializeWriter, Object obj) {
        new JSONSerializer(serializeWriter).write(obj);
    }

    public final void write(Object obj) {
        if (obj == null) {
            this.out.writeNull();
            return;
        }
        try {
            getObjectWriter(obj.getClass()).write(this, obj, null, null, 0);
        } catch (Throwable e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public final void writeWithFieldName(Object obj, Object obj2) {
        writeWithFieldName(obj, obj2, null, 0);
    }

    protected final void writeKeyValue(char c, String str, Object obj) {
        if (c != '\u0000') {
            this.out.write((int) c);
        }
        this.out.writeFieldName(str);
        write(obj);
    }

    public final void writeWithFieldName(Object obj, Object obj2, Type type, int i) {
        if (obj == null) {
            try {
                this.out.writeNull();
                return;
            } catch (Throwable e) {
                throw new JSONException(e.getMessage(), e);
            }
        }
        getObjectWriter(obj.getClass()).write(this, obj, obj2, type, i);
    }

    public final void writeWithFormat(Object obj, String str) {
        if (obj instanceof Date) {
            DateFormat dateFormat = getDateFormat();
            if (dateFormat == null) {
                dateFormat = new SimpleDateFormat(str, this.locale);
                dateFormat.setTimeZone(this.timeZone);
            }
            this.out.writeString(dateFormat.format((Date) obj));
            return;
        }
        write(obj);
    }

    public final void write(String str) {
        StringCodec.instance.write(this, str);
    }

    public ObjectSerializer getObjectWriter(Class<?> cls) {
        return this.config.getObjectWriter(cls);
    }

    public void close() {
        this.out.close();
    }

    public boolean writeDirect() {
        return this.out.writeDirect && this.beforeFilters == null && this.afterFilters == null && this.valueFilters == null && this.contextValueFilters == null && this.propertyFilters == null && this.nameFilters == null && this.propertyPreFilters == null && this.labelFilters == null;
    }

    public FieldInfo getFieldInfo() {
        return null;
    }

    public Object processValue(JavaBeanSerializer javaBeanSerializer, Object obj, String str, Object obj2) {
        if (obj2 != null && this.out.writeNonStringValueAsString && ((obj2 instanceof Number) || (obj2 instanceof Boolean))) {
            obj2 = obj2.toString();
        }
        List<ValueFilter> list = this.valueFilters;
        if (list != null) {
            for (ValueFilter process : list) {
                obj2 = process.process(obj, str, obj2);
            }
        }
        List<ContextValueFilter> list2 = this.contextValueFilters;
        if (list2 != null) {
            BeanContext beanContext = javaBeanSerializer.getFieldSerializer(str).fieldContext;
            for (ContextValueFilter process2 : list2) {
                obj2 = process2.process(beanContext, obj, str, obj2);
            }
        }
        return obj2;
    }

    public String processKey(Object obj, String str, Object obj2) {
        List<NameFilter> list = this.nameFilters;
        if (list != null) {
            for (NameFilter process : list) {
                str = process.process(obj, str, obj2);
            }
        }
        return str;
    }

    public boolean applyName(Object obj, String str) {
        List<PropertyPreFilter> list = this.propertyPreFilters;
        if (list == null) {
            return true;
        }
        for (PropertyPreFilter apply : list) {
            if (!apply.apply(this, obj, str)) {
                return false;
            }
        }
        return true;
    }

    public boolean apply(Object obj, String str, Object obj2) {
        List<PropertyFilter> list = this.propertyFilters;
        if (list == null) {
            return true;
        }
        for (PropertyFilter apply : list) {
            if (!apply.apply(obj, str, obj2)) {
                return false;
            }
        }
        return true;
    }

    public char writeBefore(Object obj, char c) {
        List<BeforeFilter> list = this.beforeFilters;
        if (list != null) {
            for (BeforeFilter writeBefore : list) {
                c = writeBefore.writeBefore(this, obj, c);
            }
        }
        return c;
    }

    public char writeAfter(Object obj, char c) {
        List<AfterFilter> list = this.afterFilters;
        if (list != null) {
            for (AfterFilter writeAfter : list) {
                c = writeAfter.writeAfter(this, obj, c);
            }
        }
        return c;
    }

    public boolean applyLabel(String str) {
        List<LabelFilter> list = this.labelFilters;
        if (list == null) {
            return true;
        }
        for (LabelFilter apply : list) {
            if (!apply.apply(str)) {
                return false;
            }
        }
        return true;
    }

    public void addFilter(SerializeFilter serializeFilter) {
        if (serializeFilter != null) {
            if (serializeFilter instanceof PropertyPreFilter) {
                getPropertyPreFilters().add((PropertyPreFilter) serializeFilter);
            }
            if (serializeFilter instanceof NameFilter) {
                getNameFilters().add((NameFilter) serializeFilter);
            }
            if (serializeFilter instanceof ValueFilter) {
                getValueFilters().add((ValueFilter) serializeFilter);
            }
            if (serializeFilter instanceof ContextValueFilter) {
                getContextValueFilters().add((ContextValueFilter) serializeFilter);
            }
            if (serializeFilter instanceof PropertyFilter) {
                getPropertyFilters().add((PropertyFilter) serializeFilter);
            }
            if (serializeFilter instanceof BeforeFilter) {
                getBeforeFilters().add((BeforeFilter) serializeFilter);
            }
            if (serializeFilter instanceof AfterFilter) {
                getAfterFilters().add((AfterFilter) serializeFilter);
            }
            if (serializeFilter instanceof LabelFilter) {
                getLabelFilters().add((LabelFilter) serializeFilter);
            }
        }
    }
}
