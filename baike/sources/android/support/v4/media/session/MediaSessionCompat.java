package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaMetadataEditor;
import android.media.RemoteControlClient;
import android.media.RemoteControlClient.MetadataEditor;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.MediaMetadataCompat.Builder;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.session.IMediaSession.Stub;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MediaSessionCompat {
    public static final String ACTION_FLAG_AS_INAPPROPRIATE = "android.support.v4.media.session.action.FLAG_AS_INAPPROPRIATE";
    public static final String ACTION_FOLLOW = "android.support.v4.media.session.action.FOLLOW";
    public static final String ACTION_SKIP_AD = "android.support.v4.media.session.action.SKIP_AD";
    public static final String ACTION_UNFOLLOW = "android.support.v4.media.session.action.UNFOLLOW";
    public static final String ARGUMENT_MEDIA_ATTRIBUTE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE";
    public static final String ARGUMENT_MEDIA_ATTRIBUTE_VALUE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE_VALUE";
    public static final int FLAG_HANDLES_MEDIA_BUTTONS = 1;
    public static final int FLAG_HANDLES_QUEUE_COMMANDS = 4;
    public static final int FLAG_HANDLES_TRANSPORT_CONTROLS = 2;
    public static final int MEDIA_ATTRIBUTE_ALBUM = 1;
    public static final int MEDIA_ATTRIBUTE_ARTIST = 0;
    public static final int MEDIA_ATTRIBUTE_PLAYLIST = 2;
    static int a;
    private final a b;
    private final MediaControllerCompat c;
    private final ArrayList<OnActiveChangeListener> d;

    public static abstract class Callback {
        private WeakReference<a> a;
        final Object b;
        private a c = null;
        private boolean d;

        private class a extends Handler {
            final /* synthetic */ Callback a;

            a(Callback callback, Looper looper) {
                this.a = callback;
                super(looper);
            }

            public void handleMessage(Message message) {
                if (message.what == 1) {
                    this.a.a();
                }
            }
        }

        @RequiresApi(21)
        private class b implements a {
            final /* synthetic */ Callback a;

            b(Callback callback) {
                this.a = callback;
            }

            public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
                IBinder iBinder = null;
                try {
                    d dVar;
                    if (str.equals("android.support.v4.media.session.command.GET_EXTRA_BINDER")) {
                        dVar = (d) this.a.a.get();
                        if (dVar != null) {
                            Bundle bundle2 = new Bundle();
                            IMediaSession extraBinder = dVar.getSessionToken().getExtraBinder();
                            String str2 = "android.support.v4.media.session.EXTRA_BINDER";
                            if (extraBinder != null) {
                                iBinder = extraBinder.asBinder();
                            }
                            BundleCompat.putBinder(bundle2, str2, iBinder);
                            resultReceiver.send(0, bundle2);
                        }
                    } else if (str.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM")) {
                        bundle.setClassLoader(MediaDescriptionCompat.class.getClassLoader());
                        this.a.onAddQueueItem((MediaDescriptionCompat) bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
                    } else if (str.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT")) {
                        bundle.setClassLoader(MediaDescriptionCompat.class.getClassLoader());
                        this.a.onAddQueueItem((MediaDescriptionCompat) bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"), bundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX"));
                    } else if (str.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM")) {
                        bundle.setClassLoader(MediaDescriptionCompat.class.getClassLoader());
                        this.a.onRemoveQueueItem((MediaDescriptionCompat) bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
                    } else if (str.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT")) {
                        dVar = (d) this.a.a.get();
                        if (dVar != null && dVar.j != null) {
                            QueueItem queueItem;
                            int i = bundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX", -1);
                            if (i < 0 || i >= dVar.j.size()) {
                                queueItem = null;
                            } else {
                                queueItem = (QueueItem) dVar.j.get(i);
                            }
                            if (queueItem != null) {
                                this.a.onRemoveQueueItem(queueItem.getDescription());
                            }
                        }
                    } else {
                        this.a.onCommand(str, bundle, resultReceiver);
                    }
                } catch (BadParcelableException e) {
                    Log.e("MediaSessionCompat", "Could not unparcel the extra data.");
                }
            }

            public boolean onMediaButtonEvent(Intent intent) {
                return this.a.onMediaButtonEvent(intent);
            }

            public void onPlay() {
                this.a.onPlay();
            }

            public void onPlayFromMediaId(String str, Bundle bundle) {
                this.a.onPlayFromMediaId(str, bundle);
            }

            public void onPlayFromSearch(String str, Bundle bundle) {
                this.a.onPlayFromSearch(str, bundle);
            }

            public void onSkipToQueueItem(long j) {
                this.a.onSkipToQueueItem(j);
            }

            public void onPause() {
                this.a.onPause();
            }

            public void onSkipToNext() {
                this.a.onSkipToNext();
            }

            public void onSkipToPrevious() {
                this.a.onSkipToPrevious();
            }

            public void onFastForward() {
                this.a.onFastForward();
            }

            public void onRewind() {
                this.a.onRewind();
            }

            public void onStop() {
                this.a.onStop();
            }

            public void onSeekTo(long j) {
                this.a.onSeekTo(j);
            }

            public void onSetRating(Object obj) {
                this.a.onSetRating(RatingCompat.fromRating(obj));
            }

            public void onSetRating(Object obj, Bundle bundle) {
                this.a.onSetRating(RatingCompat.fromRating(obj), bundle);
            }

            public void onCustomAction(String str, Bundle bundle) {
                if (str.equals("android.support.v4.media.session.action.PLAY_FROM_URI")) {
                    this.a.onPlayFromUri((Uri) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI"), (Bundle) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                } else if (str.equals("android.support.v4.media.session.action.PREPARE")) {
                    this.a.onPrepare();
                } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID")) {
                    this.a.onPrepareFromMediaId(bundle.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID"), bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH")) {
                    this.a.onPrepareFromSearch(bundle.getString("android.support.v4.media.session.action.ARGUMENT_QUERY"), bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_URI")) {
                    this.a.onPrepareFromUri((Uri) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI"), bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                } else if (str.equals("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED")) {
                    this.a.onSetCaptioningEnabled(bundle.getBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED"));
                } else if (str.equals("android.support.v4.media.session.action.SET_REPEAT_MODE")) {
                    this.a.onSetRepeatMode(bundle.getInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE"));
                } else if (str.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE")) {
                    this.a.onSetShuffleMode(bundle.getInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE"));
                } else if (str.equals("android.support.v4.media.session.action.SET_RATING")) {
                    bundle.setClassLoader(RatingCompat.class.getClassLoader());
                    this.a.onSetRating((RatingCompat) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_RATING"), bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                } else {
                    this.a.onCustomAction(str, bundle);
                }
            }
        }

        @RequiresApi(23)
        private class c extends b implements android.support.v4.media.session.MediaSessionCompatApi23.Callback {
            final /* synthetic */ Callback b;

            c(Callback callback) {
                this.b = callback;
                super(callback);
            }

            public void onPlayFromUri(Uri uri, Bundle bundle) {
                this.b.onPlayFromUri(uri, bundle);
            }
        }

        @RequiresApi(24)
        private class d extends c implements android.support.v4.media.session.MediaSessionCompatApi24.Callback {
            final /* synthetic */ Callback c;

            d(Callback callback) {
                this.c = callback;
                super(callback);
            }

            public void onPrepare() {
                this.c.onPrepare();
            }

            public void onPrepareFromMediaId(String str, Bundle bundle) {
                this.c.onPrepareFromMediaId(str, bundle);
            }

            public void onPrepareFromSearch(String str, Bundle bundle) {
                this.c.onPrepareFromSearch(str, bundle);
            }

            public void onPrepareFromUri(Uri uri, Bundle bundle) {
                this.c.onPrepareFromUri(uri, bundle);
            }
        }

        public Callback() {
            if (VERSION.SDK_INT >= 24) {
                this.b = MediaSessionCompatApi24.createCallback(new d(this));
            } else if (VERSION.SDK_INT >= 23) {
                this.b = MediaSessionCompatApi23.createCallback(new c(this));
            } else if (VERSION.SDK_INT >= 21) {
                this.b = i.createCallback(new b(this));
            } else {
                this.b = null;
            }
        }

        private void a(a aVar, Handler handler) {
            this.a = new WeakReference(aVar);
            if (this.c != null) {
                this.c.removeCallbacksAndMessages(null);
            }
            this.c = new a(this, handler.getLooper());
        }

        public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
        }

        public boolean onMediaButtonEvent(Intent intent) {
            a aVar = (a) this.a.get();
            if (aVar == null || this.c == null) {
                return false;
            }
            KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
            if (keyEvent == null || keyEvent.getAction() != 0) {
                return false;
            }
            switch (keyEvent.getKeyCode()) {
                case 79:
                case 85:
                    if (keyEvent.getRepeatCount() > 0) {
                        a();
                    } else if (this.d) {
                        this.c.removeMessages(1);
                        this.d = false;
                        PlaybackStateCompat playbackState = aVar.getPlaybackState();
                        if (((playbackState == null ? 0 : playbackState.getActions()) & 32) != 0) {
                            onSkipToNext();
                        }
                    } else {
                        this.d = true;
                        this.c.sendEmptyMessageDelayed(1, (long) ViewConfiguration.getDoubleTapTimeout());
                    }
                    return true;
                default:
                    a();
                    return false;
            }
        }

        private void a() {
            if (this.d) {
                this.d = false;
                this.c.removeMessages(1);
                a aVar = (a) this.a.get();
                if (aVar != null) {
                    int i;
                    PlaybackStateCompat playbackState = aVar.getPlaybackState();
                    long actions = playbackState == null ? 0 : playbackState.getActions();
                    if (playbackState == null || playbackState.getState() != 3) {
                        boolean z = false;
                    } else {
                        i = 1;
                    }
                    if ((516 & actions) != 0) {
                        int i2 = 1;
                    } else {
                        boolean z2 = false;
                    }
                    if ((actions & 514) != 0) {
                        int i3 = 1;
                    } else {
                        boolean z3 = false;
                    }
                    if (i != 0 && r0 != 0) {
                        onPause();
                    } else if (i == 0 && r3 != 0) {
                        onPlay();
                    }
                }
            }
        }

        public void onPrepare() {
        }

        public void onPrepareFromMediaId(String str, Bundle bundle) {
        }

        public void onPrepareFromSearch(String str, Bundle bundle) {
        }

        public void onPrepareFromUri(Uri uri, Bundle bundle) {
        }

        public void onPlay() {
        }

        public void onPlayFromMediaId(String str, Bundle bundle) {
        }

        public void onPlayFromSearch(String str, Bundle bundle) {
        }

        public void onPlayFromUri(Uri uri, Bundle bundle) {
        }

        public void onSkipToQueueItem(long j) {
        }

        public void onPause() {
        }

        public void onSkipToNext() {
        }

        public void onSkipToPrevious() {
        }

        public void onFastForward() {
        }

        public void onRewind() {
        }

        public void onStop() {
        }

        public void onSeekTo(long j) {
        }

        public void onSetRating(RatingCompat ratingCompat) {
        }

        public void onSetRating(RatingCompat ratingCompat, Bundle bundle) {
        }

        public void onSetCaptioningEnabled(boolean z) {
        }

        public void onSetRepeatMode(int i) {
        }

        public void onSetShuffleMode(int i) {
        }

        public void onCustomAction(String str, Bundle bundle) {
        }

        public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        }

        public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
        }

        public void onRemoveQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        }

        @Deprecated
        public void onRemoveQueueItemAt(int i) {
        }
    }

    public interface OnActiveChangeListener {
        void onActiveChanged();
    }

    public static final class QueueItem implements Parcelable {
        public static final Creator<QueueItem> CREATOR = new f();
        public static final int UNKNOWN_ID = -1;
        private final MediaDescriptionCompat a;
        private final long b;
        private Object c;

        public QueueItem(MediaDescriptionCompat mediaDescriptionCompat, long j) {
            this(null, mediaDescriptionCompat, j);
        }

        private QueueItem(Object obj, MediaDescriptionCompat mediaDescriptionCompat, long j) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("Description cannot be null.");
            } else if (j == -1) {
                throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            } else {
                this.a = mediaDescriptionCompat;
                this.b = j;
                this.c = obj;
            }
        }

        QueueItem(Parcel parcel) {
            this.a = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
            this.b = parcel.readLong();
        }

        public MediaDescriptionCompat getDescription() {
            return this.a;
        }

        public long getQueueId() {
            return this.b;
        }

        public void writeToParcel(Parcel parcel, int i) {
            this.a.writeToParcel(parcel, i);
            parcel.writeLong(this.b);
        }

        public int describeContents() {
            return 0;
        }

        public Object getQueueItem() {
            if (this.c != null || VERSION.SDK_INT < 21) {
                return this.c;
            }
            this.c = c.createItem(this.a.getMediaDescription(), this.b);
            return this.c;
        }

        public static QueueItem fromQueueItem(Object obj) {
            if (obj == null || VERSION.SDK_INT < 21) {
                return null;
            }
            return new QueueItem(obj, MediaDescriptionCompat.fromMediaDescription(c.getDescription(obj)), c.getQueueId(obj));
        }

        public static List<QueueItem> fromQueueItemList(List<?> list) {
            if (list == null || VERSION.SDK_INT < 21) {
                return null;
            }
            List<QueueItem> arrayList = new ArrayList();
            for (Object fromQueueItem : list) {
                arrayList.add(fromQueueItem(fromQueueItem));
            }
            return arrayList;
        }

        public String toString() {
            return "MediaSession.QueueItem {Description=" + this.a + ", Id=" + this.b + " }";
        }
    }

    static final class ResultReceiverWrapper implements Parcelable {
        public static final Creator<ResultReceiverWrapper> CREATOR = new g();
        private ResultReceiver a;

        public ResultReceiverWrapper(ResultReceiver resultReceiver) {
            this.a = resultReceiver;
        }

        ResultReceiverWrapper(Parcel parcel) {
            this.a = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            this.a.writeToParcel(parcel, i);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SessionFlags {
    }

    public static final class Token implements Parcelable {
        public static final Creator<Token> CREATOR = new h();
        private final Object a;
        private final IMediaSession b;

        Token(Object obj) {
            this(obj, null);
        }

        Token(Object obj, IMediaSession iMediaSession) {
            this.a = obj;
            this.b = iMediaSession;
        }

        public static Token fromToken(Object obj) {
            return fromToken(obj, null);
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public static Token fromToken(Object obj, IMediaSession iMediaSession) {
            if (obj == null || VERSION.SDK_INT < 21) {
                return null;
            }
            return new Token(i.verifyToken(obj), iMediaSession);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            if (VERSION.SDK_INT >= 21) {
                parcel.writeParcelable((Parcelable) this.a, i);
            } else {
                parcel.writeStrongBinder((IBinder) this.a);
            }
        }

        public int hashCode() {
            if (this.a == null) {
                return 0;
            }
            return this.a.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Token)) {
                return false;
            }
            Token token = (Token) obj;
            if (this.a == null) {
                if (token.a != null) {
                    return false;
                }
                return true;
            } else if (token.a == null) {
                return false;
            } else {
                return this.a.equals(token.a);
            }
        }

        public Object getToken() {
            return this.a;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public IMediaSession getExtraBinder() {
            return this.b;
        }
    }

    interface a {
        String getCallingPackage();

        Object getMediaSession();

        PlaybackStateCompat getPlaybackState();

        Object getRemoteControlClient();

        Token getSessionToken();

        boolean isActive();

        void release();

        void sendSessionEvent(String str, Bundle bundle);

        void setActive(boolean z);

        void setCallback(Callback callback, Handler handler);

        void setCaptioningEnabled(boolean z);

        void setExtras(Bundle bundle);

        void setFlags(int i);

        void setMediaButtonReceiver(PendingIntent pendingIntent);

        void setMetadata(MediaMetadataCompat mediaMetadataCompat);

        void setPlaybackState(PlaybackStateCompat playbackStateCompat);

        void setPlaybackToLocal(int i);

        void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat);

        void setQueue(List<QueueItem> list);

        void setQueueTitle(CharSequence charSequence);

        void setRatingType(int i);

        void setRepeatMode(int i);

        void setSessionActivity(PendingIntent pendingIntent);

        void setShuffleMode(int i);
    }

    static class e implements a {
        private final b A;
        private final Token B;
        private c C;
        private boolean D = false;
        private boolean E = false;
        private android.support.v4.media.VolumeProviderCompat.Callback F = new e(this);
        final String a;
        final String b;
        final AudioManager c;
        final RemoteControlClient d;
        final Object e = new Object();
        final RemoteCallbackList<IMediaControllerCallback> f = new RemoteCallbackList();
        boolean g = false;
        boolean h = false;
        volatile Callback i;
        int j;
        MediaMetadataCompat k;
        PlaybackStateCompat l;
        PendingIntent m;
        List<QueueItem> n;
        CharSequence o;
        int p;
        boolean q;
        int r;
        int s;
        Bundle t;
        int u;
        int v;
        VolumeProviderCompat w;
        private final Context x;
        private final ComponentName y;
        private final PendingIntent z;

        private static final class a {
            public final String command;
            public final Bundle extras;
            public final ResultReceiver stub;

            public a(String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.command = str;
                this.extras = bundle;
                this.stub = resultReceiver;
            }
        }

        class b extends Stub {
            final /* synthetic */ e a;

            b(e eVar) {
                this.a = eVar;
            }

            public void sendCommand(String str, Bundle bundle, ResultReceiverWrapper resultReceiverWrapper) {
                this.a.a(1, new a(str, bundle, resultReceiverWrapper.a));
            }

            public boolean sendMediaButton(KeyEvent keyEvent) {
                boolean z = (this.a.j & 1) != 0;
                if (z) {
                    this.a.a(21, (Object) keyEvent);
                }
                return z;
            }

            public void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                if (this.a.g) {
                    try {
                        iMediaControllerCallback.onSessionDestroyed();
                        return;
                    } catch (Exception e) {
                        return;
                    }
                }
                this.a.f.register(iMediaControllerCallback);
            }

            public void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                this.a.f.unregister(iMediaControllerCallback);
            }

            public String getPackageName() {
                return this.a.a;
            }

            public String getTag() {
                return this.a.b;
            }

            public PendingIntent getLaunchPendingIntent() {
                PendingIntent pendingIntent;
                synchronized (this.a.e) {
                    pendingIntent = this.a.m;
                }
                return pendingIntent;
            }

            public long getFlags() {
                long j;
                synchronized (this.a.e) {
                    j = (long) this.a.j;
                }
                return j;
            }

            public ParcelableVolumeInfo getVolumeAttributes() {
                int i;
                int i2;
                int maxVolume;
                int currentVolume;
                int i3 = 2;
                synchronized (this.a.e) {
                    i = this.a.u;
                    i2 = this.a.v;
                    VolumeProviderCompat volumeProviderCompat = this.a.w;
                    if (i == 2) {
                        i3 = volumeProviderCompat.getVolumeControl();
                        maxVolume = volumeProviderCompat.getMaxVolume();
                        currentVolume = volumeProviderCompat.getCurrentVolume();
                    } else {
                        maxVolume = this.a.c.getStreamMaxVolume(i2);
                        currentVolume = this.a.c.getStreamVolume(i2);
                    }
                }
                return new ParcelableVolumeInfo(i, i2, i3, maxVolume, currentVolume);
            }

            public void adjustVolume(int i, int i2, String str) {
                this.a.b(i, i2);
            }

            public void setVolumeTo(int i, int i2, String str) {
                this.a.c(i, i2);
            }

            public void prepare() throws RemoteException {
                this.a.a(3);
            }

            public void prepareFromMediaId(String str, Bundle bundle) throws RemoteException {
                this.a.a(4, (Object) str, bundle);
            }

            public void prepareFromSearch(String str, Bundle bundle) throws RemoteException {
                this.a.a(5, (Object) str, bundle);
            }

            public void prepareFromUri(Uri uri, Bundle bundle) throws RemoteException {
                this.a.a(6, (Object) uri, bundle);
            }

            public void play() throws RemoteException {
                this.a.a(7);
            }

            public void playFromMediaId(String str, Bundle bundle) throws RemoteException {
                this.a.a(8, (Object) str, bundle);
            }

            public void playFromSearch(String str, Bundle bundle) throws RemoteException {
                this.a.a(9, (Object) str, bundle);
            }

            public void playFromUri(Uri uri, Bundle bundle) throws RemoteException {
                this.a.a(10, (Object) uri, bundle);
            }

            public void skipToQueueItem(long j) {
                this.a.a(11, Long.valueOf(j));
            }

            public void pause() throws RemoteException {
                this.a.a(12);
            }

            public void stop() throws RemoteException {
                this.a.a(13);
            }

            public void next() throws RemoteException {
                this.a.a(14);
            }

            public void previous() throws RemoteException {
                this.a.a(15);
            }

            public void fastForward() throws RemoteException {
                this.a.a(16);
            }

            public void rewind() throws RemoteException {
                this.a.a(17);
            }

            public void seekTo(long j) throws RemoteException {
                this.a.a(18, Long.valueOf(j));
            }

            public void rate(RatingCompat ratingCompat) throws RemoteException {
                this.a.a(19, (Object) ratingCompat);
            }

            public void rateWithExtras(RatingCompat ratingCompat, Bundle bundle) throws RemoteException {
                this.a.a(31, (Object) ratingCompat, bundle);
            }

            public void setCaptioningEnabled(boolean z) throws RemoteException {
                this.a.a(29, Boolean.valueOf(z));
            }

            public void setRepeatMode(int i) throws RemoteException {
                this.a.a(23, i);
            }

            public void setShuffleModeEnabledRemoved(boolean z) throws RemoteException {
            }

            public void setShuffleMode(int i) throws RemoteException {
                this.a.a(30, i);
            }

            public void sendCustomAction(String str, Bundle bundle) throws RemoteException {
                this.a.a(20, (Object) str, bundle);
            }

            public MediaMetadataCompat getMetadata() {
                return this.a.k;
            }

            public PlaybackStateCompat getPlaybackState() {
                PlaybackStateCompat playbackStateCompat;
                MediaMetadataCompat mediaMetadataCompat;
                synchronized (this.a.e) {
                    playbackStateCompat = this.a.l;
                    mediaMetadataCompat = this.a.k;
                }
                return MediaSessionCompat.b(playbackStateCompat, mediaMetadataCompat);
            }

            public List<QueueItem> getQueue() {
                List<QueueItem> list;
                synchronized (this.a.e) {
                    list = this.a.n;
                }
                return list;
            }

            public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
                this.a.a(25, (Object) mediaDescriptionCompat);
            }

            public void addQueueItemAt(MediaDescriptionCompat mediaDescriptionCompat, int i) {
                this.a.a(26, (Object) mediaDescriptionCompat, i);
            }

            public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
                this.a.a(27, (Object) mediaDescriptionCompat);
            }

            public void removeQueueItemAt(int i) {
                this.a.a(28, i);
            }

            public CharSequence getQueueTitle() {
                return this.a.o;
            }

            public Bundle getExtras() {
                Bundle bundle;
                synchronized (this.a.e) {
                    bundle = this.a.t;
                }
                return bundle;
            }

            public int getRatingType() {
                return this.a.p;
            }

            public boolean isCaptioningEnabled() {
                return this.a.q;
            }

            public int getRepeatMode() {
                return this.a.r;
            }

            public boolean isShuffleModeEnabledRemoved() {
                return false;
            }

            public int getShuffleMode() {
                return this.a.s;
            }

            public boolean isTransportControlEnabled() {
                return (this.a.j & 2) != 0;
            }
        }

        class c extends Handler {
            final /* synthetic */ e a;

            public c(e eVar, Looper looper) {
                this.a = eVar;
                super(looper);
            }

            public void post(int i, Object obj, Bundle bundle) {
                Message obtainMessage = obtainMessage(i, obj);
                obtainMessage.setData(bundle);
                obtainMessage.sendToTarget();
            }

            public void post(int i, Object obj) {
                obtainMessage(i, obj).sendToTarget();
            }

            public void post(int i) {
                post(i, null);
            }

            public void post(int i, Object obj, int i2) {
                obtainMessage(i, i2, 0, obj).sendToTarget();
            }

            public void handleMessage(Message message) {
                Callback callback = this.a.i;
                if (callback != null) {
                    switch (message.what) {
                        case 1:
                            a aVar = (a) message.obj;
                            callback.onCommand(aVar.command, aVar.extras, aVar.stub);
                            return;
                        case 2:
                            this.a.b(message.arg1, 0);
                            return;
                        case 3:
                            callback.onPrepare();
                            return;
                        case 4:
                            callback.onPrepareFromMediaId((String) message.obj, message.getData());
                            return;
                        case 5:
                            callback.onPrepareFromSearch((String) message.obj, message.getData());
                            return;
                        case 6:
                            callback.onPrepareFromUri((Uri) message.obj, message.getData());
                            return;
                        case 7:
                            callback.onPlay();
                            return;
                        case 8:
                            callback.onPlayFromMediaId((String) message.obj, message.getData());
                            return;
                        case 9:
                            callback.onPlayFromSearch((String) message.obj, message.getData());
                            return;
                        case 10:
                            callback.onPlayFromUri((Uri) message.obj, message.getData());
                            return;
                        case 11:
                            callback.onSkipToQueueItem(((Long) message.obj).longValue());
                            return;
                        case 12:
                            callback.onPause();
                            return;
                        case 13:
                            callback.onStop();
                            return;
                        case 14:
                            callback.onSkipToNext();
                            return;
                        case 15:
                            callback.onSkipToPrevious();
                            return;
                        case 16:
                            callback.onFastForward();
                            return;
                        case 17:
                            callback.onRewind();
                            return;
                        case 18:
                            callback.onSeekTo(((Long) message.obj).longValue());
                            return;
                        case 19:
                            callback.onSetRating((RatingCompat) message.obj);
                            return;
                        case 20:
                            callback.onCustomAction((String) message.obj, message.getData());
                            return;
                        case 21:
                            KeyEvent keyEvent = (KeyEvent) message.obj;
                            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                            intent.putExtra("android.intent.extra.KEY_EVENT", keyEvent);
                            if (!callback.onMediaButtonEvent(intent)) {
                                a(keyEvent, callback);
                                return;
                            }
                            return;
                        case 22:
                            this.a.c(message.arg1, 0);
                            return;
                        case 23:
                            callback.onSetRepeatMode(message.arg1);
                            return;
                        case 25:
                            callback.onAddQueueItem((MediaDescriptionCompat) message.obj);
                            return;
                        case 26:
                            callback.onAddQueueItem((MediaDescriptionCompat) message.obj, message.arg1);
                            return;
                        case 27:
                            callback.onRemoveQueueItem((MediaDescriptionCompat) message.obj);
                            return;
                        case 28:
                            if (this.a.n != null) {
                                QueueItem queueItem = (message.arg1 < 0 || message.arg1 >= this.a.n.size()) ? null : (QueueItem) this.a.n.get(message.arg1);
                                if (queueItem != null) {
                                    callback.onRemoveQueueItem(queueItem.getDescription());
                                    return;
                                }
                                return;
                            }
                            return;
                        case 29:
                            callback.onSetCaptioningEnabled(((Boolean) message.obj).booleanValue());
                            return;
                        case 30:
                            callback.onSetShuffleMode(message.arg1);
                            return;
                        case 31:
                            callback.onSetRating((RatingCompat) message.obj, message.getData());
                            return;
                        default:
                            return;
                    }
                }
            }

            private void a(KeyEvent keyEvent, Callback callback) {
                if (keyEvent != null && keyEvent.getAction() == 0) {
                    long actions = this.a.l == null ? 0 : this.a.l.getActions();
                    switch (keyEvent.getKeyCode()) {
                        case 79:
                        case 85:
                            Log.w("MediaSessionCompat", "KEYCODE_MEDIA_PLAY_PAUSE and KEYCODE_HEADSETHOOK are handled already");
                            return;
                        case 86:
                            if ((actions & 1) != 0) {
                                callback.onStop();
                                return;
                            }
                            return;
                        case 87:
                            if ((actions & 32) != 0) {
                                callback.onSkipToNext();
                                return;
                            }
                            return;
                        case 88:
                            if ((actions & 16) != 0) {
                                callback.onSkipToPrevious();
                                return;
                            }
                            return;
                        case 89:
                            if ((actions & 8) != 0) {
                                callback.onRewind();
                                return;
                            }
                            return;
                        case 90:
                            if ((actions & 64) != 0) {
                                callback.onFastForward();
                                return;
                            }
                            return;
                        case 126:
                            if ((actions & 4) != 0) {
                                callback.onPlay();
                                return;
                            }
                            return;
                        case 127:
                            if ((actions & 2) != 0) {
                                callback.onPause();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            }
        }

        public e(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
            if (componentName == null) {
                throw new IllegalArgumentException("MediaButtonReceiver component may not be null.");
            }
            this.x = context;
            this.a = context.getPackageName();
            this.c = (AudioManager) context.getSystemService("audio");
            this.b = str;
            this.y = componentName;
            this.z = pendingIntent;
            this.A = new b(this);
            this.B = new Token(this.A);
            this.p = 0;
            this.u = 1;
            this.v = 3;
            this.d = new RemoteControlClient(pendingIntent);
        }

        public void setCallback(Callback callback, Handler handler) {
            this.i = callback;
            if (callback != null) {
                if (handler == null) {
                    handler = new Handler();
                }
                synchronized (this.e) {
                    if (this.C != null) {
                        this.C.removeCallbacksAndMessages(null);
                    }
                    this.C = new c(this, handler.getLooper());
                    this.i.a(this, handler);
                }
            }
        }

        void a(int i) {
            a(i, null);
        }

        void a(int i, int i2) {
            a(i, null, i2);
        }

        void a(int i, Object obj) {
            a(i, obj, null);
        }

        void a(int i, Object obj, int i2) {
            synchronized (this.e) {
                if (this.C != null) {
                    this.C.post(i, obj, i2);
                }
            }
        }

        void a(int i, Object obj, Bundle bundle) {
            synchronized (this.e) {
                if (this.C != null) {
                    this.C.post(i, obj, bundle);
                }
            }
        }

        public void setFlags(int i) {
            synchronized (this.e) {
                this.j = i;
            }
            a();
        }

        public void setPlaybackToLocal(int i) {
            if (this.w != null) {
                this.w.setCallback(null);
            }
            this.u = 1;
            a(new ParcelableVolumeInfo(this.u, this.v, 2, this.c.getStreamMaxVolume(this.v), this.c.getStreamVolume(this.v)));
        }

        public void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat) {
            if (volumeProviderCompat == null) {
                throw new IllegalArgumentException("volumeProvider may not be null");
            }
            if (this.w != null) {
                this.w.setCallback(null);
            }
            this.u = 2;
            this.w = volumeProviderCompat;
            a(new ParcelableVolumeInfo(this.u, this.v, this.w.getVolumeControl(), this.w.getMaxVolume(), this.w.getCurrentVolume()));
            volumeProviderCompat.setCallback(this.F);
        }

        public void setActive(boolean z) {
            if (z != this.h) {
                this.h = z;
                if (a()) {
                    setMetadata(this.k);
                    setPlaybackState(this.l);
                }
            }
        }

        public boolean isActive() {
            return this.h;
        }

        public void sendSessionEvent(String str, Bundle bundle) {
            a(str, bundle);
        }

        public void release() {
            this.h = false;
            this.g = true;
            a();
            b();
        }

        public Token getSessionToken() {
            return this.B;
        }

        public void setPlaybackState(PlaybackStateCompat playbackStateCompat) {
            synchronized (this.e) {
                this.l = playbackStateCompat;
            }
            b(playbackStateCompat);
            if (!this.h) {
                return;
            }
            if (playbackStateCompat == null) {
                this.d.setPlaybackState(0);
                this.d.setTransportControlFlags(0);
                return;
            }
            a(playbackStateCompat);
            this.d.setTransportControlFlags(a(playbackStateCompat.getActions()));
        }

        public PlaybackStateCompat getPlaybackState() {
            PlaybackStateCompat playbackStateCompat;
            synchronized (this.e) {
                playbackStateCompat = this.l;
            }
            return playbackStateCompat;
        }

        void a(PlaybackStateCompat playbackStateCompat) {
            this.d.setPlaybackState(b(playbackStateCompat.getState()));
        }

        int b(int i) {
            switch (i) {
                case 0:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                case 4:
                    return 4;
                case 5:
                    return 5;
                case 6:
                case 8:
                    return 8;
                case 7:
                    return 9;
                case 9:
                    return 7;
                case 10:
                case 11:
                    return 6;
                default:
                    return -1;
            }
        }

        int a(long j) {
            int i = 0;
            if ((1 & j) != 0) {
                i = 32;
            }
            if ((2 & j) != 0) {
                i |= 16;
            }
            if ((4 & j) != 0) {
                i |= 4;
            }
            if ((8 & j) != 0) {
                i |= 2;
            }
            if ((16 & j) != 0) {
                i |= 1;
            }
            if ((32 & j) != 0) {
                i |= 128;
            }
            if ((64 & j) != 0) {
                i |= 64;
            }
            if ((512 & j) != 0) {
                return i | 8;
            }
            return i;
        }

        public void setMetadata(MediaMetadataCompat mediaMetadataCompat) {
            if (mediaMetadataCompat != null) {
                mediaMetadataCompat = new Builder(mediaMetadataCompat, MediaSessionCompat.a).build();
            }
            synchronized (this.e) {
                this.k = mediaMetadataCompat;
            }
            a(mediaMetadataCompat);
            if (this.h) {
                Bundle bundle;
                if (mediaMetadataCompat == null) {
                    bundle = null;
                } else {
                    bundle = mediaMetadataCompat.getBundle();
                }
                a(bundle).apply();
            }
        }

        MetadataEditor a(Bundle bundle) {
            MetadataEditor editMetadata = this.d.editMetadata(true);
            if (bundle == null) {
                return editMetadata;
            }
            Bitmap bitmap;
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_ART)) {
                bitmap = (Bitmap) bundle.getParcelable(MediaMetadataCompat.METADATA_KEY_ART);
                if (bitmap != null) {
                    bitmap = bitmap.copy(bitmap.getConfig(), false);
                }
                editMetadata.putBitmap(100, bitmap);
            } else if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_ALBUM_ART)) {
                bitmap = (Bitmap) bundle.getParcelable(MediaMetadataCompat.METADATA_KEY_ALBUM_ART);
                if (bitmap != null) {
                    bitmap = bitmap.copy(bitmap.getConfig(), false);
                }
                editMetadata.putBitmap(100, bitmap);
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_ALBUM)) {
                editMetadata.putString(1, bundle.getString(MediaMetadataCompat.METADATA_KEY_ALBUM));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST)) {
                editMetadata.putString(13, bundle.getString(MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_ARTIST)) {
                editMetadata.putString(2, bundle.getString(MediaMetadataCompat.METADATA_KEY_ARTIST));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_AUTHOR)) {
                editMetadata.putString(3, bundle.getString(MediaMetadataCompat.METADATA_KEY_AUTHOR));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_COMPILATION)) {
                editMetadata.putString(15, bundle.getString(MediaMetadataCompat.METADATA_KEY_COMPILATION));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_COMPOSER)) {
                editMetadata.putString(4, bundle.getString(MediaMetadataCompat.METADATA_KEY_COMPOSER));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_DATE)) {
                editMetadata.putString(5, bundle.getString(MediaMetadataCompat.METADATA_KEY_DATE));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_DISC_NUMBER)) {
                editMetadata.putLong(14, bundle.getLong(MediaMetadataCompat.METADATA_KEY_DISC_NUMBER));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_DURATION)) {
                editMetadata.putLong(9, bundle.getLong(MediaMetadataCompat.METADATA_KEY_DURATION));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_GENRE)) {
                editMetadata.putString(6, bundle.getString(MediaMetadataCompat.METADATA_KEY_GENRE));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_TITLE)) {
                editMetadata.putString(7, bundle.getString(MediaMetadataCompat.METADATA_KEY_TITLE));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER)) {
                editMetadata.putLong(0, bundle.getLong(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_WRITER)) {
                editMetadata.putString(11, bundle.getString(MediaMetadataCompat.METADATA_KEY_WRITER));
            }
            return editMetadata;
        }

        public void setSessionActivity(PendingIntent pendingIntent) {
            synchronized (this.e) {
                this.m = pendingIntent;
            }
        }

        public void setMediaButtonReceiver(PendingIntent pendingIntent) {
        }

        public void setQueue(List<QueueItem> list) {
            this.n = list;
            a((List) list);
        }

        public void setQueueTitle(CharSequence charSequence) {
            this.o = charSequence;
            a(charSequence);
        }

        public Object getMediaSession() {
            return null;
        }

        public Object getRemoteControlClient() {
            return null;
        }

        public String getCallingPackage() {
            return null;
        }

        public void setRatingType(int i) {
            this.p = i;
        }

        public void setCaptioningEnabled(boolean z) {
            if (this.q != z) {
                this.q = z;
                a(z);
            }
        }

        public void setRepeatMode(int i) {
            if (this.r != i) {
                this.r = i;
                c(i);
            }
        }

        public void setShuffleMode(int i) {
            if (this.s != i) {
                this.s = i;
                d(i);
            }
        }

        public void setExtras(Bundle bundle) {
            this.t = bundle;
            b(bundle);
        }

        boolean a() {
            if (this.h) {
                if (!this.D && (this.j & 1) != 0) {
                    a(this.z, this.y);
                    this.D = true;
                } else if (this.D && (this.j & 1) == 0) {
                    b(this.z, this.y);
                    this.D = false;
                }
                if (!this.E && (this.j & 2) != 0) {
                    this.c.registerRemoteControlClient(this.d);
                    this.E = true;
                    return true;
                } else if (this.E && (this.j & 2) == 0) {
                    this.d.setPlaybackState(0);
                    this.c.unregisterRemoteControlClient(this.d);
                    this.E = false;
                    return false;
                }
            }
            if (this.D) {
                b(this.z, this.y);
                this.D = false;
            }
            if (this.E) {
                this.d.setPlaybackState(0);
                this.c.unregisterRemoteControlClient(this.d);
                this.E = false;
            }
            return false;
        }

        void a(PendingIntent pendingIntent, ComponentName componentName) {
            this.c.registerMediaButtonEventReceiver(componentName);
        }

        void b(PendingIntent pendingIntent, ComponentName componentName) {
            this.c.unregisterMediaButtonEventReceiver(componentName);
        }

        void b(int i, int i2) {
            if (this.u != 2) {
                this.c.adjustStreamVolume(this.v, i, i2);
            } else if (this.w != null) {
                this.w.onAdjustVolume(i);
            }
        }

        void c(int i, int i2) {
            if (this.u != 2) {
                this.c.setStreamVolume(this.v, i, i2);
            } else if (this.w != null) {
                this.w.onSetVolumeTo(i);
            }
        }

        void a(ParcelableVolumeInfo parcelableVolumeInfo) {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.f.getBroadcastItem(beginBroadcast)).onVolumeInfoChanged(parcelableVolumeInfo);
                } catch (RemoteException e) {
                }
            }
            this.f.finishBroadcast();
        }

        private void b() {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.f.getBroadcastItem(beginBroadcast)).onSessionDestroyed();
                } catch (RemoteException e) {
                }
            }
            this.f.finishBroadcast();
            this.f.kill();
        }

        private void a(String str, Bundle bundle) {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.f.getBroadcastItem(beginBroadcast)).onEvent(str, bundle);
                } catch (RemoteException e) {
                }
            }
            this.f.finishBroadcast();
        }

        private void b(PlaybackStateCompat playbackStateCompat) {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.f.getBroadcastItem(beginBroadcast)).onPlaybackStateChanged(playbackStateCompat);
                } catch (RemoteException e) {
                }
            }
            this.f.finishBroadcast();
        }

        private void a(MediaMetadataCompat mediaMetadataCompat) {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.f.getBroadcastItem(beginBroadcast)).onMetadataChanged(mediaMetadataCompat);
                } catch (RemoteException e) {
                }
            }
            this.f.finishBroadcast();
        }

        private void a(List<QueueItem> list) {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.f.getBroadcastItem(beginBroadcast)).onQueueChanged(list);
                } catch (RemoteException e) {
                }
            }
            this.f.finishBroadcast();
        }

        private void a(CharSequence charSequence) {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.f.getBroadcastItem(beginBroadcast)).onQueueTitleChanged(charSequence);
                } catch (RemoteException e) {
                }
            }
            this.f.finishBroadcast();
        }

        private void a(boolean z) {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.f.getBroadcastItem(beginBroadcast)).onCaptioningEnabledChanged(z);
                } catch (RemoteException e) {
                }
            }
            this.f.finishBroadcast();
        }

        private void c(int i) {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.f.getBroadcastItem(beginBroadcast)).onRepeatModeChanged(i);
                } catch (RemoteException e) {
                }
            }
            this.f.finishBroadcast();
        }

        private void d(int i) {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.f.getBroadcastItem(beginBroadcast)).onShuffleModeChanged(i);
                } catch (RemoteException e) {
                }
            }
            this.f.finishBroadcast();
        }

        private void b(Bundle bundle) {
            for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.f.getBroadcastItem(beginBroadcast)).onExtrasChanged(bundle);
                } catch (RemoteException e) {
                }
            }
            this.f.finishBroadcast();
        }
    }

    @RequiresApi(18)
    static class b extends e {
        private static boolean x = true;

        b(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
            super(context, str, componentName, pendingIntent);
        }

        public void setCallback(Callback callback, Handler handler) {
            super.setCallback(callback, handler);
            if (callback == null) {
                this.d.setPlaybackPositionUpdateListener(null);
                return;
            }
            this.d.setPlaybackPositionUpdateListener(new c(this));
        }

        void a(PlaybackStateCompat playbackStateCompat) {
            long j = 0;
            long position = playbackStateCompat.getPosition();
            float playbackSpeed = playbackStateCompat.getPlaybackSpeed();
            long lastPositionUpdateTime = playbackStateCompat.getLastPositionUpdateTime();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (playbackStateCompat.getState() != 3 || position <= 0) {
                j = position;
            } else {
                if (lastPositionUpdateTime > 0) {
                    j = elapsedRealtime - lastPositionUpdateTime;
                    if (playbackSpeed > 0.0f && playbackSpeed != 1.0f) {
                        j = (long) (((float) j) * playbackSpeed);
                    }
                }
                j += position;
            }
            this.d.setPlaybackState(b(playbackStateCompat.getState()), j, playbackSpeed);
        }

        int a(long j) {
            int a = super.a(j);
            if ((256 & j) != 0) {
                return a | 256;
            }
            return a;
        }

        void a(PendingIntent pendingIntent, ComponentName componentName) {
            if (x) {
                try {
                    this.c.registerMediaButtonEventReceiver(pendingIntent);
                } catch (NullPointerException e) {
                    Log.w("MediaSessionCompat", "Unable to register media button event receiver with PendingIntent, falling back to ComponentName.");
                    x = false;
                }
            }
            if (!x) {
                super.a(pendingIntent, componentName);
            }
        }

        void b(PendingIntent pendingIntent, ComponentName componentName) {
            if (x) {
                this.c.unregisterMediaButtonEventReceiver(pendingIntent);
            } else {
                super.b(pendingIntent, componentName);
            }
        }
    }

    @RequiresApi(19)
    static class c extends b {
        c(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
            super(context, str, componentName, pendingIntent);
        }

        public void setCallback(Callback callback, Handler handler) {
            super.setCallback(callback, handler);
            if (callback == null) {
                this.d.setMetadataUpdateListener(null);
                return;
            }
            this.d.setMetadataUpdateListener(new d(this));
        }

        int a(long j) {
            int a = super.a(j);
            if ((128 & j) != 0) {
                return a | 512;
            }
            return a;
        }

        MetadataEditor a(Bundle bundle) {
            MediaMetadataEditor a = super.a(bundle);
            if (((this.l == null ? 0 : this.l.getActions()) & 128) != 0) {
                a.addEditableKey(268435457);
            }
            if (bundle != null) {
                if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_YEAR)) {
                    a.putLong(8, bundle.getLong(MediaMetadataCompat.METADATA_KEY_YEAR));
                }
                if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_RATING)) {
                    a.putObject(101, bundle.getParcelable(MediaMetadataCompat.METADATA_KEY_RATING));
                }
                if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_USER_RATING)) {
                    a.putObject(268435457, bundle.getParcelable(MediaMetadataCompat.METADATA_KEY_USER_RATING));
                }
            }
            return a;
        }
    }

    @RequiresApi(21)
    static class d implements a {
        int a;
        boolean b;
        int c;
        int d;
        private final Object e;
        private final Token f;
        private boolean g = false;
        private final RemoteCallbackList<IMediaControllerCallback> h = new RemoteCallbackList();
        private PlaybackStateCompat i;
        private List<QueueItem> j;
        private MediaMetadataCompat k;

        class a extends Stub {
            final /* synthetic */ d a;

            a(d dVar) {
                this.a = dVar;
            }

            public void sendCommand(String str, Bundle bundle, ResultReceiverWrapper resultReceiverWrapper) {
                throw new AssertionError();
            }

            public boolean sendMediaButton(KeyEvent keyEvent) {
                throw new AssertionError();
            }

            public void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                if (!this.a.g) {
                    this.a.h.register(iMediaControllerCallback);
                }
            }

            public void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                this.a.h.unregister(iMediaControllerCallback);
            }

            public String getPackageName() {
                throw new AssertionError();
            }

            public String getTag() {
                throw new AssertionError();
            }

            public PendingIntent getLaunchPendingIntent() {
                throw new AssertionError();
            }

            public long getFlags() {
                throw new AssertionError();
            }

            public ParcelableVolumeInfo getVolumeAttributes() {
                throw new AssertionError();
            }

            public void adjustVolume(int i, int i2, String str) {
                throw new AssertionError();
            }

            public void setVolumeTo(int i, int i2, String str) {
                throw new AssertionError();
            }

            public void prepare() throws RemoteException {
                throw new AssertionError();
            }

            public void prepareFromMediaId(String str, Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            public void prepareFromSearch(String str, Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            public void prepareFromUri(Uri uri, Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            public void play() throws RemoteException {
                throw new AssertionError();
            }

            public void playFromMediaId(String str, Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            public void playFromSearch(String str, Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            public void playFromUri(Uri uri, Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            public void skipToQueueItem(long j) {
                throw new AssertionError();
            }

            public void pause() throws RemoteException {
                throw new AssertionError();
            }

            public void stop() throws RemoteException {
                throw new AssertionError();
            }

            public void next() throws RemoteException {
                throw new AssertionError();
            }

            public void previous() throws RemoteException {
                throw new AssertionError();
            }

            public void fastForward() throws RemoteException {
                throw new AssertionError();
            }

            public void rewind() throws RemoteException {
                throw new AssertionError();
            }

            public void seekTo(long j) throws RemoteException {
                throw new AssertionError();
            }

            public void rate(RatingCompat ratingCompat) throws RemoteException {
                throw new AssertionError();
            }

            public void rateWithExtras(RatingCompat ratingCompat, Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            public void setCaptioningEnabled(boolean z) throws RemoteException {
                throw new AssertionError();
            }

            public void setRepeatMode(int i) throws RemoteException {
                throw new AssertionError();
            }

            public void setShuffleModeEnabledRemoved(boolean z) throws RemoteException {
            }

            public void setShuffleMode(int i) throws RemoteException {
                throw new AssertionError();
            }

            public void sendCustomAction(String str, Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            public MediaMetadataCompat getMetadata() {
                throw new AssertionError();
            }

            public PlaybackStateCompat getPlaybackState() {
                return MediaSessionCompat.b(this.a.i, this.a.k);
            }

            public List<QueueItem> getQueue() {
                return null;
            }

            public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
                throw new AssertionError();
            }

            public void addQueueItemAt(MediaDescriptionCompat mediaDescriptionCompat, int i) {
                throw new AssertionError();
            }

            public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
                throw new AssertionError();
            }

            public void removeQueueItemAt(int i) {
                throw new AssertionError();
            }

            public CharSequence getQueueTitle() {
                throw new AssertionError();
            }

            public Bundle getExtras() {
                throw new AssertionError();
            }

            public int getRatingType() {
                return this.a.a;
            }

            public boolean isCaptioningEnabled() {
                return this.a.b;
            }

            public int getRepeatMode() {
                return this.a.c;
            }

            public boolean isShuffleModeEnabledRemoved() {
                return false;
            }

            public int getShuffleMode() {
                return this.a.d;
            }

            public boolean isTransportControlEnabled() {
                throw new AssertionError();
            }
        }

        public d(Context context, String str) {
            this.e = i.createSession(context, str);
            this.f = new Token(i.getSessionToken(this.e), new a(this));
        }

        public d(Object obj) {
            this.e = i.verifySession(obj);
            this.f = new Token(i.getSessionToken(this.e), new a(this));
        }

        public void setCallback(Callback callback, Handler handler) {
            i.setCallback(this.e, callback == null ? null : callback.b, handler);
            if (callback != null) {
                callback.a(this, handler);
            }
        }

        public void setFlags(int i) {
            i.setFlags(this.e, i);
        }

        public void setPlaybackToLocal(int i) {
            i.setPlaybackToLocal(this.e, i);
        }

        public void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat) {
            i.setPlaybackToRemote(this.e, volumeProviderCompat.getVolumeProvider());
        }

        public void setActive(boolean z) {
            i.setActive(this.e, z);
        }

        public boolean isActive() {
            return i.isActive(this.e);
        }

        public void sendSessionEvent(String str, Bundle bundle) {
            if (VERSION.SDK_INT < 23) {
                for (int beginBroadcast = this.h.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                    try {
                        ((IMediaControllerCallback) this.h.getBroadcastItem(beginBroadcast)).onEvent(str, bundle);
                    } catch (RemoteException e) {
                    }
                }
                this.h.finishBroadcast();
            }
            i.sendSessionEvent(this.e, str, bundle);
        }

        public void release() {
            this.g = true;
            i.release(this.e);
        }

        public Token getSessionToken() {
            return this.f;
        }

        public void setPlaybackState(PlaybackStateCompat playbackStateCompat) {
            Object obj;
            this.i = playbackStateCompat;
            for (int beginBroadcast = this.h.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    ((IMediaControllerCallback) this.h.getBroadcastItem(beginBroadcast)).onPlaybackStateChanged(playbackStateCompat);
                } catch (RemoteException e) {
                }
            }
            this.h.finishBroadcast();
            Object obj2 = this.e;
            if (playbackStateCompat == null) {
                obj = null;
            } else {
                obj = playbackStateCompat.getPlaybackState();
            }
            i.setPlaybackState(obj2, obj);
        }

        public PlaybackStateCompat getPlaybackState() {
            return this.i;
        }

        public void setMetadata(MediaMetadataCompat mediaMetadataCompat) {
            Object obj;
            this.k = mediaMetadataCompat;
            Object obj2 = this.e;
            if (mediaMetadataCompat == null) {
                obj = null;
            } else {
                obj = mediaMetadataCompat.getMediaMetadata();
            }
            i.setMetadata(obj2, obj);
        }

        public void setSessionActivity(PendingIntent pendingIntent) {
            i.setSessionActivity(this.e, pendingIntent);
        }

        public void setMediaButtonReceiver(PendingIntent pendingIntent) {
            i.setMediaButtonReceiver(this.e, pendingIntent);
        }

        public void setQueue(List<QueueItem> list) {
            this.j = list;
            List list2 = null;
            if (list != null) {
                List arrayList = new ArrayList();
                for (QueueItem queueItem : list) {
                    arrayList.add(queueItem.getQueueItem());
                }
                list2 = arrayList;
            }
            i.setQueue(this.e, list2);
        }

        public void setQueueTitle(CharSequence charSequence) {
            i.setQueueTitle(this.e, charSequence);
        }

        public void setRatingType(int i) {
            if (VERSION.SDK_INT < 22) {
                this.a = i;
            } else {
                j.setRatingType(this.e, i);
            }
        }

        public void setCaptioningEnabled(boolean z) {
            if (this.b != z) {
                this.b = z;
                for (int beginBroadcast = this.h.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                    try {
                        ((IMediaControllerCallback) this.h.getBroadcastItem(beginBroadcast)).onCaptioningEnabledChanged(z);
                    } catch (RemoteException e) {
                    }
                }
                this.h.finishBroadcast();
            }
        }

        public void setRepeatMode(int i) {
            if (this.c != i) {
                this.c = i;
                for (int beginBroadcast = this.h.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                    try {
                        ((IMediaControllerCallback) this.h.getBroadcastItem(beginBroadcast)).onRepeatModeChanged(i);
                    } catch (RemoteException e) {
                    }
                }
                this.h.finishBroadcast();
            }
        }

        public void setShuffleMode(int i) {
            if (this.d != i) {
                this.d = i;
                for (int beginBroadcast = this.h.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                    try {
                        ((IMediaControllerCallback) this.h.getBroadcastItem(beginBroadcast)).onShuffleModeChanged(i);
                    } catch (RemoteException e) {
                    }
                }
                this.h.finishBroadcast();
            }
        }

        public void setExtras(Bundle bundle) {
            i.setExtras(this.e, bundle);
        }

        public Object getMediaSession() {
            return this.e;
        }

        public Object getRemoteControlClient() {
            return null;
        }

        public String getCallingPackage() {
            if (VERSION.SDK_INT < 24) {
                return null;
            }
            return MediaSessionCompatApi24.getCallingPackage(this.e);
        }
    }

    public MediaSessionCompat(Context context, String str) {
        this(context, str, null, null);
    }

    public MediaSessionCompat(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
        this.d = new ArrayList();
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("tag must not be null or empty");
        } else {
            if (componentName == null) {
                componentName = MediaButtonReceiver.a(context);
                if (componentName == null) {
                    Log.w("MediaSessionCompat", "Couldn't find a unique registered media button receiver in the given context.");
                }
            }
            if (componentName != null && pendingIntent == null) {
                Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                intent.setComponent(componentName);
                pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
            }
            if (VERSION.SDK_INT >= 21) {
                this.b = new d(context, str);
                setCallback(new a(this));
                this.b.setMediaButtonReceiver(pendingIntent);
            } else if (VERSION.SDK_INT >= 19) {
                this.b = new c(context, str, componentName, pendingIntent);
            } else if (VERSION.SDK_INT >= 18) {
                this.b = new b(context, str, componentName, pendingIntent);
            } else {
                this.b = new e(context, str, componentName, pendingIntent);
            }
            this.c = new MediaControllerCompat(context, this);
            if (a == 0) {
                a = (int) TypedValue.applyDimension(1, 320.0f, context.getResources().getDisplayMetrics());
            }
        }
    }

    private MediaSessionCompat(Context context, a aVar) {
        this.d = new ArrayList();
        this.b = aVar;
        if (VERSION.SDK_INT >= 21 && !i.hasCallback(aVar.getMediaSession())) {
            setCallback(new b(this));
        }
        this.c = new MediaControllerCompat(context, this);
    }

    public void setCallback(Callback callback) {
        setCallback(callback, null);
    }

    public void setCallback(Callback callback, Handler handler) {
        a aVar = this.b;
        if (handler == null) {
            handler = new Handler();
        }
        aVar.setCallback(callback, handler);
    }

    public void setSessionActivity(PendingIntent pendingIntent) {
        this.b.setSessionActivity(pendingIntent);
    }

    public void setMediaButtonReceiver(PendingIntent pendingIntent) {
        this.b.setMediaButtonReceiver(pendingIntent);
    }

    public void setFlags(int i) {
        this.b.setFlags(i);
    }

    public void setPlaybackToLocal(int i) {
        this.b.setPlaybackToLocal(i);
    }

    public void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat) {
        if (volumeProviderCompat == null) {
            throw new IllegalArgumentException("volumeProvider may not be null!");
        }
        this.b.setPlaybackToRemote(volumeProviderCompat);
    }

    public void setActive(boolean z) {
        this.b.setActive(z);
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ((OnActiveChangeListener) it.next()).onActiveChanged();
        }
    }

    public boolean isActive() {
        return this.b.isActive();
    }

    public void sendSessionEvent(String str, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("event cannot be null or empty");
        }
        this.b.sendSessionEvent(str, bundle);
    }

    public void release() {
        this.b.release();
    }

    public Token getSessionToken() {
        return this.b.getSessionToken();
    }

    public MediaControllerCompat getController() {
        return this.c;
    }

    public void setPlaybackState(PlaybackStateCompat playbackStateCompat) {
        this.b.setPlaybackState(playbackStateCompat);
    }

    public void setMetadata(MediaMetadataCompat mediaMetadataCompat) {
        this.b.setMetadata(mediaMetadataCompat);
    }

    public void setQueue(List<QueueItem> list) {
        this.b.setQueue(list);
    }

    public void setQueueTitle(CharSequence charSequence) {
        this.b.setQueueTitle(charSequence);
    }

    public void setRatingType(int i) {
        this.b.setRatingType(i);
    }

    public void setCaptioningEnabled(boolean z) {
        this.b.setCaptioningEnabled(z);
    }

    public void setRepeatMode(int i) {
        this.b.setRepeatMode(i);
    }

    public void setShuffleMode(int i) {
        this.b.setShuffleMode(i);
    }

    public void setExtras(Bundle bundle) {
        this.b.setExtras(bundle);
    }

    public Object getMediaSession() {
        return this.b.getMediaSession();
    }

    public Object getRemoteControlClient() {
        return this.b.getRemoteControlClient();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public String getCallingPackage() {
        return this.b.getCallingPackage();
    }

    public void addOnActiveChangeListener(OnActiveChangeListener onActiveChangeListener) {
        if (onActiveChangeListener == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.d.add(onActiveChangeListener);
    }

    public void removeOnActiveChangeListener(OnActiveChangeListener onActiveChangeListener) {
        if (onActiveChangeListener == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.d.remove(onActiveChangeListener);
    }

    public static MediaSessionCompat fromMediaSession(Context context, Object obj) {
        if (context == null || obj == null || VERSION.SDK_INT < 21) {
            return null;
        }
        return new MediaSessionCompat(context, new d(obj));
    }

    private static PlaybackStateCompat b(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat) {
        long j = -1;
        if (playbackStateCompat == null || playbackStateCompat.getPosition() == -1) {
            return playbackStateCompat;
        }
        if (playbackStateCompat.getState() != 3 && playbackStateCompat.getState() != 4 && playbackStateCompat.getState() != 5) {
            return playbackStateCompat;
        }
        long lastPositionUpdateTime = playbackStateCompat.getLastPositionUpdateTime();
        if (lastPositionUpdateTime <= 0) {
            return playbackStateCompat;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        lastPositionUpdateTime = ((long) (playbackStateCompat.getPlaybackSpeed() * ((float) (elapsedRealtime - lastPositionUpdateTime)))) + playbackStateCompat.getPosition();
        if (mediaMetadataCompat != null && mediaMetadataCompat.containsKey(MediaMetadataCompat.METADATA_KEY_DURATION)) {
            j = mediaMetadataCompat.getLong(MediaMetadataCompat.METADATA_KEY_DURATION);
        }
        if (j < 0 || lastPositionUpdateTime <= j) {
            if (lastPositionUpdateTime < 0) {
                j = 0;
            } else {
                j = lastPositionUpdateTime;
            }
        }
        return new PlaybackStateCompat.Builder(playbackStateCompat).setState(playbackStateCompat.getState(), j, playbackStateCompat.getPlaybackSpeed(), elapsedRealtime).build();
    }
}
