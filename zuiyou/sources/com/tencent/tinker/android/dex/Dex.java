package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.ClassData.Method;
import com.tencent.tinker.android.dex.io.DexDataBuffer;
import com.tencent.tinker.android.dex.util.FileUtils;
import com.tencent.tinker.android.dx.util.Hex;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.AbstractList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.zip.Adler32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public final class Dex {
    private static final int CHECKSUM_OFFSET = 8;
    static final short[] EMPTY_SHORT_ARRAY = new short[0];
    private static final int SIGNATURE_OFFSET = 12;
    private final c classDefs;
    private ByteBuffer data;
    private final d fieldIds;
    private final e methodIds;
    private int nextSectionStart;
    private final f protoIds;
    private byte[] signature;
    private final g strings;
    private final TableOfContents tableOfContents;
    private final h typeIds;
    private final i typeNames;

    public final class Section extends DexDataBuffer {
        private final String name;

        private Section(String str, ByteBuffer byteBuffer) {
            super(byteBuffer);
            this.name = str;
        }

        public StringData readStringData() {
            ensureFourBytesAligned(Dex.this.tableOfContents.stringDatas, false);
            return super.readStringData();
        }

        public TypeList readTypeList() {
            ensureFourBytesAligned(Dex.this.tableOfContents.typeLists, false);
            return super.readTypeList();
        }

        public FieldId readFieldId() {
            ensureFourBytesAligned(Dex.this.tableOfContents.fieldIds, false);
            return super.readFieldId();
        }

        public MethodId readMethodId() {
            ensureFourBytesAligned(Dex.this.tableOfContents.methodIds, false);
            return super.readMethodId();
        }

        public ProtoId readProtoId() {
            ensureFourBytesAligned(Dex.this.tableOfContents.protoIds, false);
            return super.readProtoId();
        }

        public ClassDef readClassDef() {
            ensureFourBytesAligned(Dex.this.tableOfContents.classDefs, false);
            return super.readClassDef();
        }

        public Code readCode() {
            ensureFourBytesAligned(Dex.this.tableOfContents.codes, false);
            return super.readCode();
        }

        public DebugInfoItem readDebugInfoItem() {
            ensureFourBytesAligned(Dex.this.tableOfContents.debugInfos, false);
            return super.readDebugInfoItem();
        }

        public ClassData readClassData() {
            ensureFourBytesAligned(Dex.this.tableOfContents.classDatas, false);
            return super.readClassData();
        }

        public Annotation readAnnotation() {
            ensureFourBytesAligned(Dex.this.tableOfContents.annotations, false);
            return super.readAnnotation();
        }

        public AnnotationSet readAnnotationSet() {
            ensureFourBytesAligned(Dex.this.tableOfContents.annotationSets, false);
            return super.readAnnotationSet();
        }

        public AnnotationSetRefList readAnnotationSetRefList() {
            ensureFourBytesAligned(Dex.this.tableOfContents.annotationSetRefLists, false);
            return super.readAnnotationSetRefList();
        }

        public AnnotationsDirectory readAnnotationsDirectory() {
            ensureFourBytesAligned(Dex.this.tableOfContents.annotationsDirectories, false);
            return super.readAnnotationsDirectory();
        }

        public EncodedValue readEncodedArray() {
            ensureFourBytesAligned(Dex.this.tableOfContents.encodedArrays, false);
            return super.readEncodedArray();
        }

        private void ensureFourBytesAligned(com.tencent.tinker.android.dex.TableOfContents.Section section, boolean z) {
            if (!section.isElementFourByteAligned) {
                return;
            }
            if (z) {
                alignToFourBytesWithZeroFill();
            } else {
                alignToFourBytes();
            }
        }

        public int writeStringData(StringData stringData) {
            ensureFourBytesAligned(Dex.this.tableOfContents.stringDatas, true);
            return super.writeStringData(stringData);
        }

        public int writeTypeList(TypeList typeList) {
            ensureFourBytesAligned(Dex.this.tableOfContents.typeLists, true);
            return super.writeTypeList(typeList);
        }

        public int writeFieldId(FieldId fieldId) {
            ensureFourBytesAligned(Dex.this.tableOfContents.fieldIds, true);
            return super.writeFieldId(fieldId);
        }

        public int writeMethodId(MethodId methodId) {
            ensureFourBytesAligned(Dex.this.tableOfContents.methodIds, true);
            return super.writeMethodId(methodId);
        }

        public int writeProtoId(ProtoId protoId) {
            ensureFourBytesAligned(Dex.this.tableOfContents.protoIds, true);
            return super.writeProtoId(protoId);
        }

        public int writeClassDef(ClassDef classDef) {
            ensureFourBytesAligned(Dex.this.tableOfContents.classDefs, true);
            return super.writeClassDef(classDef);
        }

        public int writeCode(Code code) {
            ensureFourBytesAligned(Dex.this.tableOfContents.codes, true);
            return super.writeCode(code);
        }

        public int writeDebugInfoItem(DebugInfoItem debugInfoItem) {
            ensureFourBytesAligned(Dex.this.tableOfContents.debugInfos, true);
            return super.writeDebugInfoItem(debugInfoItem);
        }

        public int writeClassData(ClassData classData) {
            ensureFourBytesAligned(Dex.this.tableOfContents.classDatas, true);
            return super.writeClassData(classData);
        }

        public int writeAnnotation(Annotation annotation) {
            ensureFourBytesAligned(Dex.this.tableOfContents.annotations, true);
            return super.writeAnnotation(annotation);
        }

        public int writeAnnotationSet(AnnotationSet annotationSet) {
            ensureFourBytesAligned(Dex.this.tableOfContents.annotationSets, true);
            return super.writeAnnotationSet(annotationSet);
        }

        public int writeAnnotationSetRefList(AnnotationSetRefList annotationSetRefList) {
            ensureFourBytesAligned(Dex.this.tableOfContents.annotationSetRefLists, true);
            return super.writeAnnotationSetRefList(annotationSetRefList);
        }

        public int writeAnnotationsDirectory(AnnotationsDirectory annotationsDirectory) {
            ensureFourBytesAligned(Dex.this.tableOfContents.annotationsDirectories, true);
            return super.writeAnnotationsDirectory(annotationsDirectory);
        }

        public int writeEncodedArray(EncodedValue encodedValue) {
            ensureFourBytesAligned(Dex.this.tableOfContents.encodedArrays, true);
            return super.writeEncodedArray(encodedValue);
        }
    }

    private final class a implements Iterable<ClassDef> {
        final /* synthetic */ Dex a;

        private a(Dex dex) {
            this.a = dex;
        }

        public Iterator<ClassDef> iterator() {
            return !this.a.tableOfContents.classDefs.exists() ? Collections.emptySet().iterator() : new b();
        }
    }

    private final class b implements Iterator<ClassDef> {
        final /* synthetic */ Dex a;
        private final Section b;
        private int c;

        private b(Dex dex) {
            this.a = dex;
            this.b = this.a.openSection(this.a.tableOfContents.classDefs);
            this.c = 0;
        }

        public /* synthetic */ Object next() {
            return a();
        }

        public boolean hasNext() {
            return this.c < this.a.tableOfContents.classDefs.size;
        }

        public ClassDef a() {
            if (hasNext()) {
                this.c++;
                return this.b.readClassDef();
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private final class c extends AbstractList<ClassDef> implements RandomAccess {
        final /* synthetic */ Dex a;

        private c(Dex dex) {
            this.a = dex;
        }

        public /* synthetic */ Object get(int i) {
            return a(i);
        }

        public ClassDef a(int i) {
            Dex.checkBounds(i, this.a.tableOfContents.classDefs.size);
            return this.a.openSection(this.a.tableOfContents.classDefs.off + (i * 32)).readClassDef();
        }

        public int size() {
            return this.a.tableOfContents.classDefs.size;
        }
    }

    private final class d extends AbstractList<FieldId> implements RandomAccess {
        final /* synthetic */ Dex a;

        private d(Dex dex) {
            this.a = dex;
        }

        public /* synthetic */ Object get(int i) {
            return a(i);
        }

        public FieldId a(int i) {
            Dex.checkBounds(i, this.a.tableOfContents.fieldIds.size);
            return this.a.openSection(this.a.tableOfContents.fieldIds.off + (i * 8)).readFieldId();
        }

        public int size() {
            return this.a.tableOfContents.fieldIds.size;
        }
    }

    private final class e extends AbstractList<MethodId> implements RandomAccess {
        final /* synthetic */ Dex a;

        private e(Dex dex) {
            this.a = dex;
        }

        public /* synthetic */ Object get(int i) {
            return a(i);
        }

        public MethodId a(int i) {
            Dex.checkBounds(i, this.a.tableOfContents.methodIds.size);
            return this.a.openSection(this.a.tableOfContents.methodIds.off + (i * 8)).readMethodId();
        }

        public int size() {
            return this.a.tableOfContents.methodIds.size;
        }
    }

    private final class f extends AbstractList<ProtoId> implements RandomAccess {
        final /* synthetic */ Dex a;

        private f(Dex dex) {
            this.a = dex;
        }

        public /* synthetic */ Object get(int i) {
            return a(i);
        }

        public ProtoId a(int i) {
            Dex.checkBounds(i, this.a.tableOfContents.protoIds.size);
            return this.a.openSection(this.a.tableOfContents.protoIds.off + (i * 12)).readProtoId();
        }

        public int size() {
            return this.a.tableOfContents.protoIds.size;
        }
    }

    private final class g extends AbstractList<String> implements RandomAccess {
        final /* synthetic */ Dex a;

        private g(Dex dex) {
            this.a = dex;
        }

        public /* synthetic */ Object get(int i) {
            return a(i);
        }

        public String a(int i) {
            Dex.checkBounds(i, this.a.tableOfContents.stringIds.size);
            return this.a.openSection(this.a.openSection(this.a.tableOfContents.stringIds.off + (i * 4)).readInt()).readStringData().value;
        }

        public int size() {
            return this.a.tableOfContents.stringIds.size;
        }
    }

    private final class h extends AbstractList<Integer> implements RandomAccess {
        final /* synthetic */ Dex a;

        private h(Dex dex) {
            this.a = dex;
        }

        public /* synthetic */ Object get(int i) {
            return a(i);
        }

        public Integer a(int i) {
            return Integer.valueOf(this.a.descriptorIndexFromTypeIndex(i));
        }

        public int size() {
            return this.a.tableOfContents.typeIds.size;
        }
    }

    private final class i extends AbstractList<String> implements RandomAccess {
        final /* synthetic */ Dex a;

        private i(Dex dex) {
            this.a = dex;
        }

        public /* synthetic */ Object get(int i) {
            return a(i);
        }

        public String a(int i) {
            return this.a.strings.a(this.a.descriptorIndexFromTypeIndex(i));
        }

        public int size() {
            return this.a.tableOfContents.typeIds.size;
        }
    }

    public Dex(byte[] bArr) throws IOException {
        this(ByteBuffer.wrap(bArr));
    }

    private Dex(ByteBuffer byteBuffer) throws IOException {
        this.tableOfContents = new TableOfContents();
        this.strings = new g();
        this.typeIds = new h();
        this.typeNames = new i();
        this.protoIds = new f();
        this.fieldIds = new d();
        this.methodIds = new e();
        this.classDefs = new c();
        this.nextSectionStart = 0;
        this.signature = null;
        this.data = byteBuffer;
        this.data.order(ByteOrder.LITTLE_ENDIAN);
        this.tableOfContents.readFrom(this);
    }

    public Dex(int i) {
        this.tableOfContents = new TableOfContents();
        this.strings = new g();
        this.typeIds = new h();
        this.typeNames = new i();
        this.protoIds = new f();
        this.fieldIds = new d();
        this.methodIds = new e();
        this.classDefs = new c();
        this.nextSectionStart = 0;
        this.signature = null;
        this.data = ByteBuffer.wrap(new byte[i]);
        this.data.order(ByteOrder.LITTLE_ENDIAN);
        this.tableOfContents.fileSize = i;
    }

    public Dex(InputStream inputStream) throws IOException {
        this.tableOfContents = new TableOfContents();
        this.strings = new g();
        this.typeIds = new h();
        this.typeNames = new i();
        this.protoIds = new f();
        this.fieldIds = new d();
        this.methodIds = new e();
        this.classDefs = new c();
        this.nextSectionStart = 0;
        this.signature = null;
        loadFrom(inputStream);
    }

    public Dex(InputStream inputStream, int i) throws IOException {
        this.tableOfContents = new TableOfContents();
        this.strings = new g();
        this.typeIds = new h();
        this.typeNames = new i();
        this.protoIds = new f();
        this.fieldIds = new d();
        this.methodIds = new e();
        this.classDefs = new c();
        this.nextSectionStart = 0;
        this.signature = null;
        loadFrom(inputStream, i);
    }

    public Dex(File file) throws IOException {
        Throwable th;
        ZipFile zipFile;
        InputStream inputStream = null;
        this.tableOfContents = new TableOfContents();
        this.strings = new g();
        this.typeIds = new h();
        this.typeNames = new i();
        this.protoIds = new f();
        this.fieldIds = new d();
        this.methodIds = new e();
        this.classDefs = new c();
        this.nextSectionStart = 0;
        this.signature = null;
        if (file == null) {
            throw new IllegalArgumentException("file is null.");
        } else if (FileUtils.hasArchiveSuffix(file.getName())) {
            try {
                ZipFile zipFile2 = new ZipFile(file);
                try {
                    ZipEntry entry = zipFile2.getEntry("classes.dex");
                    if (entry != null) {
                        inputStream = zipFile2.getInputStream(entry);
                        loadFrom(inputStream, (int) entry.getSize());
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (zipFile2 != null) {
                            try {
                                zipFile2.close();
                                return;
                            } catch (Exception e) {
                                return;
                            }
                        }
                        return;
                    }
                    throw new DexException("Expected classes.dex in " + file);
                } catch (Throwable th2) {
                    th = th2;
                    zipFile = zipFile2;
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (Exception e2) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (zipFile != null) {
                    zipFile.close();
                }
                throw th;
            }
        } else if (file.getName().endsWith(ShareConstants.DEX_SUFFIX)) {
            try {
                InputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                try {
                    loadFrom(bufferedInputStream, (int) file.length());
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e3) {
                        }
                    }
                } catch (Exception e4) {
                    th = e4;
                    inputStream = bufferedInputStream;
                    try {
                        throw new DexException(th);
                    } catch (Throwable th4) {
                        th = th4;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception e5) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    inputStream = bufferedInputStream;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e6) {
                th = e6;
                throw new DexException(th);
            }
        } else {
            throw new DexException("unknown output extension: " + file);
        }
    }

    private static void checkBounds(int i, int i2) {
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException("index:" + i + ", length=" + i2);
        }
    }

    private void loadFrom(InputStream inputStream) throws IOException {
        loadFrom(inputStream, 0);
    }

    private void loadFrom(InputStream inputStream, int i) throws IOException {
        this.data = ByteBuffer.wrap(FileUtils.readStream(inputStream, i));
        this.data.order(ByteOrder.LITTLE_ENDIAN);
        this.tableOfContents.readFrom(this);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.data.array());
        outputStream.flush();
    }

    public void writeTo(File file) throws IOException {
        Throwable e;
        OutputStream bufferedOutputStream;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            try {
                writeTo(bufferedOutputStream);
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (Exception e2) {
                    }
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    throw new DexException(e);
                } catch (Throwable th) {
                    e = th;
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (Exception e4) {
                        }
                    }
                    throw e;
                }
            }
        } catch (Exception e5) {
            e = e5;
            bufferedOutputStream = null;
            throw new DexException(e);
        } catch (Throwable th2) {
            e = th2;
            bufferedOutputStream = null;
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            throw e;
        }
    }

    public TableOfContents getTableOfContents() {
        return this.tableOfContents;
    }

    public Section openSection(int i) {
        if (i < 0 || i >= this.data.capacity()) {
            throw new IllegalArgumentException("position=" + i + " length=" + this.data.capacity());
        }
        ByteBuffer duplicate = this.data.duplicate();
        duplicate.order(ByteOrder.LITTLE_ENDIAN);
        duplicate.position(i);
        duplicate.limit(this.data.capacity());
        return new Section("temp-section", duplicate);
    }

    public Section openSection(com.tencent.tinker.android.dex.TableOfContents.Section section) {
        int i = section.off;
        if (i < 0 || i >= this.data.capacity()) {
            throw new IllegalArgumentException("position=" + i + " length=" + this.data.capacity());
        }
        ByteBuffer duplicate = this.data.duplicate();
        duplicate.order(ByteOrder.LITTLE_ENDIAN);
        duplicate.position(i);
        duplicate.limit(i + section.byteCount);
        return new Section("section", duplicate);
    }

    public Section appendSection(int i, String str) {
        int i2 = this.nextSectionStart + i;
        ByteBuffer duplicate = this.data.duplicate();
        duplicate.order(ByteOrder.LITTLE_ENDIAN);
        duplicate.position(this.nextSectionStart);
        duplicate.limit(i2);
        Section section = new Section(str, duplicate);
        this.nextSectionStart = i2;
        return section;
    }

    public int getLength() {
        return this.data.capacity();
    }

    public int getNextSectionStart() {
        return this.nextSectionStart;
    }

    public byte[] getBytes() {
        ByteBuffer duplicate = this.data.duplicate();
        byte[] bArr = new byte[duplicate.capacity()];
        duplicate.position(0);
        duplicate.get(bArr);
        return bArr;
    }

    public List<String> strings() {
        return this.strings;
    }

    public List<Integer> typeIds() {
        return this.typeIds;
    }

    public List<String> typeNames() {
        return this.typeNames;
    }

    public List<ProtoId> protoIds() {
        return this.protoIds;
    }

    public List<FieldId> fieldIds() {
        return this.fieldIds;
    }

    public List<MethodId> methodIds() {
        return this.methodIds;
    }

    public List<ClassDef> classDefs() {
        return this.classDefs;
    }

    public Iterable<ClassDef> classDefIterable() {
        return new a();
    }

    public ClassData readClassData(ClassDef classDef) {
        int i = classDef.classDataOffset;
        if (i != 0) {
            return openSection(i).readClassData();
        }
        throw new IllegalArgumentException("offset == 0");
    }

    public Code readCode(Method method) {
        int i = method.codeOffset;
        if (i != 0) {
            return openSection(i).readCode();
        }
        throw new IllegalArgumentException("offset == 0");
    }

    public byte[] computeSignature(boolean z) {
        if (this.signature != null && !z) {
            return this.signature;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            byte[] bArr = new byte[8192];
            ByteBuffer duplicate = this.data.duplicate();
            duplicate.limit(duplicate.capacity());
            duplicate.position(32);
            while (duplicate.hasRemaining()) {
                int min = Math.min(bArr.length, duplicate.remaining());
                duplicate.get(bArr, 0, min);
                instance.update(bArr, 0, min);
            }
            byte[] digest = instance.digest();
            this.signature = digest;
            return digest;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        }
    }

    private String bytesToHexString(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length << 1);
        for (byte u1 : bArr) {
            stringBuilder.append(Hex.u1(u1));
        }
        return stringBuilder.toString();
    }

    public int computeChecksum() throws IOException {
        Adler32 adler32 = new Adler32();
        byte[] bArr = new byte[8192];
        ByteBuffer duplicate = this.data.duplicate();
        duplicate.limit(duplicate.capacity());
        duplicate.position(12);
        while (duplicate.hasRemaining()) {
            int min = Math.min(bArr.length, duplicate.remaining());
            duplicate.get(bArr, 0, min);
            adler32.update(bArr, 0, min);
        }
        return (int) adler32.getValue();
    }

    public void writeHashes() throws IOException {
        openSection(12).write(computeSignature(true));
        openSection(8).writeInt(computeChecksum());
    }

    public int nameIndexFromFieldIndex(int i) {
        checkBounds(i, this.tableOfContents.fieldIds.size);
        return this.data.getInt(((this.tableOfContents.fieldIds.off + (i * 8)) + 2) + 2);
    }

    public int findStringIndex(String str) {
        return Collections.binarySearch(this.strings, str);
    }

    public int findTypeIndex(String str) {
        return Collections.binarySearch(this.typeNames, str);
    }

    public int findFieldIndex(FieldId fieldId) {
        return Collections.binarySearch(this.fieldIds, fieldId);
    }

    public int findMethodIndex(MethodId methodId) {
        return Collections.binarySearch(this.methodIds, methodId);
    }

    public int findClassDefIndexFromTypeIndex(int i) {
        checkBounds(i, this.tableOfContents.typeIds.size);
        if (!this.tableOfContents.classDefs.exists()) {
            return -1;
        }
        for (int i2 = 0; i2 < this.tableOfContents.classDefs.size; i2++) {
            if (typeIndexFromClassDefIndex(i2) == i) {
                return i2;
            }
        }
        return -1;
    }

    public int typeIndexFromFieldIndex(int i) {
        checkBounds(i, this.tableOfContents.fieldIds.size);
        return this.data.getShort((this.tableOfContents.fieldIds.off + (i * 8)) + 2) & 65535;
    }

    public int declaringClassIndexFromMethodIndex(int i) {
        checkBounds(i, this.tableOfContents.methodIds.size);
        return this.data.getShort(this.tableOfContents.methodIds.off + (i * 8)) & 65535;
    }

    public int nameIndexFromMethodIndex(int i) {
        checkBounds(i, this.tableOfContents.methodIds.size);
        return this.data.getInt(((this.tableOfContents.methodIds.off + (i * 8)) + 2) + 2);
    }

    public short[] parameterTypeIndicesFromMethodIndex(int i) {
        checkBounds(i, this.tableOfContents.methodIds.size);
        int i2 = this.data.getShort((this.tableOfContents.methodIds.off + (i * 8)) + 2) & 65535;
        checkBounds(i2, this.tableOfContents.protoIds.size);
        i2 = this.data.getInt((((i2 * 12) + this.tableOfContents.protoIds.off) + 4) + 4);
        if (i2 == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        int i3 = this.data.getInt(i2);
        if (i3 <= 0) {
            throw new AssertionError("Unexpected parameter type list size: " + i3);
        }
        int i4 = i2 + 4;
        short[] sArr = new short[i3];
        for (int i5 = 0; i5 < i3; i5++) {
            sArr[i5] = this.data.getShort(i4);
            i4 += 2;
        }
        return sArr;
    }

    public short[] parameterTypeIndicesFromMethodId(MethodId methodId) {
        int i = methodId.protoIndex & 65535;
        checkBounds(i, this.tableOfContents.protoIds.size);
        i = this.data.getInt((((i * 12) + this.tableOfContents.protoIds.off) + 4) + 4);
        if (i == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        int i2 = this.data.getInt(i);
        if (i2 <= 0) {
            throw new AssertionError("Unexpected parameter type list size: " + i2);
        }
        int i3 = i + 4;
        short[] sArr = new short[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            sArr[i4] = this.data.getShort(i3);
            i3 += 2;
        }
        return sArr;
    }

    public int returnTypeIndexFromMethodIndex(int i) {
        checkBounds(i, this.tableOfContents.methodIds.size);
        int i2 = this.data.getShort((this.tableOfContents.methodIds.off + (i * 8)) + 2) & 65535;
        checkBounds(i2, this.tableOfContents.protoIds.size);
        return this.data.getInt(((i2 * 12) + this.tableOfContents.protoIds.off) + 4);
    }

    public int descriptorIndexFromTypeIndex(int i) {
        checkBounds(i, this.tableOfContents.typeIds.size);
        return this.data.getInt(this.tableOfContents.typeIds.off + (i * 4));
    }

    public int typeIndexFromClassDefIndex(int i) {
        checkBounds(i, this.tableOfContents.classDefs.size);
        return this.data.getInt(this.tableOfContents.classDefs.off + (i * 32));
    }

    public int annotationDirectoryOffsetFromClassDefIndex(int i) {
        checkBounds(i, this.tableOfContents.classDefs.size);
        return this.data.getInt((((((this.tableOfContents.classDefs.off + (i * 32)) + 4) + 4) + 4) + 4) + 4);
    }

    public short[] interfaceTypeIndicesFromClassDefIndex(int i) {
        checkBounds(i, this.tableOfContents.classDefs.size);
        int i2 = this.data.getInt((((this.tableOfContents.classDefs.off + (i * 32)) + 4) + 4) + 4);
        if (i2 == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        int i3 = this.data.getInt(i2);
        if (i3 <= 0) {
            throw new AssertionError("Unexpected interfaces list size: " + i3);
        }
        int i4 = i2 + 4;
        short[] sArr = new short[i3];
        for (int i5 = 0; i5 < i3; i5++) {
            sArr[i5] = this.data.getShort(i4);
            i4 += 2;
        }
        return sArr;
    }

    public short[] interfaceTypeIndicesFromClassDef(ClassDef classDef) {
        int i = this.data.getInt(((classDef.off + 4) + 4) + 4);
        if (i == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        int i2 = this.data.getInt(i);
        if (i2 <= 0) {
            throw new AssertionError("Unexpected interfaces list size: " + i2);
        }
        int i3 = i + 4;
        short[] sArr = new short[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            sArr[i4] = this.data.getShort(i3);
            i3 += 2;
        }
        return sArr;
    }
}
