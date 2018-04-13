package android.support.v4.media.session;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.SupportActivity;
import android.support.v4.app.SupportActivity.ExtraData;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.IMediaControllerCallback.Stub;
import android.support.v4.media.session.MediaSessionCompat.QueueItem;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.media.session.PlaybackStateCompat.CustomAction;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public final class MediaControllerCompat {
    private final b a;
    private final Token b;
    private final HashSet<Callback> c = new HashSet();

    public static abstract class Callback implements DeathRecipient {
        a a;
        boolean b;
        private final Object c;

        private class a extends Handler {
            boolean a = false;
            final /* synthetic */ Callback b;

            a(Callback callback, Looper looper) {
                this.b = callback;
                super(looper);
            }

            public void handleMessage(Message message) {
                if (this.a) {
                    switch (message.what) {
                        case 1:
                            this.b.onSessionEvent((String) message.obj, message.getData());
                            return;
                        case 2:
                            this.b.onPlaybackStateChanged((PlaybackStateCompat) message.obj);
                            return;
                        case 3:
                            this.b.onMetadataChanged((MediaMetadataCompat) message.obj);
                            return;
                        case 4:
                            this.b.onAudioInfoChanged((PlaybackInfo) message.obj);
                            return;
                        case 5:
                            this.b.onQueueChanged((List) message.obj);
                            return;
                        case 6:
                            this.b.onQueueTitleChanged((CharSequence) message.obj);
                            return;
                        case 7:
                            this.b.onExtrasChanged((Bundle) message.obj);
                            return;
                        case 8:
                            this.b.onSessionDestroyed();
                            return;
                        case 9:
                            this.b.onRepeatModeChanged(((Integer) message.obj).intValue());
                            return;
                        case 11:
                            this.b.onCaptioningEnabledChanged(((Boolean) message.obj).booleanValue());
                            return;
                        case 12:
                            this.b.onShuffleModeChanged(((Integer) message.obj).intValue());
                            return;
                        case 13:
                            this.b.onSessionReady();
                            return;
                        default:
                            return;
                    }
                }
            }
        }

        private static class b implements android.support.v4.media.session.MediaControllerCompatApi21.Callback {
            private final WeakReference<Callback> a;

            b(Callback callback) {
                this.a = new WeakReference(callback);
            }

            public void onSessionDestroyed() {
                Callback callback = (Callback) this.a.get();
                if (callback != null) {
                    callback.onSessionDestroyed();
                }
            }

            public void onSessionEvent(String str, Bundle bundle) {
                Callback callback = (Callback) this.a.get();
                if (callback == null) {
                    return;
                }
                if (!callback.b || VERSION.SDK_INT >= 23) {
                    callback.onSessionEvent(str, bundle);
                }
            }

            public void onPlaybackStateChanged(Object obj) {
                Callback callback = (Callback) this.a.get();
                if (callback != null && !callback.b) {
                    callback.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(obj));
                }
            }

            public void onMetadataChanged(Object obj) {
                Callback callback = (Callback) this.a.get();
                if (callback != null) {
                    callback.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(obj));
                }
            }

            public void onQueueChanged(List<?> list) {
                Callback callback = (Callback) this.a.get();
                if (callback != null) {
                    callback.onQueueChanged(QueueItem.fromQueueItemList(list));
                }
            }

            public void onQueueTitleChanged(CharSequence charSequence) {
                Callback callback = (Callback) this.a.get();
                if (callback != null) {
                    callback.onQueueTitleChanged(charSequence);
                }
            }

            public void onExtrasChanged(Bundle bundle) {
                Callback callback = (Callback) this.a.get();
                if (callback != null) {
                    callback.onExtrasChanged(bundle);
                }
            }

            public void onAudioInfoChanged(int i, int i2, int i3, int i4, int i5) {
                Callback callback = (Callback) this.a.get();
                if (callback != null) {
                    callback.onAudioInfoChanged(new PlaybackInfo(i, i2, i3, i4, i5));
                }
            }
        }

        private static class c extends Stub {
            private final WeakReference<Callback> a;

            c(Callback callback) {
                this.a = new WeakReference(callback);
            }

            public void onEvent(String str, Bundle bundle) throws RemoteException {
                Callback callback = (Callback) this.a.get();
                if (callback != null) {
                    callback.a(1, str, bundle);
                }
            }

            public void onSessionDestroyed() throws RemoteException {
                Callback callback = (Callback) this.a.get();
                if (callback != null) {
                    callback.a(8, null, null);
                }
            }

            public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) throws RemoteException {
                Callback callback = (Callback) this.a.get();
                if (callback != null) {
                    callback.a(2, playbackStateCompat, null);
                }
            }

            public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
                Callback callback = (Callback) this.a.get();
                if (callback != null) {
                    callback.a(3, mediaMetadataCompat, null);
                }
            }

            public void onQueueChanged(List<QueueItem> list) throws RemoteException {
                Callback callback = (Callback) this.a.get();
                if (callback != null) {
                    callback.a(5, list, null);
                }
            }

            public void onQueueTitleChanged(CharSequence charSequence) throws RemoteException {
                Callback callback = (Callback) this.a.get();
                if (callback != null) {
                    callback.a(6, charSequence, null);
                }
            }

            public void onCaptioningEnabledChanged(boolean z) throws RemoteException {
                Callback callback = (Callback) this.a.get();
                if (callback != null) {
                    callback.a(11, Boolean.valueOf(z), null);
                }
            }

            public void onRepeatModeChanged(int i) throws RemoteException {
                Callback callback = (Callback) this.a.get();
                if (callback != null) {
                    callback.a(9, Integer.valueOf(i), null);
                }
            }

            public void onShuffleModeChangedRemoved(boolean z) throws RemoteException {
            }

            public void onShuffleModeChanged(int i) throws RemoteException {
                Callback callback = (Callback) this.a.get();
                if (callback != null) {
                    callback.a(12, Integer.valueOf(i), null);
                }
            }

            public void onExtrasChanged(Bundle bundle) throws RemoteException {
                Callback callback = (Callback) this.a.get();
                if (callback != null) {
                    callback.a(7, bundle, null);
                }
            }

            public void onVolumeInfoChanged(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
                Callback callback = (Callback) this.a.get();
                if (callback != null) {
                    Object playbackInfo;
                    if (parcelableVolumeInfo != null) {
                        playbackInfo = new PlaybackInfo(parcelableVolumeInfo.volumeType, parcelableVolumeInfo.audioStream, parcelableVolumeInfo.controlType, parcelableVolumeInfo.maxVolume, parcelableVolumeInfo.currentVolume);
                    } else {
                        playbackInfo = null;
                    }
                    callback.a(4, playbackInfo, null);
                }
            }

            public void onSessionReady() throws RemoteException {
                Callback callback = (Callback) this.a.get();
                if (callback != null) {
                    callback.a(13, null, null);
                }
            }
        }

        public Callback() {
            if (VERSION.SDK_INT >= 21) {
                this.c = MediaControllerCompatApi21.createCallback(new b(this));
            } else {
                this.c = new c(this);
            }
        }

        public void onSessionReady() {
        }

        public void onSessionDestroyed() {
        }

        public void onSessionEvent(String str, Bundle bundle) {
        }

        public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
        }

        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
        }

        public void onQueueChanged(List<QueueItem> list) {
        }

        public void onQueueTitleChanged(CharSequence charSequence) {
        }

        public void onExtrasChanged(Bundle bundle) {
        }

        public void onAudioInfoChanged(PlaybackInfo playbackInfo) {
        }

        public void onCaptioningEnabledChanged(boolean z) {
        }

        public void onRepeatModeChanged(int i) {
        }

        public void onShuffleModeChanged(int i) {
        }

        public void binderDied() {
            onSessionDestroyed();
        }

        void a(Handler handler) {
            if (handler != null) {
                this.a = new a(this, handler.getLooper());
                this.a.a = true;
            } else if (this.a != null) {
                this.a.a = false;
                this.a.removeCallbacksAndMessages(null);
                this.a = null;
            }
        }

        void a(int i, Object obj, Bundle bundle) {
            if (this.a != null) {
                Message obtainMessage = this.a.obtainMessage(i, obj);
                obtainMessage.setData(bundle);
                obtainMessage.sendToTarget();
            }
        }
    }

    interface b {
        void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat);

        void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i);

        void adjustVolume(int i, int i2);

        boolean dispatchMediaButtonEvent(KeyEvent keyEvent);

        Bundle getExtras();

        long getFlags();

        Object getMediaController();

        MediaMetadataCompat getMetadata();

        String getPackageName();

        PlaybackInfo getPlaybackInfo();

        PlaybackStateCompat getPlaybackState();

        List<QueueItem> getQueue();

        CharSequence getQueueTitle();

        int getRatingType();

        int getRepeatMode();

        PendingIntent getSessionActivity();

        int getShuffleMode();

        TransportControls getTransportControls();

        boolean isCaptioningEnabled();

        boolean isSessionReady();

        void registerCallback(Callback callback, Handler handler);

        void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat);

        void sendCommand(String str, Bundle bundle, ResultReceiver resultReceiver);

        void setVolumeTo(int i, int i2);

        void unregisterCallback(Callback callback);
    }

    @RequiresApi(21)
    static class MediaControllerImplApi21 implements b {
        protected final Object a;
        private final List<Callback> b = new ArrayList();
        private IMediaSession c;
        private HashMap<Callback, a> d = new HashMap();

        private static class ExtraBinderRequestResultReceiver extends ResultReceiver {
            private WeakReference<MediaControllerImplApi21> a;

            public ExtraBinderRequestResultReceiver(MediaControllerImplApi21 mediaControllerImplApi21, Handler handler) {
                super(handler);
                this.a = new WeakReference(mediaControllerImplApi21);
            }

            protected void onReceiveResult(int i, Bundle bundle) {
                MediaControllerImplApi21 mediaControllerImplApi21 = (MediaControllerImplApi21) this.a.get();
                if (mediaControllerImplApi21 != null && bundle != null) {
                    mediaControllerImplApi21.c = IMediaSession.Stub.asInterface(BundleCompat.getBinder(bundle, "android.support.v4.media.session.EXTRA_BINDER"));
                    mediaControllerImplApi21.b();
                }
            }
        }

        private static class a extends c {
            a(Callback callback) {
                super(callback);
            }

            public void onSessionDestroyed() throws RemoteException {
                throw new AssertionError();
            }

            public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
                throw new AssertionError();
            }

            public void onQueueChanged(List<QueueItem> list) throws RemoteException {
                throw new AssertionError();
            }

            public void onQueueTitleChanged(CharSequence charSequence) throws RemoteException {
                throw new AssertionError();
            }

            public void onExtrasChanged(Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            public void onVolumeInfoChanged(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
                throw new AssertionError();
            }
        }

        public MediaControllerImplApi21(Context context, MediaSessionCompat mediaSessionCompat) {
            this.a = MediaControllerCompatApi21.fromToken(context, mediaSessionCompat.getSessionToken().getToken());
            this.c = mediaSessionCompat.getSessionToken().getExtraBinder();
            if (this.c == null) {
                a();
            }
        }

        public MediaControllerImplApi21(Context context, Token token) throws RemoteException {
            this.a = MediaControllerCompatApi21.fromToken(context, token.getToken());
            if (this.a == null) {
                throw new RemoteException();
            }
            this.c = token.getExtraBinder();
            if (this.c == null) {
                a();
            }
        }

        public final void registerCallback(Callback callback, Handler handler) {
            MediaControllerCompatApi21.registerCallback(this.a, callback.c, handler);
            if (this.c != null) {
                IMediaControllerCallback aVar = new a(callback);
                this.d.put(callback, aVar);
                callback.b = true;
                try {
                    this.c.registerCallbackListener(aVar);
                    return;
                } catch (Throwable e) {
                    Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                    return;
                }
            }
            synchronized (this.b) {
                this.b.add(callback);
            }
        }

        public final void unregisterCallback(Callback callback) {
            MediaControllerCompatApi21.unregisterCallback(this.a, callback.c);
            if (this.c != null) {
                try {
                    a aVar = (a) this.d.remove(callback);
                    if (aVar != null) {
                        this.c.unregisterCallbackListener(aVar);
                        return;
                    }
                    return;
                } catch (Throwable e) {
                    Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", e);
                    return;
                }
            }
            synchronized (this.b) {
                this.b.remove(callback);
            }
        }

        public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
            return MediaControllerCompatApi21.dispatchMediaButtonEvent(this.a, keyEvent);
        }

        public TransportControls getTransportControls() {
            Object transportControls = MediaControllerCompatApi21.getTransportControls(this.a);
            return transportControls != null ? new f(transportControls) : null;
        }

        public PlaybackStateCompat getPlaybackState() {
            if (this.c != null) {
                try {
                    return this.c.getPlaybackState();
                } catch (Throwable e) {
                    Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", e);
                }
            }
            Object playbackState = MediaControllerCompatApi21.getPlaybackState(this.a);
            return playbackState != null ? PlaybackStateCompat.fromPlaybackState(playbackState) : null;
        }

        public MediaMetadataCompat getMetadata() {
            Object metadata = MediaControllerCompatApi21.getMetadata(this.a);
            return metadata != null ? MediaMetadataCompat.fromMediaMetadata(metadata) : null;
        }

        public List<QueueItem> getQueue() {
            List queue = MediaControllerCompatApi21.getQueue(this.a);
            return queue != null ? QueueItem.fromQueueItemList(queue) : null;
        }

        public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
            if ((getFlags() & 4) == 0) {
                throw new UnsupportedOperationException("This session doesn't support queue management operations");
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", mediaDescriptionCompat);
            sendCommand("android.support.v4.media.session.command.ADD_QUEUE_ITEM", bundle, null);
        }

        public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
            if ((getFlags() & 4) == 0) {
                throw new UnsupportedOperationException("This session doesn't support queue management operations");
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", mediaDescriptionCompat);
            bundle.putInt("android.support.v4.media.session.command.ARGUMENT_INDEX", i);
            sendCommand("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT", bundle, null);
        }

        public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
            if ((getFlags() & 4) == 0) {
                throw new UnsupportedOperationException("This session doesn't support queue management operations");
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", mediaDescriptionCompat);
            sendCommand("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM", bundle, null);
        }

        public CharSequence getQueueTitle() {
            return MediaControllerCompatApi21.getQueueTitle(this.a);
        }

        public Bundle getExtras() {
            return MediaControllerCompatApi21.getExtras(this.a);
        }

        public int getRatingType() {
            if (VERSION.SDK_INT < 22 && this.c != null) {
                try {
                    return this.c.getRatingType();
                } catch (Throwable e) {
                    Log.e("MediaControllerCompat", "Dead object in getRatingType.", e);
                }
            }
            return MediaControllerCompatApi21.getRatingType(this.a);
        }

        public boolean isCaptioningEnabled() {
            if (this.c != null) {
                try {
                    return this.c.isCaptioningEnabled();
                } catch (Throwable e) {
                    Log.e("MediaControllerCompat", "Dead object in isCaptioningEnabled.", e);
                }
            }
            return false;
        }

        public int getRepeatMode() {
            if (this.c != null) {
                try {
                    return this.c.getRepeatMode();
                } catch (Throwable e) {
                    Log.e("MediaControllerCompat", "Dead object in getRepeatMode.", e);
                }
            }
            return -1;
        }

        public int getShuffleMode() {
            if (this.c != null) {
                try {
                    return this.c.getShuffleMode();
                } catch (Throwable e) {
                    Log.e("MediaControllerCompat", "Dead object in getShuffleMode.", e);
                }
            }
            return -1;
        }

        public long getFlags() {
            return MediaControllerCompatApi21.getFlags(this.a);
        }

        public PlaybackInfo getPlaybackInfo() {
            Object playbackInfo = MediaControllerCompatApi21.getPlaybackInfo(this.a);
            return playbackInfo != null ? new PlaybackInfo(android.support.v4.media.session.MediaControllerCompatApi21.PlaybackInfo.getPlaybackType(playbackInfo), android.support.v4.media.session.MediaControllerCompatApi21.PlaybackInfo.getLegacyAudioStream(playbackInfo), android.support.v4.media.session.MediaControllerCompatApi21.PlaybackInfo.getVolumeControl(playbackInfo), android.support.v4.media.session.MediaControllerCompatApi21.PlaybackInfo.getMaxVolume(playbackInfo), android.support.v4.media.session.MediaControllerCompatApi21.PlaybackInfo.getCurrentVolume(playbackInfo)) : null;
        }

        public PendingIntent getSessionActivity() {
            return MediaControllerCompatApi21.getSessionActivity(this.a);
        }

        public void setVolumeTo(int i, int i2) {
            MediaControllerCompatApi21.setVolumeTo(this.a, i, i2);
        }

        public void adjustVolume(int i, int i2) {
            MediaControllerCompatApi21.adjustVolume(this.a, i, i2);
        }

        public void sendCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
            MediaControllerCompatApi21.sendCommand(this.a, str, bundle, resultReceiver);
        }

        public boolean isSessionReady() {
            return this.c != null;
        }

        public String getPackageName() {
            return MediaControllerCompatApi21.getPackageName(this.a);
        }

        public Object getMediaController() {
            return this.a;
        }

        private void a() {
            sendCommand("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new ExtraBinderRequestResultReceiver(this, new Handler()));
        }

        private void b() {
            if (this.c != null) {
                synchronized (this.b) {
                    for (Callback callback : this.b) {
                        IMediaControllerCallback aVar = new a(callback);
                        this.d.put(callback, aVar);
                        callback.b = true;
                        try {
                            this.c.registerCallbackListener(aVar);
                            callback.onSessionReady();
                        } catch (Throwable e) {
                            Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                        }
                    }
                    this.b.clear();
                }
            }
        }
    }

    public static final class PlaybackInfo {
        public static final int PLAYBACK_TYPE_LOCAL = 1;
        public static final int PLAYBACK_TYPE_REMOTE = 2;
        private final int a;
        private final int b;
        private final int c;
        private final int d;
        private final int e;

        PlaybackInfo(int i, int i2, int i3, int i4, int i5) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = i5;
        }

        public int getPlaybackType() {
            return this.a;
        }

        public int getAudioStream() {
            return this.b;
        }

        public int getVolumeControl() {
            return this.c;
        }

        public int getMaxVolume() {
            return this.d;
        }

        public int getCurrentVolume() {
            return this.e;
        }
    }

    public static abstract class TransportControls {
        public static final String EXTRA_LEGACY_STREAM_TYPE = "android.media.session.extra.LEGACY_STREAM_TYPE";

        public abstract void fastForward();

        public abstract void pause();

        public abstract void play();

        public abstract void playFromMediaId(String str, Bundle bundle);

        public abstract void playFromSearch(String str, Bundle bundle);

        public abstract void playFromUri(Uri uri, Bundle bundle);

        public abstract void prepare();

        public abstract void prepareFromMediaId(String str, Bundle bundle);

        public abstract void prepareFromSearch(String str, Bundle bundle);

        public abstract void prepareFromUri(Uri uri, Bundle bundle);

        public abstract void rewind();

        public abstract void seekTo(long j);

        public abstract void sendCustomAction(CustomAction customAction, Bundle bundle);

        public abstract void sendCustomAction(String str, Bundle bundle);

        public abstract void setCaptioningEnabled(boolean z);

        public abstract void setRating(RatingCompat ratingCompat);

        public abstract void setRating(RatingCompat ratingCompat, Bundle bundle);

        public abstract void setRepeatMode(int i);

        public abstract void setShuffleMode(int i);

        public abstract void skipToNext();

        public abstract void skipToPrevious();

        public abstract void skipToQueueItem(long j);

        public abstract void stop();

        TransportControls() {
        }
    }

    private static class a extends ExtraData {
        private final MediaControllerCompat a;

        a(MediaControllerCompat mediaControllerCompat) {
            this.a = mediaControllerCompat;
        }

        MediaControllerCompat a() {
            return this.a;
        }
    }

    @RequiresApi(23)
    static class c extends MediaControllerImplApi21 {
        public c(Context context, MediaSessionCompat mediaSessionCompat) {
            super(context, mediaSessionCompat);
        }

        public c(Context context, Token token) throws RemoteException {
            super(context, token);
        }

        public TransportControls getTransportControls() {
            Object transportControls = MediaControllerCompatApi21.getTransportControls(this.a);
            return transportControls != null ? new g(transportControls) : null;
        }
    }

    @RequiresApi(24)
    static class d extends c {
        public d(Context context, MediaSessionCompat mediaSessionCompat) {
            super(context, mediaSessionCompat);
        }

        public d(Context context, Token token) throws RemoteException {
            super(context, token);
        }

        public TransportControls getTransportControls() {
            Object transportControls = MediaControllerCompatApi21.getTransportControls(this.a);
            return transportControls != null ? new h(transportControls) : null;
        }
    }

    static class e implements b {
        private IMediaSession a;
        private TransportControls b;

        public e(Token token) {
            this.a = IMediaSession.Stub.asInterface((IBinder) token.getToken());
        }

        public void registerCallback(Callback callback, Handler handler) {
            if (callback == null) {
                throw new IllegalArgumentException("callback may not be null.");
            }
            try {
                this.a.asBinder().linkToDeath(callback, 0);
                this.a.registerCallbackListener((IMediaControllerCallback) callback.c);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                callback.onSessionDestroyed();
            }
        }

        public void unregisterCallback(Callback callback) {
            if (callback == null) {
                throw new IllegalArgumentException("callback may not be null.");
            }
            try {
                this.a.unregisterCallbackListener((IMediaControllerCallback) callback.c);
                this.a.asBinder().unlinkToDeath(callback, 0);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", e);
            }
        }

        public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
            if (keyEvent == null) {
                throw new IllegalArgumentException("event may not be null.");
            }
            try {
                this.a.sendMediaButton(keyEvent);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in dispatchMediaButtonEvent.", e);
            }
            return false;
        }

        public TransportControls getTransportControls() {
            if (this.b == null) {
                this.b = new i(this.a);
            }
            return this.b;
        }

        public PlaybackStateCompat getPlaybackState() {
            try {
                return this.a.getPlaybackState();
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in getPlaybackState.", e);
                return null;
            }
        }

        public MediaMetadataCompat getMetadata() {
            try {
                return this.a.getMetadata();
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in getMetadata.", e);
                return null;
            }
        }

        public List<QueueItem> getQueue() {
            try {
                return this.a.getQueue();
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in getQueue.", e);
                return null;
            }
        }

        public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
            try {
                if ((this.a.getFlags() & 4) == 0) {
                    throw new UnsupportedOperationException("This session doesn't support queue management operations");
                }
                this.a.addQueueItem(mediaDescriptionCompat);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in addQueueItem.", e);
            }
        }

        public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
            try {
                if ((this.a.getFlags() & 4) == 0) {
                    throw new UnsupportedOperationException("This session doesn't support queue management operations");
                }
                this.a.addQueueItemAt(mediaDescriptionCompat, i);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in addQueueItemAt.", e);
            }
        }

        public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
            try {
                if ((this.a.getFlags() & 4) == 0) {
                    throw new UnsupportedOperationException("This session doesn't support queue management operations");
                }
                this.a.removeQueueItem(mediaDescriptionCompat);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in removeQueueItem.", e);
            }
        }

        public CharSequence getQueueTitle() {
            try {
                return this.a.getQueueTitle();
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in getQueueTitle.", e);
                return null;
            }
        }

        public Bundle getExtras() {
            try {
                return this.a.getExtras();
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in getExtras.", e);
                return null;
            }
        }

        public int getRatingType() {
            try {
                return this.a.getRatingType();
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in getRatingType.", e);
                return 0;
            }
        }

        public boolean isCaptioningEnabled() {
            try {
                return this.a.isCaptioningEnabled();
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in isCaptioningEnabled.", e);
                return false;
            }
        }

        public int getRepeatMode() {
            try {
                return this.a.getRepeatMode();
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in getRepeatMode.", e);
                return -1;
            }
        }

        public int getShuffleMode() {
            try {
                return this.a.getShuffleMode();
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in getShuffleMode.", e);
                return -1;
            }
        }

        public long getFlags() {
            try {
                return this.a.getFlags();
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in getFlags.", e);
                return 0;
            }
        }

        public PlaybackInfo getPlaybackInfo() {
            try {
                ParcelableVolumeInfo volumeAttributes = this.a.getVolumeAttributes();
                return new PlaybackInfo(volumeAttributes.volumeType, volumeAttributes.audioStream, volumeAttributes.controlType, volumeAttributes.maxVolume, volumeAttributes.currentVolume);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in getPlaybackInfo.", e);
                return null;
            }
        }

        public PendingIntent getSessionActivity() {
            try {
                return this.a.getLaunchPendingIntent();
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in getSessionActivity.", e);
                return null;
            }
        }

        public void setVolumeTo(int i, int i2) {
            try {
                this.a.setVolumeTo(i, i2, null);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in setVolumeTo.", e);
            }
        }

        public void adjustVolume(int i, int i2) {
            try {
                this.a.adjustVolume(i, i2, null);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in adjustVolume.", e);
            }
        }

        public void sendCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
            try {
                this.a.sendCommand(str, bundle, new ResultReceiverWrapper(resultReceiver));
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in sendCommand.", e);
            }
        }

        public boolean isSessionReady() {
            return true;
        }

        public String getPackageName() {
            try {
                return this.a.getPackageName();
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in getPackageName.", e);
                return null;
            }
        }

        public Object getMediaController() {
            return null;
        }
    }

    static class f extends TransportControls {
        protected final Object a;

        public f(Object obj) {
            this.a = obj;
        }

        public void prepare() {
            sendCustomAction("android.support.v4.media.session.action.PREPARE", null);
        }

        public void prepareFromMediaId(String str, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID", str);
            bundle2.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID", bundle2);
        }

        public void prepareFromSearch(String str, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("android.support.v4.media.session.action.ARGUMENT_QUERY", str);
            bundle2.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_SEARCH", bundle2);
        }

        public void prepareFromUri(Uri uri, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("android.support.v4.media.session.action.ARGUMENT_URI", uri);
            bundle2.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_URI", bundle2);
        }

        public void play() {
            android.support.v4.media.session.MediaControllerCompatApi21.TransportControls.play(this.a);
        }

        public void pause() {
            android.support.v4.media.session.MediaControllerCompatApi21.TransportControls.pause(this.a);
        }

        public void stop() {
            android.support.v4.media.session.MediaControllerCompatApi21.TransportControls.stop(this.a);
        }

        public void seekTo(long j) {
            android.support.v4.media.session.MediaControllerCompatApi21.TransportControls.seekTo(this.a, j);
        }

        public void fastForward() {
            android.support.v4.media.session.MediaControllerCompatApi21.TransportControls.fastForward(this.a);
        }

        public void rewind() {
            android.support.v4.media.session.MediaControllerCompatApi21.TransportControls.rewind(this.a);
        }

        public void skipToNext() {
            android.support.v4.media.session.MediaControllerCompatApi21.TransportControls.skipToNext(this.a);
        }

        public void skipToPrevious() {
            android.support.v4.media.session.MediaControllerCompatApi21.TransportControls.skipToPrevious(this.a);
        }

        public void setRating(RatingCompat ratingCompat) {
            android.support.v4.media.session.MediaControllerCompatApi21.TransportControls.setRating(this.a, ratingCompat != null ? ratingCompat.getRating() : null);
        }

        public void setRating(RatingCompat ratingCompat, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("android.support.v4.media.session.action.ARGUMENT_RATING", ratingCompat);
            bundle2.putParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            sendCustomAction("android.support.v4.media.session.action.SET_RATING", bundle2);
        }

        public void setCaptioningEnabled(boolean z) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED", z);
            sendCustomAction("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED", bundle);
        }

        public void setRepeatMode(int i) {
            Bundle bundle = new Bundle();
            bundle.putInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE", i);
            sendCustomAction("android.support.v4.media.session.action.SET_REPEAT_MODE", bundle);
        }

        public void setShuffleMode(int i) {
            Bundle bundle = new Bundle();
            bundle.putInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE", i);
            sendCustomAction("android.support.v4.media.session.action.SET_SHUFFLE_MODE", bundle);
        }

        public void playFromMediaId(String str, Bundle bundle) {
            android.support.v4.media.session.MediaControllerCompatApi21.TransportControls.playFromMediaId(this.a, str, bundle);
        }

        public void playFromSearch(String str, Bundle bundle) {
            android.support.v4.media.session.MediaControllerCompatApi21.TransportControls.playFromSearch(this.a, str, bundle);
        }

        public void playFromUri(Uri uri, Bundle bundle) {
            if (uri == null || Uri.EMPTY.equals(uri)) {
                throw new IllegalArgumentException("You must specify a non-empty Uri for playFromUri.");
            }
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("android.support.v4.media.session.action.ARGUMENT_URI", uri);
            bundle2.putParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            sendCustomAction("android.support.v4.media.session.action.PLAY_FROM_URI", bundle2);
        }

        public void skipToQueueItem(long j) {
            android.support.v4.media.session.MediaControllerCompatApi21.TransportControls.skipToQueueItem(this.a, j);
        }

        public void sendCustomAction(CustomAction customAction, Bundle bundle) {
            MediaControllerCompat.b(customAction.getAction(), bundle);
            android.support.v4.media.session.MediaControllerCompatApi21.TransportControls.sendCustomAction(this.a, customAction.getAction(), bundle);
        }

        public void sendCustomAction(String str, Bundle bundle) {
            MediaControllerCompat.b(str, bundle);
            android.support.v4.media.session.MediaControllerCompatApi21.TransportControls.sendCustomAction(this.a, str, bundle);
        }
    }

    @RequiresApi(23)
    static class g extends f {
        public g(Object obj) {
            super(obj);
        }

        public void playFromUri(Uri uri, Bundle bundle) {
            android.support.v4.media.session.MediaControllerCompatApi23.TransportControls.playFromUri(this.a, uri, bundle);
        }
    }

    @RequiresApi(24)
    static class h extends g {
        public h(Object obj) {
            super(obj);
        }

        public void prepare() {
            android.support.v4.media.session.MediaControllerCompatApi24.TransportControls.prepare(this.a);
        }

        public void prepareFromMediaId(String str, Bundle bundle) {
            android.support.v4.media.session.MediaControllerCompatApi24.TransportControls.prepareFromMediaId(this.a, str, bundle);
        }

        public void prepareFromSearch(String str, Bundle bundle) {
            android.support.v4.media.session.MediaControllerCompatApi24.TransportControls.prepareFromSearch(this.a, str, bundle);
        }

        public void prepareFromUri(Uri uri, Bundle bundle) {
            android.support.v4.media.session.MediaControllerCompatApi24.TransportControls.prepareFromUri(this.a, uri, bundle);
        }
    }

    static class i extends TransportControls {
        private IMediaSession a;

        public i(IMediaSession iMediaSession) {
            this.a = iMediaSession;
        }

        public void prepare() {
            try {
                this.a.prepare();
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in prepare.", e);
            }
        }

        public void prepareFromMediaId(String str, Bundle bundle) {
            try {
                this.a.prepareFromMediaId(str, bundle);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in prepareFromMediaId.", e);
            }
        }

        public void prepareFromSearch(String str, Bundle bundle) {
            try {
                this.a.prepareFromSearch(str, bundle);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in prepareFromSearch.", e);
            }
        }

        public void prepareFromUri(Uri uri, Bundle bundle) {
            try {
                this.a.prepareFromUri(uri, bundle);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in prepareFromUri.", e);
            }
        }

        public void play() {
            try {
                this.a.play();
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in play.", e);
            }
        }

        public void playFromMediaId(String str, Bundle bundle) {
            try {
                this.a.playFromMediaId(str, bundle);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in playFromMediaId.", e);
            }
        }

        public void playFromSearch(String str, Bundle bundle) {
            try {
                this.a.playFromSearch(str, bundle);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in playFromSearch.", e);
            }
        }

        public void playFromUri(Uri uri, Bundle bundle) {
            try {
                this.a.playFromUri(uri, bundle);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in playFromUri.", e);
            }
        }

        public void skipToQueueItem(long j) {
            try {
                this.a.skipToQueueItem(j);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in skipToQueueItem.", e);
            }
        }

        public void pause() {
            try {
                this.a.pause();
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in pause.", e);
            }
        }

        public void stop() {
            try {
                this.a.stop();
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in stop.", e);
            }
        }

        public void seekTo(long j) {
            try {
                this.a.seekTo(j);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in seekTo.", e);
            }
        }

        public void fastForward() {
            try {
                this.a.fastForward();
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in fastForward.", e);
            }
        }

        public void skipToNext() {
            try {
                this.a.next();
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in skipToNext.", e);
            }
        }

        public void rewind() {
            try {
                this.a.rewind();
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in rewind.", e);
            }
        }

        public void skipToPrevious() {
            try {
                this.a.previous();
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in skipToPrevious.", e);
            }
        }

        public void setRating(RatingCompat ratingCompat) {
            try {
                this.a.rate(ratingCompat);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in setRating.", e);
            }
        }

        public void setRating(RatingCompat ratingCompat, Bundle bundle) {
            try {
                this.a.rateWithExtras(ratingCompat, bundle);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in setRating.", e);
            }
        }

        public void setCaptioningEnabled(boolean z) {
            try {
                this.a.setCaptioningEnabled(z);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in setCaptioningEnabled.", e);
            }
        }

        public void setRepeatMode(int i) {
            try {
                this.a.setRepeatMode(i);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in setRepeatMode.", e);
            }
        }

        public void setShuffleMode(int i) {
            try {
                this.a.setShuffleMode(i);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in setShuffleMode.", e);
            }
        }

        public void sendCustomAction(CustomAction customAction, Bundle bundle) {
            sendCustomAction(customAction.getAction(), bundle);
        }

        public void sendCustomAction(String str, Bundle bundle) {
            MediaControllerCompat.b(str, bundle);
            try {
                this.a.sendCustomAction(str, bundle);
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in sendCustomAction.", e);
            }
        }
    }

    public static void setMediaController(@NonNull Activity activity, MediaControllerCompat mediaControllerCompat) {
        if (activity instanceof SupportActivity) {
            ((SupportActivity) activity).putExtraData(new a(mediaControllerCompat));
        }
        if (VERSION.SDK_INT >= 21) {
            Object obj = null;
            if (mediaControllerCompat != null) {
                obj = MediaControllerCompatApi21.fromToken(activity, mediaControllerCompat.getSessionToken().getToken());
            }
            MediaControllerCompatApi21.setMediaController(activity, obj);
        }
    }

    public static MediaControllerCompat getMediaController(@NonNull Activity activity) {
        if (activity instanceof SupportActivity) {
            MediaControllerCompat a;
            a aVar = (a) ((SupportActivity) activity).getExtraData(a.class);
            if (aVar != null) {
                a = aVar.a();
            } else {
                a = null;
            }
            return a;
        } else if (VERSION.SDK_INT < 21) {
            return null;
        } else {
            Object mediaController = MediaControllerCompatApi21.getMediaController(activity);
            if (mediaController == null) {
                return null;
            }
            try {
                return new MediaControllerCompat((Context) activity, Token.fromToken(MediaControllerCompatApi21.getSessionToken(mediaController)));
            } catch (Throwable e) {
                Log.e("MediaControllerCompat", "Dead object in getMediaController.", e);
                return null;
            }
        }
    }

    private static void b(String str, Bundle bundle) {
        if (str != null) {
            Object obj = -1;
            switch (str.hashCode()) {
                case -1348483723:
                    if (str.equals(MediaSessionCompat.ACTION_FOLLOW)) {
                        obj = null;
                        break;
                    }
                    break;
                case 503011406:
                    if (str.equals(MediaSessionCompat.ACTION_UNFOLLOW)) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                case 1:
                    if (bundle == null || !bundle.containsKey(MediaSessionCompat.ARGUMENT_MEDIA_ATTRIBUTE)) {
                        throw new IllegalArgumentException("An extra field android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE is required for this action " + str + ".");
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public MediaControllerCompat(Context context, @NonNull MediaSessionCompat mediaSessionCompat) {
        if (mediaSessionCompat == null) {
            throw new IllegalArgumentException("session must not be null");
        }
        this.b = mediaSessionCompat.getSessionToken();
        if (VERSION.SDK_INT >= 24) {
            this.a = new d(context, mediaSessionCompat);
        } else if (VERSION.SDK_INT >= 23) {
            this.a = new c(context, mediaSessionCompat);
        } else if (VERSION.SDK_INT >= 21) {
            this.a = new MediaControllerImplApi21(context, mediaSessionCompat);
        } else {
            this.a = new e(this.b);
        }
    }

    public MediaControllerCompat(Context context, @NonNull Token token) throws RemoteException {
        if (token == null) {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
        this.b = token;
        if (VERSION.SDK_INT >= 24) {
            this.a = new d(context, token);
        } else if (VERSION.SDK_INT >= 23) {
            this.a = new c(context, token);
        } else if (VERSION.SDK_INT >= 21) {
            this.a = new MediaControllerImplApi21(context, token);
        } else {
            this.a = new e(this.b);
        }
    }

    public TransportControls getTransportControls() {
        return this.a.getTransportControls();
    }

    public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
        if (keyEvent != null) {
            return this.a.dispatchMediaButtonEvent(keyEvent);
        }
        throw new IllegalArgumentException("KeyEvent may not be null");
    }

    public PlaybackStateCompat getPlaybackState() {
        return this.a.getPlaybackState();
    }

    public MediaMetadataCompat getMetadata() {
        return this.a.getMetadata();
    }

    public List<QueueItem> getQueue() {
        return this.a.getQueue();
    }

    public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        this.a.addQueueItem(mediaDescriptionCompat);
    }

    public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
        this.a.addQueueItem(mediaDescriptionCompat, i);
    }

    public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        this.a.removeQueueItem(mediaDescriptionCompat);
    }

    @Deprecated
    public void removeQueueItemAt(int i) {
        List queue = getQueue();
        if (queue != null && i >= 0 && i < queue.size()) {
            QueueItem queueItem = (QueueItem) queue.get(i);
            if (queueItem != null) {
                removeQueueItem(queueItem.getDescription());
            }
        }
    }

    public CharSequence getQueueTitle() {
        return this.a.getQueueTitle();
    }

    public Bundle getExtras() {
        return this.a.getExtras();
    }

    public int getRatingType() {
        return this.a.getRatingType();
    }

    public boolean isCaptioningEnabled() {
        return this.a.isCaptioningEnabled();
    }

    public int getRepeatMode() {
        return this.a.getRepeatMode();
    }

    public int getShuffleMode() {
        return this.a.getShuffleMode();
    }

    public long getFlags() {
        return this.a.getFlags();
    }

    public PlaybackInfo getPlaybackInfo() {
        return this.a.getPlaybackInfo();
    }

    public PendingIntent getSessionActivity() {
        return this.a.getSessionActivity();
    }

    public Token getSessionToken() {
        return this.b;
    }

    public void setVolumeTo(int i, int i2) {
        this.a.setVolumeTo(i, i2);
    }

    public void adjustVolume(int i, int i2) {
        this.a.adjustVolume(i, i2);
    }

    public void registerCallback(@NonNull Callback callback) {
        registerCallback(callback, null);
    }

    public void registerCallback(@NonNull Callback callback, Handler handler) {
        if (callback == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        if (handler == null) {
            handler = new Handler();
        }
        callback.a(handler);
        this.a.registerCallback(callback, handler);
        this.c.add(callback);
    }

    public void unregisterCallback(@NonNull Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        try {
            this.c.remove(callback);
            this.a.unregisterCallback(callback);
        } finally {
            callback.a(null);
        }
    }

    public void sendCommand(@NonNull String str, Bundle bundle, ResultReceiver resultReceiver) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("command must neither be null nor empty");
        }
        this.a.sendCommand(str, bundle, resultReceiver);
    }

    public boolean isSessionReady() {
        return this.a.isSessionReady();
    }

    public String getPackageName() {
        return this.a.getPackageName();
    }

    public Object getMediaController() {
        return this.a.getMediaController();
    }
}
