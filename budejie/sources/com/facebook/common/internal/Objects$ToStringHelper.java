package com.facebook.common.internal;

import javax.annotation.Nullable;

public final class Objects$ToStringHelper {
    private final String className;
    private ValueHolder holderHead;
    private ValueHolder holderTail;
    private boolean omitNullValues;

    private static final class ValueHolder {
        String name;
        ValueHolder next;
        Object value;

        private ValueHolder() {
        }
    }

    private Objects$ToStringHelper(String str) {
        this.holderHead = new ValueHolder();
        this.holderTail = this.holderHead;
        this.omitNullValues = false;
        this.className = (String) Preconditions.checkNotNull(str);
    }

    public Objects$ToStringHelper omitNullValues() {
        this.omitNullValues = true;
        return this;
    }

    public Objects$ToStringHelper add(String str, @Nullable Object obj) {
        return addHolder(str, obj);
    }

    public Objects$ToStringHelper add(String str, boolean z) {
        return addHolder(str, String.valueOf(z));
    }

    public Objects$ToStringHelper add(String str, char c) {
        return addHolder(str, String.valueOf(c));
    }

    public Objects$ToStringHelper add(String str, double d) {
        return addHolder(str, String.valueOf(d));
    }

    public Objects$ToStringHelper add(String str, float f) {
        return addHolder(str, String.valueOf(f));
    }

    public Objects$ToStringHelper add(String str, int i) {
        return addHolder(str, String.valueOf(i));
    }

    public Objects$ToStringHelper add(String str, long j) {
        return addHolder(str, String.valueOf(j));
    }

    public Objects$ToStringHelper addValue(@Nullable Object obj) {
        return addHolder(obj);
    }

    public Objects$ToStringHelper addValue(boolean z) {
        return addHolder(String.valueOf(z));
    }

    public Objects$ToStringHelper addValue(char c) {
        return addHolder(String.valueOf(c));
    }

    public Objects$ToStringHelper addValue(double d) {
        return addHolder(String.valueOf(d));
    }

    public Objects$ToStringHelper addValue(float f) {
        return addHolder(String.valueOf(f));
    }

    public Objects$ToStringHelper addValue(int i) {
        return addHolder(String.valueOf(i));
    }

    public Objects$ToStringHelper addValue(long j) {
        return addHolder(String.valueOf(j));
    }

    public String toString() {
        boolean z = this.omitNullValues;
        StringBuilder append = new StringBuilder(32).append(this.className).append('{');
        String str = "";
        ValueHolder valueHolder = this.holderHead.next;
        while (valueHolder != null) {
            if (!z || valueHolder.value != null) {
                append.append(str);
                str = ", ";
                if (valueHolder.name != null) {
                    append.append(valueHolder.name).append('=');
                }
                append.append(valueHolder.value);
            }
            valueHolder = valueHolder.next;
        }
        return append.append('}').toString();
    }

    private ValueHolder addHolder() {
        ValueHolder valueHolder = new ValueHolder();
        this.holderTail.next = valueHolder;
        this.holderTail = valueHolder;
        return valueHolder;
    }

    private Objects$ToStringHelper addHolder(@Nullable Object obj) {
        addHolder().value = obj;
        return this;
    }

    private Objects$ToStringHelper addHolder(String str, @Nullable Object obj) {
        ValueHolder addHolder = addHolder();
        addHolder.value = obj;
        addHolder.name = (String) Preconditions.checkNotNull(str);
        return this;
    }
}
