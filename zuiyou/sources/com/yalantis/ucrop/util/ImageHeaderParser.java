package com.yalantis.ucrop.util;

import android.media.ExifInterface;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public class ImageHeaderParser {
    private static final int[] BYTES_PER_FORMAT = new int[]{0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};
    private static final int EXIF_MAGIC_NUMBER = 65496;
    private static final int EXIF_SEGMENT_TYPE = 225;
    private static final int INTEL_TIFF_MAGIC_NUMBER = 18761;
    private static final String JPEG_EXIF_SEGMENT_PREAMBLE = "Exif\u0000\u0000";
    private static final byte[] JPEG_EXIF_SEGMENT_PREAMBLE_BYTES = JPEG_EXIF_SEGMENT_PREAMBLE.getBytes(Charset.forName("UTF-8"));
    private static final int MARKER_EOI = 217;
    private static final int MOTOROLA_TIFF_MAGIC_NUMBER = 19789;
    private static final int ORIENTATION_TAG_TYPE = 274;
    private static final int SEGMENT_SOS = 218;
    private static final int SEGMENT_START_ID = 255;
    private static final String TAG = "ImageHeaderParser";
    public static final int UNKNOWN_ORIENTATION = -1;
    private final Reader reader;

    private static class RandomAccessReader {
        private final ByteBuffer data;

        public RandomAccessReader(byte[] bArr, int i) {
            this.data = (ByteBuffer) ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).limit(i);
        }

        public void order(ByteOrder byteOrder) {
            this.data.order(byteOrder);
        }

        public int length() {
            return this.data.remaining();
        }

        public int getInt32(int i) {
            return this.data.getInt(i);
        }

        public short getInt16(int i) {
            return this.data.getShort(i);
        }
    }

    private interface Reader {
        int getUInt16() throws IOException;

        short getUInt8() throws IOException;

        int read(byte[] bArr, int i) throws IOException;

        long skip(long j) throws IOException;
    }

    private static class StreamReader implements Reader {
        private final InputStream is;

        public StreamReader(InputStream inputStream) {
            this.is = inputStream;
        }

        public int getUInt16() throws IOException {
            return ((this.is.read() << 8) & 65280) | (this.is.read() & 255);
        }

        public short getUInt8() throws IOException {
            return (short) (this.is.read() & 255);
        }

        public long skip(long j) throws IOException {
            if (j < 0) {
                return 0;
            }
            long j2 = j;
            while (j2 > 0) {
                long skip = this.is.skip(j2);
                if (skip > 0) {
                    j2 -= skip;
                } else if (this.is.read() == -1) {
                    break;
                } else {
                    j2--;
                }
            }
            return j - j2;
        }

        public int read(byte[] bArr, int i) throws IOException {
            int i2 = i;
            while (i2 > 0) {
                int read = this.is.read(bArr, i - i2, i2);
                if (read == -1) {
                    break;
                }
                i2 -= read;
            }
            return i - i2;
        }
    }

    public ImageHeaderParser(InputStream inputStream) {
        this.reader = new StreamReader(inputStream);
    }

    public int getOrientation() throws IOException {
        int uInt16 = this.reader.getUInt16();
        if (handles(uInt16)) {
            uInt16 = moveToExifSegmentAndGetLength();
            if (uInt16 != -1) {
                return parseExifSegment(new byte[uInt16], uInt16);
            }
            if (!Log.isLoggable(TAG, 3)) {
                return -1;
            }
            Log.d(TAG, "Failed to parse exif segment length, or exif segment not found");
            return -1;
        } else if (!Log.isLoggable(TAG, 3)) {
            return -1;
        } else {
            Log.d(TAG, "Parser doesn't handle magic number: " + uInt16);
            return -1;
        }
    }

    private int parseExifSegment(byte[] bArr, int i) throws IOException {
        int read = this.reader.read(bArr, i);
        if (read != i) {
            if (!Log.isLoggable(TAG, 3)) {
                return -1;
            }
            Log.d(TAG, "Unable to read exif segment data, length: " + i + ", actually read: " + read);
            return -1;
        } else if (hasJpegExifPreamble(bArr, i)) {
            return parseExifSegment(new RandomAccessReader(bArr, i));
        } else {
            if (!Log.isLoggable(TAG, 3)) {
                return -1;
            }
            Log.d(TAG, "Missing jpeg exif preamble");
            return -1;
        }
    }

    private boolean hasJpegExifPreamble(byte[] bArr, int i) {
        boolean z = bArr != null && i > JPEG_EXIF_SEGMENT_PREAMBLE_BYTES.length;
        if (z) {
            for (int i2 = 0; i2 < JPEG_EXIF_SEGMENT_PREAMBLE_BYTES.length; i2++) {
                if (bArr[i2] != JPEG_EXIF_SEGMENT_PREAMBLE_BYTES[i2]) {
                    return false;
                }
            }
        }
        return z;
    }

    private int moveToExifSegmentAndGetLength() throws IOException {
        long skip;
        int uInt16;
        short uInt8;
        do {
            short uInt82 = this.reader.getUInt8();
            if (uInt82 == (short) 255) {
                uInt8 = this.reader.getUInt8();
                if (uInt8 == (short) 218) {
                    return -1;
                }
                if (uInt8 != (short) 217) {
                    uInt16 = this.reader.getUInt16() - 2;
                    if (uInt8 == (short) 225) {
                        return uInt16;
                    }
                    skip = this.reader.skip((long) uInt16);
                } else if (!Log.isLoggable(TAG, 3)) {
                    return -1;
                } else {
                    Log.d(TAG, "Found MARKER_EOI in exif segment");
                    return -1;
                }
            } else if (!Log.isLoggable(TAG, 3)) {
                return -1;
            } else {
                Log.d(TAG, "Unknown segmentId=" + uInt82);
                return -1;
            }
        } while (skip == ((long) uInt16));
        if (!Log.isLoggable(TAG, 3)) {
            return -1;
        }
        Log.d(TAG, "Unable to skip enough data, type: " + uInt8 + ", wanted to skip: " + uInt16 + ", but actually skipped: " + skip);
        return -1;
    }

    private static int parseExifSegment(RandomAccessReader randomAccessReader) {
        ByteOrder byteOrder;
        int length = JPEG_EXIF_SEGMENT_PREAMBLE.length();
        short int16 = randomAccessReader.getInt16(length);
        if (int16 == (short) 19789) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        } else if (int16 == (short) 18761) {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        } else {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Unknown endianness = " + int16);
            }
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        randomAccessReader.order(byteOrder);
        length += randomAccessReader.getInt32(length + 4);
        short int162 = randomAccessReader.getInt16(length);
        for (int16 = (short) 0; int16 < int162; int16++) {
            int calcTagOffset = calcTagOffset(length, int16);
            short int163 = randomAccessReader.getInt16(calcTagOffset);
            if (int163 == (short) 274) {
                short int164 = randomAccessReader.getInt16(calcTagOffset + 2);
                if (int164 >= (short) 1 && int164 <= (short) 12) {
                    int int32 = randomAccessReader.getInt32(calcTagOffset + 4);
                    if (int32 >= 0) {
                        if (Log.isLoggable(TAG, 3)) {
                            Log.d(TAG, "Got tagIndex=" + int16 + " tagType=" + int163 + " formatCode=" + int164 + " componentCount=" + int32);
                        }
                        int32 += BYTES_PER_FORMAT[int164];
                        if (int32 <= 4) {
                            calcTagOffset += 8;
                            if (calcTagOffset < 0 || calcTagOffset > randomAccessReader.length()) {
                                if (Log.isLoggable(TAG, 3)) {
                                    Log.d(TAG, "Illegal tagValueOffset=" + calcTagOffset + " tagType=" + int163);
                                }
                            } else if (int32 >= 0 && calcTagOffset + int32 <= randomAccessReader.length()) {
                                return randomAccessReader.getInt16(calcTagOffset);
                            } else {
                                if (Log.isLoggable(TAG, 3)) {
                                    Log.d(TAG, "Illegal number of bytes for TI tag data tagType=" + int163);
                                }
                            }
                        } else if (Log.isLoggable(TAG, 3)) {
                            Log.d(TAG, "Got byte count > 4, not orientation, continuing, formatCode=" + int164);
                        }
                    } else if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "Negative tiff component count");
                    }
                } else if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Got invalid format code = " + int164);
                }
            }
        }
        return -1;
    }

    private static int calcTagOffset(int i, int i2) {
        return (i + 2) + (i2 * 12);
    }

    private static boolean handles(int i) {
        return (i & EXIF_MAGIC_NUMBER) == EXIF_MAGIC_NUMBER || i == MOTOROLA_TIFF_MAGIC_NUMBER || i == INTEL_TIFF_MAGIC_NUMBER;
    }

    public static void copyExif(ExifInterface exifInterface, int i, int i2, String str) {
        int i3 = 0;
        String[] strArr = new String[]{android.support.media.ExifInterface.TAG_F_NUMBER, android.support.media.ExifInterface.TAG_DATETIME, android.support.media.ExifInterface.TAG_DATETIME_DIGITIZED, android.support.media.ExifInterface.TAG_EXPOSURE_TIME, android.support.media.ExifInterface.TAG_FLASH, android.support.media.ExifInterface.TAG_FOCAL_LENGTH, android.support.media.ExifInterface.TAG_GPS_ALTITUDE, android.support.media.ExifInterface.TAG_GPS_ALTITUDE_REF, android.support.media.ExifInterface.TAG_GPS_DATESTAMP, android.support.media.ExifInterface.TAG_GPS_LATITUDE, android.support.media.ExifInterface.TAG_GPS_LATITUDE_REF, android.support.media.ExifInterface.TAG_GPS_LONGITUDE, android.support.media.ExifInterface.TAG_GPS_LONGITUDE_REF, android.support.media.ExifInterface.TAG_GPS_PROCESSING_METHOD, android.support.media.ExifInterface.TAG_GPS_TIMESTAMP, android.support.media.ExifInterface.TAG_ISO_SPEED_RATINGS, android.support.media.ExifInterface.TAG_MAKE, android.support.media.ExifInterface.TAG_MODEL, android.support.media.ExifInterface.TAG_SUBSEC_TIME, android.support.media.ExifInterface.TAG_SUBSEC_TIME_DIGITIZED, android.support.media.ExifInterface.TAG_SUBSEC_TIME_ORIGINAL, android.support.media.ExifInterface.TAG_WHITE_BALANCE};
        try {
            ExifInterface exifInterface2 = new ExifInterface(str);
            int length = strArr.length;
            while (i3 < length) {
                String str2 = strArr[i3];
                Object attribute = exifInterface.getAttribute(str2);
                if (!TextUtils.isEmpty(attribute)) {
                    exifInterface2.setAttribute(str2, attribute);
                }
                i3++;
            }
            exifInterface2.setAttribute(android.support.media.ExifInterface.TAG_IMAGE_WIDTH, String.valueOf(i));
            exifInterface2.setAttribute(android.support.media.ExifInterface.TAG_IMAGE_LENGTH, String.valueOf(i2));
            exifInterface2.setAttribute(android.support.media.ExifInterface.TAG_ORIENTATION, "0");
            exifInterface2.saveAttributes();
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }
    }
}
