package com.tencent.bugly.proguard;

import java.io.Serializable;

public abstract class k implements Serializable {
    public abstract void a(i iVar);

    public abstract void a(j jVar);

    public abstract void a(StringBuilder stringBuilder, int i);

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        a(stringBuilder, 0);
        return stringBuilder.toString();
    }
}
