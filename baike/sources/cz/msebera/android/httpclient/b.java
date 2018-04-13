package cz.msebera.android.httpclient;

final class b implements ExceptionLogger {
    b() {
    }

    public void log(Exception exception) {
        exception.printStackTrace();
    }
}
