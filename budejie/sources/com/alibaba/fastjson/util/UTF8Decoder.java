package com.alibaba.fastjson.util;

import cn.v6.sixrooms.constants.CommonInts;
import com.budejie.www.R$styleable;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

public class UTF8Decoder extends CharsetDecoder {
    private static final Charset charset = Charset.forName("UTF-8");

    public UTF8Decoder() {
        super(charset, 1.0f, 1.0f);
    }

    public static CoderResult malformedN(ByteBuffer byteBuffer, int i) {
        int i2 = 1;
        switch (i) {
            case 1:
                byte b = byteBuffer.get();
                if ((b >> 2) == -2) {
                    if (byteBuffer.remaining() < 4) {
                        return CoderResult.UNDERFLOW;
                    }
                    while (i2 < 5) {
                        if ((byteBuffer.get() & 192) != 128) {
                            return CoderResult.malformedForLength(i2);
                        }
                        i2++;
                    }
                    return CoderResult.malformedForLength(5);
                } else if ((b >> 1) != -2) {
                    return CoderResult.malformedForLength(1);
                } else {
                    if (byteBuffer.remaining() < 5) {
                        return CoderResult.UNDERFLOW;
                    }
                    while (i2 < 6) {
                        if ((byteBuffer.get() & 192) != 128) {
                            return CoderResult.malformedForLength(i2);
                        }
                        i2++;
                    }
                    return CoderResult.malformedForLength(6);
                }
            case 2:
                return CoderResult.malformedForLength(1);
            case 3:
                byte b2 = byteBuffer.get();
                byte b3 = byteBuffer.get();
                if (!(b2 == (byte) -32 && (b3 & 224) == 128) && (b3 & 192) == 128) {
                    i2 = 2;
                }
                return CoderResult.malformedForLength(i2);
            case 4:
                int i3 = byteBuffer.get() & 255;
                int i4 = byteBuffer.get() & 255;
                if (i3 > R$styleable.Theme_Custom_top_label_btn_bg || ((i3 == R$styleable.Theme_Custom_shape_cmt_like4_bg && (i4 < R$styleable.Theme_Custom_phone_verify_btn_bg || i4 > R$styleable.Theme_Custom_progressbar_bg_state)) || ((i3 == R$styleable.Theme_Custom_top_label_btn_bg && (i4 & R$styleable.Theme_Custom_shape_cmt_like4_bg) != 128) || (i4 & 192) != 128))) {
                    return CoderResult.malformedForLength(1);
                }
                if ((byteBuffer.get() & 192) != 128) {
                    return CoderResult.malformedForLength(2);
                }
                return CoderResult.malformedForLength(3);
            default:
                throw new IllegalStateException();
        }
    }

    private static CoderResult malformed(ByteBuffer byteBuffer, int i, CharBuffer charBuffer, int i2, int i3) {
        byteBuffer.position(i - byteBuffer.arrayOffset());
        CoderResult malformedN = malformedN(byteBuffer, i3);
        byteBuffer.position(i);
        charBuffer.position(i2);
        return malformedN;
    }

    private static CoderResult xflow(Buffer buffer, int i, int i2, Buffer buffer2, int i3, int i4) {
        buffer.position(i);
        buffer2.position(i3);
        return (i4 == 0 || i2 - i < i4) ? CoderResult.UNDERFLOW : CoderResult.OVERFLOW;
    }

    protected CoderResult decodeLoop(ByteBuffer byteBuffer, CharBuffer charBuffer) {
        byte[] array = byteBuffer.array();
        int position = byteBuffer.position() + byteBuffer.arrayOffset();
        int limit = byteBuffer.limit() + byteBuffer.arrayOffset();
        char[] array2 = charBuffer.array();
        int arrayOffset = charBuffer.arrayOffset() + charBuffer.position();
        int arrayOffset2 = charBuffer.arrayOffset() + charBuffer.limit();
        int min = arrayOffset + Math.min(limit - position, arrayOffset2 - arrayOffset);
        while (arrayOffset < min && array[position] >= (byte) 0) {
            int i = arrayOffset + 1;
            int i2 = position + 1;
            array2[arrayOffset] = (char) array[position];
            arrayOffset = i;
            position = i2;
        }
        while (position < limit) {
            byte b = array[position];
            if (b >= (byte) 0) {
                if (arrayOffset >= arrayOffset2) {
                    return xflow(byteBuffer, position, limit, charBuffer, arrayOffset, 1);
                }
                i = arrayOffset + 1;
                array2[arrayOffset] = (char) b;
                position++;
            } else if ((b >> 5) == -2) {
                if (limit - position < 2 || arrayOffset >= arrayOffset2) {
                    return xflow(byteBuffer, position, limit, charBuffer, arrayOffset, 2);
                }
                r8 = array[position + 1];
                r0 = ((b & 30) == 0 || (r8 & 192) != 128) ? 1 : null;
                if (r0 != null) {
                    return malformed(byteBuffer, position, charBuffer, arrayOffset, 2);
                }
                i = arrayOffset + 1;
                array2[arrayOffset] = (char) (((b << 6) ^ r8) ^ 3968);
                position += 2;
            } else if ((b >> 4) == -2) {
                if (limit - position < 3 || arrayOffset >= arrayOffset2) {
                    return xflow(byteBuffer, position, limit, charBuffer, arrayOffset, 3);
                }
                r8 = array[position + 1];
                r9 = array[position + 2];
                r0 = (!(b == (byte) -32 && (r8 & 224) == 128) && (r8 & 192) == 128 && (r9 & 192) == 128) ? null : 1;
                if (r0 != null) {
                    return malformed(byteBuffer, position, charBuffer, arrayOffset, 3);
                }
                i = arrayOffset + 1;
                array2[arrayOffset] = (char) ((((b << 12) ^ (r8 << 6)) ^ r9) ^ 8064);
                position += 3;
            } else if ((b >> 3) != -2) {
                return malformed(byteBuffer, position, charBuffer, arrayOffset, 1);
            } else {
                if (limit - position < 4 || arrayOffset2 - arrayOffset < 2) {
                    return xflow(byteBuffer, position, limit, charBuffer, arrayOffset, 4);
                }
                byte b2 = array[position + 1];
                r8 = array[position + 2];
                r9 = array[position + 3];
                i2 = ((((b & 7) << 18) | ((b2 & 63) << 12)) | ((r8 & 63) << 6)) | (r9 & 63);
                r0 = ((b2 & 192) == 128 && (r8 & 192) == 128 && (r9 & 192) == 128) ? null : 1;
                if (r0 != null || i2 < 65536 || i2 > 1114111) {
                    return malformed(byteBuffer, position, charBuffer, arrayOffset, 4);
                }
                min = arrayOffset + 1;
                array2[arrayOffset] = (char) (55296 | (((i2 - 65536) >> 10) & CommonInts.PIC_UPLOADING_ERROR_CODE));
                i = min + 1;
                array2[min] = (char) (((i2 - 65536) & CommonInts.PIC_UPLOADING_ERROR_CODE) | 56320);
                position += 4;
            }
            arrayOffset = i;
        }
        return xflow(byteBuffer, position, limit, charBuffer, arrayOffset, 0);
    }
}
