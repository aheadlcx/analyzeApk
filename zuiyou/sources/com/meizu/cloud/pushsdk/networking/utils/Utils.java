package com.meizu.cloud.pushsdk.networking.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.os.NetworkOnMainThreadException;
import android.widget.ImageView.ScaleType;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.meizu.cloud.pushsdk.networking.common.ANConstants;
import com.meizu.cloud.pushsdk.networking.common.ANRequest;
import com.meizu.cloud.pushsdk.networking.common.ANResponse;
import com.meizu.cloud.pushsdk.networking.core.Core;
import com.meizu.cloud.pushsdk.networking.error.ANError;
import com.meizu.cloud.pushsdk.networking.http.Response;
import com.meizu.cloud.pushsdk.networking.interfaces.AnalyticsListener;
import com.meizu.cloud.pushsdk.networking.okio.Okio;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

public class Utils {
    public static File getDiskCacheDir(Context context, String str) {
        return new File(context.getCacheDir(), str);
    }

    public static String getMimeType(String str) {
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(str);
        if (contentTypeFor == null) {
            return OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE;
        }
        return contentTypeFor;
    }

    public static ANResponse<Bitmap> decodeBitmap(Response response, int i, int i2, Config config, ScaleType scaleType) {
        Object decodeByteArray;
        byte[] bArr = new byte[0];
        try {
            bArr = Okio.buffer(response.body().source()).readByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Options options = new Options();
        if (i == 0 && i2 == 0) {
            options.inPreferredConfig = config;
            decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        } else {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            int i3 = options.outWidth;
            int i4 = options.outHeight;
            int resizedDimension = getResizedDimension(i, i2, i3, i4, scaleType);
            int resizedDimension2 = getResizedDimension(i2, i, i4, i3, scaleType);
            options.inJustDecodeBounds = false;
            options.inSampleSize = findBestSampleSize(i3, i4, resizedDimension, resizedDimension2);
            Bitmap decodeByteArray2 = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            if (decodeByteArray2 == null || (decodeByteArray2.getWidth() <= resizedDimension && decodeByteArray2.getHeight() <= resizedDimension2)) {
                Bitmap bitmap = decodeByteArray2;
            } else {
                decodeByteArray = Bitmap.createScaledBitmap(decodeByteArray2, resizedDimension, resizedDimension2, true);
                decodeByteArray2.recycle();
            }
        }
        if (decodeByteArray == null) {
            return ANResponse.failed(getErrorForParse(new ANError(response)));
        }
        return ANResponse.success(decodeByteArray);
    }

    private static int getResizedDimension(int i, int i2, int i3, int i4, ScaleType scaleType) {
        if (i == 0 && i2 == 0) {
            return i3;
        }
        if (scaleType == ScaleType.FIT_XY) {
            if (i == 0) {
                return i3;
            }
            return i;
        } else if (i == 0) {
            return (int) ((((double) i2) / ((double) i4)) * ((double) i3));
        } else {
            if (i2 == 0) {
                return i;
            }
            double d = ((double) i4) / ((double) i3);
            if (scaleType == ScaleType.CENTER_CROP) {
                if (((double) i) * d < ((double) i2)) {
                    return (int) (((double) i2) / d);
                }
                return i;
            } else if (((double) i) * d > ((double) i2)) {
                return (int) (((double) i2) / d);
            } else {
                return i;
            }
        }
    }

    public static int findBestSampleSize(int i, int i2, int i3, int i4) {
        float f = 1.0f;
        while (((double) (f * 2.0f)) <= Math.min(((double) i) / ((double) i3), ((double) i2) / ((double) i4))) {
            f *= 2.0f;
        }
        return (int) f;
    }

    public static void saveFile(Response response, String str, String str2) throws IOException {
        Throwable th;
        InputStream inputStream;
        FileOutputStream fileOutputStream = null;
        byte[] bArr = new byte[2048];
        try {
            InputStream byteStream = response.body().byteStream();
            try {
                File file = new File(str);
                if (!file.exists()) {
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(new File(file, str2));
                while (true) {
                    try {
                        int read = byteStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream2.write(bArr, 0, read);
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = fileOutputStream2;
                        inputStream = byteStream;
                    }
                }
                fileOutputStream2.flush();
                if (byteStream != null) {
                    try {
                        byteStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                inputStream = byteStream;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            inputStream = null;
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    public static void sendAnalytics(AnalyticsListener analyticsListener, long j, long j2, long j3, boolean z) {
        final AnalyticsListener analyticsListener2 = analyticsListener;
        final long j4 = j;
        final long j5 = j2;
        final long j6 = j3;
        final boolean z2 = z;
        Core.getInstance().getExecutorSupplier().forMainThreadTasks().execute(new Runnable() {
            public void run() {
                if (analyticsListener2 != null) {
                    analyticsListener2.onReceived(j4, j5, j6, z2);
                }
            }
        });
    }

    public static ANError getErrorForConnection(ANError aNError) {
        aNError.setErrorDetail(ANConstants.CONNECTION_ERROR);
        aNError.setErrorCode(0);
        aNError.setErrorBody(aNError.getMessage());
        return aNError;
    }

    public static ANError getErrorForServerResponse(ANError aNError, ANRequest aNRequest, int i) {
        ANError parseNetworkError = aNRequest.parseNetworkError(aNError);
        parseNetworkError.setErrorCode(i);
        parseNetworkError.setErrorDetail(ANConstants.RESPONSE_FROM_SERVER_ERROR);
        return parseNetworkError;
    }

    public static ANError getErrorForParse(ANError aNError) {
        aNError.setErrorCode(0);
        aNError.setErrorDetail(ANConstants.PARSE_ERROR);
        aNError.setErrorBody(aNError.getMessage());
        return aNError;
    }

    public static ANError getErrorForNetworkOnMainThreadOrConnection(Exception exception) {
        ANError aNError = new ANError((Throwable) exception);
        if (VERSION.SDK_INT < 11 || !(exception instanceof NetworkOnMainThreadException)) {
            aNError.setErrorDetail(ANConstants.CONNECTION_ERROR);
        } else {
            aNError.setErrorDetail(ANConstants.NETWORK_ON_MAIN_THREAD_ERROR);
        }
        aNError.setErrorCode(0);
        return aNError;
    }
}
