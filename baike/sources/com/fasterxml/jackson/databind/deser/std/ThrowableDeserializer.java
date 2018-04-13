package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;

public class ThrowableDeserializer extends BeanDeserializer {
    protected static final String PROP_NAME_MESSAGE = "message";
    private static final long serialVersionUID = 1;

    public ThrowableDeserializer(BeanDeserializer beanDeserializer) {
        super(beanDeserializer);
        this._vanillaProcessing = false;
    }

    protected ThrowableDeserializer(BeanDeserializer beanDeserializer, NameTransformer nameTransformer) {
        super((BeanDeserializerBase) beanDeserializer, nameTransformer);
    }

    public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer nameTransformer) {
        return getClass() != ThrowableDeserializer.class ? this : new ThrowableDeserializer(this, nameTransformer);
    }

    public Object deserializeFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        int i = 0;
        if (this._propertyBasedCreator != null) {
            return _deserializeUsingPropertyBased(jsonParser, deserializationContext);
        }
        if (this._delegateDeserializer != null) {
            return this._valueInstantiator.createUsingDelegate(deserializationContext, this._delegateDeserializer.deserialize(jsonParser, deserializationContext));
        }
        if (this._beanType.isAbstract()) {
            throw JsonMappingException.from(jsonParser, "Can not instantiate abstract type " + this._beanType + " (need to add/enable type information?)");
        }
        boolean canCreateFromString = this._valueInstantiator.canCreateFromString();
        boolean canCreateUsingDefault = this._valueInstantiator.canCreateUsingDefault();
        if (canCreateFromString || canCreateUsingDefault) {
            int i2 = 0;
            Object[] objArr = null;
            Object obj = null;
            while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
                int i3;
                Object obj2;
                Object[] objArr2;
                String currentName = jsonParser.getCurrentName();
                SettableBeanProperty find = this._beanProperties.find(currentName);
                jsonParser.nextToken();
                if (find == null) {
                    if ("message".equals(currentName) && canCreateFromString) {
                        obj = this._valueInstantiator.createFromString(deserializationContext, jsonParser.getText());
                        if (objArr != null) {
                            for (int i4 = 0; i4 < i2; i4 += 2) {
                                ((SettableBeanProperty) objArr[i4]).set(obj, objArr[i4 + 1]);
                            }
                            i3 = i2;
                            obj2 = obj;
                            objArr2 = null;
                        }
                    } else if (this._ignorableProps != null && this._ignorableProps.contains(currentName)) {
                        jsonParser.skipChildren();
                        i3 = i2;
                        objArr2 = objArr;
                        obj2 = obj;
                    } else if (this._anySetter != null) {
                        this._anySetter.deserializeAndSet(jsonParser, deserializationContext, obj, currentName);
                        i3 = i2;
                        objArr2 = objArr;
                        obj2 = obj;
                    } else {
                        handleUnknownProperty(jsonParser, deserializationContext, obj, currentName);
                    }
                    i3 = i2;
                    objArr2 = objArr;
                    obj2 = obj;
                } else if (obj != null) {
                    find.deserializeAndSet(jsonParser, deserializationContext, obj);
                    i3 = i2;
                    objArr2 = objArr;
                    obj2 = obj;
                } else {
                    if (objArr == null) {
                        i3 = this._beanProperties.size();
                        objArr = new Object[(i3 + i3)];
                    }
                    int i5 = i2 + 1;
                    objArr[i2] = find;
                    i3 = i5 + 1;
                    objArr[i5] = find.deserialize(jsonParser, deserializationContext);
                    objArr2 = objArr;
                    obj2 = obj;
                }
                jsonParser.nextToken();
                obj = obj2;
                objArr = objArr2;
                i2 = i3;
            }
            if (obj != null) {
                return obj;
            }
            if (canCreateFromString) {
                obj = this._valueInstantiator.createFromString(deserializationContext, null);
            } else {
                obj = this._valueInstantiator.createUsingDefault(deserializationContext);
            }
            if (objArr == null) {
                return obj;
            }
            while (i < i2) {
                ((SettableBeanProperty) objArr[i]).set(obj, objArr[i + 1]);
                i += 2;
            }
            return obj;
        }
        throw JsonMappingException.from(jsonParser, "Can not deserialize Throwable of type " + this._beanType + " without having a default contructor, a single-String-arg constructor; or explicit @JsonCreator");
    }
}
