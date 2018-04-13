package com.facebook.d;

import android.support.v4.view.ViewCompat;
import android.util.Pair;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;

public class e {
    @Nullable
    public static Pair<Integer, Integer> a(InputStream inputStream) {
        Pair<Integer, Integer> pair = null;
        byte[] bArr = new byte[4];
        try {
            inputStream.read(bArr);
            if (a(bArr, "RIFF")) {
                f(inputStream);
                inputStream.read(bArr);
                if (a(bArr, "WEBP")) {
                    inputStream.read(bArr);
                    String a = a(bArr);
                    if ("VP8 ".equals(a)) {
                        pair = c(inputStream);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else if ("VP8L".equals(a)) {
                        pair = d(inputStream);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                    } else if ("VP8X".equals(a)) {
                        pair = e(inputStream);
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

    private static Pair<Integer, Integer> c(InputStream inputStream) throws IOException {
        inputStream.skip(7);
        short h = h(inputStream);
        short h2 = h(inputStream);
        short h3 = h(inputStream);
        if (h == (short) 157 && h2 == (short) 1 && h3 == (short) 42) {
            return new Pair(Integer.valueOf(b(inputStream)), Integer.valueOf(b(inputStream)));
        }
        return null;
    }

    private static Pair<Integer, Integer> d(InputStream inputStream) throws IOException {
        f(inputStream);
        if (i(inputStream) != (byte) 47) {
            return null;
        }
        int read = ((byte) inputStream.read()) & 255;
        return new Pair(Integer.valueOf(((((byte) inputStream.read()) & 255) | ((read & 63) << 8)) + 1), Integer.valueOf((((((((byte) inputStream.read()) & 255) & 15) << 10) | ((((byte) inputStream.read()) & 255) << 2)) | ((read & 192) >> 6)) + 1));
    }

    private static Pair<Integer, Integer> e(InputStream inputStream) throws IOException {
        inputStream.skip(8);
        return new Pair(Integer.valueOf(g(inputStream) + 1), Integer.valueOf(g(inputStream) + 1));
    }

    private static boolean a(byte[] bArr, String str) {
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

    private static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            stringBuilder.append((char) b);
        }
        return stringBuilder.toString();
    }

    private static int f(InputStream inputStream) throws IOException {
        return (((byte) inputStream.read()) & 255) | (((((byte) inputStream.read()) << 8) & 65280) | (((((byte) inputStream.read()) << 16) & 16711680) | ((((byte) inputStream.read()) << 24) & ViewCompat.MEASURED_STATE_MASK)));
    }

    public static int b(InputStream inputStream) throws IOException {
        return (((byte) inputStream.read()) & 255) | ((((byte) inputStream.read()) << 8) & 65280);
    }

    private static int g(InputStream inputStream) throws IOException {
        byte i = i(inputStream);
        return (i & 255) | (((i(inputStream) << 8) & 65280) | ((i(inputStream) << 16) & 16711680));
    }

    private static short h(InputStream inputStream) throws IOException {
        return (short) (inputStream.read() & 255);
    }

    private static byte i(InputStream inputStream) throws IOException {
        return (byte) (inputStream.read() & 255);
    }
}
