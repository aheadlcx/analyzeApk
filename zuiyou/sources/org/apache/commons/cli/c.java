package org.apache.commons.cli;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class c {
    public int a = 74;
    public int b = 1;
    public int c = 3;
    public String d = "usage: ";
    public String e = System.getProperty("line.separator");
    public String f = "-";
    public String g = "--";
    public String h = "arg";
    protected Comparator i = new a(null);

    /* renamed from: org.apache.commons.cli.c$1 */
    static class AnonymousClass1 {
    }

    private static class a implements Comparator {
        private a() {
        }

        a(AnonymousClass1 anonymousClass1) {
            this();
        }

        public int compare(Object obj, Object obj2) {
            return ((Option) obj).getKey().compareToIgnoreCase(((Option) obj2).getKey());
        }
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public Comparator d() {
        return this.i;
    }

    public void a(PrintWriter printWriter, int i, Options options, int i2, int i3) {
        StringBuffer stringBuffer = new StringBuffer();
        a(stringBuffer, i, options, i2, i3);
        printWriter.println(stringBuffer.toString());
    }

    protected StringBuffer a(StringBuffer stringBuffer, int i, Options options, int i2, int i3) {
        String a = a(i2);
        String a2 = a(i3);
        List arrayList = new ArrayList();
        List<Option> helpOptions = options.helpOptions();
        Collections.sort(helpOptions, d());
        int i4 = 0;
        for (Option option : helpOptions) {
            Option option2;
            int length;
            StringBuffer stringBuffer2 = new StringBuffer(8);
            if (option2.getOpt() == null) {
                stringBuffer2.append(a).append(new StringBuffer().append("   ").append(this.g).toString()).append(option2.getLongOpt());
            } else {
                stringBuffer2.append(a).append(this.f).append(option2.getOpt());
                if (option2.hasLongOpt()) {
                    stringBuffer2.append(',').append(this.g).append(option2.getLongOpt());
                }
            }
            if (option2.hasArg()) {
                if (option2.hasArgName()) {
                    stringBuffer2.append(" <").append(option2.getArgName()).append(">");
                } else {
                    stringBuffer2.append(' ');
                }
            }
            arrayList.add(stringBuffer2);
            if (stringBuffer2.length() > i4) {
                length = stringBuffer2.length();
            } else {
                length = i4;
            }
            i4 = length;
        }
        Iterator it = helpOptions.iterator();
        int i5 = 0;
        while (it.hasNext()) {
            option2 = (Option) it.next();
            int i6 = i5 + 1;
            stringBuffer2 = new StringBuffer(arrayList.get(i5).toString());
            if (stringBuffer2.length() < i4) {
                stringBuffer2.append(a(i4 - stringBuffer2.length()));
            }
            stringBuffer2.append(a2);
            i5 = i4 + i3;
            if (option2.getDescription() != null) {
                stringBuffer2.append(option2.getDescription());
            }
            a(stringBuffer, i, i5, stringBuffer2.toString());
            if (it.hasNext()) {
                stringBuffer.append(this.e);
            }
            i5 = i6;
        }
        return stringBuffer;
    }

    protected StringBuffer a(StringBuffer stringBuffer, int i, int i2, String str) {
        int a = a(str, i, 0);
        if (a == -1) {
            stringBuffer.append(a(str));
        } else {
            stringBuffer.append(a(str.substring(0, a))).append(this.e);
            if (i2 >= i) {
                i2 = 1;
            }
            String a2 = a(i2);
            while (true) {
                str = new StringBuffer().append(a2).append(str.substring(a).trim()).toString();
                a = a(str, i, 0);
                if (a == -1) {
                    break;
                }
                if (str.length() > i && a == i2 - 1) {
                    a = i;
                }
                stringBuffer.append(a(str.substring(0, a))).append(this.e);
            }
            stringBuffer.append(str);
        }
        return stringBuffer;
    }

    protected int a(String str, int i, int i2) {
        int indexOf = str.indexOf(10, i2);
        if (indexOf == -1 || indexOf > i) {
            indexOf = str.indexOf(9, i2);
            if (indexOf == -1 || indexOf > i) {
                if (i2 + i >= str.length()) {
                    return -1;
                }
                char charAt;
                indexOf = i2 + i;
                while (indexOf >= i2) {
                    charAt = str.charAt(indexOf);
                    if (charAt == ' ' || charAt == '\n' || charAt == '\r') {
                        break;
                    }
                    indexOf--;
                }
                if (indexOf > i2) {
                    return indexOf;
                }
                indexOf = i2 + i;
                while (indexOf <= str.length()) {
                    charAt = str.charAt(indexOf);
                    if (charAt == ' ' || charAt == '\n' || charAt == '\r') {
                        break;
                    }
                    indexOf++;
                }
                if (indexOf == str.length()) {
                    indexOf = -1;
                }
                return indexOf;
            }
        }
        return indexOf + 1;
    }

    protected String a(int i) {
        StringBuffer stringBuffer = new StringBuffer(i);
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(' ');
        }
        return stringBuffer.toString();
    }

    protected String a(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        int length = str.length();
        while (length > 0 && Character.isWhitespace(str.charAt(length - 1))) {
            length--;
        }
        return str.substring(0, length);
    }
}
