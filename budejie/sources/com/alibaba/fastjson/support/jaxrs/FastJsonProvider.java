package com.alibaba.fastjson.support.jaxrs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
public class FastJsonProvider implements MessageBodyReader<Object>, MessageBodyWriter<Object> {
    private Class<?>[] clazzes;
    public Feature[] features;
    public ParserConfig parserConfig;
    public SerializeConfig serializeConfig;
    public Map<Class<?>, SerializeFilter> serializeFilters;
    public SerializerFeature[] serializerFeatures;
    @Context
    UriInfo uriInfo;

    public FastJsonProvider() {
        this.clazzes = null;
        this.serializeConfig = SerializeConfig.getGlobalInstance();
        this.parserConfig = ParserConfig.getGlobalInstance();
        this.serializerFeatures = new SerializerFeature[0];
        this.features = new Feature[0];
    }

    public FastJsonProvider(Class<?>[] clsArr) {
        this.clazzes = null;
        this.serializeConfig = SerializeConfig.getGlobalInstance();
        this.parserConfig = ParserConfig.getGlobalInstance();
        this.serializerFeatures = new SerializerFeature[0];
        this.features = new Feature[0];
        this.clazzes = clsArr;
    }

    protected boolean isValidType(Class<?> cls, Annotation[] annotationArr) {
        if (cls == null) {
            return false;
        }
        if (this.clazzes == null) {
            return true;
        }
        for (Class<?> cls2 : this.clazzes) {
            if (cls2 == cls) {
                return true;
            }
        }
        return false;
    }

    protected boolean hasMatchingMediaType(MediaType mediaType) {
        if (mediaType == null) {
            return true;
        }
        String subtype = mediaType.getSubtype();
        if ("json".equalsIgnoreCase(subtype) || subtype.endsWith("+json") || "javascript".equals(subtype) || "x-javascript".equals(subtype)) {
            return true;
        }
        return false;
    }

    public boolean isWriteable(Class<?> cls, Type type, Annotation[] annotationArr, MediaType mediaType) {
        if (hasMatchingMediaType(mediaType)) {
            return isValidType(cls, annotationArr);
        }
        return false;
    }

    public long getSize(Object obj, Class<?> cls, Type type, Annotation[] annotationArr, MediaType mediaType) {
        return -1;
    }

    public void writeTo(Object obj, Class<?> cls, Type type, Annotation[] annotationArr, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        SerializerFeature[] serializerFeatureArr;
        SerializeFilter serializeFilter;
        SerializerFeature[] serializerFeatureArr2 = this.serializerFeatures;
        if (this.uriInfo == null || !this.uriInfo.getQueryParameters().containsKey("pretty")) {
            serializerFeatureArr = serializerFeatureArr2;
        } else if (serializerFeatureArr2 == null) {
            serializerFeatureArr = new SerializerFeature[]{SerializerFeature.PrettyFormat};
        } else {
            List asList = Arrays.asList(serializerFeatureArr2);
            asList.add(SerializerFeature.PrettyFormat);
            serializerFeatureArr = (SerializerFeature[]) asList.toArray(serializerFeatureArr2);
        }
        if (this.serializeFilters != null) {
            serializeFilter = (SerializeFilter) this.serializeFilters.get(cls);
        } else {
            serializeFilter = null;
        }
        String toJSONString = JSON.toJSONString(obj, serializeFilter, serializerFeatureArr);
        if (toJSONString != null) {
            outputStream.write(toJSONString.getBytes());
        }
    }

    public boolean isReadable(Class<?> cls, Type type, Annotation[] annotationArr, MediaType mediaType) {
        if (hasMatchingMediaType(mediaType)) {
            return isValidType(cls, annotationArr);
        }
        return false;
    }

    public Object readFrom(Class<Object> cls, Type type, Annotation[] annotationArr, MediaType mediaType, MultivaluedMap<String, String> multivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {
        String iOUtils;
        try {
            iOUtils = IOUtils.toString(inputStream);
        } catch (Exception e) {
            iOUtils = null;
        }
        if (iOUtils == null) {
            return null;
        }
        return JSON.parseObject(iOUtils, cls, this.parserConfig, JSON.DEFAULT_PARSER_FEATURE, this.features);
    }
}
