package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.compat.R;
import android.support.v4.text.BidiFormatter;
import android.support.v4.view.GravityCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.SparseArray;
import android.widget.RemoteViews;
import com.umeng.analytics.pro.b;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import qsbk.app.im.datastore.DatabaseHelper;

public class NotificationCompat {
    public static final int BADGE_ICON_LARGE = 2;
    public static final int BADGE_ICON_NONE = 0;
    public static final int BADGE_ICON_SMALL = 1;
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_REMINDER = "reminder";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";
    @ColorInt
    public static final int COLOR_DEFAULT = 0;
    public static final int DEFAULT_ALL = -1;
    public static final int DEFAULT_LIGHTS = 4;
    public static final int DEFAULT_SOUND = 1;
    public static final int DEFAULT_VIBRATE = 2;
    public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";
    public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
    public static final String EXTRA_BIG_TEXT = "android.bigText";
    public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
    public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
    public static final String EXTRA_INFO_TEXT = "android.infoText";
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";
    public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
    public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
    public static final String EXTRA_MESSAGES = "android.messages";
    public static final String EXTRA_PEOPLE = "android.people";
    public static final String EXTRA_PICTURE = "android.picture";
    public static final String EXTRA_PROGRESS = "android.progress";
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
    public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
    public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
    public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
    public static final String EXTRA_SHOW_WHEN = "android.showWhen";
    public static final String EXTRA_SMALL_ICON = "android.icon";
    public static final String EXTRA_SUB_TEXT = "android.subText";
    public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
    public static final String EXTRA_TEMPLATE = "android.template";
    public static final String EXTRA_TEXT = "android.text";
    public static final String EXTRA_TEXT_LINES = "android.textLines";
    public static final String EXTRA_TITLE = "android.title";
    public static final String EXTRA_TITLE_BIG = "android.title.big";
    public static final int FLAG_AUTO_CANCEL = 16;
    public static final int FLAG_FOREGROUND_SERVICE = 64;
    public static final int FLAG_GROUP_SUMMARY = 512;
    @Deprecated
    public static final int FLAG_HIGH_PRIORITY = 128;
    public static final int FLAG_INSISTENT = 4;
    public static final int FLAG_LOCAL_ONLY = 256;
    public static final int FLAG_NO_CLEAR = 32;
    public static final int FLAG_ONGOING_EVENT = 2;
    public static final int FLAG_ONLY_ALERT_ONCE = 8;
    public static final int FLAG_SHOW_LIGHTS = 1;
    public static final int GROUP_ALERT_ALL = 0;
    public static final int GROUP_ALERT_CHILDREN = 2;
    public static final int GROUP_ALERT_SUMMARY = 1;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;
    public static final int STREAM_DEFAULT = -1;
    public static final int VISIBILITY_PRIVATE = 0;
    public static final int VISIBILITY_PUBLIC = 1;
    public static final int VISIBILITY_SECRET = -1;

    public static class Action {
        final Bundle a;
        public PendingIntent actionIntent;
        private final RemoteInput[] b;
        private final RemoteInput[] c;
        private boolean d;
        public int icon;
        public CharSequence title;

        public static final class Builder {
            private final int a;
            private final CharSequence b;
            private final PendingIntent c;
            private boolean d;
            private final Bundle e;
            private ArrayList<RemoteInput> f;

            public Builder(int i, CharSequence charSequence, PendingIntent pendingIntent) {
                this(i, charSequence, pendingIntent, new Bundle(), null, true);
            }

            public Builder(Action action) {
                this(action.icon, action.title, action.actionIntent, new Bundle(action.a), action.getRemoteInputs(), action.getAllowGeneratedReplies());
            }

            private Builder(int i, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInput[] remoteInputArr, boolean z) {
                ArrayList arrayList;
                this.d = true;
                this.a = i;
                this.b = Builder.a(charSequence);
                this.c = pendingIntent;
                this.e = bundle;
                if (remoteInputArr == null) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList(Arrays.asList(remoteInputArr));
                }
                this.f = arrayList;
                this.d = z;
            }

            public Builder addExtras(Bundle bundle) {
                if (bundle != null) {
                    this.e.putAll(bundle);
                }
                return this;
            }

            public Bundle getExtras() {
                return this.e;
            }

            public Builder addRemoteInput(RemoteInput remoteInput) {
                if (this.f == null) {
                    this.f = new ArrayList();
                }
                this.f.add(remoteInput);
                return this;
            }

            public Builder setAllowGeneratedReplies(boolean z) {
                this.d = z;
                return this;
            }

            public Builder extend(Extender extender) {
                extender.extend(this);
                return this;
            }

            public Action build() {
                RemoteInput[] remoteInputArr;
                RemoteInput[] remoteInputArr2;
                List arrayList = new ArrayList();
                List arrayList2 = new ArrayList();
                if (this.f != null) {
                    Iterator it = this.f.iterator();
                    while (it.hasNext()) {
                        RemoteInput remoteInput = (RemoteInput) it.next();
                        if (remoteInput.isDataOnly()) {
                            arrayList.add(remoteInput);
                        } else {
                            arrayList2.add(remoteInput);
                        }
                    }
                }
                if (arrayList.isEmpty()) {
                    remoteInputArr = null;
                } else {
                    remoteInputArr = (RemoteInput[]) arrayList.toArray(new RemoteInput[arrayList.size()]);
                }
                if (arrayList2.isEmpty()) {
                    remoteInputArr2 = null;
                } else {
                    remoteInputArr2 = (RemoteInput[]) arrayList2.toArray(new RemoteInput[arrayList2.size()]);
                }
                return new Action(this.a, this.b, this.c, this.e, remoteInputArr2, remoteInputArr, this.d);
            }
        }

        public interface Extender {
            Builder extend(Builder builder);
        }

        public static final class WearableExtender implements Extender {
            private int a = 1;
            private CharSequence b;
            private CharSequence c;
            private CharSequence d;

            public WearableExtender(Action action) {
                Bundle bundle = action.getExtras().getBundle("android.wearable.EXTENSIONS");
                if (bundle != null) {
                    this.a = bundle.getInt("flags", 1);
                    this.b = bundle.getCharSequence("inProgressLabel");
                    this.c = bundle.getCharSequence("confirmLabel");
                    this.d = bundle.getCharSequence("cancelLabel");
                }
            }

            public Builder extend(Builder builder) {
                Bundle bundle = new Bundle();
                if (this.a != 1) {
                    bundle.putInt("flags", this.a);
                }
                if (this.b != null) {
                    bundle.putCharSequence("inProgressLabel", this.b);
                }
                if (this.c != null) {
                    bundle.putCharSequence("confirmLabel", this.c);
                }
                if (this.d != null) {
                    bundle.putCharSequence("cancelLabel", this.d);
                }
                builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
                return builder;
            }

            public WearableExtender clone() {
                WearableExtender wearableExtender = new WearableExtender();
                wearableExtender.a = this.a;
                wearableExtender.b = this.b;
                wearableExtender.c = this.c;
                wearableExtender.d = this.d;
                return wearableExtender;
            }

            public WearableExtender setAvailableOffline(boolean z) {
                a(1, z);
                return this;
            }

            public boolean isAvailableOffline() {
                return (this.a & 1) != 0;
            }

            private void a(int i, boolean z) {
                if (z) {
                    this.a |= i;
                } else {
                    this.a &= i ^ -1;
                }
            }

            public WearableExtender setInProgressLabel(CharSequence charSequence) {
                this.b = charSequence;
                return this;
            }

            public CharSequence getInProgressLabel() {
                return this.b;
            }

            public WearableExtender setConfirmLabel(CharSequence charSequence) {
                this.c = charSequence;
                return this;
            }

            public CharSequence getConfirmLabel() {
                return this.c;
            }

            public WearableExtender setCancelLabel(CharSequence charSequence) {
                this.d = charSequence;
                return this;
            }

            public CharSequence getCancelLabel() {
                return this.d;
            }

            public WearableExtender setHintLaunchesActivity(boolean z) {
                a(2, z);
                return this;
            }

            public boolean getHintLaunchesActivity() {
                return (this.a & 2) != 0;
            }

