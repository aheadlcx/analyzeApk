package cz.msebera.android.httpclient.conn.util;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.util.Args;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

@ThreadSafe
public final class PublicSuffixMatcherLoader {
    private static volatile PublicSuffixMatcher a;

    private static PublicSuffixMatcher a(InputStream inputStream) throws IOException {
        PublicSuffixList parse = new PublicSuffixListParser().parse(new InputStreamReader(inputStream, Consts.UTF_8));
        return new PublicSuffixMatcher(parse.getRules(), parse.getExceptions());
    }

    public static PublicSuffixMatcher load(URL url) throws IOException {
        Args.notNull(url, "URL");
        InputStream openStream = url.openStream();
        try {
            PublicSuffixMatcher a = a(openStream);
            return a;
        } finally {
            openStream.close();
        }
    }

    public static PublicSuffixMatcher load(File file) throws IOException {
        Args.notNull(file, "File");
        InputStream fileInputStream = new FileInputStream(file);
        try {
            PublicSuffixMatcher a = a(fileInputStream);
            return a;
        } finally {
            fileInputStream.close();
        }
    }

    public static PublicSuffixMatcher getDefault() {
        if (a == null) {
            synchronized (PublicSuffixMatcherLoader.class) {
                if (a == null) {
                    URL resource = PublicSuffixMatcherLoader.class.getResource("/mozilla/public-suffix-list.txt");
                    if (resource != null) {
                        try {
                            a = load(resource);
                        } catch (Throwable e) {
                            HttpClientAndroidLog httpClientAndroidLog = new HttpClientAndroidLog(PublicSuffixMatcherLoader.class);
                            if (httpClientAndroidLog.isWarnEnabled()) {
                                httpClientAndroidLog.warn("Failure loading public suffix list from default resource", e);
                            }
                        }
                    } else {
                        a = new PublicSuffixMatcher(Arrays.asList(new String[]{"com"}), null);
                    }
                }
            }
        }
        return a;
    }
}
