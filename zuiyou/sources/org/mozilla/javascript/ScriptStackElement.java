package org.mozilla.javascript;

import java.io.Serializable;

public final class ScriptStackElement implements Serializable {
    static final long serialVersionUID = -6416688260860477449L;
    public final String fileName;
    public final String functionName;
    public final int lineNumber;

    public ScriptStackElement(String str, String str2, int i) {
        this.fileName = str;
        this.functionName = str2;
        this.lineNumber = i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        renderMozillaStyle(stringBuilder);
        return stringBuilder.toString();
    }

    public void renderJavaStyle(StringBuilder stringBuilder) {
        stringBuilder.append("\tat ").append(this.fileName);
        if (this.lineNumber > -1) {
            stringBuilder.append(':').append(this.lineNumber);
        }
        if (this.functionName != null) {
            stringBuilder.append(" (").append(this.functionName).append(')');
        }
    }

    public void renderMozillaStyle(StringBuilder stringBuilder) {
        if (this.functionName != null) {
            stringBuilder.append(this.functionName).append("()");
        }
        stringBuilder.append('@').append(this.fileName);
        if (this.lineNumber > -1) {
            stringBuilder.append(':').append(this.lineNumber);
        }
    }

    public void renderV8Style(StringBuilder stringBuilder) {
        stringBuilder.append("    at ");
        if (this.functionName == null || "anonymous".equals(this.functionName) || "undefined".equals(this.functionName)) {
            appendV8Location(stringBuilder);
            return;
        }
        stringBuilder.append(this.functionName).append(" (");
        appendV8Location(stringBuilder);
        stringBuilder.append(')');
    }

    private void appendV8Location(StringBuilder stringBuilder) {
        stringBuilder.append(this.fileName);
        if (this.lineNumber > -1) {
            stringBuilder.append(':').append(this.lineNumber);
        }
    }
}
