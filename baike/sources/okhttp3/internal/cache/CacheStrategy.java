package okhttp3.internal.cache;

import cz.msebera.android.httpclient.HttpStatus;
import javax.annotation.Nullable;
import okhttp3.Request;
import okhttp3.Response;

public final class CacheStrategy {
    @Nullable
    public final Response cacheResponse;
    @Nullable
    public final Request networkRequest;

    CacheStrategy(Request request, Response response) {
        this.networkRequest = request;
        this.cacheResponse = response;
    }

    public static boolean isCacheable(Response response, Request request) {
        switch (response.code()) {
            case 200:
            case 203:
            case HttpStatus.SC_NO_CONTENT /*204*/:
            case 300:
            case 301:
            case 308:
            case 404:
            case 405:
            case HttpStatus.SC_GONE /*410*/:
            case HttpStatus.SC_REQUEST_URI_TOO_LONG /*414*/:
            case 501:
                break;
            case 302:
            case 307:
                if (response.header("Expires") == null && response.cacheControl().maxAgeSeconds() == -1 && !response.cacheControl().isPublic() && !response.cacheControl().isPrivate()) {
                    return false;
                }
            default:
                return false;
        }
        return (response.cacheControl().noStore() || request.cacheControl().noStore()) ? false : true;
    }
}
