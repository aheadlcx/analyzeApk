package com.danikula.videocache.a;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

class d {

    private static final class a implements Comparator<File> {
        private a() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((File) obj, (File) obj2);
        }

        public int a(File file, File file2) {
            return a(file.lastModified(), file2.lastModified());
        }

        private int a(long j, long j2) {
            if (j < j2) {
                return -1;
            }
            return j == j2 ? 0 : 1;
        }
    }

    static void a(File file) throws IOException {
        if (file.exists()) {
            if (!file.isDirectory()) {
                throw new IOException("File " + file + " is not directory!");
            }
        } else if (!file.mkdirs()) {
            throw new IOException(String.format("Directory %s can't be created", new Object[]{file.getAbsolutePath()}));
        }
    }

    static List<File> b(File file) {
        List<File> linkedList = new LinkedList();
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return linkedList;
        }
        linkedList = Arrays.asList(listFiles);
        Collections.sort(linkedList, new a());
        return linkedList;
    }

    static void c(File file) throws IOException {
        if (file.exists()) {
            file.setLastModified(System.currentTimeMillis());
        }
    }
}
