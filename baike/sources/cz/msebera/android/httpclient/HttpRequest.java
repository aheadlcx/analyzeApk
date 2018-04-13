package cz.msebera.android.httpclient;

public interface HttpRequest extends HttpMessage {
    RequestLine getRequestLine();
}
