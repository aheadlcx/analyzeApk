package org.eclipse.paho.client.mqttv3.persist;

import java.util.Enumeration;
import java.util.Hashtable;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class MemoryPersistence implements MqttClientPersistence {
    private Hashtable a;

    public void close() throws MqttPersistenceException {
        this.a.clear();
    }

    public Enumeration keys() throws MqttPersistenceException {
        return this.a.keys();
    }

    public MqttPersistable get(String str) throws MqttPersistenceException {
        return (MqttPersistable) this.a.get(str);
    }

    public void open(String str, String str2) throws MqttPersistenceException {
        this.a = new Hashtable();
    }

    public void put(String str, MqttPersistable mqttPersistable) throws MqttPersistenceException {
        this.a.put(str, mqttPersistable);
    }

    public void remove(String str) throws MqttPersistenceException {
        this.a.remove(str);
    }

    public void clear() throws MqttPersistenceException {
        this.a.clear();
    }

    public boolean containsKey(String str) throws MqttPersistenceException {
        return this.a.containsKey(str);
    }
}