            public WearableExtender setHintDisplayActionInline(boolean z) {
                a(4, z);
                return this;
            }

            public boolean getHintDisplayActionInline() {
                return (this.a & 4) != 0;
            }
        }

        public Action(int i, CharSequence charSequence, PendingIntent pendingIntent) {
            this(i, charSequence, pendingIntent, new Bundle(), null, null, true);
        }

        Action(int i, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInput[] remoteInputArr, RemoteInput[] remoteInputArr2, boolean z) {
            this.icon = i;
            this.title = Builder.a(charSequence);
            this.actionIntent = pendingIntent;
            if (bundle == null) {
                bundle = new Bundle();
            }
            this.a = bundle;
            this.b = remoteInputArr;
            this.c = remoteInputArr2;
            this.d = z;
        }

        public int getIcon() {
            return this.icon;
        }

        public CharSequence getTitle() {
            return this.title;
        }

        public PendingIntent getActionIntent() {
            return this.actionIntent;
        }

        public Bundle getExtras() {
            return this.a;
        }

        public boolean getAllowGeneratedReplies() {
            return this.d;
        }

        public RemoteInput[] getRemoteInputs() {
            return this.b;
        }

        public RemoteInput[] getDataOnlyRemoteInputs() {
            return this.c;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BadgeIconType {
    }

    public static abstract class Style {
        @RestrictTo({Scope.LIBRARY_GROUP})
        protected Builder d;
        CharSequence e;
        CharSequence f;
        boolean g = false;

        public void setBuilder(Builder builder) {
            if (this.d != builder) {
                this.d = builder;
                if (this.d != null) {
                    this.d.setStyle(this);
                }
            }
        }

        public Notification build() {
            if (this.d != null) {
                return this.d.build();
            }
            return null;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public void addCompatExtras(Bundle bundle) {
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        protected void a(Bundle bundle) {
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews applyStandardTemplate(boolean z, int i, boolean z2) {
            Object obj;
            Object obj2;
            Resources resources = this.d.mContext.getResources();
            RemoteViews remoteViews = new RemoteViews(this.d.mContext.getPackageName(), i);
            Object obj3 = this.d.getPriority() < -1 ? 1 : null;
            if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT < 21) {
                if (obj3 != null) {
                    remoteViews.setInt(R.id.notification_background, "setBackgroundResource", R.drawable.notification_bg_low);
                    remoteViews.setInt(R.id.icon, "setBackgroundResource", R.drawable.notification_template_icon_low_bg);
                } else {
                    remoteViews.setInt(R.id.notification_background, "setBackgroundResource", R.drawable.notification_bg);
                    remoteViews.setInt(R.id.icon, "setBackgroundResource", R.drawable.notification_template_icon_bg);
                }
            }
            if (this.d.f != null) {
                if (VERSION.SDK_INT >= 16) {
                    remoteViews.setViewVisibility(R.id.icon, 0);
                    remoteViews.setImageViewBitmap(R.id.icon, this.d.f);
                } else {
                    remoteViews.setViewVisibility(R.id.icon, 8);
                }
                if (z && this.d.K.icon != 0) {
                    int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.notification_right_icon_size);
                    int dimensionPixelSize2 = dimensionPixelSize - (resources.getDimensionPixelSize(R.dimen.notification_small_icon_background_padding) * 2);
                    if (VERSION.SDK_INT >= 21) {
                        remoteViews.setImageViewBitmap(R.id.right_icon, a(this.d.K.icon, dimensionPixelSize, dimensionPixelSize2, this.d.getColor()));
                    } else {
                        remoteViews.setImageViewBitmap(R.id.right_icon, createColoredBitmap(this.d.K.icon, -1));
                    }
                    remoteViews.setViewVisibility(R.id.right_icon, 0);
                }
            } else if (z && this.d.K.icon != 0) {
                remoteViews.setViewVisibility(R.id.icon, 0);
                if (VERSION.SDK_INT >= 21) {
                    remoteViews.setImageViewBitmap(R.id.icon, a(this.d.K.icon, resources.getDimensionPixelSize(R.dimen.notification_large_icon_width) - resources.getDimensionPixelSize(R.dimen.notification_big_circle_margin), resources.getDimensionPixelSize(R.dimen.notification_small_icon_size_as_large), this.d.getColor()));
                } else {
                    remoteViews.setImageViewBitmap(R.id.icon, createColoredBitmap(this.d.K.icon, -1));
                }
            }
            if (this.d.a != null) {
                remoteViews.setTextViewText(R.id.title, this.d.a);
            }
            if (this.d.b != null) {
                remoteViews.setTextViewText(R.id.text, this.d.b);
                obj3 = 1;
            } else {
                obj3 = null;
            }
            Object obj4 = (VERSION.SDK_INT >= 21 || this.d.f == null) ? null : 1;
            if (this.d.g != null) {
                remoteViews.setTextViewText(R.id.info, this.d.g);
                remoteViews.setViewVisibility(R.id.info, 0);
                obj = 1;
                obj2 = 1;
            } else if (this.d.h > 0) {
                if (this.d.h > resources.getInteger(R.integer.status_bar_notification_info_maxnum)) {
                    remoteViews.setTextViewText(R.id.info, resources.getString(R.string.status_bar_notification_info_overflow));
                } else {
                    remoteViews.setTextViewText(R.id.info, NumberFormat.getIntegerInstance().format((long) this.d.h));
                }
                remoteViews.setViewVisibility(R.id.info, 0);
                int i2 = 1;
                int i3 = 1;
            } else {
                remoteViews.setViewVisibility(R.id.info, 8);
                obj = obj4;
                obj2 = obj3;
            }
            if (this.d.m != null && VERSION.SDK_INT >= 16) {
                remoteViews.setTextViewText(R.id.text, this.d.m);
                if (this.d.b != null) {
                    remoteViews.setTextViewText(R.id.text2, this.d.b);
                    remoteViews.setViewVisibility(R.id.text2, 0);
                    obj3 = 1;
                    if (obj3 != null && VERSION.SDK_INT >= 16) {
                        if (z2) {
                            remoteViews.setTextViewTextSize(R.id.text, 0, (float) resources.getDimensionPixelSize(R.dimen.notification_subtext_size));
                        }
                        remoteViews.setViewPadding(R.id.line1, 0, 0, 0, 0);
                    }
                    if (this.d.getWhenIfShowing() != 0) {
                        if (this.d.k || VERSION.SDK_INT < 16) {
                            remoteViews.setViewVisibility(R.id.time, 0);
                            remoteViews.setLong(R.id.time, "setTime", this.d.getWhenIfShowing());
                        } else {
                            remoteViews.setViewVisibility(R.id.chronometer, 0);
                            remoteViews.setLong(R.id.chronometer, "setBase", this.d.getWhenIfShowing() + (SystemClock.elapsedRealtime() - System.currentTimeMillis()));
                            remoteViews.setBoolean(R.id.chronometer, "setStarted", true);
                        }
                        obj = 1;
                    }
                    remoteViews.setViewVisibility(R.id.right_side, obj == null ? 0 : 8);
                    remoteViews.setViewVisibility(R.id.line3, obj2 == null ? 0 : 8);
                    return remoteViews;
                }
                remoteViews.setViewVisibility(R.id.text2, 8);
            }
            obj3 = null;
            if (z2) {
                remoteViews.setTextViewTextSize(R.id.text, 0, (float) resources.getDimensionPixelSize(R.dimen.notification_subtext_size));
            }
            remoteViews.setViewPadding(R.id.line1, 0, 0, 0, 0);
            if (this.d.getWhenIfShowing() != 0) {
                if (this.d.k) {
                }
                remoteViews.setViewVisibility(R.id.time, 0);
                remoteViews.setLong(R.id.time, "setTime", this.d.getWhenIfShowing());
                obj = 1;
            }
            if (obj == null) {
            }
            remoteViews.setViewVisibility(R.id.right_side, obj == null ? 0 : 8);
            if (obj2 == null) {
            }
            remoteViews.setViewVisibility(R.id.line3, obj2 == null ? 0 : 8);
            return remoteViews;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public Bitmap createColoredBitmap(int i, int i2) {
            return a(i, i2, 0);
        }

        private Bitmap a(int i, int i2, int i3) {
            Drawable drawable = this.d.mContext.getResources().getDrawable(i);
            int intrinsicWidth = i3 == 0 ? drawable.getIntrinsicWidth() : i3;
            if (i3 == 0) {
                i3 = drawable.getIntrinsicHeight();
            }
            Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, i3, Config.ARGB_8888);
            drawable.setBounds(0, 0, intrinsicWidth, i3);
            if (i2 != 0) {
                drawable.mutate().setColorFilter(new PorterDuffColorFilter(i2, Mode.SRC_IN));
            }
            drawable.draw(new Canvas(createBitmap));
            return createBitmap;
        }

        private Bitmap a(int i, int i2, int i3, int i4) {
            int i5 = R.drawable.notification_icon_background;
            if (i4 == 0) {
                i4 = 0;
            }
            Bitmap a = a(i5, i4, i2);
            Canvas canvas = new Canvas(a);
            Drawable mutate = this.d.mContext.getResources().getDrawable(i).mutate();
            mutate.setFilterBitmap(true);
            int i6 = (i2 - i3) / 2;
            mutate.setBounds(i6, i6, i3 + i6, i3 + i6);
            mutate.setColorFilter(new PorterDuffColorFilter(-1, Mode.SRC_ATOP));
            mutate.draw(canvas);
            return a;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public void buildIntoRemoteViews(RemoteViews remoteViews, RemoteViews remoteViews2) {
            a(remoteViews);
            remoteViews.removeAllViews(R.id.notification_main_column);
            remoteViews.addView(R.id.notification_main_column, remoteViews2.clone());
            remoteViews.setViewVisibility(R.id.notification_main_column, 0);
            if (VERSION.SDK_INT >= 21) {
                remoteViews.setViewPadding(R.id.notification_main_column_container, 0, a(), 0, 0);
            }
        }

        private void a(RemoteViews remoteViews) {
            remoteViews.setViewVisibility(R.id.title, 8);
            remoteViews.setViewVisibility(R.id.text2, 8);
            remoteViews.setViewVisibility(R.id.text, 8);
        }

        private int a() {
            Resources resources = this.d.mContext.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.notification_top_pad);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.notification_top_pad_large_text);
            float a = (a(resources.getConfiguration().fontScale, 1.0f, 1.3f) - 1.0f) / 0.29999995f;
            return Math.round((a * ((float) dimensionPixelSize2)) + (((float) dimensionPixelSize) * (1.0f - a)));
        }

        private static float a(float f, float f2, float f3) {
            if (f < f2) {
                return f2;
            }
            return f > f3 ? f3 : f;
        }
    }

    public static class BigPictureStyle extends Style {
        private Bitmap a;
        private Bitmap b;
        private boolean c;

        public BigPictureStyle(Builder builder) {
            setBuilder(builder);
        }

        public BigPictureStyle setBigContentTitle(CharSequence charSequence) {
            this.e = Builder.a(charSequence);
            return this;
        }

        public BigPictureStyle setSummaryText(CharSequence charSequence) {
            this.f = Builder.a(charSequence);
            this.g = true;
            return this;
        }

        public BigPictureStyle bigPicture(Bitmap bitmap) {
            this.a = bitmap;
            return this;
        }

        public BigPictureStyle bigLargeIcon(Bitmap bitmap) {
            this.b = bitmap;
            this.c = true;
            return this;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 16) {
                android.app.Notification.BigPictureStyle bigPicture = new android.app.Notification.BigPictureStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.e).bigPicture(this.a);
                if (this.c) {
                    bigPicture.bigLargeIcon(this.b);
                }
                if (this.g) {
                    bigPicture.setSummaryText(this.f);
                }
            }
        }
    }

    public static class BigTextStyle extends Style {
        private CharSequence a;

        public BigTextStyle(Builder builder) {
            setBuilder(builder);
        }

        public BigTextStyle setBigContentTitle(CharSequence charSequence) {
            this.e = Builder.a(charSequence);
            return this;
        }

        public BigTextStyle setSummaryText(CharSequence charSequence) {
            this.f = Builder.a(charSequence);
            this.g = true;
            return this;
        }

        public BigTextStyle bigText(CharSequence charSequence) {
            this.a = Builder.a(charSequence);
            return this;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 16) {
                android.app.Notification.BigTextStyle bigText = new android.app.Notification.BigTextStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.e).bigText(this.a);
                if (this.g) {
                    bigText.setSummaryText(this.f);
                }
            }
        }
    }

    public static class Builder {
        int A;
        Notification B;
        RemoteViews C;
        RemoteViews D;
        RemoteViews E;
        String F;
        int G;
        String H;
        long I;
        int J;
        Notification K;
        CharSequence a;
        CharSequence b;
        PendingIntent c;
        PendingIntent d;
        RemoteViews e;
        Bitmap f;
        CharSequence g;
        int h;
        int i;
        boolean j;
        boolean k;
        Style l;
        CharSequence m;
        @RestrictTo({Scope.LIBRARY_GROUP})
        public ArrayList<Action> mActions;
        @RestrictTo({Scope.LIBRARY_GROUP})
        public Context mContext;
        @Deprecated
        public ArrayList<String> mPeople;
        CharSequence[] n;
        int o;
        int p;
        boolean q;
        String r;
        boolean s;
        String t;
        boolean u;
        boolean v;
        boolean w;
        String x;
        Bundle y;
        int z;

        public Builder(@NonNull Context context, @NonNull String str) {
            this.mActions = new ArrayList();
            this.j = true;
            this.u = false;
            this.z = 0;
            this.A = 0;
            this.G = 0;
            this.J = 0;
            this.K = new Notification();
            this.mContext = context;
            this.F = str;
            this.K.when = System.currentTimeMillis();
            this.K.audioStreamType = -1;
            this.i = 0;
            this.mPeople = new ArrayList();
        }

        @Deprecated
        public Builder(Context context) {
            this(context, null);
        }

        public Builder setWhen(long j) {
            this.K.when = j;
            return this;
        }

        public Builder setShowWhen(boolean z) {
            this.j = z;
            return this;
        }

        public Builder setUsesChronometer(boolean z) {
            this.k = z;
            return this;
        }

        public Builder setSmallIcon(int i) {
            this.K.icon = i;
            return this;
        }

        public Builder setSmallIcon(int i, int i2) {
            this.K.icon = i;
            this.K.iconLevel = i2;
            return this;
        }

        public Builder setContentTitle(CharSequence charSequence) {
            this.a = a(charSequence);
            return this;
        }

        public Builder setContentText(CharSequence charSequence) {
            this.b = a(charSequence);
            return this;
        }

        public Builder setSubText(CharSequence charSequence) {
            this.m = a(charSequence);
            return this;
        }

        public Builder setRemoteInputHistory(CharSequence[] charSequenceArr) {
            this.n = charSequenceArr;
            return this;
        }

        public Builder setNumber(int i) {
            this.h = i;
            return this;
        }

        public Builder setContentInfo(CharSequence charSequence) {
            this.g = a(charSequence);
            return this;
        }

        public Builder setProgress(int i, int i2, boolean z) {
            this.o = i;
            this.p = i2;
            this.q = z;
            return this;
        }

        public Builder setContent(RemoteViews remoteViews) {
            this.K.contentView = remoteViews;
            return this;
        }

        public Builder setContentIntent(PendingIntent pendingIntent) {
            this.c = pendingIntent;
            return this;
        }

        public Builder setDeleteIntent(PendingIntent pendingIntent) {
            this.K.deleteIntent = pendingIntent;
            return this;
        }

        public Builder setFullScreenIntent(PendingIntent pendingIntent, boolean z) {
            this.d = pendingIntent;
            a(128, z);
            return this;
        }

        public Builder setTicker(CharSequence charSequence) {
            this.K.tickerText = a(charSequence);
            return this;
        }

        public Builder setTicker(CharSequence charSequence, RemoteViews remoteViews) {
            this.K.tickerText = a(charSequence);
            this.e = remoteViews;
            return this;
        }

        public Builder setLargeIcon(Bitmap bitmap) {
            this.f = bitmap;
            return this;
        }

        public Builder setSound(Uri uri) {
            this.K.sound = uri;
            this.K.audioStreamType = -1;
            return this;
        }

        public Builder setSound(Uri uri, int i) {
            this.K.sound = uri;
            this.K.audioStreamType = i;
            return this;
        }

        public Builder setVibrate(long[] jArr) {
            this.K.vibrate = jArr;
            return this;
        }

        public Builder setLights(@ColorInt int i, int i2, int i3) {
            int i4;
            int i5 = 1;
            this.K.ledARGB = i;
            this.K.ledOnMS = i2;
            this.K.ledOffMS = i3;
            if (this.K.ledOnMS == 0 || this.K.ledOffMS == 0) {
                i4 = 0;
            } else {
                i4 = 1;
            }
            Notification notification = this.K;
            int i6 = this.K.flags & -2;
            if (i4 == 0) {
                i5 = 0;
            }
            notification.flags = i6 | i5;
            return this;
        }

        public Builder setOngoing(boolean z) {
            a(2, z);
            return this;
        }

        public Builder setColorized(boolean z) {
            this.v = z;
            this.w = true;
            return this;
        }

        public Builder setOnlyAlertOnce(boolean z) {
            a(8, z);
            return this;
        }

        public Builder setAutoCancel(boolean z) {
            a(16, z);
            return this;
        }

        public Builder setLocalOnly(boolean z) {
            this.u = z;
            return this;
        }

        public Builder setCategory(String str) {
            this.x = str;
            return this;
        }

        public Builder setDefaults(int i) {
            this.K.defaults = i;
            if ((i & 4) != 0) {
                Notification notification = this.K;
                notification.flags |= 1;
            }
            return this;
        }

        private void a(int i, boolean z) {
            if (z) {
                Notification notification = this.K;
                notification.flags |= i;
                return;
            }
            notification = this.K;
            notification.flags &= i ^ -1;
        }

        public Builder setPriority(int i) {
            this.i = i;
            return this;
        }

        public Builder addPerson(String str) {
            this.mPeople.add(str);
            return this;
        }

        public Builder setGroup(String str) {
            this.r = str;
            return this;
        }

        public Builder setGroupSummary(boolean z) {
            this.s = z;
            return this;
        }

        public Builder setSortKey(String str) {
            this.t = str;
            return this;
        }

        public Builder addExtras(Bundle bundle) {
            if (bundle != null) {
                if (this.y == null) {
                    this.y = new Bundle(bundle);
                } else {
                    this.y.putAll(bundle);
                }
            }
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.y = bundle;
            return this;
        }

        public Bundle getExtras() {
            if (this.y == null) {
                this.y = new Bundle();
            }
            return this.y;
        }

        public Builder addAction(int i, CharSequence charSequence, PendingIntent pendingIntent) {
            this.mActions.add(new Action(i, charSequence, pendingIntent));
            return this;
        }

        public Builder addAction(Action action) {
            this.mActions.add(action);
            return this;
        }

        public Builder setStyle(Style style) {
            if (this.l != style) {
                this.l = style;
                if (this.l != null) {
                    this.l.setBuilder(this);
                }
            }
            return this;
        }

        public Builder setColor(@ColorInt int i) {
            this.z = i;
            return this;
        }

        public Builder setVisibility(int i) {
            this.A = i;
            return this;
        }

        public Builder setPublicVersion(Notification notification) {
            this.B = notification;
            return this;
        }

        public Builder setCustomContentView(RemoteViews remoteViews) {
            this.C = remoteViews;
            return this;
        }

        public Builder setCustomBigContentView(RemoteViews remoteViews) {
            this.D = remoteViews;
            return this;
        }

        public Builder setCustomHeadsUpContentView(RemoteViews remoteViews) {
            this.E = remoteViews;
            return this;
        }

        public Builder setChannelId(@NonNull String str) {
            this.F = str;
            return this;
        }

        public Builder setTimeoutAfter(long j) {
            this.I = j;
            return this;
        }

        public Builder setShortcutId(String str) {
            this.H = str;
            return this;
        }

        public Builder setBadgeIconType(int i) {
            this.G = i;
            return this;
        }

        public Builder setGroupAlertBehavior(int i) {
            this.J = i;
            return this;
        }

        public Builder extend(Extender extender) {
            extender.extend(this);
            return this;
        }

        @Deprecated
        public Notification getNotification() {
            return build();
        }

        public Notification build() {
            return new NotificationCompatBuilder(this).build();
        }

        protected static CharSequence a(CharSequence charSequence) {
            if (charSequence != null && charSequence.length() > com.baidu.mobstat.Config.MAX_CACHE_JSON_CAPACIT_EXCEPTION) {
                return charSequence.subSequence(0, com.baidu.mobstat.Config.MAX_CACHE_JSON_CAPACIT_EXCEPTION);
            }
            return charSequence;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews getContentView() {
            return this.C;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews getBigContentView() {
            return this.D;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews getHeadsUpContentView() {
            return this.E;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public long getWhenIfShowing() {
            return this.j ? this.K.when : 0;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public int getPriority() {
            return this.i;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public int getColor() {
            return this.z;
        }
    }

    public interface Extender {
        Builder extend(Builder builder);
    }

    public static final class CarExtender implements Extender {
        private Bitmap a;
        private UnreadConversation b;
        private int c = 0;

        public static class UnreadConversation {
            private final String[] a;
            private final RemoteInput b;
            private final PendingIntent c;
            private final PendingIntent d;
            private final String[] e;
            private final long f;

            public static class Builder {
                private final List<String> a = new ArrayList();
                private final String b;
                private RemoteInput c;
                private PendingIntent d;
                private PendingIntent e;
                private long f;

                public Builder(String str) {
                    this.b = str;
                }

                public Builder addMessage(String str) {
                    this.a.add(str);
                    return this;
                }

                public Builder setReplyAction(PendingIntent pendingIntent, RemoteInput remoteInput) {
                    this.c = remoteInput;
                    this.e = pendingIntent;
                    return this;
                }

                public Builder setReadPendingIntent(PendingIntent pendingIntent) {
                    this.d = pendingIntent;
                    return this;
                }

                public Builder setLatestTimestamp(long j) {
                    this.f = j;
                    return this;
                }

                public UnreadConversation build() {
                    return new UnreadConversation((String[]) this.a.toArray(new String[this.a.size()]), this.c, this.e, this.d, new String[]{this.b}, this.f);
                }
            }

            UnreadConversation(String[] strArr, RemoteInput remoteInput, PendingIntent pendingIntent, PendingIntent pendingIntent2, String[] strArr2, long j) {
                this.a = strArr;
                this.b = remoteInput;
                this.d = pendingIntent2;
                this.c = pendingIntent;
                this.e = strArr2;
                this.f = j;
            }

            public String[] getMessages() {
                return this.a;
            }

            public RemoteInput getRemoteInput() {
                return this.b;
            }

            public PendingIntent getReplyPendingIntent() {
                return this.c;
            }

            public PendingIntent getReadPendingIntent() {
                return this.d;
            }

            public String[] getParticipants() {
                return this.e;
            }

            public String getParticipant() {
                return this.e.length > 0 ? this.e[0] : null;
            }

            public long getLatestTimestamp() {
                return this.f;
            }
        }

        public CarExtender(Notification notification) {
            if (VERSION.SDK_INT >= 21) {
                Bundle bundle;
                if (NotificationCompat.getExtras(notification) == null) {
                    bundle = null;
                } else {
                    bundle = NotificationCompat.getExtras(notification).getBundle("android.car.EXTENSIONS");
                }
                if (bundle != null) {
                    this.a = (Bitmap) bundle.getParcelable("large_icon");
                    this.c = bundle.getInt("app_color", 0);
                    this.b = a(bundle.getBundle("car_conversation"));
                }
            }
        }

        @RequiresApi(21)
        private static UnreadConversation a(@Nullable Bundle bundle) {
            Object obj = null;
            if (bundle == null) {
                return null;
            }
            String[] strArr;
            Parcelable[] parcelableArray = bundle.getParcelableArray(DatabaseHelper.TABLE_MESSAGES);
            if (parcelableArray != null) {
                String[] strArr2 = new String[parcelableArray.length];
                for (int i = 0; i < strArr2.length; i++) {
                    if (!(parcelableArray[i] instanceof Bundle)) {
                        break;
                    }
                    strArr2[i] = ((Bundle) parcelableArray[i]).getString("text");
                    if (strArr2[i] == null) {
                        break;
                    }
                }
                int i2 = 1;
                if (obj == null) {
                    return null;
                }
                strArr = strArr2;
            } else {
                strArr = null;
            }
            PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable("on_read");
            PendingIntent pendingIntent2 = (PendingIntent) bundle.getParcelable("on_reply");
            RemoteInput remoteInput = (RemoteInput) bundle.getParcelable("remote_input");
            String[] stringArray = bundle.getStringArray("participants");
            if (stringArray == null || stringArray.length != 1) {
                return null;
            }
            return new UnreadConversation(strArr, remoteInput != null ? new RemoteInput(remoteInput.getResultKey(), remoteInput.getLabel(), remoteInput.getChoices(), remoteInput.getAllowFreeFormInput(), remoteInput.getExtras(), null) : null, pendingIntent2, pendingIntent, stringArray, bundle.getLong("timestamp"));
        }

        @RequiresApi(21)
        private static Bundle a(@NonNull UnreadConversation unreadConversation) {
            int i = 0;
            Bundle bundle = new Bundle();
            String str = null;
            if (unreadConversation.getParticipants() != null && unreadConversation.getParticipants().length > 1) {
                str = unreadConversation.getParticipants()[0];
            }
            Parcelable[] parcelableArr = new Parcelable[unreadConversation.getMessages().length];
            while (i < parcelableArr.length) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("text", unreadConversation.getMessages()[i]);
                bundle2.putString("author", str);
                parcelableArr[i] = bundle2;
                i++;
            }
            bundle.putParcelableArray(DatabaseHelper.TABLE_MESSAGES, parcelableArr);
            RemoteInput remoteInput = unreadConversation.getRemoteInput();
            if (remoteInput != null) {
                bundle.putParcelable("remote_input", new android.app.RemoteInput.Builder(remoteInput.getResultKey()).setLabel(remoteInput.getLabel()).setChoices(remoteInput.getChoices()).setAllowFreeFormInput(remoteInput.getAllowFreeFormInput()).addExtras(remoteInput.getExtras()).build());
            }
            bundle.putParcelable("on_reply", unreadConversation.getReplyPendingIntent());
            bundle.putParcelable("on_read", unreadConversation.getReadPendingIntent());
            bundle.putStringArray("participants", unreadConversation.getParticipants());
            bundle.putLong("timestamp", unreadConversation.getLatestTimestamp());
            return bundle;
        }

        public Builder extend(Builder builder) {
            if (VERSION.SDK_INT >= 21) {
                Bundle bundle = new Bundle();
                if (this.a != null) {
                    bundle.putParcelable("large_icon", this.a);
                }
                if (this.c != 0) {
                    bundle.putInt("app_color", this.c);
                }
                if (this.b != null) {
                    bundle.putBundle("car_conversation", a(this.b));
                }
                builder.getExtras().putBundle("android.car.EXTENSIONS", bundle);
            }
            return builder;
        }

        public CarExtender setColor(@ColorInt int i) {
            this.c = i;
            return this;
        }

        @ColorInt
        public int getColor() {
            return this.c;
        }

        public CarExtender setLargeIcon(Bitmap bitmap) {
            this.a = bitmap;
            return this;
        }

        public Bitmap getLargeIcon() {
            return this.a;
        }

        public CarExtender setUnreadConversation(UnreadConversation unreadConversation) {
            this.b = unreadConversation;
            return this;
        }

        public UnreadConversation getUnreadConversation() {
            return this.b;
        }
    }

    public static class DecoratedCustomViewStyle extends Style {
        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 24) {
                notificationBuilderWithBuilderAccessor.getBuilder().setStyle(new android.app.Notification.DecoratedCustomViewStyle());
            }
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT < 24 && this.d.getContentView() != null) {
                return a(this.d.getContentView(), false);
            }
            return null;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews bigContentView = this.d.getBigContentView();
            if (bigContentView == null) {
                bigContentView = this.d.getContentView();
            }
            if (bigContentView != null) {
                return a(bigContentView, true);
            }
            return null;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews headsUpContentView = this.d.getHeadsUpContentView();
            RemoteViews contentView = headsUpContentView != null ? headsUpContentView : this.d.getContentView();
            if (headsUpContentView != null) {
                return a(contentView, true);
            }
            return null;
        }

        private RemoteViews a(RemoteViews remoteViews, boolean z) {
            boolean z2;
            int i;
            RemoteViews applyStandardTemplate = applyStandardTemplate(true, R.layout.notification_template_custom_big, false);
            applyStandardTemplate.removeAllViews(R.id.actions);
            if (z && this.d.mActions != null) {
                int min = Math.min(this.d.mActions.size(), 3);
                if (min > 0) {
                    for (int i2 = 0; i2 < min; i2++) {
                        applyStandardTemplate.addView(R.id.actions, a((Action) this.d.mActions.get(i2)));
                    }
                    z2 = true;
                    i = z2 ? 0 : 8;
                    applyStandardTemplate.setViewVisibility(R.id.actions, i);
                    applyStandardTemplate.setViewVisibility(R.id.action_divider, i);
                    buildIntoRemoteViews(applyStandardTemplate, remoteViews);
                    return applyStandardTemplate;
                }
            }
            z2 = false;
            if (z2) {
            }
            applyStandardTemplate.setViewVisibility(R.id.actions, i);
            applyStandardTemplate.setViewVisibility(R.id.action_divider, i);
            buildIntoRemoteViews(applyStandardTemplate, remoteViews);
            return applyStandardTemplate;
        }

        private RemoteViews a(Action action) {
            Object obj = action.actionIntent == null ? 1 : null;
            RemoteViews remoteViews = new RemoteViews(this.d.mContext.getPackageName(), obj != null ? R.layout.notification_action_tombstone : R.layout.notification_action);
            remoteViews.setImageViewBitmap(R.id.action_image, createColoredBitmap(action.getIcon(), this.d.mContext.getResources().getColor(R.color.notification_action_color_filter)));
            remoteViews.setTextViewText(R.id.action_text, action.title);
            if (obj == null) {
                remoteViews.setOnClickPendingIntent(R.id.action_container, action.actionIntent);
            }
            if (VERSION.SDK_INT >= 15) {
                remoteViews.setContentDescription(R.id.action_container, action.title);
            }
            return remoteViews;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GroupAlertBehavior {
    }

    public static class InboxStyle extends Style {
        private ArrayList<CharSequence> a = new ArrayList();

        public InboxStyle(Builder builder) {
            setBuilder(builder);
        }

        public InboxStyle setBigContentTitle(CharSequence charSequence) {
            this.e = Builder.a(charSequence);
            return this;
        }

        public InboxStyle setSummaryText(CharSequence charSequence) {
            this.f = Builder.a(charSequence);
            this.g = true;
            return this;
        }

        public InboxStyle addLine(CharSequence charSequence) {
            this.a.add(Builder.a(charSequence));
            return this;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (VERSION.SDK_INT >= 16) {
                android.app.Notification.InboxStyle bigContentTitle = new android.app.Notification.InboxStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.e);
                if (this.g) {
                    bigContentTitle.setSummaryText(this.f);
                }
                Iterator it = this.a.iterator();
                while (it.hasNext()) {
                    bigContentTitle.addLine((CharSequence) it.next());
                }
            }
        }
    }

    public static class MessagingStyle extends Style {
        public static final int MAXIMUM_RETAINED_MESSAGES = 25;
        CharSequence a;
        CharSequence b;
        List<Message> c = new ArrayList();

        public static final class Message {
            private final CharSequence a;
            private final long b;
            private final CharSequence c;
            private Bundle d = new Bundle();
            private String e;
            private Uri f;

            public Message(CharSequence charSequence, long j, CharSequence charSequence2) {
                this.a = charSequence;
                this.b = j;
                this.c = charSequence2;
            }

            public Message setData(String str, Uri uri) {
                this.e = str;
                this.f = uri;
                return this;
            }

            public CharSequence getText() {
                return this.a;
            }

            public long getTimestamp() {
                return this.b;
            }

            public Bundle getExtras() {
                return this.d;
            }

            public CharSequence getSender() {
                return this.c;
            }

            public String getDataMimeType() {
                return this.e;
            }

            public Uri getDataUri() {
                return this.f;
            }

            private Bundle a() {
                Bundle bundle = new Bundle();
                if (this.a != null) {
                    bundle.putCharSequence("text", this.a);
                }
                bundle.putLong("time", this.b);
                if (this.c != null) {
                    bundle.putCharSequence("sender", this.c);
                }
                if (this.e != null) {
                    bundle.putString("type", this.e);
                }
                if (this.f != null) {
                    bundle.putParcelable("uri", this.f);
                }
                if (this.d != null) {
                    bundle.putBundle("extras", this.d);
                }
                return bundle;
            }

            static Bundle[] a(List<Message> list) {
                Bundle[] bundleArr = new Bundle[list.size()];
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    bundleArr[i] = ((Message) list.get(i)).a();
                }
                return bundleArr;
            }

            static List<Message> a(Parcelable[] parcelableArr) {
                List<Message> arrayList = new ArrayList(parcelableArr.length);
                for (int i = 0; i < parcelableArr.length; i++) {
                    if (parcelableArr[i] instanceof Bundle) {
                        Message a = a((Bundle) parcelableArr[i]);
                        if (a != null) {
                            arrayList.add(a);
                        }
                    }
                }
                return arrayList;
            }

            static Message a(Bundle bundle) {
                try {
                    if (!bundle.containsKey("text") || !bundle.containsKey("time")) {
                        return null;
                    }
                    Message message = new Message(bundle.getCharSequence("text"), bundle.getLong("time"), bundle.getCharSequence("sender"));
                    if (bundle.containsKey("type") && bundle.containsKey("uri")) {
                        message.setData(bundle.getString("type"), (Uri) bundle.getParcelable("uri"));
                    }
                    if (bundle.containsKey("extras")) {
                        message.getExtras().putAll(bundle.getBundle("extras"));
                    }
                    return message;
                } catch (ClassCastException e) {
                    return null;
                }
            }
        }

        MessagingStyle() {
        }

        public MessagingStyle(@NonNull CharSequence charSequence) {
            this.a = charSequence;
        }

        public CharSequence getUserDisplayName() {
            return this.a;
        }

        public MessagingStyle setConversationTitle(CharSequence charSequence) {
            this.b = charSequence;
            return this;
        }

        public CharSequence getConversationTitle() {
            return this.b;
        }

        public MessagingStyle addMessage(CharSequence charSequence, long j, CharSequence charSequence2) {
            this.c.add(new Message(charSequence, j, charSequence2));
            if (this.c.size() > 25) {
                this.c.remove(0);
            }
            return this;
        }

        public MessagingStyle addMessage(Message message) {
            this.c.add(message);
            if (this.c.size() > 25) {
                this.c.remove(0);
            }
            return this;
        }

        public List<Message> getMessages() {
            return this.c;
        }

        public static MessagingStyle extractMessagingStyleFromNotification(Notification notification) {
            Bundle extras = NotificationCompat.getExtras(notification);
            if (extras != null && !extras.containsKey(NotificationCompat.EXTRA_SELF_DISPLAY_NAME)) {
                return null;
            }
            try {
                MessagingStyle messagingStyle = new MessagingStyle();
                messagingStyle.a(extras);
                return messagingStyle;
            } catch (ClassCastException e) {
                return null;
            }
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Message a;
            if (VERSION.SDK_INT >= 24) {
                android.app.Notification.MessagingStyle conversationTitle = new android.app.Notification.MessagingStyle(this.a).setConversationTitle(this.b);
                for (Message a2 : this.c) {
                    android.app.Notification.MessagingStyle.Message message = new android.app.Notification.MessagingStyle.Message(a2.getText(), a2.getTimestamp(), a2.getSender());
                    if (a2.getDataMimeType() != null) {
                        message.setData(a2.getDataMimeType(), a2.getDataUri());
                    }
                    conversationTitle.addMessage(message);
                }
                conversationTitle.setBuilder(notificationBuilderWithBuilderAccessor.getBuilder());
                return;
            }
            CharSequence a3;
            a2 = a();
            if (this.b != null) {
                notificationBuilderWithBuilderAccessor.getBuilder().setContentTitle(this.b);
            } else if (a2 != null) {
                notificationBuilderWithBuilderAccessor.getBuilder().setContentTitle(a2.getSender());
            }
            if (a2 != null) {
                android.app.Notification.Builder builder = notificationBuilderWithBuilderAccessor.getBuilder();
                if (this.b != null) {
                    a3 = a(a2);
                } else {
                    a3 = a2.getText();
                }
                builder.setContentText(a3);
            }
            if (VERSION.SDK_INT >= 16) {
                int i;
                CharSequence spannableStringBuilder = new SpannableStringBuilder();
                if (this.b != null || b()) {
                    i = 1;
                } else {
                    i = 0;
                }
                for (int size = this.c.size() - 1; size >= 0; size--) {
                    a2 = (Message) this.c.get(size);
                    a3 = i != 0 ? a(a2) : a2.getText();
                    if (size != this.c.size() - 1) {
                        spannableStringBuilder.insert(0, "\n");
                    }
                    spannableStringBuilder.insert(0, a3);
                }
                new android.app.Notification.BigTextStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(null).bigText(spannableStringBuilder);
            }
        }

        @Nullable
        private Message a() {
            for (int size = this.c.size() - 1; size >= 0; size--) {
                Message message = (Message) this.c.get(size);
                if (!TextUtils.isEmpty(message.getSender())) {
                    return message;
                }
            }
            if (this.c.isEmpty()) {
                return null;
            }
            return (Message) this.c.get(this.c.size() - 1);
        }

        private boolean b() {
            for (int size = this.c.size() - 1; size >= 0; size--) {
                if (((Message) this.c.get(size)).getSender() == null) {
                    return true;
                }
            }
            return false;
        }

        private CharSequence a(Message message) {
            int i;
            CharSequence charSequence;
            BidiFormatter instance = BidiFormatter.getInstance();
            CharSequence spannableStringBuilder = new SpannableStringBuilder();
            Object obj = VERSION.SDK_INT >= 21 ? 1 : null;
            int i2 = obj != null ? -16777216 : -1;
            CharSequence sender = message.getSender();
            if (TextUtils.isEmpty(message.getSender())) {
                String str = this.a == null ? "" : this.a;
                if (!(obj == null || this.d.getColor() == 0)) {
                    i2 = this.d.getColor();
                }
                String str2 = str;
                i = i2;
                charSequence = str2;
            } else {
                CharSequence charSequence2 = sender;
                i = i2;
                charSequence = charSequence2;
            }
            charSequence = instance.unicodeWrap(charSequence);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.setSpan(a(i), spannableStringBuilder.length() - charSequence.length(), spannableStringBuilder.length(), 33);
            spannableStringBuilder.append("  ").append(instance.unicodeWrap(message.getText() == null ? "" : message.getText()));
            return spannableStringBuilder;
        }

        @NonNull
        private TextAppearanceSpan a(int i) {
            return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(i), null);
        }

        public void addCompatExtras(Bundle bundle) {
            super.addCompatExtras(bundle);
            if (this.a != null) {
                bundle.putCharSequence(NotificationCompat.EXTRA_SELF_DISPLAY_NAME, this.a);
            }
            if (this.b != null) {
                bundle.putCharSequence(NotificationCompat.EXTRA_CONVERSATION_TITLE, this.b);
            }
            if (!this.c.isEmpty()) {
                bundle.putParcelableArray(NotificationCompat.EXTRA_MESSAGES, Message.a(this.c));
            }
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        protected void a(Bundle bundle) {
            this.c.clear();
            this.a = bundle.getString(NotificationCompat.EXTRA_SELF_DISPLAY_NAME);
            this.b = bundle.getString(NotificationCompat.EXTRA_CONVERSATION_TITLE);
            Parcelable[] parcelableArray = bundle.getParcelableArray(NotificationCompat.EXTRA_MESSAGES);
            if (parcelableArray != null) {
                this.c = Message.a(parcelableArray);
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NotificationVisibility {
    }

    public static final class WearableExtender implements Extender {
        public static final int SCREEN_TIMEOUT_LONG = -1;
        public static final int SCREEN_TIMEOUT_SHORT = 0;
        public static final int SIZE_DEFAULT = 0;
        public static final int SIZE_FULL_SCREEN = 5;
        public static final int SIZE_LARGE = 4;
        public static final int SIZE_MEDIUM = 3;
        public static final int SIZE_SMALL = 2;
        public static final int SIZE_XSMALL = 1;
        public static final int UNSET_ACTION_INDEX = -1;
        private ArrayList<Action> a = new ArrayList();
        private int b = 1;
        private PendingIntent c;
        private ArrayList<Notification> d = new ArrayList();
        private Bitmap e;
        private int f;
        private int g = GravityCompat.END;
        private int h = -1;
        private int i = 0;
        private int j;
        private int k = 80;
        private int l;
        private String m;
        private String n;

        public WearableExtender(Notification notification) {
            Bundle bundle;
            Bundle extras = NotificationCompat.getExtras(notification);
            if (extras != null) {
                bundle = extras.getBundle("android.wearable.EXTENSIONS");
            } else {
                bundle = null;
            }
            if (bundle != null) {
                ArrayList parcelableArrayList = bundle.getParcelableArrayList("actions");
                if (VERSION.SDK_INT >= 16 && parcelableArrayList != null) {
                    Action[] actionArr = new Action[parcelableArrayList.size()];
                    for (int i = 0; i < actionArr.length; i++) {
                        if (VERSION.SDK_INT >= 20) {
                            actionArr[i] = NotificationCompat.a((android.app.Notification.Action) parcelableArrayList.get(i));
                        } else if (VERSION.SDK_INT >= 16) {
                            actionArr[i] = NotificationCompatJellybean.a((Bundle) parcelableArrayList.get(i));
                        }
                    }
                    Collections.addAll(this.a, actionArr);
                }
                this.b = bundle.getInt("flags", 1);
                this.c = (PendingIntent) bundle.getParcelable("displayIntent");
                Notification[] a = NotificationCompat.a(bundle, b.s);
                if (a != null) {
                    Collections.addAll(this.d, a);
                }
                this.e = (Bitmap) bundle.getParcelable("background");
                this.f = bundle.getInt("contentIcon");
                this.g = bundle.getInt("contentIconGravity", GravityCompat.END);
                this.h = bundle.getInt("contentActionIndex", -1);
                this.i = bundle.getInt("customSizePreset", 0);
                this.j = bundle.getInt("customContentHeight");
                this.k = bundle.getInt("gravity", 80);
                this.l = bundle.getInt("hintScreenTimeout");
                this.m = bundle.getString("dismissalId");
                this.n = bundle.getString("bridgeTag");
            }
        }

        public Builder extend(Builder builder) {
            Bundle bundle = new Bundle();
            if (!this.a.isEmpty()) {
                if (VERSION.SDK_INT >= 16) {
                    ArrayList arrayList = new ArrayList(this.a.size());
                    Iterator it = this.a.iterator();
                    while (it.hasNext()) {
                        Action action = (Action) it.next();
                        if (VERSION.SDK_INT >= 20) {
                            arrayList.add(a(action));
                        } else if (VERSION.SDK_INT >= 16) {
                            arrayList.add(NotificationCompatJellybean.a(action));
                        }
                    }
                    bundle.putParcelableArrayList("actions", arrayList);
                } else {
                    bundle.putParcelableArrayList("actions", null);
                }
            }
            if (this.b != 1) {
                bundle.putInt("flags", this.b);
            }
            if (this.c != null) {
                bundle.putParcelable("displayIntent", this.c);
            }
            if (!this.d.isEmpty()) {
                bundle.putParcelableArray(b.s, (Parcelable[]) this.d.toArray(new Notification[this.d.size()]));
            }
            if (this.e != null) {
                bundle.putParcelable("background", this.e);
            }
            if (this.f != 0) {
                bundle.putInt("contentIcon", this.f);
            }
            if (this.g != GravityCompat.END) {
                bundle.putInt("contentIconGravity", this.g);
            }
            if (this.h != -1) {
                bundle.putInt("contentActionIndex", this.h);
            }
            if (this.i != 0) {
                bundle.putInt("customSizePreset", this.i);
            }
            if (this.j != 0) {
                bundle.putInt("customContentHeight", this.j);
            }
            if (this.k != 80) {
                bundle.putInt("gravity", this.k);
            }
            if (this.l != 0) {
                bundle.putInt("hintScreenTimeout", this.l);
            }
            if (this.m != null) {
                bundle.putString("dismissalId", this.m);
            }
            if (this.n != null) {
                bundle.putString("bridgeTag", this.n);
            }
            builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
            return builder;
        }

        @RequiresApi(20)
        private static android.app.Notification.Action a(Action action) {
            Bundle bundle;
            android.app.Notification.Action.Builder builder = new android.app.Notification.Action.Builder(action.getIcon(), action.getTitle(), action.getActionIntent());
            if (action.getExtras() != null) {
                bundle = new Bundle(action.getExtras());
            } else {
                bundle = new Bundle();
            }
            bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
            if (VERSION.SDK_INT >= 24) {
                builder.setAllowGeneratedReplies(action.getAllowGeneratedReplies());
            }
            builder.addExtras(bundle);
            RemoteInput[] remoteInputs = action.getRemoteInputs();
            if (remoteInputs != null) {
                for (RemoteInput addRemoteInput : RemoteInput.a(remoteInputs)) {
                    builder.addRemoteInput(addRemoteInput);
                }
            }
            return builder.build();
        }

        public WearableExtender clone() {
            WearableExtender wearableExtender = new WearableExtender();
            wearableExtender.a = new ArrayList(this.a);
            wearableExtender.b = this.b;
            wearableExtender.c = this.c;
            wearableExtender.d = new ArrayList(this.d);
            wearableExtender.e = this.e;
            wearableExtender.f = this.f;
            wearableExtender.g = this.g;
            wearableExtender.h = this.h;
            wearableExtender.i = this.i;
            wearableExtender.j = this.j;
            wearableExtender.k = this.k;
            wearableExtender.l = this.l;
            wearableExtender.m = this.m;
            wearableExtender.n = this.n;
            return wearableExtender;
        }

        public WearableExtender addAction(Action action) {
            this.a.add(action);
            return this;
        }

        public WearableExtender addActions(List<Action> list) {
            this.a.addAll(list);
            return this;
        }

        public WearableExtender clearActions() {
            this.a.clear();
            return this;
        }

        public List<Action> getActions() {
            return this.a;
        }

        public WearableExtender setDisplayIntent(PendingIntent pendingIntent) {
            this.c = pendingIntent;
            return this;
        }

        public PendingIntent getDisplayIntent() {
            return this.c;
        }

        public WearableExtender addPage(Notification notification) {
            this.d.add(notification);
            return this;
        }

        public WearableExtender addPages(List<Notification> list) {
            this.d.addAll(list);
            return this;
        }

        public WearableExtender clearPages() {
            this.d.clear();
            return this;
        }

        public List<Notification> getPages() {
            return this.d;
        }

        public WearableExtender setBackground(Bitmap bitmap) {
            this.e = bitmap;
            return this;
        }

        public Bitmap getBackground() {
            return this.e;
        }

        public WearableExtender setContentIcon(int i) {
            this.f = i;
            return this;
        }

        public int getContentIcon() {
            return this.f;
        }

        public WearableExtender setContentIconGravity(int i) {
            this.g = i;
            return this;
        }

        public int getContentIconGravity() {
            return this.g;
        }

        public WearableExtender setContentAction(int i) {
            this.h = i;
            return this;
        }

        public int getContentAction() {
            return this.h;
        }

        public WearableExtender setGravity(int i) {
            this.k = i;
            return this;
        }

        public int getGravity() {
            return this.k;
        }

        public WearableExtender setCustomSizePreset(int i) {
            this.i = i;
            return this;
        }

        public int getCustomSizePreset() {
            return this.i;
        }

        public WearableExtender setCustomContentHeight(int i) {
            this.j = i;
            return this;
        }

        public int getCustomContentHeight() {
            return this.j;
        }

        public WearableExtender setStartScrollBottom(boolean z) {
            a(8, z);
            return this;
        }

        public boolean getStartScrollBottom() {
            return (this.b & 8) != 0;
        }

        public WearableExtender setContentIntentAvailableOffline(boolean z) {
            a(1, z);
            return this;
        }

        public boolean getContentIntentAvailableOffline() {
            return (this.b & 1) != 0;
        }

        public WearableExtender setHintHideIcon(boolean z) {
            a(2, z);
            return this;
        }

        public boolean getHintHideIcon() {
            return (this.b & 2) != 0;
        }

        public WearableExtender setHintShowBackgroundOnly(boolean z) {
            a(4, z);
            return this;
        }

        public boolean getHintShowBackgroundOnly() {
            return (this.b & 4) != 0;
        }

        public WearableExtender setHintAvoidBackgroundClipping(boolean z) {
            a(16, z);
            return this;
        }

        public boolean getHintAvoidBackgroundClipping() {
            return (this.b & 16) != 0;
        }

        public WearableExtender setHintScreenTimeout(int i) {
            this.l = i;
            return this;
        }

        public int getHintScreenTimeout() {
            return this.l;
        }

        public WearableExtender setHintAmbientBigPicture(boolean z) {
            a(32, z);
            return this;
        }

        public boolean getHintAmbientBigPicture() {
            return (this.b & 32) != 0;
        }

        public WearableExtender setHintContentIntentLaunchesActivity(boolean z) {
            a(64, z);
            return this;
        }

        public boolean getHintContentIntentLaunchesActivity() {
            return (this.b & 64) != 0;
        }

        public WearableExtender setDismissalId(String str) {
            this.m = str;
            return this;
        }

        public String getDismissalId() {
            return this.m;
        }

        public WearableExtender setBridgeTag(String str) {
            this.n = str;
            return this;
        }

        public String getBridgeTag() {
            return this.n;
        }

        private void a(int i, boolean z) {
            if (z) {
                this.b |= i;
            } else {
                this.b &= i ^ -1;
            }
        }
    }

    static Notification[] a(Bundle bundle, String str) {
        Parcelable[] parcelableArray = bundle.getParcelableArray(str);
        if ((parcelableArray instanceof Notification[]) || parcelableArray == null) {
            return (Notification[]) parcelableArray;
        }
        Parcelable[] parcelableArr = new Notification[parcelableArray.length];
        for (int i = 0; i < parcelableArray.length; i++) {
            parcelableArr[i] = (Notification) parcelableArray[i];
        }
        bundle.putParcelableArray(str, parcelableArr);
        return parcelableArr;
    }

    public static Bundle getExtras(Notification notification) {
        if (VERSION.SDK_INT >= 19) {
            return notification.extras;
        }
        if (VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getExtras(notification);
        }
        return null;
    }

    public static int getActionCount(Notification notification) {
        if (VERSION.SDK_INT >= 19) {
            if (notification.actions != null) {
                return notification.actions.length;
            }
            return 0;
        } else if (VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getActionCount(notification);
        } else {
            return 0;
        }
    }

    public static Action getAction(Notification notification, int i) {
        Bundle bundle = null;
        if (VERSION.SDK_INT >= 20) {
            return a(notification.actions[i]);
        }
        if (VERSION.SDK_INT >= 19) {
            android.app.Notification.Action action = notification.actions[i];
            SparseArray sparseParcelableArray = notification.extras.getSparseParcelableArray(NotificationCompatExtras.EXTRA_ACTION_EXTRAS);
            if (sparseParcelableArray != null) {
                bundle = (Bundle) sparseParcelableArray.get(i);
            }
            return NotificationCompatJellybean.readAction(action.icon, action.title, action.actionIntent, bundle);
        } else if (VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getAction(notification, i);
        } else {
            return null;
        }
    }

    @RequiresApi(20)
    static Action a(android.app.Notification.Action action) {
        RemoteInput[] remoteInputArr;
        boolean z;
        boolean z2 = false;
        RemoteInput[] remoteInputs = action.getRemoteInputs();
        if (remoteInputs == null) {
            remoteInputArr = null;
        } else {
            RemoteInput[] remoteInputArr2 = new RemoteInput[remoteInputs.length];
            for (int i = 0; i < remoteInputs.length; i++) {
                RemoteInput remoteInput = remoteInputs[i];
                remoteInputArr2[i] = new RemoteInput(remoteInput.getResultKey(), remoteInput.getLabel(), remoteInput.getChoices(), remoteInput.getAllowFreeFormInput(), remoteInput.getExtras(), null);
            }
            remoteInputArr = remoteInputArr2;
        }
        if (VERSION.SDK_INT >= 24) {
            if (action.getExtras().getBoolean("android.support.allowGeneratedReplies") || action.getAllowGeneratedReplies()) {
                z2 = true;
            }
            z = z2;
        } else {
            z = action.getExtras().getBoolean("android.support.allowGeneratedReplies");
        }
        return new Action(action.icon, action.title, action.actionIntent, action.getExtras(), remoteInputArr, null, z);
    }

    public static String getCategory(Notification notification) {
        if (VERSION.SDK_INT >= 21) {
            return notification.category;
        }
        return null;
    }

    public static boolean getLocalOnly(Notification notification) {
        if (VERSION.SDK_INT >= 20) {
            if ((notification.flags & 256) != 0) {
                return true;
            }
            return false;
        } else if (VERSION.SDK_INT >= 19) {
            return notification.extras.getBoolean(NotificationCompatExtras.EXTRA_LOCAL_ONLY);
        } else {
            if (VERSION.SDK_INT >= 16) {
                return NotificationCompatJellybean.getExtras(notification).getBoolean(NotificationCompatExtras.EXTRA_LOCAL_ONLY);
            }
            return false;
        }
    }

    public static String getGroup(Notification notification) {
        if (VERSION.SDK_INT >= 20) {
            return notification.getGroup();
        }
        if (VERSION.SDK_INT >= 19) {
            return notification.extras.getString(NotificationCompatExtras.EXTRA_GROUP_KEY);
        }
        if (VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getExtras(notification).getString(NotificationCompatExtras.EXTRA_GROUP_KEY);
        }
        return null;
    }

    public static boolean isGroupSummary(Notification notification) {
        if (VERSION.SDK_INT >= 20) {
            if ((notification.flags & 512) != 0) {
                return true;
            }
            return false;
        } else if (VERSION.SDK_INT >= 19) {
            return notification.extras.getBoolean(NotificationCompatExtras.EXTRA_GROUP_SUMMARY);
        } else {
            if (VERSION.SDK_INT >= 16) {
                return NotificationCompatJellybean.getExtras(notification).getBoolean(NotificationCompatExtras.EXTRA_GROUP_SUMMARY);
            }
            return false;
        }
    }

    public static String getSortKey(Notification notification) {
        if (VERSION.SDK_INT >= 20) {
            return notification.getSortKey();
        }
        if (VERSION.SDK_INT >= 19) {
            return notification.extras.getString(NotificationCompatExtras.EXTRA_SORT_KEY);
        }
        if (VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getExtras(notification).getString(NotificationCompatExtras.EXTRA_SORT_KEY);
        }
        return null;
    }

    public static String getChannelId(Notification notification) {
        if (VERSION.SDK_INT >= 26) {
            return notification.getChannelId();
        }
        return null;
    }

    public static long getTimeoutAfter(Notification notification) {
        if (VERSION.SDK_INT >= 26) {
            return notification.getTimeoutAfter();
        }
        return 0;
    }

    public static int getBadgeIconType(Notification notification) {
        if (VERSION.SDK_INT >= 26) {
            return notification.getBadgeIconType();
        }
        return 0;
    }

    public static String getShortcutId(Notification notification) {
        if (VERSION.SDK_INT >= 26) {
            return notification.getShortcutId();
        }
        return null;
    }

    public static int getGroupAlertBehavior(Notification notification) {
        if (VERSION.SDK_INT >= 26) {
            return notification.getGroupAlertBehavior();
        }
        return 0;
    }
}
