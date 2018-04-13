package org.mozilla.javascript;

import java.io.CharArrayWriter;
import java.io.FilenameFilter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RhinoException extends RuntimeException {
    private static final Pattern JAVA_STACK_PATTERN = Pattern.compile("_c_(.*)_\\d+");
    static final long serialVersionUID = 1883500631321581169L;
    private static StackStyle stackStyle;
    private int columnNumber;
    int[] interpreterLineData;
    Object interpreterStackInfo;
    private int lineNumber;
    private String lineSource;
    private String sourceName;

    static {
        stackStyle = StackStyle.RHINO;
        String property = System.getProperty("rhino.stack.style");
        if (property == null) {
            return;
        }
        if ("Rhino".equalsIgnoreCase(property)) {
            stackStyle = StackStyle.RHINO;
        } else if ("Mozilla".equalsIgnoreCase(property)) {
            stackStyle = StackStyle.MOZILLA;
        } else if ("V8".equalsIgnoreCase(property)) {
            stackStyle = StackStyle.V8;
        }
    }

    RhinoException() {
        Evaluator createInterpreter = Context.createInterpreter();
        if (createInterpreter != null) {
            createInterpreter.captureStackInfo(this);
        }
    }

    RhinoException(String str) {
        super(str);
        Evaluator createInterpreter = Context.createInterpreter();
        if (createInterpreter != null) {
            createInterpreter.captureStackInfo(this);
        }
    }

    public final String getMessage() {
        String details = details();
        if (this.sourceName == null || this.lineNumber <= 0) {
            return details;
        }
        StringBuilder stringBuilder = new StringBuilder(details);
        stringBuilder.append(" (");
        if (this.sourceName != null) {
            stringBuilder.append(this.sourceName);
        }
        if (this.lineNumber > 0) {
            stringBuilder.append('#');
            stringBuilder.append(this.lineNumber);
        }
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    public String details() {
        return super.getMessage();
    }

    public final String sourceName() {
        return this.sourceName;
    }

    public final void initSourceName(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        } else if (this.sourceName != null) {
            throw new IllegalStateException();
        } else {
            this.sourceName = str;
        }
    }

    public final int lineNumber() {
        return this.lineNumber;
    }

    public final void initLineNumber(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException(String.valueOf(i));
        } else if (this.lineNumber > 0) {
            throw new IllegalStateException();
        } else {
            this.lineNumber = i;
        }
    }

    public final int columnNumber() {
        return this.columnNumber;
    }

    public final void initColumnNumber(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException(String.valueOf(i));
        } else if (this.columnNumber > 0) {
            throw new IllegalStateException();
        } else {
            this.columnNumber = i;
        }
    }

    public final String lineSource() {
        return this.lineSource;
    }

    public final void initLineSource(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        } else if (this.lineSource != null) {
            throw new IllegalStateException();
        } else {
            this.lineSource = str;
        }
    }

    final void recordErrorOrigin(String str, int i, String str2, int i2) {
        if (i == -1) {
            i = 0;
        }
        if (str != null) {
            initSourceName(str);
        }
        if (i != 0) {
            initLineNumber(i);
        }
        if (str2 != null) {
            initLineSource(str2);
        }
        if (i2 != 0) {
            initColumnNumber(i2);
        }
    }

    private String generateStackTrace() {
        Writer charArrayWriter = new CharArrayWriter();
        super.printStackTrace(new PrintWriter(charArrayWriter));
        String charArrayWriter2 = charArrayWriter.toString();
        Evaluator createInterpreter = Context.createInterpreter();
        if (createInterpreter != null) {
            return createInterpreter.getPatchedStack(this, charArrayWriter2);
        }
        return null;
    }

    public String getScriptStackTrace() {
        return getScriptStackTrace(-1, null);
    }

    public String getScriptStackTrace(int i, String str) {
        return formatStackTrace(getScriptStack(i, str), details());
    }

    static String formatStackTrace(ScriptStackElement[] scriptStackElementArr, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        String systemProperty = SecurityUtilities.getSystemProperty("line.separator");
        if (stackStyle == StackStyle.V8 && !"null".equals(str)) {
            stringBuilder.append(str);
            stringBuilder.append(systemProperty);
        }
        for (ScriptStackElement scriptStackElement : scriptStackElementArr) {
            switch (RhinoException$1.$SwitchMap$org$mozilla$javascript$StackStyle[stackStyle.ordinal()]) {
                case 1:
                    scriptStackElement.renderMozillaStyle(stringBuilder);
                    break;
                case 2:
                    scriptStackElement.renderV8Style(stringBuilder);
                    break;
                case 3:
                    scriptStackElement.renderJavaStyle(stringBuilder);
                    break;
                default:
                    break;
            }
            stringBuilder.append(systemProperty);
        }
        return stringBuilder.toString();
    }

    @Deprecated
    public String getScriptStackTrace(FilenameFilter filenameFilter) {
        return getScriptStackTrace();
    }

    public ScriptStackElement[] getScriptStack() {
        return getScriptStack(-1, null);
    }

    public ScriptStackElement[] getScriptStack(int i, String str) {
        List arrayList = new ArrayList();
        ScriptStackElement[][] scriptStackElementArr = (ScriptStackElement[][]) null;
        if (this.interpreterStackInfo != null) {
            Evaluator createInterpreter = Context.createInterpreter();
            if (createInterpreter instanceof Interpreter) {
                scriptStackElementArr = ((Interpreter) createInterpreter).getScriptStackElements(this);
            }
        }
        StackTraceElement[] stackTrace = getStackTrace();
        Object obj = str == null ? 1 : null;
        int i2 = 0;
        int i3 = 0;
        for (StackTraceElement stackTraceElement : stackTrace) {
            String fileName = stackTraceElement.getFileName();
            if (stackTraceElement.getMethodName().startsWith("_c_") && stackTraceElement.getLineNumber() > -1 && fileName != null && !fileName.endsWith(".java")) {
                CharSequence methodName = stackTraceElement.getMethodName();
                Matcher matcher = JAVA_STACK_PATTERN.matcher(methodName);
                String group = ("_c_script_0".equals(methodName) || !matcher.find()) ? null : matcher.group(1);
                if (obj == null && str.equals(group)) {
                    obj = 1;
                } else if (obj != null && (i < 0 || i2 < i)) {
                    arrayList.add(new ScriptStackElement(fileName, group, stackTraceElement.getLineNumber()));
                    i2++;
                }
            } else if ("org.mozilla.javascript.Interpreter".equals(stackTraceElement.getClassName()) && "interpretLoop".equals(stackTraceElement.getMethodName()) && r0 != null && r0.length > i3) {
                int i4 = i3 + 1;
                for (ScriptStackElement scriptStackElement : r0[i3]) {
                    if (obj == null && str.equals(scriptStackElement.functionName)) {
                        obj = 1;
                    } else if (obj != null && (i < 0 || i2 < i)) {
                        arrayList.add(scriptStackElement);
                        i2++;
                    }
                }
                i3 = i4;
            }
        }
        return (ScriptStackElement[]) arrayList.toArray(new ScriptStackElement[arrayList.size()]);
    }

    public void printStackTrace(PrintWriter printWriter) {
        if (this.interpreterStackInfo == null) {
            super.printStackTrace(printWriter);
        } else {
            printWriter.print(generateStackTrace());
        }
    }

    public void printStackTrace(PrintStream printStream) {
        if (this.interpreterStackInfo == null) {
            super.printStackTrace(printStream);
        } else {
            printStream.print(generateStackTrace());
        }
    }

    public static boolean usesMozillaStackStyle() {
        return stackStyle == StackStyle.MOZILLA;
    }

    public static void useMozillaStackStyle(boolean z) {
        stackStyle = z ? StackStyle.MOZILLA : StackStyle.RHINO;
    }

    public static void setStackStyle(StackStyle stackStyle) {
        stackStyle = stackStyle;
    }

    public static StackStyle getStackStyle() {
        return stackStyle;
    }
}
