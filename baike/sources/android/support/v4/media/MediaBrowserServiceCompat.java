package android.support.v4.media;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.MediaBrowserCompat.MediaItem;
import android.support.v4.media.MediaBrowserServiceCompatApi21.ServiceCompatProxy;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.os.ResultReceiver;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class MediaBrowserServiceCompat extends Service {
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static final String KEY_MEDIA_ITEM = "media_item";
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static final String KEY_SEARCH_RESULTS = "search_results";
    public static final String SERVICE_INTERFACE = "android.media.browse.MediaBrowserService";
    static final boolean a = Log.isLoggable("MBServiceCompat", 3);
    final ArrayMap<IBinder, a> b = new ArrayMap();
    a c;
    final j d = new j(this);
    Token e;
    private b f;

    public static final class BrowserRoot {
        public static final String EXTRA_OFFLINE = "android.service.media.extra.OFFLINE";
        public static final String EXTRA_RECENT = "android.service.media.extra.RECENT";
        public static final String EXTRA_SUGGESTED = "android.service.media.extra.SUGGESTED";
        @Deprecated
        public static final String EXTRA_SUGGESTION_KEYWORDS = "android.service.media.extra.SUGGESTION_KEYWORDS";
        private final String a;
        private final Bundle b;

        public BrowserRoot(@NonNull String str, @Nullable Bundle bundle) {
            if (str == null) {
                throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead.");
            }
            this.a = str;
            this.b = bundle;
        }

        public String getRootId() {
            return this.a;
        }

        public Bundle getExtras() {
            return this.b;
        }
    }

    public static class Result<T> {
        private final Object a;
        private boolean b;
        private boolean c;
        private boolean d;
        private boolean e;
        private int f;

        Result(Object obj) {
            this.a = obj;
        }

        public void sendResult(T t) {
            if (this.c || this.e) {
                throw new IllegalStateException("sendResult() called when either sendResult() or sendError() had already been called for: " + this.a);
            }
            this.c = true;
            a((Object) t);
        }

        public void sendProgressUpdate(Bundle bundle) {
            if (this.c || this.e) {
                throw new IllegalStateException("sendProgressUpdate() called when either sendResult() or sendError() had already been called for: " + this.a);
            }
            a(bundle);
            this.d = true;
            b(bundle);
        }

        public void sendError(Bundle bundle) {
            if (this.c || this.e) {
                throw new IllegalStateException("sendError() called when either sendResult() or sendError() had already been called for: " + this.a);
            }
            this.e = true;
            c(bundle);
        }

        public void detach() {
            if (this.b) {
                throw new IllegalStateException("detach() called when detach() had already been called for: " + this.a);
            } else if (this.c) {
                throw new IllegalStateException("detach() called when sendResult() had already been called for: " + this.a);
            } else if (this.e) {
                throw new IllegalStateException("detach() called when sendError() had already been called for: " + this.a);
            } else {
                this.b = true;
            }
        }

        boolean a() {
            return this.b || this.c || this.e;
        }

        void a(int i) {
            this.f = i;
        }

        int b() {
            return this.f;
        }

        void a(T t) {
        }

        void b(Bundle bundle) {
            throw new UnsupportedOperationException("It is not supported to send an interim update for " + this.a);
        }

        void c(Bundle bundle) {
            throw new UnsupportedOperationException("It is not supported to send an error for " + this.a);
        }

        private void a(Bundle bundle) {
            if (bundle != null && bundle.containsKey(MediaBrowserCompat.EXTRA_DOWNLOAD_PROGRESS)) {
                float f = bundle.getFloat(MediaBrowserCompat.EXTRA_DOWNLOAD_PROGRESS);
                if (f < -1.0E-5f || f > 1.00001f) {
                    throw new IllegalArgumentException("The value of the EXTRA_DOWNLOAD_PROGRESS field must be a float number within [0.0, 1.0].");
                }
            }
        }
    }

    private class a implements DeathRecipient {
        String a;
        Bundle b;
        h c;
        BrowserRoot d;
        HashMap<String, List<Pair<IBinder, Bundle>>> e = new HashMap();
        final /* synthetic */ MediaBrowserServiceCompat f;

        a(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
            this.f = mediaBrowserServiceCompat;
        }

        public void binderDied() {
            this.f.d.post(new z(this));
        }
    }

    interface b {
        Bundle getBrowserRootHints();

        void notifyChildrenChanged(String str, Bundle bundle);

        IBinder onBind(Intent intent);

        void onCreate();

        void setSessionToken(Token token);
    }

    @RequiresApi(21)
    class c implements b, ServiceCompatProxy {
        final List<Bundle> a = new ArrayList();
        Object b;
        Messenger c;
        final /* synthetic */ MediaBrowserServiceCompat d;

        c(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
            this.d = mediaBrowserServiceCompat;
        }

        public void onCreate() {
            this.b = MediaBrowserServiceCompatApi21.createService(this.d, this);
            MediaBrowserServiceCompatApi21.onCreate(this.b);
        }

        public IBinder onBind(Intent intent) {
            return MediaBrowserServiceCompatApi21.onBind(this.b, intent);
        }

        public void setSessionToken(Token token) {
            this.d.d.postOrRun(new aa(this, token));
        }

        public void notifyChildrenChanged(String str, Bundle bundle) {
            if (this.c == null) {
                MediaBrowserServiceCompatApi21.notifyChildrenChanged(this.b, str);
            } else {
                this.d.d.post(new ab(this, str, bundle));
            }
        }

        public Bundle getBrowserRootHints() {
            if (this.c == null) {
                return null;
            }
            if (this.d.c == null) {
                throw new IllegalStateException("This should be called inside of onLoadChildren, onLoadItem or onSearch methods");
            } else if (this.d.c.b != null) {
                return new Bundle(this.d.c.b);
            } else {
                return null;
            }
        }

        public a onGetRoot(String str, int i, Bundle bundle) {
            Bundle bundle2;
            if (bundle == null || bundle.getInt("extra_client_version", 0) == 0) {
                bundle2 = null;
            } else {
                bundle.remove("extra_client_version");
                this.c = new Messenger(this.d.d);
                Bundle bundle3 = new Bundle();
                bundle3.putInt("extra_service_version", 1);
                BundleCompat.putBinder(bundle3, "extra_messenger", this.c.getBinder());
                if (this.d.e != null) {
                    IBinder iBinder;
                    IMediaSession extraBinder = this.d.e.getExtraBinder();
                    String str2 = "extra_session_binder";
                    if (extraBinder == null) {
                        iBinder = null;
                    } else {
                        iBinder = extraBinder.asBinder();
                    }
                    BundleCompat.putBinder(bundle3, str2, iBinder);
                    bundle2 = bundle3;
                } else {
                    this.a.add(bundle3);
                    bundle2 = bundle3;
                }
            }
            BrowserRoot onGetRoot = this.d.onGetRoot(str, i, bundle);
            if (onGetRoot == null) {
                return null;
            }
            if (bundle2 == null) {
                bundle2 = onGetRoot.getExtras();
            } else if (onGetRoot.getExtras() != null) {
                bundle2.putAll(onGetRoot.getExtras());
            }
            return new a(onGetRoot.getRootId(), bundle2);
        }

        public void onLoadChildren(String str, c<List<Parcel>> cVar) {
            this.d.onLoadChildren(str, new ac(this, str, cVar));
        }
    }

    @RequiresApi(23)
    class d extends c implements MediaBrowserServiceCompatApi23.ServiceCompatProxy {
        final /* synthetic */ MediaBrowserServiceCompat e;

        d(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
            this.e = mediaBrowserServiceCompat;
            super(mediaBrowserServiceCompat);
        }

        public void onCreate() {
            this.b = MediaBrowserServiceCompatApi23.createService(this.e, this);
            MediaBrowserServiceCompatApi21.onCreate(this.b);
        }

        public void onLoadItem(String str, c<Parcel> cVar) {
            this.e.onLoadItem(str, new ad(this, str, cVar));
        }
    }

    @RequiresApi(26)
    class e extends d implements MediaBrowserServiceCompatApi26.ServiceCompatProxy {
        final /* synthetic */ MediaBrowserServiceCompat f;

        e(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
            this.f = mediaBrowserServiceCompat;
            super(mediaBrowserServiceCompat);
        }

        public void onCreate() {
            this.b = MediaBrowserServiceCompatApi26.createService(this.f, this);
            MediaBrowserServiceCompatApi21.onCreate(this.b);
        }

        public void notifyChildrenChanged(String str, Bundle bundle) {
            if (bundle == null) {
                MediaBrowserServiceCompatApi21.notifyChildrenChanged(this.b, str);
            } else {
                MediaBrowserServiceCompatApi26.notifyChildrenChanged(this.b, str, bundle);
            }
        }

        public void onLoadChildren(String str, b bVar, Bundle bundle) {
            this.f.onLoadChildren(str, new ae(this, str, bVar), bundle);
        }

        public Bundle getBrowserRootHints() {
            if (this.f.c != null) {
                return this.f.c.b == null ? null : new Bundle(this.f.c.b);
            } else {
                return MediaBrowserServiceCompatApi26.getBrowserRootHints(this.b);
            }
        }
    }

    class f implements b {
        final /* synthetic */ MediaBrowserServiceCompat a;
        private Messenger b;

        f(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
            this.a = mediaBrowserServiceCompat;
        }

        public void onCreate() {
            this.b = new Messenger(this.a.d);
        }

        public IBinder onBind(Intent intent) {
            if (MediaBrowserServiceCompat.SERVICE_INTERFACE.equals(intent.getAction())) {
                return this.b.getBinder();
            }
            return null;
        }

        public void setSessionToken(Token token) {
            this.a.d.post(new af(this, token));
        }

        public void notifyChildrenChanged(@NonNull String str, Bundle bundle) {
            this.a.d.post(new ag(this, str, bundle));
        }

        public Bundle getBrowserRootHints() {
            if (this.a.c != null) {
                return this.a.c.b == null ? null : new Bundle(this.a.c.b);
            } else {
                throw new IllegalStateException("This should be called inside of onLoadChildren, onLoadItem or onSearch methods");
            }
        }
    }

    private class g {
        final /* synthetic */ MediaBrowserServiceCompat a;

        g(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
            this.a = mediaBrowserServiceCompat;
        }

        public void connect(String str, int i, Bundle bundle, h hVar) {
            if (this.a.a(str, i)) {
                this.a.d.postOrRun(new ah(this, hVar, str, bundle, i));
                return;
            }
            throw new IllegalArgumentException("Package/uid mismatch: uid=" + i + " package=" + str);
        }

        public void disconnect(h hVar) {
            this.a.d.postOrRun(new ai(this, hVar));
        }

        public void addSubscription(String str, IBinder iBinder, Bundle bundle, h hVar) {
            this.a.d.postOrRun(new aj(this, hVar, str, iBinder, bundle));
        }

        public void removeSubscription(String str, IBinder iBinder, h hVar) {
            this.a.d.postOrRun(new ak(this, hVar, str, iBinder));
        }

        public void getMediaItem(String str, ResultReceiver resultReceiver, h hVar) {
            if (!TextUtils.isEmpty(str) && resultReceiver != null) {
                this.a.d.postOrRun(new al(this, hVar, str, resultReceiver));
            }
        }

        public void registerCallbacks(h hVar, Bundle bundle) {
            this.a.d.postOrRun(new am(this, hVar, bundle));
        }

        public void unregisterCallbacks(h hVar) {
            this.a.d.postOrRun(new an(this, hVar));
        }

        public void search(String str, Bundle bundle, ResultReceiver resultReceiver, h hVar) {
            if (!TextUtils.isEmpty(str) && resultReceiver != null) {
                this.a.d.postOrRun(new ao(this, hVar, str, bundle, resultReceiver));
            }
        }

        public void sendCustomAction(String str, Bundle bundle, ResultReceiver resultReceiver, h hVar) {
            if (!TextUtils.isEmpty(str) && resultReceiver != null) {
                this.a.d.postOrRun(new ap(this, hVar, str, bundle, resultReceiver));
            }
        }
    }

    private interface h {
        IBinder asBinder();

        void onConnect(String str, Token token, Bundle bundle) throws RemoteException;

        void onConnectFailed() throws RemoteException;

        void onLoadChildren(String str, List<MediaItem> list, Bundle bundle) throws RemoteException;
    }

    private static class i implements h {
        final Messenger a;

        i(Messenger messenger) {
            this.a = messenger;
        }

        public IBinder asBinder() {
            return this.a.getBinder();
        }

        public void onConnect(String str, Token token, Bundle bundle) throws RemoteException {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putInt("extra_service_version", 1);
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", str);
            bundle2.putParcelable("data_media_session_token", token);
            bundle2.putBundle("data_root_hints", bundle);
            a(1, bundle2);
        }

        public void onConnectFailed() throws RemoteException {
            a(2, null);
        }

        public void onLoadChildren(String str, List<MediaItem> list, Bundle bundle) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", str);
            bundle2.putBundle("data_options", bundle);
            if (list != null) {
                String str2 = "data_media_item_list";
                if (list instanceof ArrayList) {
                    list = (ArrayList) list;
                } else {
                    Object arrayList = new ArrayList(list);
                }
                bundle2.putParcelableArrayList(str2, list);
            }
            a(3, bundle2);
        }

        private void a(int i, Bundle bundle) throws RemoteException {
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.arg1 = 1;
            obtain.setData(bundle);
            this.a.send(obtain);
        }
    }

    private final class j extends Handler {
        final /* synthetic */ MediaBrowserServiceCompat a;
        private final g b = new g(this.a);

        j(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
            this.a = mediaBrowserServiceCompat;
        }

        public void handleMessage(Message message) {
            Bundle data = message.getData();
            switch (message.what) {
                case 1:
                    this.b.connect(data.getString("data_package_name"), data.getInt("data_calling_uid"), data.getBundle("data_root_hints"), new i(message.replyTo));
                    return;
                case 2:
                    this.b.disconnect(new i(message.replyTo));
                    return;
                case 3:
                    this.b.addSubscription(data.getString("data_media_item_id"), BundleCompat.getBinder(data, "data_callback_token"), data.getBundle("data_options"), new i(message.replyTo));
                    return;
                case 4:
                    this.b.removeSubscription(data.getString("data_media_item_id"), BundleCompat.getBinder(data, "data_callback_token"), new i(message.replyTo));
                    return;
                case 5:
                    this.b.getMediaItem(data.getString("data_media_item_id"), (ResultReceiver) data.getParcelable("data_result_receiver"), new i(message.replyTo));
                    return;
                case 6:
                    this.b.registerCallbacks(new i(message.replyTo), data.getBundle("data_root_hints"));
                    return;
                case 7:
                    this.b.unregisterCallbacks(new i(message.replyTo));
                    return;
                case 8:
                    this.b.search(data.getString("data_search_query"), data.getBundle("data_search_extras"), (ResultReceiver) data.getParcelable("data_result_receiver"), new i(message.replyTo));
                    return;
                case 9:
                    this.b.sendCustomAction(data.getString("data_custom_action"), data.getBundle("data_custom_action_extras"), (ResultReceiver) data.getParcelable("data_result_receiver"), new i(message.replyTo));
                    return;
                default:
                    Log.w("MBServiceCompat", "Unhandled message: " + message + "\n  Service version: " + 1 + "\n  Client version: " + message.arg1);
                    return;
            }
        }

        public boolean sendMessageAtTime(Message message, long j) {
            Bundle data = message.getData();
            data.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            data.putInt("data_calling_uid", Binder.getCallingUid());
            return super.sendMessageAtTime(message, j);
        }

        public void postOrRun(Runnable runnable) {
            if (Thread.currentThread() == getLooper().getThread()) {
                runnable.run();
            } else {
                post(runnable);
            }
        }
    }

    @Nullable
    public abstract BrowserRoot onGetRoot(@NonNull String str, int i, @Nullable Bundle bundle);

    public abstract void onLoadChildren(@NonNull String str, @NonNull Result<List<MediaItem>> result);

    public void onCreate() {
        super.onCreate();
        if (VERSION.SDK_INT >= 26) {
            this.f = new e(this);
        } else if (VERSION.SDK_INT >= 23) {
            this.f = new d(this);
        } else if (VERSION.SDK_INT >= 21) {
            this.f = new c(this);
        } else {
            this.f = new f(this);
        }
        this.f.onCreate();
    }

    public IBinder onBind(Intent intent) {
        return this.f.onBind(intent);
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public void onLoadChildren(@NonNull String str, @NonNull Result<List<MediaItem>> result, @NonNull Bundle bundle) {
        result.a(1);
        onLoadChildren(str, result);
    }

    public void onLoadItem(String str, @NonNull Result<MediaItem> result) {
        result.a(2);
        result.sendResult(null);
    }

    public void onSearch(@NonNull String str, Bundle bundle, @NonNull Result<List<MediaItem>> result) {
        result.a(4);
        result.sendResult(null);
    }

    public void onCustomAction(@NonNull String str, Bundle bundle, @NonNull Result<Bundle> result) {
        result.sendError(null);
    }

    public void setSessionToken(Token token) {
        if (token == null) {
            throw new IllegalArgumentException("Session token may not be null.");
        } else if (this.e != null) {
            throw new IllegalStateException("The session token has already been set.");
        } else {
            this.e = token;
            this.f.setSessionToken(token);
        }
    }

    @Nullable
    public Token getSessionToken() {
        return this.e;
    }

    public final Bundle getBrowserRootHints() {
        return this.f.getBrowserRootHints();
    }

    public void notifyChildrenChanged(@NonNull String str) {
        if (str == null) {
            throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
        }
        this.f.notifyChildrenChanged(str, null);
    }

    public void notifyChildrenChanged(@NonNull String str, @NonNull Bundle bundle) {
        if (str == null) {
            throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
        } else if (bundle == null) {
            throw new IllegalArgumentException("options cannot be null in notifyChildrenChanged");
        } else {
            this.f.notifyChildrenChanged(str, bundle);
        }
    }

    boolean a(String str, int i) {
        if (str == null) {
            return false;
        }
        for (String equals : getPackageManager().getPackagesForUid(i)) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    void a(String str, a aVar, IBinder iBinder, Bundle bundle) {
        List list = (List) aVar.e.get(str);
        List arrayList;
        if (list == null) {
            arrayList = new ArrayList();
        } else {
            arrayList = list;
        }
        for (Pair pair : r1) {
            if (iBinder == pair.first && MediaBrowserCompatUtils.areSameOptions(bundle, (Bundle) pair.second)) {
                return;
            }
        }
        r1.add(new Pair(iBinder, bundle));
        aVar.e.put(str, r1);
        a(str, aVar, bundle);
    }

    boolean a(String str, a aVar, IBinder iBinder) {
        if (iBinder != null) {
            boolean z;
            List list = (List) aVar.e.get(str);
            if (list != null) {
                Iterator it = list.iterator();
                z = false;
                while (it.hasNext()) {
                    if (iBinder == ((Pair) it.next()).first) {
                        it.remove();
                        z = true;
                    }
                }
                if (list.size() == 0) {
                    aVar.e.remove(str);
                }
            } else {
                z = false;
            }
            return z;
        } else if (aVar.e.remove(str) != null) {
            return true;
        } else {
            return false;
        }
    }

    void a(String str, a aVar, Bundle bundle) {
        Result vVar = new v(this, str, aVar, str, bundle);
        this.c = aVar;
        if (bundle == null) {
            onLoadChildren(str, vVar);
        } else {
            onLoadChildren(str, vVar, bundle);
        }
        this.c = null;
        if (!vVar.a()) {
            throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + aVar.a + " id=" + str);
        }
    }

    List<MediaItem> a(List<MediaItem> list, Bundle bundle) {
        if (list == null) {
            return null;
        }
        int i = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
        int i2 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
        if (i == -1 && i2 == -1) {
            return list;
        }
        int i3 = i2 * i;
        int i4 = i3 + i2;
        if (i < 0 || i2 < 1 || i3 >= list.size()) {
            return Collections.EMPTY_LIST;
        }
        if (i4 > list.size()) {
            i4 = list.size();
        }
        return list.subList(i3, i4);
    }

    void a(String str, a aVar, ResultReceiver resultReceiver) {
        Result wVar = new w(this, str, resultReceiver);
        this.c = aVar;
        onLoadItem(str, wVar);
        this.c = null;
        if (!wVar.a()) {
            throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=" + str);
        }
    }

    void a(String str, Bundle bundle, a aVar, ResultReceiver resultReceiver) {
        Result xVar = new x(this, str, resultReceiver);
        this.c = aVar;
        onSearch(str, bundle, xVar);
        this.c = null;
        if (!xVar.a()) {
            throw new IllegalStateException("onSearch must call detach() or sendResult() before returning for query=" + str);
        }
    }

    void b(String str, Bundle bundle, a aVar, ResultReceiver resultReceiver) {
        Result yVar = new y(this, str, resultReceiver);
        this.c = aVar;
        onCustomAction(str, bundle, yVar);
        this.c = null;
        if (!yVar.a()) {
            throw new IllegalStateException("onCustomAction must call detach() or sendResult() or sendError() before returning for action=" + str + " extras=" + bundle);
        }
    }
}
