package qsbk.app.utils.audit;

public interface RequestListener {
    public static final int DEFAULT_LOADED_SIZE = -2;
    public static final int DEFAULT_TOTAL_SIZE = -1;

    void onCanceled(Request request);

    void onDownloading(Request request, int i, int i2);

    void onError(Request request, LoaderException loaderException);

    void onPrepare(Request request, int i);

    void onSuccess(Request request, int i, byte[] bArr);
}
