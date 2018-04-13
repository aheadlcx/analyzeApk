package org.mozilla.javascript;

public interface ExternalArrayData {
    Object getArrayElement(int i);

    int getArrayLength();

    void setArrayElement(int i, Object obj);
}
