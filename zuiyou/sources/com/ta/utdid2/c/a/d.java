package com.ta.utdid2.c.a;

import com.ta.utdid2.c.a.b.b;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParserException;

public class d {
    private static final Object c = new Object();
    private File a;
    /* renamed from: a */
    private HashMap<File, a> f35a = new HashMap();
    private final Object b = new Object();

    private static final class a implements b {
        private static final Object d = new Object();
        private WeakHashMap<b, Object> a;
        private final File b;
        private final int c;
        /* renamed from: c */
        private final File f36c;
        /* renamed from: c */
        private Map f37c;
        private boolean k = false;

        public final class a implements com.ta.utdid2.c.a.b.a {
            final /* synthetic */ a a;
            private final Map<String, Object> d = new HashMap();
            private boolean l = false;

            public a(a aVar) {
                this.a = aVar;
            }

            public com.ta.utdid2.c.a.b.a a(String str, String str2) {
                synchronized (this) {
                    this.d.put(str, str2);
                }
                return this;
            }

            public com.ta.utdid2.c.a.b.a a(String str, int i) {
                synchronized (this) {
                    this.d.put(str, Integer.valueOf(i));
                }
                return this;
            }

            public com.ta.utdid2.c.a.b.a a(String str, long j) {
                synchronized (this) {
                    this.d.put(str, Long.valueOf(j));
                }
                return this;
            }

            public com.ta.utdid2.c.a.b.a a(String str, float f) {
                synchronized (this) {
                    this.d.put(str, Float.valueOf(f));
                }
                return this;
            }

            public com.ta.utdid2.c.a.b.a a(String str, boolean z) {
                synchronized (this) {
                    this.d.put(str, Boolean.valueOf(z));
                }
                return this;
            }

            public com.ta.utdid2.c.a.b.a a(String str) {
                synchronized (this) {
                    this.d.put(str, this);
                }
                return this;
            }

            public com.ta.utdid2.c.a.b.a b() {
                synchronized (this) {
                    this.l = true;
                }
                return this;
            }

            public boolean commit() {
                boolean a;
                Object obj = null;
                synchronized (d.a()) {
                    List list;
                    if (this.a.f37c.size() > 0) {
                        obj = 1;
                    }
                    Set hashSet;
                    if (obj != null) {
                        ArrayList arrayList = new ArrayList();
                        hashSet = new HashSet(this.a.f37c.keySet());
                        list = arrayList;
                    } else {
                        hashSet = null;
                        list = null;
                    }
                    synchronized (this) {
                        if (this.l) {
                            this.a.f37c.clear();
                            this.l = false;
                        }
                        for (Entry entry : this.d.entrySet()) {
                            String str = (String) entry.getKey();
                            a value = entry.getValue();
                            if (value == this) {
                                this.a.f37c.remove(str);
                            } else {
                                this.a.f37c.put(str, value);
                            }
                            if (obj != null) {
                                list.add(str);
                            }
                        }
                        this.d.clear();
                    }
                    a = this.a.f37c;
                    if (a) {
                        this.a.a(true);
                    }
                }
                if (obj != null) {
                    for (int size = list.size() - 1; size >= 0; size--) {
                        str = (String) list.get(size);
                        for (b bVar : r3) {
                            if (bVar != null) {
                                bVar.a(this.a, str);
                            }
                        }
                    }
                }
                return a;
            }
        }

        a(File file, int i, Map map) {
            this.b = file;
            this.f36c = d.a(file);
            this.c = i;
            if (map == null) {
                map = new HashMap();
            }
            this.f37c = map;
            this.a = new WeakHashMap();
        }

        /* renamed from: a */
        public boolean m18a() {
            if (this.b == null || !new File(this.b.getAbsolutePath()).exists()) {
                return false;
            }
            return true;
        }

        public void a(boolean z) {
            synchronized (this) {
                this.k = z;
            }
        }

        public boolean c() {
            boolean z;
            synchronized (this) {
                z = this.k;
            }
            return z;
        }

