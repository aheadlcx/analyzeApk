package com.facebook.imageutils;

import android.support.v4.view.MotionEventCompat;
import android.util.Pair;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;

public class WebpUtil {
    private static final String VP8L_HEADER = "VP8L";
    private static final String VP8X_HEADER = "VP8X";
    private static final String VP8_HEADER = "VP8 ";

    private WebpUtil() {
    }

    @Nullable
    public static Pair<Integer, Integer> getSize(InputStream inputStream) {
        Pair<Integer, Integer> pair = null;
        byte[] bArr = new byte[4];
        try {
            inputStream.read(bArr);
            if (compare(bArr, "RIFF")) {
                getInt(inputStream);
                inputStream.read(bArr);
                if (compare(bArr, "WEBP")) {
                    inputStream.read(bArr);
                    String header = getHeader(bArr);
                    if (VP8_HEADER.equals(header)) {
                        pair = getVP8Dimension(inputStream);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (VP8L_HEADER.equals(header)) {
                        pair = getVP8LDimension(inputStream);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                    } else if (VP8X_HEADER.equals(header)) {
                        pair = getVP8XDimension(inputStream);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                    } else if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                } else if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e2222) {
                        e2222.printStackTrace();
                    }
                }
            } else if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e22222) {
                    e22222.printStackTrace();
                }
            }
        } catch (IOException e222222) {
            e222222.printStackTrace();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2222222) {
                    e2222222.printStackTrace();
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e22222222) {
                    e22222222.printStackTrace();
                }
            }
        }
        return pair;
    }

    private static Pair<Integer, Integer> getVP8Dimension(InputStream inputStream) throws IOException {
        inputStream.skip(7);
        short s = getShort(inputStream);
        short s2 = getShort(inputStream);
        short s3 = getShort(inputStream);
        if (s == (short) 157 && s2 == (short) 1 && s3 == (short) 42) {
            return new Pair(Integer.valueOf(get2BytesAsInt(inputStream)), Integer.valueOf(get2BytesAsInt(inputStream)));
        }
        return null;
    }

    private static Pair<Integer, Integer> getVP8LDimension(InputStream inputStream) throws IOException {
        getInt(inputStream);
        if (getByte(inputStream) != (byte) 47) {
            return null;
        }
        int read = ((byte) inputStream.read()) & 255;
        return new Pair(Integer.valueOf(((((byte) inputStream.read()) & 255) | ((read & 63) << 8)) + 1), Integer.valueOf((((((((byte) inputStream.read()) & 255) & 15) << 10) | ((((byte) inputStream.read()) & 255) << 2)) | ((read & JfifUtil.MARKER_SOFn) >> 6)) + 1));
    }

    private static Pair<Integer, Integer> getVP8XDimension(InputStream inputStream) throws IOException {
        inputStream.skip(8);
        return new Pair(Integer.valueOf(read3Bytes(inputStream) + 1), Integer.valueOf(read3Bytes(inputStream) + 1));
    }

    private static boolean compare(byte[] bArr, String str) {
        if (bArr.length != str.length()) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (str.charAt(i) != bArr[i]) {
                return false;
            }
        }
        return true;
    }

    private static String getHeader(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            stringBuilder.append((char) b);
        }
        return stringBuilder.toString();
    }

    private static int getInt(InputStream inputStream) throws IOException {
        return (((byte) inputStream.read()) & 255) | (((((byte) inputStream.read()) << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (((((byte) inputStream.read()) << 16) & 16711680) | ((((byte) inputStream.read()) << 24) & -16777216)));
    }

    public static int get2BytesAsInt(InputStream inputStream) throws IOException {
        return (((byte) inputStream.read()) & 255) | ((((byte) inputStream.read()) << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
    }

    private static int read3Bytes(InputStream inputStream) throws IOException {
        byte b = getByte(inputStream);
        return (b & 255) | (((getByte(inputStream) << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | ((getByte(inputStream) << 16) & 16711680));
    }

    private static short getShort(InputStream inputStream) throws IOException {
        return (short) (inputStream.read() & 255);
    }

    private static byte getByte(InputStream inputStream) throws IOException {
        return (byte) (inputStream.read() & 255);
    }

    private static boolean isBitOne(byte b, int i) {
        return ((b >> (i % 8)) & 1) == 1;
    }
}
