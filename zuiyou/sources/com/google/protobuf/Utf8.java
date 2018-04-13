package com.google.protobuf;

import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.nio.ByteBuffer;

final class Utf8 {
    private static final long ASCII_MASK_LONG = -9187201950435737472L;
    public static final int COMPLETE = 0;
    public static final int MALFORMED = -1;
    static final int MAX_BYTES_PER_CHAR = 3;
    private static final int UNSAFE_COUNT_ASCII_THRESHOLD = 16;
    private static final Processor processor = (UnsafeProcessor.isAvailable() ? new UnsafeProcessor() : new SafeProcessor());

    static abstract class Processor {
        abstract int encodeUtf8(CharSequence charSequence, byte[] bArr, int i, int i2);

        abstract void encodeUtf8Direct(CharSequence charSequence, ByteBuffer byteBuffer);

        abstract int partialIsValidUtf8(int i, byte[] bArr, int i2, int i3);

        abstract int partialIsValidUtf8Direct(int i, ByteBuffer byteBuffer, int i2, int i3);

        Processor() {
        }

        final boolean isValidUtf8(byte[] bArr, int i, int i2) {
            return partialIsValidUtf8(0, bArr, i, i2) == 0;
        }

        final boolean isValidUtf8(ByteBuffer byteBuffer, int i, int i2) {
            return partialIsValidUtf8(0, byteBuffer, i, i2) == 0;
        }

