package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import java.io.Serializable;

public abstract class ConcreteBeanPropertyBase implements BeanProperty, Serializable {
    private static final long serialVersionUID = 1;
    protected transient Value _format;
    protected final PropertyMetadata _metadata;

    protected ConcreteBeanPropertyBase(PropertyMetadata propertyMetadata) {
        if (propertyMetadata == null) {
            propertyMetadata = PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
        }
        this._metadata = propertyMetadata;
    }

    protected ConcreteBeanPropertyBase(ConcreteBeanPropertyBase concreteBeanPropertyBase) {
        this._metadata = concreteBeanPropertyBase._metadata;
        this._format = concreteBeanPropertyBase._format;
    }

    public boolean isRequired() {
        return this._metadata.isRequired();
    }

    public PropertyMetadata getMetadata() {
        return this._metadata;
    }

    public boolean isVirtual() {
        return false;
    }

    @Deprecated
    public final Value findFormatOverrides(AnnotationIntrospector annotationIntrospector) {
        Value value = this._format;
        if (value != null) {
            return value;
        }
        if (annotationIntrospector != null) {
            Annotated member = getMember();
            if (member != null) {
                value = annotationIntrospector.findFormat(member);
            }
        }
        if (value == null) {
            return EMPTY_FORMAT;
        }
        return value;
    }

    public Value findPropertyFormat(MapperConfig<?> mapperConfig, Class<?> cls) {
        Value defaultPropertyFormat = mapperConfig.getDefaultPropertyFormat(cls);
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        Annotated member = getMember();
        if (annotationIntrospector == null || member == null) {
            return defaultPropertyFormat;
        }
        Value findFormat = annotationIntrospector.findFormat(member);
        return findFormat != null ? defaultPropertyFormat.withOverrides(findFormat) : defaultPropertyFormat;
    }

    public JsonInclude.Value findPropertyInclusion(MapperConfig<?> mapperConfig, Class<?> cls) {
        JsonInclude.Value defaultPropertyInclusion = mapperConfig.getDefaultPropertyInclusion(cls);
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        Annotated member = getMember();
        if (annotationIntrospector == null || member == null) {
            return defaultPropertyInclusion;
        }
        JsonInclude.Value findPropertyInclusion = annotationIntrospector.findPropertyInclusion(member);
        return findPropertyInclusion != null ? defaultPropertyInclusion.withOverrides(findPropertyInclusion) : defaultPropertyInclusion;
    }
}
