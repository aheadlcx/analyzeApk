package com.tencent.tinker.android.dex.io;

import com.tencent.tinker.android.dex.Annotation;
import com.tencent.tinker.android.dex.AnnotationSet;
import com.tencent.tinker.android.dex.AnnotationSetRefList;
import com.tencent.tinker.android.dex.AnnotationsDirectory;
import com.tencent.tinker.android.dex.ClassData;
import com.tencent.tinker.android.dex.ClassData.Field;
import com.tencent.tinker.android.dex.ClassData.Method;
import com.tencent.tinker.android.dex.ClassDef;
import com.tencent.tinker.android.dex.Code;
import com.tencent.tinker.android.dex.Code.CatchHandler;
import com.tencent.tinker.android.dex.Code.Try;
import com.tencent.tinker.android.dex.DebugInfoItem;
import com.tencent.tinker.android.dex.DexException;
import com.tencent.tinker.android.dex.EncodedValue;
import com.tencent.tinker.android.dex.EncodedValueReader;
import com.tencent.tinker.android.dex.FieldId;
import com.tencent.tinker.android.dex.Leb128;
import com.tencent.tinker.android.dex.MethodId;
import com.tencent.tinker.android.dex.Mutf8;
import com.tencent.tinker.android.dex.ProtoId;
import com.tencent.tinker.android.dex.SizeOf;
import com.tencent.tinker.android.dex.StringData;
import com.tencent.tinker.android.dex.TypeList;
import com.tencent.tinker.android.dex.util.ByteInput;
import com.tencent.tinker.android.dex.util.ByteOutput;
import java.io.ByteArrayOutputStream;
import java.io.UTFDataFormatException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class DexDataBuffer implements ByteInput, ByteOutput {
    public static final int DEFAULT_BUFFER_SIZE = 512;
    private static final CatchHandler[] EMPTY_CATCHHANDLER_ARRAY = new CatchHandler[0];
    private static final short[] EMPTY_SHORT_ARRAY = new short[0];
    private static final Try[] EMPTY_TRY_ARRAY = new Try[0];
    private ByteBuffer data;
    private int dataBound;
    private boolean isResizeAllowed;

    public DexDataBuffer() {
        this.data = ByteBuffer.allocate(512);
        this.data.order(ByteOrder.LITTLE_ENDIAN);
        this.dataBound = this.data.position();
        this.data.limit(this.data.capacity());
        this.isResizeAllowed = true;
    }

    public DexDataBuffer(ByteBuffer byteBuffer) {
        this.data = byteBuffer;
        this.data.order(ByteOrder.LITTLE_ENDIAN);
        this.dataBound = byteBuffer.limit();
        this.isResizeAllowed = false;
    }

    public DexDataBuffer(ByteBuffer byteBuffer, boolean z) {
        this.data = byteBuffer;
        this.data.order(ByteOrder.LITTLE_ENDIAN);
        this.dataBound = byteBuffer.limit();
        this.isResizeAllowed = z;
    }

    public int position() {
        return this.data.position();
    }

    public void position(int i) {
        this.data.position(i);
    }

    public int available() {
        return this.dataBound - this.data.position();
    }

    private void ensureBufferSize(int i) {
        if (this.data.position() + i > this.data.limit() && this.isResizeAllowed) {
            Object array = this.data.array();
            Object obj = new byte[((array.length + i) + (array.length >> 1))];
            System.arraycopy(array, 0, obj, 0, this.data.position());
            int position = this.data.position();
            this.data = ByteBuffer.wrap(obj);
            this.data.order(ByteOrder.LITTLE_ENDIAN);
            this.data.position(position);
            this.data.limit(this.data.capacity());
        }
    }

    public byte[] array() {
        Object obj = new byte[this.dataBound];
        System.arraycopy(this.data.array(), 0, obj, 0, this.dataBound);
        return obj;
    }

    public byte readByte() {
        return this.data.get();
    }

    public int readUnsignedByte() {
        return readByte() & 255;
    }

    public short readShort() {
        return this.data.getShort();
    }

    public int readUnsignedShort() {
        return readShort() & 65535;
    }

    public int readInt() {
        return this.data.getInt();
    }

    public byte[] readByteArray(int i) {
        byte[] bArr = new byte[i];
        this.data.get(bArr);
        return bArr;
    }

    public short[] readShortArray(int i) {
        if (i == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        short[] sArr = new short[i];
        for (int i2 = 0; i2 < i; i2++) {
            sArr[i2] = readShort();
        }
        return sArr;
    }

    public int readUleb128() {
        return Leb128.readUnsignedLeb128(this);
    }

    public int readUleb128p1() {
        return Leb128.readUnsignedLeb128(this) - 1;
    }

    public int readSleb128() {
        return Leb128.readSignedLeb128(this);
    }

    public StringData readStringData() {
        int position = this.data.position();
        try {
            int readUleb128 = readUleb128();
            String decode = Mutf8.decode(this, new char[readUleb128]);
            if (decode.length() == readUleb128) {
                return new StringData(position, decode);
            }
            throw new DexException("Declared length " + readUleb128 + " doesn't match decoded length of " + decode.length());
        } catch (Throwable e) {
            throw new DexException(e);
        }
    }

    public TypeList readTypeList() {
        return new TypeList(this.data.position(), readShortArray(readInt()));
    }

    public FieldId readFieldId() {
        return new FieldId(this.data.position(), readUnsignedShort(), readUnsignedShort(), readInt());
    }

    public MethodId readMethodId() {
        return new MethodId(this.data.position(), readUnsignedShort(), readUnsignedShort(), readInt());
    }

    public ProtoId readProtoId() {
        return new ProtoId(this.data.position(), readInt(), readInt(), readInt());
    }

    public ClassDef readClassDef() {
        return new ClassDef(position(), readInt(), readInt(), readInt(), readInt(), readInt(), readInt(), readInt(), readInt());
    }

    public Code readCode() {
        CatchHandler[] readCatchHandlers;
        Try[] readTries;
        int position = this.data.position();
        int readUnsignedShort = readUnsignedShort();
        int readUnsignedShort2 = readUnsignedShort();
        int readUnsignedShort3 = readUnsignedShort();
        int readUnsignedShort4 = readUnsignedShort();
        int readInt = readInt();
        short[] readShortArray = readShortArray(readInt());
        if (readUnsignedShort4 > 0) {
            if ((readShortArray.length & 1) == 1) {
                skip(2);
            }
            int position2 = this.data.position();
            skip(readUnsignedShort4 * 8);
            readCatchHandlers = readCatchHandlers();
            int position3 = this.data.position();
            this.data.position(position2);
            readTries = readTries(readUnsignedShort4, readCatchHandlers);
            this.data.position(position3);
        } else {
            readTries = EMPTY_TRY_ARRAY;
            readCatchHandlers = EMPTY_CATCHHANDLER_ARRAY;
        }
        return new Code(position, readUnsignedShort, readUnsignedShort2, readUnsignedShort3, readInt, readShortArray, readTries, readCatchHandlers);
    }

    private CatchHandler[] readCatchHandlers() {
        int position = this.data.position();
        int readUleb128 = readUleb128();
        CatchHandler[] catchHandlerArr = new CatchHandler[readUleb128];
        for (int i = 0; i < readUleb128; i++) {
            catchHandlerArr[i] = readCatchHandler(this.data.position() - position);
        }
        return catchHandlerArr;
    }

    private Try[] readTries(int i, CatchHandler[] catchHandlerArr) {
        Try[] tryArr = new Try[i];
        for (int i2 = 0; i2 < i; i2++) {
            tryArr[i2] = new Try(readInt(), readUnsignedShort(), findCatchHandlerIndex(catchHandlerArr, readUnsignedShort()));
        }
        return tryArr;
    }

    private int findCatchHandlerIndex(CatchHandler[] catchHandlerArr, int i) {
        for (int i2 = 0; i2 < catchHandlerArr.length; i2++) {
            if (catchHandlerArr[i2].offset == i) {
                return i2;
            }
        }
        throw new IllegalArgumentException();
    }

    private CatchHandler readCatchHandler(int i) {
        int readSleb128 = readSleb128();
        int abs = Math.abs(readSleb128);
        int[] iArr = new int[abs];
        int[] iArr2 = new int[abs];
        for (int i2 = 0; i2 < abs; i2++) {
            iArr[i2] = readUleb128();
            iArr2[i2] = readUleb128();
        }
        return new CatchHandler(iArr, iArr2, readSleb128 <= 0 ? readUleb128() : -1, i);
    }

    public DebugInfoItem readDebugInfoItem() {
        final ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        int position = this.data.position();
        int readUleb128 = readUleb128();
        int readUleb1282 = readUleb128();
        int[] iArr = new int[readUleb1282];
        for (int i = 0; i < readUleb1282; i++) {
            iArr[i] = readUleb128p1();
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream(64);
            ByteOutput anonymousClass1 = new ByteOutput(this) {
                final /* synthetic */ DexDataBuffer b;

                public void writeByte(int i) {
                    byteArrayOutputStream.write(i);
                }
            };
            while (true) {
                byte readByte = readByte();
                byteArrayOutputStream.write(readByte);
                switch (readByte) {
                    case (byte) 0:
                        DebugInfoItem debugInfoItem = new DebugInfoItem(position, readUleb128, iArr, byteArrayOutputStream.toByteArray());
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception e) {
                            }
                        }
                        return debugInfoItem;
                    case (byte) 1:
                        Leb128.writeUnsignedLeb128(anonymousClass1, readUleb128());
                        break;
                    case (byte) 2:
                        try {
                            Leb128.writeSignedLeb128(anonymousClass1, readSleb128());
                            break;
                        } catch (Throwable th2) {
                            th = th2;
                            break;
                        }
                    case (byte) 3:
                    case (byte) 4:
                        Leb128.writeUnsignedLeb128(anonymousClass1, readUleb128());
                        Leb128.writeUnsignedLeb128p1(anonymousClass1, readUleb128p1());
                        Leb128.writeUnsignedLeb128p1(anonymousClass1, readUleb128p1());
                        if (readByte != (byte) 4) {
                            break;
                        }
                        Leb128.writeUnsignedLeb128p1(anonymousClass1, readUleb128p1());
                        break;
                    case (byte) 5:
                    case (byte) 6:
                        Leb128.writeUnsignedLeb128(anonymousClass1, readUleb128());
                        break;
                    case (byte) 9:
                        Leb128.writeUnsignedLeb128p1(anonymousClass1, readUleb128p1());
                        break;
                    default:
                        break;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (Exception e2) {
                }
            }
            throw th;
        }
    }

    public ClassData readClassData() {
        return new ClassData(this.data.position(), readFields(readUleb128()), readFields(readUleb128()), readMethods(readUleb128()), readMethods(readUleb128()));
    }

    private Field[] readFields(int i) {
        int i2 = 0;
        Field[] fieldArr = new Field[i];
        int i3 = 0;
        while (i2 < i) {
            i3 += readUleb128();
            fieldArr[i2] = new Field(i3, readUleb128());
            i2++;
        }
        return fieldArr;
    }

    private Method[] readMethods(int i) {
        int i2 = 0;
        Method[] methodArr = new Method[i];
        int i3 = 0;
        while (i2 < i) {
            i3 += readUleb128();
            methodArr[i2] = new Method(i3, readUleb128(), readUleb128());
            i2++;
        }
        return methodArr;
    }

    private byte[] getBytesFrom(int i) {
        byte[] bArr = new byte[(this.data.position() - i)];
        this.data.position(i);
        this.data.get(bArr);
        return bArr;
    }

    public Annotation readAnnotation() {
        int position = this.data.position();
        byte readByte = readByte();
        int position2 = this.data.position();
        new EncodedValueReader((ByteInput) this, 29).skipValue();
        return new Annotation(position, readByte, new EncodedValue(position2, getBytesFrom(position2)));
    }

    public AnnotationSet readAnnotationSet() {
        int position = this.data.position();
        int readInt = readInt();
        int[] iArr = new int[readInt];
        for (int i = 0; i < readInt; i++) {
            iArr[i] = readInt();
        }
        return new AnnotationSet(position, iArr);
    }

    public AnnotationSetRefList readAnnotationSetRefList() {
        int position = this.data.position();
        int readInt = readInt();
        int[] iArr = new int[readInt];
        for (int i = 0; i < readInt; i++) {
            iArr[i] = readInt();
        }
        return new AnnotationSetRefList(position, iArr);
    }

    public AnnotationsDirectory readAnnotationsDirectory() {
        int i;
        int position = this.data.position();
        int readInt = readInt();
        int readInt2 = readInt();
        int readInt3 = readInt();
        int readInt4 = readInt();
        int[][] iArr = (int[][]) Array.newInstance(Integer.TYPE, new int[]{readInt2, 2});
        for (i = 0; i < readInt2; i++) {
            iArr[i][0] = readInt();
            iArr[i][1] = readInt();
        }
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{readInt3, 2});
        for (i = 0; i < readInt3; i++) {
            iArr2[i][0] = readInt();
            iArr2[i][1] = readInt();
        }
        int[][] iArr3 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{readInt4, 2});
        for (i = 0; i < readInt4; i++) {
            iArr3[i][0] = readInt();
            iArr3[i][1] = readInt();
        }
        return new AnnotationsDirectory(position, readInt, iArr, iArr2, iArr3);
    }

    public EncodedValue readEncodedArray() {
        int position = this.data.position();
        new EncodedValueReader((ByteInput) this, 28).skipValue();
        return new EncodedValue(position, getBytesFrom(position));
    }

    public void skip(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        this.data.position(this.data.position() + i);
    }

    public void skipWithAutoExpand(int i) {
        ensureBufferSize(i * 1);
        skip(i);
    }

    public void alignToFourBytes() {
        this.data.position((this.data.position() + 3) & -4);
    }

    public void alignToFourBytesWithZeroFill() {
        ensureBufferSize((SizeOf.roundToTimesOfFour(this.data.position()) - this.data.position()) * 1);
        while ((this.data.position() & 3) != 0) {
            this.data.put((byte) 0);
        }
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }

    public void writeByte(int i) {
        ensureBufferSize(1);
        this.data.put((byte) i);
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }

    public void writeShort(short s) {
        ensureBufferSize(2);
        this.data.putShort(s);
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }

    public void writeUnsignedShort(int i) {
        short s = (short) i;
        if (i != (65535 & s)) {
            throw new IllegalArgumentException("Expected an unsigned short: " + i);
        }
        writeShort(s);
    }

    public void writeInt(int i) {
        ensureBufferSize(4);
        this.data.putInt(i);
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }

    public void write(byte[] bArr) {
        ensureBufferSize(bArr.length * 1);
        this.data.put(bArr);
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }

    public void write(short[] sArr) {
        ensureBufferSize(sArr.length * 2);
        for (short writeShort : sArr) {
            writeShort(writeShort);
        }
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }

    public void writeUleb128(int i) {
        Leb128.writeUnsignedLeb128(this, i);
    }

    public void writeUleb128p1(int i) {
        writeUleb128(i + 1);
    }

    public void writeSleb128(int i) {
        Leb128.writeSignedLeb128(this, i);
    }

    public int writeStringData(StringData stringData) {
        int position = this.data.position();
        try {
            writeUleb128(stringData.value.length());
            write(Mutf8.encode(stringData.value));
            writeByte(0);
            return position;
        } catch (UTFDataFormatException e) {
            throw new AssertionError(e);
        }
    }

    public int writeTypeList(TypeList typeList) {
        int position = this.data.position();
        short[] sArr = typeList.types;
        writeInt(sArr.length);
        for (short writeShort : sArr) {
            writeShort(writeShort);
        }
        return position;
    }

    public int writeFieldId(FieldId fieldId) {
        int position = this.data.position();
        writeUnsignedShort(fieldId.declaringClassIndex);
        writeUnsignedShort(fieldId.typeIndex);
        writeInt(fieldId.nameIndex);
        return position;
    }

    public int writeMethodId(MethodId methodId) {
        int position = this.data.position();
        writeUnsignedShort(methodId.declaringClassIndex);
        writeUnsignedShort(methodId.protoIndex);
        writeInt(methodId.nameIndex);
        return position;
    }

    public int writeProtoId(ProtoId protoId) {
        int position = this.data.position();
        writeInt(protoId.shortyIndex);
        writeInt(protoId.returnTypeIndex);
        writeInt(protoId.parametersOffset);
        return position;
    }

    public int writeClassDef(ClassDef classDef) {
        int position = this.data.position();
        writeInt(classDef.typeIndex);
        writeInt(classDef.accessFlags);
        writeInt(classDef.supertypeIndex);
        writeInt(classDef.interfacesOffset);
        writeInt(classDef.sourceFileIndex);
        writeInt(classDef.annotationsOffset);
        writeInt(classDef.classDataOffset);
        writeInt(classDef.staticValuesOffset);
        return position;
    }

    public int writeCode(Code code) {
        int position = this.data.position();
        writeUnsignedShort(code.registersSize);
        writeUnsignedShort(code.insSize);
        writeUnsignedShort(code.outsSize);
        writeUnsignedShort(code.tries.length);
        writeInt(code.debugInfoOffset);
        writeInt(code.instructions.length);
        write(code.instructions);
        if (code.tries.length > 0) {
            if ((code.instructions.length & 1) == 1) {
                writeShort((short) 0);
            }
            int position2 = this.data.position();
            skipWithAutoExpand(code.tries.length * 8);
            int[] writeCatchHandlers = writeCatchHandlers(code.catchHandlers);
            int position3 = this.data.position();
            this.data.position(position2);
            writeTries(code.tries, writeCatchHandlers);
            this.data.position(position3);
        }
        return position;
    }

    private int[] writeCatchHandlers(CatchHandler[] catchHandlerArr) {
        int position = this.data.position();
        writeUleb128(catchHandlerArr.length);
        int[] iArr = new int[catchHandlerArr.length];
        for (int i = 0; i < catchHandlerArr.length; i++) {
            iArr[i] = this.data.position() - position;
            writeCatchHandler(catchHandlerArr[i]);
        }
        return iArr;
    }

    private void writeCatchHandler(CatchHandler catchHandler) {
        int i = catchHandler.catchAllAddress;
        int[] iArr = catchHandler.typeIndexes;
        int[] iArr2 = catchHandler.addresses;
        if (i != -1) {
            writeSleb128(-iArr.length);
        } else {
            writeSleb128(iArr.length);
        }
        for (int i2 = 0; i2 < iArr.length; i2++) {
            writeUleb128(iArr[i2]);
            writeUleb128(iArr2[i2]);
        }
        if (i != -1) {
            writeUleb128(i);
        }
    }

    private void writeTries(Try[] tryArr, int[] iArr) {
        for (Try tryR : tryArr) {
            writeInt(tryR.startAddress);
            writeUnsignedShort(tryR.instructionCount);
            writeUnsignedShort(iArr[tryR.catchHandlerIndex]);
        }
    }

    public int writeDebugInfoItem(DebugInfoItem debugInfoItem) {
        int position = this.data.position();
        writeUleb128(debugInfoItem.lineStart);
        writeUleb128(r2);
        for (int writeUleb128p1 : debugInfoItem.parameterNames) {
            writeUleb128p1(writeUleb128p1);
        }
        write(debugInfoItem.infoSTM);
        return position;
    }

    public int writeClassData(ClassData classData) {
        int position = this.data.position();
        writeUleb128(classData.staticFields.length);
        writeUleb128(classData.instanceFields.length);
        writeUleb128(classData.directMethods.length);
        writeUleb128(classData.virtualMethods.length);
        writeFields(classData.staticFields);
        writeFields(classData.instanceFields);
        writeMethods(classData.directMethods);
        writeMethods(classData.virtualMethods);
        return position;
    }

    private void writeFields(Field[] fieldArr) {
        int i = 0;
        int length = fieldArr.length;
        int i2 = 0;
        while (i < length) {
            Field field = fieldArr[i];
            writeUleb128(field.fieldIndex - i2);
            i2 = field.fieldIndex;
            writeUleb128(field.accessFlags);
            i++;
        }
    }

    private void writeMethods(Method[] methodArr) {
        int i = 0;
        int length = methodArr.length;
        int i2 = 0;
        while (i < length) {
            Method method = methodArr[i];
            writeUleb128(method.methodIndex - i2);
            i2 = method.methodIndex;
            writeUleb128(method.accessFlags);
            writeUleb128(method.codeOffset);
            i++;
        }
    }

    public int writeAnnotation(Annotation annotation) {
        int position = this.data.position();
        writeByte(annotation.visibility);
        writeEncodedArray(annotation.encodedAnnotation);
        return position;
    }

    public int writeAnnotationSet(AnnotationSet annotationSet) {
        int position = this.data.position();
        writeInt(annotationSet.annotationOffsets.length);
        for (int writeInt : annotationSet.annotationOffsets) {
            writeInt(writeInt);
        }
        return position;
    }

    public int writeAnnotationSetRefList(AnnotationSetRefList annotationSetRefList) {
        int position = this.data.position();
        writeInt(annotationSetRefList.annotationSetRefItems.length);
        for (int writeInt : annotationSetRefList.annotationSetRefItems) {
            writeInt(writeInt);
        }
        return position;
    }

    public int writeAnnotationsDirectory(AnnotationsDirectory annotationsDirectory) {
        int position = this.data.position();
        writeInt(annotationsDirectory.classAnnotationsOffset);
        writeInt(annotationsDirectory.fieldAnnotations.length);
        writeInt(annotationsDirectory.methodAnnotations.length);
        writeInt(annotationsDirectory.parameterAnnotations.length);
        for (int[] iArr : annotationsDirectory.fieldAnnotations) {
            writeInt(iArr[0]);
            writeInt(iArr[1]);
        }
        for (int[] iArr2 : annotationsDirectory.methodAnnotations) {
            writeInt(iArr2[0]);
            writeInt(iArr2[1]);
        }
        for (int[] iArr22 : annotationsDirectory.parameterAnnotations) {
            writeInt(iArr22[0]);
            writeInt(iArr22[1]);
        }
        return position;
    }

    public int writeEncodedArray(EncodedValue encodedValue) {
        int position = this.data.position();
        write(encodedValue.data);
        return position;
    }
}
