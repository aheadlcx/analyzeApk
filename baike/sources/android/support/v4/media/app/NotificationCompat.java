package android.support.v4.media.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.media.session.MediaSession;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.mediacompat.R;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.Style;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.widget.RemoteViews;

public class NotificationCompat {

    public static class MediaStyle extends Style {
        int[] a = null;
        Token b;
        boolean c;
        PendingIntent h;

        public static Token getMediaSession(Notification notification) {
            Bundle extras = android.support.v4.app.NotificationCompat.getExtras(notification);
            if (extras != null) {
                if (VERSION.SDK_INT >= 21) {
                    Parcelable parcelable = extras.getParcelable(android.support.v4.app.NotificationCompat.EXTRA_MEDIA_SESSION);
                    if (parcelable != null) {
                        return Token.fromToken(parcelable);
                    }
                }
                IBinder binder = BundleCompat.getBinder(extras, android.support.v4.app.NotificationCompat.EXTRA_MEDIA_SESSION);
                if (binder != null) {
                    Parcel obtain = Parcel.obtain();
                    obtain.writeStrongBinder(binder);
                    obtain.setDataPosition(0);
                    Token token = (Token) Token.CREATOR.createFromParcel(obtain);
                    obtain.recycle();
                    return token;
                }
            }
            return null;
        }

        public MediaStyle(Builder builder) {
            setBuilder(builder);
        }

        public MediaStyle setShowActionsInCompactView(int... iArr) {
            this.a = iArr;
            return this;
        }

        public MediaStyle setMediaSession(Token token) {
            this.b = token;
            return this;
        }

        public MediaStyle setShowCancelButton(boolean z) {
            if (VERSION.SDK_INT < 21) {
                this.c = z;
            }
            return this;
        }

        public MediaStyle setCancelButtonIntent(PendingIntent pendingIntent) {
            this.h = pendingIntent;
            return this;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 21) {
                notificationBuilderWithBuilderAccessor.getBuilder().setStyle(a(new android.app.Notification.MediaStyle()));
            } else if (this.c) {
                notificationBuilderWithBuilderAccessor.getBuilder().setOngoing(true);
            }
        }

