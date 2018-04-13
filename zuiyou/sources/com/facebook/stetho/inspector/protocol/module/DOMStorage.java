package com.facebook.stetho.inspector.protocol.module;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.facebook.stetho.inspector.domstorage.DOMStoragePeerManager;
import com.facebook.stetho.inspector.domstorage.SharedPreferencesHelper;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import com.facebook.stetho.json.ObjectMapper;
import com.facebook.stetho.json.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class DOMStorage implements ChromeDevtoolsDomain {
    private final Context mContext;
    private final DOMStoragePeerManager mDOMStoragePeerManager;
    private final ObjectMapper mObjectMapper = new ObjectMapper();

    public static class DomStorageItemAddedParams {
        @JsonProperty(required = true)
        public String key;
        @JsonProperty(required = true)
        public String newValue;
        @JsonProperty(required = true)
        public StorageId storageId;
    }

    public static class DomStorageItemRemovedParams {
        @JsonProperty(required = true)
        public String key;
        @JsonProperty(required = true)
        public StorageId storageId;
    }

    public static class DomStorageItemUpdatedParams {
        @JsonProperty(required = true)
        public String key;
        @JsonProperty(required = true)
        public String newValue;
        @JsonProperty(required = true)
        public String oldValue;
        @JsonProperty(required = true)
        public StorageId storageId;
    }

    public static class DomStorageItemsClearedParams {
        @JsonProperty(required = true)
        public StorageId storageId;
    }

    private static class GetDOMStorageItemsResult implements JsonRpcResult {
        @JsonProperty(required = true)
        public List<List<String>> entries;

        private GetDOMStorageItemsResult() {
        }
    }

    public static class StorageId {
        @JsonProperty(required = true)
        public boolean isLocalStorage;
        @JsonProperty(required = true)
        public String securityOrigin;
    }

    public DOMStorage(Context context) {
        this.mContext = context;
        this.mDOMStoragePeerManager = new DOMStoragePeerManager(context);
    }

    @ChromeDevtoolsMethod
    public void enable(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
        this.mDOMStoragePeerManager.addPeer(jsonRpcPeer);
    }

    @ChromeDevtoolsMethod
    public void disable(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
        this.mDOMStoragePeerManager.removePeer(jsonRpcPeer);
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult getDOMStorageItems(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) throws JSONException {
        StorageId storageId = (StorageId) this.mObjectMapper.convertValue(jSONObject.getJSONObject("storageId"), StorageId.class);
        List arrayList = new ArrayList();
        String str = storageId.securityOrigin;
        if (storageId.isLocalStorage) {
            for (Entry entry : this.mContext.getSharedPreferences(str, 0).getAll().entrySet()) {
                ArrayList arrayList2 = new ArrayList(2);
                arrayList2.add(entry.getKey());
                arrayList2.add(SharedPreferencesHelper.valueToString(entry.getValue()));
                arrayList.add(arrayList2);
            }
        }
        JsonRpcResult getDOMStorageItemsResult = new GetDOMStorageItemsResult();
        getDOMStorageItemsResult.entries = arrayList;
        return getDOMStorageItemsResult;
    }

    @com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod
    public void setDOMStorageItem(com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer r12, org.json.JSONObject r13) throws org.json.JSONException, com.facebook.stetho.inspector.jsonrpc.JsonRpcException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.stetho.inspector.protocol.module.DOMStorage.setDOMStorageItem(com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer, org.json.JSONObject):void. bs: [B:4:0x0036, B:14:0x0079]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r11 = this;
        r5 = 0;
        r0 = r11.mObjectMapper;
        r1 = "storageId";
        r1 = r13.getJSONObject(r1);
        r2 = com.facebook.stetho.inspector.protocol.module.DOMStorage.StorageId.class;
        r0 = r0.convertValue(r1, r2);
        r0 = (com.facebook.stetho.inspector.protocol.module.DOMStorage.StorageId) r0;
        r1 = "key";
        r2 = r13.getString(r1);
        r1 = "value";
        r3 = r13.getString(r1);
        r1 = r0.isLocalStorage;
        if (r1 == 0) goto L_0x0074;
    L_0x0024:
        r1 = r11.mContext;
        r4 = r0.securityOrigin;
        r4 = r1.getSharedPreferences(r4, r5);
        r1 = r4.getAll();
        r5 = r1.get(r2);
        if (r5 != 0) goto L_0x0075;
    L_0x0036:
        r1 = new com.facebook.stetho.inspector.protocol.module.DOMStorage$DOMStorageAssignmentException;	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r6 = new java.lang.StringBuilder;	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r6.<init>();	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r7 = "Unsupported: cannot add new key ";	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r6 = r6.append(r7);	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r6 = r6.append(r2);	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r7 = " due to lack of type inference";	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r6 = r6.append(r7);	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r6 = r6.toString();	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r1.<init>(r6);	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        throw r1;	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
    L_0x0057:
        r1 = move-exception;
        r6 = r11.mDOMStoragePeerManager;
        r7 = com.facebook.stetho.inspector.protocol.module.Console.MessageLevel.ERROR;
        r8 = com.facebook.stetho.inspector.protocol.module.Console.MessageSource.STORAGE;
        r1 = r1.getMessage();
        com.facebook.stetho.inspector.console.CLog.writeToConsole(r6, r7, r8, r1);
        r1 = r4.contains(r2);
        if (r1 == 0) goto L_0x00a8;
    L_0x006b:
        r1 = r11.mDOMStoragePeerManager;
        r4 = com.facebook.stetho.inspector.domstorage.SharedPreferencesHelper.valueToString(r5);
        r1.signalItemUpdated(r0, r2, r3, r4);
    L_0x0074:
        return;
    L_0x0075:
        r1 = r4.edit();	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r6 = com.facebook.stetho.inspector.domstorage.SharedPreferencesHelper.valueFromString(r3, r5);	 Catch:{ IllegalArgumentException -> 0x0084 }
        assignByType(r1, r2, r6);	 Catch:{ IllegalArgumentException -> 0x0084 }
        r1.apply();	 Catch:{ IllegalArgumentException -> 0x0084 }
        goto L_0x0074;
    L_0x0084:
        r1 = move-exception;
        r1 = new com.facebook.stetho.inspector.protocol.module.DOMStorage$DOMStorageAssignmentException;	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r6 = java.util.Locale.US;	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r7 = "Type mismatch setting %s to %s (expected %s)";	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r8 = 3;	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r8 = new java.lang.Object[r8];	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r9 = 0;	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r8[r9] = r2;	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r9 = 1;	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r8[r9] = r3;	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r9 = 2;	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r10 = r5.getClass();	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r10 = r10.getSimpleName();	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r8[r9] = r10;	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r6 = java.lang.String.format(r6, r7, r8);	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        r1.<init>(r6);	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
        throw r1;	 Catch:{ DOMStorage$DOMStorageAssignmentException -> 0x0057 }
    L_0x00a8:
        r1 = r11.mDOMStoragePeerManager;
        r1.signalItemRemoved(r0, r2);
        goto L_0x0074;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.stetho.inspector.protocol.module.DOMStorage.setDOMStorageItem(com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer, org.json.JSONObject):void");
    }

    @ChromeDevtoolsMethod
    public void removeDOMStorageItem(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) throws JSONException {
        StorageId storageId = (StorageId) this.mObjectMapper.convertValue(jSONObject.getJSONObject("storageId"), StorageId.class);
        String string = jSONObject.getString("key");
        if (storageId.isLocalStorage) {
            this.mContext.getSharedPreferences(storageId.securityOrigin, 0).edit().remove(string).apply();
        }
    }

    private static void assignByType(Editor editor, String str, Object obj) throws IllegalArgumentException {
        if (obj instanceof Integer) {
            editor.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Long) {
            editor.putLong(str, ((Long) obj).longValue());
        } else if (obj instanceof Float) {
            editor.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Boolean) {
            editor.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof String) {
            editor.putString(str, (String) obj);
        } else if (obj instanceof Set) {
            putStringSet(editor, str, (Set) obj);
        } else {
            throw new IllegalArgumentException("Unsupported type=" + obj.getClass().getName());
        }
    }

    @TargetApi(11)
    private static void putStringSet(Editor editor, String str, Set<String> set) {
        editor.putStringSet(str, set);
    }
}
