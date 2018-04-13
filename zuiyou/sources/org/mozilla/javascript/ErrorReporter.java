package org.mozilla.javascript;

public interface ErrorReporter {
    void error(String str, String str2, int i, String str3, int i2);

    EvaluatorException runtimeError(String str, String str2, int i, String str3, int i2);

    void warning(String str, String str2, int i, String str3, int i2);
}
