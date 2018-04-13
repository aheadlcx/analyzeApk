package qsbk.app.core.web.ui;

import android.app.Activity;
import android.content.Intent;
import qsbk.app.core.web.plugin.Plugin;

public interface WebInterface {
    Activity getCurActivity();

    void setFocusPlugin(Plugin plugin);

    void startActivityForResult(Plugin plugin, Intent intent, int i);
}
