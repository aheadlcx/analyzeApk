package org.eclipse.paho.client.mqttv3.persist;

import java.io.File;
import java.io.FilenameFilter;

class a implements FilenameFilter {
    a() {
    }

    public boolean accept(File file, String str) {
        return str.endsWith(".msg");
    }
}
