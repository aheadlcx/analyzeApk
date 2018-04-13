package android.support.v4.media;

import android.media.browse.MediaBrowser.MediaItem;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.service.media.MediaBrowserService;
import android.support.v4.media.MediaBrowserServiceCompatApi21.ServiceImplApi21;
import android.util.Log;

class MediaBrowserServiceCompatApi23 extends MediaBrowserServiceCompatApi21 {
    private static final String TAG = "MediaBrowserServiceCompatApi21";

    public interface ServiceImplApi23 extends ServiceImplApi21 {
        void getMediaItem(String str, ItemCallback itemCallback);
    }

    public interface ItemCallback {
        void onItemLoaded(int i, Bundle bundle, Parcel parcel);
    }

    static class MediaBrowserServiceAdaptorApi23 extends MediaBrowserServiceAdaptorApi21 {

        private static class ServiceBinderProxyApi23 extends ServiceBinderProxyApi21 {
            ServiceImplApi23 mServiceImpl;

            ServiceBinderProxyApi23(ServiceImplApi23 serviceImplApi23) {
                super(serviceImplApi23);
                this.mServiceImpl = serviceImplApi23;
            }

            public void getMediaItem(String str, final ResultReceiver resultReceiver) {
                Throwable e;
                try {
                    final String str2 = (String) MediaBrowserService.class.getDeclaredField("KEY_MEDIA_ITEM").get(null);
                    this.mServiceImpl.getMediaItem(str, new ItemCallback() {
                        public void onItemLoaded(int i, Bundle bundle, Parcel parcel) {
                            if (parcel != null) {
                                parcel.setDataPosition(0);
                                bundle.putParcelable(str2, (MediaItem) MediaItem.CREATOR.createFromParcel(parcel));
                                parcel.recycle();
                            }
                            resultReceiver.send(i, bundle);
                        }
                    });
                } catch (IllegalAccessException e2) {
                    e = e2;
                    Log.i(MediaBrowserServiceCompatApi23.TAG, "Failed to get KEY_MEDIA_ITEM via reflection", e);
                } catch (NoSuchFieldException e3) {
                    e = e3;
                    Log.i(MediaBrowserServiceCompatApi23.TAG, "Failed to get KEY_MEDIA_ITEM via reflection", e);
                }
            }
        }

        MediaBrowserServiceAdaptorApi23() {
        }

        public void onCreate(ServiceImplApi23 serviceImplApi23) {
            this.mBinder = new ServiceBinderProxyApi23(serviceImplApi23);
        }
    }

    MediaBrowserServiceCompatApi23() {
    }

    public static Object createService() {
        return new MediaBrowserServiceAdaptorApi23();
    }

    public static void onCreate(Object obj, ServiceImplApi23 serviceImplApi23) {
        ((MediaBrowserServiceAdaptorApi23) obj).onCreate(serviceImplApi23);
    }
}