        public void a(Map map) {
            if (map != null) {
                synchronized (this) {
                    this.f37c = map;
                }
            }
        }

        public Map<String, ?> getAll() {
            Map hashMap;
            synchronized (this) {
                hashMap = new HashMap(this.f37c);
            }
            return hashMap;
        }

        public String getString(String str, String str2) {
            String str3;
            synchronized (this) {
                str3 = (String) this.f37c.get(str);
                if (str3 == null) {
                    str3 = str2;
                }
            }
            return str3;
        }

        public long getLong(String str, long j) {
            synchronized (this) {
                Long l = (Long) this.f37c.get(str);
                if (l != null) {
                    j = l.longValue();
                }
            }
            return j;
        }

        public com.ta.utdid2.c.a.b.a a() {
            return new a(this);
        }

        private FileOutputStream a(File file) {
            try {
                return new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                if (!file.getParentFile().mkdir()) {
                    return null;
                }
                try {
                    return new FileOutputStream(file);
                } catch (FileNotFoundException e2) {
                    return null;
                }
            }
        }

        private boolean d() {
            if (this.b.exists()) {
                if (this.f36c.exists()) {
                    this.b.delete();
                } else if (!this.b.renameTo(this.f36c)) {
                    return false;
                }
            }
            try {
                OutputStream a = a(this.b);
                if (a == null) {
                    return false;
                }
                e.a(this.f37c, a);
                a.close();
                this.f36c.delete();
                return true;
            } catch (XmlPullParserException e) {
                if (this.b.exists()) {
                    return false;
                }
                this.b.delete();
                return false;
            } catch (IOException e2) {
                if (this.b.exists()) {
                    return false;
                }
                this.b.delete();
                return false;
            }
        }
    }

    public d(String str) {
        if (str == null || str.length() <= 0) {
            throw new RuntimeException("Directory can not be empty");
        }
        this.a = new File(str);
    }

    private File a(File file, String str) {
        if (str.indexOf(File.separatorChar) < 0) {
            return new File(file, str);
        }
        throw new IllegalArgumentException("File " + str + " contains a path separator");
    }

    private File a() {
        File file;
        synchronized (this.b) {
            file = this.a;
        }
        return file;
    }

    private File b(String str) {
        return a(a(), new StringBuilder(String.valueOf(str)).append(".xml").toString());
    }

