package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.IMediaSession.Stub;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.os.ResultReceiver;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

public final class MediaBrowserCompat {
    public static final String CUSTOM_ACTION_DOWNLOAD = "android.support.v4.media.action.DOWNLOAD";
    public static final String CUSTOM_ACTION_REMOVE_DOWNLOADED_FILE = "android.support.v4.media.action.REMOVE_DOWNLOADED_FILE";
    public static final String EXTRA_DOWNLOAD_PROGRESS = "android.media.browse.extra.DOWNLOAD_PROGRESS";
    public static final String EXTRA_MEDIA_ID = "android.media.browse.extra.MEDIA_ID";
    public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
    public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
    static final boolean a = Log.isLoggable("MediaBrowserCompat", 3);
    private final b b;

    public static class ConnectionCallback {
        final Object a;
        a b;

        interface a {
            void onConnected();

            void onConnectionFailed();

            void onConnectionSuspended();
        }

        private class b implements a {
            final /* synthetic */ ConnectionCallback a;

            b(ConnectionCallback connectionCallback) {
                this.a = connectionCallback;
            }

            public void onConnected() {
                if (this.a.b != null) {
                    this.a.b.onConnected();
                }
                this.a.onConnected();
            }

            public void onConnectionSuspended() {
                if (this.a.b != null) {
                    this.a.b.onConnectionSuspended();
                }
                this.a.onConnectionSuspended();
            }

            public void onConnectionFailed() {
                if (this.a.b != null) {
                    this.a.b.onConnectionFailed();
                }
                this.a.onConnectionFailed();
            }
        }

        public ConnectionCallback() {
            if (VERSION.SDK_INT >= 21) {
                this.a = s.createConnectionCallback(new b(this));
            } else {
                this.a = null;
            }
        }

        public void onConnected() {
        }

        public void onConnectionSuspended() {
        }

        public void onConnectionFailed() {
        }

        void a(a aVar) {
            this.b = aVar;
        }
    }

    public static abstract class CustomActionCallback {
        public void onProgressUpdate(String str, Bundle bundle, Bundle bundle2) {
        }

        public void onResult(String str, Bundle bundle, Bundle bundle2) {
        }

        public void onError(String str, Bundle bundle, Bundle bundle2) {
        }
    }

    private static class CustomActionResultReceiver extends ResultReceiver {
        private final String d;
        private final Bundle e;
        private final CustomActionCallback f;

        CustomActionResultReceiver(String str, Bundle bundle, CustomActionCallback customActionCallback, Handler handler) {
            super(handler);
            this.d = str;
            this.e = bundle;
            this.f = customActionCallback;
        }

        protected void a(int i, Bundle bundle) {
            if (this.f != null) {
                switch (i) {
                    case -1:
                        this.f.onError(this.d, this.e, bundle);
                        return;
                    case 0:
                        this.f.onResult(this.d, this.e, bundle);
                        return;
                    case 1:
                        this.f.onProgressUpdate(this.d, this.e, bundle);
                        return;
                    default:
                        Log.w("MediaBrowserCompat", "Unknown result code: " + i + " (extras=" + this.e + ", resultData=" + bundle + ")");
                        return;
                }
            }
        }
    }

    public static abstract class ItemCallback {
        final Object a;

        private class a implements a {
            final /* synthetic */ ItemCallback a;

            a(ItemCallback itemCallback) {
                this.a = itemCallback;
            }

            public void onItemLoaded(Parcel parcel) {
                if (parcel == null) {
                    this.a.onItemLoaded(null);
                    return;
                }
                parcel.setDataPosition(0);
                MediaItem mediaItem = (MediaItem) MediaItem.CREATOR.createFromParcel(parcel);
                parcel.recycle();
                this.a.onItemLoaded(mediaItem);
            }

            public void onError(@NonNull String str) {
                this.a.onError(str);
            }
        }

        public ItemCallback() {
            if (VERSION.SDK_INT >= 23) {
                this.a = t.createItemCallback(new a(this));
            } else {
                this.a = null;
            }
        }

        public void onItemLoaded(MediaItem mediaItem) {
        }

        public void onError(@NonNull String str) {
        }
    }

    private static class ItemReceiver extends ResultReceiver {
        private final String d;
        private final ItemCallback e;

        ItemReceiver(String str, ItemCallback itemCallback, Handler handler) {
            super(handler);
            this.d = str;
            this.e = itemCallback;
        }

        protected void a(int i, Bundle bundle) {
            if (bundle != null) {
                bundle.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            }
            if (i == 0 && bundle != null && bundle.containsKey(MediaBrowserServiceCompat.KEY_MEDIA_ITEM)) {
                Parcelable parcelable = bundle.getParcelable(MediaBrowserServiceCompat.KEY_MEDIA_ITEM);
                if (parcelable == null || (parcelable instanceof MediaItem)) {
                    this.e.onItemLoaded((MediaItem) parcelable);
                    return;
                } else {
                    this.e.onError(this.d);
                    return;
                }
            }
            this.e.onError(this.d);
        }
    }

