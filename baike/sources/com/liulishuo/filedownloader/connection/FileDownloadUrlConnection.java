package com.liulishuo.filedownloader.connection;

import com.liulishuo.filedownloader.util.FileDownloadHelper.ConnectionCreator;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class FileDownloadUrlConnection implements FileDownloadConnection {
    protected URLConnection a;

    public static class Configuration {
        private Proxy a;
        private Integer b;
        private Integer c;

        public Configuration proxy(Proxy proxy) {
            this.a = proxy;
            return this;
        }

        public Configuration readTimeout(int i) {
            this.b = Integer.valueOf(i);
            return this;
        }

        public Configuration connectTimeout(int i) {
            this.c = Integer.valueOf(i);
            return this;
        }
    }

    public static class Creator implements ConnectionCreator {
        private final Configuration a;

        public Creator() {
            this(null);
        }

        public Creator(Configuration configuration) {
            this.a = configuration;
        }

        public FileDownloadConnection create(String str) throws IOException {
            return new FileDownloadUrlConnection(str, this.a);
        }
    }

    public FileDownloadUrlConnection(String str, Configuration configuration) throws IOException {
        this(new URL(str), configuration);
    }

    public FileDownloadUrlConnection(URL url, Configuration configuration) throws IOException {
        if (configuration == null || configuration.a == null) {
            this.a = url.openConnection();
        } else {
            this.a = url.openConnection(configuration.a);
        }
        if (configuration != null) {
            if (configuration.b != null) {
                this.a.setReadTimeout(configuration.b.intValue());
            }
            if (configuration.c != null) {
                this.a.setConnectTimeout(configuration.c.intValue());
            }
        }
    }

    public FileDownloadUrlConnection(String str) throws IOException {
        this(str, null);
    }

    public void addHeader(String str, String str2) {
        this.a.addRequestProperty(str, str2);
    }

    public boolean dispatchAddResumeOffset(String str, long j) {
        return false;
    }

    public InputStream getInputStream() throws IOException {
        return this.a.getInputStream();
    }

    public Map<String, List<String>> getRequestHeaderFields() {
        return this.a.getRequestProperties();
    }

    public Map<String, List<String>> getResponseHeaderFields() {
        return this.a.getHeaderFields();
    }

    public String getResponseHeaderField(String str) {
        return this.a.getHeaderField(str);
    }

    public void execute() throws IOException {
        this.a.connect();
    }

    public int getResponseCode() throws IOException {
        if (this.a instanceof HttpURLConnection) {
            return ((HttpURLConnection) this.a).getResponseCode();
        }
        return 0;
    }

    public void ending() {
    }
}
