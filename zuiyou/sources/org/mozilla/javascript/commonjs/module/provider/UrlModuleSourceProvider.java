package org.mozilla.javascript.commonjs.module.provider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

public class UrlModuleSourceProvider extends ModuleSourceProviderBase {
    private static final long serialVersionUID = 1;
    private final Iterable<URI> fallbackUris;
    private final Iterable<URI> privilegedUris;
    private final UrlConnectionExpiryCalculator urlConnectionExpiryCalculator;
    private final UrlConnectionSecurityDomainProvider urlConnectionSecurityDomainProvider;

    private static class URLValidator implements Serializable {
        private static final long serialVersionUID = 1;
        private final String entityTags;
        private long expiry;
        private final long lastModified;
        private final URI uri;

        public URLValidator(URI uri, URLConnection uRLConnection, long j, UrlConnectionExpiryCalculator urlConnectionExpiryCalculator) {
            this.uri = uri;
            this.lastModified = uRLConnection.getLastModified();
            this.entityTags = getEntityTags(uRLConnection);
            this.expiry = calculateExpiry(uRLConnection, j, urlConnectionExpiryCalculator);
        }

        boolean updateValidator(URLConnection uRLConnection, long j, UrlConnectionExpiryCalculator urlConnectionExpiryCalculator) throws IOException {
            boolean isResourceChanged = isResourceChanged(uRLConnection);
            if (!isResourceChanged) {
                this.expiry = calculateExpiry(uRLConnection, j, urlConnectionExpiryCalculator);
            }
            return isResourceChanged;
        }

        private boolean isResourceChanged(URLConnection uRLConnection) throws IOException {
            if (uRLConnection instanceof HttpURLConnection) {
                if (((HttpURLConnection) uRLConnection).getResponseCode() == 304) {
                    return true;
                }
                return false;
            } else if (this.lastModified != uRLConnection.getLastModified()) {
                return false;
            } else {
                return true;
            }
        }

        private long calculateExpiry(URLConnection uRLConnection, long j, UrlConnectionExpiryCalculator urlConnectionExpiryCalculator) {
            if ("no-cache".equals(uRLConnection.getHeaderField("Pragma"))) {
                return 0;
            }
            String headerField = uRLConnection.getHeaderField("Cache-Control");
            if (headerField != null) {
                if (headerField.indexOf("no-cache") != -1) {
                    return 0;
                }
                int maxAge = getMaxAge(headerField);
                if (-1 != maxAge) {
                    long currentTimeMillis = System.currentTimeMillis();
                    return (currentTimeMillis - (Math.max(Math.max(0, currentTimeMillis - uRLConnection.getDate()), ((long) uRLConnection.getHeaderFieldInt("Age", 0)) * 1000) + (currentTimeMillis - j))) + (((long) maxAge) * 1000);
                }
            }
            long headerFieldDate = uRLConnection.getHeaderFieldDate("Expires", -1);
            if (headerFieldDate != -1) {
                return headerFieldDate;
            }
            if (urlConnectionExpiryCalculator != null) {
                return urlConnectionExpiryCalculator.calculateExpiry(uRLConnection);
            }
            return 0;
        }

        private int getMaxAge(String str) {
            int i = -1;
            int indexOf = str.indexOf("max-age");
            if (indexOf != i) {
                indexOf = str.indexOf(61, indexOf + 7);
                if (indexOf != i) {
                    String substring;
                    int indexOf2 = str.indexOf(44, indexOf + 1);
                    if (indexOf2 == i) {
                        substring = str.substring(indexOf + 1);
                    } else {
                        substring = str.substring(indexOf + 1, indexOf2);
                    }
                    try {
                        i = Integer.parseInt(substring);
                    } catch (NumberFormatException e) {
                    }
                }
            }
            return i;
        }

        private String getEntityTags(URLConnection uRLConnection) {
            List list = (List) uRLConnection.getHeaderFields().get("ETag");
            if (list == null || list.isEmpty()) {
                return null;
            }
            StringBuilder stringBuilder = new StringBuilder();
            Iterator it = list.iterator();
            stringBuilder.append((String) it.next());
            while (it.hasNext()) {
                stringBuilder.append(", ").append((String) it.next());
            }
            return stringBuilder.toString();
        }

        boolean appliesTo(URI uri) {
            return this.uri.equals(uri);
        }

        void applyConditionals(URLConnection uRLConnection) {
            if (this.lastModified != 0) {
                uRLConnection.setIfModifiedSince(this.lastModified);
            }
            if (this.entityTags != null && this.entityTags.length() > 0) {
                uRLConnection.addRequestProperty("If-None-Match", this.entityTags);
            }
        }