    public static class MediaItem implements Parcelable {
        public static final Creator<MediaItem> CREATOR = new r();
        public static final int FLAG_BROWSABLE = 1;
        public static final int FLAG_PLAYABLE = 2;
        private final int a;
        private final MediaDescriptionCompat b;

        @RestrictTo({Scope.LIBRARY_GROUP})
        @Retention(RetentionPolicy.SOURCE)
        public @interface Flags {
        }

        public static MediaItem fromMediaItem(Object obj) {
            if (obj == null || VERSION.SDK_INT < 21) {
                return null;
            }
            return new MediaItem(MediaDescriptionCompat.fromMediaDescription(c.getDescription(obj)), c.getFlags(obj));
        }

        public static List<MediaItem> fromMediaItemList(List<?> list) {
            if (list == null || VERSION.SDK_INT < 21) {
                return null;
            }
            List<MediaItem> arrayList = new ArrayList(list.size());
            for (Object fromMediaItem : list) {
                arrayList.add(fromMediaItem(fromMediaItem));
            }
            return arrayList;
        }

        public MediaItem(@NonNull MediaDescriptionCompat mediaDescriptionCompat, int i) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("description cannot be null");
            } else if (TextUtils.isEmpty(mediaDescriptionCompat.getMediaId())) {
                throw new IllegalArgumentException("description must have a non-empty media id");
            } else {
                this.a = i;
                this.b = mediaDescriptionCompat;
            }
        }

        MediaItem(Parcel parcel) {
            this.a = parcel.readInt();
            this.b = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            this.b.writeToParcel(parcel, i);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("MediaItem{");
            stringBuilder.append("mFlags=").append(this.a);
            stringBuilder.append(", mDescription=").append(this.b);
            stringBuilder.append('}');
            return stringBuilder.toString();
        }

        public int getFlags() {
            return this.a;
        }

        public boolean isBrowsable() {
            return (this.a & 1) != 0;
        }

        public boolean isPlayable() {
            return (this.a & 2) != 0;
        }

        @NonNull
        public MediaDescriptionCompat getDescription() {
            return this.b;
        }

        @Nullable
        public String getMediaId() {
            return this.b.getMediaId();
        }
    }

    public static abstract class SearchCallback {
        public void onSearchResult(@NonNull String str, Bundle bundle, @NonNull List<MediaItem> list) {
        }

        public void onError(@NonNull String str, Bundle bundle) {
        }
    }

    private static class SearchResultReceiver extends ResultReceiver {
        private final String d;
        private final Bundle e;
        private final SearchCallback f;

        SearchResultReceiver(String str, Bundle bundle, SearchCallback searchCallback, Handler handler) {
            super(handler);
            this.d = str;
            this.e = bundle;
            this.f = searchCallback;
        }

        protected void a(int i, Bundle bundle) {
            if (bundle != null) {
                bundle.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            }
            if (i == 0 && bundle != null && bundle.containsKey(MediaBrowserServiceCompat.KEY_SEARCH_RESULTS)) {
                Parcelable[] parcelableArray = bundle.getParcelableArray(MediaBrowserServiceCompat.KEY_SEARCH_RESULTS);
                List list = null;
                if (parcelableArray != null) {
                    List arrayList = new ArrayList();
                    for (Parcelable parcelable : parcelableArray) {
                        arrayList.add((MediaItem) parcelable);
                    }
                    list = arrayList;
                }
                this.f.onSearchResult(this.d, this.e, list);
                return;
            }
            this.f.onError(this.d, this.e);
        }
    }

    public static abstract class SubscriptionCallback {
        WeakReference<i> a;
        private final Object b;
        private final IBinder c;

        private class a implements d {
            final /* synthetic */ SubscriptionCallback a;

            a(SubscriptionCallback subscriptionCallback) {
                this.a = subscriptionCallback;
            }

            public void onChildrenLoaded(@NonNull String str, List<?> list) {
                i iVar = this.a.a == null ? null : (i) this.a.a.get();
                if (iVar == null) {
                    this.a.onChildrenLoaded(str, MediaItem.fromMediaItemList(list));
                    return;
                }
                List fromMediaItemList = MediaItem.fromMediaItemList(list);
                List callbacks = iVar.getCallbacks();
                List optionsList = iVar.getOptionsList();
                for (int i = 0; i < callbacks.size(); i++) {
                    Bundle bundle = (Bundle) optionsList.get(i);
                    if (bundle == null) {
                        this.a.onChildrenLoaded(str, fromMediaItemList);
                    } else {
                        this.a.onChildrenLoaded(str, a(fromMediaItemList, bundle), bundle);
                    }
                }
            }

            public void onError(@NonNull String str) {
                this.a.onError(str);
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
        }

        private class b extends a implements a {
            final /* synthetic */ SubscriptionCallback b;

            b(SubscriptionCallback subscriptionCallback) {
                this.b = subscriptionCallback;
                super(subscriptionCallback);
            }

            public void onChildrenLoaded(@NonNull String str, List<?> list, @NonNull Bundle bundle) {
                this.b.onChildrenLoaded(str, MediaItem.fromMediaItemList(list), bundle);
            }

            public void onError(@NonNull String str, @NonNull Bundle bundle) {
                this.b.onError(str, bundle);
            }
        }

        public SubscriptionCallback() {
            if (VERSION.SDK_INT >= 26) {
                this.b = u.a(new b(this));
                this.c = null;
            } else if (VERSION.SDK_INT >= 21) {
                this.b = s.createSubscriptionCallback(new a(this));
                this.c = new Binder();
            } else {
                this.b = null;
                this.c = new Binder();
            }
        }

        public void onChildrenLoaded(@NonNull String str, @NonNull List<MediaItem> list) {
        }

        public void onChildrenLoaded(@NonNull String str, @NonNull List<MediaItem> list, @NonNull Bundle bundle) {
        }

        public void onError(@NonNull String str) {
        }

        public void onError(@NonNull String str, @NonNull Bundle bundle) {
        }

        private void a(i iVar) {
            this.a = new WeakReference(iVar);
        }
    }

    private static class a extends Handler {
        private final WeakReference<g> a;
        private WeakReference<Messenger> b;

        a(g gVar) {
            this.a = new WeakReference(gVar);
        }

        public void handleMessage(Message message) {
            if (this.b != null && this.b.get() != null && this.a.get() != null) {
                Bundle data = message.getData();
                data.setClassLoader(MediaSessionCompat.class.getClassLoader());
                g gVar = (g) this.a.get();
                Messenger messenger = (Messenger) this.b.get();
                try {
                    switch (message.what) {
                        case 1:
                            gVar.onServiceConnected(messenger, data.getString("data_media_item_id"), (Token) data.getParcelable("data_media_session_token"), data.getBundle("data_root_hints"));
                            return;
                        case 2:
                            gVar.onConnectionFailed(messenger);
                            return;
                        case 3:
                            gVar.onLoadChildren(messenger, data.getString("data_media_item_id"), data.getParcelableArrayList("data_media_item_list"), data.getBundle("data_options"));
                            return;
                        default:
                            Log.w("MediaBrowserCompat", "Unhandled message: " + message + "\n  Client version: " + 1 + "\n  Service version: " + message.arg1);
                            return;
                    }
                } catch (BadParcelableException e) {
                    Log.e("MediaBrowserCompat", "Could not unparcel the data.");
                    if (message.what == 1) {
                        gVar.onConnectionFailed(messenger);
                    }
                }
                Log.e("MediaBrowserCompat", "Could not unparcel the data.");
                if (message.what == 1) {
                    gVar.onConnectionFailed(messenger);
                }
            }
        }

        void a(Messenger messenger) {
            this.b = new WeakReference(messenger);
        }
    }

    interface b {
        void connect();

        void disconnect();

        @Nullable
        Bundle getExtras();

        void getItem(@NonNull String str, @NonNull ItemCallback itemCallback);

        @NonNull
        String getRoot();

        ComponentName getServiceComponent();

        @NonNull
        Token getSessionToken();

        boolean isConnected();

        void search(@NonNull String str, Bundle bundle, @NonNull SearchCallback searchCallback);

        void sendCustomAction(@NonNull String str, Bundle bundle, @Nullable CustomActionCallback customActionCallback);

        void subscribe(@NonNull String str, @Nullable Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback);

        void unsubscribe(@NonNull String str, SubscriptionCallback subscriptionCallback);
    }

    interface g {
        void onConnectionFailed(Messenger messenger);

        void onLoadChildren(Messenger messenger, String str, List list, Bundle bundle);

        void onServiceConnected(Messenger messenger, String str, Token token, Bundle bundle);
    }

    @RequiresApi(21)
    static class c implements a, b, g {
        final Context a;
        protected final Object b;
        protected final Bundle c;
        protected final a d = new a(this);
        protected h e;
        protected Messenger f;
        private final ArrayMap<String, i> g = new ArrayMap();
        private Token h;

        c(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            this.a = context;
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putInt("extra_client_version", 1);
            this.c = new Bundle(bundle);
            connectionCallback.a(this);
            this.b = s.createBrowser(context, componentName, connectionCallback.a, this.c);
        }

        public void connect() {
            s.connect(this.b);
        }

        public void disconnect() {
            if (!(this.e == null || this.f == null)) {
                try {
                    this.e.c(this.f);
                } catch (RemoteException e) {
                    Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
                }
            }
            s.disconnect(this.b);
        }

        public boolean isConnected() {
            return s.isConnected(this.b);
        }

        public ComponentName getServiceComponent() {
            return s.getServiceComponent(this.b);
        }

        @NonNull
        public String getRoot() {
            return s.getRoot(this.b);
        }

        @Nullable
        public Bundle getExtras() {
            return s.getExtras(this.b);
        }

        @NonNull
        public Token getSessionToken() {
            if (this.h == null) {
                this.h = Token.fromToken(s.getSessionToken(this.b));
            }
            return this.h;
        }

        public void subscribe(@NonNull String str, Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
            i iVar = (i) this.g.get(str);
            if (iVar == null) {
                iVar = new i();
                this.g.put(str, iVar);
            }
            subscriptionCallback.a(iVar);
            Bundle bundle2 = bundle == null ? null : new Bundle(bundle);
            iVar.putCallback(this.a, bundle2, subscriptionCallback);
            if (this.e == null) {
                s.subscribe(this.b, str, subscriptionCallback.b);
                return;
            }
            try {
                this.e.a(str, subscriptionCallback.c, bundle2, this.f);
            } catch (RemoteException e) {
                Log.i("MediaBrowserCompat", "Remote error subscribing media item: " + str);
            }
        }

        public void unsubscribe(@NonNull String str, SubscriptionCallback subscriptionCallback) {
            i iVar = (i) this.g.get(str);
            if (iVar != null) {
                List callbacks;
                List optionsList;
                int size;
                if (this.e == null) {
                    if (subscriptionCallback == null) {
                        s.unsubscribe(this.b, str);
                    } else {
                        callbacks = iVar.getCallbacks();
                        optionsList = iVar.getOptionsList();
                        for (size = callbacks.size() - 1; size >= 0; size--) {
                            if (callbacks.get(size) == subscriptionCallback) {
                                callbacks.remove(size);
                                optionsList.remove(size);
                            }
                        }
                        if (callbacks.size() == 0) {
                            s.unsubscribe(this.b, str);
                        }
                    }
                } else if (subscriptionCallback == null) {
                    try {
                        this.e.a(str, null, this.f);
                    } catch (RemoteException e) {
                        Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + str);
                    }
                } else {
                    callbacks = iVar.getCallbacks();
                    optionsList = iVar.getOptionsList();
                    for (size = callbacks.size() - 1; size >= 0; size--) {
                        if (callbacks.get(size) == subscriptionCallback) {
                            this.e.a(str, subscriptionCallback.c, this.f);
                            callbacks.remove(size);
                            optionsList.remove(size);
                        }
                    }
                }
                if (iVar.isEmpty() || subscriptionCallback == null) {
                    this.g.remove(str);
                }
            }
        }

        public void getItem(@NonNull String str, @NonNull ItemCallback itemCallback) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("mediaId is empty");
            } else if (itemCallback == null) {
                throw new IllegalArgumentException("cb is null");
            } else if (!s.isConnected(this.b)) {
                Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
                this.d.post(new c(this, itemCallback, str));
            } else if (this.e == null) {
                this.d.post(new d(this, itemCallback, str));
            } else {
                try {
                    this.e.a(str, new ItemReceiver(str, itemCallback, this.d), this.f);
                } catch (RemoteException e) {
                    Log.i("MediaBrowserCompat", "Remote error getting media item: " + str);
                    this.d.post(new e(this, itemCallback, str));
                }
            }
        }

        public void search(@NonNull String str, Bundle bundle, @NonNull SearchCallback searchCallback) {
            if (!isConnected()) {
                throw new IllegalStateException("search() called while not connected");
            } else if (this.e == null) {
                Log.i("MediaBrowserCompat", "The connected service doesn't support search.");
                this.d.post(new f(this, searchCallback, str, bundle));
            } else {
                try {
                    this.e.a(str, bundle, new SearchResultReceiver(str, bundle, searchCallback, this.d), this.f);
                } catch (Throwable e) {
                    Log.i("MediaBrowserCompat", "Remote error searching items with query: " + str, e);
                    this.d.post(new g(this, searchCallback, str, bundle));
                }
            }
        }

        public void sendCustomAction(@NonNull String str, Bundle bundle, @Nullable CustomActionCallback customActionCallback) {
            if (isConnected()) {
                if (this.e == null) {
                    Log.i("MediaBrowserCompat", "The connected service doesn't support sendCustomAction.");
                    if (customActionCallback != null) {
                        this.d.post(new h(this, customActionCallback, str, bundle));
                    }
                }
                try {
                    this.e.b(str, bundle, new CustomActionResultReceiver(str, bundle, customActionCallback, this.d), this.f);
                    return;
                } catch (Throwable e) {
                    Log.i("MediaBrowserCompat", "Remote error sending a custom action: action=" + str + ", extras=" + bundle, e);
                    if (customActionCallback != null) {
                        this.d.post(new i(this, customActionCallback, str, bundle));
                        return;
                    }
                    return;
                }
            }
            throw new IllegalStateException("Cannot send a custom action (" + str + ") with " + "extras " + bundle + " because the browser is not connected to the " + "service.");
        }

        public void onConnected() {
            Bundle extras = s.getExtras(this.b);
            if (extras != null) {
                IBinder binder = BundleCompat.getBinder(extras, "extra_messenger");
                if (binder != null) {
                    this.e = new h(binder, this.c);
                    this.f = new Messenger(this.d);
                    this.d.a(this.f);
                    try {
                        this.e.b(this.f);
                    } catch (RemoteException e) {
                        Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
                    }
                }
                IMediaSession asInterface = Stub.asInterface(BundleCompat.getBinder(extras, "extra_session_binder"));
                if (asInterface != null) {
                    this.h = Token.fromToken(s.getSessionToken(this.b), asInterface);
                }
            }
        }

        public void onConnectionSuspended() {
            this.e = null;
            this.f = null;
            this.h = null;
            this.d.a(null);
        }

        public void onConnectionFailed() {
        }

        public void onServiceConnected(Messenger messenger, String str, Token token, Bundle bundle) {
        }

        public void onConnectionFailed(Messenger messenger) {
        }

        public void onLoadChildren(Messenger messenger, String str, List list, Bundle bundle) {
            if (this.f == messenger) {
                i iVar = (i) this.g.get(str);
                if (iVar != null) {
                    SubscriptionCallback callback = iVar.getCallback(this.a, bundle);
                    if (callback == null) {
                        return;
                    }
                    if (bundle == null) {
                        if (list == null) {
                            callback.onError(str);
                        } else {
                            callback.onChildrenLoaded(str, list);
                        }
                    } else if (list == null) {
                        callback.onError(str, bundle);
                    } else {
                        callback.onChildrenLoaded(str, list, bundle);
                    }
                } else if (MediaBrowserCompat.a) {
                    Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + str);
                }
            }
        }
    }

    @RequiresApi(23)
    static class d extends c {
        d(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            super(context, componentName, connectionCallback, bundle);
        }

        public void getItem(@NonNull String str, @NonNull ItemCallback itemCallback) {
            if (this.e == null) {
                t.getItem(this.b, str, itemCallback.a);
            } else {
                super.getItem(str, itemCallback);
            }
        }
    }

    @RequiresApi(26)
    static class e extends d {
        e(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            super(context, componentName, connectionCallback, bundle);
        }

        public void subscribe(@NonNull String str, @Nullable Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
            if (bundle == null) {
                s.subscribe(this.b, str, subscriptionCallback.b);
            } else {
                u.subscribe(this.b, str, bundle, subscriptionCallback.b);
            }
        }

        public void unsubscribe(@NonNull String str, SubscriptionCallback subscriptionCallback) {
            if (subscriptionCallback == null) {
                s.unsubscribe(this.b, str);
            } else {
                u.unsubscribe(this.b, str, subscriptionCallback.b);
            }
        }
    }

    static class f implements b, g {
        final Context a;
        final ComponentName b;
        final ConnectionCallback c;
        final Bundle d;
        final a e = new a(this);
        int f = 1;
        a g;
        h h;
        Messenger i;
        private final ArrayMap<String, i> j = new ArrayMap();
        private String k;
        private Token l;
        private Bundle m;

        private class a implements ServiceConnection {
            final /* synthetic */ f a;

            a(f fVar) {
                this.a = fVar;
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                a(new p(this, componentName, iBinder));
            }

            public void onServiceDisconnected(ComponentName componentName) {
                a(new q(this, componentName));
            }

            private void a(Runnable runnable) {
                if (Thread.currentThread() == this.a.e.getLooper().getThread()) {
                    runnable.run();
                } else {
                    this.a.e.post(runnable);
                }
            }

            boolean a(String str) {
                if (this.a.g == this && this.a.f != 0 && this.a.f != 1) {
                    return true;
                }
                if (!(this.a.f == 0 || this.a.f == 1)) {
                    Log.i("MediaBrowserCompat", str + " for " + this.a.b + " with mServiceConnection=" + this.a.g + " this=" + this);
                }
                return false;
            }
        }

        public f(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            if (context == null) {
                throw new IllegalArgumentException("context must not be null");
            } else if (componentName == null) {
                throw new IllegalArgumentException("service component must not be null");
            } else if (connectionCallback == null) {
                throw new IllegalArgumentException("connection callback must not be null");
            } else {
                this.a = context;
                this.b = componentName;
                this.c = connectionCallback;
                this.d = bundle == null ? null : new Bundle(bundle);
            }
        }

        public void connect() {
            if (this.f == 0 || this.f == 1) {
                this.f = 2;
                this.e.post(new j(this));
                return;
            }
            throw new IllegalStateException("connect() called while neigther disconnecting nor disconnected (state=" + a(this.f) + ")");
        }

        public void disconnect() {
            this.f = 0;
            this.e.post(new k(this));
        }

        void a() {
            if (this.g != null) {
                this.a.unbindService(this.g);
            }
            this.f = 1;
            this.g = null;
            this.h = null;
            this.i = null;
            this.e.a(null);
            this.k = null;
            this.l = null;
        }

        public boolean isConnected() {
            return this.f == 3;
        }

        @NonNull
        public ComponentName getServiceComponent() {
            if (isConnected()) {
                return this.b;
            }
            throw new IllegalStateException("getServiceComponent() called while not connected (state=" + this.f + ")");
        }

        @NonNull
        public String getRoot() {
            if (isConnected()) {
                return this.k;
            }
            throw new IllegalStateException("getRoot() called while not connected(state=" + a(this.f) + ")");
        }

        @Nullable
        public Bundle getExtras() {
            if (isConnected()) {
                return this.m;
            }
            throw new IllegalStateException("getExtras() called while not connected (state=" + a(this.f) + ")");
        }

        @NonNull
        public Token getSessionToken() {
            if (isConnected()) {
                return this.l;
            }
            throw new IllegalStateException("getSessionToken() called while not connected(state=" + this.f + ")");
        }

        public void subscribe(@NonNull String str, Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
            i iVar;
            i iVar2 = (i) this.j.get(str);
            if (iVar2 == null) {
                iVar2 = new i();
                this.j.put(str, iVar2);
                iVar = iVar2;
            } else {
                iVar = iVar2;
            }
            Bundle bundle2 = bundle == null ? null : new Bundle(bundle);
            iVar.putCallback(this.a, bundle2, subscriptionCallback);
            if (isConnected()) {
                try {
                    this.h.a(str, subscriptionCallback.c, bundle2, this.i);
                } catch (RemoteException e) {
                    Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException parentId=" + str);
                }
            }
        }

        public void unsubscribe(@NonNull String str, SubscriptionCallback subscriptionCallback) {
            i iVar = (i) this.j.get(str);
            if (iVar != null) {
                if (subscriptionCallback == null) {
                    try {
                        if (isConnected()) {
                            this.h.a(str, null, this.i);
                        }
                    } catch (RemoteException e) {
                        Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + str);
                    }
                } else {
                    List callbacks = iVar.getCallbacks();
                    List optionsList = iVar.getOptionsList();
                    for (int size = callbacks.size() - 1; size >= 0; size--) {
                        if (callbacks.get(size) == subscriptionCallback) {
                            if (isConnected()) {
                                this.h.a(str, subscriptionCallback.c, this.i);
                            }
                            callbacks.remove(size);
                            optionsList.remove(size);
                        }
                    }
                }
                if (iVar.isEmpty() || subscriptionCallback == null) {
                    this.j.remove(str);
                }
            }
        }

        public void getItem(@NonNull String str, @NonNull ItemCallback itemCallback) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("mediaId is empty");
            } else if (itemCallback == null) {
                throw new IllegalArgumentException("cb is null");
            } else if (isConnected()) {
                try {
                    this.h.a(str, new ItemReceiver(str, itemCallback, this.e), this.i);
                } catch (RemoteException e) {
                    Log.i("MediaBrowserCompat", "Remote error getting media item: " + str);
                    this.e.post(new m(this, itemCallback, str));
                }
            } else {
                Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
                this.e.post(new l(this, itemCallback, str));
            }
        }

        public void search(@NonNull String str, Bundle bundle, @NonNull SearchCallback searchCallback) {
            if (isConnected()) {
                try {
                    this.h.a(str, bundle, new SearchResultReceiver(str, bundle, searchCallback, this.e), this.i);
                    return;
                } catch (Throwable e) {
                    Log.i("MediaBrowserCompat", "Remote error searching items with query: " + str, e);
                    this.e.post(new n(this, searchCallback, str, bundle));
                    return;
                }
            }
            throw new IllegalStateException("search() called while not connected (state=" + a(this.f) + ")");
        }

        public void sendCustomAction(@NonNull String str, Bundle bundle, @Nullable CustomActionCallback customActionCallback) {
            if (isConnected()) {
                try {
                    this.h.b(str, bundle, new CustomActionResultReceiver(str, bundle, customActionCallback, this.e), this.i);
                    return;
                } catch (Throwable e) {
                    Log.i("MediaBrowserCompat", "Remote error sending a custom action: action=" + str + ", extras=" + bundle, e);
                    if (customActionCallback != null) {
                        this.e.post(new o(this, customActionCallback, str, bundle));
                        return;
                    }
                    return;
                }
            }
            throw new IllegalStateException("Cannot send a custom action (" + str + ") with " + "extras " + bundle + " because the browser is not connected to the " + "service.");
        }

        public void onServiceConnected(Messenger messenger, String str, Token token, Bundle bundle) {
            if (!a(messenger, "onConnect")) {
                return;
            }
            if (this.f != 2) {
                Log.w("MediaBrowserCompat", "onConnect from service while mState=" + a(this.f) + "... ignoring");
                return;
            }
            this.k = str;
            this.l = token;
            this.m = bundle;
            this.f = 3;
            if (MediaBrowserCompat.a) {
                Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                b();
            }
            this.c.onConnected();
            try {
                for (Entry entry : this.j.entrySet()) {
                    String str2 = (String) entry.getKey();
                    i iVar = (i) entry.getValue();
                    List callbacks = iVar.getCallbacks();
                    List optionsList = iVar.getOptionsList();
                    for (int i = 0; i < callbacks.size(); i++) {
                        this.h.a(str2, ((SubscriptionCallback) callbacks.get(i)).c, (Bundle) optionsList.get(i), this.i);
                    }
                }
            } catch (RemoteException e) {
                Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
            }
        }

        public void onConnectionFailed(Messenger messenger) {
            Log.e("MediaBrowserCompat", "onConnectFailed for " + this.b);
            if (!a(messenger, "onConnectFailed")) {
                return;
            }
            if (this.f != 2) {
                Log.w("MediaBrowserCompat", "onConnect from service while mState=" + a(this.f) + "... ignoring");
                return;
            }
            a();
            this.c.onConnectionFailed();
        }

        public void onLoadChildren(Messenger messenger, String str, List list, Bundle bundle) {
            if (a(messenger, "onLoadChildren")) {
                if (MediaBrowserCompat.a) {
                    Log.d("MediaBrowserCompat", "onLoadChildren for " + this.b + " id=" + str);
                }
                i iVar = (i) this.j.get(str);
                if (iVar != null) {
                    SubscriptionCallback callback = iVar.getCallback(this.a, bundle);
                    if (callback == null) {
                        return;
                    }
                    if (bundle == null) {
                        if (list == null) {
                            callback.onError(str);
                        } else {
                            callback.onChildrenLoaded(str, list);
                        }
                    } else if (list == null) {
                        callback.onError(str, bundle);
                    } else {
                        callback.onChildrenLoaded(str, list, bundle);
                    }
                } else if (MediaBrowserCompat.a) {
                    Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + str);
                }
            }
        }

        private static String a(int i) {
            switch (i) {
                case 0:
                    return "CONNECT_STATE_DISCONNECTING";
                case 1:
                    return "CONNECT_STATE_DISCONNECTED";
                case 2:
                    return "CONNECT_STATE_CONNECTING";
                case 3:
                    return "CONNECT_STATE_CONNECTED";
                case 4:
                    return "CONNECT_STATE_SUSPENDED";
                default:
                    return "UNKNOWN/" + i;
            }
        }

        private boolean a(Messenger messenger, String str) {
            if (this.i == messenger && this.f != 0 && this.f != 1) {
                return true;
            }
            if (!(this.f == 0 || this.f == 1)) {
                Log.i("MediaBrowserCompat", str + " for " + this.b + " with mCallbacksMessenger=" + this.i + " this=" + this);
            }
            return false;
        }

        void b() {
            Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
            Log.d("MediaBrowserCompat", "  mServiceComponent=" + this.b);
            Log.d("MediaBrowserCompat", "  mCallback=" + this.c);
            Log.d("MediaBrowserCompat", "  mRootHints=" + this.d);
            Log.d("MediaBrowserCompat", "  mState=" + a(this.f));
            Log.d("MediaBrowserCompat", "  mServiceConnection=" + this.g);
            Log.d("MediaBrowserCompat", "  mServiceBinderWrapper=" + this.h);
            Log.d("MediaBrowserCompat", "  mCallbacksMessenger=" + this.i);
            Log.d("MediaBrowserCompat", "  mRootId=" + this.k);
            Log.d("MediaBrowserCompat", "  mMediaSessionToken=" + this.l);
        }
    }

    private static class h {
        private Messenger a;
        private Bundle b;

        public h(IBinder iBinder, Bundle bundle) {
            this.a = new Messenger(iBinder);
            this.b = bundle;
        }

        void a(Context context, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("data_package_name", context.getPackageName());
            bundle.putBundle("data_root_hints", this.b);
            a(1, bundle, messenger);
        }

        void a(Messenger messenger) throws RemoteException {
            a(2, null, messenger);
        }

        void a(String str, IBinder iBinder, Bundle bundle, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", str);
            BundleCompat.putBinder(bundle2, "data_callback_token", iBinder);
            bundle2.putBundle("data_options", bundle);
            a(3, bundle2, messenger);
        }

        void a(String str, IBinder iBinder, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("data_media_item_id", str);
            BundleCompat.putBinder(bundle, "data_callback_token", iBinder);
            a(4, bundle, messenger);
        }

        void a(String str, ResultReceiver resultReceiver, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("data_media_item_id", str);
            bundle.putParcelable("data_result_receiver", resultReceiver);
            a(5, bundle, messenger);
        }

        void b(Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putBundle("data_root_hints", this.b);
            a(6, bundle, messenger);
        }

        void c(Messenger messenger) throws RemoteException {
            a(7, null, messenger);
        }

        void a(String str, Bundle bundle, ResultReceiver resultReceiver, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_search_query", str);
            bundle2.putBundle("data_search_extras", bundle);
            bundle2.putParcelable("data_result_receiver", resultReceiver);
            a(8, bundle2, messenger);
        }

        void b(String str, Bundle bundle, ResultReceiver resultReceiver, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_custom_action", str);
            bundle2.putBundle("data_custom_action_extras", bundle);
            bundle2.putParcelable("data_result_receiver", resultReceiver);
            a(9, bundle2, messenger);
        }

        private void a(int i, Bundle bundle, Messenger messenger) throws RemoteException {
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.arg1 = 1;
            obtain.setData(bundle);
            obtain.replyTo = messenger;
            this.a.send(obtain);
        }
    }

    private static class i {
        private final List<SubscriptionCallback> a = new ArrayList();
        private final List<Bundle> b = new ArrayList();

        public boolean isEmpty() {
            return this.a.isEmpty();
        }

        public List<Bundle> getOptionsList() {
            return this.b;
        }

        public List<SubscriptionCallback> getCallbacks() {
            return this.a;
        }

        public SubscriptionCallback getCallback(Context context, Bundle bundle) {
            if (bundle != null) {
                bundle.setClassLoader(context.getClassLoader());
            }
            for (int i = 0; i < this.b.size(); i++) {
                if (MediaBrowserCompatUtils.areSameOptions((Bundle) this.b.get(i), bundle)) {
                    return (SubscriptionCallback) this.a.get(i);
                }
            }
            return null;
        }

        public void putCallback(Context context, Bundle bundle, SubscriptionCallback subscriptionCallback) {
            if (bundle != null) {
                bundle.setClassLoader(context.getClassLoader());
            }
            for (int i = 0; i < this.b.size(); i++) {
                if (MediaBrowserCompatUtils.areSameOptions((Bundle) this.b.get(i), bundle)) {
                    this.a.set(i, subscriptionCallback);
                    return;
                }
            }
            this.a.add(subscriptionCallback);
            this.b.add(bundle);
        }
    }

    public MediaBrowserCompat(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
        if (VERSION.SDK_INT >= 26) {
            this.b = new e(context, componentName, connectionCallback, bundle);
        } else if (VERSION.SDK_INT >= 23) {
            this.b = new d(context, componentName, connectionCallback, bundle);
        } else if (VERSION.SDK_INT >= 21) {
            this.b = new c(context, componentName, connectionCallback, bundle);
        } else {
            this.b = new f(context, componentName, connectionCallback, bundle);
        }
    }

    public void connect() {
        this.b.connect();
    }

    public void disconnect() {
        this.b.disconnect();
    }

    public boolean isConnected() {
        return this.b.isConnected();
    }

    @NonNull
    public ComponentName getServiceComponent() {
        return this.b.getServiceComponent();
    }

    @NonNull
    public String getRoot() {
        return this.b.getRoot();
    }

    @Nullable
    public Bundle getExtras() {
        return this.b.getExtras();
    }

    @NonNull
    public Token getSessionToken() {
        return this.b.getSessionToken();
    }

    public void subscribe(@NonNull String str, @NonNull SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        } else if (subscriptionCallback == null) {
            throw new IllegalArgumentException("callback is null");
        } else {
            this.b.subscribe(str, null, subscriptionCallback);
        }
    }

    public void subscribe(@NonNull String str, @NonNull Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        } else if (subscriptionCallback == null) {
            throw new IllegalArgumentException("callback is null");
        } else if (bundle == null) {
            throw new IllegalArgumentException("options are null");
        } else {
            this.b.subscribe(str, bundle, subscriptionCallback);
        }
    }

    public void unsubscribe(@NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        this.b.unsubscribe(str, null);
    }

    public void unsubscribe(@NonNull String str, @NonNull SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        } else if (subscriptionCallback == null) {
            throw new IllegalArgumentException("callback is null");
        } else {
            this.b.unsubscribe(str, subscriptionCallback);
        }
    }

    public void getItem(@NonNull String str, @NonNull ItemCallback itemCallback) {
        this.b.getItem(str, itemCallback);
    }

    public void search(@NonNull String str, Bundle bundle, @NonNull SearchCallback searchCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("query cannot be empty");
        } else if (searchCallback == null) {
            throw new IllegalArgumentException("callback cannot be null");
        } else {
            this.b.search(str, bundle, searchCallback);
        }
    }

    public void sendCustomAction(@NonNull String str, Bundle bundle, @Nullable CustomActionCallback customActionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("action cannot be empty");
        }
        this.b.sendCustomAction(str, bundle, customActionCallback);
    }
}
