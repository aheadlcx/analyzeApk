package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.io.Serializable;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class OptionalHandlerFactory implements Serializable {
    private static final Class<?> CLASS_DOM_DOCUMENT;
    private static final Class<?> CLASS_DOM_NODE;
    private static final Class<?> CLASS_JAVA7_PATH;
    private static final String DESERIALIZERS_FOR_JAVAX_XML = "com.fasterxml.jackson.databind.ext.CoreXMLDeserializers";
    private static final String DESERIALIZER_FOR_DOM_DOCUMENT = "com.fasterxml.jackson.databind.ext.DOMDeserializer$DocumentDeserializer";
    private static final String DESERIALIZER_FOR_DOM_NODE = "com.fasterxml.jackson.databind.ext.DOMDeserializer$NodeDeserializer";
    private static final String DESERIALIZER_FOR_PATH = "com.fasterxml.jackson.databind.ext.PathDeserializer";
    private static final String PACKAGE_PREFIX_JAVAX_XML = "javax.xml.";
    private static final String SERIALIZERS_FOR_JAVAX_XML = "com.fasterxml.jackson.databind.ext.CoreXMLSerializers";
    private static final String SERIALIZER_FOR_DOM_NODE = "com.fasterxml.jackson.databind.ext.DOMSerializer";
    public static final OptionalHandlerFactory instance = new OptionalHandlerFactory();
    private static final long serialVersionUID = 1;

    static {
        Class cls;
        Class cls2;
        Class cls3 = null;
        try {
            cls = Node.class;
            try {
                cls2 = Document.class;
            } catch (Exception e) {
                System.err.println("WARNING: could not load DOM Node and/or Document classes");
                cls2 = cls3;
                CLASS_DOM_NODE = cls;
                CLASS_DOM_DOCUMENT = cls2;
                cls3 = Class.forName("java.nio.file.Path");
                CLASS_JAVA7_PATH = cls3;
            }
        } catch (Exception e2) {
            cls = cls3;
            System.err.println("WARNING: could not load DOM Node and/or Document classes");
            cls2 = cls3;
            CLASS_DOM_NODE = cls;
            CLASS_DOM_DOCUMENT = cls2;
            cls3 = Class.forName("java.nio.file.Path");
            CLASS_JAVA7_PATH = cls3;
        }
        CLASS_DOM_NODE = cls;
        CLASS_DOM_DOCUMENT = cls2;
        try {
            cls3 = Class.forName("java.nio.file.Path");
        } catch (Exception e3) {
            System.err.println("WARNING: could not load Java7 Path class");
        }
        CLASS_JAVA7_PATH = cls3;
    }

    protected OptionalHandlerFactory() {
    }

    public JsonSerializer<?> findSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription) {
        Class rawClass = javaType.getRawClass();
        if (CLASS_JAVA7_PATH != null && CLASS_JAVA7_PATH.isAssignableFrom(rawClass)) {
            return ToStringSerializer.instance;
        }
        if (CLASS_DOM_NODE != null && CLASS_DOM_NODE.isAssignableFrom(rawClass)) {
            return (JsonSerializer) instantiate(SERIALIZER_FOR_DOM_NODE);
        }
        if (!rawClass.getName().startsWith(PACKAGE_PREFIX_JAVAX_XML) && !hasSuperClassStartingWith(rawClass, PACKAGE_PREFIX_JAVAX_XML)) {
            return null;
        }
        Object instantiate = instantiate(SERIALIZERS_FOR_JAVAX_XML);
        if (instantiate == null) {
            return null;
        }
        return ((Serializers) instantiate).findSerializer(serializationConfig, javaType, beanDescription);
    }

    public JsonDeserializer<?> findDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        Class rawClass = javaType.getRawClass();
        if (CLASS_JAVA7_PATH != null && CLASS_JAVA7_PATH.isAssignableFrom(rawClass)) {
            return (JsonDeserializer) instantiate(DESERIALIZER_FOR_PATH);
        }
        if (CLASS_DOM_NODE != null && CLASS_DOM_NODE.isAssignableFrom(rawClass)) {
            return (JsonDeserializer) instantiate(DESERIALIZER_FOR_DOM_NODE);
        }
        if (CLASS_DOM_DOCUMENT != null && CLASS_DOM_DOCUMENT.isAssignableFrom(rawClass)) {
            return (JsonDeserializer) instantiate(DESERIALIZER_FOR_DOM_DOCUMENT);
        }
        if (!rawClass.getName().startsWith(PACKAGE_PREFIX_JAVAX_XML) && !hasSuperClassStartingWith(rawClass, PACKAGE_PREFIX_JAVAX_XML)) {
            return null;
        }
        Object instantiate = instantiate(DESERIALIZERS_FOR_JAVAX_XML);
        if (instantiate == null) {
            return null;
        }
        return ((Deserializers) instantiate).findBeanDeserializer(javaType, deserializationConfig, beanDescription);
    }

    private Object instantiate(String str) {
        try {
            return Class.forName(str).newInstance();
        } catch (LinkageError e) {
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    private boolean hasSuperClassStartingWith(Class<?> cls, String str) {
        Class superclass = cls.getSuperclass();
        while (superclass != null && superclass != Object.class) {
            if (superclass.getName().startsWith(str)) {
                return true;
            }
            superclass = superclass.getSuperclass();
        }
        return false;
    }
}
