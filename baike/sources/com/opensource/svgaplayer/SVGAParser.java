package com.opensource.svgaplayer;

import android.content.Context;
import android.os.Handler;
import com.alipay.sdk.authjs.a;
import com.baidu.mobads.openad.c.b;
import com.opensource.svgaplayer.proto.MovieEntity;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.zip.Inflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0002 !B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000eH\u0002J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0002J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\r\u001a\u00020\u000eJ\u001e\u0010\u0015\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001bJ\u0016\u0010\u0015\u001a\u00020\u00192\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001bJ\u0016\u0010\u0015\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001bJ\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u00162\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\""}, d2 = {"Lcom/opensource/svgaplayer/SVGAParser;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "fileDownloader", "Lcom/opensource/svgaplayer/SVGAParser$FileDownloader;", "getFileDownloader", "()Lcom/opensource/svgaplayer/SVGAParser$FileDownloader;", "setFileDownloader", "(Lcom/opensource/svgaplayer/SVGAParser$FileDownloader;)V", "cacheDir", "Ljava/io/File;", "cacheKey", "", "url", "Ljava/net/URL;", "str", "inflate", "", "byteArray", "parse", "Lcom/opensource/svgaplayer/SVGAVideoEntity;", "inputStream", "Ljava/io/InputStream;", "", "callback", "Lcom/opensource/svgaplayer/SVGAParser$ParseCompletion;", "assetsName", "parseWithCacheKey", "readAsBytes", "unzip", "FileDownloader", "ParseCompletion", "library_release"}, k = 1, mv = {1, 1, 6})
public final class SVGAParser {
    @NotNull
    private FileDownloader a = new FileDownloader();
    private final Context b;

    @Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007"}, d2 = {"Lcom/opensource/svgaplayer/SVGAParser$ParseCompletion;", "", "onComplete", "", "videoItem", "Lcom/opensource/svgaplayer/SVGAVideoEntity;", "onError", "library_release"}, k = 1, mv = {1, 1, 6})
    public interface ParseCompletion {
        void onComplete(@NotNull SVGAVideoEntity sVGAVideoEntity);

        void onError();
    }

    @Metadata(bv = {1, 0, 1}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JZ\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062!\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00040\b2%\u0010\r\u001a!\u0012\u0017\u0012\u00150\u000ej\u0002`\u000f¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00040\bH\u0016¨\u0006\u0011"}, d2 = {"Lcom/opensource/svgaplayer/SVGAParser$FileDownloader;", "", "()V", "resume", "", "url", "Ljava/net/URL;", "complete", "Lkotlin/Function1;", "Ljava/io/InputStream;", "Lkotlin/ParameterName;", "name", "inputStream", "failure", "Ljava/lang/Exception;", "Lkotlin/Exception;", "e", "library_release"}, k = 1, mv = {1, 1, 6})
    public static class FileDownloader {
        public void resume(@NotNull URL url, @NotNull Function1<? super InputStream, Unit> function1, @NotNull Function1<? super Exception, Unit> function12) {
            Intrinsics.checkParameterIsNotNull(url, "url");
            Intrinsics.checkParameterIsNotNull(function1, b.COMPLETE);
            Intrinsics.checkParameterIsNotNull(function12, "failure");
            new Thread(new g(url, function1, function12)).start();
        }
    }

    public SVGAParser(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, com.umeng.analytics.pro.b.M);
        this.b = context;
    }

    @NotNull
    public final FileDownloader getFileDownloader() {
        return this.a;
    }

    public final void setFileDownloader(@NotNull FileDownloader fileDownloader) {
        Intrinsics.checkParameterIsNotNull(fileDownloader, "<set-?>");
        this.a = fileDownloader;
    }

    public final void parse(@NotNull String str, @NotNull ParseCompletion parseCompletion) {
        Intrinsics.checkParameterIsNotNull(str, "assetsName");
        Intrinsics.checkParameterIsNotNull(parseCompletion, a.c);
        try {
            InputStream open = this.b.getAssets().open(str);
            if (open != null) {
                parse(open, b("file:///assets/" + str), parseCompletion);
            }
        } catch (Exception e) {
        }
    }

    public final void parse(@NotNull URL url, @NotNull ParseCompletion parseCompletion) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(parseCompletion, a.c);
        if (c(a(url)).exists()) {
            SVGAVideoEntity a = a(a(url));
            if (a != null) {
                new Handler(this.b.getMainLooper()).post(new h(a, this, parseCompletion));
                return;
            }
        }
        this.a.resume(url, new i(this, url, parseCompletion), new l(this, parseCompletion));
    }

    public final void parse(@NotNull InputStream inputStream, @NotNull String str, @NotNull ParseCompletion parseCompletion) {
        Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
        Intrinsics.checkParameterIsNotNull(str, "cacheKey");
        Intrinsics.checkParameterIsNotNull(parseCompletion, a.c);
        new Thread(new n(this, inputStream, str, parseCompletion)).start();
    }

    @Nullable
    public final SVGAVideoEntity parse(@NotNull InputStream inputStream, @NotNull String str) {
        File file;
        Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
        Intrinsics.checkParameterIsNotNull(str, "cacheKey");
        byte[] a = a(inputStream);
        Object decode;
        if (a.length > 4 && a[0] == (byte) 80 && a[1] == (byte) 75 && a[2] == (byte) 3 && a[3] == (byte) 4) {
            synchronized (Integer.valueOf(SVGAParserKt.a)) {
                if (!c(str).exists()) {
                    try {
                        a(new ByteArrayInputStream(a), str);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
            File file2;
            File file3;
            try {
                file = new File(this.b.getCacheDir().getAbsolutePath() + MqttTopic.TOPIC_LEVEL_SEPARATOR + str + MqttTopic.TOPIC_LEVEL_SEPARATOR);
                File file4 = new File(file, "movie.binary");
                if (file4.isFile()) {
                    file2 = file4;
                } else {
                    file2 = null;
                }
                if (file2 != null) {
                    FileInputStream fileInputStream = new FileInputStream(file2);
                    decode = MovieEntity.ADAPTER.decode(fileInputStream);
                    Intrinsics.checkExpressionValueIsNotNull(decode, "MovieEntity.ADAPTER.decode(it)");
                    SVGAVideoEntity sVGAVideoEntity = new SVGAVideoEntity((MovieEntity) decode, file);
                    fileInputStream.close();
                    return sVGAVideoEntity;
                }
                file4 = new File(file, "movie.spec");
                file3 = file4.isFile() ? file4 : null;
                if (file3 != null) {
                    FileInputStream fileInputStream2 = new FileInputStream(file3);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[2048];
                    while (true) {
                        int read = fileInputStream2.read(bArr, 0, bArr.length);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    String byteArrayOutputStream2 = byteArrayOutputStream.toString();
                    if (byteArrayOutputStream2 != null) {
                        JSONObject jSONObject = new JSONObject(byteArrayOutputStream2);
                        fileInputStream2.close();
                        return new SVGAVideoEntity(jSONObject, file);
                    }
                    throw null;
                }
            } catch (Exception e2) {
                file.delete();
                file3.delete();
                throw e2;
            } catch (Exception e22) {
                file.delete();
                file2.delete();
                throw e22;
            } catch (Exception e222) {
                e222.printStackTrace();
            }
        } else {
            try {
                byte[] a2 = a(a);
                if (a2 != null) {
                    decode = MovieEntity.ADAPTER.decode(a2);
                    Intrinsics.checkExpressionValueIsNotNull(decode, "MovieEntity.ADAPTER.decode(it)");
                    return new SVGAVideoEntity((MovieEntity) decode, new File(str));
                }
            } catch (Exception e2222) {
                e2222.printStackTrace();
            }
        }
        return null;
    }

    private final SVGAVideoEntity a(String str) {
        File file;
        File file2;
        File file3;
        try {
            file = new File(this.b.getCacheDir().getAbsolutePath() + MqttTopic.TOPIC_LEVEL_SEPARATOR + str + MqttTopic.TOPIC_LEVEL_SEPARATOR);
            File file4 = new File(file, "movie.binary");
            file2 = file4.isFile() ? file4 : null;
            if (file2 != null) {
                FileInputStream fileInputStream = new FileInputStream(file2);
                Object decode = MovieEntity.ADAPTER.decode(fileInputStream);
                Intrinsics.checkExpressionValueIsNotNull(decode, "MovieEntity.ADAPTER.decode(it)");
                SVGAVideoEntity sVGAVideoEntity = new SVGAVideoEntity((MovieEntity) decode, file);
                fileInputStream.close();
                return sVGAVideoEntity;
            }
            file4 = new File(file, "movie.spec");
            file3 = file4.isFile() ? file4 : null;
            if (file3 != null) {
                FileInputStream fileInputStream2 = new FileInputStream(file3);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[2048];
                while (true) {
                    int read = fileInputStream2.read(bArr, 0, bArr.length);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                String byteArrayOutputStream2 = byteArrayOutputStream.toString();
                if (byteArrayOutputStream2 != null) {
                    JSONObject jSONObject = new JSONObject(byteArrayOutputStream2);
                    fileInputStream2.close();
                    return new SVGAVideoEntity(jSONObject, file);
                }
                throw null;
            }
            return null;
        } catch (Exception e) {
            file.delete();
            file3.delete();
            throw e;
        } catch (Exception e2) {
            file.delete();
            file2.delete();
            throw e2;
        } catch (Exception e22) {
            e22.printStackTrace();
        }
    }

    private final String b(String str) {
        MessageDigest instance = MessageDigest.getInstance("MD5");
        Charset forName = Charset.forName("UTF-8");
        Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(charsetName)");
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        Object bytes = str.getBytes(forName);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        instance.update(bytes);
        byte[] digest = instance.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : digest) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = new Object[]{Byte.valueOf(b)};
            String format = String.format("%02x", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            stringBuffer.append(format);
        }
        String stringBuffer2 = stringBuffer.toString();
        Intrinsics.checkExpressionValueIsNotNull(stringBuffer2, "sb.toString()");
        return stringBuffer2;
    }

    private final String a(URL url) {
        String url2 = url.toString();
        Intrinsics.checkExpressionValueIsNotNull(url2, "url.toString()");
        return b(url2);
    }

    private final File c(String str) {
        return new File(this.b.getCacheDir().getAbsolutePath() + MqttTopic.TOPIC_LEVEL_SEPARATOR + str + MqttTopic.TOPIC_LEVEL_SEPARATOR);
    }

    private final byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[2048];
        while (true) {
            int read = inputStream.read(bArr, 0, 2048);
            if (read <= 0) {
                Object toByteArray = byteArrayOutputStream.toByteArray();
                Intrinsics.checkExpressionValueIsNotNull(toByteArray, "byteArrayOutputStream.toByteArray()");
                return toByteArray;
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    private final byte[] a(byte[] bArr) {
        try {
            Inflater inflater = new Inflater();
            inflater.setInput(bArr, 0, bArr.length);
            byte[] bArr2 = new byte[2048];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int inflate = inflater.inflate(bArr2, 0, 2048);
                if (inflate <= 0) {
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr2, 0, inflate);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private final void a(InputStream inputStream, String str) {
        File c = c(str);
        c.mkdirs();
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(inputStream));
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                zipInputStream.close();
                return;
            } else if (!u.contains$default((CharSequence) nextEntry.getName(), (CharSequence) MqttTopic.TOPIC_LEVEL_SEPARATOR, false, 2, null)) {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(c, nextEntry.getName()));
                byte[] bArr = new byte[2048];
                while (true) {
                    int read = zipInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.close();
                zipInputStream.closeEntry();
            }
        }
    }
}
