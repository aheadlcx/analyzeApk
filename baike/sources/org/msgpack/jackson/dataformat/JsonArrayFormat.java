package org.msgpack.jackson.dataformat;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

public class JsonArrayFormat extends JacksonAnnotationIntrospector {
    private static final Value ARRAY_FORMAT = new Value().withShape(Shape.ARRAY);

    public Value findFormat(Annotated annotated) {
        Value findFormat = super.findFormat(annotated);
        return findFormat != null ? findFormat : ARRAY_FORMAT;
    }

    public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedClass) {
        Boolean findIgnoreUnknownProperties = super.findIgnoreUnknownProperties(annotatedClass);
        return findIgnoreUnknownProperties != null ? findIgnoreUnknownProperties : Boolean.valueOf(true);
    }
}