        @RequiresApi(21)
        android.app.Notification.MediaStyle a(android.app.Notification.MediaStyle mediaStyle) {
            if (this.a != null) {
                mediaStyle.setShowActionsInCompactView(this.a);
            }
            if (this.b != null) {
                mediaStyle.setMediaSession((MediaSession.Token) this.b.getToken());
            }
            return mediaStyle;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 21) {
                return null;
            }
            return b();
        }

        RemoteViews b() {
            boolean z;
            RemoteViews applyStandardTemplate = applyStandardTemplate(false, a(), true);
            boolean size = this.d.mActions.size();
            if (this.a == null) {
                z = false;
            } else {
                z = Math.min(this.a.length, 3);
            }
            applyStandardTemplate.removeAllViews(R.id.media_actions);
            if (z <= false) {
                for (boolean z2 = false; z2 < z; z2++) {
                    if (z2 >= size) {
                        throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", new Object[]{Integer.valueOf(z2), Integer.valueOf(size - 1)}));
                    }
                    applyStandardTemplate.addView(R.id.media_actions, a((Action) this.d.mActions.get(this.a[z2])));
                }
            }
            if (this.c) {
                applyStandardTemplate.setViewVisibility(R.id.end_padder, 8);
                applyStandardTemplate.setViewVisibility(R.id.cancel_action, 0);
                applyStandardTemplate.setOnClickPendingIntent(R.id.cancel_action, this.h);
                applyStandardTemplate.setInt(R.id.cancel_action, "setAlpha", this.d.mContext.getResources().getInteger(R.integer.cancel_button_image_alpha));
            } else {
                applyStandardTemplate.setViewVisibility(R.id.end_padder, 0);
                applyStandardTemplate.setViewVisibility(R.id.cancel_action, 8);
            }
            return applyStandardTemplate;
        }

        private RemoteViews a(Action action) {
            Object obj = action.getActionIntent() == null ? 1 : null;
            RemoteViews remoteViews = new RemoteViews(this.d.mContext.getPackageName(), R.layout.notification_media_action);
            remoteViews.setImageViewResource(R.id.action0, action.getIcon());
            if (obj == null) {
                remoteViews.setOnClickPendingIntent(R.id.action0, action.getActionIntent());
            }
            if (VERSION.SDK_INT >= 15) {
                remoteViews.setContentDescription(R.id.action0, action.getTitle());
            }
            return remoteViews;
        }

        int a() {
            return R.layout.notification_template_media;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 21) {
                return null;
            }
            return c();
        }

        RemoteViews c() {
            int min = Math.min(this.d.mActions.size(), 5);
            RemoteViews applyStandardTemplate = applyStandardTemplate(false, a(min), false);
            applyStandardTemplate.removeAllViews(R.id.media_actions);
            if (min > 0) {
                for (int i = 0; i < min; i++) {
                    applyStandardTemplate.addView(R.id.media_actions, a((Action) this.d.mActions.get(i)));
                }
            }
            if (this.c) {
                applyStandardTemplate.setViewVisibility(R.id.cancel_action, 0);
                applyStandardTemplate.setInt(R.id.cancel_action, "setAlpha", this.d.mContext.getResources().getInteger(R.integer.cancel_button_image_alpha));
                applyStandardTemplate.setOnClickPendingIntent(R.id.cancel_action, this.h);
            } else {
                applyStandardTemplate.setViewVisibility(R.id.cancel_action, 8);
            }
            return applyStandardTemplate;
        }

        int a(int i) {
            return i <= 3 ? R.layout.notification_template_big_media_narrow : R.layout.notification_template_big_media;
        }
    }

    public static class DecoratedMediaCustomViewStyle extends MediaStyle {
        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 24) {
                notificationBuilderWithBuilderAccessor.getBuilder().setStyle(a((android.app.Notification.MediaStyle) new android.app.Notification.DecoratedMediaCustomViewStyle()));
            } else {
                super.apply(notificationBuilderWithBuilderAccessor);
            }
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Object obj = null;
            if (VERSION.SDK_INT >= 24) {
                return null;
            }
            Object obj2 = this.d.getContentView() != null ? 1 : null;
            RemoteViews b;
            if (VERSION.SDK_INT >= 21) {
                if (!(obj2 == null && this.d.getBigContentView() == null)) {
                    obj = 1;
                }
                if (obj != null) {
                    b = b();
                    if (obj2 != null) {
                        buildIntoRemoteViews(b, this.d.getContentView());
                    }
                    a(b);
                    return b;
                }
            }
            b = b();
            if (obj2 != null) {
                buildIntoRemoteViews(b, this.d.getContentView());
                return b;
            }
            return null;
        }

        int a() {
            if (this.d.getContentView() != null) {
                return R.layout.notification_template_media_custom;
            }
            return super.a();
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            RemoteViews remoteViews = null;
            if (VERSION.SDK_INT < 24) {
                RemoteViews bigContentView;
                if (this.d.getBigContentView() != null) {
                    bigContentView = this.d.getBigContentView();
                } else {
                    bigContentView = this.d.getContentView();
                }
                if (bigContentView != null) {
                    remoteViews = c();
                    buildIntoRemoteViews(remoteViews, bigContentView);
                    if (VERSION.SDK_INT >= 21) {
                        a(remoteViews);
                    }
                }
            }
            return remoteViews;
        }

        int a(int i) {
            return i <= 3 ? R.layout.notification_template_big_media_narrow_custom : R.layout.notification_template_big_media_custom;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            RemoteViews remoteViews = null;
            if (VERSION.SDK_INT < 24) {
                RemoteViews headsUpContentView;
                if (this.d.getHeadsUpContentView() != null) {
                    headsUpContentView = this.d.getHeadsUpContentView();
                } else {
                    headsUpContentView = this.d.getContentView();
                }
                if (headsUpContentView != null) {
                    remoteViews = c();
                    buildIntoRemoteViews(remoteViews, headsUpContentView);
                    if (VERSION.SDK_INT >= 21) {
                        a(remoteViews);
                    }
                }
            }
            return remoteViews;
        }

        private void a(RemoteViews remoteViews) {
            int color;
            if (this.d.getColor() != 0) {
                color = this.d.getColor();
            } else {
                color = this.d.mContext.getResources().getColor(R.color.notification_material_background_media_default_color);
            }
            remoteViews.setInt(R.id.status_bar_latest_event_content, "setBackgroundColor", color);
        }
    }

    private NotificationCompat() {
    }
}
