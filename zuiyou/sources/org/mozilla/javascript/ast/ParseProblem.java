package org.mozilla.javascript.ast;

public class ParseProblem {
    private int length;
    private String message;
    private int offset;
    private String sourceName;
    private Type type;

    public enum Type {
        Error,
        Warning
    }

    public ParseProblem(Type type, String str, String str2, int i, int i2) {
        setType(type);
        setMessage(str);
        setSourceName(str2);
        setFileOffset(i);
        setLength(i2);
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getSourceName() {
        return this.sourceName;
    }

    public void setSourceName(String str) {
        this.sourceName = str;
    }

    public int getFileOffset() {
        return this.offset;
    }

    public void setFileOffset(int i) {
        this.offset = i;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int i) {
        this.length = i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(200);
        stringBuilder.append(this.sourceName).append(":");
        stringBuilder.append("offset=").append(this.offset).append(",");
        stringBuilder.append("length=").append(this.length).append(",");
        stringBuilder.append(this.type == Type.Error ? "error: " : "warning: ");
        stringBuilder.append(this.message);
        return stringBuilder.toString();
    }
}
