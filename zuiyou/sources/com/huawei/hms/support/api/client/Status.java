package com.huawei.hms.support.api.client;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import java.util.Arrays;

public final class Status {
    public static final Status CoreException = new Status(500);
    public static final Status FAILURE = new Status(1);
    public static final Status MessageNotFound = new Status(404);
    public static final Status SUCCESS = new Status(0);
    private int a;
    private String b;
    private final PendingIntent c;

    public Status(int i) {
        this(i, null);
    }

    public Status(int i, String str) {
        this(i, str, null);
    }

    public Status(int i, String str, PendingIntent pendingIntent) {
        this.a = i;
        this.b = str;
        this.c = pendingIntent;
    }

    public boolean hasResolution() {
        return this.c != null;
    }

    public void startResolutionForResult(Activity activity, int i) throws SendIntentException {
        if (hasResolution()) {
            activity.startIntentSenderForResult(this.c.getIntentSender(), i, null, 0, 0, 0);
        }
    }

    public int getStatusCode() {
        return this.a;
    }

    public String getStatusMessage() {
        return this.b;
    }

    public PendingIntent getResolution() {
        return this.c;
    }

    public boolean isSuccess() {
        return this.a <= 0;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.a), this.b, this.c});
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        if (this.a == status.a && a(this.b, status.b) && a(this.c, status.c)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "{statusCode: " + this.a + ", " + "statusMessage: " + this.b + ", " + "pendingIntent: " + this.c + ", " + "}";
    }

    private static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }
}
