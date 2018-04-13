package com.facebook.common.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableList<E> extends ArrayList<E> {
    private ImmutableList(int i) {
        super(i);
    }

    private ImmutableList(List<E> list) {
        super(list);
    }

    public static <E> ImmutableList<E> copyOf(List<E> list) {
        return new ImmutableList((List) list);
    }

    public static <E> ImmutableList<E> of(E... eArr) {
        Object immutableList = new ImmutableList(eArr.length);
        Collections.addAll(immutableList, eArr);
        return immutableList;
    }
}
