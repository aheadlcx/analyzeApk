package qsbk.app.im.datastore;

import qsbk.app.DispatchQueue;

public class Util {
    public static volatile DispatchQueue imStorageQueue = new DispatchQueue("imStorageQueue");
    public static volatile DispatchQueue imSyncTimer = new DispatchQueue("pingTimer");
}
