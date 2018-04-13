package com.facebook.imagepipeline.producers;

import android.net.Uri;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.image.EncodedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HttpUrlConnectionNetworkFetcher extends BaseNetworkFetcher<FetchState> {
    public static final int HTTP_PERMANENT_REDIRECT = 308;
    public static final int HTTP_TEMPORARY_REDIRECT = 307;
    private static final int MAX_REDIRECTS = 5;
    private static final int NUM_NETWORK_THREADS = 3;
    private final ExecutorService mExecutorService;

    public HttpUrlConnectionNetworkFetcher() {
        this(Executors.newFixedThreadPool(3));
    }

    @VisibleForTesting
    HttpUrlConnectionNetworkFetcher(ExecutorService executorService) {
        this.mExecutorService = executorService;
    }

    public FetchState createFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        return new FetchState(consumer, producerContext);
    }

    public void fetch(final FetchState fetchState, final NetworkFetcher$Callback networkFetcher$Callback) {
        final Future submit = this.mExecutorService.submit(new Runnable() {
            public void run() {
                HttpUrlConnectionNetworkFetcher.this.fetchSync(fetchState, networkFetcher$Callback);
            }
        });
        fetchState.getContext().addCallbacks(new BaseProducerContextCallbacks() {
            public void onCancellationRequested() {
                if (submit.cancel(false)) {
                    networkFetcher$Callback.onCancellation();
                }
            }
        });
    }

    @VisibleForTesting
    void fetchSync(FetchState fetchState, NetworkFetcher$Callback networkFetcher$Callback) {
        HttpURLConnection downloadFrom;
        Throwable e;
        InputStream inputStream = null;
        try {
            downloadFrom = downloadFrom(fetchState.getUri(), 5);
            if (downloadFrom != null) {
                try {
                    inputStream = downloadFrom.getInputStream();
                    networkFetcher$Callback.onResponse(inputStream, -1);
                } catch (IOException e2) {
                    e = e2;
                    try {
                        networkFetcher$Callback.onFailure(e);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e3) {
                            }
                        }
                        if (downloadFrom != null) {
                            downloadFrom.disconnect();
                        }
                    } catch (Throwable th) {
                        e = th;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e4) {
                            }
                        }
                        if (downloadFrom != null) {
                            downloadFrom.disconnect();
                        }
                        throw e;
                    }
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                }
            }
            if (downloadFrom != null) {
                downloadFrom.disconnect();
            }
        } catch (IOException e6) {
            e = e6;
            downloadFrom = null;
            networkFetcher$Callback.onFailure(e);
            if (inputStream != null) {
                inputStream.close();
            }
            if (downloadFrom != null) {
                downloadFrom.disconnect();
            }
        } catch (Throwable th2) {
            e = th2;
            downloadFrom = null;
            if (inputStream != null) {
                inputStream.close();
            }
            if (downloadFrom != null) {
                downloadFrom.disconnect();
            }
            throw e;
        }
    }

    private HttpURLConnection downloadFrom(Uri uri, int i) throws IOException {
        HttpURLConnection openConnectionTo = openConnectionTo(uri);
        int responseCode = openConnectionTo.getResponseCode();
        if (isHttpSuccess(responseCode)) {
            return openConnectionTo;
        }
        if (isHttpRedirect(responseCode)) {
            String headerField = openConnectionTo.getHeaderField("Location");
            openConnectionTo.disconnect();
            Uri parse = headerField == null ? null : Uri.parse(headerField);
            headerField = uri.getScheme();
            if (i > 0 && parse != null && !parse.getScheme().equals(headerField)) {
                return downloadFrom(parse, i - 1);
            }
            String error;
            if (i == 0) {
                error = error("URL %s follows too many redirects", uri.toString());
            } else {
                error = error("URL %s returned %d without a valid redirect", uri.toString(), Integer.valueOf(responseCode));
            }
            throw new IOException(error);
        }
        openConnectionTo.disconnect();
        throw new IOException(String.format("Image URL %s returned HTTP code %d", new Object[]{uri.toString(), Integer.valueOf(responseCode)}));
    }

    @VisibleForTesting
    static HttpURLConnection openConnectionTo(Uri uri) throws IOException {
        return (HttpURLConnection) new URL(uri.toString()).openConnection();
    }

    private static boolean isHttpSuccess(int i) {
        return i >= 200 && i < 300;
    }

    private static boolean isHttpRedirect(int i) {
        switch (i) {
            case 300:
            case 301:
            case 302:
            case 303:
            case 307:
            case 308:
                return true;
            default:
                return false;
        }
    }

    private static String error(String str, Object... objArr) {
        return String.format(Locale.getDefault(), str, objArr);
    }
}
