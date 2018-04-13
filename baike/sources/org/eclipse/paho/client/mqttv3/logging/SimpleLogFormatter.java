package org.eclipse.paho.client.mqttv3.logging;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import cz.msebera.android.httpclient.message.TokenParser;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class SimpleLogFormatter extends Formatter {
    private static final String a = System.getProperty("line.separator");

    public String format(LogRecord logRecord) {
        PrintWriter printWriter;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(logRecord.getLevel().getName()).append("\t");
        stringBuffer.append(new StringBuffer(String.valueOf(MessageFormat.format("{0, date, yy-MM-dd} {0, time, kk:mm:ss.SSSS} ", new Object[]{new Date(logRecord.getMillis())}))).append("\t").toString());
        String sourceClassName = logRecord.getSourceClassName();
        String str = "";
        if (sourceClassName != null) {
            int length = sourceClassName.length();
            if (length > 20) {
                str = logRecord.getSourceClassName().substring(length - 19);
            } else {
                str = new StringBuffer().append(sourceClassName).append(new char[]{TokenParser.SP}, 0, 1).toString();
            }
        }
        stringBuffer.append(str).append("\t").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        stringBuffer.append(left(logRecord.getSourceMethodName(), 23, TokenParser.SP)).append("\t");
        stringBuffer.append(logRecord.getThreadID()).append("\t");
        stringBuffer.append(formatMessage(logRecord)).append(a);
        if (logRecord.getThrown() != null) {
            stringBuffer.append("Throwable occurred: ");
            Throwable thrown = logRecord.getThrown();
            try {
                Writer stringWriter = new StringWriter();
                printWriter = new PrintWriter(stringWriter);
                try {
                    thrown.printStackTrace(printWriter);
                    stringBuffer.append(stringWriter.toString());
                    if (printWriter != null) {
                        try {
                            printWriter.close();
                        } catch (Exception e) {
                        }
                    }
                } catch (Throwable th) {
                    thrown = th;
                    if (printWriter != null) {
                        try {
                            printWriter.close();
                        } catch (Exception e2) {
                        }
                    }
                    throw thrown;
                }
            } catch (Throwable th2) {
                thrown = th2;
                printWriter = null;
                if (printWriter != null) {
                    printWriter.close();
                }
                throw thrown;
            }
        }
        return stringBuffer.toString();
    }

    public static String left(String str, int i, char c) {
        if (str.length() >= i) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        stringBuffer.append(str);
        int length = i - str.length();
        while (true) {
            length--;
            if (length < 0) {
                return stringBuffer.toString();
            }
            stringBuffer.append(c);
        }
    }
}
