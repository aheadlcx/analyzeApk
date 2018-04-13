package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.text.TextUtils;

public final class MediaDescriptionCompat implements Parcelable {
    public static final long BT_FOLDER_TYPE_ALBUMS = 2;
    public static final long BT_FOLDER_TYPE_ARTISTS = 3;
    public static final long BT_FOLDER_TYPE_GENRES = 4;
    public static final long BT_FOLDER_TYPE_MIXED = 0;
    public static final long BT_FOLDER_TYPE_PLAYLISTS = 5;
    public static final long BT_FOLDER_TYPE_TITLES = 1;
    public static final long BT_FOLDER_TYPE_YEARS = 6;
    public static final Creator<MediaDescriptionCompat> CREATOR = new aq();
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static final String DESCRIPTION_KEY_MEDIA_URI = "android.support.v4.media.description.MEDIA_URI";
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static final String DESCRIPTION_KEY_NULL_BUNDLE_FLAG = "android.support.v4.media.description.NULL_BUNDLE_FLAG";
    public static final String EXTRA_BT_FOLDER_TYPE = "android.media.extra.BT_FOLDER_TYPE";
    public static final String EXTRA_DOWNLOAD_STATUS = "android.media.extra.DOWNLOAD_STATUS";
    public static final long STATUS_DOWNLOADED = 2;
    public static final long STATUS_DOWNLOADING = 1;
    public static final long STATUS_NOT_DOWNLOADED = 0;
    private final String a;
    private final CharSequence b;
    private final CharSequence c;
    private final CharSequence d;
    private final Bitmap e;
    private final Uri f;
    private final Bundle g;
    private final Uri h;
    private Object i;

    public static final class Builder {
        private String a;
        private CharSequence b;
        private CharSequence c;
        private CharSequence d;
        private Bitmap e;
        private Uri f;
        private Bundle g;
        private Uri h;

        public Builder setMediaId(@Nullable String str) {
            this.a = str;
            return this;
        }

        public Builder setTitle(@Nullable CharSequence charSequence) {
            this.b = charSequence;
            return this;
        }

        public Builder setSubtitle(@Nullable CharSequence charSequence) {
            this.c = charSequence;
            return this;
        }

        public Builder setDescription(@Nullable CharSequence charSequence) {
            this.d = charSequence;
            return this;
        }

        public Builder setIconBitmap(@Nullable Bitmap bitmap) {
            this.e = bitmap;
            return this;
        }

        public Builder setIconUri(@Nullable Uri uri) {
            this.f = uri;
            return this;
        }

        public Builder setExtras(@Nullable Bundle bundle) {
            this.g = bundle;
            return this;
        }

        public Builder setMediaUri(@Nullable Uri uri) {
            this.h = uri;
            return this;
        }

        public MediaDescriptionCompat build() {
            return new MediaDescriptionCompat(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
        }
    }

    MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.a = str;
        this.b = charSequence;
        this.c = charSequence2;
        this.d = charSequence3;
        this.e = bitmap;
        this.f = uri;
        this.g = bundle;
        this.h = uri2;
    }

    MediaDescriptionCompat(Parcel parcel) {
        this.a = parcel.readString();
        this.b = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.c = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.d = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.e = (Bitmap) parcel.readParcelable(null);
        this.f = (Uri) parcel.readParcelable(null);
        this.g = parcel.readBundle();
        this.h = (Uri) parcel.readParcelable(null);
    }

    @Nullable
    public String getMediaId() {
        return this.a;
    }

    @Nullable
    public CharSequence getTitle() {
        return this.b;
    }

    @Nullable
    public CharSequence getSubtitle() {
        return this.c;
    }

    @Nullable
    public CharSequence getDescription() {
        return this.d;
    }

    @Nullable
    public Bitmap getIconBitmap() {
        return this.e;
    }

    @Nullable
    public Uri getIconUri() {
        return this.f;
    }

    @Nullable
    public Bundle getExtras() {
        return this.g;
    }

    @Nullable
    public Uri getMediaUri() {
        return this.h;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (VERSION.SDK_INT < 21) {
            parcel.writeString(this.a);
            TextUtils.writeToParcel(this.b, parcel, i);
            TextUtils.writeToParcel(this.c, parcel, i);
            TextUtils.writeToParcel(this.d, parcel, i);
            parcel.writeParcelable(this.e, i);
            parcel.writeParcelable(this.f, i);
            parcel.writeBundle(this.g);
            parcel.writeParcelable(this.h, i);
            return;
        }
        ar.writeToParcel(getMediaDescription(), parcel, i);
    }

    public String toString() {
        return this.b + ", " + this.c + ", " + this.d;
    }

    public Object getMediaDescription() {
        if (this.i != null || VERSION.SDK_INT < 21) {
            return this.i;
        }
        Object newInstance = a.newInstance();
        a.setMediaId(newInstance, this.a);
        a.setTitle(newInstance, this.b);
        a.setSubtitle(newInstance, this.c);
        a.setDescription(newInstance, this.d);
        a.setIconBitmap(newInstance, this.e);
        a.setIconUri(newInstance, this.f);
        Bundle bundle = this.g;
        if (VERSION.SDK_INT < 23 && this.h != null) {
            if (bundle == null) {
                bundle = new Bundle();
                bundle.putBoolean(DESCRIPTION_KEY_NULL_BUNDLE_FLAG, true);
            }
            bundle.putParcelable(DESCRIPTION_KEY_MEDIA_URI, this.h);
        }
        a.setExtras(newInstance, bundle);
        if (VERSION.SDK_INT >= 23) {
            a.setMediaUri(newInstance, this.h);
        }
        this.i = a.build(newInstance);
        return this.i;
    }

    public static MediaDescriptionCompat fromMediaDescription(Object obj) {
        if (obj == null || VERSION.SDK_INT < 21) {
            return null;
        }
        Uri uri;
        Bundle bundle;
        MediaDescriptionCompat build;
        Builder builder = new Builder();
        builder.setMediaId(ar.getMediaId(obj));
        builder.setTitle(ar.getTitle(obj));
        builder.setSubtitle(ar.getSubtitle(obj));
        builder.setDescription(ar.getDescription(obj));
        builder.setIconBitmap(ar.getIconBitmap(obj));
        builder.setIconUri(ar.getIconUri(obj));
        Bundle extras = ar.getExtras(obj);
        if (extras == null) {
            uri = null;
        } else {
            uri = (Uri) extras.getParcelable(DESCRIPTION_KEY_MEDIA_URI);
        }
        if (uri != null) {
            if (extras.containsKey(DESCRIPTION_KEY_NULL_BUNDLE_FLAG) && extras.size() == 2) {
                bundle = null;
                builder.setExtras(bundle);
                if (uri != null) {
                    builder.setMediaUri(uri);
                } else if (VERSION.SDK_INT >= 23) {
                    builder.setMediaUri(as.getMediaUri(obj));
                }
                build = builder.build();
                build.i = obj;
                return build;
            }
            extras.remove(DESCRIPTION_KEY_MEDIA_URI);
            extras.remove(DESCRIPTION_KEY_NULL_BUNDLE_FLAG);
        }
        bundle = extras;
        builder.setExtras(bundle);
        if (uri != null) {
            builder.setMediaUri(uri);
        } else if (VERSION.SDK_INT >= 23) {
            builder.setMediaUri(as.getMediaUri(obj));
        }
        build = builder.build();
        build.i = obj;
        return build;
    }
}
