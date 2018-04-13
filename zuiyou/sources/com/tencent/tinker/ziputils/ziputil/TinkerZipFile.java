package com.tencent.tinker.ziputils.ziputil;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.zip.ZipException;

public class TinkerZipFile implements Closeable {
    static final int GPBF_DATA_DESCRIPTOR_FLAG = 8;
    static final int GPBF_ENCRYPTED_FLAG = 1;
    static final int GPBF_UNSUPPORTED_MASK = 1;
    static final int GPBF_UTF8_FLAG = 2048;
    public static final int OPEN_DELETE = 4;
    public static final int OPEN_READ = 1;
    private String comment;
    private final LinkedHashMap<String, TinkerZipEntry> entries;
    private File fileToDeleteOnClose;
    private final String filename;
    private RandomAccessFile raf;

    public static class RAFStream extends InputStream {
        private long endOffset;
        private long offset;
        private final RandomAccessFile sharedRaf;

        public RAFStream(RandomAccessFile randomAccessFile, long j, long j2) {
            this.sharedRaf = randomAccessFile;
            this.offset = j;
            this.endOffset = j2;
        }

        public RAFStream(RandomAccessFile randomAccessFile, long j) throws IOException {
            this(randomAccessFile, j, randomAccessFile.length());
        }

        public int available() throws IOException {
            return this.offset < this.endOffset ? 1 : 0;
        }

        public int read() throws IOException {
            return Streams.readSingleByte(this);
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read;
            synchronized (this.sharedRaf) {
                long j = this.endOffset - this.offset;
                if (((long) i2) > j) {
                    i2 = (int) j;
                }
                this.sharedRaf.seek(this.offset);
                read = this.sharedRaf.read(bArr, i, i2);
                if (read > 0) {
                    this.offset += (long) read;
                } else {
                    read = -1;
                }
            }
            return read;
        }

        public long skip(long j) throws IOException {
            if (j > this.endOffset - this.offset) {
                j = this.endOffset - this.offset;
            }
            this.offset += j;
            return j;
        }
    }

    static class a {
        final long a;
        final long b;
        final int c;

        a(long j, long j2, int i) {
            this.a = j;
            this.b = j2;
            this.c = i;
        }
    }

    public TinkerZipFile(File file) throws ZipException, IOException {
        this(file, 1);
    }

    public TinkerZipFile(String str) throws IOException {
        this(new File(str), 1);
    }

    public TinkerZipFile(File file, int i) throws IOException {
        this.entries = new LinkedHashMap();
        this.filename = file.getPath();
        if (i == 1 || i == 5) {
            if ((i & 4) != 0) {
                this.fileToDeleteOnClose = file;
                this.fileToDeleteOnClose.deleteOnExit();
            } else {
                this.fileToDeleteOnClose = null;
            }
            this.raf = new RandomAccessFile(this.filename, "r");
            readCentralDir();
            return;
        }
        throw new IllegalArgumentException("Bad mode: " + i);
    }

