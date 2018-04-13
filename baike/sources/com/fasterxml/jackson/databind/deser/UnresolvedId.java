package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonLocation;

public class UnresolvedId {
    private final Object _id;
    private final JsonLocation _location;
    private final Class<?> _type;

    public UnresolvedId(Object obj, Class<?> cls, JsonLocation jsonLocation) {
        this._id = obj;
        this._type = cls;
        this._location = jsonLocation;
    }

    public Object getId() {
        return this._id;
    }

    public Class<?> getType() {
        return this._type;
    }

    public JsonLocation getLocation() {
        return this._location;
    }

    public String toString() {
        String str = "Object id [%s] (for %s) at %s";
        Object[] objArr = new Object[3];
        objArr[0] = this._id;
        objArr[1] = this._type == null ? "NULL" : this._type.getName();
        objArr[2] = this._location;
        return String.format(str, objArr);
    }
}
