package okhttp3;

import cz.msebera.android.httpclient.entity.mime.MIME;
import javax.annotation.Nullable;

public final class MultipartBody$Part {
    @Nullable
    final Headers a;
    final RequestBody b;

    public static MultipartBody$Part create(RequestBody requestBody) {
        return create(null, requestBody);
    }

    public static MultipartBody$Part create(@Nullable Headers headers, RequestBody requestBody) {
        if (requestBody == null) {
            throw new NullPointerException("body == null");
        } else if (headers != null && headers.get("Content-Type") != null) {
            throw new IllegalArgumentException("Unexpected header: Content-Type");
        } else if (headers == null || headers.get("Content-Length") == null) {
            return new MultipartBody$Part(headers, requestBody);
        } else {
            throw new IllegalArgumentException("Unexpected header: Content-Length");
        }
    }

    public static MultipartBody$Part createFormData(String str, String str2) {
        return createFormData(str, null, RequestBody.create(null, str2));
    }

    public static MultipartBody$Part createFormData(String str, @Nullable String str2, RequestBody requestBody) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        StringBuilder stringBuilder = new StringBuilder("form-data; name=");
        MultipartBody.a(stringBuilder, str);
        if (str2 != null) {
            stringBuilder.append("; filename=");
            MultipartBody.a(stringBuilder, str2);
        }
        return create(Headers.of(MIME.CONTENT_DISPOSITION, stringBuilder.toString()), requestBody);
    }

    private MultipartBody$Part(@Nullable Headers headers, RequestBody requestBody) {
        this.a = headers;
        this.b = requestBody;
    }

    @Nullable
    public Headers headers() {
        return this.a;
    }

    public RequestBody body() {
        return this.b;
    }
}
