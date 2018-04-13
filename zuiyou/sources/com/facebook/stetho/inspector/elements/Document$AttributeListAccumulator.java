package com.facebook.stetho.inspector.elements;

import java.util.ArrayList;

public final class Document$AttributeListAccumulator extends ArrayList<String> implements AttributeAccumulator {
    public void store(String str, String str2) {
        add(str);
        add(str2);
    }
}
