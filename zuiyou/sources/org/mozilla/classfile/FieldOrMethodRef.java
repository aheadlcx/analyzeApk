package org.mozilla.classfile;

final class FieldOrMethodRef {
    private String className;
    private int hashCode = -1;
    private String name;
    private String type;

    FieldOrMethodRef(String str, String str2, String str3) {
        this.className = str;
        this.name = str2;
        this.type = str3;
    }

    public String getClassName() {
        return this.className;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FieldOrMethodRef)) {
            return false;
        }
        FieldOrMethodRef fieldOrMethodRef = (FieldOrMethodRef) obj;
        if (this.className.equals(fieldOrMethodRef.className) && this.name.equals(fieldOrMethodRef.name) && this.type.equals(fieldOrMethodRef.type)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.hashCode == -1) {
            this.hashCode = (this.className.hashCode() ^ this.name.hashCode()) ^ this.type.hashCode();
        }
        return this.hashCode;
    }
}
