package com.facebook.stetho.inspector.protocol.module;

import android.util.SparseArray;
import com.facebook.stetho.inspector.helper.ObjectIdMapper;
import com.facebook.stetho.inspector.helper.PeersRegisteredListener;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.protocol.module.Database.AddDatabaseEvent;
import com.facebook.stetho.inspector.protocol.module.Database.DatabaseObject;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
class Database$DatabasePeerRegistrationListener extends PeersRegisteredListener {
    private final List<DatabaseDriver2> mDatabaseDrivers;
    @GuardedBy
    private final SparseArray<Database$DatabaseDescriptorHolder> mDatabaseHolders;
    @GuardedBy
    private final ObjectIdMapper mDatabaseIdMapper;

    private Database$DatabasePeerRegistrationListener(List<DatabaseDriver2> list) {
        this.mDatabaseHolders = new SparseArray();
        this.mDatabaseIdMapper = new ObjectIdMapper();
        this.mDatabaseDrivers = list;
    }

    public Database$DatabaseDescriptorHolder getDatabaseDescriptorHolder(String str) {
        return (Database$DatabaseDescriptorHolder) this.mDatabaseHolders.get(Integer.parseInt(str));
    }

    protected synchronized void onFirstPeerRegistered() {
        for (DatabaseDriver2 databaseDriver2 : this.mDatabaseDrivers) {
            for (DatabaseDescriptor databaseDescriptor : databaseDriver2.getDatabaseNames()) {
                if (this.mDatabaseIdMapper.getIdForObject(databaseDescriptor) == null) {
                    this.mDatabaseHolders.put(Integer.valueOf(this.mDatabaseIdMapper.putObject(databaseDescriptor)).intValue(), new Database$DatabaseDescriptorHolder(databaseDriver2, databaseDescriptor));
                }
            }
        }
    }

    protected synchronized void onLastPeerUnregistered() {
        this.mDatabaseIdMapper.clear();
        this.mDatabaseHolders.clear();
    }

    protected synchronized void onPeerAdded(JsonRpcPeer jsonRpcPeer) {
        int size = this.mDatabaseHolders.size();
        for (int i = 0; i < size; i++) {
            int keyAt = this.mDatabaseHolders.keyAt(i);
            Database$DatabaseDescriptorHolder database$DatabaseDescriptorHolder = (Database$DatabaseDescriptorHolder) this.mDatabaseHolders.valueAt(i);
            DatabaseObject databaseObject = new DatabaseObject();
            databaseObject.id = String.valueOf(keyAt);
            databaseObject.name = database$DatabaseDescriptorHolder.descriptor.name();
            databaseObject.domain = database$DatabaseDescriptorHolder.driver.getContext().getPackageName();
            databaseObject.version = "N/A";
            AddDatabaseEvent addDatabaseEvent = new AddDatabaseEvent();
            addDatabaseEvent.database = databaseObject;
            jsonRpcPeer.invokeMethod("Database.addDatabase", addDatabaseEvent, null);
        }
    }

    protected synchronized void onPeerRemoved(JsonRpcPeer jsonRpcPeer) {
    }
}
