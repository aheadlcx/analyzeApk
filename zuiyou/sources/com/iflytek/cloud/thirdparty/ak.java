package com.iflytek.cloud.thirdparty;

import android.os.Environment;
import android.text.TextUtils;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ak {

    public static class a {
        private String a = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/DataFiles/");
        private FileInputStream b;
        private BufferedOutputStream c;
        private long d = 0;
        private BufferedWriter e;

        public a(String str) {
            this.a = str;
        }

        public int a(byte[] bArr) {
            synchronized (this) {
                if (this.b != null) {
                    try {
                        int read = this.b.read(bArr);
                        return read;
                    } catch (IOException e) {
                        e.printStackTrace();
                        c();
                        return 0;
                    }
                }
                return -1;
            }
        }

        public void a() {
            synchronized (this) {
                if (this.e != null) {
                    try {
                        this.e.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    this.e = null;
                }
            }
        }

        public void a(String str) {
            synchronized (this) {
                if (this.e != null) {
                    try {
                        this.e.write(str);
                        this.e.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void a(String str, String str2) {
            File file = new File(this.a);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (this.e == null) {
                if (TextUtils.isEmpty(str)) {
                    str = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINA).format(new Date());
                }
                file = new File(this.a + str + str2);
                try {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    this.e = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void a(String str, String str2, boolean z) {
            File file = new File(this.a);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (this.c == null) {
                if (TextUtils.isEmpty(str)) {
                    str = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINA).format(new Date());
                }
                try {
                    this.c = new BufferedOutputStream(new FileOutputStream(new File(this.a + str + str2), z));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void a(byte[] bArr, boolean z) throws IOException {
            synchronized (this) {
                if (this.c != null) {
                    this.c.write(bArr);
                    if (z) {
                        this.c.flush();
                    }
                    this.d += (long) bArr.length;
                }
            }
        }

        public boolean a(String str, boolean z) {
            try {
                this.b = new FileInputStream(z ? new File(this.a + str) : new File(str));
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                this.b = null;
                return false;
            }
        }

        public void b() {
            c();
            a();
            d();
        }

        public void b(String str) {
            if (TextUtils.isEmpty(str)) {
                str = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINA).format(new Date());
            }
            a(str, ".pcm", false);
        }

        public void c() {
            synchronized (this) {
                if (this.b != null) {
                    try {
                        this.b.close();
                        this.b = null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public boolean c(String str) {
            return a(str, true);
        }

        public void d() {
            synchronized (this) {
                if (this.c != null) {
                    try {
                        this.c.flush();
                        this.c.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    this.c = null;
                }
                this.d = 0;
            }
        }

        public int e() {
            if (this.b != null) {
                try {
                    return this.b.available();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return -1;
        }

        public long f() {
            long j;
            synchronized (this) {
                j = this.d;
            }
            return j;
        }
    }

    public static class b extends Thread {
        private int a = 600;
        private long b;
        private File c;
        private long d;
        private boolean e = false;
        private Comparator<File> f = new Comparator<File>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public int a(File file, File file2) {
                long lastModified = file.lastModified();
                long lastModified2 = file2.lastModified();
                return lastModified < lastModified2 ? -1 : lastModified > lastModified2 ? 1 : 0;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((File) obj, (File) obj2);
            }
        };

        public b(String str, long j, long j2, int i) {
            super("DirSizeDeamonThread");
            this.c = new File(str);
            this.d = j;
            this.b = j2;
            this.a = i;
            if (this.a < 600) {
                this.a = 600;
            }
        }

        private List<File> a(List<File> list, Map<File, Long> map, double d) {
            List<File> list2;
            if (list == null || map == null || d <= 0.0d) {
                list2 = null;
            } else {
                List<File> arrayList = new ArrayList();
                double d2 = 0.0d;
                for (File file : list) {
                    d2 += (double) ((Long) map.get(file)).longValue();
                    arrayList.add(file);
                    if (d2 >= d) {
                        break;
                    }
                }
                if (arrayList.size() == 0) {
                    return null;
                }
                list2 = arrayList;
            }
            return list2;
        }

        private void a(List<File> list) {
            if (list != null) {
                for (File file : list) {
                    try {
                        cb.a("DirSizeDeamon", "del file:" + file.getName());
                        ak.a(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void a() {
            this.e = true;
            interrupt();
        }

        public void a(String str) {
            this.c = new File(str);
        }

        public void run() {
            super.run();
            while (!this.e) {
                Map hashMap = new HashMap();
                long currentTimeMillis = System.currentTimeMillis();
                double a = (double) ak.a(this.c, hashMap, true);
                cb.a("DirSizeDeamon", "get total file size, spent=" + (System.currentTimeMillis() - currentTimeMillis));
                if (a - ((double) this.d) > 0.0d) {
                    List arrayList = new ArrayList(hashMap.keySet());
                    Collections.sort(arrayList, this.f);
                    List a2 = a(arrayList, hashMap, (double) this.b);
                    currentTimeMillis = System.currentTimeMillis();
                    a(a2);
                    cb.a("DirSizeDeamon", "delete files, spent=" + (System.currentTimeMillis() - currentTimeMillis));
                }
                try {
                    sleep((long) (this.a * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static long a(File file, Map<File, Long> map, boolean z) {
        long j = 0;
        if (!file.exists()) {
            return 0;
        }
        if (file.isFile()) {
            return file.length();
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return 0;
        }
        for (File file2 : listFiles) {
            long a = a(file2, null, z);
            if (map != null) {
                if (!z) {
                    map.put(file2.getAbsoluteFile(), Long.valueOf(a));
                } else if (file2.isDirectory()) {
                    map.put(file2.getAbsoluteFile(), Long.valueOf(a));
                }
            }
            j += a;
        }
        return j;
    }

    public static a a(String str) {
        return new a(str);
    }

    public static boolean a(File file) {
        if (file == null) {
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return true;
        }
        boolean z = true;
        for (File a : listFiles) {
            z = a(a);
            if (!z) {
                return z;
            }
        }
        return z;
    }
}