        final int partialIsValidUtf8(int i, ByteBuffer byteBuffer, int i2, int i3) {
            if (byteBuffer.hasArray()) {
                int arrayOffset = byteBuffer.arrayOffset();
                return partialIsValidUtf8(i, byteBuffer.array(), arrayOffset + i2, arrayOffset + i3);
            } else if (byteBuffer.isDirect()) {
                return partialIsValidUtf8Direct(i, byteBuffer, i2, i3);
            } else {
                return partialIsValidUtf8Default(i, byteBuffer, i2, i3);
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        final int partialIsValidUtf8Default(int r8, java.nio.ByteBuffer r9, int r10, int r11) {
            /*
            r7 = this;
            r3 = -32;
            r4 = -96;
            r1 = -1;
            r6 = -65;
            if (r8 == 0) goto L_0x008f;
        L_0x0009:
            if (r10 < r11) goto L_0x000c;
        L_0x000b:
            return r8;
        L_0x000c:
            r5 = (byte) r8;
            if (r5 >= r3) goto L_0x001d;
        L_0x000f:
            r0 = -62;
            if (r5 < r0) goto L_0x001b;
        L_0x0013:
            r0 = r10 + 1;
            r2 = r9.get(r10);
            if (r2 <= r6) goto L_0x008e;
        L_0x001b:
            r8 = r1;
            goto L_0x000b;
        L_0x001d:
            r0 = -16;
            if (r5 >= r0) goto L_0x004c;
        L_0x0021:
            r0 = r8 >> 8;
            r0 = r0 ^ -1;
            r0 = (byte) r0;
            if (r0 != 0) goto L_0x0035;
        L_0x0028:
            r2 = r10 + 1;
            r0 = r9.get(r10);
            if (r2 < r11) goto L_0x0036;
        L_0x0030:
            r8 = com.google.protobuf.Utf8.incompleteStateFor(r5, r0);
            goto L_0x000b;
        L_0x0035:
            r2 = r10;
        L_0x0036:
            if (r0 > r6) goto L_0x004a;
        L_0x0038:
            if (r5 != r3) goto L_0x003c;
        L_0x003a:
            if (r0 < r4) goto L_0x004a;
        L_0x003c:
            r3 = -19;
            if (r5 != r3) goto L_0x0042;
        L_0x0040:
            if (r0 >= r4) goto L_0x004a;
        L_0x0042:
            r10 = r2 + 1;
            r0 = r9.get(r2);
            if (r0 <= r6) goto L_0x008f;
        L_0x004a:
            r8 = r1;
            goto L_0x000b;
        L_0x004c:
            r0 = r8 >> 8;
            r0 = r0 ^ -1;
            r2 = (byte) r0;
            r0 = 0;
            if (r2 != 0) goto L_0x0061;
        L_0x0054:
            r3 = r10 + 1;
            r2 = r9.get(r10);
            if (r3 < r11) goto L_0x0095;
        L_0x005c:
            r8 = com.google.protobuf.Utf8.incompleteStateFor(r5, r2);
            goto L_0x000b;
        L_0x0061:
            r0 = r8 >> 16;
            r0 = (byte) r0;
            r4 = r2;
            r3 = r10;
        L_0x0066:
            if (r0 != 0) goto L_0x0075;
        L_0x0068:
            r2 = r3 + 1;
            r0 = r9.get(r3);
            if (r2 < r11) goto L_0x0076;
        L_0x0070:
            r8 = com.google.protobuf.Utf8.incompleteStateFor(r5, r4, r0);
            goto L_0x000b;
        L_0x0075:
            r2 = r3;
        L_0x0076:
            if (r4 > r6) goto L_0x008b;
        L_0x0078:
            r3 = r5 << 28;
            r4 = r4 + 112;
            r3 = r3 + r4;
            r3 = r3 >> 30;
            if (r3 != 0) goto L_0x008b;
        L_0x0081:
            if (r0 > r6) goto L_0x008b;
        L_0x0083:
            r10 = r2 + 1;
            r0 = r9.get(r2);
            if (r0 <= r6) goto L_0x008f;
        L_0x008b:
            r8 = r1;
            goto L_0x000b;
        L_0x008e:
            r10 = r0;
        L_0x008f:
            r8 = partialIsValidUtf8(r9, r10, r11);
            goto L_0x000b;
        L_0x0095:
            r4 = r2;
            goto L_0x0066;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.Processor.partialIsValidUtf8Default(int, java.nio.ByteBuffer, int, int):int");
        }

        private static int partialIsValidUtf8(ByteBuffer byteBuffer, int i, int i2) {
            int access$200 = Utf8.estimateConsecutiveAscii(byteBuffer, i, i2) + i;
            while (access$200 < i2) {
                int i3 = access$200 + 1;
                access$200 = byteBuffer.get(access$200);
                if (access$200 >= 0) {
                    access$200 = i3;
                } else if (access$200 < -32) {
                    if (i3 >= i2) {
                        return access$200;
                    }
                    if (access$200 < -62 || byteBuffer.get(i3) > (byte) -65) {
                        return -1;
                    }
                    access$200 = i3 + 1;
                } else if (access$200 < -16) {
                    if (i3 >= i2 - 1) {
                        return Utf8.incompleteStateFor(byteBuffer, access$200, i3, i2 - i3);
                    }
                    r3 = i3 + 1;
                    r2 = byteBuffer.get(i3);
                    if (r2 > (byte) -65 || ((access$200 == -32 && r2 < (byte) -96) || ((access$200 == -19 && r2 >= (byte) -96) || byteBuffer.get(r3) > (byte) -65))) {
                        return -1;
                    }
                    access$200 = r3 + 1;
                } else if (i3 >= i2 - 2) {
                    return Utf8.incompleteStateFor(byteBuffer, access$200, i3, i2 - i3);
                } else {
                    r3 = i3 + 1;
                    r2 = byteBuffer.get(i3);
                    if (r2 <= (byte) -65 && (((access$200 << 28) + (r2 + 112)) >> 30) == 0) {
                        i3 = r3 + 1;
                        if (byteBuffer.get(r3) <= (byte) -65) {
                            access$200 = i3 + 1;
                            if (byteBuffer.get(i3) > (byte) -65) {
                            }
                        }
                    }
                    return -1;
                }
            }
            return 0;
        }

        final void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer) {
            if (byteBuffer.hasArray()) {
                int arrayOffset = byteBuffer.arrayOffset();
                byteBuffer.position(Utf8.encode(charSequence, byteBuffer.array(), byteBuffer.position() + arrayOffset, byteBuffer.remaining()) - arrayOffset);
            } else if (byteBuffer.isDirect()) {
                encodeUtf8Direct(charSequence, byteBuffer);
            } else {
                encodeUtf8Default(charSequence, byteBuffer);
            }
        }

        final void encodeUtf8Default(CharSequence charSequence, ByteBuffer byteBuffer) {
            int length = charSequence.length();
            int position = byteBuffer.position();
            int i = 0;
            while (i < length) {
                char charAt;
                try {
                    charAt = charSequence.charAt(i);
                    if (charAt >= '') {
                        break;
                    }
                    byteBuffer.put(position + i, (byte) charAt);
                    i++;
                } catch (IndexOutOfBoundsException e) {
                }
            }
            if (i == length) {
                byteBuffer.position(position + i);
                return;
            }
            position += i;
            while (i < length) {
                char charAt2 = charSequence.charAt(i);
                if (charAt2 < '') {
                    byteBuffer.put(position, (byte) charAt2);
                } else if (charAt2 < 'ࠀ') {
                    r2 = position + 1;
                    try {
                        byteBuffer.put(position, (byte) ((charAt2 >>> 6) | 192));
                        byteBuffer.put(r2, (byte) ((charAt2 & 63) | 128));
                        position = r2;
                    } catch (IndexOutOfBoundsException e2) {
                        position = r2;
                    }
                } else if (charAt2 < '?' || '?' < charAt2) {
                    r2 = position + 1;
                    byteBuffer.put(position, (byte) ((charAt2 >>> 12) | Opcodes.SHL_INT_LIT8));
                    position = r2 + 1;
                    byteBuffer.put(r2, (byte) (((charAt2 >>> 6) & 63) | 128));
                    byteBuffer.put(position, (byte) ((charAt2 & 63) | 128));
                } else {
                    if (i + 1 != length) {
                        i++;
                        charAt = charSequence.charAt(i);
                        if (Character.isSurrogatePair(charAt2, charAt)) {
                            int toCodePoint = Character.toCodePoint(charAt2, charAt);
                            int i2 = position + 1;
                            try {
                                byteBuffer.put(position, (byte) ((toCodePoint >>> 18) | 240));
                                r2 = i2 + 1;
                                byteBuffer.put(i2, (byte) (((toCodePoint >>> 12) & 63) | 128));
                                position = r2 + 1;
                                byteBuffer.put(r2, (byte) (((toCodePoint >>> 6) & 63) | 128));
                                byteBuffer.put(position, (byte) ((toCodePoint & 63) | 128));
                            } catch (IndexOutOfBoundsException e3) {
                                position = i2;
                            }
                        }
                    }
                    throw new UnpairedSurrogateException(i, length);
                }
                i++;
                position++;
            }
            byteBuffer.position(position);
            return;
            throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i) + " at index " + (Math.max(i, (position - byteBuffer.position()) + 1) + byteBuffer.position()));
        }
    }

    static final class SafeProcessor extends Processor {
        SafeProcessor() {
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        int partialIsValidUtf8(int r8, byte[] r9, int r10, int r11) {
            /*
            r7 = this;
            r3 = -32;
            r4 = -96;
            r1 = -1;
            r6 = -65;
            if (r8 == 0) goto L_0x0082;
        L_0x0009:
            if (r10 < r11) goto L_0x000c;
        L_0x000b:
            return r8;
        L_0x000c:
            r5 = (byte) r8;
            if (r5 >= r3) goto L_0x001b;
        L_0x000f:
            r0 = -62;
            if (r5 < r0) goto L_0x0019;
        L_0x0013:
            r0 = r10 + 1;
            r2 = r9[r10];
            if (r2 <= r6) goto L_0x0081;
        L_0x0019:
            r8 = r1;
            goto L_0x000b;
        L_0x001b:
            r0 = -16;
            if (r5 >= r0) goto L_0x0046;
        L_0x001f:
            r0 = r8 >> 8;
            r0 = r0 ^ -1;
            r0 = (byte) r0;
            if (r0 != 0) goto L_0x0031;
        L_0x0026:
            r2 = r10 + 1;
            r0 = r9[r10];
            if (r2 < r11) goto L_0x0032;
        L_0x002c:
            r8 = com.google.protobuf.Utf8.incompleteStateFor(r5, r0);
            goto L_0x000b;
        L_0x0031:
            r2 = r10;
        L_0x0032:
            if (r0 > r6) goto L_0x0044;
        L_0x0034:
            if (r5 != r3) goto L_0x0038;
        L_0x0036:
            if (r0 < r4) goto L_0x0044;
        L_0x0038:
            r3 = -19;
            if (r5 != r3) goto L_0x003e;
        L_0x003c:
            if (r0 >= r4) goto L_0x0044;
        L_0x003e:
            r10 = r2 + 1;
            r0 = r9[r2];
            if (r0 <= r6) goto L_0x0082;
        L_0x0044:
            r8 = r1;
            goto L_0x000b;
        L_0x0046:
            r0 = r8 >> 8;
            r0 = r0 ^ -1;
            r2 = (byte) r0;
            r0 = 0;
            if (r2 != 0) goto L_0x0059;
        L_0x004e:
            r3 = r10 + 1;
            r2 = r9[r10];
            if (r3 < r11) goto L_0x0087;
        L_0x0054:
            r8 = com.google.protobuf.Utf8.incompleteStateFor(r5, r2);
            goto L_0x000b;
        L_0x0059:
            r0 = r8 >> 16;
            r0 = (byte) r0;
            r4 = r2;
            r3 = r10;
        L_0x005e:
            if (r0 != 0) goto L_0x006b;
        L_0x0060:
            r2 = r3 + 1;
            r0 = r9[r3];
            if (r2 < r11) goto L_0x006c;
        L_0x0066:
            r8 = com.google.protobuf.Utf8.incompleteStateFor(r5, r4, r0);
            goto L_0x000b;
        L_0x006b:
            r2 = r3;
        L_0x006c:
            if (r4 > r6) goto L_0x007f;
        L_0x006e:
            r3 = r5 << 28;
            r4 = r4 + 112;
            r3 = r3 + r4;
            r3 = r3 >> 30;
            if (r3 != 0) goto L_0x007f;
        L_0x0077:
            if (r0 > r6) goto L_0x007f;
        L_0x0079:
            r10 = r2 + 1;
            r0 = r9[r2];
            if (r0 <= r6) goto L_0x0082;
        L_0x007f:
            r8 = r1;
            goto L_0x000b;
        L_0x0081:
            r10 = r0;
        L_0x0082:
            r8 = partialIsValidUtf8(r9, r10, r11);
            goto L_0x000b;
        L_0x0087:
            r4 = r2;
            goto L_0x005e;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.SafeProcessor.partialIsValidUtf8(int, byte[], int, int):int");
        }

        int partialIsValidUtf8Direct(int i, ByteBuffer byteBuffer, int i2, int i3) {
            return partialIsValidUtf8Default(i, byteBuffer, i2, i3);
        }

        int encodeUtf8(CharSequence charSequence, byte[] bArr, int i, int i2) {
            int length = charSequence.length();
            int i3 = 0;
            int i4 = i + i2;
            while (i3 < length && i3 + i < i4) {
                char charAt = charSequence.charAt(i3);
                if (charAt >= '') {
                    break;
                }
                bArr[i + i3] = (byte) charAt;
                i3++;
            }
            if (i3 == length) {
                return i + length;
            }
            int i5 = i + i3;
            while (i3 < length) {
                int i6;
                char charAt2 = charSequence.charAt(i3);
                if (charAt2 < '' && i5 < i4) {
                    i6 = i5 + 1;
                    bArr[i5] = (byte) charAt2;
                } else if (charAt2 < 'ࠀ' && i5 <= i4 - 2) {
                    r6 = i5 + 1;
                    bArr[i5] = (byte) ((charAt2 >>> 6) | 960);
                    i6 = r6 + 1;
                    bArr[r6] = (byte) ((charAt2 & 63) | 128);
                } else if ((charAt2 < '?' || '?' < charAt2) && i5 <= i4 - 3) {
                    i6 = i5 + 1;
                    bArr[i5] = (byte) ((charAt2 >>> 12) | 480);
                    i5 = i6 + 1;
                    bArr[i6] = (byte) (((charAt2 >>> 6) & 63) | 128);
                    i6 = i5 + 1;
                    bArr[i5] = (byte) ((charAt2 & 63) | 128);
                } else if (i5 <= i4 - 4) {
                    if (i3 + 1 != charSequence.length()) {
                        i3++;
                        charAt = charSequence.charAt(i3);
                        if (Character.isSurrogatePair(charAt2, charAt)) {
                            int toCodePoint = Character.toCodePoint(charAt2, charAt);
                            i6 = i5 + 1;
                            bArr[i5] = (byte) ((toCodePoint >>> 18) | 240);
                            i5 = i6 + 1;
                            bArr[i6] = (byte) (((toCodePoint >>> 12) & 63) | 128);
                            r6 = i5 + 1;
                            bArr[i5] = (byte) (((toCodePoint >>> 6) & 63) | 128);
                            i6 = r6 + 1;
                            bArr[r6] = (byte) ((toCodePoint & 63) | 128);
                        }
                    }
                    throw new UnpairedSurrogateException(i3 - 1, length);
                } else if ('?' > charAt2 || charAt2 > '?' || (i3 + 1 != charSequence.length() && Character.isSurrogatePair(charAt2, charSequence.charAt(i3 + 1)))) {
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + i5);
                } else {
                    throw new UnpairedSurrogateException(i3, length);
                }
                i3++;
                i5 = i6;
            }
            return i5;
        }

        void encodeUtf8Direct(CharSequence charSequence, ByteBuffer byteBuffer) {
            encodeUtf8Default(charSequence, byteBuffer);
        }

        private static int partialIsValidUtf8(byte[] bArr, int i, int i2) {
            while (i < i2 && bArr[i] >= (byte) 0) {
                i++;
            }
            return i >= i2 ? 0 : partialIsValidUtf8NonAscii(bArr, i, i2);
        }

        private static int partialIsValidUtf8NonAscii(byte[] bArr, int i, int i2) {
            while (i < i2) {
                int i3 = i + 1;
                int i4 = bArr[i];
                if (i4 < 0) {
                    if (i4 < -32) {
                        if (i3 >= i2) {
                            return i4;
                        }
                        if (i4 >= -62) {
                            i4 = i3 + 1;
                            if (bArr[i3] > (byte) -65) {
                            }
                        }
                        return -1;
                    } else if (i4 < -16) {
                        if (i3 >= i2 - 1) {
                            return Utf8.incompleteStateFor(bArr, i3, i2);
                        }
                        r3 = i3 + 1;
                        r2 = bArr[i3];
                        if (r2 <= (byte) -65 && ((i4 != -32 || r2 >= (byte) -96) && (i4 != -19 || r2 < (byte) -96))) {
                            i4 = r3 + 1;
                            if (bArr[r3] > (byte) -65) {
                            }
                        }
                        return -1;
                    } else if (i3 >= i2 - 2) {
                        return Utf8.incompleteStateFor(bArr, i3, i2);
                    } else {
                        r3 = i3 + 1;
                        r2 = bArr[i3];
                        if (r2 <= (byte) -65 && (((i4 << 28) + (r2 + 112)) >> 30) == 0) {
                            i3 = r3 + 1;
                            if (bArr[r3] <= (byte) -65) {
                                i4 = i3 + 1;
                                if (bArr[i3] > (byte) -65) {
                                }
                            }
                        }
                        return -1;
                    }
                    i = i4;
                } else {
                    i = i3;
                }
            }
            return 0;
        }
    }

    static class UnpairedSurrogateException extends IllegalArgumentException {
        UnpairedSurrogateException(int i, int i2) {
            super("Unpaired surrogate at index " + i + " of " + i2);
        }
    }

    static final class UnsafeProcessor extends Processor {
        UnsafeProcessor() {
        }

        static boolean isAvailable() {
            return UnsafeUtil.hasUnsafeArrayOperations() && UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        int partialIsValidUtf8(int r10, byte[] r11, int r12, int r13) {
            /*
            r9 = this;
            r0 = r12 | r13;
            r1 = r11.length;
            r1 = r1 - r13;
            r0 = r0 | r1;
            if (r0 >= 0) goto L_0x002d;
        L_0x0007:
            r0 = new java.lang.ArrayIndexOutOfBoundsException;
            r1 = "Array length=%d, index=%d, limit=%d";
            r2 = 3;
            r2 = new java.lang.Object[r2];
            r3 = 0;
            r4 = r11.length;
            r4 = java.lang.Integer.valueOf(r4);
            r2[r3] = r4;
            r3 = 1;
            r4 = java.lang.Integer.valueOf(r12);
            r2[r3] = r4;
            r3 = 2;
            r4 = java.lang.Integer.valueOf(r13);
            r2[r3] = r4;
            r1 = java.lang.String.format(r1, r2);
            r0.<init>(r1);
            throw r0;
        L_0x002d:
            r2 = (long) r12;
            r6 = (long) r13;
            if (r10 == 0) goto L_0x00d9;
        L_0x0031:
            r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
            if (r0 < 0) goto L_0x0036;
        L_0x0035:
            return r10;
        L_0x0036:
            r8 = (byte) r10;
            r0 = -32;
            if (r8 >= r0) goto L_0x004c;
        L_0x003b:
            r0 = -62;
            if (r8 < r0) goto L_0x004a;
        L_0x003f:
            r0 = 1;
            r0 = r0 + r2;
            r2 = com.google.protobuf.UnsafeUtil.getByte(r11, r2);
            r3 = -65;
            if (r2 <= r3) goto L_0x00da;
        L_0x004a:
            r10 = -1;
            goto L_0x0035;
        L_0x004c:
            r0 = -16;
            if (r8 >= r0) goto L_0x008a;
        L_0x0050:
            r0 = r10 >> 8;
            r0 = r0 ^ -1;
            r0 = (byte) r0;
            if (r0 != 0) goto L_0x0069;
        L_0x0057:
            r0 = 1;
            r4 = r2 + r0;
            r0 = com.google.protobuf.UnsafeUtil.getByte(r11, r2);
            r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
            if (r1 < 0) goto L_0x0068;
        L_0x0063:
            r10 = com.google.protobuf.Utf8.incompleteStateFor(r8, r0);
            goto L_0x0035;
        L_0x0068:
            r2 = r4;
        L_0x0069:
            r1 = -65;
            if (r0 > r1) goto L_0x0088;
        L_0x006d:
            r1 = -32;
            if (r8 != r1) goto L_0x0075;
        L_0x0071:
            r1 = -96;
            if (r0 < r1) goto L_0x0088;
        L_0x0075:
            r1 = -19;
            if (r8 != r1) goto L_0x007d;
        L_0x0079:
            r1 = -96;
            if (r0 >= r1) goto L_0x0088;
        L_0x007d:
            r0 = 1;
            r0 = r0 + r2;
            r2 = com.google.protobuf.UnsafeUtil.getByte(r11, r2);
            r3 = -65;
            if (r2 <= r3) goto L_0x00da;
        L_0x0088:
            r10 = -1;
            goto L_0x0035;
        L_0x008a:
            r0 = r10 >> 8;
            r0 = r0 ^ -1;
            r1 = (byte) r0;
            r0 = 0;
            if (r1 != 0) goto L_0x00a2;
        L_0x0092:
            r4 = 1;
            r4 = r4 + r2;
            r1 = com.google.protobuf.UnsafeUtil.getByte(r11, r2);
            r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
            if (r2 < 0) goto L_0x00a6;
        L_0x009d:
            r10 = com.google.protobuf.Utf8.incompleteStateFor(r8, r1);
            goto L_0x0035;
        L_0x00a2:
            r0 = r10 >> 16;
            r0 = (byte) r0;
            r4 = r2;
        L_0x00a6:
            if (r0 != 0) goto L_0x00b9;
        L_0x00a8:
            r2 = 1;
            r2 = r2 + r4;
            r0 = com.google.protobuf.UnsafeUtil.getByte(r11, r4);
            r4 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
            if (r4 < 0) goto L_0x00ba;
        L_0x00b3:
            r10 = com.google.protobuf.Utf8.incompleteStateFor(r8, r1, r0);
            goto L_0x0035;
        L_0x00b9:
            r2 = r4;
        L_0x00ba:
            r4 = -65;
            if (r1 > r4) goto L_0x00d6;
        L_0x00be:
            r4 = r8 << 28;
            r1 = r1 + 112;
            r1 = r1 + r4;
            r1 = r1 >> 30;
            if (r1 != 0) goto L_0x00d6;
        L_0x00c7:
            r1 = -65;
            if (r0 > r1) goto L_0x00d6;
        L_0x00cb:
            r0 = 1;
            r0 = r0 + r2;
            r2 = com.google.protobuf.UnsafeUtil.getByte(r11, r2);
            r3 = -65;
            if (r2 <= r3) goto L_0x00da;
        L_0x00d6:
            r10 = -1;
            goto L_0x0035;
        L_0x00d9:
            r0 = r2;
        L_0x00da:
            r2 = r6 - r0;
            r2 = (int) r2;
            r10 = partialIsValidUtf8(r11, r0, r2);
            goto L_0x0035;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8(int, byte[], int, int):int");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        int partialIsValidUtf8Direct(int r10, java.nio.ByteBuffer r11, int r12, int r13) {
            /*
            r9 = this;
            r0 = r12 | r13;
            r1 = r11.limit();
            r1 = r1 - r13;
            r0 = r0 | r1;
            if (r0 >= 0) goto L_0x0033;
        L_0x000a:
            r0 = new java.lang.ArrayIndexOutOfBoundsException;
            r1 = "buffer limit=%d, index=%d, limit=%d";
            r2 = 3;
            r2 = new java.lang.Object[r2];
            r3 = 0;
            r4 = r11.limit();
            r4 = java.lang.Integer.valueOf(r4);
            r2[r3] = r4;
            r3 = 1;
            r4 = java.lang.Integer.valueOf(r12);
            r2[r3] = r4;
            r3 = 2;
            r4 = java.lang.Integer.valueOf(r13);
            r2[r3] = r4;
            r1 = java.lang.String.format(r1, r2);
            r0.<init>(r1);
            throw r0;
        L_0x0033:
            r0 = com.google.protobuf.UnsafeUtil.addressOffset(r11);
            r2 = (long) r12;
            r2 = r2 + r0;
            r0 = r13 - r12;
            r0 = (long) r0;
            r6 = r2 + r0;
            if (r10 == 0) goto L_0x00e8;
        L_0x0040:
            r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
            if (r0 < 0) goto L_0x0045;
        L_0x0044:
            return r10;
        L_0x0045:
            r8 = (byte) r10;
            r0 = -32;
            if (r8 >= r0) goto L_0x005b;
        L_0x004a:
            r0 = -62;
            if (r8 < r0) goto L_0x0059;
        L_0x004e:
            r0 = 1;
            r0 = r0 + r2;
            r2 = com.google.protobuf.UnsafeUtil.getByte(r2);
            r3 = -65;
            if (r2 <= r3) goto L_0x00e9;
        L_0x0059:
            r10 = -1;
            goto L_0x0044;
        L_0x005b:
            r0 = -16;
            if (r8 >= r0) goto L_0x0099;
        L_0x005f:
            r0 = r10 >> 8;
            r0 = r0 ^ -1;
            r0 = (byte) r0;
            if (r0 != 0) goto L_0x0078;
        L_0x0066:
            r0 = 1;
            r4 = r2 + r0;
            r0 = com.google.protobuf.UnsafeUtil.getByte(r2);
            r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
            if (r1 < 0) goto L_0x0077;
        L_0x0072:
            r10 = com.google.protobuf.Utf8.incompleteStateFor(r8, r0);
            goto L_0x0044;
        L_0x0077:
            r2 = r4;
        L_0x0078:
            r1 = -65;
            if (r0 > r1) goto L_0x0097;
        L_0x007c:
            r1 = -32;
            if (r8 != r1) goto L_0x0084;
        L_0x0080:
            r1 = -96;
            if (r0 < r1) goto L_0x0097;
        L_0x0084:
            r1 = -19;
            if (r8 != r1) goto L_0x008c;
        L_0x0088:
            r1 = -96;
            if (r0 >= r1) goto L_0x0097;
        L_0x008c:
            r0 = 1;
            r0 = r0 + r2;
            r2 = com.google.protobuf.UnsafeUtil.getByte(r2);
            r3 = -65;
            if (r2 <= r3) goto L_0x00e9;
        L_0x0097:
            r10 = -1;
            goto L_0x0044;
        L_0x0099:
            r0 = r10 >> 8;
            r0 = r0 ^ -1;
            r1 = (byte) r0;
            r0 = 0;
            if (r1 != 0) goto L_0x00b1;
        L_0x00a1:
            r4 = 1;
            r4 = r4 + r2;
            r1 = com.google.protobuf.UnsafeUtil.getByte(r2);
            r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
            if (r2 < 0) goto L_0x00b5;
        L_0x00ac:
            r10 = com.google.protobuf.Utf8.incompleteStateFor(r8, r1);
            goto L_0x0044;
        L_0x00b1:
            r0 = r10 >> 16;
            r0 = (byte) r0;
            r4 = r2;
        L_0x00b5:
            if (r0 != 0) goto L_0x00c8;
        L_0x00b7:
            r2 = 1;
            r2 = r2 + r4;
            r0 = com.google.protobuf.UnsafeUtil.getByte(r4);
            r4 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
            if (r4 < 0) goto L_0x00c9;
        L_0x00c2:
            r10 = com.google.protobuf.Utf8.incompleteStateFor(r8, r1, r0);
            goto L_0x0044;
        L_0x00c8:
            r2 = r4;
        L_0x00c9:
            r4 = -65;
            if (r1 > r4) goto L_0x00e5;
        L_0x00cd:
            r4 = r8 << 28;
            r1 = r1 + 112;
            r1 = r1 + r4;
            r1 = r1 >> 30;
            if (r1 != 0) goto L_0x00e5;
        L_0x00d6:
            r1 = -65;
            if (r0 > r1) goto L_0x00e5;
        L_0x00da:
            r0 = 1;
            r0 = r0 + r2;
            r2 = com.google.protobuf.UnsafeUtil.getByte(r2);
            r3 = -65;
            if (r2 <= r3) goto L_0x00e9;
        L_0x00e5:
            r10 = -1;
            goto L_0x0044;
        L_0x00e8:
            r0 = r2;
        L_0x00e9:
            r2 = r6 - r0;
            r2 = (int) r2;
            r10 = partialIsValidUtf8(r0, r2);
            goto L_0x0044;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8Direct(int, java.nio.ByteBuffer, int, int):int");
        }

        int encodeUtf8(CharSequence charSequence, byte[] bArr, int i, int i2) {
            long j = (long) i;
            long j2 = j + ((long) i2);
            int length = charSequence.length();
            if (length > i2 || bArr.length - i2 < i) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(length - 1) + " at index " + (i + i2));
            }
            int i3 = 0;
            while (i3 < length) {
                char charAt = charSequence.charAt(i3);
                if (charAt >= '') {
                    break;
                }
                long j3 = 1 + j;
                UnsafeUtil.putByte(bArr, j, (byte) charAt);
                i3++;
                j = j3;
            }
            if (i3 == length) {
                return (int) j;
            }
            j3 = j;
            while (i3 < length) {
                charAt = charSequence.charAt(i3);
                if (charAt < '' && j3 < j2) {
                    j = 1 + j3;
                    UnsafeUtil.putByte(bArr, j3, (byte) charAt);
                } else if (charAt < 'ࠀ' && j3 <= j2 - 2) {
                    r12 = j3 + 1;
                    UnsafeUtil.putByte(bArr, j3, (byte) ((charAt >>> 6) | 960));
                    j = 1 + r12;
                    UnsafeUtil.putByte(bArr, r12, (byte) ((charAt & 63) | 128));
                } else if ((charAt < '?' || '?' < charAt) && j3 <= j2 - 3) {
                    j = 1 + j3;
                    UnsafeUtil.putByte(bArr, j3, (byte) ((charAt >>> 12) | 480));
                    j3 = 1 + j;
                    UnsafeUtil.putByte(bArr, j, (byte) (((charAt >>> 6) & 63) | 128));
                    j = 1 + j3;
                    UnsafeUtil.putByte(bArr, j3, (byte) ((charAt & 63) | 128));
                } else if (j3 <= j2 - 4) {
                    if (i3 + 1 != length) {
                        i3++;
                        char charAt2 = charSequence.charAt(i3);
                        if (Character.isSurrogatePair(charAt, charAt2)) {
                            int toCodePoint = Character.toCodePoint(charAt, charAt2);
                            j = 1 + j3;
                            UnsafeUtil.putByte(bArr, j3, (byte) ((toCodePoint >>> 18) | 240));
                            j3 = 1 + j;
                            UnsafeUtil.putByte(bArr, j, (byte) (((toCodePoint >>> 12) & 63) | 128));
                            r12 = j3 + 1;
                            UnsafeUtil.putByte(bArr, j3, (byte) (((toCodePoint >>> 6) & 63) | 128));
                            j = 1 + r12;
                            UnsafeUtil.putByte(bArr, r12, (byte) ((toCodePoint & 63) | 128));
                        }
                    }
                    throw new UnpairedSurrogateException(i3 - 1, length);
                } else if ('?' > charAt || charAt > '?' || (i3 + 1 != length && Character.isSurrogatePair(charAt, charSequence.charAt(i3 + 1)))) {
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt + " at index " + j3);
                } else {
                    throw new UnpairedSurrogateException(i3, length);
                }
                i3++;
                j3 = j;
            }
            return (int) j3;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void encodeUtf8Direct(java.lang.CharSequence r17, java.nio.ByteBuffer r18) {
            /*
            r16 = this;
            r8 = com.google.protobuf.UnsafeUtil.addressOffset(r18);
            r2 = r18.position();
            r2 = (long) r2;
            r4 = r8 + r2;
            r2 = r18.limit();
            r2 = (long) r2;
            r10 = r8 + r2;
            r3 = r17.length();
            r6 = (long) r3;
            r12 = r10 - r4;
            r2 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
            if (r2 <= 0) goto L_0x004e;
        L_0x001d:
            r2 = new java.lang.ArrayIndexOutOfBoundsException;
            r4 = new java.lang.StringBuilder;
            r4.<init>();
            r5 = "Failed writing ";
            r4 = r4.append(r5);
            r3 = r3 + -1;
            r0 = r17;
            r3 = r0.charAt(r3);
            r3 = r4.append(r3);
            r4 = " at index ";
            r3 = r3.append(r4);
            r4 = r18.limit();
            r3 = r3.append(r4);
            r3 = r3.toString();
            r2.<init>(r3);
            throw r2;
        L_0x004e:
            r2 = 0;
        L_0x004f:
            if (r2 >= r3) goto L_0x0066;
        L_0x0051:
            r0 = r17;
            r12 = r0.charAt(r2);
            r6 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
            if (r12 >= r6) goto L_0x0066;
        L_0x005b:
            r6 = 1;
            r6 = r6 + r4;
            r12 = (byte) r12;
            com.google.protobuf.UnsafeUtil.putByte(r4, r12);
            r2 = r2 + 1;
            r4 = r6;
            goto L_0x004f;
        L_0x0066:
            if (r2 != r3) goto L_0x0190;
        L_0x0068:
            r2 = r4 - r8;
            r2 = (int) r2;
            r0 = r18;
            r0.position(r2);
        L_0x0070:
            return;
        L_0x0071:
            if (r2 >= r3) goto L_0x0186;
        L_0x0073:
            r0 = r17;
            r12 = r0.charAt(r2);
            r4 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
            if (r12 >= r4) goto L_0x008c;
        L_0x007d:
            r4 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1));
            if (r4 >= 0) goto L_0x008c;
        L_0x0081:
            r4 = 1;
            r4 = r4 + r6;
            r12 = (byte) r12;
            com.google.protobuf.UnsafeUtil.putByte(r6, r12);
        L_0x0088:
            r2 = r2 + 1;
            r6 = r4;
            goto L_0x0071;
        L_0x008c:
            r4 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
            if (r12 >= r4) goto L_0x00b0;
        L_0x0090:
            r4 = 2;
            r4 = r10 - r4;
            r4 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1));
            if (r4 > 0) goto L_0x00b0;
        L_0x0098:
            r4 = 1;
            r14 = r6 + r4;
            r4 = r12 >>> 6;
            r4 = r4 | 960;
            r4 = (byte) r4;
            com.google.protobuf.UnsafeUtil.putByte(r6, r4);
            r4 = 1;
            r4 = r4 + r14;
            r6 = r12 & 63;
            r6 = r6 | 128;
            r6 = (byte) r6;
            com.google.protobuf.UnsafeUtil.putByte(r14, r6);
            goto L_0x0088;
        L_0x00b0:
            r4 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
            if (r12 < r4) goto L_0x00ba;
        L_0x00b5:
            r4 = 57343; // 0xdfff float:8.0355E-41 double:2.8331E-319;
            if (r4 >= r12) goto L_0x00e6;
        L_0x00ba:
            r4 = 3;
            r4 = r10 - r4;
            r4 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1));
            if (r4 > 0) goto L_0x00e6;
        L_0x00c2:
            r4 = 1;
            r4 = r4 + r6;
            r13 = r12 >>> 12;
            r13 = r13 | 480;
            r13 = (byte) r13;
            com.google.protobuf.UnsafeUtil.putByte(r6, r13);
            r6 = 1;
            r6 = r6 + r4;
            r13 = r12 >>> 6;
            r13 = r13 & 63;
            r13 = r13 | 128;
            r13 = (byte) r13;
            com.google.protobuf.UnsafeUtil.putByte(r4, r13);
            r4 = 1;
            r4 = r4 + r6;
            r12 = r12 & 63;
            r12 = r12 | 128;
            r12 = (byte) r12;
            com.google.protobuf.UnsafeUtil.putByte(r6, r12);
            goto L_0x0088;
        L_0x00e6:
            r4 = 4;
            r4 = r10 - r4;
            r4 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1));
            if (r4 > 0) goto L_0x013f;
        L_0x00ee:
            r4 = r2 + 1;
            if (r4 == r3) goto L_0x0100;
        L_0x00f2:
            r2 = r2 + 1;
            r0 = r17;
            r4 = r0.charAt(r2);
            r5 = java.lang.Character.isSurrogatePair(r12, r4);
            if (r5 != 0) goto L_0x0108;
        L_0x0100:
            r4 = new com.google.protobuf.Utf8$UnpairedSurrogateException;
            r2 = r2 + -1;
            r4.<init>(r2, r3);
            throw r4;
        L_0x0108:
            r12 = java.lang.Character.toCodePoint(r12, r4);
            r4 = 1;
            r4 = r4 + r6;
            r13 = r12 >>> 18;
            r13 = r13 | 240;
            r13 = (byte) r13;
            com.google.protobuf.UnsafeUtil.putByte(r6, r13);
            r6 = 1;
            r6 = r6 + r4;
            r13 = r12 >>> 12;
            r13 = r13 & 63;
            r13 = r13 | 128;
            r13 = (byte) r13;
            com.google.protobuf.UnsafeUtil.putByte(r4, r13);
            r4 = 1;
            r14 = r6 + r4;
            r4 = r12 >>> 6;
            r4 = r4 & 63;
            r4 = r4 | 128;
            r4 = (byte) r4;
            com.google.protobuf.UnsafeUtil.putByte(r6, r4);
            r4 = 1;
            r4 = r4 + r14;
            r6 = r12 & 63;
            r6 = r6 | 128;
            r6 = (byte) r6;
            com.google.protobuf.UnsafeUtil.putByte(r14, r6);
            goto L_0x0088;
        L_0x013f:
            r4 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
            if (r4 > r12) goto L_0x0161;
        L_0x0144:
            r4 = 57343; // 0xdfff float:8.0355E-41 double:2.8331E-319;
            if (r12 > r4) goto L_0x0161;
        L_0x0149:
            r4 = r2 + 1;
            if (r4 == r3) goto L_0x015b;
        L_0x014d:
            r4 = r2 + 1;
            r0 = r17;
            r4 = r0.charAt(r4);
            r4 = java.lang.Character.isSurrogatePair(r12, r4);
            if (r4 != 0) goto L_0x0161;
        L_0x015b:
            r4 = new com.google.protobuf.Utf8$UnpairedSurrogateException;
            r4.<init>(r2, r3);
            throw r4;
        L_0x0161:
            r2 = new java.lang.ArrayIndexOutOfBoundsException;
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r4 = "Failed writing ";
            r3 = r3.append(r4);
            r3 = r3.append(r12);
            r4 = " at index ";
            r3 = r3.append(r4);
            r3 = r3.append(r6);
            r3 = r3.toString();
            r2.<init>(r3);
            throw r2;
        L_0x0186:
            r2 = r6 - r8;
            r2 = (int) r2;
            r0 = r18;
            r0.position(r2);
            goto L_0x0070;
        L_0x0190:
            r6 = r4;
            goto L_0x0071;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.encodeUtf8Direct(java.lang.CharSequence, java.nio.ByteBuffer):void");
        }

        private static int unsafeEstimateConsecutiveAscii(byte[] bArr, long j, int i) {
            int i2 = 0;
            if (i < 16) {
                return 0;
            }
            while (i2 < i) {
                long j2 = 1 + j;
                if (UnsafeUtil.getByte(bArr, j) < (byte) 0) {
                    return i2;
                }
                i2++;
                j = j2;
            }
            return i;
        }

        private static int unsafeEstimateConsecutiveAscii(long j, int i) {
            if (i < 16) {
                return 0;
            }
            int i2 = ((int) j) & 7;
            int i3 = i2;
            long j2 = j;
            while (i3 > 0) {
                j = j2 + 1;
                if (UnsafeUtil.getByte(j2) < (byte) 0) {
                    return i2 - i3;
                }
                i3--;
                j2 = j;
            }
            i3 = i - i2;
            while (i3 >= 8 && (UnsafeUtil.getLong(j2) & Utf8.ASCII_MASK_LONG) == 0) {
                j2 += 8;
                i3 -= 8;
            }
            return i - i3;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static int partialIsValidUtf8(byte[] r9, long r10, int r12) {
            /*
            r1 = unsafeEstimateConsecutiveAscii(r9, r10, r12);
            r0 = r12 - r1;
            r2 = (long) r1;
            r2 = r2 + r10;
        L_0x0008:
            r1 = 0;
            r4 = r2;
        L_0x000a:
            if (r0 <= 0) goto L_0x001a;
        L_0x000c:
            r2 = 1;
            r2 = r2 + r4;
            r1 = com.google.protobuf.UnsafeUtil.getByte(r9, r4);
            if (r1 < 0) goto L_0x0019;
        L_0x0015:
            r0 = r0 + -1;
            r4 = r2;
            goto L_0x000a;
        L_0x0019:
            r4 = r2;
        L_0x001a:
            if (r0 != 0) goto L_0x001e;
        L_0x001c:
            r0 = 0;
        L_0x001d:
            return r0;
        L_0x001e:
            r0 = r0 + -1;
            r2 = -32;
            if (r1 >= r2) goto L_0x003b;
        L_0x0024:
            if (r0 != 0) goto L_0x0028;
        L_0x0026:
            r0 = r1;
            goto L_0x001d;
        L_0x0028:
            r0 = r0 + -1;
            r2 = -62;
            if (r1 < r2) goto L_0x0039;
        L_0x002e:
            r2 = 1;
            r2 = r2 + r4;
            r1 = com.google.protobuf.UnsafeUtil.getByte(r9, r4);
            r4 = -65;
            if (r1 <= r4) goto L_0x0008;
        L_0x0039:
            r0 = -1;
            goto L_0x001d;
        L_0x003b:
            r2 = -16;
            if (r1 >= r2) goto L_0x0072;
        L_0x003f:
            r2 = 2;
            if (r0 >= r2) goto L_0x0047;
        L_0x0042:
            r0 = unsafeIncompleteStateFor(r9, r1, r4, r0);
            goto L_0x001d;
        L_0x0047:
            r0 = r0 + -2;
            r2 = 1;
            r6 = r4 + r2;
            r2 = com.google.protobuf.UnsafeUtil.getByte(r9, r4);
            r3 = -65;
            if (r2 > r3) goto L_0x0070;
        L_0x0055:
            r3 = -32;
            if (r1 != r3) goto L_0x005d;
        L_0x0059:
            r3 = -96;
            if (r2 < r3) goto L_0x0070;
        L_0x005d:
            r3 = -19;
            if (r1 != r3) goto L_0x0065;
        L_0x0061:
            r1 = -96;
            if (r2 >= r1) goto L_0x0070;
        L_0x0065:
            r2 = 1;
            r2 = r2 + r6;
            r1 = com.google.protobuf.UnsafeUtil.getByte(r9, r6);
            r4 = -65;
            if (r1 <= r4) goto L_0x0008;
        L_0x0070:
            r0 = -1;
            goto L_0x001d;
        L_0x0072:
            r2 = 3;
            if (r0 >= r2) goto L_0x007a;
        L_0x0075:
            r0 = unsafeIncompleteStateFor(r9, r1, r4, r0);
            goto L_0x001d;
        L_0x007a:
            r0 = r0 + -3;
            r2 = 1;
            r2 = r2 + r4;
            r4 = com.google.protobuf.UnsafeUtil.getByte(r9, r4);
            r5 = -65;
            if (r4 > r5) goto L_0x00a6;
        L_0x0087:
            r1 = r1 << 28;
            r4 = r4 + 112;
            r1 = r1 + r4;
            r1 = r1 >> 30;
            if (r1 != 0) goto L_0x00a6;
        L_0x0090:
            r4 = 1;
            r4 = r4 + r2;
            r1 = com.google.protobuf.UnsafeUtil.getByte(r9, r2);
            r2 = -65;
            if (r1 > r2) goto L_0x00a6;
        L_0x009b:
            r2 = 1;
            r2 = r2 + r4;
            r1 = com.google.protobuf.UnsafeUtil.getByte(r9, r4);
            r4 = -65;
            if (r1 <= r4) goto L_0x0008;
        L_0x00a6:
            r0 = -1;
            goto L_0x001d;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8(byte[], long, int):int");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static int partialIsValidUtf8(long r8, int r10) {
            /*
            r0 = unsafeEstimateConsecutiveAscii(r8, r10);
            r2 = (long) r0;
            r2 = r2 + r8;
            r0 = r10 - r0;
        L_0x0008:
            r1 = 0;
            r4 = r2;
        L_0x000a:
            if (r0 <= 0) goto L_0x001a;
        L_0x000c:
            r2 = 1;
            r2 = r2 + r4;
            r1 = com.google.protobuf.UnsafeUtil.getByte(r4);
            if (r1 < 0) goto L_0x0019;
        L_0x0015:
            r0 = r0 + -1;
            r4 = r2;
            goto L_0x000a;
        L_0x0019:
            r4 = r2;
        L_0x001a:
            if (r0 != 0) goto L_0x001e;
        L_0x001c:
            r0 = 0;
        L_0x001d:
            return r0;
        L_0x001e:
            r0 = r0 + -1;
            r2 = -32;
            if (r1 >= r2) goto L_0x003b;
        L_0x0024:
            if (r0 != 0) goto L_0x0028;
        L_0x0026:
            r0 = r1;
            goto L_0x001d;
        L_0x0028:
            r0 = r0 + -1;
            r2 = -62;
            if (r1 < r2) goto L_0x0039;
        L_0x002e:
            r2 = 1;
            r2 = r2 + r4;
            r1 = com.google.protobuf.UnsafeUtil.getByte(r4);
            r4 = -65;
            if (r1 <= r4) goto L_0x0008;
        L_0x0039:
            r0 = -1;
            goto L_0x001d;
        L_0x003b:
            r2 = -16;
            if (r1 >= r2) goto L_0x0072;
        L_0x003f:
            r2 = 2;
            if (r0 >= r2) goto L_0x0047;
        L_0x0042:
            r0 = unsafeIncompleteStateFor(r4, r1, r0);
            goto L_0x001d;
        L_0x0047:
            r0 = r0 + -2;
            r2 = 1;
            r6 = r4 + r2;
            r2 = com.google.protobuf.UnsafeUtil.getByte(r4);
            r3 = -65;
            if (r2 > r3) goto L_0x0070;
        L_0x0055:
            r3 = -32;
            if (r1 != r3) goto L_0x005d;
        L_0x0059:
            r3 = -96;
            if (r2 < r3) goto L_0x0070;
        L_0x005d:
            r3 = -19;
            if (r1 != r3) goto L_0x0065;
        L_0x0061:
            r1 = -96;
            if (r2 >= r1) goto L_0x0070;
        L_0x0065:
            r2 = 1;
            r2 = r2 + r6;
            r1 = com.google.protobuf.UnsafeUtil.getByte(r6);
            r4 = -65;
            if (r1 <= r4) goto L_0x0008;
        L_0x0070:
            r0 = -1;
            goto L_0x001d;
        L_0x0072:
            r2 = 3;
            if (r0 >= r2) goto L_0x007a;
        L_0x0075:
            r0 = unsafeIncompleteStateFor(r4, r1, r0);
            goto L_0x001d;
        L_0x007a:
            r0 = r0 + -3;
            r2 = 1;
            r2 = r2 + r4;
            r4 = com.google.protobuf.UnsafeUtil.getByte(r4);
            r5 = -65;
            if (r4 > r5) goto L_0x00a6;
        L_0x0087:
            r1 = r1 << 28;
            r4 = r4 + 112;
            r1 = r1 + r4;
            r1 = r1 >> 30;
            if (r1 != 0) goto L_0x00a6;
        L_0x0090:
            r4 = 1;
            r4 = r4 + r2;
            r1 = com.google.protobuf.UnsafeUtil.getByte(r2);
            r2 = -65;
            if (r1 > r2) goto L_0x00a6;
        L_0x009b:
            r2 = 1;
            r2 = r2 + r4;
            r1 = com.google.protobuf.UnsafeUtil.getByte(r4);
            r4 = -65;
            if (r1 <= r4) goto L_0x0008;
        L_0x00a6:
            r0 = -1;
            goto L_0x001d;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8(long, int):int");
        }

        private static int unsafeIncompleteStateFor(byte[] bArr, int i, long j, int i2) {
            switch (i2) {
                case 0:
                    return Utf8.incompleteStateFor(i);
                case 1:
                    return Utf8.incompleteStateFor(i, UnsafeUtil.getByte(bArr, j));
                case 2:
                    return Utf8.incompleteStateFor(i, (int) UnsafeUtil.getByte(bArr, j), (int) UnsafeUtil.getByte(bArr, 1 + j));
                default:
                    throw new AssertionError();
            }
        }

        private static int unsafeIncompleteStateFor(long j, int i, int i2) {
            switch (i2) {
                case 0:
                    return Utf8.incompleteStateFor(i);
                case 1:
                    return Utf8.incompleteStateFor(i, UnsafeUtil.getByte(j));
                case 2:
                    return Utf8.incompleteStateFor(i, (int) UnsafeUtil.getByte(j), (int) UnsafeUtil.getByte(1 + j));
                default:
                    throw new AssertionError();
            }
        }
    }

    public static boolean isValidUtf8(byte[] bArr) {
        return processor.isValidUtf8(bArr, 0, bArr.length);
    }

    public static boolean isValidUtf8(byte[] bArr, int i, int i2) {
        return processor.isValidUtf8(bArr, i, i2);
    }

    public static int partialIsValidUtf8(int i, byte[] bArr, int i2, int i3) {
        return processor.partialIsValidUtf8(i, bArr, i2, i3);
    }

    private static int incompleteStateFor(int i) {
        return i > -12 ? -1 : i;
    }

    private static int incompleteStateFor(int i, int i2) {
        return (i > -12 || i2 > -65) ? -1 : (i2 << 8) ^ i;
    }

    private static int incompleteStateFor(int i, int i2, int i3) {
        return (i > -12 || i2 > -65 || i3 > -65) ? -1 : ((i2 << 8) ^ i) ^ (i3 << 16);
    }

    private static int incompleteStateFor(byte[] bArr, int i, int i2) {
        int i3 = bArr[i - 1];
        switch (i2 - i) {
            case 0:
                return incompleteStateFor(i3);
            case 1:
                return incompleteStateFor(i3, bArr[i]);
            case 2:
                return incompleteStateFor(i3, bArr[i], bArr[i + 1]);
            default:
                throw new AssertionError();
        }
    }

    private static int incompleteStateFor(ByteBuffer byteBuffer, int i, int i2, int i3) {
        switch (i3) {
            case 0:
                return incompleteStateFor(i);
            case 1:
                return incompleteStateFor(i, byteBuffer.get(i2));
            case 2:
                return incompleteStateFor(i, byteBuffer.get(i2), byteBuffer.get(i2 + 1));
            default:
                throw new AssertionError();
        }
    }

    static int encodedLength(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) < '') {
            i++;
        }
        int i2 = i;
        i = length;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            if (charAt >= 'ࠀ') {
                i += encodedLengthGeneral(charSequence, i2);
                break;
            }
            i2++;
            i = ((127 - charAt) >>> 31) + i;
        }
        if (i >= length) {
            return i;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i) + 4294967296L));
    }

    private static int encodedLengthGeneral(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        int i3 = i;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            if (charAt < 'ࠀ') {
                i2 += (127 - charAt) >>> 31;
            } else {
                i2 += 2;
                if ('?' <= charAt && charAt <= '?') {
                    if (Character.codePointAt(charSequence, i3) < 65536) {
                        throw new UnpairedSurrogateException(i3, length);
                    }
                    i3++;
                }
            }
            i3++;
        }
        return i2;
    }

    static int encode(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return processor.encodeUtf8(charSequence, bArr, i, i2);
    }

    static boolean isValidUtf8(ByteBuffer byteBuffer) {
        return processor.isValidUtf8(byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    static int partialIsValidUtf8(int i, ByteBuffer byteBuffer, int i2, int i3) {
        return processor.partialIsValidUtf8(i, byteBuffer, i2, i3);
    }

    static void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer) {
        processor.encodeUtf8(charSequence, byteBuffer);
    }

    private static int estimateConsecutiveAscii(ByteBuffer byteBuffer, int i, int i2) {
        int i3 = i2 - 7;
        int i4 = i;
        while (i4 < i3 && (byteBuffer.getLong(i4) & ASCII_MASK_LONG) == 0) {
            i4 += 8;
        }
        return i4 - i;
    }

    private Utf8() {
    }
}
