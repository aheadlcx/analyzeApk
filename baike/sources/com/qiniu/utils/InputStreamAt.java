package com.qiniu.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Images.Media;
import com.baidu.mobstat.Config;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import qsbk.app.core.model.CustomButton;

public abstract class InputStreamAt implements Closeable {
    protected long a;
    protected ArrayList<CleanCallBack> b = new ArrayList();

    public static class ByteInput extends InputStreamAt {
        private final byte[] c;

        public ByteInput(byte[] bArr) {
            this.c = bArr;
        }

        public long length() {
            return (long) this.c.length;
        }

        public long crc32() throws IOException {
            return Crc32.calc(this.c);
        }

        public void doClose() {
        }

        protected byte[] a(long j, int i) {
            int i2 = (int) j;
            if (i2 == 0 && ((long) i) == length()) {
                return this.c;
            }
            if (i <= 0) {
                return new byte[0];
            }
            byte[] bArr = new byte[i];
            System.arraycopy(this.c, i2, bArr, 0, i);
            i2 += i;
            return bArr;
        }
    }

    public interface CleanCallBack {
        void clean();
    }

    public static class FileInput extends InputStreamAt {
        private final RandomAccessFile c;
        private final File d;
        private final String e;

        public FileInput(File file) throws FileNotFoundException {
            this(file, null);
        }

        public FileInput(File file, String str) throws FileNotFoundException {
            this.d = file;
            this.c = new RandomAccessFile(file, CustomButton.POSITION_RIGHT);
            if (str == null || str.trim().length() <= 0) {
                str = file.getName();
            }
            this.e = str;
        }

        public long length() {
            return this.d.length();
        }

        public String getFilename() {
            return this.e;
        }

        public long crc32() throws IOException {
            return Crc32.calc(this.d);
        }

        public void doClose() {
            if (this.c != null) {
                try {
                    this.c.close();
                } catch (IOException e) {
                }
            }
        }

        protected byte[] a(long j, int i) throws IOException {
            if (j >= length()) {
                return null;
            }
            byte[] bArr = new byte[i];
            this.c.seek(j);
            this.c.read(bArr);
            long j2 = ((long) i) + j;
            return bArr;
        }
    }

    public interface Input {
        byte[] readAll() throws IOException;

        byte[] readNext(int i) throws IOException;
    }

    public static class UriInfo {
        private final Context a;
        private final Uri b;
        private InputStream c;
        private File d;
        private String e;
        private String f;
        private String g;
        private long h = -1;

        UriInfo(Context context, Uri uri) {
            this.a = context;
            this.b = uri;
            a();
        }

        private void a() {
            a(this.b.getPath());
            c();
            if (!b()) {
                e();
                a(this.g);
                if (!b()) {
                }
            }
        }

        public void reset() throws IOException {
            close();
            d();
        }

        private boolean b() {
            return this.d != null && this.d.exists() && this.d.isFile();
        }

        private void a(String str) {
            int i = 0;
            if (str != null) {
                String[] strArr = new String[]{"", "/mnt", "/mnt/"};
                int length = strArr.length;
                while (i < length) {
                    try {
                        this.d = new File(strArr[i] + str);
                    } catch (Exception e) {
                    }
                    if (!b()) {
                        i++;
                    } else {
                        return;
                    }
                }
            }
        }

        private void c() {
            if (b()) {
                this.e = this.d.getName();
                this.h = this.d.length();
                this.g = this.d.getAbsolutePath();
            }
        }

        private void d() throws FileNotFoundException {
            this.c = this.a.getContentResolver().openInputStream(this.b);
        }

