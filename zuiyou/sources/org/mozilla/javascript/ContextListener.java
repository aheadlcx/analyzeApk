package org.mozilla.javascript;

@Deprecated
public interface ContextListener extends ContextFactory$Listener {
    @Deprecated
    void contextEntered(Context context);

    @Deprecated
    void contextExited(Context context);
}
