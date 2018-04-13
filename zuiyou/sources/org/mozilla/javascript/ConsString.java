package org.mozilla.javascript;

import java.io.Serializable;

public class ConsString implements Serializable, CharSequence {
    private static final long serialVersionUID = -8432806714471372570L;
    private int depth = 1;
    private final int length;
    private CharSequence s1;
    private CharSequence s2;

    public ConsString(CharSequence charSequence, CharSequence charSequence2) {
        this.s1 = charSequence;
        this.s2 = charSequence2;
        this.length = charSequence.length() + charSequence2.length();
        if (charSequence instanceof ConsString) {
            this.depth += ((ConsString) charSequence).depth;
        }
        if (charSequence2 instanceof ConsString) {
            this.depth += ((ConsString) charSequence2).depth;
        }
        if (this.depth > 2000) {
            flatten();
        }
    }

    private Object writeReplace() {
        return toString();
    }

    public String toString() {
        return this.depth == 0 ? (String) this.s1 : flatten();
    }

    private synchronized String flatten() {
        if (this.depth > 0) {
            StringBuilder stringBuilder = new StringBuilder(this.length);
            appendTo(stringBuilder);
            this.s1 = stringBuilder.toString();
            this.s2 = "";
            this.depth = 0;
        }
        return (String) this.s1;
    }

    private synchronized void appendTo(StringBuilder stringBuilder) {
        appendFragment(this.s1, stringBuilder);
        appendFragment(this.s2, stringBuilder);
    }

    private static void appendFragment(CharSequence charSequence, StringBuilder stringBuilder) {
        if (charSequence instanceof ConsString) {
            ((ConsString) charSequence).appendTo(stringBuilder);
        } else {
            stringBuilder.append(charSequence);
        }
    }

    public int length() {
        return this.length;
    }

    public char charAt(int i) {
        return (this.depth == 0 ? (String) this.s1 : flatten()).charAt(i);
    }

    public CharSequence subSequence(int i, int i2) {
        return (this.depth == 0 ? (String) this.s1 : flatten()).substring(i, i2);
    }
}
