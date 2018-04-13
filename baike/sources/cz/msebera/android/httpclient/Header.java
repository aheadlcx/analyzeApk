package cz.msebera.android.httpclient;

public interface Header {
    HeaderElement[] getElements() throws ParseException;

    String getName();

    String getValue();
}
