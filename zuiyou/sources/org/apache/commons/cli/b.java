package org.apache.commons.cli;

import java.util.ArrayList;
import java.util.List;

public class b extends e {
    protected String[] b(Options options, String[] strArr, boolean z) {
        List arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        while (i < strArr.length) {
            String str = strArr[i];
            if ("--".equals(str)) {
                i2 = 1;
                arrayList.add("--");
            } else if ("-".equals(str)) {
                arrayList.add("-");
            } else if (str.startsWith("-")) {
                String a = h.a(str);
                if (options.hasOption(a)) {
                    arrayList.add(str);
                } else if (a.indexOf(61) != -1 && options.hasOption(a.substring(0, a.indexOf(61)))) {
                    arrayList.add(str.substring(0, str.indexOf(61)));
                    arrayList.add(str.substring(str.indexOf(61) + 1));
                } else if (options.hasOption(str.substring(0, 2))) {
                    arrayList.add(str.substring(0, 2));
                    arrayList.add(str.substring(2));
                } else {
                    arrayList.add(str);
                    boolean z2 = z;
                }
            } else {
                arrayList.add(str);
            }
            if (i2 != 0) {
                i++;
                while (i < strArr.length) {
                    arrayList.add(strArr[i]);
                    i++;
                }
            }
            i++;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
