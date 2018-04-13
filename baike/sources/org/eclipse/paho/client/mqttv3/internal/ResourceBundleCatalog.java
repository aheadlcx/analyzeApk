package org.eclipse.paho.client.mqttv3.internal;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceBundleCatalog extends MessageCatalog {
    private ResourceBundle a = ResourceBundle.getBundle("org.eclipse.paho.client.mqttv3.internal.nls.messages");

    protected String a(int i) {
        try {
            return this.a.getString(Integer.toString(i));
        } catch (MissingResourceException e) {
            return "MqttException";
        }
    }
}
