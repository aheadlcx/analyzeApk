package com.umeng.commonsdk.proguard;

import java.io.Serializable;

public interface l<T extends l<?, ?>, F extends s> extends Serializable {
    void clear();

    l<T, F> deepCopy();

    F fieldForId(int i);

    void read(ak akVar) throws r;

    void write(ak akVar) throws r;
}