    public static boolean isEmpty(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean equals(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == charSequence2) {
            return true;
        }
        if (!(charSequence == null || charSequence2 == null)) {
            int length = charSequence.length();
            if (length == charSequence2.length()) {
                if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
                    return charSequence.equals(charSequence2);
                }
                for (int i = 0; i < length; i++) {
                    if (charSequence.charAt(i) != charSequence2.charAt(i)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private static a parseEocdRecord(RandomAccessFile randomAccessFile, long j, boolean z) throws IOException {
        long j2;
        long j3 = -1;
        randomAccessFile.seek(j);
        byte[] bArr = new byte[18];
        randomAccessFile.readFully(bArr);
        BufferIterator it = HeapBufferIterator.iterator(bArr, 0, bArr.length, ByteOrder.LITTLE_ENDIAN);
        if (z) {
            it.skip(16);
            j2 = -1;
        } else {
            int readShort = it.readShort() & 65535;
            int readShort2 = it.readShort() & 65535;
            j2 = (long) (it.readShort() & 65535);
            int readShort3 = it.readShort() & 65535;
            it.skip(4);
            j3 = ((long) it.readInt()) & 4294967295L;
            if (!(j2 == ((long) readShort3) && readShort == 0 && readShort2 == 0)) {
                throw new ZipException("Spanned archives not supported");
            }
        }
        return new a(j2, j3, it.readShort() & 65535);
    }

    static void throwZipException(String str, long j, String str2, long j2, String str3, int i) throws ZipException {
        throw new ZipException("file name:" + str + ", file size" + j + ", entry name:" + str2 + ", entry localHeaderRelOffset:" + j2 + ", " + str3 + " signature not found; was " + Integer.toHexString(i));
    }

    public void close() throws IOException {
        RandomAccessFile randomAccessFile = this.raf;
        if (randomAccessFile != null) {
            synchronized (randomAccessFile) {
                this.raf = null;
                randomAccessFile.close();
            }
            if (this.fileToDeleteOnClose != null) {
                this.fileToDeleteOnClose.delete();
                this.fileToDeleteOnClose = null;
            }
        }
    }

    private void checkNotClosed() {
        if (this.raf == null) {
            throw new IllegalStateException("Zip file closed");
        }
    }

    public Enumeration<? extends TinkerZipEntry> entries() {
        checkNotClosed();
        final Iterator it = this.entries.values().iterator();
        return new Enumeration<TinkerZipEntry>(this) {
            final /* synthetic */ TinkerZipFile b;

            public /* synthetic */ Object nextElement() {
                return a();
            }

            public boolean hasMoreElements() {
                this.b.checkNotClosed();
                return it.hasNext();
            }

            public TinkerZipEntry a() {
                this.b.checkNotClosed();
                return (TinkerZipEntry) it.next();
            }
        };
    }

    public String getComment() {
        checkNotClosed();
        return this.comment;
    }

    public TinkerZipEntry getEntry(String str) {
        checkNotClosed();
        if (str == null) {
            throw new NullPointerException("entryName == null");
        }
        TinkerZipEntry tinkerZipEntry = (TinkerZipEntry) this.entries.get(str);
        if (tinkerZipEntry == null) {
            return (TinkerZipEntry) this.entries.get(str + "/");
        }
        return tinkerZipEntry;
    }

    public InputStream getInputStream(TinkerZipEntry tinkerZipEntry) throws IOException {
        TinkerZipEntry entry = getEntry(tinkerZipEntry.getName());
        if (entry == null) {
            return null;
        }
        InputStream rAFStream;
        RandomAccessFile randomAccessFile = this.raf;
        synchronized (randomAccessFile) {
            rAFStream = new RAFStream(randomAccessFile, entry.localHeaderRelOffset);
            DataInputStream dataInputStream = new DataInputStream(rAFStream);
            int reverseBytes = Integer.reverseBytes(dataInputStream.readInt());
            if (((long) reverseBytes) != 67324752) {
                throwZipException(this.filename, randomAccessFile.length(), entry.getName(), entry.localHeaderRelOffset, "Local File Header", reverseBytes);
            }
            dataInputStream.skipBytes(2);
            int reverseBytes2 = Short.reverseBytes(dataInputStream.readShort()) & 65535;
            if ((reverseBytes2 & 1) != 0) {
                throw new ZipException("Invalid General Purpose Bit Flag: " + reverseBytes2);
            }
            dataInputStream.skipBytes(18);
            reverseBytes2 = Short.reverseBytes(dataInputStream.readShort()) & 65535;
            int reverseBytes3 = Short.reverseBytes(dataInputStream.readShort()) & 65535;
            dataInputStream.close();
            rAFStream.skip((long) (reverseBytes2 + reverseBytes3));
            if (entry.compressionMethod == 0) {
                rAFStream.endOffset = rAFStream.offset + entry.size;
            } else {
                rAFStream.endOffset = rAFStream.offset + entry.compressedSize;
            }
        }
        return rAFStream;
    }

    public String getName() {
        return this.filename;
    }

    public int size() {
        checkNotClosed();
        return this.entries.size();
    }

    private void readCentralDir() throws IOException {
        long j = 0;
        long length = this.raf.length() - 22;
        if (length < 0) {
            throw new ZipException("File too short to be a zip file: " + this.raf.length());
        }
        this.raf.seek(0);
        if (((long) Integer.reverseBytes(this.raf.readInt())) != 67324752) {
            throw new ZipException("Not a zip archive");
        }
        long j2 = length - 65536;
        if (j2 < 0) {
            j2 = length;
        } else {
            j = j2;
            j2 = length;
        }
        do {
            this.raf.seek(j2);
            if (((long) Integer.reverseBytes(this.raf.readInt())) == 101010256) {
                byte[] bArr = new byte[18];
                this.raf.readFully(bArr);
                BufferIterator it = HeapBufferIterator.iterator(bArr, 0, bArr.length, ByteOrder.LITTLE_ENDIAN);
                int readShort = it.readShort() & 65535;
                int readShort2 = it.readShort() & 65535;
                int readShort3 = it.readShort() & 65535;
                int readShort4 = it.readShort() & 65535;
                it.skip(4);
                long readInt = ((long) it.readInt()) & 4294967295L;
                int readShort5 = it.readShort() & 65535;
                if (readShort3 == readShort4 && readShort == 0 && readShort2 == 0) {
                    if (readShort5 > 0) {
                        bArr = new byte[readShort5];
                        this.raf.readFully(bArr);
                        this.comment = new String(bArr, 0, bArr.length, StandardCharsets.UTF_8);
                    }
                    InputStream bufferedInputStream = new BufferedInputStream(new RAFStream(this.raf, readInt), 4096);
                    byte[] bArr2 = new byte[46];
                    for (readShort5 = 0; readShort5 < readShort3; readShort5++) {
                        TinkerZipEntry tinkerZipEntry = new TinkerZipEntry(bArr2, bufferedInputStream, StandardCharsets.UTF_8, false);
                        if (tinkerZipEntry.localHeaderRelOffset >= readInt) {
                            throw new ZipException("Local file header offset is after central directory");
                        }
                        String name = tinkerZipEntry.getName();
                        if (this.entries.put(name, tinkerZipEntry) != null) {
                            throw new ZipException("Duplicate entry name: " + name);
                        }
                    }
                    return;
                }
                throw new ZipException("Spanned archives not supported");
            }
            j2--;
        } while (j2 >= j);
        throw new ZipException("End Of Central Directory signature not found");
    }
}
