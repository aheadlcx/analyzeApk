package org.mozilla.javascript;

public interface ContextFactory$Listener {
    void contextCreated(Context context);

    void contextReleased(Context context);
}
