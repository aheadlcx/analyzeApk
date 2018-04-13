package org.eclipse.paho.client.mqttv3.persist;

import java.io.File;
import java.io.FileFilter;

class b implements FileFilter {
    final MqttDefaultFilePersistence a;

    b(MqttDefaultFilePersistence mqttDefaultFilePersistence) {
        this.a = mqttDefaultFilePersistence;
    }

    public boolean accept(File file) {
        return file.getName().endsWith(".bup");
    }
}