        boolean entityNeedsRevalidation() {
            return System.currentTimeMillis() > this.expiry;
        }
    }

    public UrlModuleSourceProvider(Iterable<URI> iterable, Iterable<URI> iterable2) {
        this(iterable, iterable2, new DefaultUrlConnectionExpiryCalculator(), null);
    }

    public UrlModuleSourceProvider(Iterable<URI> iterable, Iterable<URI> iterable2, UrlConnectionExpiryCalculator urlConnectionExpiryCalculator, UrlConnectionSecurityDomainProvider urlConnectionSecurityDomainProvider) {
        this.privilegedUris = iterable;
        this.fallbackUris = iterable2;
        this.urlConnectionExpiryCalculator = urlConnectionExpiryCalculator;
        this.urlConnectionSecurityDomainProvider = urlConnectionSecurityDomainProvider;
    }

    protected ModuleSource loadFromPrivilegedLocations(String str, Object obj) throws IOException, URISyntaxException {
        return loadFromPathList(str, obj, this.privilegedUris);
    }

    protected ModuleSource loadFromFallbackLocations(String str, Object obj) throws IOException, URISyntaxException {
        return loadFromPathList(str, obj, this.fallbackUris);
    }

    private ModuleSource loadFromPathList(String str, Object obj, Iterable<URI> iterable) throws IOException, URISyntaxException {
        if (iterable == null) {
            return null;
        }
        for (URI uri : iterable) {
            ModuleSource loadFromUri = loadFromUri(uri.resolve(str), uri, obj);
            if (loadFromUri != null) {
                return loadFromUri;
            }
        }
        return null;
    }

    protected ModuleSource loadFromUri(URI uri, URI uri2, Object obj) throws IOException, URISyntaxException {
        ModuleSource loadFromActualUri = loadFromActualUri(new URI(uri + ".js"), uri2, obj);
        return loadFromActualUri != null ? loadFromActualUri : loadFromActualUri(uri, uri2, obj);
    }

    protected ModuleSource loadFromActualUri(URI uri, URI uri2, Object obj) throws IOException {
        URL url = new URL(uri2 == null ? null : uri2.toURL(), uri.toString());
        long currentTimeMillis = System.currentTimeMillis();
        URLConnection openUrlConnection = openUrlConnection(url);
        if (obj instanceof URLValidator) {
            obj = (URLValidator) obj;
            if (!obj.appliesTo(uri)) {
                obj = null;
            }
        } else {
            obj = null;
        }
        if (obj != null) {
            obj.applyConditionals(openUrlConnection);
        }
        try {
            openUrlConnection.connect();
            if (obj == null || !obj.updateValidator(openUrlConnection, currentTimeMillis, this.urlConnectionExpiryCalculator)) {
                return new ModuleSource(getReader(openUrlConnection), getSecurityDomain(openUrlConnection), uri, uri2, new URLValidator(uri, openUrlConnection, currentTimeMillis, this.urlConnectionExpiryCalculator));
            }
            close(openUrlConnection);
            return NOT_MODIFIED;
        } catch (FileNotFoundException e) {
            return null;
        } catch (RuntimeException e2) {
            close(openUrlConnection);
            throw e2;
        } catch (IOException e3) {
            close(openUrlConnection);
            throw e3;
        }
    }

    private static Reader getReader(URLConnection uRLConnection) throws IOException {
        return new InputStreamReader(uRLConnection.getInputStream(), getCharacterEncoding(uRLConnection));
    }

    private static String getCharacterEncoding(URLConnection uRLConnection) {
        ParsedContentType parsedContentType = new ParsedContentType(uRLConnection.getContentType());
        String encoding = parsedContentType.getEncoding();
        if (encoding != null) {
            return encoding;
        }
        encoding = parsedContentType.getContentType();
        if (encoding == null || !encoding.startsWith("text/")) {
            return "utf-8";
        }
        return "8859_1";
    }

    private Object getSecurityDomain(URLConnection uRLConnection) {
        return this.urlConnectionSecurityDomainProvider == null ? null : this.urlConnectionSecurityDomainProvider.getSecurityDomain(uRLConnection);
    }

    private void close(URLConnection uRLConnection) {
        try {
            uRLConnection.getInputStream().close();
        } catch (IOException e) {
            onFailedClosingUrlConnection(uRLConnection, e);
        }
    }

    protected void onFailedClosingUrlConnection(URLConnection uRLConnection, IOException iOException) {
    }

    protected URLConnection openUrlConnection(URL url) throws IOException {
        return url.openConnection();
    }

    protected boolean entityNeedsRevalidation(Object obj) {
        return !(obj instanceof URLValidator) || ((URLValidator) obj).entityNeedsRevalidation();
    }
}
