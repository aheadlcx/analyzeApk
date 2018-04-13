package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import java.util.Collection;

public class IgnoredPropertyException extends PropertyBindingException {
    private static final long serialVersionUID = 1;

    public IgnoredPropertyException(JsonParser jsonParser, String str, JsonLocation jsonLocation, Class<?> cls, String str2, Collection<Object> collection) {
        super(jsonParser, str, jsonLocation, cls, str2, collection);
    }

    @Deprecated
    public IgnoredPropertyException(String str, JsonLocation jsonLocation, Class<?> cls, String str2, Collection<Object> collection) {
        super(str, jsonLocation, cls, str2, collection);
    }

    public static IgnoredPropertyException from(JsonParser jsonParser, Object obj, String str, Collection<Object> collection) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        Class cls;
        if (obj instanceof Class) {
            cls = (Class) obj;
        } else {
            cls = obj.getClass();
        }
        IgnoredPropertyException ignoredPropertyException = new IgnoredPropertyException(jsonParser, "Ignored field \"" + str + "\" (class " + cls.getName() + ") encountered; mapper configured not to allow this", jsonParser.getCurrentLocation(), cls, str, collection);
        ignoredPropertyException.prependPath(obj, str);
        return ignoredPropertyException;
    }
}