        @SuppressLint({"NewApi"})
        private void e() {
            Throwable th;
            if ("content".equalsIgnoreCase(this.b.getScheme())) {
                Cursor query;
                try {
                    String[] strArr;
                    String str;
                    ContentResolver contentResolver = this.a.getContentResolver();
                    String[] strArr2 = new String[]{"_size", "_display_name", "mime_type", "_data"};
                    Uri uri = this.b;
                    if (VERSION.SDK_INT < 19 || !DocumentsContract.isDocumentUri(this.a, this.b)) {
                        strArr = null;
                        str = null;
                    } else {
                        strArr = new String[]{DocumentsContract.getDocumentId(this.b).split(Config.TRACE_TODAY_VISIT_SPLIT)[1]};
                        uri = Media.EXTERNAL_CONTENT_URI;
                        str = "_id=?";
                    }
                    query = contentResolver.query(uri, strArr2, str, strArr, null);
                    if (query != null) {
                        try {
                            if (query.moveToFirst()) {
                                int columnCount = query.getColumnCount();
                                for (int i = 0; i < columnCount; i++) {
                                    str = query.getColumnName(i);
                                    String string = query.getString(i);
                                    if ("_display_name".equalsIgnoreCase(str)) {
                                        this.e = string;
                                    } else if ("_size".equalsIgnoreCase(str)) {
                                        this.h = query.getLong(i);
                                    } else if ("mime_type".equalsIgnoreCase(str)) {
                                        this.f = string;
                                    } else if ("_data".equalsIgnoreCase(str)) {
                                        this.g = string;
                                    }
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Exception e) {
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    query = null;
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Exception e2) {
                        }
                    }
                    throw th;
                }
            }
        }

        public void close() {
            if (this.c != null) {
                try {
                    this.c.close();
                } catch (Exception e) {
                }
            }
            this.c = null;
        }

        public Context getContext() {
            return this.a;
        }

        public Uri getUri() {
            return this.b;
        }

        public File getFile() {
            return this.d;
        }

        public InputStream getIs() throws FileNotFoundException {
            if (this.c == null) {
                d();
            }
            return this.c;
        }

        public String getName() {
            return this.e;
        }

        public long length() {
            return this.h;
        }
    }

    public static class UriInput extends InputStreamAt {
        private UriInfo c;
        private FileInput d;
        private InputStream e;

        public UriInput(Context context, Uri uri) {
            this.c = new UriInfo(context, uri);
            File file = this.c.getFile();
            if (file != null && file.exists() && file.isFile()) {
                try {
                    this.d = new FileInput(file);
                } catch (FileNotFoundException e) {
                }
            }
        }

        private void b() throws FileNotFoundException {
            if (this.d == null && this.e == null && this.c != null) {
                this.e = this.c.getIs();
            }
        }

        public long length() {
            return this.c.length();
        }

        public String getFilename() {
            return this.c.getName();
        }

        public long crc32() throws IOException {
            if (this.d != null) {
                return this.d.crc32();
            }
            return Crc32.calc(new UriInfo(this.c.getContext(), this.c.getUri()).getIs());
        }

        public void reset() throws IOException {
            super.reset();
            if (this.d != null) {
                this.d.reset();
            } else if (this.c != null) {
                this.c.reset();
                this.a = 0;
                this.e = this.c.getIs();
            }
        }

        public void doClose() {
            if (this.e != null) {
                try {
                    this.e.close();
                } catch (IOException e) {
                }
            }
            if (this.c != null) {
                this.c.close();
            }
            if (this.d != null) {
                this.d.close();
            }
            this.e = null;
            this.c = null;
            this.d = null;
        }

        public Input readNext(int i) throws IOException {
            if (this.d != null) {
                return this.d.readNext(i);
            }
            return new c(this, i);
        }

        private byte[] a(int i) throws IOException {
            if (this.a >= length()) {
                return null;
            }
            b();
            if (((long) i) + this.a >= length()) {
                i = (int) (length() - this.a);
            }
            byte[] bArr = new byte[i];
            this.e.read(bArr);
            this.a += (long) i;
            return bArr;
        }

        protected byte[] a(long j, int i) throws IOException {
            throw new UnsupportedOperationException();
        }
    }

    protected abstract byte[] a(long j, int i) throws IOException;

    public abstract long crc32() throws IOException;

    protected abstract void doClose();

    public abstract long length();

    public static UriInput fromUri(Context context, Uri uri) {
        return new UriInput(context, uri);
    }

    public static FileInput fromInputStream(Context context, InputStream inputStream) throws IOException {
        return fromInputStream(context, inputStream, null);
    }

    public static FileInput fromInputStream(Context context, InputStream inputStream, String str) throws IOException {
        File storeToFile = Util.storeToFile(context, inputStream);
        if (storeToFile == null) {
            return null;
        }
        try {
            FileInput fileInput = new FileInput(storeToFile, str);
            fileInput.b.add(new a(storeToFile));
            return fileInput;
        } catch (IOException e) {
            if (storeToFile != null) {
                storeToFile.delete();
            }
            throw e;
        }
    }

    public static FileInput fromFile(File file) throws FileNotFoundException {
        return new FileInput(file);
    }

    public static ByteInput fromByte(byte[] bArr) {
        return new ByteInput(bArr);
    }

    protected static Input a(InputStreamAt inputStreamAt, int i) {
        return new b(inputStreamAt, i);
    }

    public String getFilename() {
        return null;
    }

    public Input readNext(int i) throws IOException {
        if (((long) i) + this.a >= length()) {
            i = (int) (length() - this.a);
        }
        Input a = a(this, i);
        this.a += (long) i;
        return a;
    }

    public void reset() throws IOException {
        this.a = 0;
    }

    protected long a() {
        return this.a;
    }

    public void close() {
        try {
            doClose();
        } catch (Exception e) {
        }
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            try {
                ((CleanCallBack) it.next()).clean();
            } catch (Exception e2) {
            }
        }
    }
}
