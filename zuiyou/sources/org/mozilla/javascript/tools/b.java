package org.mozilla.javascript.tools;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.EcmaError;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.SecurityUtilities;
import org.mozilla.javascript.WrappedException;

public class b implements ErrorReporter {
    private boolean a;
    private boolean b;
    private PrintStream c;

    public b(boolean z, PrintStream printStream) {
        this.b = z;
        this.c = printStream;
    }

    public static String a(String str) {
        return a(str, (Object[]) null);
    }

    public static String a(String str, String str2) {
        return a(str, new Object[]{str2});
    }

    public static String a(String str, Object obj, Object obj2) {
        return a(str, new Object[]{obj, obj2});
    }

    public static String a(String str, Object[] objArr) {
        Context currentContext = Context.getCurrentContext();
        try {
            String string = ResourceBundle.getBundle("org.mozilla.javascript.tools.resources.Messages", currentContext == null ? Locale.getDefault() : currentContext.getLocale()).getString(str);
            return objArr == null ? string : new MessageFormat(string).format(objArr);
        } catch (MissingResourceException e) {
            throw new RuntimeException("no message resource found for message property " + str);
        }
    }

    private static String b(RhinoException rhinoException) {
        if (rhinoException instanceof JavaScriptException) {
            return a("msg.uncaughtJSException", rhinoException.details());
        }
        if (rhinoException instanceof EcmaError) {
            return a("msg.uncaughtEcmaError", rhinoException.details());
        }
        if (rhinoException instanceof EvaluatorException) {
            return rhinoException.details();
        }
        return rhinoException.toString();
    }

    public void warning(String str, String str2, int i, String str3, int i2) {
        if (this.b) {
            a(str, str2, i, str3, i2, true);
        }
    }

    public void error(String str, String str2, int i, String str3, int i2) {
        this.a = true;
        a(str, str2, i, str3, i2, false);
    }

    public EvaluatorException runtimeError(String str, String str2, int i, String str3, int i2) {
        return new EvaluatorException(str, str2, i, str3, i2);
    }

    public static void a(ErrorReporter errorReporter, RhinoException rhinoException) {
        if (errorReporter instanceof b) {
            ((b) errorReporter).a(rhinoException);
            return;
        }
        errorReporter.error(b(rhinoException), rhinoException.sourceName(), rhinoException.lineNumber(), rhinoException.lineSource(), rhinoException.columnNumber());
    }

    public void a(RhinoException rhinoException) {
        if (rhinoException instanceof WrappedException) {
            ((WrappedException) rhinoException).printStackTrace(this.c);
            return;
        }
        a(b(rhinoException) + SecurityUtilities.getSystemProperty("line.separator") + rhinoException.getScriptStackTrace(), rhinoException.sourceName(), rhinoException.lineNumber(), rhinoException.lineSource(), rhinoException.columnNumber(), false);
    }

    private void a(String str, String str2, int i, String str3, int i2, boolean z) {
        String valueOf;
        if (i > 0) {
            valueOf = String.valueOf(i);
            if (str2 != null) {
                valueOf = a("msg.format3", new Object[]{str2, valueOf, str});
            } else {
                valueOf = a("msg.format2", new Object[]{valueOf, str});
            }
        } else {
            valueOf = a("msg.format1", new Object[]{str});
        }
        if (z) {
            valueOf = a("msg.warning", valueOf);
        }
        this.c.println("js: " + valueOf);
        if (str3 != null) {
            this.c.println("js: " + str3);
            this.c.println("js: " + a(i2));
        }
    }

    private String a(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i2 = 0; i2 < i - 1; i2++) {
            stringBuilder.append(".");
        }
        stringBuilder.append("^");
        return stringBuilder.toString();
    }
}
