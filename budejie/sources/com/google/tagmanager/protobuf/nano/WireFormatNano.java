package com.google.tagmanager.protobuf.nano;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class WireFormatNano {
    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    public static final Boolean[] EMPTY_BOOLEAN_REF_ARRAY = new Boolean[0];
    public static final byte[] EMPTY_BYTES = new byte[0];
    public static final byte[][] EMPTY_BYTES_ARRAY = new byte[0][];
    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    public static final Double[] EMPTY_DOUBLE_REF_ARRAY = new Double[0];
    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    public static final Float[] EMPTY_FLOAT_REF_ARRAY = new Float[0];
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    public static final Integer[] EMPTY_INT_REF_ARRAY = new Integer[0];
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    public static final Long[] EMPTY_LONG_REF_ARRAY = new Long[0];
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    static final int MESSAGE_SET_ITEM = 1;
    static final int MESSAGE_SET_ITEM_END_TAG = makeTag(1, 4);
    static final int MESSAGE_SET_ITEM_TAG = makeTag(1, 3);
    static final int MESSAGE_SET_MESSAGE = 3;
    static final int MESSAGE_SET_MESSAGE_TAG = makeTag(3, 2);
    static final int MESSAGE_SET_TYPE_ID = 2;
    static final int MESSAGE_SET_TYPE_ID_TAG = makeTag(2, 0);
    static final int TAG_TYPE_BITS = 3;
    static final int TAG_TYPE_MASK = 7;
    static final int WIRETYPE_END_GROUP = 4;
    static final int WIRETYPE_FIXED32 = 5;
    static final int WIRETYPE_FIXED64 = 1;
    static final int WIRETYPE_LENGTH_DELIMITED = 2;
    static final int WIRETYPE_START_GROUP = 3;
    static final int WIRETYPE_VARINT = 0;

    private WireFormatNano() {
    }

    static int getTagWireType(int i) {
        return i & 7;
    }

    public static int getTagFieldNumber(int i) {
        return i >>> 3;
    }

    static int makeTag(int i, int i2) {
        return (i << 3) | i2;
    }

    public static boolean parseUnknownField(CodedInputByteBufferNano codedInputByteBufferNano, int i) throws IOException {
        return codedInputByteBufferNano.skipField(i);
    }

    public static boolean storeUnknownField(List<UnknownFieldData> list, CodedInputByteBufferNano codedInputByteBufferNano, int i) throws IOException {
        int position = codedInputByteBufferNano.getPosition();
        boolean skipField = codedInputByteBufferNano.skipField(i);
        list.add(new UnknownFieldData(i, codedInputByteBufferNano.getData(position, codedInputByteBufferNano.getPosition() - position)));
        return skipField;
    }

    public static final int getRepeatedFieldArrayLength(CodedInputByteBufferNano codedInputByteBufferNano, int i) throws IOException {
        int i2 = 1;
        int position = codedInputByteBufferNano.getPosition();
        codedInputByteBufferNano.skipField(i);
        while (codedInputByteBufferNano.getBytesUntilLimit() > 0 && codedInputByteBufferNano.readTag() == i) {
            codedInputByteBufferNano.skipField(i);
            i2++;
        }
        codedInputByteBufferNano.rewindToPosition(position);
        return i2;
    }

    public static <T> T getExtension(Extension<T> extension, List<UnknownFieldData> list) {
        if (list == null) {
            return null;
        }
        List<UnknownFieldData> arrayList = new ArrayList();
        for (UnknownFieldData unknownFieldData : list) {
            if (getTagFieldNumber(unknownFieldData.tag) == extension.fieldNumber) {
                arrayList.add(unknownFieldData);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        if (extension.isRepeatedField) {
            List arrayList2 = new ArrayList(arrayList.size());
            for (UnknownFieldData unknownFieldData2 : arrayList) {
                arrayList2.add(readData(extension.fieldType, unknownFieldData2.bytes));
            }
            return extension.listType.cast(arrayList2);
        }
        return readData(extension.fieldType, ((UnknownFieldData) arrayList.get(arrayList.size() - 1)).bytes);
    }

    private static <T> T readData(Class<T> cls, byte[] bArr) {
        if (bArr.length == 0) {
            return null;
        }
        CodedInputByteBufferNano newInstance = CodedInputByteBufferNano.newInstance(bArr);
        try {
            if (cls == String.class) {
                return cls.cast(newInstance.readString());
            }
            if (cls == Integer.class) {
                return cls.cast(Integer.valueOf(newInstance.readInt32()));
            }
            if (cls == Long.class) {
                return cls.cast(Long.valueOf(newInstance.readInt64()));
            }
            if (cls == Boolean.class) {
                return cls.cast(Boolean.valueOf(newInstance.readBool()));
            }
            if (cls == Float.class) {
                return cls.cast(Float.valueOf(newInstance.readFloat()));
            }
            if (cls == Double.class) {
                return cls.cast(Double.valueOf(newInstance.readDouble()));
            }
            if (cls == byte[].class) {
                return cls.cast(newInstance.readBytes());
            }
            if (MessageNano.class.isAssignableFrom(cls)) {
                MessageNano messageNano = (MessageNano) cls.newInstance();
                newInstance.readMessage(messageNano);
                return cls.cast(messageNano);
            }
            throw new IllegalArgumentException("Unhandled extension field type: " + cls);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Error creating instance of class " + cls, e);
        } catch (Throwable e2) {
            throw new IllegalArgumentException("Error creating instance of class " + cls, e2);
        } catch (Throwable e22) {
            throw new IllegalArgumentException("Error reading extension field", e22);
        }
    }

    public static <T> void setExtension(Extension<T> extension, T t, List<UnknownFieldData> list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (extension.fieldNumber == getTagFieldNumber(((UnknownFieldData) it.next()).tag)) {
                it.remove();
            }
        }
        if (t != null) {
            if (t instanceof List) {
                for (Object write : (List) t) {
                    list.add(write(extension.fieldNumber, write));
                }
                return;
            }
            list.add(write(extension.fieldNumber, t));
        }
    }

    private static UnknownFieldData write(int i, Object obj) {
        Class cls = obj.getClass();
        try {
            byte[] bArr;
            int makeTag;
            if (cls == String.class) {
                String str = (String) obj;
                bArr = new byte[CodedOutputByteBufferNano.computeStringSizeNoTag(str)];
                CodedOutputByteBufferNano.newInstance(bArr).writeStringNoTag(str);
                makeTag = makeTag(i, 2);
            } else if (cls == Integer.class) {
                Integer num = (Integer) obj;
                bArr = new byte[CodedOutputByteBufferNano.computeInt32SizeNoTag(num.intValue())];
                CodedOutputByteBufferNano.newInstance(bArr).writeInt32NoTag(num.intValue());
                makeTag = makeTag(i, 0);
            } else if (cls == Long.class) {
                Long l = (Long) obj;
                bArr = new byte[CodedOutputByteBufferNano.computeInt64SizeNoTag(l.longValue())];
                CodedOutputByteBufferNano.newInstance(bArr).writeInt64NoTag(l.longValue());
                makeTag = makeTag(i, 0);
            } else if (cls == Boolean.class) {
                Boolean bool = (Boolean) obj;
                bArr = new byte[CodedOutputByteBufferNano.computeBoolSizeNoTag(bool.booleanValue())];
                CodedOutputByteBufferNano.newInstance(bArr).writeBoolNoTag(bool.booleanValue());
                makeTag = makeTag(i, 0);
            } else if (cls == Float.class) {
                Float f = (Float) obj;
                bArr = new byte[CodedOutputByteBufferNano.computeFloatSizeNoTag(f.floatValue())];
                CodedOutputByteBufferNano.newInstance(bArr).writeFloatNoTag(f.floatValue());
                makeTag = makeTag(i, 5);
            } else if (cls == Double.class) {
                Double d = (Double) obj;
                bArr = new byte[CodedOutputByteBufferNano.computeDoubleSizeNoTag(d.doubleValue())];
                CodedOutputByteBufferNano.newInstance(bArr).writeDoubleNoTag(d.doubleValue());
                makeTag = makeTag(i, 1);
            } else if (cls == byte[].class) {
                byte[] bArr2 = (byte[]) obj;
                bArr = new byte[CodedOutputByteBufferNano.computeByteArraySizeNoTag(bArr2)];
                CodedOutputByteBufferNano.newInstance(bArr).writeByteArrayNoTag(bArr2);
                makeTag = makeTag(i, 2);
            } else if (MessageNano.class.isAssignableFrom(cls)) {
                MessageNano messageNano = (MessageNano) obj;
                makeTag = messageNano.getSerializedSize();
                bArr = new byte[(CodedOutputByteBufferNano.computeRawVarint32Size(makeTag) + makeTag)];
                CodedOutputByteBufferNano newInstance = CodedOutputByteBufferNano.newInstance(bArr);
                newInstance.writeRawVarint32(makeTag);
                newInstance.writeRawBytes(MessageNano.toByteArray(messageNano));
                makeTag = makeTag(i, 2);
            } else {
                throw new IllegalArgumentException("Unhandled extension field type: " + cls);
            }
            return new UnknownFieldData(makeTag, bArr);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int computeWireSize(List<UnknownFieldData> list) {
        if (list == null) {
            return 0;
        }
        int i = 0;
        for (UnknownFieldData unknownFieldData : list) {
            i = unknownFieldData.bytes.length + (i + CodedOutputByteBufferNano.computeRawVarint32Size(unknownFieldData.tag));
        }
        return i;
    }

    public static void writeUnknownFields(List<UnknownFieldData> list, CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        if (list != null) {
            for (UnknownFieldData unknownFieldData : list) {
                codedOutputByteBufferNano.writeTag(getTagFieldNumber(unknownFieldData.tag), getTagWireType(unknownFieldData.tag));
                codedOutputByteBufferNano.writeRawBytes(unknownFieldData.bytes);
            }
        }
    }
}
