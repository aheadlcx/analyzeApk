package qsbk.app.im.emotion;

@Deprecated
public interface EmotionProvider {
    int getHeight();

    String[] getKeys();

    int[] getLocalResourceIds();

    String getNameSpace();

    String[] getNames();

    String[] getUrls();

    int getWidth();
}
