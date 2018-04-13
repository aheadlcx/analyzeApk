package cz.msebera.android.httpclient.conn.util;

import cz.msebera.android.httpclient.annotation.Immutable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Immutable
public final class PublicSuffixListParser {
    public PublicSuffixList parse(Reader reader) throws IOException {
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        Reader bufferedReader = new BufferedReader(reader);
        StringBuilder stringBuilder = new StringBuilder(256);
        int i = 1;
        while (i != 0) {
            boolean a = a(bufferedReader, stringBuilder);
            Object stringBuilder2 = stringBuilder.toString();
            if (stringBuilder2.isEmpty()) {
                i = a;
            } else if (stringBuilder2.startsWith("//")) {
                i = a;
            } else {
                if (stringBuilder2.startsWith(".")) {
                    stringBuilder2 = stringBuilder2.substring(1);
                }
                boolean startsWith = stringBuilder2.startsWith("!");
                if (startsWith) {
                    stringBuilder2 = stringBuilder2.substring(1);
                }
                if (startsWith) {
                    arrayList2.add(stringBuilder2);
                } else {
                    arrayList.add(stringBuilder2);
                }
                i = a;
            }
        }
        return new PublicSuffixList(arrayList, arrayList2);
    }

    private boolean a(Reader reader, StringBuilder stringBuilder) throws IOException {
        stringBuilder.setLength(0);
        boolean z = false;
        do {
            int read = reader.read();
            if (read != -1) {
                char c = (char) read;
                if (c != '\n') {
                    if (Character.isWhitespace(c)) {
                        z = true;
                    }
                    if (!z) {
                        stringBuilder.append(c);
                    }
                }
            }
            if (read != -1) {
                return true;
            }
            return false;
        } while (stringBuilder.length() <= 256);
        return false;
    }
}
