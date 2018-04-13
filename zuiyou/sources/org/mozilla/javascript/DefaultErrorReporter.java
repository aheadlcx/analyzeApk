package org.mozilla.javascript;

class DefaultErrorReporter implements ErrorReporter {
    static final DefaultErrorReporter instance = new DefaultErrorReporter();
    private ErrorReporter chainedReporter;
    private boolean forEval;

    private DefaultErrorReporter() {
    }

    static ErrorReporter forEval(ErrorReporter errorReporter) {
        ErrorReporter defaultErrorReporter = new DefaultErrorReporter();
        defaultErrorReporter.forEval = true;
        defaultErrorReporter.chainedReporter = errorReporter;
        return defaultErrorReporter;
    }

    public void warning(String str, String str2, int i, String str3, int i2) {
        if (this.chainedReporter != null) {
            this.chainedReporter.warning(str, str2, i, str3, i2);
        }
    }

    public void error(String str, String str2, int i, String str3, int i2) {
        if (this.forEval) {
            String str4 = "SyntaxError";
            String str5 = "TypeError";
            str5 = ": ";
            str5 = "TypeError: ";
            if (str.startsWith("TypeError: ")) {
                str4 = "TypeError";
                str5 = str.substring("TypeError: ".length());
            } else {
                str5 = str;
            }
            throw ScriptRuntime.constructError(str4, str5, str2, i, str3, i2);
        } else if (this.chainedReporter != null) {
            this.chainedReporter.error(str, str2, i, str3, i2);
        } else {
            throw runtimeError(str, str2, i, str3, i2);
        }
    }

    public EvaluatorException runtimeError(String str, String str2, int i, String str3, int i2) {
        if (this.chainedReporter != null) {
            return this.chainedReporter.runtimeError(str, str2, i, str3, i2);
        }
        return new EvaluatorException(str, str2, i, str3, i2);
    }
}
