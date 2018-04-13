package qsbk.app.loader;

public interface OnAsyncLoadListener {
    void onFinishListener(String str);

    void onPrepareListener();

    String onStartListener();
}