    public b a(String str, int i) {
        b bVar;
        InputStream fileInputStream;
        FileInputStream fileInputStream2;
        byte[] bArr;
        String str2;
        FileNotFoundException e;
        a aVar;
        Throwable th;
        IOException e2;
        FileNotFoundException fileNotFoundException;
        IOException iOException;
        InputStream inputStream;
        Exception exception;
        Map map = null;
        File b = b(str);
        synchronized (c) {
            bVar = (a) this.f35a.get(b);
            if (bVar == null || bVar.c()) {
                File a = a(b);
                if (a.exists()) {
                    b.delete();
                    a.renameTo(b);
                }
                if (b.exists()) {
                    b.canRead();
                }
                if (b.exists() && b.canRead()) {
                    try {
                        fileInputStream = new FileInputStream(b);
                        try {
                            map = e.a(fileInputStream);
                            fileInputStream.close();
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Throwable th2) {
                                }
                            }
                        } catch (XmlPullParserException e3) {
                            try {
                                fileInputStream2 = new FileInputStream(b);
                                try {
                                    bArr = new byte[fileInputStream2.available()];
                                    fileInputStream2.read(bArr);
                                    str2 = new String(bArr, 0, bArr.length, "UTF-8");
                                    if (fileInputStream2 != null) {
                                        try {
                                            fileInputStream2.close();
                                        } catch (Throwable th3) {
                                        }
                                    }
                                } catch (FileNotFoundException e4) {
                                    e = e4;
                                    try {
                                        e.printStackTrace();
                                        if (fileInputStream2 != null) {
                                            try {
                                                fileInputStream2.close();
                                            } catch (Throwable th4) {
                                            }
                                        }
                                        if (fileInputStream2 != null) {
                                            try {
                                                fileInputStream2.close();
                                            } catch (Throwable th5) {
                                            }
                                        }
                                        synchronized (c) {
                                            if (bVar != null) {
                                                aVar = (a) this.f35a.get(b);
                                                if (aVar == null) {
                                                    bVar = new a(b, i, map);
                                                    this.f35a.put(b, bVar);
                                                }
                                            } else {
                                                bVar.a(map);
                                            }
                                        }
                                        return bVar;
                                    } catch (Throwable th6) {
                                        th = th6;
                                        if (fileInputStream2 != null) {
                                            try {
                                                fileInputStream2.close();
                                            } catch (Throwable th7) {
                                            }
                                        }
                                        try {
                                            throw th;
                                        } catch (Throwable th8) {
                                            th = th8;
                                        }
                                    }
                                } catch (IOException e5) {
                                    e2 = e5;
                                    e2.printStackTrace();
                                    if (fileInputStream2 != null) {
                                        try {
                                            fileInputStream2.close();
                                        } catch (Throwable th9) {
                                        }
                                    }
                                    if (fileInputStream2 != null) {
                                        fileInputStream2.close();
                                    }
                                    synchronized (c) {
                                        if (bVar != null) {
                                            bVar.a(map);
                                        } else {
                                            aVar = (a) this.f35a.get(b);
                                            if (aVar == null) {
                                                bVar = new a(b, i, map);
                                                this.f35a.put(b, bVar);
                                            }
                                        }
                                    }
                                    return bVar;
                                }
                            } catch (FileNotFoundException e6) {
                                fileNotFoundException = e6;
                                fileInputStream2 = fileInputStream;
                                e = fileNotFoundException;
                                e.printStackTrace();
                                if (fileInputStream2 != null) {
                                    fileInputStream2.close();
                                }
                                if (fileInputStream2 != null) {
                                    fileInputStream2.close();
                                }
                                synchronized (c) {
                                    if (bVar != null) {
                                        aVar = (a) this.f35a.get(b);
                                        if (aVar == null) {
                                            bVar = new a(b, i, map);
                                            this.f35a.put(b, bVar);
                                        }
                                    } else {
                                        bVar.a(map);
                                    }
                                }
                                return bVar;
                            } catch (IOException e7) {
                                iOException = e7;
                                fileInputStream2 = fileInputStream;
                                e2 = iOException;
                                e2.printStackTrace();
                                if (fileInputStream2 != null) {
                                    fileInputStream2.close();
                                }
                                if (fileInputStream2 != null) {
                                    fileInputStream2.close();
                                }
                                synchronized (c) {
                                    if (bVar != null) {
                                        bVar.a(map);
                                    } else {
                                        aVar = (a) this.f35a.get(b);
                                        if (aVar == null) {
                                            bVar = new a(b, i, map);
                                            this.f35a.put(b, bVar);
                                        }
                                    }
                                }
                                return bVar;
                            } catch (Throwable th10) {
                                th = th10;
                                fileInputStream2 = fileInputStream;
                                if (fileInputStream2 != null) {
                                    fileInputStream2.close();
                                }
                                throw th;
                            }
                            if (fileInputStream2 != null) {
                                fileInputStream2.close();
                            }
                            synchronized (c) {
                                if (bVar != null) {
                                    aVar = (a) this.f35a.get(b);
                                    if (aVar == null) {
                                        bVar = new a(b, i, map);
                                        this.f35a.put(b, bVar);
                                    }
                                } else {
                                    bVar.a(map);
                                }
                            }
                            return bVar;
                        } catch (FileNotFoundException e62) {
                            fileNotFoundException = e62;
                            inputStream = fileInputStream;
                            e = fileNotFoundException;
                            e.printStackTrace();
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (Throwable th11) {
                                }
                            }
                            synchronized (c) {
                                if (bVar != null) {
                                    bVar.a(map);
                                } else {
                                    aVar = (a) this.f35a.get(b);
                                    if (aVar == null) {
                                        bVar = new a(b, i, map);
                                        this.f35a.put(b, bVar);
                                    }
                                }
                            }
                            return bVar;
                        } catch (IOException e72) {
                            iOException = e72;
                            inputStream = fileInputStream;
                            e2 = iOException;
                            e2.printStackTrace();
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (Throwable th12) {
                                }
                            }
                            synchronized (c) {
                                if (bVar != null) {
                                    aVar = (a) this.f35a.get(b);
                                    if (aVar == null) {
                                        bVar = new a(b, i, map);
                                        this.f35a.put(b, bVar);
                                    }
                                } else {
                                    bVar.a(map);
                                }
                            }
                            return bVar;
                        } catch (Exception e8) {
                            Exception exception2 = e8;
                            inputStream = fileInputStream;
                            exception = exception2;
                            exception.printStackTrace();
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (Throwable th13) {
                                }
                            }
                            synchronized (c) {
                                if (bVar != null) {
                                    bVar.a(map);
                                } else {
                                    aVar = (a) this.f35a.get(b);
                                    if (aVar == null) {
                                        bVar = new a(b, i, map);
                                        this.f35a.put(b, bVar);
                                    }
                                }
                            }
                            return bVar;
                        } catch (Throwable th14) {
                            th = th14;
                            inputStream = fileInputStream;
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (Throwable th15) {
                                }
                            }
                            throw th;
                        }
                    } catch (XmlPullParserException e9) {
                        fileInputStream = null;
                        fileInputStream2 = new FileInputStream(b);
                        bArr = new byte[fileInputStream2.available()];
                        fileInputStream2.read(bArr);
                        str2 = new String(bArr, 0, bArr.length, "UTF-8");
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        synchronized (c) {
                            if (bVar != null) {
                                aVar = (a) this.f35a.get(b);
                                if (aVar == null) {
                                    bVar = new a(b, i, map);
                                    this.f35a.put(b, bVar);
                                }
                            } else {
                                bVar.a(map);
                            }
                        }
                        return bVar;
                    } catch (FileNotFoundException e10) {
                        e = e10;
                        fileInputStream2 = null;
                        e.printStackTrace();
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        synchronized (c) {
                            if (bVar != null) {
                                bVar.a(map);
                            } else {
                                aVar = (a) this.f35a.get(b);
                                if (aVar == null) {
                                    bVar = new a(b, i, map);
                                    this.f35a.put(b, bVar);
                                }
                            }
                        }
                        return bVar;
                    } catch (IOException e11) {
                        e2 = e11;
                        fileInputStream2 = null;
                        e2.printStackTrace();
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        synchronized (c) {
                            if (bVar != null) {
                                aVar = (a) this.f35a.get(b);
                                if (aVar == null) {
                                    bVar = new a(b, i, map);
                                    this.f35a.put(b, bVar);
                                }
                            } else {
                                bVar.a(map);
                            }
                        }
                        return bVar;
                    } catch (Exception e12) {
                        exception = e12;
                        fileInputStream2 = null;
                        exception.printStackTrace();
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        synchronized (c) {
                            if (bVar != null) {
                                bVar.a(map);
                            } else {
                                aVar = (a) this.f35a.get(b);
                                if (aVar == null) {
                                    bVar = new a(b, i, map);
                                    this.f35a.put(b, bVar);
                                }
                            }
                        }
                        return bVar;
                    } catch (Throwable th16) {
                        th = th16;
                        fileInputStream2 = null;
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        throw th;
                    }
                }
                synchronized (c) {
                    if (bVar != null) {
                        bVar.a(map);
                    } else {
                        aVar = (a) this.f35a.get(b);
                        if (aVar == null) {
                            bVar = new a(b, i, map);
                            this.f35a.put(b, bVar);
                        }
                    }
                }
            }
        }
        return bVar;
    }

    private static File a(File file) {
        return new File(file.getPath() + ".bak");
    }
}
